package com.example.stajxml;

public abstract class RjexNumber {
    private RjexNumber() {
    }

    public static String IntToString(int n) {
        String s = String.valueOf(n);
        return StringToString(s);
    }

    public static String StringToString(String s) {
        String s2 = "";
        int l = s.length();

        for (int i = l - 1; i >= 0; i--)
            s2 += s.charAt(i);

        s = "";

        for (int i = 0; i + 3 <= l; i += 3) {
            s += s2.substring(i, i + 3) + " ";
        }

        l = l % 3;
        s += s2.substring(s2.length() - l);

        s2 = "";
        l = s.length();

        for (int i = l - 1; i >= 0; i--)
            s2 += s.charAt(i);

        return s2.trim();
    }
}