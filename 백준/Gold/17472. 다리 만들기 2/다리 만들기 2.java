import java.io.*;
import java.util.*;

public class Main {
	static int n,m; // n: 세로크기(x), m: 가로 크기 (y)
	static int[][] map;
	static int islandNum = 2;
	
	static int[] dx = {-1,1,0,0};//상하좌우 순서
	static int[] dy = {0,0,-1,1};//상하좌우 순서
	
	static ArrayList<int[]> edges = new ArrayList<>();
	
	static int[] parent;
	
	static int find(int x) {
		if(x == parent[x])
			return x;
		return parent[x] = find(parent[x]);
	}
	
	static void union(int x, int y) {
		x = find(x);
		y = find(y);
		if(x != y)
			parent[y] = x; // y를 x밑으로 편입
	}
	
	static boolean inRange(int x, int y) {
		return 1<=x && x<=n && 1<=y && y<=m;
	}
	
	static void bfs (int x, int y) {
		Queue<int[]> q = new LinkedList<>();
		map[x][y] = islandNum;
		q.add(new int[] {x,y});
		
		while(!q.isEmpty()) {
			int[] pos = q.poll();
			for(int i=0; i<4; i++) {
				int nx = pos[0]+dx[i];
				int ny = pos[1]+dy[i];
				
				if(inRange(nx,ny) && map[nx][ny] == 1) {
					map[nx][ny] = islandNum;
					q.add(new int[] {nx,ny});
				}
			}
		}
	}
	
	static void findEdge(int x, int y, int startIsland) {
		for(int i=0; i<4; i++) {
			int nx = x;
			int ny = y;
			int dist = 0; 
			
			while(true) {
				nx += dx[i];
				ny += dy[i];
				
				if(!inRange(nx,ny) || map[nx][ny] == startIsland) //범위를 벗어나거나 내 섬을 만나면 break
					break;
				
				if(map[nx][ny] == 0) { //바다면 계속 전진
					dist++;
				}
				else { //다른 섬을 만났다면
					if(dist >= 2) { //그중에 다리 길이가 2가 넘는건 간선으로 저장
						edges.add(new int[] {startIsland, map[nx][ny], dist});
					}
					break;
				}
				
				
			}
		}
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n+1][m+1];
		for(int i=1; i<=n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=m; j++) {
				if(map[i][j] == 1) {
					bfs(i,j);
					islandNum++;
				}
			}
		}
//		/*bfs 잘되었는지 확인 */
//		for(int i=1; i<=n; i++) {
//			for(int j=1; j<=m; j++) {
//				System.out.print(map[i][j]+" ");
//			}
//			System.out.println();
//		}
		
		//edge 들 다 구하기
		for(int num=2; num<islandNum; num++){
			for(int i=1; i<=n; i++) {
				for(int j=1; j<=m; j++) {
					if(map[i][j] == num) {
						findEdge(i,j,num); //간선들을 시작섬 번호,도착섬 번호, 길이 순서로 저장
					}
				}
			}
		}
		
		Collections.sort(edges,(a,b) -> a[2] - b[2]);
		
		parent = new int[islandNum];
		
		for(int i=2; i<islandNum; i++)
			parent[i] = i;
		
		int minCost = 0;
		int cnt = 0;
		for(int[] edge : edges) {
			int start = edge[0];
			int end = edge[1];
			int cost = edge[2];
			
			if(find(start) != find(end)) {
				minCost += cost;
				union(start, end);
				cnt++;
			}
			
			if(cnt == islandNum-3) //islandNum이 만약 6이면 섬이 총 4개인거고 3개 간선만 있으면 됨
				break;
		}
		
		if(cnt < islandNum-3) // MST를 다 돌았는데 모든 섬을 연결할 수 없는 경우
			System.out.println(-1);
		else
			System.out.println(minCost);
		
	}
}