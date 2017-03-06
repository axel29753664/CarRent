package lv.autentica.models;

public class CarDTO {
    private String numberPlate;
    private String mark;

    public CarDTO(String numberPlate, String mark) {
        this.numberPlate = numberPlate;
        this.mark = mark;
    }

    public CarDTO() {
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}
