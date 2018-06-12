package ru.otus.HW05.testlib;

import java.lang.reflect.Method;
import java.util.List;
import java.util.ArrayList;
import ru.otus.HW05.annotations.After;
import ru.otus.HW05.annotations.Before;
import ru.otus.HW05.annotations.Test;
import java.lang.reflect.InvocationTargetException;

class TestClass {
    public Class cls;
    public Method beforeMethod;
    public Method afterMethod;
    public List<Method> testMethods;

    TestClass() {
        cls = null;
        beforeMethod = null;
        afterMethod = null;
        testMethods = new ArrayList<Method>();
    }

    TestClass(Class cls, Method beforeMethod, Method afterMethod, List<Method> testMethods) {
        this.cls = cls;
        this.beforeMethod = beforeMethod;
        this.afterMethod = afterMethod;
        this.testMethods = testMethods;
    }

    public void run() {
        try {
            for (Method method : testMethods) {
                System.out.println("[Test] " + method.getAnnotation(Test.class).value());

                Object one = cls.getDeclaredConstructor().newInstance();

                if (beforeMethod != null) {
                    beforeMethod.invoke(one);
                }
                method.invoke(one);
                if (afterMethod != null) {
                    afterMethod.invoke(one);
                }
                System.out.println("");
            }

        } catch (InstantiationException e) {
            System.out.println("InstantiationException");
        } catch (NoSuchMethodException e) {
            System.out.println("NoSuchMethodException");
        } catch (IllegalAccessException e) {
            System.out.println("IllegalAccessException");
        } catch (InvocationTargetException e) {
            System.out.println("InvocationTargetException");
            e.getCause().printStackTrace();
        }
    }

    public static TestClass fromClass(String className) throws ClassNotFoundException {
        TestClass testClass = new TestClass();

        testClass.cls = Class.forName(className);

        Method[] declaredMethods = testClass.cls.getDeclaredMethods();
        for (int i = 0; i < declaredMethods.length; i++) {
            Method m = declaredMethods[i];
            if (m.getAnnotation(Before.class) != null) {
                testClass.beforeMethod = m;
            } else if (m.getAnnotation(After.class) != null) {
                testClass.afterMethod = m;
            } else if (m.getAnnotation(Test.class) != null) {
                testClass.testMethods.add(m);
            }
        }

        return testClass;
    }
}
