package lv.autentica.validators;

import lv.autentica.Exceptions.RentCreationException;
import lv.autentica.domain.Rent;
import lv.autentica.repository.RentRepository;
import lv.autentica.services.CarService;
import lv.autentica.services.EmployeeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.persistence.EntityNotFoundException;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RentCreationValidatorTest {
    @Mock
    private EmployeeService employeeService;
    @Mock
    private CarService carService;
    @Mock
    private RentRepository rentRepository;
    @InjectMocks
    RentCreationValidator validator;

    private Rent rent;

    @Before
    public void init() {
        rent = new Rent();
        rent.setId(1L);
        rent.setEmployeePersonalCode(123L);
        rent.setCarNumberPlate("AB-456");
        rent.setRentStart(new Date());
        rent.setRentEnd(new Date());
    }

    @Test
    public void employeeIsNotInDBExceptionTest() throws Exception {

        doThrow(new EntityNotFoundException()).when(employeeService).getEmployeeByPersonalCode(rent.getEmployeePersonalCode());
        String errorMessage = null;
        try {
            validator.validate(rent);
        } catch (RentCreationException e) {
            errorMessage = e.getMessage();
        }
        assertEquals(errorMessage, RentCreationValidator.EMPLOYEE_NOT_FOUND_ERROR + rent.getEmployeePersonalCode());

    }

    @Test
    public void carIsNotInDBExceptionTest() throws Exception {

        doThrow(new EntityNotFoundException()).when(carService).getCarByNumberPlate(rent.getCarNumberPlate());
        String errorMessage = null;
        try {
            validator.validate(rent);
        } catch (RentCreationException e) {
            errorMessage = e.getMessage();
        }
        assertEquals(errorMessage, RentCreationValidator.CAR_NOT_FOUND_ERROR + rent.getCarNumberPlate());

    }

    @Test
    public void startTimeIsLessThanEndTimeThrowExceptionTest() throws Exception {

        rent.setRentStart(new GregorianCalendar(2017, Calendar.MARCH, 8).getTime());
        rent.setRentEnd(new GregorianCalendar(2017, Calendar.MARCH, 7).getTime());

        String errorMessage = null;
        try {
            validator.validate(rent);
        } catch (RentCreationException e) {
            errorMessage = e.getMessage();
        }
        assertEquals(errorMessage, RentCreationValidator.WRONG_RENT_TIME_ERROR);

    }

    @Test
    public void wrongCarRentTimesExceptionTest() throws Exception {

        rent.setRentStart(new GregorianCalendar(2017, Calendar.MARCH, 6).getTime());
        rent.setRentEnd(new GregorianCalendar(2017, Calendar.MARCH, 7).getTime());

        Rent rentFromDB = new Rent();
        rentFromDB.setRentStart(new GregorianCalendar(2017, Calendar.MARCH, 5).getTime());
        rentFromDB.setRentEnd(new GregorianCalendar(2017, Calendar.MARCH, 8).getTime());
        List<Rent> rents = new ArrayList<>();
        rents.add(rent);

        when(rentRepository.findByCarNumberPlate(rent.getCarNumberPlate())).thenReturn(rents);

        String errorMessage = null;
        try {
            validator.validate(rent);
        } catch (RentCreationException e) {
            errorMessage = e.getMessage();
        }
        assertEquals(errorMessage, RentCreationValidator.RENT_TIME_IS_OCCUPIED);

    }

}