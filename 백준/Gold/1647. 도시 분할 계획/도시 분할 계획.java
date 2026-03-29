import java.io.*;
import java.util.*;

public class Main {
	static int n,m; //n : 집의 개수 , m: 길의 개수
	static ArrayList<int[]> edges;
	static int[] parent;
	
	static int find(int x) {
		if (x == parent[x])
			return x;
		return parent[x] = find(parent[x]);
	}
	
	static void union(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x!=y)
			parent[y] =x; // y를 x에 편입
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		edges = new ArrayList<>();
		parent = new int[n+1];
		for(int i =1; i<=n; i++) {
			parent[i] = i;
		}
		while(m-->0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			edges.add(new int[] {a,b,c});
		}
		
		Collections.sort(edges, (a,b)->a[2] - b[2]);
		
		int minCost = 0;
		int lastCost = 0;
		int cnt= 0;
		for(int[] edge : edges) {
			int start = edge[0];
			int finish = edge[1];
			int cost = edge[2];
			if(find(start) != find(finish)) {
				union(start,finish);
				minCost += cost;
				lastCost = cost;
				cnt++;
			}
			if(cnt == n-1) //cnt == n-2 하고 lastCost를 삭제해도 되지만 이렇게 하는게 직관적
				break;
		}
		minCost -= lastCost;
		System.out.println(minCost);
	}
}
