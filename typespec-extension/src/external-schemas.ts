import { ArraySchema, BinarySchema, ObjectSchema, Property, Schema, Schemas, StringSchema } from "@autorest/codemodel";
import { KnownMediaType } from "@azure-tools/codegen";
import { getJavaNamespace, pascalCase } from "./utils.js";
import { SdkBodyModelPropertyType, SdkModelType, SdkType } from "@azure-tools/typespec-client-generator-core";

/*
 * These schema need to reflect
 * 1. wire schema via "serializedName"
 * 2. client schema in Java via "name"
 */
export function createResponseErrorSchema(schemas: Schemas, stringSchema: StringSchema): ObjectSchema {
  const responseErrorSchema = new ObjectSchema("Error", "Status details for long running operations", {
    language: {
      default: {
        namespace: "Azure.Core.Foundations",
      },
    },
  });
  schemas.add(responseErrorSchema);
  responseErrorSchema.addProperty(
    new Property("code", "the error code of this error.", stringSchema, {
      serializedName: "code",
      required: true,
      nullable: false,
      readOnly: true,
    }),
  );
  responseErrorSchema.addProperty(
    new Property("message", "the error message of this error.", stringSchema, {
      serializedName: "message",
      required: true,
      nullable: false,
      readOnly: true,
    }),
  );
  responseErrorSchema.addProperty(
    new Property("target", "the target of this error.", stringSchema, {
      serializedName: "target",
      required: false,
      nullable: true,
      readOnly: true,
    }),
  );
  const errorDetailsSchema = new ArraySchema("errorDetails", "the array of errors.", responseErrorSchema);
  responseErrorSchema.addProperty(
    new Property(
      "errorDetails",
      "a list of details about specific errors that led to this reported error.",
      errorDetailsSchema,
      {
        serializedName: "details",
        required: false,
        nullable: true,
        readOnly: true,
      },
    ),
  );
  return responseErrorSchema;
}

export function createPollOperationDetailsSchema(schemas: Schemas, stringSchema: StringSchema): ObjectSchema {
  const pollOperationDetailsSchema = new ObjectSchema(
    "PollOperationDetails",
    "Status details for long running operations",
    {
      language: {
        default: {
          namespace: "Azure.Core.Foundations",
        },
        java: {
          namespace: "com.azure.core.util.polling",
        },
      },
    },
  );
  schemas.add(pollOperationDetailsSchema);
  pollOperationDetailsSchema.addProperty(
    new Property("operationId", "The unique ID of the operation.", stringSchema, {
      serializedName: "id",
      required: true,
      nullable: false,
      readOnly: true,
    }),
  );
  pollOperationDetailsSchema.addProperty(
    new Property("status", "The status of the operation.", stringSchema, {
      serializedName: "status",
      required: true,
      nullable: false,
      readOnly: true,
    }),
  );
  const responseErrorSchema = createResponseErrorSchema(schemas, stringSchema);
  pollOperationDetailsSchema.addProperty(
    new Property("error", 'Error object that describes the error when status is "Failed".', responseErrorSchema, {
      serializedName: "error",
      required: false,
      nullable: true,
      readOnly: true,
      language: {
        java: {
          namespace: "com.azure.core.models",
        },
      },
    }),
  );
  return pollOperationDetailsSchema;
}

const fileDetailsMap: Map<string, ObjectSchema> = new Map();

function getFileSchemaName(baseName: string) {
  // make sure suffix "FileDetails"
  if (baseName.toLocaleLowerCase().endsWith("filedetails")) {
    return pascalCase(baseName);
  } else if (baseName.toLocaleLowerCase().endsWith("file")) {
    return pascalCase(baseName) + "Details";
  } else {
    return pascalCase(baseName) + "FileDetails";
  }
}

function createFileDetailsSchema(schemaName: string, propertyName: string, namespace: string, schemas: Schemas) {
  const fileDetailsSchema = new ObjectSchema(schemaName, 'The file details for the "' + propertyName + '" field.', {
    language: {
      default: {
        namespace: namespace,
      },
      java: {
        namespace: getJavaNamespace(namespace),
      },
    },
    serializationFormats: [KnownMediaType.Multipart],
  });
  fileDetailsSchema.serializationFormats;
  schemas.add(fileDetailsSchema);
  fileDetailsMap.set(schemaName, fileDetailsSchema);
  return fileDetailsSchema;
}

export function getFileDetailsSchema(
  property: SdkBodyModelPropertyType,
  namespace: string,
  schemas: Schemas,
  binarySchema: BinarySchema,
  stringSchema: StringSchema,
  processSchemaFunc: (type: SdkType) => Schema,
): ObjectSchema {
  if (property.type.kind === "model") {
    // property.type is File, use name and properties from property.type for the File schema
    /*
    Current logic:
    - Class name suffix "FileDetails"
    - No class hierachy for File
    - File has 3 properties: "content", "filename", "contentType" (Note that it is "contents" in TypeSpec)
    - No adjustment on "content" property, it is always BinaryData and required
    - Allow constant type for "filename" and "contentType" (to be discussed for other types e.g. enum)
    - Allow required for "filename" and "contentType"
     */
    const filePropertyName = property.name;
    const fileSchemaName = property.type.name;
    const schemaName = getFileSchemaName(fileSchemaName);
    let fileDetailsSchema = fileDetailsMap.get(schemaName);
    if (!fileDetailsSchema) {
      fileDetailsSchema = createFileDetailsSchema(schemaName, filePropertyName, namespace, schemas);
      fileDetailsMap.set(schemaName, fileDetailsSchema);
    }

    let contentTypeProperty;
    let filenameProperty;

    // find "filename" and "contentType" property in current model and its base models
    let type: SdkModelType | undefined = property.type;
    while (type !== undefined) {
      for (const property of type.properties) {
        if (!contentTypeProperty && property.name === "contentType") {
          contentTypeProperty = property;
        }
        if (!filenameProperty && property.name === "filename") {
          filenameProperty = property;
        }
      }
      type = type.baseModel;
    }

    fileDetailsSchema.addProperty(
      new Property("content", "The content of the file.", binarySchema, {
        required: true,
        nullable: false,
        readOnly: false,
      }),
    );
    fileDetailsSchema.addProperty(
      new Property(
        "filename",
        "The filename of the file.",
        filenameProperty?.type.kind === "constant" ? processSchemaFunc(filenameProperty.type) : stringSchema,
        {
          required: filenameProperty ? !filenameProperty.optional : false,
          nullable: false,
          readOnly: false,
        },
      ),
    );
    fileDetailsSchema.addProperty(
      new Property(
        "contentType",
        "The content-type of the file.",
        contentTypeProperty?.type.kind === "constant" ? processSchemaFunc(contentTypeProperty.type) : stringSchema,
        {
          required: contentTypeProperty ? !contentTypeProperty.optional : false,
          nullable: false,
          readOnly: false,
          clientDefaultValue: contentTypeProperty?.type.kind === "constant" ? undefined : "application/octet-stream",
        },
      ),
    );
    return fileDetailsSchema;
  } else {
    // property.type is bytes, create a File schema
    const filePropertyName = property.name;
    const schemaName = getFileSchemaName(filePropertyName);
    let fileDetailsSchema = fileDetailsMap.get(schemaName);
    if (!fileDetailsSchema) {
      fileDetailsSchema = createFileDetailsSchema(schemaName, filePropertyName, namespace, schemas);
      fileDetailsMap.set(schemaName, fileDetailsSchema);

      fileDetailsSchema.addProperty(
        new Property("content", "The content of the file.", binarySchema, {
          required: true,
          nullable: false,
          readOnly: false,
        }),
      );
      fileDetailsSchema.addProperty(
        new Property("filename", "The filename of the file.", stringSchema, {
          required: false,
          nullable: false,
          readOnly: false,
        }),
      );
      fileDetailsSchema.addProperty(
        new Property("contentType", "The content-type of the file.", stringSchema, {
          required: false,
          nullable: false,
          readOnly: false,
          clientDefaultValue: "application/octet-stream",
        }),
      );
    }
    return fileDetailsSchema;
  }
}
