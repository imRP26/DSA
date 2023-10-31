/*
 * https://thejoboverflow.com/problem/61/
 */
import java.io.*;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String day = br.readLine().trim();
        int k = Integer.parseInt(br.readLine().trim());
        Map<String, Integer> map1 = new HashMap<>();
        Map<Integer, String> map2 = new HashMap<>();
        map1.put("sunday", 0);
        map2.put(0, "sunday");
        map1.put("monday", 1);
        map2.put(1, "monday");
        map1.put("tuesday", 2);
        map2.put(2, "tuesday");
        map1.put("wednesday", 3);
        map2.put(3, "wednesday");
        map1.put("thursday", 4);
        map2.put(4, "thursday");
        map1.put("friday", 5);
        map2.put(5, "friday");
        map1.put("saturday", 6);
        map2.put(6, "saturday");
        k = (k + map1.get(day)) % 7;
        System.out.println(map2.get(k));
    }
}
