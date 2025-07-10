import { BrandedSdkEmitterOptions, UnbrandedSdkEmitterOptions } from "@azure-tools/typespec-client-generator-core";
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
  properties: {
    // service
    ...BrandedSdkEmitterOptions.namespace,
    "service-name": {
      type: "string",
      description:
        "Specify the human readable name of the service. The name will be used for classes like `<ServiceName>Manager` or `<ServiceName>ServiceVersion`",
      nullable: true,
    },

    // sample and test
    ...BrandedSdkEmitterOptions["examples-dir"],
    "generate-samples": {
      type: "boolean",
      description: "When set to `true`, the emitter will generate Java sample code. Default value is `true`.",
      nullable: true,
      default: true,
    },
    "generate-tests": {
      type: "boolean",
      description:
        "When set to `true`, the emitter will generate Java test code (mock test for management-plane SDK, disabled live test for data-plane SDK). Default value is `true`.",
      nullable: true,
      default: true,
    },

    "enable-sync-stack": {
      type: "boolean",
      description:
        "When set to `true`, the generated SDK uses synchronous REST API invocation. Default value is `true`. This option is to be deprecated.",
      nullable: true,
      default: true,
    },
    "stream-style-serialization": {
      type: "boolean",
      description:
        "When set to `true`, the generated SDK uses stream style serialization. Default value is `true`. This option is to be deprecated.",
      nullable: true,
      default: true,
    },
    "use-object-for-unknown": {
      type: "boolean",
      description:
        "When set to `true`, the emitter generates Java `Object` for TypeSpec `unknown`; otherwise, the emitter generates `BinaryData`. Default value is `false`. This option is for backward-compatibility.",
      nullable: true,
      default: false,
    },

    // customization
    ...UnbrandedSdkEmitterOptions["generate-protocol-methods"],
    ...UnbrandedSdkEmitterOptions["generate-convenience-methods"],
    "partial-update": {
      type: "boolean",
      description:
        "When set to `true`, the emitter will merge the generated code with existing code on `emitter-output-dir`, during post-process. Default value is `false`.",
      nullable: true,
      default: false,
    },
    "models-subpackage": {
      type: "string",
      description: "Specify the package name for model classes. Default value is `models`.",
      nullable: true,
    },
    "custom-types": {
      type: "string",
      description: "Specify the Java class names for custom model classes.",
      nullable: true,
    },
    "custom-types-subpackage": {
      type: "string",
      description: "Specify the package name for custom model classes.",
      nullable: true,
    },
    "customization-class": {
      type: "string",
      description:
        "Specify the Java class that to be executed by emitter for [code customization](https://github.com/Azure/autorest.java/blob/main/customization-base/README.md), during post-process.",
      nullable: true,
    },
    // "rename-model": {
    //   type: ["string", "object"],
    //   description:
    //     "Rename the model classes, in case they cannot be renamed via TCGC. E.g., anonymous models or templated models. Format should be in key-value form. This option is for management-plane SDK.",
    //   additionalProperties: true,
    //   nullable: true,
    // },
    // "add-inner": {
    //   type: ["string", "array"],
    //   description:
    //     "Generate the model as Inner classes. Format should be in array form. This option is for management-plane SDK.",
    //   items: { type: "string" },
    //   nullable: true,
    // },
    // "remove-inner": {
    //   type: ["string", "array"],
    //   description:
    //     "Generate the model not as Inner classes. Format should be in array form. This option is for management-plane SDK.",
    //   items: { type: "string" },
    //   nullable: true,
    // },
    // "preserve-model": {
    //   type: ["string", "array"],
    //   description:
    //     "Generate the model cleasses, even if it is not used by any API. Format should be in array form. This option is for management-plane SDK.",
    //   items: { type: "string" },
    //   nullable: true,
    // },
    // "generate-async-methods": {
    //   type: "boolean",
    //   description: "Generate async APIs in Clients. This option is for management-plane SDK.",
    //   nullable: true,
    // },
    // "resource-collection-associations": {
    //   type: "array",
    //   description:
    //     "Specify the associations of the resource to the colllection. Format should be in array form. This option is for management-plane SDK.",
    //   items: {
    //     type: "object",
    //     properties: {
    //       resource: {
    //         type: "string",
    //         description: "The name of the resource.",
    //       },
    //       collection: {
    //         type: "string",
    //         description: "The name of the collection to associate with.",
    //       },
    //     },
    //   },
    //   nullable: true,
    // },

    // configure
    "skip-special-headers": {
      type: "array",
      description: "Specify headers that emitter will ignore.",
      items: { type: "string" },
      nullable: true,
    },
    "enable-subclient": {
      type: "boolean",
      description:
        "When set to `true`, the generated SDK uses `getter` method to access child clients. Default value is `false`.",
      nullable: true,
      default: false,
    },

    // versioning
    ...UnbrandedSdkEmitterOptions["api-version"],
    "advanced-versioning": {
      type: "boolean",
      description:
        "When set to `true`, the emitter will take the history of api-versions in TypeSpec, and try generate SDK without breaking changes compared to SDK generated from prior api-versions. Default value is `false`. This is an experimental feature.",
      nullable: true,
      default: false,
    },
    "service-version-exclude-preview": {
      type: "boolean",
      description:
        "When set to `true`, the emitter will not include `##-preview` api-versions in ServiceVersion class. Default value is `false`. The option should be set to `true`, if the intended release is SDK of stable version.",
      nullable: true,
      default: false,
    },

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
        "profile": {
          type: "boolean",
          description: "Enable performance profiling.",
          nullable: true,
        },
      },
      nullable: true,
      additionalProperties: false,
      required: [],
    },
  },
  additionalProperties: true,
  required: [],
};
