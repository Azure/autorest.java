mvn package -P local,tsp -DskipTests

Set-Location ./typespec-extension/
npm install
npm run build
npm run lint
npm pack
