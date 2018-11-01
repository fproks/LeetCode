using System;
using System.Collections.Generic;
using CSharp;
using Xunit;

namespace UT
{
    public class UnitTest1
    {

        private Node SolutionNode()
        {
           
            var node = new Node();
            node.val = 5;
            var node2 = new Node();
            node2.val = 6;
            var node3 = new Node();
            node3.val = 2;
            var node4 = new Node();
            node4.val = 4;
            var nodelist1 = new List<Node>();
            nodelist1.Add(node);
            nodelist1.Add(node2);
            var node5 = new Node(3, nodelist1);
            var nodelist2 = new List<Node>();
            nodelist2.Add(node5);
            nodelist2.Add(node3);
            nodelist2.Add(node4);
           return new Node(1, nodelist2);
        }
        [Fact]
        public void Test1()
        {
            var nodeSolution = new NodeSolution();
            var root = this.SolutionNode();
            var list = nodeSolution.Preorder(root);
            var expect = new List<int>() {1, 3, 5, 6, 2, 4};
            Assert.Equal(list,expect);
        }

        [Fact]
        public void PostorderTest()
        {
            var nodeSolution = new NodeSolution();
            var root = this.SolutionNode();
            var list = nodeSolution.Postorder(root);
            var expect =new List<int>(){5,6,3,2,4,1};
            Assert.Equal(list,expect); 
        }

        [Fact]
        public void manDepthTest()
        {
            var nodeSolution = new NodeSolution();
            var root = this.SolutionNode();
            Assert.Equal(nodeSolution.MaxDepth(root),3);
        }

        [Fact]
        public void UncommonFromSentences()
        {
            var nodeSolution = new StringSolution();
            var A = "this apple is sweet";
            var B = "this apple is sour";
            Assert.Equal(2,nodeSolution.UncommonFromSentences(A,B).Length);
        }
    }
}