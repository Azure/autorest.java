import { Parameter, Schema } from "@autorest/codemodel";

export class LongRunningMetadata {
  longRunning: boolean = false;
  pollResultType?: Schema;
  finalResultType?: Schema;

  constructor(longRunning: boolean, pollResultType?: Schema, finalResultType?: Schema) {
    this.longRunning = longRunning;
    this.pollResultType = pollResultType;
    this.finalResultType = finalResultType;
  }
}

export class ClientContext {
  baseUri: string;
  hostParameters: Parameter[];

  constructor(baseUri: string, hostParameters: Parameter[]) {
    this.baseUri = baseUri;
    this.hostParameters = hostParameters;
  }
}
