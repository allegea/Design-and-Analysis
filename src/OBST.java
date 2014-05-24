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
public class OBST {
    
static int optimalSearchTree(int freq[], int n)
{
    /* Create an auxiliary 2D matrix to store results of subproblems */
    int[][] cost= new int[n+1][n+1];

    /* cost[i][j] = Optimal cost of binary search tree that can be
       formed from keys[i] to keys[j].
       cost[0][n-1] will store the resultant cost */

    // For a single key, cost is equal to frequency of the key
    for (int i = 0; i < n; i++)
        cost[i][i] = freq[i];

    // Now we need to consider chains of length 2, 3, ... .
    // L is chain length.
    for (int L=1; L<=n; L++)
    {
        // i is row number in cost[][]
        for (int i=0; i<=n-L+1; i++)
        {
            // Get column number j from row number i and chain length L
            int j = i+L-1;
            System.out.println(i+" - "+j+" - "+L);
            cost[i][j] = Integer.MAX_VALUE;

            // Try making all keys in interval keys[i..j] as root
            for (int r=i; r<=j; r++)
            {
               // c = cost when keys[r] becomes root of this subtree
               int c = ((r > i)? cost[i][r-1]:0) +
                       ((r < j)? cost[r+1][j]:0) +
                       sum(freq, i, j);
               if (c < cost[i][j])
                  cost[i][j] = c;
            }
        }
    }
    for(int i=0;i<n;i++)
        System.out.println(Arrays.toString(cost[i]));
    return cost[0][n-1];
}

    // A utility function to get sum of array elements freq[i] to freq[j]
    static int sum(int freq[], int i, int j)
    {
        int s = 0;
        for (int k = i; k <=j; k++)
        s += freq[k];
        return s;
    }
            
    public static void main(String[] args) throws IOException{
        
        //BufferedReader in = new BufferedReader(new FileReader("SCC.txt"));
        //int freq[] = {5, 40, 8, 4, 10, 10, 23, 0};
        int freq[] = {20, 5, 17, 10, 20, 3, 25, 0};
       // Arrays.sort(freq);
        int n = freq.length-1;

        System.out.printf("Cost of Optimal BST is %d ",optimalSearchTree(freq, n));
       
        
    }
    
}

