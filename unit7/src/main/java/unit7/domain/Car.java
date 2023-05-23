package unit7.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class Car {
    private int id;
    private String number;
    private String brand;
}
