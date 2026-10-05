class Solution {
    public long maxAlternatingSum(int[] nums) {
        long neg = Long.MIN_VALUE / 4; 

        long end  = neg; 
        long ond = neg; 
        long od = neg; 
        long ed = neg; 

        long ans = neg; 

        for(int x: nums) {
            
            long _end = Math.max(ond + x, x); 
            long _ond = end - x; 
            long _ed = Math.max(end, od + x); 
            long _od = Math.max(ond, ed - x); 

            ans = Math.max(ans, Math.max(Math.max(_end, _ed), Math.max(_ond, _od))); 
            end = _end; 
            ond = _ond; 
            ed = _ed; 
            od = _od; 
        }
        return ans; 
    }
}