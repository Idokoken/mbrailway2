package ndgroups.mbrailway2.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "contact")
public class PersonForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "name is required")
    private String name;
    @NotBlank(message = "Phone Number is required")
    private Integer phoneNumber;
    @NotBlank(message = "email is required")
    private String email;
    @NotBlank(message = "message is required")
    private String message;

    @Override
    public String toString() {
        return "PersonForm{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", email='" + email + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
