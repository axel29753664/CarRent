package lv.autentica.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @NotNull
    @Column(name = "personal_code")
    private Long personalCode;

    @Column(name = "name")
    private String name;

    public Employee() {
    }

    public Employee(Long personalCode, String name) {
        this.personalCode = personalCode;
        this.name = name;
    }

    public Long getPersonalCode() {
        return personalCode;
    }

    public void setPersonalCode(Long personalCode) {
        this.personalCode = personalCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
