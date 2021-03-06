package com.syouth;

import javafx.util.Pair;

import java.util.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by anton.ivanov on 8/5/2016.
 */
public class CTCI4 {
    public static Library.TreeNode CreateMinimalBST(int[] arr, int start, int end) {
        if (start > end) {
            return null;
        }
        int middle = (start + end) / 2;
        Library.TreeNode node = new Library.TreeNode(arr[middle]);
        node.setLeft(CreateMinimalBST(arr, start, middle - 1));
        node.setRight(CreateMinimalBST(arr, middle + 1, end));
        return node;
    }

    public static boolean FindRoute(Library.GraphNode start, Library.GraphNode finish) {
        HashSet<Library.GraphNode> selectedForVisit = new HashSet<>();

        Queue<Library.GraphNode> toVisit = new LinkedList<>();

        toVisit.add(start);

        while (!toVisit.isEmpty()) {
            Library.GraphNode node = toVisit.remove();
            if (node == finish) {
                return true;
            } else {
                for (Library.GraphNode n : node.getNeigthbors()) {
                    if (selectedForVisit.contains(n)) {
                        continue;
                    } else {
                        toVisit.add(n);
                        selectedForVisit.add(n);
                    }
                }
            }
        }

        return false;
    }

    public static boolean HasRoute(Library.GraphNode n1, Library.GraphNode n2) {
        return (FindRoute(n1, n2) || FindRoute(n2, n1));
    }

    public static Library.TreeNode CreateMinimalBST(int arr[]) {
        return CreateMinimalBST(arr, 0, arr.length - 1);
    }

    public static LinkedList<LinkedList<Library.TreeNode>> PrintBTByLayers(Library.TreeNode root) {
        LinkedList<LinkedList<Library.TreeNode>> result = new LinkedList<>();
        Queue<Library.TreeNode> toVisit = new LinkedList<>();

        toVisit.add(root);
        int nodesInCurLayer = 1;
        int nodesInNextLayer = 0;
        int layer = 0;
        result.add(new LinkedList<>());

        while (!toVisit.isEmpty()) {
            for (int i = 0; i < nodesInCurLayer; i++) {
                Library.TreeNode node = toVisit.remove();
                result.get(layer).add(node);
                if (node.left != null) {
                    nodesInNextLayer++;
                    toVisit.add(node.left);
                }
                if (node.rigth != null) {
                    nodesInNextLayer++;
                    toVisit.add(node.rigth);
                }
            }

            layer++;
            nodesInCurLayer = nodesInNextLayer;
            nodesInNextLayer = 0;
            if (nodesInCurLayer != 0) {
                result.add(new LinkedList<>());
            }
        }

        return result;
    }

    private static int getBranchHeight(Library.TreeNode n) {
        if (n == null) {
            return 0;
        }

        return 1 + Math.max(getBranchHeight(n.left), getBranchHeight(n.rigth));
    }

    public static boolean CheckBalanced(Library.TreeNode node) {
        int hDiff = Math.abs(getBranchHeight(node.left) - getBranchHeight(node.rigth));
        if (hDiff > 1) {
            return false;
        } else {
            return CheckBalanced(node.left) && CheckBalanced(node.rigth);
        }
    }

    private static void TraverseDepthNaturalOrder(Library.TreeNode root, LinkedList<Integer> sorted) {
        if (root == null) {
            return;
        }
        TraverseDepthNaturalOrder(root.left, sorted);
        sorted.add(root.data);
        TraverseDepthNaturalOrder(root.rigth, sorted);
    }

    public static boolean IsBST(Library.TreeNode root) {
        LinkedList<Integer> sorted = new LinkedList<>();
        TraverseDepthNaturalOrder(root, sorted);
        for (int i = 1; i < sorted.size(); i++) {
            if (sorted.get(i - 1) > sorted.get(i)) {
                return false;
            }
        }

        return true;
    }

    public static boolean IsBSTRecursive(Library.TreeNode root) {
        if (root == null) {
            return true;
        }
        boolean leftResult = true;
        if (root.left != null) {
            if (root.data < root.left.data) {
                return false;
            } else {
                leftResult = IsBSTRecursive(root.left);
            }
        }
        boolean rightResult = true;
        if (root.rigth != null) {
            if (root.rigth.data < root.data) {
                return false;
            } else {
                rightResult = IsBSTRecursive(root.rigth);
            }
        }

        return leftResult && rightResult;
    }

    private static void TopologycallySort(HashSet<Character> visited, Character root, HashMap<Character, List<Character>> graph, List<Character> result) {
        if (root == null || visited.contains(root)) {
            return;
        }
        if (graph.containsKey(root)) {
            for (Character n : graph.get(root)) {
                TopologycallySort(visited, n, graph, result);
            }
        }

        result.add(root);
        visited.add(root);
    }

    public static List<Character> FindBuildOrder(List<Character> projects, List<Pair<Character, Character>> dependencies) {
        HashMap<Character, List<Character>> graph = new HashMap<>();
        for (Pair<Character, Character> p : dependencies) {
            if (!graph.containsKey(p.getKey())) {
                graph.put(p.getKey(), new LinkedList<>());
            }
            graph.get(p.getKey()).add(p.getValue());
        }

        HashSet<Character> visited = new HashSet<>();
        List<Character> result = new LinkedList<>();
        for (Character c : projects) {
            TopologycallySort(visited, c, graph, result);
        }

        return result;
    }

    private static boolean AssureAllNodesExist(Library.TreeNode root, Library.TreeNode n1, Library.TreeNode n2) {
        if (root == null) {
            return false;
        }
        Queue<Library.TreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean n1Found = false;
        boolean n2Found = false;
        while (!queue.isEmpty()) {
            Library.TreeNode current = queue.remove();
            if (current == n1) {
                n1Found = true;
                if (n2Found) {
                    return true;
                }
            }
            if (current == n2) {
                n2Found = true;
                if (n1Found) {
                    return true;
                }
            }
            if (current.left != null) {
                queue.add(current.left);
            }
            if (current.rigth != null) {
                queue.add(current.rigth);
            }
        }

        return false;
    }

    private static boolean IsNodeInSubtree(Library.TreeNode root, Library.TreeNode node, Library.TreeNode n2) {
        if (root == null) {
            return false;
        }
        if (root == node || root == n2) {
            return true;
        } else {
            return IsNodeInSubtree(root.rigth, node, n2) || IsNodeInSubtree(root.left, node, n2);
        }
    }

    private static Library.TreeNode FindFirstCommonAncestorInternal(Library.TreeNode treeRoot, Library.TreeNode n1,
                                                           Library.TreeNode n2) {
        boolean inRightSubtree = IsNodeInSubtree(treeRoot.rigth, n1, n2);
        boolean inLeftSubtree = IsNodeInSubtree(treeRoot.left, n1, n2);
        if (inLeftSubtree && inRightSubtree) {
            return treeRoot;
        } else if (inLeftSubtree && (treeRoot.left != n1 && treeRoot.left != n2)) {
            return FindFirstCommonAncestor(treeRoot.left, n1, n2);
        } else if (inRightSubtree && (treeRoot.rigth != n1 && treeRoot.rigth != n2)){
            return FindFirstCommonAncestor(treeRoot.rigth, n1, n2);
        } else {
            return treeRoot;
        }
    }

    public static Library.TreeNode FindFirstCommonAncestor(Library.TreeNode treeRoot, Library.TreeNode n1,
                                                           Library.TreeNode n2) {
        if (!AssureAllNodesExist(treeRoot, n1, n2)) {
            return null;
        }

        return FindFirstCommonAncestorInternal(treeRoot, n1, n2);
    }

    private static void PrintSumPathsRec(Library.TreeNode root, int sum,
                                         HashMap<Integer, LinkedList<Library.TreeNode>> sumUpTo,
                                         LinkedList<Library.TreeNode> curPath,
                                         int prevSum) {
        if (root == null) {
            return;
        }
        curPath.add(root);
        int curSum = prevSum + root.data;
        if (sumUpTo.containsKey(curSum - sum)) {
            LinkedList<Library.TreeNode> nodes = sumUpTo.get(curSum - sum);
            for (Library.TreeNode n : nodes) {
                int index = curPath.indexOf(n);
                if (n == null) {
                    index = -1;
                }
                for (int i = index + 1; i < curPath.size(); i++) {
                    System.out.print(curPath.get(i).data + "->");
                }
                System.out.print("\n");
            }
        }

        if (!sumUpTo.containsKey(curSum)) {
            sumUpTo.put(curSum, new LinkedList<>());
        }
        sumUpTo.get(curSum).add(root);

        PrintSumPathsRec(root.left, sum, sumUpTo, curPath, curSum);
        PrintSumPathsRec(root.rigth, sum, sumUpTo, curPath, curSum);
        curPath.removeLast();
        sumUpTo.get(curSum).removeLast();
        if (sumUpTo.get(curSum).size() == 0) {
            sumUpTo.remove(curSum);
        }
    }

    public static void PrintAllSumPathes(Library.TreeNode root, int sum) {
        HashMap<Integer, LinkedList<Library.TreeNode>> sumUpTo = new HashMap<>();
        sumUpTo.put(0, new LinkedList<>());
        sumUpTo.get(0).add(null);
        LinkedList<Library.TreeNode> currentFullPath = new LinkedList<>();
        PrintSumPathsRec(root, sum, sumUpTo, currentFullPath, 0);
    }
}
