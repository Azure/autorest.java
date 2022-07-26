# re-build java-client-emitter
cd ../cadl-extension/
# rm -rf node_modules
# rm package-lock.json
npm install
npm run build
npm run lint
npm pack

# re-install
cd ../cadl-tests/
rm -rf node_modules
rm package-lock.json
npm install

# delete output
rm -rf cadl-output
