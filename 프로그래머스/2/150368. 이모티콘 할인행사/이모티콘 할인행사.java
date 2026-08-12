//목표 1. 가입자 최대한 늘리기 2. 판매액 최대한늘리기
//n명에게 m개 할인 (할인율 10,20,30,40)

//user 1명 : 40 (비율 %이상 인거 다 구매), 10000(이금액 넘으면 이모티콘 구매 다 취소하고 플러스 가입)

//emoticon 1개: 70000(정가)

//이모티콘 하나마다 10% , 20%, 30%, 40% 를 각각 다 적용해봐서 그때 user가 얼마만큼 사는지를 기록하는게 핵심!

import java.util.*;

class Solution {
    static List<int[]> answerList = new ArrayList<>();
    
    public void dfs(int curNum, int[] emoticons, int[][] users, int[] discounts){
        if(curNum == emoticons.length){
            int n = users.length;
            int personCnt = 0;
            int sales = 0;
            
            for(int i=0; i<n; i++){
                int userRatio = users[i][0];
                int userPay = users[i][1];
            
                double cost = 0;
                
                for(int j=0; j<emoticons.length; j++){
                    if(discounts[j] >= userRatio){
                        cost+= emoticons[j] * (1-(double)discounts[j]/100); //원가 - 할인가
                    }
                }
                if(cost >= userPay){
                    personCnt++;
                }
                else{
                    sales+=cost;
                }
            }
            answerList.add(new int[]{personCnt,sales});
            
            return;
        }
        
        for(int i=10; i<=40; i+=10){
            discounts[curNum] = i;
            dfs(curNum+1,emoticons,users,discounts);
        }
        
        
    }
    
    public int[] solution(int[][] users, int[] emoticons) {
        int[] discounts = new int[emoticons.length];
        dfs(0, emoticons, users, discounts);
        
        Collections.sort(answerList, (a, b) -> {
            if(a[0] == b[0])
                return b[1] - a[1];
            return b[0]-a[0];
        });
        
        int[] answer = new int[2];
        answer[0] = answerList.get(0)[0];
        answer[1] = answerList.get(0)[1];
        
        return answer;
    }
}