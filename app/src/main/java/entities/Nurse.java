package entities;

/**
 * Created by DanQuenazMagdyanSilv on 25/10/2017.
 */

public class Nurse extends User {
    private String coren;

    public Nurse(String email, String password, String name, String cpf, String address, String tel, String type, String coren) {
        super(email, password, name, cpf, address, tel, type);
        this.coren = coren;
    }

    public String getCoren() {
        return coren;
    }

    public void setCoren(String coren) {
        this.coren = coren;
    }
}
