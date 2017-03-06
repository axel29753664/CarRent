package lv.autentica.validators;

import lv.autentica.Exceptions.RentDTOConverterException;
import lv.autentica.models.RentDTO;
import org.springframework.stereotype.Component;

@Component
public class RentDTOValidator {
    public void validate(RentDTO rent) {
        CheckToNull.check(rent.getEmployee(), new RentDTOConverterException("Employee must be chosen."));
        CheckToNull.check(rent.getCar(), new RentDTOConverterException("Car must be chosen."));
        CheckToNull.check(rent.getRentStart(), new RentDTOConverterException("Start time must be entered"));
        CheckToNull.check(rent.getRentEnd(), new RentDTOConverterException("End time must be entered"));
    }
}
