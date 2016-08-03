package immersive.android.assembly.general.project2ecommerceapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Skywingz on 7/27/16.
 */
public class MainShopRecyclerAdapter extends RecyclerView.Adapter<MainShopRecyclerViewHolder> {

    private ArrayList<Hero> heroes;
    private Context context;

    private ShopItemClickListener listener;

    public interface ShopItemClickListener {
        void onShopItemClicked(View view, String heroName);
    }

    public void setShopItemClickListener(ShopItemClickListener itemListener) {
        this.listener = itemListener;
    }

    public MainShopRecyclerAdapter(ArrayList<Hero> list) {
        heroes = list;
    }


    @Override
    public MainShopRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.main_shop_list_item, parent, false);
        MainShopRecyclerViewHolder holder = new MainShopRecyclerViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MainShopRecyclerViewHolder holder, int position) {
        final Hero hero = heroes.get(position);
        holder.position = position;
        holder.name.setText(hero.getName());
        String heroOrigin = "(" + hero.getOrigin() + ")";
        holder.origin.setText(heroOrigin);
        String cost = "$" + hero.getPrice();
        holder.price.setText(cost);
        holder.icon.setImageResource(context.getResources().getIdentifier(hero.getIcon(), "drawable", context.getPackageName()));
        if (HeroManager.getInstance(context).getHeroCountInCart(hero.getName()) > 0) {
            holder.added.setVisibility(View.VISIBLE);
            holder.buy.setEnabled(false);
            holder.buy.setVisibility(View.GONE);
        } else {
            holder.added.setVisibility(View.INVISIBLE);
            holder.buy.setEnabled(true);
            holder.buy.setVisibility(View.VISIBLE);
        }

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
                listener.onShopItemClicked(view, hero.getName());
            }
        };

        holder.card.setOnClickListener(clickListener);
        holder.buy.setOnClickListener(clickListener);

    }

    @Override
    public int getItemCount() {
        return heroes.size();
    }

    public void updateList() {
        heroes = HeroManager.getInstance(context).getHeroes();
    }
}
