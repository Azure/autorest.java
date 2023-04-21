import { Metadata, Parameter, Schema } from "@autorest/codemodel";

export class LongRunningMetadata {
  longRunning: boolean = false;
  pollResultType?: Schema;
  finalResultType?: Schema;
  pollingStrategy?: Metadata;

  constructor(longRunning: boolean, pollResultType?: Schema, finalResultType?: Schema, pollingStrategy?: Metadata) {
    this.longRunning = longRunning;
    this.pollResultType = pollResultType;
    this.finalResultType = finalResultType;
    this.pollingStrategy = pollingStrategy;
  }
}

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
