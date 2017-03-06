package lv.autentica.controllers;

import lv.autentica.domain.Employee;
import lv.autentica.models.EmployeeDTO;
import lv.autentica.services.DTOConverters.EmployeeDTOConverter;
import lv.autentica.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "getAllEmployees", method = RequestMethod.GET)
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        List<EmployeeDTO> employeeDTOList = new ArrayList<>();
        for (Employee employee : employees) {
            EmployeeDTO employeeDTO = EmployeeDTOConverter.convertToDTO(employee);
            employeeDTOList.add(employeeDTO);
        }
        return new ResponseEntity<>(employeeDTOList, HttpStatus.OK);
    }
}
