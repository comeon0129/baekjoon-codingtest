import java.io.*;
import java.util.*;

public class Main {
	static int n,m; //n: 컴퓨터 수 m: 선의 수
	static ArrayList<int[]> edges = new ArrayList<>();
	static int[] parent;
	
	static int find(int x) {
		if(x == parent[x])
			return x;
		return parent[x] = find(parent[x]);
	}
	
	static void union(int x, int y) {
		x= find(x);
		y = find(y);
		if(x != y)
			parent[y] = x; //y를 x밑에 편입
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		parent = new int[n+1];
		for(int i=1; i<=n; i++)
			parent[i] = i;
		
		while(m-->0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			edges.add(new int[] {a,b,c}); //출발, 도착, 비용 순서
		}
		Collections.sort(edges, (a,b) -> a[2] - b[2]);
		int minCost = 0;
		for(int[] edge: edges) {
			if(find(edge[0]) != find(edge[1])) {
				union(edge[0], edge[1]);
				minCost += edge[2];
			}
		}
		System.out.println(minCost);
	}
}
