package unit6.task3;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

public class EntryPoint {

    public static void main(String[] args) {
        var client1 = new Client(1, "Max", 25, List.of(
            new PhoneNumber("+79109109109", PhoneType.MOBILE)
        ));
        var client2 = new Client(2, "Konstantine", 45, List.of(
            new PhoneNumber("+79102100442", PhoneType.MOBILE),
            new PhoneNumber("+79522239456", PhoneType.HOME)
        ));
        var client3 = new Client(3, "Evgeniy", 20, List.of(
            new PhoneNumber("+79502101992", PhoneType.MOBILE),
            new PhoneNumber("+79522239456", PhoneType.HOME),
            new PhoneNumber("+79702202442", PhoneType.MOBILE)
        ));
        var client4 = new Client(4, "Max", 20, List.of(
            new PhoneNumber("+7952233126", PhoneType.HOME)
        ));
        Client[] clients = {client1, client2, client3, client4};

        //Рассчитать суммарный возраст для определенного имени
        int sumAge = Arrays.stream(clients)
            .filter(e -> e.getName().equals("Max"))
            .map(Client::getAge)
            .reduce(Integer::sum).get();
        System.out.println(sumAge);

        //Получить Set, который содержит в себе только имена клиентов в порядке их упоминания в исходном массиве.
        var clientNames = Arrays.stream(clients)
            .map(e -> e.getName())
            .collect(Collectors.toCollection(LinkedHashSet::new));
        System.out.println(clientNames);

        //Узнать, содержит ли список хотя бы одного клиента, у которого возраст больше заданного числа.
        boolean haveMoreThan40 = Arrays.stream(clients).anyMatch(e -> e.getAge() > 40);
        System.out.println(haveMoreThan40);

        //Преобразовать массив в Map, у которой ключ - уникальный идентификатор, значение - имя. Поддержать порядок,
        // в котором клиенты добавлены в массив.
        var firstMap = Arrays.stream(clients)
            .collect(Collectors.toMap(Client::getId, Client::getName, (key1, key2) -> key1, LinkedHashMap::new));
        System.out.println(firstMap);

        //Преобразовать массив в Map, у которой ключ - возраст, значение - коллекция клиентов с таким возрастом.
        var secondMap = Arrays.stream(clients)
            .collect(Collectors.toMap(Client::getAge,
                                      e -> Arrays
                                          .stream(clients)
                                          .filter(client -> client.getAge() == e.getAge())
                                          .toList(),
                                      (key1, key2) -> key1, LinkedHashMap::new));
        System.out.println(secondMap);

        //Получить строку, содержащую телефоны всех клиентов через запятую. Предусмотреть, что у клиента телефонов
        // может и не быть.
        var allPhones = Arrays.stream(clients)
            .map(Client::getPhoneNumbers)
            .flatMap(List::stream)
            .map(PhoneNumber::getNumber)
            .collect(Collectors.joining(","));
        System.out.println(allPhones);

        //Найти самого возрастного клиента, которой пользуется стационарным телефоном.
        var oldestClientWithHomePhone = Arrays.stream(clients)
            .filter(e -> e.getPhoneNumbers().stream().anyMatch(phone -> phone.getType().equals(PhoneType.HOME)))
            .max(Comparator.comparingInt(Client::getAge));
        System.out.println(oldestClientWithHomePhone.get());
    }
}
