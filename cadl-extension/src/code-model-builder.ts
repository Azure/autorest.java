import {
  ArrayModelType,
  BooleanLiteral,
  Enum,
  getDoc,
  getEffectiveModelType,
  getFormat,
  getFriendlyName,
  getKnownValues,
  getSummary,
  getVisibility,
  ignoreDiagnostics,
  IntrinsicType,
  isArrayModelType,
  isRecordModelType,
  isTemplateDeclaration,
  isTemplateInstance,
  isUnknownType,
  Model,
  ModelProperty,
  NumericLiteral,
  Operation,
  Program,
  RecordModelType,
  StringLiteral,
  TemplatedTypeBase,
  Type,
  TypeNameOptions,
  Union,
  UnionVariant,
  getDiscriminator,
  isNeverType,
  Scalar,
  listServices,
  getNamespaceFullName,
  isNullType,
  NoTarget,
} from "@cadl-lang/compiler";
import { getResourceOperation, getSegment } from "@cadl-lang/rest";
import {
  getAuthentication,
  getHeaderFieldName,
  getPathParamName,
  getQueryParamName,
  getServers,
  getStatusCodeDescription,
  HttpOperation,
  HttpOperationParameter,
  HttpOperationResponse,
  HttpServer,
  isStatusCode,
  ServiceAuthentication,
  StatusCode,
  getHttpOperation,
} from "@cadl-lang/rest/http";
import { getVersion } from "@cadl-lang/versioning";
import { isPollingLocation, getPagedResult, getOperationLinks, isFixed } from "@azure-tools/cadl-azure-core";
import {
  getConvenienceAPIName,
  getDefaultApiVersion,
  listClients,
  listOperationGroups,
  listOperationsInOperationGroup,
} from "@azure-tools/cadl-dpg";
import { fail } from "assert";
import {
  AnySchema,
  ArraySchema,
  BinaryResponse,
  BinarySchema,
  BooleanSchema,
  ByteArraySchema,
  ChoiceValue,
  ConstantSchema,
  ConstantValue,
  DateTimeSchema,
  DateSchema,
  DictionarySchema,
  Discriminator,
  DurationSchema,
  HttpHeader,
  HttpParameter,
  ImplementationLocation,
  NumberSchema,
  ObjectSchema,
  Parameter,
  ParameterLocation,
  Property,
  Relations,
  Response,
  Schema,
  SchemaResponse,
  SchemaType,
  SecurityScheme,
  StringSchema,
  TimeSchema,
  Security,
  OAuth2SecurityScheme,
  KeySecurityScheme,
  OperationGroup,
  UriSchema,
  VirtualParameter,
  GroupSchema,
  GroupProperty,
} from "@autorest/codemodel";
import { CodeModel } from "./common/code-model.js";
import { Client as CodeModelClient } from "./common/client.js";
import { ConvenienceApi, Operation as CodeModelOperation, OperationLink, Request } from "./common/operation.js";
import { SchemaContext, SchemaUsage } from "./common/schemas/usage.js";
import { ChoiceSchema, SealedChoiceSchema } from "./common/schemas/choice.js";
import { OrSchema } from "./common/schemas/relationship.js";
import { PreNamer } from "./prenamer/prenamer.js";
import { EmitterOptions } from "./emitter.js";

export class CodeModelBuilder {
  private program: Program;
  private typeNameOptions: TypeNameOptions;
  private version: string;
  private baseUri: string;
  private hostParameters: Parameter[];
  private namespace: string;

  private options: EmitterOptions;

  private codeModel: CodeModel;

  private schemaCache = new ProcessingCache((type: Type, name: string) => this.processSchemaImpl(type, name));
  private operationCache = new Map<Operation, CodeModelOperation>();

  private specialHeaderNames = new Set(["repeatability-request-id", "repeatability-first-sent"]);

  public constructor(program1: Program, options: EmitterOptions) {
    this.options = options;
    this.program = program1;

    const service = listServices(this.program)[0];
    const serviceNamespace = service.type;
    if (serviceNamespace === undefined) {
      throw Error("Can not emit yaml for a namespace that doesn't exist.");
    }

    // java namespace
    this.namespace = getNamespaceFullName(serviceNamespace) || "Azure.Client";
    const javaNamespace = getJavaNamespace(this.namespace);

    // API version
    const apiVersion = getDefaultApiVersion(this.program, serviceNamespace);
    if (apiVersion) {
      this.version = apiVersion.value;
    } else {
      const versioning = getVersion(this.program, serviceNamespace);
      if (versioning) {
        // TODO: versioning support
        this.version = versioning.getVersions()[0].value;
      } else {
        if (service.version) {
          this.version = service.version;
        } else {
          throw new Error("API version not available.");
        }
      }
    }

    const namespace1 = this.namespace;
    this.typeNameOptions = {
      // shorten type names by removing Cadl and service namespace
      namespaceFilter(ns) {
        const name = getNamespaceFullName(ns);
        return name !== "Cadl" && name !== namespace1;
      },
    };

    // init code model
    // let title = getServiceTitle(this.program);
    // if (title === "(title)") {
    //   title = namespace;
    // }
    const title = serviceNamespace.name;

    const description = this.getDoc(serviceNamespace);
    this.codeModel = new CodeModel(title, false, {
      info: {
        description: description,
      },
      language: {
        default: {
          name: title,
          description: description,
          summary: this.getSummary(serviceNamespace),
          namespace: this.namespace,
        },
        java: {
          namespace: javaNamespace,
        },
      },
    });

    // host
    this.baseUri = "{endpoint}";
    const servers = getServers(this.program, serviceNamespace);
    // TODO: multiple servers
    if (servers && servers.length === 1) {
      this.baseUri = servers[0].url;
    }
    this.hostParameters = [];
    this.processHost(servers?.length === 1 ? servers[0] : undefined);

    const auth = getAuthentication(this.program, serviceNamespace);
    if (auth) {
      this.processAuth(auth);
    }
  }

  public build(): CodeModel {
    this.processClients();

    this.codeModel.schemas.objects?.forEach((it) => this.propagateSchemaUsage(it));

    if (this.options.namer) {
      this.codeModel = new PreNamer(this.codeModel).init().process();
    }

    return this.codeModel;
  }

  private processHost(server: HttpServer | undefined) {
    if (server) {
      server.parameters.forEach((it) => {
        let parameter;

        if (it.name === "ApiVersion") {
          // TODO hack on "ApiVersion"
          const schema = this.codeModel.schemas.add(
            new ConstantSchema(it.name, `api-version: ${this.version}`, {
              valueType: this.stringSchema,
              value: new ConstantValue(this.version),
            }),
          );
          parameter = new Parameter(it.name, this.getDoc(it), schema, {
            implementation: ImplementationLocation.Client,
            origin: "modelerfour:synthesized/api-version",
            required: true,
            protocol: {
              http: new HttpParameter(ParameterLocation.Uri),
            },
            clientDefaultValue: this.getDefaultValue(it.default),
            language: {
              default: {
                serializedName: it.name,
              },
            },
          });
        } else {
          const schema = this.processSchema(it.type, it.name);
          parameter = new Parameter(it.name, this.getDoc(it), schema, {
            implementation: ImplementationLocation.Client,
            origin: "modelerfour:synthesized/host",
            required: true,
            protocol: {
              http: new HttpParameter(ParameterLocation.Uri),
            },
            clientDefaultValue: this.getDefaultValue(it.default),
            language: {
              default: {
                serializedName: it.name,
              },
            },
          });
        }

        return this.hostParameters.push(this.codeModel.addGlobalParameter(parameter));
      });
    } else {
      this.hostParameters.push(
        this.codeModel.addGlobalParameter(
          new Parameter("endpoint", "Server parameter", this.stringSchema, {
            implementation: ImplementationLocation.Client,
            origin: "modelerfour:synthesized/host",
            required: true,
            protocol: {
              http: new HttpParameter(ParameterLocation.Uri),
            },
            language: {
              default: {
                serializedName: "endpoint",
              },
            },
            extensions: {
              "x-ms-skip-url-encoding": true,
            },
          }),
        ),
      );
    }
  }

  private processAuth(auth: ServiceAuthentication) {
    const securitySchemes: SecurityScheme[] = [];
    for (const option of auth.options) {
      for (const scheme of option.schemes) {
        switch (scheme.type) {
          case "oauth2":
            {
              const oauth2Scheme = new OAuth2SecurityScheme({
                scopes: [],
              });
              scheme.flows.forEach((it) => oauth2Scheme.scopes.push(...it.scopes.map((it) => it.value)));
              securitySchemes.push(oauth2Scheme);
            }
            break;

          case "apiKey":
            {
              const keyScheme = new KeySecurityScheme({
                name: scheme.name,
              });
              securitySchemes.push(keyScheme);
            }
            break;
        }
      }
    }
    if (securitySchemes.length > 0) {
      this.codeModel.security = new Security(true, {
        schemes: securitySchemes,
      });
    }
  }

  private processClients() {
    const clients = listClients(this.program);
    for (const client of clients) {
      const codeModelClient = new CodeModelClient(client.name, this.getDoc(client.type), {
        summary: this.getSummary(client.type),

        // at present, use global security definition
        security: this.codeModel.security,
      });

      const operationGroups = listOperationGroups(this.program, client);

      const operationWithoutGroup = listOperationsInOperationGroup(this.program, client);
      let codeModelGroup = new OperationGroup("");
      for (const operation of operationWithoutGroup) {
        codeModelGroup.addOperation(this.processRoute("", operation));
      }
      if (codeModelGroup.operations?.length > 0) {
        codeModelClient.operationGroups.push(codeModelGroup);
      }

      for (const operationGroup of operationGroups) {
        const operations = listOperationsInOperationGroup(this.program, operationGroup);
        codeModelGroup = new OperationGroup(operationGroup.type.name);
        for (const operation of operations) {
          codeModelGroup.addOperation(this.processRoute(operationGroup.type.name, operation));
        }
        codeModelClient.operationGroups.push(codeModelGroup);
      }

      this.codeModel.clients.push(codeModelClient);
    }
  }

  private processRoute(groupName: string, operation: Operation): CodeModelOperation {
    const op = ignoreDiagnostics(getHttpOperation(this.program, operation));

    const operationGroup = this.codeModel.getOperationGroup(groupName);
    const opId = groupName ? `${groupName}_${operation.name}` : `${operation.name}`;

    const codeModelOperation = new CodeModelOperation(operation.name, this.getDoc(operation), {
      operationId: opId,
      summary: this.getSummary(operation),
      apiVersions: [
        {
          version: this.version,
        },
      ],
    });

    if (!operationContainsJsonMergePatch(op)) {
      // do not generate convenience method for JSON Merge Patch

      const convenienceApiName = this.getConvenienceApiName(operation);
      if (convenienceApiName) {
        codeModelOperation.convenienceApi = new ConvenienceApi(convenienceApiName);
      } else if (!this.options["dev-options"] || (this.options["dev-options"]["generate-convenience-apis"] ?? true)) {
        // add convenienceApi by default, unless devOptions says no
        codeModelOperation.convenienceApi = new ConvenienceApi(operation.name);
      }
    }

    // cache for later reference from operationLinks
    this.operationCache.set(operation, codeModelOperation);

    codeModelOperation.addRequest(
      new Request({
        protocol: {
          http: {
            path: op.path,
            method: op.verb,
            uri: this.baseUri,
          },
        },
      }),
    );

    // host
    this.hostParameters.forEach((it) => codeModelOperation.addParameter(it));
    // parameters
    op.parameters.parameters.map((it) => this.processParameter(codeModelOperation, it));
    // "accept" header
    this.addAcceptHeaderParameter(codeModelOperation, op.responses);
    // body
    if (op.parameters.body) {
      if (op.parameters.body.parameter) {
        this.processParameterBody(codeModelOperation, op.parameters.body.parameter, operation.parameters);
      } else if (op.parameters.body.type) {
        let bodyType = this.getEffectiveSchemaType(op.parameters.body.type);

        if (bodyType.kind === "Model") {
          // try use resource type as round-trip model
          const resourceType = getResourceOperation(this.program, operation)?.resourceType;
          if (resourceType && op.responses && op.responses.length > 0) {
            const resp = op.responses[0];
            if (resp.responses && resp.responses.length > 0 && resp.responses[0].body) {
              const responseBody = resp.responses[0].body;
              const bodyTypeInResponse = this.findResponseBody(responseBody.type);
              // response body type is resource type, and request body type (if templated) contains resource type
              if (bodyTypeInResponse === resourceType && isModelReferredInTemplate(bodyType, resourceType)) {
                bodyType = resourceType;
              }
            }
          }

          this.processParameterBody(codeModelOperation, bodyType, operation.parameters);
        }
      }
    }

    // responses
    op.responses.map((it) => this.processResponse(codeModelOperation, it));

    this.processRouteForPaged(codeModelOperation, op.responses);
    this.processRouteForLongRunning(codeModelOperation, groupName, operation, op.responses);

    operationGroup.addOperation(codeModelOperation);

    return codeModelOperation;
  }

  private processRouteForPaged(op: CodeModelOperation, responses: HttpOperationResponse[]) {
    for (const response of responses) {
      if (response.responses && response.responses.length > 0 && response.responses[0].body) {
        const responseBody = response.responses[0].body;
        const bodyType = this.findResponseBody(responseBody.type);
        if (bodyType.kind === "Model") {
          const pagedResult = getPagedResult(this.program, bodyType);
          if (pagedResult) {
            op.extensions = op.extensions ?? {};
            op.extensions["x-ms-pageable"] = {
              itemName: pagedResult.itemsProperty?.name,
              nextLinkName: pagedResult.nextLinkProperty?.name,
            };

            op.responses?.forEach((r) => {
              if (r instanceof SchemaResponse) {
                this.trackSchemaUsage(r.schema, { usage: [SchemaContext.Paged] });
              }
            });

            break;
          }
        }
      }
    }
  }

  private processRouteForLongRunning(
    op: CodeModelOperation,
    groupName: string,
    operation: Operation,
    responses: HttpOperationResponse[],
  ) {
    let pollingFoundInOperationLinks = false;
    const operationLinks = getOperationLinks(this.program, operation);
    if (operationLinks) {
      op.operationLinks = {};

      for (const [linkType, linkOperation] of operationLinks) {
        if (linkType === "polling" || linkType === "final") {
          pollingFoundInOperationLinks = true;
        }

        if (linkOperation.linkedOperation) {
          // Cadl requires linked operation written before
          if (!this.operationCache.get(linkOperation.linkedOperation)) {
            this.processRoute(groupName, linkOperation.linkedOperation);
          }
          const opLink = new OperationLink(this.operationCache.get(linkOperation.linkedOperation)!);
          // parameters of operation link
          if (linkOperation.parameters) {
            opLink.parameters = this.processSchema(linkOperation.parameters, "parameters");
          }
          op.operationLinks[linkType] = opLink;
        }
      }
    }
    if (pollingFoundInOperationLinks) {
      op.extensions = op.extensions ?? {};
      op.extensions["x-ms-long-running-operation"] = true;
      return;
    }

    for (const resp of responses) {
      if (resp.responses && resp.responses.length > 0 && resp.responses[0].headers) {
        for (const [_, header] of Object.entries(resp.responses[0].headers)) {
          if (isPollingLocation(this.program, header)) {
            op.extensions = op.extensions ?? {};
            op.extensions["x-ms-long-running-operation"] = true;

            break;
          }
        }
      }
    }
  }

  private processParameter(op: CodeModelOperation, param: HttpOperationParameter) {
    if (param.name.toLowerCase() === "api-version") {
      // TODO hack on "api-version"
      const parameter = this.apiVersionParameter;
      op.addParameter(parameter);
    } else if (this.specialHeaderNames.has(param.name.toLowerCase())) {
      // special headers
      op.specialHeaders = op.specialHeaders ?? [];
      if (!containsIgnoreCase(op.specialHeaders, param.name)) {
        op.specialHeaders.push(param.name);
      }
    } else {
      let schema;
      if (param.param.type.kind === "Scalar" && param.param.type.name === "zonedDateTime") {
        // zonedTateTime in header maps to RFC 5322
        schema = this.processDateTimeSchema(param.param.type, param.param.name, true);
      } else {
        schema = this.processSchema(param.param.type, param.param.name);
      }
      const nullable = this.isNullableType(param.param.type);
      const parameter = new Parameter(param.param.name, this.getDoc(param.param), schema, {
        summary: this.getSummary(param.param),
        implementation: ImplementationLocation.Method,
        required: !param.param.optional,
        nullable: nullable,
        protocol: {
          http: new HttpParameter(param.type),
        },
        // clientDefaultValue: this.getDefaultValue(param.param.default),
        language: {
          default: {
            serializedName: param.name,
          },
        },
      });
      op.addParameter(parameter);

      this.trackSchemaUsage(schema, { usage: [SchemaContext.Input] });

      if (op.convenienceApi) {
        this.trackSchemaUsage(schema, { usage: [SchemaContext.ConvenienceApi] });
      }

      if (param.name.toLowerCase() === "content-type") {
        let mediaTypes = ["application/json"];
        if (schema instanceof ConstantSchema) {
          mediaTypes = [schema.value.value.toString()];
        } else if (schema instanceof SealedChoiceSchema) {
          mediaTypes = schema.choices.map((it) => it.value.toString());
        }
        op.requests![0].protocol.http!.mediaTypes = mediaTypes;
      }
    }
  }

  private addAcceptHeaderParameter(op: CodeModelOperation, responses: HttpOperationResponse[]) {
    const produces = new Set<string>(["application/json"]);
    for (const resp of responses) {
      if (resp.responses && resp.responses.length > 0) {
        for (const response of resp.responses) {
          response.body?.contentTypes.forEach((it) => produces.add(it));
        }
      }
    }
    const acceptTypes = Array.from(produces.values()).join(", ");

    const acceptSchema =
      this.codeModel.schemas.constants?.find(
        (it) => it.language.default.name === "accept" && it.value.value === acceptTypes,
      ) ||
      this.codeModel.schemas.add(
        new ConstantSchema("accept", `Accept: ${acceptTypes}`, {
          valueType: this.stringSchema,
          value: new ConstantValue(acceptTypes),
        }),
      );
    op.addParameter(
      new Parameter("accept", "Accept header", acceptSchema, {
        implementation: ImplementationLocation.Method,
        origin: "modelerfour:synthesized/accept",
        required: true,
        protocol: {
          http: new HttpParameter(ParameterLocation.Header),
        },
        language: {
          default: {
            serializedName: "accept",
          },
        },
      }),
    );
  }

  private processParameterBody(op: CodeModelOperation, body: ModelProperty | Model, parameters: Model) {
    const schema = this.processSchema(body.kind === "Model" ? body : body.type, body.name);
    const parameter = new Parameter(body.name, this.getDoc(body), schema, {
      summary: this.getSummary(body),
      implementation: ImplementationLocation.Method,
      required: body.kind === "Model" || !body.optional,
      protocol: {
        http: new HttpParameter(ParameterLocation.Body),
      },
      // clientDefaultValue: this.getDefaultValue(body.default),
    });
    op.addParameter(parameter);

    this.trackSchemaUsage(schema, { usage: [SchemaContext.Input] });

    if (op.convenienceApi) {
      this.trackSchemaUsage(schema, { usage: [SchemaContext.ConvenienceApi] });
    }

    if (!schema.language.default.name && schema instanceof ObjectSchema) {
      // anonymous model

      if (!parameter.language.default.name) {
        // name the parameter for documentation
        parameter.language.default.name = "request";
      }
      this.trackSchemaUsage(schema, { usage: [SchemaContext.Anonymous] });

      if (op.convenienceApi && op.parameters) {
        op.convenienceApi.requests = [];
        const request = new Request();
        request.parameters = [];
        op.convenienceApi.requests.push(request);

        for (const [key, _] of parameters.properties) {
          const existParameter = op.parameters.find((it) => it.language.default.serializedName === key);
          if (existParameter) {
            // parameter
            if (
              existParameter.implementation === ImplementationLocation.Method &&
              (existParameter.origin?.startsWith("modelerfour:synthesized/") ?? true)
            ) {
              request.parameters.push(
                new Parameter(
                  existParameter.language.default.name,
                  existParameter.language.default.description,
                  existParameter.schema,
                  {
                    language: {
                      default: {
                        serializedName: existParameter.language.default.serializedName,
                      },
                    },
                    protocol: existParameter.protocol,
                    summary: existParameter.summary,
                    implementation: ImplementationLocation.Method,
                    required: existParameter.required,
                    nullable: existParameter.nullable,
                  },
                ),
              );
            }
          } else {
            // property from anonymous model
            const existBodyProperty = schema.properties?.find((it) => it.serializedName === key);
            if (existBodyProperty) {
              request.parameters.push(
                new VirtualParameter(
                  existBodyProperty.language.default.name,
                  existBodyProperty.language.default.description,
                  existBodyProperty.schema,
                  {
                    originalParameter: parameter,
                    targetProperty: existBodyProperty,
                    language: {
                      default: {
                        serializedName: existBodyProperty.serializedName,
                      },
                    },
                    summary: existBodyProperty.summary,
                    implementation: ImplementationLocation.Method,
                    required: existBodyProperty.required,
                    nullable: existBodyProperty.nullable,
                  },
                ),
              );
            }
          }
        }
        request.signatureParameters = request.parameters;

        if (request.signatureParameters.length > 6) {
          // create an option bag
          const name = op.language.default.name + "Options";
          const namespace = body.kind === "Model" ? getNamespace(body) : this.namespace;
          // option bag schema
          const optionBagSchema = this.codeModel.schemas.add(
            new GroupSchema(name, `Options for ${op.language.default.name} API`, {
              language: {
                default: {
                  namespace: namespace,
                },
                java: {
                  namespace: getJavaNamespace(namespace),
                },
              },
            }),
          );
          request.parameters.forEach((it) => {
            optionBagSchema.add(
              new GroupProperty(it.language.default.name, it.language.default.description, it.schema, {
                originalParameter: [it],
                summary: it.summary,
                required: it.required,
                nullable: it.nullable,
                readOnly: false,
                serializedName: it.language.default.serializedName,
              }),
            );
          });

          this.trackSchemaUsage(optionBagSchema, { usage: [SchemaContext.Input, SchemaContext.ConvenienceApi] });

          // option bag parameter
          const optionBagParameter = new Parameter(
            "options",
            optionBagSchema.language.default.description,
            optionBagSchema,
            {
              implementation: ImplementationLocation.Method,
              required: true,
              nullable: false,
            },
          );

          request.signatureParameters = [optionBagParameter];
          request.parameters.forEach((it) => (it.groupedBy = optionBagParameter));
          request.parameters.push(optionBagParameter);
        }
      }
    }
  }

  private findResponseBody(bodyType: Type): Type {
    // find a type that possibly without http metadata like @statusCode
    return this.getEffectiveSchemaType(bodyType);
  }

  private processResponse(op: CodeModelOperation, resp: HttpOperationResponse) {
    // TODO: what to do if more than 1 response?
    let response;
    let headers;
    if (resp.responses && resp.responses.length > 0 && resp.responses[0].headers) {
      // headers
      headers = [];
      for (const [key, header] of Object.entries(resp.responses[0].headers)) {
        const schema = this.processSchema(header.type, key);
        headers.push(
          new HttpHeader(key, schema, {
            language: {
              default: {
                name: key,
                description: this.getDoc(header),
              },
            },
          }),
        );
      }
    }
    if (resp.responses && resp.responses.length > 0 && resp.responses[0].body) {
      const responseBody = resp.responses[0].body;
      const bodyType = this.findResponseBody(responseBody.type);
      if (bodyType.kind === "Model" && bodyType.name === "bytes") {
        // binary
        response = new BinaryResponse({
          protocol: {
            http: {
              statusCodes: [this.getStatusCode(resp.statusCode)],
              headers: headers,
              mediaTypes: responseBody.contentTypes,
              knownMediaType: "binary",
            },
          },
          language: {
            default: {
              name: op.language.default.name + "Response",
              description: this.getResponseDescription(resp),
            },
          },
        });
      } else {
        // schema (usually JSON)
        const schema = this.processSchema(bodyType, "response");
        response = new SchemaResponse(schema, {
          protocol: {
            http: {
              statusCodes: [this.getStatusCode(resp.statusCode)],
              headers: headers,
              mediaTypes: responseBody.contentTypes,
            },
          },
          language: {
            default: {
              name: op.language.default.name + "Response",
              description: this.getResponseDescription(resp),
            },
          },
        });
      }
    } else {
      // not binary nor schema, usually NoContent
      response = new Response({
        protocol: {
          http: {
            statusCodes: [this.getStatusCode(resp.statusCode)],
            headers: headers,
          },
        },
        language: {
          default: {
            name: op.language.default.name + "Response",
            description: this.getResponseDescription(resp),
          },
        },
      });
    }
    if (resp.statusCode === "*" || Number(resp.statusCode) / 100 > 3) {
      // TODO: x-ms-error-response
      op.addException(response);

      if (response instanceof SchemaResponse) {
        this.trackSchemaUsage(response.schema, { usage: [SchemaContext.Exception] });
      }
    } else {
      op.addResponse(response);

      if (response instanceof SchemaResponse) {
        this.trackSchemaUsage(response.schema, { usage: [SchemaContext.Output] });

        if (op.convenienceApi) {
          this.trackSchemaUsage(response.schema, { usage: [SchemaContext.ConvenienceApi] });
        }
      }
    }
  }

  private getStatusCode(statusCode: StatusCode): string {
    return statusCode === "*" ? "default" : statusCode;
  }

  private getResponseDescription(resp: HttpOperationResponse): string {
    return (
      resp.description ||
      (resp.statusCode === "*" ? "An unexpected error response" : getStatusCodeDescription(resp.statusCode)) ||
      ""
    );
  }

  private processSchema(type: Type, nameHint: string): Schema {
    return this.schemaCache.process(type, nameHint) || fail("Unable to process schema.");
  }

  private processSchemaImpl(type: Type, nameHint: string): Schema {
    switch (type.kind) {
      case "Intrinsic":
        if (isUnknownType(type)) {
          return this.processAnySchema(type, nameHint);
        } else {
          throw new Error(`Unrecognized intrinsic type: '${type.name}'.`);
        }

      case "String":
        return this.processChoiceSchemaForLiteral(type, nameHint);

      case "Number":
        // TODO: float
        return this.processChoiceSchemaForLiteral(type, nameHint);

      case "Boolean":
        return this.processChoiceSchemaForLiteral(type, nameHint);

      case "Enum":
        return this.processChoiceSchema(type, this.getName(type), isFixed(this.program, type));

      case "Union":
        return this.processUnionSchema(type, nameHint);

      case "ModelProperty": {
        let schema = undefined;
        const knownValues = getKnownValues(this.program, type);
        if (knownValues) {
          // use it for extensible enum
          schema = this.processChoiceSchema(knownValues, this.getName(knownValues), false);
        } else {
          schema = this.processSchema(type.type, nameHint);
        }
        return this.applyModelPropertyDecorators(type, nameHint, schema);
      }

      case "Scalar":
        return this.processScalar(type, undefined, nameHint);

      case "Model":
        if (isArrayModelType(this.program, type)) {
          return this.processArraySchema(type, nameHint);
        } else if (isRecordModelType(this.program, type)) {
          return this.processDictionarySchema(type, nameHint);
        } else {
          return this.processObjectSchema(type, this.getName(type));
        }
    }
    throw new Error(`Unrecognized type: '${type.kind}'.`);
  }

  private processScalar(type: Scalar, formatFromDerived: string | undefined, nameHint: string): Schema {
    const scalarName = type.name;
    if (this.program.checker.isStdType(type)) {
      switch (scalarName) {
        case "string": {
          const format = formatFromDerived ?? getFormat(this.program, type);
          if (format) {
            return this.processFormatString(type, format, nameHint);
          }
          return this.processStringSchema(type, nameHint);
        }

        case "bytes":
          return this.processByteArraySchema(type, nameHint, false);

        case "boolean":
          return this.processBooleanSchema(type, nameHint);

        case "plainTime":
          return this.processTimeSchema(type, nameHint);

        case "plainDate":
          return this.processDateSchema(type, nameHint);

        case "zonedDateTime":
          return this.processDateTimeSchema(type, nameHint, false);

        case "duration":
          return this.processDurationSchema(type, nameHint);

        case "url":
          return this.processUrlSchema(type, nameHint);
      }

      if (scalarName.startsWith("int") || scalarName.startsWith("uint") || scalarName === "safeint") {
        // integer
        const integerSize = scalarName === "safeint" || scalarName.includes("int64") ? 64 : 32;
        return this.processIntegerSchema(type, nameHint, integerSize);
      } else if (scalarName.startsWith("float")) {
        // float point
        return this.processNumberSchema(type, nameHint);
      } else {
        throw new Error(`Unrecognized scalar type: '${scalarName}'.`);
      }
    } else {
      const knownValues = getKnownValues(this.program, type as Scalar);
      if (knownValues) {
        // use it for extensible enum
        return this.processChoiceSchema(knownValues, this.getName(type), false);
      } else {
        if (type.baseScalar) {
          // fallback to baseScalar
          const schema = this.processScalar(type.baseScalar, getFormat(this.program, type), nameHint);
          const doc = getDoc(this.program, type);
          const summary = getSummary(this.program, type);
          if (doc) {
            schema.language.default.description = doc;
          }
          if (summary) {
            schema.summary = summary;
          }
          return schema;
        } else {
          throw new Error(`Unrecognized scalar type: '${scalarName}'.`);
        }
      }
    }
  }

  private processAnySchema(type: IntrinsicType, name: string): AnySchema {
    return this.codeModel.schemas.add(
      new AnySchema(this.getDoc(type), {
        summary: this.getSummary(type),
      }),
    );
  }

  private processStringSchema(type: Scalar, name: string): StringSchema {
    return this.codeModel.schemas.add(
      new StringSchema(name, this.getDoc(type), {
        summary: this.getSummary(type),
      }),
    );
  }

  private processByteArraySchema(type: Scalar, name: string, base64Encoded: boolean): ByteArraySchema {
    return this.codeModel.schemas.add(
      new ByteArraySchema(name, this.getDoc(type), {
        summary: this.getSummary(type),
        format: base64Encoded ? "base64url" : "byte",
      }),
    );
  }

  private processIntegerSchema(type: Scalar, name: string, precision: number): NumberSchema {
    return this.codeModel.schemas.add(
      new NumberSchema(name, this.getDoc(type), SchemaType.Integer, precision, {
        summary: this.getSummary(type),
      }),
    );
  }

  private processNumberSchema(type: Scalar, name: string): NumberSchema {
    return this.codeModel.schemas.add(
      new NumberSchema(name, this.getDoc(type), SchemaType.Number, 64, {
        summary: this.getSummary(type),
      }),
    );
  }

  private processBooleanSchema(type: Scalar, name: string): BooleanSchema {
    return this.codeModel.schemas.add(
      new BooleanSchema(name, this.getDoc(type), {
        summary: this.getSummary(type),
      }),
    );
  }

  private processArraySchema(type: ArrayModelType, name: string): ArraySchema {
    const elementSchema = this.processSchema(type.indexer.value, name);
    return this.codeModel.schemas.add(
      new ArraySchema(name, this.getDoc(type), elementSchema, {
        summary: this.getSummary(type),
      }),
    );
  }

  private processDictionarySchema(type: RecordModelType, name: string): DictionarySchema {
    const dictSchema = new DictionarySchema<any>(name, this.getDoc(type), null, {
      summary: this.getSummary(type),
    });

    // cache this now before we accidentally recurse on this type.
    this.schemaCache.set(type, dictSchema);

    const elementSchema = this.processSchema(type.indexer.value, name);
    dictSchema.elementType = elementSchema;

    return this.codeModel.schemas.add(dictSchema);
  }

  private processChoiceSchema(
    type: Enum,
    name: string,
    sealed: boolean,
  ): ChoiceSchema | SealedChoiceSchema | ConstantSchema {
    const namespace = getNamespace(type);
    const valueType =
      typeof type.members.values().next().value.value === "number" ? this.integerSchema : this.stringSchema;

    const choices: ChoiceValue[] = [];
    type.members.forEach((it) => choices.push(new ChoiceValue(it.name, this.getDoc(it), it.value ?? it.name)));

    if (sealed) {
      return this.codeModel.schemas.add(
        new SealedChoiceSchema(name, this.getDoc(type), {
          summary: this.getSummary(type),
          choiceType: valueType as any,
          choices: choices,
          language: {
            default: {
              namespace: namespace,
            },
            java: {
              namespace: getJavaNamespace(namespace),
            },
          },
        }),
      );
    } else {
      return this.codeModel.schemas.add(
        new ChoiceSchema(name, this.getDoc(type), {
          summary: this.getSummary(type),
          choiceType: valueType as any,
          choices: choices,
          language: {
            default: {
              namespace: namespace,
            },
            java: {
              namespace: getJavaNamespace(namespace),
            },
          },
        }),
      );
    }
  }

  private processChoiceSchemaForLiteral(
    type: StringLiteral | NumericLiteral | BooleanLiteral,
    name: string,
  ): ConstantSchema {
    const valueType =
      type.kind === "String" ? this.stringSchema : type.kind === "Boolean" ? this.booleanSchema : this.integerSchema;

    return this.codeModel.schemas.add(
      new ConstantSchema(name, this.getDoc(type), {
        summary: this.getSummary(type),
        valueType: valueType,
        value: new ConstantValue(type.value),
      }),
    );

    // return this.codeModel.schemas.add(
    //   new ChoiceSchema(name, this.getDoc(type), {
    //     summary: this.getSummary(type),
    //     choiceType: valueType as any,
    //     choices: [new ChoiceValue(type.value.toString(), this.getDoc(type), type.value)]
    //   })
    // );
  }

  private processChoiceSchemaForUnion(type: Union, variants: UnionVariant[], name: string): SealedChoiceSchema {
    const kind = variants[0].type.kind;
    const valueType =
      kind === "String" ? this.stringSchema : kind === "Boolean" ? this.booleanSchema : this.integerSchema;

    const choices: ChoiceValue[] = [];
    variants.forEach((it) =>
      choices.push(new ChoiceValue((it.type as any).value.toString(), this.getDoc(it), (it.type as any).value)),
    );

    const namespace = getNamespace(type);
    return this.codeModel.schemas.add(
      new SealedChoiceSchema(name, this.getDoc(type), {
        summary: this.getSummary(type),
        choiceType: valueType as any,
        choices: choices,
        language: {
          default: {
            namespace: namespace,
          },
          java: {
            namespace: getJavaNamespace(namespace),
          },
        },
      }),
    );
  }

  private processDateTimeSchema(type: Scalar, name: string, rfc1123: boolean): DateTimeSchema {
    return this.codeModel.schemas.add(
      new DateTimeSchema(name, this.getDoc(type), {
        summary: this.getSummary(type),
        format: rfc1123 ? "date-time-rfc1123" : "date-time",
      }),
    );
  }

  private processDateSchema(type: Scalar, name: string): DateSchema {
    return this.codeModel.schemas.add(
      new DateSchema(name, this.getDoc(type), {
        summary: this.getSummary(type),
      }),
    );
  }

  private processTimeSchema(type: Scalar, name: string): TimeSchema {
    return this.codeModel.schemas.add(
      new TimeSchema(name, this.getDoc(type), {
        summary: this.getSummary(type),
      }),
    );
  }

  private processDurationSchema(type: Scalar, name: string): DurationSchema {
    return this.codeModel.schemas.add(
      new DurationSchema(name, this.getDoc(type), {
        summary: this.getSummary(type),
      }),
    );
  }

  private processUrlSchema(type: Scalar, name: string): UriSchema {
    return this.codeModel.schemas.add(
      new UriSchema(name, this.getDoc(type), {
        summary: this.getSummary(type),
      }),
    );
  }

  private processObjectSchema(type: Model, name: string): ObjectSchema {
    const namespace = getNamespace(type);
    const objectSchema = this.codeModel.schemas.add(
      new ObjectSchema(name, this.getDoc(type), {
        summary: this.getSummary(type),
        language: {
          default: {
            namespace: namespace,
          },
          java: {
            namespace: getJavaNamespace(namespace),
          },
        },
      }),
    );

    // cache this now before we accidentally recurse on this type.
    this.schemaCache.set(type, objectSchema);

    // discriminator
    let discriminatorPropertyName: string | undefined = undefined;
    const discriminator = getDiscriminator(this.program, type);
    if (discriminator) {
      discriminatorPropertyName = discriminator.propertyName;
      objectSchema.discriminator = new Discriminator(
        new Property(discriminatorPropertyName, discriminatorPropertyName, this.stringSchema, {
          required: true,
          serializedName: discriminatorPropertyName,
        }),
      );
    }

    // parent
    if (type.baseModel) {
      const parentSchema = this.processSchema(type.baseModel, this.getName(type.baseModel));
      objectSchema.parents = new Relations();
      objectSchema.parents.immediate.push(parentSchema);

      if (parentSchema instanceof ObjectSchema) {
        pushDistinct(objectSchema.parents.all, parentSchema);

        parentSchema.children = parentSchema.children || new Relations();
        pushDistinct(parentSchema.children.immediate, objectSchema);
        pushDistinct(parentSchema.children.all, objectSchema);

        if (parentSchema.parents) {
          pushDistinct(objectSchema.parents.all, ...parentSchema.parents.all);

          parentSchema.parents.all.forEach((it) => {
            if (it instanceof ObjectSchema && it.children) {
              pushDistinct(it.children.all, objectSchema);
            }
          });
        }
      }
    }

    // value of the discriminator property
    if (objectSchema.parents) {
      const parentWithDiscriminator = objectSchema.parents.all.find(
        (it) => it instanceof ObjectSchema && it.discriminator,
      );
      if (parentWithDiscriminator) {
        discriminatorPropertyName = (parentWithDiscriminator as ObjectSchema).discriminator!.property.serializedName;

        const discriminatorProperty = Array.from(type.properties.values()).find(
          (it) => it.name === discriminatorPropertyName && it.type.kind === "String",
        );
        if (discriminatorProperty) {
          // value of the StringLiteral of the discriminator property
          objectSchema.discriminatorValue = (discriminatorProperty.type as StringLiteral).value;
        } else {
          // fallback to name of the Model
          objectSchema.discriminatorValue = name;
        }
      }
    }

    // properties
    for (const prop of type.properties.values()) {
      if (
        prop.name === discriminatorPropertyName || // skip the discriminator property
        isNeverType(prop.type) // skip property of type "never"
      ) {
        continue;
      }
      objectSchema.addProperty(this.processModelProperty(prop));
    }

    // process all children
    type.derivedModels?.filter(modelContainsDerivedModel).forEach((it) => this.processSchema(it, this.getName(it)));

    return objectSchema;
  }

  private getEffectiveSchemaType(type: Type): Type {
    const program = this.program;

    function isSchemaProperty(property: ModelProperty) {
      const headerInfo = getHeaderFieldName(program, property);
      const queryInfo = getQueryParamName(program, property);
      const pathInfo = getPathParamName(program, property);
      const statusCodeInfo = isStatusCode(program, property);
      return !(headerInfo || queryInfo || pathInfo || statusCodeInfo);
    }

    if (type.kind === "Model") {
      const effective = getEffectiveModelType(program, type, isSchemaProperty);
      if (effective.name) {
        return effective;
      }
    }
    return type;
  }

  private applyModelPropertyDecorators(prop: ModelProperty, nameHint: string, schema: Schema): Schema {
    // if (schema instanceof StringSchema) {
    //   const decorators = {
    //     minLength: getMinLength(this.program, prop),
    //     maxLength: getMaxLength(this.program, prop),
    //     pattern: getPattern(this.program, prop),
    //   };

    //   if (Object.values(decorators).some((it) => it !== undefined)) {
    //     schema = new StringSchema(schema.language.default.name, schema.language.default.description, {
    //       language: schema.language,
    //       summary: schema.summary,
    //       extensions: schema.extensions,
    //       ...decorators,
    //     });
    //   }
    // } else if (schema instanceof NumberSchema) {
    //   const decorators = {
    //     minimum: getMinValue(this.program, prop),
    //     maximum: getMaxValue(this.program, prop),
    //   };

    //   if (Object.values(decorators).some((it) => it !== undefined)) {
    //     schema = new NumberSchema(
    //       schema.language.default.name,
    //       schema.language.default.description,
    //       schema.type,
    //       schema.precision,
    //       {
    //         language: schema.language,
    //         summary: schema.summary,
    //         extensions: schema.extensions,
    //         ...decorators,
    //       },
    //     );
    //   }
    // }
    const format = getFormat(this.program, prop);
    if (format) {
      if (prop.type.kind === "Scalar" && schema instanceof StringSchema) {
        schema = this.processFormatString(prop.type, format, nameHint);
      }
    }
    return schema;
  }

  private processModelProperty(prop: ModelProperty): Property {
    const schema = this.processSchema(prop, prop.name);
    const nullable = this.isNullableType(prop.type);

    return new Property(this.getName(prop), this.getDoc(prop), schema, {
      summary: this.getSummary(prop),
      required: !prop.optional,
      nullable: nullable,
      readOnly: this.isReadOnly(prop),
      // clientDefaultValue: this.getDefaultValue(prop.default),
      serializedName: prop.name,
    });
  }

  private processFormatString(type: Scalar, format: string, nameHint: string): Schema {
    switch (format) {
      case "byte":
        return this.processByteArraySchema(type, nameHint, true);
      case "binary":
        return this.processByteArraySchema(type, nameHint, false);
      case "date-time":
        return this.processDateTimeSchema(type, nameHint, false);
      case "date-time-rfc1123":
        return this.processDateTimeSchema(type, nameHint, true);
      case "password":
      case "url":
      case "uuid":
        return this.processStringSchema(type, nameHint);
    }
    throw new Error(`Unrecognized string format: '${format}'.`);
  }

  private processUnionSchema(type: Union, name: string): Schema {
    const nonNullVariants = Array.from(type.variants.values()).filter((it) => !isNullType(it.type));
    if (nonNullVariants.length === 1) {
      // nullable
      return this.processSchema(nonNullVariants[0].type, name);
    }

    if (this.isSameLiteralTypes(nonNullVariants)) {
      // enum
      return this.processChoiceSchemaForUnion(type, nonNullVariants, name);
    }

    // TODO: name from cadl-dpg
    const unionSchema = new OrSchema(name + "Model", this.getDoc(type), {
      summary: this.getSummary(type),
    });
    unionSchema.anyOf = [];
    nonNullVariants.forEach((it) => {
      const variantName = this.getUnionVariantName(name, it.type, { depth: 0 });
      const schema = this.processSchema(it.type, variantName);
      const property = new Property(variantName, this.getDoc(type), schema, {
        summary: this.getSummary(type),
        required: false,
        nullable: true,
        readOnly: false,
      });
      unionSchema.anyOf.push(property);
    });
    return this.codeModel.schemas.add(unionSchema);
  }

  private getUnionVariantName(prefix: string, type: Type, option: any): string {
    switch (type.kind) {
      case "Scalar": {
        const scalarName = type.name;
        let name = type.name;
        if (scalarName.startsWith("int") || scalarName.startsWith("uint") || scalarName === "safeint") {
          name = scalarName === "safeint" || scalarName.includes("int64") ? "Long" : "Integer";
        } else if (scalarName.startsWith("float")) {
          name = "Double";
        } else if (scalarName === "bytes") {
          name = "ByteArray";
        } else if (scalarName === "zonedDateTime") {
          name = "Time";
        }
        return prefix + pascalCase(name);
      }
      case "Enum":
        return prefix + pascalCase(type.name);
      case "Model":
        if (isArrayModelType(this.program, type)) {
          ++option.depth;
          if (option.depth == 1) {
            return prefix + this.getUnionVariantName("", type.indexer.value, option) + "List";
          } else {
            return prefix + "ListOf" + this.getUnionVariantName("", type.indexer.value, option);
          }
        } else if (isRecordModelType(this.program, type)) {
          ++option.depth;
          if (option.depth == 1) {
            return prefix + this.getUnionVariantName(prefix, type.indexer.value, option) + "Map";
          } else {
            return prefix + "MapOf" + this.getUnionVariantName("", type.indexer.value, option);
          }
        } else {
          return prefix + pascalCase(type.name);
        }
    }
    return prefix;
  }

  private isNullableType(type: Type): boolean {
    if (type.kind === "Union") {
      const nullVariants = Array.from(type.variants.values()).filter((it) => isNullType(it.type));
      return nullVariants.length >= 1;
    } else {
      return false;
    }
  }

  private isSameLiteralTypes(variants: UnionVariant[]): boolean {
    const kindSet = new Set(variants.map((it) => it.type.kind));
    if (kindSet.size === 1) {
      const kind = kindSet.values().next().value;
      return kind === "String" || kind === "Number" || kind === "Boolean";
    } else {
      return false;
    }
  }

  private getDefaultValue(type: Type | undefined): any {
    if (type) {
      switch (type.kind) {
        case "String":
          return type.value;
        case "Number":
          return type.value;
        case "Boolean":
          return type.value;
        // case "Tuple":
        //   return type.values.map(getDefaultValue);
      }
    }
    return undefined;
  }

  private getDoc(target: Type): string {
    return getDoc(this.program, target) || "";
  }

  private getSummary(target: Type): string | undefined {
    return getSummary(this.program, target);
  }

  private getName(target: Model | Enum | ModelProperty | Scalar): string {
    const friendlyName = getFriendlyName(this.program, target);
    if (friendlyName) {
      return friendlyName;
    } else {
      if (target.kind === "Model" && target.templateArguments && target.templateArguments.length > 0) {
        const cadlName = this.program.checker.getTypeName(target, this.typeNameOptions);
        const newName = getNameForTemplate(target);
        this.program.trace("cadl-java", `Rename Cadl model '${cadlName}' to '${newName}'`);
        return newName;
      } else {
        return target.name;
      }
    }
  }

  private isReadOnly(target: ModelProperty): boolean {
    // const key = isKey(this.program, target);
    const segment = getSegment(this.program, target) !== undefined;
    if (segment) {
      return true;
    } else {
      const visibility = getVisibility(this.program, target);
      if (visibility) {
        return (
          !visibility.includes("write") &&
          !visibility.includes("create") &&
          !visibility.includes("update") &&
          !visibility.includes("delete") &&
          !visibility.includes("query")
        );
      } else {
        return false;
      }
    }
  }

  private getConvenienceApiName(op: Operation): string | undefined {
    // check @convenienceMethod
    return getConvenienceAPIName(this.program, op);
  }

  private logWarning(msg: string) {
    this.program.trace("cadl-java", msg);
    this.program.reportDiagnostic({
      code: "cadl-java",
      severity: "warning",
      message: msg,
      target: NoTarget,
    });
  }

  private _stringSchema?: StringSchema;
  get stringSchema(): StringSchema {
    return (
      this._stringSchema ||
      (this._stringSchema = this.codeModel.schemas.add(new StringSchema("string", "simple string")))
    );
  }

  private _integerSchema?: NumberSchema;
  get integerSchema(): NumberSchema {
    return (
      this._integerSchema ||
      (this._integerSchema = this.codeModel.schemas.add(
        new NumberSchema("integer", "simple integer", SchemaType.Integer, 64),
      ))
    );
  }

  private _booleanSchema?: BooleanSchema;
  get booleanSchema(): BooleanSchema {
    return (
      this._booleanSchema ||
      (this._booleanSchema = this.codeModel.schemas.add(new BooleanSchema("boolean", "simple boolean")))
    );
  }

  private _binarySchema?: BinarySchema;
  get binarySchema(): BinarySchema {
    return this._binarySchema || (this._binarySchema = this.codeModel.schemas.add(new BinarySchema("binary")));
  }

  private _anySchema?: AnySchema;
  public get anySchema(): AnySchema {
    return this._anySchema ?? (this._anySchema = this.codeModel.schemas.add(new AnySchema("Anything")));
  }

  private _apiVersionParameter?: Parameter;
  get apiVersionParameter(): Parameter {
    return (
      this._apiVersionParameter ||
      (this._apiVersionParameter = new Parameter(
        "api-version",
        "Version parameter",
        this.codeModel.schemas.add(
          new ConstantSchema("accept", `api-version: ${this.version}`, {
            valueType: this.stringSchema,
            value: new ConstantValue(this.version),
          }),
        ),
        {
          implementation: ImplementationLocation.Client,
          origin: "modelerfour:synthesized/api-version",
          required: true,
          protocol: {
            http: new HttpParameter(ParameterLocation.Query),
          },
          clientDefaultValue: this.version,
          language: {
            default: {
              serializedName: "api-version",
            },
          },
        },
      ))
    );
  }

  private propagateSchemaUsage(schema: Schema): void {
    const processedSchemas = new Set<Schema>();

    const innerApplySchemaUsage = (schema: Schema, schemaUsage: SchemaUsage) => {
      this.trackSchemaUsage(schema, schemaUsage);
      innerPropagateSchemaUsage(schema, schemaUsage);
    };

    const innerPropagateSchemaUsage = (schema: Schema, schemaUsage: SchemaUsage) => {
      if (processedSchemas.has(schema)) {
        return;
      }

      processedSchemas.add(schema);
      if (schema instanceof ObjectSchema) {
        if (schemaUsage.usage || schemaUsage.serializationFormats) {
          schema.properties?.forEach((p) => innerApplySchemaUsage(p.schema, schemaUsage));

          schema.parents?.all?.forEach((p) => innerApplySchemaUsage(p, schemaUsage));
          schema.parents?.immediate?.forEach((p) => innerApplySchemaUsage(p, schemaUsage));

          schema.children?.all?.forEach((c) => innerApplySchemaUsage(c, schemaUsage));
          schema.children?.immediate?.forEach((c) => innerApplySchemaUsage(c, schemaUsage));

          // Object.values(schema.discriminator?.all ?? {}).forEach((d) => {
          //   innerApplySchemaUsage(d, schemaUsage);
          // });
          // Object.values(schema.discriminator?.immediate ?? {}).forEach((d) => {
          //   innerApplySchemaUsage(d, schemaUsage);
          // });
        }
      } else if (schema instanceof DictionarySchema) {
        innerApplySchemaUsage(schema.elementType, schemaUsage);
      } else if (schema instanceof ArraySchema) {
        innerApplySchemaUsage(schema.elementType, schemaUsage);
      }
    };

    // Exclude context that not to be propagated
    const schemaUsage = {
      usage: (schema as SchemaUsage).usage?.filter(
        (it) => it !== SchemaContext.Paged && it !== SchemaContext.Anonymous,
      ),
      serializationFormats: (schema as SchemaUsage).serializationFormats,
    };
    // Propagate the usage of the initial schema itself
    innerPropagateSchemaUsage(schema, schemaUsage);
  }

  private trackSchemaUsage(schema: Schema, schemaUsage: SchemaUsage): void {
    if (
      schema instanceof ObjectSchema ||
      schema instanceof GroupSchema ||
      schema instanceof ChoiceSchema ||
      schema instanceof SealedChoiceSchema ||
      schema instanceof OrSchema
    ) {
      if (schemaUsage.usage) {
        pushDistinct((schema.usage = schema.usage || []), ...schemaUsage.usage);
      }

      if (schema instanceof OrSchema) {
        schema.anyOf.forEach((it) => this.trackSchemaUsage(it.schema, schemaUsage));
      }
    } else if (schema instanceof DictionarySchema) {
      this.trackSchemaUsage(schema.elementType, schemaUsage);
    } else if (schema instanceof ArraySchema) {
      this.trackSchemaUsage(schema.elementType, schemaUsage);
    }
  }
}

/** Acts as a cache for processing inputs.
 *
 * If the input is undefined, the output is always undefined.
 * for a given input, the process is only ever called once.
 *
 *
 */
class ProcessingCache<In, Out> {
  private results = new Map<In, Out>();
  constructor(private transform: (orig: In, ...args: Array<any>) => Out) {}
  has(original: In | undefined) {
    return !!original && !!this.results.get(original);
  }
  set(original: In, result: Out) {
    this.results.set(original, result);
    return result;
  }
  process(original: In | undefined, ...args: Array<any>): Out | undefined {
    if (original) {
      const result: Out = this.results.get(original) || this.transform(original, ...args);
      this.results.set(original, result);
      return result;
    }
    return undefined;
  }
}

/** adds only if the item is not in the collection already
 *
 * @note  While this isn't very efficient, it doesn't disturb the original
 * collection, so you won't get inadvertent side effects from using Set, etc.
 */
function pushDistinct<T>(targetArray: Array<T>, ...items: Array<T>): Array<T> {
  for (const i of items) {
    if (!targetArray.includes(i)) {
      targetArray.push(i);
    }
  }
  return targetArray;
}

function getNamespace(type: Model | Enum | Union | Operation): string | undefined {
  let namespaceRef = type.namespace;
  let namespaceStr: string | undefined = undefined;
  while (namespaceRef && namespaceRef.name.length !== 0) {
    namespaceStr = namespaceRef.name + (namespaceStr ? "." + namespaceStr : "");
    namespaceRef = namespaceRef.namespace;
  }
  return namespaceStr;
}

function getJavaNamespace(namespace: string | undefined): string | undefined {
  return namespace ? "com." + namespace.toLowerCase() : undefined;
}

function modelContainsDerivedModel(model: Model): boolean {
  return !isTemplateDeclaration(model) && !(isTemplateInstance(model) && model.derivedModels.length === 0);
}

function isModelReferredInTemplate(template: TemplatedTypeBase, target: Model): boolean {
  return (
    template === target ||
    (template.templateArguments?.some((it) =>
      it.kind === "Model" || it.kind === "Union" ? isModelReferredInTemplate(it, target) : false,
    ) ??
      false)
  );
}

function getNameForTemplate(target: Type): string {
  switch (target.kind) {
    case "Model": {
      let name = target.name;
      if (target.templateArguments) {
        name = name + target.templateArguments.map((it) => getNameForTemplate(it)).join("");
      }
      return name;
    }

    case "String":
      return target.value;

    default:
      return "";
  }
}

function containsIgnoreCase(stringList: string[], str: string): boolean {
  return stringList && str ? stringList.findIndex((s) => s.toLowerCase() === str.toLowerCase()) != -1 : false;
}

function operationContainsJsonMergePatch(op: HttpOperation): boolean {
  for (const param of op.parameters.parameters) {
    if (param.name.toLowerCase() === "content-type") {
      return true;
    }
  }
  return false;
}

function pascalCase(name: string): string {
  if (name.length > 0) {
    return name[0].toUpperCase() + name.slice(1);
  } else {
    return name;
  }
}

// function hasDecorator(type: DecoratedType, name: string): boolean {
//   return type.decorators.find((it) => it.decorator.name === name) !== undefined;
// }
