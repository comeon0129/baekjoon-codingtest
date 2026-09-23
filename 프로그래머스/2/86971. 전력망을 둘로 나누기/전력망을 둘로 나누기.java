//서로 연결을 끊어서 두 전력망이 갖는 송전탑 개수를 비슷하게 맞춘다
//n: 송전탑 수 wires: v[0], v[1]이 서로 연결된 것
//구하는 것 최대한 비슷하게 둘로 나눴을때, 두 전력망이 가진 송전탑 개수 차이

//조합 문제이지 않나? n개가 있는데 1개 뽑거나 2개뽑거나.. 해서 n-1개까지 뽑아서 양쪽 차이 비교하고 그 차가 최소일때 차이를 return 하는 dfs를 만들면 해결될거같다.
//여러개를 끊을 수 있는 문제였다면 저런식으로도 할 수 있을거같은데 1개만 끊는거면 그냥 다 해보면 된다.
import java.util.*;
class Solution {
    static int bfs(int x, int n, List<Integer>[] graph){
        boolean[] visited = new boolean[n+1];
        visited[x] = true;
        int cnt = 1;
        
        Queue<Integer> q = new LinkedList<>();
        q.add(x);
        
        while(!q.isEmpty()){
            int v = q.poll();
            
            for(int next: graph[v]){
                if(!visited[next]){
                    visited[next] = true;
                    cnt++;
                    q.add(next);
                }
            }
        }
        return cnt;      
    }
    public int solution(int n, int[][] wires) {
        List<Integer>[] graph = new ArrayList[n+1];
        for(int i=1; i<=n; i++)
            graph[i] = new ArrayList<>();
        
        for(int i=0; i< wires.length; i++){
            int v1 = wires[i][0];
            int v2 = wires[i][1];
            graph[v1].add(v2);
            graph[v2].add(v1);
        }
        //하나씩 끊고 그때마다 양쪽 그룹의 송전탑 개수를 세고, 그 차이가 최소이면 해당 차이를 answer로 저장
        int minDiff = Integer.MAX_VALUE;
        for(int i=0; i< wires.length; i++){
            int v1 = wires[i][0];
            int v2 = wires[i][1];
            graph[v1].remove(Integer.valueOf(v2));
            graph[v2].remove(Integer.valueOf(v1));
            
            int group1 = bfs(1,n,graph);
            int group2 = n - group1;
            
            int diff = Math.abs(group1-group2);
           
            if(diff < minDiff){
                minDiff = diff;
            }
            graph[v1].add(v2);
            graph[v2].add(v1);
        }
        return minDiff;
    }
}