using CSharp;
using NUnit.Framework;

namespace Tests
{
    public class IntTest
    {
        private readonly IntSolution _solution = new IntSolution();
        private readonly StringSolution _stringSolution = new StringSolution();
        private readonly ArraySolution _arraySolution = new ArraySolution();
        [SetUp]
        public void Setup()
        {
        }

        [Test]
        public void BinaryGapTest()
        {
            Assert.AreEqual(2,_solution.BinaryGap(22));
            Assert.AreEqual(2,_solution.BinaryGap(5));
        }

        [Test]
        public void ReverseOnlyLettersTest()
        {
            Assert.AreEqual("dc-ba",_stringSolution.ReverseOnlyLetters("ab-cd"));
            Assert.AreEqual("j-Ih-gfE-dCba",_stringSolution.ReverseOnlyLetters("a-bC-dEf-ghIj"));
            Assert.AreEqual("Qedo1ct-eeLg=ntse-T!",_stringSolution.ReverseOnlyLetters("Test1ng-Leet=code-Q!"));
        }

        [Test]
        public void ToGoatLatinTest()
        {
            Assert.AreEqual("Imaa peaksmaaa oatGmaaaa atinLmaaaaa",_stringSolution.ToGoatLatin("I speak Goat Latin"));
        }

        [Test]
        public void RotatedDigitsTest()
        {
            Assert.AreEqual(247,_solution.RotatedDigits(857));
        }

        [Test]
        public void IsMonotonicTest()
        {
            Assert.True(_arraySolution.IsMonotonic(new int[]{1,2,3,4}));
            Assert.True(_arraySolution.IsMonotonic(new int[]{6,5,4,4}));
            Assert.False(_arraySolution.IsMonotonic(new int[]{1,3,2}));
            Assert.True(_arraySolution.IsMonotonic(new int[]{1,2,4,5}));
            Assert.True(_arraySolution.IsMonotonic(new int[]{1,1,1}));
        }

        [Test]
        public void BackspaceCompare()
        {
            Assert.True(_stringSolution.BackspaceCompare("ab##","c#d#"));
            Assert.True(_stringSolution.BackspaceCompare("a##c","#a#c"));
            Assert.False(_stringSolution.BackspaceCompare("a#c","b"));
        }
    }
}