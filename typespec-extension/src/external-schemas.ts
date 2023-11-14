import { ArraySchema, ObjectSchema, Property, Schemas, StringSchema } from "@autorest/codemodel";

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
