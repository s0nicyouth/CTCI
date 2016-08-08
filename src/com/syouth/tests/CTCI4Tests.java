package com.syouth.tests;

import com.syouth.CTCI4;
import com.syouth.Library;
import org.junit.Assert;
import org.junit.Test;
import sun.awt.image.ImageWatched;

import java.util.LinkedList;

/**
 * Created by anton.ivanov on 8/5/2016.
 */
public class CTCI4Tests {

    @Test
    public void testMinimalBST() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        Library.TreeNode root = CTCI4.CreateMinimalBST(arr);
    }

    private void CreateGraph(Library.GraphNode root, int levels, LinkedList<Library.GraphNode> nodes) {
        if (levels == 0) {
            return;
        } else {
            for (int i = 0; i < Math.random() * 100 % levels + Math.random() * 100 % 5; i++) {
                Library.GraphNode newNode = new Library.GraphNode((int) Math.random() * 100 % levels);
                nodes.add(newNode);
                root.addNeighbor(newNode);
            }

            for (Library.GraphNode n : root.getNeigthbors()) {
                CreateGraph(n, levels - 1, nodes);
            }
        }
    }

    @Test
    public void testFindRoute() {
        {
            Library.GraphNode root = new Library.GraphNode();
            LinkedList<Library.GraphNode> nodes = new LinkedList<>();
            CreateGraph(root, 10, nodes);

            for (int i = 0; i < 3; i++) {
                Assert.assertTrue(CTCI4.HasRoute(root, nodes.get((int)Math.random() * 100 % nodes.size())));
            }
            for (int i = 0; i < 5; i++) {
                Assert.assertFalse(CTCI4.HasRoute(root, new Library.GraphNode()));
            }
        }
    }

    @Test
    public void testLayers() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17};

        Library.TreeNode root = CTCI4.CreateMinimalBST(arr);

        LinkedList<LinkedList<Library.TreeNode>> layers = CTCI4.PrintBTByLayers(root);
    }
}
