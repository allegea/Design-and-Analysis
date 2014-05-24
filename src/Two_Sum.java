/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

/**
 *
 * @author Alejandro E. Garces
 */
public class Two_Sum {
    
      public static void main(String[] args) throws IOException{
        
        BufferedReader in = new BufferedReader(new FileReader("HashInt.txt"));
        
        String line = "";
        HashSet<Integer> set = new HashSet<Integer>();
        HashSet<String> answer = new HashSet<String>();
        HashSet<Integer> numbers = new HashSet<Integer>();
        
        while((line=in.readLine())!=null)
        {
            set.add(Integer.parseInt(line));
            
        }
        
        for(int x:set)
        {
           // System.out.println(x);
            if(x>4000)continue;
            for(int i=2500;i<=4000;i++)
            {
                if(numbers.contains(i))continue;
                int aux = i-x;
                if(aux==x)
                    break;
                String insert = Math.min(x,aux)+"_"+Math.max(x, aux);
                if(set.contains(aux) && !answer.contains(insert))
                {
                    answer.add(insert);
                    numbers.add(i);
                }
                
            }
        }
        
        System.out.println(answer.size());
        System.out.println(answer);
        System.out.println(numbers.size());
        System.out.println(numbers);
                
      
      }
}
