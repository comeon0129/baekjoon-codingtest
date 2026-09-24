import java.util.*;
class Solution {
    public int solution(String name) {
        int answer = 0;
        //1. 상하 조작
        for(int i=0; i<name.length(); i++){
            if(name.charAt(i) <= 'N')
                answer+= name.charAt(i) - 'A';
            else
                answer+= 'Z' - name.charAt(i) +1;
        }
        
        //2. 좌우조작(완전탐색)
        
        //처음부터 끝까지 오른쪽으로만 가는 경우
        int move = name.length()-1;
        
        for(int i=0; i<name.length()-1; i++){
            int next = i+1;
            //i 다음으로 나오는 알파벳중 'A'가 아닌 가장 첫 인덱스 찾기
            while(next < name.length() && name.charAt(next) == 'A'){
                next++;
            }
            //오른쪽으로 갔다가 턴
            //왼쪽으로 갔다가 턴
            move = Math.min(move, (i*2)+(name.length()-next));
            move = Math.min(move, (name.length()-next) * 2 + i); 
        }
        
        answer+=move;
       
        
        return answer;
    }
}