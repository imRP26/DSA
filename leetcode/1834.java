/*
 * https://leetcode.com/problems/single-threaded-cpu/
 */



/*
 * Min-Heap + Sorting Approach from 
 * https://leetcode.com/problems/single-threaded-cpu/solutions/2216661/single-threaded-cpu/
 */ 
class Solution {
    public int[] getOrder(int[][] tasks) {
        int numTasks = tasks.length;
        /*
         * Sorting to be done either on the basis of minimum task processing time 
         * or minimum task index.
         * Item to be stored in the PriorityQueue -> 
         * (task_enqueue_time, task_processing_time, task_index)
         */
        PriorityQueue<int[]> nextTask = new PriorityQueue<int[]>(
            (a, b) -> (a[1] != b[1]) ? (a[1] - b[1]) : (a[2] - b[2]));
        int[][] sortedTasks = new int[numTasks][3];
        for (int i = 0; i < numTasks; i++) {
            sortedTasks[i][0] = tasks[i][0]; // task_enqueue_time
            sortedTasks[i][1] = tasks[i][1]; // task_processing_time
            sortedTasks[i][2] = i; // task_index
        }
        Arrays.sort(sortedTasks, (a, b) -> Integer.compare(a[0], b[0]));
        int[] result = new int[numTasks];
        int currTime = 0, taskIndex = 0, answerIndex = 0;
        while (taskIndex < numTasks || !nextTask.isEmpty()) {
            /*
             * When the minHeap is empty, currTime is updated to be set to next 
             * task's enqueue time.
             */
            if (nextTask.isEmpty() && currTime < sortedTasks[taskIndex][0])
                currTime = sortedTasks[taskIndex][0];
            /*
             * Pushing all those tasks into the minHeap whose 
             * enqueueTime <= currentTime
             */
            while (taskIndex < numTasks && currTime >= sortedTasks[taskIndex][0])
                nextTask.add(sortedTasks[taskIndex++]);
            int processingTime = nextTask.peek()[1], index = nextTask.peek()[2];
            nextTask.remove();
			currTime += processingTime;
            result[answerIndex++] = index;
        }
        return result;
    }
}
