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
import java.util.TreeSet;

/**
 *
 * @author Alejandro E. Garces
 */
public class SCCs {
    
    public static int[] position;
    public static boolean[] explored;
    public static int label;
    public static int size;
    
    public static void DFS(ArrayList[] nodes, int s, int[] pos)
    {
        int sum=0;
        explored[s] = true;
        
        if(nodes[s]!=null)
        {
            //System.out.println(s+" - "+nodes[s]);
            for(Object i:nodes[s])
            {
                int u = Integer.parseInt(i.toString());
                if(!explored[u])
                    DFS(nodes, u, pos);
            }
            
        }
        size++;
        pos[label++]=s;

    }
            
            
    public static void main(String[] args) throws IOException{
        
        BufferedReader in = new BufferedReader(new FileReader("SCC.txt"));
        
        String line = "";
        int count = 0;
        int n = 875714;
        int u,v;
        String[] read;
        ArrayList[] nodes = new ArrayList[n+1];
        ArrayList[] nodesR = new ArrayList[n+1];
        explored = new boolean[n+1];
        int[] pos1 = new int[n];
        int[] pos2 = new int[n];
        label = 0;

        while((line = in.readLine())!=null)
        {
            read = line.split("[ ]+");
            u = Integer.parseInt(read[0]);
            v = Integer.parseInt(read[1]);
            if(nodes[u]==null)
                nodes[u] = new ArrayList<Integer>();
            nodes[u].add(v);
            
            if(nodesR[v]==null)
                nodesR[v] = new ArrayList<Integer>();
            nodesR[v].add(u);
        }
        
      //  System.out.println(Arrays.toString(nodesR));
        for(int i=n;i>=1;i--)
        {
                if(!explored[i])
                {
                    size = 0;
                    DFS(nodesR, i,pos1);
                  // System.out.println("DFS - "+ size);
                }

        }

        //System.out.println("First");
       // System.out.println("POS - "+Arrays.toString(pos1));
        label = 0;
        explored = new boolean[n+1];
       
        ArrayList<Integer> answer = new ArrayList<Integer>();
        for(int i=n-1;i>=0;i--)
        {
                if(!explored[pos1[i]]){
                     size = 0;
                     DFS(nodes, pos1[i],pos2);
                    // System.out.println("DFS - "+ size);
                     answer.add(size);
                }
                   

        }
        
        Collections.sort(answer);
        Collections.reverse(answer);
        
        for(int i=0;i<5;i++)
            System.out.print(answer.get(i)+",");
        //System.out.println(answer);
        
       // System.out.println("POS - "+Arrays.toString(pos1));
        
    }
    
}

