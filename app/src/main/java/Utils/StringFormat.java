package Utils;

/**
 * Created by danqu on 23/11/2017.
 */

public class StringFormat {

    public static String formatEmail(String email){
        String auxEmail;

        auxEmail = email.replace(".com", "");
        auxEmail = auxEmail.replace(".br", "");
        auxEmail = auxEmail.replace("@", "_");

        return auxEmail;
    }
}
