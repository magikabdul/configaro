package cloud.cholewa.configaro.exception.common;

public class ErrorDict {

    public static final String USER_FIRSTNAME_BLANK = "firstname must not be blank";
    public static final String USER_FIRSTNAME_LENGTH = "firstname length min size is 3 chars";
    public static final String USER_LASTNAME_BLANK = "lastname must not be blank";
    public static final String USER_LASTNAME_LENGTH = "lastname length min size is 3 chars";
    public static final String USER_EMAIL_INVALID = "email is invalid";
    public static final String USER_EMAIL_EXISTS = "email exists in database";
    public static final String USER_PASSWORD_BLANK = "password must not be blank";
    public static final String USER_PASSWORD_LENGTH = "password length min size 8 chars";
    public static final String USER_ROLE_INVALID = "invalid role name";
}
