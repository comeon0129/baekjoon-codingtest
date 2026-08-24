import java.util.*;

class Solution {
    static int[] parent = new int[2501];
    static String[] graph = new String[2501];
    static int find(int x){
        if(parent[x] == x)
            return x;
        return parent[x] = find(parent[x]);
    }
    static void union(int a, int b){
        int rootA = find(a);
        int rootB = find(b);
        
        if(rootA == rootB) return;
        
        String rootValue = graph[rootA] != null ? graph[rootA] : graph[rootB];
        
        parent[rootB] = rootA;
        graph[rootA] = rootValue;
    }
    
    static void unmerge(int x){
        int root = find(x);
        String originalValue = graph[root];
        
        //1. 같은 그룹인 애들 먼저 찾아두기
        List<Integer> targetList = new ArrayList<>();
        for(int i=1; i<=2500; i++){
            if(find(i) == root)
                targetList.add(i);
        }
        
        //2. 찾아둔 애들 전부 각자도생으로 만들고 값 비우기
        for(int idx : targetList){
            parent[idx] = idx;
            graph[idx] = null;
        }
        
        //3. UNMERGE를 지시한 원래 그 좌표(x)에만 백업한 값을 남겨둔다
        graph[x] = originalValue;

    }
    
    public String[] solution(String[] commands) {
        int n = commands.length;
        ArrayList<String> answerList = new ArrayList<>();
        //전부 자기자신을 부모로
        for(int i=1; i<=2500; i++)
            parent[i] = i;
        
        for(String command: commands){
            String[] s = command.split(" ");
            
            if(s[0].equals("UPDATE")){
                if(s.length == 4){ //UPDATE r c value 명령어
                    int r = Integer.parseInt(s[1]);
                    int c = Integer.parseInt(s[2]);
                    String value = s[3];
                    
                    int idx = (r-1)*50+c;
                    int root = find(idx);
                    graph[root] = value;
                }
                else{
                    String value1 = s[1];
                    String value2 = s[2];
                    for(int i=1; i<=2500; i++){
                        if(graph[i] != null && graph[i].equals(value1))
                            graph[i] = value2;
                    }
                }
            }
            else if(s[0].equals("MERGE")){
                int r1 = Integer.parseInt(s[1]);
                int c1 = Integer.parseInt(s[2]); 
                int r2 = Integer.parseInt(s[3]);
                int c2 = Integer.parseInt(s[4]); 
                
                union((r1-1)*50+c1,(r2-1)*50+c2);
            }
            else if(s[0].equals("UNMERGE")){
                int r = Integer.parseInt(s[1]);
                int c = Integer.parseInt(s[2]);
                unmerge((r-1)*50+c);
            }
            else{
                int r = Integer.parseInt(s[1]);
                int c = Integer.parseInt(s[2]);
                
                int idx = (r-1)*50+c;
                
                int root = find(idx);
                
                answerList.add(graph[root] == null ? "EMPTY" : graph[root]);
            }  
        }
        String[] answer = new String[answerList.size()];
        
        for(int i=0; i<answerList.size(); i++)
            answer[i] = answerList.get(i);
        return answer;
    }
}