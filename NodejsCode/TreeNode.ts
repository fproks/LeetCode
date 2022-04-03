class TreeNode {
    val: number
    left: TreeNode | null
    right: TreeNode | null
    constructor(val?: number, left?: TreeNode | null, right?: TreeNode | null) {
        this.val = (val===undefined ? 0 : val)
        this.left = (left===undefined ? null : left)
        this.right = (right===undefined ? null : right)
    }
}


class BSTIterator {
    private  gen: Generator<number>
    private  temp: ReturnType<Generator<number>['next']>
    constructor(root: TreeNode | null) {
        if(!root) return
        this.gen =this.generator(root)
        this.temp =this.gen.next()
    }
    *generator(node:TreeNode){
        if(node.left) yield *this.generator(node.left)
        yield  node.val
        if(node.right) yield  *this.generator(node.right)
    }

    next(): number {
        let val =this.temp.value
        this.temp =this.gen.next()
        return  val
    }

    hasNext(): boolean {
        return !this.temp.done
    }


}
