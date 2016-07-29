package immersive.android.assembly.general.project2ecommerceapp;

/**
 * Created by Skywingz on 7/26/16.
 */
public class Hero {

    private int id;
    private String name;
    private String origin;
    private String role;
    private String job;
    private int rarity;
    private String race;
    private String gender;
    private String backStory;
    private String icon;

    private String reviewRating;
    private int totalReviews;

    private String price;
    private int numInCart;


    public Hero() {}

    public Hero(int id, String name, String origin, String role, String job, int rarity, String race,
                String gender, String backStory, String icon, String reviewRating, int totalReviews,
                String price, int numInCart) {

        this.id = id;
        this.name = name;
        this.origin = origin;
        this.role = role;
        this.job = job;
        this.rarity = rarity;
        this.race = race;
        this.gender = gender;
        this.backStory = backStory;
        this.icon = icon;
        this.reviewRating = reviewRating;
        this.totalReviews = totalReviews;
        this.price = price;
        this.numInCart = numInCart;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public int getRarity() {
        return rarity;
    }

    public void setRarity(int rarity) {
        this.rarity = rarity;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBackStory() {
        return backStory;
    }

    public void setBackStory(String backStory) {
        this.backStory = backStory;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getReviewRating() {
        return reviewRating;
    }

    public void setReviewRating(String reviewRating) {
        this.reviewRating = reviewRating;
    }

    public int getTotalReviews() {
        return totalReviews;
    }

    public void setTotalReviews(int totalReviews) {
        this.totalReviews = totalReviews;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getNumInCart() {
        return numInCart;
    }

    public void setNumInCart(int numInCart) {
        this.numInCart = numInCart;
    }
}
