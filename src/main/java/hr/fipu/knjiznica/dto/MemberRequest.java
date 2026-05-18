package hr.fipu.knjiznica.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class MemberRequest {

    @NotBlank(message = "Ime clana je obavezno.")
    @Size(max = 50, message = "Ime moze imati najvise 50 znakova.")
    private String firstName;

    @NotBlank(message = "Prezime clana je obavezno.")
    @Size(max = 50, message = "Prezime moze imati najvise 50 znakova.")
    private String lastName;

    @NotBlank(message = "Email je obavezan.")
    @Email(message = "Email nije ispravnog oblika.")
    @Size(max = 120, message = "Email moze imati najvise 120 znakova.")
    private String email;

    @Size(max = 30, message = "Telefon moze imati najvise 30 znakova.")
    private String phone;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}