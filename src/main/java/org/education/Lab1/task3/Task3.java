package org.education.Lab1.task3;

public class Task3 {

    public void calculate(double a, double b, double h){
        while(a < b){
            System.out.format("%.4f",a);
            System.out.print("  ");
            System.out.format("%.4f",Math.tan(a));
            System.out.println();
            a += h;
        }
    }
}
