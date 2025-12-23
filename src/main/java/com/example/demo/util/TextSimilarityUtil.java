package com.example.demo.util;

import java.util.*;

public class TextSimilarityUtil {
    public static double similarity(String a, String b) {
        if (a == null || b == null || a.isBlank() || b.isBlank()) return 0.0;

        a = a.toLowerCase();
        b = b.toLowerCase();

        Set<String> sa = new HashSet<>(List.of(a.split("\\s+")));
        Set<String> sb = new HashSet<>(List.of(b.split("\\s+")));

        Set<String> inter = new HashSet<>(sa);
        inter.retainAll(sb);

        Set<String> union = new HashSet<>(sa);
        union.addAll(sb);

        return union.isEmpty() ? 0.0 : (double) inter.size() / union.size();
    }
}
