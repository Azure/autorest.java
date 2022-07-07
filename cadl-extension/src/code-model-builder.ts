import {
  ArrayType,
  BooleanLiteralType,
  EnumType,
  getDoc,
  getFormat,
  getFriendlyName,
  getIntrinsicModelName,
  getKnownValues,
  getMaxLength,
  getMaxValue,
  getMinLength,
  getMinValue,
  getPattern,
  getServiceNamespace,
  getServiceNamespaceString,
  getServiceTitle,
  getServiceVersion,
  getSummary,
  getVisibility,
  ignoreDiagnostics,
  isIntrinsic,
  ModelType,
  ModelTypeProperty,
  NumericLiteralType,
  Program,
  StringLiteralType,
  Type, UnionType,
} from "@cadl-lang/compiler";
import {
  getAllRoutes,
  getServers,
  getStatusCodeDescription,
  HttpOperationParameter,
  HttpOperationResponse,
  HttpServer,
  OperationDetails,
  StatusCode,
} from "@cadl-lang/rest/http";
import {
  getVersion 
} from "@cadl-lang/versioning";
import {
  AnySchema,
  ArraySchema,
  BinarySchema,
  BooleanSchema,
  ChoiceSchema,
  ChoiceValue,
  CodeModel,
  ConstantSchema,
  ConstantValue,
  DateTimeSchema,
  DateSchema,
  DictionarySchema,
  HttpHeader,
  HttpParameter,
  ImplementationLocation,
  NumberSchema,
  ObjectSchema,
  Operation,
  Parameter,
  ParameterLocation,
  Property,
  Request,
  Response,
  Schema,
  SchemaResponse,
  SchemaType,
  SealedChoiceSchema,
  StringSchema,
  TimeSchema,
  DurationSchema,
  ByteArraySchema,
} from "@autorest/codemodel";
import {fail} from "assert";

export class CodeModelBuilder {
  private program: Program;
  private _namespace: string;
  private version: string;
  private baseUri: string;
  private hostParameters: Parameter[];

  private codeModel: CodeModel;

  private schemaCache = new ProcessingCache((type: Type, name: string) =>
    this.processSchemaImpl(type, name),
  );

  public constructor(program1: Program) {
    this.program = program1;
    const serviceNamespace = getServiceNamespace(this.program);
    if (serviceNamespace === undefined) {
      throw Error("Can not emit yaml for a namespace that doesn't exist.");
    }

    // java namespace
    this._namespace = "com." + (getServiceNamespaceString(this.program) || "Azure.Client").toLowerCase();

    // service version
    this.version = getServiceVersion(this.program);
    if (this.version === "0000-00-00") {
      const versioning = getVersion(this.program, serviceNamespace);
      if (versioning) {
        // TODO: versioning support
        this.version = versioning.getVersions()[0].value;
      }
    }

    // init code model
    const title = getServiceTitle(this.program);
    const description = this.getDoc(serviceNamespace);
    this.codeModel = new CodeModel(title, false, {
      info: {
        description: description
      },
      language: {
        default: {
          name: title,
          description: description,
          summary: this.getSummary(serviceNamespace)
        }
      }
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
  }

  public build(): CodeModel {
    ignoreDiagnostics(getAllRoutes(this.program)).map(it => this.processRoute(it));

    return this.codeModel;
  }

  public namespace(): string {
    return this._namespace;
  }

  private processHost(server: HttpServer | undefined) {
    if (server) {
      server.parameters.forEach(it => {
        const schema = this.processSchema(it.type, it.name);
        return this.hostParameters.push(this.codeModel.addGlobalParameter(new Parameter(it.name, this.getDoc(it), schema, {
          implementation: ImplementationLocation.Client,
          required: true,
          protocol: {
            http: new HttpParameter(ParameterLocation.Uri)
          },
          clientDefaultValue: this.getDefaultValue(it.default),
          language: {
            default: {
              serializedName: it.name
            }
          }
        })));
      });
    } else {
      this.hostParameters.push(this.codeModel.addGlobalParameter(this.hostParameter));
    }
  }

  private processRoute(op: OperationDetails) {
    const operationGroup = this.codeModel.getOperationGroup(op.groupName);
    const opId = `${op.groupName}_${op.operation.name}`

    const operation = new Operation(op.operation.name, this.getDoc(op.operation), {
      operationId: opId,
      language: {
        default: {
          summary: this.getSummary(op.operation)
        }
      },
      apiVersions: [{
        version: this.version
      }]
    });

    operation.addRequest(new Request({
      protocol: {
        http: {
          path: op.path,
          method: op.verb,
          uri: this.baseUri
        }
      }
    }));

    this.hostParameters.forEach(it => operation.addParameter(it));
    op.parameters.parameters.map(it => this.processParameter(operation, it));
    this.addAcceptHeaderParameter(operation, op.responses);
    if (op.parameters.body) {
      this.processBody(operation, op.parameters.body);
    }
    op.responses.map(it => this.processResponse(operation, it));

    operationGroup.addOperation(operation);
  }

  private processParameter(op: Operation, param: HttpOperationParameter) {
    if (param.name === 'api-version') {
      const parameter = this.apiVersionParameter;
      op.addParameter(parameter);
    } else {
      const schema = this.processSchema(param.param.type, param.param.name);
      const parameter = new Parameter(param.name, this.getDoc(param.param), schema, {
        implementation: ImplementationLocation.Method,
        required: !param.param.optional,
        protocol: {
          http: new HttpParameter(param.type)
        },
        clientDefaultValue: this.getDefaultValue(param.param.default),
        language: {
          default: {
            serializedName: param.name
          }
        }
      });
      op.addParameter(parameter);
    }
  }

  private addAcceptHeaderParameter(op: Operation, responses: HttpOperationResponse[]) {
    const produces = new Set<string>(["application/json"]);
    for (const resp of responses) {
      if (resp.responses && resp.responses.length > 0) {
        for (const response of resp.responses) {
          response.body?.contentTypes.forEach(it => produces.add(it));
        }
      }
    }
    const acceptTypes = Array.from(produces.values()).join(", ");

    const acceptSchema = this.codeModel.schemas.constants?.find(
      it => it.language.default.name === "accept" && it.value.value === acceptTypes,
    ) || this.codeModel.schemas.add(new ConstantSchema("accept", `Accept: ${acceptTypes}`, {
      valueType: this.stringSchema,
      value: new ConstantValue(acceptTypes)
    }));
    op.addParameter(new Parameter("accept", "Accept header", acceptSchema, {
      implementation: ImplementationLocation.Method,
      required: true,
      protocol: {
        http: new HttpParameter(ParameterLocation.Header)
      },
      language: {
        default: {
          serializedName: "accept",
        },
      }
    }));
  }

  private processBody(op: Operation, body: ModelTypeProperty) {
    const schema = this.processSchema(body.type, body.name);
    const parameter = new Parameter(body.name, this.getDoc(body), schema, {
      implementation: ImplementationLocation.Method,
      required: !body.optional,
      protocol: {
        http: new HttpParameter(ParameterLocation.Body)
      },
      clientDefaultValue: this.getDefaultValue(body.default),
    });
    op.addParameter(parameter);
  }

  private processResponse(op: Operation, resp: HttpOperationResponse) {
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
      if (responseBody.type.kind === "Model" && responseBody.type.name === "bytes") {
        // binary
        response = new BinarySchema(this.getResponseDescription(resp), {
          protocol: {
            http: {
              statusCodes: [this.getStatusCode(resp.statusCode)],
              headers: headers,
              mediaTypes: responseBody.contentTypes,
              knownMediaType: "binary"
            }
          }
        });
      } else {
        // schema (usually JSON)
        const schema = this.processSchema(responseBody.type, "response");
        response = new SchemaResponse(schema, {
          protocol: {
            http: {
              statusCodes: [this.getStatusCode(resp.statusCode)],
              headers: headers,
              mediaTypes: responseBody.contentTypes
            }
          },
          language: {
            default: {
              description: this.getResponseDescription(resp)
            }
          }
        });
      }
    } else {
      // not binary nor schema, usually NoContent
      response = new Response({
        protocol: {
          http: {
            statusCodes: [this.getStatusCode(resp.statusCode)],
            headers: headers,
          }
        },
        language: {
          default: {
            description: this.getResponseDescription(resp)
          }
        }
      });
    }
    if (resp.statusCode === "*") {
      // TODO: x-ms-error-response
      op.addException(response);
    } else {
      op.addResponse(response);
    }
  }

  private getStatusCode(statusCode: StatusCode): string {
    return statusCode === "*" ? "default" : statusCode;
  }

  private getResponseDescription(resp: HttpOperationResponse): string {
    return resp.description || (resp.statusCode === "*" ? "An unexpected error response" : getStatusCodeDescription(resp.statusCode)) || "";
  }

  private processSchema(type: Type, name: string): Schema {
    return this.schemaCache.process(type, name) || fail("Unable to process schema.");
  }

  private processSchemaImpl(type: Type, name: string): Schema {
    switch (type.kind) {
      case "String":
        // TODO: getFormat
        // byte, binary, date, date-time, password
        return this.processStringSchema(type, name);
        
      case "Number":
        const isInteger = getFormat(this.program, type)?.startsWith("int");
        return isInteger ? this.processIntegerSchema(type, name) : this.processNumberSchema(type, name);

      case "Boolean":
        return this.processBooleanSchema(type, name);

      case "Array":
        return this.processArraySchema(type, name);

      case "Enum":
        return this.processChoiceSchema(type, name, true);

      case "Union":
        return this.processUnionSchema(type, name)

      case "Model":
        if (isIntrinsic(this.program, type)) {
          const intrinsicModelName = getIntrinsicModelName(this.program, type);
          switch (intrinsicModelName) {
            case "string":
              const enumType = getKnownValues(this.program, type);
              if (enumType) {
                return this.processChoiceSchema(enumType, name, false);
              } else {
                return this.processStringSchema(type, name);
              }

            case "bytes":
              return this.processByteArraySchema(type, name);

            case "boolean":
              return this.processBooleanSchema(type, name);
              
            case "Map":
              return this.processMapSchema(type, name);

            case "plainTime":
              return this.processTimeSchema(type, name);

            case "plainDate":
              return this.processDateSchema(type, name);

            case "zonedDateTime":
              return this.processDateTimeSchema(type, name, false);

            case "duration":
              return this.processDurationSchema(type, name);
          }

          if (intrinsicModelName.startsWith("int") || intrinsicModelName.startsWith("uint") || intrinsicModelName === "safeint") {
            // integer
            return this.processIntegerSchema(type, name);
          } else if (intrinsicModelName.startsWith("float")) {
            // float point
            return this.processNumberSchema(type, name);
          } else {
            throw new Error(`Unrecognized intrinsic type: '${intrinsicModelName}'.`);
          }
        } else {
          return this.processObjectSchema(type, this.getName(type, type.name));
        }
    }
    throw new Error(`Unrecognized type: '${type.kind}'.`);
  }

  private processStringSchema(type: StringLiteralType | ModelType, name: string): StringSchema {
    return this.codeModel.schemas.add(
      new StringSchema(name, this.getDoc(type), {
        summary: this.getSummary(type),
        maxLength: getMaxLength(this.program, type),
        minLength: getMinLength(this.program, type),
        pattern: getPattern(this.program, type)
      })
    );
  }

  private processByteArraySchema(type: ModelType, name: string): ByteArraySchema {
    return this.codeModel.schemas.add(
      new ByteArraySchema(name, this.getDoc(type), {
        summary: this.getSummary(type),
        format: "byte"
      })
    );
  }

  private processIntegerSchema(type: NumericLiteralType | ModelType, name: string): NumberSchema {
    return this.codeModel.schemas.add(
      new NumberSchema(name, this.getDoc(type), SchemaType.Integer, 64, {
        summary: this.getSummary(type),
        maximum: getMaxValue(this.program, type),
        minimum: getMinValue(this.program, type)
      })
    );
  }

  private processNumberSchema(type: NumericLiteralType | ModelType, name: string): NumberSchema {
    return this.codeModel.schemas.add(
      new NumberSchema(name, this.getDoc(type), SchemaType.Number, 64, {
        summary: this.getSummary(type),
        maximum: getMaxValue(this.program, type),
        minimum: getMinValue(this.program, type)
      })
    );
  }

  private processBooleanSchema(type: BooleanLiteralType | ModelType, name: string): BooleanSchema {
    return this.codeModel.schemas.add(
      new BooleanSchema(name, this.getDoc(type), {
        summary: this.getSummary(type)
      })
    );
  }

  private processArraySchema(type: ArrayType, name: string): ArraySchema {
    const elementSchema = this.processSchema(type.elementType, name);
    return this.codeModel.schemas.add(
      new ArraySchema(name, this.getDoc(type), elementSchema, {
        summary: this.getSummary(type),
        maxItems: getMaxLength(this.program, type),
        minItems: getMinLength(this.program, type),
      }));
  }

  private processMapSchema(type: ModelType, name: string): DictionarySchema {
    const dictSchema = this.codeModel.schemas.add(
      new DictionarySchema<any>(name, this.getDoc(type), null, {
        summary: this.getSummary(type),
      }));

    // cache this now before we accidentally recurse on this type.
    this.schemaCache.set(type, dictSchema);

    const elementType = type.properties.get("v")!;
    const elementSchema = this.processSchema(elementType.type, elementType.name);
    dictSchema.elementType = elementSchema;

    return this.codeModel.schemas.add(dictSchema);
  }

  private processChoiceSchema(type: EnumType, name: string, sealed: boolean): ChoiceSchema | SealedChoiceSchema | ConstantSchema {
    const isConstant = sealed && type.members.length === 1;
    const valueType = typeof type.members[0].value === "number" ? this.integerSchema : this.stringSchema;
    
    if (isConstant) {
      return this.codeModel.schemas.add(
        new ConstantSchema(name, this.getDoc(type), {
          summary: this.getSummary(type),
          valueType: valueType,
          value: new ConstantValue(type.members[0].value)
        })
      );
    } else {
      const choices: ChoiceValue[] = [];
      type.members.forEach(it => choices.push(new ChoiceValue(it.name, this.getDoc(it), it.value || it.name)));

      if (sealed) {
        return this.codeModel.schemas.add(
          new SealedChoiceSchema(name, this.getDoc(type), {
            summary: this.getSummary(type),
            choiceType: valueType as any,
            choices: choices
          })
        );
      } else {
        return this.codeModel.schemas.add(
          new ChoiceSchema(name, this.getDoc(type), {
            summary: this.getSummary(type),
            choiceType: valueType as any,
            choices: choices
          })
        );
      }
    }
  }

  private processDateTimeSchema(type: ModelType, name: string, rfc1123: boolean): DateTimeSchema {
    return this.codeModel.schemas.add(
        new DateTimeSchema(name, this.getDoc(type), {
          summary: this.getSummary(type),
          format: rfc1123 ? "date-time-rfc1123" : "date-time",
        })
    );
  }

  private processDateSchema(type: ModelType, name: string): DateSchema {
    return this.codeModel.schemas.add(
        new DateSchema(name, this.getDoc(type), {
          summary: this.getSummary(type),
        })
    );
  }

  private processTimeSchema(type: ModelType, name: string): TimeSchema {
    return this.codeModel.schemas.add(
        new TimeSchema(name, this.getDoc(type), {
          summary: this.getSummary(type),
        })
    );
  }

  private processDurationSchema(type: ModelType, name: string): DurationSchema {
    return this.codeModel.schemas.add(
        new DurationSchema(name, this.getDoc(type), {
          summary: this.getSummary(type),
        })
    );
  }

  private processObjectSchema(type: ModelType, name: string): ObjectSchema {
    const objectSchema = this.codeModel.schemas.add(
      new ObjectSchema(name, this.getDoc(type), {
        summary: this.getSummary(type)
      })
    );

    // cache this now before we accidentally recurse on this type.
    this.schemaCache.set(type, objectSchema);

    for (const [_, prop] of type.properties) {
      const schema = this.processSchema(prop.type, prop.name);
      objectSchema.addProperty(
        new Property(this.getName(prop, prop.name), this.getDoc(prop), schema, {
          required: !prop.optional,
          readOnly: !getVisibility(this.program, prop)?.includes("write"),
          serializedName: prop.name
        })
      );
    }

    return objectSchema;
  }

  private processUnionSchema(type: UnionType, name: string): Schema {
    const nonNullVariants = Array.from(type.variants.values())
        .filter(it => !(isIntrinsic(this.program, it.type) && getIntrinsicModelName(this.program, it.type) === "null"));
    if (nonNullVariants.length === 1) {
      // nullable
      return this.processSchema(nonNullVariants[0].type, name);
    }

    // TODO
    return this.codeModel.schemas.add(
        new ObjectSchema(name, this.getDoc(type), {
          summary: this.getSummary(type),
        })
    );
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

  private getName(target: Type, name: string): string {
    return getFriendlyName(this.program, target) || name;
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
      (this._integerSchema = this.codeModel.schemas.add(new NumberSchema("integer", "simple integer", SchemaType.Integer, 64)))
    );
  }

  private _binarySchema?: BinarySchema;
  get binarySchema(): BinarySchema {
    return (
      this._binarySchema ||
      (this._binarySchema = this.codeModel.schemas.add(new BinarySchema("binary")))
    );
  }

  private _anySchema?: AnySchema;
  public get anySchema(): AnySchema {
    return this._anySchema ?? (this._anySchema = this.codeModel.schemas.add(new AnySchema("Anything")));
  }

  private _hostParameter?: Parameter;
  get hostParameter(): Parameter {
    return (
      this._hostParameter ||
      (this._hostParameter = new Parameter("endpoint", "Server parameter", this.stringSchema, {
        implementation: ImplementationLocation.Client,
        required: true,
        protocol: {
          http: new HttpParameter(ParameterLocation.Uri),
        },
        clientDefaultValue: "",
        language: {
          default: {
            serializedName: "endpoint"
          }
        },
        extensions: {
          "x-ms-skip-url-encoding": true
        }
      }))
    );
  }

  private _apiVersionParameter?: Parameter;
  get apiVersionParameter(): Parameter {
    return (
      this._apiVersionParameter ||
      (this._apiVersionParameter = new Parameter("api-version", "Version parameter", this.codeModel.schemas.add(new ConstantSchema("accept", `api-version: ${this.version}`, {
        valueType: this.stringSchema,
        value: new ConstantValue(this.version)
      })), {
        implementation: ImplementationLocation.Client,
        required: true,
        protocol: {
          http: new HttpParameter(ParameterLocation.Query),
        },
        clientDefaultValue: this.version,
        language: {
          default: {
            serializedName: "api-version"
          }
        }
      }))
    );
  }
}

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
