import java.io.*;
import java.util.*;

public class Main {
	static int n,m; //n: 우주신들의 개수, m: 통로의 개수
	static boolean[] visited;
	static double[] godsX;
	static double[] godsY;
	static double[][] graph;
	static double length = 0;
	
	static void prim() { //다익스트라랑 거의 똑같은데 dist[i]가 시작지점까지의 총거리가 아니라 mst에 연결되는 최소 간선거리임
		visited[1] = true;
		double[] dist = new double[n+1];
		for(int i=1; i<=n; i++)//시작 노드에서 연결된 노드들 dist 갱신
			dist[i] = graph[1][i];
		
		for(int i=1; i<=n-1; i++) {
			int minNode = -1;
			double minDist = Integer.MAX_VALUE;
			for(int j=1; j<=n; j++) {
				if(!visited[j] && dist[j] < minDist) {
					minDist = dist[j];
					minNode = j;
				}
			}
			//선택된 노드 visited처리, length에 추가
			visited[minNode] = true;
			length+= minDist;
			
			//선택된 노드에서 연결된 노드들 dist 갱신
			for(int j=1; j<=n; j++) {
				if(!visited[j] && graph[minNode][j] < dist[j])
					dist[j] = graph[minNode][j];
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st =new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		visited = new boolean[n+1];
		godsX = new double[n+1];
		godsY = new double[n+1];
		graph = new double[n+1][n+1];
		
		for(int i=1; i<=n; i++) {
			st = new StringTokenizer(br.readLine());
			double x = Integer.parseInt(st.nextToken());
			double y = Integer.parseInt(st.nextToken());
			godsX[i] = x;
			godsY[i] = y;
		}
		
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=n; j++) {
				graph[i][j] = Integer.MAX_VALUE;
			}
		}
		
		for(int i=1; i<=n; i++) {
			for(int j=i+1; j<=n; j++) {
				graph[i][j] = Math.sqrt(Math.pow(godsX[i]-godsX[j], 2)
						+Math.pow(godsY[i]-godsY[j], 2));
				graph[j][i] = Math.sqrt(Math.pow(godsX[i]-godsX[j], 2)
						+Math.pow(godsY[i]-godsY[j], 2));
			}
		}
		while(m-->0) {
			st = new StringTokenizer(br.readLine());
			int x= Integer.parseInt(st.nextToken());
			int y= Integer.parseInt(st.nextToken());
			graph[x][y] = 0;
			graph[y][x] = 0;
		}
		prim();
		System.out.printf("%.2f",length);
	}
}
