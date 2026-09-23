//체육복은 바로 앞번호나 바로 뒷번호한테만 빌려줄 수 있음.

//n: 전체 학생 수, lost: 체육복 도난당한 학생들 reserve: 여벌 체육복 가져온 학생들 번호
//구하는것: 체육수업을 들을 수 있는 학생의 최댓값
import java.util.*;
class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int[] clothes = new int[n+1];
        
        for(int i=1; i<=n; i++)
            clothes[i] = 1; //옷 한벌씩 있음
        
        for(int i=0; i<lost.length; i++){
            clothes[lost[i]]--;
        }
        
        for(int i=0; i<reserve.length; i++)
            clothes[reserve[i]]++;
        
        
        for(int i=1; i<=n; i++){
            //만약 체육복이 없는 학생이 있다면 앞에서 먼저 빌리기
            if(clothes[i] == 0){
                
                if(i>=2 && clothes[i-1] == 2){
                    clothes[i-1]--;
                    clothes[i]++;
                }
                //앞에서 못빌린 경우 뒤에서 빌리기
                else if(i<n && clothes[i+1] == 2){
                    clothes[i+1]--;
                    clothes[i]++;
                }
            }
        }
        int answer = 0;
        for(int i=1; i<=n; i++){
            if(clothes[i] > 0)
                answer++;
        }
        
        return answer;
    }
}