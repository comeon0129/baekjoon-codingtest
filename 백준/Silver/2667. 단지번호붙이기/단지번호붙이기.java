import java.io.*;
import java.util.*;

public class Main {
	
	static int n;
	static int grid[][];
	static StringBuilder sb = new StringBuilder();
	static boolean visited[][];	
	static ArrayList<Integer> list = new ArrayList<>();
	
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	static int count = 0;
	
	public static boolean canGo(int x, int y) {
		if(x<0 || y<0 || x>=n || y>=n)
			return false;
		else if(grid[x][y] == 0)
			return false;
		else if(visited[x][y])
			return false;
		return true;
	}
	
	public static void dfs(int x, int y) {
		visited[x][y] = true;
		count ++;
		for(int i=0; i<4; i++) {
			int nx = x+dx[i];
			int ny = y+dy[i];
			if(canGo(nx,ny))
				dfs(nx,ny);
		}
	}
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	n = Integer.parseInt(br.readLine());
    	grid = new int[n][n];
    	visited = new boolean[n][n];
    	
    	for(int i=0; i<n; i++) {
    		String inputLine = br.readLine();
    		for(int j=0; j<n; j++) {
    			grid[i][j] = inputLine.charAt(j)-'0';
    		}
    	}	
    	
    	for(int i=0; i<n; i++) {
    		for(int j=0; j<n; j++) {
    			if(grid[i][j] == 1 && !visited[i][j]) {
    				dfs(i,j);
    				list.add(count);
    				count = 0;
    			}
    		}
    	}
    	System.out.println(list.size());
    	Collections.sort(list);
    	for(int i : list) {
    		sb.append(i).append("\n");
    	}
    	System.out.print(sb);
    }
}
