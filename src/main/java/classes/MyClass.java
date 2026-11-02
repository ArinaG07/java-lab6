package classes;

import annotations.Repeat;

public class MyClass {

    @Repeat(times = 1)
    public void publicMethod1() {
        System.out.println("Вызван publicMethod1");
    }

    public void publicMethod2() {
        System.out.println("Вызван publicMethod2");
    }


    @Repeat(times = 2)
    protected void protectedMethod1(String text) {
        System.out.println("Вызван protectedMethod1 с аргументом: " + text);
    }

    @Repeat(times = 3)
    protected void protectedMethod2() {
        System.out.println("Вызван protectedMethod2");
    }


    @Repeat(times = 2)
    private void privateMethod1(int number) {
        System.out.println("Вызван privateMethod1 с числом: " + number);
    }

    @Repeat(times = 3)
    private void privateMethod2(String str, boolean flag) {
        System.out.println("Вызван privateMethod2 с аргументами: " + str + ", " + flag);
    }
}
