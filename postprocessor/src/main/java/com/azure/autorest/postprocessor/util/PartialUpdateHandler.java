package com.azure.autorest.postprocessor.util;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.ast.nodeTypes.NodeWithSimpleName;

import java.util.ArrayList;
import java.util.List;

import static com.github.javaparser.StaticJavaParser.parse;

public class PartialUpdateHandler {

    public static String handlePartialUpdateForFile(String generatedFileContent, String existingFileContent) {
        CompilationUnit compilationUnitForGeneratedFile = parse(generatedFileContent);
        CompilationUnit compilationUnitForExistingFile = parse(existingFileContent);
        ClassOrInterfaceDeclaration generatedClazz = getClassOrInterfaceDeclaration(compilationUnitForGeneratedFile);
        ClassOrInterfaceDeclaration existingClazz = getClassOrInterfaceDeclaration(compilationUnitForExistingFile);

        List<BodyDeclaration<?>> generatedFileMembers = new ArrayList<>();
        if (generatedClazz != null) {
            generatedFileMembers = generatedClazz.getMembers();
        }
        List<BodyDeclaration<?>> existingFileMembers = new ArrayList<>();
        if (existingClazz != null) {
            existingFileMembers = existingClazz.getMembers();
        }

        // 1. check if the file is in scope of partial update:
        // if there is a method has @Generated annotation, then the file is in scope of partial update, otherwise return directly
        boolean hasGeneratedAnnotations = generatedFileMembers.stream().anyMatch(member -> hasGeneratedAnnotation(member));

        if (!hasGeneratedAnnotations) {
            return generatedFileContent;
        }

        NodeList<BodyDeclaration<?>> updatedMembersList = new NodeList<>();
        // 2. iterate existingFileMembers, keep manual written members, and replace generated members with the corresponding newly generated one
        for (BodyDeclaration<?> existingMember : existingFileMembers) {
            boolean isGeneratedMethod = hasGeneratedAnnotation(existingMember);
            if (!isGeneratedMethod) { // manual written member
                updatedMembersList.add(existingMember);
            } else {
                // find the corresponding newly generated member
                for (BodyDeclaration<?> generatedMember : generatedFileMembers) {
                    if (isMembersCorresponding(existingMember, generatedMember)) {
                        updatedMembersList.add(generatedMember);
                    }
                }
            }
        }

        // 3. add remaining members in generated file to the new members list
        for (BodyDeclaration<?> generatedMember : generatedFileMembers) {
            boolean hasMembersWithSameName = false;
            for (BodyDeclaration<?> existingMember : updatedMembersList) {
                if (isMembersWithSameName(existingMember, generatedMember)) {
                    hasMembersWithSameName = true;
                }
            }
            if (!hasMembersWithSameName) {
                updatedMembersList.add(generatedMember);
            }
        }

        // 4. update members
        generatedClazz.setMembers(updatedMembersList);

        // 5. update imports
        compilationUnitForGeneratedFile.getImports().addAll(compilationUnitForExistingFile.getImports());

        return compilationUnitForGeneratedFile.toString();
    }

    private static boolean hasGeneratedAnnotation(BodyDeclaration<?> member) {
        if (member.getAnnotations() != null && member.getAnnotations().size() > 0) {
            return member.getAnnotations().stream().anyMatch(annotationExpr -> annotationExpr.getName().toString().equals("Generated"));
        } else {
            return false;
        }
    }

    private static boolean isMembersCorresponding(BodyDeclaration<?> member1, BodyDeclaration<?> member2) {
        if (member1.isCallableDeclaration() && member2.isCallableDeclaration()) {
            // compare signature
            if (member1.asCallableDeclaration().getSignature().equals(member2.asCallableDeclaration().getSignature())) {
                return true;
            }
        } else if (isMembersWithSameName(member1, member2)) {
            return true;
        }
        return false;
    }

    private static boolean isMembersWithSameName(BodyDeclaration<?> member1, BodyDeclaration<?> member2) {
        if (member1.isFieldDeclaration() && member2.isFieldDeclaration()) {
            return isFieldDeclarationWithSameName(member1, member2);
        } else if (member1.getMetaModel().equals(member2.getMetaModel()) &&
                member1 instanceof NodeWithSimpleName && member2 instanceof NodeWithSimpleName) {
            // compare name
            if (((NodeWithSimpleName) member2).getName().equals(((NodeWithSimpleName) member1).getName())) {
                return true;
            }
        }
        return false;
    }

    private static boolean isFieldDeclarationWithSameName(BodyDeclaration<?> member1, BodyDeclaration<?> member2) {
        if (member1.asFieldDeclaration().getVariables() != null &&
                member1.asFieldDeclaration().getVariables().size() > 0 &&
                member2.asFieldDeclaration().getVariables() != null &&
                member2.asFieldDeclaration().getVariables().size() > 0) {
            // for FieldDeclaration, currently make it simple, we only compare the first variable, if the first variable has the same name, then we consider they are field declarations with same name
            if (member1.asFieldDeclaration().getVariables().get(0).getName().equals(member2.asFieldDeclaration().getVariables().get(0).getName())) {
                return true;
            }
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
}
