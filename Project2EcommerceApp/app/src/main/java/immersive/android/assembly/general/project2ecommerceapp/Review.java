package immersive.android.assembly.general.project2ecommerceapp;

/**
 * Created by Skywingz on 7/26/16.
 */
public class Review {

    private int id;
    private String userName;
    private String userComment;
    private String userRating;
    private String heroName;



    public Review() {}

    public Review(int id, String userName, String userComment, String userRating, String heroName) {
        this.id = id;
        this.userName = userName;
        this.userComment = userComment;
        this.userRating = userRating;
        this.heroName = heroName;

    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserComment() {
        return userComment;
    }

    public void setUserComment(String userComment) {
        this.userComment = userComment;
    }

    public String getUserRating() {
        return userRating;
    }

    public void setUserRating(String userRating) {
        this.userRating = userRating;
    }

    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }



}
