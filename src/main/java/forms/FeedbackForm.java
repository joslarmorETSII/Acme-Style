package forms;

import domain.Servise;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.SafeHtml;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class FeedbackForm {

    public FeedbackForm(){
        super();
    }

    private Servise servise;
    private String	    text;
    private Integer		points;

    @NotNull
    @Valid
    public Servise getServise() {
        return servise;
    }

    public void setServise(Servise servise) {
        this.servise = servise;
    }

    @NotBlank
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    @NotNull
    @Range(min = 0, max = 5)
    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer point) {
        this.points = points;
    }
}
