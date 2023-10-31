mvn package -P local,tsp -DskipTests

Set-Location ./typespec-extension/
npm install --force
npm run build
npm run lint
npm pack
