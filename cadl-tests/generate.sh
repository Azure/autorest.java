bash ./setup.sh

function cadl_compile {
    rm -rf cadl-output/java/src/

    curl $1 --output tmp.cadl
    cadl compile tmp.cadl

    cp -rf cadl-output/java/src/ .
    rm src/main/java/module-info.java

    rm -rf cadl-output/java/src/
    rm tmp.cadl
}

cadl_compile https://raw.githubusercontent.com/Azure/autorest.testserver/dpg_cadl/cadl/dpg-update1.cadl
