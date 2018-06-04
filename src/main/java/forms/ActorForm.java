package forms;

import domain.Actor;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

public class ActorForm {

    public ActorForm() { super(); }

    private String  name;
    private String	surname;
    private String	phone;
    private String	email;
    private String  postalAddresses;

    @NotBlank
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    public String getName() {
        return this.name;
    }
    public void setName(final String name) {
        this.name = name;
    }

    @NotBlank
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    public String getSurname() {
        return this.surname;
    }
    public void setSurname(final String surname) {
        this.surname = surname;
    }

    @Pattern(regexp = "^\\+(\\d{2})( )(\\d{4,9})|()$")
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    public String getPhone() {
        return this.phone;
    }
    public void setPhone(final String phone) {
        this.phone = phone;
    }

    @Email
    @NotBlank
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    public String getEmail() {
        return this.email;
    }
    public void setEmail(final String email) {
        this.email = email;
    }

    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    public String getPostalAddresses() {
        return this.postalAddresses;
    }
    public void setPostalAddresses(final String postalAddresses) {
        this.postalAddresses = postalAddresses;
    }


}
