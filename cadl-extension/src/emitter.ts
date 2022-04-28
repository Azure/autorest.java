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
  ModelTypeProperty,
  Program,
  resolvePath,
  Type,
  ModelType,
  ArrayType,
} from "@cadl-lang/compiler";
import {
  getAllRoutes,
  getContentTypes,
  getDiscriminator,
  HttpOperationParameter,
  HttpOperationParameters,
  HttpOperationResponse,
  HttpOperationResponseContent,
  OperationDetails,
  rest,
} from "@cadl-lang/rest";
import {
  getVersions,
} from "@cadl-lang/versioning";
import { 
  dump, 
  DEFAULT_SCHEMA, 
  Type as YamlType, 
} from "js-yaml";

let program: Program;
let namespace: string;

export async function $onEmit(program1: Program) {
  program = program1;
  const yamlMap = createClientModel();
  await program.host.writeFile(resolvePath(program.compilerOptions.outputPath || "", "./client-model.yaml"), dump(yamlMap, { schema }));
}

function createClientModel(): any {
  const serviceNamespace = getServiceNamespace(program);
  if (serviceNamespace === undefined) {
    throw Error("Can not emit yaml for a namespace that doesn't exist.");
  }

  let versions = getVersions(program, serviceNamespace);
  if (versions.length === 0 && getServiceVersion(program)) {
    versions = [getServiceVersion(program)];
  }
  namespace = getServiceNamespaceString(program)?.toLowerCase() || "client";
  // Get types
  const clientModel = {
    name: getServiceTitle(program),
    description: getDoc(program, serviceNamespace),
    serviceClient: createServiceClient(),
    models: Array.from(modelsMap.values())
  };
  return clientModel;
}

function createServiceClient(): any {
  const clientName = getServiceTitle(program).replace(/ /g, "");
  const httpPipelineParameter = {
    name: "httpPipeline",
    wireType: httpPipelineType
  };
  const serializerAdapterParameter = {
    name: "serializerAdapter",
    wireType: serializerAdapterType
  };

  const clientMethods = getAllRoutes(program).map(createClientMethod);

  const serviceClient = {
    packageName: namespace,
    clientBaseName: clientName,
    interfaceName: clientName + "Client",
    className: clientName + "Client",
    httpPipelineParameter: httpPipelineParameter,
    serializerAdapterParameter: serializerAdapterParameter,
    constructors: [
      {
        parameters: [
          httpPipelineParameter,
          serializerAdapterParameter
        ]
      }
    ],
    proxy: {
      name: clientName + "Service",
      clientTypeName: clientName + "Client",
      baseURL: "{$host}",
      methods: clientMethods.map(m => m.proxyMethod)
    },
    clientMethods: clientMethods
  };
  return serviceClient;
}

function createProxyMethod(operation: OperationDetails): any {
  const response = operation.responses[0];

  const method = {
    httpMethod: operation.verb.toUpperCase(),
    urlPath: operation.path,
    name: operation.operation.name,
    requestContentType: "application/json",
    returnType: getJavaType(response.responses[0].body?.type),
    parameters: operation.parameters.parameters.map(getParameter)
  }
  return method;
}

function createClientMethod(operation: OperationDetails): any {
  const response = operation.responses[0];
  const responseType = getJavaType(response.responses[0].body?.type);

  const method = {
    name: operation.operation.name + "AsyncWithResponse",
    description: getDoc(program, operation.operation),
    type: "SimpleAsyncRestResponse",
    returnValue: {
      type: responseType ? monoResponseType(responseType) : monoResponseType(voidType),
      description: response.responses[0].body?.type ? getDoc(program, response.responses[0].body?.type) : "completion",
    },
    parameters: operation.parameters.parameters.map(p => {
      return {
        name: p.name,
        wireType: getJavaType(p.param.type)
      };
    }),
    proxyMethod: createProxyMethod(operation)
  }
  return method;
}

const parametersMap = new Map<HttpOperationParameter, any>();

function getParameter(parameter: HttpOperationParameter): any {
  const cached = parametersMap.get(parameter);
  if (cached) {
    return cached;
  } else {
    const param = {
      name: parameter.name,
      requestParameterName: parameter.name,
      parameterReference: parameter.name,
      requestParameterLocation: parameter.type.toUpperCase(),
      wireType: getJavaType(parameter.param.type),
      clientType: getJavaType(parameter.param.type)
    };
    return param;
  }
}

interface JavaType {
}

class ClassType implements JavaType {
  packageName: string;
  name: string;

  constructor(packageName: string, name: string) {
    this.packageName = packageName;
    this.name = name;
  }
}

const voidType = new ClassType("java.lang", "Void");
const stringType = new ClassType("java.lang", "String");
const integerType = new ClassType("java.lang", "Integer");
const longType = new ClassType("java.lang", "Long");
const httpPipelineType = new ClassType("com.azure.core.http", "HttpPipeline");
const serializerAdapterType = new ClassType("com.azure.core.util.serializer", "SerializerAdapter");

class GenericType implements JavaType {
  packageName: string;
  name: string;
  typeArguments: JavaType[];

  constructor(packageName: string, name: string, typeArguments: JavaType[]) {
    this.packageName = packageName;
    this.name = name;
    this.typeArguments = typeArguments;
  }
}

const typesMap = new Map<Type, JavaType>();
const monoResponseTypesMap = new Map<JavaType, GenericType>();
const listTypesMap = new Map<JavaType, GenericType>();

function listType(javaType: JavaType): GenericType {
  const cached = listTypesMap.get(javaType);
  if (cached) {
    return cached;
  } else {
    const type = new GenericType("java.collection", "List", [javaType]);
    listTypesMap.set(javaType, type);
    return type;
  }
}

function monoResponseType(javaType: JavaType): GenericType {
  const cached = monoResponseTypesMap.get(javaType);
  if (cached) {
    return cached;
  } else {
    const type = new GenericType(
      "reactor.core.publisher", "Mono", 
      [new GenericType("com.azure.core.http.rest", "Response", [javaType])]
    );
    monoResponseTypesMap.set(javaType, type);
    return type;
  }
}

function getJavaType(type: Type | undefined): JavaType {
  if (type === undefined) {
    return voidType;
  } else {
    const cached = typesMap.get(type);
    if (cached) {
      return cached;
    }

    let javaType = null;
    switch (type.kind) {
      case "String":
        javaType = stringType;
        break;
      case "Number":
        javaType = integerType;
        break;
      case "Array":
        javaType = listType(getJavaType((type as ArrayType).elementType));
        break;
      case "Model":
        switch (type.name) {
          case "string":
            javaType = stringType;
            break;
          case "int32":
            javaType = integerType;
            break;
          case "int64":
            javaType = longType;
            break;
          default:
            javaType = getModelType(type);
            break;
        }
        break;
      default:
        throw Error("Not supported kind: " + type.kind);
    }

    typesMap.set(type, javaType);
    return javaType;
  }
}

const modelsMap = new Map<Type, any>();

function getModelType(type: ModelType): JavaType {
  const cached = modelsMap.get(type);
  if (cached) {
    return new ClassType(namespace, type.name);;
  } else {
    let typeName = type.name;
    if (type.templateArguments && type.templateArguments.length > 0) {
      typeName = typeName + type.templateArguments.map(t => (t as ModelType).name).join("");
      type.templateArguments.forEach(t => getModelType((t as ModelType)));
    }
    const model = {
      packageName: namespace,
      name: typeName,
      properties: Array.from(type.properties.entries()).map(([n, p]) => getProperty(n, p))
    };
    
    modelsMap.set(type, model);
    return new ClassType(namespace, typeName);
  }
}

const propertiesMap = new Map<ModelTypeProperty, any>();

function getProperty(name: string, property: ModelTypeProperty): any {
  const cached = propertiesMap.get(property);
  if (cached) {
    return cached;
  } else {
    const p = {
      name: property.name,
      serializedName: name,
      description: getDoc(program, property.type),
      wireType: getJavaType(property.type),
      clientType: getJavaType(property.type)
    };
    propertiesMap.set(property, p);
    return p;
  }
}

const schema = DEFAULT_SCHEMA.extend(
  [new YamlType("tag:yaml.org,2002:com.azure.autorest.model.clientmodel.ClassType", {
    kind: 'mapping',
    resolve: undefined,
    construct: undefined,
    predicate: (object: any) => object instanceof ClassType,
    represent: (object: any) => object
  }), 
  new YamlType("tag:yaml.org,2002:com.azure.autorest.model.clientmodel.GenericType", {
    kind: 'mapping',
    resolve: undefined,
    construct: undefined,
    predicate: (object: any) => object instanceof GenericType,
    represent: (object: any) => object
  })
]);
