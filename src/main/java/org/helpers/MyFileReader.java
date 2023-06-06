package org.helpers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyFileReader {
    public static String readFile(String pathRelativeToRoot) {
        try {
            String fileStr = new String(Files.readAllBytes(Paths.get(pathRelativeToRoot)));
            return fileStr;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T[] parseArray(String string, Function<String[], T> parser) {
        List<T> res = new ArrayList<>();
        Pattern p = Pattern.compile("\\[([^\\]]+)\\]");
        Matcher m = p.matcher(string);
        while (m.find()) {
            String tuple = m.group(1);
            String[] tupleArr = tuple.split(",\\s*");
            res.add(parser.apply(tupleArr));
        }

        return (T[]) res.toArray();
    }

    public static int[][] parseArrayAsInt(String string) {
        List<int[]> res = new ArrayList<>();
        Pattern p = Pattern.compile("\\[([^\\]]+)\\]");
        Matcher m = p.matcher(string);
        while (m.find()) {
            String tuple = m.group(1);
            String[] tupleArr = tuple.split(",\\s*");
            res.add(new int[] {Integer.parseInt(tupleArr[0]), Integer.parseInt(tupleArr[1])});
        }

        return res.toArray(new int[0][0]);
    }

    public static String[][] parseArray(String string) {
        List<String[]> res = new ArrayList<>();
        Pattern p = Pattern.compile("\\[([^\\]]+)\\]");
        Matcher m = p.matcher(string);
        while (m.find()) {
            String tuple = m.group(1);
            String[] tupleArr = tuple.split(",\\s*");
            res.add(tupleArr);
        }

        return res.toArray(new String[0][0]);
    }

    public static String[] parseCommaSeparatedString(String str) {
        String[] s1 = str.split(",\\s*");
        return s1;
    }

    public static int[] parseCommaSeparatedStringAsInt(String str) {
        String[] s1 = parseCommaSeparatedString(str);
        int[] int1 = new int[s1.length];
        for(int i = 0; i < s1.length; i++) int1[i] = Integer.parseInt(s1[i]);
        return int1;
    }


}
