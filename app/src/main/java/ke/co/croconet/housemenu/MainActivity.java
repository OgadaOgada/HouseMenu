package ke.co.croconet.housemenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.material.snackbar.Snackbar;
import com.opencsv.CSVReader;

import org.apache.commons.collections.list.CursorableLinkedList;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Calendar;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

//import static ke.co.croconet.housemenu.ZipManager.unzip;
import static ke.co.croconet.housemenu.ZipManager.unzip;
import static ke.co.croconet.housemenu.ZipManager.zip;


public class MainActivity extends AppCompatActivity {

    private AdView mAdView;
    Button monday, tuesday,wednesday,thursday,friday, saturday, sunday,menusharebtn;
    TextView mealsummary;
    ConstraintLayout constraintLayout;

    private final Handler handler = new Handler();
    String foldername = "/HouseMenuBackup";
    String zippedfile = "/HouseMealPlan.zip";

//    today_meal myTodayMeal;
    public static String day="";
    importExportClass myVictual;
    static DatabaseHelper myDatabase;

    //Changing text color of the day
    int yearInt  = Calendar.getInstance().get(Calendar.YEAR);
    String yearStr = Integer.toString(yearInt);

    String myColor = "#000000";
    int timeInt = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
    int dowInt  = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);

    int dayInt  = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    String dayStr = Integer.toString(dayInt);
    int monthInt  = Calendar.getInstance().get(Calendar.MONTH);
    String monthStr="";
//    /*-------------- storage access---------------*/
    private static final int STORAGE_REQUEST_CODE_EXPORT = 1;
    private static final int STORAGE_REQUEST_CODE_IMPORT = 2;
    private String[]storagePermissions;
    private int[]storagePermissionsResults;

    private float x1,x2;
    static final int MIN_DISTANCE = 150;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestStoragePermissionImport();
        requestStoragePermissionExport();
        setContentView(R.layout.activity_main);
        this.setTitle("House Meal Planner");


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

//        onRequestforPermission(1,storagePermissions,PackageManager.PERMISSION_GRANTED);

        myDatabase = new DatabaseHelper(this);

        storagePermissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};

        myVictual = new importExportClass();

        monday = (Button)findViewById(R.id.mondayButton);
        tuesday = (Button)findViewById(R.id.tuesdayButton);
        wednesday = (Button)findViewById(R.id.wednesdayButton);
        thursday = (Button)findViewById(R.id.thursdayButton);
        friday = (Button)findViewById(R.id.fridayButton);
        saturday = (Button)findViewById(R.id.saturdayButton);
        sunday = (Button)findViewById(R.id.sundayButton);
//        menusharebtn = (Button)findViewById(R.id.menuShareButton);


        constraintLayout = (ConstraintLayout) findViewById(R.id.constraintLyt);

        mealsummary = (TextView)findViewById(R.id.mealSummary);
        quickReference();
//      doTheAutoRefresh();
        changeDay();

        //Buttons onclicks
        monday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                day="Monday";
                mealsDashboard();
            }
        });

        tuesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                day="Tuesday";
               mealsDashboard();
            }
        });

        wednesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                day="Wednesday";
               mealsDashboard();
            }
        });

        thursday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                day="Thursday";
               mealsDashboard();
            }
        });

        friday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                day="Friday";
               mealsDashboard();
            }
        });

        saturday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                day="Saturday";
               mealsDashboard();
            }
        });

        sunday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                day="Sunday";
               mealsDashboard();
            }

        });

    }//end of oncreate


    //menu and overflow
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.importMenu:
//                Toast.makeText(this,"Import menu data coming soon",Toast.LENGTH_SHORT).show();
            //        //restore button action
                    if(checkStoragePermission()) {
                        //permission allowed
                            File folder = new File(Environment.getExternalStorageDirectory() + foldername); //SQLiteBackup folder name
                            File[] contents = folder.listFiles();
                            // HouseMenuBackup is not really a directory or does not exist..
                             if (!folder.exists()) {
                                 folder.mkdir(); //creates folder is not available
                                 unzipMethod();
                            }
                            // HouseMenuBackup Folder is empty
                            else if (contents.length == 0) {
//                                Toast.makeText(this, "HouseMenuBackup folder is empty!", Toast.LENGTH_LONG).show();
                                unzipMethod();
                            }
                            //HouseMenuBackup exist and has content, then restore
                            else{
                            showDialog(this, "Current meal plan will be overwritten, continue with menu import?",
                                    "Okay", "Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    importCSV();
                                }
                            });
                            }
                    }
                    else {
                        //permission denied
//                        requestStoragePermissionImport();


                        if (ContextCompat.checkSelfPermission(this,
                                Manifest.permission.READ_EXTERNAL_STORAGE)
                                != PackageManager.PERMISSION_GRANTED) {
                            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
                                alertBuilder.setCancelable(true);
//                                alertBuilder.setTitle("Storage permission necessary");
                                alertBuilder.setMessage("House meal menu app needs storage permission to restore meal plan backup data.");
                                alertBuilder.setPositiveButton("Proceed", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        requestStoragePermissionExport();
                                    }
                                });
                                alertBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        mySnackbar("You wont be able to backup, restore or share meal plan!");
//                                        Toast.makeText(getApplicationContext(),"You wont be able to backup or restore menu!",Toast.LENGTH_SHORT).show();
                                    }
                                });

                                AlertDialog alert = alertBuilder.create();
                                alert.show();
                            }
//                            else {
//                                requestStoragePermissionExport();
//                            }
                        }

                    }
                return true;
            case R.id.exportMenu:
//                Toast.makeText(this,"Export menu data coming soon",Toast.LENGTH_SHORT).show();
               //Backup button action
                    if(checkStoragePermission()){
                    //permission allowed
                        showDialog(this,"Previous backup data(if any) will be overwritten, continue with menu backup?","Okay","Cancel",new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            exportCSV();
                            zipMethod();
//                            myToast("Meal plan backup saved in "+foldername.substring(1)+" folder",Toast.LENGTH_LONG);
                            startMainActivity();
                            }
                        });
                    }
                    else {
                        //permisson denied
//                        requestStoragePermissionExport();
                        if (ContextCompat.checkSelfPermission(this,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                != PackageManager.PERMISSION_GRANTED) {
                            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
                                alertBuilder.setCancelable(true);
//                                alertBuilder.setTitle("Storage permission necessary");
                                alertBuilder.setMessage("House meal menu app needs storage permission to save meal plan backup data.");
                                alertBuilder.setPositiveButton("Proceed", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    requestStoragePermissionExport();
                                    }
                                });
                                alertBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        mySnackbar("You wont be able to backup, restore or share meal plan!");
//                                        Toast.makeText(getApplicationContext(),"You wont be able to backup or restore menu!",Toast.LENGTH_SHORT).show();
                                    }
                                });

                                AlertDialog alert = alertBuilder.create();
                                alert.show();
                            }
//                            else {
//                                requestStoragePermissionExport();
//                            }
                        }

                    }

                return true;
            case R.id.clearMenu:
                Cursor res = myDatabase.viewMonBreakfastMeals();
//                if (res.getCount() == 0) { //
                //problem...removed monday
//                    myToast("Monday Breakfast is empty", Toast.LENGTH_SHORT);
//                }else {
                showDialog(this,"All meal plan will be deleted, continue?","Yes","Cancel",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        exportCSVWithoutMessage();
                        zipMethod();
                        myDeleteMealMenuRecords();
                        startMainActivity();
                        mySnackbar("House meal plan cleared");
                    }

                });
                return true;
            case R.id.shareMealPlan:

                if(checkStoragePermission()){
                    exportCSVWithoutMessage();
                    zipMethod();
                    File zippedBackup = new File(Environment.getExternalStorageDirectory() + ""); //zipped meal menu backup
                    String filePath = zippedBackup+zippedfile;
                    //check if zip exists then unzip and call importcsv() method
                    if(zippedBackup.exists()) {
                        zippedBackup.delete();
                        sendFile(filePath);
                    }
//                    shareMethod("House Meal Planner Backup",folder);
                }
                else {
                    //permisson denied
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.READ_EXTERNAL_STORAGE)
                            != PackageManager.PERMISSION_GRANTED) {
                        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
                            alertBuilder.setCancelable(true);
//                                alertBuilder.setTitle("Storage permission necessary");
                            alertBuilder.setMessage("House meal menu app needs storage permission to share meal plan backup data.");
                            alertBuilder.setPositiveButton("Proceed", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    requestStoragePermissionExport();
                                }
                            });
                            alertBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    mySnackbar("You wont be able to backup, restore or share meal plan!");
//                                    Toast.makeText(getApplicationContext(),,Toast.LENGTH_SHORT).show();
                                }
                            });

                            AlertDialog alert = alertBuilder.create();
                            alert.show();
                        }
                    }

                }

                return true;
            case R.id.shareMenu:
                shareMethod("House Meal Planner App","https://play.google.com/store/apps/details?id=ke.co.croconet.housemenu");
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    //intent for main class
    public void startMainActivity() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

    //get db data and display in text view
    public void quickReference(){

        int timeInt = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        int dowInt  = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
//        SHOWING A QUICK OVERVIEW
        String meal1 = "", meal2 = "",  meal3 = "", meal4 = "", meal5 = "";
        String dowStr = Integer.toString(dowInt);
        String mealType;

        if(timeInt<=10){mealType="Breakfast";}
        else if(timeInt<=14){ mealType="Lunch";}
        else {mealType="Supper";}
        //getting time and day
        if(dowInt==1){dowStr="Sunday";}
        if(dowInt==2){dowStr="Monday";}
        if(dowInt==3){dowStr="Tuesday";}
        if(dowInt==4){dowStr="Wednesday";}
        if(dowInt==5){dowStr="Thursday";}
        if(dowInt==6){dowStr="Friday";}
        if(dowInt==7){dowStr="Saturday";}

        String mealReference = "\n"+dowStr+" "+mealType+" Quick Overview\n\n";
        String noMeal ="\nNo meal has been assigned for "+dowStr+" "+mealType.toLowerCase();
//
        //monday
        if(dowInt==2) {

            if(timeInt<=10){
                Cursor res1 = myDatabase.viewMonBreakfastMealsSpecific("1");
                if (res1.getCount() != 0) {
                    StringBuffer buffer1 = new StringBuffer();
                    while (res1.moveToNext()) {
                        meal1 = res1.getString(1);
                    }}
                Cursor res2 = myDatabase.viewMonBreakfastMealsSpecific("2");
                if (res2.getCount() != 0){
                    StringBuffer buffer2 = new StringBuffer();
                    while (res2.moveToNext()) {
                        meal2 = res2.getString(1);
                    }}
                Cursor res3 = myDatabase.viewMonBreakfastMealsSpecific("3");
                if (res3.getCount() != 0) {
                    StringBuffer buffer3 = new StringBuffer();
                    while (res3.moveToNext()) {
                        meal3 = res3.getString(1);
                    }}
                Cursor res4 = myDatabase.viewMonBreakfastMealsSpecific("4");
                if (res4.getCount() != 0) {
                    StringBuffer buffer4 = new StringBuffer();
                    while (res4.moveToNext()) {
                        meal4 = res4.getString(1);
                    }}
                Cursor res5 = myDatabase.viewMonBreakfastMealsSpecific("5");
                if (res5.getCount() != 0) {
                    StringBuffer buffer5 = new StringBuffer();
                    while (res5.moveToNext()) {
                        meal5 = res5.getString(1);
                    }}
                if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1+", "+meal2+", "+meal3+", "+meal4+", "+meal5);}

                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1+", "+meal2+", "+meal3+", "+meal4);}

                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1+", "+meal2+", "+meal3);}

                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1+", "+meal2);}

                else if(!meal1.trim().equalsIgnoreCase("")&&meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1);}
                else {mealsummary.setText(noMeal);}
            }
            else if (timeInt<=14){
                Cursor res1 = myDatabase.viewMonLunchMealsSpecific("1");
                if (res1.getCount() != 0) {
                    StringBuffer buffer1 = new StringBuffer();
                    while (res1.moveToNext()) {
                        meal1 = res1.getString(1);
                    }}
                Cursor res2 = myDatabase.viewMonLunchMealsSpecific("2");
                if (res2.getCount() != 0){
                    StringBuffer buffer2 = new StringBuffer();
                    while (res2.moveToNext()) {
                        meal2 = res2.getString(1);
                    }}
                Cursor res3 = myDatabase.viewMonLunchMealsSpecific("3");
                if (res3.getCount() != 0) {
                    StringBuffer buffer3 = new StringBuffer();
                    while (res3.moveToNext()) {
                        meal3 = res3.getString(1);
                    }}
                Cursor res4 = myDatabase.viewMonLunchMealsSpecific("4");
                if (res4.getCount() != 0) {
                    StringBuffer buffer4 = new StringBuffer();
                    while (res4.moveToNext()) {
                        meal4 = res4.getString(1);
                    }}
                Cursor res5 = myDatabase.viewMonLunchMealsSpecific("5");
                if (res5.getCount() != 0) {
                    StringBuffer buffer5 = new StringBuffer();
                    while (res5.moveToNext()) {
                        meal5 = res5.getString(1);
                    }}


                if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1+", "+meal2+", "+meal3+", "+meal4+", "+meal5);}

                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1+", "+meal2+", "+meal3+", "+meal4);}

                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1+", "+meal2+", "+meal3);}

                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1+", "+meal2);}

                else if(!meal1.trim().equalsIgnoreCase("")&&meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1);}
                else {mealsummary.setText(noMeal);}
            }
            else{
                Cursor res1 = myDatabase.viewMonSupperMealsSpecific("1");
                if (res1.getCount() != 0) {
                    StringBuffer buffer1 = new StringBuffer();
                    while (res1.moveToNext()) {
                        meal1 = res1.getString(1);
                    }}
                Cursor res2 = myDatabase.viewMonSupperMealsSpecific("2");
                if (res2.getCount() != 0){
                    StringBuffer buffer2 = new StringBuffer();
                    while (res2.moveToNext()) {
                        meal2 = res2.getString(1);
                    }}
                Cursor res3 = myDatabase.viewMonSupperMealsSpecific("3");
                if (res3.getCount() != 0) {
                    StringBuffer buffer3 = new StringBuffer();
                    while (res3.moveToNext()) {
                        meal3 = res3.getString(1);
                    }}
                Cursor res4 = myDatabase.viewMonSupperMealsSpecific("4");
                if (res4.getCount() != 0) {
                    StringBuffer buffer4 = new StringBuffer();
                    while (res4.moveToNext()) {
                        meal4 = res4.getString(1);
                    }}
                Cursor res5 = myDatabase.viewMonSupperMealsSpecific("5");
                if (res5.getCount() != 0) {
                    StringBuffer buffer5 = new StringBuffer();
                    while (res5.moveToNext()) {
                        meal5 = res5.getString(1);
                    }}

                if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1+", "+meal2+", "+meal3+", "+meal4+", "+meal5);}

                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1+", "+meal2+", "+meal3+", "+meal4);}

                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1+", "+meal2+", "+meal3);}

                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1+", "+meal2);}

                else if(!meal1.trim().equalsIgnoreCase("")&&meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1);}
                else {mealsummary.setText(noMeal);}
            }
        }

//      tuesday
        else if(dowInt==3) {

            if(timeInt<=10){
                Cursor res1 = myDatabase.viewTueBreakfastMealsSpecific("1");
                if (res1.getCount() != 0) {
                    StringBuffer buffer1 = new StringBuffer();
                    while (res1.moveToNext()) {
                        meal1 = res1.getString(1);
                    }}
                Cursor res2 = myDatabase.viewTueBreakfastMealsSpecific("2");
                if (res2.getCount() != 0){
                    StringBuffer buffer2 = new StringBuffer();
                    while (res2.moveToNext()) {
                        meal2 = res2.getString(1);
                    }}
                Cursor res3 = myDatabase.viewTueBreakfastMealsSpecific("3");
                if (res3.getCount() != 0) {
                    StringBuffer buffer3 = new StringBuffer();
                    while (res3.moveToNext()) {
                        meal3 = res3.getString(1);
                    }}
                Cursor res4 = myDatabase.viewTueBreakfastMealsSpecific("4");
                if (res4.getCount() != 0) {
                    StringBuffer buffer4 = new StringBuffer();
                    while (res4.moveToNext()) {
                        meal4 = res4.getString(1);
                    }}
                Cursor res5 = myDatabase.viewTueBreakfastMealsSpecific("5");
                if (res5.getCount() != 0) {
                    StringBuffer buffer5 = new StringBuffer();
                    while (res5.moveToNext()) {
                        meal5 = res5.getString(1);
                    }}

                if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1+", "+meal2+", "+meal3+", "+meal4+", "+meal5);}

                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1+", "+meal2+", "+meal3+", "+meal4);}

                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1+", "+meal2+", "+meal3);}

                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1+", "+meal2);}

                else if(!meal1.trim().equalsIgnoreCase("")&&meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1);}
                else {mealsummary.setText(noMeal);}
            }
            else if (timeInt<=14){
                Cursor res1 = myDatabase.viewTueLunchMealsSpecific("1");
                if (res1.getCount() != 0) {
                    StringBuffer buffer1 = new StringBuffer();
                    while (res1.moveToNext()) {
                        meal1 = res1.getString(1);
                    }}
                Cursor res2 = myDatabase.viewTueLunchMealsSpecific("2");
                if (res2.getCount() != 0){
                    StringBuffer buffer2 = new StringBuffer();
                    while (res2.moveToNext()) {
                        meal2 = res2.getString(1);
                    }}
                Cursor res3 = myDatabase.viewTueLunchMealsSpecific("3");
                if (res3.getCount() != 0) {
                    StringBuffer buffer3 = new StringBuffer();
                    while (res3.moveToNext()) {
                        meal3 = res3.getString(1);
                    }}
                Cursor res4 = myDatabase.viewTueLunchMealsSpecific("4");
                if (res4.getCount() != 0) {
                    StringBuffer buffer4 = new StringBuffer();
                    while (res4.moveToNext()) {
                        meal4 = res4.getString(1);
                    }}
                Cursor res5 = myDatabase.viewTueLunchMealsSpecific("5");
                if (res5.getCount() != 0) {
                    StringBuffer buffer5 = new StringBuffer();
                    while (res5.moveToNext()) {
                        meal5 = res5.getString(1);
                    }}

                if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1+", "+meal2+", "+meal3+", "+meal4+", "+meal5);}

                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1+", "+meal2+", "+meal3+", "+meal4);}

                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1+", "+meal2+", "+meal3);}

                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1+", "+meal2);}

                else if(!meal1.trim().equalsIgnoreCase("")&&meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1);}
                else {mealsummary.setText(noMeal);}
            }
            else{
                Cursor res1 = myDatabase.viewTueSupperMealsSpecific("1");
                if (res1.getCount() != 0) {
                    StringBuffer buffer1 = new StringBuffer();
                    while (res1.moveToNext()) {
                        meal1 = res1.getString(1);
                    }}
                Cursor res2 = myDatabase.viewTueSupperMealsSpecific("2");
                if (res2.getCount() != 0){
                    StringBuffer buffer2 = new StringBuffer();
                    while (res2.moveToNext()) {
                        meal2 = res2.getString(1);
                    }}
                Cursor res3 = myDatabase.viewTueSupperMealsSpecific("3");
                if (res3.getCount() != 0) {
                    StringBuffer buffer3 = new StringBuffer();
                    while (res3.moveToNext()) {
                        meal3 = res3.getString(1);
                    }}
                Cursor res4 = myDatabase.viewTueSupperMealsSpecific("4");
                if (res4.getCount() != 0) {
                    StringBuffer buffer4 = new StringBuffer();
                    while (res4.moveToNext()) {
                        meal4 = res4.getString(1);
                    }}
                Cursor res5 = myDatabase.viewTueSupperMealsSpecific("5");
                if (res5.getCount() != 0) {
                    StringBuffer buffer5 = new StringBuffer();
                    while (res5.moveToNext()) {
                        meal5 = res5.getString(1);
                    }}
                if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1+", "+meal2+", "+meal3+", "+meal4+", "+meal5);}

                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1+", "+meal2+", "+meal3+", "+meal4);}

                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1+", "+meal2+", "+meal3);}

                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1+", "+meal2);}

                else if(!meal1.trim().equalsIgnoreCase("")&&meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1);}
                else {mealsummary.setText(noMeal);}
            }
        }

        //wednesday
        else if(dowInt==4) {

            if(timeInt<=10){
                Cursor res1 = myDatabase.viewWedBreakfastMealsSpecific("1");
                if (res1.getCount() != 0) {
                    StringBuffer buffer1 = new StringBuffer();
                    while (res1.moveToNext()) {
                        meal1 = res1.getString(1);
                    }}
                Cursor res2 = myDatabase.viewWedBreakfastMealsSpecific("2");
                if (res2.getCount() != 0){
                    StringBuffer buffer2 = new StringBuffer();
                    while (res2.moveToNext()) {
                        meal2 = res2.getString(1);
                    }}
                Cursor res3 = myDatabase.viewWedBreakfastMealsSpecific("3");
                if (res3.getCount() != 0) {
                    StringBuffer buffer3 = new StringBuffer();
                    while (res3.moveToNext()) {
                        meal3 = res3.getString(1);
                    }}
                Cursor res4 = myDatabase.viewWedBreakfastMealsSpecific("4");
                if (res4.getCount() != 0) {
                    StringBuffer buffer4 = new StringBuffer();
                    while (res4.moveToNext()) {
                        meal4 = res4.getString(1);
                    }}
                Cursor res5 = myDatabase.viewWedBreakfastMealsSpecific("5");
                if (res5.getCount() != 0) {
                    StringBuffer buffer5 = new StringBuffer();
                    while (res5.moveToNext()) {
                        meal5 = res5.getString(1);
                    }}

                if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1+", "+meal2+", "+meal3+", "+meal4+", "+meal5);}

                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1+", "+meal2+", "+meal3+", "+meal4);}

                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1+", "+meal2+", "+meal3);}

                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1+", "+meal2);}

                else if(!meal1.trim().equalsIgnoreCase("")&&meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1);}
                else {mealsummary.setText(noMeal);}
            }
            else if (timeInt<=14){
                Cursor res1 = myDatabase.viewWedLunchMealsSpecific("1");
                if (res1.getCount() != 0) {
                    StringBuffer buffer1 = new StringBuffer();
                    while (res1.moveToNext()) {
                        meal1 = res1.getString(1);
                    }}
                Cursor res2 = myDatabase.viewWedLunchMealsSpecific("2");
                if (res2.getCount() != 0){
                    StringBuffer buffer2 = new StringBuffer();
                    while (res2.moveToNext()) {
                        meal2 = res2.getString(1);
                    }}
                Cursor res3 = myDatabase.viewWedLunchMealsSpecific("3");
                if (res3.getCount() != 0) {
                    StringBuffer buffer3 = new StringBuffer();
                    while (res3.moveToNext()) {
                        meal3 = res3.getString(1);
                    }}
                Cursor res4 = myDatabase.viewWedLunchMealsSpecific("4");
                if (res4.getCount() != 0) {
                    StringBuffer buffer4 = new StringBuffer();
                    while (res4.moveToNext()) {
                        meal4 = res4.getString(1);
                    }}
                Cursor res5 = myDatabase.viewWedLunchMealsSpecific("5");
                if (res5.getCount() != 0) {
                    StringBuffer buffer5 = new StringBuffer();
                    while (res5.moveToNext()) {
                        meal5 = res5.getString(1);
                    }}

                if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1+", "+meal2+", "+meal3+", "+meal4+", "+meal5);}

                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1+", "+meal2+", "+meal3+", "+meal4);}

                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1+", "+meal2+", "+meal3);}

                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1+", "+meal2);}

                else if(!meal1.trim().equalsIgnoreCase("")&&meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1);}
                else {mealsummary.setText(noMeal);}
            }
            else{
                Cursor res1 = myDatabase.viewWedSupperMealsSpecific("1");
                if (res1.getCount() != 0) {
                    StringBuffer buffer1 = new StringBuffer();
                    while (res1.moveToNext()) {
                        meal1 = res1.getString(1);
                    }}
                Cursor res2 = myDatabase.viewWedSupperMealsSpecific("2");
                if (res2.getCount() != 0){
                    StringBuffer buffer2 = new StringBuffer();
                    while (res2.moveToNext()) {
                        meal2 = res2.getString(1);
                    }}
                Cursor res3 = myDatabase.viewWedSupperMealsSpecific("3");
                if (res3.getCount() != 0) {
                    StringBuffer buffer3 = new StringBuffer();
                    while (res3.moveToNext()) {
                        meal3 = res3.getString(1);
                    }}
                Cursor res4 = myDatabase.viewWedSupperMealsSpecific("4");
                if (res4.getCount() != 0) {
                    StringBuffer buffer4 = new StringBuffer();
                    while (res4.moveToNext()) {
                        meal4 = res4.getString(1);
                    }}
                Cursor res5 = myDatabase.viewWedSupperMealsSpecific("5");
                if (res5.getCount() != 0) {
                    StringBuffer buffer5 = new StringBuffer();
                    while (res5.moveToNext()) {
                        meal5 = res5.getString(1);
                    }}

                if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1+", "+meal2+", "+meal3+", "+meal4+", "+meal5);}

                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1+", "+meal2+", "+meal3+", "+meal4);}

                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1+", "+meal2+", "+meal3);}

                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1+", "+meal2);}

                else if(!meal1.trim().equalsIgnoreCase("")&&meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1);}
                else {mealsummary.setText(noMeal);}
            }
        }

        //thursday
        else if(dowInt==5) {

            if(timeInt<=10){
                Cursor res1 = myDatabase.viewThurBreakfastMealsSpecific("1");
                if (res1.getCount() != 0) {
                    StringBuffer buffer1 = new StringBuffer();
                    while (res1.moveToNext()) {
                        meal1 = res1.getString(1);
                    }}
                Cursor res2 = myDatabase.viewThurBreakfastMealsSpecific("2");
                if (res2.getCount() != 0){
                    StringBuffer buffer2 = new StringBuffer();
                    while (res2.moveToNext()) {
                        meal2 = res2.getString(1);
                    }}
                Cursor res3 = myDatabase.viewThurBreakfastMealsSpecific("3");
                if (res3.getCount() != 0) {
                    StringBuffer buffer3 = new StringBuffer();
                    while (res3.moveToNext()) {
                        meal3 = res3.getString(1);
                    }}
                Cursor res4 = myDatabase.viewThurBreakfastMealsSpecific("4");
                if (res4.getCount() != 0) {
                    StringBuffer buffer4 = new StringBuffer();
                    while (res4.moveToNext()) {
                        meal4 = res4.getString(1);
                    }}
                Cursor res5 = myDatabase.viewThurBreakfastMealsSpecific("5");
                if (res5.getCount() != 0) {
                    StringBuffer buffer5 = new StringBuffer();
                    while (res5.moveToNext()) {
                        meal5 = res5.getString(1);
                    }}

                if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1+", "+meal2+", "+meal3+", "+meal4+", "+meal5);}

                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1+", "+meal2+", "+meal3+", "+meal4);}

                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1+", "+meal2+", "+meal3);}

                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1+", "+meal2);}

                else if(!meal1.trim().equalsIgnoreCase("")&&meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1);}
                else {mealsummary.setText(noMeal);}
            }
            else if (timeInt<=14){
                Cursor res1 = myDatabase.viewThurLunchMealsSpecific("1");
                if (res1.getCount() != 0) {
                    StringBuffer buffer1 = new StringBuffer();
                    while (res1.moveToNext()) {
                        meal1 = res1.getString(1);
                    }}
                Cursor res2 = myDatabase.viewThurLunchMealsSpecific("2");
                if (res2.getCount() != 0){
                    StringBuffer buffer2 = new StringBuffer();
                    while (res2.moveToNext()) {
                        meal2 = res2.getString(1);
                    }}
                Cursor res3 = myDatabase.viewThurLunchMealsSpecific("3");
                if (res3.getCount() != 0) {
                    StringBuffer buffer3 = new StringBuffer();
                    while (res3.moveToNext()) {
                        meal3 = res3.getString(1);
                    }}
                Cursor res4 = myDatabase.viewThurLunchMealsSpecific("4");
                if (res4.getCount() != 0) {
                    StringBuffer buffer4 = new StringBuffer();
                    while (res4.moveToNext()) {
                        meal4 = res4.getString(1);
                    }}
                Cursor res5 = myDatabase.viewThurLunchMealsSpecific("5");
                if (res5.getCount() != 0) {
                    StringBuffer buffer5 = new StringBuffer();
                    while (res5.moveToNext()) {
                        meal5 = res5.getString(1);
                    }}

                if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1+", "+meal2+", "+meal3+", "+meal4+", "+meal5);}

                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1+", "+meal2+", "+meal3+", "+meal4);}

                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1+", "+meal2+", "+meal3);}

                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1+", "+meal2);}

                else if(!meal1.trim().equalsIgnoreCase("")&&meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1);}
                else {mealsummary.setText(noMeal);}
            }
            else{
                Cursor res1 = myDatabase.viewThurSupperMealsSpecific("1");
                if (res1.getCount() != 0) {
                    StringBuffer buffer1 = new StringBuffer();
                    while (res1.moveToNext()) {
                        meal1 = res1.getString(1);
                    }}
                Cursor res2 = myDatabase.viewThurSupperMealsSpecific("2");
                if (res2.getCount() != 0){
                    StringBuffer buffer2 = new StringBuffer();
                    while (res2.moveToNext()) {
                        meal2 = res2.getString(1);
                    }}
                Cursor res3 = myDatabase.viewThurSupperMealsSpecific("3");
                if (res3.getCount() != 0) {
                    StringBuffer buffer3 = new StringBuffer();
                    while (res3.moveToNext()) {
                        meal3 = res3.getString(1);
                    }}
                Cursor res4 = myDatabase.viewThurSupperMealsSpecific("4");
                if (res4.getCount() != 0) {
                    StringBuffer buffer4 = new StringBuffer();
                    while (res4.moveToNext()) {
                        meal4 = res4.getString(1);
                    }}
                Cursor res5 = myDatabase.viewThurSupperMealsSpecific("5");
                if (res5.getCount() != 0) {
                    StringBuffer buffer5 = new StringBuffer();
                    while (res5.moveToNext()) {
                        meal5 = res5.getString(1);
                    }}

                if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1+", "+meal2+", "+meal3+", "+meal4+", "+meal5);}

                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1+", "+meal2+", "+meal3+", "+meal4);}

                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1+", "+meal2+", "+meal3);}

                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1+", "+meal2);}

                else if(!meal1.trim().equalsIgnoreCase("")&&meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1);}
                else {mealsummary.setText(noMeal);}
            }
        }

        //friday
        else if(dowInt==6) {

            if(timeInt<=10){
                Cursor res1 = myDatabase.viewFriBreakfastMealsSpecific("1");
                if (res1.getCount() != 0) {
                    StringBuffer buffer1 = new StringBuffer();
                    while (res1.moveToNext()) {
                        meal1 = res1.getString(1);
                    }}
                Cursor res2 = myDatabase.viewFriBreakfastMealsSpecific("2");
                if (res2.getCount() != 0){
                    StringBuffer buffer2 = new StringBuffer();
                    while (res2.moveToNext()) {
                        meal2 = res2.getString(1);
                    }}
                Cursor res3 = myDatabase.viewFriBreakfastMealsSpecific("3");
                if (res3.getCount() != 0) {
                    StringBuffer buffer3 = new StringBuffer();
                    while (res3.moveToNext()) {
                        meal3 = res3.getString(1);
                    }}
                Cursor res4 = myDatabase.viewFriBreakfastMealsSpecific("4");
                if (res4.getCount() != 0) {
                    StringBuffer buffer4 = new StringBuffer();
                    while (res4.moveToNext()) {
                        meal4 = res4.getString(1);
                    }}
                Cursor res5 = myDatabase.viewFriBreakfastMealsSpecific("5");
                if (res5.getCount() != 0) {
                    StringBuffer buffer5 = new StringBuffer();
                    while (res5.moveToNext()) {
                        meal5 = res5.getString(1);
                    }}

                if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1+", "+meal2+", "+meal3+", "+meal4+", "+meal5);}

                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1+", "+meal2+", "+meal3+", "+meal4);}

                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1+", "+meal2+", "+meal3);}

                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1+", "+meal2);}

                else if(!meal1.trim().equalsIgnoreCase("")&&meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1);}
                else {mealsummary.setText(noMeal);}
            }
            else if (timeInt<=14){
                Cursor res1 = myDatabase.viewFriLunchMealsSpecific("1");
                if (res1.getCount() != 0) {
                    StringBuffer buffer1 = new StringBuffer();
                    while (res1.moveToNext()) {
                        meal1 = res1.getString(1);
                    }}
                Cursor res2 = myDatabase.viewFriLunchMealsSpecific("2");
                if (res2.getCount() != 0){
                    StringBuffer buffer2 = new StringBuffer();
                    while (res2.moveToNext()) {
                        meal2 = res2.getString(1);
                    }}
                Cursor res3 = myDatabase.viewFriLunchMealsSpecific("3");
                if (res3.getCount() != 0) {
                    StringBuffer buffer3 = new StringBuffer();
                    while (res3.moveToNext()) {
                        meal3 = res3.getString(1);
                    }}
                Cursor res4 = myDatabase.viewFriLunchMealsSpecific("4");
                if (res4.getCount() != 0) {
                    StringBuffer buffer4 = new StringBuffer();
                    while (res4.moveToNext()) {
                        meal4 = res4.getString(1);
                    }}
                Cursor res5 = myDatabase.viewFriLunchMealsSpecific("5");
                if (res5.getCount() != 0) {
                    StringBuffer buffer5 = new StringBuffer();
                    while (res5.moveToNext()) {
                        meal5 = res5.getString(1);
                    }}

                if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1+", "+meal2+", "+meal3+", "+meal4+", "+meal5);}

                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1+", "+meal2+", "+meal3+", "+meal4);}

                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1+", "+meal2+", "+meal3);}

                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1+", "+meal2);}

                else if(!meal1.trim().equalsIgnoreCase("")&&meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1);}
                else {mealsummary.setText(noMeal);}
            }
            else{
                Cursor res1 = myDatabase.viewFriSupperMealsSpecific("1");
                if (res1.getCount() != 0) {
                    StringBuffer buffer1 = new StringBuffer();
                    while (res1.moveToNext()) {
                        meal1 = res1.getString(1);
                    }}
                Cursor res2 = myDatabase.viewFriSupperMealsSpecific("2");
                if (res2.getCount() != 0){
                    StringBuffer buffer2 = new StringBuffer();
                    while (res2.moveToNext()) {
                        meal2 = res2.getString(1);
                    }}
                Cursor res3 = myDatabase.viewFriSupperMealsSpecific("3");
                if (res3.getCount() != 0) {
                    StringBuffer buffer3 = new StringBuffer();
                    while (res3.moveToNext()) {
                        meal3 = res3.getString(1);
                    }}
                Cursor res4 = myDatabase.viewFriSupperMealsSpecific("4");
                if (res4.getCount() != 0) {
                    StringBuffer buffer4 = new StringBuffer();
                    while (res4.moveToNext()) {
                        meal4 = res4.getString(1);
                    }}
                Cursor res5 = myDatabase.viewFriSupperMealsSpecific("5");
                if (res5.getCount() != 0) {
                    StringBuffer buffer5 = new StringBuffer();
                    while (res5.moveToNext()) {
                        meal5 = res5.getString(1);
                    }}

                if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1+", "+meal2+", "+meal3+", "+meal4+", "+meal5);}

                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1+", "+meal2+", "+meal3+", "+meal4);}

                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1+", "+meal2+", "+meal3);}

                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1+", "+meal2);}

                else if(!meal1.trim().equalsIgnoreCase("")&&meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1);}
                else {mealsummary.setText(noMeal);}
            }
        }

        //saturday
        else if(dowInt==7) {

            if(timeInt<=10){
                Cursor res1 = myDatabase.viewSatBreakfastMealsSpecific("1");
                if (res1.getCount() != 0) {
                    StringBuffer buffer1 = new StringBuffer();
                    while (res1.moveToNext()) {
                        meal1 = res1.getString(1);
                    }}
                Cursor res2 = myDatabase.viewSatBreakfastMealsSpecific("2");
                if (res2.getCount() != 0){
                    StringBuffer buffer2 = new StringBuffer();
                    while (res2.moveToNext()) {
                        meal2 = res2.getString(1);
                    }}
                Cursor res3 = myDatabase.viewSatBreakfastMealsSpecific("3");
                if (res3.getCount() != 0) {
                    StringBuffer buffer3 = new StringBuffer();
                    while (res3.moveToNext()) {
                        meal3 = res3.getString(1);
                    }}
                Cursor res4 = myDatabase.viewSatBreakfastMealsSpecific("4");
                if (res4.getCount() != 0) {
                    StringBuffer buffer4 = new StringBuffer();
                    while (res4.moveToNext()) {
                        meal4 = res4.getString(1);
                    }}
                Cursor res5 = myDatabase.viewSatBreakfastMealsSpecific("5");
                if (res5.getCount() != 0) {
                    StringBuffer buffer5 = new StringBuffer();
                    while (res5.moveToNext()) {
                        meal5 = res5.getString(1);
                    }}

                if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1+", "+meal2+", "+meal3+", "+meal4+", "+meal5);}

                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1+", "+meal2+", "+meal3+", "+meal4);}

                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1+", "+meal2+", "+meal3);}

                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1+", "+meal2);}

                else if(!meal1.trim().equalsIgnoreCase("")&&meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1);}
                else {mealsummary.setText(noMeal);}
            }
            else if (timeInt<=14){
                Cursor res1 = myDatabase.viewSatLunchMealsSpecific("1");
                if (res1.getCount() != 0) {
                    StringBuffer buffer1 = new StringBuffer();
                    while (res1.moveToNext()) {
                        meal1 = res1.getString(1);
                    }}
                Cursor res2 = myDatabase.viewSatLunchMealsSpecific("2");
                if (res2.getCount() != 0){
                    StringBuffer buffer2 = new StringBuffer();
                    while (res2.moveToNext()) {
                        meal2 = res2.getString(1);
                    }}
                Cursor res3 = myDatabase.viewSatLunchMealsSpecific("3");
                if (res3.getCount() != 0) {
                    StringBuffer buffer3 = new StringBuffer();
                    while (res3.moveToNext()) {
                        meal3 = res3.getString(1);
                    }}
                Cursor res4 = myDatabase.viewSatLunchMealsSpecific("4");
                if (res4.getCount() != 0) {
                    StringBuffer buffer4 = new StringBuffer();
                    while (res4.moveToNext()) {
                        meal4 = res4.getString(1);
                    }}
                Cursor res5 = myDatabase.viewSatLunchMealsSpecific("5");
                if (res5.getCount() != 0) {
                    StringBuffer buffer5 = new StringBuffer();
                    while (res5.moveToNext()) {
                        meal5 = res5.getString(1);
                    }}

                if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1+", "+meal2+", "+meal3+", "+meal4+", "+meal5);}

                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1+", "+meal2+", "+meal3+", "+meal4);}

                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1+", "+meal2+", "+meal3);}

                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1+", "+meal2);}

                else if(!meal1.trim().equalsIgnoreCase("")&&meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1);}
                else {mealsummary.setText(noMeal);}
            }
            else{
                Cursor res1 = myDatabase.viewSatSupperMealsSpecific("1");
                if (res1.getCount() != 0) {
                    StringBuffer buffer1 = new StringBuffer();
                    while (res1.moveToNext()) {
                        meal1 = res1.getString(1);
                    }}
                Cursor res2 = myDatabase.viewSatSupperMealsSpecific("2");
                if (res2.getCount() != 0){
                    StringBuffer buffer2 = new StringBuffer();
                    while (res2.moveToNext()) {
                        meal2 = res2.getString(1);
                    }}
                Cursor res3 = myDatabase.viewSatSupperMealsSpecific("3");
                if (res3.getCount() != 0) {
                    StringBuffer buffer3 = new StringBuffer();
                    while (res3.moveToNext()) {
                        meal3 = res3.getString(1);
                    }}
                Cursor res4 = myDatabase.viewSatSupperMealsSpecific("4");
                if (res4.getCount() != 0) {
                    StringBuffer buffer4 = new StringBuffer();
                    while (res4.moveToNext()) {
                        meal4 = res4.getString(1);
                    }}
                Cursor res5 = myDatabase.viewSatSupperMealsSpecific("5");
                if (res5.getCount() != 0) {
                    StringBuffer buffer5 = new StringBuffer();
                    while (res5.moveToNext()) {
                        meal5 = res5.getString(1);
                    }}

                if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1+", "+meal2+", "+meal3+", "+meal4+", "+meal5);}

                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1+", "+meal2+", "+meal3+", "+meal4);}

                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1+", "+meal2+", "+meal3);}

                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1+", "+meal2);}

                else if(!meal1.trim().equalsIgnoreCase("")&&meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1);}
                else {mealsummary.setText(noMeal);}
            }
        }

        //sunquickReferenceday
        else if(dowInt==1) {

            if(timeInt<=10){
                Cursor res1 = myDatabase.viewSunBreakfastMealsSpecific("1");
                if (res1.getCount() != 0) {
                    StringBuffer buffer1 = new StringBuffer();
                    while (res1.moveToNext()) {
                        meal1 = res1.getString(1);
                    }}
                Cursor res2 = myDatabase.viewSunBreakfastMealsSpecific("2");
                if (res2.getCount() != 0){
                    StringBuffer buffer2 = new StringBuffer();
                    while (res2.moveToNext()) {
                        meal2 = res2.getString(1);
                    }}
                Cursor res3 = myDatabase.viewSunBreakfastMealsSpecific("3");
                if (res3.getCount() != 0) {
                    StringBuffer buffer3 = new StringBuffer();
                    while (res3.moveToNext()) {
                        meal3 = res3.getString(1);
                    }}
                Cursor res4 = myDatabase.viewSunBreakfastMealsSpecific("4");
                if (res4.getCount() != 0) {
                    StringBuffer buffer4 = new StringBuffer();
                    while (res4.moveToNext()) {
                        meal4 = res4.getString(1);
                    }}
                Cursor res5 = myDatabase.viewSunBreakfastMealsSpecific("5");
                if (res5.getCount() != 0) {
                    StringBuffer buffer5 = new StringBuffer();
                    while (res5.moveToNext()) {
                        meal5 = res5.getString(1);
                    }}

                if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1+", "+meal2+", "+meal3+", "+meal4+", "+meal5);}

                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1+", "+meal2+", "+meal3+", "+meal4);}

                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1+", "+meal2+", "+meal3);}

                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1+", "+meal2);}

                else if(!meal1.trim().equalsIgnoreCase("")&&meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1);}
                else {mealsummary.setText(noMeal);}
            }
            else if (timeInt<=14){
                Cursor res1 = myDatabase.viewSunLunchMealsSpecific("1");
                if (res1.getCount() != 0) {
                    StringBuffer buffer1 = new StringBuffer();
                    while (res1.moveToNext()) {
                        meal1 = res1.getString(1);
                    }}
                Cursor res2 = myDatabase.viewSunLunchMealsSpecific("2");
                if (res2.getCount() != 0){
                    StringBuffer buffer2 = new StringBuffer();
                    while (res2.moveToNext()) {
                        meal2 = res2.getString(1);
                    }}
                Cursor res3 = myDatabase.viewSunLunchMealsSpecific("3");
                if (res3.getCount() != 0) {
                    StringBuffer buffer3 = new StringBuffer();
                    while (res3.moveToNext()) {
                        meal3 = res3.getString(1);
                    }}
                Cursor res4 = myDatabase.viewSunLunchMealsSpecific("4");
                if (res4.getCount() != 0) {
                    StringBuffer buffer4 = new StringBuffer();
                    while (res4.moveToNext()) {
                        meal4 = res4.getString(1);
                    }}
                Cursor res5 = myDatabase.viewSunLunchMealsSpecific("5");
                if (res5.getCount() != 0) {
                    StringBuffer buffer5 = new StringBuffer();
                    while (res5.moveToNext()) {
                        meal5 = res5.getString(1);
                    }}

                if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1+", "+meal2+", "+meal3+", "+meal4+", "+meal5);}

                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1+", "+meal2+", "+meal3+", "+meal4);}

                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1+", "+meal2+", "+meal3);}

                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1+", "+meal2);}

                else if(!meal1.trim().equalsIgnoreCase("")&&meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1);}
                else {mealsummary.setText(noMeal);}
            }
            else{
                Cursor res1 = myDatabase.viewSunSupperMealsSpecific("1");
                if (res1.getCount() != 0) {
                    StringBuffer buffer1 = new StringBuffer();
                    while (res1.moveToNext()) {
                        meal1 = res1.getString(1);
                    }}
                Cursor res2 = myDatabase.viewSunSupperMealsSpecific("2");
                if (res2.getCount() != 0){
                    StringBuffer buffer2 = new StringBuffer();
                    while (res2.moveToNext()) {
                        meal2 = res2.getString(1);
                    }}
                Cursor res3 = myDatabase.viewSunSupperMealsSpecific("3");
                if (res3.getCount() != 0) {
                    StringBuffer buffer3 = new StringBuffer();
                    while (res3.moveToNext()) {
                        meal3 = res3.getString(1);
                    }}
                Cursor res4 = myDatabase.viewSunSupperMealsSpecific("4");
                if (res4.getCount() != 0) {
                    StringBuffer buffer4 = new StringBuffer();
                    while (res4.moveToNext()) {
                        meal4 = res4.getString(1);
                    }}
                Cursor res5 = myDatabase.viewSunSupperMealsSpecific("5");
                if (res5.getCount() != 0) {
                    StringBuffer buffer5 = new StringBuffer();
                    while (res5.moveToNext()) {
                        meal5 = res5.getString(1);
                    }}

                if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1+", "+meal2+", "+meal3+", "+meal4+", "+meal5);}

                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1+", "+meal2+", "+meal3+", "+meal4);}

                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1+", "+meal2+", "+meal3);}

                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1+", "+meal2);}

                else if(!meal1.trim().equalsIgnoreCase("")&&meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    mealsummary.setText(mealReference+meal1);}
                else {mealsummary.setText(noMeal);}
            }
        }


    }

    //get time and date then change respective day text
    public void changeDay(){
        if(monthInt==0){monthStr = "Jan";}
        else if(monthInt==1){monthStr = "Feb";}
        else if(monthInt==2){monthStr = "Mar";}
        else if(monthInt==3){monthStr = "Apr";}
        else if(monthInt==4){monthStr = "May";}
        else if(monthInt==5){monthStr = "Jun";}
        else if(monthInt==6){monthStr = "Jul";}
        else if(monthInt==7){monthStr = "Aug";}
        else if(monthInt==8){monthStr = "Sep";}
        else if(monthInt==9){monthStr = "Oct";}
        else if(monthInt==10){monthStr = "Nov";}
        else if(monthInt==11){monthStr = "Dec";}

        if(dowInt==1){
//            sunday.setBackgroundResource(R.color.teal_700);
            sunday.setTextColor(Color.parseColor(myColor));
//            sunday.setTypeface(null, Typeface.BOLD);
            sunday.setText("Sunday\n"+dayStr.trim()+" "+monthStr.trim());
        }
        else if(dowInt==2){
//            monday.setBackgroundResource(R.color.teal_700);
            monday.setTextColor(Color.parseColor(myColor));
//            monday.setTypeface(null,Typeface.BOLD);
            monday.setText("Monday\n"+dayStr.trim()+" "+monthStr.trim());
        }
        else if(dowInt==3){
            tuesday.setTextColor(Color.parseColor(myColor));
//            tuesday.setTypeface(null,Typeface.BOLD);
            tuesday.setText("Tuesday\n"+dayStr.trim()+" "+monthStr.trim());
        }
        else if(dowInt==4){
            wednesday.setTextColor(Color.parseColor(myColor));
//            wednesday.setTypeface(null,Typeface.BOLD);
            wednesday.setText("Wednesday\n"+dayStr.trim()+" "+monthStr.trim());
        }
        else if(dowInt==5){
            thursday.setTextColor(Color.parseColor(myColor));
//            thursday.setTypeface(null,Typeface.BOLD);
            thursday.setText("Thursday\n"+dayStr.trim()+" "+monthStr.trim());

        }
        else if(dowInt==6){
            friday.setTextColor(Color.parseColor(myColor));
//            friday.setTypeface(null,Typeface.BOLD);
            friday.setText("Friday\n"+dayStr.trim()+" "+monthStr.trim());

        }
        else{
            saturday.setTextColor(Color.parseColor(myColor));
//            saturday.setTypeface(null,Typeface.BOLD);
            saturday.setText("Saturday\n"+dayStr.trim()+" "+monthStr.trim());

        }
    }

    //auto refresh depending with time
    private void doTheAutoRefresh() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Write code for your refresh logic
                Toast.makeText(getApplicationContext(),"Testing auto refresh",Toast.LENGTH_LONG).show();
                changeDay();
                quickReference();
            }
        }, 30000);
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        Toast.makeText(this, "The application has started", Toast.LENGTH_LONG).show();
//    }
    @Override
    public void onRestart() {
        super.onRestart();
        startMainActivity();
////            Toast.makeText(this, "restarted", Toast.LENGTH_LONG).show();
//        Toast.makeText(this, "Main activity started again", Toast.LENGTH_LONG).show();
    }
//onresume onpause onstp

//    @Override
//    public void onResume() {
//        super.onResume();
//        startMainActivity();
////        Toast.makeText(this, "The application has resumed", Toast.LENGTH_LONG).show();
//    }
//    @Override
//    public void onPause() {
//        super.onPause();
//        startMainActivity();
////        Toast.makeText(this, "The application has paused", Toast.LENGTH_LONG).show();
//    }
//    @Override
//    public void onStop() {
//        super.onStop();
//        Toast.makeText(this, "The application has stopped", Toast.LENGTH_LONG).show();
//    }

    //intent for todaymeals class
    public void mealsDashboard(){
        Intent intent = new Intent(getApplicationContext(),today_meal.class);
        startActivity(intent);
        overridePendingTransition( R.anim.left_in, R.anim.left_out);
        finish();
    }

    //SHORT MESSAGE TO USER
    public void myVictualAdapter(String title, String msg){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(msg);
        AlertDialog dialog =  builder.show();
//        TextView messageText = (TextView)dialog.findViewById(android.R.id.message);
//        messageText.setGravity(Gravity.CENTER);
    }

    //swipe
//    @Override
//    public boolean onTouchEvent(MotionEvent event)
//    {
//        switch(event.getAction())
//        {
//            case MotionEvent.ACTION_DOWN:
//                x1 = event.getX();
//                break;
//            case MotionEvent.ACTION_UP:
//                x2 = event.getX();
//                float deltaX = x2 - x1;
//                if (Math.abs(deltaX) > MIN_DISTANCE)
//                {
//                    Toast.makeText(this, "left2right swipe", Toast.LENGTH_SHORT).show ();
//                }
//                else
//                {
//                    // consider as something else - a screen tap for example
//                }
//                break;
//        }
//        return super.onTouchEvent(event);
//    }


    private boolean checkStoragePermission(){
        //check if storage permission is enabled or not and return true/false
        boolean result  = ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);
        return result;
    }
    public void requestStoragePermissionImport(){
        //request storage permission for import
//        ActivityCompat.requestPermissions(this, storagePermissions, STORAGE_REQUEST_CODE_IMPORT);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }
    }
    public void requestStoragePermissionExport(){
        //request storage permission for export

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
        }

//        ActivityCompat.requestPermissions(this, storagePermissions, STORAGE_REQUEST_CODE_EXPORT);
    }

    //import csv
    private void importCSV() {

        File folder_ = new File(Environment.getExternalStorageDirectory()+foldername);
        String folder = folder_+"/";
        //file name
        String monbreakfast = "MonBreakfast.csv";
        String monlunch = "MonLunch.csv";
        String monsupper = "MonSupper.csv";
        String tuebreakfast = "TueBreakfast.csv";
        String tuelunch = "TueLunch.csv";
        String tuesupper = "TueSupper.csv";
        String wedbreakfast = "WedBreakfast.csv";
        String wedlunch = "WedLunch.csv";
        String wedsupper = "WedSupper.csv";
        String thurbreakfast = "ThurBreakfast.csv";
        String thurlunch = "ThurLunch.csv";
        String thursupper = "ThurSupper.csv";
        String fribreakfast = "FriBreakfast.csv";
        String frilunch = "FriLunch.csv";
        String frisupper = "FriSupper.csv";
        String satbreakfast = "SatBreakfast.csv";
        String satlunch = "SatLunch.csv";
        String satsupper = "SatSupper.csv";
        String sunbreakfast = "SunBreakfast.csv";
        String sunlunch = "SunLunch.csv";
        String sunsupper = "SunSupper.csv";

////        //use same path and file name
        String monBreakfastPathAndName = folder + monbreakfast;
        File monbrkcsvFile = new File(monBreakfastPathAndName);
        String monLunchPathAndName = folder  + monlunch;
        File monlnchcsvFile = new File(monLunchPathAndName);
        String monSupperPathAndName = folder  + monsupper;
        File monspcsvFile = new File(monSupperPathAndName);

        String tueBreakfastPathAndName = folder  + tuebreakfast;
        File tuebrkcsvFile = new File(tueBreakfastPathAndName);
        String tueLunchPathAndName = folder  + tuelunch;
        File tuelnchcsvFile = new File(tueLunchPathAndName);
        String tueSupperPathAndName = folder  + tuesupper;
        File tuespcsvFile = new File(tueSupperPathAndName);

        String wedBreakfastPathAndName = folder  + wedbreakfast;
        File wedbrkcsvFile = new File(wedBreakfastPathAndName);
        String wedLunchPathAndName = folder  + wedlunch;
        File wedlnchcsvFile = new File(wedLunchPathAndName);
        String wedSupperPathAndName = folder  + wedsupper;
        File wedspcsvFile = new File(wedSupperPathAndName);

        String thurBreakfastPathAndName = folder  + thurbreakfast;
        File thurbrkcsvFile = new File(thurBreakfastPathAndName);
        String thurLunchPathAndName = folder  + thurlunch;
        File thurlnchcsvFile = new File(thurLunchPathAndName);
        String thurSupperPathAndName = folder  + thursupper;
        File thurspcsvFile = new File(thurSupperPathAndName);

        String friBreakfastPathAndName = folder  + fribreakfast;
        File fribrkcsvFile = new File(friBreakfastPathAndName);
        String friLunchPathAndName = folder  + frilunch;
        File frilnchcsvFile = new File(friLunchPathAndName);
        String friSupperPathAndName = folder  + frisupper;
        File frispcsvFile = new File(friSupperPathAndName);

        String satBreakfastPathAndName = folder  + satbreakfast;
        File satbrkcsvFile = new File(satBreakfastPathAndName);
        String satLunchPathAndName = folder  + satlunch;
        File satlnchcsvFile = new File(satLunchPathAndName);
        String satSupperPathAndName = folder  + satsupper;
        File satspcsvFile = new File(satSupperPathAndName);

        String sunBreakfastPathAndName = folder  + sunbreakfast;
        File sunbrkcsvFile = new File(sunBreakfastPathAndName);
        String sunLunchPathAndName = folder  + sunlunch;
        File sunlnchcsvFile = new File(sunLunchPathAndName);
        String sunSupperPathAndName = folder  + sunsupper;
        File sunspcsvFile = new File(sunSupperPathAndName);

        //check if mon breakfast backup exist
        if (monbrkcsvFile.exists()) {
            myDatabase.deleteMonBreakfastData();
            try {
                CSVReader monbrkcsvReader = new CSVReader(new FileReader(monbrkcsvFile.getAbsolutePath()));
                String[] nextLine;
                while ((nextLine = monbrkcsvReader.readNext()) != null) {
                    //same order as export
                    String meal = nextLine[1];
                    String nutrition = nextLine[2];
                    boolean data = myDatabase.insertMonBreakfastData(
                            "" + meal,
                            "" + nutrition);
                }
//                Toast.makeText(this,"Monday breakfast backup restored",Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(this, "" + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Monday breakfast backup not found", Toast.LENGTH_SHORT).show();

        }
        //check if mon lunch backup exist
        if (monlnchcsvFile.exists()) {
            myDatabase.deleteMonLunchData();
            try {
                CSVReader monlnccsvReader = new CSVReader(new FileReader(monlnchcsvFile.getAbsolutePath()));
                String[] nextLine;
                while ((nextLine = monlnccsvReader.readNext()) != null) {
                    //same order as export
                    String meal = nextLine[1];
                    String nutrition = nextLine[2];
                    boolean data = myDatabase.insertMonLunchData(
                            "" + meal,
                            "" + nutrition);
                }
//                Toast.makeText(this,"Monday lunch backup restored",Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(this, "" + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Monday lunch backup not found", Toast.LENGTH_SHORT).show();

        }
        //check if mon supper backup exist
        if (monspcsvFile.exists()) {
            myDatabase.deleteMonSupperData();
            try {
                CSVReader monspcsvReader = new CSVReader(new FileReader(monspcsvFile.getAbsolutePath()));
                String[] nextLine;
                while ((nextLine = monspcsvReader.readNext()) != null) {
                    //same order as export
                    String meal = nextLine[1];
                    String nutrition = nextLine[2];
                    boolean data = myDatabase.insertMonSupperData(
                            "" + meal,
                            "" + nutrition);
                }
//                Toast.makeText(this,"Monday supper backup restored",Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(this, "" + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Monday supper backup not found", Toast.LENGTH_SHORT).show();

        }
        //check if tue breakfast backup exist
        if (tuebrkcsvFile.exists()) {
            myDatabase.deleteTueBreakfastData();
            try {
                CSVReader tuebrkcsvReader = new CSVReader(new FileReader(tuebrkcsvFile.getAbsolutePath()));
                String[] nextLine;
                while ((nextLine = tuebrkcsvReader.readNext()) != null) {
                    //same order as export
                    String meal = nextLine[1];
                    String nutrition = nextLine[2];
                    boolean data = myDatabase.insertTueBreakfastData(
                            "" + meal,
                            "" + nutrition);
                }
//                Toast.makeText(this,"Tuesday breakfast backup restored",Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(this, "" + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Tuesday breakfast backup not found", Toast.LENGTH_SHORT).show();

        }
        //check if tue lunch backup exist
        if (tuelnchcsvFile.exists()) {
            myDatabase.deleteTueLunchData();
            try {
                CSVReader tuelnccsvReader = new CSVReader(new FileReader(tuelnchcsvFile.getAbsolutePath()));
                String[] nextLine;
                while ((nextLine = tuelnccsvReader.readNext()) != null) {
                    //same order as export
                    String meal = nextLine[1];
                    String nutrition = nextLine[2];
                    boolean data = myDatabase.insertTueLunchData(
                            "" + meal,
                            "" + nutrition);
                }
//                Toast.makeText(this,"Tuesday lunch backup restored",Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(this, "" + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Tuesday lunch backup not found", Toast.LENGTH_SHORT).show();

        }
        //check if tue supper backup exist
        if (tuespcsvFile.exists()) {
            myDatabase.deleteTueSupperData();
            try {
                CSVReader tuespcsvReader = new CSVReader(new FileReader(tuespcsvFile.getAbsolutePath()));
                String[] nextLine;
                while ((nextLine = tuespcsvReader.readNext()) != null) {
                    //same order as export
                    String meal = nextLine[1];
                    String nutrition = nextLine[2];
                    boolean data = myDatabase.insertTueSupperData(
                            "" + meal,
                            "" + nutrition);
                }
//                Toast.makeText(this,"Tuesday supper backup restored",Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(this, "" + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Tuesday supper backup not found", Toast.LENGTH_SHORT).show();

        }
        //check if wed breakfast backup exist
        if (wedbrkcsvFile.exists()) {
            myDatabase.deletewedBreakfastData();
            try {
                CSVReader wedbrkcsvReader = new CSVReader(new FileReader(wedbrkcsvFile.getAbsolutePath()));
                String[] nextLine;
                while ((nextLine = wedbrkcsvReader.readNext()) != null) {
                    //same order as export
                    String meal = nextLine[1];
                    String nutrition = nextLine[2];
                    boolean data = myDatabase.insertWedBreakfastData(
                            "" + meal,
                            "" + nutrition);
                }
//                Toast.makeText(this,"Wednesday breakfast backup restored",Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(this, "" + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Wednesday breakfast backup not found", Toast.LENGTH_SHORT).show();

        }
        //check if wed lunch backup exist
        if (wedlnchcsvFile.exists()) {
            myDatabase.deletewedLunchData();
            try {
                CSVReader wedlnccsvReader = new CSVReader(new FileReader(wedlnchcsvFile.getAbsolutePath()));
                String[] nextLine;
                while ((nextLine = wedlnccsvReader.readNext()) != null) {
                    //same order as export
                    String meal = nextLine[1];
                    String nutrition = nextLine[2];
                    boolean data = myDatabase.insertWedLunchData(
                            "" + meal,
                            "" + nutrition);
                }
//                Toast.makeText(this,"Wednesday lunch backup restored",Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(this, "" + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Wednesday lunch backup not found", Toast.LENGTH_SHORT).show();

        }
        //check if wed supper backup exist
        if (wedspcsvFile.exists()) {
            myDatabase.deletewedSupperData();
            try {
                CSVReader wedspcsvReader = new CSVReader(new FileReader(wedspcsvFile.getAbsolutePath()));
                String[] nextLine;
                while ((nextLine = wedspcsvReader.readNext()) != null) {
                    //same order as export
                    String meal = nextLine[1];
                    String nutrition = nextLine[2];
                    boolean data = myDatabase.insertWedSupperData(
                            "" + meal,
                            "" + nutrition);
                }
//                Toast.makeText(this,"Wednesday supper backup restored",Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(this, "" + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Wednesday supper backup not found", Toast.LENGTH_SHORT).show();

        }
        //check if thur breakfast backup exist
        if (thurbrkcsvFile.exists()) {
            myDatabase.deletethurBreakfastData();
            try {
                CSVReader thurbrkcsvReader = new CSVReader(new FileReader(thurbrkcsvFile.getAbsolutePath()));
                String[] nextLine;
                while ((nextLine = thurbrkcsvReader.readNext()) != null) {
                    //same order as export
                    String meal = nextLine[1];
                    String nutrition = nextLine[2];
                    boolean data = myDatabase.insertThurBreakfastData(
                            "" + meal,
                            "" + nutrition);
                }
//                Toast.makeText(this,"Thursday breakfast backup restored",Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(this, "" + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Thursday breakfast backup not found", Toast.LENGTH_SHORT).show();

        }
        //check if thur lunch backup exist
        if (thurlnchcsvFile.exists()) {
            myDatabase.deletethurLunchData();
            try {
                CSVReader thurlnccsvReader = new CSVReader(new FileReader(thurlnchcsvFile.getAbsolutePath()));
                String[] nextLine;
                while ((nextLine = thurlnccsvReader.readNext()) != null) {
                    //same order as export
                    String meal = nextLine[1];
                    String nutrition = nextLine[2];
                    boolean data = myDatabase.insertThurLunchData(
                            "" + meal,
                            "" + nutrition);
                }
//                Toast.makeText(this,"Thursday lunch backup restored",Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(this, "" + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Thursday lunch backup not found", Toast.LENGTH_SHORT).show();

        }
        //check if thur supper backup exist
        if (frispcsvFile.exists()) {

            myDatabase.deletethurSupperData();
            try {
                CSVReader thurspcsvReader = new CSVReader(new FileReader(thurspcsvFile.getAbsolutePath()));
                String[] nextLine;
                while ((nextLine = thurspcsvReader.readNext()) != null) {
                    //same order as export
                    String meal = nextLine[1];
                    String nutrition = nextLine[2];
                    boolean data = myDatabase.insertThurSupperData(
                            "" + meal,
                            "" + nutrition);
                }
//                Toast.makeText(this,"Thursday supper backup restored",Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(this, "" + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Thursday supper backup not found", Toast.LENGTH_SHORT).show();

        }
        //check if fri breakfast backup exist
        if (fribrkcsvFile.exists()) {
            myDatabase.deletefriBreakfastData();
            try {
                CSVReader fribrkcsvReader = new CSVReader(new FileReader(fribrkcsvFile.getAbsolutePath()));
                String[] nextLine;
                while ((nextLine = fribrkcsvReader.readNext()) != null) {
                    //same order as export
                    String meal = nextLine[1];
                    String nutrition = nextLine[2];
                    boolean data = myDatabase.insertFriBreakfastData(
                            "" + meal,
                            "" + nutrition);
                }
//                Toast.makeText(this,"Friday breakfast backup restored",Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(this, "" + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Friday breakfast backup not found", Toast.LENGTH_SHORT).show();

        }
        //check if fri lunch backup exist
        if (frilnchcsvFile.exists()) {
            myDatabase.deletefriLunchData();
            try {
                CSVReader frilnccsvReader = new CSVReader(new FileReader(frilnchcsvFile.getAbsolutePath()));
                String[] nextLine;
                while ((nextLine = frilnccsvReader.readNext()) != null) {
                    //same order as export
                    String meal = nextLine[1];
                    String nutrition = nextLine[2];
                    boolean data = myDatabase.insertFriLunchData(
                            "" + meal,
                            "" + nutrition);
                }
//                Toast.makeText(this,"Friday lunch backup restored",Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(this, "" + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Friday lunch backup not found", Toast.LENGTH_SHORT).show();

        }
        //check if fri supper backup exist
        if (frispcsvFile.exists()) {
            myDatabase.deletefriSupperData();
            try {
                CSVReader frispcsvReader = new CSVReader(new FileReader(frispcsvFile.getAbsolutePath()));
                String[] nextLine;
                while ((nextLine = frispcsvReader.readNext()) != null) {
                    //same order as export
                    String meal = nextLine[1];
                    String nutrition = nextLine[2];
                    boolean data = myDatabase.insertFriSupperData(
                            "" + meal,
                            "" + nutrition);
                }
//                Toast.makeText(this,"Friday supper backup restored",Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(this, "" + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Friday supper backup not found", Toast.LENGTH_SHORT).show();

        }
        //check if sat breakfast backup exist
        if (satbrkcsvFile.exists()) {
            myDatabase.deletesatBreakfastData();
            try {
                CSVReader satbrkcsvReader = new CSVReader(new FileReader(satbrkcsvFile.getAbsolutePath()));
                String[] nextLine;
                while ((nextLine = satbrkcsvReader.readNext()) != null) {
                    //same order as export
                    String meal = nextLine[1];
                    String nutrition = nextLine[2];
                    boolean data = myDatabase.insertSatBreakfastData(
                            "" + meal,
                            "" + nutrition);
                }
//                Toast.makeText(this,"Saturday breakfast backup restored",Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(this, "" + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Saturday breakfast backup not found", Toast.LENGTH_SHORT).show();

        }
        //check if sat lunch backup exist
        if (satlnchcsvFile.exists()) {
            myDatabase.deletesatLunchData();
            try {
                CSVReader satlnccsvReader = new CSVReader(new FileReader(satlnchcsvFile.getAbsolutePath()));
                String[] nextLine;
                while ((nextLine = satlnccsvReader.readNext()) != null) {
                    //same order as export
                    String meal = nextLine[1];
                    String nutrition = nextLine[2];
                    boolean data = myDatabase.insertSatLunchData(
                            "" + meal,
                            "" + nutrition);
                }
//                Toast.makeText(this,"Saturday lunch backup restored",Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(this, "" + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Saturday lunch backup not found", Toast.LENGTH_SHORT).show();

        }
        //check if sat supper backup exist
        if (satspcsvFile.exists()) {
            myDatabase.deletesatSupperData();
            try {
                CSVReader satspcsvReader = new CSVReader(new FileReader(satspcsvFile.getAbsolutePath()));
                String[] nextLine;
                while ((nextLine = satspcsvReader.readNext()) != null) {
                    //same order as export
                    String meal = nextLine[1];
                    String nutrition = nextLine[2];
                    boolean data = myDatabase.insertSatSupperData(
                            "" + meal,
                            "" + nutrition);
                }
//                Toast.makeText(this,"Saturday supper backup restored",Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(this, "" + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Saturday supper backup not found", Toast.LENGTH_SHORT).show();

        }
        //check if sun breakfast backup exist
        if (sunbrkcsvFile.exists()) {
            myDatabase.deletesunBreakfastData();
            try {
                CSVReader sunbrkcsvReader = new CSVReader(new FileReader(sunbrkcsvFile.getAbsolutePath()));
                String[] nextLine;
                while ((nextLine = sunbrkcsvReader.readNext()) != null) {
                    //same order as export
                    String meal = nextLine[1];
                    String nutrition = nextLine[2];
                    boolean data = myDatabase.insertSunBreakfastData(
                            "" + meal,
                            "" + nutrition);
                }
//                Toast.makeText(this,"Sunday breakfast ackup restored",Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(this, "" + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Sunday breakfast backup not found", Toast.LENGTH_SHORT).show();

        }
        //check if sun lunch backup exist
        if (sunlnchcsvFile.exists()) {
            myDatabase.deletesunLunchData();
            try {
                CSVReader sunlnccsvReader = new CSVReader(new FileReader(sunlnchcsvFile.getAbsolutePath()));
                String[] nextLine;
                while ((nextLine = sunlnccsvReader.readNext()) != null) {
                    //same order as export
                    String meal = nextLine[1];
                    String nutrition = nextLine[2];
                    boolean data = myDatabase.insertSunLunchData(
                            "" + meal,
                            "" + nutrition);
                }
//                Toast.makeText(this,"Sunday lunch backup restored",Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(this, "" + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Sunday lunch backup not found", Toast.LENGTH_SHORT).show();

        }
        //check if sun supper backup exist
        if (sunspcsvFile.exists()) {
            myDatabase.deletesunSupperData();
            try {
                CSVReader sunspcsvReader = new CSVReader(new FileReader(sunspcsvFile.getAbsolutePath()));
                String[] nextLine;
                while ((nextLine = sunspcsvReader.readNext()) != null) {
                    //same order as export
                    String meal = nextLine[1];
                    String nutrition = nextLine[2];
                    boolean data = myDatabase.insertSunSupperData(
                            "" + meal,
                            "" + nutrition);
                }
//                Toast.makeText(this,"Sunday supper backup restored",Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(this, "" + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Sunday supper backup not found", Toast.LENGTH_SHORT).show();
        }
        myToast("Meal plan restored successfully", Toast.LENGTH_SHORT);
        startMainActivity();
    }
//
    //export csv
    private void exportCSV() {
////        //path of csv file
        File folder = new File(Environment.getExternalStorageDirectory()+foldername); //SQLiteBackup folder name
        boolean isFolderCreated = false;
        if(!folder.exists()){
            isFolderCreated = folder.mkdir(); //creates folder is not avail

        }
        Log.d("CSC_TAG","exportCSV: "+isFolderCreated);
////        //file name
        String monbreakfast = "MonBreakfast.csv";
        String monlunch = "MonLunch.csv";
        String monsupper = "MonSupper.csv";
        String tuebreakfast = "TueBreakfast.csv";
        String tuelunch = "TueLunch.csv";
        String tuesupper = "TueSupper.csv";
        String wedbreakfast = "WedBreakfast.csv";
        String wedlunch = "WedLunch.csv";
        String wedsupper = "WedSupper.csv";
        String thurbreakfast = "ThurBreakfast.csv";
        String thurlunch = "ThurLunch.csv";
        String thursupper = "ThurSupper.csv";
        String fribreakfast = "FriBreakfast.csv";
        String frilunch = "FriLunch.csv";
        String frisupper = "FriSupper.csv";
        String satbreakfast = "SatBreakfast.csv";
        String satlunch = "SatLunch.csv";
        String satsupper = "SatSupper.csv";
        String sunbreakfast = "SunBreakfast.csv";
        String sunlunch = "SunLunch.csv";
        String sunsupper = "SunSupper.csv";
////        //complete path
        String monBreakfastPathAndname = folder.toString()+"/"+monbreakfast;
        String monLunchPathAndname = folder.toString()+"/"+monlunch;
        String monSupperPathAndname = folder.toString()+"/"+monsupper;
        String tueBreakfastPathAndname = folder.toString()+"/"+tuebreakfast;
        String tueLunchPathAndname = folder.toString()+"/"+tuelunch;
        String tueSupperPathAndname = folder.toString()+"/"+tuesupper;
        String wedBreakfastPathAndname = folder.toString()+"/"+wedbreakfast;
        String wedLunchPathAndname = folder.toString()+"/"+wedlunch;
        String wedSupperPathAndname = folder.toString()+"/"+wedsupper;
        String thurBreakfastPathAndname = folder.toString()+"/"+thurbreakfast;
        String thurLunchPathAndname = folder.toString()+"/"+thurlunch;
        String thurSupperPathAndname = folder.toString()+"/"+thursupper;
        String friBreakfastPathAndname = folder.toString()+"/"+fribreakfast;
        String friLunchPathAndname = folder.toString()+"/"+frilunch;
        String friSupperPathAndname = folder.toString()+"/"+frisupper;
        String satBreakfastPathAndname = folder.toString()+"/"+satbreakfast;
        String satLunchPathAndname = folder.toString()+"/"+satlunch;
        String satSupperPathAndname = folder.toString()+"/"+satsupper;
        String sunBreakfastPathAndname = folder.toString()+"/"+sunbreakfast;
        String sunLunchPathAndname = folder.toString()+"/"+sunlunch;
        String sunSupperPathAndname = folder.toString()+"/"+sunsupper;
////        //get  records
        Cursor monBrk =  myDatabase.viewMonBreakfastMeals();
        Cursor monLnch =  myDatabase.viewMonLunchMeals();
        Cursor monSp =  myDatabase.viewMonSupperMeals();

        Cursor TueBrk =  myDatabase.viewTueBreakfastMeals();
        Cursor TueLnch =  myDatabase.viewTueLunchMeals();
        Cursor TueSp =  myDatabase.viewTueSupperMeals();

        Cursor WedBrk =  myDatabase.viewWedBreakfastMeals();
        Cursor WedLnch =  myDatabase.viewWedLunchMeals();
        Cursor WedSp =  myDatabase.viewWedSupperMeals();

        Cursor ThurBrk =  myDatabase.viewThurBreakfastMeals();
        Cursor ThurLnch =  myDatabase.viewThurLunchMeals();
        Cursor ThurSp =  myDatabase.viewThurSupperMeals();

        Cursor FriBrk =  myDatabase.viewFriBreakfastMeals();
        Cursor FriLnch =  myDatabase.viewFriLunchMeals();
        Cursor FriSp =  myDatabase.viewFriSupperMeals();

        Cursor SatBrk =  myDatabase.viewSatBreakfastMeals();
        Cursor SatLnch =  myDatabase.viewSatLunchMeals();
        Cursor SatSp =  myDatabase.viewSatSupperMeals();

        Cursor SunBrk =  myDatabase.viewSunBreakfastMeals();
        Cursor SunLnch =  myDatabase.viewSunLunchMeals();
        Cursor SunSp =  myDatabase.viewSunSupperMeals();

        try{
            //mon breakfast
            FileWriter monbrk =new FileWriter(monBreakfastPathAndname);
            while (monBrk.moveToNext()) {
                monbrk.append("" + monBrk.getString(0));//id
                monbrk.append(",");
                monbrk.append("" + monBrk.getString(1));//meal
                monbrk.append(",");
                monbrk.append("" + monBrk.getString(2));//nutrition
                monbrk.append("\n");
            }
            monbrk.flush();
            monbrk.close();
            //mon lunch
            FileWriter monlnch =new FileWriter(monLunchPathAndname);
            while (monLnch.moveToNext()) {
                monlnch.append("" + monLnch.getString(0));//id
                monlnch.append(",");
                monlnch.append("" + monLnch.getString(1));//meal
                monlnch.append(",");
                monlnch.append("" + monLnch.getString(2));//nutrition
                monlnch.append("\n");
            }
            monlnch.flush();monlnch.close();
            //mon supper
            FileWriter monspr =new FileWriter(monSupperPathAndname);
            while (monSp.moveToNext()){
                monspr.append(""+monSp.getString(0));//id
                monspr.append(",");
                monspr.append(""+monSp.getString(1));//meal
                monspr.append(",");
                monspr.append(""+monSp.getString(2));//nutrition
                monspr.append("\n");
            }
            monspr.flush();
            monspr.close();


            //mon breakfast
            FileWriter tuebrk =new FileWriter(tueBreakfastPathAndname);
            while (TueBrk.moveToNext()) {
                tuebrk.append("" + TueBrk.getString(0));//id
                tuebrk.append(",");
                tuebrk.append("" + TueBrk.getString(1));//meal
                tuebrk.append(",");
                tuebrk.append("" + TueBrk.getString(2));//nutrition
                tuebrk.append("\n");
            }
            tuebrk.flush();
            tuebrk.close();
            //tue lunch
            FileWriter tuelnch =new FileWriter(tueLunchPathAndname);
            while (TueLnch.moveToNext()) {
                tuelnch.append("" + TueLnch.getString(0));//id
                tuelnch.append(",");
                tuelnch.append("" + TueLnch.getString(1));//meal
                tuelnch.append(",");
                tuelnch.append("" + TueLnch.getString(2));//nutrition
                tuelnch.append("\n");
            }
            tuelnch.flush();tuelnch.close();
            //tue supper
            FileWriter tuespr =new FileWriter(tueSupperPathAndname);
            while (TueSp.moveToNext()){
                tuespr.append(""+TueSp.getString(0));//id
                tuespr.append(",");
                tuespr.append(""+TueSp.getString(1));//meal
                tuespr.append(",");
                tuespr.append(""+TueSp.getString(2));//nutrition
                tuespr.append("\n");
            }
            tuespr.flush();
            tuespr.close();


            //wed breakfast
            FileWriter wedbrk =new FileWriter(wedBreakfastPathAndname);
            while (WedBrk.moveToNext()) {
                wedbrk.append("" + WedBrk.getString(0));//id
                wedbrk.append(",");
                wedbrk.append("" + WedBrk.getString(1));//meal
                wedbrk.append(",");
                wedbrk.append("" + WedBrk.getString(2));//nutrition
                wedbrk.append("\n");
            }
            wedbrk.flush();
            wedbrk.close();
            //wed lunch
            FileWriter wedlnch =new FileWriter(wedLunchPathAndname);
            while (WedLnch.moveToNext()) {
                wedlnch.append("" + WedLnch.getString(0));//id
                wedlnch.append(",");
                wedlnch.append("" + WedLnch.getString(1));//meal
                wedlnch.append(",");
                wedlnch.append("" + WedLnch.getString(2));//nutrition
                wedlnch.append("\n");
            }
            wedlnch.flush();wedlnch.close();
            //wed supper
            FileWriter wedspr =new FileWriter(wedSupperPathAndname);
            while (WedSp.moveToNext()){
                wedspr.append(""+WedSp.getString(0));//id
                wedspr.append(",");
                wedspr.append(""+WedSp.getString(1));//meal
                wedspr.append(",");
                wedspr.append(""+WedSp.getString(2));//nutrition
                wedspr.append("\n");
            }
            wedspr.flush();
            wedspr.close();


            //thur breakfast
            FileWriter thurbrk =new FileWriter(thurBreakfastPathAndname);
            while (ThurBrk.moveToNext()) {
                thurbrk.append("" + ThurBrk.getString(0));//id
                thurbrk.append(",");
                thurbrk.append("" + ThurBrk.getString(1));//meal
                thurbrk.append(",");
                thurbrk.append("" + ThurBrk.getString(2));//nutrition
                thurbrk.append("\n");
            }
            thurbrk.flush();
            thurbrk.close();
            //thur lunch
            FileWriter thurlnch =new FileWriter(thurLunchPathAndname);
            while (ThurLnch.moveToNext()) {
                thurlnch.append("" + ThurLnch.getString(0));//id
                thurlnch.append(",");
                thurlnch.append("" + ThurLnch.getString(1));//meal
                thurlnch.append(",");
                thurlnch.append("" + ThurLnch.getString(2));//nutrition
                thurlnch.append("\n");
            }
            thurlnch.flush();thurlnch.close();
            //thur supper
            FileWriter thurspr =new FileWriter(thurSupperPathAndname);
            while (ThurSp.moveToNext()){
                thurspr.append(""+ThurSp.getString(0));//id
                thurspr.append(",");
                thurspr.append(""+ThurSp.getString(1));//meal
                thurspr.append(",");
                thurspr.append(""+ThurSp.getString(2));//nutrition
                thurspr.append("\n");
            }
            thurspr.flush();
            thurspr.close();


            //fri breakfast
            FileWriter fribrk =new FileWriter(friBreakfastPathAndname);
            while (FriBrk.moveToNext()) {
                fribrk.append("" + FriBrk.getString(0));//id
                fribrk.append(",");
                fribrk.append("" + FriBrk.getString(1));//meal
                fribrk.append(",");
                fribrk.append("" + FriBrk.getString(2));//nutrition
                fribrk.append("\n");
            }
            fribrk.flush();
            fribrk.close();
            //fri lunch
            FileWriter frilnch =new FileWriter(friLunchPathAndname);
            while (FriLnch.moveToNext()) {
                frilnch.append("" + FriLnch.getString(0));//id
                frilnch.append(",");
                frilnch.append("" + FriLnch.getString(1));//meal
                frilnch.append(",");
                frilnch.append("" + FriLnch.getString(2));//nutrition
                frilnch.append("\n");
            }
            frilnch.flush();frilnch.close();
            //fri supper
            FileWriter frispr =new FileWriter(friSupperPathAndname);
            while (FriSp.moveToNext()){
                frispr.append(""+FriSp.getString(0));//id
                frispr.append(",");
                frispr.append(""+FriSp.getString(1));//meal
                frispr.append(",");
                frispr.append(""+FriSp.getString(2));//nutrition
                frispr.append("\n");
            }
            frispr.flush();
            frispr.close();


            //sat breakfast
            FileWriter satbrk =new FileWriter(satBreakfastPathAndname);
            while (SatBrk.moveToNext()) {
                satbrk.append("" + SatBrk.getString(0));//id
                satbrk.append(",");
                satbrk.append("" + SatBrk.getString(1));//meal
                satbrk.append(",");
                satbrk.append("" + SatBrk.getString(2));//nutrition
                satbrk.append("\n");
            }
            satbrk.flush();
            satbrk.close();
            //sat lunch
            FileWriter satlnch =new FileWriter(satLunchPathAndname);
            while (SatLnch.moveToNext()) {
                satlnch.append("" + SatLnch.getString(0));//id
                satlnch.append(",");
                satlnch.append("" + SatLnch.getString(1));//meal
                satlnch.append(",");
                satlnch.append("" + SatLnch.getString(2));//nutrition
                satlnch.append("\n");
            }
            satlnch.flush();satlnch.close();
            //sat supper
            FileWriter satspr =new FileWriter(satSupperPathAndname);
            while (SatSp.moveToNext()){
                satspr.append(""+SatSp.getString(0));//id
                satspr.append(",");
                satspr.append(""+SatSp.getString(1));//meal
                satspr.append(",");
                satspr.append(""+SatSp.getString(2));//nutrition
                satspr.append("\n");
            }
            satspr.flush();
            satspr.close();


            //sun breakfast
            FileWriter sunbrk =new FileWriter(sunBreakfastPathAndname);
            while (SunBrk.moveToNext()) {
                sunbrk.append("" + SunBrk.getString(0));//id
                sunbrk.append(",");
                sunbrk.append("" + SunBrk.getString(1));//meal
                sunbrk.append(",");
                sunbrk.append("" + SunBrk.getString(2));//nutrition
                sunbrk.append("\n");
            }
            sunbrk.flush();
            sunbrk.close();
            //sun lunch
            FileWriter sunlnch =new FileWriter(sunLunchPathAndname);
            while (SunLnch.moveToNext()) {
                sunlnch.append("" + SunLnch.getString(0));//id
                sunlnch.append(",");
                sunlnch.append("" + SunLnch.getString(1));//meal
                sunlnch.append(",");
                sunlnch.append("" + SunLnch.getString(2));//nutrition
                sunlnch.append("\n");
            }
            sunlnch.flush();sunlnch.close();
            //sun supper
            FileWriter sunspr =new FileWriter(sunSupperPathAndname);
            while (SunSp.moveToNext()){
                sunspr.append(""+SunSp.getString(0));//id
                sunspr.append(",");
                sunspr.append(""+SunSp.getString(1));//meal
                sunspr.append(",");
                sunspr.append(""+SunSp.getString(2));//nutrition
                sunspr.append("\n");
            }
            sunspr.flush();
            sunspr.close();
            myToast("Meal plan backup successful, path "+foldername.substring(1),Toast.LENGTH_LONG);
        }
        catch (Exception e){
////            // if there is any error
            Toast.makeText(this,""+e.getMessage(),Toast.LENGTH_LONG).show();
//
        }
    }

    private void exportCSVWithoutMessage() {
////        //path of csv file
        File folder = new File(Environment.getExternalStorageDirectory()+foldername); //SQLiteBackup folder name
        boolean isFolderCreated = false;
        if(!folder.exists()){
            isFolderCreated = folder.mkdir(); //creates folder is not avail

        }
        Log.d("CSC_TAG","exportCSV: "+isFolderCreated);
////        //file name
        String monbreakfast = "MonBreakfast.csv";
        String monlunch = "MonLunch.csv";
        String monsupper = "MonSupper.csv";
        String tuebreakfast = "TueBreakfast.csv";
        String tuelunch = "TueLunch.csv";
        String tuesupper = "TueSupper.csv";
        String wedbreakfast = "WedBreakfast.csv";
        String wedlunch = "WedLunch.csv";
        String wedsupper = "WedSupper.csv";
        String thurbreakfast = "ThurBreakfast.csv";
        String thurlunch = "ThurLunch.csv";
        String thursupper = "ThurSupper.csv";
        String fribreakfast = "FriBreakfast.csv";
        String frilunch = "FriLunch.csv";
        String frisupper = "FriSupper.csv";
        String satbreakfast = "SatBreakfast.csv";
        String satlunch = "SatLunch.csv";
        String satsupper = "SatSupper.csv";
        String sunbreakfast = "SunBreakfast.csv";
        String sunlunch = "SunLunch.csv";
        String sunsupper = "SunSupper.csv";
////        //complete path
        String monBreakfastPathAndname = folder.toString()+"/"+monbreakfast;
        String monLunchPathAndname = folder.toString()+"/"+monlunch;
        String monSupperPathAndname = folder.toString()+"/"+monsupper;
        String tueBreakfastPathAndname = folder.toString()+"/"+tuebreakfast;
        String tueLunchPathAndname = folder.toString()+"/"+tuelunch;
        String tueSupperPathAndname = folder.toString()+"/"+tuesupper;
        String wedBreakfastPathAndname = folder.toString()+"/"+wedbreakfast;
        String wedLunchPathAndname = folder.toString()+"/"+wedlunch;
        String wedSupperPathAndname = folder.toString()+"/"+wedsupper;
        String thurBreakfastPathAndname = folder.toString()+"/"+thurbreakfast;
        String thurLunchPathAndname = folder.toString()+"/"+thurlunch;
        String thurSupperPathAndname = folder.toString()+"/"+thursupper;
        String friBreakfastPathAndname = folder.toString()+"/"+fribreakfast;
        String friLunchPathAndname = folder.toString()+"/"+frilunch;
        String friSupperPathAndname = folder.toString()+"/"+frisupper;
        String satBreakfastPathAndname = folder.toString()+"/"+satbreakfast;
        String satLunchPathAndname = folder.toString()+"/"+satlunch;
        String satSupperPathAndname = folder.toString()+"/"+satsupper;
        String sunBreakfastPathAndname = folder.toString()+"/"+sunbreakfast;
        String sunLunchPathAndname = folder.toString()+"/"+sunlunch;
        String sunSupperPathAndname = folder.toString()+"/"+sunsupper;
////        //get  records
        Cursor monBrk =  myDatabase.viewMonBreakfastMeals();
        Cursor monLnch =  myDatabase.viewMonLunchMeals();
        Cursor monSp =  myDatabase.viewMonSupperMeals();

        Cursor TueBrk =  myDatabase.viewTueBreakfastMeals();
        Cursor TueLnch =  myDatabase.viewTueLunchMeals();
        Cursor TueSp =  myDatabase.viewTueSupperMeals();

        Cursor WedBrk =  myDatabase.viewWedBreakfastMeals();
        Cursor WedLnch =  myDatabase.viewWedLunchMeals();
        Cursor WedSp =  myDatabase.viewWedSupperMeals();

        Cursor ThurBrk =  myDatabase.viewThurBreakfastMeals();
        Cursor ThurLnch =  myDatabase.viewThurLunchMeals();
        Cursor ThurSp =  myDatabase.viewThurSupperMeals();

        Cursor FriBrk =  myDatabase.viewFriBreakfastMeals();
        Cursor FriLnch =  myDatabase.viewFriLunchMeals();
        Cursor FriSp =  myDatabase.viewFriSupperMeals();

        Cursor SatBrk =  myDatabase.viewSatBreakfastMeals();
        Cursor SatLnch =  myDatabase.viewSatLunchMeals();
        Cursor SatSp =  myDatabase.viewSatSupperMeals();

        Cursor SunBrk =  myDatabase.viewSunBreakfastMeals();
        Cursor SunLnch =  myDatabase.viewSunLunchMeals();
        Cursor SunSp =  myDatabase.viewSunSupperMeals();

        try{
            //mon breakfast
            FileWriter monbrk =new FileWriter(monBreakfastPathAndname);
            while (monBrk.moveToNext()) {
                monbrk.append("" + monBrk.getString(0));//id
                monbrk.append(",");
                monbrk.append("" + monBrk.getString(1));//meal
                monbrk.append(",");
                monbrk.append("" + monBrk.getString(2));//nutrition
                monbrk.append("\n");
            }
            monbrk.flush();
            monbrk.close();
            //mon lunch
            FileWriter monlnch =new FileWriter(monLunchPathAndname);
            while (monLnch.moveToNext()) {
                monlnch.append("" + monLnch.getString(0));//id
                monlnch.append(",");
                monlnch.append("" + monLnch.getString(1));//meal
                monlnch.append(",");
                monlnch.append("" + monLnch.getString(2));//nutrition
                monlnch.append("\n");
            }
            monlnch.flush();monlnch.close();
            //mon supper
            FileWriter monspr =new FileWriter(monSupperPathAndname);
            while (monSp.moveToNext()){
                monspr.append(""+monSp.getString(0));//id
                monspr.append(",");
                monspr.append(""+monSp.getString(1));//meal
                monspr.append(",");
                monspr.append(""+monSp.getString(2));//nutrition
                monspr.append("\n");
            }
            monspr.flush();
            monspr.close();


            //mon breakfast
            FileWriter tuebrk =new FileWriter(tueBreakfastPathAndname);
            while (TueBrk.moveToNext()) {
                tuebrk.append("" + TueBrk.getString(0));//id
                tuebrk.append(",");
                tuebrk.append("" + TueBrk.getString(1));//meal
                tuebrk.append(",");
                tuebrk.append("" + TueBrk.getString(2));//nutrition
                tuebrk.append("\n");
            }
            tuebrk.flush();
            tuebrk.close();
            //tue lunch
            FileWriter tuelnch =new FileWriter(tueLunchPathAndname);
            while (TueLnch.moveToNext()) {
                tuelnch.append("" + TueLnch.getString(0));//id
                tuelnch.append(",");
                tuelnch.append("" + TueLnch.getString(1));//meal
                tuelnch.append(",");
                tuelnch.append("" + TueLnch.getString(2));//nutrition
                tuelnch.append("\n");
            }
            tuelnch.flush();tuelnch.close();
            //tue supper
            FileWriter tuespr =new FileWriter(tueSupperPathAndname);
            while (TueSp.moveToNext()){
                tuespr.append(""+TueSp.getString(0));//id
                tuespr.append(",");
                tuespr.append(""+TueSp.getString(1));//meal
                tuespr.append(",");
                tuespr.append(""+TueSp.getString(2));//nutrition
                tuespr.append("\n");
            }
            tuespr.flush();
            tuespr.close();


            //wed breakfast
            FileWriter wedbrk =new FileWriter(wedBreakfastPathAndname);
            while (WedBrk.moveToNext()) {
                wedbrk.append("" + WedBrk.getString(0));//id
                wedbrk.append(",");
                wedbrk.append("" + WedBrk.getString(1));//meal
                wedbrk.append(",");
                wedbrk.append("" + WedBrk.getString(2));//nutrition
                wedbrk.append("\n");
            }
            wedbrk.flush();
            wedbrk.close();
            //wed lunch
            FileWriter wedlnch =new FileWriter(wedLunchPathAndname);
            while (WedLnch.moveToNext()) {
                wedlnch.append("" + WedLnch.getString(0));//id
                wedlnch.append(",");
                wedlnch.append("" + WedLnch.getString(1));//meal
                wedlnch.append(",");
                wedlnch.append("" + WedLnch.getString(2));//nutrition
                wedlnch.append("\n");
            }
            wedlnch.flush();wedlnch.close();
            //wed supper
            FileWriter wedspr =new FileWriter(wedSupperPathAndname);
            while (WedSp.moveToNext()){
                wedspr.append(""+WedSp.getString(0));//id
                wedspr.append(",");
                wedspr.append(""+WedSp.getString(1));//meal
                wedspr.append(",");
                wedspr.append(""+WedSp.getString(2));//nutrition
                wedspr.append("\n");
            }
            wedspr.flush();
            wedspr.close();


            //thur breakfast
            FileWriter thurbrk =new FileWriter(thurBreakfastPathAndname);
            while (ThurBrk.moveToNext()) {
                thurbrk.append("" + ThurBrk.getString(0));//id
                thurbrk.append(",");
                thurbrk.append("" + ThurBrk.getString(1));//meal
                thurbrk.append(",");
                thurbrk.append("" + ThurBrk.getString(2));//nutrition
                thurbrk.append("\n");
            }
            thurbrk.flush();
            thurbrk.close();
            //thur lunch
            FileWriter thurlnch =new FileWriter(thurLunchPathAndname);
            while (ThurLnch.moveToNext()) {
                thurlnch.append("" + ThurLnch.getString(0));//id
                thurlnch.append(",");
                thurlnch.append("" + ThurLnch.getString(1));//meal
                thurlnch.append(",");
                thurlnch.append("" + ThurLnch.getString(2));//nutrition
                thurlnch.append("\n");
            }
            thurlnch.flush();thurlnch.close();
            //thur supper
            FileWriter thurspr =new FileWriter(thurSupperPathAndname);
            while (ThurSp.moveToNext()){
                thurspr.append(""+ThurSp.getString(0));//id
                thurspr.append(",");
                thurspr.append(""+ThurSp.getString(1));//meal
                thurspr.append(",");
                thurspr.append(""+ThurSp.getString(2));//nutrition
                thurspr.append("\n");
            }
            thurspr.flush();
            thurspr.close();


            //fri breakfast
            FileWriter fribrk =new FileWriter(friBreakfastPathAndname);
            while (FriBrk.moveToNext()) {
                fribrk.append("" + FriBrk.getString(0));//id
                fribrk.append(",");
                fribrk.append("" + FriBrk.getString(1));//meal
                fribrk.append(",");
                fribrk.append("" + FriBrk.getString(2));//nutrition
                fribrk.append("\n");
            }
            fribrk.flush();
            fribrk.close();
            //fri lunch
            FileWriter frilnch =new FileWriter(friLunchPathAndname);
            while (FriLnch.moveToNext()) {
                frilnch.append("" + FriLnch.getString(0));//id
                frilnch.append(",");
                frilnch.append("" + FriLnch.getString(1));//meal
                frilnch.append(",");
                frilnch.append("" + FriLnch.getString(2));//nutrition
                frilnch.append("\n");
            }
            frilnch.flush();frilnch.close();
            //fri supper
            FileWriter frispr =new FileWriter(friSupperPathAndname);
            while (FriSp.moveToNext()){
                frispr.append(""+FriSp.getString(0));//id
                frispr.append(",");
                frispr.append(""+FriSp.getString(1));//meal
                frispr.append(",");
                frispr.append(""+FriSp.getString(2));//nutrition
                frispr.append("\n");
            }
            frispr.flush();
            frispr.close();


            //sat breakfast
            FileWriter satbrk =new FileWriter(satBreakfastPathAndname);
            while (SatBrk.moveToNext()) {
                satbrk.append("" + SatBrk.getString(0));//id
                satbrk.append(",");
                satbrk.append("" + SatBrk.getString(1));//meal
                satbrk.append(",");
                satbrk.append("" + SatBrk.getString(2));//nutrition
                satbrk.append("\n");
            }
            satbrk.flush();
            satbrk.close();
            //sat lunch
            FileWriter satlnch =new FileWriter(satLunchPathAndname);
            while (SatLnch.moveToNext()) {
                satlnch.append("" + SatLnch.getString(0));//id
                satlnch.append(",");
                satlnch.append("" + SatLnch.getString(1));//meal
                satlnch.append(",");
                satlnch.append("" + SatLnch.getString(2));//nutrition
                satlnch.append("\n");
            }
            satlnch.flush();satlnch.close();
            //sat supper
            FileWriter satspr =new FileWriter(satSupperPathAndname);
            while (SatSp.moveToNext()){
                satspr.append(""+SatSp.getString(0));//id
                satspr.append(",");
                satspr.append(""+SatSp.getString(1));//meal
                satspr.append(",");
                satspr.append(""+SatSp.getString(2));//nutrition
                satspr.append("\n");
            }
            satspr.flush();
            satspr.close();


            //sun breakfast
            FileWriter sunbrk =new FileWriter(sunBreakfastPathAndname);
            while (SunBrk.moveToNext()) {
                sunbrk.append("" + SunBrk.getString(0));//id
                sunbrk.append(",");
                sunbrk.append("" + SunBrk.getString(1));//meal
                sunbrk.append(",");
                sunbrk.append("" + SunBrk.getString(2));//nutrition
                sunbrk.append("\n");
            }
            sunbrk.flush();
            sunbrk.close();
            //sun lunch
            FileWriter sunlnch =new FileWriter(sunLunchPathAndname);
            while (SunLnch.moveToNext()) {
                sunlnch.append("" + SunLnch.getString(0));//id
                sunlnch.append(",");
                sunlnch.append("" + SunLnch.getString(1));//meal
                sunlnch.append(",");
                sunlnch.append("" + SunLnch.getString(2));//nutrition
                sunlnch.append("\n");
            }
            sunlnch.flush();sunlnch.close();
            //sun supper
            FileWriter sunspr =new FileWriter(sunSupperPathAndname);
            while (SunSp.moveToNext()){
                sunspr.append(""+SunSp.getString(0));//id
                sunspr.append(",");
                sunspr.append(""+SunSp.getString(1));//meal
                sunspr.append(",");
                sunspr.append(""+SunSp.getString(2));//nutrition
                sunspr.append("\n");
            }
            sunspr.flush();
            sunspr.close();
        }
        catch (Exception e){
////            // if there is any error
            Toast.makeText(this,"There was an error during back up"+e.getMessage(),Toast.LENGTH_LONG).show();
//
        }
    }

    public void myDeleteMealMenuRecords() {


            myDatabase.deleteMonBreakfastData();
            myDatabase.deleteMonLunchData();
            myDatabase.deleteMonSupperData();

            myDatabase.deleteTueBreakfastData();
            myDatabase.deleteTueLunchData();
            myDatabase.deleteTueSupperData();

            myDatabase.deletewedBreakfastData();
            myDatabase.deletewedLunchData();
            myDatabase.deletewedSupperData();

            myDatabase.deletethurBreakfastData();
            myDatabase.deletethurLunchData();
            myDatabase.deletethurSupperData();

            myDatabase.deletefriBreakfastData();
            myDatabase.deletefriLunchData();
            myDatabase.deletefriSupperData();

            myDatabase.deletesatBreakfastData();
            myDatabase.deletesatLunchData();
            myDatabase.deletesatSupperData();

            myDatabase.deletesunBreakfastData();
            myDatabase.deletesunLunchData();
            myDatabase.deletesunSupperData();

    }

    public void unzipMethod(){

        File folder = new File(Environment.getExternalStorageDirectory() + foldername);
        File zippedBackup = new File(Environment.getExternalStorageDirectory() + zippedfile); //zipped meal menu backup
        //check if zip exists then unzip and call importcsv() method

        if(zippedBackup.exists()) {
//                                         Toast.makeText(this, "zip exist!", Toast.LENGTH_SHORT).show();
            if (folder.canWrite()) {
                try {
                    unzip(zippedBackup.getPath(), folder.getPath());
                    showDialog(this, "Current meal plan will be overwritten, continue with menu import?",
                            "Okay", "Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    importCSV();
                                }
                            });
//                                                 Toast.makeText(this, "Unzipped!", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        //else (zip does not exist)
        else {
            Toast.makeText(this, "HouseMenuBackup does not exist on your internal storage!", Toast.LENGTH_LONG).show();
        }
    }

    public void zipMethod(){
        File zippedBackup = new File(Environment.getExternalStorageDirectory() + zippedfile); //zipped meal menu backup
        if(zippedBackup.exists()) {
            zippedBackup.delete();
        }

        //permission allowed
        String folder = Environment.getExternalStorageDirectory()+foldername;
        //mySnackbar("Coming Soon"); //coming here
        String zippedDBPath = Environment.getExternalStorageDirectory().getPath();
        String[] s = new String[21];

//                  Type the path of the files in here
        s[0] = folder + "/MonBreakfast.csv";
        s[1] = folder + "/MonLunch.csv";
        s[2] = folder + "/MonSupper.csv";

        s[3] = folder + "/TueBreakfast.csv";
        s[4] = folder + "/TueLunch.csv";
        s[5] = folder + "/TueSupper.csv";

        s[6] = folder + "/WedBreakfast.csv";
        s[7] = folder + "/WedLunch.csv";
        s[8] = folder + "/WedSupper.csv";

        s[9] = folder + "/ThurBreakfast.csv";
        s[10] = folder + "/ThurLunch.csv";
        s[11] = folder + "/ThurSupper.csv";

        s[12] = folder + "/FriBreakfast.csv";
        s[13] = folder + "/FriLunch.csv";
        s[14] = folder + "/FriSupper.csv";

        s[15] = folder + "/SatBreakfast.csv";
        s[16] = folder + "/SatLunch.csv";
        s[17] = folder + "/SatSupper.csv";

        s[18] = folder + "/SunBreakfast.csv";
        s[19] = folder + "/SunLunch.csv";
        s[20] = folder + "/SunSupper.csv";

//                  first parameter is the files second parameter is zip file name
        ZipManager zipManager = new ZipManager();

//                  calling the zip function
        try {
            zipManager.zip(s, zippedDBPath + "/HouseMealPlan.zip");

//                        myToast("Zip folder created here "+zippedDBPath,Toast.LENGTH_LONG);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //confirm dialog builder
    public static void showDialog(Context context, String message, String positiveButton, String negativeButton,
                                  DialogInterface.OnClickListener onClickListener) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
//        dialog.setTitle(title);
        dialog.setMessage(message);
        dialog.setPositiveButton(positiveButton,onClickListener);
        dialog.setNegativeButton(negativeButton,null);
        dialog.show();
    }
    public void shareMethod(String subject, String text){

        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_SUBJECT,subject);
        shareIntent.putExtra(Intent.EXTRA_TEXT, text);
        shareIntent.setType("text/plain");
        startActivity(shareIntent);
    }

    public void sendFile(String myFilePath) {
        Intent intentShareFile = new Intent(Intent.ACTION_SEND);
        File fileWithinMyDir = new File(myFilePath);

        if (fileWithinMyDir.exists()) {
            intentShareFile.setType("application/zip");
            intentShareFile.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://" + myFilePath));

            intentShareFile.putExtra(Intent.EXTRA_SUBJECT,
                    "Sharing meal plan...");
            intentShareFile.putExtra(Intent.EXTRA_TEXT, "Sharing meal plan...");

            startActivity(Intent.createChooser(intentShareFile, "Share Meal Plan"));
        }
    }


    public void mySnackbar(String message){
        Snackbar snackbar = Snackbar
                .make(constraintLayout, message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }
    public void myToast(String message, int length){
        Toast.makeText(getApplicationContext(),message,length).show();
    }


}