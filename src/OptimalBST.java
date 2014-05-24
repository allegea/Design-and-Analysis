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
public class OptimalBST {

    static int gap = 1;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader in = new BufferedReader(new FileReader("knapsack2.txt"));
        //Scanner in = new Scanner(new FileReader("clustering1test.txt"));
        double[] prob = {0, 0.05 , .4, .08, .04, .1, .1, .23};
        int n = prob.length-1;
        double[][] dp = new double[n][n+1];
        for(int s=0;s<n;s++)
            for(int i=1; i<=n;i++)
            {
                double min = Double.MAX_VALUE;
                for(int k=i;k<=i+s;k++)
                {
                   // min = Math.min(min, prob[k]+dp[i,][])
                }
            }
       
       
       
        System.exit(0);
        in.close();
    }
}
