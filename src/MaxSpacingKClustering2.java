/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.*;

public class MaxSpacingKClustering2 {

    static int[] p, rank;
    static int N, sets;

    static void init() {
        p = new int[N];
        rank = new int[N];
        sets = N;
        for (int i = 0; i < N; ++i) {
            p[i] = i;
            rank[i] = 0;
        }
    }

    static int find(int i) {
        return p[i] == i ? i : (p[i] = find(p[i]));
    }

    static void merge(int i, int j) {
        int pi = find(i);
        int pj = find(j);
        if (rank[pi] > rank[pj]) {
            p[pj] = pi;
        } else {
            p[pi] = pj;
        }
        if (rank[pi] == rank[pj]) {
            ++rank[pj];
        }
        --sets;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("clustering2.txt"));
        String[] parts = in.readLine().split("[ ]+");
        N = Integer.parseInt(parts[0]);
        int bits = Integer.parseInt(parts[1]);
        int[] nodes = new int[N];
        for (int i = 0; i < N; ++i) {
            String line = in.readLine().replaceAll(" ", "");
            nodes[i] = Integer.parseInt(line, 2);
        }
        init();
        for (int i = 0; i < N; ++i) {
            for (int j = i + 1; j < N; ++j) {
                if (find(i) != find(j) && Integer.bitCount(nodes[i] ^ nodes[j]) <= 2) {
                    merge(i, j);
                }
            }
        }
        System.out.println(sets);
        in.close();
        System.exit(0);
    }
}