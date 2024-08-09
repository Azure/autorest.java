import {
  AnySchema,
  ApiVersion,
  ArraySchema,
  BinaryResponse,
  BinarySchema,
  BooleanSchema,
  ByteArraySchema,
  ChoiceValue,
  DateSchema,
  DateTimeSchema,
  DictionarySchema,
  Discriminator,
  GroupProperty,
  GroupSchema,
  HttpHeader,
  HttpParameter,
  ImplementationLocation,
  KeySecurityScheme,
  Language,
  Metadata,
  NumberSchema,
  OAuth2SecurityScheme,
  ObjectSchema,
  OperationGroup,
  Parameter,
  ParameterLocation,
  Property,
  Relations,
  Response,
  Schema,
  SchemaResponse,
  SchemaType,
  Security,
  SecurityScheme,
  SerializationStyle,
  StringSchema,
  TimeSchema,
  UnixTimeSchema,
  UriSchema,
  VirtualParameter,
} from "@autorest/codemodel";
import { KnownMediaType } from "@azure-tools/codegen";
import { isPollingLocation } from "@azure-tools/typespec-azure-core";
import {
  SdkArrayType,
  SdkBodyModelPropertyType,
  SdkBuiltInType,
  SdkClientType,
  SdkConstantType,
  SdkContext,
  SdkDateTimeType,
  SdkDictionaryType,
  SdkDurationType,
  SdkEnumType,
  SdkEnumValueType,
  SdkHeaderParameter,
  SdkHttpOperation,
  SdkHttpResponse,
  SdkLroPagingServiceMethod,
  SdkLroServiceMethod,
  SdkMethod,
  SdkModelPropertyType,
  SdkModelType,
  SdkPathParameter,
  SdkQueryParameter,
  SdkServiceMethod,
  SdkType,
  SdkUnionType,
  UsageFlags,
  createSdkContext,
  getAllModels,
  getClientNameOverride,
  getClientType,
  getWireName,
  isApiVersion,
  isInternal,
  isSdkBuiltInKind,
  isSdkIntKind,
  shouldGenerateConvenient,
  shouldGenerateProtocol,
} from "@azure-tools/typespec-client-generator-core";
import {
  EmitContext,
  Interface,
  Model,
  ModelProperty,
  Namespace,
  Operation,
  Program,
  Scalar,
  Type,
  TypeNameOptions,
  Union,
  getDoc,
  getEffectiveModelType,
  getFriendlyName,
  getNamespaceFullName,
  getOverloadedOperation,
  getProjectedName,
  getSummary,
  getVisibility,
  isArrayModelType,
  isRecordModelType,
  listServices,
} from "@typespec/compiler";
import {
  Authentication,
  HttpOperation,
  HttpOperationResponse,
  HttpServer,
  HttpStatusCodeRange,
  HttpStatusCodesEntry,
  Visibility,
  getAuthentication,
  getHeaderFieldName,
  getPathParamName,
  getQueryParamName,
  getStatusCodeDescription,
  isHeader,
  isPathParam,
  isQueryParam,
} from "@typespec/http";
import { getSegment } from "@typespec/rest";
import { getAddedOnVersions } from "@typespec/versioning";
import { fail } from "assert";
import pkg from "lodash";
import { Client as CodeModelClient, CrossLanguageDefinition } from "./common/client.js";
import { CodeModel } from "./common/code-model.js";
import { LongRunningMetadata } from "./common/long-running-metadata.js";
import { Operation as CodeModelOperation, ConvenienceApi, Request } from "./common/operation.js";
import { ChoiceSchema, SealedChoiceSchema } from "./common/schemas/choice.js";
import { ConstantSchema, ConstantValue } from "./common/schemas/constant.js";
import { OrSchema } from "./common/schemas/relationship.js";
import { DurationSchema } from "./common/schemas/time.js";
import { SchemaContext, SchemaUsage } from "./common/schemas/usage.js";
import { EmitterOptions } from "./emitter.js";
import { createPollOperationDetailsSchema, getFileDetailsSchema } from "./external-schemas.js";
import { ClientContext } from "./models.js";
import {
  ORIGIN_API_VERSION,
  SPECIAL_HEADER_NAMES,
  cloneOperationParameter,
  getServiceVersion,
  isKnownContentType,
  isLroNewPollingStrategy,
  isPayloadProperty,
  sdkHttpOperationIsJsonMergePatch,
  sdkHttpOperationIsMultipart,
  sdkHttpOperationIsMultipleContentTypes,
} from "./operation-utils.js";
import { PreNamer } from "./prenamer/prenamer.js";
import {
  ProcessingCache,
  getAccess,
  getDurationFormatFromSdkType,
  getNonNullSdkType,
  getUnionDescription,
  getUsage,
  modelIs,
  pushDistinct,
} from "./type-utils.js";
import { getNamespace, logWarning, pascalCase, stringArrayContainsIgnoreCase, trace } from "./utils.js";
import { pathToFileURL } from "url";
const { isEqual } = pkg;

export class CodeModelBuilder {
  private program: Program;
  private typeNameOptions: TypeNameOptions;
  private namespace: string;
  private sdkContext!: SdkContext;
  private options: EmitterOptions;
  private codeModel: CodeModel;
  private emitterContext: EmitContext<EmitterOptions>;
  private serviceNamespace: Namespace | Interface | Operation;

  private loggingEnabled: boolean = false;

  readonly schemaCache = new ProcessingCache((type: SdkType, name: string) =>
    this.processSchemaFromSdkTypeImpl(type, name),
  );
  readonly typeUnionRefCache = new Map<Type, Union | null | undefined>(); // Union means it ref a Union type, null means it does not ref any Union, undefined means type visited but not completed

  // current apiVersion name to generate code
  private apiVersion: string | undefined;

  public constructor(program1: Program, context: EmitContext<EmitterOptions>) {
    this.options = context.options;
    this.program = program1;
    this.emitterContext = context;
    if (this.options["dev-options"]?.loglevel) {
      this.loggingEnabled = true;
    }

    if (this.options["skip-special-headers"]) {
      this.options["skip-special-headers"].forEach((it) => SPECIAL_HEADER_NAMES.add(it.toLowerCase()));
    }

    const service = listServices(this.program)[0];
    const serviceNamespace = service.type;
    if (serviceNamespace === undefined) {
      throw Error("Cannot emit yaml for a namespace that doesn't exist.");
    }
    this.serviceNamespace = serviceNamespace;

    this.namespace = getNamespaceFullName(serviceNamespace) || "Azure.Client";
    // java namespace
    const javaNamespace = this.getJavaNamespace(this.namespace);

    const namespace1 = this.namespace;
    this.typeNameOptions = {
      // shorten type names by removing TypeSpec and service namespace
      namespaceFilter(ns) {
        const name = getNamespaceFullName(ns);
        return name !== "TypeSpec" && name !== namespace1;
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
  }

  public async build(): Promise<CodeModel> {
    this.sdkContext = await createSdkContext(this.emitterContext, "@azure-tools/typespec-java", {
      versioning: { previewStringRegex: /$/ },
    }); // include all versions and do the filter by ourselves

    // auth
    // TODO: it is not very likely, but different client could have different auth
    const auth = getAuthentication(this.program, this.serviceNamespace);
    if (auth) {
      this.processAuth(auth);
    }

    if (this.sdkContext.arm) {
      // ARM
      this.codeModel.arm = true;
      this.options["group-etag-headers"] = false;
    }

    // const clients = this.processClients();

    this.processClientsFromSdkType();

    this.processModels();

    this.processSchemaUsage();

    if (this.options.namer) {
      this.codeModel = new PreNamer(this.codeModel).init().process();
    }

    this.deduplicateSchemaName();

    return this.codeModel;
  }

  private processHostParametersFromSdkType(sdkPathParameters: SdkPathParameter[]): Parameter[] {
    const hostParameters: Parameter[] = [];
    let parameter;
    sdkPathParameters.forEach((arg) => {
      if (arg.isApiVersionParam) {
        parameter = this.createApiVersionParameter(arg.name, ParameterLocation.Uri);
      } else {
        const schema = this.processSchemaFromSdkType(arg.type, arg.name);
        this.trackSchemaUsage(schema, {
          usage: [SchemaContext.Input, SchemaContext.Output /*SchemaContext.Public*/],
        });
        parameter = new Parameter(arg.name, arg.description ?? "", schema, {
          implementation: ImplementationLocation.Client,
          origin: "modelerfour:synthesized/host",
          required: true,
          protocol: {
            http: new HttpParameter(ParameterLocation.Uri),
          },
          language: {
            default: {
              serializedName: arg.name,
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
  }

  private processAuth(auth: Authentication) {
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

                if (this.isBranded()) {
                  // Azure would not allow BasicAuth or BearerAuth
                  this.logWarning(`{scheme.scheme} auth method is currently not supported.`);
                  continue;
                }
              }

              const keyScheme = new KeySecurityScheme({
                name: "authorization",
              });
              (keyScheme as any).prefix = schemeOrApiKeyPrefix; // TODO: modify KeySecurityScheme, after design stable
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

  private isBranded(): boolean {
    return !this.options["flavor"] || this.options["flavor"].toLocaleLowerCase() === "azure";
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

  private processModels() {
    const processedSdkModels: Set<SdkModelType | SdkEnumType> = new Set();

    // lambda to mark model as public
    const modelAsPublic = (model: SdkModelType | SdkEnumType) => {
      const schema = this.processSchemaFromSdkType(model, "");

      this.trackSchemaUsage(schema, {
        usage: [SchemaContext.Public],
      });
    };

    const sdkModels: (SdkModelType | SdkEnumType)[] = getAllModels(this.sdkContext);

    // process sdk models
    for (const model of sdkModels) {
      if (!processedSdkModels.has(model)) {
        const access = getAccess(model.__raw);
        if (access === "public") {
          modelAsPublic(model);
        } else if (access === "internal") {
          const schema = this.processSchemaFromSdkType(model, model.name);

          this.trackSchemaUsage(schema, {
            usage: [SchemaContext.Internal],
          });
        }

        const usage = getUsage(model.__raw);
        if (usage) {
          const schema = this.processSchemaFromSdkType(model, "");

          this.trackSchemaUsage(schema, {
            usage: usage,
          });
        }

        processedSdkModels.add(model);
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

      // Internal on Anonymous
      if (schemaUsage?.includes(SchemaContext.Anonymous)) {
        const index = schemaUsage.indexOf(SchemaContext.Internal);
        if (index < 0) {
          schemaUsage.push(SchemaContext.Internal);
        }
      }
    }
  }

  private processClientsFromSdkType() {
    // preprocess group-etag-headers
    this.options["group-etag-headers"] = this.options["group-etag-headers"] ?? true;

    const sdkPackage = this.sdkContext.sdkPackage;
    for (const client of sdkPackage.clients) {
      let clientName = client.name;
      let javaNamespace = this.getJavaNamespace(this.namespace);
      const clientFullName = client.name;
      const clientNameSegments = clientFullName.split(".");
      if (clientNameSegments.length > 1) {
        clientName = clientNameSegments.at(-1)!;
        const clientSubNamespace = clientNameSegments.slice(0, -1).join(".");
        javaNamespace = this.getJavaNamespace(this.namespace + "." + clientSubNamespace);
      }

      const codeModelClient = new CodeModelClient(clientName, client.details ?? "", {
        summary: client.description,
        language: {
          default: {
            namespace: this.namespace,
          },
          java: {
            namespace: javaNamespace,
          },
        },

        // at present, use global security definition
        security: this.codeModel.security,
      });
      // codeModelClient.crossLanguageDefinitionId = client.crossLanguageDefinitionId;

      // versioning
      const versions = client.apiVersions;
      if (versions && versions.length > 0) {
        if (!this.sdkContext.apiVersion || ["all", "latest"].includes(this.sdkContext.apiVersion)) {
          this.apiVersion = versions[versions.length - 1];
        } else {
          this.apiVersion = versions.find((it: string) => it === this.sdkContext.apiVersion);
          if (!this.apiVersion) {
            throw new Error("Unrecognized api-version: " + this.sdkContext.apiVersion);
          }
        }

        codeModelClient.apiVersions = [];
        for (const version of this.getFilteredApiVersionsFromString(
          this.apiVersion,
          versions,
          this.options["service-version-exclude-preview"],
        )) {
          const apiVersion = new ApiVersion();
          apiVersion.version = version;
          codeModelClient.apiVersions.push(apiVersion);
        }
      }

      // client initialization
      let baseUri = "{endpoint}";
      let hostParameters: Parameter[] = [];
      client.initialization.properties.forEach((initializationProperty) => {
        if (initializationProperty.kind === "endpoint") {
          let sdkPathParameters: SdkPathParameter[] = [];
          if (initializationProperty.type.kind === "union") {
            if (initializationProperty.type.values.length <= 2) {
              // only get the path parameters from the endpoint whose serverUrl is not {"endpoint"}
              for (const endpointType of initializationProperty.type.values) {
                if (endpointType.kind === "endpoint" && endpointType.serverUrl !== "{endpoint}") {
                  sdkPathParameters = endpointType.templateArguments;
                  baseUri = endpointType.serverUrl;
                }
              }
            } else {
              throw new Error("multiple server url defined for one client is not supported yet");
            }
          } else if (initializationProperty.type.kind === "endpoint") {
            sdkPathParameters = initializationProperty.type.templateArguments;
            baseUri = initializationProperty.type.serverUrl;
          } else {
            throw new Error("unexpected endpoint parameter type");
          }

          hostParameters = this.processHostParametersFromSdkType(sdkPathParameters);
          codeModelClient.addGlobalParameters(hostParameters);
        }
      });

      const clientContext = new ClientContext(
        baseUri,
        hostParameters,
        codeModelClient.globalParameters!,
        codeModelClient.apiVersions,
      );

      // preprocess operation groups and operations
      // operations without operation group
      const serviceMethodsWithoutSubClient = this.listServiceMethodsUnderClient(client, false);
      let codeModelGroup = new OperationGroup("");
      for (const serviceMethod of serviceMethodsWithoutSubClient) {
        if (!this.needToSkipProcessingOperation(serviceMethod.__raw, clientContext)) {
          codeModelGroup.addOperation(this.processOperationFromSdkType(serviceMethod, clientContext, ""));
        }
      }
      if (codeModelGroup.operations?.length > 0) {
        codeModelClient.operationGroups.push(codeModelGroup);
      }

      // operations under operation groups
      const subClients = this.listSubClientsUnderClient(client, true, true);
      for (const subClient of subClients) {
        const serviceMethods = this.listServiceMethodsUnderClient(subClient, false);
        // operation group with no operation is skipped
        if (serviceMethods.length > 0) {
          codeModelGroup = new OperationGroup(subClient.name);
          for (const serviceMethod of serviceMethods) {
            if (!this.needToSkipProcessingOperation(serviceMethod.__raw, clientContext)) {
              codeModelGroup.addOperation(
                this.processOperationFromSdkType(serviceMethod, clientContext, subClient.name),
              );
            }
          }
          codeModelClient.operationGroups.push(codeModelGroup);
        }
      }
      this.codeModel.clients.push(codeModelClient);

      // postprocess for ServiceVersion
      let apiVersionSameForAllClients = true;
      let sharedApiVersions = undefined;
      for (const client of this.codeModel.clients) {
        const apiVersions = client.apiVersions;
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
          const apiVersions = client.apiVersions;
          if (apiVersions) {
            client.serviceVersion = getServiceVersion(client);
          }
        }
      }
    }
  }

  private buildSdkPathPathParameterForARM(): SdkPathParameter {
    return {
      kind: "path",
      name: "endpoint",
      isGeneratedName: true,
      description: "Service host",
      onClient: true,
      urlEncode: false,
      optional: false,
      serializedName: "endpoint",
      correspondingMethodParams: [],
      type: {
        kind: "string",
        encode: "string",
        decorators: [],
        name: "string",
        crossLanguageDefinitionId: "string",
      },
      isApiVersionParam: false,
      decorators: [],
      apiVersions: [],
      crossLanguageDefinitionId: "endpoint",
    };
  }

  private listSubClientsUnderClient(
    client: SdkClientType<SdkHttpOperation>,
    includeNestedOperationGroups: boolean,
    isRootClient: boolean,
  ): SdkClientType<SdkHttpOperation>[] {
    const operationGroups: SdkClientType<SdkHttpOperation>[] = [];
    for (const method of client.methods) {
      if (method.kind === "clientaccessor") {
        const subClient = method.response;
        if (!isRootClient) {
          // if it is not root client, append the parent client's name
          subClient.name = this.removeClientSuffix(client.name) + this.removeClientSuffix(pascalCase(subClient.name));
        }
        operationGroups.push(subClient);
        if (includeNestedOperationGroups) {
          for (const operationGroup of this.listSubClientsUnderClient(subClient, includeNestedOperationGroups, false)) {
            operationGroups.push(operationGroup);
          }
        }
      }
    }
    return operationGroups;
  }

  private listServiceMethodsUnderClient(
    client: SdkClientType<SdkHttpOperation>,
    includeNestedServiceMethods: boolean,
  ): SdkServiceMethod<SdkHttpOperation>[] {
    const methods: SdkServiceMethod<SdkHttpOperation>[] = [];
    for (const method of client.methods) {
      if (includeNestedServiceMethods) {
        if (method.kind === "clientaccessor") {
          const client = method.response;
          for (const method of this.listServiceMethodsUnderClient(client, includeNestedServiceMethods)) {
            methods.push(method);
          }
        }
      }
      if (method.kind !== "clientaccessor") {
        methods.push(method);
      }
    }
    return methods;
  }

  private removeClientSuffix(clientName: string): string {
    return clientName.endsWith("Client") ? clientName.slice(0, -6) : clientName;
  }

  /**
   * Filter api-versions for "ServiceVersion".
   * TODO(xiaofei) pending TCGC design: https://github.com/Azure/typespec-azure/issues/965
   *
   * @param pinnedApiVersion the api-version to use as filter base
   * @param versions api-versions to filter
   * @returns filtered api-versions
   */
  private getFilteredApiVersionsFromString(
    pinnedApiVersion: string | undefined,
    versions: string[],
    excludePreview: boolean = false,
  ): string[] {
    if (!pinnedApiVersion) {
      return versions;
    }
    return versions
      .slice(0, versions.indexOf(pinnedApiVersion) + 1)
      .filter((version) => !excludePreview || pinnedApiVersion.includes("preview") || !version.includes("preview"));
  }

  /**
   * `@armProviderNamespace` currently will add a default server if not defined globally:
   * https://github.com/Azure/typespec-azure/blob/8b8d7c05f168d9305a09691c4fedcb88f4a57652/packages/typespec-azure-resource-manager/src/namespace.ts#L121-L128
   * TODO: if the synthesized server has the right hostParameter, we can use that instead
   *
   * @param server returned by getServers
   * @returns whether it's synthesized by `@armProviderNamespace`
   */
  private isArmSynthesizedServer(server: HttpServer): boolean {
    return this.isArm() && (!server.parameters || server.parameters.size == 0);
  }

  private needToSkipProcessingOperation(operation: Operation | undefined, clientContext: ClientContext): boolean {
    // don't generate protocol and convenience method for overloaded operations
    // issue link: https://github.com/Azure/autorest.java/issues/1958#issuecomment-1562558219 we will support generate overload methods for non-union type in future (TODO issue: https://github.com/Azure/autorest.java/issues/2160)
    if (operation && getOverloadedOperation(this.program, operation)) {
      this.trace(`Operation '${operation.name}' is temporary skipped, as it is an overloaded operation`);
      return true;
    }
    return false;
  }

  /**
   * Whether we support advanced versioning in non-breaking fashion.
   */
  private supportsAdvancedVersioning(): boolean {
    return Boolean(this.options["advanced-versioning"]);
  }

  private getOperationExample(sdkMethod: SdkServiceMethod<SdkHttpOperation>): Record<string, any> | undefined {
    const httpOperationExamples = sdkMethod.operation.examples;
    if (httpOperationExamples && httpOperationExamples.length > 0) {
      const operationExamples: Record<string, any> = {};
      for (const example of httpOperationExamples) {
        const operationExample = example.rawExample;
        operationExample["x-ms-original-file"] = pathToFileURL(example.filePath).toString();
        operationExamples[operationExample.title ?? operationExample.operationId ?? sdkMethod.name] = operationExample;
      }
      return operationExamples;
    } else {
      return undefined;
    }
  }

  private processOperationFromSdkType(
    sdkMethod: SdkServiceMethod<SdkHttpOperation>,
    clientContext: ClientContext,
    groupName: string,
  ): CodeModelOperation {
    const operationName = sdkMethod.name;
    const httpOperation = sdkMethod.operation;
    const operationId = groupName ? `${groupName}_${operationName}` : `${operationName}`;
    const operationGroup = this.codeModel.getOperationGroup(groupName);

    let operationExamples = undefined;
    if (sdkMethod.operation && sdkMethod.operation.__raw) {
      operationExamples = this.getOperationExample(sdkMethod);
    }

    const codeModelOperation = new CodeModelOperation(operationName, sdkMethod.details ?? "", {
      operationId: operationId,
      summary: sdkMethod.description,
      extensions: {
        "x-ms-examples": operationExamples,
      },
    });

    (codeModelOperation as CrossLanguageDefinition).crossLanguageDefinitionId = sdkMethod.crossLanguageDefintionId;
    codeModelOperation.internalApi = sdkMethod.access === "internal";

    const convenienceApiName = this.getConvenienceApiNameFromServiceMethod(sdkMethod);
    let generateConvenienceApi: boolean = Boolean(convenienceApiName);
    let generateProtocolApi: boolean = sdkMethod.__raw
      ? shouldGenerateProtocol(this.sdkContext, sdkMethod.__raw)
      : true;

    let apiComment: string | undefined = undefined;
    if (generateConvenienceApi) {
      // check if the convenience API need to be disabled for some special cases
      if (sdkHttpOperationIsMultipart(httpOperation)) {
        // do not generate protocol method for multipart/form-data, as it be very hard for user to prepare the request body as BinaryData
        generateProtocolApi = false;
        apiComment = `Protocol API requires serialization of parts with content-disposition and data, as operation '${operationName}' is 'multipart/form-data'`;
        this.logWarning(apiComment);
      } else if (sdkHttpOperationIsMultipleContentTypes(httpOperation)) {
        // and multiple content types
        // issue link: https://github.com/Azure/autorest.java/issues/1958#issuecomment-1562558219
        generateConvenienceApi = false;
        apiComment = `Convenience API is not generated, as operation '${operationName}' is multiple content-type`;
        this.logWarning(apiComment);
      } else if (
        sdkHttpOperationIsJsonMergePatch(httpOperation) &&
        this.options["stream-style-serialization"] === false
      ) {
        // do not generate convenient method for json merge patch operation if stream-style-serialization is not enabled
        generateConvenienceApi = false;
        apiComment = `Convenience API is not generated, as operation '${operationName}' is 'application/merge-patch+json' and stream-style-serialization is not enabled`;
        this.logWarning(apiComment);
      }
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
            path: httpOperation.path,
            method: httpOperation.verb,
            uri: clientContext.baseUri,
          },
        },
      }),
    );

    // host
    clientContext.hostParameters.forEach((it) => codeModelOperation.addParameter(it));
    // path/query/header parameters
    for (const param of httpOperation.parameters) {
      // if it's paged operation with request body, skip content-type header added by TCGC, as next link call should not have content type header
      if ((sdkMethod.kind === "paging" || sdkMethod.kind === "lropaging") && httpOperation.bodyParam) {
        if (param.serializedName.toLocaleLowerCase() === "content-type") {
          continue;
        }
      }
      // if the request body is optional, skip content-type header added by TCGC
      if (httpOperation.bodyParam && httpOperation.bodyParam.optional) {
        if (param.serializedName.toLocaleLowerCase() === "content-type") {
          continue;
        }
      }
      this.processParameterFromSdkType(codeModelOperation, param, clientContext);
    }

    // body
    if (httpOperation.bodyParam && httpOperation.__raw && sdkMethod.__raw && httpOperation.bodyParam.type.__raw) {
      this.processParameterBodyFromSdkType(
        codeModelOperation,
        httpOperation.__raw,
        httpOperation,
        httpOperation.bodyParam,
      );
    }

    // group ETag header parameters, if exists
    if (this.options["group-etag-headers"]) {
      this.processEtagHeaderParametersFromSdkType(codeModelOperation, sdkMethod.operation);
    }

    // lro metadata
    let lroMetadata = new LongRunningMetadata(false);
    if (sdkMethod.kind === "lro" || sdkMethod.kind === "lropaging") {
      lroMetadata = this.processLroMetadataFromSdkType(codeModelOperation, sdkMethod);
    }

    // responses
    for (const [code, response] of sdkMethod.operation.responses) {
      this.processResponseFromSdkType(codeModelOperation, code, response, lroMetadata.longRunning, false);
    }

    // exception
    for (const [code, response] of sdkMethod.operation.exceptions) {
      this.processResponseFromSdkType(codeModelOperation, code, response, lroMetadata.longRunning, true);
    }

    // check for paged
    // this.processRouteForPaged(codeModelOperation, sdkMethod.operation.__raw.responses);
    this.processRouteForPagedFromSdkType(codeModelOperation, sdkMethod.operation.responses, sdkMethod);

    // check for long-running operation
    this.processRouteForLongRunningFromSdkType(codeModelOperation, sdkMethod.operation.responses, lroMetadata);

    operationGroup.addOperation(codeModelOperation);

    return codeModelOperation;
  }

  private processRouteForPagedFromSdkType(
    op: CodeModelOperation,
    responses: Map<number | HttpStatusCodeRange, SdkHttpResponse>,
    sdkMethod: SdkMethod<SdkHttpOperation>,
  ) {
    if (sdkMethod.kind === "paging" || sdkMethod.kind === "lropaging") {
      for (const [_, response] of responses) {
        const bodyType = response.type;
        if (bodyType && bodyType.kind === "model") {
          const pagedResult = sdkMethod.__raw_paged_metadata;
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

  private processLroMetadataFromSdkType(
    op: CodeModelOperation,
    sdkMethod: SdkLroServiceMethod<SdkHttpOperation> | SdkLroPagingServiceMethod<SdkHttpOperation>,
  ): LongRunningMetadata {
    const trackConvenienceApi: boolean = Boolean(op.convenienceApi);

    const lroMetadata = sdkMethod.__raw_lro_metadata;
    // needs lroMetadata.statusMonitorStep, as getLroMetadata would return for @pollingOperation operation
    if (lroMetadata && lroMetadata.pollingInfo && lroMetadata.statusMonitorStep) {
      let pollingSchema = undefined;
      let finalSchema = undefined;

      let pollingStrategy: Metadata | undefined = undefined;
      let finalResultPropertySerializedName: string | undefined = undefined;

      const verb = sdkMethod.operation.verb;
      const useNewPollStrategy = isLroNewPollingStrategy(sdkMethod.operation.__raw, lroMetadata);
      if (useNewPollStrategy) {
        // use OperationLocationPollingStrategy
        pollingStrategy = new Metadata({
          language: {
            java: {
              name: "OperationLocationPollingStrategy",
              namespace: this.getJavaNamespace(this.namespace) + ".implementation",
            },
          },
        });
      }

      // pollingSchema
      if (modelIs(lroMetadata.pollingInfo.responseModel, "OperationStatus", "Azure.Core.Foundations")) {
        pollingSchema = this.pollResultSchema;
      } else {
        const pollType = this.findResponseBody(lroMetadata.pollingInfo.responseModel);
        const sdkType = getClientType(this.sdkContext, pollType);
        pollingSchema = this.processSchemaFromSdkType(sdkType, "pollResult");
      }

      // finalSchema
      if (
        verb !== "delete" &&
        lroMetadata.finalResult &&
        lroMetadata.finalEnvelopeResult &&
        lroMetadata.finalResult !== "void" &&
        lroMetadata.finalEnvelopeResult !== "void"
      ) {
        const finalResult = useNewPollStrategy ? lroMetadata.finalResult : lroMetadata.finalEnvelopeResult;
        const finalType = this.findResponseBody(finalResult);
        const sdkType = getClientType(this.sdkContext, finalType);
        finalSchema = this.processSchemaFromSdkType(sdkType, "finalResult");

        if (
          useNewPollStrategy &&
          lroMetadata.finalStep &&
          lroMetadata.finalStep.kind === "pollingSuccessProperty" &&
          lroMetadata.finalStep.target
        ) {
          // final result is the value in lroMetadata.finalStep.target
          finalResultPropertySerializedName = this.getSerializedName(lroMetadata.finalStep.target);
        }
      }

      // track usage
      if (pollingSchema) {
        this.trackSchemaUsage(pollingSchema, { usage: [SchemaContext.Output] });
        if (trackConvenienceApi) {
          this.trackSchemaUsage(pollingSchema, {
            usage: [op.internalApi ? SchemaContext.Internal : SchemaContext.Public],
          });
        }
      }
      if (finalSchema) {
        this.trackSchemaUsage(finalSchema, { usage: [SchemaContext.Output] });
        if (trackConvenienceApi) {
          this.trackSchemaUsage(finalSchema, {
            usage: [op.internalApi ? SchemaContext.Internal : SchemaContext.Public],
          });
        }
      }

      op.lroMetadata = new LongRunningMetadata(
        true,
        pollingSchema,
        finalSchema,
        pollingStrategy,
        finalResultPropertySerializedName,
      );
      return op.lroMetadata;
    }

    return new LongRunningMetadata(false);
  }

  private processRouteForLongRunningFromSdkType(
    op: CodeModelOperation,
    responses: Map<number | HttpStatusCodeRange, SdkHttpResponse>,
    lroMetadata: LongRunningMetadata,
  ) {
    if (lroMetadata.longRunning) {
      op.extensions = op.extensions ?? {};
      op.extensions["x-ms-long-running-operation"] = true;
      return;
    }

    for (const [_, response] of responses) {
      if (response.headers) {
        for (const header of response.headers) {
          if (isPollingLocation(this.program, header.__raw)) {
            op.extensions = op.extensions ?? {};
            op.extensions["x-ms-long-running-operation"] = true;

            break;
          }
        }
      }
    }
  }

  private _armApiVersionParameter?: Parameter;

  private processParameterFromSdkType(
    op: CodeModelOperation,
    param: SdkQueryParameter | SdkPathParameter | SdkHeaderParameter,
    clientContext: ClientContext,
  ) {
    if (clientContext.apiVersions && isApiVersion(this.sdkContext, param)) {
      // pre-condition for "isApiVersion": the client supports ApiVersions
      if (this.isArm()) {
        // Currently we assume ARM tsp only have one client and one api-version.
        // TODO: How will service define mixed api-versions(like those in Compute RP)?
        const apiVersion = this.apiVersion;
        if (!this._armApiVersionParameter) {
          this._armApiVersionParameter = this.createApiVersionParameter(
            "api-version",
            param.kind === "query" ? ParameterLocation.Query : ParameterLocation.Path,
            apiVersion,
          );
          clientContext.addGlobalParameter(this._armApiVersionParameter);
        }
        op.addParameter(this._armApiVersionParameter);
      } else {
        const parameter = param.kind === "query" ? this.apiVersionParameter : this.apiVersionParameterInPath;
        op.addParameter(parameter);
        clientContext.addGlobalParameter(parameter);
      }
    } else if (param.kind === "path" && param.onClient && this.isSubscriptionId(param)) {
      const parameter = this.subscriptionIdParameter(param);
      op.addParameter(parameter);
      clientContext.addGlobalParameter(parameter);
    } else if (param.kind === "header" && SPECIAL_HEADER_NAMES.has(param.serializedName.toLowerCase())) {
      // special headers
      op.specialHeaders = op.specialHeaders ?? [];
      if (!stringArrayContainsIgnoreCase(op.specialHeaders, param.serializedName)) {
        op.specialHeaders.push(param.serializedName);
      }
    } else {
      // schema
      const sdkType = getNonNullSdkType(param.type);
      const schema = this.processSchemaFromSdkType(sdkType, param.name);

      // skip-url-encoding
      let extensions: { [id: string]: any } | undefined = undefined;
      if (
        (param.kind === "query" || param.kind === "path") &&
        isSdkBuiltInKind(sdkType.kind) && // TODO: question: Scalar -> builtin kinds, is it fine?
        schema instanceof UriSchema
      ) {
        extensions = { "x-ms-skip-url-encoding": true };
      }

      if (this.supportsAdvancedVersioning() && param.__raw) {
        // versioning
        const addedOn = getAddedOnVersions(this.program, param.__raw);
        if (addedOn) {
          extensions = extensions ?? {};
          extensions["x-ms-versioning-added"] = clientContext.getAddedVersions(addedOn);
        }
      }

      // format if array
      let style = undefined;
      let explode = undefined;
      if (sdkType.kind === "array") {
        if (param.kind === "query") {
          const format = param.collectionFormat;
          switch (format) {
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
              if (format) {
                this.logWarning(`Unrecognized query parameter format: '${format}'.`);
              }
              break;
          }
        } else if (param.kind === "header") {
          const format = param.collectionFormat;
          switch (format) {
            case "csv":
              style = SerializationStyle.Simple;
              break;

            default:
              if (format) {
                this.logWarning(`Unrecognized header parameter format: '${format}'.`);
              }
              break;
          }
        }
      }

      const nullable = param.type.kind === "nullable";
      const parameter = new Parameter(param.name, param.details ?? "", schema, {
        summary: param.description,
        implementation: ImplementationLocation.Method,
        required: !param.optional,
        nullable: nullable,
        protocol: {
          http: new HttpParameter(param.kind, {
            style: style,
            explode: explode,
          }),
        },
        language: {
          default: {
            serializedName: param.serializedName, // it uses param.name previously, but better to use param.serializedName directly
          },
        },
        extensions: extensions,
      });
      op.addParameter(parameter);

      this.trackSchemaUsage(schema, { usage: [SchemaContext.Input] });

      if (op.convenienceApi) {
        this.trackSchemaUsage(schema, { usage: [op.internalApi ? SchemaContext.Internal : SchemaContext.Public] });
      }
    }
  }

  private processEtagHeaderParametersFromSdkType(op: CodeModelOperation, httpOperation: SdkHttpOperation) {
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
        const request = new Request({
          protocol: op.requests![0].protocol,
        });
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

        const namespace = getNamespace(httpOperation.__raw.operation); // TODO: SdkHttpOperation does not have namespace
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
        if (op.convenienceApi) {
          this.trackSchemaUsage(requestConditionsSchema, {
            usage: [op.internalApi ? SchemaContext.Internal : SchemaContext.Public],
          });
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

  private processParameterBodyFromSdkType(
    op: CodeModelOperation,
    rawHttpOperation: HttpOperation,
    sdkHttpOperation: SdkHttpOperation,
    sdkBody: SdkModelPropertyType,
  ) {
    // set contentTypes to mediaTypes
    op.requests![0].protocol.http!.mediaTypes = rawHttpOperation.parameters.body!.contentTypes;

    const unknownRequestBody =
      op.requests![0].protocol.http!.mediaTypes &&
      op.requests![0].protocol.http!.mediaTypes.length > 0 &&
      !isKnownContentType(op.requests![0].protocol.http!.mediaTypes);

    const sdkType: SdkType = sdkBody.type;

    let schema: Schema;
    if (unknownRequestBody && sdkType.kind === "bytes") {
      // if it's unknown request body, handle binary request body
      schema = this.processBinarySchemaFromSdkType(sdkType);
    } else {
      schema = this.processSchemaFromSdkType(getNonNullSdkType(sdkType), sdkBody.name);
    }

    const parameterName = sdkBody.name;
    const parameter = new Parameter(parameterName, sdkBody.description ?? "", schema, {
      summary: sdkBody.details,
      implementation: ImplementationLocation.Method,
      required: !sdkBody.optional,
      protocol: {
        http: new HttpParameter(ParameterLocation.Body),
      },
    });
    op.addParameter(parameter);

    this.trackSchemaUsage(schema, { usage: [SchemaContext.Input] });

    if (op.convenienceApi) {
      // model/schema does not need to be Public or Internal, if it is not to be used in convenience API
      this.trackSchemaUsage(schema, { usage: [op.internalApi ? SchemaContext.Internal : SchemaContext.Public] });
    }

    if (sdkHttpOperationIsJsonMergePatch(sdkHttpOperation)) {
      this.trackSchemaUsage(schema, { usage: [SchemaContext.JsonMergePatch] });
    }
    if (op.convenienceApi && sdkHttpOperationIsMultipart(sdkHttpOperation)) {
      this.trackSchemaUsage(schema, { serializationFormats: [KnownMediaType.Multipart] });
    }

    // Implicit body parameter would have usage flag: UsageFlags.Spread, for this case we need to do body parameter flatten
    const bodyParameterFlatten = sdkType.kind === "model" && sdkType.usage & UsageFlags.Spread && !this.isArm();

    if (schema instanceof ObjectSchema && bodyParameterFlatten) {
      // flatten body parameter
      const parameters = sdkHttpOperation.parameters;
      const bodyParameter = sdkHttpOperation.bodyParam;
      // name the schema for documentation
      schema.language.default.name = pascalCase(op.language.default.name) + "Request";

      if (!parameter.language.default.name) {
        // name the parameter for documentation
        parameter.language.default.name = "request";
      }

      if (sdkHttpOperationIsJsonMergePatch(sdkHttpOperation)) {
        // skip model flatten, if "application/merge-patch+json"
        schema.language.default.name = pascalCase(op.language.default.name) + "PatchRequest";
        return;
      }

      this.trackSchemaUsage(schema, { usage: [SchemaContext.Anonymous] });

      if (op.convenienceApi && op.parameters) {
        op.convenienceApi.requests = [];
        const request = new Request({
          protocol: op.requests![0].protocol,
        });
        request.parameters = [];
        op.convenienceApi.requests.push(request);

        // header/query/path params
        for (const opParameter of parameters) {
          this.addParameterOrBodyToCodeModelRequest(opParameter, op, request, schema, parameter);
        }
        // body param
        if (bodyParameter) {
          if (bodyParameter.type.kind === "model") {
            for (const bodyParam of bodyParameter.type.properties) {
              if (bodyParam.kind === "property") {
                this.addParameterOrBodyToCodeModelRequest(bodyParam, op, request, schema, parameter);
              }
            }
          }
        }
        request.signatureParameters = request.parameters;

        if (request.signatureParameters.length > 6) {
          // create an option bag
          const name = op.language.default.name + "Options";
          const namespace = getNamespace(rawHttpOperation.operation);
          // option bag schema
          const optionBagSchema = this.codeModel.schemas.add(
            new GroupSchema(name, `Options for ${op.language.default.name} API`, {
              language: {
                default: {
                  namespace: namespace,
                },
                java: {
                  namespace: this.getJavaNamespace(namespace),
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
          if (op.convenienceApi) {
            this.trackSchemaUsage(optionBagSchema, {
              usage: [op.internalApi ? SchemaContext.Internal : SchemaContext.Public],
            });
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

  private addParameterOrBodyToCodeModelRequest(
    opParameter: SdkPathParameter | SdkHeaderParameter | SdkQueryParameter | SdkBodyModelPropertyType,
    op: CodeModelOperation,
    request: Request,
    schema: ObjectSchema,
    originalParameter: Parameter,
  ) {
    const serializedName = opParameter.serializedName;
    const existParameter = op.parameters?.find((it) => it.language.default.serializedName === serializedName);
    request.parameters = request.parameters ?? [];
    if (existParameter) {
      // parameter
      if (
        existParameter.implementation === ImplementationLocation.Method &&
        (existParameter.origin?.startsWith("modelerfour:synthesized/") ?? true) &&
        !(existParameter.schema instanceof ConstantSchema)
      ) {
        request.parameters.push(cloneOperationParameter(existParameter));
      }
    } else {
      // property from anonymous model
      const existBodyProperty = schema.properties?.find((it) => it.serializedName === serializedName);
      if (existBodyProperty && !existBodyProperty.readOnly && !(existBodyProperty.schema instanceof ConstantSchema)) {
        request.parameters.push(
          new VirtualParameter(
            existBodyProperty.language.default.name,
            existBodyProperty.language.default.description,
            existBodyProperty.schema,
            {
              originalParameter: originalParameter,
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
  private findResponseBody(bodyType: Type): Type {
    // find a type that possibly without http metadata like @statusCode
    return this.getEffectiveSchemaType(bodyType);
  }

  private processResponseFromSdkType(
    op: CodeModelOperation,
    statusCode: number | HttpStatusCodeRange | "*",
    sdkResponse: SdkHttpResponse,
    longRunning: boolean,
    isErrorResponse: boolean,
  ) {
    // TODO: what to do if more than 1 response?
    // It happens when the response type is Union, on one status code.
    // let response: Response;
    let headers: Array<HttpHeader> | undefined = undefined;

    // headers
    headers = [];
    if (sdkResponse.headers) {
      for (const header of sdkResponse.headers) {
        const schema = this.processSchemaFromSdkType(header.type, header.serializedName);
        headers.push(
          new HttpHeader(header.serializedName, schema, {
            language: {
              default: {
                name: header.serializedName,
                description: header.description ?? header.details,
              },
            },
          }),
        );
      }
    }

    // let responseBody: SdkHttpResponse | undefined = undefined;
    const bodyType: SdkType | undefined = sdkResponse.type;
    let trackConvenienceApi: boolean = Boolean(op.convenienceApi);

    const unknownResponseBody =
      sdkResponse.contentTypes && sdkResponse.contentTypes.length > 0 && !isKnownContentType(sdkResponse.contentTypes);

    let response: Response;
    if (unknownResponseBody && bodyType && bodyType.kind === "bytes") {
      // binary
      response = new BinaryResponse({
        protocol: {
          http: {
            statusCodes: this.getStatusCodes(statusCode),
            headers: headers,
            mediaTypes: sdkResponse.contentTypes,
            knownMediaType: KnownMediaType.Binary,
          },
        },
        language: {
          default: {
            name: op.language.default.name + "Response",
            description: sdkResponse.description,
          },
        },
      });
    } else if (bodyType) {
      // schema (usually JSON)
      let schema: Schema | undefined = undefined;
      if (longRunning) {
        // LRO uses the LroMetadata for poll/final result, not the response of activation request
        trackConvenienceApi = false;
      }
      if (!schema) {
        schema = this.processSchemaFromSdkType(bodyType, op.language.default.name + "Response");
      }
      response = new SchemaResponse(schema, {
        protocol: {
          http: {
            statusCodes: this.getStatusCodes(statusCode),
            headers: headers,
            mediaTypes: sdkResponse.contentTypes,
          },
        },
        language: {
          default: {
            name: op.language.default.name + "Response",
            description: sdkResponse.description,
          },
        },
      });
    } else {
      // not binary nor schema, usually NoContent
      response = new Response({
        protocol: {
          http: {
            statusCodes: this.getStatusCodes(statusCode),
            headers: headers,
          },
        },
        language: {
          default: {
            name: op.language.default.name + "Response",
            description: sdkResponse.description,
          },
        },
      });
    }

    if (isErrorResponse) {
      op.addException(response);

      if (response instanceof SchemaResponse) {
        this.trackSchemaUsage(response.schema, { usage: [SchemaContext.Exception] });
      }
    } else {
      op.addResponse(response);

      if (response instanceof SchemaResponse) {
        this.trackSchemaUsage(response.schema, { usage: [SchemaContext.Output] });

        if (trackConvenienceApi) {
          this.trackSchemaUsage(response.schema, {
            usage: [op.internalApi ? SchemaContext.Internal : SchemaContext.Public],
          });
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

  private processSchemaFromSdkType(type: SdkType, nameHint: string): Schema {
    return this.schemaCache.process(type, nameHint) || fail("Unable to process schema.");
  }

  private processSchemaFromSdkTypeImpl(type: SdkType, nameHint: string): Schema {
    if (isSdkBuiltInKind(type.kind)) {
      return this.processBuiltInFromSdkType(type as SdkBuiltInType, nameHint);
    } else {
      switch (type.kind) {
        case "enum":
          return this.processChoiceSchemaFromSdkType(type, type.name);

        case "enumvalue":
          return this.processConstantSchemaFromEnumValueFromSdkType(type, nameHint);

        case "union":
          return this.processUnionSchemaFromSdkType(type, type.name);

        case "model":
          return this.processObjectSchemaFromSdkType(type, type.name);

        case "dict":
          return this.processDictionarySchemaFromSdkType(type, nameHint);

        case "array":
          return this.processArraySchemaFromSdkType(type, nameHint);

        case "duration":
          return this.processDurationSchemaFromSdkType(type, nameHint, getDurationFormatFromSdkType(type));

        case "constant":
          return this.processConstantSchemaFromSdkType(type, nameHint);

        case "utcDateTime":
        case "offsetDateTime":
          if (type.encode === "unixTimestamp") {
            return this.processUnixTimeSchemaFromSdkType(type, nameHint);
          } else {
            return this.processDateTimeSchemaFromSdkType(type, nameHint, type.encode === "rfc7231");
          }
      }
    }
    throw new Error(`Unrecognized type: '${type.kind}'.`);
  }

  private processBuiltInFromSdkType(type: SdkBuiltInType, nameHint: string): Schema {
    nameHint = nameHint || type.kind;

    if (isSdkIntKind(type.kind)) {
      const integerSize = type.kind === "safeint" || type.kind.includes("int64") ? 64 : 32;
      return this.processIntegerSchemaFromSdkType(type, nameHint, integerSize);
    } else {
      switch (type.kind) {
        case "any":
          return this.processAnySchemaFromSdkType();

        case "string":
          return this.processStringSchemaFromSdkType(type, nameHint);

        case "float":
        case "float32":
        case "float64":
          return this.processNumberSchemaFromSdkType(type, nameHint);

        case "decimal":
        case "decimal128":
          return this.processDecimalSchemaFromSdkType(type, nameHint);

        case "bytes":
          return this.processByteArraySchemaFromSdkType(type, nameHint);

        case "boolean":
          return this.processBooleanSchemaFromSdkType(type, nameHint);

        case "plainTime":
          return this.processTimeSchemaFromSdkType(type, nameHint);

        case "plainDate":
          return this.processDateSchemaFromSdkType(type, nameHint);

        case "url":
          return this.processUrlSchemaFromSdkType(type, nameHint);
      }
    }
  }

  private processAnySchemaFromSdkType(): AnySchema {
    return this.anySchema;
  }

  private processStringSchemaFromSdkType(type: SdkBuiltInType, name: string): StringSchema {
    return this.codeModel.schemas.add(
      new StringSchema(name, type.details ?? "", {
        summary: type.description,
      }),
    );
  }

  private processByteArraySchemaFromSdkType(type: SdkBuiltInType, name: string): ByteArraySchema {
    const base64Encoded: boolean = type.encode === "base64url";
    return this.codeModel.schemas.add(
      new ByteArraySchema(name, type.details ?? "", {
        summary: type.description,
        format: base64Encoded ? "base64url" : "byte",
      }),
    );
  }

  private processIntegerSchemaFromSdkType(type: SdkBuiltInType, name: string, precision: number): NumberSchema {
    return this.codeModel.schemas.add(
      new NumberSchema(name, type.details ?? "", SchemaType.Integer, precision, {
        summary: type.description,
      }),
    );
  }

  private processNumberSchemaFromSdkType(type: SdkBuiltInType, name: string): NumberSchema {
    return this.codeModel.schemas.add(
      new NumberSchema(name, type.details ?? "", SchemaType.Number, 64, {
        summary: type.description,
      }),
    );
  }

  private processDecimalSchemaFromSdkType(type: SdkBuiltInType, name: string): NumberSchema {
    // "Infinity" maps to "BigDecimal" in Java
    return this.codeModel.schemas.add(
      new NumberSchema(name, type.details ?? "", SchemaType.Number, Infinity, {
        summary: type.description,
      }),
    );
  }

  private processBooleanSchemaFromSdkType(type: SdkBuiltInType, name: string): BooleanSchema {
    return this.codeModel.schemas.add(
      new BooleanSchema(name, type.details ?? "", {
        summary: type.description,
      }),
    );
  }

  private processArraySchemaFromSdkType(type: SdkArrayType, name: string): ArraySchema {
    let nullableItems = false;
    let elementType = type.valueType;
    if (elementType.kind === "nullable") {
      nullableItems = true;
      elementType = elementType.type;
    }

    const elementSchema = this.processSchemaFromSdkType(elementType, name);
    return this.codeModel.schemas.add(
      new ArraySchema(name, type.details ?? "", elementSchema, {
        summary: type.description,
        nullableItems: nullableItems,
      }),
    );
  }

  private processDictionarySchemaFromSdkType(type: SdkDictionaryType, name: string): DictionarySchema {
    const dictSchema = new DictionarySchema<any>(name, type.details ?? "", null, {
      summary: type.description,
    });

    // cache this now before we accidentally recurse on this type.
    if (!this.schemaCache.has(type)) {
      this.schemaCache.set(type, dictSchema);
    }

    let nullableItems = false;
    let elementType = type.valueType;
    if (elementType.kind === "nullable") {
      nullableItems = true;
      elementType = elementType.type;
    }
    const elementSchema = this.processSchemaFromSdkType(elementType, name);
    dictSchema.elementType = elementSchema;

    dictSchema.nullableItems = nullableItems;

    return this.codeModel.schemas.add(dictSchema);
  }

  private processChoiceSchemaFromSdkType(
    type: SdkEnumType,
    name: string,
  ): ChoiceSchema | SealedChoiceSchema | ConstantSchema {
    const rawEnumType = type.__raw;
    const namespace = getNamespace(rawEnumType);
    const valueType = this.processSchemaFromSdkType(type.valueType, type.valueType.kind);

    const choices: ChoiceValue[] = [];
    type.values.forEach((it: SdkEnumValueType) =>
      choices.push(new ChoiceValue(it.name, it.description ?? "", it.value ?? it.name)),
    );

    const schemaType = type.isFixed ? SealedChoiceSchema : ChoiceSchema;

    const schema = new schemaType(type.name ?? name, type.details ?? "", {
      summary: type.description,
      choiceType: valueType as any,
      choices: choices,
      language: {
        default: {
          namespace: namespace,
        },
        java: {
          namespace: this.getJavaNamespace(namespace),
        },
      },
    });
    schema.crossLanguageDefinitionId = type.crossLanguageDefinitionId;
    return this.codeModel.schemas.add(schema);
  }

  private processConstantSchemaFromSdkType(type: SdkConstantType, name: string): ConstantSchema {
    const valueType = this.processSchemaFromSdkType(type.valueType, type.valueType.kind);

    return this.codeModel.schemas.add(
      new ConstantSchema(type.name ?? name, type.details ?? "", {
        summary: type.description,
        valueType: valueType,
        value: new ConstantValue(type.value),
      }),
    );
  }

  private processConstantSchemaFromEnumValueFromSdkType(type: SdkEnumValueType, name: string): ConstantSchema {
    const valueType = this.processSchemaFromSdkType(type.enumType, type.enumType.name);

    return this.codeModel.schemas.add(
      new ConstantSchema(type.name ?? name, type.details ?? "", {
        summary: type.description,
        valueType: valueType,
        value: new ConstantValue(type.value ?? type.name),
      }),
    );
  }

  private processUnixTimeSchemaFromSdkType(type: SdkDateTimeType, name: string): UnixTimeSchema {
    return this.codeModel.schemas.add(
      new UnixTimeSchema(name, type.details ?? "", {
        summary: type.description,
      }),
    );
  }

  private processDateTimeSchemaFromSdkType(type: SdkDateTimeType, name: string, rfc1123: boolean): DateTimeSchema {
    return this.codeModel.schemas.add(
      new DateTimeSchema(name, type.details ?? "", {
        summary: type.description,
        format: rfc1123 ? "date-time-rfc1123" : "date-time",
      }),
    );
  }

  private processDateSchemaFromSdkType(type: SdkBuiltInType, name: string): DateSchema {
    return this.codeModel.schemas.add(
      new DateSchema(name, type.details ?? "", {
        summary: type.description,
      }),
    );
  }

  private processTimeSchemaFromSdkType(type: SdkBuiltInType, name: string): TimeSchema {
    return this.codeModel.schemas.add(
      new TimeSchema(name, type.details ?? "", {
        summary: type.description,
      }),
    );
  }

  private processDurationSchemaFromSdkType(
    type: SdkDurationType,
    name: string,
    format: DurationSchema["format"] = "duration-rfc3339",
  ): DurationSchema {
    return this.codeModel.schemas.add(
      new DurationSchema(name, type.details ?? "", {
        summary: type.description,
        format: format,
      }),
    );
  }

  private processUrlSchemaFromSdkType(type: SdkBuiltInType, name: string): UriSchema {
    return this.codeModel.schemas.add(
      new UriSchema(name, type.details ?? "", {
        summary: type.description,
      }),
    );
  }

  private processObjectSchemaFromSdkType(type: SdkModelType, name: string): ObjectSchema {
    const rawModelType = type.__raw;
    const namespace = getNamespace(rawModelType);
    const objectSchema = new ObjectSchema(name, type.details ?? "", {
      summary: type.description,
      language: {
        default: {
          namespace: namespace,
        },
        java: {
          namespace: this.getJavaNamespace(namespace),
        },
      },
    });
    (objectSchema as CrossLanguageDefinition).crossLanguageDefinitionId = type.crossLanguageDefinitionId;
    this.codeModel.schemas.add(objectSchema);

    // cache this now before we accidentally recurse on this type.
    if (!this.schemaCache.has(type)) {
      this.schemaCache.set(type, objectSchema);
    }

    // discriminator
    if (type.discriminatedSubtypes && type.discriminatorProperty) {
      objectSchema.discriminator = new Discriminator(this.processModelPropertyFromSdkType(type.discriminatorProperty));
      for (const discriminatorValue in type.discriminatedSubtypes) {
        const subType = type.discriminatedSubtypes[discriminatorValue];
        this.processSchemaFromSdkType(subType, subType.name);
      }
    }

    // type is a subtype
    if (type.baseModel) {
      const parentSchema = this.processSchemaFromSdkType(type.baseModel, type.baseModel.name);
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
      objectSchema.discriminatorValue = type.discriminatorValue;
    }
    if (type.additionalProperties) {
      // model has additional property
      const sdkDictType: SdkDictionaryType = {
        kind: "dict",
        keyType: {
          kind: "string",
          encode: "string",
          decorators: [],
          name: "string",
          crossLanguageDefinitionId: type.crossLanguageDefinitionId,
        },
        description: type.description,
        valueType: type.additionalProperties,
        decorators: [],
      };
      const parentSchema = this.processSchemaFromSdkType(sdkDictType, "Record");
      objectSchema.parents = objectSchema.parents ?? new Relations();
      objectSchema.parents.immediate.push(parentSchema);
      pushDistinct(objectSchema.parents.all, parentSchema);
      objectSchema.discriminatorValue = type.discriminatorValue;
    }

    // properties
    for (const prop of type.properties) {
      if (prop.kind === "property" && !prop.discriminator) {
        objectSchema.addProperty(this.processModelPropertyFromSdkType(prop));
      }
    }

    return objectSchema;
  }

  private getEffectiveSchemaType(type: Type): Type {
    const program = this.program;
    function isSchemaProperty(property: ModelProperty) {
      return isPayloadProperty(program, property);
    }

    if (type.kind === "Model") {
      const effective = getEffectiveModelType(program, type, isSchemaProperty);
      if (this.isArm() && getNamespace(effective as Model)?.startsWith("Azure.ResourceManager")) {
        // Catalog is TrackedResource<CatalogProperties>
        return type;
      } else if (effective.name) {
        return effective;
      }
    }
    return type;
  }

  private processModelPropertyFromSdkType(prop: SdkModelPropertyType): Property {
    let nullable = false;
    let nonNullType = prop.type;
    if (nonNullType.kind === "nullable") {
      nullable = true;
      nonNullType = nonNullType.type;
    }
    let schema;

    let extensions: Record<string, any> | undefined = undefined;
    if (this.isSecret(prop)) {
      extensions = extensions ?? {};
      extensions["x-ms-secret"] = true;
      // if the property does not return in response, it had to be nullable
      nullable = true;
    }
    if (prop.kind === "property" && prop.flatten) {
      extensions = extensions ?? {};
      extensions["x-ms-client-flatten"] = true;
    }
    const mutability = this.getMutability(prop);
    if (mutability) {
      extensions = extensions ?? {};
      extensions["x-ms-mutability"] = mutability;
    }

    if (prop.kind === "property" && prop.multipartOptions) {
      // TODO: handle MultipartOptions.isMulti
      if (prop.multipartOptions.isFilePart) {
        schema = this.processMultipartFormDataFilePropertySchemaFromSdkType(prop);
      } else {
        schema = this.processSchemaFromSdkType(nonNullType, "");
      }
    } else {
      schema = this.processSchemaFromSdkType(nonNullType, "");
    }

    return new Property(prop.name, prop.details ?? "", schema, {
      summary: prop.description,
      required: !prop.optional,
      nullable: nullable,
      readOnly: this.isReadOnly(prop),
      serializedName: prop.kind === "property" ? prop.serializedName : undefined,
      extensions: extensions,
    });
  }

  private processUnionSchemaFromSdkType(type: SdkUnionType, name: string): Schema {
    if (!(type.__raw && type.__raw.kind === "Union")) {
      throw new Error(`Invalid type for union: '${type.kind}'.`);
    }
    const rawUnionType: Union = type.__raw as Union;
    const namespace = getNamespace(rawUnionType);
    const baseName = type.name ?? pascalCase(name) + "Model";
    this.logWarning(
      `Convert TypeSpec Union '${getUnionDescription(rawUnionType, this.typeNameOptions)}' to Class '${baseName}'`,
    );
    const unionSchema = new OrSchema(baseName + "Base", type.details ?? "", {
      summary: type.description,
    });
    unionSchema.anyOf = [];
    type.values.forEach((it) => {
      const variantName = this.getUnionVariantName(it.__raw, { depth: 0 });
      const modelName = variantName + baseName;
      const propertyName = "value";

      // these ObjectSchema is not added to codeModel.schemas
      const objectSchema = new ObjectSchema(modelName, it.details ?? "", {
        summary: it.description,
        language: {
          default: {
            namespace: namespace,
          },
          java: {
            namespace: this.getJavaNamespace(namespace),
          },
        },
      });

      const variantSchema = this.processSchemaFromSdkType(it, variantName);
      objectSchema.addProperty(
        new Property(propertyName, type.details ?? "", variantSchema, {
          summary: type.description,
          required: true,
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

  private processBinarySchemaFromSdkType(type: SdkBuiltInType): BinarySchema {
    return this.codeModel.schemas.add(
      new BinarySchema(type.description ?? "", {
        summary: type.details,
      }),
    );
  }

  private getUnionVariantName(type: Type | undefined, option: any): string {
    if (type === undefined) {
      throw new Error("type is undefined.");
    }
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
      case "Union":
        return type.name ?? "Union";
      default:
        throw new Error(`Unrecognized type for union variable: '${type.kind}'.`);
    }
  }

  private processMultipartFormDataFilePropertySchemaFromSdkType(property: SdkBodyModelPropertyType): Schema {
    const processSchemaFunc = (type: SdkType) => this.processSchemaFromSdkType(type, "");
    if (property.type.kind === "bytes" || property.type.kind === "model") {
      const namespace =
        property.type.kind === "model" ? (getNamespace(property.type.__raw) ?? this.namespace) : this.namespace;
      return getFileDetailsSchema(
        property,
        getNamespace(property.type.__raw) ?? this.namespace,
        namespace,
        this.codeModel.schemas,
        this.binarySchema,
        this.stringSchema,
        processSchemaFunc,
      );
    } else if (
      property.type.kind === "array" &&
      (property.type.valueType.kind === "bytes" || property.type.valueType.kind === "model")
    ) {
      const namespace =
        property.type.valueType.kind === "model"
          ? (getNamespace(property.type.valueType.__raw) ?? this.namespace)
          : this.namespace;
      return new ArraySchema(
        property.name,
        property.details ?? "",
        getFileDetailsSchema(
          property,
          namespace,
          this.getJavaNamespace(namespace),
          this.codeModel.schemas,
          this.binarySchema,
          this.stringSchema,
          processSchemaFunc,
        ),
        {
          summary: property.description,
        },
      );
    } else {
      throw new Error(`Invalid type for multipart form data: '${property.type.kind}'.`);
    }
  }

  private getDoc(target: Type | undefined): string {
    return target ? getDoc(this.program, target) || "" : "";
  }

  private getSummary(target: Type | undefined): string | undefined {
    return target ? getSummary(this.program, target) : undefined;
  }

  private getName(target: ModelProperty | Operation, nameHint: string | undefined = undefined): string {
    // TODO: once getLibraryName API in typespec-client-generator-core can get projected name from language and client, as well as can handle template case, use getLibraryName API
    const emitterClientName = getClientNameOverride(this.sdkContext, target);
    if (emitterClientName && typeof emitterClientName === "string") {
      return emitterClientName;
    }
    // TODO: deprecate getProjectedName
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

    if (typeof target.name === "symbol") {
      return "";
    }
    return target.name || "";
  }

  private getSerializedName(target: ModelProperty): string {
    if (isHeader(this.program, target)) {
      return getHeaderFieldName(this.program, target);
    } else if (isQueryParam(this.program, target)) {
      return getQueryParamName(this.program, target);
    } else if (isPathParam(this.program, target)) {
      return getPathParamName(this.program, target);
    } else {
      // TODO: currently this is only for JSON
      return getWireName(this.sdkContext, target);
    }
  }

  private isReadOnly(target: SdkModelPropertyType): boolean {
    const segment = target.__raw ? getSegment(this.program, target.__raw) !== undefined : false;
    if (segment) {
      return true;
    } else {
      const visibility = target.__raw ? getVisibility(this.program, target.__raw) : undefined;
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

  private isSecret(target: SdkModelPropertyType): boolean {
    if (target.kind === "property" && target.visibility) {
      return !target.visibility.includes(Visibility.Read);
    } else {
      return false;
    }
  }

  private getMutability(target: SdkModelPropertyType): string[] | undefined {
    if (target.kind === "property" && target.visibility) {
      const mutability: string[] = [];
      if (target.visibility.includes(Visibility.Create)) {
        mutability.push("create");
      }
      if (target.visibility.includes(Visibility.Update)) {
        mutability.push("update");
      }
      if (target.visibility.includes(Visibility.Read)) {
        mutability.push("read");
      }
      if (mutability.length === 3) {
        // if all 3 (supported) mutability values are present, there is no need to set the x-ms-mutability
        return undefined;
      } else {
        return mutability;
      }
    } else {
      return undefined;
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

  private getConvenienceApiNameFromServiceMethod(sdkMethod: SdkServiceMethod<SdkHttpOperation>): string | undefined {
    // check @convenienceMethod
    if (sdkMethod.__raw && shouldGenerateConvenient(this.sdkContext, sdkMethod.__raw)) {
      return sdkMethod.name;
    } else {
      return undefined;
    }
  }

  private getJavaNamespace(namespace: string | undefined): string | undefined {
    const tspNamespace = this.namespace;
    const baseJavaNamespace = this.emitterContext.options.namespace;
    if (!namespace) {
      return undefined;
    } else if (baseJavaNamespace && (namespace === tspNamespace || namespace.startsWith(tspNamespace + "."))) {
      return baseJavaNamespace + namespace.slice(tspNamespace.length).toLowerCase();
    } else {
      return "com." + namespace.toLowerCase();
    }
  }

  private logWarning(msg: string) {
    if (this.loggingEnabled) {
      logWarning(this.program, msg);
    }
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

  private _binarySchema?: BinarySchema;
  get binarySchema(): BinarySchema {
    return this._binarySchema || (this._binarySchema = this.codeModel.schemas.add(new BinarySchema("simple binary")));
  }

  private _pollResultSchema?: ObjectSchema;
  get pollResultSchema(): ObjectSchema {
    return (
      this._pollResultSchema ??
      (this._pollResultSchema = createPollOperationDetailsSchema(this.codeModel.schemas, this.stringSchema))
    );
  }

  private createApiVersionParameter(
    serializedName: string,
    parameterLocation: ParameterLocation,
    value = "",
  ): Parameter {
    return new Parameter(
      serializedName,
      "Version parameter",
      this.codeModel.schemas.add(
        new ConstantSchema(serializedName, "API Version", {
          valueType: this.stringSchema,
          value: new ConstantValue(value),
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

  private isSubscriptionId(param: SdkPathParameter): boolean {
    return "subscriptionId".toLocaleLowerCase() === param.name.toLocaleLowerCase();
  }

  private subscriptionIdParameter(parameter: SdkPathParameter): Parameter {
    if (!this._subscriptionParameter) {
      const description = parameter.description;
      this._subscriptionParameter = new Parameter(
        "subscriptionId",
        description ? description : "The ID of the target subscription.",
        this.stringSchema,
        {
          implementation: ImplementationLocation.Client,
          required: true,
          protocol: {
            http: new HttpParameter(ParameterLocation.Path),
          },
          language: {
            default: {
              serializedName: "subscriptionId",
            },
          },
        },
      );
    }
    return this._subscriptionParameter;
  }

  private _subscriptionParameter?: Parameter;

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

            if (schema.discriminator) {
              // propagate access/usage to immediate children, if the schema is a discriminated model
              // if the schema is not a discriminated model, its children likely not valid for the mode/API
              // TODO: it does not handle the case that concrete model (kind: "type1") for the discriminated model have depth larger than 1 (e.g. kind: "type1" | "type2" in middle)
              schema.children?.immediate?.forEach((c) => innerApplySchemaUsage(c, schemaUsage));
            }

            if (schema.discriminator?.property?.schema) {
              innerApplySchemaUsage(schema.discriminator?.property?.schema, schemaUsage);
            }
          }
        }
      } else if (schema instanceof DictionarySchema) {
        innerApplySchemaUsage(schema.elementType, schemaUsage);
      } else if (schema instanceof ArraySchema) {
        innerApplySchemaUsage(schema.elementType, schemaUsage);
      } else if (schema instanceof OrSchema) {
        schema.anyOf?.forEach((it) => innerApplySchemaUsage(it, schemaUsage));
      } else if (schema instanceof ConstantSchema) {
        innerApplySchemaUsage(schema.valueType, schemaUsage);
      }
    };

    // Exclude context that not to be propagated
    const schemaUsage = {
      usage: (schema as SchemaUsage).usage?.filter(
        (it) => it !== SchemaContext.Paged && it !== SchemaContext.Anonymous,
      ),
      serializationFormats: (schema as SchemaUsage).serializationFormats?.filter(
        (it) => it !== KnownMediaType.Multipart,
      ),
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
      if (schemaUsage.serializationFormats) {
        pushDistinct(
          (schema.serializationFormats = schema.serializationFormats || []),
          ...schemaUsage.serializationFormats,
        );
      }
    } else if (schema instanceof DictionarySchema) {
      this.trackSchemaUsage(schema.elementType, schemaUsage);
    } else if (schema instanceof ArraySchema) {
      this.trackSchemaUsage(schema.elementType, schemaUsage);
    }
  }

  private isArm(): boolean {
    return Boolean(this.codeModel.arm);
  }

  // private getLocationFromSdkParameter(param: SdkModelPropertyType): string {
  //   if (Object.values(ParameterLocation).includes(param.kind)) {
  //     return param.kind;
  //   } else {
  //     return "none";
  //   }
  // }
}
