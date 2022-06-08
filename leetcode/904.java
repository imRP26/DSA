import java.util.*;

/*
 * Question Link -> https://leetcode.com/problems/fruit-into-baskets/
 * For all the explanations, refer to "304.java" in this same folder.
 */



 // My Naive Solution using HashMap -> TC = O(n), SC = O(k)
 class Solution1 {
    public int totalFruit(int[] fruits) {
        int n = fruits.length, result = 0, low = 0, high = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (high = 0; high < n; high++) {
            while (map.size() > 2) {
                int fruit = fruits[low], fruitCount = map.get(fruit);
                if (fruitCount == 1)
                    map.remove(fruit);
                else
                    map.put(fruit, fruitCount - 1);
                low++;
            }
            int fruit = fruits[high];
            if (map.containsKey(fruit))
                map.put(fruit, map.get(fruit) + 1);
            else
                map.put(fruit, 1);
            if (map.size() <= 2)
                result = Math.max(result, high - low + 1);
        }
        return result;
    }
}



// When there's an infinite stream of the fruits array -> use of TreeMap
class Solution2 {
    public int totalFruit(int[] fruits) {
        TreeMap<Integer, Integer> lastOccurence = new TreeMap<>();
        Map<Integer, Integer> inWindow = new HashMap<>();
        int low = 0, high, result = 1, n = fruits.length;
        for (high = 0; high < n; high++) {
            int fruit = fruits[high];
            while (inWindow.size() == 2 && !inWindow.containsKey(fruit)) {
                int firstRemovIndex = lastOccurence.firstKey();
                int outFruit = lastOccurence.get(firstRemovIndex);
                inWindow.remove(outFruit);
                lastOccurence.remove(firstRemovIndex);
                low = firstRemovIndex + 1;
            }
            if (inWindow.containsKey(fruit))
                lastOccurence.remove(inWindow.get(fruit));
            inWindow.put(fruit, high);
            lastOccurence.put(high, fruit);
            result = Math.max(result, high - low + 1);
        }
        return result;
    }
}



// Use of LinkedHashMap
class Solution3 {
    public int totalFruit(int[] fruits) {
        int low = 0, high = 0, n = fruits.length, result = 0;
        Map<Integer, Integer> map = new LinkedHashMap<>();
        for (high = 0; high < n; high++) {
            int fruit = fruits[high];
            if (map.containsKey(fruit))
                map.remove(fruit);
            map.put(fruit, high);
            if (map.size() == 3) {
                fruit = map.keySet().iterator().next();
                low = map.get(fruit) + 1;
                map.remove(fruit);
            }
            result = Math.max(result, high - low + 1);
        }
        return result;
    }
}



// Use of raw-bones LRU Cache
class Node {
    Node next, previous;
    int position, fruitNum;
    
    Node(int fruitNum, int position) {
        this.fruitNum = fruitNum;
        this.position = position;
    }
}

class Solution4 {
    
    Map<Integer, Node> map;
    Node head, tail;
    
    public Node removeNode(Node node) {
        map.remove(node.fruitNum);
        if (node.next != null)
            node.next.previous = node.previous;
        if (node.previous != null)
            node.previous.next = node.next;
        if (node == head)
            head = node.next;
        if (node == tail)
            tail = node.previous;
        node.previous = null;
        node.next = null;
        return node;
    }
    
    public Node appendNode(Node node) {
        map.put(node.fruitNum, node);
        if (tail == null)
            head = tail = node;
        else {
            tail.next = node;
            node.previous = tail;
            tail = node;
        }
        return node;
    }
    
    public int totalFruit(int[] fruits) {
        map = new HashMap<>();
        int result = 0, low = 0, high, n = fruits.length;
        for (high = 0; high < n; high++) {
            int fruit = fruits[high];
            if (map.containsKey(fruit)) {
                Node node = map.get(fruit);
                node.position = high;
                removeNode(node);
                appendNode(node);
            }
            else {
                Node node = new Node(fruit, high);
                appendNode(node);
            }
            if (map.size() == 3) {
                low = head.position + 1;
                removeNode(head);
            }
            result = Math.max(result, high - low + 1);
        }
        return result;
    }
}



// Use of Binary Search
class Solution {
    
    Map<Integer, Integer> map = new HashMap<>();
    
    public boolean subArrayExists(int[] fruits, int mid) {
        int n = fruits.length;
        if (n <= 2)
            return true;
        map.clear();
        for (int i = 0; i < n; i++) {
            if (i >= mid) {
                if (map.size() <= 2)
                    return true;
                int fruit = fruits[i - mid], frequency = map.get(fruit);
                if (frequency == 1)
                    map.remove(fruit);
                else
                    map.put(fruit, frequency - 1);
            }
            map.put(fruits[i], map.getOrDefault(fruits[i], 0) + 1);
        }
        return map.size() <= 2;
    }
    
    public int totalFruit(int[] fruits) {
        int low = 0, high = fruits.length, mid, result = 0;
        while (low <= high) {
            mid = low + (high - low) / 2;
            if (subArrayExists(fruits, mid)) {
                result = Math.max(result, mid);
                low = mid + 1;
            }
            else
                high = mid - 1;
        }
        return result;
    }
}



// Use of LinkedHashSet
class Solution6 {
    public int totalFruit(int[] fruits) {
        LinkedHashSet<Integer> set = new LinkedHashSet<>();
        int n = fruits.length, length = 0, prevLength = 0, result = 0;
        int[] index = new int[n + 1];
        for (int i = 0; i < n; i++) {
            int fruit = fruits[i];
            if (!set.add(fruit)) {
                set.remove(fruit);
                set.add(fruit);
            }
            length = prevLength + 1;
            if (set.size() == 3) {
                int removFruit = set.iterator().next();
                set.remove(removFruit);
                length = i - index[removFruit];
            }
            index[fruit] = i;
            prevLength = length;
            result = Math.max(result, length);
        }
        return result;
    }
}
