import java.util.*;
class Node implements Comparable<Node>{
    int end;
    int time;
    
    Node(int end, int time){
        this.end = end;
        this.time = time;
    }
    
    @Override
    public int compareTo(Node o){
       return this.time - o.time; 
    } 
}
class Solution {
    public int solution(int N, int[][] road, int K) {
        List<Node>[] graph = new ArrayList[N+1];
        for(int i=1; i<=N; i++)
            graph[i] = new ArrayList<>();
        
        //1. 그래프 연결(양방향)
        for(int[] r : road){
            graph[r[0]].add(new Node(r[1],r[2]));
            graph[r[1]].add(new Node(r[0],r[2]));
        }
        
        //2. 최단 거리 배열 초기화
        int[] dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;
        
        //3. 다익스트라 시작(1번마을부터 거리 0으로 출발)
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1,0));
        
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            
            if(cur.time > dist[cur.end]) continue;
            
            for(Node next: graph[cur.end]){
                int newTime = cur.time+next.time;
                
                if(newTime < dist[next.end]){
                    dist[next.end] = newTime;
                    pq.add(new Node(next.end, newTime));
                }
            }
        }
    
        //4. k시간 이하로 배달 가능한 마을 개수 세기
        int answer = 0;
        for(int i=1; i<=N; i++){
            if(dist[i] <= K) answer++;
        }



        return answer;
    }
}