package entities;

/**
 * Created by DanQuenazMagdyanSilv on 09/10/2017.
 */

public class Medic extends User {

    private Long crm;
    private String specialization;
    private String hospitals;

    public Medic(){}

    public Medic(Long crm, String specialization, String hospitals) {
        this.crm = crm;
        this.specialization = specialization;
        this.hospitals = hospitals;
    }

    public Medic(String email, String name, Long cpf, String address, Long tel, String type, Long crm, String specialization, String hospitals) {
        super(email, name, cpf, address, tel, type);
        this.crm = crm;
        this.specialization = specialization;
        this.hospitals = hospitals;
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

    public String getHospitals() {
        return hospitals;
    }

    public void setHospitals(String hospitals) {
        this.hospitals = hospitals;
    }

    public String toString(){
        return super.getName();
    }
}
