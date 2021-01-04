# <img align="center" src="../images/logo.png">  Troubleshooting

## Error Handling

Our generated clients raise [exceptions defined in `com.azure.core.exception`][azure_core_exceptions]. While the base for all exceptions is [`AzureException`][azure_exception],
[`HttpResponseException`][http_response_exception] is also a common base catch-all for exceptions, as these errors are thrown in the case of a request being made, and a non-successful
status code being received from the service.


A very basic form of error handling looks like this

```java
package com.azure.pets;

import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.pets.models.Dog;

public static void main(String args[])
{
    PetsClient client = new PetsClientBuilder()
        .credential(new DefaultAzureCredentialBuilder().build())
        .buildClient();

    try {
        Dog dog = client.getDog();
    } catch (HttpResponseException e) {
        System.out.println(e.getMessage());
    }

}
```

You can also catch errors with more granularity, i.e. just catching a [`ResourceNotFoundException`][resource_not_found_exception].

```java
package com.azure.pets;

import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.pets.models.Dog;

public static void main(String args[])
{
    PetsClient client = new PetsClientBuilder()
        .credential(new DefaultAzureCredentialBuilder().build())
        .buildClient();

    try {
        Dog dog = client.getDog();
    } catch (ResourceNotFoundException e) {
        System.out.println(e.getMessage());
    }

}
```

## Logging

You can set the `AZURE_LOG_LEVEL` environment variable to view logging statements made in the client library. For example, setting `AZURE_LOG_LEVEL=LogLevel.ERROR` would show all error log messages. The log levels can be found here: [log levels][log_levels].

View our [logging wiki][logging_wiki] for more detailed instructions on enabling logging.

<!-- LINKS -->
[azure_core_exceptions]: https://docs.microsoft.com/java/api/com.azure.core.exception?view=azure-java-stable
[azure_exception]: https://docs.microsoft.com/java/api/com.azure.core.exception.azureexception?view=azure-java-stable
[http_response_exception]: https://docs.microsoft.com/java/api/com.azure.core.exception.httpresponseexception?view=azure-java-stable
[resource_not_found_exception]: https://docs.microsoft.com/java/api/com.azure.core.exception.resourcenotfoundexception?view=azure-java-stable
[log_levels]: https://docs.microsoft.com/java/api/com.azure.core.util.logging.loglevel?view=azure-java-stable
[logging_wiki]: https://github.com/Azure/azure-sdk-for-java/wiki/Logging-with-Azure-SDK