// Time Complexity : O(5^k), where k = number of digits in n
// Space Complexity : O(k)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

// Your code here along with comments explaining your approach
/*
We build the numbers using the existing 5 set of numbers by storing them in a map and creating combinations
for them by doing a recursive approach.For each valid number, we check if its rotated version is equal to
the original number and confirm if its confusing or not.
 */
class Solution {
    int count;
    public int confusingNumberII(int n) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 1);
        map.put(6, 9);
        map.put(8, 8);
        map.put(9, 6);
        map.put(0, 0);

        this.count = 0;
        dfs(map, 0l , n);
        return count;
    }

    private void dfs(Map<Integer,Integer> map, long currNum, int n) {
        if(isConfusing((int)currNum, map))
            count++;

        for(int key : map.keySet()) {
            long newNum = currNum * 10 + key;
            if(newNum != 0 && newNum <= n)
                dfs(map, newNum, n);
        }
    }

    private boolean isConfusing(int num, Map<Integer, Integer> map) {
        int temp = num;
        int result = 0;
        while(num > 0) {
            int lastDigit = num % 10;
            result = result * 10 + map.get(lastDigit);
            num = num / 10;
        }
        return result != temp;
    }
}