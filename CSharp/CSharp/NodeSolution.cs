using System;
using System.Collections;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Reflection.Metadata.Ecma335;

namespace CSharp {
    public class Node {
        public int val;
        public IList<Node> children;

        public Node() { }

        public Node(int _val, IList<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    public class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int x) {
            val = x;
        }
    }

    public class NodeSolution {
        public IList<int> Preorder(Node root) {
            var list = new List<int>();
            perInsert(root, list);
            return list;
        }

        private void perInsert(Node root, IList<int> list) {
            if (root == null) return;
            list.Add(root.val);
            if (root.children == null) return;
            foreach (var node in root.children) {
                perInsert(node, list);
            }
        }

        public IList<int> Postorder(Node root) {
            var list = new List<int>();
            PostOrderList(root, list);
            return list;
        }

        private void PostOrderList(Node root, IList<int> list) {
            if (root == null) return;
            if (root.children != null) {
                foreach (var child in root.children) {
                    PostOrderList(child, list);
                }
            }

            list.Add(root.val);
        }

        public int MaxDepth(Node root) {
            return MaxDepth(root, 0);
        }

        private int MaxDepth(Node root, int deep) {
            if (root == null) return deep;
            deep++;
            return root.children?.Select(node => MaxDepth(node, deep)).Concat(new[] {deep}).Max() ?? deep;
        }

        public TreeNode SearchBST(TreeNode root, int val) {
            while (true) {
                if (root == null) return null;
                if (root.val == val) return root;
                root = root.val > val ? root.left : root.right;
            }
        }

        public TreeNode IncreasingBST(TreeNode root) {
            TreeNode result = null;
            if (root == null) return null;
            if (root.left != null) {
                result = IncreasingBST(root.left);
            }

            if (result != null) {
                var tmp = result;
                while (tmp.right != null) {
                    tmp = tmp.right;
                }

                tmp.right = new TreeNode(root.val) {right = IncreasingBST(root.right)};
            }
            else {
                result = new TreeNode(root.val) {right = IncreasingBST(root.right)};
            }

            return result;
        }

        public IList<IList<int>> LevelOrder(Node root) {
            IList<IList<int>> result = new List<IList<int>>();
            var queue = new Queue<KeyValuePair<Node, int>>();
            queue.Enqueue(new KeyValuePair<Node, int>(root, 0));
            while (queue.Count > 0) {
                var tmp = queue.Dequeue();
                if (result.Count <= tmp.Value) result.Add(new List<int>());
                result[tmp.Value].Add(tmp.Key.val);
                if (tmp.Key.children != null && tmp.Key.children.Count > 0) {
                    foreach (var node in tmp.Key.children) {
                        queue.Enqueue(new KeyValuePair<Node, int>(node, tmp.Value + 1));
                    }
                }
            }

            return result;
        }

        public int LongestUnivaluePath(TreeNode root) {
            int longest = 0;
            if (root != null) {
                longest = Math.Max(LongestGavenNode(root), longest);
                longest = Math.Max(LongestUnivaluePath(root.left), longest);
                longest = Math.Max(LongestUnivaluePath(root.right), longest);
            }

            return longest;
        }

        private int LongestGavenNode(TreeNode root) {
            int left = 0, right = 0;
            if (root != null) {
                if (root.left != null && root.left.val == root.val) {
                    left += DeepsWithGavenNode(root.left) + 1;
                }

                if (root.right != null && root.right.val == root.val) {
                    right += DeepsWithGavenNode(root.right) + 1;
                }
            }

            return left + right;
        }

        private int DeepsWithGavenNode(TreeNode root) {
            int left = 0, right = 0;
            if (root != null) {
                if (root.left != null && root.left.val == root.val) {
                    left = left + 1 + DeepsWithGavenNode(root.left);
                }

                if (root.right != null && root.right.val == root.val) {
                    right = right + 1 + DeepsWithGavenNode(root.right);
                }
            }

            return Math.Max(left, right);
        }

        public bool IsUnivalTree(TreeNode root) {
            if (root == null) return true;
            var x = root.val;
            if (root.left != null && root.left.val != x) return false;
            if (root.right != null && root.right.val != x) return false;
            else {
                return IsUnivalTree(root.left) && IsUnivalTree(root.right);
            }
        }

        public int RangeSumBST(TreeNode root, int L, int R) {
            int sum = 0;
            RangeSumBST(ref sum, root, L, R);
            return sum;
        }


        private void RangeSumBST(ref int sum, TreeNode root, int L, int R) {
            if (root == null) return;
            if (root.val <= R && root.val >= L) {
                sum += root.val;
            }

            RangeSumBST(ref sum, root.left, L, R);
            RangeSumBST(ref sum, root.right, L, R);
        }

        //1038. Binary Search Tree to Greater Sum Tree
        public TreeNode BstToGst(TreeNode root) {
            int sum = 0;
            addSumBst(root, ref sum);
            return root;
        }

        private void addSumBst(TreeNode root, ref int sum) {
            if (root.right != null) {
                addSumBst(root.right, ref sum);
            }

            root.val += sum;
            sum = root.val;
            if (root.left != null) addSumBst(root.left, ref sum);
        }
    }

    public class isCousinsSolution {
        bool flags = true;

        public bool IsCousins(TreeNode treeNode, int x, int y) {
            int deepx = 0, deepy = 0;
            Helper(treeNode, x, y, ref deepx, ref deepy, 0);
            return flags && deepx == deepy;
        }

        private void Helper(TreeNode treeNode, int x, int y, ref int deepx, ref int deepy, int deep) {
            if (treeNode == null) return;
            if (treeNode.left != null && treeNode.right != null) {
                if (treeNode.right.val == x && treeNode.left.val == y) {
                    flags = false;
                    return;
                }

                if (treeNode.left.val == x && treeNode.right.val == y) {
                    flags = false;
                    return;
                }
            }

            if (treeNode.val == x) deepx = deep + 1;
            if (treeNode.val == y) deepy = deep + 1;
            if (deepx == deepy && deepx != 0) return;
            Helper(treeNode.left, x, y, ref deepx, ref deepy, deep + 1);
            Helper(treeNode.right, x, y, ref deepx, ref deepy, deep + 1);
        }
    }

    //919. Complete Binary Tree Inserter
    public class CBTInserter {
        private TreeNode _tree;
        private List<TreeNode> _list;

        public CBTInserter(TreeNode root) {
            this._tree = root;
            Queue<TreeNode> queue = new Queue<TreeNode>();
            _list.Add(new TreeNode(0));
            queue.Enqueue(root);
        }

        private void BFS(Queue<TreeNode> queue) {
            while (queue.Count > 0) {
                var fon = queue.Dequeue();
                if (fon.left != null) {
                    queue.Enqueue(fon.left);
                }

                if (fon.right != null) {
                    queue.Enqueue(fon.right);
                }

                _list.Add(fon);
            }
        }

        public int Insert(int v) {
            _list.Add(new TreeNode(v));
            return _list[_list.Count / 2].val;
        }

        public TreeNode Get_root() {
            return _tree;
        }
    }
}