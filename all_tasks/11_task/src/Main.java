class Solution {
    public boolean isPalindrome(String s) {
        StringBuilder cleaner = new StringBuilder();
        s = s.toLowerCase();
        for (char c : s.toCharArray()) {
            if ((c >= 'a' && c <= 'z') || (c >= '0' && c <= '9')) {
                cleaner.append(c);
            }
        }
        String cleaned = cleaner.toString();
        String reversed = cleaner.reverse().toString();
        return cleaned.equals(reversed);
    }
}