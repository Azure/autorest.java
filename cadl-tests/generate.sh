bash ./setup.sh

function generate_remote {
    curl $1 --output tmp.cadl
    cadl compile tmp.cadl
    if [ $? -ne 0 ]; then
        exit 1
    fi

    cp -rf cadl-output/java/src/ .
    rm src/main/java/module-info.java

    rm -rf cadl-output/java/src/
    rm tmp.cadl
}

function generate {
    cadl compile $1
    if [ $? -ne 0 ]; then
        exit 1
    fi

    cp -rf cadl-output/java/src/ .
    rm src/main/java/module-info.java

    rm -rf cadl-output/
}

rm -rf src/main/
rm -rf cadl-output/

# testserver
generate_remote https://raw.githubusercontent.com/Azure/autorest.testserver/dpg_cadl/cadl/dpg-update1.cadl

# server
generate ./cadl/server.cadl

# model
generate ./cadl/builtin.cadl
generate ./cadl/polymorphism.cadl
