/*
 * https://leetcode.com/problems/shopping-offers/
 */



/*
 * Approach of Simple Recursion (#FML) from -> 
 * https://leetcode.com/problems/shopping-offers/solutions/105212/very-easy-to-understand-java-solution-beats-95-with-explanation/comments/137618
 */
class Solution {

    private int directPurchase(List<Integer> price, List<Integer> needs) {
        int cost = 0;
        for (int i = 0; i < needs.size(); i++)
            cost += price.get(i) * needs.get(i);
        return cost;
    }

    private boolean isValidOffer(List<Integer> offer, List<Integer> needs) {
        for (int i = 0; i < needs.size(); i++) {
            if (needs.get(i) < offer.get(i))
                return false;
        }
        return true;
    }

    private int backtrack(List<Integer> price, List<List<Integer> > special, List<Integer> needs, int idx) {
        int localMinCost = directPurchase(price, needs);
        for (int i = idx; i < special.size(); i++) {
            List<Integer> offer = special.get(i);
            if (isValidOffer(offer, needs)) {
                List<Integer> tempNeeds = new ArrayList<>();
                for (int j = 0; j < needs.size(); j++)
                    tempNeeds.add(needs.get(j) - offer.get(j));
                localMinCost = Math.min(localMinCost, offer.get(offer.size() - 1) + backtrack(price, special, tempNeeds, i));
            }
        }
        return localMinCost;
    }

    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        return backtrack(price, special, needs, 0);
    }
}
