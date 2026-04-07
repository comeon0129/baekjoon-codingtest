import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static int[] arr;
	static ArrayList<Integer> answer = new ArrayList<>();
	
	static int binarySearch(int num) {
		int left = 0;
		int right = answer.size()-1;
		int pos = answer.size(); // 불가능한 값으로 초기화
		while(left <= right) {
			int mid = (left+right)/2;
			if(answer.get(mid) >= num) {
				pos = mid;
				right = mid-1; //지금 있는 자리가 num보다 크다는 건 더 왼쪽에도 num보다 큰 수가 있을 수 있음. 따라서 왼쪽으로 이동
			}
			else {
				left = mid+1; //지금 있는 자리가 num보다 작다는 건 더 오른쪽도 num 보다 작을 수 있음 따라서 오른쪽으로 이동.
			}
		}
		return pos;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int num : arr) {
			if(answer.isEmpty() || num > answer.get(answer.size()-1)) {
				//현재 숫자가 리스트 마지막 값보다 크면 그냥 뒤에 추가
				answer.add(num);
			}
			else {
				//작거나 같다면 들어갈 자리를 이분 탐색으로 찾아서 찾은 자리에 덮어쓰기
				int pos = binarySearch(num);
				answer.set(pos, num);
			}
		}
				System.out.println(answer.size());
		
	}
}
