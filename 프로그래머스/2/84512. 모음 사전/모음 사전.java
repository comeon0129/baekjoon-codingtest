class Solution {
    static int answer = 0;
    static int count = 0;
    static void dfs(String[] alpha, String cur, String word){
        if(cur.equals(word)){
            answer = count;
        }
        
        if(cur.length() >= 5)
            return;
        
        for(int i=0; i<5; i++){
            cur += alpha[i];
            count++;
            dfs(alpha,cur,word);
            cur = cur.substring(0, cur.length()-1);
        }
    }
    
    public int solution(String word) {
        String[] alpha = new String[]{"A","E","I","O","U"};
        dfs(alpha,"",word);
        return answer;
    }
}