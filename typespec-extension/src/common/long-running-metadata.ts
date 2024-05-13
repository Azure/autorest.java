import { Metadata, Schema } from "@autorest/codemodel";

export class LongRunningMetadata {
  longRunning: boolean = false;
  pollResultType?: Schema;
  finalResultType?: Schema;
  pollingStrategy?: Metadata;
  finalResultPropertyWireName?: string;

  constructor(
    longRunning: boolean,
    pollResultType?: Schema,
    finalResultType?: Schema,
    pollingStrategy?: Metadata,
    finalResultPropertyWireName?: string,
  ) {
    this.longRunning = longRunning;
    this.pollResultType = pollResultType;
    this.finalResultType = finalResultType;
    this.pollingStrategy = pollingStrategy;
    this.finalResultPropertyWireName = finalResultPropertyWireName;
  }
}
