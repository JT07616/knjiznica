package hr.fipu.knjiznica.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class MemberRequest {

    @NotBlank(message = "Ime člana je obavezno.")
    @Size(max = 50, message = "Ime može imati najviše 50 znakova.")
    private String firstName;

    @NotBlank(message = "Prezime člana je obavezno.")
    @Size(max = 50, message = "Prezime može imati najviše 50 znakova.")
    private String lastName;

    @NotBlank(message = "Email je obavezan.")
    @Email(message = "Email nije ispravnog oblika.")
    @Size(max = 120, message = "Email može imati najviše 120 znakova.")
    private String email;

    @Size(max = 30, message = "Telefon može imati najviše 30 znakova.")
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