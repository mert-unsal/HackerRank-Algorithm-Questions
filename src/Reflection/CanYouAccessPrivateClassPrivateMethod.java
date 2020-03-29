package Reflection;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.security.Permission;

public class CanYouAccessPrivateClassPrivateMethod {
    public static void main(String[] args) throws Exception {
        DoNotTerminate.forbidExit();

        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int num = Integer.parseInt(br.readLine().trim());
            Object o = new Inner();// Must be used to hold the reference of the instance of the class Solution.Inner.Private
            o = new Inner();// Must be used to hold the reference of the instance of the class Solution.Inner.Private
            Class<?> invokerClass = o.getClass().getDeclaredClasses()[0];
            Method method = invokerClass.getDeclaredMethod("powerof2",int.class);
            Constructor<?> ctor = invokerClass.getDeclaredConstructor(o.getClass());
            ctor.setAccessible(true);
            method.setAccessible(true);
            System.out.println(String.valueOf(num) + " is " + method.invoke(ctor.newInstance(o), num));
            o = ctor.newInstance(o);
        }//end of try

        catch (DoNotTerminate.ExitTrappedException e) {
            System.out.println("Unsuccessful Termination!!");
        }
    }//end of main

    static class Inner{
        private class Private{
            private String powerof2(int num){
                return ((num&num-1)==0)?"power of 2":"not a power of 2";
            }
        }
    }//end of Inner

}//end of Solution

class DoNotTerminate { //This class prevents exit(0)

    public static class ExitTrappedException extends SecurityException {

        private static final long serialVersionUID = 1L;
    }

    public static void forbidExit() {
        final SecurityManager securityManager = new SecurityManager() {
            @Override
            public void checkPermission(Permission permission) {
                if (permission.getName().contains("exitVM")) {
                    throw new DoNotTerminate.ExitTrappedException();
                }
            }
        };
        System.setSecurityManager(securityManager);
    }
}
