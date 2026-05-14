import java.util.*;
class Solution {
    public int solution(int[] nums) {
        int answer = 0;
        int maxResult = nums.length /2; 
        HashSet<Integer> set = new HashSet<>();
        
        for(int num: nums){
            set.add(num);
        }
        
        if(set.size() < maxResult){
            answer = set.size();
        }
        else{
            answer = maxResult;
        }
        
        
        return answer;
    }
}