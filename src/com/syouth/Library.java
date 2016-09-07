package com.syouth;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

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
        public int data;
        public Character cData = null;

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

        public static GraphNode FindNodeWithData(GraphNode root, Character toFind) {
            Queue<GraphNode> query = new LinkedList<>();
            query.add(root);
            HashSet<GraphNode> visited = new HashSet<>();
            visited.add(root);
            while (!query.isEmpty()) {
                GraphNode current = query.remove();
                if (current.cData.equals(toFind)) {
                    return current;
                } else {
                    for (GraphNode neighbour : current.getNeigthbors()) {
                        if (visited.contains(neighbour)) {
                            continue;
                        } else {
                            visited.add(neighbour);
                            query.add(neighbour);
                        }
                    }
                }
            }

            return null;
        }
    }
}
