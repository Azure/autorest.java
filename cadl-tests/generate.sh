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

export GENERATE_MODELS=true
export GENERATE_CONVENIENCE_METHODS=true

# testserver
generate_remote https://raw.githubusercontent.com/Azure/autorest.testserver/dpg_cadl/cadl/dpg-update1.cadl

for f in $(ls -1 ./cadl/*)
do
    generate $f
done
