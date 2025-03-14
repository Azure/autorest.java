import { JSONSchemaType } from "@typespec/compiler";

export const LIB_NAME = "@typespec/http-client-java";

export interface DevOptions {
  "generate-code-model"?: boolean;
  "debug"?: boolean;
  "loglevel"?: "off" | "debug" | "info" | "warn" | "error";
  "java-temp-dir"?: string; // working directory for java codegen, e.g. transformed code-model file
}

export interface EmitterOptions {
  "namespace"?: string;

  "flavor"?: string;

  "service-name"?: string;
  "service-versions"?: string[];

  "skip-special-headers"?: string[];

  "generate-samples"?: boolean;
  "generate-tests"?: boolean;

  "enable-sync-stack"?: boolean;
  "stream-style-serialization"?: boolean;
  "use-object-for-unknown"?: boolean;

  "partial-update"?: boolean;
  "models-subpackage"?: string;
  "custom-types"?: string;
  "custom-types-subpackage"?: string;
  "customization-class"?: string;
  "polling"?: any;

  "group-etag-headers"?: boolean;

  "enable-subclient"?: boolean;

  "advanced-versioning"?: boolean;
  "api-version"?: string;
  "service-version-exclude-preview"?: boolean;

  "dev-options"?: DevOptions;
}

const EmitterOptionsSchema: JSONSchemaType<EmitterOptions> = {
  type: "object",
  additionalProperties: true,
  properties: {
    "namespace": { type: "string", nullable: true },

    "flavor": { type: "string", nullable: true },

    // service
    "service-name": { type: "string", nullable: true },
    "service-versions": { type: "array", items: { type: "string" }, nullable: true },

    // header
    "skip-special-headers": { type: "array", items: { type: "string" }, nullable: true },

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
    "polling": { type: "object", additionalProperties: true, nullable: true },

    "group-etag-headers": { type: "boolean", nullable: true },

    "enable-subclient": { type: "boolean", nullable: true, default: false },

    "advanced-versioning": { type: "boolean", nullable: true, default: false },
    "api-version": { type: "string", nullable: true },
    "service-version-exclude-preview": { type: "boolean", nullable: true, default: false },

    "dev-options": { type: "object", additionalProperties: true, nullable: true },
  },
  required: [],
};
