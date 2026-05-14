class Solution {
    static int dfs(int[] numbers, int target, int index, int sum){
        //0. 종료 조건: 모든 숫자를 다 사용했을떄
        if(index == numbers.length){
            if(sum == target)
                return 1;
            else
                return 0;
        }
        return dfs(numbers,target, index+1, sum+numbers[index])
                + dfs(numbers,target,index+1, sum-numbers[index]);
        
    }
    public int solution(int[] numbers, int target) {
        int answer = dfs(numbers,target,0,0);
        return answer;
    }
}