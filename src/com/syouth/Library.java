package com.syouth;

import java.util.LinkedList;

/**
 * Created by anton.ivanov on 8/5/2016.
 */
public class Library {
    public static class TreeNode {
        public int data;
        public TreeNode left;
        public TreeNode rigth;
        public TreeNode parent;

        public TreeNode(int data) {
            this.data = data;
        }

        public TreeNode setRight(TreeNode node) {
            rigth = node;
            if (node != null) {
                node.parent = this;
            }

            return node;
        }

        public TreeNode setLeft(TreeNode node) {
            left = node;
            if (node != null) {
                node.parent = this;
            }

            return node;
        }
    }

    public static class GraphNode {
        private LinkedList<GraphNode> neithbors = new LinkedList<>();
        int data;

        public GraphNode() {}

        public GraphNode(int data) {
            this.data = data;
        }

        public void addNeighbor(GraphNode node) {
            neithbors.add(node);
        }

        public LinkedList<GraphNode> getNeigthbors() {
            return neithbors;
        }
    }
}
