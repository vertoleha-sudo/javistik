class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0;
        int now = 0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] == 1) {
                now += 1;
            } else {
                now = 0;
            }
            if (now > max) {
                max = now;
            }
        }
        return max;
    }
}