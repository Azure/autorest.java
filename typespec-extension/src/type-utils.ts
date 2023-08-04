import {
  EncodeData,
  IntrinsicScalarName,
  Model,
  Program,
  Scalar,
  TemplatedTypeBase,
  Type,
  Union,
  UnionVariant,
  isNullType,
  isTemplateDeclaration,
  isTemplateInstance,
} from "@typespec/compiler";
import { DurationSchema } from "./common/schemas/time";

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

export function isSameLiteralTypes(variants: UnionVariant[]): boolean {
  const kindSet = new Set(variants.map((it) => it.type.kind));
  if (kindSet.size === 1) {
    const kind = kindSet.values().next().value;
    return kind === "String" || kind === "Number" || kind === "Boolean";
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
    } else if (isSameLiteralTypes(nonNullVariants)) {
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
