class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    	if (nums1.length == 0) {
    		return (nums2[nums2.length / 2 + (nums2.length % 2 == 0 ? -1 : 0)] + nums2[nums2.length / 2]) / 2.0;
    	}

    	if (nums2.length == 0) {
    		return (nums1[nums1.length / 2 + (nums1.length % 2 == 0 ? -1 : 0)] + nums1[nums1.length / 2]) / 2.0;
    	}

        int smaller_half_end_1 = nums1.length / 2 + (nums1.length % 2 == 0 ? -1 : 0);
        int bigger_half_start_1 = nums1.length / 2;
        int smaller_half_end_2 = nums2.length / 2 + (nums2.length % 2 == 0 ? -1 : 0);
        int bigger_half_start_2 = nums2.length / 2;

        return (Math.max(nums1[smaller_half_end_1], nums2[smaller_half_end_2]) + Math.min(nums1[bigger_half_start_1], nums2[bigger_half_start_2])) / 2.0;
    }
}