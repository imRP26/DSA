import java.util.*;

/*
 * https://leetcode.com/problems/dot-product-of-two-sparse-vectors/
 */



// Approach 1 -> Storing a sparse vector as a 1-D array
class SparseVector1 {

    int[] array;

    SparseVector1(int[] nums) {
        this.array = nums;
    }

    // Return the dot product of two sparse vectors
    public int dotProduct(SparseVector1 vec) {
        int result = 0;
        for (int i = 0; i < this.array.length; i++)
            result += this.array[i] * vec.array[i];
        return result;
    }
}

// Your SparseVector object will be instantiated and called as such:
// SparseVector v1 = new SparseVector(nums1);
// SparseVector v2 = new SparseVector(nums2);
// int ans = v1.dotProduct(v2);



/*
 * Store the non-0 values and their corresponding indices in a dictionary, with 
 * the index being the key. Any index that's not present corresponds to a value 
 * 0 in the input array.
 */
class SparseVector2 {
    
    Map<Integer, Integer> mapping;

    SparseVector2(int[] nums) {
        mapping = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0)
                mapping.put(i, nums[i]);
        }
    }

    public int dotProduct(SparseVector2 vec) {
        int result = 0;
        for (int i : this.mapping.keySet()) {
            if (vec.mapping.containsKey(i))
                result += this.mapping.get(i) * vec.mapping.get(i);
        }
        return result;
    }
}



/*
 * Representation of the elements of a sparse vector as a list of <index, value> 
 * pairs - 2 pointers can be used to iterate through the 2 vectors to calculate 
 * the dot product
 */
class SparseVector3 {

    List<int[]> pairs;

    SparseVector3(int[] nums) {
        this.pairs = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0)
                this.pairs.add(new int[] {i, nums[i]});
        }   
    }

    public int dotProduct(SparseVector3 vec) {
        int result = 0, p = 0, q = 0;
        while (p < this.pairs.size() && q < vec.pairs.size()) {
            if (this.pairs.get(p)[0] == vec.pairs.get(q)[0]) {
                result += pairs.get(p)[1] * vec.pairs.get(q)[1];
                p++;
                q++;
            }
            else if (this.pairs.get(p)[0] > vec.pairs.get(q)[0])
                q++;
            else
                p++;
        }
        return result;
    }
}
