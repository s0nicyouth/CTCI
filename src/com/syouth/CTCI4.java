package com.syouth;

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

    public static Library.TreeNode CreateMinimalBST(int arr[]) {
        return CreateMinimalBST(arr, 0, arr.length - 1);
    }
}
