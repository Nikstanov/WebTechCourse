package org.education.Lab1.task4;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Task4 {
    static Random random = new Random();

    public static List<Integer> findPrimeNums(int[] nums){
        List<Integer> primes = new ArrayList<>();
        for(int num : nums){
            if(isPrime(BigInteger.valueOf(num))){
                primes.add(num);
            }
        }
        return primes;
    }

    // Taken from another lab ...
    private static boolean isPrime(BigInteger number){
        int ran = Math.abs(random.nextInt(number.intValue()));
        while(ran == 0){
            ran = Math.abs(random.nextInt(number.intValue()));
        }
        BigInteger a = BigInteger.valueOf(ran);
        return fastMod(a, number.subtract(BigInteger.ONE), number).equals(BigInteger.ONE);
    }

    private static BigInteger fastMod(BigInteger a, BigInteger b, BigInteger c){
        BigInteger result = BigInteger.ONE;
        while(!b.equals(BigInteger.ZERO)){
            while(b.mod(BigInteger.TWO).equals(BigInteger.ZERO)){
                b = b.divide(BigInteger.TWO);
                a = a.multiply(a);
                a = a.mod(c);
            }
            b = b.subtract(BigInteger.ONE);
            result = result.multiply(a);
            result = result.mod(c);
        }
        return result;
    }
}
