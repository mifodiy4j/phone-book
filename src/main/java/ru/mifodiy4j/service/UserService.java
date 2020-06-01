package ru.mifodiy4j.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mifodiy4j.model.Phone;
import ru.mifodiy4j.model.User;
import ru.mifodiy4j.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {

    @NonNull
    private UserRepository repository;

    public List<User> getUsers() {
        return repository.findAll();
    }

    public User save(String name, Phone phone) {
        Optional<User> userOptional = findByName(name);
        User user = userOptional.orElseGet(() -> create(name));
        user.getPhones().add(phone);
        return repository.save(user);
    }

    public Optional<User> findByName(String name) {
        return repository.findByName(name);
    }

    public User create(String name) {
        User user = new User();
        user.setName(name);
        return repository.save(user);
    }
}
