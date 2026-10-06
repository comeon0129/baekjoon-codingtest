//구명 보트 하나에 최대 2명
//구하는것: 필요한 구명보트 개수의 최솟값
import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        //1.몸무게 순으로 정렬
        Arrays.sort(people);
        
        //2. 제일 몸무게 작은사람과 큰 사람 더해서 탈수 있으면 둘다 빼고 못타면 가장 큰 사람만 빼기
        int start = 0;
        int end = people.length-1;
        int answer = 0;
        while(start <= end){
            if(people[start] + people[end] <= limit){
                start++;
            }
            end--;
            answer++; 
        }
        
        return answer;
    }
}