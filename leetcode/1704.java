/*
 * Instead of applying toLowerCase() upon the entire string, we can apply that 
 * on individual chars.
 */
class Solution {
    
    public boolean isVowel(char c) {
        c = Character.toLowerCase(c);
        return (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u');
    }
    
    public boolean halvesAreAlike(String s) {
        int len = s.length(), num1 = 0, num2 = 0;
        for (int i = 0; i < len; i++) {
            if (i < len / 2 && isVowel(s.charAt(i)))
                num1++;
            else if (i >= len / 2 && isVowel(s.charAt(i)))
                num2++;
        }
        return (num1 == num2);
    }
}
