class Solution {
    public boolean isPalindrome(int x) {
        String orig = Integer.toString(x);
        StringBuilder reversed = new StringBuilder(orig).reverse();
        return orig.equals(reversed.toString());
    }
}