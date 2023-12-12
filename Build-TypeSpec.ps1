mvn clean install -P local,tsp -DskipTests "-Djacoco.skip"

Push-Location ./typespec-extension/
try {
    npm ci
    npm run build
    npm run lint
    npm pack
}
finally {
    Pop-Location
}
