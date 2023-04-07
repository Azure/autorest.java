# Copyright (c) Microsoft Corporation. All rights reserved.
# Licensed under the MIT License.

export SPEC_REPO=https://github.com/Azure/azure-rest-api-specs.git
export CADL_PATH=specification/cognitiveservices/AnomalyDetector
export CADL_BRANCH=main

mkdir tsp-repo
cd tsp-repo
git init
git config core.sparseCheckout true
git remote add -f origin $SPEC_REPO
echo $CADL_PATH > .git/info/sparse-checkout
git checkout $CADL_BRANCH
cd ..

cp -rf tsp-repo/$CADL_PATH tsp-src
rm -rf tsp-repo
