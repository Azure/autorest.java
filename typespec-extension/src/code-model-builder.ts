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
  getEncode,
  getOverloadedOperation,
  EnumMember,
  walkPropertiesInherited,
  isVoidType,
  isErrorModel,
  UsageFlags,
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
  Authentication,
  HttpStatusCodesEntry,
  getHttpOperation,
  getQueryParamOptions,
  getHeaderFieldOptions,
  isPathParam,
  HttpOperationBody,
  Visibility,
} from "@typespec/http";
import { Availability, Version, getAddedOnVersions, getAvailabilityMap, getVersion } from "@typespec/versioning";
import {
  isPollingLocation,
  getPagedResult,
  isFixed,
  getLroMetadata,
  getUnionAsEnum,
  UnionEnum,
} from "@azure-tools/typespec-azure-core";
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
  getClientNameOverride,
  shouldFlattenProperty,
  getWireName,
  getAllModels,
  getClientType,
  SdkModelType,
  SdkEnumType,
  SdkType,
  AccessFlags,
  SdkEnumValueType,
  SdkUnionType,
  SdkModelPropertyType,
  SdkBodyModelPropertyType,
  SdkBuiltInKinds,
  SdkBuiltInType,
  SdkConstantType,
  SdkDurationType,
  SdkDatetimeType,
  isSdkBuiltInKind,
  SdkArrayType,
  SdkDictionaryType,
  getDefaultApiVersion,
  getSdkModelPropertyType,
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
import { KnownMediaType } from "@azure-tools/codegen";
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
import { createPollOperationDetailsSchema, getFileDetailsSchema } from "./external-schemas.js";
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
  getAccess,
  getUsage,
  getUnionDescription,
  modelIs,
  getModelNameForProperty,
  isAllValueInteger,
  getDurationFormatFromSdkType,
  isSdkIntKind,
  isSdkFloatKind,
} from "./type-utils.js";
import {
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
import { isArmCommonType } from "./type-utils.js";
import pkg, { get } from "lodash";
const { isEqual } = pkg;

export class CodeModelBuilder {
  private program: Program;
  private typeNameOptions: TypeNameOptions;
  private namespace: string;
  private sdkContext: SdkContext;
  private options: EmitterOptions;
  private codeModel: CodeModel;
  private loggingEnabled: boolean = false;

  readonly schemaCache = new ProcessingCache((type: SdkType, name: string) => this.processSchemaFromSdkTypeImpl(type, name));
  readonly typeUnionRefCache = new Map<Type, Union | null | undefined>(); // Union means it ref a Union type, null means it does not ref any Union, undefined means type visited but not completed

  private operationExamples: Map<Operation, any> = new Map<Operation, any>();
  // current apiVersion name to generate code
  private apiVersion: Version | undefined;

  public constructor(program1: Program, context: EmitContext<EmitterOptions>) {
    this.options = context.options;
    this.program = program1;
    if (this.options["dev-options"]?.loglevel) {
      this.loggingEnabled = true;
    }

    if (this.options["skip-special-headers"]) {
      this.options["skip-special-headers"].forEach((it) => SPECIAL_HEADER_NAMES.add(it.toLowerCase()));
    }

    this.sdkContext = createSdkContext(context, "@azure-tools/typespec-java");
    const service = listServices(this.program)[0];
    const serviceNamespace = service.type;
    if (serviceNamespace === undefined) {
      throw Error("Cannot emit yaml for a namespace that doesn't exist.");
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

    if (this.sdkContext.arm) {
      // ARM
      this.codeModel.arm = true;
      this.options["group-etag-headers"] = false;
    }

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
    if (server && !this.isArmSynthesizedServer(server)) {
      server.parameters.forEach((it) => {
        let parameter;

        if (isApiVersion(this.sdkContext, it)) {
          parameter = this.createApiVersionParameter(it.name, ParameterLocation.Uri);
        } else {
          const sdkType = getClientType(this.sdkContext, it.type);
          const schema = this.processSchemaFromSdkType(sdkType, it.name);
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

  private processModels(clients: SdkClient[]) {
    const processedModels: Set<Type> = new Set();
    const processedSdkModels: Set<SdkModelType | SdkEnumType> = new Set();
    for (const client of clients) {
      const models: (Model | Enum | Union)[] = Array.from(client.service.models.values());
      Array.from(client.service.enums.values()).forEach((it) => models.push(it));
      Array.from(client.service.unions.values()).forEach((it) => models.push(it));

      // lambda to mark model as public
      const modelAsPublic = (model: SdkModelType | SdkEnumType) => {
        // check it does not contain Union
        // const union = unionReferredByType(this.program, model, this.typeUnionRefCache);
        // if (union) {
        //   const errorMsg = `Model '${getTypeName(
        //     model,
        //     this.typeNameOptions,
        //   )}' cannot be set as access=public, as it refers Union '${getUnionDescription(union, this.typeNameOptions)}'`;
        //   throw new Error(errorMsg);
        // }

        // change to sdk type
        // const sdkType = getClientType(this.sdkContext, model);
        const schema = this.processSchemaFromSdkType(model, "");

        this.trackSchemaUsage(schema, {
          usage: [SchemaContext.Public],
        });
      };

      const sdkModels: (SdkModelType | SdkEnumType)[] = getAllModels(this.sdkContext);
      

      // process sdk models
      for (const model of sdkModels) {
        if (!processedSdkModels.has(model)) {
          const access = getAccess(model.__raw as Model | Enum | Union);
          // const access = model.access;
          if (access === "public") {
            modelAsPublic(model);
          } else if (access === "internal") {
            const schema = this.processSchemaFromSdkType(model, model.name);

            this.trackSchemaUsage(schema, {
              usage: [SchemaContext.Internal],
            });
          }

          const usage = getUsage(model.__raw as Model | Enum | Union);
          if (usage) {
            const schema = this.processSchemaFromSdkType(model, "");

            this.trackSchemaUsage(schema, {
              usage: usage,
            });
          }

          processedSdkModels.add(model);
        }
      }

      // process tsp compiler models
      // for (const model of models) {
      //   if (!processedModels.has(model)) {
      //     const access = getAccess(model);
      //     if (access === "public") {
      //       modelAsPublic(model);
      //     } else if (access === "internal") {
      //       const sdkType = getClientType(this.sdkContext, model);
      //       const schema = this.processSchemaFromSdkType(sdkType, "");

      //       this.trackSchemaUsage(schema, {
      //         usage: [SchemaContext.Internal],
      //       });
      //     }

      //     const usage = getUsage(model);
      //     if (usage) {
      //       // TODO: change to sdk type
      //       const sdkType = getClientType(this.sdkContext, model);
      //       const schema = this.processSchemaFromSdkType(sdkType, "");

      //       this.trackSchemaUsage(schema, {
      //         usage: usage,
      //       });
      //     }

      //     processedModels.add(model);
      //   }
      // }
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
      // skip models under "Azure.ResourceManager"
      if (this.isArm() && schema.language.default?.namespace?.startsWith("Azure.ResourceManager")) {
        return;
      }
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

  private processClients(): SdkClient[] {
    const clients = listClients(this.sdkContext);
    // preprocess group-etag-headers
    this.options["group-etag-headers"] = this.options["group-etag-headers"] ?? true;

    for (const client of clients) {
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

        this.apiVersion = getDefaultApiVersion(this.sdkContext, client.service);
      }

      // server
      let baseUri = "{endpoint}";
      const servers = getServers(this.program, client.service);
      if (servers && servers.length === 1 && !this.isArmSynthesizedServer(servers[0])) {
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
          let operationGroupName: string;
          if (groupPath.length > 1) {
            // groupPath should be in format of "OpenAIClient.Chat.Completions"
            operationGroupName = groupPath.slice(1).join("");
          } else {
            // protection
            operationGroupName = operationGroup.type.name;
          }
          codeModelGroup = new OperationGroup(operationGroupName);
          for (const operation of operations) {
            if (!this.needToSkipProcessingOperation(operation, clientContext)) {
              codeModelGroup.addOperation(this.processOperation(operationGroupName, operation, clientContext));
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

    return clients;
  }

  /**
   * `@armProviderNamespace` currently will add a default server if not defined globally:
   * https://github.com/Azure/typespec-azure/blob/8b8d7c05f168d9305a09691c4fedcb88f4a57652/packages/typespec-azure-resource-manager/src/namespace.ts#L121-L128
   * TODO: if the synthesized server has the right hostParameter, we can use that insteadß
   *
   * @param server returned by getServers
   * @returns whether it's synthesized by `@armProviderNamespace`
   */
  private isArmSynthesizedServer(server: HttpServer): boolean {
    return this.isArm() && (!server.parameters || server.parameters.size == 0);
  }

  private needToSkipProcessingOperation(operation: Operation, clientContext: ClientContext): boolean {
    if (!this.existsAtCurrentVersion(operation)) {
      return true;
    }
    // don't generate protocol and convenience method for overloaded operations
    // issue link: https://github.com/Azure/autorest.java/issues/1958#issuecomment-1562558219 we will support generate overload methods for non-union type in future (TODO issue: https://github.com/Azure/autorest.java/issues/2160)
    if (getOverloadedOperation(this.program, operation)) {
      this.trace(`Operation '${operation.name}' is temporary skipped, as it is an overloaded operation`);
      return true;
    }
    return false;
  }

  private existsAtCurrentVersion(type: Type): boolean {
    const availabilityMap = getAvailabilityMap(this.program, type);
    // if unversioned then everything exists
    if (
      !availabilityMap ||
      !this.apiVersion ||
      this.supportsAdvancedVersioning() // if supports non-breaking versioning, then it always exists
    ) {
      return true;
    }
    const availability = availabilityMap.get(this.apiVersion?.name);
    return availability === Availability.Added || availability === Availability.Available;
  }

  /**
   * Whether we support advanced versioning in non-breaking fashion.
   */
  private supportsAdvancedVersioning(): boolean {
    return Boolean(this.options["advanced-versioning"]);
  }

  private processOperation(groupName: string, operation: Operation, clientContext: ClientContext): CodeModelOperation {
    const op = ignoreDiagnostics(getHttpOperation(this.program, operation));

    const operationGroup = this.codeModel.getOperationGroup(groupName);
    const operationName = this.getName(operation);
    const opId = groupName ? `${groupName}_${operationName}` : `${operationName}`;

    let operationExample = this.operationExamples.get(operation);
    if (!operationExample && operation.sourceOperation) {
      // if the operation is customized in client.tsp, the operation would be different from that of main.tsp
      // try the operation.sourceOperation
      operationExample = this.operationExamples.get(operation.sourceOperation);
    }

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
      if (operationIsMultipart(op)) {
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
      } else if (operationIsJsonMergePatch(op) && this.options["stream-style-serialization"] === false) {
        // do not generate convenient method for json merge patch operation if stream-style-serialization is not enabled
        generateConvenienceApi = false;
        apiComment = `Convenience API is not generated, as operation '${op.operation.name}' is 'application/merge-patch+json' and stream-style-serialization is not enabled`;
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
    op.parameters.parameters
      .filter((param) => this.existsAtCurrentVersion(param.param))
      .map((it) => this.processParameter(codeModelOperation, it, clientContext));
    // "accept" header
    this.addAcceptHeaderParameter(codeModelOperation, op.responses);
    // body
    if (op.parameters.body) {
      if (op.parameters.body.parameter) {
        if (!isVoidType(op.parameters.body.parameter.type)) {
          this.processParameterBody(codeModelOperation, op, op.parameters.body.parameter);
        }
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
    if (this.options["group-etag-headers"]) {
      this.processEtagHeaderParameters(codeModelOperation, op);
    }

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
          const sdkType = getClientType(this.sdkContext, pollType);
          pollingSchema = this.processSchemaFromSdkType(sdkType, "pollResult");
        }
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
        // change to sdk type
        const sdkType = getClientType(this.sdkContext, finalType);
        finalSchema = this.processSchemaFromSdkType(sdkType, "finalResult");
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
  }

  private _armApiVersionParameter?: Parameter;

  private processParameter(op: CodeModelOperation, param: HttpOperationParameter, clientContext: ClientContext) {
    if (clientContext.apiVersions && isApiVersion(this.sdkContext, param)) {
      // pre-condition for "isApiVersion": the client supports ApiVersions
      if (this.isArm()) {
        // Currently we assume ARM tsp only have one client and one api-version.
        // TODO: How will service define mixed api-versions(like those in Compute RP)?
        const apiVersion = this.apiVersion?.value;
        if (!this._armApiVersionParameter) {
          this._armApiVersionParameter = this.createApiVersionParameter(
            "api-version",
            param.type === "query" ? ParameterLocation.Query : ParameterLocation.Path,
            apiVersion,
          );
          clientContext.addGlobalParameter(this._armApiVersionParameter);
        }
        op.addParameter(this._armApiVersionParameter);
      } else {
        const parameter = param.type === "query" ? this.apiVersionParameter : this.apiVersionParameterInPath;
        op.addParameter(parameter);
        clientContext.addGlobalParameter(parameter);
      }
    } else if (this.isSubscriptionId(param)) {
      const parameter = this.subscriptionIdParameter(param);
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
        // change to sdk type
        const sdkType = getClientType(this.sdkContext, param.param);
        schema = this.processSchemaFromSdkType(sdkType, param.param.name);
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

      if (this.supportsAdvancedVersioning()) {
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

    const produces = new Set<string>();
    for (const resp of responses) {
      if (resp.responses && resp.responses.length > 0) {
        for (const response of resp.responses) {
          response.body?.contentTypes.forEach((it) => produces.add(it));
        }
      }
    }
    if (produces.size === 0) {
      produces.add("application/json");
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

    const sdkType: SdkType = getClientType(this.sdkContext, body);
    
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
      schema = this.processSchemaFromSdkType(sdkType, body.name);
    }

    const isAnonymousModel = sdkType.kind === "model" && sdkType.isGeneratedName === true;
    const parameter = new Parameter(this.getName(body) , this.getDoc(body), schema, {
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
      if (schema instanceof ObjectSchema) {
        this.processMultipartFormDataSchema(schema);
      }
      this.trackSchemaUsage(schema, { serializationFormats: [KnownMediaType.Multipart] });
    }

    if (schema instanceof ObjectSchema && isAnonymousModel) {
      // anonymous model

      // name the schema for documentation
      schema.language.default.name = pascalCase(op.language.default.name) + "Request";

      if (!parameter.language.default.name) {
        // name the parameter for documentation
        parameter.language.default.name = "request";
      }

      if (operationIsJsonMergePatch(httpOperation)) {
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
            if (
              existBodyProperty &&
              !existBodyProperty.readOnly &&
              !(existBodyProperty.schema instanceof ConstantSchema)
            ) {
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
    // It happens when the response type is Union, on one status code.
    let response: Response;
    let headers: Array<HttpHeader> | undefined = undefined;
    if (resp.responses && resp.responses.length > 0) {
      // headers
      headers = [];
      for (const response of resp.responses.values()) {
        if (response.headers) {
          for (const [key, header] of Object.entries(response.headers)) {
            // TODO: change to sdk type
            const sdkType = getClientType(this.sdkContext, header);
            const schema = this.processSchemaFromSdkType(sdkType, key);
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
      }
    }

    let responseBody: HttpOperationBody | undefined = undefined;
    let bodyType: Type | undefined = undefined;
    let trackConvenienceApi = op.convenienceApi ?? false;
    if (resp.responses && resp.responses.length > 0 && resp.responses[0].body) {
      responseBody = resp.responses[0].body;
    }
    if (responseBody) {
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
              knownMediaType: KnownMediaType.Binary,
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
            const sdkType = getClientType(this.sdkContext, bodyType);
            schema = this.processSchemaFromSdkType(sdkType, op.language.default.name + "Response");
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

  // private processSchema(type: Type, nameHint: string): Schema {
  //   return this.schemaCache.process(type, nameHint) || fail("Unable to process schema.");
  // }

  private processSchemaFromSdkType(type: SdkType, nameHint: string): Schema {
    return this.schemaCache.process(type, nameHint) || fail("Unable to process schema.");
  }

  private processSchemaFromSdkTypeImpl(type: SdkType, nameHint: string): Schema {
    switch (type.kind) {
      case "any":
        return this.processAnySchemaFromSdkType(type, nameHint);

      case "enum":
        return this.processChoiceSchemaFromSdkType(type, type.name);
      
      case "enumvalue":
        return this.processConstantSchemaFromEnumValueFromSdkType(type as SdkEnumValueType, nameHint);

      case "union":
        return this.processUnionSchemaFromSdkType(type, type.name);

      case "model":
        return this.processObjectSchemaFromSdkType(type, type.name);
        
      case "dict":
        return this.processDictionarySchemaFromSdkType(type, nameHint);
        
      case "array":
        return this.processArraySchemaFromSdkType(type, nameHint);

      case "duration":
        return this.processDurationSchemaFromSdkType(type as SdkDurationType, nameHint, getDurationFormatFromSdkType(type as SdkDurationType));
      
      case "constant":
        return this.processConstantSchemaFromSdkType(type as SdkConstantType, nameHint);

      case "bytes":
      case "boolean":
      case "plainDate":
      case "plainTime":
      case "numeric":
      case "integer":
      case "safeint":
      case "int8":
      case "uint8":
      case "int16":
      case "uint16":
      case "int32":
      case "uint32":
      case "int64":
      case "uint64":
      case "float":
      case "float32":
      case "float64":
      case "decimal":
      case "decimal128":
      case "string":
      case "password":
      case "guid":
      case "url":
      case "uuid":
      case "eTag":
      case "armId":
      case "ipAddress":
      case "azureLocation":
        return this.processBuiltInFromSdkType(type, nameHint);

      case "utcDateTime":
      case "offsetDateTime":
        if ((type as SdkDatetimeType).encode === "unixTimestamp") {
          return this.processUnixTimeSchemaFromSdkType(type as SdkDatetimeType, nameHint);
        } else {
          return this.processDateTimeSchemaFromSdkType(type as SdkDatetimeType, nameHint, (type as SdkDatetimeType).encode === "rfc7231");
        }
    }
    throw new Error(`Unrecognized type: '${type.kind}'.`);
  }


  // private processSchemaImpl(type: Type, nameHint: string): Schema {
  //   const sdkType: SdkType = getClientType(this.sdkContext, type);
  //   // const schemaFromSdkType = this.processSchemaFromSdkTypeImpl(sdkType, nameHint);                                                                                                                             
  //   // if (schemaFromSdkType) {
  //   //   return schemaFromSdkType;
  //   // }
  //   switch (type.kind) {
  //     case "Intrinsic":
  //       if (isUnknownType(type)) {
  //         const sdkType: SdkType = getClientType(this.sdkContext, type) as SdkType;
  //         return this.processAnySchemaFromSdkType(sdkType, nameHint);
  //       } else {
  //         throw new Error(`Unrecognized intrinsic type: '${type.name}'.`);
  //       }

  //     case "String":
  //       return this.processConstantSchemaFromSdkType(sdkType as SdkConstantType, nameHint);

  //     case "Number":
  //       return this.processConstantSchemaFromSdkType(sdkType as SdkConstantType, nameHint);

  //     case "Boolean":
  //       return this.processConstantSchemaFromSdkType(sdkType as SdkConstantType, nameHint);

  //     case "Enum":
  //       return this.processChoiceSchemaFromSdkType(sdkType as SdkEnumType, nameHint);

  //     case "Union":
  //       if (sdkType.kind === "enum") {
  //         return this.processChoiceSchemaFromSdkType(sdkType as SdkEnumType, nameHint);
  //       }
  //       if (sdkType.kind === "model") {
  //         return this.processObjectSchemaFromSdkType(sdkType as SdkModelType, nameHint);
  //       }
  //       if (isSdkBuiltInKind(sdkType.kind)) {
  //         return this.processBuiltInFromSdkType(sdkType as SdkBuiltInType, nameHint);
  //       }
  //       return this.processUnionSchemaFromSdkType(
  //         getClientType(this.sdkContext, type) as SdkUnionType,
  //         this.getName(type, nameHint),
  //       );

  //     case "ModelProperty": {
  //       // let schema = undefined;
  //       // const sdkSchema = this.processSchemaFromSdkTypeImpl(sdkType, nameHint);
  //       // if (sdkSchema) {
  //       //   return sdkSchema;
  //       // } else {
  //       //   const schemaNameHint =
  //       //     type.type.kind === "Scalar" && this.program.checker.isStdType(type.type)
  //       //       ? nameHint // std scalar won't need a nameHint
  //       //       : pascalCase(getModelNameForProperty(type)) + pascalCase(nameHint);
  //       //   schema = this.processSchema(type.type, schemaNameHint);
  //       //   return this.applyModelPropertyDecorators(type, nameHint, schema);
  //       // }
  //       let schema = undefined;
  //       if (sdkType.kind === "enum") {
  //         return this.processChoiceSchemaFromSdkType(sdkType as SdkEnumType, (sdkType as SdkEnumType).name);
  //       } else if (sdkType.kind === "constant") {
  //         return this.processConstantSchemaFromSdkType(sdkType as SdkConstantType, nameHint);
  //       } else if (sdkType.kind === "duration") {
  //         return this.processDurationSchemaFromSdkType(sdkType as SdkDurationType, nameHint, getDurationFormatFromSdkType(sdkType as SdkDurationType));
  //       } else if (sdkType.kind === "model") {
  //         // TODO: get model property
  //         // const sdkModelProperty = (sdkType as SdkModelType).properties.filter(p => p.name === nameHint);
  //         return this.processObjectSchemaFromSdkType(sdkType as SdkModelType, nameHint);
  //       } else {
  //         const schemaNameHint =
  //           type.type.kind === "Scalar" && this.program.checker.isStdType(type.type)
  //             ? nameHint // std scalar won't need a nameHint
  //             : pascalCase(getModelNameForProperty(type)) + pascalCase(nameHint);
  //         schema = this.processSchema(type.type, schemaNameHint);
  //       }
  //       return this.applyModelPropertyDecorators(type, nameHint, schema);
  //     }

  //     case "Scalar":
  //       switch (sdkType.kind) {
  //         case "utcDateTime":
  //         case "offsetDateTime":
  //           if ((sdkType as SdkDatetimeType).encode === "unixTimestamp") {
  //             return this.processUnixTimeSchemaFromSdkType(sdkType as SdkDatetimeType, nameHint);
  //           } else {
  //             return this.processDateTimeSchemaFromSdkType(sdkType as SdkDatetimeType, nameHint, (sdkType as SdkDatetimeType).encode === "rfc7231");
  //           }
  //         case "duration":
  //           return this.processDurationSchemaFromSdkType(sdkType as SdkDurationType, nameHint, getDurationFormatFromSdkType(sdkType as SdkDurationType));
  //         }
  //       return this.processBuiltInFromSdkType(sdkType as SdkBuiltInType, nameHint);

  //     case "Model":
  //       if (sdkType.kind === "array") {
  //         return this.processArraySchemaFromSdkType(sdkType as SdkArrayType, nameHint);
  //       } else if (sdkType.kind === "dict") {
  //         // "pure" Record that does not have properties in it
  //         return this.processDictionarySchemaFromSdkType(sdkType as SdkDictionaryType, nameHint);
  //       } else {
  //         return this.processObjectSchemaFromSdkType(sdkType as SdkModelType, (sdkType as SdkModelType).name);
  //       }

  //     case "EnumMember":
  //       // e.g. "type: TypeEnum.EnumValue1"
        // return this.processConstantSchemaForEnumMember(type, this.getName(type));

  //     case "UnionVariant":
  //       // e.g. "type: Union.Variant1"
  //       if (type.type.kind === "String" || type.type.kind === "Number" || type.type.kind === "Boolean") {
  //         return this.processConstantSchemaForUnionVariant(type, this.getName(type));
  //       } else {
  //         throw new Error(`Unsupported type reference to UnionVariant.`);
  //       }
  //   }

  //   throw new Error(`Unrecognized type: '${type.kind}'.`);
  // }

  private processBuiltInFromSdkType(type: SdkBuiltInType, nameHint: string): Schema {
    switch (type.kind) {
      case "string": 
      case "password":
      case "guid":
      case "ipAddress":
      case "uuid":
      case "ipV4Address":
      case "ipV6Address":
      case "eTag":
      case "armId":
      case "azureLocation":
        return this.processStringSchemaFromSdkType(type, type.kind);
      
      case "numeric":
      case "integer":
      case "safeint":
      case "int8":
      case "uint8":
      case "int16":
      case "uint16":
      case "int32":
      case "uint32":
      case "int64":
      case "uint64":
        // integer
        const integerSize = type.kind === "safeint" || type.kind.includes("int64") ? 64 : 32;
        return this.processIntegerSchemaFromSdkType(type, nameHint, integerSize);

      case "float":
      case "float32":
      case "float64":
        return this.processNumberSchemaFromSdkType(type, nameHint);

      case "decimal":
      case "decimal128":
        return this.processDecimalSchemaFromSdkType(type, nameHint)
        
      case "bytes":
        return this.processByteArraySchemaFromSdkType(type, nameHint);

      case "boolean":
        return this.processBooleanSchemaFromSdkType(type, nameHint);

      case "plainTime":
        return this.processTimeSchemaFromSdkType(type, nameHint);

      case "plainDate":
        return this.processDateSchemaFromSdkType(type, nameHint);

      case "url":
      case "uri":
        return this.processUrlSchemaFromSdkType(type, nameHint);
    }
    throw new Error(`Unrecognized builtin type: '${type.kind}'.`);
  }

  private processAnySchemaFromSdkType(type: SdkType, name: string): AnySchema {
    return this.anySchema;
  }

  private processStringSchema(type: Scalar, name: string): StringSchema {
    return this.codeModel.schemas.add(
      new StringSchema(name, this.getDoc(type), {
        summary: this.getSummary(type),
      }),
    );
  }

  private processStringSchemaFromSdkType(type: SdkBuiltInType, name: string): StringSchema {
    return this.codeModel.schemas.add(
      new StringSchema(name, this.getDoc(type.__raw as Type), {
        summary: this.getSummary(type.__raw as Type),
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

  private processByteArraySchemaFromSdkType(type: SdkBuiltInType, name: string): ByteArraySchema {
    const base64Encoded: boolean = type.encode === "base64url";
    return this.codeModel.schemas.add(
      new ByteArraySchema(name, this.getDoc(type.__raw as Scalar), {
        summary: this.getSummary(type.__raw as Scalar),
        format: base64Encoded ? "base64url" : "byte",
      }),
    );
  }

  private processIntegerSchemaFromSdkType(type: SdkBuiltInType, name: string, precision: number): NumberSchema {
    return this.codeModel.schemas.add(
      new NumberSchema(name, this.getDoc(type.__raw as Scalar), SchemaType.Integer, precision, {
        summary: this.getSummary(type.__raw as Scalar),
      }),
    );
  }

  private processNumberSchemaFromSdkType(type: SdkBuiltInType, name: string): NumberSchema {
    return this.codeModel.schemas.add(
      new NumberSchema(name, this.getDoc(type.__raw as Scalar), SchemaType.Number, 64, {
        summary: this.getSummary(type.__raw as Scalar),
      }),
    );
  }


  private processDecimalSchemaFromSdkType(type: SdkBuiltInType, name: string): NumberSchema {
    // "Infinity" maps to "BigDecimal" in Java
    return this.codeModel.schemas.add(
      new NumberSchema(name, this.getDoc(type.__raw as Scalar), SchemaType.Number, Infinity, {
        summary: this.getSummary(type.__raw as Scalar),
      }),
    );
  }

  private processBooleanSchemaFromSdkType(type: SdkBuiltInType, name: string): BooleanSchema {
    return this.codeModel.schemas.add(
      new BooleanSchema(name, this.getDoc(type.__raw as Scalar), {
        summary: this.getSummary(type.__raw as Scalar),
      }),
    );
  }

  private processArraySchemaFromSdkType(type: SdkArrayType, name: string): ArraySchema {
    const elementSchema = this.processSchemaFromSdkType(type.valueType, name);
    return this.codeModel.schemas.add(
      new ArraySchema(name, this.getDoc(type.__raw as Type), elementSchema, {
        summary: this.getSummary(type.__raw as Type),
      }),
    );
  }

  private processDictionarySchemaFromSdkType(type: SdkDictionaryType, name: string): DictionarySchema {
    const dictSchema = new DictionarySchema<any>(name, this.getDoc(type.__raw as Type), null, {
      summary: this.getSummary(type.__raw as Type),
    });

    // cache this now before we accidentally recurse on this type.
    this.schemaCache.set(type, dictSchema);

    const elementSchema = this.processSchemaFromSdkType(type.valueType, name);
    dictSchema.elementType = elementSchema;

    dictSchema.nullableItems = type.valueType.nullable;

    return this.codeModel.schemas.add(dictSchema);
  }

  private processChoiceSchemaFromSdkType(
    type: SdkEnumType,
    name: string,
  ): ChoiceSchema | SealedChoiceSchema | ConstantSchema {
    const namespace = getNamespace(type.__raw as Enum);
    const valueType = isSdkIntKind(type.valueType.kind)
      ? this.integerSchema
      : isSdkFloatKind(type.valueType.kind)
        ? this.doubleSchema
        : this.stringSchema;

    const choices: ChoiceValue[] = [];
    type.values.forEach((it: SdkEnumValueType) =>
      choices.push(new ChoiceValue(it.name, it.description ?? "", it.value ?? it.name)),
    );

    const schemaType = type.isFixed ? SealedChoiceSchema : ChoiceSchema;

    const schema = new schemaType(type.name ? type.name : name, type.description ?? "", {
      summary: this.getSummary(type.__raw),
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
    schema.crossLanguageDefinitionId = type.crossLanguageDefinitionId;
    return this.codeModel.schemas.add(schema);
  }

  private processConstantSchemaFromSdkType(type: SdkConstantType, name: string): ConstantSchema {
    const valueType =
    type.valueType.kind === "string"
      ? this.stringSchema
      : type.valueType.kind === "boolean"
        ? this.booleanSchema
        : isSdkIntKind(type.valueType.kind)
          ? this.integerSchema
          : this.doubleSchema;

    return this.codeModel.schemas.add(
      new ConstantSchema(name, this.getDoc(type.__raw as (StringLiteral | NumericLiteral | BooleanLiteral)), {
        summary: this.getSummary(type.__raw),
        valueType: valueType,
        value: new ConstantValue(type.value),
      }),
    );
  }

  private processConstantSchemaFromEnumValueFromSdkType(type: SdkEnumValueType, name: string): ConstantSchema {
    const valueType = this.processSchemaFromSdkType(type.enumType, type.enumType.name);

    return this.codeModel.schemas.add(
      new ConstantSchema(name, this.getDoc(type.__raw as Type), {
        summary: this.getSummary(type.__raw as Type),
        valueType: valueType,
        value: new ConstantValue(type.value ?? type.name),
      }),
    );
  }


  private processUnixTimeSchema(type: Scalar, name: string): UnixTimeSchema {
    return this.codeModel.schemas.add(
      new UnixTimeSchema(name, this.getDoc(type), {
        summary: this.getSummary(type),
      }),
    );
  }

  private processUnixTimeSchemaFromSdkType(type: SdkDatetimeType, name: string): UnixTimeSchema {
    return this.codeModel.schemas.add(
      new UnixTimeSchema(name, this.getDoc(type.__raw as Type), {
        summary: this.getSummary(type.__raw as Type),
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

  private processDateTimeSchemaFromSdkType(type: SdkDatetimeType, name: string, rfc1123: boolean): DateTimeSchema {
    return this.codeModel.schemas.add(
      new DateTimeSchema(name, this.getDoc(type.__raw as Type), {
        summary: this.getSummary(type.__raw as Type),
        format: rfc1123 ? "date-time-rfc1123" : "date-time",
      }),
    );
  }

  private processDateSchemaFromSdkType(type: SdkBuiltInType, name: string): DateSchema {
    return this.codeModel.schemas.add(
      new DateSchema(name, this.getDoc(type.__raw as Scalar), {
        summary: this.getSummary(type.__raw as Scalar),
      }),
    );
  }

  private processTimeSchemaFromSdkType(type: SdkBuiltInType, name: string): TimeSchema {
    return this.codeModel.schemas.add(
      new TimeSchema(name, this.getDoc(type.__raw as Scalar), {
        summary: this.getSummary(type.__raw as Scalar),
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

  private processDurationSchemaFromSdkType(
    type: SdkDurationType,
    name: string,
    format: DurationSchema["format"] = "duration-rfc3339",
  ): DurationSchema {
    return this.codeModel.schemas.add(
      new DurationSchema(name, this.getDoc(type.__raw as Scalar), {
        summary: this.getSummary(type.__raw as Scalar),
        format: format,
      }),
    );
  }

  private processUrlSchemaFromSdkType(type: SdkBuiltInType, name: string): UriSchema {
    return this.codeModel.schemas.add(
      new UriSchema(name, this.getDoc(type.__raw as Scalar), {
        summary: this.getSummary(type.__raw as Scalar),
      }),
    );
  }

  private processObjectSchemaFromSdkType(type: SdkModelType, name: string): ObjectSchema {
    const namespace = getNamespace(type.__raw as Model);
    if (
      (this.isArm() &&
        namespace?.startsWith("Azure.ResourceManager") &&
        // there's ResourceListResult under Azure.ResourceManager namespace,
        // which shouldn't be considered Resource schema parent
        (name?.startsWith("TrackedResource") ||
          name?.startsWith("ExtensionResource") ||
          name?.startsWith("ProxyResource"))) ||
      name === "ArmResource"
    ) {
      const objectSchema = this.dummyResourceSchemaFromSdkType(type, name, namespace);
      this.codeModel.schemas.add(objectSchema);

      return objectSchema;
    }
    const objectSchema = new ObjectScheme(name, this.getDoc(type.__raw as Model), {
      summary: this.getSummary(type.__raw as Model),
      language: {
        default: {
          namespace: namespace,
        },
        java: {
          namespace: getJavaNamespace(namespace),
        },
      },
    });
    objectSchema.crossLanguageDefinitionId = type.crossLanguageDefinitionId;
    this.codeModel.schemas.add(objectSchema);

    // cache this now before we accidentally recurse on this type.
    this.schemaCache.set(type, objectSchema);

    // discriminator
    if (type.discriminatedSubtypes && type.discriminatorProperty) {
      objectSchema.discriminator = new Discriminator(this.processModelPropertyFromSdkType(type.discriminatorProperty));
      for (const discriminatorValue in type.discriminatedSubtypes) {
        const subType = type.discriminatedSubtypes[discriminatorValue];
        this.processSchemaFromSdkType(subType, subType.name);
      }
    }
    // let discriminatorPropertyName: string | undefined = undefined;
    // type discriminatorTypeWithPropertyName = Partial<Discriminator> & { propertyName: string };
    // const discriminatorPropertyName = type.discriminatedSubtypes
    // const discriminator = getDiscriminator(this.program, type);
    // if (discriminator) {
    //   discriminatorPropertyName = discriminator.propertyName;
    //   // find the discriminator property from model
    //   // the property is required for getting its serializedName
    //   let discriminatorProperty = Array.from(type.properties.values()).find(
    //     (it) => it.name === discriminatorPropertyName,
    //   );
    //   if (!discriminatorProperty) {
    //     // try find the discriminator property from any of its derived models
    //     for (const deriveModel of type.derivedModels) {
    //       discriminatorProperty = Array.from(deriveModel.properties.values()).find(
    //         (it) => it.name === discriminatorPropertyName,
    //       );
    //       if (discriminatorProperty) {
    //         // found
    //         break;
    //       }
    //     }
    //   }
    //   if (discriminatorProperty) {
    //     objectSchema.discriminator = new Discriminator(this.processModelProperty(discriminatorProperty));
    //   } else {
    //     // fallback to property name, if cannot find the discriminator property
    //     objectSchema.discriminator = new Discriminator(
    //       new Property(discriminatorPropertyName, discriminatorPropertyName, this.stringSchema, {
    //         required: true,
    //         serializedName: discriminatorPropertyName,
    //       }),
    //     );
    //   }
    //   (objectSchema.discriminator as discriminatorTypeWithPropertyName).propertyName = discriminatorPropertyName;
    // }

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
    else if (type.additionalProperties) {
      // parentSchema could be DictionarySchema, which means the model is "additionalProperties"
            // "pure" Record processed elsewhere

      // "mixed" Record that have properties, treat the model as "additionalProperties"
      /* type should have sourceModel, as
      model Type is Record<> {
        prop1: string
      }
      */
      const parentSchema = this.processSchemaFromSdkType(type.additionalProperties, this.getName(type.additionalProperties.__raw as Model));
      objectSchema.parents = new Relations();
      objectSchema.parents.immediate.push(parentSchema);
      pushDistinct(objectSchema.parents.all, parentSchema);
      objectSchema.discriminatorValue = type.discriminatorValue;
    }

    // properties
    for (const prop of type.properties) {
      // TODO: skip discriminator property
      if (prop.name === type.discriminatorProperty?.name || 
        // prop.name === type.baseModel?.discriminatorProperty?.name ||
        !isPayloadProperty(this.program, prop.__raw as ModelProperty) ||
        !this.existsAtCurrentVersion(prop.__raw as ModelProperty)) {
        continue;
      } else {
        objectSchema.addProperty(this.processModelPropertyFromSdkType(prop));
      }
    }

    // process all children
    // type.discriminatedSubtypes?.forEach((it) => this.processSchema(it, this.getName(it)));

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

  private dummyResourceSchemaFromSdkType(type: SdkModelType, name?: string, namespace?: string): ObjectSchema {
    const resourceModelName = name?.startsWith("TrackedResource") ? "Resource" : "ProxyResource";
    const resource = this.dummyObjectSchemaFromSdkType(type, resourceModelName, namespace);
    const declaredProperties = type.properties;
    for (const prop of declaredProperties) {
      if (this.existsAtCurrentVersion(type.__raw as Type)) {      
        resource.addProperty(this.processModelPropertyFromSdkType(prop));
      }
    }
    return resource;
  }

  private dummyObjectSchema(type: Model, name: string, namespace?: string): ObjectSchema {
    return new ObjectScheme(name, this.getDoc(type), {
      summary: this.getSummary(type),
      language: {
        default: {
          name: name,
          namespace: namespace,
        },
        java: {
          name: name,
          namespace: getJavaNamespace(namespace),
        },
      },
    });
  }

  private dummyObjectSchemaFromSdkType(type: SdkModelType, name: string, namespace?: string): ObjectSchema {
    return new ObjectScheme(name, this.getDoc(type.__raw as Model), {
      summary: this.getSummary(type.__raw as Model),
      language: {
        default: {
          name: name,
          namespace: namespace,
        },
        java: {
          name: name,
          namespace: getJavaNamespace(namespace),
        },
      },
    });
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

  private processModelPropertyFromSdkType(prop: SdkModelPropertyType): Property {
    // TODO: question: why the schema name is like this?
    const schemaNameHint = pascalCase(getModelNameForProperty(prop.__raw as ModelProperty)) + pascalCase(prop.name);
    const schema = this.processSchemaFromSdkType(prop.type, schemaNameHint);
    let nullable = prop.optional;

    let extensions: Record<string, any> | undefined = undefined;
    if (this.isSecret(prop.__raw as ModelProperty)) {
      extensions = extensions ?? {};
      extensions["x-ms-secret"] = true;
      // if the property does not return in response, it had to be nullable
      nullable = true;
    }
    if (prop.kind === "property" && (prop as SdkBodyModelPropertyType).flatten) {
      extensions = extensions ?? {};
      extensions["x-ms-client-flatten"] = true;
    }

    return new Property(prop.name, this.getDoc(prop.__raw as ModelProperty), schema, {
      summary: this.getSummary(prop.__raw as ModelProperty),
      required: !prop.optional,
      nullable: nullable,
      readOnly: this.isReadOnly(prop.__raw as ModelProperty),
      // clientDefaultValue: this.getDefaultValue(prop.default),
      serializedName: (prop as SdkBodyModelPropertyType).serializedName,
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

  private processUnionSchemaFromSdkType(type: SdkUnionType, name: string): Schema {
    // TODO: name from typespec-client-generator-core
    const namespace = getNamespace(type.__raw as Union);
    const baseName = type.name ?? pascalCase(name) + "Model";
    this.logWarning(
      `Convert TypeSpec Union '${getUnionDescription(type.__raw as Union, this.typeNameOptions)}' to Class '${baseName}'`,
    );
    const unionSchema = new OrSchema(baseName + "Base", this.getDoc(type.__raw as Union), {
      summary: this.getSummary(type.__raw as Union),
    });
    unionSchema.anyOf = [];
    type.values.forEach((it) => {
      const variantName = this.getUnionVariantName(it.__raw as Type, { depth: 0 });
      const modelName = variantName + baseName;
      const propertyName = "value";

      // these ObjectSchema is not added to codeModel.schemas
      const objectSchema = new ObjectSchema(modelName, this.getDoc(type.__raw as Union), {
        summary: this.getSummary(type.__raw as Union),
        language: {
          default: {
            namespace: namespace,
          },
          java: {
            namespace: getJavaNamespace(namespace),
          },
        },
      });

      const variantSchema = this.processSchemaFromSdkType(it, variantName);
      objectSchema.addProperty(
        new Property(propertyName, this.getDoc(type.__raw as Union), variantSchema, {
          summary: this.getSummary(type.__raw as Union),
          required: true,
          nullable: it.nullable,
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
      case "Union":
        return type.name ?? "Union";
      default:
        throw new Error(`Unrecognized type for union variable: '${type.kind}'.`);
    }
  }

  private processMultipartFormDataSchema(schema: ObjectSchema) {
    if (schema.properties) {
      for (const property of schema.properties) {
        if (property.schema instanceof ByteArraySchema) {
          property.schema = getFileDetailsSchema(
            property.language.default.name,
            schema.language.default.namespace,
            this.codeModel.schemas,
            this.binarySchema,
            this.stringSchema,
          );
        } else if (property.schema instanceof ArraySchema && property.schema.elementType instanceof ByteArraySchema) {
          property.schema = new ArraySchema(
            property.language.default.name,
            property.language.default.description,
            getFileDetailsSchema(
              property.language.default.name,
              schema.language.default.namespace,
              this.codeModel.schemas,
              this.binarySchema,
              this.stringSchema,
            ),
          );
        }
      }
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

  private getSummary(target: Type | undefined): string | undefined {
    return target ? getSummary(this.program, target) : undefined;
  }

  private getName(
    target: Model | Union | UnionVariant | Enum | EnumMember | ModelProperty | Scalar | Operation,
    nameHint: string | undefined = undefined,
  ): string {
    if (!target) {
      return nameHint || "";
    }
    // const sdkType = getClientType(this.sdkContext, target);
    // if (sdkType.kind === "model") {
    //   return sdkType.name;
    // }
    // TODO: once getLibraryName API in typespec-client-generator-core can get projected name from language and client, as well as can handle template case, use getLibraryName API
    const emitterClientName = getClientNameOverride(this.sdkContext, target);
    if (emitterClientName) {
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
    if (typeof target.name === "symbol") {
      return "";
    }
    return target.name || "";
  }

  private getSerializedName(target: ModelProperty): string {
    // TODO: currently this is only for JSON
    return getWireName(this.sdkContext, target);
  }

  // private isReadOnly(target: SdkModelPropertyType): boolean {
  //   // const key = isKey(this.program, target);
  //   const segment = getSegment(this.program, target.__raw as Type) !== undefined;
  //   // if (segment) {
  //   //   return true;
  //   // } else {
  //   const visibility = target.kind === "property" ? (target as SdkBodyModelPropertyType).visibility : undefined;
  //   return visibility?.length == 1 && visibility[0] === Visibility.Read;
  //   // }
  // }

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

  // private isSecret(target: SdkModelPropertyType): boolean {
  //   if (target.kind === "property") {
  //     const visibility = (target as SdkBodyModelPropertyType).visibility;
  //     if (visibility) {
  //       return !visibility.includes(Visibility.Read);
  //     }
  //   }
  //   return false;
  // }

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

  private isSubscriptionId(param: HttpOperationParameter): boolean {
    return (
      "subscriptionId".toLocaleLowerCase() === param?.name?.toLocaleLowerCase() &&
      param.param &&
      isArmCommonType(param.param) &&
      isPathParam(this.program, param.param)
    );
  }

  private subscriptionIdParameter(parameter: HttpOperationParameter): Parameter {
    if (!this._subscriptionParameter) {
      const param = parameter.param;
      const description = getDoc(this.program, param);
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

            schema.children?.all?.forEach((c) => innerApplySchemaUsage(c, schemaUsage));
            schema.children?.immediate?.forEach((c) => innerApplySchemaUsage(c, schemaUsage));

            if (schema.discriminator?.property?.schema) {
              innerApplySchemaUsage(schema.discriminator?.property?.schema, schemaUsage);
            }
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
