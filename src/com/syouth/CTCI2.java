package com.syouth;

import java.util.HashSet;

/**
 * Created by anton.ivanov on 6/20/2016.
 */
public class CTCI2 {

    public static class Node {
        public Node next = null;
        public int data;

        public Node(int d) {
            data = d;
        }

        public void appendToTail(int d) {
            Node end = new Node(d);
            Node n = this;
            while (n.next != null) {
                n = n.next;
            }
            n.next = end;
        }
    }

    public static void RemoveDupsFromLinkedLIst(Node head) {
        if (head == null || head.next == null) {
            return;
        }
        HashSet<Integer> dups = new HashSet<>();
        Node prev = head;
        Node next = head.next;
        dups.add(head.data);
        while(next != null) {
            if (dups.contains(next.data)) {
                prev.next = next.next;
                next = next.next;
            } else {
                dups.add(next.data);
                prev = next;
                next = next.next;
            }
        }
    }

    public static Node FindKthElemInLinkedList(Node head, int k) {
        Node kthAhead = head;
        for (int i = 1; i < k; i++) {
            kthAhead = kthAhead.next;
            if (kthAhead == null) {
                return null;
            }
        }

        Node kthFromEnd = head;
        while (kthAhead.next != null) {
            kthAhead = kthAhead.next;
            kthFromEnd = kthFromEnd.next;
        }

        return kthFromEnd;
    }

    public static void DeleteElem(Node elem) {
        if (elem == null || elem.next == null) {
            return;
        }
        elem.data = elem.next.data;
        elem.next = elem.next.next;
    }

    public static Node partitionLinkedList(Node head, int val) {
        boolean firstMove = true;
        Node lastLeft = null;
        Node cur = head;
        Node prev = null;
        while (cur != null) {
            Node next = cur.next;
            if (cur.data < val) {
                if (prev != null) {
                    prev.next = cur.next;
                }
                if (!cur.equals(head)) {
                    cur.next = head;
                }
                head = cur;
                if (firstMove) {
                    lastLeft = cur;
                    firstMove = false;
                }
            } else if (cur.data == val) {
                if (prev != null) {
                    prev.next = cur.next;
                }
                if (!firstMove) {
                    cur.next = lastLeft.next;
                    lastLeft.next = cur;
                    lastLeft = cur;
                } else {
                    if (!cur.equals(head)) {
                        cur.next = head;
                    }
                    head = cur;
                    firstMove = false;
                }
            } else {
                prev = cur;
            }

            if (prev == null) {
                prev = cur;
            }
            cur = next;
        }

        return head;
    }
}
