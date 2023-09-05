package org.education.Lab1.task1;

public class Task1 {

    public double calculate(double x, double y){
        return  (((1 + Math.pow(Math.sin(x + y), 2.0))
                / (2 + Math.abs(x - 2 * x / (1 + x*x*y*y)))) + x);
    }
}
