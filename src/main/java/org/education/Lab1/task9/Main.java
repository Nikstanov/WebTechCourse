package org.education.Lab1.task9;

public class Main {

    public static void main(String[] args) {
        Bucket bucket = new Bucket();
        int ind = 0;
        while(ind < args.length){
            bucket.addBall(new Ball(args[ind], Double.parseDouble(args[ind+1])));
            ind+=2;
        }
        System.out.println("Count of blue balls: " + bucket.countWithColor(Ball.COLOR_BLUE));
        System.out.print("Bucket weight: ");
        System.out.printf("%.4f", bucket.getWeight());
    }
}
