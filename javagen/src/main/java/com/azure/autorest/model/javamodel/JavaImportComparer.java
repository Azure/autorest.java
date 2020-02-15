package com.azure.autorest.model.javamodel;

import java.util.Comparator;
import java.util.Locale;

public class JavaImportComparer implements Comparator<String> {
    private static String[] getImportParts(String import_Keyword) {
        return import_Keyword.split("[.]", -1);
    }

    private static boolean isLastPart(int importIndex, String[] importParts) {
        return importIndex == importParts.length - 1;
    }

    public final int compare(String lhsImport, String rhsImport) {
        int result;

        if (lhsImport.equals(rhsImport)) {
            result = 0;
        } else if (lhsImport == null) {
            result = -1;
        } else if (rhsImport == null) {
            result = 1;
        } else {
            result = 0;

            String[] lhsImportParts = getImportParts(lhsImport);
            String[] rhsImportParts = getImportParts(rhsImport);

            int minimumImportPartCount = Math.min(lhsImportParts.length, rhsImportParts.length);
            for (int i = 0; i < minimumImportPartCount; ++i) {
                String lhsImportPart = lhsImportParts[i].toLowerCase(Locale.ROOT);
                String rhsImportPart = rhsImportParts[i].toLowerCase(Locale.ROOT);

                if (!lhsImportPart.equals(rhsImportPart)) {
                    boolean isLastLhsPart = isLastPart(i, lhsImportParts);
                    boolean isLastRhsPart = isLastPart(i, rhsImportParts);
                    if (isLastLhsPart != isLastRhsPart) {
                        result = isLastLhsPart ? -1 : 1;
                    } else {
                        result = lhsImportPart.compareTo(rhsImportPart);
                    }
                    break;
                }
            }
        }

        return result;
    }
}
