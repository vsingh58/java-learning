package jmethod;


import java.util.regex.Pattern;

/**
 * Created by zhishan on 10/11/14.
 */
public class Utils {

    private static final int TRUE = 1;
    private static final int FALSE = 0;
    private static final String VALUE = "value"; // key field
    private static final String LEGAL = "legal";

    private final static int[] wi = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1};
    // verify digit
    private final static char[] vi = {'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'};

    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    public static int emailChecker(final String hex) {
        if (hex != null && pattern.matcher(hex).matches()) {
            return TRUE;
        }
        return FALSE;
    }

    public static String getBirthDayByPID(String pidNO){
        if ( pidNO != null){
            return pidNO.substring(6,14);
        }
        return null;
    }

    /*
   *
   * identification no
   * primaryKey: lakala user a unique ID
   * */
    public static Boolean validatePID(String primaryKey) {
        if (primaryKey == null || primaryKey.isEmpty()) {
            return false;
        }
        if (primaryKey.matches("\\d{17}[xX\\d]")) {
            String verify = primaryKey.substring(17, 18);
            String code = String.valueOf(getIDCCD(primaryKey));
            if (verify.equalsIgnoreCase(code)) {
                return true;
            }
            return false;
        }
        return false;
    }
    // when no argument given, the method will be called.

    public static Boolean validatePID() {
        System.out.println("CheckPId.validatePID requires an argument");
        return false;
    }

    public static int validateMobile(String mobileNum) {
        if (mobileNum != null) {
            String digits_11 = String.valueOf(mobileNum);
            if (digits_11.matches("1\\d{10}")) {
                return TRUE;
            }
        }
        return FALSE;
    }

/*
//    SemanicException occurs:
//    The parameters of GenericUDFReflect(class, method[,arg1[,arg2]...]) must be primitive (int, double,string, etc).
    private static int validateMapEqual(Map<String, String> left, Map<String, String> right) {
        int lLegal = Integer.parseInt(left.get(LEGAL));
        String lValue = left.get(VALUE);

        int rLegal = Integer.parseInt(right.get(LEGAL));
        String rValue = left.get(VALUE);
        System.out.println(lLegal + ":" + lValue +"," + rLegal + ":" + rValue);
        if (rLegal == TRUE && lLegal == TRUE && rValue.equals(lValue)){
            return TRUE;
        }
        return FALSE;
    }
*/


    private static char getIDCCD(String PID) {
        int[] ai = new int[18];
        int remaining = 0;

        String dgs = PID.substring(0, 17);
        int sum = 0;
        for (int i = 0; i < 17; i++) {
            String k = dgs.substring(i, i + 1);
            ai[i] = Integer.parseInt(k);
        }

        for (int i = 0; i < 17; i++) {
            sum = sum + wi[i] * ai[i];
        }
        remaining = sum % 11;
        return vi[remaining];
    }

    public String test() {
        return "Hello World";
    }

    public static void main(String[] args) {
        String id = "370830198605122273";
        System.out.println(validatePID(id));
        System.out.println(validateMobile("13051495442"));
        System.out.println(emailChecker("12@133.com"));
        new Utils().test();

    }

}
