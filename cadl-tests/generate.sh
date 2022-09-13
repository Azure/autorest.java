bash ./setup.sh

function generate {
    echo "cadl compile $1"

    cadl compile $1
    if [ $? -ne 0 ]; then
        exit 1
    fi

    rm cadl-output/src/main/java/module-info.java
    rm -rf cadl-output/src/samples
    cp -rf cadl-output/src .

    rm -rf cadl-output
}

mkdir -p ./existingcode/src/main/java/com/cadl/
cp -rf ./src/main/java/com/cadl/partialupdate ./existingcode/src/main/java/com/cadl/partialupdate
rm -rf src/main
rm -rf cadl-output

# enable convenience methods for tests
export GENERATE_MODELS=true
export GENERATE_CONVENIENCE_METHODS=true
export PARTIAL_UPDATE=true

# run other local tests except partial update
for f in $(find ./cadl/ -name "*.cadl")
do
  if [[ $(realpath $f) != *"partialupdate"* ]]; then
    generate $f
  fi
done

# partial update test
cadl compile ./cadl/partialupdate.cadl --outputPath=./existingcode/
cp -rf ./existingcode/src/main/java/com/cadl/partialupdate ./src/main/java/com/cadl/partialupdate
rm -rf ./existingcode

# run cadl ranch tests sources
cp -rf node_modules/@azure-tools/cadl-ranch-specs/http .

export NAMESPACE=com.cadl.testserver.servicedriven1
generate ./http/resiliency/srv-driven-1/main.cadl

export NAMESPACE=com.cadl.testserver.servicedriven2
generate ./http/resiliency/srv-driven-2/main.cadl

unset NAMESPACE
for f in $(find ./http -name "*.cadl")
do
 if [[ $(realpath $f) != *"resiliency"* ]]; then
   generate $f
 fi
done
rm -rf http
