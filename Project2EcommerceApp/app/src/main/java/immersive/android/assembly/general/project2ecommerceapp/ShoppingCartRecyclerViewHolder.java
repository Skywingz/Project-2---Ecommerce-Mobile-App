package immersive.android.assembly.general.project2ecommerceapp;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class ShoppingCartRecyclerViewHolder extends RecyclerView.ViewHolder {

    public ImageView icon, minus, plus, star1, star2, star3, star4, star5;
    public TextView name, quantity, unitCost, totalCost;
    public CardView card;


    public ShoppingCartRecyclerViewHolder(View view) {
        super(view);

        icon = (ImageView) view.findViewById(R.id.cartListCharacterIcon);
        name = (TextView) view.findViewById(R.id.cartListCharacterName);
        quantity = (TextView) view.findViewById(R.id.checkoutItemQuantity);
        unitCost = (TextView) view.findViewById(R.id.cartListCharacterPrice);
        totalCost = (TextView) view.findViewById(R.id.cartListCharacterTotalPrice);
        minus = (ImageView) view.findViewById(R.id.checkoutItemMinus);
        plus = (ImageView) view.findViewById(R.id.checkoutItemPlus);
        star1 = (ImageView) view.findViewById(R.id.cartListRatingStar1);
        star2 = (ImageView) view.findViewById(R.id.cartListRatingStar2);
        star3 = (ImageView) view.findViewById(R.id.cartListRatingStar3);
        star4 = (ImageView) view.findViewById(R.id.cartListRatingStar4);
        star5 = (ImageView) view.findViewById(R.id.cartListRatingStar5);
        card = (CardView) view.findViewById(R.id.cartListItemCardView);
    }
}
