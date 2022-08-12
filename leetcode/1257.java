import java.util.*;

/*
 * https://leetcode.com/problems/smallest-common-region/
 */



/*
 * My Naive Solution - Make use of the iterative approach to compute the LCA of 
 * region1 and region2. Prior to this, the complete graph is constructed.
 */
class Solution {
    
    void dfs(int source, int ancestor, Map<Integer, Integer> parent, Map<Integer, List<Integer>> graph) {
        parent.put(source, ancestor);
        for (int i = 0; i < graph.get(source).size(); i++)
            dfs(graph.get(source).get(i), source, parent, graph);
    }
    
    public String findSmallestRegion(List<List<String>> regions, String region1, String region2) {
        int cityIndex = 1;
        Map<String, Integer> cityToIndex = new HashMap<>();
        Map<Integer, String> indexToCity = new HashMap<>();
        for (int i = 0; i < regions.size(); i++) {
            for (int j = 0; j < regions.get(i).size(); j++) {
                if (!cityToIndex.containsKey(regions.get(i).get(j))) {
                    cityToIndex.put(regions.get(i).get(j), cityIndex++);
                    indexToCity.put(cityIndex - 1, regions.get(i).get(j));
                }
            }
        }
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < regions.size(); i++) {
            int n = regions.get(i).size();
            int sourceCity = cityToIndex.get(regions.get(i).get(0));
            graph.put(sourceCity, new ArrayList<>());
            for (int j = 1; j < n; j++) {
                int destinationCity = cityToIndex.get(regions.get(i).get(j));
                graph.get(sourceCity).add(destinationCity);
                graph.putIfAbsent(destinationCity, new ArrayList<>());
            }
        }
        Map<Integer, Integer> parent = new HashMap<>();
        dfs(1, 0, parent, graph);
        Set<Integer> nodes = new HashSet<>();
        cityIndex = cityToIndex.get(region1);
        while (cityIndex != 1) {
            nodes.add(cityIndex);
            cityIndex = parent.get(cityIndex);
        }
        nodes.add(1);
        cityIndex = cityToIndex.get(region2);
        while (!nodes.contains(cityIndex))
            cityIndex = parent.get(cityIndex);
        return indexToCity.get(cityIndex);
    }
}



/*
 * A better / cleaner approach of doing the exact same thing as above
 */
class Solution2 {
    public String findSmallestRegion(List<List<String>> regions, String region1, String region2) {
        Map<String, String> parents = new HashMap<>();
        for (List<String> region : regions) {
            for (int i = 1; i < region.size(); i++)
                parents.put(region.get(i), region.get(0));
        }
        Set<String> ancestors = new HashSet<>();
        while (region1 != null) {
            ancestors.add(region1);
            region1 = parents.get(region1);
        }
        while (!ancestors.contains(region2))
            region2 = parents.get(region2);
        return region2;
    }
}
