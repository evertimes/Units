package unit8.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@Data
@ToString
@NoArgsConstructor
public class ClientCarStatisticsDto {

    private int userId;
    private long carCount;

}
