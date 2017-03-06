package lv.autentica.validators;

public class CheckToNull {
    public static void check(Object o, RuntimeException e) {
        if (o == null) {
            throw e;
        }
    }
}
