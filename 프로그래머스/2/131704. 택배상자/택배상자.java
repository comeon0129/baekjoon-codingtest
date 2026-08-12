//그냥 컨테이너벨트는 큐 먼저 들어간게 먼저 나가는거
//보조 컨베이너 벨트 마지막에 보관한거 먼저 꺼내니까 스택

//그니까 1. 지금 당장 실을 순서면 실는다.
//2. 아니면 보조 컨테이너에 실기
import java.util.*;
class Solution {
    public int solution(int[] order) {
        Stack<Integer> s = new Stack<>();
        
        //order[0] 를 만났어 얘를 해결 할 수 있을 때까지 curBox 값을 올려가면서 stack에 넣어
        //해결이 가능하면 처리하고 count 하나 올리고 order[1] 로 이동해. 
        //할 수 있는 행동 1. 그냥 실기
        //2. 보조 컨베이어 벨트에 실기
        //3. 보조 컨베이어 벨트에서 꺼내서 실기
        
        int curBox =1;
        int idx = 0;
        
        while(idx < order.length){
            if(curBox == order[idx]){ //그냥 실을 수 있다면 그냥 실기
                curBox++;
                idx++;
            }
            else if(curBox < order[idx]){ //보조 컨베이어벨트에 실는 경우
                s.add(curBox);
                curBox++;
            }
            else{ //보조 컨베이어 벨트에서 꺼내서 실을 수 있는 경우
                int box = s.pop();
                if(order[idx] == box){
                    idx++;
                }
                else
                    break;
            }
        }
        
        
        int answer = idx;
        return answer;
    }
}