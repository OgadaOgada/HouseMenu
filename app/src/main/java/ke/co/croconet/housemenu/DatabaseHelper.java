package ke.co.croconet.housemenu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.WeakHashMap;
//import android.support.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "MealMenuDatabase";

    public static final String monBreakfastTable = "mondayBreakfastTable";
    private static final String monbrtable_col1 = "ID";
    private static final String monbrtable_col2 = "Breakfast";
    private static final String monbrtable_col3 = "BrNutrition";

    private static final String monLunchTable = "mondayLunchTable";
    private static final String monlntable_col1 = "ID";
    private static final String monlntable_col2 = "Lunch";
    private static final String monlntable_col3 = "LnNutrition";

    private static final String monSupperTable = "mondaySupperTable";
    private static final String monsptable_col1 = "ID";
    private static final String monsptable_col2 = "Supper";
    private static final String monsptable_col3 = "SpNutrition";

    private static final String tueBreakfastTable = "tuesdayBreakfastTable";
    private static final String tuebrtable_col1 = "ID";
    private static final String tuebrtable_col2 = "Breakfast";
    private static final String tuebrtable_col3 = "BrNutrition";

    private static final String tueLunchTable = "tuesdayLunchTable";
    private static final String tuelntable_col1 = "ID";
    private static final String tuelntable_col2 = "Lunch";
    private static final String tuelntable_col3 = "LnNutrition";

    private static final String tueSupperTable = "tuesdaySupperTable";
    private static final String tuesptable_col1 = "ID";
    private static final String tuesptable_col2 = "Supper";
    private static final String tuesptable_col3 = "SpNutrition";

    private static final String wedBreakfastTable = "wednesdayBreakfastTable";
    private static final String wedbrtable_col1 = "ID";
    private static final String wedbrtable_col2 = "Breakfast";
    private static final String wedbrtable_col3 = "BrNutrition";

    private static final String wedLunchTable = "wednesdayLunchTable";
    private static final String wedlntable_col1 = "ID";
    private static final String wedlntable_col2 = "Lunch";
    private static final String wedlntable_col3 = "SnNutrition";

    private static final String wedSupperTable = "wednesdaySupperTable";
    private static final String wedsptable_col1 = "ID";
    private static final String wedsptable_col2 = "Supper";
    private static final String wedsptable_col3 = "SpNutrition";

    private static final String thurBreakfastTable = "thursdayBreakfastTable";
    private static final String thurbrtable_col1 = "ID";
    private static final String thurbrtable_col2 = "Breakfast";
    private static final String thurbrtable_col3 = "BrNutrition";

    private static final String thurLunchTable = "thursdayLunchTable";
    private static final String thurlntable_col1 = "ID";
    private static final String thurlntable_col2 = "Lunch";
    private static final String thurlntable_col3 = "SnNutrition";

    private static final String thurSupperTable = "thursdaySupperTable";
    private static final String thursptable_col1 = "ID";
    private static final String thursptable_col2 = "Supper";
    private static final String thursptable_col3 = "SpNutrition";

    private static final String friBreakfastTable = "fridayBreakfastTable";
    private static final String fribrtable_col1 = "ID";
    private static final String fribrtable_col2 = "Breakfast";
    private static final String fribrtable_col3 = "BrNutrition";

    private static final String friLunchTable = "fridayLunchTable";
    private static final String frilntable_col1 = "ID";
    private static final String frilntable_col2 = "Lunch";
    private static final String frilntable_col3 = "SnNutrition";

    private static final String friSupperTable = "fridaySupperTable";
    private static final String frisptable_col1 = "ID";
    private static final String frisptable_col2 = "Supper";
    private static final String frisptable_col3 = "SpNutrition";

    private static final String satBreakfastTable = "saturdayBreakfastTable";
    private static final String satbrtable_col1 = "ID";
    private static final String satbrtable_col2 = "Breakfast";
    private static final String satbrtable_col3 = "BrNutrition";

    private static final String satLunchTable = "saturdayLunchTable";
    private static final String satlntable_col1 = "ID";
    private static final String satlntable_col2 = "Lunch";
    private static final String satlntable_col3 = "SnNutrition";

    private static final String satSupperTable = "saturdaySupperTable";
    private static final String satsptable_col1 = "ID";
    private static final String satsptable_col2 = "Supper";
    private static final String satsptable_col3 = "SpNutrition";

    private static final String sunBreakfastTable = "sundayBreakfastTable";
    private static final String sunbrtable_col1 = "ID";
    private static final String sunbrtable_col2 = "Breakfast";
    private static final String sunbrtable_col3 = "BrNutrition";

    private static final String sunLunchTable = "sundayLunchTable";
    private static final String sunlntable_col1 = "ID";
    private static final String sunlntable_col2 = "Lunch";
    private static final String sunlntable_col3 = "SnNutrition";

    private static final String sunSupperTable = "sundaySupperTable";
    private static final String sunsptable_col1 = "ID";
    private static final String sunsptable_col2 = "Supper";
    private static final String sunsptable_col3 = "SpNutrition";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    //CREATING THE TABLE
    @Override
    public void onCreate(SQLiteDatabase db) {

        String createMonBreakfastTable = "CREATE TABLE "+monBreakfastTable+"("+monbrtable_col1+" INTEGER PRIMARY KEY , "+monbrtable_col2+"" +
                " TEXT NOT NULL, "+monbrtable_col3+" TEXT NOT NULL)";
        String createMonLunchTable = "CREATE TABLE "+monLunchTable+"("+monlntable_col1+" INTEGER PRIMARY KEY , "+monlntable_col2
                +" TEXT NOT NULL, "+monlntable_col3+" TEXT NOT NULL)";
        String createMonSupperTable = "CREATE TABLE "+monSupperTable+"("+monsptable_col1+" INTEGER PRIMARY KEY , "+monsptable_col2
                +" TEXT NOT NULL, "+monsptable_col3+" TEXT NOT NULL)";

        String createTueBreakfastTable = "CREATE TABLE "+tueBreakfastTable+"("+tuebrtable_col1+" INTEGER PRIMARY KEY , "+tuebrtable_col2
                +" TEXT  NOT NULL, "+tuebrtable_col3+" TEXT NOT NULL)";
        String createTueLunchTable = "CREATE TABLE "+tueLunchTable+"("+tuelntable_col1+" INTEGER PRIMARY KEY , "+tuelntable_col2
                +" TEXT  NOT NULL, "+tuelntable_col3+" TEXT NOT NULL)";
        String createTueSupperTable = "CREATE TABLE "+tueSupperTable+"("+tuesptable_col1+" INTEGER PRIMARY KEY , "+tuesptable_col2
                +" TEXT NOT NULL, "+tuesptable_col3+" TEXT NOT NULL)";

        String createWedBreakfastTable = "CREATE TABLE "+wedBreakfastTable+"("+wedbrtable_col1+" INTEGER PRIMARY KEY , "+wedbrtable_col2
                +" TEXT NOT NULL, "+wedbrtable_col3+" TEXT NOT NULL)";
        String createWedLunchTable = "CREATE TABLE "+wedLunchTable+"("+wedlntable_col1+" INTEGER PRIMARY KEY , "+wedlntable_col2
                +" TEXT NOT NULL, "+wedlntable_col3+" TEXT NOT NULL)";
        String createWedSupperTable = "CREATE TABLE "+wedSupperTable+"("+wedsptable_col1+" INTEGER PRIMARY KEY , "+wedsptable_col2
                +" TEXT NOT NULL, "+wedsptable_col3+" TEXT NOT NULL)";

        String createThurBreakfastTable = "CREATE TABLE "+thurBreakfastTable+"("+thurbrtable_col1+" INTEGER PRIMARY KEY , "+thurbrtable_col2
                +" TEXT NOT NULL, "+thurbrtable_col3+" TEXT NOT NULL)";
        String createThurLunchTable = "CREATE TABLE "+thurLunchTable+"("+thurlntable_col1+" INTEGER PRIMARY KEY , "+thurlntable_col2
                +" TEXT NOT NULL, "+thurlntable_col3+" TEXT NOT NULL)";
        String createThurSupperTable = "CREATE TABLE "+thurSupperTable+"("+thursptable_col1+" INTEGER PRIMARY KEY , "+thursptable_col2
                +" TEXT NOT NULL, "+thursptable_col3+" TEXT NOT NULL)";

        String createFriBreakfastTable = "CREATE TABLE "+friBreakfastTable+"("+fribrtable_col1+" INTEGER PRIMARY KEY , "+fribrtable_col2
                +" TEXT NOT NULL, "+fribrtable_col3+" TEXT NOT NULL)";
        String createFriLunchTable = "CREATE TABLE "+friLunchTable+"("+frilntable_col1+" INTEGER PRIMARY KEY , "+frilntable_col2
                +" TEXT NOT NULL, "+frilntable_col3+" TEXT NOT NULL)";
        String createFriSupperTable = "CREATE TABLE "+friSupperTable+"("+frisptable_col1+" INTEGER PRIMARY KEY , "+frisptable_col2
                +" TEXT NOT NULL, "+frisptable_col3+" TEXT NOT NULL)";

        String createSatBreakfastTable = "CREATE TABLE "+satBreakfastTable+"("+satbrtable_col1+" INTEGER PRIMARY KEY , "+satbrtable_col2
                +" TEXT NOT NULL, "+satbrtable_col3+" TEXT NOT NULL)";
        String createSatLunchTable = "CREATE TABLE "+satLunchTable+"("+satlntable_col1+" INTEGER PRIMARY KEY , "+satlntable_col2
                +" TEXT NOT NULL, "+satlntable_col3+" TEXT NOT NULL)";
        String createSatSupperTable = "CREATE TABLE "+satSupperTable+"("+satsptable_col1+" INTEGER PRIMARY KEY , "+satsptable_col2
                +" TEXT NOT NULL, "+satsptable_col3+" TEXT NOT NULL)";

        String createSunBreakfastTable = "CREATE TABLE "+sunBreakfastTable+"("+sunbrtable_col1+" INTEGER PRIMARY KEY , "+sunbrtable_col2
                +" TEXT NOT NULL, "+sunbrtable_col3+" TEXT NOT NULL)";
        String createSunLunchTable = "CREATE TABLE "+sunLunchTable+"("+sunlntable_col1+" INTEGER PRIMARY KEY , "+sunlntable_col2
                +" TEXT NOT NULL, "+sunlntable_col3+" TEXT NOT NULL)";
        String createSunSupperTable = "CREATE TABLE "+sunSupperTable+"("+sunsptable_col1+" INTEGER PRIMARY KEY , "+sunsptable_col2
                +" TEXT NOT NULL, "+sunsptable_col3+" TEXT NOT NULL)";

        db.execSQL(createMonBreakfastTable);
        db.execSQL(createMonLunchTable);
        db.execSQL(createMonSupperTable);

        db.execSQL(createTueBreakfastTable);
        db.execSQL(createTueLunchTable);
        db.execSQL(createTueSupperTable);

        db.execSQL(createWedBreakfastTable);
        db.execSQL(createWedLunchTable);
        db.execSQL(createWedSupperTable);

        db.execSQL(createThurBreakfastTable);
        db.execSQL(createThurLunchTable);
        db.execSQL(createThurSupperTable);

        db.execSQL(createFriBreakfastTable);
        db.execSQL(createFriLunchTable);
        db.execSQL(createFriSupperTable);

        db.execSQL(createSatBreakfastTable);
        db.execSQL(createSatLunchTable);
        db.execSQL(createSatSupperTable);

        db.execSQL(createSunBreakfastTable);
        db.execSQL(createSunLunchTable);
        db.execSQL(createSunSupperTable);
    }

    //UPGRADING AND CREATING A FRESH
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + monBreakfastTable);
        db.execSQL("DROP TABLE IF EXISTS " + monLunchTable);
        db.execSQL("DROP TABLE IF EXISTS " + monSupperTable);

        db.execSQL("DROP TABLE IF EXISTS " + tueBreakfastTable);
        db.execSQL("DROP TABLE IF EXISTS " + tueLunchTable);
        db.execSQL("DROP TABLE IF EXISTS " + tueSupperTable);

        db.execSQL("DROP TABLE IF EXISTS " + wedBreakfastTable);
        db.execSQL("DROP TABLE IF EXISTS " + wedLunchTable);
        db.execSQL("DROP TABLE IF EXISTS " + wedSupperTable);

        db.execSQL("DROP TABLE IF EXISTS " + thurBreakfastTable);
        db.execSQL("DROP TABLE IF EXISTS " + thurLunchTable);
        db.execSQL("DROP TABLE IF EXISTS " + thurSupperTable);

        db.execSQL("DROP TABLE IF EXISTS " + friBreakfastTable);
        db.execSQL("DROP TABLE IF EXISTS " + friLunchTable);
        db.execSQL("DROP TABLE IF EXISTS " + friSupperTable);

        db.execSQL("DROP TABLE IF EXISTS " + satBreakfastTable);
        db.execSQL("DROP TABLE IF EXISTS " + satLunchTable);
        db.execSQL("DROP TABLE IF EXISTS " + satSupperTable);

        db.execSQL("DROP TABLE IF EXISTS " + sunBreakfastTable);
        db.execSQL("DROP TABLE IF EXISTS " + sunLunchTable);
        db.execSQL("DROP TABLE IF EXISTS " + sunSupperTable);
        onCreate(db);
    }
    //INSERTING DATA INTO MONDAY TABLE
   public boolean insertMonBreakfastData(String breakfast, String breakfastNutrition) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

            contentValues.put(monbrtable_col2, breakfast);
            contentValues.put(monbrtable_col3, breakfastNutrition);
            //
            long result = db.insert(monBreakfastTable, null, contentValues);
            db.close();
            if(result==-1){
                return false;
            }
            else{
                return true;
            }
    }
   public boolean insertMonLunchData(String lunch, String lunchNutrition) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

            contentValues.put(monlntable_col2, lunch);
            contentValues.put(monlntable_col3, lunchNutrition);
            //
            long result = db.insert(monLunchTable, null, contentValues);
            db.close();

            if(result==-1){
                return false;
            }
            else{
                return true;
            }
    }
   public boolean insertMonSupperData(String supper, String supperNutrition) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

            contentValues.put(monsptable_col2, supper);
            contentValues.put(monsptable_col3, supperNutrition);
            //
            long result = db.insert(monSupperTable, null, contentValues);
            db.close();

            if(result==-1){
                return false;
            }
            else{
                return true;
            }
    }
    //INSERTING DATA INTO TUESDAY TABLE
   public boolean insertTueBreakfastData(String breakfast, String breakfastNutrition) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

            contentValues.put(tuebrtable_col2, breakfast);
            contentValues.put(tuebrtable_col3, breakfastNutrition);
            //
            long result = db.insert(tueBreakfastTable, null, contentValues);
            db.close();

            if(result==-1){
                return false;
            }
            else{
                return true;
            }
    }
   public boolean insertTueLunchData(String lunch, String lunchNutrition) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
            contentValues.put(tuelntable_col2, lunch);
            contentValues.put(tuelntable_col3, lunchNutrition);
            //
            long result = db.insert(tueLunchTable, null, contentValues);
            db.close();

            if(result==-1){
                return false;
            }
            else{
                return true;
            }
    }
   public boolean insertTueSupperData(String supper, String supperNutrition) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

            contentValues.put(tuesptable_col2, supper);
            contentValues.put(tuesptable_col3, supperNutrition);
            //
            long result = db.insert(tueSupperTable, null, contentValues);
            db.close();

            if(result==-1){
                return false;
            }
            else{
                return true;
            }
    }
    //INSERTING DATA INTO WEDNESDAY TABLE
   public boolean insertWedBreakfastData(String breakfast, String breakfastNutrition) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

            contentValues.put(wedbrtable_col2, breakfast);
            contentValues.put(wedbrtable_col3, breakfastNutrition);
            //
            long result = db.insert(wedBreakfastTable, null, contentValues);
            db.close();

            if(result==-1){
                return false;
            }
            else{
                return true;
            }
    }
   public boolean insertWedLunchData(String lunch, String lunchNutrition) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
            contentValues.put(wedlntable_col2, lunch);
            contentValues.put(wedlntable_col3, lunchNutrition);
            //
            long result = db.insert(wedLunchTable, null, contentValues);
            db.close();

            if(result==-1){
                return false;
            }
            else{
                return true;
            }
    }
   public boolean insertWedSupperData(String supper, String supperNutrition) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
            contentValues.put(wedsptable_col2, supper);
            contentValues.put(wedsptable_col3, supperNutrition);
            //
            long result = db.insert(wedSupperTable, null, contentValues);
            db.close();

            if(result==-1){
                return false;
            }
            else{
                return true;
            }
    }
    //INSERTING DATA INTO THURSDAY TABLE
   public boolean insertThurBreakfastData(String breakfast, String breakfastNutrition) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

            contentValues.put(thurbrtable_col2, breakfast);
            contentValues.put(thurbrtable_col3, breakfastNutrition);
            //
            long result = db.insert(thurBreakfastTable, null, contentValues);
            db.close();

            if(result==-1){
                return false;
            }
            else{
                return true;
            }
    }
   public boolean insertThurLunchData(String lunch, String lunchNutrition) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

            contentValues.put(thurlntable_col2, lunch);
            contentValues.put(thurlntable_col3, lunchNutrition);
            //
            long result = db.insert(thurLunchTable, null, contentValues);
            db.close();

            if(result==-1){
                return false;
            }
            else{
                return true;
            }
    }
   public boolean insertThurSupperData(String supper, String supperNutrition) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
            contentValues.put(thursptable_col2, supper);
            contentValues.put(thursptable_col3, supperNutrition);
            //
            long result = db.insert(thurSupperTable, null, contentValues);
            db.close();

            if(result==-1){
                return false;
            }
            else{
                return true;
            }
    }
    //INSERTING DATA INTO FRIDAY TABLE
   public boolean insertFriBreakfastData(String breakfast, String breakfastNutrition) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

            contentValues.put(fribrtable_col2, breakfast);
            contentValues.put(fribrtable_col3, breakfastNutrition);
            //
            long result = db.insert(friBreakfastTable, null, contentValues);
            db.close();

            if(result==-1){
                return false;
            }
            else{
                return true;
            }
    }
   public boolean insertFriLunchData(String lunch, String lunchNutrition) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
            contentValues.put(frilntable_col2, lunch);
            contentValues.put(frilntable_col3, lunchNutrition);
            //
            long result = db.insert(friLunchTable, null, contentValues);
            db.close();

            if(result==-1){
                return false;
            }
            else{
                return true;
            }
    }
   public boolean insertFriSupperData(String supper, String supperNutrition) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
            contentValues.put(frisptable_col2, supper);
            contentValues.put(frisptable_col3, supperNutrition);
            //
            long result = db.insert(friSupperTable, null, contentValues);
            db.close();

            if(result==-1){
                return false;
            }
            else{
                return true;
            }
    }
    //INSERTING DATA INTO SATURDAY TABLE
   public boolean insertSatBreakfastData(String breakfast, String breakfastNutrition) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

            contentValues.put(satbrtable_col2, breakfast);
            contentValues.put(satbrtable_col3, breakfastNutrition);
            //
            long result = db.insert(satBreakfastTable, null, contentValues);
            db.close();

            if(result==-1){
                return false;
            }
            else{
                return true;
            }
    }
   public boolean insertSatLunchData(String lunch, String lunchNutrition) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
            contentValues.put(satlntable_col2, lunch);
            contentValues.put(satlntable_col3, lunchNutrition);
            //
            long result = db.insert(satLunchTable, null, contentValues);
            db.close();

            if(result==-1){
                return false;
            }
            else{
                return true;
            }
    }
   public boolean insertSatSupperData(String supper, String supperNutrition) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
            contentValues.put(satsptable_col2, supper);
            contentValues.put(satsptable_col3, supperNutrition);
            //
            long result = db.insert(satSupperTable, null, contentValues);
            db.close();

            if(result==-1){
                return false;
            }
            else{
                return true;
            }
    }
    //INSERTING DATA INTO SUNDAY TABLE
   public boolean insertSunBreakfastData(String breakfast, String breakfastNutrition) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

            contentValues.put(sunbrtable_col2, breakfast);
            contentValues.put(sunbrtable_col3, breakfastNutrition);
            //
            long result = db.insert(sunBreakfastTable, null, contentValues);
            db.close();

            if(result==-1){
                return false;
            }
            else{
                return true;
            }
    }
   public boolean insertSunLunchData(String lunch, String lunchNutrition) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
            contentValues.put(sunlntable_col2, lunch);
            contentValues.put(sunlntable_col3, lunchNutrition);
            //
            long result = db.insert(sunLunchTable, null, contentValues);
            db.close();

            if(result==-1){
                return false;
            }
            else{
                return true;
            }
    }
   public boolean insertSunSupperData(String supper, String supperNutrition) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
            contentValues.put(sunsptable_col2, supper);
            contentValues.put(sunsptable_col3, supperNutrition);
            //
            long result = db.insert(sunSupperTable, null, contentValues);
            db.close();

            if(result==-1){
                return false;
            }
            else{
                return true;
            }
    }
 //getting data
    //RETRIEVING MONDAY BREAKFAST DATA
    public Cursor viewMonBreakfastMealsSpecific(String mealID){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+monBreakfastTable+" WHERE ID = ?", new String[]{mealID});
        return  res;
    }
    //SEARCH MONDAY BREAKFAST DATA
    public Cursor searchMonBreakfastMealsSpecific(String newMeal){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+monBreakfastTable+" WHERE "+monbrtable_col2+" = ?", new String[]{newMeal});
        return  res;
    }
    //RETRIEVING MONDAY BREAKFAST DATA
    public Cursor viewMonBreakfastMeals(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+monBreakfastTable,null);
        return  res;
    }
    //RETRIEVING MONDAY LUNCH DATA
    public Cursor viewMonLunchMealsSpecific(String mealID){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+monLunchTable+" WHERE ID = ?", new String[]{mealID});
        return  res;
    }
    //SEARCH MONDAY lunch DATA
    public Cursor searchMonLunchMealsSpecific(String newMeal){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+monLunchTable+" WHERE "+monlntable_col2+" = ?", new String[]{newMeal});
        return  res;
    }
    //RETRIEVING MONDAY LUNCH DATA
    public Cursor viewMonLunchMeals(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+monLunchTable,null);
        return  res;
    }
    //RETRIEVING MONDAY SUPPER DATA
    public Cursor viewMonSupperMealsSpecific(String mealID){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+monSupperTable+" WHERE ID = ?", new String[]{mealID});
        return  res;
    }
    //SEARCH MONDAY supper DATA
    public Cursor searchMonSupperMealsSpecific(String newMeal){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+monSupperTable+" WHERE "+monsptable_col2+" = ?", new String[]{newMeal});
        return  res;
    }
    //RETRIEVING MONDAY SUPPER DATA
    public Cursor viewMonSupperMeals(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+monSupperTable,null);
        return  res;
    }


    //RETRIEVING TUESDAY BREAKFAST DATA
    public Cursor viewTueBreakfastMealsSpecific(String mealID){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+tueBreakfastTable+" WHERE ID = ?", new String[]{mealID});
        return  res;
    }
    //search tuesday breakfast
    public Cursor searchTueBreakfastMealsSpecific(String newMeal){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+tueBreakfastTable+" WHERE "+tuebrtable_col2+" = ?", new String[]{newMeal});
        return  res;
    }
    //RETRIEVING TUESDAY BREAKFAST DATA
    public Cursor viewTueBreakfastMeals(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+tueBreakfastTable,null);
        return  res;
    }
    //RETRIEVING TUESDAY LUNCH DATA
    public Cursor viewTueLunchMealsSpecific(String mealID){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+tueLunchTable+" WHERE ID = ?", new String[]{mealID});
        return  res;
    }
    //search tuesday lunch
    public Cursor searchTueLunchMealsSpecific(String newMeal){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+tueLunchTable+" WHERE "+tuelntable_col2+" = ?", new String[]{newMeal});
        return  res;
    }
    //RETRIEVING TUESDAY LUNCH DATA
    public Cursor viewTueLunchMeals(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+tueLunchTable,null);
        return  res;
    }
    //RETRIEVING TUESDAY SUPPER DATA
    public Cursor viewTueSupperMealsSpecific(String mealID){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+tueSupperTable+" WHERE ID = ?", new String[]{mealID});
        return  res;
    }
    //search tuesday supper
    public Cursor searchTueSupperMealsSpecific(String newMeal){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+tueSupperTable+" WHERE "+tuesptable_col2+" = ?", new String[]{newMeal});
        return  res;
    }
    //RETRIEVING TUESDAY SUPPER DATA
    public Cursor viewTueSupperMeals(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+tueSupperTable,null);
        return  res;
    }


    //RETRIEVING WED BREAKFAST DATA
    public Cursor viewWedBreakfastMealsSpecific(String mealID){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+wedBreakfastTable+" WHERE ID = ?", new String[]{mealID});
        return  res;
    }
    //search wed breakfast
    public Cursor searchWedBreakfastMealsSpecific(String newMeal){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+wedBreakfastTable+" WHERE "+wedbrtable_col2+" = ?", new String[]{newMeal});
        return  res;
    }
    //RETRIEVING WED BREAKFAST DATA
    public Cursor viewWedBreakfastMeals(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+wedBreakfastTable,null);
        return  res;
    }
    //RETRIEVING WED LUNCH DATA
    public Cursor viewWedLunchMealsSpecific(String mealID){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+wedLunchTable+" WHERE ID = ?", new String[]{mealID});
        return  res;
    }
    //search wed lunch
    public Cursor searchWedLunchMealsSpecific(String newMeal){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+wedLunchTable+" WHERE "+wedlntable_col2+" = ?", new String[]{newMeal});
        return  res;
    }
    //RETRIEVING WED LUNCH DATA
    public Cursor viewWedLunchMeals(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+wedLunchTable,null);
        return  res;
    }
    //RETRIEVING WED SUPPER DATA
    public Cursor viewWedSupperMealsSpecific(String mealID){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+wedSupperTable+" WHERE ID = ?", new String[]{mealID});
        return  res;
    }
    //search wed supper
    public Cursor searchWedSupperMealsSpecific(String newMeal){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+wedSupperTable+" WHERE "+wedsptable_col2+" = ?", new String[]{newMeal});
        return  res;
    }
    //RETRIEVING WED SUPPER DATA
    public Cursor viewWedSupperMeals(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+wedSupperTable,null);
        return  res;
    }



    //RETRIEVING THUR BREAKFAST DATA
    public Cursor viewThurBreakfastMealsSpecific(String mealID){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+thurBreakfastTable+" WHERE ID = ?", new String[]{mealID});
        return  res;
    }
    //search thur breakfast
    public Cursor searchThurBreakfastMealsSpecific(String newMeal){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+thurBreakfastTable+" WHERE "+thurbrtable_col2+" = ?", new String[]{newMeal});
        return  res;
    }
    //RETRIEVING THUR BREAKFAST DATA
    public Cursor viewThurBreakfastMeals(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+thurBreakfastTable,null);
        return  res;
    }
    //RETRIEVING THUR LUNCH DATA
    public Cursor viewThurLunchMealsSpecific(String mealID){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+thurLunchTable+" WHERE ID = ?", new String[]{mealID});
        return  res;
    }
    //search thur lunch
    public Cursor searchThurLunchMealsSpecific(String newMeal){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+thurLunchTable+" WHERE "+thurlntable_col2+" = ?", new String[]{newMeal});
        return  res;
    }
    //RETRIEVING THUR LUNCH DATA
    public Cursor viewThurLunchMeals(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+thurLunchTable,null);
        return  res;
    }
    //RETRIEVING THUR SUPPER DATA
    public Cursor viewThurSupperMealsSpecific(String mealID){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+thurSupperTable+" WHERE ID = ?", new String[]{mealID});
        return  res;
    }
    //search thur supper
    public Cursor searchThurSupperMealsSpecific(String newMeal){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+thurSupperTable+" WHERE "+thursptable_col2+" = ?", new String[]{newMeal});
        return  res;
    }
    //RETRIEVING THUR SUPPER DATA
    public Cursor viewThurSupperMeals(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+thurSupperTable,null);
        return  res;
    }



    //RETRIEVING FRI BREAKFAST DATA
    public Cursor viewFriBreakfastMealsSpecific(String mealID){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+friBreakfastTable+" WHERE ID = ?", new String[]{mealID});
        return  res;
    }
    //search fri breakfast
    public Cursor searchFriBreakfastMealsSpecific(String newMeal){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+friBreakfastTable+" WHERE "+fribrtable_col2+" = ?", new String[]{newMeal});
        return  res;
    }
    //RETRIEVING FRI BREAKFAST DATA
    public Cursor viewFriBreakfastMeals(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+friBreakfastTable,null);
        return  res;
    }
    //RETRIEVING FRI LUNCH DATA
    public Cursor viewFriLunchMealsSpecific(String mealID){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+friLunchTable+" WHERE ID = ?", new String[]{mealID});
        return  res;
    }
    //search fri lunch
    public Cursor searchFriLunchMealsSpecific(String newMeal){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+friLunchTable+" WHERE "+frilntable_col2+" = ?", new String[]{newMeal});
        return  res;
    }
    //RETRIEVING FRI LUNCH DATA
    public Cursor viewFriLunchMeals(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+friLunchTable,null);
        return  res;
    }
    //RETRIEVING FRI SUPPER DATA
    public Cursor viewFriSupperMealsSpecific(String mealID){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+friSupperTable+" WHERE ID = ?", new String[]{mealID});
        return  res;
    }
    //search fri supper
    public Cursor searchFriSupperMealsSpecific(String newMeal){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+friSupperTable+" WHERE "+frisptable_col2+" = ?", new String[]{newMeal});
        return  res;
    }
    //RETRIEVING FRI SUPPER DATA
    public Cursor viewFriSupperMeals(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+friSupperTable,null);
        return  res;
    }


    //RETRIEVING SAT BREAKFAST DATA
    public Cursor viewSatBreakfastMealsSpecific(String mealID){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+satBreakfastTable+" WHERE ID = ?", new String[]{mealID});
        return  res;
    }
    //search sat breakfast
    public Cursor searchSatBreakfastMealsSpecific(String newMeal){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+satBreakfastTable+" WHERE "+satbrtable_col2+" = ?", new String[]{newMeal});
        return  res;
    }
    //RETRIEVING SAT BREAKFAST DATA
    public Cursor viewSatBreakfastMeals(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+satBreakfastTable,null);
        return  res;
    }
    //RETRIEVING SAT LUNCH DATA
    public Cursor viewSatLunchMealsSpecific(String mealID){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+satLunchTable+" WHERE ID = ?", new String[]{mealID});
        return  res;
    }
    //search sat lunch
    public Cursor searchSatLunchMealsSpecific(String newMeal){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+satLunchTable+" WHERE "+satlntable_col2+" = ?", new String[]{newMeal});
        return  res;
    }
    //RETRIEVING SAT LUNCH DATA
    public Cursor viewSatLunchMeals(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+satLunchTable,null);
        return  res;
    }
    //RETRIEVING SAT SUPPER DATA
    public Cursor viewSatSupperMealsSpecific(String mealID){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+satSupperTable+" WHERE ID = ?", new String[]{mealID});
        return  res;
    }
    //search sat supper
    public Cursor searchSatSupperMealsSpecific(String newMeal){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+satSupperTable+" WHERE "+satsptable_col2+" = ?", new String[]{newMeal});
        return  res;
    }
    //RETRIEVING SAT SUPPER DATA
    public Cursor viewSatSupperMeals(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+satSupperTable,null);
        return  res;
    }


    //RETRIEVING SUN BREAKFAST DATA
    public Cursor viewSunBreakfastMealsSpecific(String mealID){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+sunBreakfastTable+" WHERE ID = ?", new String[]{mealID});
        return  res;
    }
    //search sun breakfast
    public Cursor searchSunBreakfastMealsSpecific(String newMeal){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+sunBreakfastTable+" WHERE "+sunbrtable_col2+" = ?", new String[]{newMeal});
        return  res;
    }
    //RETRIEVING SUN BREAKFAST DATA
    public Cursor viewSunBreakfastMeals(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+sunBreakfastTable,null);
        return  res;
    }
    //RETRIEVING SUN LUNCH DATA
    public Cursor viewSunLunchMealsSpecific(String mealID){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+sunLunchTable+" WHERE ID = ?", new String[]{mealID});
        return  res;
    }
    //search sun lunch
    public Cursor searchSunLunchMealsSpecific(String newMeal){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+sunLunchTable+" WHERE "+sunlntable_col2+" = ?", new String[]{newMeal});
        return  res;
    }
    //RETRIEVING SUN LUNCH DATA
    public Cursor viewSunLunchMeals(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+sunLunchTable,null);
        return  res;
    }
    //RETRIEVING SUN SUPPER DATA
    public Cursor viewSunSupperMealsSpecific(String mealID){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+sunSupperTable+" WHERE ID = ?", new String[]{mealID});
        return  res;
    }
    //search sun supper
    public Cursor searchSunSupperMealsSpecific(String newMeal){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+sunSupperTable+" WHERE "+sunsptable_col2+" = ?", new String[]{newMeal});
        return  res;
    }
    //RETRIEVING SUN SUPPER DATA
    public Cursor viewSunSupperMeals(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+sunSupperTable,null);
        return  res;
    }
//
////    RETRIEVING DATA FROM MONDAY TABLE
//    public Cursor monBreakfast(String mealID){
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor res = db.rawQuery("SELECT "+montable_col2+","+montable_col3+" FROM "+MONTABLE+"  WHERE ID = ?", new String[]{mealID});
//        return  res;
//    }

    //RETRIEVING SINGLE DATA FROM LOGIN TABLE
//    public Cursor viewSingle(String username, String password){
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        Cursor res = db.rawQuery("SELECT Username, Password FROM Login_Table WHERE Username = ? AND Username = ?", new String[]{username,password});
//
//        //Cursor res = db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+col2+" =?",null);
//        return  res;
//
//    }
//    zebola my music

    //UPDATING MONDAY DATA
    public boolean updateMonBreakfastData(String breakfast, String breakfastNutrition, String mealID) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(monbrtable_col2, breakfast);
        contentValues.put(monbrtable_col3, breakfastNutrition);

        db.update(monBreakfastTable,contentValues," ID=? ",new String[]{mealID});
        db.close();
        return  true;
    }

    public boolean updateMonLunchData(String lunch, String lunchNutrition, String mealID) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(monlntable_col2, lunch);
        contentValues.put(monlntable_col3, lunchNutrition);

        db.update(monLunchTable,contentValues," ID=? ",new String[]{mealID});
            return true;

    }
    public boolean updateMonSupperData(String supper, String supperNutrition, String mealID) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(monsptable_col2, supper);
        contentValues.put(monsptable_col3, supperNutrition);

        db.update(monSupperTable,contentValues," ID=? ",new String[]{mealID});
        db.close();
        return  true;
    }

    //UPDATING TUESDAY TABLE
    public boolean updateTueBreakfastData(String breakfast, String breakfastNutrition, String mealID) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(tuebrtable_col2, breakfast);
        contentValues.put(tuebrtable_col3, breakfastNutrition);

        db.update(tueBreakfastTable,contentValues," ID=? ",new String[]{mealID});
        db.close();
        return  true;
    }

    public boolean updateTueLunchData(String lunch, String lunchNutrition, String mealID) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(tuelntable_col2, lunch);
        contentValues.put(tuelntable_col3, lunchNutrition);

        db.update(tueLunchTable,contentValues," ID=? ",new String[]{mealID});
        db.close();
        return  true;
    }

    public boolean updateTueSupperData(String supper, String supperNutrition, String mealID) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(tuesptable_col2, supper);
        contentValues.put(tuesptable_col3, supperNutrition);

        db.update(tueSupperTable,contentValues," ID=? ",new String[]{mealID});
        db.close();
        return  true;
    }

    //UPDATING WEDNESDAY TABLE
    public boolean updateWedBreakfastData(String breakfast, String breakfastNutrition, String mealID) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(wedbrtable_col2, breakfast);
        contentValues.put(wedbrtable_col3, breakfastNutrition);

        db.update(wedBreakfastTable,contentValues," ID=? ",new String[]{mealID});
        db.close();
        return  true;
    }
    public boolean updateWedLunchData(String lunch, String lunchNutrition, String mealID) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(wedlntable_col2, lunch);
        contentValues.put(wedlntable_col3, lunchNutrition);

        db.update(wedLunchTable,contentValues," ID=? ",new String[]{mealID});
        db.close();
        return  true;
    }
    public boolean updateWedSupperData(String supper, String supperNutrition, String mealID) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(wedsptable_col2, supper);
        contentValues.put(wedsptable_col3, supperNutrition);

        db.update(wedSupperTable,contentValues," ID=? ",new String[]{mealID});
        db.close();
        return  true;
    }

    //UPDATING THURSDAY TABLE
    public boolean updateThurBreakfastData(String breakfast, String breakfastNutrition, String mealID) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(thurbrtable_col2, breakfast);
        contentValues.put(thurbrtable_col3, breakfastNutrition);

        db.update(thurBreakfastTable,contentValues," ID=? ",new String[]{mealID});
        db.close();
        return  true;
    }
    public boolean updateThurLunchData(String lunch, String lunchNutrition, String mealID) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(thurlntable_col2, lunch);
        contentValues.put(thurlntable_col3, lunchNutrition);

        db.update(thurLunchTable,contentValues," ID=? ",new String[]{mealID});
        db.close();
        return  true;
    }
    public boolean updateThurSupperData(String supper, String supperNutrition, String mealID) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(thursptable_col2, supper);
        contentValues.put(thursptable_col3, supperNutrition);

        db.update(thurSupperTable,contentValues," ID=? ",new String[]{mealID});
        db.close();
        return  true;
    }
    //UPDATING FRIDAY TABLE
    public boolean updateFriBreakfastData(String breakfast, String breakfastNutrition, String mealID) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(fribrtable_col2, breakfast);
        contentValues.put(fribrtable_col3, breakfastNutrition);

        db.update(friBreakfastTable,contentValues," ID=? ",new String[]{mealID});
        db.close();
        return  true;
    }
    public boolean updateFriLunchData(String lunch, String lunchNutrition, String mealID) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(frilntable_col2, lunch);
        contentValues.put(frilntable_col3, lunchNutrition);

        db.update(friLunchTable,contentValues," ID=? ",new String[]{mealID});
        db.close();
        return  true;
    }
    public boolean updateFriSupperData(String supper, String supperNutrition, String mealID) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(frisptable_col2, supper);
        contentValues.put(frisptable_col3, supperNutrition);

        db.update(friSupperTable,contentValues," ID=? ",new String[]{mealID});
        db.close();
        return  true;
    }
    //UPDATE SATURDAY TABLE
    public boolean updateSatBreakfastData(String breakfast, String breakfastNutrition, String mealID) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(satbrtable_col2, breakfast);
        contentValues.put(satbrtable_col3, breakfastNutrition);

        db.update(satBreakfastTable,contentValues," ID=? ",new String[]{mealID});
        db.close();
        return  true;
    }
    public boolean updateSatLunchData(String lunch, String lunchNutrition, String mealID) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(satlntable_col2, lunch);
        contentValues.put(satlntable_col3, lunchNutrition);

        db.update(satLunchTable,contentValues," ID=? ",new String[]{mealID});
        db.close();
        return  true;
    }
    public boolean updateSatSupperData(String supper, String supperNutrition, String mealID) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(satsptable_col2, supper);
        contentValues.put(satsptable_col3, supperNutrition);

    db.update(satSupperTable,contentValues," ID=? ",new String[]{mealID});
        db.close();
        return  true;
    }
    //UPDATE SUNDAY TABLE
    public boolean updateSunBreakfastData(String breakfast, String breakfastNutrition, String mealID) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(sunbrtable_col2, breakfast);
        contentValues.put(sunbrtable_col3, breakfastNutrition);

        db.update(sunBreakfastTable,contentValues," ID=? ",new String[]{mealID});
        db.close();
        return  true;
    }
    public boolean updateSunLunchData(String lunch, String lunchNutrition, String mealID) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(sunlntable_col2, lunch);
        contentValues.put(sunlntable_col3, lunchNutrition);

        db.update(sunLunchTable,contentValues," ID=? ",new String[]{mealID});
        db.close();
        return  true;
    }
    public boolean updateSunSupperData(String supper, String supperNutrition, String mealID) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(sunsptable_col2, supper);
        contentValues.put(sunsptable_col3, supperNutrition);

        db.update(sunSupperTable,contentValues," ID=? ",new String[]{mealID});
        db.close();
        return  true;
    }

    public boolean deleteMonBreakfastData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + monBreakfastTable);
        return true;
    }
    public boolean deleteMonLunchData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + monLunchTable);
        return true;
    }
    public boolean deleteMonSupperData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + monSupperTable);
        return true;
    }


    public boolean deleteTueBreakfastData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + tueBreakfastTable);
        return true;
    }
    public boolean deleteTueLunchData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + tueLunchTable);
        return true;
    }
    public boolean deleteTueSupperData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + tueSupperTable);
        return true;
    }


    public boolean deletewedBreakfastData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + wedBreakfastTable);
        return true;
    }
    public boolean deletewedLunchData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + wedLunchTable);
        return true;
    }
    public boolean deletewedSupperData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + wedSupperTable);
        return true;
    }


    public boolean deletethurBreakfastData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + thurBreakfastTable);
        return true;
    }
    public boolean deletethurLunchData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + thurLunchTable);
        return true;
    }
    public boolean deletethurSupperData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + thurSupperTable);
        return true;
    }


    public boolean deletefriBreakfastData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + friBreakfastTable);
        return true;
    }
    public boolean deletefriLunchData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + friLunchTable);
        return true;
    }
    public boolean deletefriSupperData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + friSupperTable);
        return true;
    }


    public boolean deletesatBreakfastData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + satBreakfastTable);
        return true;
    }
    public boolean deletesatLunchData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + satLunchTable);
        return true;
    }
    public boolean deletesatSupperData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + satSupperTable);
        return true;
    }


    public boolean deletesunBreakfastData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + sunBreakfastTable);
        return true;
    }
    public boolean deletesunLunchData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + sunLunchTable);
        return true;
    }
    public boolean deletesunSupperData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + sunSupperTable);
        return true;
    }

}
