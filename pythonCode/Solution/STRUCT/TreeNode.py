class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

    def toList(self):
        res = [self.val]
        if self.left is None:
            res.append(None)
        else:
            res.extend(self.left.toList())
        if self.right is None:
            res.append(None)
        else:
            res.extend(self.right.toList())
        return res

if __name__ =="__main__":
    t =TreeNode(1)
    t.right=TreeNode(3)
    print(t.toList())
