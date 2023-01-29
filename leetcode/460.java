/*
 * https://leetcode.com/problems/lfu-cache/
 */



/*
 * Really, really difficult to get this in 1 go!!
 * Approach 1 from 
 * https://leetcode.com/problems/lfu-cache/solutions/2815229/lfu-cache/?orderBy=most_votes
 */ 
class LFUCache1 {

	// key : original_key; value : {frequency_of_occurence, actual_value} 
	private Map<Integer, Pair<Integer, Integer> > cache;
	// key : frequency, value : all keys that have the same frequency
	private Map<Integer, LinkedHashSet<Integer> > frequencies;
	private int minFrequency;
	private int capacity;

    public LFUCache1(int capacity) {
        cache = new HashMap<>();
        frequencies = new HashMap<>();
        minFrequency = 0;
        this.capacity = capacity;
    }
    
    private void insert(int key, int frequency, int value) {
    	cache.put(key, new Pair<>(frequency, value));
    	frequencies.putIfAbsent(frequency, new LinkedHashSet<>());
    	frequencies.get(frequency).add(key);
    }

    public int get(int key) {
        Pair<Integer, Integer> frequencyAndValue = cache.get(key);
        if (frequencyAndValue == null)
        	return -1;
        int frequency = frequencyAndValue.getKey();
        Set<Integer> keys = frequencies.get(frequency);
        keys.remove(key);
        if (minFrequency == frequency && keys.isEmpty())
        	minFrequency++;
        int value = frequencyAndValue.getValue();
        insert(key, frequency + 1, value);
        return value;
    }
    
    public void put(int key, int value) {
        if (capacity <= 0)
        	return;
        Pair<Integer, Integer> frequencyAndValue = cache.get(key);
        if (frequencyAndValue != null) { // when we're to add a new value associated with the key
        	cache.put(key, new Pair<>(frequencyAndValue.getKey(), value));
        	get(key);
        	return;
        }
        if (capacity == cache.size()) {
        	Set<Integer> keys = frequencies.get(minFrequency);
        	int keyToDelete = keys.iterator().next();
        	cache.remove(keyToDelete);
        	keys.remove(keyToDelete);
        }
        minFrequency = 1;
        insert(key, 1, value);
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */



/*
 * Approach of using 3 HashMaps from 
 * https://leetcode.com/problems/lfu-cache/solutions/94521/java-o-1-very-easy-solution-using-3-hashmaps-and-linkedhashset/comments/311118
 */
class LFUCache2 {
	
	private int minFrequency, capacity;
	private Map<Integer, Integer> keyToValue, keyToFrequency;
	private Map<Integer, LinkedHashSet<Integer> > frequencyToLRUKeys;

	public LFUCache2(int capacity) {
		this.capacity = capacity;
		this.minFrequency = 0;
		this.keyToValue = new HashMap<>();
		this.keyToFrequency = new HashMap<>();
		this.frequencyToLRUKeys = new HashMap<>();
	}

	private void putFrequency(int key, int frequency) {
		keyToFrequency.put(key, frequency);
		frequencyToLRUKeys.computeIfAbsent(frequency, ignore -> new LinkedHashSet<>()).add(key);
	}

	public int get(int key) {
		if (!keyToValue.containsKey(key))
			return -1;
		int frequency = keyToFrequency.get(key);
		frequencyToLRUKeys.get(frequency).remove(key);
		if (frequency == minFrequency && frequencyToLRUKeys.get(frequency).size() == 0)
			minFrequency++;
		putFrequency(key, frequency + 1);
		return keyToValue.get(key);
	}

	private void evict(int key) {
		frequencyToLRUKeys.get(minFrequency).remove(key);
		keyToValue.remove(key);
	}

	public void put(int key, int value) {
		if (capacity <= 0)
			return;
		if (keyToValue.containsKey(key)) {
			keyToValue.put(key, value);
			int frequency = keyToFrequency.get(key);
			frequencyToLRUKeys.get(frequency).remove(key);
			if (frequency == minFrequency && frequencyToLRUKeys.get(frequency).size() == 0)
					minFrequency++;
			putFrequency(key, frequency + 1);
			return;
		}
		if (keyToValue.size() >= capacity)
			evict(frequencyToLRUKeys.get(minFrequency).iterator().next());
		minFrequency = 1;
		putFrequency(key, minFrequency);
		keyToValue.put(key, value);
	}
}
