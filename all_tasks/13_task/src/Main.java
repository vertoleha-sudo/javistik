class Solution {
    public boolean isPerfectSquare(int num) {
        int left = 1, right = num;
        int end;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            long kub = (long) mid * mid;
            if (kub == num) {
                return true;
            } else if (kub < num) {
                left = mid + 1;
            } else if (kub > num) {
                right = mid - 1;
            } else {
                return false;
            }
        }
        return false;
    }
}