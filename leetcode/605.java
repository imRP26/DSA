/*
 * https://leetcode.com/problems/can-place-flowers/
 */



/*
 * Some amount of Jugaad!! - But Ad-Hoc
 */
class Solution1 {

    private boolean helper(int[] flowerbed, int n, int option) {
        int numFlowers = flowerbed.length;
        int[] flowers = new int[numFlowers];
        for (int i = 0; i < numFlowers; i++)
            flowers[i] = (option == 2) ? flowerbed[numFlowers - i - 1] : flowerbed[i];
        for (int i = 0; i < numFlowers; i++) {
            if (flowers[i] == 1)
                continue;
            if ((i == 0 && flowers[i] == 0 && flowers[i + 1] == 0) || 
                (i == numFlowers - 1 && flowers[i] == 0 && flowers[i - 1] == 0) || 
                (i > 0 && i < numFlowers - 1 && flowers[i - 1] == 0 && flowers[i + 1] == 0 && flowers[i] == 0)) {
                flowers[i] = 1;
                n--;
            }
        }
        return n <= 0;
    }

    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        if (n == 0)
            return true;
        int numFlowers = flowerbed.length;
        if (numFlowers % 2 == 0) {
            if (n > numFlowers / 2)
                return false;
        }
        else {
            if (n - 1 > numFlowers / 2)
                return false;
        }
        if (numFlowers == 1)
            return flowerbed[0] == 0;
        if (numFlowers == 2)
            return flowerbed[0] == 0 && flowerbed[1] == 0;
        return helper(flowerbed, n, 1) || helper(flowerbed, n, 2);
    }
} 



/*
 * Solution from official LC editorial -> 
 * https://leetcode.com/problems/can-place-flowers/editorial/
 */
class Solution2 {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int count = 0, numFlowers = flowerbed.length;
        for (int i = 0; i < numFlowers; i++) {
            if (flowerbed[i] == 1)
                continue;
            boolean leftPlotEmpty = (i == 0) || (flowerbed[i - 1] == 0);
            boolean rightPlotEmpty = (i == numFlowers - 1) || (flowerbed[i + 1] == 0);
            if (leftPlotEmpty && rightPlotEmpty) {
                flowerbed[i] = 1;
                count++;
                if (count >= n)
                    return true;
            }
        }
        return count >= n;
    }
}
