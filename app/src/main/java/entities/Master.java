package entities;

/**
 * Created by DanQuenazMagdyanSilv on 09/10/2017.
 */

public class Master extends User {
    private String function;

    public Master() {}

    public Master(String email, String name, Long cpf, String address, Long tel, String type, String function) {
        super(email, name, cpf, address, tel, type);
        this.function = function;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }
}
