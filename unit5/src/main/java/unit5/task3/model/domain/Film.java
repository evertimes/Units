package unit5.task3.model.domain;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Film implements Serializable {

    private static final long serialVersionUID = -9211510976058567207L;

    private String filmName;
    private Actor mainActor;
}
