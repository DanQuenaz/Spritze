package entities;

/**
 * Created by DanQuenazMagdyanSilv on 25/10/2017.
 */

public class Nurse extends User {
    private Long coren;

    public Nurse(){}

    public Nurse(Long coren, String hospitals) {
        this.coren = coren;
    }

    public Nurse(String email, String name, Long cpf, String address, Long tel, String type, String hospitals, Long coren) {
        super(email, name, cpf, address, tel, type, hospitals);
        this.coren = coren;
    }

    public Long getCoren() {
        return coren;
    }

    public void setCoren(Long coren) {
        this.coren = coren;
    }


}
