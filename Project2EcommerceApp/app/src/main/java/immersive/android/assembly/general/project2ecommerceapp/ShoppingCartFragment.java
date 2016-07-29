package immersive.android.assembly.general.project2ecommerceapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Skywingz on 7/28/16.
 */
public class ShoppingCartFragment extends Fragment {


    private RecyclerView recycler;
    private CardView backButton, checkout;
    private TextView totalCost;

    private ShoppingCartRecyclerAdapter adapter;
    private ShoppingCartRecyclerAdapter.ShoppingCartClickListener listener;




    public static Fragment getInstance(ShoppingCartRecyclerAdapter.ShoppingCartClickListener listen) {
        ShoppingCartFragment fragment = new ShoppingCartFragment();
        fragment.listener = listen;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.shopping_cart_fragment, container, false);

        recycler = (RecyclerView) rootView.findViewById(R.id.shoppingCartRecyclerView);
        backButton = (CardView) rootView.findViewById(R.id.shoppingCartBackButtonCardView);
        totalCost = (TextView) rootView.findViewById(R.id.shoppingCartTotalCost);
        checkout = (CardView) rootView.findViewById(R.id.shoppingCartShoppingCartButtonCardView);


        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        totalCost.setText(HeroManager.getInstance(getActivity()).getTotalCost());

        adapter = new ShoppingCartRecyclerAdapter(HeroManager.getInstance(getActivity()).getShoppingCartHeroes());

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(layoutManager);

        recycler.setAdapter(adapter);

        adapter.setShoppingCartItemClickListener(listener);

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (HeroManager.getInstance(getActivity()).getTotalItems() > 0) {
                    final View dialogView = LayoutInflater.from(getActivity()).inflate(R.layout.popup_confirm_purchase, null);
                    TextView cost = (TextView) dialogView.findViewById(R.id.popupConfirmPurchaseTotalPrice);
                    TextView cancel = (TextView) dialogView.findViewById(R.id.popupConfirmPurchaseCancelButton);
                    TextView proceed = (TextView) dialogView.findViewById(R.id.popupConfirmPurchaseContinueButton);

                    cost.setText(HeroManager.getInstance(getActivity()).getTotalCost());

                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setView(dialogView);

                    final AlertDialog dialog = builder.create();

                    cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });

                    proceed.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            HeroManager.getInstance(getActivity()).emptyShoppingCart();
                            updateList();

                            final View kupoView = LayoutInflater.from(getActivity()).inflate(R.layout.popup_moogle_thank_you, null);
                            CardView keepShopping = (CardView) kupoView.findViewById(R.id.happyMoogleContinueButton);
                            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                            builder.setView(kupoView);

                            final AlertDialog kupoDialog = builder.create();

                            keepShopping.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    kupoDialog.dismiss();
                                }
                            });

                            kupoDialog.show();

                            dialog.dismiss();
                        }
                    });

                    dialog.show();
                }

            }
        });

    }

    public void updateList() {
        adapter.updateList();
        adapter.notifyDataSetChanged();
        totalCost.setText(HeroManager.getInstance(getActivity()).getTotalCost());
    }


}
