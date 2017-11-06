using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace AutoRest.Java.DanModel
{
    [TestClass]
    public class JavaImportComparerTests
    {
        [TestMethod]
        public void Compare()
        {
            CompareTest("rx.Single", "rx.Observable", 1);
            CompareTest("rx.Observable", "rx.Single", -1);
            CompareTest("rx.Single", "rx.Single", 0);
        }

        private static void CompareTest(string lhs, string rhs, int expectedComparison)
        {
            JavaImportComparer comparer = new JavaImportComparer();
            int comparison = comparer.Compare(lhs, rhs);
            Assert.IsTrue((comparison < 0 && expectedComparison < 0) ||
                          (comparison == 0 && expectedComparison == 0) ||
                          (comparison > 0 && expectedComparison > 0),
                          $"When comparing \"{lhs}\" and \"{rhs}\", expected the result to be {comparisonToString(expectedComparison)}, but it was {comparisonToString(comparison)}.");
        }

        private static string comparisonToString(int comparison)
        {
            return comparison < 0 ? "less than zero" :
                    comparison == 0 ? "zero" :
                    "greater than zero";
        }
    }
}
