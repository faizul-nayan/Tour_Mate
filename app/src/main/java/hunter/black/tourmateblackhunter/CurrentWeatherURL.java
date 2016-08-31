package hunter.black.tourmateblackhunter;

/**
 * Created by home on 8/3/2016.
 */
public class CurrentWeatherURL {

    String correspondingCityID;
    String unit;
    String url;
    String urlForeCast;

    public CurrentWeatherURL(String correspondingCityID, String unit) {
        this.correspondingCityID = correspondingCityID;
        this.unit = unit;
        url = "http://api.openweathermap.org/data/2.5/weather?id="+correspondingCityID+"&units="+unit+"&appid=a1b7bf2838a0d6073780e30c25b5e11b";
    }

    public CurrentWeatherURL(String correspondingCityID){
        this.correspondingCityID = correspondingCityID;
        urlForeCast = "http://api.openweathermap.org/data/2.5/forecast/daily?id="+correspondingCityID+"&units=metric&appid=a1b7bf2838a0d6073780e30c25b5e11b";
    }

    public String getUrl() {
        return url;
    }

    public String getUrlForeCast(){ return urlForeCast; }
}
