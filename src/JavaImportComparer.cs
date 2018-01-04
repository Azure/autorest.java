using System;
using System.Collections.Generic;

namespace AutoRest.Java
{
    public class JavaImportComparer : IComparer<string>
    {
        public int Compare(string lhsImport, string rhsImport)
        {
            int result;

            if (lhsImport == rhsImport)
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

                string[] lhsImportParts = GetImportParts(lhsImport);
                string[] rhsImportParts = GetImportParts(rhsImport);

                int minimumImportPartCount = Math.Min(lhsImportParts.Length, rhsImportParts.Length);
                for (int i = 0; i < minimumImportPartCount; ++i)
                {
                    string lhsImportPart = lhsImportParts[i].ToLowerInvariant();
                    string rhsImportPart = rhsImportParts[i].ToLowerInvariant();

                    if (!lhsImportPart.Equals(rhsImportPart))
                    {
                        bool isLastLhsPart = IsLastPart(i, lhsImportParts);
                        bool isLastRhsPart = IsLastPart(i, rhsImportParts);
                        if (isLastLhsPart != isLastRhsPart)
                        {
                            result = isLastLhsPart ? -1 : 1;
                        }
                        else
                        {
                            result = lhsImportPart.CompareTo(rhsImportPart);
                        }
                        break;
                    }
                }
            }

            return result;
        }

        private static string[] GetImportParts(string import)
        {
            return import.Split('.');
        }

        private static Boolean IsLastPart(int importIndex, string[] importParts)
        {
            return importIndex == importParts.Length - 1;
        }
    }
}
