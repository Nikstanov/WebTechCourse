package task9;

import org.education.Lab1.task9.Ball;
import org.education.Lab1.task9.Bucket;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Task9Test {

    @Test
    public void test9(){
        Bucket bucket = new Bucket();
        bucket.addBall(new Ball(Ball.COLOR_BLUE, 3));
        bucket.addBall(new Ball(Ball.COLOR_YELLOW, 2));
        bucket.addBall(new Ball(Ball.COLOR_BLUE, 1));
        bucket.addBall(new Ball(Ball.COLOR_GRAY, 4));

        Assertions.assertEquals(2, bucket.countWithColor(Ball.COLOR_BLUE));
        Assertions.assertEquals(10, bucket.getWeight());
    }

}
