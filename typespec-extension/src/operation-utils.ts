import { ModelProperty, Operation, Program, Type, Union, ignoreDiagnostics, resolvePath } from "@typespec/compiler";
import {
  HttpOperation,
  getHeaderFieldName,
  getQueryParamName,
  getPathParamName,
  isStatusCode,
  getAllHttpServices,
} from "@typespec/http";
import { resolveOperationId } from "@typespec/openapi";
import { ApiVersions, Parameter } from "@autorest/codemodel";
import { LroMetadata } from "@azure-tools/typespec-azure-core";
import { Client as CodeModelClient, ServiceVersion } from "./common/client.js";
import { CodeModel } from "./common/code-model.js";
import { EmitterOptions } from "./emitter.js";
import { getVersion } from "@typespec/versioning";
import { getNamespace, logWarning, pascalCase, trace } from "./utils.js";
import { unionReferedByType } from "./type-utils.js";

export const specialHeaderNames = new Set([
  "repeatability-request-id",
  "repeatability-first-sent",
  "x-ms-client-request-id",
  "client-request-id",
  "return-client-request-id",
]);

export const originApiVersion = "modelerfour:synthesized/api-version";

export async function loadExamples(program: Program, options: EmitterOptions): Promise<Map<Operation, any>> {
  const operationExamplesMap = new Map<Operation, any>();
  if (options["examples-directory"]) {
    const operationIdExamplesMap = new Map<string, any>();

    const service = ignoreDiagnostics(getAllHttpServices(program))[0];
    let version = undefined;
    const versioning = getVersion(program, service.namespace);
    if (versioning && versioning.getVersions()) {
      const versions = versioning.getVersions();
      version = versions[versions.length - 1].value;
    }

    const exampleDir = version
      ? resolvePath(options["examples-directory"], version)
      : resolvePath(options["examples-directory"]);
    try {
      if (!(await program.host.stat(exampleDir)).isDirectory()) return operationExamplesMap;
    } catch (err) {
      logWarning(program, `Examples directory '${exampleDir}' does not exist.`);
      return operationExamplesMap;
    }
    const exampleFiles = await program.host.readDir(exampleDir);
    for (const fileName of exampleFiles) {
      try {
        const exampleFile = await program.host.readFile(resolvePath(exampleDir, fileName));
        const example = JSON.parse(exampleFile.text);
        if (!example.operationId) {
          logWarning(program, `Example file '${fileName}' is missing operationId.`);
          continue;
        }

        if (!operationIdExamplesMap.has(example.operationId)) {
          operationIdExamplesMap.set(example.operationId, example);
        }
      } catch (err) {
        logWarning(program, `Failed to load example file '${fileName}'.`);
      }
    }

    if (operationIdExamplesMap.size > 0) {
      const routes = service.operations;
      routes.forEach((it) => {
        const operationId = pascalCaseForOperationId(resolveOperationId(program, it.operation));
        if (operationIdExamplesMap.has(operationId)) {
          operationExamplesMap.set(it.operation, operationIdExamplesMap.get(operationId));
        }
      });
    }
  }
  return operationExamplesMap;
}

function pascalCaseForOperationId(name: string) {
  return name
    .split("_")
    .map((s) => pascalCase(s))
    .join("_");
}

export function operationContainsJsonMergePatch(op: HttpOperation): boolean {
  for (const param of op.parameters.parameters) {
    if (param.type === "header" && param.name.toLowerCase() === "content-type") {
      if (param.param.type.kind === "String" && param.param.type.value === "application/merge-patch+json") {
        return true;
      }
    }
  }
  return false;
}

export function operationIsMultipleContentTypes(op: HttpOperation): boolean {
  if (
    op.parameters.parameters &&
    op.parameters.parameters.some(
      (parameter) =>
        parameter?.type === "header" &&
        parameter?.name?.toLowerCase() === "content-type" &&
        parameter?.param?.type?.kind === "Union",
    )
  ) {
    return true;
  }
  return false;
}

export function operationRefersUnion(
  program: Program,
  op: HttpOperation,
  getTypeName: (type: Type) => string,
): boolean {
  const visited = new Set<Type>();
  // request body
  if (op.parameters.body) {
    if (op.parameters.body.parameter) {
      const ret = unionReferedByType(program, op.parameters.body.parameter.type, visited);
      if (ret) {
        trace(program, `Operation '${op.operation.name}' refers Union '${getUnionName(ret, getTypeName)}'`);
        return true;
      }
    } else if (op.parameters.body.type) {
      const ret = unionReferedByType(program, op.parameters.body.type, visited);
      if (ret) {
        trace(program, `Operation '${op.operation.name}' refers Union '${getUnionName(ret, getTypeName)}'`);
        return true;
      }
    }
  }
  // response body
  if (op.responses && op.responses.length > 0 && op.responses[0].type) {
    const ret = unionReferedByType(program, op.responses[0].type, visited);
    if (ret) {
      trace(program, `Operation '${op.operation.name}' refers Union '${getUnionName(ret, getTypeName)}'`);
      return true;
    }
  }
  return false;
}

function getUnionName(union: Union, getTypeName: (type: Type) => string) {
  let name = union.name;
  if (!name) {
    const names: string[] = [];
    union.variants.forEach((it) => {
      names.push(getTypeName(it.type));
    });
    name = names.join(" | ");
  }
  return name;
}

export function isPayloadProperty(program: Program, property: ModelProperty) {
  const headerInfo = getHeaderFieldName(program, property);
  const queryInfo = getQueryParamName(program, property);
  const pathInfo = getPathParamName(program, property);
  const statusCodeInfo = isStatusCode(program, property);
  return !(headerInfo || queryInfo || pathInfo || statusCodeInfo);
}

export function getClientApiVersions(client: CodeModelClient): ApiVersions | undefined {
  if (client.globalParameters?.find((it) => it.origin === originApiVersion)) {
    return client.apiVersions;
  } else {
    return undefined;
  }
}

export function getServiceVersion(client: CodeModelClient | CodeModel): ServiceVersion {
  let name = client.language.default.name;
  let description = name;
  if (name.endsWith("Client")) {
    name = name.substring(0, name.length - "Client".length);
  } else {
    description = description + "Client";
  }
  if (name.endsWith("Service")) {
    name = name + "Version";
  } else {
    name = name + "ServiceVersion";
  }
  return new ServiceVersion(name, description);
}

export function isLroNewPollingStrategy(httpOperation: HttpOperation, lroMetadata: LroMetadata): boolean {
  const operation = httpOperation.operation;
  let useNewStrategy = false;
  if (
    lroMetadata.pollingInfo &&
    lroMetadata.statusMonitorStep &&
    lroMetadata.pollingInfo.responseModel.name === "OperationStatus" &&
    getNamespace(lroMetadata.pollingInfo.responseModel) === "Azure.Core.Foundations"
  ) {
    useNewStrategy =
      operation.sourceOperation !== undefined && getNamespace(operation.sourceOperation) === "Azure.Core";
  }

  if (!useNewStrategy) {
    // following 2 pattern in LroMetadata requires new polling strategy

    if (httpOperation.verb === "put" && !lroMetadata.finalStep) {
      // PUT without last GET on resource
      useNewStrategy = true;
    } else if (
      httpOperation.verb === "post" &&
      lroMetadata.finalStep &&
      lroMetadata.finalStep.kind === "pollingSuccessProperty" &&
      lroMetadata.finalStep.target.name === "result"
    ) {
      // POST with final result in "result" property
      useNewStrategy = true;
    }
  }

  return useNewStrategy;
}

export function cloneOperationParameter(parameter: Parameter): Parameter {
  return new Parameter(parameter.language.default.name, parameter.language.default.description, parameter.schema, {
    language: {
      default: {
        serializedName: parameter.language.default.serializedName,
      },
    },
    protocol: parameter.protocol,
    summary: parameter.summary,
    implementation: parameter.implementation,
    required: parameter.required,
    nullable: parameter.nullable,
    extensions: parameter.extensions,
  });
}
