package lv.autentica.services.DTOConverters;


import lv.autentica.domain.Car;
import lv.autentica.domain.Employee;
import lv.autentica.domain.Rent;
import lv.autentica.models.CarDTO;
import lv.autentica.models.EmployeeDTO;
import lv.autentica.models.RentDTO;
import lv.autentica.services.CarService;
import lv.autentica.services.EmployeeService;
import lv.autentica.validators.RentDTOValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RentDTOConverter {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private CarService carService;
    @Autowired
    private RentDTOValidator rentDTOValidator;

    public RentDTO convertToDTO(Rent rent) {
        RentDTO rentDTO = new RentDTO();

        rentDTO.setId(rent.getId());
        Employee employee = employeeService.getEmployeeByPersonalCode(rent.getEmployeePersonalCode());
        EmployeeDTO employeeDTO = EmployeeDTOConverter.convertToDTO(employee);
        rentDTO.setEmployee(employeeDTO);
        Car car = carService.getCarByNumberPlate(rent.getCarNumberPlate());
        CarDTO carDTO = CarDTOConverter.convertToDTO(car);
        rentDTO.setCar(carDTO);
        rentDTO.setRentStart(rent.getRentStart());
        rentDTO.setRentEnd(rent.getRentEnd());

        return rentDTO;
    }


    public Rent convertToEntity(RentDTO rentDTO) {

        rentDTOValidator.validate(rentDTO);

        Rent rent = new Rent();
        rent.setId(rentDTO.getId());
        rent.setEmployeePersonalCode(rentDTO.getEmployee().getPersonalCode());
        rent.setCarNumberPlate(rentDTO.getCar().getNumberPlate());
        rent.setRentStart(rentDTO.getRentStart());
        rent.setRentEnd(rentDTO.getRentEnd());

        return rent;
    }
}
