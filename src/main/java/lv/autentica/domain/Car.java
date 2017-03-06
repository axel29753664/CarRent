package lv.autentica.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table (name = "cars")
public class Car {
    @Id
    @NotNull
    @Column(name = "number_plate")
    private String numberPlate;
    @Column(name = "mark")
    private String mark;

    public Car() {
    }

    public Car(String numberPlate, String mark) {
        this.numberPlate = numberPlate;
        this.mark = mark;
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
