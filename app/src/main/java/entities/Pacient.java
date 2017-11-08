package entities;

/**
 * Created by danqu on 08/11/2017.
 */

public class Pacient {
    private String name;
    private Long age;
    private Long cpf;
    private String adress;
    private Long tell;

    public Pacient(){}

    public Pacient(String name, Long age, Long cpf, String adress, Long tell) {
        this.name = name;
        this.age = age;
        this.cpf = cpf;
        this.adress = adress;
        this.tell = tell;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public Long getTell() {
        return tell;
    }

    public void setTell(Long tell) {
        this.tell = tell;
    }
}
