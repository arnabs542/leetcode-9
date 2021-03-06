/*
There are a row of n houses, each house can be painted with one of the k colors. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x k cost matrix. For example, costs[0][0] is the cost of painting house 0 with color 0; costs[1][2] is the cost of painting house 1 with color 2, and so on... Find the minimum cost to paint all houses.

Note:
All costs are positive integers.

Example:

Input: [[1,5,3],[2,9,4]]
Output: 5
Explanation: Paint house 0 into color 0, paint house 1 into color 2. Minimum cost: 1 + 4 = 5;
             Or paint house 0 into color 2, paint house 1 into color 0. Minimum cost: 3 + 2 = 5.
Follow up:
Could you solve it in O(nk) runtime?
*/
class Solution {
    public int minCostII(int[][] costs) {
        if (costs == null || costs.length == 0) return 0;

        // n is number of houses, k is number of colors
        int n = costs.length, k = costs[0].length;
        // min1 is the index of the 1st-smallest cost till previous house
        // min2 is the index of the 2nd-smallest cost till previous house
        // two helper values gurantees there will be one value that is one different color from the last house
        int min1 = -1, min2 = -1;

        // traverse the house
        // we can guarantee two helper values is enough
        for (int i = 0; i < n; i++) {
            int last1 = min1, last2 = min2;
            min1 = -1; min2 = -1;

            // choosing a color
            for (int j = 0; j < k; j++) {
                if(j != last1){
                    // in most cases, if the color is not the same as previous one
                    // we choose the optmial choice for last step
                    costs[i][j] += last1 < 0 ? 0 : costs[i-1][last1];
                } else {
                    // otherwise, we choose the second optimal color choice, which is guranteed to
                    // be different from previous color
                    costs[i][j] += last2 < 0 ? 0 : costs[i-1][last2];
                }

                // find the indices of 1st and 2nd smallest cost of painting current house i
                if(min1 < 0 || costs[i][j] < costs[i][min1]){
                    // update two values, the new value is better that both
                    min2 = min1;
                    min1 = j;
                } else if (min2 < 0 || costs[i][j] < costs[i][min2]){
                    // update the second best value
                    min2 = j;
                }
            }
        }

        return costs[n - 1][min1];
    }
}
