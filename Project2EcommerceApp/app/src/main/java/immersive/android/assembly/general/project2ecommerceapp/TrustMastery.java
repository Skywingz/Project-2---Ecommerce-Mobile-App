package immersive.android.assembly.general.project2ecommerceapp;


public class TrustMastery {

    private int id;
    private String name;
    private String info;
    private String icon;
    private String heroName;


    public TrustMastery() {}

    public TrustMastery(int id, String name, String info, String icon, String heroName) {
        this.id = id;
        this.name = name;
        this.info = info;
        this.icon = icon;
        this.heroName = heroName;
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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }
}
