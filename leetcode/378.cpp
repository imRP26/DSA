#include <bits/stdc++.h>
using namespace std;

/*
 * https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/
 */

/*
 * Concept of Min-Heap :- 
 * TC = O(K * log(K)), SC = O(K)
 * https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/discuss/1322101/C%2B%2BJavaPython-MaxHeap-MinHeap-Binary-Search-Picture-Explain-Clean-and-Concise
 */
class Solution1 {
    public:
        int kthSmallest(vector<vector<int> > &matrix, int k) {
            int n = matrix.size(), result;
            priority_queue<vector<int>, vector<vector<int> >, greater<> > minHeap;
            for (int i = 0; i < min(n, k); i++)
                minHeap.push({matrix[i][0], i, 0});
            for (int i = 1; i <= k; i++) {
                auto top = minHeap.top();
                minHeap.pop();
                result = top[0];
                int row = top[1], column = top[2];
                if (column + 1 < n)
                    minHeap.push({matrix[row][column + 1], row, column + 1});
            }
            return result;
        }
};



/*
 * Concept of Max Heap :- 
 * https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/discuss/1322101/C%2B%2BJavaPython-MaxHeap-MinHeap-Binary-Search-Picture-Explain-Clean-and-Concise
 * TC = O(N * N * log(K)), SC = O(K)
 */
class Solution2 {
    public:
        int kthSmallest(vector<vector<int>>& matrix, int k) {
            int n = matrix.size();
            priority_queue<int> maxHeap;
            for (int row = 0; row < n; row++) {
                for (int column = 0; column < n; column++) {
                    maxHeap.push(matrix[row][column]);
                    if (maxHeap.size() > k)
                        maxHeap.pop();
                }
            }
            return maxHeap.top();
        }
};



/*
 * Binary Search approach from here -> 
 * https://www.youtube.com/watch?v=w36ekZYq-Ms
 */
class Solution3 {
    public:
        int kthSmallest(vector<vector<int>>& matrix, int k) {
            int n = matrix.size(), low = matrix[0][0], high = matrix[n - 1][n - 1];
            while (low < high) {
                int mid = low + (high - low) / 2, count = 0;
                for (int i = 0; i < n; i++)
                    count += upper_bound(matrix[i].begin(), matrix[i].end(), mid) - matrix[i].begin();
                if (count < k)
                    low = mid + 1;
                else
                    high = mid;
            }
            return low;
        }
};
