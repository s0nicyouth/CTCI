package com.syouth.tests;

import com.syouth.CTCI4;
import com.syouth.Library;
import org.junit.Assert;
import org.junit.Test;

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
        int[] arr = {1, 2, 3, 4, 5, 6, 7};

        Library.TreeNode root = CTCI4.CreateMinimalBST(arr);

        LinkedList<LinkedList<Library.TreeNode>> layers = CTCI4.PrintBTByLayers(root);
        LinkedList<LinkedList<Integer>> layers_check = new LinkedList<>();
        layers_check.add(new LinkedList<>());
        layers_check.add(new LinkedList<>());
        layers_check.add(new LinkedList<>());
        layers_check.get(0).add(4);
        layers_check.get(1).add(2);
        layers_check.get(1).add(6);
        layers_check.get(2).add(1);
        layers_check.get(2).add(3);
        layers_check.get(2).add(5);
        layers_check.get(2).add(7);
        Assert.assertEquals(3, layers.size());
        for (int i = 0; i < 3; i++) {
            int j = 0;
            for (Library.TreeNode n : layers.get(i)) {
                Assert.assertEquals(n.data, layers_check.get(i).get(j).intValue());
                j++;
            }
        }
    }

    @Test
    public void testIsBST() {
        {
            int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};

            Library.TreeNode root = CTCI4.CreateMinimalBST(arr);

            Assert.assertTrue(CTCI4.IsBST(root));
            Assert.assertTrue(CTCI4.IsBSTRecursive(root));
        }
        {
            int[] arr = {1, 2, 3, 4, 5, 7, 6, 8, 9, 10, 11};

            Library.TreeNode root = CTCI4.CreateMinimalBST(arr);

            Assert.assertFalse(CTCI4.IsBST(root));
        }
    }

    @Test
    public void TestFindCommonAncentor() {
        {
            Library.TreeNode root = new Library.TreeNode(1);
            Library.TreeNode n1 = root.setLeft(new Library.TreeNode(2)).setRight(new Library.TreeNode(4));
            Library.TreeNode n2 = root.setRight(new Library.TreeNode(3)).setLeft(new Library.TreeNode(5));
            Assert.assertEquals(root, CTCI4.FindFirstCommonAncestor(root, n1, n2));
        }
        {
            Library.TreeNode root = new Library.TreeNode(1);
            Library.TreeNode temp = root.setLeft(new Library.TreeNode(2)).setRight(new Library.TreeNode(4));
            temp.setRight(new Library.TreeNode(10));
            temp.setLeft(new Library.TreeNode(22));
            Library.TreeNode commonAncentor = root.setRight(new Library.TreeNode(3)).setLeft(new Library.TreeNode(5));
            Library.TreeNode n1 = commonAncentor.setLeft(new Library.TreeNode(22));
            Library.TreeNode n2 = commonAncentor.setRight(new Library.TreeNode(98)).setLeft(new Library.TreeNode(2));
            Assert.assertEquals(commonAncentor, CTCI4.FindFirstCommonAncestor(root, n1, n2));
        }
        {
            Library.TreeNode root = new Library.TreeNode(1);
            root.setLeft(new Library.TreeNode(2)).setRight(new Library.TreeNode(4));
            Library.TreeNode commonAncentor = root.setRight(new Library.TreeNode(3));
            Library.TreeNode n2 = commonAncentor.setLeft(new Library.TreeNode(5));
            Library.TreeNode n1 = n2.setRight(new Library.TreeNode(2));
            Assert.assertEquals(commonAncentor, CTCI4.FindFirstCommonAncestor(root, n1, n2));
        }
        {
            Library.TreeNode root = new Library.TreeNode(1);
            root.setLeft(new Library.TreeNode(2)).setRight(new Library.TreeNode(4));
            Library.TreeNode commonAncentor = root.setRight(new Library.TreeNode(3));
            Library.TreeNode n2 = commonAncentor.setLeft(new Library.TreeNode(5));
            Library.TreeNode n1 = n2.setRight(new Library.TreeNode(2));
            Assert.assertEquals(null, CTCI4.FindFirstCommonAncestor(root, new Library.TreeNode(2),
                    new Library.TreeNode(4)));
        }
        {
            Library.TreeNode root = new Library.TreeNode(1);
            root.setLeft(new Library.TreeNode(2)).setRight(new Library.TreeNode(4));
            Library.TreeNode commonAncentor = root.setRight(new Library.TreeNode(3));
            Library.TreeNode n2 = commonAncentor.setLeft(new Library.TreeNode(5));
            Library.TreeNode n1 = n2.setRight(new Library.TreeNode(2));
            Assert.assertEquals(null, CTCI4.FindFirstCommonAncestor(root, null, null));
        }

        {
            Library.TreeNode root = new Library.TreeNode(1);
            root.setLeft(new Library.TreeNode(2)).setRight(new Library.TreeNode(4));
            Library.TreeNode commonAncentor = root.setRight(new Library.TreeNode(3));
            Library.TreeNode n2 = commonAncentor.setLeft(new Library.TreeNode(5));
            Library.TreeNode n1 = n2.setRight(new Library.TreeNode(2));
            Assert.assertEquals(null, CTCI4.FindFirstCommonAncestor(root, n1, new Library.TreeNode(3)));
        }
    }
}
