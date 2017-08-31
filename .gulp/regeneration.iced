
###############################################
# LEGACY 
# Instead: have bunch of configuration files sitting in a well-known spot, discover them, feed them to AutoRest, done.

regenExpected = (opts,done) ->
  outputDir = if !!opts.outputBaseDir then "#{opts.outputBaseDir}/#{opts.outputDir}" else opts.outputDir
  keys = Object.getOwnPropertyNames(opts.mappings)
  instances = keys.length

  for kkey in keys
    optsMappingsValue = opts.mappings[kkey]
    key = kkey.trim().toLowerCase()
    
    args = [
      "--java",
      "--output-folder=#{outputDir}/#{key}",
      "--license-header=#{if !!opts.header then opts.header else 'MICROSOFT_MIT_NO_VERSION'}",
      "--java.namespace=#{['Fixtures', key.replace(/\/|\./, '')].join('.')}",
      "--input-file=#{swaggerDir}/#{optsMappingsValue}"
    ]

    if (opts.azureArm)
      args.push("--java.azure-arm=true")

    if (opts.fluent)
      args.push("--java.fluent=true")

    autorest args,() =>
      instances--
      return done() if instances is 0 

defaultMappings = {
  'ParameterFlattening': 'parameter-flattening.json',
  'BodyArray': 'body-array.json',
  'BodyBoolean': 'body-boolean.json',
  'BodyByte': 'body-byte.json',
  'BodyComplex': 'body-complex.json',
  'BodyDate': 'body-date.json',
  'BodyDateTime': 'body-datetime.json',
  'BodyDateTimeRfc1123': 'body-datetime-rfc1123.json',
  'BodyDuration': 'body-duration.json',
  'BodyDictionary': 'body-dictionary.json',
  'BodyFile': 'body-file.json',
  'BodyFormData': 'body-formdata.json',
  'BodyInteger': 'body-integer.json',
  'BodyNumber': 'body-number.json',
  'BodyString': 'body-string.json',
  'Header': 'header.json',
  'Http': 'httpInfrastructure.json',
  'Report': 'report.json',
  'RequiredOptional': 'required-optional.json',
  'Url': 'url.json',
  'Validation': 'validation.json',
  'CustomBaseUri': 'custom-baseUrl.json',
  'CustomBaseUriMoreOptions': 'custom-baseUrl-more-options.json',
  'ModelFlattening': 'model-flattening.json'
}

defaultAzureMappings = {
  'Lro': 'lro.json',
  'Paging': 'paging.json',
  'AzureReport': 'azure-report.json',
  'AzureParameterGrouping': 'azure-parameter-grouping.json',
  'AzureResource': 'azure-resource.json',
  'Head': 'head.json',
  'HeadExceptions': 'head-exceptions.json',
  'SubscriptionIdApiVersion': 'subscriptionId-apiVersion.json',
  'AzureSpecials': 'azure-special-properties.json',
  'CustomBaseUri': 'custom-baseUrl.json'
}

swaggerDir = "node_modules/@microsoft.azure/autorest.testserver/swagger"

task 'regenerate-javaazure', '', (done) ->
  regenExpected {
    'outputBaseDir': 'test/azure',
    'mappings': defaultAzureMappings,
    'outputDir': 'src/main/java/fixtures',
    'azureArm': true
  },done
  return null

task 'regenerate-javaazurefluent', '', (done) ->
  regenExpected {
    'outputBaseDir': 'test/azurefluent',
    'mappings': defaultAzureMappings,
    'outputDir': 'src/main/java/fixtures',
    'azureArm': true,
    'fluent': true
  },done
  return null

task 'regenerate-java', '', (done) ->
  regenExpected {
    'outputBaseDir': 'test/vanilla',
    'mappings': defaultMappings,
    'outputDir': 'src/main/java/fixtures'
  },done
  return null

task 'regenerate', "regenerate expected code for tests", ['regenerate-java', 'regenerate-javaazure', 'regenerate-javaazurefluent'], (done) ->
  done();
