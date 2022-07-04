cd ../cadl-extension/
npm run build
cd ../cadl-project/
mkdir node_modules/\@cadl-lang/java-client-emitter/
tar -c --exclude node_modules -C ../cadl-extension/ . | tar -x -C node_modules/\@cadl-lang/java-client-emitter/

cadl compile confidentialledger.cadl
