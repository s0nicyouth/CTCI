package com.syouth;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * Created by anton.ivanov on 6/20/2016.
 */
public class CTCI1 {
    public static boolean AreAllCharsUnique(String str) {
        if (str == null || str.length() == 0) {
            return true;
        }
        HashSet<Character> charsSet = new HashSet<>();
        for (int i = 0; i < str.length(); i++) {
            if (charsSet.contains(str.charAt(i))) {
                return false;
            } else {
                charsSet.add(str.charAt(i));
            }
        }

        return true;
    }

    public static void ReverseStringInPlace(char str[]) {
        if (str == null || str.length == 0) {
            return;
        }
        int length = str.length;
        int middle = (int) Math.floor(length / 2.f);

        for (int i = 0; i < middle; i++) {
            char tmp = str[i];
            str[i] = str[length - 1 - i];
            str[length - 1 - i] = tmp;
        }
    }

    public static boolean IsOneStringPermutationOfOther(String str1, String str2) {
        if (str1 == null || str2 == null) {
            return true;
        }
        if (str1.length() != str2.length()) {
            return false;
        }

        HashMap<Character, Integer> str1Map = new HashMap<>();
        for (int i = 0; i < str1.length(); i++) {
            Character charToTest = str1.charAt(i);
            Integer val = str1Map.get(charToTest);
            if (val != null) {
                str1Map.put(charToTest, val + 1);
            } else {
                str1Map.put(charToTest, 1);
            }
        }

        HashMap<Character, Integer> str2Map = new HashMap<>();
        for (int i = 0; i < str2.length(); i++) {
            Character charToTest = str2.charAt(i);
            Integer val = str2Map.get(charToTest);
            if (val != null) {
                str2Map.put(charToTest, val + 1);
            } else {
                str2Map.put(charToTest, 1);
            }
        }

        if (str1Map.size() != str2Map.size()) {
            return false;
        }

        for (HashMap.Entry<Character, Integer> ent : str1Map.entrySet()) {
            Integer val = str2Map.get(ent.getKey());
            if (val == null) {
                return false;
            } else {
                if (!val.equals(ent.getValue())) {
                    return false;
                }
            }
        }

        return true;
    }

    public static String CompressString(String str) {
        if (str == null || str.length() <= 2) {
            return str;
        }

        Character prevChar = str.charAt(0);
        int count = 1;
        StringBuilder buffer = new StringBuilder();
        buffer.append(str.charAt(0));
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) == prevChar) {
                count++;
            } else {
                buffer.append(String.valueOf(count));
                buffer.append(str.charAt(i));
                count = 1;
            }
            prevChar = str.charAt(i);
        }
        buffer.append(String.valueOf(count));

        return str.length() > buffer.length() ? buffer.toString() : str;
    }
}