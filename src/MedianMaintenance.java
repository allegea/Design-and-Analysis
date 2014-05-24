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
import java.util.PriorityQueue;

/**
 *
 * @author Alejandro E. Garces
 */
public class MedianMaintenance {
    
        public static void main(String[] args) throws IOException
        {

            BufferedReader in = new BufferedReader(new FileReader("Median.txt"));
            BufferedReader in2 = new BufferedReader(new FileReader("Median.txt"));
            PriorityQueue<Integer> min = new PriorityQueue<Integer>();
            PriorityQueue<Integer> max = new PriorityQueue<Integer>();
            ArrayList<Integer> test = new ArrayList<Integer>();

            String line = "";
            int input = 0;
            long media = 0;
            int size = 0;
            int k=0;
            int minV;
            int maxV;
            int aux = 0;
           /* while((line=in.readLine())!=null)
            {
                input = Integer.parseInt(line); 
                size++;                
                test.add(input);
                Collections.sort(test);
                k=size/2;
                if((size&1)==0)
                    k--;
                media+=test.get(k);
                media%=10000;
                System.out.println(test.get(k)+" - "+media);
            }*/
            
            System.out.println("*****************************");
            media = 0;
            for(int i=0;i<2;i++)
            {
                input = Integer.parseInt(in2.readLine());
                if(i==0)
                {
                    min.offer(-1*input);
                    media+=input;
                }else{
                    minV = -1*min.poll();
                    media+=Math.min(minV, input);
                    min.offer(-1*Math.min(minV,input));
                    max.offer(Math.max(minV,input));
                    
                }
                media%=10000;
                System.out.println(media);
            }
            
            int[] sort = new int[3];
            while((line = in2.readLine())!=null)
            {
                sort[0] = (-1*min.poll());
                sort[1] = max.poll();
                sort[2] = Integer.parseInt(line);
                
                Arrays.sort(sort);
                if(max.size()==min.size())
                {
                    min.offer((-1)*sort[0]);
                    max.offer(sort[1]);
                    max.offer(sort[2]);
                    aux = max.poll();
                    max.offer(aux);
                }else
                {
                    min.offer((-1)*sort[0]);
                    min.offer((-1)*sort[1]);
                    max.offer(sort[2]);
                    aux = min.poll();
                    min.offer(aux);
                    aux*=-1;
                    
                }
                media+=aux;
                media%=10000;
                System.out.println(aux+" - "+media);
                
                    
            }

        }
    
}
