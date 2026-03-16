import java.io.*;
import java.util.*;

public class Main {
	static int n,e; // n: 정점의 개수 e: 간선의 개수
	static ArrayList<int[]>[] graph;
	static int[] dist;
	
	
	static void dijkstra(int start, int finish) {
		PriorityQueue<int[]> pq =new PriorityQueue<>((a,b) -> a[1]-b[1]);
		
		for(int i=1; i<=n; i++)
			dist[i] = Integer.MAX_VALUE;
		
		
		dist[start] = 0;
		pq.add(new int[] {start, 0}); // 현재 도시, 지금까지 온 총 거리
		
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			int curV = cur[0];
			int curDist = cur[1];
			
			if(dist[curV] < curDist)
				continue;
			
			for(int[] edge : graph[curV]) {
				int nextV = edge[0];
				int nextDist = edge[1];
				if(curDist+nextDist < dist[nextV]) {
					dist[nextV] = curDist+nextDist;
					pq.add(new int[] {nextV, curDist+nextDist});
				}
			}
		}
		
	}
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	e = Integer.parseInt(st.nextToken());
    	
    	graph = new ArrayList[n+1];
    	dist = new int[n+1];
    	
    	for(int i=1; i<=n; i++) {
    		graph[i] = new ArrayList<>();
    		dist[i] = Integer.MAX_VALUE;
    	}
    	
    	while(e-->0) {
    		st = new StringTokenizer(br.readLine());
    		int a = Integer.parseInt(st.nextToken());
    		int b = Integer.parseInt(st.nextToken());
    		int c = Integer.parseInt(st.nextToken());
    		graph[a].add(new int[] {b,c}); // 도착하는 정점, 비용 순서
    		graph[b].add(new int[] {a,c});
    	}
    	
    	st = new StringTokenizer(br.readLine());
    	int v1 = Integer.parseInt(st.nextToken()); 
    	int v2 = Integer.parseInt(st.nextToken()); 
    	
    	int d1 = 0;
    	boolean d1flag = false;
    	dijkstra(1, v1);
    	if(dist[v1] == Integer.MAX_VALUE)
    		d1flag = true;
    	else
    		d1+= dist[v1];
    	dijkstra(v1,v2);
    	if(dist[v2] == Integer.MAX_VALUE)
    		d1flag = true;
    	else
    		d1+= dist[v2];
    	dijkstra(v2,n);
    	if(dist[n] == Integer.MAX_VALUE)
    		d1flag = true;
    	else
    		d1+= dist[n];
    	
    	int d2 = 0;
    	boolean d2flag = false;
    	dijkstra(1,v2);
    	if(dist[v2] == Integer.MAX_VALUE)
    		d2flag = true;
    	else
    		d2+= dist[v2];
    	dijkstra(v2,v1);
    	if(dist[v1] == Integer.MAX_VALUE)
    		d2flag = true;
    	else
    		d2+= dist[v1];
    	dijkstra(v1,n);
    	if(dist[n] == Integer.MAX_VALUE)
    		d2flag = true;
    	else
    		d2+= dist[n];
    	
    	int path1 = Integer.MAX_VALUE;
    	int path2 = Integer.MAX_VALUE;
    	
    	if(!d1flag)
    		path1 = d1;
    	
    	if(!d2flag)
    		path2 = d2;
    	
    	if(d1flag && d2flag)
    		System.out.println(-1);
    	else
    		System.out.println(Math.min(path1, path2));
    	
    }
}
