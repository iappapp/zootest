package com.github.iappapp.modal;

public class AutoCloseTest implements AutoCloseable {
    public AutoCloseTest() {
    }

    public AutoCloseTest autoStart() {
        System.out.println("auto start");
        return new AutoCloseTest();
    }

    @Override
    public void close() throws Exception {
        System.out.println("auto close start");
    }

    public static void main(String[] args) {
        try (AutoCloseTest autoCloseTest = new AutoCloseTest()){
            autoCloseTest.autoStart();
        } catch (Exception ex) {

        }
    }
}
