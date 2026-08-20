class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        
        //트럭이 가진 배달cap과 수거cap
        int dCap =0;
        int pCap =0;
        
        for(int i=n-1; i>=0; i--){
            //현재 보고 있는 집의 배달/수거량을 트럭cap에서 뺌
            //음수가 된다는건 물류창고에서 트럭이 와야한다는뜻
            
            dCap -= deliveries[i]; 
            pCap -= pickups[i];
            
            int count =0; //이 집까지 와야하는 왕복횟수
            while(dCap <0 || pCap < 0){
                dCap+=cap;
                pCap+=cap;
                count++;
            }
            
            answer+=(long)(i+1)*2*count;
        }
        
        
        
        return answer;
    }
}