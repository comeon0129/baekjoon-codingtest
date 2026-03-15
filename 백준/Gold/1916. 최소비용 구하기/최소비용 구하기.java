import java.io.*;
import java.util.*;

public class Main {
	static int n,m; // n: 도시의 개수 m: 버스의 개수
	static ArrayList<int[]>[] buses;
	static int[] dist;
	
	static void dijkstra(int start, int finish) {
		PriorityQueue<int[]> pq =new PriorityQueue<>((a,b) -> a[1]-b[1]);
		
		dist[start] = 0;
		pq.add(new int[] {start, 0}); // 현재 도시, 지금까지 온 총 거리
		
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			int curCity = cur[0];
			int curDist = cur[1];
			
			if(dist[curCity] < curDist)
				continue;
			
			for(int[] bus : buses[curCity]) {
				int nextCity = bus[0];
				int nextDist = bus[1];
				if(curDist+nextDist < dist[nextCity]) {
					dist[nextCity] = curDist+nextDist;
					pq.add(new int[] {nextCity, curDist+nextDist});
				}
			}
		}
		
	}
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	n = Integer.parseInt(br.readLine());
    	m = Integer.parseInt(br.readLine());
    	
    	buses = new ArrayList[n+1];
    	dist = new int[n+1];
    	
    	for(int i=1; i<=n; i++) {
    		buses[i] = new ArrayList<>();
    		dist[i] = Integer.MAX_VALUE;
    	}
    	
    	while(m-->0) {
    		StringTokenizer st = new StringTokenizer(br.readLine());
    		int start = Integer.parseInt(st.nextToken());
    		int end = Integer.parseInt(st.nextToken());
    		int cost = Integer.parseInt(st.nextToken());
    		buses[start].add(new int[] {end,cost}); //도착 도시번호, 비용 순서
    	}
    	
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	int start = Integer.parseInt(st.nextToken()); 
    	int finish = Integer.parseInt(st.nextToken()); 
    	
    	dijkstra(start,finish);
    	
    	System.out.println(dist[finish]);
    }
}