//스택 문제: 내 앞에 나보다 작은 놈이 있으면 무조건 죽인다
import java.util.*;
class Solution {
    public String solution(String number, int k) {
        // StringBuilder를 스택처럼 사용!
        StringBuilder sb = new StringBuilder();
        
        for(int i=0; i<number.length(); i++){
            char c = number.charAt(i);
            
            while(k>0 && sb.length() > 0 && sb.charAt(sb.length()-1) < c) {
                sb.deleteCharAt(sb.length()-1);
                k--;
            }
            sb.append(c);
        }
        
        //남아 있는 경우 다 털기
        if(k>0){
            sb.delete(sb.length()-k, sb.length());
        }
        
    
        return sb.toString();
    }
}