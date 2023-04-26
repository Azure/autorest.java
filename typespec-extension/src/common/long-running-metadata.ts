import { Metadata, Schema } from "@autorest/codemodel";

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
