package lv.autentica.services.DTOConverters;

import lv.autentica.domain.Employee;
import lv.autentica.models.EmployeeDTO;

public class EmployeeDTOConverter {
    public static EmployeeDTO convertToDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();

        employeeDTO.setPersonalCode(employee.getPersonalCode());
        employeeDTO.setName(employee.getName());

        return employeeDTO;
    }
    public static Employee convertToEntity(EmployeeDTO employeeDTO){
        Employee employee = new Employee();

        employee.setPersonalCode(employeeDTO.getPersonalCode());
        employee.setName(employeeDTO.getName());

        return employee;
    }
}
