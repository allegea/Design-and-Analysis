/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

/**
 *
 * @author Alejandro E. Garces
 */
public class Clustering2 {

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
       
       public void showData()
       {
           System.out.println(Arrays.toString(ids));
           System.out.println(Arrays.toString(sizes));
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

    public static int HammingD(int u, int v)
    {
        int count = 0;
        for(int i=0;i<24 && count<=2;i++)
            if((u&(1<<i)) != (v&(1<<i)))
                count++;
        //System.out.println(count);
        return count;
        
    }
    
    public static class Data implements Comparable<Data>{
        
        int x;
        int bits;
        Data(int xx, int bb)
        {
            x = xx;
            bits = bb;
        }
        
        @Override
        public int compareTo(Data o)
        {
            return bits - o.bits;
        }
        
        @Override
        public String toString()
        {
            return x+" - "+bits;
        }
        
    }
   
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
       // BufferedReader in = new BufferedReader(new FileReader("clustering1test.txt"));
         BufferedReader in = new BufferedReader(new FileReader("clustering2.txt"));
        //Scanner in = new Scanner(new FileReader("clustering1test.txt"));
        
       String[] read = in.readLine().split("[ ]+");
       int n = Integer.parseInt(read[0]);
       int m = Integer.parseInt(read[1]);
       
       Data[] other = new Data[n];
       
       UnionFind uf = new UnionFind(n);
       
      // TreeMap<String, Integer> map = new TreeMap<String, Integer>();
       /*for(int i=0;i<n;i++)
       {
           String line = in.readLine().replaceAll(" ", "");
           int x = Integer.parseInt(line, 2);
           nodes[i] = line;
           amountbits[i] = Integer.bitCount(x);
           numbers[i] = x;
           other[i] = new Data(x, amountbits[i]);
          // System.out.println(numbers[i]+" - "+ amountbits[i]+" - "+nodes[i]);
       }*/
       
       for(int i=0;i<n;i++)
       {
           read = in.readLine().split("[ ]+");
           int count = 0;
           int x = 0;
           for(int j=0;j<m;j++)
               if(read[j].charAt(0)=='1')
               {
                     x|=(1<<j);
                   count++;
               }
            other[i] = new Data(x, count);
           
       }
       
       Arrays.sort(other);
       
       for(int i=0;i<n;i++)
       {
           for(int j=i+1;j<n;j++)
           {
               if(Math.abs(other[i].bits-other[j].bits)>2)break;
               if(uf.inSame(i, j))continue;
              //if(HammingD(other[i].x, other[j].x)<=2)
               if(Integer.bitCount(other[i].x^other[j].x)<=2)
                  uf.union(i, j);
               
                   
           }
           
       }
       
       System.out.println(uf.showN());
       
      /* for(int i=0;i<n;i++)
       {
           for(int j=i+1;j<n;j++)
           {
               if(uf.inSame(i, j))continue;
               
               if(Math.abs(amountbits[i]-amountbits[j])<=2)
               {
                  // System.out.println(i+" - "+j);
                   if(HammingD(numbers[i], numbers[j])<=2)
                   uf.union(i, j);
               }
                   
           }
           
       }
       
       System.out.println(uf.showN());*/
      // uf.showData();
       
        
        //while()
       
        System.exit(0);
        in.close();
    }
}
