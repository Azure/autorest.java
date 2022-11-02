import { DeepPartial } from "@azure-tools/codegen";
import { Aspect, OperationGroup, Security } from "@autorest/codemodel";

export interface Client extends Aspect {
  /** All operations  */
  operationGroups: Array<OperationGroup>;

  security: Security;
}

export class Client extends Aspect implements Client {
  constructor(name: string, description: string, objectInitializer?: DeepPartial<Client>) {
    super(name, description, objectInitializer);

    this.operationGroups = [];
    this.security = new Security(false);

    this.applyTo(this, objectInitializer);
  }
}
