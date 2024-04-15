import {
  DecoratedType,
  DecoratorApplication,
  DurationKnownEncoding,
  EncodeData,
  Enum,
  EnumMember,
  IntrinsicScalarName,
  Model,
  ModelProperty,
  Operation,
  Program,
  Scalar,
  StringLiteral,
  TemplatedTypeBase,
  Type,
  TypeNameOptions,
  Union,
  getTypeName,
  isNullType,
  isTemplateDeclaration,
  isTemplateInstance,
  isTypeSpecValueTypeOf,
} from "@typespec/compiler";
import { SchemaContext } from "@autorest/codemodel";
import { DurationSchema } from "./common/schemas/time.js";
import { getNamespace, pascalCase } from "./utils.js";
import { getUnionAsEnum } from "@azure-tools/typespec-azure-core";
import { SdkDurationType, SdkType } from "@azure-tools/typespec-client-generator-core";

/** Acts as a cache for processing inputs.
 *
 * If the input is undefined, the output is always undefined.
 * for a given input, the process is only ever called once.
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

export function isNullableType(type: Type): boolean {
  if (type.kind === "Union") {
    const nullVariants = Array.from(type.variants.values()).filter((it) => isNullType(it.type));
    return nullVariants.length >= 1;
  } else {
    return false;
  }
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

export function getDurationFormatFromSdkType(type: SdkDurationType): DurationSchema["format"] {
  let format: DurationSchema["format"] = "duration-rfc3339";
  // duration encoded as seconds
  if (type.encode === "seconds") {
    if (isSdkIntKind(type.wireType.kind)) {
      format = "seconds-integer";
    } else if (isSdkFloatKind(type.wireType.kind)) {
      format = "seconds-number";
    } else {
      throw new Error(`Unrecognized scalar type used by duration encoded as seconds: '${type.kind}'.`);
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

export function unionReferredByType(
  program: Program,
  type: Type,
  cache: Map<Type, Union | null | undefined>,
): Union | null {
  if (cache.has(type)) {
    const ret = cache.get(type);
    if (ret) {
      return ret;
    } else {
      return null;
    }
  }
  cache.set(type, undefined);

  if (type.kind === "Union") {
    // ref CodeModelBuilder.processUnionSchema
    const nonNullVariants = Array.from(type.variants.values()).filter((it) => !isNullType(it.type));
    if (nonNullVariants.length === 1) {
      // Type | null, follow that Type
      const ret = unionReferredByType(program, nonNullVariants[0], cache);
      if (ret) {
        cache.set(type, ret);
        return ret;
      }
    } else if (getUnionAsEnum(type)) {
      // "literal1" | "literal2" -> Enum
      cache.set(type, null);
      return null;
    } else {
      // found Union
      cache.set(type, type);
      return type;
    }
  } else if (type.kind === "Model") {
    if (type.indexer) {
      // follow indexer (for Array/Record)
      const ret = unionReferredByType(program, type.indexer.value, cache);
      if (ret) {
        cache.set(type, ret);
        return ret;
      }
    }
    // follow properties
    for (const property of type.properties.values()) {
      const ret = unionReferredByType(program, property.type, cache);
      if (ret) {
        cache.set(type, ret);
        return ret;
      }
    }
    cache.set(type, null);
    return null;
  }
  cache.set(type, null);
  return null;
}

export function getUnionDescription(union: Union, typeNameOptions: TypeNameOptions): string {
  let name = union.name;
  if (!name) {
    const names: string[] = [];
    union.variants.forEach((it) => {
      names.push(getTypeName(it.type, typeNameOptions));
    });
    name = names.join(" | ");
  }
  return name;
}

export function getNamePrefixForProperty(property: ModelProperty): string {
  if (property && property.model) {
    if (property.model.name) {
      return property.model.name;
    } else if (property.model.namespace) {
      for (const model of property.model.namespace.models.values()) {
        for (const prop of model.properties.values()) {
          const candidateModel = prop.type;
          // find the property that refers to the unnamed property.model
          if (
            candidateModel.kind === "Model" &&
            (candidateModel === property.model || candidateModel.indexer?.value === property.model)
          ) {
            return getNamePrefixForProperty(prop) + pascalCase(prop.name);
          } else if (
            candidateModel.kind === "Union" &&
            Array.from(candidateModel.variants.values()).find((it) => it.type === property.model)
          ) {
            return getNamePrefixForProperty(prop) + pascalCase(prop.name);
          }
        }
      }
    }
  }
  return "";
}

export function modelIs(model: Model, name: string, namespace: string): boolean {
  let currentModel: Model | undefined = model;
  while (currentModel) {
    if (currentModel.name === name && getNamespace(currentModel) === namespace) {
      return true;
    }
    currentModel = currentModel.sourceModel;
  }
  return false;
}

export function getAccess(type: Model | Operation | Enum | Union): string | undefined {
  return getDecoratorScopedValue(type, "$access", (it) => {
    const value = it.args[0].value;
    if (value.kind === "EnumMember") {
      return value.name;
    } else {
      return undefined;
    }
  });
}

export function isAllValueInteger(values: number[]): boolean {
  return values.every((it) => Number.isInteger(it));
}

export function getUsage(type: Model | Operation | Enum | Union): SchemaContext[] | undefined {
  return getDecoratorScopedValue(type, "$usage", (it) => {
    const value = it.args[0].value;
    const values: EnumMember[] = [];
    const ret: SchemaContext[] = [];
    if (value.kind === "EnumMember") {
      values.push(value);
    } else if (value.kind === "Union") {
      for (const v of value.variants.values()) {
        values.push(v.type as EnumMember);
      }
    } else {
      return undefined;
    }
    for (const v of values) {
      switch (v.name) {
        case "input":
          ret.push(SchemaContext.Input);
          break;
        case "output":
          ret.push(SchemaContext.Output);
          break;
      }
    }
    if (ret.length === 0) {
      return undefined;
    }
    return ret;
  });
}

/**
 * Check if a given model or model property is an ARM common type.
 * This is copied from typespec-azure-resource-manager. We don't want to depend on this package since it now has weird dependency on typespec-autorest.
 *
 * @param {Type} entity - The entity to be checked.
 *  @return {boolean} - A boolean value indicating whether an entity is an ARM common type.
 */
export function isArmCommonType(entity: Type): boolean {
  const commonDecorators = ["$armCommonDefinition", "$armCommonParameter"];
  if (isTypeSpecValueTypeOf(entity, ["Model", "ModelProperty"])) {
    return commonDecorators.some((commonDecorator) =>
      entity.decorators.some((d) => d.decorator.name === commonDecorator),
    );
  }
  return false;
}

function getDecoratorScopedValue<T>(
  type: DecoratedType,
  decorator: string,
  mapFunc: (d: DecoratorApplication) => T,
): T | undefined {
  let value = type.decorators
    .filter(
      (it) =>
        it.decorator.name === decorator && it.args.length == 2 && (it.args[1].value as StringLiteral).value === "java",
    )
    .map((it) => mapFunc(it))
    .find(() => true);
  if (value) {
    return value;
  }
  value = type.decorators
    .filter(
      (it) =>
        it.decorator.name === decorator &&
        it.args.length == 2 &&
        (it.args[1].value as StringLiteral).value === "client",
    )
    .map((it) => mapFunc(it))
    .find(() => true);
  if (value) {
    return value;
  }
  value = type.decorators
    .filter((it) => it.decorator.name === decorator && it.args.length == 1)
    .map((it) => mapFunc(it))
    .find(() => true);
  if (value) {
    return value;
  }
  return undefined;
}


export function isSdkIntKind(kind: string): boolean {
  return [
    "numeric",
    "integer",
    "safeint",
    "int8",
    "int16",
    "int32",
    "int64",
    "uint8",
    "uint16",
    "uint32",
    "uint64",
  ].includes(kind);
}

export function isSdkFloatKind(kind: string): boolean {
  return ["float", "float32", "float64", "decimal", "decimal128"].includes(kind);
}

export function isSdkStringKind(kind: string): boolean {
  return ["string", "password", "guid", "url", "uuid", "eTag", "armId", "ipAddress", "azureLocation"].includes(kind);
}
