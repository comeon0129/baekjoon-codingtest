//스택 문제: 내 앞에 나보다 작은 놈이 있으면 무조건 죽인다
import java.util.*;
class Solution {
    public String solution(String number, int k) {
        Stack<Character> s = new Stack<>();
        s.push(number.charAt(0));
        for(int i=1; i<number.length(); i++){
            while(k>0 && !s.isEmpty() && s.peek() < number.charAt(i)){
                s.pop();
                k--;
            }
            s.push(number.charAt(i));
        }
        
        //남아 있는 경우 다 털기
        while(k>0){
            s.pop();
            k--;
        }
        
        String temp = "";
        while(!s.isEmpty()){
            temp+=s.pop();
        }
        
        String answer = "";
        for (int i = temp.length() - 1; i >= 0; i--) {
            answer += temp.charAt(i);
        }
        return answer;
    }
}