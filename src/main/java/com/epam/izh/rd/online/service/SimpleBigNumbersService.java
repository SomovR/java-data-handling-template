package com.epam.izh.rd.online.service;

import java.math.BigDecimal;
import java.math.BigInteger;

public class SimpleBigNumbersService implements BigNumbersService {

    /**
     * Метод делит первое число на второе с заданной точностью
     * Например 1/3 с точностью 2 = 0.33
     * @param range точность
     * @return результат
     */
    @Override
    public BigDecimal getPrecisionNumber(int a, int b, int range) {
        BigDecimal number1 = new BigDecimal(a);
        BigDecimal number2 = new BigDecimal(b);
        return number1.divide(number2, range, BigDecimal.ROUND_HALF_EVEN);
    }

    /**
     * Метод находит простое число по номеру
     *
     * @param range номер числа, считая с числа 2
     * @return простое число
     */
    @Override
    public BigInteger getPrimaryNumber(int range) {
        int counter = 0;
        int value = 2;
        boolean isPrimary;
        if (range != 1) {
            while (counter != range) {
                isPrimary = true;
                value++;
                for (int i = 2; i < value; i++) {
                    if (value % i == 0) {
                        isPrimary = false;
                        break;
                    }
                }
                if (isPrimary) {
                    counter++;
                }
            }
        }
        return BigInteger.valueOf(value);
    }
}
