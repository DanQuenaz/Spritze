package Calc;

/**
 * Created by danqu on 12/11/2017.
 */

public class DosageCalculation {

    private static String recipe;

    public static String Calc(String drugName, double weight, Long age){
        Long days = new Long(12);
        recipe = new String();
        recipe = drugName + " durante " + Long.toString(days) + ", 5 vezes ao dia";
        return recipe;
    }
}
