import { KnownMediaType } from "@azure-tools/codegen";

export enum SchemaContext {
  /** Schema is used as an input to an operation. */
  Input = "input",

  /** Schema is used as an output from an operation. */
  Output = "output",

  /** Schema is used as an exception from an operation. */
  Exception = "exception",

  /** Schema is used from the operation for generating convenience API. */
  ConvenienceApi = "convenience-api",

  /** Schema is used from the pageable operation. This usage does not propagate. */
  Paged = "paged",
}

export interface SchemaUsage {
  /** contexts in which the schema is used */
  usage?: SchemaContext[];

  /** Known media types in which this schema can be serialized */
  serializationFormats?: KnownMediaType[];
}
