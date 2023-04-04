import {
  Enum,
  Model,
  ModelProperty,
  Operation,
  Program,
  TemplatedTypeBase,
  Type,
  Union,
  isTemplateDeclaration,
  isTemplateInstance,
} from "@typespec/compiler";
import { HttpOperation, getHeaderFieldName, getQueryParamName, getPathParamName, isStatusCode } from "@typespec/http";
import { ApiVersions } from "@autorest/codemodel";
import { Client as CodeModelClient, ServiceVersion } from "./common/client.js";
import { CodeModel } from "./common/code-model.js";

export const specialHeaderNames = new Set(["repeatability-request-id", "repeatability-first-sent"]);

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
