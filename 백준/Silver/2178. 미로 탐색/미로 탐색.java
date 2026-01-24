import java.io.*;
import java.util.*;

public class Main {
	
	public static int n,m; // n: x, m:y
	public static int[][] grid;
	public static boolean[][] visited;
	public static Queue<int[]> q = new LinkedList<>();
	
	public static int[] dx = {-1,1,0,0};
	public static int[] dy = {0,0,-1,1};
	
	public static boolean inRange(int x, int y) {
		return x>=0 && x<n && y>=0 && y<m;
	}
	
	public static void bfs() {
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int x = cur[0];
			int y = cur[1];
			for(int i=0; i<4; i++) {
				int nx = x+dx[i];
				int ny = y+dy[i];
				if(inRange(nx, ny)&& !visited[nx][ny] && grid[nx][ny]==1) {
					grid[nx][ny] = grid[x][y]+1;
					visited[nx][ny] = true;
					q.add(new int[] {nx,ny});
				}
			}
		}
	}
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	m = Integer.parseInt(st.nextToken());
    	grid = new int[n][m];
    	visited = new boolean[n][m];
    	for(int i=0; i<n; i++) {
    		String inputLine = br.readLine();
    		for(int j=0; j<m; j++) {
    			grid[i][j] = inputLine.charAt(j) - '0';
    		}
    	}
    	q.add(new int[]{0,0});
    	visited[0][0] = true;
    	bfs();
    	System.out.println(grid[n-1][m-1]);
    	
    }
    
}