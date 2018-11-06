using CSharp;
using NUnit.Framework;

namespace Tests{
    public class IntTest{
        private readonly IntSolution _solution = new IntSolution();
        private readonly StringSolution _stringSolution = new StringSolution();
        private readonly ArraySolution _arraySolution = new ArraySolution();
        private readonly NodeSolution _nodeSolution = new NodeSolution();

        [SetUp]
        public void Setup() { }

        [Test]
        public void BinaryGapTest() {
            Assert.AreEqual(2, _solution.BinaryGap(22));
            Assert.AreEqual(2, _solution.BinaryGap(5));
        }

        [Test]
        public void ReverseOnlyLettersTest() {
            Assert.AreEqual("dc-ba", _stringSolution.ReverseOnlyLetters("ab-cd"));
            Assert.AreEqual("j-Ih-gfE-dCba", _stringSolution.ReverseOnlyLetters("a-bC-dEf-ghIj"));
            Assert.AreEqual("Qedo1ct-eeLg=ntse-T!", _stringSolution.ReverseOnlyLetters("Test1ng-Leet=code-Q!"));
        }

        [Test]
        public void ToGoatLatinTest() {
            Assert.AreEqual("Imaa peaksmaaa oatGmaaaa atinLmaaaaa", _stringSolution.ToGoatLatin("I speak Goat Latin"));
        }

        [Test]
        public void RotatedDigitsTest() {
            Assert.AreEqual(247, _solution.RotatedDigits(857));
        }

        [Test]
        public void IsMonotonicTest() {
            Assert.True(_arraySolution.IsMonotonic(new int[] {1, 2, 3, 4}));
            Assert.True(_arraySolution.IsMonotonic(new int[] {6, 5, 4, 4}));
            Assert.False(_arraySolution.IsMonotonic(new int[] {1, 3, 2}));
            Assert.True(_arraySolution.IsMonotonic(new int[] {1, 2, 4, 5}));
            Assert.True(_arraySolution.IsMonotonic(new int[] {1, 1, 1}));
        }

        [Test]
        public void BackspaceCompare() {
            Assert.True(_stringSolution.BackspaceCompare("ab##", "c#d#"));
            Assert.True(_stringSolution.BackspaceCompare("a##c", "#a#c"));
            Assert.False(_stringSolution.BackspaceCompare("a#c", "b"));
        }

        [Test]
        public void longestGavenNode() {
            TreeNode root = new TreeNode(5);
            root.left = new TreeNode(4);
            root.right = new TreeNode(5);
            root.left.left = new TreeNode(1);
            root.left.right = new TreeNode(1);
            root.right.right = new TreeNode(5);
            Assert.AreEqual(2, _nodeSolution.LongestUnivaluePath(root));
        }

        [Test]
        public void NumMagicSquaresInside() {
            int[][] arr = new int[3][];

            arr[0] = new int[4] {4, 3, 8, 4};
            arr[1] = new int[4] {9, 5, 1, 9};
            arr[2] = new[] {2, 7, 6, 2};
            Assert.AreEqual(1, _arraySolution.NumMagicSquaresInside(arr));
        }

        [Test]
        public void NetworkDelayTime() {
            int[,] time = new int[3, 3] {
                {2, 1, 1},
                {2, 3, 1},
                {3, 4, 1}
            };
            Assert.AreEqual(2,_arraySolution.NetworkDelayTime(time, 4, 2));
        }
    }
}