
Remove-Item ./tsp-output -Recurse -Force

tsp compile tsp/union.tsp

Remove-Item -Path ./src/main/java/com/cadl/union -Recurse -Force

Copy-Item -Path ./tsp-output/src/main/java/com/cadl/union -Destination ./src/main/java/com/cadl/ -Recurse -Force -Exclude @("ReadmeSamples.java", "module-info.java")

