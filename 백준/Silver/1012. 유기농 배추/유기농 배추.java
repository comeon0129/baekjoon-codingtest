import java.io.*;
import java.util.*;

public class Main {
	
	public static int[][] grid;
	public static int m,n,k; // m: 가로 n: 세로, k: 배추 심어져있는 위치개수
	
	public static int[] dx = {-1,1,0,0};
	public static int[] dy = {0,0,-1,1};
	
	public static boolean inRange(int x, int y) {
		return x>=0 && x<m && y>=0 && y<n;
	}
	
	public static void dfs(int x, int y) {
		grid[x][y] = 0;
		
		for(int i=0; i<4; i++) {
			int nx = x+dx[i];
			int ny = y+dy[i];
			if(inRange(nx, ny) && grid[nx][ny] == 1)
				dfs(nx,ny);
		}
		return;
		
	}
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int t = Integer.parseInt(br.readLine());
    	StringBuilder sb = new StringBuilder();
    	while(t-->0) {
    		String inputLine = br.readLine();
    		StringTokenizer st = new StringTokenizer(inputLine);
    		m = Integer.parseInt(st.nextToken());
    		n = Integer.parseInt(st.nextToken());
    		k = Integer.parseInt(st.nextToken());
    		
    		grid = new int[m][n];
    		int total = 0;
    		while(k-->0) {
    			st = new StringTokenizer(br.readLine());
    			int x = Integer.parseInt(st.nextToken());
    			int y = Integer.parseInt(st.nextToken());
    			grid[x][y] = 1;
    		}
    		
    		for(int i=0; i<m; i++) {
    			for(int j=0; j<n; j++) {
    				if(grid[i][j] == 1) {
    					dfs(i,j);
    					total++;
    				}
    			}
    		}
    		sb.append(total).append("\n");
    	}
    	System.out.println(sb);
    	
    }
    
}