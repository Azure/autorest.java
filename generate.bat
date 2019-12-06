call autorest --java --use:.\ --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/body-boolean.json --namespace=fixtures.bodyboolean --output-folder=tests --sync-methods=all
call autorest --java --use:.\ --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/body-complex.json --namespace=fixtures.bodycomplex --output-folder=tests --sync-methods=all
call autorest --java --use:.\ --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/body-file.json --namespace=fixtures.bodyfile --output-folder=tests --sync-methods=all
call autorest --java --use:.\ --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/body-string.json --namespace=fixtures.bodystring --output-folder=tests --sync-methods=all
call autorest --java --use:.\ --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/custom-baseUrl.json --namespace=fixtures.custombaseuri --output-folder=tests --sync-methods=all
call autorest --java --use:.\ --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/header.json --namespace=fixtures.header --output-folder=tests --sync-methods=all
call autorest --java --use:.\ --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/body-dictionary.json --namespace=fixtures.bodydictionary --output-folder=tests --sync-methods=all
call autorest --java --use:.\ --input-file=https://raw.githubusercontent.com/Azure/autorest.testserver/master/swagger/body-duration.json --namespace=fixtures.bodyduration --output-folder=tests --sync-methods=all

