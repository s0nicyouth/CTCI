package com.syouth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

/**
 * Created by anton.ivanov on 6/20/2016.
 */
public class CTCI2 {

    public static class Node {
        public Node next = null;
        public int data;

        public int size() {
            int size = 0;
            Node next = this;
            while (next != null) {
                size++;
                next = next.next;
            }

            return size;
        }

        public Node(int d) {
            data = d;
        }

        public Node appendToTail(int d) {
            Node end = new Node(d);
            Node n = this;
            while (n.next != null) {
                n = n.next;
            }
            n.next = end;

            return end;
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

    public static class NodeNoEq {
        public NodeNoEq next = null;
        public int data;

        public NodeNoEq(int d) {
            data = d;
        }

        public void appendToTail(int d) {
            NodeNoEq end = new NodeNoEq(d);
            NodeNoEq n = this;
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

    public static Node AddLinkedLists(Node l1, Node l2) {
        Node result = null;
        int carry = 0;
        while (l1 != null || l2 != null) {
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

            l1 = l1 != null ? l1.next : null;
            l2 = l2 != null ? l2.next : null;
        }

        if (carry == 1) {
            result.appendToTail(1);
        }

        return result;
    }

    public static Node AddLinkedListsForward(Node l1, Node l2) {
        int l1Len = 0;
        Node l1Counter = l1;
        while (l1Counter != null) {
            l1Len++;
            l1Counter = l1Counter.next;
        }
        int l2Len = 0;
        Node l2Counter = l2;
        while (l2Counter != null) {
            l2Len++;
            l2Counter = l2Counter.next;
        }
        Node result = null;
        Node current = null;
        ArrayList<Node> reverse = new ArrayList<>();
        if (l1Len > l2Len) {
            while (l1Len != l2Len) {
                if (current == null) {
                    current = new Node(l1.data);
                    result = current;
                } else {
                    current.next = new Node(l1.data);
                    current = current.next;
                }
                reverse.add(current);
                l1 = l1.next;
                l1Len--;
            }
        } else {
            while (l2Len > l1Len) {
                if (current == null) {
                    current = new Node(l2.data);
                    result = current;
                } else {
                    current.next = new Node(l2.data);
                }
                reverse.add(current);
                l2 = l2.next;
                l2Len--;
            }
        }
        while (l1 != null || l2 != null) {
            int l1Val = l1 != null ? l1.data : 0;
            int l2Val = l2 != null ? l2.data : 0;
            int res = l1Val + l2Val;
            if (res < 10) {
                if (current == null) {
                    current = new Node(res);
                    result = current;
                } else {
                    current.next = new Node(res);
                    current = current.next;
                }
                reverse.add(current);
            } else {
                if (current == null) {
                    current = new Node(1);
                    current.next = new Node(res % 10);
                    result = current;
                } else {
                    int carry = 1;
                    int i = 1;
                    current.next = new Node(res % 10);
                    do {
                        int val = reverse.get(reverse.size() - i).data;
                        int sm = val + carry;
                        reverse.get(reverse.size() - i).data = sm % 10;
                        if (sm > 9) {
                            carry = 1;
                        } else {
                            carry = 0;
                        }
                        i++;
                        if (i > reverse.size() && carry == 1) {
                            Node n = new Node(0);
                            n.next = result;
                            result = n;
                            reverse.add(0, n);
                        }
                    } while (carry != 0);
                }
                current = current.next;
                reverse.add(current);
            }

            l1 = l1 != null ? l1.next : null;
            l2 = l2 != null ? l2.next : null;
        }

        return result;
    }

    private static int RAddll(Node l1, Node l2, Node result) {
        if (l1 == null && l2 == null) {
            return -1;
        }

        int carry = RAddll(l1.next, l2.next, result.appendToTail(0));
        if (carry == -1) {
            result.next = null;
            carry = 0;
        }
        int sum = l1.data + l2.data + carry;
        int toCarry = 0;
        if (sum >= 10) {
            result.data = sum % 10;
            toCarry = 1;
        } else {
            result.data = sum;
        }

        return toCarry;
    }

    public static Node AddLLRecoursive(Node l1, Node l2) {
        int l1size = l1.size();
        int l2size = l2.size();
        Node minList = l1size > l2size ? l2 : l1;
        Node maxList = l1size > l2size ? l1 : l2;
        int diff = Math.abs(l2size - l1size);
        for (int i = 0; i < diff; i++) {
            Node zero = new Node(0);
            zero.next = minList;
            minList = zero;
        }

        Node result = new Node(0);
        int carry = RAddll(maxList, minList, result);
        if (carry == 1) {
            Node n = new Node(1);
            n.next = result;
            result = n;
        }
        return result;
    }

    private static NodeNoEq getLoopNodeRecursive(NodeNoEq start, HashMap<NodeNoEq, Integer> map) {
        if (start == null) {
            return null;
        }
        if (map.containsKey(start) && map.get(start) == 1) {
            return start;
        }
        map.put(start, 1);
        NodeNoEq n = getLoopNodeRecursive(start.next, map);
        map.put(start, 2);
        return n;
    }

    public static NodeNoEq getLoopNodeTopSort(NodeNoEq start) {
        HashMap<NodeNoEq, Integer> visitedNodes = new HashMap<>();
        return getLoopNodeRecursive(start, visitedNodes);
    }

    public static Node getLoopNodeIterable(Node start) {
        if (start == null) {
            return null;
        }
        Node runner1 = start;
        Node runner2 = start;
        do {
            runner1 = runner1.next;
            runner2 = runner2.next;
            if (runner2 != null) {
                runner2 = runner2.next;
            }
        } while (runner1 != runner2 && runner2 != null);
        if (runner2 == null) {
            return null;
        } else {
            runner1 = start;
            while (runner1 != runner2) {
                runner1 = runner1.next;
                runner2 = runner2.next;
            }
            return runner1;
        }
    }

    public static boolean checkLinkedListPalindrome(Node start) {
        int len = 0;
        Node lenStart = start;
        while (lenStart != null) {
            lenStart = lenStart.next;
            len++;
        }

        int div = (int) Math.floor(len / 2);
        Stack<Node> reverseStack = new Stack<>();
        lenStart = start;
        for (int i = 0; i < div; i++) {
            reverseStack.push(lenStart);
            lenStart = lenStart.next;
        }
        if (len % 2 == 0) {
        } else {
            lenStart = lenStart.next;
        }
        for (int i = (len % 2 == 0 ? div + 1 : div  + 2); i < len; i++) {
            if (reverseStack.pop().data != lenStart.data) {
                return false;
            } else {
                lenStart = lenStart.next;
            }
        }

        return true;
    }
}
