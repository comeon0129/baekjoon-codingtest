import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static int[][] people;
	static int minVal = Integer.MAX_VALUE;
	static boolean[] chosen;
	
	static void calcMin() {
		int startTeam = 0;
		int linkTeam = 0;
		
		for(int i=0; i<n; i++) {
			if(chosen[i]) {
				for(int j=0; j<n; j++) {
					if(chosen[j] && j != i)
						startTeam += people[i][j];
				}
			}
			else {
				for(int j=0; j<n; j++) {
					if(!chosen[j] && j!= i)
						linkTeam += people[i][j];
				}
			}
		}
		minVal = Math.min(minVal, Math.abs(startTeam-linkTeam));
	}
	
	static void chooseTeam(int start, int depth) {
		if(depth == n/2 ) {
			calcMin();
			return;
		}
		
		for(int i=start; i<n; i++) {
			if(!chosen[i]) {
				chosen[i] = true;
				chooseTeam(i+1, depth+1);
				chosen[i] = false;
			}
		}
	}
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	n = Integer.parseInt(br.readLine());
    	people =new int[n][n];
    	chosen = new boolean[n];
    	for(int i=0; i<n; i++) {
    		StringTokenizer st = new StringTokenizer(br.readLine());
    		for(int j=0; j<n; j++) {
    			people[i][j] = Integer.parseInt(st.nextToken());
    		}
    	}
    	chooseTeam(0,0);
    	System.out.println(minVal);
    }
}
