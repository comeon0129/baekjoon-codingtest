import java.util.*;

class Solution {
    static int[] parent = new int[2501];
    static String[] graph = new String[2501];
    
    static int find(int x){
        if(parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }
    
    static void union(int a, int b){
        int rootA = find(a);
        int rootB = find(b);
        
        if(rootA == rootB) return;
        
        String rootVal;
        if(graph[rootA] != null)
            rootVal = graph[rootA];
        else
            rootVal = graph[rootB];
        
        parent[rootB] = rootA;
        graph[rootA] = rootVal;
    }
    
    static void unmerge(int x){
        int root = find(x);
        String origin = graph[root];
        
        List<Integer> targetList = new ArrayList<>();
        for(int i=1; i<=2500; i++){
            if(find(i) == root)
                targetList.add(i);
        }
        
        for(int idx: targetList){
            parent[idx] = idx;
            graph[idx] = null;
        }
        graph[x] = origin;
    }
    public String[] solution(String[] commands) {
        int n = commands.length;
        ArrayList<String> answerList = new ArrayList<>();
        for(int i=1; i<=2500; i++)
            parent[i] = i;
        
        for(String command: commands){
            String[] s = command.split(" ");
            
            if(s[0].equals("UPDATE")){
                if(s.length == 4){
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
                if(graph[root] == null)
                    answerList.add("EMPTY");
                else
                    answerList.add(graph[root]);
            }
        }
        String[] answer = new String[answerList.size()];
        for(int i=0; i<answerList.size(); i++)
            answer[i] = answerList.get(i);
        return answer;
    }
}