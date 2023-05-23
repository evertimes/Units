package unit7.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@AllArgsConstructor
@Data
@ToString
public class ClientCarStatisticsDto {

    private int userId;
    private String userName;
    private int carCount;

}
