package lv.autentica.validators;

import lv.autentica.Exceptions.RentCreationException;
import lv.autentica.domain.Rent;
import lv.autentica.repository.RentRepository;
import lv.autentica.services.CarService;
import lv.autentica.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;

@Component
public class RentCreationValidator {
    public static final String EMPLOYEE_NOT_FOUND_ERROR = "Unable to find employee with personal code: ";
    public static final String CAR_NOT_FOUND_ERROR = "Unable to find car with number plate: ";
    public static final String WRONG_RENT_TIME_ERROR = "Wrong rent time";
    public static final String RENT_TIME_IS_OCCUPIED = "Rent time is occupied.";

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private CarService carService;
    @Autowired
    private RentRepository rentRepository;

    public void validate(Rent newRent) {
        CheckToNull.check(newRent.getEmployeePersonalCode(), new RentCreationException("Employee personal code can't be null."));
        CheckToNull.check(newRent.getCarNumberPlate(), new RentCreationException("Car number plate can't be null."));
        CheckToNull.check(newRent.getRentStart(), new RentCreationException("Start time can't be null."));
        CheckToNull.check(newRent.getRentEnd(), new RentCreationException("End time can't be null."));

        checkEmployeeIsInDB(newRent.getEmployeePersonalCode());
        checkCarIsInDB(newRent.getCarNumberPlate());
        checkStartTimeIsLessThanEndTime(newRent.getRentStart(), newRent.getRentEnd());
        checkCarRentTimes(newRent);

    }

    private void checkCarRentTimes(Rent newRent) {
        List<Rent> currentCarRents = rentRepository.findByCarNumberPlate(newRent.getCarNumberPlate());
        for (Rent currentCarRent : currentCarRents) {

            if (checkRentTimeIsOccupied(newRent, currentCarRent)) {
                throw new RentCreationException(RENT_TIME_IS_OCCUPIED);
            }
        }
    }

    private void checkStartTimeIsLessThanEndTime(Date startTime, Date endTime) {
        if (startTime.after(endTime)) {
            throw new RentCreationException(WRONG_RENT_TIME_ERROR);
        }

    }

    private void checkCarIsInDB(String carNumberPlate) {
        try {
            carService.getCarByNumberPlate(carNumberPlate);
        } catch (EntityNotFoundException e) {
            throw new RentCreationException(CAR_NOT_FOUND_ERROR + carNumberPlate);
        }
    }

    private void checkEmployeeIsInDB(Long employeePersonalCode) {
        try {
            employeeService.getEmployeeByPersonalCode(employeePersonalCode);
        } catch (EntityNotFoundException e) {
            throw new RentCreationException(EMPLOYEE_NOT_FOUND_ERROR + employeePersonalCode);
        }
    }

    private boolean checkRentTimeIsOccupied(Rent newRent, Rent currentCarRent) {
        if (newRent.getRentStart().before(currentCarRent.getRentEnd())) {
            if ((newRent.getRentStart().after(currentCarRent.getRentStart())) || (newRent.getRentEnd().after(currentCarRent.getRentStart()))) {
                return true;
            }
        }
        return false;
    }
}
