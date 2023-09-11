package org.education.Lab1.task5;

public class Task5 {

    public static int deleteElements(int[] arr){
        if(arr.length == 0){
            return 0;
        }
        return arr.length - newRow(arr, 0);
    }

    private static int newRow(int[] arr, int ind){
        int temp = 1, res = Integer.MIN_VALUE;
        for(int i = ind + 1; i < arr.length; i++){
            if(arr[i] > arr[ind]){
                temp++;
                ind = i;
            }
            else{
                res = Math.max(newRow(arr, i), res);
            }
        }
        res = Math.max(temp, res);
        return res;
    }

}
