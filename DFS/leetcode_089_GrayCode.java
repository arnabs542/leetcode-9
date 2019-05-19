/*
The gray code is a binary numeral system where two successive values differ in only one bit.

Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code. A gray code sequence must begin with 0.

Example 1:

Input: 2
Output: [0,1,3,2]
Explanation:
00 - 0
01 - 1
11 - 3
10 - 2

For a given n, a gray code sequence may not be uniquely defined.
For example, [0,2,3,1] is also a valid gray code sequence.

00 - 0
10 - 2
11 - 3
01 - 1
Example 2:

Input: 0
Output: [0]
Explanation: We define the gray code sequence to begin with 0.
             A gray code sequence of n has size = 2n, which for n = 0 the size is 20 = 1.
             Therefore, for n = 0 the gray code sequence is [0].
*/
// solution 1: backtracking
// add the first half and then second half
// 00,01,11,10 -> (000,001,011,010 ) (110,111,101,100)
class Solution {
    public static List<Integer> grayCode(int n) {
	if (n < 0)
		return new ArrayList<Integer>();
	if (n == 0) {
		List<Integer> list = new ArrayList<Integer>();
		list.add(0);
		return list;
	}
    // the first half of the result
	List<Integer> tmp = grayCode(n - 1);
	List<Integer> result = new ArrayList<Integer>(tmp);
	int addNumber = 1 << (n - 1);
    // add the second half of the result
	for (int i = tmp.size() - 1; i >= 0; i--) {
		result.add(addNumber + tmp.get(i));
	}

	return result;
}
}

// a very brilliant solution
/*
My idea is to generate the sequence iteratively. For example, when n=3, we can get the result based on n=2.
00,01,11,10 -> (000,001,011,010 ) (110,111,101,100). The middle two numbers only differ at their highest bit,
 while the rest numbers of part two are exactly symmetric of part one. It is easy to see its correctness.
*/
public List<Integer> grayCode(int n) {
    List<Integer> rs=new ArrayList<Integer>();
    rs.add(0);
    for(int i=0;i<n;i++){
        int size=rs.size();
        for(int k=size-1;k>=0;k--)
            rs.add(rs.get(k) | 1<<i);
    }
    return rs;
}
