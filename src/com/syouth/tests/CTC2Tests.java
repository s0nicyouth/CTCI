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
}
