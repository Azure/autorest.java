import {
  resolvePath,
  getNormalizedAbsolutePath,
  Program,
  JSONSchemaType,
  createCadlLibrary,
} from "@cadl-lang/compiler";
import { dump } from "js-yaml";
import { promisify } from "util";
import { execFile } from "child_process";
import { promises } from "fs";
import { CodeModelBuilder } from "./code-model-builder.js";
import { dirname } from "path";
import { fileURLToPath } from "url";

export interface EmitterOptions {
  "namespace"?: string;
  "service-name"?: string;
  "partial-update"?: boolean;
  "output-dir"?: string;
  "service-versions"?: Array<string>;
}

const EmitterOptionsSchema: JSONSchemaType<EmitterOptions> = {
  type: "object",
  additionalProperties: true,
  properties: {
    "namespace": { type: "string", nullable: true },
    "service-name": { type: "string", nullable: true },
    "partial-update": { type: "boolean", nullable: true },
    "output-dir": { type: "string", nullable: true },
    "service-versions": { type: "array", items: { type: "string" }, nullable: true },
  },
  required: [],
};

export const $lib = createCadlLibrary({
  name: "JavaEmitter",
  diagnostics: {},
  emitter: {
    options: EmitterOptionsSchema,
  },
});

export async function $onEmit(program: Program, options: EmitterOptions) {
  const builder = new CodeModelBuilder(program);
  const codeModel = builder.build();

  if (!program.compilerOptions.noEmit && !program.hasError()) {
    const __dirname = dirname(fileURLToPath(import.meta.url));
    const moduleRoot = resolvePath(__dirname, "..", "..");

    const outputPath =
      options["output-dir"] ??
      program.compilerOptions.outputDir ??
      getNormalizedAbsolutePath("./cadl-output", undefined);
    options["output-dir"] = getNormalizedAbsolutePath(outputPath, undefined);

    const codeModelFileName = resolvePath(outputPath, "./code-model.yaml");

    await promises.mkdir(outputPath).catch((err) => {
      if (err.code !== "EISDIR" && err.code !== "EEXIST") {
        throw err;
      }
    });

    await program.host.writeFile(codeModelFileName, dump(codeModel));

    program.trace("cadl-java", `Code model file written to ${codeModelFileName}`);

    const emitterOptions = JSON.stringify(options);
    program.trace("cadl-java", `Emitter options ${emitterOptions}`);

    const jarFileName = resolvePath(moduleRoot, "target", "azure-cadl-extension-jar-with-dependencies.jar");
    program.trace("cadl-java", `Exec JAR ${jarFileName}`);

    const output = await promisify(execFile)("java", [
      `-DemitterOptions=${emitterOptions}`,
      "-jar",
      jarFileName,
      codeModelFileName,
    ]);
    program.trace("cadl-java", output.stdout ? output.stdout : output.stderr);
  }
}
