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
public class MergeSort {
    
    static long invertions = 0;
    
    public static ArrayList<Integer> merge(ArrayList<Integer> a, ArrayList<Integer> b){
        
        ArrayList<Integer> merge = new ArrayList<>();
        int j=0,k=0;
        for(int i=0;i<a.size()+b.size();i++)
        {
            if(j==a.size()){
               merge.addAll(b.subList(k, b.size()));
               break;
            }
            if(k==b.size()){
               merge.addAll(a.subList(j, a.size()));
               break;
            }
            
            if(a.get(j)<b.get(k)){
                merge.add(a.get(j));
               // if(j<k)invertions++;
                j++;
            }else {
                merge.add(b.get(k));
                invertions+=a.size()-j;
                k++;
            } 
        }
        return merge;
    }
    
     public static ArrayList<Integer> MergeSort(ArrayList<Integer> x){
         
         if(x.size()==1)
             return x;
         int size = x.size();
         return merge(MergeSort(new ArrayList(x.subList(0, size/2))),MergeSort(new ArrayList(x.subList(size/2, size))));
     }
     
     public static void invertions(ArrayList<Integer> x){
         
         int inv = 0;
         
         for(int i=0;i<x.size();i++){
             for(int j=0;j<x.size();j++){
             if(x.get(i)>x.get(j) && i<j )inv++;
            }
         }
         System.out.println(inv);
     }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException{
        
        BufferedReader in   = new BufferedReader(new FileReader("IntegerArray.txt"));
        
        ArrayList<Integer> x = new ArrayList<>();
        String line;
        int count =0;
        while((line = in.readLine())!=null){
            
            
            x.add(Integer.parseInt(line));
           // if(count++>500)break;
            
        }
        
        //invertions(x);
        
        
        
        System.out.println(MergeSort(x));
        System.out.println(invertions);
    }
}
