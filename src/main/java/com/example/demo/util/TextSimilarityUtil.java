package com.example.demo.util;

import java.util.*;

public class TextSimilarityUtil {

    public static double similarity(String a, String b) {
        if (a == null || b == null) return 0.0;

        a = a.toLowerCase().trim();
        b = b.toLowerCase().trim();

        if (a.equals(b)) return 1.0;
        if (a.isEmpty() || b.isEmpty()) return 0.0;

        Set<String> s1 = new HashSet<>(Arrays.asList(a.split("\\s+")));
        Set<String> s2 = new HashSet<>(Arrays.asList(b.split("\\s+")));

        Set<String> intersection = new HashSet<>(s1);
        intersection.retainAll(s2);

        Set<String> union = new HashSet<>(s1);
        union.addAll(s2);

        return union.isEmpty() ? 0.0 :
                (double) intersection.size() / union.size();
    }
}
