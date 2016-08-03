package immersive.android.assembly.general.project2ecommerceapp;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity implements MainShopRecyclerAdapter.ShopItemClickListener,
        MainShopFragment.ShopFragmentClickListener, ShoppingCartRecyclerAdapter.ShoppingCartClickListener, MainShopFragment.SpinnerItemSelectedListener, DetailMainFragment.DetailButtonClickedListener {

    private Toolbar toolbar;
    private int stackCount;
    private boolean sortByName, sortAsc;
    private String originIndex, roleIndex, jobIndex, rarityIndex, raceIndex, genderIndex;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stackCount = 1;
        sortByName = true;
        sortAsc = true;
        originIndex = "ANY";
        roleIndex = "ANY";
        jobIndex = "ANY";
        rarityIndex = "ANY";
        raceIndex = "ANY";
        genderIndex = "ANY";

        toolbar = (Toolbar) findViewById(R.id.mainShopTopToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        handleIntent(getIntent());

        getSupportFragmentManager().beginTransaction().add(R.id.main_activity_container, MainShopFragment.getInstance(this, this, this), "main_fragment_tag").commit();






    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if(Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = getIntent().getStringExtra(SearchManager.QUERY);
            HeroManager.getInstance(this).updateListWithSearch(query.trim());
            ((MainShopFragment)getSupportFragmentManager().findFragmentByTag("main_fragment_tag")).updateList();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_shop_menu_options, menu);

        SearchManager manager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.searchOption).getActionView();
        ComponentName name = new ComponentName(this, MainActivity.class);
        searchView.setSearchableInfo(manager.getSearchableInfo(name));

        return true;
    }





    @Override
    public void onShopItemClicked(View view, String heroName) {
        switch(view.getId()) {
            case R.id.shopListItemCardView: // entire item clicked
                stackCount++;
                toolbar.setVisibility(View.GONE);
                getSupportFragmentManager().beginTransaction().addToBackStack("main_fragment_tag")
                        .replace(R.id.main_activity_container, DetailMainFragment.getInstance(heroName, this), "detail_fragment").commit();
                break;
            case R.id.shopListBuyButton: // buy item button pressed
                HeroManager.getInstance(this).addHeroToCart(heroName);
                ((MainShopFragment)getSupportFragmentManager().findFragmentByTag("main_fragment_tag")).updateList();
                ((MainShopFragment)getSupportFragmentManager().findFragmentByTag("main_fragment_tag")).updateTotalItemsAndCost();
                break;
            default: break;
        }
    }

    @Override
    public void onDetailButtonClicked(View view) {
        switch(view.getId()) {
            case R.id.detailShoppingCartButtonCardView:
                getSupportFragmentManager().beginTransaction()//.addToBackStack("detail_fragment")
                        .replace(R.id.main_activity_container, ShoppingCartFragment.getInstance(this), "shopping_cart_fragment").commit();
                break;
            default: break;
        }
    }

    @Override
    public void onShopButtonsClicked(View view) {
        switch(view.getId()) {
//            case R.id.filterButtonCardView: break;
            case R.id.shoppingCartButtonCardView:
                stackCount++;
                toolbar.setVisibility(View.GONE);
                getSupportFragmentManager().beginTransaction().addToBackStack("main_fragment_tag")
                        .replace(R.id.main_activity_container, ShoppingCartFragment.getInstance(this), "shopping_cart_fragment").commit();
                break;
            case R.id.filterButtonCardView:

                boolean[] currentSorting = HeroManager.getInstance(this).getCurrentSortingMethods(); // {sortByName, sortAsc}
                String[] currentFilters = HeroManager.getInstance(this).getCurrentFilters(); // {originIndex, roleIndex, jobIndex, rarityIndex, raceIndex, genderIndex}

                if (currentSorting[0] != sortByName || currentSorting[1] != sortAsc || currentFilters[0] != originIndex || currentFilters[1] != roleIndex
                        || currentFilters[2] != jobIndex || currentFilters[3] != rarityIndex || currentFilters[4] != raceIndex || currentFilters[5] != genderIndex) {

                    HeroManager.getInstance(this).updateSortingAndFiltering(new String[]{originIndex, roleIndex, jobIndex, rarityIndex, raceIndex, genderIndex}, new boolean[]{sortByName, sortAsc});
                    ((MainShopFragment)getSupportFragmentManager().findFragmentByTag("main_fragment_tag")).updateList();
                    ((MainShopFragment)getSupportFragmentManager().findFragmentByTag("main_fragment_tag")).updateTotalItemsAndCost();

                }

                break;
            case R.id.sortbyName:
                if (!sortByName) {sortByName = true;}
                break;
            case R.id.sortbyPrice:
                if (sortByName) {sortByName = false;}
                break;
            case R.id.sortbyAsc:
                if (!sortAsc) {sortAsc = true;}
                break;
            case R.id.sortbyDesc:
                if (sortAsc) {sortAsc = false;}
                break;
//            case R.id.filterAll: break;
//            case R.id.filterNone: break;
            default: break;
        }


    }

    @Override
    public void onSpinnerItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch(parent.getId()) {
            case R.id.originSpinner:
                originIndex = parent.getSelectedItem().toString();
                break;
            case R.id.jobSpinner:
                jobIndex = parent.getSelectedItem().toString();
                break;
            case R.id.roleSpinner:
                roleIndex = parent.getSelectedItem().toString();
                break;
            case R.id.raritySpinner:
                rarityIndex = parent.getSelectedItem().toString();
                break;
            case R.id.raceSpinner:
                raceIndex = parent.getSelectedItem().toString();
                break;
            case R.id.genderSpinner:
                genderIndex = parent.getSelectedItem().toString();
                break;
            default: break;
        }

    }

    @Override
    public void onShoppingCartItemButtonClicked(View view, String heroName, int index) {
        switch(view.getId()) {
            case R.id.cartListItemCardView: // entire item clicked
                stackCount++;
                toolbar.setVisibility(View.GONE);
                getSupportFragmentManager().beginTransaction().addToBackStack("shopping_cart_fragment")
                        .replace(R.id.main_activity_container, DetailMainFragment.getInstance(heroName, this), "detail_fragment").commit();
                break;
            case R.id.checkoutItemMinus: // minus button clicked
                HeroManager.getInstance(this).subtractOneQuantity(heroName);
                ((ShoppingCartFragment) getSupportFragmentManager().findFragmentByTag("shopping_cart_fragment")).updateList();
                break;
            case R.id.checkoutItemPlus: // plus button clicked
                HeroManager.getInstance(this).addOneQuantity(heroName);
                ((ShoppingCartFragment) getSupportFragmentManager().findFragmentByTag("shopping_cart_fragment")).updateList();
                break;

            default: break;
        }
    }




    @Override
    public void onBackPressed() {
        super.onBackPressed();

        HeroManager.getInstance(this).clearCurrentHeroReviewsList();

        stackCount--;
        if (stackCount == 1) {
            toolbar.setVisibility(View.VISIBLE);
            ((MainShopFragment)getSupportFragmentManager().findFragmentByTag("main_fragment_tag")).reInitializeSorting();
            ((MainShopFragment)getSupportFragmentManager().findFragmentByTag("main_fragment_tag")).updateList();
            ((MainShopFragment)getSupportFragmentManager().findFragmentByTag("main_fragment_tag")).updateTotalItemsAndCost();
        }
    }


}
