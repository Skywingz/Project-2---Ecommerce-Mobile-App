package immersive.android.assembly.general.project2ecommerceapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class ReviewRecyclerAdapter extends RecyclerView.Adapter<ReviewRecyclerViewHolder> {

    private ArrayList<Review> reviews;
    private Context context;


    public ReviewRecyclerAdapter(ArrayList<Review> list) {
        this.reviews = list;
    }

    @Override
    public ReviewRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.review_list_item, parent, false);
        ReviewRecyclerViewHolder holder = new ReviewRecyclerViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ReviewRecyclerViewHolder holder, int position) {
        Review review = reviews.get(position);
        holder.userName.setText(review.getUserName());
        holder.comment.setText(review.getUserComment());
        double rating = Double.parseDouble(review.getUserRating());
        holder.rating.setRating((float)rating);

    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }
}
