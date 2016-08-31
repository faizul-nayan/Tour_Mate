package hunter.black.tourmateblackhunter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

/**
 * Created by FaizulHauqe on 8/27/2016.
 */
public class TravellersAdapter extends RecyclerView.Adapter<TravellersAdapter.TravellersHolder> {
    private List<TravellersListItem> travellersListData;
    private LayoutInflater inflater;

    public TravellersAdapter (List<TravellersListItem> travellersListItems, Context c){
        this.inflater = LayoutInflater.from(c);
        this.travellersListData = travellersListItems;
    }

    @Override
    public TravellersHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.traveller_list,parent,false);
        return new TravellersHolder(view);

    }

    @Override
    public void onBindViewHolder(final TravellersHolder holder, final int position) {
        final TravellersListItem travellersListItem = travellersListData.get(position);
        holder.travellersName.setText(travellersListItem.getTravellersName());
        holder.travellersImgResId.setImageResource(travellersListItem.getTravellersImgResId());


    }

    @Override
    public int getItemCount() {
        return travellersListData.size();
    }

    class TravellersHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView travellersName;
        private ImageView travellersImgResId;
        private View container;


        public TravellersHolder(View itemView) {
            super(itemView);
            travellersName = (TextView) itemView.findViewById(R.id.travellersNameTV);
            travellersImgResId = (ImageView) itemView.findViewById(R.id.travellersIconView);

            container = itemView.findViewById(R.id.rootOfTravellersList);
            travellersImgResId.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(view.getId()==R.id.travellersIconView){

                // Intent intent = new Intent(TravellersAdapter.this, RowDetailsActivity.class);


            }else {

            }
        }
    }
}
