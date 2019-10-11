using CSharp;
using Xunit;

namespace UT {
    public class ArrayTest {
        private readonly ArraySolution arraySolution = new ArraySolution();

        [Fact]
        public void IsOneBitCharacter() {
            Assert.True(arraySolution.IsOneBitCharacter(new int[] {1, 0, 0}));
            Assert.False(arraySolution.IsOneBitCharacter(new int[] {1, 1, 1, 0}));
        }

        [Fact]
        public void UniqueOccurrences() {
            Assert.False(arraySolution.UniqueOccurrences(new int[] {1, 2}));
            Assert.True(arraySolution.UniqueOccurrences(new int[] {1, 2, 2, 1, 1, 3}));
        }

        [Fact]
        public void GetMaximumGold() {
            int[][] grid = new int[3][];
            grid[0] = new[] {0, 6, 0};
            grid[1] = new[] {5, 8, 7};
            grid[2] = new[] {0, 9, 0};
            Assert.Equal(24, arraySolution.GetMaximumGold(grid));
        }
    }
}