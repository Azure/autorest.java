import { ApiVersions, Parameter } from "@autorest/codemodel";
import { Version } from "@typespec/versioning";

export class ClientContext {
  baseUri: string;
  hostParameters: Parameter[];
  globalParameters: Parameter[];
  apiVersions?: string[];

  constructor(baseUri: string, hostParameters: Parameter[], globalParameters: Parameter[], apiVersions?: ApiVersions) {
    this.baseUri = baseUri;
    this.hostParameters = hostParameters;
    this.globalParameters = globalParameters;
    this.apiVersions = apiVersions?.map((it) => it.version);
  }

  addGlobalParameter(parameter: Parameter) {
    if (!this.globalParameters.includes(parameter)) {
      this.globalParameters.push(parameter);
    }
  }

  getAddedVersions(versions: Version[]): string[] {
    // currently only allow one added version
    const addedVersions: string[] = [];
    const addedVersion = versions.shift()!.value;
    if (this.apiVersions) {
      let includeVersion = false;
      for (const version of this.apiVersions) {
        if (version === addedVersion) {
          includeVersion = true;
        }
        if (includeVersion) {
          addedVersions.push(version);
        }
      }
    }
    return addedVersions;
  }
}
