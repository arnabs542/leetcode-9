/*
There are N workers.  The i-th worker has a quality[i] and a minimum wage expectation wage[i].

Now we want to hire exactly K workers to form a paid group.  When hiring a group of K workers, we must pay them according to the following rules:

Every worker in the paid group should be paid in the ratio of their quality compared to other workers in the paid group.
Every worker in the paid group must be paid at least their minimum wage expectation.
Return the least amount of money needed to form a paid group satisfying the above conditions.



Example 1:

Input: quality = [10,20,5], wage = [70,50,30], K = 2
Output: 105.00000
Explanation: We pay 70 to 0-th worker and 35 to 2-th worker.
Example 2:

Input: quality = [3,1,10,10,1], wage = [4,8,2,2,7], K = 3
Output: 30.66667
Explanation: We pay 4 to 0-th worker, 13.33333 to 2-th and 3-th workers seperately.


Note:

1 <= K <= N <= 10000, where N = quality.length = wage.length
1 <= quality[i] <= 10000
1 <= wage[i] <= 10000
Answers within 10^-5 of the correct answer will be considered correct.
*/

// priority queue， 时间稍微比comparable方法慢一点
public double mincostToHireWorkers(int[] q, int[] w, int K) {
    double[][] workers = new double[q.length][2];
    for (int i = 0; i < q.length; ++i)
        workers[i] = new double[]{(double)(w[i]) / q[i], (double)q[i]};
    Arrays.sort(workers, (a, b) -> Double.compare(a[0], b[0]));
    double res = Double.MAX_VALUE, qsum = 0;
    PriorityQueue<Double> pq = new PriorityQueue<>();
    for (double[] worker: workers) {
        qsum += worker[1];
        pq.add(-worker[1]);
        if (pq.size() > K) qsum += pq.poll();
        if (pq.size() == K) res = Math.min(res, qsum * worker[0]);
    }
    return res;
}

// similar implement with Comparable


// brute force using greedy, TLE
// loop to find the most suitable factor
class Solution {
    public double mincostToHireWorkers(int[] quality, int[] wage, int K) {
        int N = quality.length;
        double ans = 1e9;

        for (int captain = 0; captain < N; ++captain) {
            // Must pay at least wage[captain] / quality[captain] per qual
            double factor = (double) wage[captain] / quality[captain];
            double prices[] = new double[N];
            int t = 0;
            for (int worker = 0; worker < N; ++worker) {
                double price = factor * quality[worker];
                if (price < wage[worker]) continue;
                prices[t++] = price;
            }

            if (t < K) continue;
            Arrays.sort(prices, 0, t);
            double cand = 0;
            for (int i = 0; i < K; ++i)
                cand += prices[i];
            ans = Math.min(ans, cand);
        }

        return ans;
    }
}
