import {
  getDoc,
  getIntrinsicModelName,
  getServiceHost,
  getServiceNamespace,
  getServiceNamespaceString,
  getServiceTitle,
  getServiceVersion,
  isErrorModel,
  mapChildModels,
  ModelType,
  ModelTypeProperty,
  Program,
  resolvePath,
  Type,
} from "@cadl-lang/compiler";
import {
  getAllRoutes,
  getContentTypes,
  getDiscriminator,
  http,
  HttpOperationParameter,
  HttpOperationParameters,
  HttpOperationResponse,
  HttpOperationResponseContent,
  OperationDetails,
  rest,
} from "@cadl-lang/rest";
import { getAddedOn, 
  getRemovedOn, 
  getVersions,
  getVersionRecords,
} from "@cadl-lang/versioning";
import { dump } from "js-yaml";

const { getStatusCodeDescription } = http;

export async function $onEmit(program: Program) {
  const yamlMap = createYamlEmitter(program);
  await program.host.writeFile(resolvePath(program.compilerOptions.outputPath || "", "./code-model.yaml"), dump(yamlMap));
}

function createYamlEmitter(program: Program) {
  const serviceNamespace = getServiceNamespace(program);
  if (serviceNamespace === undefined) {
    throw Error("Can not emit yaml for a namespace that doesn't exist.");
  }

  let versions = getVersions(program, serviceNamespace);
  if (versions.length === 0 && getServiceVersion(program)) {
    versions = [getServiceVersion(program)];
  }
  // Get types
  const codeModel = {
    client: {
      name: getServiceTitle(program).replace(/ /g, ""),
      description: getDoc(program, serviceNamespace),
      parameters: [
        {
          optional: false,
          description: "Service host",
          clientName: "endpoint",
          clientDefaultValue: "https://" + getServiceHost(program),
          restApiName: "$host",
          location: "path",
          type: KnownTypes.string,
          implementation: "Client",
        },
      ],
      security: {},
      namespace: getServiceNamespaceString(program),
      url: "",
      apiVersions: versions,
    },
    operationGroups: emitOperationGroups(program),
    types: [...typesMap.values(), ...Object.values(KnownTypes)],
  };
  return codeModel;
}

const camelToSnakeCase = (str: string) =>
  str.replace(/[A-Z]/g, (letter) => `_${letter.toLowerCase()}`);

const typesMap = new Map<Type, Record<string, any>>();

function getType(program: Program, type: Type): any {
  const cached = typesMap.get(type);
  if (cached) {
    return cached;
  }
  const newValue = emitType(program, type);
  typesMap.set(type, newValue);
  return newValue;
}

function emitParamBase(program: Program, parameter: ModelTypeProperty): Record<string, any> {
  return {
    optional: parameter.optional,
    description: getDoc(program, parameter),
    addedApiVersion: getAddedOn(program, parameter),
    removedApiVersion: getRemovedOn(program, parameter),
    clientName: camelToSnakeCase(parameter.name),
  };
}

function emitRequestBody(
  program: Program,
  body: ModelTypeProperty,
  params: HttpOperationParameters
): Record<string, any> {
  const base = emitParamBase(program, body);
  const bodyMap: Record<string, any> = { contentTypeToType: {} };
  const contentTypeParam = params.parameters.find(
    (p) => p.type === "header" && p.name === "content-type"
  );
  const contentTypes = contentTypeParam
    ? getContentTypes(program, contentTypeParam.param)
    : ["application/json"];
  for (const contentType of contentTypes) {
    bodyMap.contentTypeToType[contentType] = getType(program, body.type);
  }
  return { ...base, ...bodyMap };
}

function emitParameter(
  program: Program,
  parameter: HttpOperationParameter,
  implementation: string
): Record<string, any> {
  const base = emitParamBase(program, parameter.param);
  const paramMap: Record<string, any> = {
    restApiName: parameter.name,
    location: parameter.type,
    type: getType(program, parameter.param.type),
    implementation: implementation,
    clientDefaultValue: undefined,
  };
  return { ...base, ...paramMap };
}

function emitResponse(
  program: Program,
  response: HttpOperationResponse,
  innerResponse: HttpOperationResponseContent
): Record<string, any> {
  const contentTypeToType: Record<string, any> = {};
  if (innerResponse.body) {
    for (const contentType of innerResponse.body.contentTypes) {
      contentTypeToType[contentType] = getType(program, innerResponse.body.type);
    }
  }

  return {
    headers: innerResponse.headers,
    statusCodes: [response.statusCode],
    isError: isErrorModel(program, response.type),
    addedApiVersion: getAddedOn(program, response.type),
    removedApiVersion: getRemovedOn(program, response.type),
    contentTypeToType: contentTypeToType,
  };
}

function emitOperation(program: Program, operation: OperationDetails): Record<string, any> {
  // Set up parameters for operation
  const parameters: Record<string, any>[] = [];
  for (const param of operation.parameters.parameters) {
    parameters.push(emitParameter(program, param, "method"));
  }

  // Set up responses for operation
  const responses: Record<string, any>[] = [];
  for (const response of operation.responses) {
    for (const innerResponse of response.responses) {
      responses.push(emitResponse(program, response, innerResponse));
    }
  }
  let requestBody: Record<string, any> | undefined;
  if (operation.parameters.body === undefined) {
    requestBody = undefined;
  } else {
    requestBody = emitRequestBody(program, operation.parameters.body, operation.parameters);
  }

  return {
    name: operation.operation.name,
    description: getDoc(program, operation.operation),
    url: operation.path,
    method: operation.verb,
    parameters: parameters,
    requestBody: requestBody,
    responses: responses,
    groupName: operation.groupName,
    addedApiVersion: getAddedOn(program, operation.operation),
    removedApiVersion: getRemovedOn(program, operation.operation),
    operationType: "basic",
  };
}

// Return any string literal values for type
function getStringValues(type: Type): string[] {
  if (type.kind === "String") {
    return [type.value];
  } else if (type.kind === "Union") {
    return type.options.flatMap(getStringValues).filter((v) => v);
  }
  return [];
}

function getDiscriminatorMapping(
  program: Program,
  discriminator: any,
  childModels: readonly ModelType[]
): Record<string, string> | undefined {
  const { propertyName } = discriminator;
  const getMapping = (t: ModelType): any => {
    const prop = t.properties?.get(propertyName);
    if (prop) {
      return getStringValues(prop.type).flatMap((v) => [{ [v]: getType(program, t) }]);
    }
    return undefined;
  };
  const mappings = childModels.flatMap(getMapping).filter((v) => v); // only defined values
  return mappings.length > 0 ? mappings.reduce((a, s) => ({ ...a, ...s }), {}) : undefined;
}

function emitProperty(program: Program, property: ModelTypeProperty): Record<string, any> {
  return {
    clientName: camelToSnakeCase(property.name),
    restApiName: property.name,
    type: getType(program, property.type),
    optional: property.optional,
    description: getDoc(program, property),
    addedApiVersion: getAddedOn(program, property),
    removedApiVersion: getRemovedOn(program, property),
  };
}

function emitModel(program: Program, type: ModelType): Record<string, any> {
  const name = getIntrinsicModelName(program, type);
  switch (name) {
    case "bytes":
      return { type: "base64" };
    case "int8":
    case "int16":
    case "int32":
    case "int64":
    case "safeint":
    case "uint8":
    case "uint16":
    case "uint32":
    case "uint64":
      return { type: "integer" };
    case "float32":
    case "float64":
      return { type: "float" };
    case "string":
      return KnownTypes.string;
    case "boolean":
      return { type: "boolean" };
    case "plainDate":
      return { type: "date" };
    case "zonedDateTime":
      return { type: "datetime" };
    case "plainTime":
      return { type: "time" };
    case "duration":
      return { type: "duration" };
    case "Map":
      const valType = type.properties.get("v")!;
      return { type: "dict", elementType: emitProperty(program, valType) };
    default:
      // Now we know it's a defined model
      const discriminator = getDiscriminator(program, type);
      const discriminatorEntry: Record<string, any> | undefined = {};
      if (discriminator) {
        const childModels = mapChildModels(program).get(type) ?? [];
        const discriminatorEntry: Record<string, any> = {};
        const discriminatorMapping = getDiscriminatorMapping(program, discriminator, childModels);
        if (discriminatorMapping) {
          discriminatorEntry.mapping = discriminatorMapping;
        }
        discriminatorEntry.propertyName = discriminator.propertyName;
      }
      const properties: Record<string, any>[] = [];
      for (const property of type.properties.values()) {
        properties.push(emitProperty(program, property));
      }
      return {
        type: "model",
        name: type.name,
        description: getDoc(program, type),
        baseModels: [type.baseModel],
        discriminatedSubtypes: {},
        properties: properties,
        addedApiVersion: getAddedOn(program, type),
        removedApiVersion: getRemovedOn(program, type),
      };
  }
}

function emitType(program: Program, type: Type): Record<string, any> {
  switch (type.kind) {
    case "Number":
      return { type: "number", constantValue: type.value };
    case "String":
      return { type: "string", constantValue: type.value };
    case "Boolean":
      return { type: "boolean", constantValue: type.value };
    case "Array":
      return { type: "list", elementType: getType(program, type.elementType) };
    case "Model":
      return emitModel(program, type);
    default:
      throw Error("Not supported");
  }
}

function emitOperationGroups(program: Program): Record<string, any>[] {
  const operationGroups: Record<string, any>[] = [];
  const allOperations = getAllRoutes(program);
  for (const operation of allOperations) {
    let existingOperationGroup: Record<string, any> | undefined = undefined;
    for (const operationGroup of operationGroups) {
      if (operationGroup["className"] === operation.groupName) {
        existingOperationGroup = operationGroup;
      }
    }
    const emittedOperation = emitOperation(program, operation);
    if (existingOperationGroup) {
      existingOperationGroup["operations"].push(emittedOperation);
    } else {
      const newOperationGroup = {
        propertyName: operation.groupName.toLowerCase(),
        className: operation.groupName,
        operations: [emittedOperation],
      };
      operationGroups.push(newOperationGroup);
    }
  }
  return operationGroups;
}

const KnownTypes = {
  string: { type: "string" },
};
