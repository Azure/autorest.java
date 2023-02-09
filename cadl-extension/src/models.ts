import { Schema } from "@autorest/codemodel";

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
