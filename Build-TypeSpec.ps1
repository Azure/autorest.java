mvn clean install -P local,tsp -DskipTests "-Djacoco.skip"

Set-Location ./typespec-extension/
npm install
npm run build
npm run lint
npm pack
