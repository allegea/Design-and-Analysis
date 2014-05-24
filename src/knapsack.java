/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 *
 * @author Alejandro E. Garces
 */
public class knapsack {

    static int[] v;
    static int[] w;
    static int size;
    static HashMap<String, Integer> map;
     static HashMap<Integer, HashMap<Integer, Integer>> map2;
    static int DP(int i, int weight)
    {
        if(i == 0 || weight == 0) return 0;
        if(map.containsKey(i+"-"+weight))return map.get(i+"-"+weight);
        if(w[i-1]>weight)return DP(i-1, weight);
        int value = Math.max(DP(i-1, weight), v[i-1]+DP(i-1, weight-w[i-1]));
        map.put(i+"-"+weight, value);
        return value;
    }
    static int DP2(int i, int weight)
    {
        if(i == 0 || weight == 0) return 0;
        if(map2.containsKey(i) && map2.get(i).containsKey(weight))
                return map2.get(i).get(weight);
        
        if(w[i-1]>weight)return DP(i-1, weight);
        int value = Math.max(DP(i-1, weight), v[i-1]+DP(i-1, weight-w[i-1]));
        if(map2.containsKey(i))
                map2.get(i).put(weight, value);
        else map2.put(i, new HashMap<Integer, Integer>(weight, value));
        return value;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
       // BufferedReader in = new BufferedReader(new FileReader("clustering1test.txt"));
         BufferedReader in = new BufferedReader(new FileReader("knapsack1.txt"));
        //Scanner in = new Scanner(new FileReader("clustering1test.txt"));
        String[] read = in.readLine().split("[ ]+");
        size = Integer.parseInt(read[0]);
        int n = Integer.parseInt(read[1]);
        v = new int[n+1];
        w = new int[n+1];
        map = new HashMap<String, Integer>();
        map2 =  new HashMap<Integer, HashMap<Integer, Integer>>();
        for(int i=0;i<n;i++)
        {
            read = in.readLine().split("[ ]+");
            v[i+1] = Integer.parseInt(read[0]);
            w[i+1] = Integer.parseInt(read[1]);
        }
        
        System.out.println(DP2(n+1, size));
        System.out.println("Map Size "+map.size());
        
        //Bottom Up version
        int[][] dp = new int[n+1][size+1];
        for(int i=1;i<=n;i++)
            for(int j=0;j<=size;j++)
            {
                if(w[i] > j)
                    dp[i][j] = dp[i-1][j];
                else dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-w[i]]+v[i]);
            }
        //for(int i=0)
        
        System.out.println(dp[n][size]);
        
       
       
        System.exit(0);
        in.close();
    }
}
