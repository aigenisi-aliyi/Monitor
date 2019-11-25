package com.kaku.library;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants com.kaku.app.view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);

    }

    static double nub1 = 4.2;
    static double nub2 = 6.6;
    static double nub3 = 2.51;
    static double nub4 = 3.50;
    static double nub5 = 4.50;
    static double nub6 = 2.5;
    static double nub7 = 3.5;
    @Test
    public void main() {
        System.out.println("nub1 = 4.2 "+new BigDecimal(String.valueOf(nub1)).setScale(0, BigDecimal.ROUND_HALF_EVEN));
        System.out.println("nub2 = 6.6 "+new BigDecimal(String.valueOf(nub2)).setScale(0, BigDecimal.ROUND_HALF_EVEN));
        System.out.println(" nub3=2.51 "+new BigDecimal(String.valueOf(nub3)).setScale(0, BigDecimal.ROUND_HALF_EVEN));
        System.out.println(" nub4 =3.50"+new BigDecimal(String.valueOf(nub4)).setScale(0, BigDecimal.ROUND_HALF_EVEN));
        System.out.println(" nub5 =4.50"+new BigDecimal(String.valueOf(nub5)).setScale(0, BigDecimal.ROUND_HALF_EVEN));
        System.out.println(" nub6=  2.5"+new BigDecimal(String.valueOf(nub6)).setScale(0, BigDecimal.ROUND_HALF_EVEN));
        System.out.println(" nub7 = 3.5"+new BigDecimal(String.valueOf(nub7)).setScale(0, BigDecimal.ROUND_HALF_EVEN));

    }


}
