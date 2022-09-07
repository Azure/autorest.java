import { resolvePath, getNormalizedAbsolutePath, Program } from "@cadl-lang/compiler";
import { dump } from "js-yaml";
import { promisify } from "util";
import { execFile } from "child_process";
import { promises } from "fs";
import { CodeModelBuilder } from "./code-model-builder.js";

export async function $onEmit(program: Program) {
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

  const jarFile = "node_modules/@azure-tools/cadl-java/target/azure-cadl-extension-jar-with-dependencies.jar";
  program.logger.info(`Exec JAR ${jarFile}`);

  const output = await promisify(execFile)("java", [
    "-jar",
    jarFile,
    codeModelFileName,
    getNormalizedAbsolutePath(outputPath, undefined),
  ]);
  program.logger.info(output.stdout ? output.stdout : output.stderr);
}
