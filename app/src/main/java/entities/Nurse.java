package entities;

/**
 * Created by DanQuenazMagdyanSilv on 25/10/2017.
 */

public class Nurse extends User {
    private Long coren;
    private String hospitals;

    public Nurse(){}

    public Nurse(Long coren, String hospitals) {
        this.coren = coren;
        this.hospitals = hospitals;
    }

    public Nurse(String email, String name, Long cpf, String address, Long tel, String type, String hospitals, Long coren, String hospitals1) {
        super(email, name, cpf, address, tel, type, hospitals);
        this.coren = coren;
        this.hospitals = hospitals1;
    }

    public Long getCoren() {
        return coren;
    }

    public void setCoren(Long coren) {
        this.coren = coren;
    }

    @Override
    public String getHospitals() {
        return hospitals;
    }

    @Override
    public void setHospitals(String hospitals) {
        this.hospitals = hospitals;
    }
}
