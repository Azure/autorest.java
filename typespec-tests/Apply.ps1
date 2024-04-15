
Remove-Item ./tsp-output -Recurse -Force

tsp compile tsp/naming.tsp

Remove-Item -Path ./src/main/java/com/cadl/naming -Recurse -Force -ErrorAction SilentlyContinue

Copy-Item -Path ./tsp-output/src/main/java/com/cadl/naming -Destination ./src/main/java/com/cadl/ -Recurse -Force -Exclude @("ReadmeSamples.java", "module-info.java")

