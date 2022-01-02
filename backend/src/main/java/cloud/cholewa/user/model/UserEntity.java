package cloud.cholewa.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class UserEntity {
    @Id
    private Long id;

    private String firstname;
    private String lastname;
    private String email;
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleEntity roleEntity;
}
