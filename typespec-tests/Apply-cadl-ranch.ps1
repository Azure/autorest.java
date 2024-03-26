Copy-Item -Path node_modules/@azure-tools/cadl-ranch-specs/http -Destination ./ -Recurse -Force

tsp compile ./http/type/model/inheritance/nested-discriminator/main.tsp

Remove-Item -Path ./src/main/java/com/type/model/inheritance/nesteddiscriminator/ -Recurse -Force

Copy-Item -Path ./tsp-output/src/main/java/com/type/model/inheritance/nesteddiscriminator/ -Destination ./src/main/java/com/type/model/inheritance/nesteddiscriminator/ -Recurse -Force -Exclude @("ReadmeSamples.java", "module-info.java")
