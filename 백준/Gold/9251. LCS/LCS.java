import java.io.*;
import java.util.*;

public class Main {	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	String input1 = br.readLine();
    	String input2 = br.readLine();
    	
    	int n = input1.length();
    	int m = input2.length();
    	
    	
    	int[][] dp = new int[n+1][m+1];
    	
    	for(int i=1; i<=n; i++) {
    		for(int j=1; j<=m; j++) {
    			if(input1.charAt(i-1) == input2.charAt(j-1)) {
    				dp[i][j] = dp[i-1][j-1]+1;
    			}
    			else {
    				dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
    			}
    		}
    	}
    	
    	System.out.println(dp[n][m]);
    	
    }
}
