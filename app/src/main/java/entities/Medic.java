package entities;

/**
 * Created by DanQuenazMagdyanSilv on 09/10/2017.
 */

public class Medic extends User {

    private Long crm;
    private String specialization;

    public Medic(){}

    public Medic(Long crm, String specialization) {
        this.crm = crm;
        this.specialization = specialization;
    }

    public Medic(String email, String name, Long cpf, String address, Long tel, String type, String hospitals, Long crm, String specialization) {
        super(email, name, cpf, address, tel, type, hospitals);
        this.crm = crm;
        this.specialization = specialization;
    }

    public Long getCrm() {
        return crm;
    }

    public void setCrm(Long crm) {
        this.crm = crm;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}
