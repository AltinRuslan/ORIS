package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegDto {
    private String name;
    private String email;
    private String password;

    public boolean isAdmin() {return this.email.equals("email");}
}
