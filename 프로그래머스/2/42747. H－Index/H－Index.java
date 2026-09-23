//생각나는건 binarySearch돌리는거?
import java.util.*;
class Solution {
    static boolean check(int mid, int[] citations){
        int up = 0;
        for(int i=0; i<citations.length; i++){
            if(citations[i] >= mid)
                up++;
        }
        
        if(up >= mid)
            return true;
        
        return false;
    }
    
    public int solution(int[] citations) {
        int left = 0;
        int right = 1000;
        int answer = 0;
        
        while(left <= right){
            int mid = (left+right) / 2;
            
            if(check(mid, citations)){
                answer = Math.max(answer,mid);
                left = mid+1;
            }
            else{
                right = mid-1;
            }
        }
        
        return answer;
    }
}