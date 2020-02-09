package com.epam.izh.rd.online.service;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleRegExpService implements RegExpService {

    /**
     * Метод должен читать файл sensitive_data.txt (из директории resources) и маскировать в нем конфиденциальную информацию.
     * Номер счета должен содержать только первые 4 и последние 4 цифры (1234 **** **** 5678). Метод должен содержать регулярное
     * выражение для поиска счета.
     *
     * @return обработанный текст
     */
    @Override
    public String maskSensitiveData() {
        File file = new File("src/main/resources/sensitive_data.txt");
        BufferedReader reader = null;
        String str = null;
        Pattern pattern;
        Matcher matcher;
        try {
            reader = new BufferedReader(new FileReader(file));
            str = reader.readLine();
            pattern = Pattern.compile("([0-9]+)\\s([0-9]+)\\s([0-9]+)\\s([0-9]+)");
            matcher = pattern.matcher(str);
            if (matcher.find()) {
                str = matcher.replaceAll("$1 **** **** $4");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return str;
    }

    /**
     * Метод должен считыввать файл sensitive_data.txt (из директории resources) и заменять плейсхолдер ${payment_amount} и ${balance} на заданные числа. Метод должен
     * содержать регулярное выражение для поиска плейсхолдеров
     *
     * @return обработанный текст
     */
    @Override
    public String replacePlaceholders(double paymentAmount, double balance) {
        File file = new File("src/main/resources/sensitive_data.txt");
        BufferedReader reader = null;
        String str = null;
        Pattern pattern;
        Matcher matcher;
        try {
            reader = new BufferedReader(new FileReader(file));
            str = reader.readLine();
            pattern = Pattern.compile("\\$\\{payment_amount}");
            matcher = pattern.matcher(str);
            if (matcher.find()) {
                str = matcher.replaceAll(String.valueOf((int)paymentAmount));
            }
            pattern = Pattern.compile("\\$\\{balance}");
            matcher = pattern.matcher(str);
            if (matcher.find()) {
                str = matcher.replaceAll(String.valueOf((int)balance));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return str;
    }
}
