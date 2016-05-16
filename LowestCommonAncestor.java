/**
 * Created by jinhuawang on 5/16/16.
 * Solution to https://www.hackerrank.com/challenges/binary-search-tree-lowest-common-ancestor
 */
public class LowestCommonAncestor {

    static Node lca(Node root,int v1,int v2)
    {
        if(v1<root.data&&v2<root.data){
            return lca(root.left, v1, v2);
        }
        if(v1>root.data&&v2>root.data){
            return lca(root.right, v1, v2);
        }
        return root;
    }

}

class Node {
    int data;
    Node left;
    Node right;
}
