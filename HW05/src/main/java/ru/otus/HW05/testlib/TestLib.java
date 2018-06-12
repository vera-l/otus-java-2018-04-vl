package ru.otus.HW05.testlib;

import java.util.List;
import java.util.ArrayList;
import java.net.URL;
import java.io.File;

public class TestLib {
    private final static String FILE_PATH_SEPARATOR = "/";
    private final static String JAVA_PACKAGE_SEPARATOR = ".";
    private final static String JAVA_CLASS_SUFFIX = ".class";

    public static void runTests(String classOrPackageName) {
        try {
            TestClass.fromClass(classOrPackageName).run();
        } catch (ClassNotFoundException e) {
            try {
                List<String> classes = getClassesFromPackage(classOrPackageName);
                for (String cls : classes) {
                    TestClass.fromClass(cls).run();
                }
            } catch (ClassNotFoundException ex) {
                System.out.println("Class or package " + classOrPackageName + " is not found");
            }
        }
    }

    private static List<String> getClassesFromPackage(String packageName) {
        List<String> classes = new ArrayList<String>();
        String path = packageName.replace(JAVA_PACKAGE_SEPARATOR, FILE_PATH_SEPARATOR);
        URL url = Thread.currentThread().getContextClassLoader().getResource(path);
        if (url == null) {
            throw new IllegalArgumentException("");
        }
        File dir = new File(url.getFile());
        for (File file : dir.listFiles()) {
            String fileName = file.getName();
            if (file.isFile() && fileName.endsWith(JAVA_CLASS_SUFFIX)) {
                classes.add(
                    packageName + JAVA_PACKAGE_SEPARATOR + fileName.replace(JAVA_CLASS_SUFFIX, "")
                );
            }
        }
        return classes;
    }

}
