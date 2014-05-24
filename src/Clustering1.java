/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author Alejandro E. Garces
 */
public class Clustering1 {

   public static class UnionFind {
       
       public int[] ids;
       public int[] sizes;
       public int n;
       public int m;
       UnionFind(int nn)
       {
           n = nn;
           m = n+1;
           ids = new int[m+1];
           sizes = new int[m+1];
           for(int i=0;i<m;i++)
           {
               ids[i] = i;
               sizes[i] = 1;
           }
       }
       
       public int root(int i)
       {
           return ids[i]==i?i:( ids[i] = root(ids[i]));
       }
       
       public boolean inSame(int i, int j)
       {
           return root(i) == root(j);
       }
       
       public void union(int i, int j)
       {
           int u = root(i);
           int v = root(j);
           
           if(sizes[u]>sizes[v])
           {
               ids[v] = u;
               sizes[u]+=sizes[v];
           }else
           {
               ids[u] = v;
               sizes[v]+=sizes[u];
           }
           n--;
       }
       
       public int showN()
       {
           System.out.println("|| "+n);
           return n;
       }
   }
       
       public static class Edge implements Comparable<Edge>{
           
           int u;
           int v;
           int w;
           Edge(int uu, int vv, int ww)
           {
               u = uu;
               v = vv;
               w = ww;
           }
           
           @Override
           public int compareTo(Edge o)
           {
               return this.w-o.w;
           }
           
           @Override
           public String toString()
           {
               return u+" - "+v+" - "+w;
           }
       }

   
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
       // BufferedReader in = new BufferedReader(new FileReader("clustering1test.txt"));
         BufferedReader in = new BufferedReader(new FileReader("clustering1.txt"));
        //Scanner in = new Scanner(new FileReader("clustering1test.txt"));
        
        ArrayList<Edge> edges = new ArrayList<Edge>();
        int k = 4;
        int n = Integer.parseInt(in.readLine());
        
        UnionFind uf = new UnionFind(n);
        String line = "";
        while((line = in.readLine()) != null && line.length() > 0)
        {
            String[] read = line.split("[ ]+");
            int u = Integer.parseInt(read[0]);
            int v = Integer.parseInt(read[1]);
            int w = Integer.parseInt(read[2]);
            edges.add(new Edge(u, v, w));
        }
        
        Collections.sort(edges);
        for(Edge x:edges)
        {
            System.out.println(x.toString());
            if(!uf.inSame(x.u, x.v))
            {
                 if(uf.showN() == k){
                System.out.println("************"+x.w);
                break;
                 }
                uf.union(x.u, x.v);
               // uf.showN();
                
            }
        }
        
        
        //while()
       
        System.exit(0);
        in.close();
    }
}
