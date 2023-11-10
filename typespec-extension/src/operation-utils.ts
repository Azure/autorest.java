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
import { getVersion } from "@typespec/versioning";
import { Client as CodeModelClient, ServiceVersion } from "./common/client.js";
import { CodeModel } from "./common/code-model.js";
import { EmitterOptions } from "./emitter.js";
import { getNamespace, logWarning, pascalCase } from "./utils.js";
import { modelIs, unionReferredByType } from "./type-utils.js";

export const SPECIAL_HEADER_NAMES = new Set([
  "repeatability-request-id",
  "repeatability-first-sent",
  "x-ms-client-request-id",
  "client-request-id",
  "return-client-request-id",
]);

export const ORIGIN_API_VERSION = "modelerfour:synthesized/api-version";

export const CONTENT_TYPE_KEY = "content-type";

// azure-core SerializerEncoding.SUPPORTED_MIME_TYPES
const SUPPORTED_MIME_TYPES = new Set<string>([
  "text/xml",
  "application/xml",
  "application/json",
  "text/css",
  "text/csv",
  "text/html",
  "text/javascript",
  "text/plain",
  // not in azure-core
  "application/merge-patch+json",
]);

export function isKnownContentType(contentTypes: string[]): boolean {
  return contentTypes
    .map((it) => it.toLowerCase())
    .some((it) => {
      return SUPPORTED_MIME_TYPES.has(it);
    });
}

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

export function operationIsJsonMergePatch(op: HttpOperation): boolean {
  return operationIsContentType(op, "application/merge-patch+json");
}

export function operationIsMultipart(op: HttpOperation): boolean {
  return operationIsContentType(op, "multipart/form-data");
}

function operationIsContentType(op: HttpOperation, contentType: string): boolean {
  for (const param of op.parameters.parameters) {
    if (param.type === "header" && param.name.toLowerCase() === CONTENT_TYPE_KEY) {
      if (param.param.type.kind === "String" && param.param.type.value === contentType) {
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
        parameter?.name?.toLowerCase() === CONTENT_TYPE_KEY &&
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
  cache: Map<Type, Union | null | undefined>,
): Union | null {
  // request parameters
  for (const parameter of op.parameters.parameters) {
    const ret = unionReferredByType(program, parameter.param.type, cache);
    if (ret) {
      return ret;
    }
  }
  // request body
  if (op.parameters.body) {
    if (op.parameters.body.parameter) {
      const ret = unionReferredByType(program, op.parameters.body.parameter.type, cache);
      if (ret) {
        return ret;
      }
    } else if (op.parameters.body.type) {
      const ret = unionReferredByType(program, op.parameters.body.type, cache);
      if (ret) {
        return ret;
      }
    }
  }
  // response body
  if (op.responses && op.responses.length > 0 && op.responses[0].type) {
    const ret = unionReferredByType(program, op.responses[0].type, cache);
    if (ret) {
      return ret;
    }
  }
  // TODO (weidxu): LRO response
  return null;
}

export function isPayloadProperty(program: Program, property: ModelProperty) {
  const headerInfo = getHeaderFieldName(program, property);
  const queryInfo = getQueryParamName(program, property);
  const pathInfo = getPathParamName(program, property);
  const statusCodeInfo = isStatusCode(program, property);
  return !(headerInfo || queryInfo || pathInfo || statusCodeInfo);
}

export function getClientApiVersions(client: CodeModelClient): ApiVersions | undefined {
  if (client.globalParameters?.find((it) => it.origin === ORIGIN_API_VERSION)) {
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
    modelIs(lroMetadata.pollingInfo.responseModel, "OperationStatus", "Azure.Core.Foundations")
  ) {
    useNewStrategy = operationIs(operation, undefined, "Azure.Core");
  }

  if (!useNewStrategy) {
    // LroMetadata: following 2 pattern in LroMetadata requires new polling strategy
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

    // OperationStatus: check if the required property "id" and "status" is present
    // If they are not present, azure-core cannot de-serialize the response to PollOperationDetailsSchema
    if (useNewStrategy) {
      const idProperty = lroMetadata.envelopeResult.properties.get("id");
      const statusProperty = lroMetadata.envelopeResult.properties.get("status");
      useNewStrategy = !!(idProperty && statusProperty && !idProperty.optional && !statusProperty.optional);
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

function operationIs(operation: Operation, name: string | undefined, namespace: string): boolean {
  let currentOp: Operation | undefined = operation;
  while (currentOp) {
    if ((!name || currentOp.name === name) && getNamespace(currentOp) === namespace) {
      return true;
    }
    currentOp = currentOp.sourceOperation;
  }
  return false;
}
