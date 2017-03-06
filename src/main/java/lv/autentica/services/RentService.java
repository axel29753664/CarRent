package lv.autentica.services;

import lv.autentica.Exceptions.RentCreationException;
import lv.autentica.domain.Car;
import lv.autentica.domain.Employee;
import lv.autentica.domain.Rent;
import lv.autentica.repository.RentRepository;
import lv.autentica.validators.RentCreationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class RentService {
    @Autowired
    private RentRepository rentRepository;
    @Autowired
    EmployeeService employeeService;
    @Autowired
    CarService carService;
    @Autowired
    private RentCreationValidator creationValidator;

    @Transactional
    public void createNewRent(Rent newRent) throws RentCreationException {

        creationValidator.validate(newRent);
        rentRepository.save(newRent);
    }

    public void deleteRentById(Long id) {
        rentRepository.delete(id);
    }

    public List<Rent> getEmployeeCarsRent(Employee employee) {
        return rentRepository.findByEmployeePersonalCode(employee.getPersonalCode());
    }

    public List<Rent> getCarRents(Car car) {
        return rentRepository.findByCarNumberPlate(car.getNumberPlate());
    }

    public List<Rent> getAllRents() {
        return rentRepository.findAll();
    }

    public List<Rent> getRentsWithRentEndAfterDate(Date date) {
        return rentRepository.findByRentEndAfter(date);
    }

}
