
Remove-Item ./tsp-output -Recurse -Force

tsp compile tsp/eunm.tsp

Remove-Item -Path ./src/main/java/com/cadl/enum -Recurse -Force -ErrorAction SilentlyContinue

Copy-Item -Path ./tsp-output/src/main/java/com/cadl/enumservice -Destination ./src/main/java/com/cadl/ -Recurse -Force -Exclude @("ReadmeSamples.java", "module-info.java")

