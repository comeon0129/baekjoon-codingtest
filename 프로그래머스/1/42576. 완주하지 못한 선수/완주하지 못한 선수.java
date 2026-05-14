import java.util.*;
class Solution {
    public String solution(String[] participant, String[] completion) {
        HashMap<String,Integer> map = new HashMap<>();
        
        //1. participant에 있는 선수들을 hash map<선수이름, 인원수> 로 옮기기
        for(int i=0; i<participant.length; i++){
            if(map.containsKey(participant[i])){
                map.replace(participant[i], map.get(participant[i])+1);    
            }
            else
                map.put(participant[i],1);
        }
        
        //2. completion보고 해당하는 hashmap의 인원수 깎기
        for(int i=0; i<completion.length; i++){
            if(map.containsKey(completion[i])){
                map.replace(completion[i], map.get(completion[i])-1);    
            }
        }
        
        //3. 맵을 뒤져서 값이 0이 아닌 사람 찾기!
        String answer = "";
        for(String key: map.keySet()){
            if(map.get(key) != 0){
                answer = key;
                break;
            }
        }
        return answer;
    }
}