Remove-Item ./src -Recurse -Force
Remove-Item ./tsp -Recurse -Force
Copy-Item -Path ../core/packages/http-client-java/generator/http-client-generator-test/src -Destination ./src -Recurse -Force
Copy-Item -Path ../core/packages/http-client-java/generator/http-client-generator-test/tsp -Destination ./tsp -Recurse -Force
