package LeetCode.struct;

public class QuadTree {
    public boolean val;
    public boolean isLeaf;
    public QuadTree topLeft;
    public QuadTree topRight;
    public QuadTree bottomLeft;
    public QuadTree bottomRight;

    public QuadTree() {
    }

    public QuadTree(boolean _val, boolean _isLeaf, QuadTree _topLeft, QuadTree _topRight, QuadTree _bottomLeft, QuadTree _bottomRight) {
        val = _val;
        isLeaf = _isLeaf;
        topLeft = _topLeft;
        topRight = _topRight;
        bottomLeft = _bottomLeft;
        bottomRight = _bottomRight;

    }

    public QuadTree intersect(QuadTree quadTree1, QuadTree quadTree2) {
        if (quadTree1.isLeaf)
            return quadTree1.val ? quadTree1 : quadTree2;
        if (quadTree2.isLeaf)
            return quadTree2.val ? quadTree2 : quadTree1;
        quadTree1.topLeft = intersect(quadTree1.topLeft, quadTree2.topLeft);
        quadTree1.topRight = intersect(quadTree1.topRight, quadTree2.topRight);
        quadTree1.bottomLeft = intersect(quadTree1.bottomLeft, quadTree2.bottomLeft);
        quadTree1.bottomRight = intersect(quadTree1.bottomRight, quadTree2.bottomRight);

        if (quadTree1.topLeft.isLeaf && quadTree1.topRight.isLeaf
                && quadTree1.bottomLeft.isLeaf && quadTree1.bottomRight.isLeaf
                && quadTree1.topLeft.val == quadTree1.topRight.val
                && quadTree1.topLeft.val == quadTree1.bottomLeft.val
                && quadTree1.topLeft.val == quadTree1.bottomRight.val) {

            quadTree1.val = quadTree1.topLeft.val;
            quadTree1.isLeaf = true;
            quadTree1.topLeft = null;
            quadTree1.topRight = null;
            quadTree1.bottomLeft = null;
            quadTree1.bottomRight = null;
        }

        quadTree1.val = quadTree1.val || quadTree2.val;
        quadTree1.isLeaf = quadTree1.isLeaf || quadTree2.isLeaf;
        return quadTree1;

    }


}
