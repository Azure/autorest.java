Copy-Item -Path node_modules/@azure-tools/cadl-ranch-specs/http -Destination ./ -Recurse -Force

Remove-Item ./tsp-output -Recurse -Force

tsp compile ./http/type/union/main.tsp

Remove-Item -Path ./src/main/java/com/type/union/ -Recurse -Force -ErrorAction SilentlyContinue

Copy-Item -Path ./tsp-output/src/main/java/com/union/ -Destination ./src/main/java/com/union/ -Recurse -Force -Exclude @("ReadmeSamples.java", "module-info.java")

Remove-Item ./http -Recurse -Force