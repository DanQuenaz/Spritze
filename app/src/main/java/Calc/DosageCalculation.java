package Calc;

/**
 * Created by danqu on 12/11/2017.
 */

public class DosageCalculation {

    private static String recipe;

    public static String Calc(String drugName, double weight, Long age){
        Long days = new Long(7);
        recipe = new String();
        if(drugName.equals("Diazepam")){
            if(age <= 12){
                double auxM = weight * 0.2;
                recipe = "Administrar " + Double.toString(auxM) + "mg, a cada 8h durante 7 dias";
                return recipe;
            }else if(age > 12){
                double auxM = weight * 0.5;
                recipe = "Administrar " + Double.toString(auxM) + "mg, a cada 8h durante 7 dias";
                return recipe;
            }
        }

        recipe = drugName + " durante " + Long.toString(days) + ", 5 vezes ao dia";
        return recipe;
    }
}
