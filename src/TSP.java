/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 *
 * @author Alejandro E. Garces
 */

public class TSP{

	static int N;
        static int MAX;
        static int M;
        static int MMAX;
	static float[][] dist;
        static float[] ori;
        static float INF = Float.MAX_VALUE/2;
	static float[][] dp;
	static float tsp(int pos, int bitmask)
	{
                if(bitmask == MAX)return ori[pos];
                if(dp[pos][bitmask] != INF) return dp[pos][bitmask];
                float min = INF;
                for(int i=0;i<N-1;i++)
                    if(i!= pos && ((1<<i)&bitmask) == 0)
                    min = Math.min(min, dist[pos][i]+tsp(i, bitmask|(1<<i)));
                return (dp[pos][bitmask] = min);
	}
	public static void main(String[] args) throws IOException{
	
		Scanner in = new Scanner(new FileReader("tsp.txt"));
		N = in.nextInt();
                MAX = (1<<(N-1))-1;
		dist = new float[N-1][N-1];
		Point2D.Float[] points = new Point2D.Float[N];
                dp = new float[N-1][1<<(N-1)];
		for(int i=0;i<N;i++)
                    points[i] = new Point2D.Float(in.nextFloat(), in.nextFloat());
                
                for(int i=0;i<N-1;i++)
                Arrays.fill(dp[i], INF);
                ori = new float[N-1];
		
                
		for(int i=0;i<N-1;i++)
		{	
                        ori[i] = (float)points[i+1].distance(points[0]);
                        dist[i][i] = 0;
			for(int j=i+1;j<N-1;j++)
			dist[i][j] = dist[j][i] = (float)points[i+1].distance(points[j+1]);
                        System.out.println(Arrays.toString(dist[i]));
		}
                System.out.println("***");
                double answer = INF;
                for(int i=0;i<N-1;i++)
                {
		 answer = Math.min(answer, tsp(i, 1<<i)+ori[i]);
                 System.out.println(i+" - "+answer);
                }
                
                System.out.println("Final "+answer);
		in.close();
		System.exit(0);
		
	}
}