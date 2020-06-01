package ru.mifodiy4j.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mifodiy4j.model.Phone;

public interface PhoneRepository extends JpaRepository<Phone, Long> {

    Long deleteByNumber(String number);
}
