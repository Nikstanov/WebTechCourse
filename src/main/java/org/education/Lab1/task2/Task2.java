package org.education.Lab1.task2;

public class Task2 {

    public boolean inZone(int x, int y){
        if(y > 5 || y < -3){
            return false;
        }
        if(y > 0){
            return (x <= 4 && x >= -4);
        }
        else{
            return (x <= 6 && x >= -6);
        }
    }
}
