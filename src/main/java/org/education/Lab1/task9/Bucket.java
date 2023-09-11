package org.education.Lab1.task9;

import java.util.ArrayList;
import java.util.List;

public class Bucket {

    private final List<Ball> balls;

    public Bucket() {
        this.balls = new ArrayList<>();
    }

    public void addBall(Ball ball){
        balls.add(ball);
    }

    public double getWeight(){
        double res = 0;
        for(Ball ball : balls){
            res += ball.getWeight();
        }
        return res;
    }

    public int countWithColor(String color){
        int res = 0;
        for(Ball ball : balls){
            if(color.equals(ball.getColor())){
                res++;
            }
        }
        return res;
    }
}
