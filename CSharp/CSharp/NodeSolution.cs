using System;
using System.Collections;
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

        public TreeNode IncreasingBST(TreeNode root)
        {
            TreeNode result = null;
            if (root == null) return null;
            if (root.left != null)
            {
                result = IncreasingBST(root.left);
            }

            if (result != null)
            {
                var tmp = result;
                while (tmp.right != null)
                {
                    tmp = tmp.right;
                }

                tmp.right = new TreeNode(root.val) {right = IncreasingBST(root.right)};
            }
            else
            {
                result = new TreeNode(root.val) {right = IncreasingBST(root.right)};
            }

            return result;
        }

        public IList<IList<int>> LevelOrder(Node root)
        {
            IList<IList<int>> result = new List<IList<int>>();
            var queue = new Queue<KeyValuePair<Node, int>>();
            queue.Enqueue(new KeyValuePair<Node, int>(root, 0));
            while (queue.Count > 0)
            {
                var tmp = queue.Dequeue();
                if (result.Count <= tmp.Value) result.Add(new List<int>());
                result[tmp.Value].Add(tmp.Key.val);
                if (tmp.Key.children != null && tmp.Key.children.Count > 0)
                {
                    foreach (var node in tmp.Key.children)
                    {
                        queue.Enqueue(new KeyValuePair<Node, int>(node,tmp.Value+1));
                    }
                   
                }
            }

            return result;
        }
    }
}