/*
 * https://leetcode.com/problems/lru-cache/
 */



/*
 * Refer to Striver's video for the same - below is my commented, organic solution!
 */
class LRUCache {

    class dllnode {
        int key, val;
        dllnode next, prev;
        public dllnode(int k, int v) {
            key = k;
            val = v;
            prev = next = null;
        }
    }

    dllnode head, tail;
    Map<Integer, dllnode> map;
    int cap;

    public LRUCache(int capacity) {
        head = new dllnode(-1, -1);
        tail = new dllnode(-1, -1);
        head.next = tail;
        tail.prev = head;
        map = new HashMap<>();
        cap = capacity;
    }
    
    // removal followed by subsequent addition, assuming that the object is already present in the dll / hashmap
    public void addRemoveUtils(int key, int val) {
        // old config of the current node of interest
        dllnode node = map.get(key), nextNode = node.next, prevNode = node.prev;
        if (val != -1)
            node = new dllnode(node.key, val);
        prevNode.next = nextNode;
        nextNode.prev = prevNode;
        // change in config at the beginning of the DLL
        dllnode firstNode = head.next;
        firstNode.prev = node;
        node.next = firstNode;
        node.prev = head;
        head.next = node;
        // addition of the new dllnode
        map.put(key, node);
    }

    public void printDLL() {
        dllnode node = head;
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
        System.out.println();
    }

    public int get(int key) {
        if (!map.containsKey(key))
            return -1;
        addRemoveUtils(key, -1);
        //System.out.println("GET operation!!");
        //printDLL();
        return map.get(key).val;
    }

    public void put(int key, int value) {
        if (!map.containsKey(key)) {
            dllnode node = new dllnode(key, value);
            // removal of the LRU node at the ending of the DLL, since capacity is getting exceeded
            if (map.size() == cap) {
                dllnode lastNode = tail.prev, prevLastNode = lastNode.prev;
                prevLastNode.next = tail;
                tail.prev = prevLastNode;
                map.remove(lastNode.key);
            }
            // changes in config at the beginning of the DLL
            dllnode firstNode = head.next;
            firstNode.prev = node;
            node.next = firstNode;
            head.next = node;
            node.prev = head;
            map.put(key, node);
        }
        else
            addRemoveUtils(key, value);
        //System.out.println("PUT operation!!");
        //printDLL();
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
