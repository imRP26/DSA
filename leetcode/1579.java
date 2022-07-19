import java.util.*;

/*
 * https://leetcode.com/problems/remove-max-number-of-edges-to-keep-graph-fully-traversable/
 */



/*
 * We initially assume that the given graph is empty, and we try constructing 
 * the graph using the minimum number of edges possible so that both Bob and 
 * Alice can traverse.
 * In order to get the maximum bang for our buck, we start building our graph 
 * using edges of type 3, since this type of edge caters to both Alice and Bob.
 * We also maintain 2 separate union-find structures for Alice and Bob.
 * Upon encountering an edge, if it can be unioned, then we increment the 
 * count of covered vertices corresponding to the edge type, else, we increment 
 * the count of edges that can be removed.
 * We do a final check to ensure that both Alice and Bob can cover all the given 
 * 'n' vertices.
 */
class Solution {
    
    int findParent(int u, int[] parent) {
        while (u != parent[u]) {
            parent[u] = parent[parent[u]]; // Union by Path Compression
            u = parent[u];
        }
        return u;
    }
    
    boolean union(int u, int v, int[] parent) {
        int parentU = findParent(u, parent), parentV = findParent(v, parent);
        if (parentU == parentV)
            return false;
        parent[parentV] = parentU;
        return true;
    }
    
    public int maxNumEdgesToRemove(int n, int[][] edges) {
        int[] parentAlice = new int[n + 1];
        int[] parentBob = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parentAlice[i] = i;
            parentBob[i] = i;
        }
        //Arrays.sort(edges, (a, b) -> (b[0] - a[0]));
        Arrays.sort(edges, (a, b) -> Integer.compare(b[0], a[0]));
        int result = 0, mergedAlice = 1, mergedBob = 1;
        for (int[] edge : edges) {
            if (edge[0] == 3) {
                boolean tempAlice = union(edge[1], edge[2], parentAlice);
                boolean tempBob = union(edge[1], edge[2], parentBob);
                if (tempAlice)
                    mergedAlice++;
                if (tempBob)
                    mergedBob++;
                if (!tempAlice && !tempBob)
                    result++;
            }
            else if (edge[0] == 1) {
                if (union(edge[1], edge[2], parentAlice))
                    mergedAlice++;
                else
                    result++;
            }
            else {
                if (union(edge[1], edge[2], parentBob))
                    mergedBob++;
                else
                    result++;
            }
        }
        return (mergedAlice == n && mergedBob == n) ? result : -1;
    }
}
