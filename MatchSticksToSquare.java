// Time Complexity : O(4^n)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

// Your code here along with comments explaining your approach
/*
Compute the total of matchsticks to understand the possible length of each side. Also validate if max length
of the matchstick observed is greater than the possible length.If so, return false. Also validate if the
sum is divisible by 4 as its one of the features of square. Now sort the matchsticks to find the bigger ones
first. Try all possible combinations of the matchsticks by putting each stick on any of the 4 sides using
recursion and making sure we are not exceeding the possible length to form the square. If not, backtrack
them.
 */
class Solution {
    public boolean makesquare(int[] matchsticks) {
        int maxLength = 0;
        int sum = 0;

        for(int matchstick : matchsticks) {
            sum += matchstick;
            maxLength = Math.max(maxLength, matchstick);
        }

        int lengthOfEachSide = sum / 4;
        if(sum % 4 != 0)
            return false;
        if(maxLength > lengthOfEachSide)
            return false;

        //Sort the matchsticks such that the bigger ones can be tried first and less number of backtrack operations
        Arrays.sort(matchsticks);

        return helper(matchsticks, matchsticks.length - 1, new int[4], lengthOfEachSide);
    }

    private boolean helper(int[] matchsticks, int idx, int[] square, int lengthOfEachSide) {
        if(idx == -1)
            return true;

        for(int i = 0 ; i< 4 ; i++) {
            //check to further optimize runtime
            if(i >0 && square[i] == square[i - 1])
                continue;
            if(square[i] + matchsticks[idx] <= lengthOfEachSide) {
                square[i] += matchsticks[idx];
                if(helper(matchsticks, idx - 1, square, lengthOfEachSide))
                    return true;
                square[i] -= matchsticks[idx];
            }
        }
        return false;
    }
}