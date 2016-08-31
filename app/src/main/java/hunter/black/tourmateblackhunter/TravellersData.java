package hunter.black.tourmateblackhunter;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by FaizulHauqe on 8/27/2016.
 */
public class TravellersData {
    private static final String[] travellersName = {"Add", "Shovon", "Babu", "Pagli"};
    private static final int[] travellersImageId = {android.R.drawable.ic_menu_add, android.R.drawable.ic_menu_gallery,
            android.R.drawable.ic_menu_gallery, android.R.drawable.ic_menu_gallery};

    public static List<TravellersListItem> getTravellersListData(){
        List<TravellersListItem> data = new ArrayList<>();
        for(int x=0;x<4;x++){
            for (int i=0; i<travellersName.length && i<travellersImageId.length; i++){
                TravellersListItem item = new TravellersListItem();
                item.setTravellersImgResId(travellersImageId[i]);
                item.setTravellersName(travellersName[i]);
                data.add(item);
            }
        }

        return data;
    }
}

