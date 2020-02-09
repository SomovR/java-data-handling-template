package com.epam.izh.rd.online.repository;

import java.io.*;
import java.nio.file.Files;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class SimpleFileRepository implements FileRepository {

    /**
     * Метод рекурсивно подсчитывает количество файлов в директории
     *
     * @param path путь до директори
     * @return файлов, в том числе скрытых
     */
    @Override
    public long countFilesInDirectory(String path) {
        File dir = new File("src/main/resources/" + path);
        long count = 0;
        if (dir.isFile()) {
            count++;
        } else {
            File[] files = dir.listFiles();
            if (files != null) {
                for (File file : files) {
                    count += countFilesInDirectory(path + "/" + file.getName());
                }
            }
        }
        return count;
    }

    /**
     * Метод рекурсивно подсчитывает количество папок в директории, считая корень
     *
     * @param path путь до директории
     * @return число папок
     */
    @Override
    public long countDirsInDirectory(String path) {
        File dir = new File("src/main/resources/" + path);
        long count = 0;
        if (dir.isDirectory()) {
            count++;
            File[] files = dir.listFiles();
            if (files != null) {
                for (File file : files) {
                    count += countDirsInDirectory(path + "/" + file.getName());
                }
            }
        }
        return count;
    }

    /**
     * Метод копирует все файлы с расширением .txt
     *
     * @param from путь откуда
     * @param to   путь куда
     */
    @Override
    public void copyTXTFiles(String from, String to) {
        File sourceFile = new File("src/main/resources/" + from);
        File destFile = new File("src/main/resources/" + to);
        try {
            Files.copy(sourceFile.toPath(), destFile.toPath(), REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод создает файл на диске с расширением txt
     *
     * @param path путь до нового файла
     * @param name имя файла
     * @return был ли создан файл
     */
    @Override
    public boolean createFile(String path, String name) {
        boolean isCreated = false;
        File folder = new File("src/main/resources/" + path);
        if (!folder.exists()) {
            folder.mkdir();
        }
        File file;
        try {
            file = new File(folder.getPath() + "/" + name);
            isCreated = file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println(isCreated);  тест фейлится, но в методе значение переменной true
        return isCreated;
    }


    /**
     * Метод считывает тело файла .txt из папки src/main/resources
     *
     * @param fileName имя файла
     * @return контент
     */
    @Override
    public String readFileFromResources(String fileName) {
        StringBuilder builder = new StringBuilder();
        String str;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/" + fileName));
            while ((str = reader.readLine()) != null) {
                builder.append(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }
}


