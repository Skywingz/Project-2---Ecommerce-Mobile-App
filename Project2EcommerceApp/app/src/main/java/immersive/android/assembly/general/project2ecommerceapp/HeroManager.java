package immersive.android.assembly.general.project2ecommerceapp;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Skywingz on 7/27/16.
 */
public class HeroManager {

    private Context context;
    private ArrayList<Hero> heroes;
    private ArrayList<Review> reviews;
    private ArrayList<Hero> shoppingCartHeroes; // These hero objects contain a quantity
//    private ArrayList<TrustMastery> masteries;
    private boolean sortByName, sortAsc;
    private String originIndex, roleIndex, jobIndex, rarityIndex, raceIndex, genderIndex;


    private static HeroManager instance;

    private HeroManager(final Context context) {
        this.context = context;

//        heroes = new ArrayList<Hero>();
//
//        AsyncTask<Context, Void, ArrayList<Hero>> task1 = new AsyncTask<Context, Void, ArrayList<Hero>>() {
//            @Override
//            protected ArrayList<Hero> doInBackground(Context... contexts) {
//                return DatabaseHelper.getInstance(contexts[0]).getAllHeroes();}
//            @Override
//            protected void onPostExecute(ArrayList<Hero> heros) {
//                Log.d("AsyncTask", "RETRIEVING HEROES " + (heros.size()));
//                heroes = heros;
//                Log.d("AsyncTask", "HEROES SIZE = " + (heroes.size()));}
//        };
//        task1.execute(this.context);

        heroes = DatabaseHelper.getInstance(this.context).getAllHeroes();
        shoppingCartHeroes = DatabaseHelper.getInstance(this.context).getAllShoppingCartHeroes();


        reviews = new ArrayList<>();
        sortByName = true;
        sortAsc = true;
        originIndex = "ANY";
        roleIndex = "ANY";
        jobIndex = "ANY";
        rarityIndex = "ANY";
        raceIndex = "ANY";
        genderIndex = "ANY";
//        originIndex = 0;
//        roleIndex = 0;
//        jobIndex = 0;
//        rarityIndex = 0;
//        raceIndex = 0;
//        genderIndex = 0;


//        masteries = DatabaseHelper.getInstance(context).getAllTrustMasteries();
//        reviews = DatabaseHelper.getInstance(context).getAllReviews();
    }

    public static HeroManager getInstance(Context context) {
        if (instance == null) {
            instance = new HeroManager(context);
        }

        return instance;
    }

    public TrustMastery getHeroTrustMastery(String heroName) {
        return DatabaseHelper.getInstance(context).getTrustMastery(heroName);
    }

    public void loadHeroReviews(String heroName) {
        reviews = DatabaseHelper.getInstance(context).getHeroReviews(heroName);
    }

    public int getTotalReviews() {
        return reviews.size();
    }

    public String getAverageReviewRating() {
        double average = 0.0;
        if (reviews.size() > 0) {
            for (Review review : reviews) {average += Double.parseDouble(review.getUserRating());}
            average = average / (double)reviews.size();
        }

        return String.format("%.1f", average);
    }

    public void addHeroReview(String userName, String userComment, String userRating, String heroName) { //, int heroID) {
        int heroID = 0;
        for (Hero hero : heroes) {
            if (hero.getName().equals(heroName)) {
                heroID = hero.getId();
                break;
            }
        }
        int reviewID = (heroID * 1000) + reviews.size();
        DatabaseHelper.getInstance(context).addHeroReview(reviewID, userName, userComment, userRating, heroName);
        reviews.add(new Review(reviewID, userName, userComment, userRating, heroName));
    }

    public void clearCurrentHeroReviewsList() {
        reviews.clear();
    }

    public int getTotalItems() {
        return DatabaseHelper.getInstance(context).getTotalItemsInCart();
    }

    public String getTotalCost() {
        return "$" + DatabaseHelper.getInstance(context).getTotalCost();
    }

    public void updateListWithSearch(String search) {
        heroes = DatabaseHelper.getInstance(context).getUpdatedHeroSearch(search);
    }

    public Hero getSpecificHero(String name) {
        return DatabaseHelper.getInstance(context).getSingleHero(name);
    }

    public int getHeroCountInCart(String heroName) {
        int count = 0;
        for (Hero hero : shoppingCartHeroes) {
            if (hero.getName().equals(heroName)) {
                count = hero.getNumInCart();
                break;
            }
        }
        return count;
    }

    public void addHeroToCart(String heroName) {
        Hero tempHero = null;
        for (Hero hero : heroes) {
            if (hero.getName().equals(heroName)) {
                tempHero = hero;
                break;
            }
        }
        tempHero.setNumInCart(1);
        shoppingCartHeroes.add(tempHero);
        DatabaseHelper.getInstance(context).addHeroToCart(heroName);
    }

    public void addOneQuantity(String heroName) {
        int newQuantity = 0;
        for (Hero hero : shoppingCartHeroes) {
            if (hero.getName().equals(heroName)) {
                newQuantity = hero.getNumInCart() + 1;
                hero.setNumInCart(newQuantity);
                break;
            }
        }
        DatabaseHelper.getInstance(context).updateHeroQuantity(heroName, newQuantity);
    }

    public void subtractOneQuantity(String heroName) {
        int newQuantity = 0;
        int index = 0;

        for (int i = 0; i < shoppingCartHeroes.size(); i++) {
            if (shoppingCartHeroes.get(i).getName().equals(heroName)) {
                newQuantity = shoppingCartHeroes.get(i).getNumInCart() - 1;
                index = i;
                break;
            }
        }

        if (newQuantity == 0) { // only one left so remove it
            shoppingCartHeroes.remove(index);
            DatabaseHelper.getInstance(context).removeHeroFromCart(heroName);
        } else {
            shoppingCartHeroes.get(index).setNumInCart(newQuantity);
            DatabaseHelper.getInstance(context).updateHeroQuantity(heroName, newQuantity);
        }
    }

    public void emptyShoppingCart() {
        shoppingCartHeroes.clear();
        DatabaseHelper.getInstance(context).emptyShoppingCartTable();
    }

    public String[] getCurrentFilters() {
        return new String[]{originIndex, roleIndex, jobIndex, rarityIndex, raceIndex, genderIndex};
    }

    public boolean[] getCurrentSortingMethods() {
        return new boolean[]{sortByName, sortAsc};
    }

    public void updateSortingAndFiltering(String[] filters, boolean[] sorting) {

        sortByName = sorting[0];
        sortAsc = sorting[1];
        originIndex = filters[0];
        roleIndex = filters[1];
        jobIndex = filters[2];
        rarityIndex = filters[3];
        raceIndex = filters[4];
        genderIndex = filters[5];

        heroes = DatabaseHelper.getInstance(context).getFilteredHeroList(originIndex, roleIndex, jobIndex, rarityIndex, raceIndex, genderIndex);

        if (heroes.size() > 0) {
            if (sortByName) {
                Collections.sort(heroes, new Comparator<Hero>() {
                    @Override
                    public int compare(Hero one, Hero two) {
                        return one.getName().compareTo(two.getName());
                    }
                });

            } else {
                Collections.sort(heroes, new Comparator<Hero>() {
                    @Override
                    public int compare(Hero one, Hero two) {
                        return Double.compare(Double.parseDouble(one.getPrice()), Double.parseDouble(two.getPrice()));
                    }
                });

            }

            if (!sortAsc) {Collections.reverse(heroes);}
        }

    }

    public void forceRecyclerViewRefresh(String heroName) {
        for (Hero hero : heroes) {
            if (hero.getName().equals(heroName)) {
                hero.setName(hero.getName() + "");
                break;
            }
        }
    }




    public ArrayList<Hero> getHeroes() {
        return heroes;
    }

    public void setHeroes(ArrayList<Hero> heroes) {
        this.heroes = heroes;
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }

    public ArrayList<Hero> getShoppingCartHeroes() {
        return shoppingCartHeroes;
    }

    public void setShoppingCartHeroes(ArrayList<Hero> shoppingCartHeroes) {
        this.shoppingCartHeroes = shoppingCartHeroes;
    }
}
