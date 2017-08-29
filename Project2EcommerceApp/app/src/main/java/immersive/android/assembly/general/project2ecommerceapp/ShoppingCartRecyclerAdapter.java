package immersive.android.assembly.general.project2ecommerceapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class ShoppingCartRecyclerAdapter extends RecyclerView.Adapter<ShoppingCartRecyclerViewHolder> {

    private ArrayList<Hero> heroes;
    private Context context;

    private ShoppingCartClickListener listener;

    public interface ShoppingCartClickListener{
        void onShoppingCartItemButtonClicked(View view, String heroName, int index);
    }

    public void setShoppingCartItemClickListener(ShoppingCartClickListener listen) {
        this.listener = listen;
    }

    public ShoppingCartRecyclerAdapter(ArrayList<Hero> list) {
        this.heroes = list;
    }

    @Override
    public ShoppingCartRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.shopping_cart_list_item, parent, false);
        ShoppingCartRecyclerViewHolder holder = new ShoppingCartRecyclerViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ShoppingCartRecyclerViewHolder holder, final int position) {
        final Hero hero = heroes.get(position);
        holder.icon.setImageResource(context.getResources().getIdentifier(hero.getIcon(), "drawable", context.getPackageName()));
        holder.name.setText(hero.getName());
        int count = hero.getNumInCart();
        String price = "$" + hero.getPrice();
        double cost = Double.parseDouble(hero.getPrice());
        double total = ((double) count) * cost;
        holder.quantity.setText(String.valueOf(count));
        holder.unitCost.setText(price);
        String finalTotal = "$" + String.format("%.2f", total);
        holder.totalCost.setText(finalTotal);

        holder.star1.setVisibility(View.VISIBLE);
        holder.star2.setVisibility(View.VISIBLE);
        holder.star3.setVisibility(View.VISIBLE);
        holder.star4.setVisibility(View.VISIBLE);
        holder.star5.setVisibility(View.VISIBLE);
        switch(hero.getRarity()) {
            case 2:
                holder.star3.setVisibility(View.GONE);
                holder.star4.setVisibility(View.GONE);
                holder.star5.setVisibility(View.GONE);
                break;
            case 3:
                holder.star4.setVisibility(View.GONE);
                holder.star5.setVisibility(View.GONE);
                break;
            case 4:
                holder.star5.setVisibility(View.GONE);
                break;
            default: break;
        }

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onShoppingCartItemButtonClicked(view, hero.getName(), position);
            }
        };

        holder.card.setOnClickListener(clickListener);
        holder.minus.setOnClickListener(clickListener);
        holder.plus.setOnClickListener(clickListener);


    }

    @Override
    public int getItemCount() {
        return heroes.size();
    }

    public void updateList() {
        heroes = HeroManager.getInstance(context).getShoppingCartHeroes();
    }

}
