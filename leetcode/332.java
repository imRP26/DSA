/*
 * https://leetcode.com/problems/reconstruct-itinerary/
 */



/*
 * The airports can be considered as vertices and tickets as directed edges of 
 * a graph. Now since an itinerary is to be formed using all the tickets only 
 * once, so we can be assured that we need to find an Eulerian Path for this 
 * directed graph.
 * An Eulerian Path uses every edge in a graph only once.
 * 
 * Approach from -> 
 * https://leetcode.com/problems/reconstruct-itinerary/solutions/78766/share-my-solution/?envType=daily-question&envId=2023-09-14
 */
class Solution {

    private Map<String, PriorityQueue<String> > flights = new HashMap<>();
    private List<String> path = new ArrayList<>();

    private void dfs(String departure) {
        PriorityQueue<String> arrivals = flights.get(departure);
        while (arrivals != null && !arrivals.isEmpty())
            dfs(arrivals.poll());
        path.add(0, departure);
    }

    public List<String> findItinerary(List<List<String>> tickets) {
        for (List<String> ticket : tickets) {
            flights.putIfAbsent(ticket.get(0), new PriorityQueue<>());
            flights.get(ticket.get(0)).add(ticket.get(1));
        }
        dfs("JFK");
        return path;
    }
}



/*
 * Refer to William Fiset's videos on Eulerian Path / Circuit!
 * https://youtu.be/xR4sGgwtR2I?si=A41Zq_FEQJ2Tha4P
 * https://www.youtube.com/watch?v=8MpoO2zA2l4
 * https://youtu.be/QQ3jO1dKjYQ?si=CQqcYLNYd-eX_7lX
 */
