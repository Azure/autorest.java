bash ./setup.sh

function generate {
    echo "cadl compile $1"

    cadl compile $1
    if [ $? -ne 0 ]; then
        exit 1
    fi

    cp -rf cadl-output/java/src .
    rm src/main/java/module-info.java

    rm -rf cadl-output/
}

rm -rf src/main/
rm -rf cadl-output/

# enable convenience methods for tests
export GENERATE_MODELS=true
export GENERATE_CONVENIENCE_METHODS=true

# run local tests
for f in $(find ./cadl/ -name "*.cadl")
do
    generate $f
done

## run cadl ranch tests sources
#cp -rf node_modules/@azure-tools/cadl-ranch-specs/http .
#for f in $(find ./http -name "*.cadl")
#do
#  if [[ $(realpath $f) == *"models"* ]]; then
#    generate $f
#  fi
#done
#rm -rf http
