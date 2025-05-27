interface AnonymousClassInterface {
    public void methodFromInterface();
}

class AnonymousClass {
    public static void main ( String[] args ) {
        String oClassName = "\"AnonymousClass\"";

        AnonymousClassInterface oAnonymous = new AnonymousClassInterface() {
            // like local classes can access members and local variables
            public void methodFromInterface( ) {
                methodNotFromInterface();
            }

            public void methodNotFromInterface ( ) {
                System.out.println("From the anonymous class inside the class named : " + oClassName);
            }
        };

        oAnonymous.methodFromInterface();

        // it was not in the interface so not possible
        //oAnonymous.methodNotFromInterface();
    }

}