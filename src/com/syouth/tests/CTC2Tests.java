package com.syouth.tests;

import com.syouth.CTCI2;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by anton.ivanov on 6/21/2016.
 */
public class CTC2Tests {
    @Test
    public void testRemoveHaveDups() {
        CTCI2.Node head = new CTCI2.Node(1);
        head.appendToTail(2);
        head.appendToTail(3);
        head.appendToTail(1);
        head.appendToTail(1);
        head.appendToTail(1);
        head.appendToTail(4);
        head.appendToTail(3);
        head.appendToTail(4);
        head.appendToTail(5);
        head.appendToTail(2);
        CTCI2.RemoveDupsFromLinkedLIst(head);
        CTCI2.Node next = head;
        int i = 1;
        while (next != null) {
            Assert.assertEquals(i, next.data);
            next = next.next;
            i++;
        }
    }

    @Test
    public void testRemoveHaveNoDups() {
        CTCI2.Node head = new CTCI2.Node(1);
        head.appendToTail(2);
        head.appendToTail(3);
        head.appendToTail(4);
        head.appendToTail(5);
        CTCI2.RemoveDupsFromLinkedLIst(head);
        CTCI2.Node next = head;
        int i = 1;
        while (next != null) {
            Assert.assertEquals(i, next.data);
            next = next.next;
            i++;
        }
    }

    @Test
    public void testFindKth() {
        CTCI2.Node head = new CTCI2.Node(1);
        head.appendToTail(2);
        head.appendToTail(3);
        head.appendToTail(4);
        head.appendToTail(5);
        Assert.assertEquals(3, CTCI2.FindKthElemInLinkedList(head, 3).data);
    }

    @Test
    public void testFindKthNotPossible() {
        CTCI2.Node head = new CTCI2.Node(1);
        head.appendToTail(2);
        head.appendToTail(3);
        head.appendToTail(4);
        head.appendToTail(5);
        Assert.assertEquals(null, CTCI2.FindKthElemInLinkedList(head, 33));
    }

    @Test
    public void testDeleteElem() {
        CTCI2.Node head = new CTCI2.Node(1);
        head.appendToTail(2);
        head.appendToTail(3);
        head.appendToTail(3);
        head.appendToTail(4);
        head.appendToTail(5);
        CTCI2.Node toDel = head.next.next.next;
        CTCI2.DeleteElem(toDel);
        CTCI2.Node next = head;
        int i = 1;
        while (next != null) {
            Assert.assertEquals(i, next.data);
            next = next.next;
            i++;
        }
    }

    @Test
    public void testPartitionLinkedList() {
        CTCI2.Node head = new CTCI2.Node(1);
        head.appendToTail(0);
        head.appendToTail(10);
        head.appendToTail(2);
        head.appendToTail(5);
        head.appendToTail(3);
        CTCI2.Node cur = CTCI2.partitionLinkedList(head, 5);
        while (cur.data != 5) {
            Assert.assertTrue(cur.data < 5);
            cur = cur.next;
        }
        while (cur != null) {
            Assert.assertTrue(cur.data >= 5);
            cur = cur.next;
        }
    }

    @Test
    public void testLinkedListAdd() {
        {
            CTCI2.Node num1 = new CTCI2.Node(7);
            num1.appendToTail(1);
            num1.appendToTail(6);
            CTCI2.Node num2 = new CTCI2.Node(5);
            num2.appendToTail(9);
            num2.appendToTail(2);
            CTCI2.Node result = CTCI2.AddLinkedLists(num1, num2);
            CTCI2.Node test = new CTCI2.Node(2);
            test.appendToTail(1);
            test.appendToTail(9);
            Assert.assertEquals(test, result);
        }
        {
            CTCI2.Node num1 = new CTCI2.Node(5);
            CTCI2.Node num2 = new CTCI2.Node(5);
            CTCI2.Node result = CTCI2.AddLinkedLists(num1, num2);
            CTCI2.Node test = new CTCI2.Node(0);
            test.appendToTail(1);
            Assert.assertEquals(test, result);
        }
        {
            CTCI2.Node num1 = new CTCI2.Node(5);
            CTCI2.Node num2 = new CTCI2.Node(5);
            num2.appendToTail(2);
            CTCI2.Node test = new CTCI2.Node(0);
            test.appendToTail(3);
            CTCI2.Node result = CTCI2.AddLinkedLists(num1, num2);
            Assert.assertEquals(test, result);
        }
    }

    @Test
    public void testLinkedListAddForward() {
        {
            CTCI2.Node num1 = new CTCI2.Node(6);
            num1.appendToTail(1);
            num1.appendToTail(7);
            CTCI2.Node num2 = new CTCI2.Node(2);
            num2.appendToTail(9);
            num2.appendToTail(5);
            CTCI2.Node test = new CTCI2.Node(9);
            test.appendToTail(1);
            test.appendToTail(2);
            CTCI2.Node result = CTCI2.AddLinkedListsForward(num1, num2);
            Assert.assertEquals(test, result);
        }
        {
            CTCI2.Node num1 = new CTCI2.Node(6);
            num1.appendToTail(1);
            CTCI2.Node num2 = new CTCI2.Node(2);
            num2.appendToTail(9);
            num2.appendToTail(5);
            CTCI2.Node test = new CTCI2.Node(3);
            test.appendToTail(5);
            test.appendToTail(6);
            CTCI2.Node result = CTCI2.AddLinkedListsForward(num1, num2);
            Assert.assertEquals(test, result);
        }
    }

    @Test
    public void testDetectLoopTopSort() {
        {
            CTCI2.NodeNoEq start = new CTCI2.NodeNoEq(1);
            start.next = new CTCI2.NodeNoEq(2);
            start.next.next = new CTCI2.NodeNoEq(3);
            start.next.next.next = new CTCI2.NodeNoEq(4);
            start.next.next.next.next = new CTCI2.NodeNoEq(5);
            start.next.next.next.next.next = start.next.next;
            Assert.assertTrue(start.next.next == CTCI2.getLoopNodeTopSort(start));
        }
    }

    @Test
    public void testDetectLoopIterable() {
        {
            CTCI2.Node start = new CTCI2.Node(1);
            start.next = new CTCI2.Node(2);
            start.next.next = new CTCI2.Node(3);
            start.next.next.next = new CTCI2.Node(4);
            start.next.next.next.next = new CTCI2.Node(5);
            start.next.next.next.next.next = start.next.next;
            Assert.assertTrue(start.next.next == CTCI2.getLoopNodeIterable(start));
        }
    }

    @Test
    public void testPalindrome() {
        {
            CTCI2.Node start = new CTCI2.Node(1);
            start.appendToTail(2);
            start.appendToTail(2);
            start.appendToTail(1);
            Assert.assertTrue(CTCI2.checkLinkedListPalindrome(start));
        }
        {
            CTCI2.Node start = new CTCI2.Node(1);
            start.appendToTail(2);
            start.appendToTail(3);
            start.appendToTail(2);
            start.appendToTail(1);
            Assert.assertTrue(CTCI2.checkLinkedListPalindrome(start));
        }
        {
            CTCI2.Node start = new CTCI2.Node(1);
            start.appendToTail(2);
            start.appendToTail(3);
            start.appendToTail(1);
            start.appendToTail(1);
            Assert.assertFalse(CTCI2.checkLinkedListPalindrome(start));
        }
        {
            CTCI2.Node start = new CTCI2.Node(1);
            start.appendToTail(2);
            start.appendToTail(3);
            start.appendToTail(3);
            start.appendToTail(3);
            start.appendToTail(3);
            start.appendToTail(3);
            start.appendToTail(3);
            start.appendToTail(2);
            start.appendToTail(1);
            Assert.assertTrue(CTCI2.checkLinkedListPalindrome(start));
        }
    }
}
