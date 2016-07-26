package com.syouth;

import java.util.Objects;

/**
 * Created by anton.ivanov on 7/21/2016.
 */
public class CTCI3 {

    public static class SetOfStacks<T> {
        private int mCapacity;
        private int mCurrentStackSize = 0;
        private Stack<Stack<T>> mStackOfStacks = new Stack<>();

        public SetOfStacks(int capacity) {
            mCapacity = capacity;
            mStackOfStacks.push(new Stack<>());
        }

        public void push(T data) {
            if (mCurrentStackSize < mCapacity) {
                mStackOfStacks.peek().push(data);
                mCurrentStackSize++;
            } else {
                mStackOfStacks.push(new Stack<>());
                mStackOfStacks.peek().push(data);
                mCurrentStackSize = 1;
            }
        }

        public void pop() {
            if (mCurrentStackSize > 1) {
                mStackOfStacks.peek().pop();
                mCurrentStackSize--;
            } else {
                mStackOfStacks.peek().pop();
                mStackOfStacks.pop();
                if (!mStackOfStacks.IsEmpty()) {
                    mCurrentStackSize = mCapacity;
                } else {
                    mStackOfStacks.push(new Stack<>());
                    mCurrentStackSize = 0;
                }
            }
        }

        public T peek() {
            return mStackOfStacks.peek().peek();
        }
    }

    public static class MinStack<T extends Comparable<T>> extends Stack<T> {
        private Stack<T> minStack = new Stack<T>();

        public void push(T data){
            if (IsEmpty()) {
                minStack.push(data);
            } else {
                T curVal = minStack.peek();
                if (data.compareTo(curVal) < 0) {
                    minStack.push(data);
                } else {
                    minStack.push(curVal);
                }
            }
            super.push(data);
        }

        public void pop() {
            super.pop();
            minStack.pop();
        }

        public T min() {
            return minStack.peek();
        }
    }

    public static class Stack<T> {
        private static class Node<Type> {
            private Node next;
            Type data;

            public Node(Type data) {
                this.data = data;
            }
        }

        private Node<T> top;

        public void push(T data) {
            Node<T> newNode = new Node<>(data);
            if (top != null) {
                newNode.next = top;
            }

            top = newNode;
        }

        public void pop() {
            top = top.next;
        }

        public T peek() {
            return top.data;
        }

        public boolean IsEmpty() {
            return top == null;
        }
    }

    public static<T extends Comparable<T>> void SortStack(Stack<T> stack) {
        Stack<T> stackHelper = new Stack<>();
        while (!stack.IsEmpty()) {
            int elemsToBack = 0;
            T elem = stack.peek();
            stack.pop();
            while (!stackHelper.IsEmpty() && stackHelper.peek().compareTo(elem) < 0) {
                T peekedElem = stackHelper.peek();
                stackHelper.pop();
                stack.push(peekedElem);
                elemsToBack++;
            }
            stackHelper.push(elem);
            for (int i = 0; i < elemsToBack; i++) {
                stackHelper.push(stack.peek());
                stack.pop();
            }
        }

        while (!stackHelper.IsEmpty()) {
            stack.push(stackHelper.peek());
            stackHelper.pop();
        }
    }
}
