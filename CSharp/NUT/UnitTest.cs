using CSharp;
using NUnit.Framework;

namespace Tests
{
    public class IntTest
    {
        private readonly IntSolution _solution = new IntSolution();
        private readonly StringSolution _stringSolution = new StringSolution();
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
    }
}