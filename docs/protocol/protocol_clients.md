# Protocol Clients

## Introduction

## User interface

## ProxyMethod

## BinaryData

## RequestOptions

## Paging

## Long running operations
In progress. [PR](https://github.com/Azure/azure-sdk-for-java/pull/22795).

## Stream response
High level client provides four methods for a API that returns binary data. The return types are listed below.
```
InputStream getFile();
Flux<ByteBuffer> getFileAsync();
StreamResponse getFileWithResponse();
Mono<StreamResponse> getFileWithResponseAsync();
```
Protocol client does not introduce extra types to support stream response. It returns `BinaryData` like normal APIs. Stream is a built-in support in `BinaryData`. This feature is in progress. [PR](https://github.com/Azure/azure-sdk-for-java/pull/22829).

## Generating tests and examples
It is TODO work. It can save us much time to release SDKs.