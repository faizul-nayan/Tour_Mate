package hunter.black.tourmateblackhunter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by FaizulHauqe on 8/31/2016.
 */
public class EventListAdapter extends ArrayAdapter {

    private Context context;
    private  ArrayList<AllEventsData> dataArrayList;

    public EventListAdapter(Context context, ArrayList<AllEventsData> dataArrayList) {
        super(context, R.layout.custom_row_of_main_activiity_list,dataArrayList);
        this.context = context;
        this.dataArrayList = dataArrayList;
    }

    static class ViewHolder{
        private TextView placeNameMActivityTV;
        private TextView currentTempTV;
        private TextView currentWeatherConditionTV;
        private TextView journetStartDayMAtextView;
        private TextView journetEndDayMAtextView;
        private TextView totalBudgetMAtextView;
        private TextView spentMoneyMAtextView;
        private TextView remainingBalanceMAtextView;
        private TextView satDayWeatherInfoTV;
        private TextView sunDayWeatherInfoTV;
        private TextView monDayWeatherInfoTV;
        private TextView tueDayWeatherInfoTV;
        private TextView wedDayWeatherInfoTV;
        private TextView thuDayWeatherInfoTV;
        private TextView friDayWeatherInfoTV;


        private ImageView satImageView;
        private ImageView sunImageView;
        private ImageView monImageView;
        private ImageView tueImageView;
        private ImageView wedImageView;
        private ImageView thuImageView;
        private ImageView friImageView;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder viewHolder;


        if (convertView == null){
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.custom_row_of_main_activiity_list,null);

            viewHolder = new ViewHolder();
            viewHolder.placeNameMActivityTV=(TextView)convertView.findViewById(R.id.placeNameMActivityTV);
            viewHolder.currentTempTV=(TextView)convertView.findViewById(R.id.currentTempTV);
            viewHolder.currentWeatherConditionTV=(TextView)convertView.findViewById(R.id.currentWeatherConditionTV);
            viewHolder.journetStartDayMAtextView=(TextView)convertView.findViewById(R.id.journetStartDayMAtextView);
            viewHolder.journetEndDayMAtextView=(TextView)convertView.findViewById(R.id.journetEndDayMAtextView);
            viewHolder.totalBudgetMAtextView=(TextView)convertView.findViewById(R.id.totalBudgetMAtextView);
            viewHolder.spentMoneyMAtextView=(TextView)convertView.findViewById(R.id.spentMoneyMAtextView);
            viewHolder.remainingBalanceMAtextView=(TextView)convertView.findViewById(R.id.remainingBalanceMAtextView);
            viewHolder.satDayWeatherInfoTV=(TextView)convertView.findViewById(R.id.satDayWeatherInfoTV);
            viewHolder.sunDayWeatherInfoTV=(TextView)convertView.findViewById(R.id.sunDayWeatherInfoTV);
            viewHolder.monDayWeatherInfoTV=(TextView)convertView.findViewById(R.id.monDayWeatherInfoTV);
            viewHolder.tueDayWeatherInfoTV=(TextView)convertView.findViewById(R.id.tueDayWeatherInfoTV);
            viewHolder.wedDayWeatherInfoTV=(TextView)convertView.findViewById(R.id.wedDayWeatherInfoTV);
            viewHolder.thuDayWeatherInfoTV=(TextView)convertView.findViewById(R.id.thuDayWeatherInfoTV);
            viewHolder.friDayWeatherInfoTV=(TextView)convertView.findViewById(R.id.friDayWeatherInfoTV);

            viewHolder.satImageView = (ImageView)convertView.findViewById(R.id.satDayWeatherConditionImage);
            viewHolder.sunImageView = (ImageView)convertView.findViewById(R.id.sunDayWeatherConditionImage);
            viewHolder.monImageView = (ImageView)convertView.findViewById(R.id.monDayWeatherConditionImage);
            viewHolder.tueImageView = (ImageView)convertView.findViewById(R.id.tueDayWeatherConditionImage);
            viewHolder.wedImageView = (ImageView)convertView.findViewById(R.id.wedDayWeatherConditionImage);
            viewHolder.thuImageView = (ImageView)convertView.findViewById(R.id.thuDayWeatherConditionImage);
            viewHolder.friImageView = (ImageView)convertView.findViewById(R.id.friDayWeatherConditionImage);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder=(ViewHolder)convertView.getTag();
        }

        viewHolder.placeNameMActivityTV.setText(dataArrayList.get(position).getPlace());
        viewHolder.currentTempTV.setText(dataArrayList.get(position).getTemp());
        viewHolder.currentWeatherConditionTV.setText(dataArrayList.get(position).getTempDetails());
        viewHolder.journetStartDayMAtextView.setText(dataArrayList.get(position).getStart());
        viewHolder.journetEndDayMAtextView.setText(dataArrayList.get(position).getEnd());
        viewHolder.totalBudgetMAtextView.setText(dataArrayList.get(position).getBudget());
        viewHolder.spentMoneyMAtextView.setText(dataArrayList.get(position).getSpent());
        viewHolder.remainingBalanceMAtextView.setText(dataArrayList.get(position).getRemaining());
        viewHolder.satDayWeatherInfoTV.setText(dataArrayList.get(position).getSatInfo());
        viewHolder.sunDayWeatherInfoTV.setText(dataArrayList.get(position).getSunInfo());
        viewHolder.monDayWeatherInfoTV.setText(dataArrayList.get(position).getMonInfo());
        viewHolder.tueDayWeatherInfoTV.setText(dataArrayList.get(position).getTueInfo());
        viewHolder.wedDayWeatherInfoTV.setText(dataArrayList.get(position).getWedInfo());
        viewHolder.thuDayWeatherInfoTV.setText(dataArrayList.get(position).getThuInfo());
        viewHolder.friDayWeatherInfoTV.setText(dataArrayList.get(position).getFriInfo());


        return convertView;
    }
}
