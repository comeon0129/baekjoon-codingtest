import java.io.*;
import java.util.*;

public class Main {
	static int k,n; // k: 이미 가지고 있는 랜선의 개수 n: 필요한 랜선의 개수
	static int[] length;
	
	static boolean check(long mid) {
		long cnt = 0;
		for(int i=1; i<=k; i++) {
			cnt+= length[i] / mid;
		}
		
		if(cnt >=n)
			return true;
		return false;
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		k = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		length = new int[k+1];
		for(int i=1; i<=k; i++)
			length[i] = Integer.parseInt(br.readLine());
		
		long left = 1;
		long right = Integer.MAX_VALUE;
		long answer =0;
		
		while(left <= right) {
			long mid = (left+right) /2 ;
			
			if(check(mid)) { //해당 랜선 길이로 맞출 수 있으면 더 가능할 수도 있으니까 
				answer = mid;
				left= mid+1;
			}
			else { //불가능하면 그거보다 왼쪽부분만 봐야하니까
				right = mid-1;
			}
		}
		System.out.println(answer);
	}
		
}
