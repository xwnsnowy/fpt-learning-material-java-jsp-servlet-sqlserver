/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author user
 */
public class Node {

    public String code;
    public Node left;
    public Node right;

    public Node(String code) {
        this.code = code;
        this.left = null;
        this.right = null;
    }

    // Adds a child node to the left or right of the current node, alternating between levels
    public void addChild(Node childNode) {
        if (this.left == null) {
            this.left = childNode;
        } else if (this.right == null) {
            this.right = childNode;
        } else if (this.left.height() <= this.right.height()) {
            this.left.addChild(childNode);
        } else {
            this.right.addChild(childNode);
        }
    }

    // Returns the height of the subtree rooted at this node
    public int height() {
        int leftHeight = (this.left == null) ? 0 : this.left.height();
        int rightHeight = (this.right == null) ? 0 : this.right.height();
        //chieu cao cua cay con hien tai bang 1 + max height cay con ben trai va ben phai
        return 1 + Math.max(leftHeight, rightHeight);
    }
}
