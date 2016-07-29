package immersive.android.assembly.general.project2ecommerceapp;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * Created by Skywingz on 7/27/16.
 */
public class MainShopRecyclerViewHolder extends RecyclerView.ViewHolder {

    public ImageView icon, star1, star2, star3, star4, star5;
    public TextView price, name, origin, buy, added;
    public CardView card;


    public MainShopRecyclerViewHolder(View view) {
        super(view);

        icon = (ImageView) view.findViewById(R.id.shopListCharacterIcon);
        star1 = (ImageView) view.findViewById(R.id.shopListRatingStar1);
        star2 = (ImageView) view.findViewById(R.id.shopListRatingStar2);
        star3 = (ImageView) view.findViewById(R.id.shopListRatingStar3);
        star4 = (ImageView) view.findViewById(R.id.shopListRatingStar4);
        star5 = (ImageView) view.findViewById(R.id.shopListRatingStar5);
        price = (TextView) view.findViewById(R.id.shopListCharacterPrice);
        name = (TextView) view.findViewById(R.id.shopListCharacterName);
        origin = (TextView) view.findViewById(R.id.shopListCharacterOrigin);
        card = (CardView) view.findViewById(R.id.shopListItemCardView);
        buy = (TextView) view.findViewById(R.id.shopListBuyButton);
        added = (TextView) view.findViewById(R.id.shopListAddedToCart);
    }
}
