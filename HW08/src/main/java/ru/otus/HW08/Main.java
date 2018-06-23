package ru.otus.HW08;

import java.util.List;
import java.util.ArrayList;
import ru.otus.HW08.person.Person;
import ru.otus.HW08.person.Address;
import ru.otus.HW08.person.Phone;
import org.json.JSONObject;


public class Main {

    public static void main(String... args) {
        Person person = getSomePerson();
        String jsonString = JsonLib.toJson(person).toString();
        prettyPrintJson(jsonString);
    }

    private static Person getSomePerson() {
        Person person = new Person();

        person.setFirstName("John");
        person.setLastName("Smith");
        person.setIsAlive(true);
        person.setAge(25);
        person.setCompany(null);
        person.setHeight(182.5);

        Address address = new Address();
        address.setStreetAddress("21 2nd Street");
        address.setCity("New York");
        address.setState("NY");
        address.setPostalCode("10021-3100");
        person.setAddress(address);

        Phone phone1 = new Phone();
        phone1.setType("home");
        phone1.setNumber("212 555-1234");

        Phone phone2 = new Phone();
        phone2.setType("fax");
        phone2.setNumber("646 555-4567");

        List<Phone> phones = new ArrayList<Phone>(2);
        phones.add(phone1);
        phones.add(phone2);
        person.setPhones(phones);

        person.setIds(new int[]{123, 456, 789});

        return person;
    }

    public static void prettyPrintJson(String jsonString) {
        JSONObject jsonObject = new JSONObject(jsonString);
        System.out.println(jsonObject.toString(4));
    }
}