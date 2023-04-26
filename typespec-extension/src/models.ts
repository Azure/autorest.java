import { Parameter } from "@autorest/codemodel";

export class ClientContext {
  baseUri: string;
  hostParameters: Parameter[];
  globalParameters: Parameter[];

  constructor(baseUri: string, hostParameters: Parameter[], globalParameters: Parameter[]) {
    this.baseUri = baseUri;
    this.hostParameters = hostParameters;
    this.globalParameters = globalParameters;
  }

  addGlobalParameter(parameter: Parameter) {
    if (!this.globalParameters.includes(parameter)) {
      this.globalParameters.push(parameter);
    }
  }
}
