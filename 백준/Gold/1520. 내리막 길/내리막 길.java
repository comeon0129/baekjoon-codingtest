import java.io.*;
import java.util.*;


public class Main {
	static int n,m;
	static int[][] map;
	static int[][] dp; // dp[i][j]: i,j 에서 도착지까지 갈 수 있는 경우의 수
	
	static boolean inRange(int x, int y) {
		return 1<=x && x<=n && 1<=y && y<=m;
	}
	
	static int dfs(int x, int y) {
		int[] dx = {-1,1,0,0};
		int[] dy = {0,0,-1,1};
		
		if(x == n && y == m) return 1;
		
		if(dp[x][y] !=-1) return dp[x][y];
		
		dp[x][y] = 0;
		for(int i=0; i<4; i++) {
			int nx = x+dx[i];
			int ny = y+dy[i];
				
			if(inRange(nx,ny) && map[nx][ny] < map[x][y]) {
				dp[x][y] += dfs(nx,ny);
			}
		}
		return dp[x][y];
	}
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	m = Integer.parseInt(st.nextToken());
    	map = new int[n+1][m+1];
    	dp = new int[n+1][m+1];
    	for(int i=1; i<=n; i++) {
    		st = new StringTokenizer(br.readLine());
    		for(int j=1; j<=m; j++) {
    			map[i][j] = Integer.parseInt(st.nextToken());
    			dp[i][j] = -1;
    		}
    	}
    	
    	System.out.println(dfs(1,1));	
    }
}
