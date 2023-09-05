package org.education.Lab1.task6;

public class Task6 {

    public int[][] makeMatrix(int[] arr){
        int[][] res = new int[arr.length][arr.length];
        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr.length; j++){
                res[i][j] = arr[(i + j) % arr.length];
            }
        }
        return res;
    }
}
