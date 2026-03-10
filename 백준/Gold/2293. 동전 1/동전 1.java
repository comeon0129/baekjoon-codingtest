import java.io.*;
import java.util.*;

public class Main {
	static int n,k;
	
	static int[] coin;
	static int[] dp; // i번쨰 원소로 끝나는 가장 긴 수열의 길이
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	k = Integer.parseInt(st.nextToken());
    	
    	coin = new int[n+1];
    	dp = new int[k+1];
    	
    	for(int i=1; i<=n; i++) {
    		String input = br.readLine();
    		coin[i] = Integer.parseInt(input);
    	}
    	
    	dp[0] = 1;
    	for(int i=1; i<=n; i++) {
    		for(int j=1; j<=k; j++) {
    			if(j >= coin[i]) {
    				dp[j] += dp[j-coin[i]];
    			}
    		}
    	}
    	System.out.println(dp[k]); 	
    }
}
