class Solution {
    public int firstUniqChar(String s) {
        char c;
        int[] all = new int[26];
        for (int i = 0; i < s.length(); i++) {
            c = s.charAt(i);
            all[c - 'a']++;
        }
        for (int j = 0; j < s.length(); j++) {
            if (all[s.charAt(j) - 'a'] == 1) {
                return j;
            }
        }
        return -1;
    }
}