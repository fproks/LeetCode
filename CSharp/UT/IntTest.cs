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
    }
}