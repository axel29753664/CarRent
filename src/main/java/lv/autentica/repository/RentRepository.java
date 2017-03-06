package lv.autentica.repository;

import lv.autentica.domain.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.OrderBy;
import java.util.Date;
import java.util.List;

public interface RentRepository extends JpaRepository<Rent, Long> {
    List<Rent> findByCarNumberPlate(String numberPlate);

    List<Rent> findByEmployeePersonalCode(Long personalCode);

    List<Rent> findByRentEndAfter(Date date);

}
