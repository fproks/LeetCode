using CSharp;
using Xunit;

namespace UT
{
    public class IntTest
    {
        private readonly IntSolution _solution = new IntSolution();

        [Fact]
        public void BinaryGapTest()
        {
            Assert.Equal(2,_solution.BinaryGap(22));
            Assert.Equal(2,_solution.BinaryGap(5));
        }
    }
}