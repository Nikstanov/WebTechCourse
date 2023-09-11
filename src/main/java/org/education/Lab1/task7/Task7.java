package org.education.Lab1.task7;

public class Task7 {

    public static void sort(double[] arr){
        int ind = 0;
        while(ind < arr.length - 1){
            if(arr[ind] <= arr[ind + 1]){
                ind++;
            }
            else{
                double temp = arr[ind];
                arr[ind] = arr[ind + 1];
                arr[ind + 1] = temp;
                ind--;
            }
        }
    }
}
