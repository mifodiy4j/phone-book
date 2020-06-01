package ru.mifodiy4j.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mifodiy4j.model.Phone;
import ru.mifodiy4j.repository.PhoneRepository;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PhoneService {

    @NonNull
    private PhoneRepository repository;

    public Phone save(String number) {
        Phone phone = new Phone();
        phone.setNumber(number);
        return repository.save(phone);
    }

    @Transactional
    public void delete(String number) {
        repository.deleteByNumber(number);
    }
}
