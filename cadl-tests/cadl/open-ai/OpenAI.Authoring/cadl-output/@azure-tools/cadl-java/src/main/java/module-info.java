module com.azure.openai {
    requires transitive com.azure.core;

    exports com.azure.openai;
    exports com.azure.openai.models;

    opens com.azure.openai.models to
            com.azure.core,
            com.fasterxml.jackson.databind;
}
