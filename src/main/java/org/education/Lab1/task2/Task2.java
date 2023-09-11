package org.education.Lab1.task2;

public class Task2 {
    public static boolean scopeCheck(double x, double y){
        if(y > 5 || y < -3){
            return false;
        }
        return (y > 0) ? (x <= 4 && x >= -4) : (x <= 6 && x >= -6);
    }
}
