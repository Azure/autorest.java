# Java Client Emitter for CADL

## Build JAR

`mvn package -P local,cadl -DskipTests` in project root.

This will build `./cadl-extension`, which is basically `preprocessor` and `javagen` combined.

## Build and install Emitter

`bash setup.sh` in `./cadl-project` folder.

It makes the npm package in `./cadl-extension`, then install it to `./cadl-project` folder.

## Generate code

`cadl compile <target.cadl>` in `./cadl-project` folder.

Generated code will be at `./cadl-project/cadl-ouput/java` folder
