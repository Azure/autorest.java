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
        d -> System.out.println(d.getName())
    );
}
```

## Paging Operations

A paging operation pages through lists of data, returning an iterator for the items. Network calls get made when users start iterating through the output, not when the operation
is initially called.

For our example, we will use the pageable operation generated from [this][example_swagger] swagger. Let's say we generated this swagger with namespace `com.azure.paging`.

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


<!-- LINKS -->
[operation_group_example]: https://github.com/Azure/azure-rest-api-specs/blob/master/specification/batch/data-plane/Microsoft.Batch/stable/2020-09-01.12.0/BatchService.json#L64
[mixin_example]: https://github.com/Azure/autorest/blob/master/docs/openapi/examples/pets.json#L20
[pets_swaggger]: https://github.com/Azure/autorest/blob/master/docs/openapi/examples/pets.json
[initializing]: ./initializing.md
[observable]: https://docs.oracle.com/javase/7/docs/api/java/util/Observable.html
[sync_poller]:  https://docs.microsoft.com/java/api/com.azure.core.util.polling.syncpoller?view=azure-java-stable
[example_swagger]: ../samples/specification/directives/pollingPaging.json
[poller_guidelines]: https://azure.github.io/azure-sdk/java_introduction.html#methods-invoking-long-running-operations
[poller_flux]: https://docs.microsoft.com/java/api/com.azure.core.util.polling.pollerflux?view=azure-java-stable
[mono]: https://projectreactor.io/docs/core/release/api/reactor/core/publisher/Mono.html
[paged_iterable]: https://docs.microsoft.com/java/api/com.azure.core.http.rest.pagediterable?view=azure-java-stable
[paged_flux]: https://docs.microsoft.com/java/api/com.azure.core.http.rest.pagedflux?view=azure-java-stable