bash ./setup.sh

function generate_remote {
    echo "cadl compile $1"

    curl $1 --output tmp.cadl
    cadl compile tmp.cadl
    if [ $? -ne 0 ]; then
        exit 1
    fi

    cp -rf cadl-output/java/src .
    rm src/main/java/module-info.java

    rm -rf cadl-output/java/src
    rm tmp.cadl
}

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

# run local tests
for f in $(find ./cadl/ -name "*.cadl")
do
    generate $f
done

# run cadl ranch tests sources
for f in $(find ./node_modules/@azure-tools/cadl-ranch-specs/http -name "*.cadl")
do
  if [[ $(realpath $f) == *"models"* || $(realpath $f) == *"hello"* ]]; then
    generate $f
  fi
done