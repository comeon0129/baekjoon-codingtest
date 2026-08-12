//우선 record 먼저 쪼개기
//공백 기준으로 명령어 / 유저 아이디 / 닉네임

//명령어가 Enter면 uid 닉네임 
//명령어가 Leave면 uid
//명령어가 Change면 uid 닉네임

//유일한 걸리는게 Change를 하면 앞에 문구도 싹다 바뀐다는거?

// 1번 사이클을 돌려서 이름 먼저 전부 확정을 해놓고 두번째 사이클 돌리면서 결과문구 뽑으면 될거같은데?
import java.util.*;
class Solution {
    public String[] solution(String[] record) {
        Map<String, String> map = new HashMap<>();
        
        //1라운드 이름확정
        for(String r: record){
            String[] s = r.split(" ");
            String command = s[0];
            String uid = s[1];
            if(command.equals("Enter") || command.equals("Change")){
                map.put(uid, s[2]);
            }
        }
        
        //2라운드 결과 뽑기
        List<String> answerList = new ArrayList<>();
        
        for(String r: record){
            String[] s = r.split(" ");
            String command = s[0];
            String uid = s[1];
            
            if(command.equals("Enter")){
                answerList.add(map.get(uid)+"님이 들어왔습니다.");
            }
            else if(command.equals("Leave")){
                answerList.add(map.get(uid)+"님이 나갔습니다.");
            }
        }
        
        // Set<String> keySet = map.keySet();
        // for (String key : keySet) {
        //     System.out.println(key + " : " + map.get(key));     
        // }
        
        //3라운드 결과 옮기기
        String[] answer = new String[answerList.size()];
        for(int i=0; i<answer.length; i++){
            answer[i] = answerList.get(i);
        }
        return answer;
    }
}