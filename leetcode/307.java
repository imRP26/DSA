/*
 * https://leetcode.com/problems/range-sum-query-mutable/
 */



/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(index,val);
 * int param_2 = obj.sumRange(left,right);
 */
// My Initial Approach of SegTree
class NumArray1 {

    int n;
    int[] array;
    int[] segTree;
    
    public NumArray1(int[] nums) {
        this.n = nums.length;
        this.array = new int[n];
        for (int i = 0; i < n; i++)
            this.array[i] = nums[i];
        this.segTree = new int[4 * n];
        buildTree(0, 0, n - 1);
    }
    
    public void buildTree(int node, int low, int high) {
        if (low == high)
            this.segTree[node] = this.array[low];
        else {
            int mid = low + (high - low) / 2;
            buildTree(2 * node + 1, low, mid);
            buildTree(2 * node + 2, mid + 1, high);
            this.segTree[node] = this.segTree[2 * node + 1] + this.segTree[2 * node + 2];
        }
    }
    
    public void updateTree(int node, int low, int high, int index, int val) {
        if (low == high) {
            this.segTree[node] += val - this.array[index];
            this.array[index] = val;
        }
        else {
            int mid = low + (high - low) / 2;
            if (low <= index && index <= mid)
                updateTree(2 * node + 1, low, mid, index, val);
            else
                updateTree(2 * node + 2, mid + 1, high, index, val);
            this.segTree[node] = this.segTree[2 * node + 1] + this.segTree[2 * node + 2];
        }
    }
    
    public void update(int index, int val) {
        updateTree(0, 0, n - 1, index, val);
    }
    
    public int treeSumRange(int node, int low, int high, int left, int right) {
        if (right < low || high < left)
            return 0;
        if (left <= low && right >= high)
            return this.segTree[node];
        int mid = low + (high - low) / 2;
        int q1 = treeSumRange(2 * node + 1, low, mid, left, right);
        int q2 = treeSumRange(2 * node + 2, mid + 1, high, left, right);
        return (q1 + q2);
    }
    
    public int sumRange(int left, int right) {
        return treeSumRange(0, 0, n - 1, left, right);
    }
}



// Another method of representing the segtree, but using the same concept
class NumArray2 {

    class segmentTreeNode {
        int start, end, sum;
        segmentTreeNode left, right;

        public segmentTreeNode(int low, int high) {
            this.start = low;
            this.end = high;
            this.left = null;
            this.right = null;
            this.sum = 0;
        }
    }
    
    segmentTreeNode root = null;

    segmentTreeNode buildSegmentTree(int[] nums, int start, int end) {
        if (start > end)
            return null;
        segmentTreeNode node = new segmentTreeNode(start, end);
        if (start == end)
            node.sum = nums[start];
        else {
            int mid = start + (end - start) / 2;
            node.left = buildSegmentTree(nums, start, mid);
            node.right = buildSegmentTree(nums, mid + 1, end);
            node.sum = node.left.sum + node.right.sum;
        }
        return node;
    }
    
    public NumArray2(int[] nums) {
        root = buildSegmentTree(nums, 0, nums.length - 1);
    }

    void update(segmentTreeNode root, int index, int val) {
        if (root.start == root.end)
            root.sum = val;
        else {
            int mid = root.start + (root.end - root.start) / 2;
            if (index <= mid)
                update(root.left, index, val);
            else
                update(root.right, index, val);
            root.sum = root.left.sum + root.right.sum;
        }
    }
    
    public void update(int index, int val) {
        update(root, index, val);
    }

    int sumRange(segmentTreeNode root, int start, int end) {
        if (root.start == start && root.end == end)
            return root.sum;
        int mid = root.start + (root.end - root.start) / 2;
        if (mid >= end)
            return sumRange(root.left, start, end);
        if (start > mid)
            return sumRange(root.right, start, end);
        return sumRange(root.right, mid + 1, end) + sumRange(root.left, start, mid);
    }

    public int sumRange(int left, int right) {
        return sumRange(root, left, right);
    }
}
