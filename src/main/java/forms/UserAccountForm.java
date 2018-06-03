package forms;

import org.hibernate.validator.constraints.SafeHtml;

import javax.persistence.Column;
import javax.validation.constraints.Size;

public class UserAccountForm {

    public UserAccountForm() { super(); }

    private String	username;
    private String  oldPassword;
    private String	newPassword;
    private String	repeatPassword;

    @Column(unique = true)
    @Size(min = 5, max = 32)
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    public String getUsername() {
        return this.username;
    }
    public void setUsername(final String username) {
        this.username = username;
    }

    @Size(min = 5, max = 32)
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    @Size(min = 5, max = 32)
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    @Size(min = 5, max = 32)
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    public String getRepeatPassword() {
        return this.repeatPassword;
    }
    public void setRepeatPassword(final String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }
}
