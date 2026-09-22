//생각나는 아이디어: 우선 brown,yellow를 더하고 해당 값이 될 수 있는 조합 전부 시도하기

class Solution {
    static boolean check(int r, int c, int brown, int yellow){
        
        int cnt = 0;
        //갈색 격자 수 세기
        for(int i=1; i<=r; i++){
            for(int j=1; j<=c; j++){
                if(i==1 || i==r || j==1 || j==c)
                    cnt++;
            }
        }
        
        if(cnt == brown)
            return true;
        return false;
    }
    
    public int[] solution(int brown, int yellow) {
        int total = brown + yellow;
          int[] answer = new int[2];
        for(int r=1; r<= 2000; r++){
            for(int c=1; c<= 2000; c++){
                if(r>=c && r*c == total && check(r,c,brown,yellow)){
                    answer[0] = r;
                    answer[1] = c;
                    return answer;
                }
            }
        }
        
        return answer;
    }
}