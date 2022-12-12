# Copyright (c) Microsoft Corporation. All rights reserved.
# Licensed under the MIT License.

export CADL_PATH=specification/cognitiveservices/OpenAI.Inference
export CADL_BRANCH=feature/cognitiveservices/openai-cadl

mkdir cadl_repo
cd cadl_repo
git init
git config core.sparseCheckout true
git remote add -f origin https://github.com/Azure/azure-rest-api-specs.git
echo $CADL_PATH > .git/info/sparse-checkout
git checkout $CADL_BRANCH
cd ..

cp -rf cadl_repo/$CADL_PATH cadl_src
rm -rf cadl_repo
