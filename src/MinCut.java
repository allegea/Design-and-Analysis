/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

/**
 *
 * @author Alejandro E. Garces
 */
public class MinCut {
public static int answer = Integer.MAX_VALUE;
   
    public static int MinCut(ArrayList<ArrayList<Integer>> adjList, ArrayList<Integer> visited,HashMap<Integer, HashSet<Integer>> merge)
    {
        //ArrayList<ArrayList<Integer>> adjList = new ArrayList<ArrayList<Integer>>(adjListB);
       // ArrayList<Integer> visited = new ArrayList<Integer>(visitedB);
       // HashMap<Integer, HashSet<Integer>> merge = new HashMap<Integer,HashSet<Integer>>(mergeB);

        Random select = new Random();
        ArrayList<Integer> aux;
        HashSet<Integer> x;
        int size = 0;
        int posA = 0;
        int posB = 0;
        int posAA = 0;
        int posBB = 0;
        
        /*System.out.println(visited);
            System.out.println(adjList);
            System.out.println(merge);*/
        boolean found = false;    
        while((size = visited.size())>2)
        {
            found = false;
           posA = select.nextInt(size);
           posAA = visited.get(posA);
           
           posB = select.nextInt(adjList.get(posAA).size());
           posBB = adjList.get(posAA).get(posB);
          /* System.out.println("------------------\n"+posA+" PosA "+posAA);
           System.out.println(posB+" PosB "+posBB);*/
           //posBB = visited.get(posB);
           for(int i=0;i<visited.size() && !found;i++)
               if(i!=posA && merge.get(visited.get(i)).contains(posBB))
               {
                   posBB = visited.get(i);
                   found=true;
               }
          // posBB = adjList.get(posAA).get(posB);
           //if(!found)continue;
           
           posAA = visited.remove(posA);
          // System.out.println("------------------\n"+posA+" PosA "+posAA);
           //System.out.println(posB+" PosB "+posBB);
           
           x = merge.remove(posAA);
           merge.get(posBB).addAll(x);
           aux = adjList.get(posAA);

           for(int i=0;i<adjList.get(posBB).size();i++)
               if(merge.get(posBB).contains(adjList.get(posBB).get(i)))
               {
                   adjList.get(posBB).remove(i);
                   i--;
               }
           
           for(int i=0;i<aux.size();i++)
               if(!merge.get(posBB).contains(aux.get(i)))
               adjList.get(posBB).add(aux.get(i));
           
           
           
         /*  System.out.println(visited);
            System.out.println(adjList);
            System.out.println(merge);*/
 
        }
       /* System.out.println("------------------");
        System.out.println(visited);
        System.out.println(adjList);
        System.out.println(merge);*/
        
        return adjList.get(visited.get(0)).size();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here

        int[] answ = new int[200*200];
        for(int qq=0;qq<200*200;qq++)
        {
        BufferedReader in = new BufferedReader(new FileReader("kargerMinCut.txt"));
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> visited = new ArrayList<Integer>();
        HashMap<Integer, HashSet<Integer>> merge = new HashMap<Integer,HashSet<Integer>>();
        HashSet<Integer> x;
        
        String line = "";
        
        while((line = in.readLine())!=null)
        {
            String[] read = line.split("\\t");
            //System.out.println(Arrays.toString(read));
            int pos = Integer.parseInt(read[0])-1;
            adjList.add(pos, new ArrayList<Integer>());
             x = new HashSet<Integer>();
             x.add(pos);
            merge.put(pos, x);
            visited.add(pos);
            for(int i=1;i<read.length;i++)
                adjList.get(pos).add(Integer.parseInt(read[i])-1);
        }
        
        
        
        
           /* System.out.println("************************************");
            System.out.println("************************************");*/
            answ[qq]=MinCut(adjList,visited,merge);
            answer = Math.min(answer, answ[qq]);
           /* System.out.println(answer);
            System.out.println("************************************");
            System.out.println("************************************");*/
            System.out.println(qq);
        
        }
       /* System.out.println(merge);
        System.out.println(adjList);
        System.out.println(visited);*/
        System.out.println("respuesta "+answer);
        System.out.println(Arrays.toString(answ));
        
    }
}
