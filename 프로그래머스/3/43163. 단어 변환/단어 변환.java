import java.util.*;
class Solution {
    static int bfs(String begin, int beginIdx, int targetIdx, String[] words, int n){
       //1. visited[] 배열 선언
        int[] visited = new int[n+1];
        
        Queue<String> q = new LinkedList<>();
        q.add(begin);
        
        while(!q.isEmpty()){
            String word = q.poll();
            
            int wordIdx = n;
            //word의 index구하는 과정 포함?
            for(int i=0; i<n; i++){
                if(words[i].equals(word)){
                    wordIdx = i;
                    break;
                }
            }
            
            for(int i=0; i<n; i++){
                if(visited[i] != 0)
                    continue;
                
                int cnt = 0;
                
                for(int j=0; j<word.length(); j++){
                    if(word.charAt(j) != words[i].charAt(j))
                        cnt++;
                }
                if(cnt == 1){
                    visited[i] = visited[wordIdx]+1;
                    q.add(words[i]);
                }
            }
        }
        if(visited[targetIdx] == 0 )
            return 0;
        else
            return visited[targetIdx]; 
    }
    
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        int n = words.length; //전체 단어 개수
        
        //0. 우선 target이 words[] 배열 안에 있는지 확인하고 없으면 그냥 0 return
        int beginIdx = n;
        int targetIdx = -1;
        for(int i=0; i<n; i++){
            if(words[i].equals(target)){
                targetIdx = i;
            }  
            if(words[i].equals(beginIdx)){
                beginIdx = i;
            }
        }   
        if(targetIdx == -1)
            return 0;
       
        //1. bfs 돌려서 최단거리 찾아주기
        answer = bfs(begin, beginIdx, targetIdx, words, n);
        return answer;
    }
}