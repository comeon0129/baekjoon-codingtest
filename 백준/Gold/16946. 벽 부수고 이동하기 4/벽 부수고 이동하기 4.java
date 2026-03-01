import java.io.*;
import java.util.*;

public class Main {
	static int n,m;
	static int[][] map;
	static boolean[][] visited;
	static int[][] group; //그룹 번호를 담고 있음
	static int[] groupSize; //각 그룹 번호에 해당하는 사이즈를 담고 있음
	
	static boolean inRange(int x, int y) {
		return 0<= x && x<n && 0<= y && y<m;
	}
	
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	static int dfs(int x, int y, int number) {
		
		int cnt = 0;
		
		for(int i=0; i<4; i++) {
			int nx = x+dx[i];
			int ny = y+dy[i];
			
			if(inRange(nx, ny) && !visited[nx][ny] && map[nx][ny] == 0) {
				visited[nx][ny] = true;
				group[nx][ny] = number;
				cnt += dfs(nx,ny,number);
			}
		}
		
		return cnt+1;
		
	}
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	m = Integer.parseInt(st.nextToken());
    	map = new int[n][m];
    	visited = new boolean[n][m];
    	group = new int[n][m];
    	groupSize = new int[1000000];
    
    	for(int i=0; i<n; i++) {
    		String input = br.readLine();
    		for(int j=0; j<m; j++) {
    			map[i][j]=  input.charAt(j) - '0';
    		}
    	}
    	
    	int groupNum = 1;
    	
    	for(int i=0; i<n; i++) {
    		for(int j=0; j<m; j++) {
    			if(map[i][j] == 0 && !visited[i][j]) {
    				visited[i][j] = true;
    				group[i][j] = groupNum; 
    				groupSize[groupNum] = dfs(i,j, groupNum);
    				groupNum++;
    			}
    		}
    	}
    	
    	//여기까지 했으면 그룹이랑 그룹사이즈들이 다 정해졌을거고, 이제 벽들 돌면서 인접한 그룹 사이즈만 더해주면 됌
    	
    	for(int i=0; i<n; i++) {
    		for(int j=0; j<m; j++) {
    			ArrayList<Integer> prevNums = new ArrayList<>(); //이전 그룹의 번호들 저장
    			int cnt = 0; //최종 벽에 더해질 값
    			if(map[i][j] == 1) {
    				for(int k=0; k<4; k++) {
    					int nx = i+dx[k];
    					int ny = j+dy[k];
    					
    					if(inRange(nx,ny) && map[nx][ny] == 0) {
    						if(prevNums.contains(group[nx][ny]))
    							continue;
//    						System.out.println(i+","+j+"좌표에서의 더해지는 값들: "+groupSize[group[nx][ny]]);
    						cnt+=groupSize[group[nx][ny]];
    						prevNums.add(group[nx][ny]);
    					}
    				}
    			}
    			map[i][j] += cnt;
    		}
    	}
    	
    	for(int i=0; i<n; i++) {
    		for(int j=0; j<m; j++) {
    			System.out.print(map[i][j] %10);
    		}
    		System.out.println();
    	}
    }
}