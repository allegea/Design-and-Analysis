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

public class Johnson{

	static final int INF = Integer.MAX_VALUE;
	static class Edge implements Comparable<Edge>{
	
		int v;
		int w;
		int r;
		Edge(int vv, int ww, int rr)
		{
			this.v = vv;
			this.w = ww;
			this.r = rr;
		}
		
		Edge(int vv, int rr)
		{
			this.v = vv;
			this.r = rr;
		}
		
		@Override
		public int compareTo(Edge o)
		{
			return this.r-o.r;
		}
		
		@Override
		public String toString()
		{
			return v+" - "+r+" - "+w;
		}
	}
	
	static ArrayList<Edge>[] nodes;
	static int N, M;
        
        static int[] Dijkstra(int u)
        {
            int[] dist = new int[N+1];
            Arrays.fill(dist, INF);
            boolean[] visited = new boolean[N+1];
            dist[u] = 0;
            Queue<Edge> q = new PriorityQueue<Edge>();
            q.offer(new Edge(u, 0));
            while(!q.isEmpty())
            {
                Edge x = q.poll();
                if(!visited[x.v])
                {
                    visited[x.v] = true;
                    for(Edge w:nodes[x.v])
                    {
                        if(dist[w.v] > dist[x.v] + w.r)
                        {
                            dist[w.v] = dist[x.v] + w.r;
                            q.offer(new Edge(w.v, dist[w.v]));
                        }
                    }
                }
            }
            
            return dist;
            
        }
	
	public static void main(String[] args) throws IOException {
		//BufferedReader in = new BufferedReader(new FileReader("g3.txt"));
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer read = new StringTokenizer(in.readLine());
		N = Integer.parseInt(read.nextToken());
		M = Integer.parseInt(read.nextToken());
		int s = 0;
		nodes = new ArrayList[N+1];
		int[] dist = new int[N+1];
		for(int i=0;i<=N;i++)nodes[i] = new ArrayList<Edge>();
		
		for(int i=0;i<M;i++)
		{
			read = new StringTokenizer(in.readLine());
			int u = Integer.parseInt(read.nextToken());
			int v = Integer.parseInt(read.nextToken());
			int w = Integer.parseInt(read.nextToken());
			nodes[u].add(new Edge(v, w, 0));
		}
		
		//Add new Edge S to G prime
		for(int i=1;i<=N;i++)
		nodes[s].add(new Edge(i, 0, 0));
		
		//Run Bellman-Ford
		Arrays.fill(dist, INF);
		dist[s] = 0;
		for(int i=1;i<N;i++)
			for(int u=0;u<=N;u++)
				for(Edge x:nodes[u])
				dist[x.v] = Math.min(dist[x.v], dist[u]+x.w);
		
		//Check if the graph have negative cycle
		boolean noNegCycle = true;
		for(int u=0;u<=N && noNegCycle;u++)
                    for(Edge x:nodes[u])
			if(dist[x.v] > dist[u]+x.w)
			{	noNegCycle = false;
				break;
			}
                
		if(!noNegCycle){
			System.out.println("Negative Cycle detected");
			return;
		}
		
		//Reweighting 
		for(int i=1;i<=N;i++)
		{
			int size = nodes[i].size();
			for(int j=0;j<size;j++)
                        {
                            nodes[i].get(j).r = nodes[i].get(j).w + dist[i] - dist[j];
                            System.out.println(i+" - "+j+" - "+nodes[i].get(j).r);
                        }
		}
		
		int[][] paths = new int[N+1][N+1];
		
		//Run dijkstra N times
		for(int i=1;i<=N;i++)
			paths[i] = Dijkstra(i);
			
		//Set the distance of G graph
		for(int i=1;i<=N;i++)
			for(int j=1;j<=N;j++)
				paths[i][j] = paths[i][j]+ dist[j] - dist[i];
		
		//Find min of all the dist
		int min = INF;
		for(int i=1;i<=N;i++)
			for(int j=1;j<=N;j++)
                            if(i!=j)
				min = Math.min(min, paths[i][j]);
		
		System.out.println(min);
		
		
	}
}
