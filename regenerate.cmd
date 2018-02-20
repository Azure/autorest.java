@echo off

set autoRestSwaggerPrefix=node_modules/@microsoft.azure/autorest.testserver/swagger
set javaAzureArmFlag="--java.azure-arm=true"
set javaFluentFlag="--java.fluent=true"

call :AutoRest vanilla parameterflattening %autoRestSwaggerPrefix%/parameter-flattening.json
call :AutoRest vanilla bodyarray %autoRestSwaggerPrefix%/body-array.json
call :AutoRest vanilla bodyboolean %autoRestSwaggerPrefix%/body-boolean.json
call :AutoRest vanilla bodybyte %autoRestSwaggerPrefix%/body-byte.json
call :AutoRest vanilla bodycomplex %autoRestSwaggerPrefix%/body-complex.json
call :AutoRest vanilla bodydate %autoRestSwaggerPrefix%/body-date.json
call :AutoRest vanilla bodydatetime %autoRestSwaggerPrefix%/body-datetime.json
call :AutoRest vanilla bodydatetimerfc1123 %autoRestSwaggerPrefix%/body-datetime-rfc1123.json
call :AutoRest vanilla bodyduration %autoRestSwaggerPrefix%/body-duration.json
call :AutoRest vanilla bodydictionary %autoRestSwaggerPrefix%/body-dictionary.json
call :AutoRest vanilla bodyfile %autoRestSwaggerPrefix%/body-file.json
call :AutoRest vanilla bodyformdata %autoRestSwaggerPrefix%/body-formdata.json
call :AutoRest vanilla bodyinteger %autoRestSwaggerPrefix%/body-integer.json
call :AutoRest vanilla bodynumber %autoRestSwaggerPrefix%/body-number.json
call :AutoRest vanilla bodystring %autoRestSwaggerPrefix%/body-string.json
call :AutoRest vanilla header %autoRestSwaggerPrefix%/header.json
call :AutoRest vanilla http %autoRestSwaggerPrefix%/httpInfrastructure.json
call :AutoRest vanilla report %autoRestSwaggerPrefix%/report.json
call :AutoRest vanilla requiredoptional %autoRestSwaggerPrefix%/required-optional.json
call :AutoRest vanilla url %autoRestSwaggerPrefix%/url.json
call :AutoRest vanilla validation %autoRestSwaggerPrefix%/validation.json
call :AutoRest vanilla custombaseuri %autoRestSwaggerPrefix%/custom-baseUrl.json
call :AutoRest vanilla custombaseurimoreoptions %autoRestSwaggerPrefix%/custom-baseUrl-more-options.json
call :AutoRest vanilla modelflattening %autoRestSwaggerPrefix%/model-flattening.json
call :AutoRest azure lro %autoRestSwaggerPrefix%/lro.json %javaAzureArmFlag%
call :AutoRest azure paging %autoRestSwaggerPrefix%/paging.json %javaAzureArmFlag%
call :AutoRest azure azurereport %autoRestSwaggerPrefix%/azure-report.json %javaAzureArmFlag%
call :AutoRest azure azureparametergrouping %autoRestSwaggerPrefix%/azure-parameter-grouping.json %javaAzureArmFlag%
call :AutoRest azure azureresource %autoRestSwaggerPrefix%/azure-resource.json %javaAzureArmFlag%
call :AutoRest azure head %autoRestSwaggerPrefix%/head.json %javaAzureArmFlag%
call :AutoRest azure headexceptions %autoRestSwaggerPrefix%/head-exceptions.json %javaAzureArmFlag%
call :AutoRest azure subscriptionidapiversion %autoRestSwaggerPrefix%/subscriptionId-apiVersion.json %javaAzureArmFlag%
call :AutoRest azure azurespecials %autoRestSwaggerPrefix%/azure-special-properties.json %javaAzureArmFlag%
call :AutoRest azure custombaseuri %autoRestSwaggerPrefix%/custom-baseUrl.json %javaAzureArmFlag%
call :AutoRest azurefluent lro %autoRestSwaggerPrefix%/lro.json %javaAzureArmFlag% %javaFluentFlag%
call :AutoRest azurefluent paging %autoRestSwaggerPrefix%/paging.json %javaAzureArmFlag% %javaFluentFlag%
call :AutoRest azurefluent azurereport %autoRestSwaggerPrefix%/azure-report.json %javaAzureArmFlag% %javaFluentFlag%
call :AutoRest azurefluent azureparametergrouping %autoRestSwaggerPrefix%/azure-parameter-grouping.json %javaAzureArmFlag% %javaFluentFlag%
call :AutoRest azurefluent azureresource %autoRestSwaggerPrefix%/azure-resource.json %javaAzureArmFlag% %javaFluentFlag%
call :AutoRest azurefluent head %autoRestSwaggerPrefix%/head.json %javaAzureArmFlag% %javaFluentFlag%
call :AutoRest azurefluent headexceptions %autoRestSwaggerPrefix%/head-exceptions.json %javaAzureArmFlag% %javaFluentFlag%
call :AutoRest azurefluent subscriptionidapiversion %autoRestSwaggerPrefix%/subscriptionId-apiVersion.json %javaAzureArmFlag% %javaFluentFlag%
call :AutoRest azurefluent azurespecials %autoRestSwaggerPrefix%/azure-special-properties.json %javaAzureArmFlag% %javaFluentFlag%
call :AutoRest azurefluent custombaseuri %autoRestSwaggerPrefix%/custom-baseUrl.json %javaAzureArmFlag% %javaFluentFlag%
call :AutoRest nonnull bodybyte %autoRestSwaggerPrefix%/body-byte.json "--java.non-null-annotations=false"
call :AutoRest clienttypeprefix bodybyte %autoRestSwaggerPrefix%/body-byte.json "--java.client-type-prefix=zzz"
call :AutoRest generateclientinterfaces bodybyte %autoRestSwaggerPrefix%/body-byte.json "--java.generate-client-interfaces=false"
call :AutoRest implementationsubpackage bodybyte %autoRestSwaggerPrefix%/body-byte.json "--java.implementation-subpackage=spam"
call :AutoRest implementationsubpackage-empty bodybyte %autoRestSwaggerPrefix%/body-byte.json "--java.implementation-subpackage=''"
call :AutoRest modelssubpackage bodybyte %autoRestSwaggerPrefix%/body-byte.json "--java.models-subpackage=spam"
call :AutoRest modelssubpackage-empty bodybyte %autoRestSwaggerPrefix%/body-byte.json "--java.models-subpackage=''"
call :AutoRest xml xml test/swagger/xml-service.json "--enable-xml=true"
call :AutoRest javaversion-7 javaversion7 %autoRestSwaggerPrefix%/paging.json %javaAzureArmFlag% "--java.java-version=7"
call :AutoRest javaversion-8 javaversion8 %autoRestSwaggerPrefix%/paging.json %javaAzureArmFlag% "--java.java-version=8"
goto :EOF

:AutoRest
@echo on
rmdir /S /Q test\%~1\src\main\java\fixtures\%~2
call AutoRest --java --output-folder=test/%~1 --license-header=MICROSOFT_MIT_NO_VERSION --java.namespace=Fixtures.%~2 --input-file=%~3 --use=C:\Users\daschult\Sources\autorest.java %~4 %~5 %~6 %~7 %~8 %~9
@echo off
goto :EOF