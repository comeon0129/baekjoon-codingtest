// 한 번호가 다른 번호의 접두어에 포함되는지를 어떻게 알 수 있을까..
// hash set안에 전화번호를 다 집어넣어놓고 하나씩 보면서
// 길이0부터 해당 단어 길이 -1까지 늘려가면서 전화번호부 안에 있는지 보기
import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        boolean answer = true;
        Set<String> s = new HashSet<>();
        for(String phone: phone_book)
            s.add(phone);
        
        for(String phone: phone_book){
            for(int i=1; i<phone.length(); i++){
                String sub = phone.substring(0,i);
                if(s.contains(sub))
                    return false;
            }
        }
        
        
        return answer;
    }
}