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
import java.util.Queue;

/**
 *
 * @author Alejandro E. Garces
 */
public class Prims {
        static boolean[] visited;
        static ArrayList<Edge>[] nodes;
        static Queue<Edge> q;
        static int co;

    
    public static class Edge implements Comparable {

        public Edge(int a, int w) {
            this.w = w;
            this.a = a;
        }
        
        int w;
        int a;

        @Override
        public int compareTo(Object o) {
           return w -((Edge)o).w;
        }

        @Override
        public String toString() {
            return w+"**"+a;
        }
    }
    
    static void process(int u)
    {
        System.out.println(u+" - "+co);
        co++;
        visited[u] = true;
        for(Edge x:nodes[u])
            if(!visited[x.a])
                q.offer(x);
        
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("edges.txt"));
        
        String[] read = in.readLine().split("[ ]+");
        int n = Integer.parseInt(read[0]);
        int m = Integer.parseInt(read[1]);
        nodes = new ArrayList[n+1];
        visited = new boolean[n+1];
        q = new PriorityQueue<Edge>();
        
        for(int i=0;i<=n;i++)nodes[i] = new ArrayList<Edge>();
        
        for(int i=0;i<m;i++)
        {
            read = in.readLine().split("[ ]+");
            int u = Integer.parseInt(read[0]);
            int v = Integer.parseInt(read[1]);
            int w = Integer.parseInt(read[2]);
            nodes[u].add(new Edge(v, w));
            nodes[v].add(new Edge(u, w));
        }
        
        long MST = 0;
       
        co = 0;
        process(1);
        while(!q.isEmpty())
        {
            //System.out.println(q);
            Edge x = q.poll();
            if(!visited[x.a])
            {
                MST+=x.w;
                process(x.a);
            }
        }
        int count = 0;
        for(int i=0;i<=n;i++)
            if(!visited[i])
                count++;
        System.out.println(MST+" - "+count);
        System.exit(0);
        in.close();
    }
}
