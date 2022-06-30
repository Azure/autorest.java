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
  dump, 
  DEFAULT_SCHEMA, 
  Type as YamlType, 
} from "js-yaml";
import {
  CodeModel, Operation
} from "@autorest/codemodel";
  
let program: Program;
let namespace: string;
let codeModel: CodeModel;

export function createCodeMode(program1: Program): CodeModel {
  program = program1;

  const serviceNamespace = getServiceNamespace(program);
  if (serviceNamespace === undefined) {
    throw Error("Can not emit yaml for a namespace that doesn't exist.");
  }

  let versions = getVersions(program, serviceNamespace);
  if (versions.length === 0 && getServiceVersion(program)) {
    versions = [getServiceVersion(program)];
  }
  namespace = getServiceNamespaceString(program)?.toLowerCase() || "client";

  const title = getServiceTitle(program);
  const description = getDoc(program, serviceNamespace);
  codeModel = new CodeModel(title, false, {
    info: {
      description: description
    },
    language: {
      default: {
        name: title,
        description: description,
        summary: getSummary(program, serviceNamespace)
      }
    }
  });

  getAllRoutes(program).map(processRoute);

  return codeModel;
}

function processRoute(op: OperationDetails) {
  const operationGroup = codeModel.getOperationGroup(op.groupName);
  const opId = `${op.groupName}_${op.operation.name}`

  const operation = new Operation(op.operation.name, getDoc(program, op.operation) || '', {
    operationId: opId,
    language: {
      default: {
        summary: getSummary(program, op.operation)
      }
    }
  });

  operationGroup.addOperation(operation);
}
