package lv.autentica.services.DTOConverters;

import lv.autentica.domain.Car;
import lv.autentica.models.CarDTO;

public class CarDTOConverter {
    public static CarDTO convertToDTO(Car car) {
        CarDTO carDTO = new CarDTO();

        carDTO.setNumberPlate(car.getNumberPlate());
        carDTO.setMark(car.getMark());

        return carDTO;
    }
    public static Car convertToEntity(CarDTO carDTO){
        Car car = new Car();

        car.setNumberPlate(carDTO.getNumberPlate());
        car.setMark(carDTO.getMark());

        return car;
    }
}
