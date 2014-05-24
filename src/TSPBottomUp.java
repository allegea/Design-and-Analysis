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

public class TSPBottomUp{

	static int N;
        static int MAX;
        static int M;
        static int MMAX;
	static float[][] dist;
        static float INF = Float.MAX_VALUE/2;
	static float[][] dp;

	public static void main(String[] args) throws IOException{
	
		Scanner in = new Scanner(new FileReader("tsp2.txt"));
		N = in.nextInt();
                MAX = (1<<N)-1;
		dist = new float[N+1][N+1];
		Point2D.Float[] points = new Point2D.Float[N+1];
                dp = new float[N+1][1<<N];
		for(int i=1;i<=N;i++)
                    points[i] = new Point2D.Float(in.nextFloat(), in.nextFloat());
                
                //for(int i=1;i<=N;i++)
                //Arrays.fill(dp[i], INF);
		
                
		for(int i=1;i<=N;i++)
		{	dist[i][i] = 0;
			for(int j=i+1;j<=N;j++)
			dist[i][j] = dist[j][i] = (float)points[i].distance(points[j]);
		}
                
                for(int k=2; k <= N; k++) // Ohne 0 & 1 für k, bis und mit iN
                {
                        dp[k][1^(1<<(k-1))] = dist[k][1]; // Stadt 1 + Stadt k
                }
                
            for (int s = 3; s <= N; s++) // Mächtigkeitszahl erhöhen, ohne 0,1,2
            {
                for (int S = (1 << (s - 2)); S < (1 << N); S++) // Beginne bei 2^(s-2), ende bei 2^iN
                {
                    if (Integer.bitCount(S) == s) // Menge mit der Mächtigkeitszahl s
                    {
                        for (int k = 2; k <= N; k++) // Alle Städte durchgehen
                        {
                            if ((S & (1 << (k - 1))) > 0) // Wenn Stadt k in Menge S vorkommt... && S >= (1<<(k-1))
                            {
                                dp[k][S] = INF;
                                for (int m = 2; m <= N; m++) // Zweite Verbindungsstadt finden
                                {
                                    if (m == k || (S & (1 << (m - 1))) == 0) // m darf nicht gleich k sein & m muss in der Menge S vorhanden sein
                                    {
                                        continue;
                                    }
                                    System.out.println(S+" - "+k+" - "+(S ^ (1 << (k - 1))));
                                    System.out.println(Integer.toBinaryString(S)+" - "+Integer.toBinaryString(k)+" - "+Integer.toBinaryString(S ^ (1 << (k - 1))));
                                    System.out.println("**********");
                                    dp[k][S] = Math.min(dp[m][S ^ (1 << (k - 1))] + dist[m][k], dp[k][S]); // Was ist kleiner der "neue" Wert oder der Ursprungswert?
                                }
                            }
                        }
                    }
                }
            }

            float opt = INF;
            for (int k = 2; k <= N; k++) {
                opt = Math.min(dp[k][(1 << (N)) - 1] + dist[1][k], opt);
            }
            System.out.println("**"+opt);
                
                /*for (int bitmask = (1 << N) - 2; bitmask > 0; bitmask--)
                    for(int i=0;i<N;i++)
                        if(((1<<i)&bitmask) != 0)
                            for(int j=0;j<N;j++)
                                if(((1<<j)&bitmask) == 0)
                                     dp[i][bitmask] = Math.min(dp[i][bitmask], dp[j][bitmask | (1 << j)] + dist[i][j]);
                                    
                int min = 0;
                for (int i = 1; i < N; i++)
	                if (dp[i][1 << i]  < dp[min][1 << min])
	                    min = i;
		
                System.out.println("** "+dp[min][1<<min]);*/
		in.close();
		System.exit(0);
		
	}
}