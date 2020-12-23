# <img align="center" src="../images/logo.png">  Calling Operations with Your Java Client

AutoRest provides both synchronous and asynchronous method overloads for each service operation.
Depending on your swagger definition, operations can be accessed through operation groups (TODO: link to swagger docs) on the client,
or directly on the client.

## Operation Group vs No Operation Group

If your swagger defines an operation group for your operation (for example, in [this][operation_group_example] swagger, the operation `list`
is part of operation group `application`), you would access the operation through the operation group getter on the client, `getApplications()`.
This makes the call `client.getApplications().list()`.

If there's no operation group, as in [this][mixin_example] case, you would access the operation directly from the client
itself, i.e. `client.getDog()`.

## Regular Operations

### Sync Operations

We will be using the [example swagger][pets_swagger] in our main docs repo. After [initializing][initializing] our client, we
call our operation like this:

```java
package com.azure.pets;

import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.pets.models.Dog;

public static void main(String args[])
{
    PetsClient client = new PetsClientBuilder()
        .credential(new DefaultAzureCredentialBuilder().build())
        .buildClient();

    Dog dog = client.getDog();
}
```

### Async Operations

When calling our async operations, we use our async client, which is suffixed with `AsyncClient` instead of `Client`. Our async operations
return an [`Observable`][observable], so they need to be subscribed to. Following the [example above](#sync-operations Sync Operations),
our call to `getDog` looks like this:

```java
package com.azure.pets;

import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.pets.models.Dog;

public static void main(String args[])
{
    PetsAsyncClient client = new PetsClientBuilder()
        .credential(new DefaultAzureCredentialBuilder().build())
        .buildAsyncClient();

    client.getDog().subscribe(
        d -> dog = d
    );
}
```

## Long Running Operations

Long-running operations are operations which consist of an initial request sent to the service to start an operation, followed by polling the service at intervals to determine whether the operation has completed or failed, and if it has succeeded, to get the result.

In concurrence with our [java guidelines][poller_guidelines], all of our long running operations are prefixed with `begin`, to signify the starting of the long running operation.

For our example, we will use the long running operation generated from [this][example_swagger] swagger. Let's say we generated this swagger with namespace `com.azure.lro`.

### Sync Long Running Operations

Our sync long running operations return a [`SyncPoller`][lro_poller] polling object. Calling `.getFinalResult()` on this poller
waits for the operation to finish and returns the final result.

```java
package com.azure.lro;

import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.lro.models.Product;
import com.azure.lro.models.OperationResult;
import com.azure.core.util.polling.SyncPoller;

public static void main(String args[])
{
    PollingPagingExampleClient client = new PollingPagingExampleClientBuilder()
        .credential(new DefaultAzureCredentialBuilder().build())
        .buildClient();

    Product inputProduct = new Product(1, "My Polling Example");
    SyncPoller<OperationResult, Product> poller = client.beginBasicPolling(inputProduct);
    Product outputProduct = poller.getFinalResult();
}
```

### Async Long Running Operations

By default, our async long running operations return a [`PollerFlux`][poller_flux] polling object. This `PollerFlux` eventually returns a [`Mono`][mono]

```python
import asyncio
from azure.identity import DefaultAzureCredential
from azure.lro.aio import PollingPagingExampleClient
from azure.lro.models import Product

async def basic_polling():
    async with PollingPagingExampleClient(credential=DefaultAzureCredential()) as client:
        input_product = Product(id=1, name="My Polling Example")
        poller = await client.begin_basic_polling(product=input_product)
        output_product = await poller.result()

loop = asyncio.get_event_loop()
loop.run_until_complete(basic_polling())
loop.close()
```

```java
package com.azure.lro;

import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.lro.models.Product;
import com.azure.lro.models.OperationResult;
import reactor.core.publisher.Mono;
import com.azure.core.util.polling.PollerFlux;

public static void main(String args[])
{
    PollingPagingExampleAsyncClient client = new PollingPagingExampleClientBuilder()
        .credential(new DefaultAzureCredentialBuilder().build())
        .buildAsyncClient();

    Product inputProduct = new Product(1, "My Polling Example");

    PollerFlux<OperationResult, Product> poller = client.beginBasicPolling(inputProduct);
    Mono<Product> productMono =
        poller
            .last()
            .flatMap(pollResponse -> {
                if (pollResponse.getStatus().isComplete()) {
                    System.out.println("Polling completed successfully");
                    // completed successfully, retrieving final result.
                    return pollResponse.getFinalResult();
                } else {
                    return Mono.error(
                        new RuntimeException("Polling completed unsuccessfully with status:" + pollResponse.getStatus()));
                }
            });

    productMono.subscribe(
        p -> outputProduct = p
    );
}
```

## Paging Operations

A paging operation pages through lists of data, returning an iterator for the items. Network calls get made when users start iterating through the output, not when the operation
is initially called.

For our example, we will use the long running operation generated from [this][example_swagger] swagger. Let's say we generated this swagger with namespace `com.azure.paging`.

### Sync Paging Operations

Our sync paging operations return an [`PagedIterable`][paged_iterable] pager. The initial call to the function returns
the pager, but doesn't make any network calls. Instead, calls are made when users start iterating, with each network call returning a page of data.

```java
package com.azure.paging;

import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.core.util.paging.PagedIterable;

public static void main(String args[])
{
    PollingPagingExampleClient client = new PollingPagingExampleClientBuilder()
        .credential(new DefaultAzureCredentialBuilder().build())
        .buildClient();

    PagedIterable<Product> products = client.basicPaging();
    products.forEach(product -> {
        System.out.printf("Product has Id %.2f and Name %s", product.getId(), product.getName());
    });
}
```

### Async Paging Operations

Our sync paging operations return an [`PagedFlux`][paged_flux]. Since network calls aren't
made until starting to page, our generated operation is synchronous, and there's no need to wait the initial call to the function. Since network calls are made when iterating,
we have to do async looping.

```java
package com.azure.paging;

import com.azure.identity.DefaultAzureCredentialBuilder;

public static void main(String args[])
{
    PollingPagingExampleAsyncClient client = new PollingPagingExampleClientBuilder()
        .credential(new DefaultAzureCredentialBuilder().build())
        .buildAsyncClient();

    client.basicPaging().subscribe(product -> {
        System.out.printf("Product has Id %.2f and Name %s", product.getId(), product.getName());
    };
}
```


## Advanced: LRO + paging

We also support generating a long running paging operation. In this case, we return a poller from the operation, and the final result from the poller is
a pager that pages through the final lists of data.


<!-- LINKS -->
[operation_group_example]: https://github.com/Azure/azure-rest-api-specs/blob/master/specification/batch/data-plane/Microsoft.Batch/stable/2020-09-01.12.0/BatchService.json#L64
[mixin_example]: https://github.com/Azure/autorest/blob/new_docs/docs/openapi/examples/pets.json#L20
[pets_swaggger]: https://github.com/Azure/autorest/blob/new_docs/docs/openapi/examples/pets.json
[initializing]: ./initializing.md
[observable]: https://docs.oracle.com/javase/7/docs/api/java/util/Observable.html
[sync_poller]:  https://docs.microsoft.com/en-us/java/api/com.azure.core.util.polling.syncpoller?view=azure-java-stable
[example_swagger]: ../samples/specification/directives/pollingPaging.json
[poller_guidelines]: https://azure.github.io/azure-sdk/java_introduction.html#methods-invoking-long-running-operations
[poller_flux]: https://docs.microsoft.com/en-us/java/api/com.azure.core.util.polling.pollerflux?view=azure-java-stable
[mono]: https://projectreactor.io/docs/core/release/api/reactor/core/publisher/Mono.html
[paged_iterable]: https://docs.microsoft.com/en-us/java/api/com.azure.core.http.rest.pagediterable?view=azure-java-stable
[paged_flux]: https://docs.microsoft.com/en-us/java/api/com.azure.core.http.rest.pagedflux?view=azure-java-stable