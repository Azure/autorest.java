package com.azure.autorest.model.javamodel;

import java.util.*;

public class JavaImportComparer implements Comparator<String>
{
    public final int compare(String lhsImport, String rhsImport)
    {
        int result;

        if (lhsImport.equals(rhsImport))
        {
            result = 0;
        }
        else if (lhsImport == null)
        {
            result = -1;
        }
        else if (rhsImport == null)
        {
            result = 1;
        }
        else
        {
            result = 0;

            String[] lhsImportParts = GetImportParts(lhsImport);
            String[] rhsImportParts = GetImportParts(rhsImport);

            int minimumImportPartCount = Math.min(lhsImportParts.length, rhsImportParts.length);
            for (int i = 0; i < minimumImportPartCount; ++i)
            {
                String lhsImportPart = lhsImportParts[i].toLowerCase(Locale.ROOT);
                String rhsImportPart = rhsImportParts[i].toLowerCase(Locale.ROOT);

                if (!lhsImportPart.equals(rhsImportPart))
                {
                    boolean isLastLhsPart = IsLastPart(i, lhsImportParts);
                    boolean isLastRhsPart = IsLastPart(i, rhsImportParts);
                    if (isLastLhsPart != isLastRhsPart)
                    {
                        result = isLastLhsPart ? -1 : 1;
                    }
                    else
                    {
                        result = lhsImportPart.compareTo(rhsImportPart);
                    }
                    break;
                }
            }
        }

        return result;
    }

    private static String[] GetImportParts(String import_Keyword)
    {
        return import_Keyword.split("[.]", -1);
    }

    private static boolean IsLastPart(int importIndex, String[] importParts)
    {
        return importIndex == importParts.length - 1;
    }
}