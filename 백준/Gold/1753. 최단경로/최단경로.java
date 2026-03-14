import java.io.*;
import java.util.*;


public class Main {
	static int V,E; // V: 정점의 개수 E: 간선의 개수
	static ArrayList<int[]>[] graph;
	static int[] dist;
	
	static void dijkstra(int start) {
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				// TODO Auto-generated method stuqb
				return o1[1] - o2[1]; //거리 순서로 오름차순 정렬
			}
			
		});
		pq.add(new int[] {start,0}); //{노드, 거리}
		dist[start] = 0;
		
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			int curNode = cur[0]; //현재 노드
			int curDist = cur[1]; //출발노드부터 현재 노드까지 온 총 거리
			
			if(curDist > dist[curNode]) //현재노드까지 온 총 거리가 이미 dist에 들어있는 최단 거리보다 길다면 스킵
				continue;
			
			for(int[] edge: graph[curNode]){
				int next = edge[0];
				int nextDist = curDist+edge[1];
				
				if(nextDist < dist[next]) {// dist에 적혀있는 값보다 이동 거리가 적다면
					dist[next] = nextDist; // 갱신
					pq.add(new int[] {next, nextDist});
				}
			}
			
		}
		
	}
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	V = Integer.parseInt(st.nextToken());
    	E = Integer.parseInt(st.nextToken()); 
    	
    	graph = new ArrayList[V+1];
    	
    	for(int i=1; i<=V; i++)
    		graph[i] = new ArrayList<>();
    	
    	dist = new int[V+1];
    	
    	for(int i=1; i<=V; i++)
    		dist[i] = Integer.MAX_VALUE;
    	
    	int startNode = Integer.parseInt(br.readLine());
    	
    	while(E-->0) {
    		st = new StringTokenizer(br.readLine());
    		int u = Integer.parseInt(st.nextToken());
    		int v = Integer.parseInt(st.nextToken());
    		int w = Integer.parseInt(st.nextToken());
    		graph[u].add(new int[] {v,w});
    	}
    	
    	dijkstra(startNode);
    	
    	for(int i=1; i<=V; i++) {
    		if(dist[i] == Integer.MAX_VALUE)
    			System.out.println("INF");
    		else {
    			System.out.println(dist[i]);
    		}
    	}
    }
}

