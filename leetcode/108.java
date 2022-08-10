import java.util.*;

/*
 * https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
 */



// Definition for a binary tree node
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {
    }
    TreeNode(int val) {
        this.val = val;
    }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}



/*
 * My Naive Solution - find the mid element and make that as the current root 
 * and then recur for the left and right subarrays.
 */
class Solution1 {
    
    TreeNode helper(int low, int high, int[] nums) {
        if (low > high || high < 0 || low == nums.length)
            return null;
        if (low == high)
            return new TreeNode(nums[low]);
        int mid = low + (high - low) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        if (low <= mid - 1 && low >= 0 && low < nums.length && mid - 1 >= 0 && mid - 1 < nums.length)
            root.left = helper(low, mid - 1, nums);
        if (mid + 1 <= high && mid + 1 >= 0 && mid + 1 < nums.length && high >= 0 && high < nums.length)
            root.right = helper(mid + 1, high, nums);
        return root;
    }
    
    public TreeNode sortedArrayToBST(int[] nums) {
        int low = 0, high = nums.length - 1, mid = low + (high - low) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = helper(low, mid - 1, nums);
        root.right = helper(mid + 1, high, nums);    
        return root; 
    }
}



// Much better solution in terms of readability
class Solution2 {
    
    TreeNode helper(int low, int high, int[] nums) {
        if (low > high)
            return null;
        int mid = low + (high - low) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = helper(low, mid - 1, nums);
        root.right = helper(mid + 1, high, nums);
        return root;
    }
    
    public TreeNode sortedArrayToBST(int[] nums) {
        return helper(0, nums.length - 1, nums); 
    }
}



// Preorder Traversal - choosing random middle element as root
class Solution3 {
    int[] nums;
    Random rand = new Random();

    TreeNode helper(int low, int high) {
        if (low > high)
            return null;
        int index = low + (high - low) / 2;
        if ((low + high) % 2 == 1)
            index += rand.nextInt(2);
        TreeNode root = new TreeNode(nums[index]);
        root.left = helper(low, index - 1);
        root.right = helper(index + 1, high);
        return root;
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        this.nums = nums;
        return helper(0, nums.length - 1);
    }
}