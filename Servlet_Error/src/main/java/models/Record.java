package models;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@Builder
public class Record {
    private String idUser;
    private String idDoctor;
    private String date;
    private String time;
    private String email;
}
