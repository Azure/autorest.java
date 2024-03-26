Copy-Item -Path node_modules/@azure-tools/cadl-ranch-specs/http -Destination ./ -Recurse -Force

tsp compile ./http/type/model/inheritance/single-discriminator/main.tsp

Remove-Item -Path ./src/main/java/com/type/model/inheritance/singlediscriminator/ -Recurse -Force

Copy-Item -Path ./tsp-output/src/main/java/com/type/model/inheritance/singlediscriminator/ -Destination ./src/main/java/com/type/model/inheritance/singlediscriminator/ -Recurse -Force -Exclude @("ReadmeSamples.java", "module-info.java")

Remove-Item ./http -Recurse -Force