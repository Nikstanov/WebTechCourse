package org.education.Lab1.task8;

public class Task8 {
    public int[] pushSequence(int[] a, int[] b){
        int[] res = new int[a.length + b.length];
        int indA = 0, indB = 0;
        while(indA < a.length && indB < b.length){
            if(a[indA] < b[indB]){
                res[indA + indB] = a[indA];
                indA++;
            }
            else{
                res[indA + indB] = b[indB];
                indB++;
            }
        }
        if(indA < a.length){
            for(;indA < a.length; indA++){
                res[indA + indB] = a[indA];
            }
        }
        else{
            for(;indB < b.length; indB++){
                res[indA + indB] = b[indB];
            }
        }
        return res;
    }
}
