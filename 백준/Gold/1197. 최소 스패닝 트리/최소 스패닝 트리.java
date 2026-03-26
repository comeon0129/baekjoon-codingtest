import java.io.*;
import java.util.*;

public class Main {
	static int v,e; // v: 정점 개수, e: 간선 개수
	static int[] parent;
	
	static int find(int x) {
		if(x == parent[x])
			return x;
		return parent[x] = find(parent[x]);
	}
	
	static void union(int x, int y) {
		x= find(x);
		y = find(y);
		if(x!=y)
			parent[y] = x; //y가 x밑으로 편입
	}
	
	static ArrayList<int[]> edges;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st =new StringTokenizer(br.readLine());
		v = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());
		
		parent = new int[v+1];
		for(int i=1; i<=v; i++)
			parent[i] = i;
		
		edges = new ArrayList<>();
		while(e-->0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			edges.add(new int[] {a,b,c});
		}
		Collections.sort(edges, (a,b) -> a[2] - b[2]);
		
		long mst = 0;
		int cnt = 0; // 선택된 간선 수 체크용
		for(int i=0; i<edges.size(); i++) {
			int[] edge = edges.get(i);
			if(find(edge[0]) != find(edge[1])) {
				mst+=edge[2];
				union(edge[0],edge[1]);
				cnt++;
				
				if(cnt == v-1) break;
			}
		}
		System.out.println(mst);
	}
}
