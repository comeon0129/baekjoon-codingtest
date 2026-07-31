//우선 구하는 건 전체 의상 조합 경우의 수
// 예제 1번 보면 
// headgear : "yellow_hat", "green_turban"
// eyewear : blue_sunglasses

//예를들어서 3개 , 2개, 1개 이렇게 있었다고 보자.
//그러면 3+2+1+3*2+3*1+2*1+3*2*1 = 23개가 정답인가?

//28분 남았는데 아이디어 떠올리자..

//문제 예시인 2,1,1,1 조합이면
// 2+1+1+1+2*1+ 2*1+ 2*1+ 1*1+ 1*1 + 1*1 + 2*1*1*1 = 5+6+3+2 = 16개?

import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        HashMap<String, Integer> countMap = new HashMap<>();
        
        for(int i=0; i<clothes.length; i++){
            String key = clothes[i][1];
            if(countMap.containsKey(key)){
                countMap.put(key, countMap.get(key) + 1);
            }
            else{
                countMap.put(key, 1);
            }
        }
        
        System.out.println(countMap);
        
        //여기까지는 생각처럼 잘 되었는데 이제 value 값을 다 더하고 각각 value끼리 곱해지는 과정을 하면 끝난다.
        
        int answer = 1;
        
        //1. 우선 전체 value 다 더하기
        for(String s: countMap.keySet()){
            answer = answer * (countMap.get(s)+1);
        }
        
        answer--;
        
        
        return answer;
    }
}