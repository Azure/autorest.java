import {
  EncodeData,
  Enum,
  IntrinsicScalarName,
  Model,
  ModelProperty,
  NoTarget,
  Operation,
  Program,
  Scalar,
  TemplatedTypeBase,
  Type,
  Union,
  ignoreDiagnostics,
  isGlobalNamespace,
  isService,
  isTemplateDeclaration,
  isTemplateInstance,
  resolvePath,
} from "@typespec/compiler";
import {
  HttpOperation,
  getHeaderFieldName,
  getQueryParamName,
  getPathParamName,
  isStatusCode,
  getAllHttpServices,
} from "@typespec/http";
import { ApiVersions } from "@autorest/codemodel";
import { Client as CodeModelClient, ServiceVersion } from "./common/client.js";
import { CodeModel } from "./common/code-model.js";
import { EmitterOptions } from "./emitter.js";
import { getVersion } from "@typespec/versioning";
import { DurationSchema } from "./common/schemas/time.js";

export const specialHeaderNames = new Set([
  "repeatability-request-id",
  "repeatability-first-sent",
  "x-ms-client-request-id",
]);

export const originApiVersion = "modelerfour:synthesized/api-version";

/** Acts as a cache for processing inputs.
 *
 * If the input is undefined, the output is always undefined.
 * for a given input, the process is only ever called once.
 *
 *
 */
export class ProcessingCache<In, Out> {
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
export function pushDistinct<T>(targetArray: Array<T>, ...items: Array<T>): Array<T> {
  for (const i of items) {
    if (!targetArray.includes(i)) {
      targetArray.push(i);
    }
  }
  return targetArray;
}

export function logWarning(program: Program, msg: string) {
  program.trace("typespec-java", msg);
  program.reportDiagnostic({
    code: "typespec-java",
    severity: "warning",
    message: msg,
    target: NoTarget,
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
        const operationId = pascalCase(resolveOperationId(program, it.operation));
        if (operationIdExamplesMap.has(operationId)) {
          operationExamplesMap.set(it.operation, operationIdExamplesMap.get(operationId));
        }
      });
    }
  }
  return operationExamplesMap;
}

export function resolveOperationId(program: Program, operation: Operation) {
  // const explicitOperationId = getOperationId(program, operation);
  // if (explicitOperationId) {
  //   return explicitOperationId;
  // }

  if (operation.interface) {
    return `${operation.interface.name}_${operation.name}`;
  }
  const namespace = operation.namespace;
  if (namespace === undefined || isGlobalNamespace(program, namespace) || isService(program, namespace)) {
    return operation.name;
  }

  return `${namespace.name}_${operation.name}`;
}

export function getNamespace(type: Model | Enum | Union | Operation): string | undefined {
  let namespaceRef = type.namespace;
  let namespaceStr: string | undefined = undefined;
  while (namespaceRef && namespaceRef.name.length !== 0) {
    namespaceStr = namespaceRef.name + (namespaceStr ? "." + namespaceStr : "");
    namespaceRef = namespaceRef.namespace;
  }
  return namespaceStr;
}

export function getJavaNamespace(namespace: string | undefined): string | undefined {
  return namespace ? "com." + namespace.toLowerCase() : undefined;
}

export function modelContainsDerivedModel(model: Model): boolean {
  return !isTemplateDeclaration(model) && !(isTemplateInstance(model) && model.derivedModels.length === 0);
}

export function isModelReferredInTemplate(template: TemplatedTypeBase, target: Model): boolean {
  return (
    template === target ||
    (template?.templateMapper?.args?.some((it) =>
      it.kind === "Model" || it.kind === "Union" ? isModelReferredInTemplate(it, target) : false,
    ) ??
      false)
  );
}

export function getNameForTemplate(target: Type): string {
  switch (target.kind) {
    case "Model": {
      let name = target.name;
      if (target.templateMapper && target.templateMapper.args) {
        name = name + target.templateMapper.args.map((it) => getNameForTemplate(it)).join("");
      }
      return name;
    }

    case "String":
      return target.value;

    default:
      return "";
  }
}

export function stringArrayContainsIgnoreCase(stringList: string[], str: string): boolean {
  return stringList && str ? stringList.findIndex((s) => s.toLowerCase() === str.toLowerCase()) != -1 : false;
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

export function pascalCase(name: string): string {
  if (name.length > 0) {
    return name[0].toUpperCase() + name.slice(1);
  } else {
    return name;
  }
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

export function getDurationFormat(encode: EncodeData): DurationSchema["format"] {
  let format: DurationSchema["format"] = "duration-rfc3339";
  // duration encoded as seconds
  if (encode.encoding === "seconds") {
    const scalarName = encode.type.name;
    if (scalarName.startsWith("int") || scalarName.startsWith("uint") || scalarName === "safeint") {
      format = "seconds-integer";
    } else if (scalarName.startsWith("float")) {
      format = "seconds-number";
    } else {
      throw new Error(`Unrecognized scalar type used by duration encoded as seconds: '${scalarName}'.`);
    }
  }
  return format;
}

export function hasScalarAsBase(type: Scalar, scalarName: IntrinsicScalarName): boolean {
  let scalarType: Scalar | undefined = type;
  while (scalarType) {
    if (scalarType.name === scalarName) {
      return true;
    }
    scalarType = scalarType.baseScalar;
  }
  return false;
}
