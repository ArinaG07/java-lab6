package app;

import annotations.Repeat;
import classes.MyClass;

import java.lang.reflect.Method;

public class Invoker {

    public static void invokeAnnotatedMethods() {
        MyClass obj = new MyClass();
        Method[] methods = MyClass.class.getDeclaredMethods();

        for (Method method : methods) {
            if (method.isAnnotationPresent(Repeat.class)) {
                Repeat repeat = method.getAnnotation(Repeat.class);
                int times = repeat.times();

                method.setAccessible(true);

                Object[] args = prepareArguments(method.getParameterTypes());

                for (int i = 0; i < times; i++) {
                    try {
                        method.invoke(obj, args);
                    } catch (Exception e) {
                        System.err.println("Ошибка при вызове метода " + method.getName() + ": " + e.getMessage());
                    }
                }
            }
        }
    }

    private static Object[] prepareArguments(Class<?>[] parameterTypes) {
        Object[] args = new Object[parameterTypes.length];
        for (int i = 0; i < parameterTypes.length; i++) {
            if (parameterTypes[i] == int.class) {
                args[i] = 42;
            } else if (parameterTypes[i] == String.class) {
                args[i] = "Тест";
            } else if (parameterTypes[i] == boolean.class) {
                args[i] = true;
            } else {
                args[i] = null;
            }
        }
        return args;
    }
}
