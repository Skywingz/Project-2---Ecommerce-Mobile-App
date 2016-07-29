package immersive.android.assembly.general.project2ecommerceapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Skywingz on 7/26/16.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "immersive.android.assembly.general.project2ecommerceapp.database";
    private static final int DATABASE_VERSION = 2;

    private static final String TABLE_HERO = "table_name_hero";
    private static final String TABLE_TRUST_MASTERY = "table_name_trust_mastery";
    private static final String TABLE_REVIEW = "table_name_review";
    private static final String TABLE_SHOPPING_CART = "table_name_shopping_cart";
    private static final String COL_ID = "_id";

    // Columns for Hero Table
    private static final String COL_HERO_NAME = "column_hero_name";
    private static final String COL_HERO_ORIGIN = "column_hero_origin";
    private static final String COL_HERO_ROLE = "column_hero_role";
    private static final String COL_HERO_JOB = "column_hero_job";
    private static final String COL_HERO_RARITY = "column_hero_rarity";
    private static final String COL_HERO_RACE = "column_hero_race";
    private static final String COL_HERO_GENDER = "column_hero_gender";
    private static final String COL_HERO_BACK_STORY = "column_hero_back_story";
    private static final String COL_HERO_ICON = "column_hero_icon";
    private static final String COL_HERO_REVIEW_RATING = "column_hero_review_rating";
    private static final String COL_HERO_TOTAL_REVIEWS = "column_hero_total_reviews";
    private static final String COL_HERO_PRICE = "column_hero_price";
    private static final String COL_HERO_NUMBER_IN_CART = "column_hero_number_in_cart";

    // Columns for TrustMastery Table
    private static final String COL_TRUST_MASTERY_NAME = "column_trust_mastery_name";
    private static final String COL_TRUST_MASTERY_INFO = "column_trust_mastery_info";
    private static final String COL_TRUST_MASTERY_ICON = "column_trust_mastery_icon";
    private static final String COL_TRUST_MASTERY_HERO_NAME = "column_trust_mastery_hero_name";

    // Columns for Review Table
    private static final String COL_REVIEW_USER_NAME = "column_review_user_name";
    private static final String COL_REVIEW_USER_COMMENT = "column_review_user_comment";
    private static final String COL_REVIEW_USER_RATING = "column_review_user_rating";
    private static final String COL_REVIEW_HERO_NAME = "column_review_hero_name";

    // Columns for Shopping Cart Table
    private static final String COL_SHOPPING_CART_HERO_NAME = "column_shopping_cart_hero_name";
    private static final String COL_SHOPPING_CART_ITEM_QUANTITY = "column_shopping_cart_item_quantity";


    // Table SQL Exec Strings
    private static final String CREATE_HERO_TABLE =
            "CREATE TABLE " + TABLE_HERO + "("
                    + COL_ID + " INTEGER PRIMARY KEY, "
                    + COL_HERO_NAME + " TEXT, "
                    + COL_HERO_ORIGIN + " TEXT, "
                    + COL_HERO_ROLE + " TEXT, "
                    + COL_HERO_JOB + " TEXT, "
                    + COL_HERO_RARITY + " INTEGER, "
                    + COL_HERO_RACE + " TEXT, "
                    + COL_HERO_GENDER + " TEXT, "
                    + COL_HERO_BACK_STORY + " TEXT, "
                    + COL_HERO_ICON + " TEXT, "
                    + COL_HERO_REVIEW_RATING + " TEXT, "
                    + COL_HERO_TOTAL_REVIEWS + " INTEGER, "
                    + COL_HERO_PRICE + " TEXT, "
                    + COL_HERO_NUMBER_IN_CART + " INTEGER)";

    private static final String CREATE_TRUST_MASTERY_TABLE =
            "CREATE TABLE " + TABLE_TRUST_MASTERY + "("
                    + COL_ID + " INTEGER PRIMARY KEY, "
                    + COL_TRUST_MASTERY_NAME + " TEXT, "
                    + COL_TRUST_MASTERY_INFO + " TEXT, "
                    + COL_TRUST_MASTERY_ICON + " TEXT, "
                    + COL_TRUST_MASTERY_HERO_NAME + " TEXT)";

    private static final String CREATE_REVIEW_TABLE =
            "CREATE TABLE " + TABLE_REVIEW + "("
                    + COL_ID + " INTEGER PRIMARY KEY, "
                    + COL_REVIEW_USER_NAME + " TEXT, "
                    + COL_REVIEW_USER_COMMENT + " TEXT, "
                    + COL_REVIEW_USER_RATING + " TEXT, "
                    + COL_REVIEW_HERO_NAME + " TEXT)";

    private static final String CREATE_SHOPPING_CART_TABLE =
            "CREATE TABLE " + TABLE_SHOPPING_CART + "("
                    + COL_ID + " INTEGER PRIMARY KEY, "
                    + COL_SHOPPING_CART_HERO_NAME + " TEXT, "
                    + COL_SHOPPING_CART_ITEM_QUANTITY + " INTEGER)";



    private static DatabaseHelper instance;

    public static DatabaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseHelper(context.getApplicationContext());
        }

        return instance;
    }



    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_HERO_TABLE);
        database.execSQL(CREATE_TRUST_MASTERY_TABLE);
        database.execSQL(CREATE_REVIEW_TABLE);
        database.execSQL(CREATE_SHOPPING_CART_TABLE);

        // Populate the Hero and Trust Mastery Tables
        ContentValues values = new ContentValues();
        int[] ids = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
                11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
                21, 22, 23, 24, 25, 26, 27, 28, 29, 30,
                31, 32, 33, 34, 35, 36, 37, 38, 39, 40,
                41, 42, 43, 44, 45, 46, 47, 48, 49, 50,
                51, 52, 53, 54, 55, 56, 57, 58, 59, 60,
                61, 62, 63, 64, 65, 66, 67, 68, 69, 70,
                71, 72, 73};

        String[] heroNames = {"Anastasis", "Anzelm", "Artemios", "Bartz", "Baurg", "Bedile", "Biggs", "Carrie", "Cecil", "Celes",
                "Cerius", "Chizuru", "Cloud of Darkness", "Clyne", "Cyan", "Duane", "Edgar", "Eldin", "Exdeath", "Fina",
                "Firion", "Fran", "Galuf", "Garland", "Gilbert", "Gimlee", "Golbez", "Hayate", "Kain", "Kefka",
                "Kenyu", "King Giott", "Krile", "Kuja", "Lasswell", "Leah", "Leo", "Liza", "Locke", "Luna",
                "Magitek Armor Terra", "Maria", "Maxell", "Medius", "Mel", "Miyuki", "Mizell", "Montana", "Ollie", "Paul",
                "Paula", "Penelo", "Rain", "Rakshasa", "Rizer", "Ronaldo", "Roselia", "Russell", "Rydia", "Sabin",
                "Sarah", "Sarai", "Shadow", "Shantotto", "Shiki", "Skaha", "Terra", "Tronn", "Vaan", "Vivi",
                "Wedge", "Xiao", "Zidane"};

        String[] heroOrigins = {"FF 12", "FF BE", "FF BE", "FF 5", "FF BE", "FF BE", "FF 6", "FF BE", "FF 4", "FF 6",
                "FF BE", "FF BE", "FF 3", "FF BE", "FF 6", "FF BE", "FF 6", "FF BE", "FF 5", "FF BE",
                "FF 2", "FF 12", "FF 5", "FF 1", "FF BE", "FF BE", "FF 4", "FF BE", "FF 4", "FF 6",
                "FF BE", "FF 4", "FF 5", "FF 9", "FF BE", "FF BE", "FF 6", "FF BE", "FF 6", "FF BE",
                "FF 6", "FF 2", "FF BE", "FF BE", "FF BE", "FF BE", "FF BE", "FF BE", "FF BE", "FF 2",
                "FF BE", "FF 12", "FF BE", "FF BE", "FF BE", "FF BE", "FF BE", "FF BE", "FF 4", "FF 6",
                "FF 1", "FF BE", "FF 6", "FF 11", "FF BE", "FF BE", "FF 6", "FF BE", "FF 12", "FF 9",
                "FF 6", "FF BE", "FF 9"};

        String[] heroRoles = {"Healing", "Magic Damage, Healing", "Physical Damage", "Physical Damage", "Physical Damage", "Hybrid Damage, Support", "Magic Damage", "Physical Damage, Healing", "Tank, Healing", "Magic Damage, Support",
                "Support", "Physical Damage", "Physical Damage, Support", "Healing", "Physical Damage", "Support, Physical Damage", "Physical Damage, Support", "Support", "Magic Damage", "Healing, Support",
                "Physical Damage", "Support, Healing", "Magic Damage", "Physical Damage, Tank", "Support", "Healing", "Magic Damage", "Support", "Physical Damage", "Magic Damage",
                "Physical Damage", "Physical Damage", "Magic Damage, Healing", "Magic Damage", "Physical Damage", "Healing", "Tank, Physical Damage", "Support", "Support, Physical Damage", "Physical Damage",
                "Magic Damage", "Healing", "Physical Damage", "Physical Damage", "Support, Healing", "Support", "Support", "Support", "Tank", "Support",
                "Magic Damage", "Support", "Physical Damage, Support", "Hybrid Damage, Support", "Physical Damage", "Hybrid Damage", "Healing", "Tank, Support", "Magic Damage", "Physical Damage",
                "Support", "Support", "Support", "Magic Damage", "Physical Damage", "Physical Damage", "Magic Damage, Healing", "Magic Damage", "Support", "Magic Damage",
                "Magic Damage", "Physical Damage, Support", "Support"};

        String[] heroJobs = {"High Priest", "Red Mage", "Ranger", "Adventurer", "Monk", "Spellblade", "Imperial", "Machinist", "Paladin", "Rune Knight",
                "Green Mage", "Samurai", "Ravager", "Knight", "Samurai", "Warmage", "Machinist", "Thief", "Dark Mage", "White Mage",
                "Warrior", "Sky Pirate", "Warrior", "Chaos Knight", "Bard", "Ranger", "Mage", "Ninja", "Dragoon", "Archmage",
                "Black Belt", "Warrior", "Red Mage", "Ravager", "Knight", "White Mage", "General", "Bard", "Adventurer", "Ranger",
                "Imperial", "White Mage", "Gunner", "Gunner", "Alchemist", "Ninja", "Green Mage", "Adventurer", "Viking", "Bandit",
                "Black Mage", "Dancer", "Knight", "Spellblade", "Warrior", "Spellblade", "White Mage", "Gladiator", "Summoner", "Monk",
                "Songstress", "Dancer", "Assassin", "Black Mage", "Samurai", "Dragoon", "Magic Warrior", "Black Mage", "Warrior", "Black Mage",
                "Imperial", "Monk", "Bandit"};

        int[] heroRarity = {3, 4, 5, 5, 2, 4, 3, 3, 5, 5,
                5, 5, 5, 4, 4, 5, 4, 2, 5, 5,
                5, 4, 4, 5, 5, 2, 5, 5, 4, 5,
                3, 3, 4, 5, 5, 2, 5, 2, 5, 4,
                4, 4, 2, 5, 3, 5, 3, 3, 3, 3,
                3, 4, 5, 5, 2, 3, 5, 4, 4, 4,
                3, 2, 4, 4, 3, 3, 5, 2, 5, 4,
                3, 5, 5};

        String[] heroRace = {"Human", "Human", "Human", "Human", "Human", "Human", "Human", "Human", "Human", "Human",
                "Human", "Human", "Reaper", "Human", "Human", "Human", "Human", "Human", "Human", "Human",
                "Human", "Viera", "Human", "Human", "Human", "Human", "Human", "Human", "Human", "Human",
                "Human", "Dwarf", "Human", "Angel", "Human", "Human", "Human", "Human", "Human", "Human",
                "Human", "Human", "Human", "Human", "Human", "Human", "Human", "Human", "Human", "Human",
                "Human", "Human", "Human", "Human", "Human", "Human", "Human", "Human", "Human", "Human",
                "Human", "Human", "Human", "Tarutaru", "Human", "Human", "Human", "Human", "Human", "Black Mage",
                "Human", "Human", "Human"};

        String[] heroGenders = {"Male", "Male", "Male", "Male", "Male", "Male", "Male", "Female", "Male", "Female",
                "Female", "Female", "Female", "Male", "Male", "Male", "Male", "Male", "Male", "Female",
                "Male", "Female", "Male", "Male", "Male", "Male", "Male", "Male", "Male", "Male",
                "Male", "Male", "Female", "Male", "Male", "Female", "Male", "Female", "Male", "Female",
                "Female", "Female", "Male", "Male", "Female", "Female", "Male", "Male", "Male", "Male",
                "Female", "Female", "Male", "Female", "Male", "Male", "Female", "Male", "Female", "Male",
                "Female", "Female", "Male", "Female", "Male", "Female", "Female", "Male", "Male", "Male",
                "Male", "Female", "Male"};

        String[] heroStory = {"anastasis_story", "anzelm_story", "artemios_story", "bartz_story", "baurg_story", "bedile_story", "biggs_story", "carrie_story", "cecil_story", "celes_story",
                "cerius_story", "chizuru_story", "cloudofdarkness_story", "clyne_story", "cyan_story", "duane_story", "edgar_story", "eldin_story", "exdeath_story", "fina_story",
                "firion_story", "fran_story", "galuf_story", "garland_story", "gilbert_story", "gimlee_story", "golbez_story", "hayate_story", "kain_story", "kefka_story",
                "kenyu_story", "kinggiott_story", "krile_story", "kuja_story", "lasswell_story", "leah_story", "leo_story", "liza_story", "locke_story", "luna_story",
                "magitekarmorterra_story", "maria_story", "maxell_story", "medius_story", "mel_story", "miyuki_story", "mizell_story", "montana_story", "ollie_story", "paul_story",
                "paula_story", "penelo_story", "rain_story", "rakshasa_story", "rizer_story", "ronaldo_story", "roselia_story", "russell_story", "rydia_story", "sabin_story",
                "sarah_story", "sarai_story", "shadow_story", "shantotto_story", "shiki_story", "skaha_story", "terra_story", "tronn_story", "vaan_story", "vivi_story",
                "wedge_story", "xiao_story", "zidane_story"};

        String[] heroIcons = {"ic_anastasis", "ic_anzelm", "ic_artemios", "ic_bartz", "ic_baurg", "ic_bedile", "ic_biggs", "ic_carrie", "ic_cecil", "ic_celes",
                "ic_cerius", "ic_chizuru", "ic_cloud_of_darkness", "ic_clyne", "ic_cyan", "ic_duane", "ic_edgar", "ic_eldin", "ic_exdeath", "ic_fina",
                "ic_firion", "ic_fran", "ic_galuf", "ic_garland", "ic_gilbert", "ic_gimlee", "ic_golbez", "ic_hayate", "ic_kain", "ic_kefka",
                "ic_kenyu", "ic_king_giott", "ic_krile", "ic_kuja", "ic_lasswell", "ic_leah", "ic_leo", "ic_liza", "ic_locke", "ic_luna",
                "ic_magitek_armor_terra", "ic_maria", "ic_maxell", "ic_medius", "ic_mel", "ic_miyuki", "ic_mizell", "ic_montana", "ic_ollie", "ic_paul",
                "ic_paula", "ic_penelo", "ic_rain", "ic_rakshasa", "ic_rizer", "ic_ronaldo", "ic_roselia", "ic_russell", "ic_rydia", "ic_sabin",
                "ic_sarah", "ic_sarai", "ic_shadow", "ic_shantotto", "ic_shiki", "ic_skaha", "ic_terra", "ic_tronn", "ic_vaan", "ic_vivi",
                "ic_wedge", "ic_xiao", "ic_zidane"};

        String[] heroPrice = {"4.99", "9.99", "19.99", "49.99", "1.99", "9.99", "4.99", "4.99", "79.99", "49.99",
                "19.99", "79.99", "49.99", "9.99", "9.99", "19.99", "9.99", "1.99", "79.99", "99.99",
                "49.99", "9.99", "9.99", "19.99", "19.99", "1.99", "49.99", "19.99", "9.99", "79.99",
                "4.99", "4.99", "19.99", "19.99", "99.99", "1.99", "79.99", "1.99", "49.99", "9.99",
                "9.99", "9.99", "1.99", "19.99", "4.99", "19.99", "4.99", "4.99", "4.99", "4.99",
                "4.99", "9.99", "99.99", "19.99", "1.99", "4.99", "79.99", "9.99", "9.99", "9.99",
                "4.99", "1.99", "9.99", "9.99", "4.99", "4.99", "49.99", "1.99", "79.99", "9.99",
                "4.99", "19.99", "79.99"};

        String[] trustMasteryName = {"Regen", "Drain", "Equip Bow", "Doublehand", "Store", "Thundaga Blade", "Blizzard Beam", "Sunbeam", "Excalibur", "Minerva Bustier",
                "Miracle Shoes", "Blade Mastery", "Auto-Limit", "Cover", "Evade", "Bioga Blade", "Machine Killer", "Pilfer", "Holy", "n/a",
                "Equip H Armor", "Cleanse", "Comet", "Equip L Sword", "Dream Harp", "Aim", "Meteor", "Black Cowl", "Gungnir", "Ribbon",
                "Raging Fist", "Stone Killer", "Thundaga", "Flare", "n/a", "Banish", "Aegis Shield", "Lullaby", "Rising Sun", "Barrage",
                "n/a", "Esuna", "Power Shot", "Growth Egg", "Drink", "Sakurafubuki", "Deprotect", "Camouflage", "Aquan Killer", "Escape",
                "Stonra", "Equip S Sword", "n/a", "Holy Blade", "HP +10%", "Drain Blade", "Equip Staff", "Bladeblitz", "Blizzaga", "Counter",
                "Paean", "Silence Dance", "Throw", "MAG +30%", "Tranquility", "Dragon Killer", "Ultima", "Fira", "Maximillian", "Firaga",
                "Fire Beam", "Kaiser Knuckles", "Dual Wield"};

        String[] trustMasteryDescription = {"tm_info_regen", "tm_info_drain", "tm_info_equipbow", "tm_info_doublehand", "tm_info_store", "tm_info_thundagablade", "tm_info_blizzardbeam", "tm_info_sunbeam", "tm_info_excalibur", "tm_info_minervabustier",
                "tm_info_miracleshoes", "tm_info_blademastery", "tm_info_autolimit", "tm_info_cover", "tm_info_evade", "tm_info_biogablade", "tm_info_machinekiller", "tm_info_pilfer", "tm_info_holy", "n/a",
                "tm_info_equipharmor", "tm_info_cleanse", "tm_info_comet", "tm_info_equiplsword", "tm_info_dreamharp", "tm_info_aim", "tm_info_meteor", "tm_info_blackcowl", "tm_info_gungnir", "tm_info_ribbon",
                "tm_info_ragingfist", "tm_info_stonekiller", "tm_info_thundaga", "tm_info_flare", "n/a", "tm_info_banish", "tm_info_aegisshield", "tm_info_lullaby", "tm_info_risingsun", "tm_info_barrage",
                "n/a", "tm_info_esuna", "tm_info_powershot", "tm_info_growthegg", "tm_info_drink", "tm_info_sakurafubuki", "tm_info_deprotect", "tm_info_camouflage", "tm_info_aquankiller", "tm_info_escape",
                "tm_info_stonra", "tm_info_equipssword", "n/a", "tm_info_holyblade", "tm_info_hp10", "tm_info_drainblade", "tm_info_equipstaff", "tm_info_bladeblitz", "tm_info_blizzaga", "tm_info_counter",
                "tm_info_paean", "tm_info_silencedance", "tm_info_throw", "tm_info_mag30", "tm_info_tranquility", "tm_info_dragonkiller", "tm_info_ultima", "tm_info_fira", "tm_info_maximillian", "tm_info_firaga",
                "tm_info_firebeam", "tm_info_kaiserknuckles", "tm_info_dualwield"};

        String[] trustMasteryIcons = {"tm_regen", "tm_drain", "tm_equip_bow", "tm_doublehand", "tm_store", "tm_thundagablade", "tm_blizzardbeam", "tm_sunbeam", "tm_excalibur", "tm_minervabustier",
                "tm_miracleshoes", "tm_blademastery", "tm_autolimit", "tm_cover", "tm_evade", "tm_biogablade", "tm_machinekiller", "tm_pilfer", "tm_holy", "n/a",
                "tm_equipharmor", "tm_cleanse", "tm_comet", "tm_equiplsword", "tm_dreamharp", "tm_aim", "tm_meteor", "tm_blackcowl", "tm_gungnir", "tm_ribbon",
                "tm_ragingfist", "tm_stonekiller", "tm_thundaga", "tm_flare", "n/a", "tm_banish", "tm_aegisshield", "tm_lullaby", "tm_risingsun", "tm_barrage",
                "n/a", "tm_esuna", "tm_powershot", "tm_growthegg", "tm_drink", "tm_sakurafubuki", "tm_deprotect", "tm_camouflage", "tm_aquankiller", "tm_escape",
                "tm_stonra", "tm_equipssword", "n/a", "tm_holyblade", "tm_hp10", "tm_drainblade", "tm_equipstaff", "tm_bladeblitz", "tm_blizzaga", "tm_counter",
                "tm_paean", "tm_silencedance", "tm_throw", "tm_mag30", "tm_tranquility", "tm_dragonkiller", "tm_ultima", "tm_fira", "tm_maximillian", "tm_firaga",
                "tm_firebeam", "tm_kaiserknuckles", "tm_dualwield"};


        // Hero Table
        for (int i = 0; i < ids.length; i++) {
            values.put(COL_ID, ids[i]);
            values.put(COL_HERO_NAME, heroNames[i]);
            values.put(COL_HERO_ORIGIN, heroOrigins[i]);
            values.put(COL_HERO_ROLE, heroRoles[i]);
            values.put(COL_HERO_JOB, heroJobs[i]);
            values.put(COL_HERO_RARITY, heroRarity[i]);
            values.put(COL_HERO_RACE, heroRace[i]);
            values.put(COL_HERO_GENDER, heroGenders[i]);
            values.put(COL_HERO_BACK_STORY, heroStory[i]);
            values.put(COL_HERO_ICON, heroIcons[i]);
            values.put(COL_HERO_REVIEW_RATING, "5.0"); // starts at 5.0 rating
            values.put(COL_HERO_TOTAL_REVIEWS, 0); // starts at 0
            values.put(COL_HERO_PRICE, heroPrice[i]);
            values.put(COL_HERO_NUMBER_IN_CART, 0); // starts at 0
            database.insert(TABLE_HERO, null, values);
            values.clear();
        }

        // Trust Mastery Table
        for (int i = 0; i < ids.length; i++) {
            values.put(COL_ID, ids[i]);
            values.put(COL_TRUST_MASTERY_NAME, trustMasteryName[i]);
            values.put(COL_TRUST_MASTERY_INFO, trustMasteryDescription[i]);
            values.put(COL_TRUST_MASTERY_ICON, trustMasteryIcons[i]);
            values.put(COL_TRUST_MASTERY_HERO_NAME, heroNames[i]);
            database.insert(TABLE_TRUST_MASTERY, null, values);
            values.clear();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int i, int i1) {
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_HERO);
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_TRUST_MASTERY);
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_REVIEW);
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_SHOPPING_CART);
        onCreate(database);
    }


    public ArrayList<Hero> getAllHeroes() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = null;
        ArrayList<Hero> heroes = new ArrayList<>();

        String query = "SELECT * FROM " + TABLE_HERO;
        cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            while(!cursor.isAfterLast()) {
                heroes.add(new Hero(cursor.getInt(cursor.getColumnIndex(COL_ID)), cursor.getString(cursor.getColumnIndex(COL_HERO_NAME)),
                        cursor.getString(cursor.getColumnIndex(COL_HERO_ORIGIN)), cursor.getString(cursor.getColumnIndex(COL_HERO_ROLE)),
                        cursor.getString(cursor.getColumnIndex(COL_HERO_JOB)), cursor.getInt(cursor.getColumnIndex(COL_HERO_RARITY)),
                        cursor.getString(cursor.getColumnIndex(COL_HERO_RACE)), cursor.getString(cursor.getColumnIndex(COL_HERO_GENDER)),
                        cursor.getString(cursor.getColumnIndex(COL_HERO_BACK_STORY)), cursor.getString(cursor.getColumnIndex(COL_HERO_ICON)),
                        cursor.getString(cursor.getColumnIndex(COL_HERO_REVIEW_RATING)), cursor.getInt(cursor.getColumnIndex(COL_HERO_TOTAL_REVIEWS)),
                        cursor.getString(cursor.getColumnIndex(COL_HERO_PRICE)), cursor.getInt(cursor.getColumnIndex(COL_HERO_NUMBER_IN_CART))));

                cursor.moveToNext();
            }
        }

        cursor.close();
        db.close();
        return heroes;
    }

    public ArrayList<Hero> getAllShoppingCartHeroes() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = null;
        ArrayList<Hero> heroes = new ArrayList<>();



        String query = "SELECT * FROM " + TABLE_HERO + " JOIN " + TABLE_SHOPPING_CART + " ON " + TABLE_HERO + "." + COL_HERO_NAME
                + " = " + TABLE_SHOPPING_CART + "." + COL_SHOPPING_CART_HERO_NAME;
        cursor = db.rawQuery(query, null, null);
        if (cursor.moveToFirst()) {
            while(!cursor.isAfterLast()) {
                heroes.add(new Hero(cursor.getInt(cursor.getColumnIndex(COL_ID)), cursor.getString(cursor.getColumnIndex(COL_HERO_NAME)),
                        cursor.getString(cursor.getColumnIndex(COL_HERO_ORIGIN)), cursor.getString(cursor.getColumnIndex(COL_HERO_ROLE)),
                        cursor.getString(cursor.getColumnIndex(COL_HERO_JOB)), cursor.getInt(cursor.getColumnIndex(COL_HERO_RARITY)),
                        cursor.getString(cursor.getColumnIndex(COL_HERO_RACE)), cursor.getString(cursor.getColumnIndex(COL_HERO_GENDER)),
                        cursor.getString(cursor.getColumnIndex(COL_HERO_BACK_STORY)), cursor.getString(cursor.getColumnIndex(COL_HERO_ICON)),
                        cursor.getString(cursor.getColumnIndex(COL_HERO_REVIEW_RATING)), cursor.getInt(cursor.getColumnIndex(COL_HERO_TOTAL_REVIEWS)),
                        cursor.getString(cursor.getColumnIndex(COL_HERO_PRICE)), cursor.getInt(cursor.getColumnIndex(COL_SHOPPING_CART_ITEM_QUANTITY))));

                cursor.moveToNext();
            }
        }




        cursor.close();
        db.close();
        return heroes;
    }

    public ArrayList<TrustMastery> getAllTrustMasteries() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = null;
        ArrayList<TrustMastery> masteries = new ArrayList<>();

        String query = "SELECT * FROM " + TABLE_TRUST_MASTERY;
        cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            while(!cursor.isAfterLast()) {
                masteries.add(new TrustMastery(cursor.getInt(cursor.getColumnIndex(COL_ID)),
                        cursor.getString(cursor.getColumnIndex(COL_TRUST_MASTERY_NAME)),
                        cursor.getString(cursor.getColumnIndex(COL_TRUST_MASTERY_INFO)),
                        cursor.getString(cursor.getColumnIndex(COL_TRUST_MASTERY_ICON)),
                        cursor.getString(cursor.getColumnIndex(COL_TRUST_MASTERY_HERO_NAME))));

                cursor.moveToNext();
            }
        }

        cursor.close();
        db.close();
        return masteries;
    }

    public ArrayList<Review> getAllReviews() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = null;
        ArrayList<Review> reviews = new ArrayList<>();

        String query = "SELECT * FROM " + TABLE_REVIEW;
        cursor = db.rawQuery(query, null, null);

        if (cursor.moveToFirst()) {
            while(!cursor.isAfterLast()) {
                reviews.add(new Review(cursor.getInt(cursor.getColumnIndex(COL_ID)), cursor.getString(cursor.getColumnIndex(COL_REVIEW_USER_NAME)),
                        cursor.getString(cursor.getColumnIndex(COL_REVIEW_USER_COMMENT)), cursor.getString(cursor.getColumnIndex(COL_REVIEW_USER_RATING)),
                        cursor.getString(cursor.getColumnIndex(COL_REVIEW_HERO_NAME))));

                cursor.moveToNext();
            }
        }

        cursor.close();
        db.close();
        return reviews;
    }

    public TrustMastery getTrustMastery(String heroName) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = null;
        TrustMastery mastery = null;

        cursor = db.query(TABLE_TRUST_MASTERY, null, COL_TRUST_MASTERY_HERO_NAME + " =?", new String[]{heroName}, null, null, null);
        if (cursor.moveToFirst()) {
            mastery = new TrustMastery(cursor.getInt(cursor.getColumnIndex(COL_ID)),
                    cursor.getString(cursor.getColumnIndex(COL_TRUST_MASTERY_NAME)),
                    cursor.getString(cursor.getColumnIndex(COL_TRUST_MASTERY_INFO)),
                    cursor.getString(cursor.getColumnIndex(COL_TRUST_MASTERY_ICON)),
                    cursor.getString(cursor.getColumnIndex(COL_TRUST_MASTERY_HERO_NAME)));
        }

        cursor.close();
        db.close();
        return mastery;
    }

    public ArrayList<Review> getHeroReviews(String heroName) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = null;
        ArrayList<Review> reviews = new ArrayList<>();

        cursor = db.query(TABLE_REVIEW, null, COL_REVIEW_HERO_NAME + " =?", new String[]{heroName}, null, null, null);
        if (cursor.moveToFirst()) {
            while(!cursor.isAfterLast()) {
                reviews.add(new Review(cursor.getInt(cursor.getColumnIndex(COL_ID)), cursor.getString(cursor.getColumnIndex(COL_REVIEW_USER_NAME)),
                        cursor.getString(cursor.getColumnIndex(COL_REVIEW_USER_COMMENT)), cursor.getString(cursor.getColumnIndex(COL_REVIEW_USER_RATING)),
                        cursor.getString(cursor.getColumnIndex(COL_REVIEW_HERO_NAME))));
                cursor.moveToNext();
            }
        }

        cursor.close();
        db.close();
        return reviews;
    }

    public void addHeroReview(int id, String userName, String userComment, String userRating, String heroName) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_ID, id);
        values.put(COL_REVIEW_USER_NAME, userName);
        values.put(COL_REVIEW_USER_COMMENT, userComment);
        values.put(COL_REVIEW_USER_RATING, userRating);
        values.put(COL_REVIEW_HERO_NAME, heroName);
        db.insert(TABLE_REVIEW, null, values);

        db.close();
    }

    public int getTotalItemsInCart() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = null;
        int total = 0;

        String query = "SELECT * FROM " + TABLE_SHOPPING_CART;
        cursor = db.rawQuery(query, null, null);
        if (cursor.moveToFirst()) {
            while(!cursor.isAfterLast()) {
                total += cursor.getInt(cursor.getColumnIndex(COL_SHOPPING_CART_ITEM_QUANTITY));
                cursor.moveToNext();
            }
        }

        cursor.close();
        db.close();
        return total;
    }

    public String getTotalCost() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = null;
        Double totalCost = 0.0;

//        String query = "SELECT " + COL_HERO_PRICE + "," + COL_HERO_NUMBER_IN_CART + " FROM " + TABLE_HERO
//                + " WHERE CAST(" + COL_HERO_NUMBER_IN_CART + " AS INTEGER) > 0";
        String query = "SELECT " + COL_HERO_PRICE + "," + COL_SHOPPING_CART_ITEM_QUANTITY + " FROM " + TABLE_HERO + " JOIN "
                + TABLE_SHOPPING_CART + " ON " + TABLE_HERO + "." + COL_HERO_NAME + " = " + TABLE_SHOPPING_CART + "."
                + COL_SHOPPING_CART_HERO_NAME;
        cursor = db.rawQuery(query, null, null);
        if (cursor.moveToFirst()) {
            while(!cursor.isAfterLast()) {
//                totalCost += (double) cursor.getInt(cursor.getColumnIndex(COL_HERO_NUMBER_IN_CART))
//                        * Double.parseDouble(cursor.getString(cursor.getColumnIndex(COL_HERO_PRICE)));
                totalCost += ((double) cursor.getInt(cursor.getColumnIndex(COL_SHOPPING_CART_ITEM_QUANTITY)))
                        * Double.parseDouble(cursor.getString(cursor.getColumnIndex(COL_HERO_PRICE)));
                cursor.moveToNext();
            }
        }

        cursor.close();
        db.close();
        return String.format("%.2f", totalCost);
    }

    public ArrayList<Hero> getUpdatedHeroSearch(String search) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = null;
        ArrayList<Hero> heroes = new ArrayList<>();
        String query = "";

        if (search.length() == 0) {
            query = "SELECT * FROM " + TABLE_HERO;
        } else {
            query = "SELECT * FROM " + TABLE_HERO + " WHERE " + COL_HERO_NAME + " LIKE '" + search + "%'";
        }

        cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            while(!cursor.isAfterLast()) {
                heroes.add(new Hero(cursor.getInt(cursor.getColumnIndex(COL_ID)), cursor.getString(cursor.getColumnIndex(COL_HERO_NAME)),
                        cursor.getString(cursor.getColumnIndex(COL_HERO_ORIGIN)), cursor.getString(cursor.getColumnIndex(COL_HERO_ROLE)),
                        cursor.getString(cursor.getColumnIndex(COL_HERO_JOB)), cursor.getInt(cursor.getColumnIndex(COL_HERO_RARITY)),
                        cursor.getString(cursor.getColumnIndex(COL_HERO_RACE)), cursor.getString(cursor.getColumnIndex(COL_HERO_GENDER)),
                        cursor.getString(cursor.getColumnIndex(COL_HERO_BACK_STORY)), cursor.getString(cursor.getColumnIndex(COL_HERO_ICON)),
                        cursor.getString(cursor.getColumnIndex(COL_HERO_REVIEW_RATING)), cursor.getInt(cursor.getColumnIndex(COL_HERO_TOTAL_REVIEWS)),
                        cursor.getString(cursor.getColumnIndex(COL_HERO_PRICE)), cursor.getInt(cursor.getColumnIndex(COL_HERO_NUMBER_IN_CART))));

                cursor.moveToNext();
            }
        }

        cursor.close();
        db.close();
        return heroes;
    }

    public Hero getSingleHero(String name) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = null;
        Hero hero = null;

        cursor = db.query(TABLE_HERO, null, COL_HERO_NAME + " =?", new String[]{name}, null, null, null);
        if (cursor.moveToFirst()) {
            hero = new Hero(cursor.getInt(cursor.getColumnIndex(COL_ID)), cursor.getString(cursor.getColumnIndex(COL_HERO_NAME)),
                    cursor.getString(cursor.getColumnIndex(COL_HERO_ORIGIN)), cursor.getString(cursor.getColumnIndex(COL_HERO_ROLE)),
                    cursor.getString(cursor.getColumnIndex(COL_HERO_JOB)), cursor.getInt(cursor.getColumnIndex(COL_HERO_RARITY)),
                    cursor.getString(cursor.getColumnIndex(COL_HERO_RACE)), cursor.getString(cursor.getColumnIndex(COL_HERO_GENDER)),
                    cursor.getString(cursor.getColumnIndex(COL_HERO_BACK_STORY)), cursor.getString(cursor.getColumnIndex(COL_HERO_ICON)),
                    cursor.getString(cursor.getColumnIndex(COL_HERO_REVIEW_RATING)), cursor.getInt(cursor.getColumnIndex(COL_HERO_TOTAL_REVIEWS)),
                    cursor.getString(cursor.getColumnIndex(COL_HERO_PRICE)), cursor.getInt(cursor.getColumnIndex(COL_HERO_NUMBER_IN_CART)));
        }

        cursor.close();
        db.close();
        return hero;
    }

    public void addHeroToCart(String heroName) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_SHOPPING_CART_HERO_NAME, heroName);
        values.put(COL_SHOPPING_CART_ITEM_QUANTITY, 1);
        db.insert(TABLE_SHOPPING_CART, null, values);

        db.close();
    }

    public void updateHeroQuantity(String heroName, int quantity) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_SHOPPING_CART_ITEM_QUANTITY, quantity);
        db.update(TABLE_SHOPPING_CART, values, COL_SHOPPING_CART_HERO_NAME + " =?", new String[]{heroName});

        db.close();
    }

    public void removeHeroFromCart(String heroName) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SHOPPING_CART, COL_SHOPPING_CART_HERO_NAME + " =?", new String[]{heroName});
        db.close();
    }

    public void emptyShoppingCartTable() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SHOPPING_CART, null, null);
        db.close();
    }

    public ArrayList<Hero> getFilteredHeroList(String originIndex, String roleIndex, String jobIndex, String rarityIndex, String raceIndex, String genderIndex) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = null;
        ArrayList<Hero> heroes = new ArrayList<>();
        String query = "";

        if (originIndex.equals("ANY")) {originIndex = "";}
        if (roleIndex.equals("ANY")) {roleIndex = "";}
        if (jobIndex.equals("ANY")) {jobIndex = "";}
        if (rarityIndex.equals("ANY")) {rarityIndex = "";}
        if (raceIndex.equals("ANY")) {raceIndex = "";}
        if (genderIndex.equals("ANY")) {genderIndex = "";}

        query = "SELECT * FROM " + TABLE_HERO + " WHERE " + COL_HERO_ORIGIN + " LIKE '%" + originIndex + "%' AND "
                + COL_HERO_ROLE + " LIKE '%" + roleIndex + "%' AND "
                + COL_HERO_JOB + " LIKE '%" + jobIndex + "%' AND "
                + COL_HERO_RARITY + " LIKE '%" + rarityIndex + "%' AND "
                + COL_HERO_RACE + " LIKE '%" + raceIndex + "%' AND "
                + COL_HERO_GENDER + " LIKE '%" + genderIndex + "%'";

        cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            while(!cursor.isAfterLast()) {
                heroes.add(new Hero(cursor.getInt(cursor.getColumnIndex(COL_ID)), cursor.getString(cursor.getColumnIndex(COL_HERO_NAME)),
                        cursor.getString(cursor.getColumnIndex(COL_HERO_ORIGIN)), cursor.getString(cursor.getColumnIndex(COL_HERO_ROLE)),
                        cursor.getString(cursor.getColumnIndex(COL_HERO_JOB)), cursor.getInt(cursor.getColumnIndex(COL_HERO_RARITY)),
                        cursor.getString(cursor.getColumnIndex(COL_HERO_RACE)), cursor.getString(cursor.getColumnIndex(COL_HERO_GENDER)),
                        cursor.getString(cursor.getColumnIndex(COL_HERO_BACK_STORY)), cursor.getString(cursor.getColumnIndex(COL_HERO_ICON)),
                        cursor.getString(cursor.getColumnIndex(COL_HERO_REVIEW_RATING)), cursor.getInt(cursor.getColumnIndex(COL_HERO_TOTAL_REVIEWS)),
                        cursor.getString(cursor.getColumnIndex(COL_HERO_PRICE)), cursor.getInt(cursor.getColumnIndex(COL_HERO_NUMBER_IN_CART))));

                cursor.moveToNext();
            }
        }

        cursor.close();
        db.close();
        return heroes;
    }









}
