package com.syouth.tests;

import com.syouth.CTCI3;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by anton.ivanov on 7/21/2016.
 */
public class CTCI3Tests {

    @Test
    public void testMinStack() {
        {
            CTCI3.MinStack<Integer> stack = new CTCI3.MinStack<>();
            stack.push(1);
            stack.push(0);
            stack.push(10);
            stack.push(-1101);
            stack.push(12);
            stack.push(0);
            stack.push(11);
            Assert.assertEquals(-1101, stack.min().intValue());
        }
        {
            CTCI3.MinStack<Integer> stack = new CTCI3.MinStack<>();
            Assert.assertEquals(true, stack.IsEmpty());
        }
        {
            CTCI3.MinStack<Integer> stack = new CTCI3.MinStack<>();
            stack.push(1);
            Assert.assertEquals(false, stack.IsEmpty());
            stack.pop();
            Assert.assertEquals(true, stack.IsEmpty());
        }
    }

    @Test
    public void testSetOfStacks() {
        {
            CTCI3.SetOfStacks<Integer> setOfStacks = new CTCI3.SetOfStacks<>(2);
            setOfStacks.push(1);
            setOfStacks.push(2);
            setOfStacks.push(3);
            setOfStacks.push(4);
            setOfStacks.push(5);
            setOfStacks.push(6);
            setOfStacks.push(7);
            CTCI3.Stack<Integer> stack = new CTCI3.Stack<>();
            stack.push(1);
            stack.push(2);
            stack.push(3);
            stack.push(4);
            stack.push(5);
            stack.push(6);
            stack.push(7);
            Assert.assertEquals(stack.peek().intValue(), setOfStacks.peek().intValue());
        }
        {
            CTCI3.SetOfStacks<Integer> setOfStacks = new CTCI3.SetOfStacks<>(2);
            setOfStacks.push(1);
            setOfStacks.push(2);
            setOfStacks.push(3);
            setOfStacks.push(4);
            setOfStacks.push(5);
            setOfStacks.push(6);
            setOfStacks.push(7);
            CTCI3.Stack<Integer> stack = new CTCI3.Stack<>();
            stack.push(1);
            stack.push(2);
            stack.push(3);
            stack.push(4);
            stack.push(5);
            stack.push(6);
            stack.push(7);
            stack.pop();
            stack.pop();
            stack.pop();
            setOfStacks.pop();
            setOfStacks.pop();
            setOfStacks.pop();
            Assert.assertEquals(stack.peek().intValue(), setOfStacks.peek().intValue());
        }
    }

    @Test
    public void testStackSort() {
        CTCI3.Stack<Integer> stackToSort = new CTCI3.Stack<>();
        stackToSort.push(0);
        stackToSort.push(-12);
        stackToSort.push(3);
        stackToSort.push(15);
        stackToSort.push(2);
        stackToSort.push(-9);
        stackToSort.push(45);
        stackToSort.push(-101);
        CTCI3.Stack<Integer> stackForChecking = new CTCI3.Stack<>();
        stackForChecking.push(-101);
        stackForChecking.push(-12);
        stackForChecking.push(-9);
        stackForChecking.push(0);
        stackForChecking.push(2);
        stackForChecking.push(3);
        stackForChecking.push(15);
        stackForChecking.push(45);
        CTCI3.SortStack(stackToSort);
        while (!stackForChecking.IsEmpty() && !stackToSort.IsEmpty()) {
            Assert.assertEquals(stackForChecking.peek().intValue(), stackToSort.peek().intValue());
            stackForChecking.pop();
            stackToSort.pop();
        }

        Assert.assertTrue(stackForChecking.IsEmpty());
        Assert.assertTrue(stackToSort.IsEmpty());
    }
}
