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
  isUnknownType,
  Model,
  ModelProperty,
  NumericLiteral,
  Operation,
  Program,
  RecordModelType,
  StringLiteral,
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
  getTypeName,
  EmitContext,
  getProjectedName,
  getService,
  getEncode,
  getOverloadedOperation,
  isErrorModel,
  EnumMember,
} from "@typespec/compiler";
import { getResourceOperation, getSegment } from "@typespec/rest";
import {
  getAuthentication,
  getServers,
  getStatusCodeDescription,
  HttpOperation,
  HttpOperationParameter,
  HttpOperationResponse,
  HttpServer,
  ServiceAuthentication,
  HttpStatusCodesEntry,
  getHttpOperation,
  getQueryParamOptions,
  getHeaderFieldOptions,
} from "@typespec/http";
import { getAddedOnVersions, getVersion } from "@typespec/versioning";
import { isPollingLocation, getPagedResult, isFixed, getLroMetadata } from "@azure-tools/typespec-azure-core";
import {
  SdkContext,
  listClients,
  listOperationGroups,
  listOperationsInOperationGroup,
  isApiVersion,
  shouldGenerateConvenient,
  createSdkContext,
  shouldGenerateProtocol,
  isInternal,
  SdkClient,
  getCrossLanguageDefinitionId,
} from "@azure-tools/typespec-client-generator-core";
import { fail } from "assert";
import {
  AnySchema,
  ArraySchema,
  BinaryResponse,
  BinarySchema,
  BooleanSchema,
  ByteArraySchema,
  ChoiceValue,
  DateTimeSchema,
  DateSchema,
  DictionarySchema,
  Discriminator,
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
  ApiVersion,
  SerializationStyle,
  Metadata,
  UnixTimeSchema,
  Language,
} from "@autorest/codemodel";
import { CodeModel } from "./common/code-model.js";
import { Client as CodeModelClient, ObjectScheme } from "./common/client.js";
import { ConvenienceApi, Operation as CodeModelOperation, Request } from "./common/operation.js";
import { SchemaContext, SchemaUsage } from "./common/schemas/usage.js";
import { ChoiceSchema, SealedChoiceSchema } from "./common/schemas/choice.js";
import { ConstantSchema, ConstantValue } from "./common/schemas/constant.js";
import { OrSchema } from "./common/schemas/relationship.js";
import { LongRunningMetadata } from "./common/long-running-metadata.js";
import { DurationSchema } from "./common/schemas/time.js";
import { PreNamer } from "./prenamer/prenamer.js";
import { EmitterOptions } from "./emitter.js";
import { createPollOperationDetailsSchema } from "./external-schemas.js";
import { ClientContext } from "./models.js";
import {
  stringArrayContainsIgnoreCase,
  getJavaNamespace,
  getNamespace,
  pascalCase,
  logWarning,
  trace,
} from "./utils.js";
import {
  ProcessingCache,
  isModelReferredInTemplate,
  pushDistinct,
  modelContainsDerivedModel,
  getNameForTemplate,
  getDurationFormat,
  hasScalarAsBase,
  isNullableType,
  isSameLiteralTypes,
  getAccess,
  getUsage,
  getUnionDescription,
  modelIs,
  getModelNameForProperty,
  isAllValueInteger,
} from "./type-utils.js";
import {
  getClientApiVersions,
  getServiceVersion,
  operationIsJsonMergePatch,
  isPayloadProperty,
  ORIGIN_API_VERSION,
  SPECIAL_HEADER_NAMES,
  loadExamples,
  isLroNewPollingStrategy,
  operationIsMultipleContentTypes,
  cloneOperationParameter,
  operationIsMultipart,
  isKnownContentType,
  CONTENT_TYPE_KEY,
} from "./operation-utils.js";
import pkg from "lodash";
import { getExtensions } from "@typespec/openapi";
const { isEqual } = pkg;

export class CodeModelBuilder {
  private program: Program;
  private typeNameOptions: TypeNameOptions;
  private namespace: string;
  private sdkContext: SdkContext;

  private options: EmitterOptions;

  private codeModel: CodeModel;

  readonly schemaCache = new ProcessingCache((type: Type, name: string) => this.processSchemaImpl(type, name));
  readonly typeUnionRefCache = new Map<Type, Union | null | undefined>(); // Union means it ref a Union type, null means it does not ref any Union, nndefined means type visited but not completed

  private operationExamples: Map<Operation, any> = new Map<Operation, any>();

  public constructor(program1: Program, context: EmitContext<EmitterOptions>) {
    this.options = context.options;
    this.program = program1;

    if (this.options["skip-special-headers"]) {
      this.options["skip-special-headers"].forEach((it) => SPECIAL_HEADER_NAMES.add(it.toLowerCase()));
    }

    this.sdkContext = createSdkContext(context, "@azure-tools/typespec-java");
    const service = listServices(this.program)[0];
    const serviceNamespace = service.type;
    if (serviceNamespace === undefined) {
      throw Error("Can not emit yaml for a namespace that doesn't exist.");
    }

    // java namespace
    this.namespace = getNamespaceFullName(serviceNamespace) || "Azure.Client";
    const javaNamespace = getJavaNamespace(this.namespace);

    const namespace1 = this.namespace;
    this.typeNameOptions = {
      // shorten type names by removing TypeSpec and service namespace
      namespaceFilter(ns) {
        const name = getNamespaceFullName(ns);
        return name !== "Cadl" && name !== namespace1;
      },
    };

    // init code model
    const title = this.options["service-name"] ?? serviceNamespace.name;

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

    // auth
    // TODO: it is not very likely, but different client could have different auth
    const auth = getAuthentication(this.program, serviceNamespace);
    if (auth) {
      this.processAuth(auth);
    }
  }

  public async build(): Promise<CodeModel> {
    this.operationExamples = await loadExamples(this.program, this.options);

    const clients = this.processClients();

    this.processModels(clients);

    this.processSchemaUsage();

    if (this.options.namer) {
      this.codeModel = new PreNamer(this.codeModel).init().process();
    }

    this.deduplicateSchemaName();

    return this.codeModel;
  }

  private processHost(server: HttpServer | undefined): Parameter[] {
    const hostParameters: Parameter[] = [];
    if (server) {
      server.parameters.forEach((it) => {
        let parameter;

        if (isApiVersion(this.sdkContext, it)) {
          parameter = this.createApiVersionParameter(it.name, ParameterLocation.Uri);
        } else {
          const schema = this.processSchema(it.type, it.name);
          this.trackSchemaUsage(schema, {
            usage: [SchemaContext.Input, SchemaContext.Output /*SchemaContext.Public*/],
          });
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
            extensions: {
              "x-ms-skip-url-encoding": schema instanceof UriSchema,
            },
          });
        }

        hostParameters.push(this.codeModel.addGlobalParameter(parameter));
      });
      return hostParameters;
    } else {
      hostParameters.push(
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
      return hostParameters;
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

          case "http":
            {
              let schemeOrApiKeyPrefix: string = scheme.scheme;
              if (schemeOrApiKeyPrefix === "basic" || schemeOrApiKeyPrefix === "bearer") {
                // HTTP Authentication should use "Basic token" or "Bearer token"
                schemeOrApiKeyPrefix = pascalCase(schemeOrApiKeyPrefix);

                if (!(this.options.branded === false)) {
                  // Azure would not allow BasicAuth or BearerAuth
                  this.logWarning(`{scheme.scheme} auth method is currently not supported.`);
                  continue;
                }
              }

              const keyScheme = new KeySecurityScheme({
                name: "authorization",
              });
              (keyScheme as any).prefix = schemeOrApiKeyPrefix; // TODO (weidxu): modify KeySecurityScheme, after design stable
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

  private isInternal(context: SdkContext, operation: Operation): boolean {
    const access = getAccess(operation);
    if (access) {
      return access === "internal";
    } else {
      // TODO: deprecate "internal"
      // eslint-disable-next-line deprecation/deprecation
      return isInternal(context, operation);
    }
  }

  private processModels(clients: SdkClient[]) {
    const processedModels: Set<Type> = new Set();
    for (const client of clients) {
      const models: (Model | Enum)[] = Array.from(client.service.models.values());
      Array.from(client.service.enums.values()).forEach((it) => models.push(it));

      // lambda to mark model as public
      const modelAsPublic = (model: Model | Enum) => {
        // check it does not contain Union
        // const union = unionReferredByType(this.program, model, this.typeUnionRefCache);
        // if (union) {
        //   const errorMsg = `Model '${getTypeName(
        //     model,
        //     this.typeNameOptions,
        //   )}' cannot be set as access=public, as it refers Union '${getUnionDescription(union, this.typeNameOptions)}'`;
        //   throw new Error(errorMsg);
        // }

        const schema = this.processSchema(model, model.name);

        this.trackSchemaUsage(schema, {
          usage: [SchemaContext.Public],
        });
      };

      for (const model of models) {
        if (!processedModels.has(model)) {
          const access = getAccess(model);
          if (access === "public") {
            modelAsPublic(model);
          } else if (access === "internal") {
            const schema = this.processSchema(model, model.name);

            this.trackSchemaUsage(schema, {
              usage: [SchemaContext.Internal],
            });
          }

          const usage = getUsage(model);
          if (usage) {
            const schema = this.processSchema(model, model.name);

            this.trackSchemaUsage(schema, {
              usage: usage,
            });
          }

          processedModels.add(model);
        }
      }
    }
  }

  private processSchemaUsage() {
    this.codeModel.schemas.objects?.forEach((it) => this.propagateSchemaUsage(it));

    // post process for schema usage
    this.codeModel.schemas.objects?.forEach((it) => this.resolveSchemaUsage(it));
    this.codeModel.schemas.groups?.forEach((it) => this.resolveSchemaUsage(it));
    this.codeModel.schemas.choices?.forEach((it) => this.resolveSchemaUsage(it));
    this.codeModel.schemas.sealedChoices?.forEach((it) => this.resolveSchemaUsage(it));
    this.codeModel.schemas.ors?.forEach((it) => this.resolveSchemaUsage(it));
    this.codeModel.schemas.constants?.forEach((it) => this.resolveSchemaUsage(it));
  }

  private deduplicateSchemaName() {
    // deduplicate model name
    const nameCount = new Map<string, number>();
    const deduplicateName = (schema: Schema) => {
      const name = schema.language.default.name;
      // skip models under "com.azure.core."
      if (name && !schema.language.java?.namespace?.startsWith("com.azure.core.")) {
        if (!nameCount.has(name)) {
          nameCount.set(name, 1);
        } else {
          const count = nameCount.get(name)!;
          nameCount.set(name, count + 1);
          schema.language.default.name = name + count;
        }
      }
    };
    this.codeModel.schemas.objects?.forEach((it) => deduplicateName(it));
    this.codeModel.schemas.groups?.forEach((it) => deduplicateName(it)); // it may contain RequestConditions under "com.azure.core."
    this.codeModel.schemas.choices?.forEach((it) => deduplicateName(it));
    this.codeModel.schemas.sealedChoices?.forEach((it) => deduplicateName(it));
    this.codeModel.schemas.ors?.forEach((it) => deduplicateName(it));
    this.codeModel.schemas.constants?.forEach((it) => deduplicateName(it));
  }

  private resolveSchemaUsage(schema: Schema) {
    if (
      schema instanceof ObjectSchema ||
      schema instanceof GroupSchema ||
      schema instanceof ChoiceSchema ||
      schema instanceof SealedChoiceSchema ||
      schema instanceof OrSchema ||
      schema instanceof ConstantSchema
    ) {
      const schemaUsage: SchemaContext[] | undefined = schema.usage;
      // Public override Internal
      if (schemaUsage?.includes(SchemaContext.Public)) {
        const index = schemaUsage.indexOf(SchemaContext.Internal);
        if (index >= 0) {
          schemaUsage.splice(index, 1);
        }
      }
    }
  }

  private processClients(): SdkClient[] {
    const clients = listClients(this.sdkContext);
    for (const client of clients) {
      if (client.arm) {
        this.codeModel.arm = true;
      }
      const codeModelClient = new CodeModelClient(client.name, this.getDoc(client.type), {
        summary: this.getSummary(client.type),

        // at present, use global security definition
        security: this.codeModel.security,
      });
      codeModelClient.crossLanguageDefinitionId = client.crossLanguageDefinitionId;

      // versioning
      const versioning = getVersion(this.program, client.service);
      if (versioning && versioning.getVersions()) {
        // @versioned in versioning
        codeModelClient.apiVersions = [];
        for (const version of versioning.getVersions()) {
          const apiVersion = new ApiVersion();
          apiVersion.version = version.value;
          codeModelClient.apiVersions.push(apiVersion);
        }
      } else {
        // fallback to @service.version
        const service = getService(this.program, client.service);
        if (service?.version) {
          codeModelClient.apiVersions = [];
          const apiVersion = new ApiVersion();
          apiVersion.version = service.version;
          codeModelClient.apiVersions.push(apiVersion);
        } else {
          throw new Error(`API version not available for client ${client.name}.`);
        }
      }

      // server
      let baseUri = "{endpoint}";
      const servers = getServers(this.program, client.service);
      if (servers && servers.length === 1) {
        baseUri = servers[0].url;
      }
      const hostParameters = this.processHost(servers?.length === 1 ? servers[0] : undefined);
      codeModelClient.addGlobalParameters(hostParameters);
      const clientContext = new ClientContext(
        baseUri,
        hostParameters,
        codeModelClient.globalParameters!,
        codeModelClient.apiVersions,
      );
      clientContext.preProcessOperations(this.sdkContext, client);

      const operationGroups = listOperationGroups(this.sdkContext, client, true);

      const operationWithoutGroup = listOperationsInOperationGroup(this.sdkContext, client);
      let codeModelGroup = new OperationGroup("");
      for (const operation of operationWithoutGroup) {
        if (!this.needToSkipProcessingOperation(operation, clientContext)) {
          codeModelGroup.addOperation(this.processOperation("", operation, clientContext));
        }
      }
      if (codeModelGroup.operations?.length > 0) {
        codeModelClient.operationGroups.push(codeModelGroup);
      }

      for (const operationGroup of operationGroups) {
        const operations = listOperationsInOperationGroup(this.sdkContext, operationGroup);
        // operation group with no operation is skipped
        if (operations.length > 0) {
          const groupPath = operationGroup.groupPath.split(".");
          let oprationGroupName: string;
          if (groupPath.length > 1) {
            // groupPath should be in format of "OpenAIClient.Chat.Completions"
            oprationGroupName = groupPath.slice(1).join("");
          } else {
            // protection
            oprationGroupName = operationGroup.type.name;
          }
          codeModelGroup = new OperationGroup(oprationGroupName);
          for (const operation of operations) {
            if (!this.needToSkipProcessingOperation(operation, clientContext)) {
              codeModelGroup.addOperation(this.processOperation(oprationGroupName, operation, clientContext));
            }
          }
          codeModelClient.operationGroups.push(codeModelGroup);
        }
      }

      this.codeModel.clients.push(codeModelClient);
    }

    // postprocess for ServiceVersion
    let apiVersionSameForAllClients = true;
    let sharedApiVersions = undefined;
    for (const client of this.codeModel.clients) {
      const apiVersions = getClientApiVersions(client);
      if (!apiVersions) {
        // client does not have apiVersions
        apiVersionSameForAllClients = false;
      } else if (!sharedApiVersions) {
        // first client, set it to sharedApiVersions
        sharedApiVersions = apiVersions;
      } else {
        apiVersionSameForAllClients = isEqual(sharedApiVersions, apiVersions);
      }
      if (!apiVersionSameForAllClients) {
        break;
      }
    }
    if (apiVersionSameForAllClients) {
      const serviceVersion = getServiceVersion(this.codeModel);
      for (const client of this.codeModel.clients) {
        client.serviceVersion = serviceVersion;
      }
    } else {
      for (const client of this.codeModel.clients) {
        const apiVersions = getClientApiVersions(client);
        if (apiVersions) {
          client.serviceVersion = getServiceVersion(client);
        }
      }
    }

    return clients;
  }

  private needToSkipProcessingOperation(operation: Operation, clientContext: ClientContext): boolean {
    // don't generate protocol and convenience method for overloaded operations
    // issue link: https://github.com/Azure/autorest.java/issues/1958#issuecomment-1562558219 we will support generate overload methods for non-union type in future (TODO issue: https://github.com/Azure/autorest.java/issues/2160)
    if (getOverloadedOperation(this.program, operation)) {
      this.trace(`Operation '${operation.name}' is temporary skipped, as it is an overloaded operation`);
      return true;
    }
    return false;
  }

  private processOperation(groupName: string, operation: Operation, clientContext: ClientContext): CodeModelOperation {
    const op = ignoreDiagnostics(getHttpOperation(this.program, operation));

    const operationGroup = this.codeModel.getOperationGroup(groupName);
    const operationName = this.getName(operation);
    const opId = groupName ? `${groupName}_${operationName}` : `${operationName}`;

    const operationExample = this.operationExamples.get(operation);

    const codeModelOperation = new CodeModelOperation(operationName, this.getDoc(operation), {
      operationId: opId,
      summary: this.getSummary(operation),
      extensions: {
        "x-ms-examples": operationExample
          ? { [operationExample.title ?? operationExample.operationId ?? operation.name]: operationExample }
          : undefined,
      },
    });

    codeModelOperation.crossLanguageDefinitionId = getCrossLanguageDefinitionId(operation);
    codeModelOperation.internalApi = this.isInternal(this.sdkContext, operation);

    const convenienceApiName = this.getConvenienceApiName(operation);
    let generateConvenienceApi: boolean = Boolean(convenienceApiName);
    let generateProtocolApi: boolean = shouldGenerateProtocol(this.sdkContext, operation);

    let apiComment: string | undefined = undefined;
    if (generateConvenienceApi) {
      // check if the convenience API need to be disabled for some special cases
      if (operationIsJsonMergePatch(op)) {
        // do not generate convenience method for JSON Merge Patch
        generateConvenienceApi = false;
        apiComment = `Convenience API is not generated, as operation '${op.operation.name}' is 'application/merge-patch+json'`;
        this.logWarning(apiComment);
      } else if (operationIsMultipart(op)) {
        // do not generate protocol method for multipart/form-data, as it be very hard for user to prepare the request body as BinaryData
        generateProtocolApi = false;
        apiComment = `Protocol API requires serialization of parts with content-disposition and data, as operation '${op.operation.name}' is 'multipart/form-data'`;
        this.logWarning(apiComment);
      } else if (operationIsMultipleContentTypes(op)) {
        // and multiple content types
        // issue link: https://github.com/Azure/autorest.java/issues/1958#issuecomment-1562558219
        generateConvenienceApi = false;
        apiComment = `Convenience API is not generated, as operation '${op.operation.name}' is multiple content-type`;
        this.logWarning(apiComment);
      }
      // else {
      //   const union = operationRefersUnion(this.program, op, this.typeUnionRefCache);
      //   if (union) {
      //     // and Union
      //     generateConvenienceApi = false;
      //     apiComment = `Convenience API is not generated, as operation '${
      //       op.operation.name
      //     }' refers Union '${getUnionDescription(union, this.typeNameOptions)}'`;
      //     this.logWarning(apiComment);
      //   }
      // }
    }
    if (generateConvenienceApi && convenienceApiName) {
      codeModelOperation.convenienceApi = new ConvenienceApi(convenienceApiName);
    }
    if (apiComment) {
      codeModelOperation.language.java = new Language();
      codeModelOperation.language.java.comment = apiComment;
    }

    // check for generating protocol api or not
    codeModelOperation.generateProtocolApi = generateProtocolApi && !codeModelOperation.internalApi;

    codeModelOperation.addRequest(
      new Request({
        protocol: {
          http: {
            path: op.path,
            method: op.verb,
            uri: clientContext.baseUri,
          },
        },
      }),
    );

    // host
    clientContext.hostParameters.forEach((it) => codeModelOperation.addParameter(it));
    // parameters
    op.parameters.parameters.map((it) => this.processParameter(codeModelOperation, it, clientContext));
    // "accept" header
    this.addAcceptHeaderParameter(codeModelOperation, op.responses);
    // body
    if (op.parameters.body) {
      if (op.parameters.body.parameter) {
        this.processParameterBody(codeModelOperation, op, op.parameters.body.parameter);
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

          this.processParameterBody(codeModelOperation, op, bodyType);
        }
      }
    }

    // group ETag header parameters, if exists
    this.processEtagHeaderParameters(codeModelOperation, op);

    // lro metadata
    const lroMetadata = this.processLroMetadata(codeModelOperation, op);

    // responses
    const candidateResponseSchema = lroMetadata.pollResultType; // candidate: response body type of pollingOperation
    op.responses.map((it) => this.processResponse(codeModelOperation, it, candidateResponseSchema));

    // check for paged
    this.processRouteForPaged(codeModelOperation, op.responses);
    // check for long-running operation
    this.processRouteForLongRunning(codeModelOperation, operation, op.responses, lroMetadata);

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

  private processLroMetadata(op: CodeModelOperation, httpOperation: HttpOperation): LongRunningMetadata {
    const operation = httpOperation.operation;

    const lroMetadata = getLroMetadata(this.program, operation);
    // needs lroMetadata.statusMonitorStep, as getLroMetadata would return for @pollingOperation operation
    if (lroMetadata && lroMetadata.pollingInfo && lroMetadata.statusMonitorStep) {
      let pollingSchema = undefined;
      let finalSchema = undefined;

      const verb = httpOperation.verb;
      const useNewPollStrategy = isLroNewPollingStrategy(httpOperation, lroMetadata);

      let pollingStrategy: Metadata | undefined = undefined;
      if (useNewPollStrategy) {
        // use new experimental OperationLocationPollingStrategy
        pollingStrategy = new Metadata({
          language: {
            java: {
              name: "OperationLocationPollingStrategy",
              namespace: "com.azure.core.experimental.util.polling",
            },
          },
        });
      }

      // pollingSchema
      if (useNewPollStrategy) {
        // com.azure.core.experimental.models.PollResult
        pollingSchema = this.pollResultSchema;
      } else {
        if (modelIs(lroMetadata.pollingInfo.responseModel, "OperationStatus", "Azure.Core.Foundations")) {
          pollingSchema = this.pollResultSchema;
        } else {
          const pollType = this.findResponseBody(lroMetadata.pollingInfo.responseModel);
          pollingSchema = this.processSchema(pollType, "pollResult");
        }
      }

      // finalSchema
      if (verb !== "delete" && lroMetadata.logicalResult) {
        const finalResult = useNewPollStrategy ? lroMetadata.logicalResult : lroMetadata.envelopeResult;
        const finalType = this.findResponseBody(finalResult);
        finalSchema = this.processSchema(finalType, "finalResult");
      }

      // track usage
      if (pollingSchema) {
        this.trackSchemaUsage(pollingSchema, { usage: [SchemaContext.Output] });
        if (op.internalApi) {
          this.trackSchemaUsage(pollingSchema, { usage: [SchemaContext.Internal] });
        } else if (op.convenienceApi) {
          this.trackSchemaUsage(pollingSchema, { usage: [SchemaContext.Public] });
        }
      }
      if (finalSchema) {
        this.trackSchemaUsage(finalSchema, { usage: [SchemaContext.Output] });
        if (op.internalApi) {
          this.trackSchemaUsage(pollingSchema, { usage: [SchemaContext.Internal] });
        } else if (op.convenienceApi) {
          this.trackSchemaUsage(finalSchema, { usage: [SchemaContext.Public] });
        }
      }

      op.lroMetadata = new LongRunningMetadata(true, pollingSchema, finalSchema, pollingStrategy);
      return op.lroMetadata;
    }

    return new LongRunningMetadata(false);
  }

  private processRouteForLongRunning(
    op: CodeModelOperation,
    operation: Operation,
    responses: HttpOperationResponse[],
    lroMetadata: LongRunningMetadata,
  ) {
    if (lroMetadata.longRunning) {
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

    if (this.isArmLongRunningOperation(this.program, operation)) {
      op.extensions = op.extensions ?? {};
      op.extensions["x-ms-long-running-operation"] = true;
    }
  }

  private processParameter(op: CodeModelOperation, param: HttpOperationParameter, clientContext: ClientContext) {
    if (isApiVersion(this.sdkContext, param)) {
      const parameter = param.type === "query" ? this.apiVersionParameter : this.apiVersionParameterInPath;
      op.addParameter(parameter);
      clientContext.addGlobalParameter(parameter);
    } else if (SPECIAL_HEADER_NAMES.has(param.name.toLowerCase())) {
      // special headers
      op.specialHeaders = op.specialHeaders ?? [];
      if (!stringArrayContainsIgnoreCase(op.specialHeaders, param.name)) {
        op.specialHeaders.push(param.name);
      }
    } else {
      // schema
      let schema;
      if (
        param.type === "header" &&
        param.param.type.kind === "Scalar" &&
        getEncode(this.program, param.param) === undefined &&
        getEncode(this.program, param.param.type) === undefined &&
        (hasScalarAsBase(param.param.type, "utcDateTime") || hasScalarAsBase(param.param.type, "offsetDateTime"))
      ) {
        // utcDateTime in header maps to rfc7231
        schema = this.processDateTimeSchema(param.param.type, param.param.name, true);
      } else {
        schema = this.processSchema(param.param, param.param.name);
      }

      // skip-url-encoding
      let extensions: { [id: string]: any } | undefined = undefined;
      if (
        (param.type === "query" || param.type === "path") &&
        param.param.type.kind === "Scalar" &&
        schema instanceof UriSchema
      ) {
        extensions = { "x-ms-skip-url-encoding": true };
      }

      // currently under dev-options.support-versioning
      if (this.options["dev-options"] && this.options["dev-options"]["support-versioning"]) {
        // versioning
        const addedOn = getAddedOnVersions(this.program, param.param);
        if (addedOn) {
          extensions = extensions ?? {};
          extensions["x-ms-versioning-added"] = clientContext.getAddedVersions(addedOn);
        }
      }

      // format if array
      let style = undefined;
      let explode = undefined;
      if (param.param.type.kind === "Model" && isArrayModelType(this.program, param.param.type)) {
        if (param.type === "query") {
          const queryParamOptions = getQueryParamOptions(this.program, param.param);
          switch (queryParamOptions?.format) {
            case "csv":
              style = SerializationStyle.Simple;
              break;

            case "ssv":
              style = SerializationStyle.SpaceDelimited;
              break;

            case "tsv":
              style = SerializationStyle.TabDelimited;
              break;

            case "pipes":
              style = SerializationStyle.PipeDelimited;
              break;

            case "multi":
              style = SerializationStyle.Form;
              explode = true;
              break;

            default:
              if (queryParamOptions?.format) {
                this.logWarning(`Unrecognized query parameter format: '${queryParamOptions?.format}'.`);
              }
              break;
          }
        } else if (param.type === "header") {
          const headerFieldOptions = getHeaderFieldOptions(this.program, param.param);
          switch (headerFieldOptions?.format) {
            case "csv":
              style = SerializationStyle.Simple;
              break;

            default:
              if (headerFieldOptions?.format) {
                this.logWarning(`Unrecognized header parameter format: '${headerFieldOptions?.format}'.`);
              }
              break;
          }
        }
      }

      const nullable = isNullableType(param.param.type);
      const parameter = new Parameter(this.getName(param.param), this.getDoc(param.param), schema, {
        summary: this.getSummary(param.param),
        implementation: ImplementationLocation.Method,
        required: !param.param.optional,
        nullable: nullable,
        protocol: {
          http: new HttpParameter(param.type, {
            style: style,
            explode: explode,
          }),
        },
        // clientDefaultValue: this.getDefaultValue(param.param.default),
        language: {
          default: {
            serializedName: param.name,
          },
        },
        extensions: extensions,
      });
      op.addParameter(parameter);

      this.trackSchemaUsage(schema, { usage: [SchemaContext.Input] });

      if (op.internalApi) {
        this.trackSchemaUsage(schema, { usage: [SchemaContext.Internal] });
      } else if (op.convenienceApi) {
        this.trackSchemaUsage(schema, { usage: [SchemaContext.Public] });
      }

      if (param.name.toLowerCase() === CONTENT_TYPE_KEY) {
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
    if (op.parameters?.some((it) => it.language.default.serializedName?.toLowerCase() === "accept")) {
      // parameters already include "accept" header
      return;
    }

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

  private processEtagHeaderParameters(op: CodeModelOperation, httpOperation: HttpOperation) {
    if (op.convenienceApi && op.parameters && op.signatureParameters) {
      const etagHeadersNames = new Set<string>([
        "if-match",
        "if-none-match",
        "if-unmodified-since",
        "if-modified-since",
      ]);

      // collect etag headers in parameters
      const etagHeaders: string[] = [];
      if (op.parameters) {
        for (const parameter of op.parameters) {
          if (
            parameter.language.default.serializedName &&
            etagHeadersNames.has(parameter.language.default.serializedName.toLowerCase())
          ) {
            etagHeaders.push(parameter.language.default.serializedName);
          }
        }
      }

      let groupToRequestConditions = false;
      let groupToMatchConditions = false;

      if (etagHeaders.length === 4) {
        // all 4 headers available, use RequestConditions
        groupToRequestConditions = true;
      } else if (etagHeaders.length === 2) {
        const etagHeadersLowerCase = etagHeaders.map((it) => it.toLowerCase());
        if (etagHeadersLowerCase.includes("if-match") && etagHeadersLowerCase.includes("if-none-match")) {
          // only 2 headers available, use MatchConditions
          groupToMatchConditions = true;
        }
      }

      if (groupToRequestConditions || groupToMatchConditions) {
        op.convenienceApi.requests = [];
        const request = new Request();
        request.parameters = [];
        request.signatureParameters = [];
        op.convenienceApi.requests.push(request);

        for (const parameter of op.parameters) {
          // copy all parameters to request
          const clonedParameter = cloneOperationParameter(parameter);
          request.parameters.push(clonedParameter);

          // copy signatureParameters, but exclude etag headers (as they won't be in method signature)
          if (
            op.signatureParameters.includes(parameter) &&
            !(
              parameter.language.default.serializedName &&
              etagHeaders.includes(parameter.language.default.serializedName)
            )
          ) {
            request.signatureParameters.push(clonedParameter);
          }
        }

        const namespace = getNamespace(httpOperation.operation);
        const schemaName = groupToRequestConditions ? "RequestConditions" : "MatchConditions";
        const schemaDescription = groupToRequestConditions
          ? "Specifies HTTP options for conditional requests based on modification time."
          : "Specifies HTTP options for conditional requests.";

        // group schema
        const requestConditionsSchema = this.codeModel.schemas.add(
          new GroupSchema(schemaName, schemaDescription, {
            language: {
              default: {
                namespace: namespace,
              },
              java: {
                namespace: "com.azure.core.http",
              },
            },
          }),
        );

        // parameter (optional) of the group schema
        const requestConditionsParameter = new Parameter(
          schemaName,
          requestConditionsSchema.language.default.description,
          requestConditionsSchema,
          {
            implementation: ImplementationLocation.Method,
            required: false,
            nullable: true,
          },
        );

        this.trackSchemaUsage(requestConditionsSchema, { usage: [SchemaContext.Input] });
        if (op.internalApi) {
          this.trackSchemaUsage(requestConditionsSchema, { usage: [SchemaContext.Internal] });
        } else if (op.convenienceApi) {
          this.trackSchemaUsage(requestConditionsSchema, { usage: [SchemaContext.Public] });
        }

        // update group schema for properties
        for (const parameter of request.parameters) {
          if (
            parameter.language.default.serializedName &&
            etagHeaders.includes(parameter.language.default.serializedName)
          ) {
            parameter.groupedBy = requestConditionsParameter;

            requestConditionsSchema.add(
              // name is serializedName, as it must be same as that in RequestConditions class
              new GroupProperty(
                parameter.language.default.serializedName,
                parameter.language.default.description,
                parameter.schema,
                {
                  originalParameter: [parameter],
                  summary: parameter.summary,
                  required: false,
                  nullable: true,
                  readOnly: false,
                  serializedName: parameter.language.default.serializedName,
                },
              ),
            );
          }
        }

        // put RequestConditions/MatchConditions as last parameter/signatureParameters
        request.parameters.push(requestConditionsParameter);
        request.signatureParameters.push(requestConditionsParameter);
      }
    }
  }

  private processParameterBody(op: CodeModelOperation, httpOperation: HttpOperation, body: ModelProperty | Model) {
    const parameters = httpOperation.operation.parameters;

    const unknownRequestBody =
      op.requests![0].protocol.http!.mediaTypes &&
      op.requests![0].protocol.http!.mediaTypes.length > 0 &&
      !isKnownContentType(op.requests![0].protocol.http!.mediaTypes);

    let schema: Schema;
    if (
      unknownRequestBody &&
      body.kind === "ModelProperty" &&
      body.type.kind === "Scalar" &&
      body.type.name === "bytes"
    ) {
      // handle binary request body
      schema = this.processBinarySchema(body.type);
    } else {
      schema = this.processSchema(body, body.name);
    }
    const parameter = new Parameter(this.getName(body), this.getDoc(body), schema, {
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

    if (op.internalApi) {
      this.trackSchemaUsage(schema, { usage: [SchemaContext.Internal] });
    } else if (op.convenienceApi) {
      this.trackSchemaUsage(schema, { usage: [SchemaContext.Public] });
    }

    if (operationIsJsonMergePatch(httpOperation)) {
      this.trackSchemaUsage(schema, { usage: [SchemaContext.JsonMergePatch] });
    }
    if (op.convenienceApi && operationIsMultipart(httpOperation)) {
      this.trackSchemaUsage(schema, { usage: [SchemaContext.MultipartFormData] });
    }

    if (!schema.language.default.name && schema instanceof ObjectSchema) {
      // anonymous model

      // name the schema for documentation
      schema.language.default.name = op.language.default.name + "Request";

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

        for (const [_, opParameter] of parameters.properties) {
          const serializedName = this.getSerializedName(opParameter);
          const existParameter = op.parameters.find((it) => it.language.default.serializedName === serializedName);
          if (existParameter) {
            // parameter
            if (
              existParameter.implementation === ImplementationLocation.Method &&
              (existParameter.origin?.startsWith("modelerfour:synthesized/") ?? true)
            ) {
              request.parameters.push(cloneOperationParameter(existParameter));
            }
          } else {
            // property from anonymous model
            const existBodyProperty = schema.properties?.find((it) => it.serializedName === serializedName);
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
          const namespace = getNamespace(httpOperation.operation);
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

          this.trackSchemaUsage(optionBagSchema, { usage: [SchemaContext.Input] });
          if (op.internalApi) {
            this.trackSchemaUsage(optionBagSchema, { usage: [SchemaContext.Internal] });
          } else if (op.convenienceApi) {
            this.trackSchemaUsage(optionBagSchema, { usage: [SchemaContext.Public] });
          }

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

  private processResponse(
    op: CodeModelOperation,
    resp: HttpOperationResponse,
    candidateResponseSchema: Schema | undefined = undefined,
  ) {
    // TODO: what to do if more than 1 response?
    let response: Response;
    let headers: Array<HttpHeader> | undefined = undefined;
    if (resp.responses && resp.responses.length > 0 && resp.responses[0].headers) {
      // headers
      headers = [];
      for (const [key, header] of Object.entries(resp.responses[0].headers)) {
        const schema = this.processSchema(header, key);
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

    let bodyType: Type | undefined = undefined;
    let trackConvenienceApi = op.convenienceApi ?? false;
    if (resp.responses && resp.responses.length > 0 && resp.responses[0].body) {
      const responseBody = resp.responses[0].body;
      const unknownResponseBody =
        responseBody.contentTypes.length > 0 && !isKnownContentType(responseBody.contentTypes);

      bodyType = this.findResponseBody(responseBody.type);
      if (unknownResponseBody && bodyType.kind === "Scalar" && bodyType.name === "bytes") {
        // binary
        response = new BinaryResponse({
          protocol: {
            http: {
              statusCodes: this.getStatusCodes(resp.statusCodes),
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
        let schema: Schema | undefined = undefined;
        const verb = op.requests?.[0].protocol?.http?.method;
        if (
          // LRO, candidateResponseSchema is Model/ObjectSchema
          candidateResponseSchema &&
          candidateResponseSchema instanceof ObjectSchema &&
          // bodyType is templated Model
          bodyType.kind === "Model" &&
          bodyType.templateMapper &&
          bodyType.templateMapper.args &&
          bodyType.templateMapper.args.length > 0
        ) {
          if (verb === "post") {
            // for LRO ResourceAction, the standard does not require a final type, hence it can be the same as intermediate type
            // https://github.com/microsoft/api-guidelines/blob/vNext/azure/ConsiderationsForServiceDesign.md#long-running-action-operations

            // check if we can use candidateResponseSchema as response schema (instead of the templated Model), for LRO ResourceAction
            if (candidateResponseSchema.properties?.length === bodyType.properties.size) {
              let match = true;
              for (const prop of Array.from(bodyType.properties.values())) {
                const name = this.getName(prop);
                if (!candidateResponseSchema.properties?.find((it) => it.language.default.name === name)) {
                  match = false;
                  break;
                }
              }
              if (match) {
                schema = candidateResponseSchema;
                this.trace(
                  `Replace TypeSpec model '${this.getName(bodyType)}' with '${
                    candidateResponseSchema.language.default.name
                  }'`,
                );
              }
            }
          } else if (verb === "delete") {
            // for LRO ResourceDelete, final type will be replaced to "Void" in convenience API, hence do not generate the class
            trackConvenienceApi = false;
          }
        }
        if (!schema) {
          if (verb === "post" && op.lroMetadata && op.lroMetadata.pollResultType) {
            // for standard LRO action, return type is the pollResultType
            schema = op.lroMetadata.pollResultType;
          } else {
            schema = this.processSchema(bodyType, op.language.default.name + "Response");
          }
        }
        response = new SchemaResponse(schema, {
          protocol: {
            http: {
              statusCodes: this.getStatusCodes(resp.statusCodes),
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
            statusCodes: this.getStatusCodes(resp.statusCodes),
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
    if (resp.statusCodes === "*" || (bodyType && isErrorModel(this.program, bodyType))) {
      // "*", or the model is @error
      op.addException(response);

      if (response instanceof SchemaResponse) {
        this.trackSchemaUsage(response.schema, { usage: [SchemaContext.Exception] });
      }
    } else {
      op.addResponse(response);

      if (response instanceof SchemaResponse) {
        this.trackSchemaUsage(response.schema, { usage: [SchemaContext.Output] });

        if (op.internalApi) {
          this.trackSchemaUsage(response.schema, { usage: [SchemaContext.Internal] });
        } else if (trackConvenienceApi) {
          this.trackSchemaUsage(response.schema, { usage: [SchemaContext.Public] });
        }
      }
    }
  }

  private getStatusCodes(statusCodes: HttpStatusCodesEntry): string[] {
    if (statusCodes === "*") {
      return ["default"];
    } else if (typeof statusCodes === "number") {
      return [statusCodes.toString()];
    } else {
      // HttpStatusCodeRange
      // azure-core does not support "status code range", hence here we expand the range to array of status codes
      return Array(statusCodes.end - statusCodes.start + 1)
        .fill(statusCodes.start)
        .map((it, index) => it + index)
        .map((it) => it.toString());
    }
  }

  private getResponseDescription(resp: HttpOperationResponse): string {
    return (
      resp.description ||
      (resp.statusCodes === "*" ? "An unexpected error response" : getStatusCodeDescription(resp.statusCodes)) ||
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
        return this.processConstantSchemaForLiteral(type, nameHint);

      case "Number":
        // TODO: float
        return this.processConstantSchemaForLiteral(type, nameHint);

      case "Boolean":
        return this.processConstantSchemaForLiteral(type, nameHint);

      case "Enum":
        return this.processChoiceSchema(type, this.getName(type), isFixed(this.program, type));

      case "Union":
        return this.processUnionSchema(type, this.getName(type, nameHint));

      case "ModelProperty": {
        let schema = undefined;
        const knownValues = getKnownValues(this.program, type);
        if (knownValues) {
          // use it for extensible enum
          schema = this.processChoiceSchema(knownValues, this.getName(knownValues), false);
        } else {
          const schemaNameHint = pascalCase(getModelNameForProperty(type)) + pascalCase(nameHint);
          schema = this.processSchema(type.type, schemaNameHint);
        }
        return this.applyModelPropertyDecorators(type, nameHint, schema);
      }

      case "Scalar":
        return this.processScalar(type, undefined, nameHint);

      case "Model":
        if (isArrayModelType(this.program, type)) {
          return this.processArraySchema(type, nameHint);
        } else if (isRecordModelType(this.program, type) && type.properties.size == 0) {
          // "pure" Record that does not have properties in it
          return this.processDictionarySchema(type, nameHint);
        } else {
          return this.processObjectSchema(type, this.getName(type, nameHint));
        }

      case "EnumMember":
        // e.g. "type: TypeEnum.EnumValue1"
        return this.processConstantSchemaForEnumMember(type, this.getName(type));
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

        case "utcDateTime":
        case "offsetDateTime":
          return this.processDateTimeSchema(type, nameHint, false);

        case "duration":
          return this.processDurationSchema(type, nameHint);

        case "url":
          return this.processUrlSchema(type, nameHint);
      }

      if (scalarName.startsWith("decimal")) {
        // decimal
        return this.processDecimalSchema(type, nameHint);
      } else if (scalarName.startsWith("int") || scalarName.startsWith("uint") || scalarName === "safeint") {
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
        const encode = getEncode(this.program, type);
        if (encode) {
          // process as encode
          if (encode.encoding === "seconds" && hasScalarAsBase(type, "duration")) {
            return this.processDurationSchema(type, nameHint, getDurationFormat(encode));
          } else if (
            (encode.encoding === "rfc3339" || encode.encoding === "rfc7231" || encode.encoding === "unixTimestamp") &&
            (hasScalarAsBase(type, "utcDateTime") || hasScalarAsBase(type, "offsetDateTime"))
          ) {
            if (encode.encoding === "unixTimestamp") {
              return this.processUnixTimeSchema(type, nameHint);
            } else {
              return this.processDateTimeSchema(type, nameHint, encode.encoding === "rfc7231");
            }
          } else if (encode.encoding === "base64url" && hasScalarAsBase(type, "bytes")) {
            return this.processByteArraySchema(type, nameHint, true);
          }
        }

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
    return this.anySchema;
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

  private processDecimalSchema(type: Scalar, name: string): NumberSchema {
    // "Infinity" maps to "BigDecimal" in Java
    return this.codeModel.schemas.add(
      new NumberSchema(name, this.getDoc(type), SchemaType.Number, Infinity, {
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

    if (type.indexer.value.kind === "Union") {
      dictSchema.nullableItems =
        Array.from(type.indexer.value.variants.values()).findIndex((it) => isNullType(it.type)) >= 0;
    }

    return this.codeModel.schemas.add(dictSchema);
  }

  private processChoiceSchema(
    type: Enum,
    name: string,
    sealed: boolean,
  ): ChoiceSchema | SealedChoiceSchema | ConstantSchema {
    const namespace = getNamespace(type);
    const valueType =
      typeof type.members.values().next().value.value === "number"
        ? isAllValueInteger(Array.from(type.members.values()).map((it) => it.value as number))
          ? this.integerSchema
          : this.doubleSchema
        : this.stringSchema;

    const choices: ChoiceValue[] = [];
    type.members.forEach((it) => choices.push(new ChoiceValue(it.name, this.getDoc(it), it.value ?? it.name)));

    const schemaType = sealed ? SealedChoiceSchema : ChoiceSchema;

    const schema = new schemaType(name, this.getDoc(type), {
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
    });
    schema.crossLanguageDefinitionId = getCrossLanguageDefinitionId(type);
    return this.codeModel.schemas.add(schema);
  }

  private processConstantSchemaForLiteral(
    type: StringLiteral | NumericLiteral | BooleanLiteral,
    name: string,
  ): ConstantSchema {
    const valueType =
      type.kind === "String"
        ? this.stringSchema
        : type.kind === "Boolean"
          ? this.booleanSchema
          : isAllValueInteger([type.value])
            ? this.integerSchema
            : this.doubleSchema;

    return this.codeModel.schemas.add(
      new ConstantSchema(name, this.getDoc(type), {
        summary: this.getSummary(type),
        valueType: valueType,
        value: new ConstantValue(type.value),
      }),
    );
  }

  private processConstantSchemaForEnumMember(type: EnumMember, name: string): ConstantSchema {
    const valueType = this.processSchema(type.enum, this.getName(type.enum));

    return this.codeModel.schemas.add(
      new ConstantSchema(name, this.getDoc(type), {
        summary: this.getSummary(type),
        valueType: valueType,
        value: new ConstantValue(type.value ?? type.name),
      }),
    );
  }

  private processChoiceSchemaForUnion(
    type: Union,
    variants: UnionVariant[],
    name: string,
  ): ChoiceSchema | SealedChoiceSchema {
    // variants is Literal

    const kindSet = new Set(variants.map((it) => it.type.kind));
    // "choice1" | "choice2" is sealed
    // "choice1" | "choice2" | string is extensible
    const sealed = kindSet.size === 1;

    variants = variants.filter(
      (it) => it.type.kind === "String" || it.type.kind === "Number" || it.type.kind === "Boolean",
    );
    const kind = variants[0].type.kind;
    const valueType =
      kind === "String"
        ? this.stringSchema
        : kind === "Boolean"
          ? this.booleanSchema
          : isAllValueInteger(variants.map((it) => (it.type as any).value))
            ? this.integerSchema
            : this.doubleSchema;

    const choices: ChoiceValue[] = [];
    variants.forEach((it) =>
      choices.push(new ChoiceValue((it.type as any).value.toString(), this.getDoc(it), (it.type as any).value)),
    );

    const namespace = getNamespace(type);

    const schemaType = sealed ? SealedChoiceSchema : ChoiceSchema;
    const schema = new schemaType(name, this.getDoc(type), {
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
    });
    // schema.crossLanguageDefinitionId = getCrossLanguageDefinitionId(type);
    return this.codeModel.schemas.add(schema);
  }

  private processUnixTimeSchema(type: Scalar, name: string): UnixTimeSchema {
    return this.codeModel.schemas.add(
      new UnixTimeSchema(name, this.getDoc(type), {
        summary: this.getSummary(type),
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

  private processDurationSchema(
    type: Scalar,
    name: string,
    format: DurationSchema["format"] = "duration-rfc3339",
  ): DurationSchema {
    return this.codeModel.schemas.add(
      new DurationSchema(name, this.getDoc(type), {
        summary: this.getSummary(type),
        format: format,
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
    const objectSchema = new ObjectScheme(name, this.getDoc(type), {
      summary: this.getSummary(type),
      language: {
        default: {
          namespace: namespace,
        },
        java: {
          namespace: getJavaNamespace(namespace),
        },
      },
    });
    objectSchema.crossLanguageDefinitionId = getCrossLanguageDefinitionId(type);
    this.codeModel.schemas.add(objectSchema);

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
      } else {
        // parentSchema could be DictionarySchema, which means the model is "additionalProperties"
        pushDistinct(objectSchema.parents.all, parentSchema);
      }
    } else if (isRecordModelType(this.program, type)) {
      // "pure" Record processed elsewhere
      // "mixed" Record that have properties, treat the model as "additionalProperties"
      const parentSchema = this.processDictionarySchema(type, this.getName(type));
      objectSchema.parents = new Relations();
      objectSchema.parents.immediate.push(parentSchema);
      pushDistinct(objectSchema.parents.all, parentSchema);
    }

    // value of the discriminator property
    if (objectSchema.parents) {
      const parentWithDiscriminator = objectSchema.parents.all.find(
        (it) => it instanceof ObjectSchema && it.discriminator,
      );
      if (parentWithDiscriminator) {
        discriminatorPropertyName = (parentWithDiscriminator as ObjectSchema).discriminator!.property.serializedName;

        const discriminatorProperty = Array.from(type.properties.values()).find(
          (it) => it.name === discriminatorPropertyName && (it.type.kind === "String" || it.type.kind === "EnumMember"),
        );
        if (discriminatorProperty) {
          if (discriminatorProperty.type.kind === "String") {
            // value of StringLiteral of the discriminator property
            objectSchema.discriminatorValue = discriminatorProperty.type.value;
          } else if (discriminatorProperty.type.kind === "EnumMember") {
            // value of EnumMember of the discriminator property
            // lint requires value be string, not number
            objectSchema.discriminatorValue =
              (discriminatorProperty.type.value as string) ?? discriminatorProperty.type.name;
          }
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
        isNeverType(prop.type) || // skip property of type "never"
        !isPayloadProperty(this.program, prop)
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
      return isPayloadProperty(program, property);
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
    } else if (prop.type.kind === "Scalar") {
      const encode = getEncode(this.program, prop);
      if (encode) {
        if (encode.encoding === "seconds" && hasScalarAsBase(prop.type, "duration")) {
          schema = this.processDurationSchema(prop.type, nameHint, getDurationFormat(encode));
        } else if (
          (encode.encoding === "rfc3339" || encode.encoding === "rfc7231" || encode.encoding === "unixTimestamp") &&
          (hasScalarAsBase(prop.type, "utcDateTime") || hasScalarAsBase(prop.type, "offsetDateTime"))
        ) {
          if (encode.encoding === "unixTimestamp") {
            return this.processUnixTimeSchema(prop.type, nameHint);
          } else {
            return this.processDateTimeSchema(prop.type, nameHint, encode.encoding === "rfc7231");
          }
        } else if (encode.encoding === "base64url" && hasScalarAsBase(prop.type, "bytes")) {
          return this.processByteArraySchema(prop.type, nameHint, true);
        }
      }
    }
    return schema;
  }

  private processModelProperty(prop: ModelProperty): Property {
    const schema = this.processSchema(prop, prop.name);
    let nullable = isNullableType(prop.type);

    let extensions = undefined;
    if (this.isSecret(prop)) {
      extensions = {
        "x-ms-secret": true,
      };
      // if the property does not return in response, it had to be nullable
      nullable = true;
    }

    return new Property(this.getName(prop), this.getDoc(prop), schema, {
      summary: this.getSummary(prop),
      required: !prop.optional,
      nullable: nullable,
      readOnly: this.isReadOnly(prop),
      // clientDefaultValue: this.getDefaultValue(prop.default),
      serializedName: this.getSerializedName(prop),
      extensions: extensions,
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
      case "eTag":
        return this.processStringSchema(type, nameHint);
      default:
        this.logWarning(`Unrecognized string format: '${format}'.`);
        return this.processStringSchema(type, nameHint);
    }
  }

  private processUnionSchema(type: Union, name: string): Schema {
    const nonNullVariants = Array.from(type.variants.values()).filter((it) => !isNullType(it.type));
    if (nonNullVariants.length === 1) {
      // nullable
      return this.processSchema(nonNullVariants[0].type, name);
    }

    if (isSameLiteralTypes(nonNullVariants)) {
      // enum
      return this.processChoiceSchemaForUnion(type, nonNullVariants, name);
    }

    // TODO: name from typespec-client-generator-core
    const namespace = getNamespace(type);
    const baseName = pascalCase(name) + "Model";
    this.logWarning(
      `Convert TypeSpec Union '${getUnionDescription(type, this.typeNameOptions)}' to Class '${baseName}'`,
    );
    const unionSchema = new OrSchema(baseName + "Base", this.getDoc(type), {
      summary: this.getSummary(type),
    });
    unionSchema.anyOf = [];
    nonNullVariants.forEach((it) => {
      const variantName = this.getUnionVariantName(it.type, { depth: 0 });
      const modelName = variantName + baseName;
      const propertyName = "value";

      // these ObjectSchema is not added to codeModel.schemas
      const objectSchema = new ObjectSchema(modelName, this.getDoc(type), {
        summary: this.getSummary(type),
        language: {
          default: {
            namespace: namespace,
          },
          java: {
            namespace: getJavaNamespace(namespace),
          },
        },
      });

      const variantSchema = this.processSchema(it.type, variantName);
      objectSchema.addProperty(
        new Property(propertyName, this.getDoc(type), variantSchema, {
          summary: this.getSummary(type),
          required: true,
          nullable: true,
          readOnly: false,
        }),
      );
      unionSchema.anyOf.push(objectSchema);
    });
    return this.codeModel.schemas.add(unionSchema);
  }

  private processBinarySchema(type: Scalar): BinarySchema {
    return this.codeModel.schemas.add(
      new BinarySchema(this.getDoc(type), {
        summary: this.getSummary(type),
      }),
    );
  }

  private getUnionVariantName(type: Type, option: any): string {
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
        } else if (scalarName === "utcDateTime" || scalarName === "offsetDateTime") {
          name = "Time";
        }
        return pascalCase(name);
      }
      case "Enum":
        return pascalCase(type.name);
      case "Model":
        if (isArrayModelType(this.program, type)) {
          ++option.depth;
          if (option.depth == 1) {
            return this.getUnionVariantName(type.indexer.value, option) + "List";
          } else {
            return "ListOf" + this.getUnionVariantName(type.indexer.value, option);
          }
        } else if (isRecordModelType(this.program, type)) {
          ++option.depth;
          if (option.depth == 1) {
            return this.getUnionVariantName(type.indexer.value, option) + "Map";
          } else {
            return "MapOf" + this.getUnionVariantName(type.indexer.value, option);
          }
        } else {
          return pascalCase(type.name);
        }
      case "String":
        return pascalCase(type.value);
      case "Number":
        return pascalCase(type.valueAsString);
      case "Boolean":
        return pascalCase(type.value ? "True" : "False");
      default:
        throw new Error(`Unrecognized type for union variable: '${type.kind}'.`);
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

  private getName(
    target: Model | Union | Enum | EnumMember | ModelProperty | Scalar | Operation,
    nameHint: string | undefined = undefined,
  ): string {
    // TODO: once getLibraryName API in typespec-client-generator-core can get projected name from language and client, as well as can handle template case, use getLibraryName API
    const languageProjectedName = getProjectedName(this.program, target, "java");
    if (languageProjectedName) {
      return languageProjectedName;
    }

    const clientProjectedName = getProjectedName(this.program, target, "client");
    if (clientProjectedName) {
      return clientProjectedName;
    }

    const friendlyName = getFriendlyName(this.program, target);
    if (friendlyName) {
      return friendlyName;
    }

    // if no projectedName and friendlyName found, return the name of the target (including special handling for template)
    if (
      target.kind === "Model" &&
      target.templateMapper &&
      target.templateMapper.args &&
      target.templateMapper.args.length > 0
    ) {
      const tspName = getTypeName(target, this.typeNameOptions);
      const newName = getNameForTemplate(target);
      this.logWarning(`Rename TypeSpec Model '${tspName}' to '${newName}'`);
      return newName;
    }

    if (!target.name && nameHint) {
      const newName = nameHint;
      this.logWarning(`Rename anonymous TypeSpec ${target.kind} to '${newName}'`);
      return newName;
    }
    return target.name || "";
  }

  private getSerializedName(target: ModelProperty): string {
    // First get projected name, if not found, return target.name
    const jsonProjectedName = getProjectedName(this.program, target, "json");
    if (jsonProjectedName) {
      return jsonProjectedName;
    }
    return target.name;
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

  private isSecret(target: ModelProperty): boolean {
    const visibility = getVisibility(this.program, target);
    if (visibility) {
      return !visibility.includes("read");
    } else {
      return false;
    }
  }

  private getConvenienceApiName(op: Operation): string | undefined {
    // check @convenienceMethod
    if (shouldGenerateConvenient(this.sdkContext, op)) {
      return this.getName(op);
    } else {
      return undefined;
    }
  }

  private logWarning(msg: string) {
    logWarning(this.program, msg);
  }

  private trace(msg: string) {
    trace(this.program, msg);
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

  private _doubleSchema?: NumberSchema;
  get doubleSchema(): NumberSchema {
    return (
      this._doubleSchema ||
      (this._doubleSchema = this.codeModel.schemas.add(
        new NumberSchema("double", "simple float", SchemaType.Number, 64),
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

  private _anySchema?: AnySchema;
  get anySchema(): AnySchema {
    return this._anySchema ?? (this._anySchema = this.codeModel.schemas.add(new AnySchema("Anything")));
  }

  private _pollResultSchema?: ObjectSchema;
  get pollResultSchema(): ObjectSchema {
    return (
      this._pollResultSchema ??
      (this._pollResultSchema = createPollOperationDetailsSchema(this.codeModel.schemas, this.stringSchema))
    );
  }

  private createApiVersionParameter(serializedName: string, parameterLocation: ParameterLocation): Parameter {
    return new Parameter(
      serializedName,
      "Version parameter",
      this.codeModel.schemas.add(
        new ConstantSchema(serializedName, "API Version", {
          valueType: this.stringSchema,
          value: new ConstantValue(""),
        }),
      ),
      {
        implementation: ImplementationLocation.Client,
        origin: ORIGIN_API_VERSION,
        required: true,
        protocol: {
          http: new HttpParameter(parameterLocation),
        },
        language: {
          default: {
            serializedName: serializedName,
          },
        },
      },
    );
  }

  private _apiVersionParameter?: Parameter;
  get apiVersionParameter(): Parameter {
    return (
      this._apiVersionParameter ||
      (this._apiVersionParameter = this.createApiVersionParameter("api-version", ParameterLocation.Query))
    );
  }

  private _apiVersionParameterInPath?: Parameter;
  get apiVersionParameterInPath(): Parameter {
    return (
      this._apiVersionParameterInPath ||
      // TODO: hardcode as "apiVersion", as it is what we get from compiler
      (this._apiVersionParameterInPath = this.createApiVersionParameter("apiVersion", ParameterLocation.Path))
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
      if (schema instanceof ObjectSchema || schema instanceof GroupSchema) {
        if (schemaUsage.usage || schemaUsage.serializationFormats) {
          schema.properties?.forEach((p) => {
            if (p.readOnly && schemaUsage.usage?.includes(SchemaContext.Input)) {
              const schemaUsageWithoutInput = {
                usage: schemaUsage.usage.filter((it) => it != SchemaContext.Input),
                serializationFormats: schemaUsage.serializationFormats,
              };
              innerApplySchemaUsage(p.schema, schemaUsageWithoutInput);
            } else {
              innerApplySchemaUsage(p.schema, schemaUsage);
            }
          });

          if (schema instanceof ObjectSchema) {
            schema.parents?.all?.forEach((p) => innerApplySchemaUsage(p, schemaUsage));
            schema.parents?.immediate?.forEach((p) => innerApplySchemaUsage(p, schemaUsage));

            schema.children?.all?.forEach((c) => innerApplySchemaUsage(c, schemaUsage));
            schema.children?.immediate?.forEach((c) => innerApplySchemaUsage(c, schemaUsage));
          }

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
      } else if (schema instanceof OrSchema) {
        schema.anyOf?.forEach((it) => innerApplySchemaUsage(it, schemaUsage));
      }
    };

    // Exclude context that not to be propagated
    const schemaUsage = {
      usage: (schema as SchemaUsage).usage?.filter(
        (it) => it !== SchemaContext.Paged && it !== SchemaContext.Anonymous && it !== SchemaContext.MultipartFormData,
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
      schema instanceof OrSchema ||
      schema instanceof ConstantSchema
    ) {
      if (schemaUsage.usage) {
        pushDistinct((schema.usage = schema.usage || []), ...schemaUsage.usage);
      }
    } else if (schema instanceof DictionarySchema) {
      this.trackSchemaUsage(schema.elementType, schemaUsage);
    } else if (schema instanceof ArraySchema) {
      this.trackSchemaUsage(schema.elementType, schemaUsage);
    }
  }

  private isArmLongRunningOperation(program: Program, op: Operation) {
    return this.codeModel.arm && Boolean(getExtensions(program, op)?.get("x-ms-long-running-operation"));
  }

  private isSchemaUsageEmpty(schema: Schema): boolean {
    if (
      schema instanceof ObjectSchema ||
      schema instanceof GroupSchema ||
      schema instanceof ChoiceSchema ||
      schema instanceof SealedChoiceSchema ||
      schema instanceof OrSchema ||
      schema instanceof ConstantSchema
    ) {
      return !(schema.usage && schema.usage.length > 0);
    } else if (schema instanceof DictionarySchema) {
      return this.isSchemaUsageEmpty(schema.elementType);
    } else if (schema instanceof ArraySchema) {
      return this.isSchemaUsageEmpty(schema.elementType);
    }
    return false;
  }
}
