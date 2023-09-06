import org.education.Lab1.task1.Task1;
import org.education.Lab1.task5.Task5;
import org.education.Lab1.task6.Task6;
import org.education.Lab1.task7.Task7;
import org.education.Lab1.task9.Bucket;
import org.education.Lab1.task9.Ball;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class FirstLabTest {

    static Task1 task1;
    static Task5 task5;
    static Task6 task6;
    static Task7 task7;

    @BeforeAll
    static void before(){
        task1 = new Task1();
        task5 = new Task5();
        task6 = new Task6();
        task7 = new Task7();
    }

    @Test
    public void test1(){
        //TODO
    }

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

    @Test
    public void test5(){
        Assertions.assertEquals(1 , task5.deleteElements(new int[]{1,3,2,5}));
        Assertions.assertEquals(2 , task5.deleteElements(new int[]{1,3,2,5,3,7}));
        Assertions.assertEquals(3 , task5.deleteElements(new int[]{9,3,2,5,1,7,8}));
        Assertions.assertEquals(0 , task5.deleteElements(new int[]{1,3,4,5,7,8}));

    }

    @Test
    public void test6(){
        int[][] testArr = new int[][] {{1,2,3,4,5}, {2,3,4,5,1}, {3,4,5,1,2}, {4,5,1,2,3}, {5,1,2,3,4}};
        Assertions.assertTrue(Arrays.deepEquals(testArr, task6.makeMatrix(new int[]{1, 2, 3, 4, 5})));
    }

    @Test
    public void test7(){
        int[] temp = new int[]{3,9,11,5,7,13};
        task7.sort(temp);
        Assertions.assertArrayEquals(new int[]{3,5,7,9,11,13}, temp);
    }
}
