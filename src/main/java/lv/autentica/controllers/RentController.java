package lv.autentica.controllers;

import lv.autentica.Exceptions.RentCreationException;
import lv.autentica.Exceptions.RentDTOConverterException;
import lv.autentica.domain.Rent;
import lv.autentica.models.ErrorModel;
import lv.autentica.models.RentDTO;
import lv.autentica.services.DTOConverters.RentDTOConverter;
import lv.autentica.services.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class RentController {
    @Autowired
    private RentService rentService;

    @Autowired
    private RentDTOConverter rentDTOConverter;

    @RequestMapping(value = "addNewRent", method = RequestMethod.POST)
    public ResponseEntity<?> addNewRent(@RequestBody RentDTO rentDTO) {

        try {
            Rent rent = rentDTOConverter.convertToEntity(rentDTO);
            rentService.createNewRent(rent);
        } catch (RentDTOConverterException e) {
            return new ResponseEntity<>(new ErrorModel(e.getMessage()), HttpStatus.CONFLICT);
        } catch (RentCreationException e) {
            return new ResponseEntity<>(new ErrorModel(e.getMessage()), HttpStatus.CONFLICT);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "getAllRents", method = RequestMethod.GET)
    public ResponseEntity<List<RentDTO>> getAllRents() {

        List<Rent> rents = rentService.getAllRents();
        List<RentDTO> rentDTOList = convertRentListToDTOList(rents);
        return new ResponseEntity<>(rentDTOList, HttpStatus.OK);
    }

    @RequestMapping(value = "getCurrentRents", method = RequestMethod.GET)
    public ResponseEntity<List<RentDTO>> getCurrentRents() {
        Date todayDate = new Date();
        List<Rent> rents = rentService.getRentsWithRentEndAfterDate(todayDate);
        List<RentDTO> rentDTOList = convertRentListToDTOList(rents);
        return new ResponseEntity<>(rentDTOList, HttpStatus.OK);
    }


    @RequestMapping(value = "deleteRents", method = RequestMethod.POST)
    public ResponseEntity<?> deleteRents(@RequestBody List<Long> ids) {
        try {
            for (Long id : ids) {
                rentService.deleteRentById(id);
            }
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(new ErrorModel(e.getMessage()), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private List<RentDTO> convertRentListToDTOList(List<Rent> rents) {
        List<RentDTO> rentDTOList = new ArrayList<>();
        for (Rent rent : rents) {
            RentDTO rentDTO = rentDTOConverter.convertToDTO(rent);
            rentDTOList.add(rentDTO);
        }
        return rentDTOList;
    }

}
