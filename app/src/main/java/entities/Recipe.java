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

    public Recipe() {
    }

    public Recipe(String remedy, String text, String date, String observations, String medic, Long crm) {
        this.remedy = remedy;
        this.text = text;
        this.date = date;
        this.observations = observations;
        this.medic = medic;
        this.crm = crm;
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
        return this.getDate();
    }


}
