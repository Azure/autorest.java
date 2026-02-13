# Common error, and their fixes:

## `duplicate-client-name`

1. Find the model of duplicate client name, add `@@clientName(<ref-to-model>, "<unique-name>", "java");` in "client.tsp", to rename it to a unique name. 
