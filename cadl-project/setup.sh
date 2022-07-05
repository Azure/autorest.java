cd ../cadl-extension/
npm install
npm run build
npm pack
cd ../cadl-project/
rm -rf node_modules
rm package-lock.json
npm install
