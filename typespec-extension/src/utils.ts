import { Enum, Model, NoTarget, Operation, Program, Union } from "@typespec/compiler";

export function logWarning(program: Program, msg: string) {
  trace(program, msg);
  program.reportDiagnostic({
    code: "typespec-java",
    severity: "warning",
    message: msg,
    target: NoTarget,
  });
}

export function trace(program: Program, msg: string) {
  program.trace("typespec-java", msg);
}

export function pascalCase(name: string): string {
  if (name.length > 0) {
    return name[0].toUpperCase() + name.slice(1);
  } else {
    return name;
  }
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

export function stringArrayContainsIgnoreCase(stringList: string[], str: string): boolean {
  return stringList && str ? stringList.findIndex((s) => s.toLowerCase() === str.toLowerCase()) != -1 : false;
}
