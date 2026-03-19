import java.io.*;
import java.util.*;

public class Main {
	static int N,M,X; // N: 마을 개수, M: 단방향 도로 개수, X: 도착마을번호
	static ArrayList<int[]>[] graph;
	static int[] dist; // 출발지로부터 걸리는 최단 거리
	static int[] time; // 각 학생당 걸리는 최종 시간
	
	static void dijkstra(int start) {
		for(int i=1; i<=N; i++)
			dist[i] = Integer.MAX_VALUE;
		dist[start] = 0;
		
		PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->a[1]-b[1]);
		pq.add(new int[] {start, 0}); //현재 마을, 지금까지 온 거리 저장
		
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			int curV = cur[0];
			int curDist = cur[1];
			
			if(curDist > dist[curV]) //이미 처리한 경우이므로 스킵
				continue;
			
			for(int[] road: graph[curV]) {
				int nextV = road[0];
				int nextDist = curDist+road[1];
				
				if(nextDist < dist[nextV]) {
					dist[nextV] = nextDist;
					pq.add(new int[] {nextV, nextDist});
				}
			}
		}
	}
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	N = Integer.parseInt(st.nextToken());
    	M = Integer.parseInt(st.nextToken());
    	X = Integer.parseInt(st.nextToken());
    	
    	graph = new ArrayList[N+1];
    	dist = new int[N+1];
    	time = new int[N+1];
    	
    	for(int i=1; i<=N; i++)
    		graph[i] = new ArrayList<>();
    	
    	for(int i=1; i<=M; i++) {
    		st = new StringTokenizer(br.readLine());
    		int start = Integer.parseInt(st.nextToken());
    		int end = Integer.parseInt(st.nextToken());
    		int cost = Integer.parseInt(st.nextToken());
    		graph[start].add(new int[] {end,cost}); //도착마을번호,비용 순서
    	}
    	
    	dijkstra(X);
    	for(int i=1; i<=N; i++)
    		time[i] += dist[i]; // 각 마을의 학생에게 X 마을에서 각 i번째 마을까지 도착하는데 걸리는 시간 더해줌
    	
    	for(int i=1; i<=N; i++) {
    		if(i != X) {
    			dijkstra(i);
    			time[i] += dist[X]; // i번째 마을에서 X 마을까지 도착하는데 걸리는 시간을 더해줌
    		}
    	}
    	int answer = Integer.MIN_VALUE;
    	
    	for(int i=1; i<=N; i++)
    		answer = Math.max(answer, time[i]);
    	System.out.println(answer);
    }
}
