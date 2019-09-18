package LeetCode.hard;

import java.util.concurrent.atomic.AtomicInteger;

public class ArraySolution {

    //980. Unique Paths III
    public int uniquePathsIII(int[][] grid) {
        int M = grid.length;
        int N = grid[0].length;
        int zerocount = 0;
        int[][] dir =new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        AtomicInteger res = new AtomicInteger(0);
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++)
                if (grid[i][j] == 0) zerocount++;
        }
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++)
                if (grid[i][j] == 1) dfs(grid,i,j,0,zerocount,dir,res);
        }
        return res.intValue();

    }
    private  void dfs(int[][] grid,int x ,int y,int pathcount,int zerocount,int[][] dir,AtomicInteger res){
        if(grid[x][y]==2 && pathcount==zerocount)res.addAndGet(1);
        int M =grid.length;
        int N =grid[0].length;
        int pre =grid[x][y];
        if(pre==0)++pathcount;
        grid[x][y]=-1;
        for (int[] d : dir){
            int nx =x+d[0];
            int ny =y+d[1];
            if(nx <0 || nx >=M || ny <0 || ny >=N || grid[nx][ny]==-1)
                continue;
            dfs(grid,nx,ny,pathcount,zerocount,dir,res);
        }
        grid[x][y]=pre;
    }

}
