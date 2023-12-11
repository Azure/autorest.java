# Copyright (c) Microsoft Corporation. All rights reserved.
# Licensed under the MIT License.

export SPEC_REPO=https://github.com/bterlson/openai-in-typespec.git
export TSP_PATH=*
export REPO_BRANCH=main

mkdir tsp-repo
cd tsp-repo
git init
git config core.sparseCheckout true
git remote add -f origin $SPEC_REPO
echo $TSP_PATH > .git/info/sparse-checkout
git checkout $REPO_BRANCH
cd ..

if [ "$TSP_PATH" == "*" ]; then
  cp -rf tsp-repo tsp-src
else
  cp -rf tsp-repo/$TSP_PATH tsp-src
fi
rm -rf tsp-repo
