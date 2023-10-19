# Copyright (c) Microsoft Corporation. All rights reserved.
# Licensed under the MIT License.

export SPEC_REPO=https://github.com/Azure/azure-rest-api-specs.git
export TSP_PATH=specification/cognitiveservices/ContentSafety
export REPO_BRANCH=main

mkdir tsp-repo
cd tsp-repo
git init
git config core.sparseCheckout true
git remote add -f origin $SPEC_REPO
echo $TSP_PATH > .git/info/sparse-checkout
git checkout $REPO_BRANCH
cd ..

cp -rf tsp-repo/$TSP_PATH tsp-src
rm -rf tsp-repo
