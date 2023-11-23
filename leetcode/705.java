/*
 * https://leetcode.com/problems/design-hashset/
 */



/*
 * As naive as it can get........
 */
class MyHashSet {

    boolean[] hashset;

    public MyHashSet() {
        hashset = new boolean[1000001];
    }
    
    public void add(int key) {
        hashset[key] = true;
    }
    
    public void remove(int key) {
        hashset[key] = false;
    }
    
    public boolean contains(int key) {
        return hashset[key];
    }
}



/*
 * Approach 1 (BucketArray) from LC Official Editorial!
 */
class Bucket {

    private LinkedList<Integer> container;

    public Bucket() {
        container = new LinkedList<Integer>();
    }

    public void insert(Integer key) {
        int index = this.container.indexOf(key);
        if (index == -1)
            this.container.addFirst(key);
    }

    public void delete(Integer key) {
        this.container.remove(key);
    }

    public boolean exists(Integer key) {
        int index = this.container.indexOf(key);
        return index != -1;
    }
}


class MyHashSet {

    private Bucket[] bucketArray;
    private int keyRange;

    public MyHashSet() {
        this.keyRange = 769;
        this.bucketArray = new Bucket[this.keyRange];
        for (int i = 0; i < this.keyRange; i++)
            this.bucketArray[i] = new Bucket();
    }
    
    public void add(int key) {
        int bucketIndex = key % this.keyRange;
        this.bucketArray[bucketIndex].insert(key);
    }
    
    public void remove(int key) {
        int bucketIndex = key % this.keyRange;
        this.bucketArray[bucketIndex].delete(key);
    }
    
    public boolean contains(int key) {
        int bucketIndex = key % this.keyRange;
        return this.bucketArray[bucketIndex].exists(key);
    }
}



