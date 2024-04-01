
Remove-Item ./tsp-output -Recurse -Force

tsp compile tsp/lro.tsp

Remove-Item -Path ./src/main/java/com/cadl/longrunning -Recurse -Force -ErrorAction SilentlyContinue

Copy-Item -Path ./tsp-output/src/main/java/com/cadl/longrunning -Destination ./src/main/java/com/cadl/ -Recurse -Force -Exclude @("ReadmeSamples.java", "module-info.java")

