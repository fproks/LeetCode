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
    }
}