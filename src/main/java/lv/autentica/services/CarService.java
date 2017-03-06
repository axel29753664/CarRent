package lv.autentica.services;

import lv.autentica.domain.Car;
import lv.autentica.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    public void addNewCar(Car car) {
        carRepository.save(car);
    }

    public Car getCarByNumberPlate(String numberPlate) {
        return carRepository.getOne(numberPlate);
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }
}
