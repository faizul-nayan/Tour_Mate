package hunter.black.tourmateblackhunter;

/**
 * Created by FaizulHauqe on 8/27/2016.
 */
public class NewEventObject {
    private String placeName;
    private String budget;
    private String from;
    private String to;

    public NewEventObject(String placeName, String budget, String from, String to) {
        this.placeName = placeName;
        this.budget = budget;
        this.from = from;
        this.to = to;
    }

    public String getPlaceName() {
        return placeName;
    }

    public String getBudget() {
        return budget;
    }

    public String getTo() {
        return to;
    }

    public String getFrom() {
        return from;
    }
}
