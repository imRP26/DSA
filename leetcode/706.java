/*
 * https://leetcode.com/problems/design-hashmap/
 */



/*
 * As naive as they come....
 */
class MyHashMap {

    private int[] hashmap;

    public MyHashMap() {
        hashmap = new int[1000001];
        Arrays.fill(hashmap, -1);
    }
    
    public void put(int key, int value) {
        hashmap[key] = value;
    }
    
    public int get(int key) {
        return hashmap[key];
    }
    
    public void remove(int key) {
        hashmap[key] = -1;
    }
}



/*
 * Approach from LC Official Editorial! -> I need to stop making a mockery out of myself!
 */
class Pair<U, V> {
    
    public U first;
    public V second;

    public Pair(U first, V second) {
        this.first = first;
        this.second = second;
    }
}


class Bucket {

    private List<Pair<Integer, Integer> > bucket;

    public Bucket() {
        this.bucket = new LinkedList<Pair<Integer, Integer> >();
    }

    public Integer get(Integer key) {
        for (Pair<Integer, Integer> pair : this.bucket) {
            if (pair.first.equals(key))
                return pair.second;
        }
        return -1;
    }

    public void update(Integer key, Integer value) {
        boolean found = false;
        for (Pair<Integer, Integer> pair : this.bucket) {
            if (pair.first.equals(key)) {
                pair.second = value;
                found = true;
                break;
            }
        }
        if (!found)
            this.bucket.add(new Pair<Integer, Integer> (key, value));
    }

    public void remove(Integer key) {
        for (Pair<Integer, Integer> pair : this.bucket) {
            if (pair.first.equals(key)) {
                this.bucket.remove(pair);
                break;
            }
        }
    }
}


class MyHashMap {

    private int keySpace;
    private List<Bucket> hashTable;

    public MyHashMap() {
        this.keySpace = 2069; // a large prime number
        this.hashTable = new ArrayList<>();
        // Open Addressing to take care of Collisions!
        for (int i = 0; i < this.keySpace; i++) 
            this.hashTable.add(new Bucket());
    }
    
    public void put(int key, int value) {
        int hashKey = key % this.keySpace;
        this.hashTable.get(hashKey).update(key, value);
    }
    
    public int get(int key) {
        int hashKey = key % this.keySpace;
        return this.hashTable.get(hashKey).get(key);
    }
    
    public void remove(int key) {
        int hashKey = key % this.keySpace;
        this.hashTable.get(hashKey).remove(key);
    }
}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */
