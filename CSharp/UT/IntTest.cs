using CSharp;
using Xunit;

namespace UT {
    public class IntTest {
        private readonly IntSolution _solution = new IntSolution();

        [Fact]
        public void BinaryGapTest() {
            Assert.Equal(2, _solution.BinaryGap(22));
            Assert.Equal(2, _solution.BinaryGap(5));
        }

        [Fact]
        public void ping() {
            RecentCounter counter = new RecentCounter();
            Assert.Equal(1, counter.Ping(1));
            Assert.Equal(2, counter.Ping(100));
            Assert.Equal(3, counter.Ping(3001));
            Assert.Equal(3, counter.Ping(3002));
        }

        [Fact]
        public void IsCousinsTest() {
            TreeNode tree = new TreeNode(1);
            tree.left = new TreeNode(2);
            tree.left.right = new TreeNode(4);
            tree.right = new TreeNode(3);
            tree.right.right = new TreeNode(5);
            Assert.True(new isCousinsSolution().IsCousins(tree, 4, 5));
        }

        [Fact]
        public void TribonacciTest() {
            var tribon = new TribonacciSolution();
            Assert.Equal(4, tribon.Tribonacci(4));
            Assert.Equal(1389537, tribon.Tribonacci(25));
        }
    }
}