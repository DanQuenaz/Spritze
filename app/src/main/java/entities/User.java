package entities;

/**
 * Created by DanQuenazMagdyanSilv on 09/10/2017.
 */

public class User {
    private String email;
    private String name;
    private Long cpf;
    private String address;
    private Long tel;
    private String type;

    public User(){}

    public User(String email, String name, Long cpf, String address, Long tel, String type) {
        this.email = email;
        this.name = name;
        this.cpf = cpf;
        this.address = address;
        this.tel = tel;
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getTel() {
        return tel;
    }

    public void setTel(Long tel) {
        this.tel = tel;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
