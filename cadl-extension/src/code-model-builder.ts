import {
  ArrayModelType,
  BooleanLiteral,
  DecoratedType,
  Enum,
  getDoc,
  getEffectiveModelType,
  getFormat,
  getFriendlyName,
  getIntrinsicModelName,
  getKnownValues,
  getServiceNamespace,
  getServiceNamespaceString,
  getServiceTitle,
  getServiceVersion,
  getSummary,
  getVisibility,
  ignoreDiagnostics,
  IntrinsicType,
  isArrayModelType,
  isIntrinsic,
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
} from "@cadl-lang/compiler";
import { getResourceOperation, getSegment } from "@cadl-lang/rest";
import {
  getAllRoutes,
  getAuthentication,
  getHeaderFieldName,
  getPathParamName,
  getQueryParamName,
  getServers,
  getStatusCodeDescription,
  HttpOperationParameter,
  HttpOperationResponse,
  HttpServer,
  isStatusCode,
  OperationDetails,
  ServiceAuthentication,
  StatusCode,
} from "@cadl-lang/rest/http";
import { getVersion } from "@cadl-lang/versioning";
import { fail } from "assert";
import {
  AnySchema,
  ArraySchema,
  BinaryResponse,
  BinarySchema,
  BooleanSchema,
  ByteArraySchema,
  ChoiceValue,
  CodeModel,
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
} from "@autorest/codemodel";
import { Operation as CodeModelOperation, OperationLink, Request } from "./common/operation.js";
import { SchemaContext, SchemaUsage } from "./common/schemas/usage.js";
import { ChoiceSchema, SealedChoiceSchema } from "./common/schemas/choice.js";
import { isPollingLocation, getPagedResult, getOperationLinks } from "@azure-tools/cadl-azure-core";

export class CodeModelBuilder {
  private program: Program;
  private typeNameOptions: TypeNameOptions;
  private version: string;
  private baseUri: string;
  private hostParameters: Parameter[];

  private codeModel: CodeModel;

  private schemaCache = new ProcessingCache((type: Type, name: string) => this.processSchemaImpl(type, name));
  private operationCache = new Map<Operation, CodeModelOperation>();

  private specialHeaderNames = new Set(["repeatability-request-id", "repeatability-first-sent"]);

  public constructor(program1: Program) {
    this.program = program1;
    const serviceNamespace = getServiceNamespace(this.program);
    if (serviceNamespace === undefined) {
      throw Error("Can not emit yaml for a namespace that doesn't exist.");
    }

    // java namespace
    const namespace = getServiceNamespaceString(this.program) || "Azure.Client";
    const javaNamespace = getJavaNamespace(namespace);

    // service version
    this.version = getServiceVersion(this.program);
    if (this.version === "0000-00-00") {
      const versioning = getVersion(this.program, serviceNamespace);
      if (versioning) {
        // TODO: versioning support
        this.version = versioning.getVersions()[0].value;
      }
    }

    this.typeNameOptions = {
      // shorten type names by removing Cadl and service namespace
      namespaceFilter(ns) {
        const name = program1.checker.getNamespaceString(ns);
        return name !== "Cadl" && name !== namespace;
      },
    };

    // init code model
    let title = getServiceTitle(this.program);
    if (title === "(title)") {
      title = namespace;
    }
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
          namespace: namespace,
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
    ignoreDiagnostics(getAllRoutes(this.program)).map((it) => this.processRoute(it));

    this.codeModel.schemas.objects?.forEach((it) => this.propagateSchemaUsage(it));

    return this.codeModel;
  }

  private processHost(server: HttpServer | undefined) {
    if (server) {
      server.parameters.forEach((it) => {
        const schema = this.processSchema(it.type, it.name);
        return this.hostParameters.push(
          this.codeModel.addGlobalParameter(
            new Parameter(it.name, this.getDoc(it), schema, {
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
            }),
          ),
        );
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

  private processRoute(op: OperationDetails) {
    const groupName = op.container.name;
    const operationGroup = this.codeModel.getOperationGroup(groupName);
    const opId = `${groupName}_${op.operation.name}`;

    const requireConvenienceMethod = this.isConvenienceMethod(op);

    const operation = new CodeModelOperation(op.operation.name, this.getDoc(op.operation), {
      operationId: opId,
      summary: this.getSummary(op.operation),
      apiVersions: [
        {
          version: this.version,
        },
      ],
      extensions: {
        convenienceMethod: requireConvenienceMethod,
      },
    });

    // cache for later reference from operationLinks
    this.operationCache.set(op.operation, operation);

    operation.addRequest(
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
    this.hostParameters.forEach((it) => operation.addParameter(it));
    // parameters
    op.parameters.parameters.map((it) => this.processParameter(operation, it));
    // Accept header
    this.addAcceptHeaderParameter(operation, op.responses);
    // body
    if (op.parameters.bodyParameter) {
      this.processParameterBody(operation, op.parameters.bodyParameter);
    } else if (op.parameters.bodyType) {
      let bodyType = this.getEffectiveSchemaType(op.parameters.bodyType);

      if (bodyType.kind === "Model") {
        // try use resource type as round-trip model
        const resourceType = getResourceOperation(this.program, op.operation)?.resourceType;
        if (resourceType && op.responses && op.responses.length > 0) {
          const resp = op.responses[0];
          if (resp.responses && resp.responses.length > 0 && resp.responses[0].body) {
            const responseBody = resp.responses[0].body;
            const bodyTypeInResponse = this.findResponseBody(responseBody.type);
            // response body type is reosurce type, and request body type (if templated) contains resource type
            if (bodyTypeInResponse === resourceType && isModelReferredInTemplate(bodyType, resourceType)) {
              bodyType = resourceType;
            }
          }
        }

        this.processParameterBody(operation, bodyType);
      }
    }

    // responses
    op.responses.map((it) => this.processResponse(operation, it));

    this.processRouteForPaged(operation, op.responses);
    this.processRouteForLongRunning(operation, op.operation, op.responses);

    operationGroup.addOperation(operation);
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

            break;
          }
        }
      }
    }
  }

  private processRouteForLongRunning(op: CodeModelOperation, operation: Operation, responses: HttpOperationResponse[]) {
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
      const parameter = this.apiVersionParameter;
      op.addParameter(parameter);
    } else if (this.specialHeaderNames.has(param.name.toLowerCase())) {
      // special headers
      op.specialHeaders = op.specialHeaders ?? [];
      if (!containsIgnoreCase(op.specialHeaders, param.name)) {
        op.specialHeaders.push(param.name);
      }
    } else {
      const schema = this.processSchema(param.param.type, param.param.name);
      const nullable = this.isNullableType(param.param.type);
      const parameter = new Parameter(param.name, this.getDoc(param.param), schema, {
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

      if (op.extensions?.convenienceMethod) {
        this.trackSchemaUsage(schema, { usage: [SchemaContext.ConvenienceMethod] });
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

  private processParameterBody(op: CodeModelOperation, body: ModelProperty | Model) {
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

    if (op.extensions?.convenienceMethod) {
      this.trackSchemaUsage(schema, { usage: [SchemaContext.ConvenienceMethod] });
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
        headers.push(new HttpHeader(key, schema));
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

        if (op.extensions?.convenienceMethod) {
          this.trackSchemaUsage(response.schema, { usage: [SchemaContext.ConvenienceMethod] });
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
        return this.processChoiceSchema(type, this.getName(type), true);

      case "Union":
        return this.processUnionSchema(type, nameHint);

      case "ModelProperty":
        // return this.processSchema(type.type, nameHint);
        return this.applyModelPropertyDecorators(type, nameHint, this.processSchema(type.type, nameHint));

      case "Model":
        if (isIntrinsic(this.program, type)) {
          const intrinsicModelName = getIntrinsicModelName(this.program, type);
          switch (intrinsicModelName) {
            case "string": {
              const Enum = getKnownValues(this.program, type);
              if (Enum) {
                return this.processChoiceSchema(Enum, this.getName(type), false);
              } else {
                const format = getFormat(this.program, type);
                if (format) {
                  return this.processFormatString(type, format, nameHint);
                }
                return this.processStringSchema(type, nameHint);
              }
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
          }

          if (
            intrinsicModelName.startsWith("int") ||
            intrinsicModelName.startsWith("uint") ||
            intrinsicModelName === "safeint"
          ) {
            // integer
            const integerSize = intrinsicModelName === "safeint" || intrinsicModelName.includes("int64") ? 64 : 32;
            return this.processIntegerSchema(type, nameHint, integerSize);
          } else if (intrinsicModelName.startsWith("float")) {
            // float point
            return this.processNumberSchema(type, nameHint);
          } else {
            throw new Error(`Unrecognized intrinsic type: '${intrinsicModelName}'.`);
          }
        } else if (isArrayModelType(this.program, type)) {
          return this.processArraySchema(type, nameHint);
        } else if (isRecordModelType(this.program, type)) {
          return this.processDictionarySchema(type, nameHint);
        } else {
          return this.processObjectSchema(type, this.getName(type));
        }
    }
    throw new Error(`Unrecognized type: '${type.kind}'.`);
  }

  private processAnySchema(type: IntrinsicType, name: string): AnySchema {
    return this.codeModel.schemas.add(
      new AnySchema(this.getDoc(type), {
        summary: this.getSummary(type),
      }),
    );
  }

  private processStringSchema(type: Model, name: string): StringSchema {
    return this.codeModel.schemas.add(
      new StringSchema(name, this.getDoc(type), {
        summary: this.getSummary(type),
      }),
    );
  }

  private processByteArraySchema(type: Model, name: string, base64Encoded: boolean): ByteArraySchema {
    return this.codeModel.schemas.add(
      new ByteArraySchema(name, this.getDoc(type), {
        summary: this.getSummary(type),
        format: base64Encoded ? "base64url" : "byte",
      }),
    );
  }

  private processIntegerSchema(type: Model, name: string, precision: number): NumberSchema {
    return this.codeModel.schemas.add(
      new NumberSchema(name, this.getDoc(type), SchemaType.Integer, precision, {
        summary: this.getSummary(type),
      }),
    );
  }

  private processNumberSchema(type: Model, name: string): NumberSchema {
    return this.codeModel.schemas.add(
      new NumberSchema(name, this.getDoc(type), SchemaType.Number, 64, {
        summary: this.getSummary(type),
      }),
    );
  }

  private processBooleanSchema(type: Model, name: string): BooleanSchema {
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

  private processDateTimeSchema(type: Model, name: string, rfc1123: boolean): DateTimeSchema {
    return this.codeModel.schemas.add(
      new DateTimeSchema(name, this.getDoc(type), {
        summary: this.getSummary(type),
        format: rfc1123 ? "date-time-rfc1123" : "date-time",
      }),
    );
  }

  private processDateSchema(type: Model, name: string): DateSchema {
    return this.codeModel.schemas.add(
      new DateSchema(name, this.getDoc(type), {
        summary: this.getSummary(type),
      }),
    );
  }

  private processTimeSchema(type: Model, name: string): TimeSchema {
    return this.codeModel.schemas.add(
      new TimeSchema(name, this.getDoc(type), {
        summary: this.getSummary(type),
      }),
    );
  }

  private processDurationSchema(type: Model, name: string): DurationSchema {
    return this.codeModel.schemas.add(
      new DurationSchema(name, this.getDoc(type), {
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
      if (prop.name === discriminatorPropertyName) {
        // skip the discriminator property
        continue;
      }

      objectSchema.addProperty(this.processModelProperty(prop));
    }

    // process all children
    type.derivedModels?.filter(includeDerivedModel).forEach((it) => this.processSchema(it, this.getName(it)));

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
      if (prop.type.kind === "Model" && schema instanceof StringSchema) {
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

  private processFormatString(type: Model, format: string, nameHint: string): Schema {
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
      case "uri":
        return this.processStringSchema(type, nameHint);
    }
    throw new Error(`Unrecognized string format: '${format}'.`);
  }

  private processUnionSchema(type: Union, name: string): Schema {
    const nonNullVariants = Array.from(type.variants.values()).filter(
      (it) => !(isIntrinsic(this.program, it.type) && getIntrinsicModelName(this.program, it.type) === "null"),
    );
    if (nonNullVariants.length === 1) {
      // nullable
      return this.processSchema(nonNullVariants[0].type, name);
    }

    if (this.isSameLiteralTypes(nonNullVariants)) {
      // enum
      return this.processChoiceSchemaForUnion(type, nonNullVariants, name);
    }

    // TODO
    throw new Error(`Unsupported Union type: '${name}'.`);
  }

  private isNullableType(type: Type): boolean {
    if (type.kind === "Union") {
      const nullVariants = Array.from(type.variants.values()).filter(
        (it) => isIntrinsic(this.program, it.type) && getIntrinsicModelName(this.program, it.type) === "null",
      );
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

  private getName(target: Model | Enum | ModelProperty): string {
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
        return !visibility.includes("write");
      } else {
        return false;
      }
    }
  }

  private isConvenienceMethod(op: OperationDetails) {
    // check @convenienceMethod
    let hasConvenienceMethod =
      hasDecorator(op.operation, "$convenienceMethod") || hasDecorator(op.container, "$convenienceMethod");
    if (!hasConvenienceMethod) {
      // check @extension with x-ms-convenient-api=true
      const extensionDecorators = op.operation.decorators.filter((it) => it.decorator.name === "$extension");
      for (const extensionDecorator of extensionDecorators) {
        if (extensionDecorator.args.length == 2) {
          const name = extensionDecorator.args[0].value;
          const value = extensionDecorator.args[1].value;

          if (name === "x-ms-convenient-api" && value === true) {
            hasConvenienceMethod = true;
            break;
          }
        }
      }
    }
    return hasConvenienceMethod;
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

    // Propagate the usage of the initial schema itself
    innerPropagateSchemaUsage(schema, schema as SchemaUsage);
  }

  private trackSchemaUsage(schema: Schema, schemaUsage: SchemaUsage): void {
    if (schema instanceof ObjectSchema || schema instanceof ChoiceSchema || schema instanceof SealedChoiceSchema) {
      if (schemaUsage.usage) {
        pushDistinct((schema.usage = schema.usage || []), ...schemaUsage.usage);
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
 * If the input is undefined, the ouptut is always undefined.
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

function includeDerivedModel(model: Model): boolean {
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

function containsIgnoreCase(stringList: string[], str: string) {
  return stringList && str ? stringList.findIndex((s) => s.toLowerCase() === str.toLowerCase()) != -1 : false;
}

function hasDecorator(type: DecoratedType, name: string): boolean {
  return type.decorators.find((it) => it.decorator.name === name) !== undefined;
}
