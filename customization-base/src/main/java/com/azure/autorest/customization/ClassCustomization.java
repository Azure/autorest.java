// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.customization;

import com.azure.autorest.customization.implementation.Utils;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.InitializerDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.Statement;

import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * The class level customization for an AutoRest generated class.
 */
public final class ClassCustomization {
    private final CompilationUnit compilationUnit;
    private final TypeDeclaration<?> type;
    private final String packageName;
    private final String className;

    ClassCustomization(CompilationUnit compilationUnit, String packageName, String className) {
        this.compilationUnit = compilationUnit;
        Optional<ClassOrInterfaceDeclaration> clazz = compilationUnit.getClassByName(className);
        Optional<EnumDeclaration> enumDeclaration = compilationUnit.getEnumByName(className);

        if (clazz.isEmpty() && enumDeclaration.isEmpty()) {
            throw new IllegalArgumentException("Class " + className + " does not exist in package " + packageName);
        } else if (clazz.isPresent()) {
            this.type = clazz.get();
        } else {
            this.type = enumDeclaration.get();
        }

        this.className = className;
        this.packageName = packageName;
    }

    /**
     * Gets the name of the package that contains this class.
     *
     * @return The name of the package that contains this class.
     */
    public String getPackageName() {
        return packageName;
    }

    /**
     * Gets the name of the class this customization is using.
     *
     * @return The name of the class.
     */
    public String getClassName() {
        return className;
    }

    /**
     * Adds imports to the class.
     *
     * @param imports Imports to add.
     * @return A new {@link ClassCustomization} updated with the new imports for chaining.
     */
    public ClassCustomization addImports(String... imports) {
        Utils.addImports(compilationUnit, (imports != null) ? Arrays.asList(imports) : Collections.emptyList());
        return this;
    }

    /**
     * Adds a static block to the class. The {@code staticCodeBlock} should include the static keyword followed by
     * the static code.
     * @param staticCodeBlock The static code block including the static keyword.
     * @return The updated {@link ClassCustomization}.
     */
    public ClassCustomization addStaticBlock(String staticCodeBlock) {
        return addStaticBlock(staticCodeBlock, null);
    }

    /**
     * Adds a static block to the class.
     *
     * @param staticCodeBlock The static code block. If this is {@code null} or an empty string, the class is not
     * modified and the {@link ClassCustomization} instance is returned without any change.
     * @param importsToAdd The list of imports to add to the class.
     * @return The updated {@link ClassCustomization}.
     */
    public ClassCustomization addStaticBlock(String staticCodeBlock, List<String> importsToAdd) {
        if (staticCodeBlock == null || staticCodeBlock.isEmpty()) {
            return this;
        }

        Utils.addImports(compilationUnit, importsToAdd);
        InitializerDeclaration staticInitializer = type.getMembers().stream()
            .filter(BodyDeclaration::isInitializerDeclaration)
            .map(BodyDeclaration::asInitializerDeclaration)
            .filter(InitializerDeclaration::isStatic)
            .findFirst().orElse(null);

        if (staticCodeBlock.startsWith("static")) {
            staticCodeBlock = staticCodeBlock.substring("static".length()).trim();
        }

        NodeList<Statement> statements = Utils.parseCodeBlockOrStatement(staticCodeBlock);

        if (staticInitializer != null) {
            staticInitializer.getBody().getStatements().addAll(statements);
            return this;
        }

        // If adding a static initializer, add it after fields but before constructors or methods.
        int index = Utils.indexOfTypeDeclaration(type.getMembers(),
            declaration -> declaration.isConstructorDeclaration() || declaration.isMethodDeclaration());

        staticInitializer = new InitializerDeclaration(true, new BlockStmt(statements));
        if (index == -1) {
            type.getMembers().add(staticInitializer);
        } else {
            type.getMembers().add(index, staticInitializer);
        }

        return this;
    }

    /**
     * Gets the method level customization for a method in the class.
     *
     * @param methodNameOrSignature the method name or signature
     * @return the method level customization
     */
    public MethodCustomization getMethod(String methodNameOrSignature) {
        List<MethodDeclaration> methods;
        int parameterStart = methodNameOrSignature.indexOf("(");
        if (parameterStart != -1) {
            String methodName = methodNameOrSignature.substring(0, parameterStart).trim();

            // Don't need to worry about a trailing ')' as only the parameter types matter
            String[] methodParams = methodNameOrSignature.substring(parameterStart + 1).split(",");
            String[] methodParamTypes = Arrays.stream(methodParams)
                .map(String::trim)
                .map(param -> param.substring(0, param.indexOf(" ")))
                .toArray(String[]::new);

            methods = type.getMethodsBySignature(methodName, methodParamTypes);
        } else {
            methods = type.getMethodsByName(methodNameOrSignature.trim());
        }

        MethodDeclaration method = methods.stream().findFirst().orElseThrow(() -> new IllegalArgumentException(
                "Method " + methodNameOrSignature + " does not exist in class " + className));

        return new MethodCustomization(compilationUnit, method, packageName, className);
    }

    /**
     * Gets the constructor level customization for a constructor in the class.
     * <p>
     * If only the constructor name is passed and the class has multiple constructors an error will be thrown to prevent
     * ambiguous runtime behavior.
     *
     * @param constructorNameOrSignature The constructor name or signature.
     * @return The constructor level customization.
     * @throws IllegalStateException If only the constructor name is passed and the class has multiple constructors.
     */
    public ConstructorCustomization getConstructor(String constructorNameOrSignature) {
        ConstructorDeclaration constructor;
        int parameterStart = constructorNameOrSignature.indexOf("(");
        if (parameterStart != -1) {
            // Don't need to worry about a trailing ')' as only the parameter types matter
            String[] constructorParams = constructorNameOrSignature.substring(parameterStart + 1).split(",");
            String[] constructorParamTypes = Arrays.stream(constructorParams)
                .map(String::trim)
                .map(param -> param.substring(0, param.indexOf(" ")))
                .toArray(String[]::new);

            constructor = type.getConstructorByParameterTypes(constructorParamTypes).orElseThrow(() ->
                new IllegalArgumentException("Constructor " + constructorNameOrSignature + " does not exist in class "
                    + className));
        } else {
            if (type.getConstructors().size() > 1) {
                throw new IllegalStateException("Class " + className + " has multiple constructors. Please provide the "
                    + "constructor signature to avoid ambiguous behavior.");
            }

            constructor = type.getConstructors().get(0);
        }

        return new ConstructorCustomization(compilationUnit, constructor, packageName, className);
    }

    /**
     * Gets the property level customization for a property in the class.
     * <p>
     * For constant properties use {@link #getConstant(String)}.
     *
     * @param propertyName the property name
     * @return the property level customization
     */
    public PropertyCustomization getProperty(String propertyName) {
        FieldDeclaration property = type.getMembers().stream().filter(BodyDeclaration::isFieldDeclaration)
            .map(BodyDeclaration::asFieldDeclaration)
            .filter(fieldDeclaration -> fieldDeclaration.getVariable(0).getNameAsString().equals(propertyName))
            .findFirst().orElseThrow(() -> new IllegalArgumentException(
                "Property " + propertyName + " does not exist in " + "class " + className));

        return new PropertyCustomization(property, packageName, className, propertyName);
    }

    /**
     * Gets the constant level customization for a constant in the class.
     * <p>
     * For instance properties use {@link #getProperty(String)}.
     *
     * @param constantName The constant name.
     * @return The constant level customization.
     */
    public ConstantCustomization getConstant(String constantName) {
        FieldDeclaration constant = type.getMembers().stream().filter(BodyDeclaration::isFieldDeclaration)
            .map(BodyDeclaration::asFieldDeclaration)
            .filter(fieldDeclaration -> fieldDeclaration.getVariable(0).getNameAsString().equals(constantName))
            .filter(field -> field.isStatic() && field.isFinal())
            .findFirst().orElseThrow(() -> new IllegalArgumentException(
                "Constant " + constantName + " does not exist in " + "class " + className));

        return new ConstantCustomization(constant, packageName, className, constantName);
    }

    /**
     * Gets the Javadoc customization for this class.
     *
     * @return the Javadoc customization
     */
    public JavadocCustomization getJavadoc() {
        return new JavadocCustomization(type);
    }

    /**
     * Adds a constructor to this class.
     *
     * @param constructor The entire constructor as a literal string.
     * @return The constructor level customization for the added constructor.
     */
    public ConstructorCustomization addConstructor(String constructor) {
        return addConstructor(constructor, null);
    }

    /**
     * Adds a constructor to this class.
     *
     * @param constructor The entire constructor as a literal string.
     * @param importsToAdd Any additional imports required by the constructor. These will be custom types or types that
     * are ambiguous on which to use such as {@code List} or the utility class {@code Arrays}.
     * @return The constructor level customization for the added constructor.
     */
    public ConstructorCustomization addConstructor(String constructor, List<String> importsToAdd) {
        Utils.addImports(compilationUnit, importsToAdd);
        ConstructorDeclaration constructorDeclaration
            = (ConstructorDeclaration) StaticJavaParser.parseAnnotationBodyDeclaration(constructor);
        type.addMember(constructorDeclaration);

        return new ConstructorCustomization(compilationUnit, constructorDeclaration, packageName, className);
    }

    /**
     * Adds a method to this class.
     *
     * @param method The entire method as a literal string.
     * @return The method level customization for the added method.
     */
    public MethodCustomization addMethod(String method) {
        return addMethod(method, null);
    }

    /**
     * Adds a method to this class.
     *
     * @param method The entire method as a literal string.
     * @param importsToAdd Any additional imports required by the constructor. These will be custom types or types that
     * are ambiguous on which to use such as {@code List} or the utility class {@code Arrays}.
     * @return The method level customization for the added method.
     */
    public MethodCustomization addMethod(String method, List<String> importsToAdd) {
        Utils.addImports(compilationUnit, importsToAdd);
        MethodDeclaration methodDeclaration = StaticJavaParser.parseMethodDeclaration(method);
        type.addMember(methodDeclaration);

        return new MethodCustomization(compilationUnit, methodDeclaration, packageName, className);
    }

    /**
     * Removes a method from this class.
     * <p>
     * If there exists multiple methods with the same name or signature only the first one found will be removed.
     * <p>
     * This method doesn't update usages of the method being removed. If the method was used elsewhere those usages will
     * have to be updated or removed in another customization, or customizations.
     * <p>
     * If this removes the only method contained in the class this will result in a class with no methods.
     *
     * @param methodNameOrSignature The name or signature of the method being removed.
     * @return The current ClassCustomization.
     */
    public ClassCustomization removeMethod(String methodNameOrSignature) {
        getMethod(methodNameOrSignature).method.remove();
        return this;
    }

    /**
     * Replace the modifier for this class.
     * <p>
     * For compound modifiers such as {@code public abstract} use bitwise OR ({@code |}) of multiple Modifiers, {@code
     * Modifier.PUBLIC | Modifier.ABSTRACT}.
     * <p>
     * Pass {@code 0} for {@code modifiers} to indicate that the method has no modifiers.
     *
     * @param modifiers The {@link Modifier Modifiers} for the class.
     * @return The updated ClassCustomization object.
     * @throws IllegalArgumentException If the {@code modifier} is less than {@code 0} or any {@link Modifier} included
     * in the bitwise OR isn't a valid class {@link Modifier}.
     */
    public ClassCustomization setModifier(int modifiers) {
        Utils.setModifiers(type, modifiers, Modifier.classModifiers());
        return this;
    }

    /**
     * Add an annotation on the class. The annotation class will be automatically imported.
     *
     * @param annotation the annotation to add to the class. The leading @ can be omitted.
     * @return the current class customization for chaining
     */
    public ClassCustomization addAnnotation(String annotation) {
        Utils.addAnnotation(type, annotation);
        return this;
    }

    /**
     * Remove an annotation from the class.
     *
     * @param annotation the annotation to remove from the class. The leading @ can be omitted.
     * @return the current class customization for chaining
     */
    public ClassCustomization removeAnnotation(String annotation) {
        Utils.removeAnnotation(type, annotation);
        return this;
    }

    /**
     * Rename an enum member if the current class is an enum class.
     *
     * @param enumMemberName the current enum member name
     * @param newName the new enum member name
     * @return the current class customization for chaining
     */
    public ClassCustomization renameEnumMember(String enumMemberName, String newName) {
        if (type instanceof EnumDeclaration) {
            EnumDeclaration enumDeclaration = (EnumDeclaration) type;
            enumDeclaration.getEntries().stream()
                .filter(enumConstantDeclaration -> enumConstantDeclaration.getNameAsString().equals(enumMemberName))
                .findFirst()
                .ifPresent(enumConstantDeclaration -> enumConstantDeclaration.setName(newName));
        } else {
            type.getMembers().stream().filter(BodyDeclaration::isFieldDeclaration)
                .map(BodyDeclaration::asFieldDeclaration)
                .filter(field -> field.isPublic() && field.isStatic() && field.isFinal())
                .filter(field -> field.getVariable(0).getNameAsString().equals(enumMemberName))
                .findFirst()
                .ifPresent(field -> field.getVariable(0).setName(newName));
        }

        return this;
    }

    /**
     * Allows for a fully controlled modification of the abstract syntax tree that represents this class.
     *
     * @param astCustomization The abstract syntax tree customization callback.
     * @return A new ClassCustomization for this class with the abstract syntax tree changes applied.
     */
    public ClassCustomization customizeAst(Consumer<CompilationUnit> astCustomization) {
        astCustomization.accept(compilationUnit);

        return new ClassCustomization(compilationUnit, packageName, className);
    }
}
