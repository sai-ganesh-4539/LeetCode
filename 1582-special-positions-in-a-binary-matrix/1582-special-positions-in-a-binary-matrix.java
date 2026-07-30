class Solution {
    public int numSpecial(int[][] mat) {
        int row = mat.length;
        int col = mat[0].length;
        int count = 0;
        int rowsum[] = new int[row];
        int colsum[] = new int[col];
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                rowsum[i] += mat[i][j];
                colsum[j] += mat[i][j];
            }
        }
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(mat[i][j]==1 && rowsum[i]==1 && colsum[j]==1){
                    count++;
                    
                }
            }
        }
        return count;
    }
}