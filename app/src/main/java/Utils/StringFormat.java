package Utils;

import entities.Recipe;

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

    public static String formatRecipeKey(Recipe auxRec){
        String auxKey = auxRec.getDate().replace(".", "");
        auxKey = auxRec.getRemedy() + auxKey;
        auxKey = auxKey.replace(" ", "");
        auxKey = auxKey.replace("-", "");
        auxKey = auxKey.replace("/", "");
        auxKey = auxKey.replace(":", "");
        auxKey = auxKey.replace("+", "");

        auxKey = Long.toString(auxRec.getCrm()) + auxKey;

        return auxKey;
    }
}
