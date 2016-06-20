package com.syouth.tests;

import com.syouth.CTCI1;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by anton.ivanov on 6/20/2016.
 */
public class CTC1Tests {
    @Test
    public void testAllUnique() {
        String str1 = "abcdefghijklmnopqrstuvwxyz";
        Assert.assertTrue(CTCI1.AreAllCharsUnique(str1));
        str1 = "t";
        Assert.assertTrue(CTCI1.AreAllCharsUnique(str1));
    }

    @Test
    public void testNotAllUnique() {
        String str1 = "aabcdefghijklmnopqrstuvwxyz";
        Assert.assertFalse(CTCI1.AreAllCharsUnique(str1));
        str1 = "abcdefghijklmnopqrstuvwxyza";
        Assert.assertFalse(CTCI1.AreAllCharsUnique(str1));
        str1 = "ttttttaaaaaazzzzz";
        Assert.assertFalse(CTCI1.AreAllCharsUnique(str1));
    }

    @Test
    public void testEmptyOrNull() {
        String str1 = "";
        Assert.assertTrue(CTCI1.AreAllCharsUnique(str1));
        Assert.assertTrue(CTCI1.AreAllCharsUnique(null));
    }

    @Test
    public void testReverse() {
        char str[] = {'1', '2', '3', '4', '5', '6'};
        CTCI1.ReverseStringInPlace(str);
        Assert.assertEquals("654321", new String(str));
        char str1[] = {'1', '2', '3', '4', '5'};
        CTCI1.ReverseStringInPlace(str1);
        Assert.assertEquals("54321", new String(str1));
    }

    @Test
    public void testStringPermutationTrue() {
        String str1 = "abcdef";
        String str2 = "fbceda";
        Assert.assertTrue(CTCI1.IsOneStringPermutationOfOther(str1, str2));
        str1 = "aaaaaa";
        str2 = "aaaaaa";
        Assert.assertTrue(CTCI1.IsOneStringPermutationOfOther(str1, str2));
        str1 = "afgav";
        str2 = "aavfg";
        Assert.assertTrue(CTCI1.IsOneStringPermutationOfOther(str1, str2));
    }

    @Test
    public void testStringPermutationFalse() {
        String str1 = "avb";
        String str2 = "asfasdad";
        Assert.assertFalse(CTCI1.IsOneStringPermutationOfOther(str1, str2));
        str1 = "avb";
        str2 = "abc";
        Assert.assertFalse(CTCI1.IsOneStringPermutationOfOther(str1, str2));
    }

    @Test
    public void testCompressOk() {
        String str = "aabcccccaaa";
        Assert.assertEquals("a2b1c5a3", CTCI1.CompressString(str));
        str = "aaaaaaaaaaaaaaar";
        Assert.assertEquals("a15r1", CTCI1.CompressString(str));
    }

    @Test
    public void testCompressNotOk() {
        String str = "a";
        Assert.assertEquals("a", CTCI1.CompressString(str));
        str = "aa";
        Assert.assertEquals("aa", CTCI1.CompressString(str));
        str = "abcdefgh";
        Assert.assertEquals("abcdefgh", CTCI1.CompressString(str));
    }
}
