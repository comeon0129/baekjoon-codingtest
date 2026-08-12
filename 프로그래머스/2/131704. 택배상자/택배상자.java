import java.util.*;
class Solution {
    public int solution(int[] order) {
        Stack<Integer> s = new Stack<>();
        
        int idx = 0;
        for(int i=1; i<=order.length; i++){
            //1. 우선 보조벨트에 실기
            s.push(i);
            
            //2. 바로 기사님이 원하는 상자인지 보기
            while(!s.isEmpty() && s.peek() == order[idx]){
                s.pop();
                idx++;
            }
        }
        
        
        
        int answer = idx;
        return answer;
    }
}