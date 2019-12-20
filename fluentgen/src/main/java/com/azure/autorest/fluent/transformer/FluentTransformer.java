package com.azure.autorest.fluent.transformer;

import com.azure.autorest.extension.base.model.codemodel.CodeModel;
import com.azure.autorest.extension.base.model.codemodel.Language;
import com.azure.autorest.extension.base.model.codemodel.Languages;
import com.azure.autorest.extension.base.model.codemodel.Parameter;
import com.azure.autorest.extension.base.model.codemodel.Protocol;
import com.azure.autorest.extension.base.model.codemodel.Protocols;
import com.azure.autorest.extension.base.model.codemodel.RequestParameterLocation;
import com.azure.autorest.extension.base.model.codemodel.StringSchema;

import java.util.ArrayList;
import java.util.List;

public class FluentTransformer {

    public CodeModel preTransform(CodeModel codeModel) {
        codeModel = addApiVersionParameter(codeModel);
        //codeModel = modifySubscriptionIdParameter(codeModel);
        return codeModel;
    }

    protected CodeModel addApiVersionParameter(CodeModel codeModel) {
        final Language language = new Language();
        language.setName("api-version");
        language.setDescription("The API version to use for this operation.");

        final StringSchema schema = new StringSchema();
        schema.setLanguage(new Languages());
        schema.getLanguage().setDefault(language);

        codeModel.getOperationGroups().stream().flatMap(og -> og.getOperations().stream()).forEach(o -> {
            final Parameter parameter = new Parameter();
            parameter.setSchema(schema);
            parameter.setImplementation(Parameter.ImplementationLocation.CLIENT);
            parameter.setRequired(true);
            parameter.setLanguage(new Languages());
            parameter.getLanguage().setDefault(language);
            parameter.setProtocol(new Protocols());
            parameter.getProtocol().setHttp(new Protocol());
            parameter.getProtocol().getHttp().setIn(RequestParameterLocation.Query);

            if (o.getApiVersions() != null && !o.getApiVersions().isEmpty()) {
                parameter.setClientDefaultValue(o.getApiVersions().get(0).getVersion());
            }

            final List<Parameter> parameters = new ArrayList<>(o.getRequest().getParameters());
            parameters.add(parameter);
            o.getRequest().setParameters(parameters);
        });

        return codeModel;
    }

    protected CodeModel modifySubscriptionIdParameter(CodeModel codeModel) {
        codeModel.getOperationGroups().forEach(g -> g.getOperations().forEach(o ->
        {
            if (o.getRequest().getParameters() != null) {
                o.getRequest().getParameters().forEach(p -> {
                    if ("subscriptionId".equals(p.getLanguage().getDefault().getName())) {
                        p.setImplementation(Parameter.ImplementationLocation.CLIENT);
                    }
                });
            };
        }));

        return codeModel;
    }
}
