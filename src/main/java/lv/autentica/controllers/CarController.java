package lv.autentica.controllers;

import lv.autentica.domain.Car;
import lv.autentica.models.CarDTO;
import lv.autentica.services.CarService;
import lv.autentica.services.DTOConverters.CarDTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CarController {
    @Autowired
    private CarService carService;

    @RequestMapping(value = "getAllCars", method = RequestMethod.GET)
    public ResponseEntity<List<CarDTO>> getAllCars() {

        List<Car> cars = carService.getAllCars();
        List<CarDTO> carDTOList = new ArrayList<>();
        for (Car car : cars) {
            CarDTO carDTO = CarDTOConverter.convertToDTO(car);
            carDTOList.add(carDTO);
        }
        return new ResponseEntity<>(carDTOList, HttpStatus.OK);
    }

}
