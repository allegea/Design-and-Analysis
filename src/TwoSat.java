/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.*;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Alejandro E. Garces
 */

public class TwoSat {

	static HashMap<Integer, Integer> pos;
	static boolean[][] sign;
	static int[][] posValues;
	static boolean[] values;
	static int N, M;
	static Random newA;
	static boolean isSatisfies()
	{
		boolean answer = true;
		for(int i=0;i<N && answer;i++)
			answer&=isTrue(i);
		return answer;
	}
	
	static boolean isTrue(int pos)
	{
		boolean first, second;
		first = values[posValues[pos][0]]&(!sign[pos][0]);
		//if(sign[pos][0])first = (!first);
		second = values[posValues[pos][1]]&(!sign[pos][1]);
		//if(sign[pos][1])second = (!second);
		
		return (first|second);
	
	}
	
	static void change(int pos)
	{
		//System.out.println("change - "+pos+" - "+isTrue(pos));
		boolean first, second;
		first = values[posValues[pos][0]]&(!sign[pos][0]);
		//if(sign[pos][0])first = (!first);
		second = values[posValues[pos][1]]&(!sign[pos][1]);
		//if(sign[pos][1])second = (!second);
		
		if(((!first)&(!sign[pos][0]))|((second)&(!sign[pos][1])))
		{
			first = !first;
                        values[posValues[pos][0]] = first;
		}else if(((first)&(!sign[pos][0]))|((!second)&(!sign[pos][1])))
		{
			second = !second;
                        values[posValues[pos][1]] = second;
		}else {
			first = !first;
			second = !second;
                        values[posValues[pos][0]] = first;
                        values[posValues[pos][1]] = second;
		}
		//System.out.println("changeA - "+pos+" - "+isTrue(pos));
	}
	
	static void newAssign()
	{
		for(int i=0;i<M;i++)
		values[i] = newA.nextBoolean();
                
	}
	
	static int pos(int x)
	{
		x = Math.abs(x);
		int n = pos.size();
		if(pos.containsKey(x))
		return pos.get(x);
		pos.put(x, n);
		return n;
	}
	
	public static void main(String[] args) throws IOException{
                String file = "2sat1";
		System.setIn(new FileInputStream(file+".txt"));
                //System.setOut(new PrintStream(new FileOutputStream(file+"O.txt")));
                Scanner in = new Scanner(System.in);
		newA = new Random();
		Random unsat = new Random();
		N = in.nextInt();
		M = 2*N;
		pos = new HashMap<Integer, Integer>();
		sign = new boolean[N][2];
		posValues = new int[N][2];
		values = new boolean[M];
		int x, y;
		for(int i=0;i<N;i++)
		{
			x = in.nextInt();
			y = in.nextInt();
			posValues[i][0] = pos(x);
			posValues[i][1] = pos(y);
			if(x<0)sign[i][0] = true;
			if(y<0)sign[i][1] = true;
		}
		
		M = pos.size();
                int n = (int)(Math.log(M)/Math.log(2));
                long m = 2*(long)M*(long)M;
                System.out.println(N+" - "+M+" - "+n+" - "+m);
		boolean answer = false;
		gotoEnd:{
		for(int h = 0;h <= n;h++)
		{
                    //System.out.println("h "+h);
			newAssign();
			for(long q=0;q<=m;q++)
			{
                            //System.out.println("q "+q);
				if(isSatisfies() == true){
					answer = true;
					break gotoEnd;
				}
				
				while(true)
				{
					int i = unsat.nextInt(N);
					if(isTrue(i))continue;
					change(i);
					break;
					
				}
				
			}
		}
		}
		System.out.println((answer?"1":"0"));
		
		in.close();
		System.exit(0);
		
	}
}