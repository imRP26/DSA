/*
 * https://leetcode.com/problems/finding-pairs-with-a-certain-sum
 */



/**
 * Your FindSumPairs object will be instantiated and called as such:
 * FindSumPairs obj = new FindSumPairs(nums1, nums2);
 * obj.add(index,val);
 * int param_2 = obj.count(tot);
 */



/*
 * Directly taken from Hint1 - also the same 1 as mentioned in the official LC editorial
 */
class FindSumPairs {

  private int[] nums1;
  private int[] nums2;
  private Map<Integer, Integer> numCounts;

  public FindSumPairs(int[] nums1, int[] nums2) {
    this.nums1 = nums1;
    this.nums2 = nums2;
    numCounts = new HashMap<>();
    for (int num: nums2) {
      numCounts.put(num, numCounts.getOrDefault(num, 0) + 1);
    }
  }
    
  public void add(int index, int val) {
    numCounts.put(nums2[index], numCounts.get(nums2[index]) - 1);
    if (numCounts.get(nums2[index]) == 0) {
      numCounts.remove(nums2[index]);
    }
    nums2[index] += val;
    numCounts.put(nums2[index], numCounts.getOrDefault(nums2[index], 0) + 1);
  }
    
  public int count(int tot) {
    int numSumPairs = 0;
    for (int num: nums1) {
      int remainingValue = tot - num;
      numSumPairs += numCounts.getOrDefault(remainingValue, 0);
    }
    return numSumPairs;
  }
}



/*
 * Referred from -> 
 * https://leetcode.com/problems/finding-pairs-with-a-certain-sum/solutions/6925404/unordered-mapsortbinary-search-c37ms-bea-atq2/
 *
 * Also have a look at this comment -> 
 * https://leetcode.com/problems/finding-pairs-with-a-certain-sum/solutions/6925404/unordered-mapsortbinary-search-c37ms-bea-atq2/comments/3065026/
 */
class FindSumPairs {

  private int[] nums1;
  private int[] nums2;
  private Map<Integer, Integer> numCounts;
  private int maxNumInNums2 = 0;

  public FindSumPairs(int[] nums1, int[] nums2) {
    this.nums1 = nums1;
    this.nums2 = nums2;
    Arrays.sort(nums1);
    numCounts = new HashMap<>();
    for (int num: nums2) {
      numCounts.put(num, numCounts.getOrDefault(num, 0) + 1);
      maxNumInNums2 = Math.max(maxNumInNums2, num);
    }
  }
    
  public void add(int index, int val) { // O(1)
    numCounts.put(nums2[index], numCounts.get(nums2[index]) - 1);
    if (numCounts.get(nums2[index]) == 0) {
      numCounts.remove(nums2[index]);
    }
    nums2[index] += val;
    numCounts.put(nums2[index], numCounts.getOrDefault(nums2[index], 0) + 1);
    maxNumInNums2 = Math.max(maxNumInNums2, nums2[index]);
  }
    
  public int count(int tot) {
    int numSumPairs = 0;
    int lowerBound = getLowerBound(Math.max(1, tot - maxNumInNums2));
    for (int i = lowerBound; i < nums1.length; i++) {
      if (nums1[i] >= tot) {
        break;
      }
      numSumPairs += numCounts.getOrDefault(tot - nums1[i], 0);
    }
    return numSumPairs;
  }

  private int getLowerBound(int val) {
    int low = 0;
    int high = nums1.length - 1;
    int lowerBound = nums1.length;
    while (low <= high) {
      int mid = low + (high - low) / 2;
      if (nums1[mid] >= val) {
        lowerBound = mid;
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }
    return lowerBound;
  }
}
