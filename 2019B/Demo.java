class Demo {

    public void exceptionThrower() throws MyOwnException {
        throw new MyOwnException();
    }
    public static void main(String... args) {
        Demo demo = new Demo();
        try {
            demo.exceptionThrower();
        }
        catch (MyOwnException e) {
            System.out.println("Here is an exception!");
        }
        finally {
            System.out.println("Exceptions are handled.");
        }
    }
}

class MyOwnException extends Exception {

    private static final long serialVersionUID = 1L;

    MyOwnException() {
        System.out.println("In 'MyOwnException'");
    }

}
