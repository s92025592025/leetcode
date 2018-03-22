# Maximum Sum of 3 Non-Overlapping Subarrays
 * Problem Num: 689

## Thoughts
First of all, I think this also an amazing way of "divide and conquer" way of programming which was intriduced in CSE332. The method I discovered on the discussion borad(great thanks to those people) was basically take a the mid subarray sum as the pivot, and add what ever is the largest subarray sum in both right and left protion. By this way, we could run this program with O(N) runtime since we only need to move the pivot along the array once, and getting the largest subarray sum in both right and left part can be done in O(1)(discuss in next 2 paragraphs).

To keep track the largest subarray sum in the left, we have to use a Queue to store the largest subarray sum as we go through the array in order. We then keep removing from Queue as the left portion expand as the pivot moves.

As for the largest subarray sum in the right, we have to use a Stack, which store the largest subarray sum in reverse order. As we srink the right portion as mid pivot moves, we pop the Stack.

