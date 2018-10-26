using System;
using System.Collections.Generic;
using System.Linq;

namespace CSharp
{
    public class Node
    {
        public int val;
        public IList<Node> children;

        public Node()
        {
        }

        public Node(int _val, IList<Node> _children)
        {
            val = _val;
            children = _children;
        }
    }

    public class TreeNode
    {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int x)
        {
            val = x;
        }
    }

    public class NodeSolution
    {
        public IList<int> Preorder(Node root)
        {
            var list = new List<int>();
            perInsert(root, list);
            return list;
        }

        private void perInsert(Node root, IList<int> list)
        {
            if (root == null) return;
            list.Add(root.val);
            if (root.children == null) return;
            foreach (var node in root.children)
            {
                perInsert(node, list);
            }
        }

        public IList<int> Postorder(Node root)
        {
            var list = new List<int>();
            PostOrderList(root, list);
            return list;
        }

        private void PostOrderList(Node root, IList<int> list)
        {
            if (root == null) return;
            if (root.children != null)
            {
                foreach (var child in root.children)
                {
                    PostOrderList(child, list);
                }
            }

            list.Add(root.val);
        }

        public int MaxDepth(Node root)
        {
            return MaxDepth(root, 0);
        }

        private int MaxDepth(Node root, int deep)
        {
            if (root == null) return deep;
            deep++;
            return root.children?.Select(node => MaxDepth(node, deep)).Concat(new[] {deep}).Max() ?? deep;
        }

        public TreeNode SearchBST(TreeNode root, int val)
        {
            while (true)
            {
                if (root == null) return null;
                if (root.val == val) return root;
                root = root.val > val ? root.left : root.right;
            }
        }
    }
}