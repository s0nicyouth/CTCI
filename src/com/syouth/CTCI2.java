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

        @Override
        public int hashCode() {
            Node head = this;
            int hash = 0;
            while (head != null) {
                hash += head.data;
                head = head.next;
            }

            return hash;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Node) || o == null) {
                return false;
            }
            Node oNode = (Node) o;
            Node head = this;
            while (head != null || oNode != null) {
                if (head.data != oNode.data) {
                    return false;
                }
                oNode = oNode.next;
                head = head.next;
            }

            if (head != oNode) {
                return false;
            }

            return true;
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

    public static Node AddLinkedLists(Node l1, Node l2) {
        Node result = null;
        int carry = 0;
        while (l1 != null && l2 != null) {
            int l1n = l1 != null ? l1.data : 0;
            int l2n = l2 != null ? l2.data : 0;
            int sum = l1n + l2n + carry;
            if (sum < 10) {
                if (result == null) {
                    result = new Node(sum);
                } else {
                    result.appendToTail(sum);
                }
                carry = 0;
            } else {
                if (result == null) {
                    result = new Node(sum % 10);
                } else {
                    result.appendToTail(sum % 10);
                }
                carry = 1;
            }

            l1 = l1.next;
            l2 = l2.next;
        }

        if (carry == 1) {
            result.appendToTail(1);
        }

        return result;
    }
}
