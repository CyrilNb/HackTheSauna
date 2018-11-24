package hackjunction2018.c2c.hackthesauna;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import hackjunction2018.c2c.hackthesauna.Model.Session;

/**
 * Created by Corentin on 11/24/2018.
 */

public class SessionAdapter extends RecyclerView.Adapter<SessionAdapter.ViewHolder>{

    private List<Session> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mDateView, mAverageTempView, mDurationView, mCaloriesView;
        public ViewHolder(View v) {
            super(v);
            mDateView = v.findViewById(R.id.dateSession);
            mAverageTempView = v.findViewById(R.id.tempSession);
            mDurationView = v.findViewById(R.id.durationSession);
            mCaloriesView = v.findViewById(R.id.caloriesSession);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public SessionAdapter(List<Session> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public SessionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.session_card, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(SessionAdapter.ViewHolder holder, int position) {
        holder.mDateView.setText(mDataset.get(position).getDate());
        holder.mAverageTempView.setText(mDataset.get(position).getAverageTemperature());
        holder.mCaloriesView.setText(mDataset.get(position).getBurnedCalories());
        holder.mDurationView.setText(mDataset.get(position).getDuration());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}
