/**
 * @user: linhos
 * @Time: Create in 15:25 2017/9/19
 */
public class Solution {
    int convertBSTsum =0;
    public TreeNode convertBST(TreeNode root) {
        convertFromRight(root);
        return root;
    }
    //538. Convert BST to Greater Tree
    // 后序遍历，从大到小
    private void convertFromRight(TreeNode root){
        if(root==null) return;
        convertFromRight(root.right);
        root.val+= convertBSTsum;
        convertBSTsum =root.val;
        convertFromRight(root.left);
    }
}
