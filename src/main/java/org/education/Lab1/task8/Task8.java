package org.education.Lab1.task8;

public class Task8 {
    public static int[] pushSequence(double[] a, double[] b){
        int[] res = new int[b.length];
        int indA = 0, indB = 0;
        while(indA < a.length && indB < b.length){
            if(a[indA] < b[indB]){
                indA++;
            }
            else{
                res[indB] = indA + indB;
                indB++;
            }
        }
        if(indB < b.length){
            for(;indB < b.length; indB++){
                res[indB] = indA + indB;
            }
        }
        return res;
    }
}
