package immersive.android.assembly.general.project2ecommerceapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;


public class MainShopFragment extends Fragment {

    private RecyclerView recycler;
    private MainShopRecyclerAdapter adapter;

    private MainShopRecyclerAdapter.ShopItemClickListener listener;
    private ShopFragmentClickListener fragmentClickListener;
    private SpinnerItemSelectedListener spinnerListener;

    private CardView filter, shoppingCart;
    private TextView totalCost, totalItems, filterText;
    private View bottomSheet;
    private BottomSheetBehavior mBottomSheetBehavior;

    private TextView sortByName, sortByPrice, sortByAsc, sortByDesc, filterAll; //, filterNone;
    private TextView sortByNameBack, sortByPriceBack, sortByAscBack, sortByDescBack;
    private Spinner origin, job, role, rarity, race, gender;



    public interface ShopFragmentClickListener {
        void onShopButtonsClicked(View view);
    }

    public interface SpinnerItemSelectedListener {
        void onSpinnerItemSelected(AdapterView<?> parent, View view, int position, long id);
    }

    public static Fragment getInstance(MainShopRecyclerAdapter.ShopItemClickListener listen, ShopFragmentClickListener shopListener,
                  SpinnerItemSelectedListener spinListener) {
        MainShopFragment fragment = new MainShopFragment();
        fragment.listener = listen;
        fragment.fragmentClickListener = shopListener;
        fragment.spinnerListener = spinListener;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.main_shop_fragment, container, false);

        recycler = (RecyclerView) rootView.findViewById(R.id.shopRecyclerView);
        filter = (CardView) rootView.findViewById(R.id.filterButtonCardView);
        filterText = (TextView) rootView.findViewById(R.id.filterButton);
        shoppingCart = (CardView) rootView.findViewById(R.id.shoppingCartButtonCardView);
        totalCost = (TextView) rootView.findViewById(R.id.shopTotalCost);
        totalItems = (TextView) rootView.findViewById(R.id.shoppingCartQuantity);
        bottomSheet = rootView.findViewById(R.id.main_bottom_sheet);

        sortByName = (TextView) rootView.findViewById(R.id.sortbyName);
        sortByPrice = (TextView) rootView.findViewById(R.id.sortbyPrice);
        sortByAsc = (TextView) rootView.findViewById(R.id.sortbyAsc);
        sortByDesc = (TextView) rootView.findViewById(R.id.sortbyDesc);

        sortByNameBack = (TextView) rootView.findViewById(R.id.sortbyNameBackground);
        sortByPriceBack = (TextView) rootView.findViewById(R.id.sortbyPriceBackground);
        sortByAscBack = (TextView) rootView.findViewById(R.id.sortbyAscBackground);
        sortByDescBack = (TextView) rootView.findViewById(R.id.sortbyDescBackground);
        filterAll = (TextView) rootView.findViewById(R.id.filterAll);

        origin = (Spinner) rootView.findViewById(R.id.originSpinner);
        job = (Spinner) rootView.findViewById(R.id.jobSpinner);
        role = (Spinner) rootView.findViewById(R.id.roleSpinner);
        rarity = (Spinner) rootView.findViewById(R.id.raritySpinner);
        race = (Spinner) rootView.findViewById(R.id.raceSpinner);
        gender = (Spinner) rootView.findViewById(R.id.genderSpinner);


        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mBottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        mBottomSheetBehavior.setPeekHeight(0);
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        mBottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_DRAGGING) {
                    mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
            }
        });

        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mBottomSheetBehavior.getState() == BottomSheetBehavior.STATE_HIDDEN
                        || mBottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
                    mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    filterText.setText("Apply");
                    filter.setCardBackgroundColor(getResources().getColor(R.color.green_500));
                    shoppingCart.setEnabled(false);
                } else {
                    mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    filterText.setText("Filter");
                    filter.setCardBackgroundColor(getResources().getColor(R.color.indigo_A400));
                    shoppingCart.setEnabled(true);

                    fragmentClickListener.onShopButtonsClicked(view); // check for filter options

                }
            }
        });

        shoppingCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentClickListener.onShopButtonsClicked(view);
            }
        });

        sortByName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sortByNameBack.setVisibility(View.VISIBLE);
                sortByPriceBack.setVisibility(View.INVISIBLE);
                fragmentClickListener.onShopButtonsClicked(view);
            }
        });

        sortByPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sortByNameBack.setVisibility(View.INVISIBLE);
                sortByPriceBack.setVisibility(View.VISIBLE);
                fragmentClickListener.onShopButtonsClicked(view);
            }
        });

        sortByAsc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sortByAscBack.setVisibility(View.VISIBLE);
                sortByDescBack.setVisibility(View.INVISIBLE);
                fragmentClickListener.onShopButtonsClicked(view);
            }
        });

        sortByDesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sortByAscBack.setVisibility(View.INVISIBLE);
                sortByDescBack.setVisibility(View.VISIBLE);
                fragmentClickListener.onShopButtonsClicked(view);
            }
        });

        filterAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                origin.setSelection(0);
                job.setSelection(0);
                role.setSelection(0);
                rarity.setSelection(0);
                race.setSelection(0);
                gender.setSelection(0);

            }
        });


        totalCost.setText(HeroManager.getInstance(getActivity()).getTotalCost());
        totalItems.setText(String.valueOf(HeroManager.getInstance(getActivity()).getTotalItems()));

        ArrayAdapter<CharSequence> originAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.character_origin, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> jobAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.character_job, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> roleAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.character_role, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> rarityAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.character_max_rarity, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> raceAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.character_race, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> genderAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.character_gender, android.R.layout.simple_spinner_item);
        originAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        jobAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        roleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        rarityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        raceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        origin.setAdapter(originAdapter);
        job.setAdapter(jobAdapter);
        role.setAdapter(roleAdapter);
        rarity.setAdapter(rarityAdapter);
        race.setAdapter(raceAdapter);
        gender.setAdapter(genderAdapter);

        origin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinnerListener.onSpinnerItemSelected(adapterView, view, i, l);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        job.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinnerListener.onSpinnerItemSelected(adapterView, view, i, l);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        role.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinnerListener.onSpinnerItemSelected(adapterView, view, i, l);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        rarity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinnerListener.onSpinnerItemSelected(adapterView, view, i, l);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        race.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinnerListener.onSpinnerItemSelected(adapterView, view, i, l);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinnerListener.onSpinnerItemSelected(adapterView, view, i, l);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });


        adapter = new MainShopRecyclerAdapter(HeroManager.getInstance(getActivity()).getHeroes());

        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(manager);

        recycler.setAdapter(adapter);

        adapter.setShopItemClickListener(listener);


    }

    public void updateList() {
        adapter.updateList();
        adapter.notifyDataSetChanged();
    }

    public void updateTotalItemsAndCost() {
        totalCost.setText(HeroManager.getInstance(getActivity()).getTotalCost());
        totalItems.setText(String.valueOf(HeroManager.getInstance(getActivity()).getTotalItems()));
    }

    public void reInitializeSorting() {
        boolean[] initial = HeroManager.getInstance(getActivity()).getCurrentSortingMethods(); //{sortByName, sortAsc}
        if (initial[0]) {
            sortByNameBack.setVisibility(View.VISIBLE);
            sortByPriceBack.setVisibility(View.INVISIBLE);
        } else {
            sortByNameBack.setVisibility(View.INVISIBLE);
            sortByPriceBack.setVisibility(View.VISIBLE);
        }
        if (initial[1]) {
            sortByAscBack.setVisibility(View.VISIBLE);
            sortByDescBack.setVisibility(View.INVISIBLE);
        } else {
            sortByAscBack.setVisibility(View.INVISIBLE);
            sortByDescBack.setVisibility(View.VISIBLE);
        }
    }







}
