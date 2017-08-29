package immersive.android.assembly.general.project2ecommerceapp;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import org.w3c.dom.Text;


public class ReviewRecyclerViewHolder extends RecyclerView.ViewHolder {

    TextView userName, comment;
    RatingBar rating;


    public ReviewRecyclerViewHolder(View view) {
        super(view);

        userName = (TextView) view.findViewById(R.id.detailReviewListItemName);
        comment = (TextView) view.findViewById(R.id.detailReviewListItemComments);
        rating = (RatingBar) view.findViewById(R.id.detailReviewListItemRatingBar);

    }
}
