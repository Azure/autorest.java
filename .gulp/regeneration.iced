###############################################
# LEGACY
# Instead: have bunch of configuration files sitting in a well-known spot, discover them, feed them to AutoRest, done.

rimraf = require "rimraf"

regenExpected = (opts,done) ->
  keys = Object.getOwnPropertyNames(opts.mappings)
  instances = keys.length

  outputDir = opts.outputDir

  for kkey in keys
    optsMappingsValue = opts.mappings[kkey]
    key = kkey.trim().toLowerCase()

    namespace = key.replace(/\/|\./, '')

    args = [
      "--java",
      "--output-folder=#{outputDir}",
      "--license-header=#{if !!opts.header then opts.header else 'MICROSOFT_MIT_NO_VERSION'}",
      "--java.namespace=#{['Fixtures', namespace].join('.')}",
      "--input-file=#{swaggerDir}/#{optsMappingsValue}",
      "--sync-methods=all"
    ]
    if opts.extraArguments
      for extraArgument in opts.extraArguments
        args.push extraArgument

    if (opts.azureArm)
      args.push("--java.azure-arm=true")

    if (opts.fluent)
      args.push("--java.fluent=true")

    baseFolderPath = "#{outputDir}/src/main/java/fixtures/#{namespace}"
    rimraf.sync baseFolderPath

    autorest args, () ->
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
    'outputDir': 'test/azure',
    'mappings': defaultAzureMappings
    'azureArm': true
  },done
  return null

task 'regenerate-javaazurefluent', '', (done) ->
  regenExpected {
    'outputDir': 'test/azurefluent',
    'mappings': defaultAzureMappings
    'azureArm': true,
    'fluent': true
  },done
  return null

task 'regenerate-java', '', (done) ->
  regenExpected {
    'outputDir': 'test/vanilla',
    'mappings': defaultMappings
  },done
  return null

task 'regenerate-nonnull', '', (done) ->
  regenExpected {
    'outputDir': 'test/nonnull',
    'mappings': {
      'BodyByte': 'body-byte.json'
    },
    'extraArguments': [
      '--java.non-null-annotations=false',
    ]
  },done
  return null

task 'regenerate-clienttypeprefix', '', (done) ->
  regenExpected {
    'outputDir': 'test/clienttypeprefix',
    'mappings': {
      'BodyByte': 'body-byte.json'
    },
    'extraArguments': [
      '--java.client-type-prefix=zzz'
    ]
  },done
  return null

task 'regenerate-generateclientinterfaces', '', (done) ->
  regenExpected {
    'outputDir': 'test/generateclientinterfaces',
    'mappings': {
      'BodyByte': 'body-byte.json'
    },
    'extraArguments': [
      '--java.generate-client-interfaces=false'
    ]
  },done
  return null

task 'regenerate-implementationsubpackage', '', (done) ->
  regenExpected {
    'outputDir': 'test/implementationsubpackage',
    'mappings': {
      'BodyByte': 'body-byte.json'
    },
    'extraArguments': [
      '--java.implementation-subpackage=spam'
    ]
  },done
  return null

task 'regenerate-implementationsubpackage-empty', '', (done) ->
  regenExpected {
    'outputDir': 'test/implementationsubpackage-empty',
    'mappings': {
      'BodyByte': 'body-byte.json'
    },
    'extraArguments': [
      "--java.implementation-subpackage=''"
    ]
  },done

task 'regenerate-modelssubpackage', '', (done) ->
  regenExpected {
    'outputDir': 'test/modelssubpackage',
    'mappings': {
      'BodyByte': 'body-byte.json'
    },
    'extraArguments': [
      '--java.models-subpackage=spam'
    ]
  },done
  return null

task 'regenerate-modelssubpackage-empty', '', (done) ->
  regenExpected {
    'outputDir': 'test/modelssubpackage-empty',
    'mappings': {
      'BodyByte': 'body-byte.json'
    },
    'extraArguments': [
      "--java.models-subpackage=''"
    ]
  },done

task 'regenerate-requiredparameterclientmethods-false', '', (done) ->
  regenExpected {
    'outputDir': 'test/requiredparameterclientmethods-false',
    'mappings': {
      'RequiredOptional': 'required-optional.json',
    },
    'extraArguments': [
      "--java.required-parameter-client-methods=false"
    ]
  },done

task 'regenerate-xml', '', (done) ->
  outputDir = 'test/xml'

  namespace = 'xml'

  args = [
    "--java",
    "--output-folder=#{outputDir}",
    "--license-header=MICROSOFT_MIT_NO_VERSION",
    "--java.namespace=#{['Fixtures', namespace].join('.')}",
    "--input-file=test/swagger/xml-service.json",
    "--enable-xml=true"
  ]

  baseFolderPath = "#{outputDir}/src/main/java/fixtures/#{namespace}"
  rimraf.sync baseFolderPath

  autorest args, done
  return null

task 'regenerate-addcontextparameter', '', (done) ->
  regenExpected {
    'outputDir': 'test/addcontextparameter',
    'mappings': {
      'BodyByte': 'body-byte.json'
    },
    'extraArguments': [
      "--add-context-parameter=true"
    ]
  },done

regenerateTasks = [
  'regenerate-java',
  'regenerate-javaazure',
  'regenerate-javaazurefluent',
  'regenerate-nonnull',
  'regenerate-clienttypeprefix',
  'regenerate-generateclientinterfaces',
  'regenerate-implementationsubpackage',
  'regenerate-implementationsubpackage-empty',
  'regenerate-modelssubpackage',
  'regenerate-modelssubpackage-empty',
  'regenerate-requiredparameterclientmethods-false',
  'regenerate-xml',
  'regenerate-addcontextparameter'
]

task 'regenerate', "regenerate expected code for tests", regenerateTasks, (done) ->
  done();
