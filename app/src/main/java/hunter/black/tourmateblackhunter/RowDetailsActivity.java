package hunter.black.tourmateblackhunter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


public class RowDetailsActivity extends AppCompatActivity {


//    private RecyclerView recyclerView;
//    private TravellersAdapter adapter;
      Fragment mainFragment;
      FragmentManager manager;
    FragmentTransaction transaction;

    TourDetailsFragment tourDetailsFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_row_details);

        tourDetailsFragment = new TourDetailsFragment();
        manager = getFragmentManager();
        transaction = manager.beginTransaction();
        transaction.add(R.id.motherOfAllFragment, tourDetailsFragment);
        transaction.commit();

        /* _____________________________________________
        //USE THIS CODE for Recycler view in HomeFragmentOfExpenses.class . I tested this in this activity class.
        //But using this code in a fragment class, maybe a little bit different.



        recyclerView = (RecyclerView) findViewById(R.id.travellersRecyclerList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapter = new TravellersAdapter(TravellersData.getTravellersListData(),this);
        recyclerView.setAdapter(adapter);


        ________________________________________ */


    }
}