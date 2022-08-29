import java.util.*;

/*
 * Question Link -> https://leetcode.com/problems/merge-intervals/
 */



/*
 * Sort the given array by the start points.
 * Then either expand the considered interval or expand a new one.
 */
class Solution1 {
    public int[][] merge(int[][] intervals) {
        if (intervals.length <= 1)
            return intervals;
        Arrays.sort(intervals, (i1, i2) -> Integer.compare(i1[0], i2[0]));
        List<int[]> result = new ArrayList<>();
        int[] newInterval = intervals[0];
        result.add(newInterval);
        for (int[] interval : intervals) {
            if (interval[0] <= newInterval[1])
                newInterval[1] = Math.max(newInterval[1], interval[1]);
            else {
                newInterval = interval;
                result.add(newInterval);
            }
        }
        return result.toArray(new int[result.size()][]);
    }
}



// Another (more believable?) way
class Solution2 {
    public int[][] merge(int[][] intervals) {
        if (intervals.length <= 1)
            return intervals;
        List<int[]> result = new ArrayList<>();
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        int start = intervals[0][0], end = intervals[0][1];
        for (int[] interval : intervals) {
            if (interval[0] <= end)
                end = Math.max(end, interval[1]);
            else {
                result.add(new int[] {start, end});
                start = interval[0];
                end = interval[1];
            }
        }
        result.add(new int[] {start, end});
        return result.toArray(new int[0][]);
    }
}



// Determining an interval using counts
class Solution3 {
    public int[][] merge(int[][] intervals) {
        if (intervals.length <= 1)
            return intervals;
        List<int[]> list = new ArrayList<>();
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int[] interval : intervals) {
            map.put(interval[0], map.getOrDefault(interval[0], 0) + 1);
            map.put(interval[1], map.getOrDefault(interval[1], 0) - 1);
        }
        int count = 0, start = Integer.MAX_VALUE, end = Integer.MIN_VALUE;
        for (int k : map.keySet()) {
            count += map.get(k);
            start = Math.min(start, k);
            end = Math.max(end, k);
            if (count == 0) {
                list.add(new int[] {start, end});
                start = Integer.MAX_VALUE;
                end = Integer.MIN_VALUE;
            }
        }
        return list.toArray(new int[list.size()][2]);
    }
}



/*
 * FB Follow-Up Question
 * Adding intervals & merging them for a large stream of intervals...
 * An Interval Tree is useful for solving the above issue...
 */
class Solution4 {

    class intervalTreeNode {
        int low, high, max;
        intervalTreeNode left, right;
        intervalTreeNode(int l, int h) {
            this.low = l;
            this.high = h;
            this.max = h;
            this.left = this.right = null;
        }
    }

    intervalTreeNode add(intervalTreeNode root, int[] interval) {
        if (root == null)
            return new intervalTreeNode(interval[0], interval[1]);
        if (interval[0] < root.low)
            root.left = add(root.left, interval);
        else
            root.right = add(root.right, interval);
        return root;
    }

    List<int[]> merge(intervalTreeNode root) {
        List<int[]> list = new ArrayList<>();
        if (root == null)
            return list;
        List<int[]> left = merge(root.left);
        List<int[]> right = merge(root.right);
        boolean inserted = false;
        for (int[] interval : left) {
            if (interval[1] >= root.low) {
                inserted = true;
                interval[0] = Math.min(interval[0], root.low);
                interval[1] = Math.max(interval[1], root.high);
                root = new intervalTreeNode(interval[0], interval[1]);
            }
            list.add(interval);
        }
        if (!inserted)
            list.add(new int[] {root.low, root.high});
        for (int[] interval : right) {
            if (interval[0] <= root.high) {
                inserted = true;
                interval[0] = Math.min(interval[0], root.low);
                interval[1] = Math.max(interval[1], root.high);
                list.remove(list.size() - 1);
            }
            list.add(interval);
        }
        return list;
    }

    public int[][] merge(int[][] intervals) {
        intervalTreeNode root = new intervalTreeNode(intervals[0][0], intervals[0][1]);
        for (int i = 1; i < intervals.length; i++)
            add(root, intervals[i]);
        List<int[]> list = merge(root);
        int[][] result = new int[list.size()][2];
        for (int i = 0; i < result.length; i++)
            result[i] = list.get(i);
        return result;
    }
}
