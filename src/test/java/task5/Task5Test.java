package task5;

import org.education.Lab1.task5.Task5;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Task5Test {

    @Test
    public void test5(){
        Assertions.assertEquals(0 , Task5.deleteElements(new int[]{}));
        Assertions.assertEquals(1 , Task5.deleteElements(new int[]{1,3,2,5}));
        Assertions.assertEquals(2 , Task5.deleteElements(new int[]{1,3,2,5,3,7}));
        Assertions.assertEquals(3 , Task5.deleteElements(new int[]{9,3,2,5,1,7,8}));
        Assertions.assertEquals(0 , Task5.deleteElements(new int[]{1,3,4,5,7,8}));
    }

}
