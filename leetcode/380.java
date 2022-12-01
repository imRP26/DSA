import java.util.*;


/*
 * Solution 1
 */
class RandomizedSet1 {

    List<Integer> nums;
    Map<Integer, Integer> locations;
    java.util.Random rand = new java.util.Random();

    public RandomizedSet1() {
        nums = new ArrayList<Integer>();
        locations = new HashMap<Integer, Integer>();
    }
    
    public boolean insert(int val) {
        boolean contains = locations.containsKey(val);
        if (contains)
            return false;
        locations.put(val, nums.size());
        nums.add(val);
        return true;
    }
    
    public boolean remove(int val) {
        boolean contains = locations.containsKey(val);
        if (!contains)
            return false;
        int location = locations.get(val);
        if (location < nums.size() - 1) {
            int lastValue = nums.get(nums.size() - 1);
            nums.set(location, lastValue);
            locations.put(lastValue, location);
        }
        locations.remove(val);
        nums.remove(nums.size() - 1);
        return true;
    }
    
    public int getRandom() {
        return nums.get(rand.nextInt(nums.size()));
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */



/*
 * Solution 2 -> 
 * https://leetcode.com/problems/insert-delete-getrandom-o1/discuss/85425/Java-Solution-(Beats-99.20)-Using-HashMap-and-ArrayList-with-Explanation
 */
class RandomizedSet2 {

    Map<Integer, Integer> valueToIndex;
    List<Integer> list;

    public RandomizedSet() {
        valueToIndex = new HashMap<>();
        list = new ArrayList<>();
    }

    public boolean insert(int val) {
        if (valueToIndex.containsKey(val))
            return false;
        valueToIndex.put(val, list.size());
        list.add(val);
        return true;
    }

    public boolean remove(int val) {
        int index = valueToIndex.getOrDefault(val, -1);
        if (index == -1)
            return false;
        Collections.swap(list, index, list.size() - 1);
        int swappedWith = list.get(index);
        valueToIndex.put(swappedWith, index);
        list.remove(list.size() - 1); // O(1)
        valueToIndex.remove(val);
        return true;
    }

    public int getRandom() {
        int low = 0, high = list.size();
        int index = (int)(Math.random() * (high - low) + low);
        return list.get(index);
    }
}
