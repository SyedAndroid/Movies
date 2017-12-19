package challenge.cabonline.com.movie.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import challenge.cabonline.com.movie.R;
import challenge.cabonline.com.movie.model.ReviewModel;

public class ReviewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    private List<ReviewModel> data;

    public ReviewAdapter(Context context, List<ReviewModel> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.review_list_item, parent, false);
        viewHolder = new MyItemHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        ((MyItemHolder) holder).authorText.setText(data.get(position).getAuthor());
        ((MyItemHolder) holder).reviewText.setText(data.get(position).getContent());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void addAll(List<ReviewModel> data) {
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    public ReviewModel get(int position) {
        return data.get(position);
    }

    public List<ReviewModel> getData() {
        return data;
    }

    static class MyItemHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.authorText)
        TextView authorText;
        @BindView(R.id.reviewText)
        TextView reviewText;


        MyItemHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

    }


}