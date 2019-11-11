package com.azure.autorest.transformer;

import com.azure.autorest.extension.base.model.codemodel.AndSchema;
import com.azure.autorest.extension.base.model.codemodel.ChoiceSchema;
import com.azure.autorest.extension.base.model.codemodel.CodeModel;
import com.azure.autorest.extension.base.model.codemodel.Language;
import com.azure.autorest.extension.base.model.codemodel.ObjectSchema;
import com.azure.autorest.extension.base.model.codemodel.Schema;
import com.azure.autorest.extension.base.model.codemodel.Schemas;
import com.azure.autorest.extension.base.model.codemodel.SealedChoiceSchema;
import com.azure.autorest.util.CodeNamer;

public class Transformer {
    public CodeModel transform(CodeModel codeModel) {
        transformSchemas(codeModel.getSchemas());
        return codeModel;
    }

    private void transformSchemas(Schemas schemas) {
        for (ObjectSchema objectSchema : schemas.getObjects()) {
            nameSchema(objectSchema);
        }
        for (AndSchema andSchema : schemas.getAnds()) {
            nameSchema(andSchema);
        }
        for (ChoiceSchema choiceSchema: schemas.getChoices()) {
            nameSchema(choiceSchema);
        }
        for (SealedChoiceSchema sealedChoiceSchema : schemas.getSealedChoices()) {
            nameSchema(sealedChoiceSchema);
        }
    }

    private void nameSchema(Schema schema) {
        Language language = schema.getLanguage().getDefault();
        Language java = new Language();
        java.setName(CodeNamer.getTypeName(language.getName()));
        schema.getLanguage().setJava(java);
    }
}
