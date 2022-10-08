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

export interface EmitterOptions {}

const EmitterOptionsSchema: JSONSchemaType<EmitterOptions> = {
  type: "object",
  additionalProperties: true,
  properties: {},
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
  const __dirname = dirname(fileURLToPath(import.meta.url));
  const moduleRoot = resolvePath(__dirname, "..", "..");

  const builder = new CodeModelBuilder(program);
  const codeModel = builder.build();

  const tempConfigurationFile = resolvePath("./java.json");
  let tempConfigurationFileAccessible = true;
  await promises.access(tempConfigurationFile).catch((_) => {
    tempConfigurationFileAccessible = false;
  });
  if (tempConfigurationFileAccessible) {
    const configurationContent = await program.host.readFile(tempConfigurationFile);
    const configuration = JSON.parse(configurationContent.text);

    program.logger.debug(`Configuration file (temporary) content: ${configurationContent.text}`);

    (codeModel as any).configuration = configuration;
  }

  const outputPath = program.compilerOptions.outputPath ?? getNormalizedAbsolutePath("./cadl-output", undefined);
  const codeModelFileName = resolvePath(outputPath, "./code-model.yaml");

  await promises.mkdir(outputPath).catch((err) => {
    if (err.code !== "EISDIR" && err.code !== "EEXIST") {
      throw err;
    }
  });

  await program.host.writeFile(codeModelFileName, dump(codeModel));

  program.logger.info(`Code model file written to ${codeModelFileName}`);

  const jarFileName = resolvePath(moduleRoot, "target", "azure-cadl-extension-jar-with-dependencies.jar");
  program.logger.info(`Exec JAR ${jarFileName}`);

  const javaOptions = [
    `-DemitterOptions=${JSON.stringify(options)}`,
    "-jar",
    jarFileName,
    codeModelFileName,
    getNormalizedAbsolutePath(outputPath, undefined),
  ];

  const output = await promisify(execFile)("java", javaOptions);
  program.logger.info(output.stdout ? output.stdout : output.stderr);
}
