package com.syouth.tests;

import com.syouth.CTCI4;
import com.syouth.Library;
import org.junit.Test;

/**
 * Created by anton.ivanov on 8/5/2016.
 */
public class CTCI4Tests {

    @Test
    public void TestMinimalBST() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        Library.TreeNode root = CTCI4.CreateMinimalBST(arr);
    }
}
