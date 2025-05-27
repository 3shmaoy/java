class ClassWithMethodForFriends {
    public static void onlyForFriendClass ( classFriend.ClassSignature oSignature ) throws Exception {
        if ( oSignature == null ) throw new Exception() ;
        System.out.println("OK (From Friend Class)");
    }
}

class classFriend {
    // once is enough for optimiaility
    private static final ClassSignature oSignature = new ClassSignature();

    public static final class ClassSignature {
        // signature class to emulate friend classes
        // no inheritance is also important

        private ClassSignature () {
            // only the containing class can call the private constructor
        }
    };

    public static void main ( String[] args ) {
        try {
            System.out.print("Calling From Friend Class : ");
            ClassWithMethodForFriends.onlyForFriendClass(classFriend.oSignature);
        } catch ( Exception oException ) {
            System.out.println(oException.getMessage());
        }

        new ClassNotFriend();
    }
}

class ClassNotFriend {
    ClassNotFriend () {
        String sMsg = "Calling From a NON-Friend Class : ";

        // Constructor is inaccessible
        //classFriend.ClassSignature o1 = new classFriend.ClassSignature();


        // can't call the friend method w/o the signature
        try {
            Object o2 = new Object();

            // java.lang.Object cannot be cast to classFriend$ClassSignature
            //classFriend.ClassSignature o3 = (classFriend.ClassSignature) o2;

            // Cannot cast java.lang.Object to [package.]classFriend$ClassSignature
            //classFriend.ClassSignature o3 = (classFriend.ClassSignature) Class.forName("classFriend$ClassSignature").cast(o2);

            classFriend.ClassSignature o3 = null;

            ClassWithMethodForFriends.onlyForFriendClass(o3);

            System.out.print(sMsg + "NOT POSSIBLE");
        } catch ( ClassCastException oException ) {
            System.out.println(sMsg + oException.getMessage());
        } catch ( Exception oException ) {
            System.out.println(sMsg + oException.getMessage());
        }
    }
}