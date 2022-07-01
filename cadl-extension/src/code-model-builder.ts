import {
  getDoc,
  getServiceNamespace,
  getServiceNamespaceString,
  getServiceTitle,
  getServiceVersion,
  ModelTypeProperty,
  Program,
  resolvePath,
  Type,
  ModelType,
  ArrayType,
  getSummary,
} from "@cadl-lang/compiler";
import {
  getAllRoutes,
  HttpOperationParameter,
  OperationDetails,
} from "@cadl-lang/rest";
import {
  getVersions,
} from "@cadl-lang/versioning";
import {
  CodeModel, 
  Operation, 
  Parameter, 
  Schema,
  StringSchema,
  HttpParameter,
  ImplementationLocation, 
  ParameterLocation
} from "@autorest/codemodel";

export class CodeModelBuilder {
  private program: Program;
  private namespace: string;
  private codeModel: CodeModel;

  public constructor(program1: Program) {
    this.program = program1;

    const serviceNamespace = getServiceNamespace(this.program);
    if (serviceNamespace === undefined) {
      throw Error("Can not emit yaml for a namespace that doesn't exist.");
    }

    let versions = getVersions(this.program, serviceNamespace);
    if (versions.length === 0 && getServiceVersion(this.program)) {
      versions = [getServiceVersion(this.program)];
    }
    this.namespace = getServiceNamespaceString(this.program)?.toLowerCase() || "client";

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
  }

  public build(): CodeModel {
    // TODO: parameterized host
    this.processHost();

    getAllRoutes(this.program).map(this.processRoute);

    return this.codeModel;
  }

  private processHost() {
    this.codeModel.addGlobalParameter(this.hostParameter);
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
      }
    });

    op.parameters.parameters.map(it => this.processParameter(operation, it));

    operationGroup.addOperation(operation);
  }

  private processParameter(op: Operation, param: HttpOperationParameter) {
    const schema = this.processSchema(param.param.type);
    const parameter = new Parameter(param.name, this.getDoc(param.param), schema, {
      implementation: ImplementationLocation.Method,
      required: !param.param.optional,
      protocol: {
        http: {
          in: param.type
        }
      },
      clientDefaultValue: param.param.default ? this.processSchema(param.param.default) : null,
      language: {
        default: {
          serializedName: param.name
        }
      }
    });
    op.addParameter(parameter);
  }

  private processSchema(type: Type): Schema {

  }

  private getDoc(target: Type): string {
    return getDoc(this.program, target) || "";
  }

  private getSummary(target: Type): string | undefined {
    return getSummary(this.program, target);
  }

  private _stringSchema?: StringSchema;
  get stringSchema() {
    return (
      this._stringSchema ||
      (this._stringSchema = this.codeModel.schemas.add(new StringSchema("string", "simple string")))
    );
  }

  private _hostParameter?: Parameter;
  get hostParameter() {
    return (
      this._hostParameter ||
      (this._hostParameter = new Parameter("$host", "server parameter", this.stringSchema, {
        implementation: ImplementationLocation.Client,
        required: true,
        protocol: {
          http: new HttpParameter(ParameterLocation.Uri),
        },
        clientDefaultValue: "",
        language: {
          default: {
            serializedName: "$host"
          }
        },
        extensions: {
          "x-ms-skip-url-encoding": true
        }
      }))
    );
  }
}
