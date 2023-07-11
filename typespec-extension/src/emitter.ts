import {
  createTypeSpecLibrary,
  EmitContext,
  getNormalizedAbsolutePath,
  JSONSchemaType,
  NoTarget,
  resolvePath,
} from "@typespec/compiler";
import { dump } from "js-yaml";
import { promisify } from "util";
import { spawn } from "child_process";
import { promises } from "fs";
import { CodeModelBuilder } from "./code-model-builder.js";
import { dirname } from "path";
import { fileURLToPath } from "url";

export interface EmitterOptions {
  "namespace"?: string;
  "output-dir"?: string;
  "partial-update"?: boolean;
  "service-name"?: string;
  "service-versions"?: string[];

  "skip-special-headers"?: string[];

  "namer"?: boolean;
  "generate-samples"?: boolean;
  "generate-tests"?: boolean;
  "enable-sync-stack"?: boolean;

  "custom-types"?: string;
  "custom-types-subpackage"?: string;
  "customization-class"?: string;

  "examples-directory"?: string;

  "dev-options"?: DevOptions;
}

export interface DevOptions {
  "generate-code-model"?: boolean;
  "support-versioning"?: boolean;
  "debug"?: boolean;
  "loglevel"?: "off" | "debug" | "info" | "warn" | "error";
}

const EmitterOptionsSchema: JSONSchemaType<EmitterOptions> = {
  type: "object",
  additionalProperties: true,
  properties: {
    "namespace": { type: "string", nullable: true },
    "output-dir": { type: "string", nullable: true },
    "partial-update": { type: "boolean", nullable: true, default: false },
    "service-name": { type: "string", nullable: true },
    "service-versions": { type: "array", items: { type: "string" }, nullable: true },

    "skip-special-headers": { type: "array", items: { type: "string" }, nullable: true },

    "namer": { type: "boolean", nullable: true, default: false },
    "generate-samples": { type: "boolean", nullable: true, default: true },
    "generate-tests": { type: "boolean", nullable: true, default: true },
    "enable-sync-stack": { type: "boolean", nullable: true, default: true },

    "custom-types": { type: "string", nullable: true },
    "custom-types-subpackage": { type: "string", nullable: true },
    "customization-class": { type: "string", nullable: true },

    "examples-directory": { type: "string", nullable: true },

    "dev-options": { type: "object", additionalProperties: true, nullable: true },
  },
  required: [],
};

export const $lib = createTypeSpecLibrary({
  name: "@azure-tools/typespec-java",
  diagnostics: {},
  emitter: {
    options: EmitterOptionsSchema,
  },
});

export async function $onEmit(context: EmitContext<EmitterOptions>) {
  const program = context.program;
  const options = context.options;
  const builder = new CodeModelBuilder(program, context);
  const codeModel = await builder.build();

  if (!program.compilerOptions.noEmit && !program.hasError()) {
    const __dirname = dirname(fileURLToPath(import.meta.url));
    const moduleRoot = resolvePath(__dirname, "..", "..");

    const outputPath = options["output-dir"] ?? context.emitterOutputDir;
    options["output-dir"] = getNormalizedAbsolutePath(outputPath, undefined);

    const codeModelFileName = resolvePath(outputPath, "./code-model.yaml");

    await promises.mkdir(outputPath, { recursive: true }).catch((err) => {
      if (err.code !== "EISDIR" && err.code !== "EEXIST") {
        throw err;
      }
    });

    await program.host.writeFile(codeModelFileName, dump(codeModel));

    program.trace("typespec-java", `Code model file written to ${codeModelFileName}`);

    const emitterOptions = JSON.stringify(options);
    program.trace("typespec-java", `Emitter options ${emitterOptions}`);

    const jarFileName = resolvePath(moduleRoot, "target", "azure-typespec-extension-jar-with-dependencies.jar");
    program.trace("typespec-java", `Exec JAR ${jarFileName}`);

    const javaArgs: string[] = [];
    javaArgs.push(`-DemitterOptions=${emitterOptions}`);
    if (options["dev-options"]?.debug) {
      javaArgs.push("-agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=*:5005");
    }
    if (options["dev-options"]?.loglevel) {
      javaArgs.push("-Dloglevel=" + options["dev-options"]?.loglevel);
    }
    javaArgs.push("-jar");
    javaArgs.push(jarFileName);
    javaArgs.push(codeModelFileName);
    try {
      await promisify(spawn)("java", javaArgs, { stdio: "inherit" });
    } catch (error: any) {
      if (error && "code" in error && error["code"] === "ENOENT") {
        const msg = "'java' is not on PATH. Please install JDK 11 or above.";
        program.trace("typespec-java", msg);
        program.reportDiagnostic({
          code: "typespec-java",
          severity: "error",
          message: msg,
          target: NoTarget,
        });
        throw new Error(msg);
      } else {
        throw error;
      }
    }

    if (!options["dev-options"]?.["generate-code-model"]) {
      await program.host.rm(codeModelFileName);
    }
  }
}
