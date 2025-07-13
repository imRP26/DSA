/*
 * https://leetcode.com/problems/maximum-number-of-tasks-you-can-assign/
 */



/*
 * Miscellaneous :-
 * some good info about TreeMaps -> https://stackoverflow.com/questions/27364907/treemap-lastkey-lookup-time
 * 
 * In the case of objects, java is going to use a hybrid between mergesort and insertion sort , called TimSort, which has O(n) space complexity. 
 * For primitive types, java will use a dual pivot variation of quicksort, which has O(log(n)) space complexity.
 */



/*
 * Approach of Greedy + Binary Search from LC official editorial
 */
class Solution {
  /*
   * Let's be greedy for a bit - so if we already know that we can assign 'k' tasks, then we'd select the top 'k'
   * workers having the most strength and the top 'k' tasks having the least value.
   * Continuing in that manner, if 'k' tasks are assignable, then 'k - 1' tasks are also definitely assignable!
   * Hence, binary search can be used here - monotonicity is being preserved!
   *
   * 
   */
  public int maxTaskAssign(int[] tasks, int[] workers, int pills, int strength) {
    Arrays.sort(tasks); // O(n log n)
    Arrays.sort(workers); // O(m log m)
    int low = 1, high = Math.min(workers.length, tasks.length), result = 0;
    while (low <= high) { // O(log(min(m, n)))
      int mid = low + (high - low) / 2;
      if (checkForAssignableTasks(tasks, workers, pills, strength, mid)) {
        result = mid;
        low = mid + 1;
      } else {
        high = mid - 1;
      }
    }
    return result; 
  }

  // here, we assume that 'numAssignable' tasks will always be possible!
  private boolean checkForAssignableTasks(
      int[] tasks, int[] workers, int pills, int strength, int numAssignableTasks) {
    TreeMap<Integer, Integer> workerStrength = new TreeMap<>();
    for (int i = workers.length - numAssignableTasks; i < workers.length; i++) { // O(min(m, n))
      workerStrength.put(workers[i], workerStrength.getOrDefault(workers[i], 0) + 1); // O(log(min(m, n)))
    }
    // reverse-iterating the tasks array so that we reach an endpoint sooner in case of a failure!
    for (int i = numAssignableTasks - 1; i >= 0; i--) { // O(min(m, n))
      Integer key = workerStrength.lastKey(); // O(log(min(m, n)))
      if (key >= tasks[i]) {
        // all hunky dory - no pill required here!
        workerStrength.put(key, workerStrength.get(key) - 1); // O(log(min(m, n)))
        if (workerStrength.get(key) == 0) { // O(log(min(m, n)))
          workerStrength.remove(key); // O(log(min(m, n)))
        }
      } else {
        if (pills == 0) {
          // need some lifeline, but there ain't none available!
          return false;
        }
        // worker having the minimum required strength
        key = workerStrength.ceilingKey(tasks[i] - strength); // O(log(min(m, n)))
        if (key == null) {
          // if no such worker is available!
          return false;
        }
        pills--;
        workerStrength.put(key, workerStrength.get(key) - 1); // O(log(min(m, n)))
        if (workerStrength.get(key) == 0) { // O(log(min(m, n)))
          workerStrength.remove(key); // O(log(min(m, n)))
        }
      }
    }
    return true;
  }
}



/*
 * 2nd approach - very very tough man, at this point I definitely want a pill!
 */
class Solution {
  public int maxTaskAssign(int[] tasks, int[] workers, int pills, int strength) {
    Arrays.sort(tasks); // O(n log n)
    Arrays.sort(workers); // O(m log m)
    int low = 1, high = Math.min(workers.length, tasks.length), result = 0;
    while (low <= high) { // O(log(min(m, n)))
      int mid = low + (high - low) / 2;
      if (checkForAssignableTasks(tasks, workers, pills, strength, mid)) {
        result = mid;
        low = mid + 1;
      } else {
        high = mid - 1;
      }
    }
    return result; 
  }

  private boolean checkForAssignableTasks(
      int[] tasks, int[] workers, int pills, int strength, int numAssignableTasks) {
    Deque<Integer> workerStrength = new ArrayDeque<>();
    int numWorkers = workers.length, ptr = numWorkers - 1;
    for (int i = numAssignableTasks - 1; i >= 0; i--) { // O(min(m, n))
      while (ptr >= numWorkers - numAssignableTasks && workers[ptr] + strength >= tasks[i]) {
        workerStrength.addFirst(workers[ptr]);
        ptr--;
      }
      if (workerStrength.isEmpty()) {
        return false;
      } else if (workerStrength.getLast() >= tasks[i]) {
        workerStrength.pollLast(); // O(1)
      } else {
        if (pills == 0) {
          return false;
        }
        pills--;
        workerStrength.pollFirst(); // O(1)
      }
    }    
    return true;
  }
}
