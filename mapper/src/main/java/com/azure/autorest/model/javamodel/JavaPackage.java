package com.azure.autorest.model.javamodel;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.ClientException;
import com.azure.autorest.model.clientmodel.ClientModel;
import com.azure.autorest.model.clientmodel.ClientResponse;
import com.azure.autorest.model.clientmodel.EnumType;
import com.azure.autorest.model.clientmodel.Manager;
import com.azure.autorest.model.clientmodel.MethodGroupClient;
import com.azure.autorest.model.clientmodel.PackageInfo;
import com.azure.autorest.model.clientmodel.PageDetails;
import com.azure.autorest.model.clientmodel.ServiceClient;
import com.azure.autorest.model.clientmodel.XmlSequenceWrapper;
import com.azure.autorest.template.Templates;

import java.util.ArrayList;

public class JavaPackage
{
    private JavaSettings settings;
    private ArrayList<JavaFile> javaFiles;
    private JavaFileFactory javaFileFactory;

    public JavaPackage()
    {
        this.settings = JavaSettings.getInstance();
        this.javaFiles = new ArrayList<JavaFile>();
        this.javaFileFactory = new JavaFileFactory(settings);
    }

    public final void AddManager(String package_Keyword, String name, Manager model)
    {
        JavaFile javaFile = javaFileFactory.CreateSourceFile(package_Keyword, name);
        Templates.getManagerTemplate().Write(model, javaFile);
        javaFiles.add(javaFile);
    }

    public final void AddServieClient(String package_Keyword, String name, ServiceClient model)
    {
        JavaFile javaFile = javaFileFactory.CreateSourceFile(package_Keyword, name);
        Templates.getServiceClientTemplate().Write(model, javaFile);
        javaFiles.add(javaFile);
    }

    public final void AddServiceClientInterface(String name, ServiceClient model)
    {
        JavaFile javaFile = javaFileFactory.CreateSourceFile(settings.getPackage(), name);
        Templates.getServiceClientInterfaceTemplate().Write(model, javaFile);
        javaFiles.add(javaFile);
    }

    public final void AddServieClientBuilder(String package_Keyword, String name, ServiceClient model)
    {
        JavaFile javaFile = javaFileFactory.CreateSourceFile(package_Keyword, name);
        Templates.getServiceClientBuilderTemplate().Write(model, javaFile);
        javaFiles.add(javaFile);
    }

    public final void AddMethodGroup(String package_Keyword, String name, MethodGroupClient model)
    {
        JavaFile javaFile = javaFileFactory.CreateSourceFile(package_Keyword, name);
        Templates.getMethodGroupTemplate().Write(model, javaFile);
        javaFiles.add(javaFile);
    }

    public final void AddMethodGroupInterface(String name, MethodGroupClient model)
    {
        JavaFile javaFile = javaFileFactory.CreateSourceFile(settings.getPackage(), name);
        Templates.getMethodGroupInterfaceTemplate().Write(model, javaFile);
        javaFiles.add(javaFile);
    }

    public final void AddModel(String package_Keyword, String name, ClientModel model)
    {
        JavaFile javaFile = javaFileFactory.CreateSourceFile(package_Keyword, name);
        Templates.getModelTemplate().Write(model, javaFile);
        javaFiles.add(javaFile);
    }

    public final void AddException(String package_Keyword, String name, ClientException model)
    {
        JavaFile javaFile = javaFileFactory.CreateSourceFile(package_Keyword, name);
        Templates.getExceptionTemplate().Write(model, javaFile);
        javaFiles.add(javaFile);
    }

    public final void AddEnum(String package_Keyword, String name, EnumType model)
    {
        JavaFile javaFile = javaFileFactory.CreateSourceFile(package_Keyword, name);
        Templates.getEnumTemplate().Write(model, javaFile);
        javaFiles.add(javaFile);
    }

    public final void AddPage(String package_Keyword, String name, PageDetails model)
    {
        JavaFile javaFile = javaFileFactory.CreateSourceFile(package_Keyword, name);
        Templates.getPageTemplate().Write(model, javaFile);
        javaFiles.add(javaFile);
    }

    public final void AddClientResponse(String package_Keyword, String name, ClientResponse model)
    {
        JavaFile javaFile = javaFileFactory.CreateSourceFile(package_Keyword, name);
        Templates.getResponseTemplate().Write(model, javaFile);
        javaFiles.add(javaFile);
    }

    public final void AddXmlSequenceWrapper(String package_Keyword, String name, XmlSequenceWrapper model)
    {
        JavaFile javaFile = javaFileFactory.CreateSourceFile(package_Keyword, name);
        Templates.getXmlSequenceWrapperTemplate().Write(model, javaFile);
        javaFiles.add(javaFile);
    }

    public final void AddPackageInfo(String package_Keyword, String name, PackageInfo model)
    {
        JavaFile javaFile = javaFileFactory.CreateEmptySourceFile(package_Keyword, name);
        Templates.getPackageInfoTemplate().Write(model, javaFile);
        javaFiles.add(javaFile);
    }

    // TODO: POM?
//    public final void AddPom(PomTemplate pomTemplate)
//    {
//        StringBuilder pomContentsBuilder = new StringBuilder();
//        try (pomTemplate.TextWriter = new StringWriter(pomContentsBuilder))
//        {
//            pomTemplate.ExecuteAsync().GetAwaiter().GetResult();
//        }
//        javaFiles.add(new JavaFile("pom.xml", pomContentsBuilder.toString()));
//    }
}