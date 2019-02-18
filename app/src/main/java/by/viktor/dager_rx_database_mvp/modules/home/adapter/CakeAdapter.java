package by.viktor.dager_rx_database_mvp.modules.home.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import by.viktor.dager_rx_database_mvp.R;
import by.viktor.dager_rx_database_mvp.mvp.model.Cake;

public class CakeAdapter extends RecyclerView.Adapter<CakeAdapter.Holder> {

    private LayoutInflater mLayoutInflater;
    private List<Cake> mCakeList = new ArrayList<>();

    public CakeAdapter(LayoutInflater inflater){
        mLayoutInflater = inflater;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.list_item_layout, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.bidn(mCakeList.get(position));
    }

    @Override
    public int getItemCount() {
        return mCakeList.size();
    }

    public void addCakes(List<Cake> cakes){
        mCakeList.addAll(cakes);
        notifyDataSetChanged();
    }

    public void clearCakes() {
        mCakeList.clear();
        notifyDataSetChanged();
    }

    public class Holder extends RecyclerView.ViewHolder{


        @BindView(R.id.cake_icon) protected ImageView mCakeIcon;
        @BindView(R.id.textview_title) protected TextView mCakeTitle;
        @BindView(R.id.textview_preview_description) protected TextView mCakeIconPreviewDescription;

        private Context mContext;

        public Holder(@NonNull View itemView) {
            super(itemView);
            mContext = itemView.getContext();
            ButterKnife.bind(this, itemView);
        }

        public void bidn(Cake cake) {
            mCakeTitle.setText(cake.getTitle());
            mCakeIconPreviewDescription.setText(cake.getPreviewDescription());

            Glide.with(mContext)
                    .load(cake.getImageUrl())
                    .into(mCakeIcon);

        }
    }
}
