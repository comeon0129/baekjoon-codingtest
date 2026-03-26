import java.io.*;
import java.util.*;

public class Main {
	static int n,m; // n: 문제 개수, m: 문제에 대한 정보 개수
	static int[] indegree;
	static ArrayList<Integer>[] graph;
	static StringBuilder sb = new StringBuilder();
	
	static void topologicalSort() {
		PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> a-b);
		for(int i=1; i<=n; i++) {
			if(indegree[i] == 0)
				pq.add(i);
		}
		while(!pq.isEmpty()) {
			int problem = pq.poll();
			
			for(Integer connect: graph[problem]) {
				indegree[connect]--;
				if(indegree[connect] == 0)
					pq.add(connect);
			}
			sb.append(problem+" ");
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st =new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		graph = new ArrayList[n+1];
		for(int i=1; i<=n; i++)
			graph[i] = new ArrayList<>();
		indegree = new int[n+1];
		
		while(m-->0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a].add(b);
			indegree[b]++;
		}
		topologicalSort();
		System.out.print(sb);
	}
}