package lv.autentica.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "rent")
public class Rent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "employee_personal_code")
    private Long employeePersonalCode;

    @Column(name = "car_number_plate")
    private String carNumberPlate;

    private Date rentStart;
    private Date rentEnd;

    public Rent(Long employeePersonalCode, String carNumberPlate, Date rentStart, Date rentEnd) {
        this.employeePersonalCode = employeePersonalCode;
        this.carNumberPlate = carNumberPlate;
        this.rentStart = rentStart;
        this.rentEnd = rentEnd;
    }

    public Rent() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmployeePersonalCode() {
        return employeePersonalCode;
    }

    public void setEmployeePersonalCode(Long employeePersonalCode) {
        this.employeePersonalCode = employeePersonalCode;
    }

    public String getCarNumberPlate() {
        return carNumberPlate;
    }

    public void setCarNumberPlate(String carNumberPlate) {
        this.carNumberPlate = carNumberPlate;
    }

    public Date getRentStart() {
        return rentStart;
    }

    public void setRentStart(Date rentStart) {
        this.rentStart = rentStart;
    }

    public Date getRentEnd() {
        return rentEnd;
    }

    public void setRentEnd(Date rentEnd) {
        this.rentEnd = rentEnd;
    }
}
