/*
In a row of seats, 1 represents a person sitting in that seat, and 0 represents that the seat is empty.

There is at least one empty seat, and at least one person sitting.

Alex wants to sit in the seat such that the distance between him and the closest person to him is maximized.

Return that maximum distance to closest person.

Example 1:

Input: [1,0,0,0,1,0,1]
Output: 2
Explanation:
If Alex sits in the second open seat (seats[2]), then the closest person has distance 2.
If Alex sits in any other open seat, the closest person has distance 1.
Thus, the maximum distance to the closest person is 2.
Example 2:

Input: [1,0,0,0]
Output: 3
Explanation:
If Alex sits in the last seat, the closest person is 3 seats away.
This is the maximum distance possible, so the answer is 3.
Note:

1 <= seats.length <= 20000
seats contains only 0s or 1s, at least one 0, and at least one 1.
*/

// my two pass solution
class Solution {
    public int maxDistToClosest(int[] seats) {
        List<Integer> sitting = new ArrayList<>();
        for (int i = 0; i < seats.length; i++) {
            if (seats[i] == 1) sitting.add(i);
        }
        int maximum = 1;
        for (int i = 0; i < sitting.size(); i++) {
            int current = sitting.get(i);
            if (i == 0 && current > 0) maximum = Math.max(maximum, current);
            if (i < sitting.size()-1) {
                int next = sitting.get(i+1);
                maximum = Math.max(maximum, (next-current)/2);
            }
            if (i == sitting.size()-1) {
                maximum = Math.max(maximum, seats.length-current-1);
            }
        }
        return maximum;
    }
}

// actually, we can keep track of the previous pointer and directly modify the result
public int maxDistToClosest(int[] seats) {
    int res = 0, n = seats.length, last = -1;
    for (int i = 0; i < n; ++i) {
        if (seats[i] == 1) {
            res = last < 0 ? i : Math.max(res, (i - last) / 2);
            last = i;
        }
    }
    res = Math.max(res, n - last - 1);
    return res;
}
