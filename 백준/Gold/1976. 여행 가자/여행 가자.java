import java.io.*;
import java.util.*;

public class Main {
	static int n; //도시의 수
	static int m; //여행 계획에 속한 도시들의 수
	static int[] parent;
	static int find(int x) {
		if(x == parent[x])
			return x;
		return parent[x] = find(parent[x]);
	}
	
	static void union(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x != y) {
			parent[y] = x; //y쪽에 있는 애들이 전부 x에 붙음
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		parent = new int[n+1];
		for(int i=1; i<=n; i++)
			parent[i] = i;
		for(int i=1; i<=n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=1; j<=n; j++) {
				int connection = Integer.parseInt(st.nextToken());
				if(connection == 1)
					union(i,j);
			}
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] plan = new int[m];
		for(int i=0; i<m; i++) {
			plan[i] = Integer.parseInt(st.nextToken());
		}
		int root = find(plan[0]);
		boolean possible = true;
		for(int i=1; i<m; i++) {
			if(root != find(plan[i])) {
				possible = false;
				break;
			}
		}
		if(possible)
			System.out.println("YES");
		else
			System.out.println("NO");
	}
}
