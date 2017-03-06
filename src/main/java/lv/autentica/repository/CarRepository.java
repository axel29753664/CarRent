package lv.autentica.repository;

import lv.autentica.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car,String> {

}
