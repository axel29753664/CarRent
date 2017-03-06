package lv.autentica.controllers;

import lv.autentica.Exceptions.RentCreationException;
import lv.autentica.Exceptions.RentDTOConverterException;
import lv.autentica.domain.Rent;
import lv.autentica.models.ErrorModel;
import lv.autentica.models.RentDTO;
import lv.autentica.services.DTOConverters.RentDTOConverter;
import lv.autentica.services.RentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RentControllerTest {
    @Mock
    private RentService rentService;
    @Mock
    RentDTOConverter rentDTOConverter;
    @InjectMocks
    RentController rentController;

    private String errorMessage = "Error message";

    @Test
    public void getAllRents() throws Exception {

        List<Rent> rents = new ArrayList<>();
        List<RentDTO> rentDTOList = new ArrayList<>();
        Rent rent = new Rent();
        RentDTO rentDTO = new RentDTO();

        rents.add(rent);
        rentDTOList.add(rentDTO);

        when(rentService.getAllRents()).thenReturn(rents);
        when(rentDTOConverter.convertToDTO(rent)).thenReturn(rentDTO);

        ResponseEntity response = rentController.getAllRents();

        assertEquals(response.getBody(), rentDTOList);
    }


    @Test
    public void addNewRentThrowCreationExceptionTest() throws Exception {

        when(rentDTOConverter.convertToEntity(null)).thenReturn(null);
        doThrow(new RentCreationException(errorMessage)).when(rentService).createNewRent(null);

        ResponseEntity response = rentController.addNewRent(null);
        ErrorModel errorFromResponse = (ErrorModel) response.getBody();

        assertEquals(errorFromResponse.getMessage(), errorMessage);
    }

    @Test
    public void addNewRentThrowDTOConverterExceptionTest() throws Exception {

        doThrow(new RentDTOConverterException(errorMessage)).when(rentDTOConverter).convertToEntity(null);

        ResponseEntity response = rentController.addNewRent(null);
        ErrorModel errorFromResponse = (ErrorModel) response.getBody();

        assertEquals(errorFromResponse.getMessage(), errorMessage);
    }


    @Test
    public void deleteRentsThrowEntityNotFoundExceptionTest() throws Exception {

        doThrow(new EntityNotFoundException(errorMessage)).when(rentService).deleteRentById(1L);
        List<Long> ids = new ArrayList<>();
        ids.add(1L);
        ResponseEntity response = rentController.deleteRents(ids);
        ErrorModel errorFromResponse = (ErrorModel) response.getBody();

        assertEquals(errorFromResponse.getMessage(), errorMessage);

    }

}