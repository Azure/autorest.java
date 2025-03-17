import { JSONSchemaType } from "@typespec/compiler";

export const LIB_NAME = "@azure-tools/typespec-java";

export interface DevOptions {
  "generate-code-model"?: boolean;
  "debug"?: boolean;
  "loglevel"?: "off" | "debug" | "info" | "warn" | "error";
  "java-temp-dir"?: string; // working directory for java codegen, e.g. transformed code-model file
}

// see EmitterOptionsDev in code-model-builder.ts for a full list of options
// but many of them is not recommended to be used, except for backward-compatible or some corner cases
export interface EmitterOptions {
  "namespace"?: string;
  "service-name"?: string;

  "generate-samples"?: boolean;
  "generate-tests"?: boolean;

  "partial-update"?: boolean;
  "models-subpackage"?: string;
  "custom-types"?: string;
  "custom-types-subpackage"?: string;
  "customization-class"?: string;

  "skip-special-headers"?: string[];
  "enable-subclient"?: boolean;

  "advanced-versioning"?: boolean;
  "api-version"?: string;
  "service-version-exclude-preview"?: boolean;

  "dev-options"?: DevOptions;
}

export const EmitterOptionsSchema: JSONSchemaType<EmitterOptions> = {
  type: "object",
  additionalProperties: true,
  properties: {
    // service
    "namespace": { type: "string", nullable: true },
    "service-name": { type: "string", nullable: true },

    // sample and test
    "generate-samples": { type: "boolean", nullable: true, default: true },
    "generate-tests": { type: "boolean", nullable: true, default: true },

    "enable-sync-stack": { type: "boolean", nullable: true, default: true },
    "stream-style-serialization": { type: "boolean", nullable: true, default: true },
    "use-object-for-unknown": { type: "boolean", nullable: true, default: false },

    // customization
    "partial-update": { type: "boolean", nullable: true, default: false },
    "models-subpackage": { type: "string", nullable: true },
    "custom-types": { type: "string", nullable: true },
    "custom-types-subpackage": { type: "string", nullable: true },
    "customization-class": { type: "string", nullable: true },

    // configure
    "skip-special-headers": { type: "array", items: { type: "string" }, nullable: true },
    "enable-subclient": { type: "boolean", nullable: true, default: false },

    // versioning
    "api-version": { type: "string", nullable: true },
    "advanced-versioning": { type: "boolean", nullable: true, default: false },
    "service-version-exclude-preview": { type: "boolean", nullable: true, default: false },

    // dev options
    "dev-options": {
      type: "object",
      description: "Developer options for http-client-java emitter.",
      properties: {
        "generate-code-model": {
          type: "boolean",
          description: "Generate intermittent 'code-model.yaml' file in output directory.",
          nullable: true,
        },
        "debug": {
          type: "boolean",
          description: "Enable Java remote debug on port 5005.",
          nullable: true,
        },
        "loglevel": {
          type: "string",
          description: "Log level for Java logging. Default is 'warn'.",
          nullable: true,
          enum: ["off", "debug", "info", "warn", "error"],
        },
        "java-temp-dir": {
          type: "string",
          description: "Temporary working directory for Java code generator.",
          nullable: true,
        },
      },
      nullable: true,
      additionalProperties: false,
      required: [],
    },
  },
  required: [],
};
