/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/**
 *
 * @author Alejandro E. Garces
 */
public class SchedulesJobs1 {

    static class Job implements Comparable<Job>{
        
        int weight;
        int length;
        int greedy;
        Job(int w, int l)
        {
            weight = w;
            length = l;
            greedy = weight - length;
            //greedy = weight/(double)length;
        }
        
        @Override
        public int compareTo(Job o)
        {
            if(this.greedy != o.greedy) return o.greedy - this.greedy;
            return o.weight - this.weight;
        }
        
        /*@Override
        public int compareTo(Job o)
        {
            if(this.greedy >= o.greedy) return -1;
            return 1;
        }*/
        
        @Override
        public String toString()
        {
            return greedy+" - "+weight+" - "+length;
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("jobs.txt"));
        
        
        int n = Integer.parseInt(in.readLine());
        Job[] jobs = new Job[n];
        for(int i=0;i<n;i++)
        {
            String[] read = in.readLine().split("[ ]+");
            int w = Integer.parseInt(read[0]);
            int l = Integer.parseInt(read[1]);
            jobs[i] = new Job(w, l);
        }
        Arrays.sort(jobs);
        long sum = 0;
        long sumL = 0;
        for(int i=0;i<n; i++)
        {
            System.out.println(jobs[i]);
            sumL += jobs[i].length;
            sum+= (jobs[i].weight*sumL);
        }
        System.out.println(sum);
        System.exit(0);
        in.close();
    }
}
