package ru.mifodiy4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.mifodiy4j.dto.Users;
import ru.mifodiy4j.model.Phone;
import ru.mifodiy4j.model.User;
import ru.mifodiy4j.service.PhoneService;
import ru.mifodiy4j.service.UserService;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.util.List;

@SpringBootApplication(scanBasePackages = {"ru.mifodiy4j"})
public class Main implements CommandLineRunner {

    private static final String SHOW = "show";
    private static final String ADD = "add";
    private static final String DELETE = "delete";
    private static final String XML = "xml";

    @Autowired
    private PhoneService phoneService;

    @Autowired
    private UserService userService;

    public static void main(String[] args) {

        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        String type = args[0];
        if (ADD.equals(type)) {
            String phoneNumber = args[2];
            Phone phone = phoneService.save(phoneNumber);
            String userName = args[1];
            User user = userService.save(userName, phone);
            System.out.println(user);
        } else if (DELETE.equals(type)) {
            String phoneNumber = args[1];
            phoneService.delete(phoneNumber);
        } else if (SHOW.equals(type)) {
            List<User> users = userService.getUsers();
            System.out.println(users);
        } else if (XML.equals(type)) {
            List<User> userList = userService.getUsers();
            Users users = new Users(userList);
            JAXBContext jaxbContext = JAXBContext.newInstance(Users.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(users, System.out);
        }
    }
}
