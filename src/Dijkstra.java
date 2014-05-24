/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

/**
 *
 * @author Alejandro E. Garces
 */
public class Dijkstra {
    
    public static class Edge implements Comparable{
        
        public int v;
        public long w;
        
        public Edge(int v,long w){
            this.v=v;
            this.w=w;
        }

        @Override
        public int compareTo(Object o) {
            return (int)(this.w-((Edge)o).w);
        }
        
        
    }
    
    public static void main(String[] args) throws IOException{
        
       // System.out.println((-1%5));
        BufferedReader in = new BufferedReader(new FileReader("dijkstraData.txt"));
        int[] show = {7,37,59,82,99,115,133,165,188,197};
        int n = 200;
         ArrayList[] nodes = new ArrayList[n+1];
        boolean[] explored = new boolean[n+1];
        long[] dist = new long[n+1];
        PriorityQueue<Edge> queue = new PriorityQueue<Edge>();
        Edge poll,aux;
            Arrays.fill(dist, Long.MAX_VALUE);
            dist[1]=0;
        String line = "";
        String[] read;
        int u,v,w;
        
        while((line=in.readLine())!=null)
        {
            read = line.split("[ \t]+");
            u = Integer.parseInt(read[0]);
            if(nodes[u]==null)
                nodes[u] = new ArrayList<Edge>();
            
            for(int i=1;i<read.length;i++)
            {
                //System.out.println(read[i]);
                String[] adj = read[i].split(",");
                v = Integer.parseInt(adj[0]);
                w = Integer.parseInt(adj[1]);
                if(nodes[v]==null)
                nodes[v] = new ArrayList<Edge>();
                
                nodes[u].add(new Edge(v,w));
 
            }

        }
        
        queue.offer(new Edge(1,0));
        while(!queue.isEmpty())
        {
            poll = queue.poll();
            
            if(!explored[poll.v])
            {
                explored[poll.v]=true;
                for(Object x:nodes[poll.v])
                {
                    aux = (Edge)x;
                    if(dist[aux.v]>dist[poll.v]+aux.w)
                    {
                        dist[aux.v]=dist[poll.v]+aux.w;
                        queue.offer(new Edge(aux.v,dist[aux.v]));
                    }
                }
                
            }
            
        }
        
        
        System.out.println(Arrays.toString(dist));
        for(int i=0;i<show.length;i++)
            System.out.print(dist[show[i]]+",");

    }
}
