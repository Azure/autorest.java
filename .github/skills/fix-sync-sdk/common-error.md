# Common errors and their fixes

## `duplicate-client-name`

1. Locate the model(s) that produce the duplicate client name error and add `@@clientName(<ref-to-model>, "<unique-name>", "java");` to one of them in "client.tsp" to give it a unique name.
