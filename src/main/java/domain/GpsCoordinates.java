package domain;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.SafeHtml;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

@Embeddable
@Access(AccessType.PROPERTY)
public class GpsCoordinates {

    private String name;
    private Double longitude;
    private Double latitude;

    public GpsCoordinates(){super();}

    @NotBlank
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull
    @Digits(integer = 3, fraction = 5)
    @Range(min = (long) -180.0, max = (long) 180.0)
    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @NotNull
    @Digits(integer = 2, fraction = 5)
    @Range(min = (long) -90.0, max = (long) 90.0)
    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
}
