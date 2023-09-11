package org.education.Lab1.task3;

import java.util.ArrayList;
import java.util.List;

public class Task3 {

    public static void main(String[] args) {
        calculate(35.0, 37.7, 0.7).forEach(val -> {
            System.out.print(val[0]);
            System.out.print(", ");
            System.out.print(val[1]);
            System.out.println();
        });
    }

    public static List<double[]> calculate(double a, double b, double h){
        List<double[]> table = new ArrayList<>();
        while(a < b){
            double[] res = new double[2];
            res[0] = a;
            res[1] = Math.tan(a);
            a+=h;
            table.add(res);
        }
        return table;
    }
}
