package lv.autentica.models;

public class EmployeeDTO {
    private Long personalCode;
    private String name;

    public EmployeeDTO(Long personalCode, String name) {
        this.personalCode = personalCode;
        this.name = name;
    }

    public EmployeeDTO() {
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
