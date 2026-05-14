class Solution {
    public String solution(String s) {
        // 면접 꿀팁: String 덧셈 대신 StringBuilder를 쓰면 "성능을 고려하는 지원자"로 보입니다!
        StringBuilder answer = new StringBuilder(); 
        
        // 1. 공백 처리가 꼬이지 않게 일단 전부 소문자로 바꾼 뒤, '한 글자씩' 쪼갭니다.
        String[] words = s.toLowerCase().split(""); 
        
        // 2. 다음 글자가 대문자가 되어야 하는지 체크하는 깃발(플래그)
        boolean isFirst = true; 
        
        for(String word : words) {
            // isFirst가 true면 대문자로, 아니면 원래대로(소문자) 붙이기
            answer.append(isFirst ? word.toUpperCase() : word);
            
            // 방금 처리한 글자가 "공백"이라면? 다음 글자는 무조건 첫 글자(대문자)가 되어야 함!
            if(word.equals(" ")) {
                isFirst = true;
            } else {
                isFirst = false;
            }
        }
        
        return answer.toString();
    }
}