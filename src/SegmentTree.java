/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 *
 * @author Alejandro E. Garces
 */


class SegmentTree{

	static class SegTree{
	
		int[] RMQ;
		int[] values;
		int n;
		SegTree()
		{
		
		}
		
		void create(int[] val)
		{
                        int m = val.length;
                        n = (int)(2 * Math.pow(2.0, Math.floor((Math.log((double)m) / Math.log(2.0)) + 1)));
			//n = m * 10;
			RMQ = new int[n];
			values = new int[m];
			for(int i=0;i<m;i++)values[i] = val[i];
			buildST(1, 0, val.length-1);
		}
		
		void buildST(int root, int L, int R)
		{
			if(L == R){
                            RMQ[root] = L;
                        }
			else{
				int nL = root*2;
				int nR = root*2+1;
				buildST(nL, L, (L+R)/2);
				buildST(nR,(L+R)/2+1, R);
				int pL = RMQ[nL];
				int pR = RMQ[nR];
				int vL = values[pL];
				int vR = values[pR];
				RMQ[root] = ((vL <= vR)?pL:pR);
			}
		}
                
                public int update(int index, int value)
                {
                    return update(1, 0, values.length-1, index, value);
                }
                
                private int update(int root, int L, int R, int index, int value) {
                int i = index;
                int j = index;
                if (i > R || j < L)
                    return RMQ[root];
                if (L == i && R == j) {
                    values[i] = value;
                    return RMQ[root] = L;
                }
                int pL = update(root * 2, L, (L + R) / 2, index, value);
                int pR = update(root * 2 + 1, (L + R) / 2 + 1, R, index, value);
                return RMQ[root] = ((values[pL] <= values[pR]) ? pL : pR);
            }
                
                public int QR(int i, int j)
                {
                    return QR(1, 0, values.length-1, i, j);
                }

                private int QR(int root, int L, int R, int i, int j) {
                    //System.out.println(root + " * "+L+" * "+R);
                     if(i > R || j < L) return -1;
                     if(L >= i && R <= j)return RMQ[root];
                     
                     int pL = QR(root*2, L, (L+R)/2, i, j);
                     int pR = QR(root*2+1,(L+R)/2+1, R, i, j);
                     if(pL == -1)return pR;
                     if(pR == -1)return pL;
                     return values[pL] <= values [pR]?pL:pR;
                     
            
                 }
	}
	public static void main(String[] args) throws IOException
	{
            int n = 7;
            int[] vector = new int[n];
            SegTree sT = new SegTree();
            Random r = new Random();
            for(int i=0;i<n;i++)vector[i] = r.nextInt()%300;
            
            System.out.println(Arrays.toString(vector));
            sT.create(vector);
            System.out.println("QR + "+sT.QR(0, 6));
            sT.update(5, -200);
            vector[5] = -200;
             System.out.println(Arrays.toString(vector));
            System.out.println("QR + "+sT.QR(4, 6));
            
            sT.update(2, -100);
            vector[2] = -100;
             System.out.println(Arrays.toString(vector));
            System.out.println("QR + "+sT.QR(0, 3));
            
            System.out.println("QR + "+sT.QR(0, 6));
            
	}

}