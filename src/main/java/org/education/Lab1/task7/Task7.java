package org.education.Lab1.task7;

public class Task7 {

    public void sort(int[] arr){
        int ind = 0;
        while(ind < arr.length - 1){
            if(arr[ind] <= arr[ind + 1]){
                ind++;
            }
            else{
                int temp = arr[ind];
                arr[ind] = arr[ind + 1];
                arr[ind + 1] = temp;
                ind--;
            }
        }
    }
}
