package entities;

/**
 * Created by DanQuenazMagdyanSilv on 09/10/2017.
 */

public class Master extends User {
    private String function;

    public Master() {}

    public Master(String email, String password, String name, String cpf, String address, String tel, String function) {
        super(email, password, name, cpf, address, tel);
        this.function = function;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }
}
