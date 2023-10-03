// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.partialupdate.util;

import com.github.javaparser.JavaToken;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.CallableDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.ast.comments.JavadocComment;
import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.ast.modules.ModuleDeclaration;
import com.github.javaparser.ast.modules.ModuleDirective;
import com.github.javaparser.ast.nodeTypes.NodeWithSimpleName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Partial update handler. It can handle partial update for .java class files.
 *
 * <p>Below partial update use cases are supported:
 *
 * <ul>
 * <li>Manually add class member (field / method / constructor) -&gt; keep the added member
 * <li>Manually update method signature, e.g. parameter change, method access level change -&gt; keep the manual changed signature, and not generate the corresponding method with the same method name
 * <li>Manually remove one class member -&gt; if the member's definition is in swagger, this member will be auto generated again
 * <li>Swagger add a new api -&gt; add the new api to generated file
 * <li>Swagger update an existing api -&gt; if the api is auto generated, then the existing generated member will be replaced to the new one. If it is manual updated, we will keep the manual updated member.
 * <li>Swagger delete an existing api -&gt; if the existing api is auto generated, then it should be removed. If it is manual updated, we will keep the manual updated member.
 * </ul>
 */
public class PartialUpdateHandler {

    /**
     * Handle partial update by comparing generatedFileContent and existingFileContent. It supports handling partial update for class or interface file, and module-info.java file
     * When handling partial update for each interface or class file, it will compare existing file which has manual update and generated file which is generated by autorest. It keeps the manual update members and replace generated members with the newly generated one.
     * When handling partial update for each module-info.java file, for simplicity, currently it will always use existing file if existing file is considered as manually modified.
     *
     * <p>Handle partial update steps:
     * <ul>
     *  <li>Parse existing file content and generated file content using JavaParser
     *  <li>If the file is module-info.java, then handle it by simply compare the difference between existing file and generated file
     *  <li>If the file is class or interface file, handle partial update for it
     *  <li>Otherwise, we just return the generatedFileContent directly
     * </ul>
     *
     * @param generatedFileContent the newly generated file content
     * @param existingFileContent  the existing file content that contains user's manual update code
     * @return the file content after handling partial update
     */
    public static String handlePartialUpdateForFile(String generatedFileContent, String existingFileContent) {
        // 1. Parse existing file content and generated file content using JavaParser
        CompilationUnit compilationUnitForGeneratedFile = StaticJavaParser.parse(generatedFileContent);
        CompilationUnit compilationUnitForExistingFile = StaticJavaParser.parse(existingFileContent);

        // 2. If it's module-info.java file, then go to handlePartialUpdateForModuleInfoFile
        if (compilationUnitForExistingFile.getModule().isPresent() &&
                compilationUnitForGeneratedFile.getModule().isPresent()) {
            return handlePartialUpdateForModuleInfoFile(compilationUnitForGeneratedFile, compilationUnitForExistingFile);
        }

        // 3. If it's package-info.java file, then go to handlePartialUpdateForPackageInfoFile
        if (isPackageInfoFile(compilationUnitForExistingFile)
            && isPackageInfoFile(compilationUnitForGeneratedFile)) {
            return handlePartialUpdateForPackageInfoFile(compilationUnitForGeneratedFile, compilationUnitForExistingFile);
        }

        // 4. If it's class or interface file, handle partial update for class or interface file
        if (isClassOrInterfaceFile(compilationUnitForExistingFile) &&
                isClassOrInterfaceFile(compilationUnitForGeneratedFile)) {
            return handlePartialUpdateForClassOrInterfaceFile(compilationUnitForGeneratedFile, generatedFileContent,
                    compilationUnitForExistingFile);
        }

        return generatedFileContent;
    }

    /**
     * <p>Handle partial update for class or interface file steps:
     * <ul>
     *  <li>Parse existing file content and generated file content using JavaParser
     *  <li>Get class members for existing file and generated file
     *  <li>Check if the file is in scope of partial update by iterate the members in generated file to see if there is a method has {@code @Generated} annotation. If it has {@code @Generated} annotation, then the file is in scope of partial update, otherwise return generated file content directly.
     *  <li>Iterate existing file members, keep manual updated members, and replace generated members with the corresponding newly generated one. Here we will not do the replace on the existing file member list,  we just create a new member list {@code updatedMembersList} and put in those manually update members and newly generated members.
     *  <li>Add remaining newly generated members to {@code updatedMembersList}
     *  <li>Update generated file members with {@code updatedMembersList}
     *  <li>Update generated file imports
     * </ul>
     *
     * @param compilationUnitForGeneratedFile the newly generated file content
     * @param generatedFileContent the newly generated file content
     * @param compilationUnitForExistingFile  the existing file content that contains user's manual update code
     * @return the file content after handling partial update
     */
    private static String handlePartialUpdateForClassOrInterfaceFile(CompilationUnit compilationUnitForGeneratedFile,
                                                                     String generatedFileContent,
                                                                     CompilationUnit compilationUnitForExistingFile) {
        // 1. Parse existing file content and generated file content using JavaParser
        ClassOrInterfaceDeclaration generatedClazz = getClassOrInterfaceDeclaration(compilationUnitForGeneratedFile);
        ClassOrInterfaceDeclaration existingClazz = getClassOrInterfaceDeclaration(compilationUnitForExistingFile);

        // 2. Get class members for existing file and generated file
        List<BodyDeclaration<?>> generatedFileMembers = new ArrayList<>();
        if (generatedClazz != null) {
            generatedFileMembers = generatedClazz.getMembers();
        }
        List<BodyDeclaration<?>> existingFileMembers = new ArrayList<>();
        if (existingClazz != null) {
            existingFileMembers = existingClazz.getMembers();
        }

        // 3. Verify Generated File, will throw error if there is invalid part found.
        validateGeneratedClassOrInterface(generatedFileMembers);

        // 4. Check if the file is in scope of partial update:
        // if there is a method has @Generated annotation, then the file is in scope of partial update, otherwise return directly
        boolean hasGeneratedAnnotations = generatedFileMembers.stream().anyMatch(member -> hasGeneratedAnnotation(member));

        if (!hasGeneratedAnnotations) {
            return generatedFileContent;
        }

        NodeList<BodyDeclaration<?>> updatedMembersList = new NodeList<>();
        // 5. Iterate existingFileMembers, keep manual written members, and replace generated members with the corresponding newly generated one
        for (BodyDeclaration<?> existingMember : existingFileMembers) {
            boolean isGeneratedMethod = hasGeneratedAnnotation(existingMember);
            if (!isGeneratedMethod) { // manual written member
                updatedMembersList.add(existingMember);
            } else {
                // find the corresponding newly generated member
                for (BodyDeclaration<?> generatedMember : generatedFileMembers) {
                    if (isMembersCorresponding(existingMember, generatedMember)) {
                        updatedMembersList.add(generatedMember);
                        break;
                    }
                }
            }
        }

        // 6. Add remaining members in generated file to the new members list
        for (BodyDeclaration<?> generatedMember : generatedFileMembers) {
            boolean needToAddToUpdateMembersList = true;
            for (BodyDeclaration<?> existingMember : updatedMembersList) {
                // If the generated member and the existing member is corresponding,
                // or if there is an existing member who has the same name as the generated member and is manually written,
                // Then we don't put the generated member to the updatedMembersList.
                if (isMembersCorresponding(existingMember, generatedMember) || (isMembersWithSameName(existingMember, generatedMember) && !hasGeneratedAnnotation(existingMember))) {
                    needToAddToUpdateMembersList = false;
                    break;
                }
            }
            if (needToAddToUpdateMembersList) {
                updatedMembersList.add(generatedMember);
            }
        }

        // 7. Update members
        generatedClazz.setMembers(updatedMembersList);

        // 8. Update imports
        compilationUnitForGeneratedFile.getImports().addAll(compilationUnitForExistingFile.getImports());

        return compilationUnitForGeneratedFile.toString();
    }

    /**
     * Verify if the generated class or interface is valid
     * @param generatedFileMembers, members in the generated file
     * @return true if the generated class or interface is valid, otherwise return false
     */
    private static void validateGeneratedClassOrInterface(List<BodyDeclaration<?>> generatedFileMembers) {
        // 1. Verify there is no duplicate methods (methods with same signature are considered duplicate methods)
        Set<CallableDeclaration.Signature> methodSignatureSet = new HashSet<>();
        for (BodyDeclaration<?> generatedMember : generatedFileMembers) {
            if (generatedMember.isCallableDeclaration()) {
                if (methodSignatureSet.contains(generatedMember.asCallableDeclaration().getSignature())) {
                    throw new RuntimeException("Found duplicate methods in the generated file.");
                }
                methodSignatureSet.add(generatedMember.asCallableDeclaration().getSignature());
            }
        }
    }

    /**
     * Handle partial update for module-info.java file.
     * We will merge module-info.java file contents.
     *
     * @param compilationUnitForGeneratedFile the newly generated file content
     * @param compilationUnitForExistingFile the existing file content that contains user's manual update code
     * @return the content after handling partial update
     */
    private static String handlePartialUpdateForModuleInfoFile(CompilationUnit compilationUnitForGeneratedFile,
                                                               CompilationUnit compilationUnitForExistingFile) {
        return mergeModuleFileContent(compilationUnitForGeneratedFile, compilationUnitForExistingFile);
    }

    /**
     *
     * The basic logic is as below:
     * 1. Parse the directives from the two files
     * 2. Create requires, exports, opens, uses, provides directive lists from the generated file and existing file
     * 3. Merge the requires, exports, opens, uses, provides directive lists one by one
     * 4. Add the directive lists to ModuleDeclaration in generated file, then use generated file as return value
     * @param compilationUnitForGeneratedFile the newly generated file content
     * @param compilationUnitForExistingFile the existing file content that contains user's manual update code
     * @return merged module-info.java file content
     */
    private static String mergeModuleFileContent(CompilationUnit compilationUnitForGeneratedFile,
                                                 CompilationUnit compilationUnitForExistingFile) {
        if (!compilationUnitForExistingFile.getModule().isPresent() || !compilationUnitForGeneratedFile.getModule().isPresent()) {
            throw new RuntimeException("Generated file or existing file is not module-info file");
        }

        NodeList<ModuleDirective> directivesForGeneratedFile = compilationUnitForGeneratedFile.getModule().get().getDirectives();

        NodeList<ModuleDirective> directivesForExistingFile = compilationUnitForExistingFile.getModule().get().getDirectives();

        // generated file directives
        NodeList<ModuleDirective> requiresDirectivesForGeneratedFile = new NodeList<>();
        NodeList<ModuleDirective> exportsDirectivesForGeneratedFile = new NodeList<>();
        NodeList<ModuleDirective> opensDirectivesForGeneratedFile = new NodeList<>();
        NodeList<ModuleDirective> usesDirectivesForGeneratedFile = new NodeList<>();
        NodeList<ModuleDirective> providesDirectivesForGeneratedFile = new NodeList<>();
        addToEachTypeOfDirectiveList(directivesForGeneratedFile, requiresDirectivesForGeneratedFile,
                exportsDirectivesForGeneratedFile, opensDirectivesForGeneratedFile, usesDirectivesForGeneratedFile,
                providesDirectivesForGeneratedFile);

        // existing file directives
        NodeList<ModuleDirective> requiresDirectivesForExistingFile = new NodeList<>();
        NodeList<ModuleDirective> exportsDirectivesForExistingFile = new NodeList<>();
        NodeList<ModuleDirective> opensDirectivesForExistingFile = new NodeList<>();
        NodeList<ModuleDirective> usesDirectivesForExistingFile = new NodeList<>();
        NodeList<ModuleDirective> providesDirectivesForExistingFile = new NodeList<>();
        addToEachTypeOfDirectiveList(directivesForExistingFile, requiresDirectivesForExistingFile,
                exportsDirectivesForExistingFile, opensDirectivesForExistingFile, usesDirectivesForExistingFile,
                providesDirectivesForExistingFile);

        // generated file directives
        NodeList<ModuleDirective> requiresDirectiveNodeList = mergeDirectiveNodeList(requiresDirectivesForGeneratedFile, requiresDirectivesForExistingFile);
        NodeList<ModuleDirective> exportsDirectiveNodeList = mergeDirectiveNodeList(exportsDirectivesForGeneratedFile, exportsDirectivesForExistingFile);
        NodeList<ModuleDirective> opensDirectiveNodeList = mergeDirectiveNodeList(opensDirectivesForGeneratedFile, opensDirectivesForExistingFile);
        NodeList<ModuleDirective> usesDirectiveNodeList = mergeDirectiveNodeList(usesDirectivesForGeneratedFile, usesDirectivesForExistingFile);
        NodeList<ModuleDirective> providesDirectiveNodeList = mergeDirectiveNodeList(providesDirectivesForGeneratedFile, providesDirectivesForExistingFile);
        addToEachTypeOfDirectiveList(directivesForGeneratedFile, requiresDirectivesForExistingFile,
                exportsDirectivesForExistingFile, opensDirectivesForExistingFile, usesDirectivesForExistingFile,
                providesDirectivesForExistingFile);

        NodeList<ModuleDirective> moduleDirectives = new NodeList<>();
        moduleDirectives.addAll(requiresDirectiveNodeList);
        moduleDirectives.addAll(exportsDirectiveNodeList);
        moduleDirectives.addAll(opensDirectiveNodeList);
        moduleDirectives.addAll(usesDirectiveNodeList);
        moduleDirectives.addAll(providesDirectiveNodeList);

        ModuleDeclaration moduleDeclaration = compilationUnitForGeneratedFile.getModule().get();
        moduleDeclaration.setDirectives(moduleDirectives);

        compilationUnitForGeneratedFile.setModule(moduleDeclaration);

        // add comments as compilationUnitForGeneratedFile.toString() does not include comments
        StringBuilder comments = new StringBuilder();
        for (Comment comment : compilationUnitForGeneratedFile.getOrphanComments()) {
            comments.append(comment.toString());
        }

        return comments + "\n" + compilationUnitForGeneratedFile;
    }

    /**
     * Handle partial update for package-info.java file.
     * We will merge package-info.java file contents.
     *
     * @param compilationUnitForGeneratedFile the newly generated file content
     * @param compilationUnitForExistingFile the existing file content that contains user's manual update code
     * @return the content after handling partial update
     */
    private static String handlePartialUpdateForPackageInfoFile(CompilationUnit compilationUnitForGeneratedFile,
                                                               CompilationUnit compilationUnitForExistingFile) {
        if (!isPackageInfoFile(compilationUnitForExistingFile) || !isPackageInfoFile(compilationUnitForGeneratedFile)) {
            throw new RuntimeException("Generated file or existing file is not package-info file");
        }

        JavadocComment generatedJavadoc = compilationUnitForGeneratedFile.getPackageDeclaration()
                .flatMap(PackageDeclaration::getComment)
                .map(Comment::asJavadocComment)
                .orElse(null);

        JavadocComment existingJavadoc = compilationUnitForExistingFile.getPackageDeclaration()
                .flatMap(PackageDeclaration::getComment)
                .map(Comment::asJavadocComment)
                .orElse(null);

        // If the existing file has no Javadocs just return the generated file.
        if (existingJavadoc == null) {
            return compilationUnitForGeneratedFile.toString();
        }

        String existingJavadocString = existingJavadoc.toString();
        int existingGeneratedDocStartPosition = existingJavadocString.indexOf("<!-- start generated doc -->");
        int existingGeneratedDocEndPosition = existingJavadocString.indexOf("<!-- end generated doc -->");

        if (existingGeneratedDocStartPosition == -1 && existingGeneratedDocEndPosition == -1) {
            // If the existing file has no generated doc, just return the existing file.
            compilationUnitForGeneratedFile.getPackageDeclaration().get().setComment(existingJavadoc);
            return compilationUnitForExistingFile.toString();
        }

        if (existingGeneratedDocEndPosition == -1) {
            throw new RuntimeException("Existing file has a start generated doc ('<!-- start generated doc -->') but "
                    + "no end generated doc ('<!-- end generated doc -->').");
        } else if (existingGeneratedDocStartPosition == -1) {
            throw new RuntimeException("Existing file has an end generated doc ('<!-- end generated doc -->') but "
                    + "no start generated doc ('<!-- start generated doc -->').");
        }

        String generatedJavadocString = generatedJavadoc.toString();
        int startGenerateDocPosition = generatedJavadocString.indexOf("<!-- start generated doc -->");
        int endGenerateDocPosition = generatedJavadocString.indexOf("<!-- end generated doc -->");


        String mergedJavadoc = existingJavadocString.substring(0, existingGeneratedDocStartPosition) +
                generatedJavadocString.substring(startGenerateDocPosition, endGenerateDocPosition + 26) +
                existingJavadocString.substring(existingGeneratedDocEndPosition + 26);

        String[] lines = mergedJavadoc.split("\r\n");

        if (lines.length == 0) {
            compilationUnitForGeneratedFile.getPackageDeclaration().get().setComment(new JavadocComment());
        } else {
            compilationUnitForGeneratedFile.getPackageDeclaration().get().setComment(
                    new JavadocComment(String.join("\r\n", Arrays.copyOfRange(lines, 1, lines.length - 1))));
        }

        return compilationUnitForGeneratedFile.toString();
    }


    /**
     *
     * Merge two directive list. The logic is as below:
     * 1. Add all the directives in list1 to the returned list.
     * 2. For each directive in list2, check if it is in list1, if it is in list1, then we don't need to add it to returned list, otherwise, we need to add it to the returned list
     * @param list1 first directive list
     * @param list2 second directive list
     * @return the merged directive list
     */
    private static NodeList<ModuleDirective> mergeDirectiveNodeList(NodeList<ModuleDirective> list1, NodeList<ModuleDirective> list2) {
        NodeList<ModuleDirective> res = new NodeList<>();
        res.addAll(list1);
        for (ModuleDirective directive2 : list2) {
            boolean isInList1 = false;
            for (ModuleDirective directive1 : list1) {
                if (directive1.getTokenRange().isPresent() && directive2.getTokenRange().isPresent()) {
                    // 1. build two token string lists from the two directives, only put in non-empty tokens
                    // 2. compare the two token list
                    // 3. if the two token lists are the same, then we consider directive2 is in list1, otherwise, we consider directive2 is not in list1
                    List<String> tokenList1 = new ArrayList<>();
                    List<String> tokenList2 = new ArrayList<>();
                    for (JavaToken token1 : directive1.getTokenRange().get()) {
                        String trimmedToken1 = token1.asString().trim();
                        if (!trimmedToken1.isEmpty()) {
                            tokenList1.add(trimmedToken1);
                        }
                    }
                    for (JavaToken token2 : directive2.getTokenRange().get()) {
                        String trimmedToken2 = token2.asString().trim();
                        if (!trimmedToken2.isEmpty()) {
                            tokenList2.add(trimmedToken2);
                        }
                    }
                    if(tokenList1.equals(tokenList2)) {
                        isInList1 = true;
                    }
                }
            }
            if (!isInList1) {
                res.add(directive2);
            }
        }
        return res;
    }

    private static void addToEachTypeOfDirectiveList(NodeList<ModuleDirective> allDirectives,
                                                     NodeList<ModuleDirective> requiresDirectiveNodeList,
                                                     NodeList<ModuleDirective> exportsDirectiveNodeList,
                                                     NodeList<ModuleDirective> opensDirectiveNodeList,
                                                     NodeList<ModuleDirective> usesDirectiveNodeList,
                                                     NodeList<ModuleDirective> providesDirectiveNodeList) {
        for (ModuleDirective directive : allDirectives) {
            if (directive.isModuleRequiresDirective()) {
                requiresDirectiveNodeList.add(directive);
            }
            if (directive.isModuleExportsDirective()) {
                exportsDirectiveNodeList.add(directive);
            }
            if (directive.isModuleOpensDirective()) {
                opensDirectiveNodeList.add(directive);
            }
            if (directive.isModuleUsesDirective()) {
                usesDirectiveNodeList.add(directive);
            }
            if (directive.isModuleProvidesDirective()) {
                providesDirectiveNodeList.add(directive);
            }
        }

    }

    private static boolean hasGeneratedAnnotation(BodyDeclaration<?> member) {
        if (member.getAnnotations() != null && member.getAnnotations().size() > 0) {
            return member.getAnnotations().stream().anyMatch(annotationExpr -> annotationExpr.getName().toString().equals("Generated"));
        } else {
            return false;
        }
    }

    /**
     * Compare whether two members are corresponding: if two members are callable, which means they are constructor or method, we will compare the signature, otherwise, we will compare the name.
     * @param member1
     * @param member2
     * @return true if two members are corresponding, false if two members are not corresponding.
     */
    private static boolean isMembersCorresponding(BodyDeclaration<?> member1, BodyDeclaration<?> member2) {
        if (member1.isCallableDeclaration() && member2.isCallableDeclaration()) {
            // compare signature
            return member1.asCallableDeclaration().getSignature().equals(member2.asCallableDeclaration().getSignature());
        } else {
            return isMembersWithSameName(member1, member2);
        }
    }

    private static boolean isMembersWithSameName(BodyDeclaration<?> member1, BodyDeclaration<?> member2) {
        if (member1.isFieldDeclaration() && member2.isFieldDeclaration()) {
            return isFieldDeclarationWithSameName(member1, member2);
        } else if (member1.getMetaModel().equals(member2.getMetaModel()) &&
                member1 instanceof NodeWithSimpleName && member2 instanceof NodeWithSimpleName) {
            // compare name
            return ((NodeWithSimpleName<?>) member2).getName().equals(((NodeWithSimpleName<?>) member1).getName());
        }
        return false;
    }

    private static boolean isFieldDeclarationWithSameName(BodyDeclaration<?> member1, BodyDeclaration<?> member2) {
        if (member1.asFieldDeclaration().getVariables() != null &&
                !member1.asFieldDeclaration().getVariables().isEmpty() &&
                member2.asFieldDeclaration().getVariables() != null &&
                !member2.asFieldDeclaration().getVariables().isEmpty()) {
            // for FieldDeclaration, currently make it simple, we only compare the first variable, if the first variable
            // has the same name, then we consider they are field declarations with same name
            return member1.asFieldDeclaration().getVariables().get(0).getName()
                    .equals(member2.asFieldDeclaration().getVariables().get(0).getName());
        }
        return false;
    }

    private static ClassOrInterfaceDeclaration getClassOrInterfaceDeclaration(CompilationUnit cu) {
        NodeList<TypeDeclaration<?>> types = cu.getTypes();
        if (types.size() == 1 && types.get(0).isClassOrInterfaceDeclaration()) {
            SimpleName className = types.get(0).getName();
            if (cu.getClassByName(className.asString()).isPresent()) {
                return cu.getClassByName(className.asString()).get();
            }
        }
        return null;
    }

    // A package-info.java file has no types and should only be comprised of comments, imports, and a package
    // declaration.
    private static boolean isPackageInfoFile(CompilationUnit cu) {
        return (cu.getTypes() == null || cu.getTypes().isEmpty())
            && cu.getChildNodes().stream().allMatch(node -> node instanceof Comment
                || node instanceof ImportDeclaration
                || node instanceof PackageDeclaration);
    }

    private static boolean isClassOrInterfaceFile(CompilationUnit cu) {
        NodeList<TypeDeclaration<?>> types = cu.getTypes();
        return types.size() == 1 && types.get(0).isClassOrInterfaceDeclaration();
    }
}
