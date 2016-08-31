package hunter.black.tourmateblackhunter;

/**
 * Created by FaizulHauqe on 8/31/2016.
 */
public class ForecastDataObject {
    String discription;
    String image;

    public ForecastDataObject(String discription, String image) {
        this.discription = discription;
        this.image = image;
    }

    public String getDiscription() {
        return discription;
    }

    public String getImage() {
        return image;
    }
}
