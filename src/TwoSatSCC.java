/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.*;
import java.util.*;

/**
 *
 * @author Alejandro E. Garces
 */

public class TwoSatSCC {

	static HashMap<Integer, Integer> pos;
        static ArrayList<Integer>[] nodes;
        static ArrayList<Integer>[] nodesR;
        static boolean[] visited;
        static int[] posSCC;
	static int[] values;
	static int N, M, label;
        static int MAX ;
        static boolean checkC = true;
        static HashSet<Integer> onSameSCC;
        
	
	static int pos(int x)
	{
		int n = pos.size();
		if(pos.containsKey(x))
		return pos.get(x);
		pos.put(x, n);
		return n;
	}
        
        static void SCC(int u)
        {
            if(visited[u])return;
            visited[u] = true;
            for(int v:nodesR[u])
                SCC(v); 
            
            posSCC[label++] = u;
        }
        
        static void DFS(int u)
        {
            if(visited[u] || !checkC)return;
            visited[u] = true;
            
            if(!onSameSCC.add(Math.abs(values[u])))
            {
                checkC = false;
                return;
            }
                    
            for(int v:nodes[u])
                DFS(v); 
        }
	
	public static void main(String[] args) throws IOException{
                
            int k = 0;
                for( k=1;k<=6;k++)
                {
                //String file = "2ST6";
                String file = "2sat"+k;
		System.setIn(new FileInputStream(file+".txt"));
                //System.setOut(new PrintStream(new FileOutputStream(file+"O.txt")));
                Scanner in = new Scanner(System.in);
                

                label = 0;
                checkC = true;
		N = in.nextInt();
                MAX = 4*N+1;
		pos = new HashMap<Integer, Integer>();
                onSameSCC = new HashSet<Integer>();
		values = new int[MAX];
                nodes = new ArrayList[MAX];
                nodesR = new ArrayList[MAX];
                
                for(int i=0;i<MAX;i++){
                    nodes[i] = new ArrayList<Integer>();
                    nodesR[i] = new ArrayList<Integer>();
                }
                int u, v, x, y;
		
		for(int i=0;i<N;i++)
		{
			x = in.nextInt();
			y = in.nextInt();
                        // (x0|x1) <==> (-x0 => x1) && (-x1 => x0)
                        u = pos(-x);
			v = pos(y);
                        values[u] = -x;
                        values[v] = y;
                        nodes[u].add(v);
                        //System.out.println(u+" -> "+v);
                        nodesR[v].add(u);
                        
                        u = pos(x);
			v = pos(-y);
                        values[u] = x;
                        values[v] = -y;
                        //System.out.println(v+" -> "+u);
                        nodes[v].add(u);
                        nodesR[u].add(v);
		}
		
                M = pos.size();
                visited = new boolean[M];
                posSCC = new int[M];
                //System.out.println(N+" - "+M+" - "+MAX);
                
                for(int i=0;i<M;i++)
                    if(!visited[i])
                        SCC(i);

                visited = new boolean[M];
                for(int i=M-1;i>=0 && checkC;i--)
                    if(!visited[posSCC[i]])
                    {
                        onSameSCC = new HashSet<Integer>();
                        DFS(posSCC[i]);
                    }
                    
                
		System.out.println("Case #"+k+": "+(checkC?"1":"0"));
		in.close();
                }
                
		System.exit(0);
	}
}