import java.io.*;
import java.util.*;

public class Main {
	public static int n,m; //n: 세로크기(y) m: 가로크기(x)
	public static int[][] map;
	public static int maxSafe = 0;
	
	public static void checkSafetyZone(int[][] map) {
		int safe = 0;
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(map[i][j] == 0)
					safe++;
			}
		}
		maxSafe= Integer.max(safe, maxSafe);
	}
	
	public static boolean inRange(int x, int y) {
		return 0<=x && x<n && 0<=y && y<m;
	}
	
	public static void bfs() {
		Queue<int[]> q =new LinkedList<>();
		int[][] copyMap = new int[n][m];
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				copyMap[i][j] = map[i][j];
				if(copyMap[i][j] == 2)
					q.add(new int[] {i,j});
			}
		}
		int[] dx = {-1,1,0,0};
		int[] dy = {0,0,-1,1};
		
		while(!q.isEmpty()) {
			int[] pos = q.poll();
			int x = pos[0];
			int y = pos[1];
			for(int i=0; i<4; i++) {
				int nx = x+dx[i];
				int ny = y+dy[i];
				if(inRange(nx,ny)&& copyMap[nx][ny] == 0) {
					copyMap[nx][ny] = 2;
					q.add(new int[] {nx,ny});
				}
			}
		}
		checkSafetyZone(copyMap);
	}
	
	public static void chooseWalls(int depth) {
		if(depth==3) {
			bfs(); //벽이 3개면 바이러스 퍼트리러가기
			return;
		}
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(map[i][j] == 0) {
					map[i][j] =1; //벽 세우고
					chooseWalls(depth+1); //재귀 호출하고
					map[i][j] = 0; //재귀 끝나면 벽 허물기
				}
			}
		}
		
	}
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	m = Integer.parseInt(st.nextToken());
    	map = new int[n][m];
    	for(int i=0; i<n; i++) {
    		st = new StringTokenizer(br.readLine());
    		for(int j=0; j<m; j++) {
    			map[i][j] = Integer.parseInt(st.nextToken());
    		}
    	}
    	chooseWalls(0);
    	System.out.println(maxSafe);
    }
}
