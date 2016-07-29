package immersive.android.assembly.general.project2ecommerceapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Skywingz on 7/28/16.
 */
public class DetailMainFragment extends Fragment {

    private DetailPagerAdapter pagerAdapter;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private CardView buyButton, cart;
    private TextView totalCost, price, quantity, added;
    private String heroName;

    private DetailButtonClickedListener listener;

    public interface DetailButtonClickedListener {
        void onDetailButtonClicked(View view);
    }


    public DetailMainFragment() {}

    public static Fragment getInstance(String name, DetailButtonClickedListener listen) {
        DetailMainFragment fragment = new DetailMainFragment();
        fragment.heroName = name;
        fragment.listener = listen;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_main_fragment, container, false);

        viewPager = (ViewPager) view.findViewById(R.id.detailViewPager);
        tabLayout = (TabLayout) view.findViewById(R.id.detailTabLayout);
        totalCost = (TextView) view.findViewById(R.id.detailTotalCost);
        buyButton = (CardView) view.findViewById(R.id.detailBuyButtonCardView);
        cart = (CardView) view.findViewById(R.id.detailShoppingCartButtonCardView);
        price = (TextView) view.findViewById(R.id.detailBuyButtonPrice);
        quantity = (TextView) view.findViewById(R.id.detailShoppingCartQuantity);
        added = (TextView) view.findViewById(R.id.detailAddedToCart);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Hero hero = HeroManager.getInstance(getActivity()).getSpecificHero(heroName);
        totalCost.setText(HeroManager.getInstance(getActivity()).getTotalCost());
        quantity.setText(String.valueOf(HeroManager.getInstance(getActivity()).getTotalItems()));
        String cost = "$" + hero.getPrice();
        price.setText(cost);
        if (HeroManager.getInstance(getActivity()).getHeroCountInCart(heroName) > 0) {
            buyButton.setEnabled(false);
            buyButton.setVisibility(View.INVISIBLE);
            added.setVisibility(View.VISIBLE);
        } else {
            buyButton.setEnabled(true);
            buyButton.setVisibility(View.VISIBLE);
            added.setVisibility(View.INVISIBLE);
        }

        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HeroManager.getInstance(getActivity()).addHeroToCart(heroName);
                buyButton.setEnabled(false);
                buyButton.setVisibility(View.INVISIBLE);
                added.setVisibility(View.VISIBLE);
                totalCost.setText(HeroManager.getInstance(getActivity()).getTotalCost());
                quantity.setText(String.valueOf(HeroManager.getInstance(getActivity()).getTotalItems()));
            }
        });

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO temporarily disabled because of wierd fragment stacking, needs further research
//                listener.onDetailButtonClicked(view);
            }
        });


        pagerAdapter = new DetailPagerAdapter(getFragmentManager(), 3, heroName);

        viewPager.setAdapter(pagerAdapter);

        tabLayout.setupWithViewPager(viewPager);


    }
}
