/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author Alejandro E. Garces
 */
public class FloydWarshall {

    static final int INF = Integer.MAX_VALUE/2;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
       // BufferedReader in = new BufferedReader(new FileReader("clustering1test.txt"));
        int answer = INF;
        //for(int h = 1; h<= 3;h++)
        {
                //BufferedReader in = new BufferedReader(new FileReader("g"+h+".txt"));
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer read = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(read.nextToken());
            int m = Integer.parseInt(read.nextToken());
            int[][] dist = new int[n+1][n+1];
            for(int i=0;i<=n;i++)
            {
                Arrays.fill(dist[i], INF);
                dist[i][i] = 0;
            }

            for(int i=0;i<m;i++)
            {
                    read = new StringTokenizer(in.readLine());
                    int u = Integer.parseInt(read.nextToken());
                    int v = Integer.parseInt(read.nextToken());
                    int w = Integer.parseInt(read.nextToken());
                    dist[u][v] = w;
            }

            int min = INF;
            for(int k=1;k<=n;k++)
                for(int i=1;i<=n;i++)
                    for(int j=1;j<=n;j++)
                    {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k]+dist[k][j]);
                        if(i != j) min = Math.min(min, dist[i][j]);
                    }
            for(int i=0;i<=n;i++)
                System.out.println(Arrays.toString(dist[i]));
            boolean noNegCycle = true;
            for(int k=1;k<=n && noNegCycle;k++)
                if(dist[k][k] < 0) noNegCycle = false;

            if(noNegCycle)answer = Math.min(answer, min);
                //System.out.println(min);
            //else System.out.println("Negative Cycle");
            in.close();
        }
        System.out.println(answer);
        System.exit(0);
        
    }
}
