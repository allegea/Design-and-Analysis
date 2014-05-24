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

/**
 *
 * @author Alejandro E. Garces
 */
public class QuickSort {

    static long sum = 0;

    public static void swap(int[] array, int i, int j) {
        int aux;
        aux = array[i];
        array[i] = array[j];
        array[j] = aux;

    }

    public static int sort(int[] array, int piv, int l, int r) {

        sum += r - l;
        int i = l + 1;
        swap(array, l, piv);
       // System.out.println(piv + " - " + l + " - " + r + " - ***");
        for (int j = i; j <= r; j++) {
            if (array[j] < array[l]) {
                swap(array, j, i);
                i++;
            }
        }
       // System.out.println(Arrays.toString(array) + " - " + l + " - " + r + " - !!!!");
        swap(array, l, i - 1);


        return i - 1;

    }

    public static void quicksort(int[] array, int l, int r) {
        //System.out.println(Arrays.toString(array) + " - " + l + " - " + r + " - " + sum);
        if (r - l < 1) {
           // System.out.println("****");
            return;
        }
       // pivot(array, l,  r);
        int part = sort(array,l , l, r);
        quicksort(array, l, part - 1);
        quicksort(array, part+1, r);
    }

    public static int pivot(int[] array, int l, int r)
    {
        int[] aux = {array[l],array[r],array[l+(r-l)/2]};
        //System.out.println(array[l]+" - "+array[r]+" - "+array[l+(r-l)/2]+"$$$$$$$$$");
        Arrays.sort(aux);
        int answer = 0;
        if(aux[1]==array[l])answer= l;
        else if(aux[1]==array[r])answer= r;
        else if(aux[1]==array[l+(r-l)/2])answer= l+(r-l)/2;
        
       // System.out.println(Arrays.toString(aux)+" *!* "+answer);
        return answer;
        
        
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here

        BufferedReader in = new BufferedReader(new FileReader("QuickSort.txt"));
        int n= 10000;
        int[] array = new int[n];
       /* ArrayList<Integer> x = new ArrayList<Integer>();
        for(int i=1;i<=n;i++)
            x.add(i);
        
        Collections.shuffle(x);
        for(int i=0;i<n;i++)
          array[i]=x.get(i);
          */
          
        String line = "";
        int c = 0;
       
         while((line = in.readLine())!=null)
         array[c++]=Integer.parseInt(line);
         quicksort(array, 0, 10000-1);
         
         
       /*array[0] = 3;
        array[1] = 8;
        array[2] = 5;
        array[3] = 2;
        array[4] = 1;
        array[5] = 4;
        array[6] = 7;
        array[7] = 6;
*/
        //quicksort(array, 0, n-1);

        
          System.out.println(sum); 
          for(int i=0;i<n;i++) 
          {
            System.out.print((i+1)+" - "+array[i]);
            if(array[i]!=i+1)System.out.print(" - **********");
            System.out.println(); 
          }
         
        System.out.println(Arrays.toString(array));
    }
}
