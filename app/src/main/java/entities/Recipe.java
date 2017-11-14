package entities;

/**
 * Created by danqu on 12/11/2017.
 */

public class Recipe {
    private String remedy;
    private String text;
    private String date;
    private String observations;
    private String medic;
    private Long crm;
    private String nurse;
    private Long coren;
    private String status;

    public Recipe() {
    }

    public Recipe(String remedy, String text, String date, String observations, String medic, Long crm) {
        this.remedy = remedy;
        this.text = text;
        this.date = date;
        this.observations = observations;
        this.medic = medic;
        this.crm = crm;
        this.status = "Não aplicado ainda";
    }

    public Recipe(String remedy, String text, String date, String observations, String medic, Long crm, String nurse, Long coren, String status) {
        this.remedy = remedy;
        this.text = text;
        this.date = date;
        this.observations = observations;
        this.medic = medic;
        this.crm = crm;
        this.nurse = nurse;
        this.coren = coren;
        this.status = "Não aplicado ainda";

    }

    public String getNurse() {
        return nurse;
    }

    public void setNurse(String nurse) {
        this.nurse = nurse;
    }

    public Long getCoren() {
        return coren;
    }

    public void setCoren(Long coren) {
        this.coren = coren;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemedy() {
        return remedy;
    }

    public void setRemedy(String remedy) {
        this.remedy = remedy;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public String getMedic() {
        return medic;
    }

    public void setMedic(String medic) {
        this.medic = medic;
    }

    public Long getCrm() {
        return crm;
    }

    public void setCrm(Long crm) {
        this.crm = crm;
    }

    public String toString(){
        return (this.getRemedy() + " - "+ this.getDate());
    }


}
