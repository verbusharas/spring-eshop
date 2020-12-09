package lt.verbus.eshop.user.model;

import lombok.Getter;
import lombok.Setter;
import lt.verbus.eshop.user.service.validator.LithuanianPhoneNumber;
import lt.verbus.eshop.user.service.validator.LithuanianZipCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @NotBlank
    @Size(min = 3, max = 200)
    private String username;

    @Column(nullable=false)
    @Size(min=3)
    @NotBlank
    private String password;

    @LithuanianPhoneNumber
    private String phone;

    @LithuanianZipCode
    private String zip;

    private String avatar;

}
