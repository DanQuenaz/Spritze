package entities;

/**
 * Created by DanQuenazMagdyanSilv on 09/10/2017.
 */

public class Medic extends User {

    private String crm;
    private String specialization;

    public Medic(){}

    public Medic(String email, String password, String name, String cpf, String address, String tel, String type, String crm, String specialization) {
        super(email, password, name, cpf, address, tel, type);
        this.crm = crm;
        this.specialization = specialization;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}
