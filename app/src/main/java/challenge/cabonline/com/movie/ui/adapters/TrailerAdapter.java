package challenge.cabonline.com.movie.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import challenge.cabonline.com.movie.R;
import challenge.cabonline.com.movie.model.TrailerModel;
import challenge.cabonline.com.movie.ui.utils.URLUtils;

public class TrailerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    @Inject
    Picasso picasso;
    private List<TrailerModel> data;

    public TrailerAdapter(Context context, List<TrailerModel> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.trailer_list_item, parent, false);
        viewHolder = new MyItemHolder(v);

        return viewHolder;
    }

    @Override

    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        String thumbnailURL = URLUtils.makeThumbnailURL(data.get(position).getKey());
        picasso.with(context).load(thumbnailURL).into(((MyItemHolder) holder).imageView);

    }

    @Override
    public int getItemCount() {
        return data.size() - 1;
    }

    public void addAll(List<TrailerModel> data) {
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    public TrailerModel get(int position) {
        return data.get(position);
    }

    public List<TrailerModel> getData() {
        return data;
    }

    public static class MyItemHolder extends RecyclerView.ViewHolder {
        ImageView imageView;


        MyItemHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.trailerImage);
        }

    }


}