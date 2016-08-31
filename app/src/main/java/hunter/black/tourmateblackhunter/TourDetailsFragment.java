package hunter.black.tourmateblackhunter;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by FaizulHauqe on 8/28/2016.
 */
public class TourDetailsFragment extends Fragment {

    TextView totalBudgetInFragmentExpensesTV;
    TextView RemainingTkInFragmentExpensesTV;
    TextView totalSentMoneyInFragmentExpensesTV;
    TextView newTravellers;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_home_fragment_of_expenses,container,false);
        totalBudgetInFragmentExpensesTV = (TextView) view.findViewById(R.id.totalBudgetInFragmentExpensesTV);
        RemainingTkInFragmentExpensesTV = (TextView) view.findViewById(R.id.RemainingTkInFragmentExpensesTV);
        totalSentMoneyInFragmentExpensesTV = (TextView) view.findViewById(R.id.totalSentMoneyInFragmentExpensesTV);
        newTravellers = (TextView) view.findViewById(R.id.newTravellers);
        newTravellers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), NewTravellersActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
