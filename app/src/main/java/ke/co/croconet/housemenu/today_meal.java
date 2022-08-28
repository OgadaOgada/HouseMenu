package ke.co.croconet.housemenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.Calendar;

import static ke.co.croconet.housemenu.MainActivity.day;
import static ke.co.croconet.housemenu.foodDescription.*;


public class today_meal extends AppCompatActivity {


    TableLayout tablelayout;
    LinearLayout keylayout;
    importExportClass myvictual;
    DatabaseHelper myDatabase;

    Button breakfastbtn, lunchbtn, supperbtn;
    TextView firstMealTv, secondMealTv, thirdMealTv,  fourthMealTv, fifthMealTv,firstMealNutrition,
            secondMealNutrition, thirdMealNutrition, fourthMealNutrition, fifthMealNutrition, todaysmealTv, notificationText,infotextview;
    
    String mealType, notificationString;
    String carbs="Carbs", vit="Vit",prot="Prot", hyphen="-", errorText="There's an error, restart app and try again.",
            nutritionError="oops, there's a problem", noDescription="";
    String forSameMealsChange = "Similar meal already assigned for ";
    String dialogMsgPt1="Changing meal from\t ",dialogMsgPt2=" to\t ",dialogMsgPt3=", continue?";
    String dialogMsgPositive="Yes", dialogMsgNegative="Cancel";
    String tea = "Tea";
    String ugali = "Ugali";

    String deleteBlank = "Deleting blank text, are you okay?";
    int textViewID;
    //String for this method (will be relocated soon)
    String mealtv1="firstMealTextView",mealtv2="secondMealTextView",mealtv3="thirdMealTextView",mealtv4="fourthMealTextView",mealtv5="fifthMealTextView";
    String firstCell = "Start from the first cell downwards!", secondCell ="Continue from second cell downwards!",
            thirdCell = "Continue from third cell downwards!", fourthCell = "Continue from fourth cell downwards!";

    String yesterdayBreakfast = "You took the same meal yesterday breakfast, consider changing to other options available.";
    String yesterdayLunch = "You took the same meal yesterday lunch, consider changing to other options available.";
    String yesterdaySupper = "You took the same meal yesterday supper, consider changing to other options available.";
    String alreadyExistTodaySupper = "This meal is already assigned for supper today.";
    String alreadyExistTodayLunch = "This meal is already assigned for lunch today.";
    String alreadyExistTodayBreak = "This meal is already assigned for breakfast today.";
//stophere

    String pressedTextView="firstMealTextView";

    //oncreate menu and to get item clicked id
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.victual_list, menu);
        menu.setHeaderTitle("Victual Options");
        textViewID  = v.getId();
        String str = Integer.toString(textViewID);
//        Toast.makeText(getApplicationContext(),str,Toast.LENGTH_LONG).show();
        switch (textViewID){
            case 2131230960:
                pressedTextView = "firstMealTextView";
//                Toast.makeText(getApplicationContext(),pressedTextView,Toast.LENGTH_SHORT).show();
                return;
            case 2131231195:
                pressedTextView = "secondMealTextView";
//                Toast.makeText(getApplicationContext(),pressedTextView,Toast.LENGTH_SHORT).show();
                return;
            case 2131231290:
                pressedTextView = "thirdMealTextView";
//                Toast.makeText(getApplicationContext(),pressedTextView,Toast.LENGTH_SHORT).show();
                return;
            case 2131230968:
                pressedTextView = "fourthMealTextView";
//                Toast.makeText(getApplicationContext(),pressedTextView,Toast.LENGTH_SHORT).show();
                return;
            case 2131230953:
                pressedTextView = "fifthMealTextView";
//                Toast.makeText(getApplicationContext(),pressedTextView,Toast.LENGTH_SHORT).show();
                return;
            default:
                pressedTextView = "";
                return;
        }
    }

    //menu click events switch
    @SuppressLint("ResourceType")
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.delete:
//                Toast.makeText(this,"delete button", Toast.LENGTH_SHORT).show();

                String firstmeal =firstMealTv.getText().toString();
                String secondmeal =secondMealTv.getText().toString();
                String thirdmeal =thirdMealTv.getText().toString();
                String fourthmeal =fourthMealTv.getText().toString();
                String fifthmeal =fifthMealTv.getText().toString();

                String  mealtype =(String) todaysmealTv.getText();
                if(mealtype.equalsIgnoreCase("Monday Breakfast")){
                    if (pressedTextView.equalsIgnoreCase("firstMealTextView")){
                        if(firstmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                        }else{deleteMonBreakfastMeal(firstmeal,"1");}}
                    else if (pressedTextView.equalsIgnoreCase("secondMealTextView")){
                        if(secondmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                        }else{
                        deleteMonBreakfastMeal(secondmeal,"2");}}
                    else if (pressedTextView.equalsIgnoreCase("thirdMealTextView")){
                        if(thirdmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                        }else{
                        deleteMonBreakfastMeal(thirdmeal,"3");}}
                    else if (pressedTextView.equalsIgnoreCase("fourthMealTextView")){
                            if(fourthmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                            }else{
                        deleteMonBreakfastMeal(fourthmeal,"4");}}
                    else if(pressedTextView.equalsIgnoreCase("fifthMealTextView")){
                                if(fifthmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                                }else{
                        deleteMonBreakfastMeal(fifthmeal,"5");}}
                }
                else if(mealtype.equalsIgnoreCase("Monday Lunch")){
                    if (pressedTextView.equalsIgnoreCase("firstMealTextView")){
                        if(firstmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                        }else{
                        deleteMonLunchMeal(firstmeal,"1");}}
                    else if (pressedTextView.equalsIgnoreCase("secondMealTextView")){
                            if(secondmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                            }else{
                        deleteMonLunchMeal(secondmeal,"2");}}
                    else if (pressedTextView.equalsIgnoreCase("thirdMealTextView")){
                                if(thirdmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                                }else{
                        deleteMonLunchMeal(thirdmeal,"3");}}
                    else if (pressedTextView.equalsIgnoreCase("fourthMealTextView")){
                                if(fourthmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                                }else{
                        deleteMonLunchMeal(fourthmeal,"4");}}
                    else if(pressedTextView.equalsIgnoreCase("fifthMealTextView")){
                                if(fifthmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                                }else{
                        deleteMonLunchMeal(fifthmeal,"5");}}
                }
                else if(mealtype.equalsIgnoreCase("Monday Supper")){
                    if (pressedTextView.equalsIgnoreCase("firstMealTextView")){
                        if(firstmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                        }else{
                        deleteMonSupperMeal(firstmeal,"1");}}
                    else if (pressedTextView.equalsIgnoreCase("secondMealTextView")){
                            if(secondmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                            }else{
                        deleteMonSupperMeal(secondmeal,"2");}}
                    else if (pressedTextView.equalsIgnoreCase("thirdMealTextView")){
                            if(thirdmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                            }else{
                        deleteMonSupperMeal(thirdmeal,"3");}}
                    else if (pressedTextView.equalsIgnoreCase("fourthMealTextView")){
                            if(fourthmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                            }else{
                        deleteMonSupperMeal(fourthmeal,"4");}}
                    else if(pressedTextView.equalsIgnoreCase("fifthMealTextView")){
                            if(fifthmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                            }else{
                        deleteMonSupperMeal(fifthmeal,"5");}}
                }
                else if(mealtype.equalsIgnoreCase("Tuesday Breakfast")){
                    if (pressedTextView.equalsIgnoreCase("firstMealTextView")){
                        if(firstmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                        }else{
                        deleteTueBreakfastMeal(firstmeal,"1");}}
                    else if (pressedTextView.equalsIgnoreCase("secondMealTextView")){
                            if(secondmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                            }else{
                        deleteTueBreakfastMeal(secondmeal,"2");}}
                    else if (pressedTextView.equalsIgnoreCase("thirdMealTextView")){
                            if(thirdmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                            }else{
                        deleteTueBreakfastMeal(thirdmeal,"3");}}
                    else if (pressedTextView.equalsIgnoreCase("fourthMealTextView")){
                            if(fourthmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                            }else{
                        deleteTueBreakfastMeal(fourthmeal,"4");}}
                    else if(pressedTextView.equalsIgnoreCase("fifthMealTextView")){
                            if(fifthmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                            }else{
                        deleteTueBreakfastMeal(fifthmeal,"5");}}
                }
                else if(mealtype.equalsIgnoreCase("Tuesday Lunch")){
                    if (pressedTextView.equalsIgnoreCase("firstMealTextView")){
                        if(firstmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                        }else{
                        deleteTueLunchMeal(firstmeal,"1");}}
                    else if (pressedTextView.equalsIgnoreCase("secondMealTextView")){
                            if(secondmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                            }else{
                        deleteTueLunchMeal(secondmeal,"2");}}
                    else if (pressedTextView.equalsIgnoreCase("thirdMealTextView")){
                            if(thirdmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                            }else{
                        deleteTueLunchMeal(thirdmeal,"3");}}
                    else if (pressedTextView.equalsIgnoreCase("fourthMealTextView")){
                            if(fourthmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                            }else{
                        deleteTueLunchMeal(fourthmeal,"4");}}
                    else if(pressedTextView.equalsIgnoreCase("fifthMealTextView")){
                            if(fifthmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                            }else{
                        deleteTueLunchMeal(fifthmeal,"5");}}
                }
                else if(mealtype.equalsIgnoreCase("Tuesday Supper")){
                    if (pressedTextView.equalsIgnoreCase("firstMealTextView")){
                        if(firstmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                        }else{
                        deleteTueSupperMeal(firstmeal,"1");}}
                    else if (pressedTextView.equalsIgnoreCase("secondMealTextView")){
                            if(secondmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                            }else{
                        deleteTueSupperMeal(secondmeal,"2");}}
                    else if (pressedTextView.equalsIgnoreCase("thirdMealTextView")){
                            if(thirdmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                            }else{
                        deleteTueSupperMeal(thirdmeal,"3");}}
                    else if (pressedTextView.equalsIgnoreCase("fourthMealTextView")){
                            if(fourthmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                            }else{
                        deleteTueSupperMeal(fourthmeal,"4");}}
                    else if(pressedTextView.equalsIgnoreCase("fifthMealTextView")){
                            if(fifthmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                            }else{
                        deleteTueSupperMeal(fifthmeal,"5");}}
                }
                else if(mealtype.equalsIgnoreCase("Wednesday Breakfast")){
                    if (pressedTextView.equalsIgnoreCase("firstMealTextView")){
                        if(firstmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                        }else{
                        deleteWedBreakfastMeal(firstmeal,"1");}}
                    else if (pressedTextView.equalsIgnoreCase("secondMealTextView")){
                            if(secondmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                            }else{
                        deleteWedBreakfastMeal(secondmeal,"2");}}
                    else if (pressedTextView.equalsIgnoreCase("thirdMealTextView")){
                            if(thirdmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                            }else{
                        deleteWedBreakfastMeal(thirdmeal,"3");}}
                    else if (pressedTextView.equalsIgnoreCase("fourthMealTextView")){
                            if(fourthmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                            }else{
                        deleteWedBreakfastMeal(fourthmeal,"4");}}
                    else if(pressedTextView.equalsIgnoreCase("fifthMealTextView")){
                            if(fifthmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                            }else{
                        deleteWedBreakfastMeal(fifthmeal,"5");}}
                }
                else if(mealtype.equalsIgnoreCase("Wednesday Lunch")){
                    if (pressedTextView.equalsIgnoreCase("firstMealTextView")){
                        if(firstmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                        }else{
                        deleteWedLunchMeal(firstmeal,"1");}}
                    else if (pressedTextView.equalsIgnoreCase("secondMealTextView")){
                        if(secondmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                        }else{
                        deleteWedLunchMeal(secondmeal,"2");}}
                    else if (pressedTextView.equalsIgnoreCase("thirdMealTextView")){
                        if(thirdmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                        }else{
                        deleteWedLunchMeal(thirdmeal,"3");}}
                    else if (pressedTextView.equalsIgnoreCase("fourthMealTextView")){
                        if(fourthmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                        }else{
                        deleteWedLunchMeal(fourthmeal,"4");}}
                    else if(pressedTextView.equalsIgnoreCase("fifthMealTextView")){
                        if(fifthmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                        }else{
                        deleteWedLunchMeal(fifthmeal,"5");}}
                }
                else if(mealtype.equalsIgnoreCase("Wednesday Supper")){
                    if (pressedTextView.equalsIgnoreCase("firstMealTextView")){
                        deleteWedSupperMeal(firstmeal,"1");}
                    else if (pressedTextView.equalsIgnoreCase("secondMealTextView")){
                        deleteWedSupperMeal(secondmeal,"2");}
                    else if (pressedTextView.equalsIgnoreCase("thirdMealTextView")){
                        deleteWedSupperMeal(thirdmeal,"3");}
                    else if (pressedTextView.equalsIgnoreCase("fourthMealTextView")){
                        deleteWedSupperMeal(fourthmeal,"4");}
                    else if(pressedTextView.equalsIgnoreCase("fifthMealTextView")){
                        deleteWedSupperMeal(fifthmeal,"5");}
                }
                else if(mealtype.equalsIgnoreCase("Thursday Breakfast")){
                    if (pressedTextView.equalsIgnoreCase("firstMealTextView")){
                        if(firstmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                        }else{
                        deleteThurBreakfastMeal(firstmeal,"1");}}
                    else if (pressedTextView.equalsIgnoreCase("secondMealTextView")){
                            if(secondmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                            }else{
                        deleteThurBreakfastMeal(secondmeal,"2");}}
                    else if (pressedTextView.equalsIgnoreCase("thirdMealTextView")){
                            if(thirdmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                            }else{
                        deleteThurBreakfastMeal(thirdmeal,"3");}}
                    else if (pressedTextView.equalsIgnoreCase("fourthMealTextView")){
                            if(fourthmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                            }else{
                        deleteThurBreakfastMeal(fourthmeal,"4");}}
                    else if(pressedTextView.equalsIgnoreCase("fifthMealTextView")){
                            if(fifthmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                            }else{
                        deleteThurBreakfastMeal(fifthmeal,"5");}}
                }
                else if(mealtype.equalsIgnoreCase("Thursday Lunch")){
                    if (pressedTextView.equalsIgnoreCase("firstMealTextView")){
                        if(firstmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                        }else{
                        deleteThurLunchMeal(firstmeal,"1");}}
                    else if (pressedTextView.equalsIgnoreCase("secondMealTextView")){
                            if(secondmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                            }else{
                        deleteThurLunchMeal(secondmeal,"2");}}
                    else if (pressedTextView.equalsIgnoreCase("thirdMealTextView")){
                                if(thirdmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                                }else{
                        deleteThurLunchMeal(thirdmeal,"3");}}
                    else if (pressedTextView.equalsIgnoreCase("fourthMealTextView")){
                                    if(fourthmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                                    }else{
                        deleteThurLunchMeal(fourthmeal,"4");}}
                    else if(pressedTextView.equalsIgnoreCase("fifthMealTextView")){
                                        if(fifthmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                                        }else{
                        deleteThurLunchMeal(fifthmeal,"5");}}
                }
                else if(mealtype.equalsIgnoreCase("Thursday Supper")){
                    if (pressedTextView.equalsIgnoreCase("firstMealTextView")){
                        if(firstmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                        }else{
                        deleteThurSupperMeal(firstmeal,"1");}}
                    else if (pressedTextView.equalsIgnoreCase("secondMealTextView")){
                        if(secondmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                        }else{
                        deleteThurSupperMeal(secondmeal,"2");}}
                    else if (pressedTextView.equalsIgnoreCase("thirdMealTextView")){
                        if(thirdmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                        }else{
                        deleteThurSupperMeal(thirdmeal,"3");}}
                    else if (pressedTextView.equalsIgnoreCase("fourthMealTextView")){
                        if(fourthmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                        }else{
                        deleteThurSupperMeal(fourthmeal,"4");}}
                    else if(pressedTextView.equalsIgnoreCase("fifthMealTextView")){
                        if(fifthmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                        }else{
                        deleteThurSupperMeal(fifthmeal,"5");}}
                }
                else if(mealtype.equalsIgnoreCase("Friday Breakfast")){
                    if (pressedTextView.equalsIgnoreCase("firstMealTextView")){
                        if(firstmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                        }else{
                        deleteFriBreakfastMeal(firstmeal,"1");}}
                    else if (pressedTextView.equalsIgnoreCase("secondMealTextView")){
                        if(secondmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                        }else{
                        deleteFriBreakfastMeal(secondmeal,"2");}}
                    else if (pressedTextView.equalsIgnoreCase("thirdMealTextView")){
                        if(thirdmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                        }else{
                        deleteFriBreakfastMeal(thirdmeal,"3");}}
                    else if (pressedTextView.equalsIgnoreCase("fourthMealTextView")){
                        if(fourthmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                        }else{
                        deleteFriBreakfastMeal(fourthmeal,"4");}}
                    else if(pressedTextView.equalsIgnoreCase("fifthMealTextView")){
                        if(fifthmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                        }else{
                        deleteFriBreakfastMeal(fifthmeal,"5");}}
                }
                else if(mealtype.equalsIgnoreCase("Friday Lunch")){
                    if (pressedTextView.equalsIgnoreCase("firstMealTextView")){
                        if(firstmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                        }else{
                        deleteFriLunchMeal(firstmeal,"1");}}
                    else if (pressedTextView.equalsIgnoreCase("secondMealTextView")){
                        if(secondmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                        }else{
                        deleteFriLunchMeal(secondmeal,"2");}}
                    else if (pressedTextView.equalsIgnoreCase("thirdMealTextView")){
                        if(thirdmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                        }else{
                        deleteFriLunchMeal(thirdmeal,"3");}}
                    else if (pressedTextView.equalsIgnoreCase("fourthMealTextView")){
                        if(fourthmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                        }else{
                        deleteFriLunchMeal(fourthmeal,"4");}}
                    else if(pressedTextView.equalsIgnoreCase("fifthMealTextView")){
                        if(fifthmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                        }else{
                        deleteFriLunchMeal(fifthmeal,"5");}}
                }
                else if(mealtype.equalsIgnoreCase("Friday Supper")){
                    if (pressedTextView.equalsIgnoreCase("firstMealTextView")){
                        if(firstmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                        }else{
                        deleteFriSupperMeal(firstmeal,"1");}}
                    else if (pressedTextView.equalsIgnoreCase("secondMealTextView")){
                        if(secondmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                        }else{
                        deleteFriSupperMeal(secondmeal,"2");}}
                    else if (pressedTextView.equalsIgnoreCase("thirdMealTextView")){
                        if(thirdmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                        }else{
                        deleteFriSupperMeal(thirdmeal,"3");}}
                    else if (pressedTextView.equalsIgnoreCase("fourthMealTextView")){
                        if(fourthmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                        }else{
                        deleteFriSupperMeal(fourthmeal,"4");}}
                    else if(pressedTextView.equalsIgnoreCase("fifthMealTextView")){
                        if(fifthmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                        }else{
                        deleteFriSupperMeal(fifthmeal,"5");}}
                }
                else if(mealtype.equalsIgnoreCase("Saturday Breakfast")){
                    if (pressedTextView.equalsIgnoreCase("firstMealTextView")){
                        if(firstmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                        }else{
                        deleteSatBreakfastMeal(firstmeal,"1");}}
                    else if (pressedTextView.equalsIgnoreCase("secondMealTextView")){
                        if(secondmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                        }else{
                        deleteSatBreakfastMeal(secondmeal,"2");}}
                    else if (pressedTextView.equalsIgnoreCase("thirdMealTextView")){
                        if(thirdmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                        }else{
                        deleteSatBreakfastMeal(thirdmeal,"3");}}
                    else if (pressedTextView.equalsIgnoreCase("fourthMealTextView")){
                        if(fourthmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                        }else{
                        deleteSatBreakfastMeal(fourthmeal,"4");}}
                    else if(pressedTextView.equalsIgnoreCase("fifthMealTextView")){
                        if(fifthmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                        }else{
                        deleteSatBreakfastMeal(fifthmeal,"5");}}
                }
                else if(mealtype.equalsIgnoreCase("Saturday Lunch")){
                    if (pressedTextView.equalsIgnoreCase("firstMealTextView")){
                        if(firstmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                        }else{
                        deleteSatLunchMeal(firstmeal,"1");}}
                    else if (pressedTextView.equalsIgnoreCase("secondMealTextView")){
                        if(secondmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                        }else{
                        deleteSatLunchMeal(secondmeal,"2");}}
                    else if (pressedTextView.equalsIgnoreCase("thirdMealTextView")){
                        if(thirdmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                        }else{
                        deleteSatLunchMeal(thirdmeal,"3");}}
                    else if (pressedTextView.equalsIgnoreCase("fourthMealTextView")){
                        if(fourthmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                        }else{
                        deleteSatLunchMeal(fourthmeal,"4");}}
                    else if(pressedTextView.equalsIgnoreCase("fifthMealTextView")){
                        if(fifthmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                        }else{
                        deleteSatLunchMeal(fifthmeal,"5");}}
                }
                else if(mealtype.equalsIgnoreCase("Saturday Supper")){
                    if (pressedTextView.equalsIgnoreCase("firstMealTextView")){
                        if(firstmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                        }else{
                        deleteSatSupperMeal(firstmeal,"1");}}
                    else if (pressedTextView.equalsIgnoreCase("secondMealTextView")){
                        if(secondmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                        }else{
                        deleteSatSupperMeal(secondmeal,"2");}}
                    else if (pressedTextView.equalsIgnoreCase("thirdMealTextView")){
                        if(thirdmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                        }else{
                        deleteSatSupperMeal(thirdmeal,"3");}}
                    else if (pressedTextView.equalsIgnoreCase("fourthMealTextView")){
                        if(fourthmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                        }else{
                        deleteSatSupperMeal(fourthmeal,"4");}}
                    else if(pressedTextView.equalsIgnoreCase("fifthMealTextView")){
                        if(fifthmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                        }else{
                        deleteSatSupperMeal(fifthmeal,"5");}}
                }
                else if(mealtype.equalsIgnoreCase("Sunday Breakfast")){
                    if (pressedTextView.equalsIgnoreCase("firstMealTextView")){
                        if(firstmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                        }else{
                        deleteSunBreakfastMeal(firstmeal,"1");}}
                    else if (pressedTextView.equalsIgnoreCase("secondMealTextView")){
                        if(secondmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                        }else{
                        deleteSunBreakfastMeal(secondmeal,"2");}}
                    else if (pressedTextView.equalsIgnoreCase("thirdMealTextView")){
                        if(thirdmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                        }else{
                        deleteSunBreakfastMeal(thirdmeal,"3");}}
                    else if (pressedTextView.equalsIgnoreCase("fourthMealTextView")){
                        if(fourthmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                        }else{
                        deleteSunBreakfastMeal(fourthmeal,"4");}}
                    else if(pressedTextView.equalsIgnoreCase("fifthMealTextView")){
                        if(fifthmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                        }else{
                        deleteSunBreakfastMeal(fifthmeal,"5");}}
                }
                else if(mealtype.equalsIgnoreCase("Sunday Lunch")){
                    if (pressedTextView.equalsIgnoreCase("firstMealTextView")){
                        if(firstmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                        }else{
                        deleteSunLunchMeal(firstmeal,"1");}}
                    else if (pressedTextView.equalsIgnoreCase("secondMealTextView")){
                        if(secondmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                        }else{
                        deleteSunLunchMeal(secondmeal,"2");}}
                    else if (pressedTextView.equalsIgnoreCase("thirdMealTextView")){
                        if(thirdmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                        }else{
                        deleteSunLunchMeal(thirdmeal,"3");}}
                    else if (pressedTextView.equalsIgnoreCase("fourthMealTextView")){
                        if(fourthmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                        }else{
                        deleteSunLunchMeal(fourthmeal,"4");}}
                    else if(pressedTextView.equalsIgnoreCase("fifthMealTextView")){
                        if(fifthmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                        }else{
                        deleteSunLunchMeal(fifthmeal,"5");}}
                }
                else if(mealtype.equalsIgnoreCase("Sunday Supper")){
                    if (pressedTextView.equalsIgnoreCase("firstMealTextView")){
                        if(firstmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                        }else{
                        deleteSunSupperMeal(firstmeal,"1");}}
                    else if (pressedTextView.equalsIgnoreCase("secondMealTextView")){
                        if(secondmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                        }else{
                        deleteSunSupperMeal(secondmeal,"2");}}
                    else if (pressedTextView.equalsIgnoreCase("thirdMealTextView")){
                        if(thirdmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                        }else{
                        deleteSunSupperMeal(thirdmeal,"3");}}
                    else if (pressedTextView.equalsIgnoreCase("fourthMealTextView")){
                        if(fourthmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                        }else{
                        deleteSunSupperMeal(fourthmeal,"4");}}
                    else if(pressedTextView.equalsIgnoreCase("fifthMealTextView")){
                        if(fifthmeal.trim().equalsIgnoreCase("")){
                            //Toast.makeText(getApplicationContext(),deleteBlank,Toast.LENGTH_SHORT).show();
                        }else{
                        deleteSunSupperMeal(fifthmeal,"5");}}
                    }
                else
                    Toast.makeText(getApplicationContext(),"There's an error, restart app and try again",Toast.LENGTH_LONG).show();

                    //toptop
                return true;
            case R.id.ackee:
                mondayMeals("Ackee fruit", vit);
                tuesdayMeals("Ackee fruit", vit);
                wednesdayMeals("Ackee fruit", vit);
                thursdayMeals("Ackee fruit", vit);
                fridayMeals("Ackee fruit", vit);
                saturdayMeals("Ackee fruit", vit);
                sundayMeals("Ackee fruit", vit);
                Toast.makeText(getApplicationContext(),"Be cautious about unripe or overripe ackee fruit.",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.aliya:
                mondayMeals("Dried meat", prot);
                tuesdayMeals("Dried meat", prot);
                wednesdayMeals("Dried meat", prot);
                thursdayMeals("Dried meat", prot);
                fridayMeals("Dried meat", prot);
                saturdayMeals("Dried meat", prot);
                sundayMeals("Dried meat", prot);
                return true;
            case R.id.apple:
                mondayMeals("Apple", vit);
                tuesdayMeals("Apple", vit);
                wednesdayMeals("Apple", vit);
                thursdayMeals("Apple", vit);
                fridayMeals("Apple", vit);
                saturdayMeals("Apple", vit);
                sundayMeals("Apple", vit);
                return true;
            case R.id.nduma:
                mondayMeals("Arrowroot", carbs);
                tuesdayMeals("Arrowroot", carbs);
                wednesdayMeals("Arrowroot", carbs);
                thursdayMeals("Arrowroot", carbs);
                fridayMeals("Arrowroot", carbs);
                saturdayMeals("Arrowroot", carbs);
                sundayMeals("Arrowroot", carbs);
                return true;
            case R.id.avocado:
                mondayMeals("Avocado", vit);
                tuesdayMeals("Avocado", vit);
                wednesdayMeals("Avocado", vit);
                thursdayMeals("Avocado", vit);
                fridayMeals("Avocado", vit);
                saturdayMeals("Avocado", vit);
                sundayMeals("Avocado", vit);
                return true;
            case R.id.banana:
                mondayMeals("Ripe banana", vit);
                tuesdayMeals("Ripe banana", vit);
                wednesdayMeals("Ripe banana", vit);
                thursdayMeals("Ripe banana", vit);
                fridayMeals("Ripe banana", vit);
                saturdayMeals("Ripe banana", vit);
                sundayMeals("Ripe banana", vit);
                return true;
            case R.id.matoke:
                mondayMeals("Cooked banana", vit);
                tuesdayMeals("Cooked banana", vit);
                wednesdayMeals("Cooked banana", vit);
                thursdayMeals("Cooked banana", vit);
                fridayMeals("Cooked banana", vit);
                saturdayMeals("Cooked banana", vit);
                sundayMeals("Cooked banana", vit);
                return true;
            case R.id.beans:
                mondayMeals("Beans", prot);
                tuesdayMeals("Beans", prot);
                wednesdayMeals("Beans", prot);
                thursdayMeals("Beans", prot);
                fridayMeals("Beans", prot);
                saturdayMeals("Beans", prot);
                sundayMeals("Beans", prot);
                return true;
            case R.id.beef:
                mondayMeals("Beef", prot);
                tuesdayMeals("Beef", prot);
                wednesdayMeals("Beef", prot);
                thursdayMeals("Beef", prot);
                fridayMeals("Beef", prot);
                saturdayMeals("Beef", prot);
                sundayMeals("Beef", prot);
                return true;
            case R.id.bhajia:
                mondayMeals("Bhajia", carbs);
                tuesdayMeals("Bhajia", carbs);
                wednesdayMeals("Bhajia", carbs);
                thursdayMeals("Bhajia", carbs);
                fridayMeals("Bhajia", carbs);
                saturdayMeals("Bhajia", carbs);
                sundayMeals("Bhajia", carbs);
                return true;
            case R.id.biryani:
                mondayMeals("Biryani", carbs+" & "+prot);
                tuesdayMeals("Biryani", carbs+" & "+prot);
                wednesdayMeals("Biryani", carbs+" & "+prot);
                thursdayMeals("Biryani", carbs+" & "+prot);
                fridayMeals("Biryani", carbs+" & "+prot);
                saturdayMeals("Biryani", carbs+" & "+prot);
                sundayMeals("Biryani", carbs+" & "+prot);
                return true;
            case R.id.bread:
                mondayMeals("Bread", carbs);
                tuesdayMeals("Bread", carbs);
                wednesdayMeals("Bread", carbs);
                thursdayMeals("Bread", carbs);
                fridayMeals("Bread", carbs);
                saturdayMeals("Bread", carbs);
                sundayMeals("Bread", carbs);
                return true;
            case R.id.brocollli:
                mondayMeals("Brocolli", vit);
                tuesdayMeals("Brocolli", vit);
                wednesdayMeals("Brocolli", vit);
                thursdayMeals("Brocolli", vit);
                fridayMeals("Brocolli", vit);
                saturdayMeals("Brocolli", vit);
                sundayMeals("Brocolli", vit);
                return true;
            case R.id.burger:
                mondayMeals("Burger", carbs+" & "+prot);
                tuesdayMeals("Burger", carbs+" & "+prot);
                wednesdayMeals("Burger", carbs+" & "+prot);
                thursdayMeals("Burger", carbs+" & "+prot);
                fridayMeals("Burger", carbs+" & "+prot);
                saturdayMeals("Burger", carbs+" & "+prot);
                sundayMeals("Burger", carbs+" & "+prot);
                return true;
            case R.id.cabbage:
                mondayMeals("Cabbage", vit);
                tuesdayMeals("Cabbage", vit);
                wednesdayMeals("Cabbage", vit);
                thursdayMeals("Cabbage", vit);
                fridayMeals("Cabbage", vit);
                saturdayMeals("Cabbage", vit);
                sundayMeals("Cabbage", vit);
                return true;
            case R.id.carrot:
                mondayMeals("Carrot", vit);
                tuesdayMeals("Carrot", vit);
                wednesdayMeals("Carrot", vit);
                thursdayMeals("Carrot", vit);
                fridayMeals("Carrot", vit);
                saturdayMeals("Carrot", vit);
                sundayMeals("Carrot", vit);
                return true;

            case R.id.cassava:
                mondayMeals("Cassava", carbs);
                tuesdayMeals("Cassava", carbs);
                wednesdayMeals("Cassava", carbs);
                thursdayMeals("Cassava", carbs);
                fridayMeals("Cassava", carbs);
                saturdayMeals("Cassava", carbs);
                sundayMeals("Cassava", carbs);
                Toast.makeText(getApplicationContext(), "Be mindful of cyanide poisoning when preparing cassava.", Toast.LENGTH_LONG).show();
                return true;

            case R.id.chapati:
                mondayMeals("Chapati", carbs);
                tuesdayMeals("Chapati", carbs);
                wednesdayMeals("Chapati", carbs);
                thursdayMeals("Chapati", carbs);
                fridayMeals("Chapati", carbs);
                saturdayMeals("Chapati", carbs);
                sundayMeals("Chapati", carbs);
                return true;

            case R.id.cherimoya:
                mondayMeals("Custard Apple", vit);
                tuesdayMeals("Custard Apple", vit);
                wednesdayMeals("Custard Apple", vit);
                thursdayMeals("Custard Apple", vit);
                fridayMeals("Custard Apple", vit);
                saturdayMeals("Custard Apple", vit);
                sundayMeals("Custard Apple", vit);
                return true;

            case R.id.chicken:
                mondayMeals("Chicken", prot);
                tuesdayMeals("Chicken", prot);
                wednesdayMeals("Chicken", prot);
                thursdayMeals("Chicken", prot);
                fridayMeals("Chicken", prot);
                saturdayMeals("Chicken", prot);
                sundayMeals("Chicken", prot);
                return true;

            case R.id.chips:
                mondayMeals("Chips", carbs);
                tuesdayMeals("Chips", carbs);
                wednesdayMeals("Chips", carbs);
                thursdayMeals("Chips", carbs);
                fridayMeals("Chips", carbs);
                saturdayMeals("Chips", carbs);
                sundayMeals("Chips", carbs);
                return true;

            case R.id.coffee:
                mondayMeals("Coffee", hyphen);
                tuesdayMeals("Coffee", hyphen);
                wednesdayMeals("Coffee", hyphen);
                thursdayMeals("Coffee", hyphen);
                fridayMeals("Coffee", hyphen);
                saturdayMeals("Coffee", hyphen);
                sundayMeals("Coffee", hyphen);
                return true;

            case R.id.coconutMilk:
                mondayMeals("Coconut milk", prot);
                tuesdayMeals("Coconut milk", prot);
                wednesdayMeals("Coconut milk", prot);
                thursdayMeals("Coconut milk", prot);
                fridayMeals("Coconut milk", prot);
                saturdayMeals("Coconut milk", prot);
                sundayMeals("Coconut milk", prot);
                return true;

            case R.id.chocolate:
                mondayMeals("Chocolate", hyphen);
                tuesdayMeals("Chocolate", hyphen);
                wednesdayMeals("Chocolate", hyphen);
                thursdayMeals("Chocolate", hyphen);
                fridayMeals("Chocolate", hyphen);
                saturdayMeals("Chocolate", hyphen);
                sundayMeals("Chocolate", hyphen);
                return true;

            case R.id.salad:
                mondayMeals("Kachumbari", vit);
                tuesdayMeals("Kachumbari", vit);
                wednesdayMeals("Kachumbari", vit);
                thursdayMeals("Kachumbari", vit);
                fridayMeals("Kachumbari", vit);
                saturdayMeals("Kachumbari", vit);
                sundayMeals("Kachumbari", vit);
                return true;

            case R.id.cucumber:
                mondayMeals("Cucumber", vit);
                tuesdayMeals("Cucumber", vit);
                wednesdayMeals("Cucumber", vit);
                thursdayMeals("Cucumber", vit);
                fridayMeals("Cucumber", vit);
                saturdayMeals("Cucumber", vit);
                sundayMeals("Cucumber", vit);
                return true;

            case R.id.cupuacu:
                mondayMeals("Cupuacu", prot);
                tuesdayMeals("Cupuacu", prot);
                wednesdayMeals("Cupuacu", prot);
                thursdayMeals("Cupuacu", prot);
                fridayMeals("Cupuacu", prot);
                saturdayMeals("Cupuacu", prot);
                sundayMeals("Cupuacu", prot);
                return true;

            case R.id.dates:
                mondayMeals("Dates", vit);
                tuesdayMeals("Dates", vit);
                wednesdayMeals("Dates", vit);
                thursdayMeals("Dates", vit);
                fridayMeals("Dates", vit);
                saturdayMeals("Dates", vit);
                sundayMeals("Dates", vit);
                return true;

            case R.id.dragonFruit:
                mondayMeals("Dragon Fruit", vit);
                tuesdayMeals("Dragon Fruit", vit);
                wednesdayMeals("Dragon Fruit", vit);
                thursdayMeals("Dragon Fruit", vit);
                fridayMeals("Dragon Fruit", vit);
                saturdayMeals("Dragon Fruit", vit);
                sundayMeals("Dragon Fruit", vit);
                return true;

            case R.id.durian:
                mondayMeals("Durian", vit);
                tuesdayMeals("Durian", vit);
                wednesdayMeals("Durian", vit);
                thursdayMeals("Durian", vit);
                fridayMeals("Durian", vit);
                saturdayMeals("Durian", vit);
                sundayMeals("Durian", vit);
                return true;

            case R.id.eggplant:
                mondayMeals("Eggplant", vit);
                tuesdayMeals("Eggplant", vit);
                wednesdayMeals("Eggplant", vit);
                thursdayMeals("Eggplant", vit);
                fridayMeals("Eggplant", vit);
                saturdayMeals("Eggplant", vit);
                sundayMeals("Eggplant", vit);
                return true;

            case R.id.eggs:
                mondayMeals("Eggs", prot);
                tuesdayMeals("Eggs", prot);
                wednesdayMeals("Eggs", prot);
                thursdayMeals("Eggs", prot);
                fridayMeals("Eggs", prot);
                saturdayMeals("Eggs", prot);
                sundayMeals("Eggs", prot);
                return true;

            case R.id.fermentedMilk:
                mondayMeals("Fermented Milk", prot);
                tuesdayMeals("Fermented Milk", prot);
                wednesdayMeals("Fermented Milk", prot);
                thursdayMeals("Fermented Milk", prot);
                fridayMeals("Fermented Milk", prot);
                saturdayMeals("Fermented Milk", prot);
                sundayMeals("Fermented Milk", prot);
                return true;

            case R.id.figs:
                mondayMeals("Figs", vit);
                tuesdayMeals("Figs", vit);
                wednesdayMeals("Figs", vit);
                thursdayMeals("Figs", vit);
                fridayMeals("Figs", vit);
                saturdayMeals("Figs", vit);
                sundayMeals("Figs", vit);
                return true;

            case R.id.fish:
                mondayMeals("Fish", prot);
                tuesdayMeals("Fish", prot);
                wednesdayMeals("Fish", prot);
                thursdayMeals("Fish", prot);
                fridayMeals("Fish", prot);
                saturdayMeals("Fish", prot);
                sundayMeals("Fish", prot);
                return true;

            case R.id.githeri:
                mondayMeals("Githeri", carbs+" & "+prot);
                tuesdayMeals("Githeri", carbs+" & "+prot);
                wednesdayMeals("Githeri", carbs+" & "+prot);
                thursdayMeals("Githeri", carbs+" & "+prot);
                fridayMeals("Githeri", carbs+" & "+prot);
                saturdayMeals("Githeri", carbs+" & "+prot);
                sundayMeals("Githeri", carbs+" & "+prot);
                return true;

            case R.id.gizzards:
                mondayMeals("Gizzards", prot);
                tuesdayMeals("Gizzards", prot);
                wednesdayMeals("Gizzards", prot);
                thursdayMeals("Gizzards", prot);
                fridayMeals("Gizzards", prot);
                saturdayMeals("Gizzards", prot);
                sundayMeals("Gizzards", prot);
                return true;

            case R.id.goat:
                mondayMeals("Goat", prot);
                tuesdayMeals("Goat", prot);
                wednesdayMeals("Goat", prot);
                thursdayMeals("Goat", prot);
                fridayMeals("Goat", prot);
                saturdayMeals("Goat", prot);
                sundayMeals("Goat", prot);
                return true;

            case R.id.dengu:
                mondayMeals("Green grams", carbs);
                tuesdayMeals("Green grams", carbs);
                wednesdayMeals("Green grams", carbs);
                thursdayMeals("Green grams", carbs);
                fridayMeals("Green grams", carbs);
                saturdayMeals("Green grams", carbs);
                sundayMeals("Green grams", carbs);
                return true;

            case R.id.grilledMeat:
                mondayMeals("Grilled meat", prot);
                tuesdayMeals("Grilled meat", prot);
                wednesdayMeals("Grilled meat", prot);
                thursdayMeals("Grilled meat", prot);
                fridayMeals("Grilled meat", prot);
                saturdayMeals("Grilled meat", prot);
                sundayMeals("Grilled meat", prot);
                return true;

            case R.id.guava:
                mondayMeals("Guava", vit);
                tuesdayMeals("Guava", vit);
                wednesdayMeals("Guava", vit);
                thursdayMeals("Guava", vit);
                fridayMeals("Guava", vit);
                saturdayMeals("Guava", vit);
                sundayMeals("Guava", vit);
                return true;

            case R.id.indigenousVegetable:
                return true;
            case R.id.amaranthus:
                mondayMeals("Terere", vit);
                tuesdayMeals("Terere", vit);
                wednesdayMeals("Terere", vit);
                thursdayMeals("Terere", vit);
                fridayMeals("Terere", vit);
                saturdayMeals("Terere", vit);
                sundayMeals("Terere", vit);
                return true;
            case R.id.arrowrootLeaves:
                mondayMeals("Arrowroot leaves", vit);
                tuesdayMeals("Arrowroot leaves", vit);
                wednesdayMeals("Arrowroot leaves", vit);
                thursdayMeals("Arrowroot leaves", vit);
                fridayMeals("Arrowroot leaves", vit);
                saturdayMeals("Arrowroot leaves", vit);
                sundayMeals("Arrowroot leaves", vit);
                return true;
            case R.id.basella:
                mondayMeals("Nderema", vit);
                tuesdayMeals("Nderema", vit);
                wednesdayMeals("Nderema", vit);
                thursdayMeals("Nderema", vit);
                fridayMeals("Nderema", vit);
                saturdayMeals("Nderema", vit);
                sundayMeals("Nderema", vit);
                return true;
            case R.id.beanLeaves:
                mondayMeals("Bean leaves", vit);
                tuesdayMeals("Bean leaves", vit);
                wednesdayMeals("Bean leaves", vit);
                thursdayMeals("Bean leaves", vit);
                fridayMeals("Bean leaves", vit);
                saturdayMeals("Bean leaves", vit);
                sundayMeals("Bean leaves", vit);
                return true;
            case R.id.managu:
                mondayMeals("Managu", vit);
                tuesdayMeals("Managu", vit);
                wednesdayMeals("Managu", vit);
                thursdayMeals("Managu", vit);
                fridayMeals("Managu", vit);
                saturdayMeals("Managu", vit);
                sundayMeals("Managu", vit);
                return true;
            case R.id.cassavaLeaves:
                mondayMeals("Cassava leaves", vit);
                tuesdayMeals("Cassava leaves", vit);
                wednesdayMeals("Cassava leaves", vit);
                thursdayMeals("Cassava leaves", vit);
                fridayMeals("Cassava leaves", vit);
                saturdayMeals("Cassava leaves", vit);
                sundayMeals("Cassava leaves", vit);
                Toast.makeText(getApplicationContext(), "Be mindful of cyanide poisoning when preparing cassava leaves.", Toast.LENGTH_LONG).show();
                return true;
            case R.id.cowpeaLeaves:
                mondayMeals("Kunde", vit);
                tuesdayMeals("Kunde", vit);
                wednesdayMeals("Kunde", vit);
                thursdayMeals("Kunde", vit);
                fridayMeals("Kunde", vit);
                saturdayMeals("Kunde", vit);
                sundayMeals("Kunde", vit);
                return true;
            case R.id.mitoo:
                mondayMeals("Miro/mito", vit);
                tuesdayMeals("Miro/mito", vit);
                wednesdayMeals("Miro/mito", vit);
                thursdayMeals("Miro/mito", vit);
                fridayMeals("Miro/mito", vit);
                saturdayMeals("Miro/mito", vit);
                sundayMeals("Miro/mito", vit);
                return true;
            case R.id.mrenda:
                mondayMeals("Mrenda", vit);
                tuesdayMeals("Mrenda", vit);
                wednesdayMeals("Mrenda", vit);
                thursdayMeals("Mrenda", vit);
                fridayMeals("Mrenda", vit);
                saturdayMeals("Mrenda", vit);
                sundayMeals("Mrenda", vit);
                return true;
            case R.id.pumpkinLeaves:
                mondayMeals("Seveve", vit);
                tuesdayMeals("Seveve", vit);
                wednesdayMeals("Seveve", vit);
                thursdayMeals("Seveve", vit);
                fridayMeals("Seveve", vit);
                saturdayMeals("Seveve", vit);
                sundayMeals("Seveve", vit);
                return true;
            case R.id.spiderPlantSaga:
                mondayMeals("Saga", vit);
                tuesdayMeals("Saga", vit);
                wednesdayMeals("Saga", vit);
                thursdayMeals("Saga", vit);
                fridayMeals("Saga", vit);
                saturdayMeals("Saga", vit);
                sundayMeals("Saga", vit);
                return true;
            case R.id.stingingNettleLeaves:
                mondayMeals("Nettle leaves", vit);
                tuesdayMeals("Nettle leaves", vit);
                wednesdayMeals("Nettle leaves", vit);
                thursdayMeals("Nettle leaves", vit);
                fridayMeals("Nettle leaves", vit);
                saturdayMeals("Nettle leaves", vit);
                sundayMeals("Nettle leaves", vit);
                return true;
            case R.id.sweetPotatoLeaves:
                mondayMeals("Potato leaves", vit);
                tuesdayMeals("Potato leaves", vit);
                wednesdayMeals("Potato leaves", vit);
                thursdayMeals("Potato leaves", vit);
                fridayMeals("Potato leaves", vit);
                saturdayMeals("Potato leaves", vit);
                sundayMeals("Potato leaves", vit);
                return true;

            case R.id.waru:
                mondayMeals("Irish Potato", carbs);
                tuesdayMeals("Irish Potato", carbs);
                wednesdayMeals("Irish Potato", carbs);
                thursdayMeals("Irish Potato", carbs);
                fridayMeals("Irish Potato", carbs);
                saturdayMeals("Irish Potato", carbs);
                sundayMeals("Irish Potato", carbs);
                return true;

            case R.id.jabuticaba:
                mondayMeals("Jabuticaba", vit);
                tuesdayMeals("Jabuticaba", vit);
                wednesdayMeals("Jabuticaba", vit);
                thursdayMeals("Jabuticaba", vit);
                fridayMeals("Jabuticaba", vit);
                saturdayMeals("Jabuticaba", vit);
                sundayMeals("Jabuticaba", vit);
                return true;

            case R.id.jackFruit:
                mondayMeals("Jack fruit", vit);
                tuesdayMeals("Jack fruit", vit);
                wednesdayMeals("Jack fruit", vit);
                thursdayMeals("Jack fruit", vit);
                fridayMeals("Jack fruit", vit);
                saturdayMeals("Jack fruit", vit);
                sundayMeals("Jack fruit", vit);
                return true;

            case R.id.juice:
                mondayMeals("Juice", vit);
                tuesdayMeals("Juice", vit);
                wednesdayMeals("Juice", vit);
                thursdayMeals("Juice", vit);
                fridayMeals("Juice", vit);
                saturdayMeals("Juice", vit);
                sundayMeals("Juice", vit);
                return true;

            case R.id.lemon:
                mondayMeals("Lemon", vit);
                tuesdayMeals("Lemon", vit);
                wednesdayMeals("Lemon", vit);
                thursdayMeals("Lemon", vit);
                fridayMeals("Lemon", vit);
                saturdayMeals("Lemon", vit);
                sundayMeals("Lemon", vit);
                return true;

            case R.id.lentils:
                mondayMeals("Lentils", prot);
                tuesdayMeals("Lentils", prot);
                wednesdayMeals("Lentils", prot);
                thursdayMeals("Lentils", prot);
                fridayMeals("Lentils", prot);
                saturdayMeals("Lentils", prot);
                sundayMeals("Lentils", prot);
                return true;

            case R.id.lime:
                mondayMeals("Lime", vit);
                tuesdayMeals("Lime", vit);
                wednesdayMeals("Lime", vit);
                thursdayMeals("Lime", vit);
                fridayMeals("Lime", vit);
                saturdayMeals("Lime", vit);
                sundayMeals("Lime", vit);
                return true;

            case R.id.liver:
                mondayMeals("Liver", prot);
                tuesdayMeals("Liver", prot);
                wednesdayMeals("Liver", prot);
                thursdayMeals("Liver", prot);
                fridayMeals("Liver", prot);
                saturdayMeals("Liver", prot);
                sundayMeals("Liver", prot);
                return true;

            case R.id.mabuyu:
                mondayMeals("Mabuyu", vit);
                tuesdayMeals("Mabuyu", vit);
                wednesdayMeals("Mabuyu", vit);
                thursdayMeals("Mabuyu", vit);
                fridayMeals("Mabuyu", vit);
                saturdayMeals("Mabuyu", vit);
                sundayMeals("Mabuyu", vit);
                Toast.makeText(getApplicationContext(),"Mabuyu is a type of candy made from the seeds of the Baobab tree fruit." +
                        " The tree is called “Mbuyu” in Swahili hence the name",Toast.LENGTH_LONG).show();
                return true;

            case R.id.roastedMaize:
                mondayMeals("Roasted maize", carbs);
                tuesdayMeals("Roasted maize", carbs);
                wednesdayMeals("Roasted maize", carbs);
                thursdayMeals("Roasted maize", carbs);
                fridayMeals("Roasted maize", carbs);
                saturdayMeals("Roasted maize", carbs);
                sundayMeals("Roasted maize", carbs);
                return true;

            case R.id.boiledMaize:
                mondayMeals("Boiled maize", carbs);
                tuesdayMeals("Boiled maize", carbs);
                wednesdayMeals("Boiled maize", carbs);
                thursdayMeals("Boiled maize", carbs);
                fridayMeals("Boiled maize", carbs);
                saturdayMeals("Boiled maize", carbs);
                sundayMeals("Boiled maize", carbs);
                return true;

            case R.id.mandazi:
                mondayMeals("Mandazi", carbs);
                tuesdayMeals("Mandazi", carbs);
                wednesdayMeals("Mandazi", carbs);
                thursdayMeals("Mandazi", carbs);
                fridayMeals("Mandazi", carbs);
                saturdayMeals("Mandazi", carbs);
                sundayMeals("Mandazi", carbs);
                return true;

            case R.id.mango:
                mondayMeals("Mango", vit);
                tuesdayMeals("Mango", vit);
                wednesdayMeals("Mango", vit);
                thursdayMeals("Mango", vit);
                fridayMeals("Mango", vit);
                saturdayMeals("Mango", vit);
                sundayMeals("Mango", vit);
                return true;

            case R.id.mashedPotato:
                mondayMeals("Mashed potato", carbs);
                tuesdayMeals("Mashed potato", carbs);
                wednesdayMeals("Mashed potato", carbs);
                thursdayMeals("Mashed potato", carbs);
                fridayMeals("Mashed potato", carbs);
                saturdayMeals("Mashed potato", carbs);
                sundayMeals("Mashed potato", carbs);
                return true;

            case R.id.pigeonPeas:
                mondayMeals("Pigeon peas", prot+" & "+carbs);
                tuesdayMeals("Pigeon peas", prot+" & "+carbs);
                wednesdayMeals("Pigeon peas", prot+" & "+carbs);
                thursdayMeals("Pigeon peas", prot+" & "+carbs);
                fridayMeals("Pigeon peas", prot+" & "+carbs);
                saturdayMeals("Pigeon peas", prot+" & "+carbs);
                sundayMeals("Pigeon peas", prot+" & "+carbs);
                return true;


            case R.id.miracleFruit:
                mondayMeals("Miracle fruit", vit);
                tuesdayMeals("Miracle fruit", vit);
                wednesdayMeals("Miracle fruit", vit);
                thursdayMeals("Miracle fruit", vit);
                fridayMeals("Miracle fruit", vit);
                saturdayMeals("Miracle fruit", vit);
                sundayMeals("Miracle fruit", vit);
                return true;

            case R.id.mukimo:
                mondayMeals("Mukimo", carbs);
                tuesdayMeals("Mukimo", carbs);
                wednesdayMeals("Mukimo", carbs);
                thursdayMeals("Mukimo", carbs);
                fridayMeals("Mukimo", carbs);
                saturdayMeals("Mukimo", carbs);
                sundayMeals("Mukimo", carbs);
                return true;

            case R.id.mushroom:
                mondayMeals("Mushroom", vit);
                tuesdayMeals("Mushroom", vit);
                wednesdayMeals("Mushroom", vit);
                thursdayMeals("Mushroom", vit);
                fridayMeals("Mushroom", vit);
                saturdayMeals("Mushroom", vit);
                sundayMeals("Mushroom", vit);
                return true;

            case R.id.muthokoi:
                mondayMeals("Muthokoi", carbs+" & "+prot);
                tuesdayMeals("Muthokoi", carbs+" & "+prot);
                wednesdayMeals("Muthokoi", carbs+" & "+prot);
                thursdayMeals("Muthokoi", carbs+" & "+prot);
                fridayMeals("Muthokoi", carbs+" & "+prot);
                saturdayMeals("Muthokoi", carbs+" & "+prot);
                sundayMeals("Muthokoi", carbs+" & "+prot);
                return true;

            case R.id.mutton:
                mondayMeals("Mutton", prot);
                tuesdayMeals("Mutton", prot);
                wednesdayMeals("Mutton", prot);
                thursdayMeals("Mutton", prot);
                fridayMeals("Mutton", prot);
                saturdayMeals("Mutton", prot);
                sundayMeals("Mutton", prot);
                return true;

            case R.id.olive:
                mondayMeals("Olive", vit);
                tuesdayMeals("Olive", vit);
                wednesdayMeals("Olive", vit);
                thursdayMeals("Olive", vit);
                fridayMeals("Olive", vit);
                saturdayMeals("Olive", vit);
                sundayMeals("Olive", vit);
                return true;

            case R.id.omena:
                mondayMeals("Omena", prot);
                tuesdayMeals("Omena", prot);
                wednesdayMeals("Omena", prot);
                thursdayMeals("Omena", prot);
                fridayMeals("Omena", prot);
                saturdayMeals("Omena", prot);
                sundayMeals("Omena", prot);
                return true;

            case R.id.orange:
                mondayMeals("Oranges", vit);
                tuesdayMeals("Oranges", vit);
                wednesdayMeals("Oranges", vit);
                thursdayMeals("Oranges", vit);
                fridayMeals("Oranges", vit);
                saturdayMeals("Oranges", vit);
                sundayMeals("Oranges", vit);
                return true;

            case R.id.pawpaw:
                mondayMeals("Pawpaw", vit);
                tuesdayMeals("Pawpaw", vit);
                wednesdayMeals("Pawpaw", vit);
                thursdayMeals("Pawpaw", vit);
                fridayMeals("Pawpaw", vit);
                saturdayMeals("Pawpaw", vit);
                sundayMeals("Pawpaw", vit);
                return true;

            case R.id.peaches:
                mondayMeals("Peaches", vit);
                tuesdayMeals("Peaches", vit);
                wednesdayMeals("Peaches", vit);
                thursdayMeals("Peaches", vit);
                fridayMeals("Peaches", vit);
                saturdayMeals("Peaches", vit);
                sundayMeals("Peaches", vit);
                return true;

            case R.id.peanutStew:
                mondayMeals("Peanut stew", prot);
                tuesdayMeals("Peanut stew", prot);
                wednesdayMeals("Peanut stew", prot);
                thursdayMeals("Peanut stew", prot);
                fridayMeals("Peanut stew", prot);
                saturdayMeals("Peanut stew", prot);
                sundayMeals("Peanut stew", prot);
                return true;

            case R.id.peas:
                mondayMeals("Green peas", prot);
                tuesdayMeals("Green peas", prot);
                wednesdayMeals("Green peas", prot);
                thursdayMeals("Green peas", prot);
                fridayMeals("Green peas", prot);
                saturdayMeals("Green peas", prot);
                sundayMeals("Green peas", prot);
                return true;

            case R.id.pilau:
                mondayMeals("Pilau", carbs);
                tuesdayMeals("Pilau", carbs);
                wednesdayMeals("Pilau", carbs);
                thursdayMeals("Pilau", carbs);
                fridayMeals("Pilau", carbs);
                saturdayMeals("Pilau", carbs);
                sundayMeals("Pilau", carbs);
                return true;

            case R.id.pineapple:
                mondayMeals("Pineapple", vit);
                tuesdayMeals("Pineapple", vit);
                wednesdayMeals("Pineapple", vit);
                thursdayMeals("Pineapple", vit);
                fridayMeals("Pineapple", vit);
                saturdayMeals("Pineapple", vit);
                sundayMeals("Pineapple", vit);
                return true;

            case R.id.pizza:
                mondayMeals("Pizza", prot+" & "+carbs);
                tuesdayMeals("Pizza", prot+" & "+carbs);
                wednesdayMeals("Pizza", prot+" & "+carbs);
                thursdayMeals("Pizza", prot+" & "+carbs);
                fridayMeals("Pizza", prot+" & "+carbs);
                saturdayMeals("Pizza", prot+" & "+carbs);
                sundayMeals("Pizza", prot+" & "+carbs);
                return true;

            case R.id.pomegranate:
                mondayMeals("Pomegranate", vit);
                tuesdayMeals("Pomegranate", vit);
                wednesdayMeals("Pomegranate", vit);
                thursdayMeals("Pomegranate", vit);
                fridayMeals("Pomegranate", vit);
                saturdayMeals("Pomegranate", vit);
                sundayMeals("Pomegranate", vit);
                return true;

            case R.id.pork:
                mondayMeals("Pork", prot);
                tuesdayMeals("Pork", prot);
                wednesdayMeals("Pork", prot);
                thursdayMeals("Pork", prot);
                fridayMeals("Pork", prot);
                saturdayMeals("Pork", prot);
                sundayMeals("Pork", prot);
                return true;

            case R.id.porridge:
                mondayMeals("Porridge", carbs);
                tuesdayMeals("Porridge", carbs);
                wednesdayMeals("Porridge", carbs);
                thursdayMeals("Porridge", carbs);
                fridayMeals("Porridge", carbs);
                saturdayMeals("Porridge", carbs);
                sundayMeals("Porridge", carbs);
                return true;

            case R.id.pumpkin:
                mondayMeals("Pumpkin", vit);
                tuesdayMeals("Pumpkin", vit);
                wednesdayMeals("Pumpkin", vit);
                thursdayMeals("Pumpkin", vit);
                fridayMeals("Pumpkin", vit);
                saturdayMeals("Pumpkin", vit);
                sundayMeals("Pumpkin", vit);
                return true;

            case R.id.rambutan:
                mondayMeals("Rambutan", vit);
                tuesdayMeals("Rambutan", vit);
                wednesdayMeals("Rambutan", vit);
                thursdayMeals("Rambutan", vit);
                fridayMeals("Rambutan", vit);
                saturdayMeals("Rambutan", vit);
                sundayMeals("Rambutan", vit);
                return true;

            case R.id.rice:
                mondayMeals("Rice", carbs);
                tuesdayMeals("Rice", carbs);
                wednesdayMeals("Rice", carbs);
                thursdayMeals("Rice", carbs);
                fridayMeals("Rice", carbs);
                saturdayMeals("Rice", carbs);
                sundayMeals("Rice", carbs);
                return true;

            case R.id.samosa:
                mondayMeals("Samosa", carbs);
                tuesdayMeals("Samosa", carbs);
                wednesdayMeals("Samosa", carbs);
                thursdayMeals("Samosa", carbs);
                fridayMeals("Samosa", carbs);
                saturdayMeals("Samosa", carbs);
                sundayMeals("Samosa", carbs);
                return true;

            case R.id.sausage:
                mondayMeals("Sausage", prot);
                tuesdayMeals("Sausage", prot);
                wednesdayMeals("Sausage", prot);
                thursdayMeals("Sausage", prot);
                fridayMeals("Sausage", prot);
                saturdayMeals("Sausage", prot);
                sundayMeals("Sausage", prot);
                return true;

            case R.id.shellfish:
                mondayMeals("Shellfish", prot);
                tuesdayMeals("Shellfish", prot);
                wednesdayMeals("Shellfish", prot);
                thursdayMeals("Shellfish", prot);
                fridayMeals("Shellfish", prot);
                saturdayMeals("Shellfish", prot);
                sundayMeals("Shellfish", prot);
                return true;

            case R.id.smokey:
                mondayMeals("Smokey", prot);
                tuesdayMeals("Smokey", prot);
                wednesdayMeals("Smokey", prot);
                thursdayMeals("Smokey", prot);
                fridayMeals("Smokey", prot);
                saturdayMeals("Smokey", prot);
                sundayMeals("Smokey", prot);
                return true;

            case R.id.snacks:
                mondayMeals("Snacks", hyphen);
                tuesdayMeals("Snacks", hyphen);
                wednesdayMeals("Snacks", hyphen);
                thursdayMeals("Snacks", hyphen);
                fridayMeals("Snacks", hyphen);
                saturdayMeals("Snacks", hyphen);
                sundayMeals("Snacks", hyphen);
                return true;

            case R.id.soda:
                mondayMeals("Soda", hyphen);
                tuesdayMeals("Soda", hyphen);
                wednesdayMeals("Soda", hyphen);
                thursdayMeals("Soda", hyphen);
                fridayMeals("Soda", hyphen);
                saturdayMeals("Soda", hyphen);
                sundayMeals("Soda", hyphen);
                return true;

            case R.id.spinach:
                mondayMeals("Spinach", vit);
                tuesdayMeals("Spinach", vit);
                wednesdayMeals("Spinach", vit);
                thursdayMeals("Spinach", vit);
                fridayMeals("Spinach", vit);
                saturdayMeals("Spinach", vit);
                sundayMeals("Spinach", vit);
                return true;

            case R.id.collardGreens:
                mondayMeals("Sukuma", vit);
                tuesdayMeals("Sukuma", vit);
                wednesdayMeals("Sukuma", vit);
                thursdayMeals("Sukuma", vit);
                fridayMeals("Sukuma", vit);
                saturdayMeals("Sukuma", vit);
                sundayMeals("Sukuma", vit);
                return true;

            case R.id.sweetPotato:
                mondayMeals("Sweet potato", carbs);
                tuesdayMeals("Sweet potato", carbs);
                wednesdayMeals("Sweet potato", carbs);
                thursdayMeals("Sweet potato", carbs);
                fridayMeals("Sweet potato", carbs);
                saturdayMeals("Sweet potato", carbs);
                sundayMeals("Sweet potato", carbs);
                return true;

            case R.id.tangerine:
                mondayMeals("Tangerine", vit);
                tuesdayMeals("Tangerine", vit);
                wednesdayMeals("Tangerine", vit);
                thursdayMeals("Tangerine", vit);
                fridayMeals("Tangerine", vit);
                saturdayMeals("Tangerine", vit);
                sundayMeals("Tangerine", vit);
                return true;

            case R.id.tea:
                mondayMeals("Tea", hyphen);
                tuesdayMeals("Tea", hyphen);
                wednesdayMeals("Tea", hyphen);
                thursdayMeals("Tea", hyphen);
                fridayMeals("Tea", hyphen);
                saturdayMeals("Tea", hyphen);
                sundayMeals("Tea", hyphen);
                return true;

            case R.id.ugali:
                mondayMeals("Ugali", carbs);
                tuesdayMeals("Ugali", carbs);
                wednesdayMeals("Ugali", carbs);
                thursdayMeals("Ugali", carbs);
                fridayMeals("Ugali", carbs);
                saturdayMeals("Ugali", carbs);
                sundayMeals("Ugali", carbs);
                return true;

            case R.id.viaziKarai:
                mondayMeals("Viazi karai", carbs);
                tuesdayMeals("Viazi karai", carbs);
                wednesdayMeals("Viazi karai", carbs);
                thursdayMeals("Viazi karai", carbs);
                fridayMeals("Viazi karai", carbs);
                saturdayMeals("Viazi karai", carbs);
                sundayMeals("Viazi karai", carbs);
                return true;

            case R.id.watermelon:
                mondayMeals("Watermelon", vit);
                tuesdayMeals("Watermelon", vit);
                wednesdayMeals("Watermelon", vit);
                thursdayMeals("Watermelon", vit);
                fridayMeals("Watermelon", vit);
                saturdayMeals("Watermelon", vit);
                sundayMeals("Watermelon", vit);
                return true;

            case R.id.wingedTermite:
                mondayMeals("Winged termite", prot);
                tuesdayMeals("Winged termite", prot);
                wednesdayMeals("Winged termite", prot);
                thursdayMeals("Winged termite", prot);
                fridayMeals("Winged termite", prot);
                saturdayMeals("Winged termite", prot);
                sundayMeals("Winged termite", prot);
                return true;

            case R.id.wine:
                mondayMeals("Wine", hyphen);
                tuesdayMeals("Wine", hyphen);
                wednesdayMeals("Wine", hyphen);
                thursdayMeals("Wine", hyphen);
                fridayMeals("Wine", hyphen);
                saturdayMeals("Wine", hyphen);
                sundayMeals("Wine", hyphen);
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }

    private AdView mAdView;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today_meal);
        this.setTitle("House Meal Planner");


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        notificationString ="Long press cells to assign, change or delete meals";

        myDatabase = new DatabaseHelper(this);

        myvictual = new importExportClass();

        tablelayout = (TableLayout) findViewById(R.id.tableLayout);
        keylayout = (LinearLayout) findViewById(R.id.keyLinearLayout);
        todaysmealTv = (TextView) findViewById(R.id.todaysMealText);


        int timeInt = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
//        String time = Integer.toString(timeInt);

        breakfastbtn = (Button) findViewById(R.id.breakfastButton);
        lunchbtn = (Button) findViewById(R.id.lunchButton);
        supperbtn = (Button) findViewById(R.id.supperButton);
        supperbtn = (Button) findViewById(R.id.supperButton);

        firstMealTv = (TextView) findViewById(R.id.firstMealText);
        firstMealNutrition = (TextView) findViewById(R.id.firstMealNutritionText);

        secondMealTv = (TextView) findViewById(R.id.secondMealText);
        secondMealNutrition = (TextView) findViewById(R.id.secondMealNutritionText);

        thirdMealTv = (TextView) findViewById(R.id.thirdMealText);
        thirdMealNutrition = (TextView) findViewById(R.id.thirdMealNutritionText);

        fourthMealTv = (TextView) findViewById(R.id.fourthMealText);
        fourthMealNutrition = (TextView) findViewById(R.id.fourthMealNutritionText);

        fifthMealTv = (TextView) findViewById(R.id.fifthMealText);
        fifthMealNutrition = (TextView) findViewById(R.id.fifthMealNutritionText);


        notificationText = (TextView)findViewById(R.id.notificationTextView);
        notificationText.setText(notificationString);

        registerForContextMenu(firstMealTv);
        registerForContextMenu(secondMealTv);
        registerForContextMenu(thirdMealTv);
        registerForContextMenu(fourthMealTv);
        registerForContextMenu(fifthMealTv);

        infotextview = (TextView)findViewById(R.id.infoTextView); //herer

        //SETTING MEAL DETAILS ONCLICK
        firstMealTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                foodName = (String) firstMealTv.getText().toString();
                foodInfo();
                if (foodName.trim().equalsIgnoreCase(blank) || desc.equalsIgnoreCase(blank)) {
//                    Toast.makeText(getApplicationContext(), noDescription, Toast.LENGTH_SHORT).show();

                } else {
                    foodInfoMessage(nutritionTitle,desc);
                    nutritionTitle = "";
                    desc = "";
                    foodName = "";
                }
            }
        });

        secondMealTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                foodName = (String) secondMealTv.getText();
                foodInfo();
                if (foodName.trim().equalsIgnoreCase(blank) || desc.equalsIgnoreCase(blank)) {
//                    Toast.makeText(getApplicationContext(), noDescription, Toast.LENGTH_SHORT).show();


                }  else {
                    foodInfoMessage(nutritionTitle,desc);
                    nutritionTitle = "";
                    desc = "";
                    foodName = "";
                }
            }
        });
        thirdMealTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                foodName = (String) thirdMealTv.getText();
                foodInfo();
                if (foodName.trim().equalsIgnoreCase(blank) || desc.equalsIgnoreCase(blank)) {
//                    Toast.makeText(getApplicationContext(), noDescription, Toast.LENGTH_SHORT).show();
                } else {
                    foodInfoMessage(nutritionTitle,desc);
                    nutritionTitle = "";
                    desc = "";
                    foodName = "";
                }
            }
        });
        fourthMealTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                foodName = (String) fourthMealTv.getText();
                foodInfo();
                if (foodName.trim().equalsIgnoreCase(blank) || desc.equalsIgnoreCase(blank)) {
//                    Toast.makeText(getApplicationContext(), noDescription, Toast.LENGTH_SHORT).show();

                }  else {
                    foodInfoMessage(nutritionTitle,desc);
                    nutritionTitle = "";
                    desc = "";
                    foodName = "";
                }
            }
        });
        fifthMealTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                foodName = (String) fifthMealTv.getText();
                foodInfo();
                if (foodName.trim().equalsIgnoreCase(blank) || desc.equalsIgnoreCase(blank)){
//                    Toast.makeText(getApplicationContext(), noDescription, Toast.LENGTH_SHORT).show();

                }else {
                    foodInfoMessage(nutritionTitle,desc);
                    nutritionTitle = "";
                    desc = "";
                    foodName = "";
                }
            }
        });

        // LONG PRESS ON MEAL TEXT VIEWS
//        firstMealTv.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                nutritionTitle=victualTitle;
//                desc=victualList;
//                foodInfoMessage(nutritionTitle,desc);
//                return true;
//            }
//        });

//        secondMealTv.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                nutritionTitle=victualTitle;
//                desc=victualList;
//                foodInfoMessage(nutritionTitle,desc);
//                return true;
//            }
//        });
//        thirdMealTv.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                nutritionTitle=victualTitle;
//                desc=victualList;
//                foodInfoMessage(nutritionTitle,desc);
//                return true;
//            }
//        });
//        fourthMealTv.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                nutritionTitle=victualTitle;
//                desc=victualList;
//                foodInfoMessage(nutritionTitle,desc);
//                return true;
//            }
//        });
//        fifthMealTv.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                nutritionTitle=victualTitle;
//                desc=victualList;
//                foodInfoMessage(nutritionTitle,desc);
//                return true;
//            }
//        });

        //SETTING ONCLICK TO NUTRITION CELLS
        firstMealNutrition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                foodNutrition = (String) firstMealNutrition.getText();
                if(foodNutrition.trim().equalsIgnoreCase("")){

                }
                else if (foodNutrition.trim().equalsIgnoreCase(carbs)) {
                    desc = carbsInfo;
                    foodNutrition = "";
                    nutritionTitle = "";
                    foodInfoMessage(nutritionTitle,desc);
                    desc = "";
                    foodNutrition = "";
                    nutritionTitle = "";
                }
                else if (foodNutrition.trim().equalsIgnoreCase(vit)) {
                    desc = vitsInfo;
                    foodNutrition = "";
                    nutritionTitle = "";
                    foodInfoMessage(nutritionTitle,desc);
                    desc = "";
                    foodNutrition = "";
                    nutritionTitle = "";
                } else if (foodNutrition.trim().equalsIgnoreCase(prot)) {
                    desc = protsInfo;
                    foodNutrition = "";
                    nutritionTitle = "";
                    foodInfoMessage(nutritionTitle,desc);
                    desc = "";
                    foodNutrition = "";
                    nutritionTitle = "";
                } else if (foodNutrition.trim().equalsIgnoreCase(prot+" & "+vit) || foodNutrition.trim().equalsIgnoreCase(vit+" & "+prot)) {
                    desc = protsInfo + "\n\nAND\n\n" + vitsInfo;
                    foodNutrition = "";
                    nutritionTitle = "";
                    foodInfoMessage(nutritionTitle,desc);
                    desc = "";
                    foodNutrition = "";
                    nutritionTitle = "";
                } else if (foodNutrition.trim().equalsIgnoreCase(prot+" & "+carbs) || foodNutrition.trim().equalsIgnoreCase(carbs+" & "+prot)) {
                    desc = protsInfo + "\n\nAND\n\n" + carbsInfo;
                    foodNutrition = "";
                    nutritionTitle = "";
                    foodInfoMessage(nutritionTitle,desc);
                    desc = "";
                    foodNutrition = "";
                    nutritionTitle = "";
                } else if (foodNutrition.trim().equalsIgnoreCase(vit+" & "+carbs) || foodNutrition.trim().equalsIgnoreCase(carbs+" & "+vit)) {
                    desc = carbsInfo + "\n\nAND\n\n" + vitsInfo;
                    foodNutrition = "";
                    nutritionTitle = "";
                    foodInfoMessage(nutritionTitle,desc);
                    desc = "";
                    foodNutrition = "";
                    nutritionTitle = "";
                }
                else if (foodNutrition.trim().equalsIgnoreCase(blankTea)) {
                    desc = teaInfo;
                    foodNutrition = "";
                    nutritionTitle = "";
                    foodInfoMessage(nutritionTitle,desc);
                    desc = "";
                    foodNutrition = "";
                    nutritionTitle = "";
                } else {
                    Toast.makeText(getApplicationContext(), nutritionError, Toast.LENGTH_SHORT).show();
                }
            }
        });

        secondMealNutrition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                foodNutrition = (String) secondMealNutrition.getText();
                if(foodNutrition.trim().equalsIgnoreCase("")){

                }
                else if (foodNutrition.trim().equalsIgnoreCase(carbs)) {
                    desc = carbsInfo;
                    foodNutrition = "";
                    nutritionTitle = "";
                    foodInfoMessage(nutritionTitle,desc);
                    desc = "";
                    foodNutrition = "";
                    nutritionTitle = "";
                }else if (foodNutrition.trim().equalsIgnoreCase(vit)) {
                    desc = vitsInfo;
                    foodNutrition = "";
                    nutritionTitle = "";
                    foodInfoMessage(nutritionTitle,desc);
                    desc = "";
                    foodNutrition = "";
                    nutritionTitle = "";
                } else if (foodNutrition.trim().equalsIgnoreCase(prot)) {
                    desc = protsInfo;
                    foodNutrition = "";
                    nutritionTitle = "";
                    foodInfoMessage(nutritionTitle,desc);
                    desc = "";
                    foodNutrition = "";
                    nutritionTitle = "";
                } else if (foodNutrition.trim().equalsIgnoreCase(prot+" & "+vit) || foodNutrition.trim().equalsIgnoreCase(vit+" & "+prot)) {
                    desc = protsInfo + "\n\nAND\n\n" + vitsInfo;
                    foodNutrition = "";
                    nutritionTitle = "";
                    foodInfoMessage(nutritionTitle,desc);
                    desc = "";
                    foodNutrition = "";
                    nutritionTitle = "";
                } else if (foodNutrition.trim().equalsIgnoreCase(prot+" & "+carbs) || foodNutrition.trim().equalsIgnoreCase(carbs+" & "+prot)) {
                    desc = protsInfo + "\n\nAND\n\n" + carbsInfo;
                    foodNutrition = "";
                    nutritionTitle = "";
                    foodInfoMessage(nutritionTitle,desc);
                    desc = "";
                    foodNutrition = "";
                    nutritionTitle = "";
                } else if (foodNutrition.trim().equalsIgnoreCase(vit+" & "+carbs) || foodNutrition.trim().equalsIgnoreCase(carbs+" & "+vit)) {
                    desc = carbsInfo + "\n\nAND\n\n" + vitsInfo;
                    foodNutrition = "";
                    nutritionTitle = "";
                    foodInfoMessage(nutritionTitle,desc);
                    desc = "";
                    foodNutrition = "";
                    nutritionTitle = "";
                }
                else if (foodNutrition.trim().equalsIgnoreCase(blankTea)) {
                    desc = teaInfo;
                    foodNutrition = "";
                    nutritionTitle = "";
                    foodInfoMessage(nutritionTitle,desc);
                    desc = "";
                    foodNutrition = "";
                    nutritionTitle = "";
                } else {
                    Toast.makeText(getApplicationContext(), nutritionError, Toast.LENGTH_SHORT).show();
                }
            }
        });

        thirdMealNutrition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                foodNutrition = (String) thirdMealNutrition.getText();
                if(foodNutrition.trim().equalsIgnoreCase("")){

                }
                else if (foodNutrition.trim().equalsIgnoreCase(carbs)) {
                    desc = carbsInfo;
                    foodNutrition = "";
                    nutritionTitle = "";
                    foodInfoMessage(nutritionTitle,desc);
                    desc = "";
                    nutritionTitle = "";
                    foodNutrition = "";
                }else if (foodNutrition.trim().equalsIgnoreCase(vit)) {
                    desc = vitsInfo;
                    foodNutrition = "";
                    nutritionTitle = "";
                    foodInfoMessage(nutritionTitle,desc);
                    desc = "";
                    nutritionTitle = "";
                    foodNutrition = "";
                } else if (foodNutrition.trim().equalsIgnoreCase(prot)) {
                    desc = protsInfo;
                    foodNutrition = "";
                    nutritionTitle = "";
                    foodInfoMessage(nutritionTitle,desc);
                    desc = "";
                    nutritionTitle = "";
                    foodNutrition = "";
                }  else if (foodNutrition.trim().equalsIgnoreCase(prot+" & "+vit) || foodNutrition.trim().equalsIgnoreCase(vit+" & "+prot)) {
                    desc = protsInfo + "\n\nAND\n\n" + vitsInfo;
                    foodNutrition = "";
                    nutritionTitle = "";
                    foodInfoMessage(nutritionTitle,desc);
                    desc = "";
                    foodNutrition = "";
                    nutritionTitle = "";
                } else if (foodNutrition.trim().equalsIgnoreCase(prot+" & "+carbs) || foodNutrition.trim().equalsIgnoreCase(carbs+" & "+prot)) {
                    desc = protsInfo + "\n\nAND\n\n" + carbsInfo;
                    foodNutrition = "";
                    nutritionTitle = "";
                    foodInfoMessage(nutritionTitle,desc);
                    desc = "";
                    foodNutrition = "";
                    nutritionTitle = "";
                } else if (foodNutrition.trim().equalsIgnoreCase(vit+" & "+carbs) || foodNutrition.trim().equalsIgnoreCase(carbs+" & "+vit)) {
                    desc = carbsInfo + "\n\nAND\n\n" + vitsInfo;
                    foodNutrition = "";
                    nutritionTitle = "";
                    foodInfoMessage(nutritionTitle,desc);
                    desc = "";
                    foodNutrition = "";
                    nutritionTitle = "";
                } else {
//                    Toast.makeText(getApplicationContext(),"Coming soon",Toast.LENGTH_SHORT).show();
                }
            }
        });

        fourthMealNutrition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                foodNutrition = (String) fourthMealNutrition.getText();
                if(foodNutrition.trim().equalsIgnoreCase("")){

                }
                else if (foodNutrition.trim().equalsIgnoreCase(carbs)) {
                    desc = carbsInfo;
                    foodNutrition = "";
                    nutritionTitle = "";
                    foodInfoMessage(nutritionTitle,desc);
                    desc = "";
                    nutritionTitle = "";
                    foodNutrition = "";
                } else if (foodNutrition.trim().equalsIgnoreCase(vit)) {
                    desc = vitsInfo;
                    foodNutrition = "";
                    nutritionTitle = "";
                    foodInfoMessage(nutritionTitle,desc);
                    desc = "";
                    nutritionTitle = "";
                    foodNutrition = "";
                } else if (foodNutrition.trim().equalsIgnoreCase(prot)) {
                    desc = protsInfo;
                    foodNutrition = "";
                    nutritionTitle = "";
                    foodInfoMessage(nutritionTitle,desc);
                    desc = "";
                    foodNutrition = "";
                    nutritionTitle = "";
                } else if (foodNutrition.trim().equalsIgnoreCase(prot+" & "+vit) || foodNutrition.trim().equalsIgnoreCase(vit+" & "+prot)) {
                    desc = protsInfo + "\n\nAND\n\n" + vitsInfo;
                    foodNutrition = "";
                    nutritionTitle = "";
                    foodInfoMessage(nutritionTitle,desc);
                    desc = "";
                    foodNutrition = "";
                    nutritionTitle = "";
                } else if (foodNutrition.trim().equalsIgnoreCase(prot+" & "+carbs) || foodNutrition.trim().equalsIgnoreCase(carbs+" & "+prot)) {
                    desc = protsInfo + "\n\nAND\n\n" + carbsInfo;
                    foodNutrition = "";
                    nutritionTitle = "";
                    foodInfoMessage(nutritionTitle,desc);
                    desc = "";
                    foodNutrition = "";
                    nutritionTitle = "";
                } else if (foodNutrition.trim().equalsIgnoreCase(vit+" & "+carbs) || foodNutrition.trim().equalsIgnoreCase(carbs+" & "+vit)) {
                    desc = carbsInfo + "\n\nAND\n\n" + vitsInfo;
                    foodNutrition = "";
                    nutritionTitle = "";
                    foodInfoMessage(nutritionTitle,desc);
                    desc = "";
                    foodNutrition = "";
                    nutritionTitle = "";
                }
                else if (foodNutrition.trim().equalsIgnoreCase(blankTea)) {
                    desc = teaInfo;
                    foodNutrition = "";
                    nutritionTitle = "";
                    foodInfoMessage(nutritionTitle,desc);
                    desc = "";
                    foodNutrition = "";
                    nutritionTitle = "";
                } else {
                    Toast.makeText(getApplicationContext(), nutritionError, Toast.LENGTH_SHORT).show();
                }
            }
        });


        fifthMealNutrition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                foodNutrition = (String) fifthMealNutrition.getText();
                if(foodNutrition.trim().equalsIgnoreCase("")){

                }
                else if (foodNutrition.trim().equalsIgnoreCase(carbs)) {
                    desc = carbsInfo;
                    foodNutrition = "";
                    nutritionTitle = "";
                    foodInfoMessage(nutritionTitle,desc);
                    desc = "";
                    nutritionTitle = "";
                    foodNutrition = "";
                } else if (foodNutrition.trim().equalsIgnoreCase(vit)) {
                    desc = vitsInfo;
                    foodNutrition = "";
                    nutritionTitle = "";
                    foodInfoMessage(nutritionTitle,desc);
                    desc = "";
                    nutritionTitle = "";
                    foodNutrition = "";
                } else if (foodNutrition.trim().equalsIgnoreCase(prot)) {
                    desc = protsInfo;
                    foodNutrition = "";
                    nutritionTitle = "";
                    foodInfoMessage(nutritionTitle,desc);
                    desc = "";
                    foodNutrition = "";
                    nutritionTitle = "";
                } else if (foodNutrition.trim().equalsIgnoreCase(prot+" & "+vit) || foodNutrition.trim().equalsIgnoreCase(vit+" & "+prot)) {
                    desc = protsInfo + "\n\nAND\n\n" + vitsInfo;
                    foodNutrition = "";
                    nutritionTitle = "";
                    foodInfoMessage(nutritionTitle,desc);
                    desc = "";
                    foodNutrition = "";
                    nutritionTitle = "";
                } else if (foodNutrition.trim().equalsIgnoreCase(prot+" & "+carbs) || foodNutrition.trim().equalsIgnoreCase(carbs+" & "+prot)) {
                    desc = protsInfo + "\n\nAND\n\n" + carbsInfo;
                    foodNutrition = "";
                    nutritionTitle = "";
                    foodInfoMessage(nutritionTitle,desc);
                    desc = "";
                    foodNutrition = "";
                    nutritionTitle = "";
                } else if (foodNutrition.trim().equalsIgnoreCase(vit+" & "+carbs) || foodNutrition.trim().equalsIgnoreCase(carbs+" & "+vit)) {
                    desc = carbsInfo + "\n\nAND\n\n" + vitsInfo;
                    foodNutrition = "";
                    nutritionTitle = "";
                    foodInfoMessage(nutritionTitle,desc);
                    desc = "";
                    foodNutrition = "";
                    nutritionTitle = "";
                }
                else if (foodNutrition.trim().equalsIgnoreCase(blankTea)) {
                    desc = teaInfo;
                    foodNutrition = "";
                    nutritionTitle = "";
                    foodInfoMessage(nutritionTitle,desc);
                    desc = "";
                    foodNutrition = "";
                    nutritionTitle = "";
                } else {
                    Toast.makeText(getApplicationContext(), nutritionError, Toast.LENGTH_SHORT).show();
                }
            }
        });


        //SETTING DEFAULT MEAL DEPENDING ON TIME

        if (day.equalsIgnoreCase("Monday")) {

            if (timeInt <= 10) {
                mondayBreakfast();
            } else if (timeInt <= 14) {
                mondayLunch();
            } else {
                mondaySupper();
            }

            breakfastbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mondayBreakfast();
                }
            });

            lunchbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mondayLunch();
                }
            });
            supperbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mondaySupper();
                }
            });
        } else if (day.equalsIgnoreCase("Tuesday")) {


            if (timeInt <= 10) {
                tuesdayBreakfast();
            } else if (timeInt <= 14) {
                tuesdayLunch();
            } else {
                tuesdaySupper();
            }

            breakfastbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tuesdayBreakfast();
                }

            });

            lunchbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tuesdayLunch();
                }
            });
            supperbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tuesdaySupper();
                }

            });
        } else if (day.equalsIgnoreCase("Wednesday")) {


            if (timeInt <= 10) {
                wednesdayBreakfast();
            } else if (timeInt <= 14) {
                wednesdayLunch();
            } else {
                wednesdaySupper();
            }

            breakfastbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    wednesdayBreakfast();
                }
            });

            lunchbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    wednesdayLunch();
                }
            });
            supperbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    wednesdaySupper();
                }
            });
        } else if (day.equalsIgnoreCase("Thursday")) {


            if (timeInt <= 10) {
                thursdayBreakfast();
            } else if (timeInt <= 14) {
                thursdayLunch();
            } else {
                thursdaySupper();
            }

            breakfastbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    thursdayBreakfast();
                }
            });

            lunchbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    thursdayLunch();
                }
            });
            supperbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    thursdaySupper();
                }
            });
        } else if (day.equalsIgnoreCase("Friday")) {


            if (timeInt <= 10) {
                fridayBreakfast();
            } else if (timeInt <= 14) {
                fridayLunch();
            } else {
                fridaySupper();
            }

            breakfastbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fridayBreakfast();
                }
            });

            lunchbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fridayLunch();
                }
            });
            supperbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fridaySupper();
                }
            });
        } else if (day.equalsIgnoreCase("Saturday")) {

            if (timeInt <= 10) {
                saturdayBreakfast();
            } else if (timeInt <= 14) {
                saturdayLunch();
            } else {
                saturdaySupper();
            }

            // BREAKFAST, LUNCH AND SUPPER BUTTON ONCLICK
            breakfastbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    saturdayBreakfast();
                }
            });

            lunchbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    saturdayLunch();
                }
            });
            supperbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    saturdaySupper();

                }
            });
        } else if (day.equalsIgnoreCase("Sunday")) {

            if (timeInt <= 10) {
                sundayBreakfast();
            } else if (timeInt <= 14) {
                sundayLunch();
            } else {
                sundaySupper();
            }

            breakfastbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sundayBreakfast();
                }
            });

            lunchbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sundayLunch();
                }
            });
            supperbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sundaySupper();
                }
            });
        }

    }//end of oncreate

    public void startMainActivity(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        overridePendingTransition( R.anim.right_in, R.anim.right_out);
        finish();
    }
//    @Override
//    public void onStop(){
//        super.onStop();
//        startMainActivity();
//    }

// reading table records and setting to textviews
    @SuppressLint("SetTextI18n")
    public void mondayBreakfast() {
        todaysmealTv.setText("Monday Breakfast");
        String monBrmeal1 = "", monBrmeal1Nut = "", monBrmeal2 = "", monBrmeal2Nut = "", monBrmeal3 = "",
                monBrmeal3Nut = "", monBrmeal4 = "", monBrmeal4Nut = "", monBrmeal5 = "", monBrmeal5Nut = "";
        clearTextFields();

            Cursor res1 = myDatabase.viewMonBreakfastMealsSpecific("1");
            if (res1.getCount() == 0) {
//                // notificationText.setText(notificationString);
            } else {
//            // notificationText.setText("");
            StringBuffer buffer1 = new StringBuffer();
            while (res1.moveToNext()) {
                monBrmeal1 = res1.getString(1);
                monBrmeal1Nut = res1.getString(2);
            }}

            Cursor res2 = myDatabase.viewMonBreakfastMealsSpecific("2");
            if (res2.getCount() == 0) {
//                // notificationText.setText(notificationString);
            } else {
//                // notificationText.setText("");
                StringBuffer buffer2 = new StringBuffer();
                while (res2.moveToNext()) {
                monBrmeal2 = res2.getString(1);
                monBrmeal2Nut = res2.getString(2);
            }}

            Cursor res3 = myDatabase.viewMonBreakfastMealsSpecific("3");
            if (res3.getCount() == 0) {
//                // notificationText.setText(notificationString);
                } else {
                    // notificationText.setText("");
                    StringBuffer buffer3 = new StringBuffer();
                    while (res3.moveToNext()) {
                    monBrmeal3 = res3.getString(1);
                    monBrmeal3Nut = res3.getString(2);
            }}

            Cursor res4 = myDatabase.viewMonBreakfastMealsSpecific("4");
            if (res4.getCount() == 0) {
//                // notificationText.setText(notificationString);
            } else {
                // notificationText.setText("");
                StringBuffer buffer4 = new StringBuffer();
                while (res4.moveToNext()) {
                    monBrmeal4 = res4.getString(1);
                    monBrmeal4Nut = res4.getString(2);
            }}

        Cursor res5 = myDatabase.viewMonBreakfastMealsSpecific("5");
        if (res5.getCount() == 0) {
//            // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer5 = new StringBuffer();
            while (res5.moveToNext()) {
                monBrmeal5 = res5.getString(1);
                monBrmeal5Nut = res5.getString(2);
            }}
            firstMealTv.setText(monBrmeal1);
            firstMealNutrition.setText(monBrmeal1Nut);
            secondMealTv.setText(monBrmeal2);
            secondMealNutrition.setText(monBrmeal2Nut);
            thirdMealTv.setText(monBrmeal3);
            thirdMealNutrition.setText(monBrmeal3Nut);
            fourthMealTv.setText(monBrmeal4);
            fourthMealNutrition.setText(monBrmeal4Nut);
            fifthMealTv.setText(monBrmeal5);
            fifthMealNutrition.setText(monBrmeal5Nut);
            myDatabase.close();
    }

        //MONDAY LUNCH
    @SuppressLint("SetTextI18n")
    public void mondayLunch() {
        todaysmealTv.setText("Monday Lunch");
        String monLnmeal1 = "", monLnmeal1Nut = "", monLnmeal2 = "", monLnmeal2Nut = "", monLnmeal3 = "",
                monLnmeal3Nut = "", monLnmeal4 = "", monLnmeal4Nut = "", monLnmeal5 = "", monLnmeal5Nut = "";
        clearTextFields();

        Cursor res1 = myDatabase.viewMonLunchMealsSpecific("1");
        if (res1.getCount() == 0) {
//            // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer1 = new StringBuffer();
            while (res1.moveToNext()) {
                monLnmeal1 = res1.getString(1);
                monLnmeal1Nut = res1.getString(2);
            }}

        Cursor res2 = myDatabase.viewMonLunchMealsSpecific("2");
        if (res2.getCount() == 0) {
//                // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer2 = new StringBuffer();
            while (res2.moveToNext()) {
                monLnmeal2 = res2.getString(1);
                monLnmeal2Nut = res2.getString(2);
            }}

        Cursor res3 = myDatabase.viewMonLunchMealsSpecific("3");
        if (res3.getCount() == 0) {
//            // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer3 = new StringBuffer();
            while (res3.moveToNext()) {
                monLnmeal3 = res3.getString(1);
                monLnmeal3Nut = res3.getString(2);
            }}

        Cursor res4 = myDatabase.viewMonLunchMealsSpecific("4");
        if (res4.getCount() == 0) {
//            // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer4 = new StringBuffer();
            while (res4.moveToNext()) {
                monLnmeal4 = res4.getString(1);
                monLnmeal4Nut = res4.getString(2);
            }}

        Cursor res5 = myDatabase.viewMonLunchMealsSpecific("5");
        if (res5.getCount() == 0) {
//            // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer5 = new StringBuffer();
            while (res5.moveToNext()) {
                monLnmeal5 = res5.getString(1);
                monLnmeal5Nut = res5.getString(2);
            }}
            firstMealTv.setText(monLnmeal1);
            firstMealNutrition.setText(monLnmeal1Nut);
            secondMealTv.setText(monLnmeal2);
            secondMealNutrition.setText(monLnmeal2Nut);
            thirdMealTv.setText(monLnmeal3);
            thirdMealNutrition.setText(monLnmeal3Nut);
            fourthMealTv.setText(monLnmeal4);
            fourthMealNutrition.setText(monLnmeal4Nut);
            fifthMealTv.setText(monLnmeal5);
            fifthMealNutrition.setText(monLnmeal5Nut);
            myDatabase.close();

    }
        //MONDAY SUPPER
    @SuppressLint("SetTextI18n")
    public void mondaySupper() {

        todaysmealTv.setText("Monday Supper");
        String monSpmeal1 = "", monSpmeal1Nut = "", monSpmeal2 = "", monSpmeal2Nut = "", monSpmeal3 = "",
                monSpmeal3Nut = "", monSpmeal4 = "", monSpmeal4Nut = "", monSpmeal5 = "", monSpmeal5Nut = "";
        clearTextFields();

        Cursor res1 = myDatabase.viewMonSupperMealsSpecific("1");
        if (res1.getCount() == 0) {
//            // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer1 = new StringBuffer();
            while (res1.moveToNext()) {
                monSpmeal1 = res1.getString(1);
                monSpmeal1Nut = res1.getString(2);
            }}

        Cursor res2 = myDatabase.viewMonSupperMealsSpecific("2");
        if (res2.getCount() == 0) {
//                // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer2 = new StringBuffer();
            while (res2.moveToNext()) {
                monSpmeal2 = res2.getString(1);
                monSpmeal2Nut = res2.getString(2);
            }}

        Cursor res3 = myDatabase.viewMonSupperMealsSpecific("3");
        if (res3.getCount() == 0) {
//            // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer3 = new StringBuffer();
            while (res3.moveToNext()) {
                monSpmeal3 = res3.getString(1);
                monSpmeal3Nut = res3.getString(2);
            }}

        Cursor res4 = myDatabase.viewMonSupperMealsSpecific("4");
        if (res4.getCount() == 0) {
//            // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer4 = new StringBuffer();
            while (res4.moveToNext()) {
                monSpmeal4 = res4.getString(1);
                monSpmeal4Nut = res4.getString(2);
            }}

        Cursor res5 = myDatabase.viewMonSupperMealsSpecific("5");
        if (res5.getCount() == 0) {
//            // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer5 = new StringBuffer();
            while (res5.moveToNext()) {
                monSpmeal5 = res5.getString(1);
                monSpmeal5Nut = res5.getString(2);
            }}
            firstMealTv.setText(monSpmeal1);
            firstMealNutrition.setText(monSpmeal1Nut);
            secondMealTv.setText(monSpmeal2);
            secondMealNutrition.setText(monSpmeal2Nut);
            thirdMealTv.setText(monSpmeal3);
            thirdMealNutrition.setText(monSpmeal3Nut);
            fourthMealTv.setText(monSpmeal4);
            fourthMealNutrition.setText(monSpmeal4Nut);
            fifthMealTv.setText(monSpmeal5);
            fifthMealNutrition.setText(monSpmeal5Nut);
            myDatabase.close();

    }

    //Tuesday breakfast
    @SuppressLint("SetTextI18n")
    public void tuesdayBreakfast() {
        todaysmealTv.setText("Tuesday Breakfast");
        String tueBrmeal1 = "", tueBrmeal1Nut = "", tueBrmeal2 = "", tueBrmeal2Nut = "", tueBrmeal3 = "",
                tueBrmeal3Nut = "", tueBrmeal4 = "", tueBrmeal4Nut = "", tueBrmeal5 = "", tueBrmeal5Nut = "";
        clearTextFields();

        Cursor res1 = myDatabase.viewTueBreakfastMealsSpecific("1");
        if (res1.getCount() == 0) {
//            // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer1 = new StringBuffer();
            while (res1.moveToNext()) {
                tueBrmeal1 = res1.getString(1);
                tueBrmeal1Nut = res1.getString(2);
            }}

        Cursor res2 = myDatabase.viewTueBreakfastMealsSpecific("2");
        if (res2.getCount() == 0) {
//                // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer2 = new StringBuffer();
            while (res2.moveToNext()) {
                tueBrmeal2 = res2.getString(1);
                tueBrmeal2Nut = res2.getString(2);
            }}

        Cursor res3 = myDatabase.viewTueBreakfastMealsSpecific("3");
        if (res3.getCount() == 0) {
//            // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer3 = new StringBuffer();
            while (res3.moveToNext()) {
                tueBrmeal3 = res3.getString(1);
                tueBrmeal3Nut = res3.getString(2);
            }}

        Cursor res4 = myDatabase.viewTueBreakfastMealsSpecific("4");
        if (res4.getCount() == 0) {
//            // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer4 = new StringBuffer();
            while (res4.moveToNext()) {
                tueBrmeal4 = res4.getString(1);
                tueBrmeal4Nut = res4.getString(2);
            }}

        Cursor res5 = myDatabase.viewTueBreakfastMealsSpecific("5");
        if (res5.getCount() == 0) {
//            // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer5 = new StringBuffer();
            while (res5.moveToNext()) {
                tueBrmeal5 = res5.getString(1);
                tueBrmeal5Nut = res5.getString(2);
            }}
            firstMealTv.setText(tueBrmeal1);
            firstMealNutrition.setText(tueBrmeal1Nut);
            secondMealTv.setText(tueBrmeal2);
            secondMealNutrition.setText(tueBrmeal2Nut);
            thirdMealTv.setText(tueBrmeal3);
            thirdMealNutrition.setText(tueBrmeal3Nut);
            fourthMealTv.setText(tueBrmeal4);
            fourthMealNutrition.setText(tueBrmeal4Nut);
            fifthMealTv.setText(tueBrmeal5);
            fifthMealNutrition.setText(tueBrmeal5Nut);
            myDatabase.close();

    }
        // tuesday lunch
    @SuppressLint("SetTextI18n")
    public void tuesdayLunch() {
        todaysmealTv.setText("Tuesday Lunch");
        String tueLnmeal1 = "", tueLnmeal1Nut = "", tueLnmeal2 = "", tueLnmeal2Nut = "", tueLnmeal3 = "",
                tueLnmeal3Nut = "", tueLnmeal4 = "", tueLnmeal4Nut = "", tueLnmeal5 = "", tueLnmeal5Nut = "";
        clearTextFields();

        Cursor res1 = myDatabase.viewTueLunchMealsSpecific("1");
        if (res1.getCount() == 0) {
//            // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer1 = new StringBuffer();
            while (res1.moveToNext()) {
                tueLnmeal1 = res1.getString(1);
                tueLnmeal1Nut = res1.getString(2);
            }}

        Cursor res2 = myDatabase.viewTueLunchMealsSpecific("2");
        if (res2.getCount() == 0) {
//                // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer2 = new StringBuffer();
            while (res2.moveToNext()) {
                tueLnmeal2 = res2.getString(1);
                tueLnmeal2Nut = res2.getString(2);
            }}

        Cursor res3 = myDatabase.viewTueLunchMealsSpecific("3");
        if (res3.getCount() == 0) {
//            // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer3 = new StringBuffer();
            while (res3.moveToNext()) {
                tueLnmeal3 = res3.getString(1);
                tueLnmeal3Nut = res3.getString(2);
            }}

        Cursor res4 = myDatabase.viewTueLunchMealsSpecific("4");
        if (res4.getCount() == 0) {
//            // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer4 = new StringBuffer();
            while (res4.moveToNext()) {
                tueLnmeal4 = res4.getString(1);
                tueLnmeal4Nut = res4.getString(2);
            }}

        Cursor res5 = myDatabase.viewTueLunchMealsSpecific("5");
        if (res5.getCount() == 0) {
//            // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer5 = new StringBuffer();
            while (res5.moveToNext()) {
                tueLnmeal5 = res5.getString(1);
                tueLnmeal5Nut = res5.getString(2);
            }}
            firstMealTv.setText(tueLnmeal1);
            firstMealNutrition.setText(tueLnmeal1Nut);
            secondMealTv.setText(tueLnmeal2);
            secondMealNutrition.setText(tueLnmeal2Nut);
            thirdMealTv.setText(tueLnmeal3);
            thirdMealNutrition.setText(tueLnmeal3Nut);
            fourthMealTv.setText(tueLnmeal4);
            fourthMealNutrition.setText(tueLnmeal4Nut);
            fifthMealTv.setText(tueLnmeal5);
            fifthMealNutrition.setText(tueLnmeal5Nut);
            myDatabase.close();


    }
    //Tuesday supper
    @SuppressLint("SetTextI18n")
    public void tuesdaySupper() {
        todaysmealTv.setText("Tuesday Supper");
        String tueSpmeal1 = "", tueSpmeal1Nut = "", tueSpmeal2 = "", tueSpmeal2Nut = "", tueSpmeal3 = "",
                tueSpmeal3Nut = "", tueSpmeal4 = "", tueSpmeal4Nut = "", tueSpmeal5 = "", tueSpmeal5Nut = "";
        clearTextFields();

        Cursor res1 = myDatabase.viewTueSupperMealsSpecific("1");
        if (res1.getCount() == 0) {
//            // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer1 = new StringBuffer();
            while (res1.moveToNext()) {
                tueSpmeal1 = res1.getString(1);
                tueSpmeal1Nut = res1.getString(2);
            }}

        Cursor res2 = myDatabase.viewTueSupperMealsSpecific("2");
        if (res2.getCount() == 0) {
//                // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer2 = new StringBuffer();
            while (res2.moveToNext()) {
                tueSpmeal2 = res2.getString(1);
                tueSpmeal2Nut = res2.getString(2);
            }}

        Cursor res3 = myDatabase.viewTueSupperMealsSpecific("3");
        if (res3.getCount() == 0) {
//            // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer3 = new StringBuffer();
            while (res3.moveToNext()) {
                tueSpmeal3 = res3.getString(1);
                tueSpmeal3Nut = res3.getString(2);
            }}

        Cursor res4 = myDatabase.viewTueSupperMealsSpecific("4");
        if (res4.getCount() == 0) {
//            // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer4 = new StringBuffer();
            while (res4.moveToNext()) {
                tueSpmeal4 = res4.getString(1);
                tueSpmeal4Nut = res4.getString(2);
            }}

        Cursor res5 = myDatabase.viewTueSupperMealsSpecific("5");
        if (res5.getCount() == 0) {
//            // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer5 = new StringBuffer();
            while (res5.moveToNext()) {
                tueSpmeal5 = res5.getString(1);
                tueSpmeal5Nut = res5.getString(2);
            }}
            firstMealTv.setText(tueSpmeal1);
            firstMealNutrition.setText(tueSpmeal1Nut);
            secondMealTv.setText(tueSpmeal2);
            secondMealNutrition.setText(tueSpmeal2Nut);
            thirdMealTv.setText(tueSpmeal3);
            thirdMealNutrition.setText(tueSpmeal3Nut);
            fourthMealTv.setText(tueSpmeal4);
            fourthMealNutrition.setText(tueSpmeal4Nut);
            fifthMealTv.setText(tueSpmeal5);
            fifthMealNutrition.setText(tueSpmeal5Nut);
            myDatabase.close();

    }

    //Wednesday breakfast
    @SuppressLint("SetTextI18n")
    public void wednesdayBreakfast() {
        todaysmealTv.setText("Wednesday Breakfast");
        String wedBrmeal1="",wedBrmeal1Nut="",wedBrmeal2="",wedBrmeal2Nut="",wedBrmeal3="",
                wedBrmeal3Nut="",wedBrmeal4="",wedBrmeal4Nut="",wedBrmeal5="",wedBrmeal5Nut="";
        clearTextFields();

        Cursor res1 = myDatabase.viewWedBreakfastMealsSpecific("1");
        if (res1.getCount() == 0) {
//            // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer1 = new StringBuffer();
            while (res1.moveToNext()) {
                wedBrmeal1 = res1.getString(1);
                wedBrmeal1Nut = res1.getString(2);
            }}

        Cursor res2 = myDatabase.viewWedBreakfastMealsSpecific("2");
        if (res2.getCount() == 0) {
//                // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer2 = new StringBuffer();
            while (res2.moveToNext()) {
                wedBrmeal2 = res2.getString(1);
                wedBrmeal2Nut = res2.getString(2);
            }}

        Cursor res3 = myDatabase.viewWedBreakfastMealsSpecific("3");
        if (res3.getCount() == 0) {
            // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer3 = new StringBuffer();
            while (res3.moveToNext()) {
                wedBrmeal3 = res3.getString(1);
                wedBrmeal3Nut = res3.getString(2);
            }}

        Cursor res4 = myDatabase.viewWedBreakfastMealsSpecific("4");
        if (res4.getCount() == 0) {
            // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer4 = new StringBuffer();
            while (res4.moveToNext()) {
                wedBrmeal4 = res4.getString(1);
                wedBrmeal4Nut = res4.getString(2);
            }}

        Cursor res5 = myDatabase.viewWedBreakfastMealsSpecific("5");
        if (res5.getCount() == 0) {
            // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer5 = new StringBuffer();
            while (res5.moveToNext()) {
                wedBrmeal5 = res5.getString(1);
                wedBrmeal5Nut = res5.getString(2);
            }}
            firstMealTv.setText(wedBrmeal1);
            firstMealNutrition.setText(wedBrmeal1Nut);
            secondMealTv.setText(wedBrmeal2);
            secondMealNutrition.setText(wedBrmeal2Nut);
            thirdMealTv.setText(wedBrmeal3);
            thirdMealNutrition.setText(wedBrmeal3Nut);
            fourthMealTv.setText(wedBrmeal4);
            fourthMealNutrition.setText(wedBrmeal4Nut);
            fifthMealTv.setText(wedBrmeal5);
            fifthMealNutrition.setText(wedBrmeal5Nut);
            myDatabase.close();
}
    //wednesday lunch
    @SuppressLint("SetTextI18n")
    public void wednesdayLunch(){
        todaysmealTv.setText("Wednesday Lunch");
        String wedLnmeal1="",wedLnmeal1Nut="",wedLnmeal2="",wedLnmeal2Nut="",wedLnmeal3="",
                wedLnmeal3Nut="",wedLnmeal4="",wedLnmeal4Nut="",wedLnmeal5="",wedLnmeal5Nut="";
        clearTextFields();

        Cursor res1 = myDatabase.viewWedLunchMealsSpecific("1");
        if (res1.getCount() == 0) {
            // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer1 = new StringBuffer();
            while (res1.moveToNext()) {
                wedLnmeal1 = res1.getString(1);
                wedLnmeal1Nut = res1.getString(2);
            }}

        Cursor res2 = myDatabase.viewWedLunchMealsSpecific("2");
        if (res2.getCount() == 0) {
//                // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer2 = new StringBuffer();
            while (res2.moveToNext()) {
                wedLnmeal2 = res2.getString(1);
                wedLnmeal2Nut = res2.getString(2);
            }}

        Cursor res3 = myDatabase.viewWedLunchMealsSpecific("3");
        if (res3.getCount() == 0) {
            // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer3 = new StringBuffer();
            while (res3.moveToNext()) {
                wedLnmeal3 = res3.getString(1);
                wedLnmeal3Nut = res3.getString(2);
            }}

        Cursor res4 = myDatabase.viewWedLunchMealsSpecific("4");
        if (res4.getCount() == 0) {
            // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer4 = new StringBuffer();
            while (res4.moveToNext()) {
                wedLnmeal4 = res4.getString(1);
                wedLnmeal4Nut = res4.getString(2);
            }}

        Cursor res5 = myDatabase.viewWedLunchMealsSpecific("5");
        if (res5.getCount() == 0) {
            // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer5 = new StringBuffer();
            while (res5.moveToNext()) {
                wedLnmeal5 = res5.getString(1);
                wedLnmeal5Nut = res5.getString(2);
            }}
            firstMealTv.setText(wedLnmeal1);
            firstMealNutrition.setText(wedLnmeal1Nut);
            secondMealTv.setText(wedLnmeal2);
            secondMealNutrition.setText(wedLnmeal2Nut);
            thirdMealTv.setText(wedLnmeal3);
            thirdMealNutrition.setText(wedLnmeal3Nut);
            fourthMealTv.setText(wedLnmeal4);
            fourthMealNutrition.setText(wedLnmeal4Nut);
            fifthMealTv.setText(wedLnmeal5);
            fifthMealNutrition.setText(wedLnmeal5Nut);
            myDatabase.close();

    }
    //wednesday supper
    @SuppressLint("SetTextI18n")
    public void wednesdaySupper() {
        todaysmealTv.setText("Wednesday Supper");
        String wedSpmeal1 = "", wedSpmeal1Nut = "", wedSpmeal2 = "", wedSpmeal2Nut = "", wedSpmeal3 = "",
                wedSpmeal3Nut = "", wedSpmeal4 = "", wedSpmeal4Nut = "", wedSpmeal5 = "", wedSpmeal5Nut = "";
        clearTextFields();

        Cursor res1 = myDatabase.viewWedSupperMealsSpecific("1");
        if (res1.getCount() == 0) {
            // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer1 = new StringBuffer();
            while (res1.moveToNext()) {
                wedSpmeal1 = res1.getString(1);
                wedSpmeal1Nut = res1.getString(2);
            }}

        Cursor res2 = myDatabase.viewWedSupperMealsSpecific("2");
        if (res2.getCount() == 0) {
//                // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer2 = new StringBuffer();
            while (res2.moveToNext()) {
                wedSpmeal2 = res2.getString(1);
                wedSpmeal2Nut = res2.getString(2);
            }}

        Cursor res3 = myDatabase.viewWedSupperMealsSpecific("3");
        if (res3.getCount() == 0) {
            // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer3 = new StringBuffer();
            while (res3.moveToNext()) {
                wedSpmeal3 = res3.getString(1);
                wedSpmeal3Nut = res3.getString(2);
            }}

        Cursor res4 = myDatabase.viewWedSupperMealsSpecific("4");
        if (res4.getCount() == 0) {
            // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer4 = new StringBuffer();
            while (res4.moveToNext()) {
                wedSpmeal4 = res4.getString(1);
                wedSpmeal4Nut = res4.getString(2);
            }}

        Cursor res5 = myDatabase.viewWedSupperMealsSpecific("5");
        if (res5.getCount() == 0) {
            // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer5 = new StringBuffer();
            while (res5.moveToNext()) {
                wedSpmeal5 = res5.getString(1);
                wedSpmeal5Nut = res5.getString(2);
            }}
            firstMealTv.setText(wedSpmeal1);
            firstMealNutrition.setText(wedSpmeal1Nut);
            secondMealTv.setText(wedSpmeal2);
            secondMealNutrition.setText(wedSpmeal2Nut);
            thirdMealTv.setText(wedSpmeal3);
            thirdMealNutrition.setText(wedSpmeal3Nut);
            fourthMealTv.setText(wedSpmeal4);
            fourthMealNutrition.setText(wedSpmeal4Nut);
            fifthMealTv.setText(wedSpmeal5);
            fifthMealNutrition.setText(wedSpmeal5Nut);
            myDatabase.close();

    }

    //Thursday breakfast
    @SuppressLint("SetTextI18n")
    public void thursdayBreakfast() {
        todaysmealTv.setText("Thursday Breakfast");
        String thurBrmeal1 = "", thurBrmeal1Nut = "", thurBrmeal2 = "", thurBrmeal2Nut = "", thurBrmeal3 = "",
                thurBrmeal3Nut = "", thurBrmeal4 = "", thurBrmeal4Nut = "", thurBrmeal5 = "", thurBrmeal5Nut = "";
        clearTextFields();

        Cursor res1 = myDatabase.viewThurBreakfastMealsSpecific("1");
        if (res1.getCount() == 0) {
            // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer1 = new StringBuffer();
            while (res1.moveToNext()) {
                thurBrmeal1 = res1.getString(1);
                thurBrmeal1Nut = res1.getString(2);
            }}

        Cursor res2 = myDatabase.viewThurBreakfastMealsSpecific("2");
        if (res2.getCount() == 0) {
//                // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer2 = new StringBuffer();
            while (res2.moveToNext()) {
                thurBrmeal2 = res2.getString(1);
                thurBrmeal2Nut = res2.getString(2);
            }}

        Cursor res3 = myDatabase.viewThurBreakfastMealsSpecific("3");
        if (res3.getCount() == 0) {
            // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer3 = new StringBuffer();
            while (res3.moveToNext()) {
                thurBrmeal3 = res3.getString(1);
                thurBrmeal3Nut = res3.getString(2);
            }}

        Cursor res4 = myDatabase.viewThurBreakfastMealsSpecific("4");
        if (res4.getCount() == 0) {
            // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer4 = new StringBuffer();
            while (res4.moveToNext()) {
                thurBrmeal4 = res4.getString(1);
                thurBrmeal4Nut = res4.getString(2);
            }}

        Cursor res5 = myDatabase.viewThurBreakfastMealsSpecific("5");
        if (res5.getCount() == 0) {
            // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer5 = new StringBuffer();
            while (res5.moveToNext()) {
                thurBrmeal5 = res5.getString(1);
                thurBrmeal5Nut = res5.getString(2);
            }}
            firstMealTv.setText(thurBrmeal1);
            firstMealNutrition.setText(thurBrmeal1Nut);
            secondMealTv.setText(thurBrmeal2);
            secondMealNutrition.setText(thurBrmeal2Nut);
            thirdMealTv.setText(thurBrmeal3);
            thirdMealNutrition.setText(thurBrmeal3Nut);
            fourthMealTv.setText(thurBrmeal4);
            fourthMealNutrition.setText(thurBrmeal4Nut);
            fifthMealTv.setText(thurBrmeal5);
            fifthMealNutrition.setText(thurBrmeal5Nut);
            myDatabase.close();
    }
    //thursday lunch
    @SuppressLint("SetTextI18n")
    public void thursdayLunch(){
        todaysmealTv.setText("Thursday Lunch");
        String thurLnmeal1="",thurLnmeal1Nut="",thurLnmeal2="",thurLnmeal2Nut="",thurLnmeal3="",
                thurLnmeal3Nut="",thurLnmeal4="",thurLnmeal4Nut="",thurLnmeal5="",thurLnmeal5Nut="";
        clearTextFields();

        Cursor res1 = myDatabase.viewThurLunchMealsSpecific("1");
        if (res1.getCount() == 0) {
            // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer1 = new StringBuffer();
            while (res1.moveToNext()) {
                thurLnmeal1 = res1.getString(1);
                thurLnmeal1Nut = res1.getString(2);
            }}

        Cursor res2 = myDatabase.viewThurLunchMealsSpecific("2");
        if (res2.getCount() == 0) {
//                // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer2 = new StringBuffer();
            while (res2.moveToNext()) {
                thurLnmeal2 = res2.getString(1);
                thurLnmeal2Nut = res2.getString(2);
            }}

        Cursor res3 = myDatabase.viewThurLunchMealsSpecific("3");
        if (res3.getCount() == 0) {
            // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer3 = new StringBuffer();
            while (res3.moveToNext()) {
                thurLnmeal3 = res3.getString(1);
                thurLnmeal3Nut = res3.getString(2);
            }}

        Cursor res4 = myDatabase.viewThurLunchMealsSpecific("4");
        if (res4.getCount() == 0) {
            // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer4 = new StringBuffer();
            while (res4.moveToNext()) {
                thurLnmeal4 = res4.getString(1);
                thurLnmeal4Nut = res4.getString(2);
            }}

        Cursor res5 = myDatabase.viewThurLunchMealsSpecific("5");
        if (res5.getCount() == 0) {
            // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer5 = new StringBuffer();
            while (res5.moveToNext()) {
                thurLnmeal5 = res5.getString(1);
                thurLnmeal5Nut = res5.getString(2);
            }}
            firstMealTv.setText(thurLnmeal1);
            firstMealNutrition.setText(thurLnmeal1Nut);
            secondMealTv.setText(thurLnmeal2);
            secondMealNutrition.setText(thurLnmeal2Nut);
            thirdMealTv.setText(thurLnmeal3);
            thirdMealNutrition.setText(thurLnmeal3Nut);
            fourthMealTv.setText(thurLnmeal4);
            fourthMealNutrition.setText(thurLnmeal4Nut);
            fifthMealTv.setText(thurLnmeal5);
            fifthMealNutrition.setText(thurLnmeal5Nut);
            myDatabase.close();
    }
    @SuppressLint("SetTextI18n")
    public void thursdaySupper(){
        todaysmealTv.setText("Thursday Supper");
        String thurSpmeal1="",thurSpmeal1Nut="",thurSpmeal2="",thurSpmeal2Nut="",thurSpmeal3="",
                thurSpmeal3Nut="",thurSpmeal4="",thurSpmeal4Nut="",thurSpmeal5="",thurSpmeal5Nut="";
        clearTextFields();

        Cursor res1 = myDatabase.viewThurSupperMealsSpecific("1");
        if (res1.getCount() == 0) {
            // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer1 = new StringBuffer();
            while (res1.moveToNext()) {
                thurSpmeal1 = res1.getString(1);
                thurSpmeal1Nut = res1.getString(2);
            }}

        Cursor res2 = myDatabase.viewThurSupperMealsSpecific("2");
        if (res2.getCount() == 0) {
//                // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer2 = new StringBuffer();
            while (res2.moveToNext()) {
                thurSpmeal2 = res2.getString(1);
                thurSpmeal2Nut = res2.getString(2);
            }}

        Cursor res3 = myDatabase.viewThurSupperMealsSpecific("3");
        if (res3.getCount() == 0) {
            // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer3 = new StringBuffer();
            while (res3.moveToNext()) {
                thurSpmeal3 = res3.getString(1);
                thurSpmeal3Nut = res3.getString(2);
            }}

        Cursor res4 = myDatabase.viewThurSupperMealsSpecific("4");
        if (res4.getCount() == 0) {
            // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer4 = new StringBuffer();
            while (res4.moveToNext()) {
                thurSpmeal4 = res4.getString(1);
                thurSpmeal4Nut = res4.getString(2);
            }}

        Cursor res5 = myDatabase.viewThurSupperMealsSpecific("5");
        if (res5.getCount() == 0) {
            // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer5 = new StringBuffer();
            while (res5.moveToNext()) {
                thurSpmeal5 = res5.getString(1);
                thurSpmeal5Nut = res5.getString(2);
            }}
            firstMealTv.setText(thurSpmeal1);
            firstMealNutrition.setText(thurSpmeal1Nut);
            secondMealTv.setText(thurSpmeal2);
            secondMealNutrition.setText(thurSpmeal2Nut);
            thirdMealTv.setText(thurSpmeal3);
            thirdMealNutrition.setText(thurSpmeal3Nut);
            fourthMealTv.setText(thurSpmeal4);
            fourthMealNutrition.setText(thurSpmeal4Nut);
            fifthMealTv.setText(thurSpmeal5);
            fifthMealNutrition.setText(thurSpmeal5Nut);
            myDatabase.close();

    }

    //Friday breakfast
    @SuppressLint("SetTextI18n")
    public void fridayBreakfast(){
        todaysmealTv.setText("Friday Breakfast");
        String friBrmeal1="",friBrmeal1Nut="",friBrmeal2="",friBrmeal2Nut="",friBrmeal3="",
                friBrmeal3Nut="",friBrmeal4="",friBrmeal4Nut="",friBrmeal5="",friBrmeal5Nut="";
        clearTextFields();

        Cursor res1 = myDatabase.viewFriBreakfastMealsSpecific("1");
        if (res1.getCount() == 0) {
            // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer1 = new StringBuffer();
            while (res1.moveToNext()) {
                friBrmeal1 = res1.getString(1);
                friBrmeal1Nut = res1.getString(2);
            }}

        Cursor res2 = myDatabase.viewFriBreakfastMealsSpecific("2");
        if (res2.getCount() == 0) {
//                // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer2 = new StringBuffer();
            while (res2.moveToNext()) {
                friBrmeal2 = res2.getString(1);
                friBrmeal2Nut = res2.getString(2);
            }}

        Cursor res3 = myDatabase.viewFriBreakfastMealsSpecific("3");
        if (res3.getCount() == 0) {
            // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer3 = new StringBuffer();
            while (res3.moveToNext()) {
                friBrmeal3 = res3.getString(1);
                friBrmeal3Nut = res3.getString(2);
            }}

        Cursor res4 = myDatabase.viewFriBreakfastMealsSpecific("4");
        if (res4.getCount() == 0) {
            // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer4 = new StringBuffer();
            while (res4.moveToNext()) {
                friBrmeal4 = res4.getString(1);
                friBrmeal4Nut = res4.getString(2);
            }}

        Cursor res5 = myDatabase.viewFriBreakfastMealsSpecific("5");
        if (res5.getCount() == 0) {
            // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer5 = new StringBuffer();
            while (res5.moveToNext()) {
                friBrmeal5 = res5.getString(1);
                friBrmeal5Nut = res5.getString(2);
            }}
            firstMealTv.setText(friBrmeal1);
            firstMealNutrition.setText(friBrmeal1Nut);
            secondMealTv.setText(friBrmeal2);
            secondMealNutrition.setText(friBrmeal2Nut);
            thirdMealTv.setText(friBrmeal3);
            thirdMealNutrition.setText(friBrmeal3Nut);
            fourthMealTv.setText(friBrmeal4);
            fourthMealNutrition.setText(friBrmeal4Nut);
            fifthMealTv.setText(friBrmeal5);
            fifthMealNutrition.setText(friBrmeal5Nut);
            myDatabase.close();

    }
    //friday lunch
    @SuppressLint("SetTextI18n")
    public void fridayLunch(){
        todaysmealTv.setText("Friday Lunch");
        String friLnmeal1="",friLnmeal1Nut="",friLnmeal2="", friLnmeal2Nut="",friLnmeal3="",
                friLnmeal3Nut="",friLnmeal4="",friLnmeal4Nut="",friLnmeal5="",friLnmeal5Nut="";
        clearTextFields();

        Cursor res1 = myDatabase.viewFriLunchMealsSpecific("1");
        if (res1.getCount() == 0) {
            // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer1 = new StringBuffer();
            while (res1.moveToNext()) {
                friLnmeal1 = res1.getString(1);
                friLnmeal1Nut = res1.getString(2);
            }}

        Cursor res2 = myDatabase.viewFriLunchMealsSpecific("2");
        if (res2.getCount() == 0) {
//                // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer2 = new StringBuffer();
            while (res2.moveToNext()) {
                friLnmeal2 = res2.getString(1);
                friLnmeal2Nut = res2.getString(2);
            }}

        Cursor res3 = myDatabase.viewFriLunchMealsSpecific("3");
        if (res3.getCount() == 0) {
            // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer3 = new StringBuffer();
            while (res3.moveToNext()) {
                friLnmeal3 = res3.getString(1);
                friLnmeal3Nut = res3.getString(2);
            }}

        Cursor res4 = myDatabase.viewFriLunchMealsSpecific("4");
        if (res4.getCount() == 0) {
            // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer4 = new StringBuffer();
            while (res4.moveToNext()) {
                friLnmeal4 = res4.getString(1);
                friLnmeal4Nut = res4.getString(2);
            }}

        Cursor res5 = myDatabase.viewFriLunchMealsSpecific("5");
        if (res5.getCount() == 0) {
            // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer5 = new StringBuffer();
            while (res5.moveToNext()) {
                friLnmeal5 = res5.getString(1);
                friLnmeal5Nut = res5.getString(2);
            }}
            firstMealTv.setText(friLnmeal1);
            firstMealNutrition.setText(friLnmeal1Nut);
            secondMealTv.setText(friLnmeal2);
            secondMealNutrition.setText(friLnmeal2Nut);
            thirdMealTv.setText(friLnmeal3);
            thirdMealNutrition.setText(friLnmeal3Nut);
            fourthMealTv.setText(friLnmeal4);
            fourthMealNutrition.setText(friLnmeal4Nut);
            fifthMealTv.setText(friLnmeal5);
            fifthMealNutrition.setText(friLnmeal5Nut);
            myDatabase.close();
    }
    //friday supper
    @SuppressLint("SetTextI18n")
    public void fridaySupper(){
        todaysmealTv.setText("Friday Supper");
        String friSpmeal1="",friSpmeal1Nut="",friSpmeal2="",friSpmeal2Nut="",friSpmeal3="",
                friSpmeal3Nut="",friSpmeal4="",friSpmeal4Nut="",friSpmeal5="",friSpmeal5Nut="";
        clearTextFields();

        Cursor res1 = myDatabase.viewFriSupperMealsSpecific("1");
        if (res1.getCount() == 0) {
            // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer1 = new StringBuffer();
            while (res1.moveToNext()) {
                friSpmeal1 = res1.getString(1);
                friSpmeal1Nut = res1.getString(2);
            }}

        Cursor res2 = myDatabase.viewFriSupperMealsSpecific("2");
        if (res2.getCount() == 0) {
//                // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer2 = new StringBuffer();
            while (res2.moveToNext()) {
                friSpmeal2 = res2.getString(1);
                friSpmeal2Nut = res2.getString(2);
            }}

        Cursor res3 = myDatabase.viewFriSupperMealsSpecific("3");
        if (res3.getCount() == 0) {
            // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer3 = new StringBuffer();
            while (res3.moveToNext()) {
                friSpmeal3 = res3.getString(1);
                friSpmeal3Nut = res3.getString(2);
            }}

        Cursor res4 = myDatabase.viewFriSupperMealsSpecific("4");
        if (res4.getCount() == 0) {
            // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer4 = new StringBuffer();
            while (res4.moveToNext()) {
                friSpmeal4 = res4.getString(1);
                friSpmeal4Nut = res4.getString(2);
            }}

        Cursor res5 = myDatabase.viewFriSupperMealsSpecific("5");
        if (res5.getCount() == 0) {
            // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer5 = new StringBuffer();
            while (res5.moveToNext()) {
                friSpmeal5 = res5.getString(1);
                friSpmeal5Nut = res5.getString(2);
            }}
            firstMealTv.setText(friSpmeal1);
            firstMealNutrition.setText(friSpmeal1Nut);
            secondMealTv.setText(friSpmeal2);
            secondMealNutrition.setText(friSpmeal2Nut);
            thirdMealTv.setText(friSpmeal3);
            thirdMealNutrition.setText(friSpmeal3Nut);
            fourthMealTv.setText(friSpmeal4);
            fourthMealNutrition.setText(friSpmeal4Nut);
            fifthMealTv.setText(friSpmeal5);
            fifthMealNutrition.setText(friSpmeal5Nut);
            myDatabase.close();

    }

    //Saturday breakfast
    @SuppressLint("SetTextI18n")
    public void saturdayBreakfast(){
        todaysmealTv.setText("Saturday Breakfast");
        String satBrmeal1="",satBrmeal1Nut="",satBrmeal2="",satBrmeal2Nut="",satBrmeal3="",
                satBrmeal3Nut="",satBrmeal4="",satBrmeal4Nut="",satBrmeal5="",satBrmeal5Nut="";
        clearTextFields();

        Cursor res1 = myDatabase.viewSatBreakfastMealsSpecific("1");
        if (res1.getCount() == 0) {
            // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer1 = new StringBuffer();
            while (res1.moveToNext()) {
                satBrmeal1 = res1.getString(1);
                satBrmeal1Nut = res1.getString(2);
            }}

        Cursor res2 = myDatabase.viewSatBreakfastMealsSpecific("2");
        if (res2.getCount() == 0) {
//                // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer2 = new StringBuffer();
            while (res2.moveToNext()) {
                satBrmeal2 = res2.getString(1);
                satBrmeal2Nut = res2.getString(2);
            }}

        Cursor res3 = myDatabase.viewSatBreakfastMealsSpecific("3");
        if (res3.getCount() == 0) {
            // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer3 = new StringBuffer();
            while (res3.moveToNext()) {
                satBrmeal3 = res3.getString(1);
                satBrmeal3Nut = res3.getString(2);
            }}

        Cursor res4 = myDatabase.viewSatBreakfastMealsSpecific("4");
        if (res4.getCount() == 0) {
            // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer4 = new StringBuffer();
            while (res4.moveToNext()) {
                satBrmeal4 = res4.getString(1);
                satBrmeal4Nut = res4.getString(2);
            }}

        Cursor res5 = myDatabase.viewSatBreakfastMealsSpecific("5");
        if (res5.getCount() == 0) {
            // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer5 = new StringBuffer();
            while (res5.moveToNext()) {
                satBrmeal5 = res5.getString(1);
                satBrmeal5Nut = res5.getString(2);
            }}
            firstMealTv.setText(satBrmeal1);
            firstMealNutrition.setText(satBrmeal1Nut);
            secondMealTv.setText(satBrmeal2);
            secondMealNutrition.setText(satBrmeal2Nut);
            thirdMealTv.setText(satBrmeal3);
            thirdMealNutrition.setText(satBrmeal3Nut);
            fourthMealTv.setText(satBrmeal4);
            fourthMealNutrition.setText(satBrmeal4Nut);
            fifthMealTv.setText(satBrmeal5);
            fifthMealNutrition.setText(satBrmeal5Nut);
            myDatabase.close();

    }
    //saturday lunch
    @SuppressLint("SetTextI18n")
    public void saturdayLunch(){
        todaysmealTv.setText("Saturday Lunch");
        String satLnmeal1="",satLnmeal1Nut="",satLnmeal2="",satLnmeal2Nut="",satLnmeal3="",
                satLnmeal3Nut="",satLnmeal4="",satLnmeal4Nut="",satLnmeal5="",satLnmeal5Nut="";
        clearTextFields();

        Cursor res1 = myDatabase.viewSatLunchMealsSpecific("1");
        if (res1.getCount() == 0) {
            // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer1 = new StringBuffer();
            while (res1.moveToNext()) {
                satLnmeal1 = res1.getString(1);
                satLnmeal1Nut = res1.getString(2);
            }}

        Cursor res2 = myDatabase.viewSatLunchMealsSpecific("2");
        if (res2.getCount() == 0) {
//                // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer2 = new StringBuffer();
            while (res2.moveToNext()) {
                satLnmeal2 = res2.getString(1);
                satLnmeal2Nut = res2.getString(2);
            }}

        Cursor res3 = myDatabase.viewSatLunchMealsSpecific("3");
        if (res3.getCount() == 0) {
            // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer3 = new StringBuffer();
            while (res3.moveToNext()) {
                satLnmeal3 = res3.getString(1);
                satLnmeal3Nut = res3.getString(2);
            }}

        Cursor res4 = myDatabase.viewSatLunchMealsSpecific("4");
        if (res4.getCount() == 0) {
            // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer4 = new StringBuffer();
            while (res4.moveToNext()) {
                satLnmeal4 = res4.getString(1);
                satLnmeal4Nut = res4.getString(2);
            }}

        Cursor res5 = myDatabase.viewSatLunchMealsSpecific("5");
        if (res5.getCount() == 0) {
            // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer5 = new StringBuffer();
            while (res5.moveToNext()) {
                satLnmeal5 = res5.getString(1);
                satLnmeal5Nut = res5.getString(2);
            }}
            firstMealTv.setText(satLnmeal1);
            firstMealNutrition.setText(satLnmeal1Nut);
            secondMealTv.setText(satLnmeal2);
            secondMealNutrition.setText(satLnmeal2Nut);
            thirdMealTv.setText(satLnmeal3);
            thirdMealNutrition.setText(satLnmeal3Nut);
            fourthMealTv.setText(satLnmeal4);
            fourthMealNutrition.setText(satLnmeal4Nut);
            fifthMealTv.setText(satLnmeal5);
            fifthMealNutrition.setText(satLnmeal5Nut);
            myDatabase.close();

    }
    //saturday supper
    @SuppressLint("SetTextI18n")
    public void saturdaySupper(){
        todaysmealTv.setText("Saturday Supper");
        String satSpmeal1="",satSpmeal1Nut="",satSpmeal2="",satSpmeal2Nut="",satSpmeal3="",
                satSpmeal3Nut="",satSpmeal4="",satSpmeal4Nut="",satSpmeal5="",satSpmeal5Nut="";
        clearTextFields();

        Cursor res1 = myDatabase.viewSatSupperMealsSpecific("1");
        if (res1.getCount() == 0) {
            // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer1 = new StringBuffer();
            while (res1.moveToNext()) {
                satSpmeal1 = res1.getString(1);
                satSpmeal1Nut = res1.getString(2);
            }}

        Cursor res2 = myDatabase.viewSatSupperMealsSpecific("2");
        if (res2.getCount() == 0) {
//                // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer2 = new StringBuffer();
            while (res2.moveToNext()) {
                satSpmeal2 = res2.getString(1);
                satSpmeal2Nut = res2.getString(2);
            }}

        Cursor res3 = myDatabase.viewSatSupperMealsSpecific("3");
        if (res3.getCount() == 0) {
            // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer3 = new StringBuffer();
            while (res3.moveToNext()) {
                satSpmeal3 = res3.getString(1);
                satSpmeal3Nut = res3.getString(2);
            }}

        Cursor res4 = myDatabase.viewSatSupperMealsSpecific("4");
        if (res4.getCount() == 0) {
            // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer4 = new StringBuffer();
            while (res4.moveToNext()) {
                satSpmeal4 = res4.getString(1);
                satSpmeal4Nut = res4.getString(2);
            }}

        Cursor res5 = myDatabase.viewSatSupperMealsSpecific("5");
        if (res5.getCount() == 0) {
            // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer5 = new StringBuffer();
            while (res5.moveToNext()) {
                satSpmeal5 = res5.getString(1);
                satSpmeal5Nut = res5.getString(2);
            }}
            firstMealTv.setText(satSpmeal1);
            firstMealNutrition.setText(satSpmeal1Nut);
            secondMealTv.setText(satSpmeal2);
            secondMealNutrition.setText(satSpmeal2Nut);
            thirdMealTv.setText(satSpmeal3);
            thirdMealNutrition.setText(satSpmeal3Nut);
            fourthMealTv.setText(satSpmeal4);
            fourthMealNutrition.setText(satSpmeal4Nut);
            fifthMealTv.setText(satSpmeal5);
            fifthMealNutrition.setText(satSpmeal5Nut);
            myDatabase.close();
    }

    //Sunday breakfast
    @SuppressLint("SetTextI18n")
    public void sundayBreakfast(){
        todaysmealTv.setText("Sunday Breakfast");
        String sunBrmeal1="",sunBrmeal1Nut="",sunBrmeal2="",sunBrmeal2Nut="",sunBrmeal3="",
                sunBrmeal3Nut="",sunBrmeal4="",sunBrmeal4Nut="",sunBrmeal5="",sunBrmeal5Nut="";
        clearTextFields();

        Cursor res1 = myDatabase.viewSunBreakfastMealsSpecific("1");
        if (res1.getCount() == 0) {
            // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer1 = new StringBuffer();
            while (res1.moveToNext()) {
                sunBrmeal1 = res1.getString(1);
                sunBrmeal1Nut = res1.getString(2);
            }}

        Cursor res2 = myDatabase.viewSunBreakfastMealsSpecific("2");
        if (res2.getCount() == 0) {
//                // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer2 = new StringBuffer();
            while (res2.moveToNext()) {
                sunBrmeal2 = res2.getString(1);
                sunBrmeal2Nut = res2.getString(2);
            }}

        Cursor res3 = myDatabase.viewSunBreakfastMealsSpecific("3");
        if (res3.getCount() == 0) {
            // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer3 = new StringBuffer();
            while (res3.moveToNext()) {
                sunBrmeal3 = res3.getString(1);
                sunBrmeal3Nut = res3.getString(2);
            }}

        Cursor res4 = myDatabase.viewSunBreakfastMealsSpecific("4");
        if (res4.getCount() == 0) {
            // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer4 = new StringBuffer();
            while (res4.moveToNext()) {
                sunBrmeal4 = res4.getString(1);
                sunBrmeal4Nut = res4.getString(2);
            }}

        Cursor res5 = myDatabase.viewSunBreakfastMealsSpecific("5");
        if (res5.getCount() == 0) {
            // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer5 = new StringBuffer();
            while (res5.moveToNext()) {
                sunBrmeal5 = res5.getString(1);
                sunBrmeal5Nut = res5.getString(2);
            }}
            firstMealTv.setText(sunBrmeal1);
            firstMealNutrition.setText(sunBrmeal1Nut);
            secondMealTv.setText(sunBrmeal2);
            secondMealNutrition.setText(sunBrmeal2Nut);
            thirdMealTv.setText(sunBrmeal3);
            thirdMealNutrition.setText(sunBrmeal3Nut);
            fourthMealTv.setText(sunBrmeal4);
            fourthMealNutrition.setText(sunBrmeal4Nut);
            fifthMealTv.setText(sunBrmeal5);
            fifthMealNutrition.setText(sunBrmeal5Nut);
            myDatabase.close();

    }
    //sunday lunch
    @SuppressLint("SetTextI18n")
    public void sundayLunch(){
        todaysmealTv.setText("Sunday Lunch");
        String sunLnmeal1="",sunLnmeal1Nut="",sunLnmeal2="",sunLnmeal2Nut="",sunLnmeal3="",
                sunLnmeal3Nut="",sunLnmeal4="",sunLnmeal4Nut="",sunLnmeal5="",sunLnmeal5Nut="";
        clearTextFields();

        Cursor res1 = myDatabase.viewSunLunchMealsSpecific("1");
        if (res1.getCount() == 0) {
            // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer1 = new StringBuffer();
            while (res1.moveToNext()) {
                sunLnmeal1 = res1.getString(1);
                sunLnmeal1Nut = res1.getString(2);
            }}

        Cursor res2 = myDatabase.viewSunLunchMealsSpecific("2");
        if (res2.getCount() == 0) {
//                // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer2 = new StringBuffer();
            while (res2.moveToNext()) {
                sunLnmeal2 = res2.getString(1);
                sunLnmeal2Nut = res2.getString(2);
            }}

        Cursor res3 = myDatabase.viewSunLunchMealsSpecific("3");
        if (res3.getCount() == 0) {
            // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer3 = new StringBuffer();
            while (res3.moveToNext()) {
                sunLnmeal3 = res3.getString(1);
                sunLnmeal3Nut = res3.getString(2);
            }}

        Cursor res4 = myDatabase.viewSunLunchMealsSpecific("4");
        if (res4.getCount() == 0) {
            // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer4 = new StringBuffer();
            while (res4.moveToNext()) {
                sunLnmeal4 = res4.getString(1);
                sunLnmeal4Nut = res4.getString(2);
            }}

        Cursor res5 = myDatabase.viewSunLunchMealsSpecific("5");
        if (res5.getCount() == 0) {
            // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer5 = new StringBuffer();
            while (res5.moveToNext()) {
                sunLnmeal5 = res5.getString(1);
                sunLnmeal5Nut = res5.getString(2);
            }}
            firstMealTv.setText(sunLnmeal1);
            firstMealNutrition.setText(sunLnmeal1Nut);
            secondMealTv.setText(sunLnmeal2);
            secondMealNutrition.setText(sunLnmeal2Nut);
            thirdMealTv.setText(sunLnmeal3);
            thirdMealNutrition.setText(sunLnmeal3Nut);
            fourthMealTv.setText(sunLnmeal4);
            fourthMealNutrition.setText(sunLnmeal4Nut);
            fifthMealTv.setText(sunLnmeal5);
            fifthMealNutrition.setText(sunLnmeal5Nut);
            myDatabase.close();

    }
    //sunday supper
    @SuppressLint("SetTextI18n")
    public void sundaySupper(){
        todaysmealTv.setText("Sunday Supper");
        String sunSpmeal1="",sunSpmeal1Nut="",sunSpmeal2="",sunSpmeal2Nut="",sunSpmeal3="",
                sunSpmeal3Nut="",sunSpmeal4="",sunSpmeal4Nut="",sunSpmeal5="",sunSpmeal5Nut="";
        clearTextFields();

        Cursor res1 = myDatabase.viewSunSupperMealsSpecific("1");
        if (res1.getCount() == 0) {
            // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer1 = new StringBuffer();
            while (res1.moveToNext()) {
                sunSpmeal1 = res1.getString(1);
                sunSpmeal1Nut = res1.getString(2);
            }}

        Cursor res2 = myDatabase.viewSunSupperMealsSpecific("2");
        if (res2.getCount() == 0) {
//                // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer2 = new StringBuffer();
            while (res2.moveToNext()) {
                sunSpmeal2 = res2.getString(1);
                sunSpmeal2Nut = res2.getString(2);
            }}

        Cursor res3 = myDatabase.viewSunSupperMealsSpecific("3");
        if (res3.getCount() == 0) {
            // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer3 = new StringBuffer();
            while (res3.moveToNext()) {
                sunSpmeal3 = res3.getString(1);
                sunSpmeal3Nut = res3.getString(2);
            }}

        Cursor res4 = myDatabase.viewSunSupperMealsSpecific("4");
        if (res4.getCount() == 0) {
            // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer4 = new StringBuffer();
            while (res4.moveToNext()) {
                sunSpmeal4 = res4.getString(1);
                sunSpmeal4Nut = res4.getString(2);
            }}

        Cursor res5 = myDatabase.viewSunSupperMealsSpecific("5");
        if (res5.getCount() == 0) {
            // notificationText.setText(notificationString);
        } else {
            // notificationText.setText("");
            StringBuffer buffer5 = new StringBuffer();
            while (res5.moveToNext()) {
                sunSpmeal5 = res5.getString(1);
                sunSpmeal5Nut = res5.getString(2);
            }}
            firstMealTv.setText(sunSpmeal1);
            firstMealNutrition.setText(sunSpmeal1Nut);
            secondMealTv.setText(sunSpmeal2);
            secondMealNutrition.setText(sunSpmeal2Nut);
            thirdMealTv.setText(sunSpmeal3);
            thirdMealNutrition.setText(sunSpmeal3Nut);
            fourthMealTv.setText(sunSpmeal4);
            fourthMealNutrition.setText(sunSpmeal4Nut);
            fifthMealTv.setText(sunSpmeal5);
            fifthMealNutrition.setText(sunSpmeal5Nut);
            myDatabase.close();
    }

    //DETECTING BACKPRESS TO LOAD MAIN ACTIVITY
    @Override
    public void onBackPressed() {
        startMainActivity();
    }

    //SHORT MESSAGE TO USER
    public void foodInfoMessage(String title, String message){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        AlertDialog dialog =  builder.show();
//        TextView messageText = (TextView)dialog.findViewById(android.R.id.message);
//        messageText.setGravity(Gravity.CENTER);
    }
//stophere

        //MONDAY MEALS
public  void mondayMeals(String meal, String nutrition) {
        //                Monday breakfast
        mealType = todaysmealTv.getText().toString();
    if (pressedTextView.equalsIgnoreCase(mealtv1)) {

        if (mealType.equalsIgnoreCase("Monday Breakfast")) {
                    Cursor res2 = myDatabase.viewMonBreakfastMeals();
                    if (res2.getCount() == 0) {

                    if (myDatabase.searchMonLunchMealsSpecific(meal).getCount()!=0 && !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchMonSupperMealsSpecific(meal).getCount()!=0 && !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                    }
                    else {
                        searchSunBreakfast(meal);
                        searchSunLunch(meal);
                        searchSunSupper(meal);
                        myDatabase.insertMonBreakfastData(meal, nutrition);
//                Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                        firstMealTv.setText(meal);
                        firstMealNutrition.setText(nutrition);

                    myDatabase.close();
                    }
                }
            else {

                    String currentMeal1 = firstMealTv.getText().toString();
                    String currentMeal2 = secondMealTv.getText().toString();
                    String currentMeal3 = thirdMealTv.getText().toString();
                    String currentMeal4 = fourthMealTv.getText().toString();
                    String currentMeal5 = fifthMealTv.getText().toString();

                    if(meal.equalsIgnoreCase(currentMeal1)||meal.equalsIgnoreCase(currentMeal2)||meal.equalsIgnoreCase(currentMeal3)||
                            meal.equalsIgnoreCase(currentMeal4)||meal.equalsIgnoreCase(currentMeal5)){
                        Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                            }
                    else {

                        if (myDatabase.searchMonLunchMealsSpecific(meal).getCount()!=0 && !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                            Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                        }
                        else if(myDatabase.searchMonSupperMealsSpecific(meal).getCount()!=0 && !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                    Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                }
                        else if (firstMealTv.getText().toString().trim().equalsIgnoreCase("")){
                            searchSunBreakfast(meal);
                            searchSunLunch(meal);
                            searchSunSupper(meal);
                        boolean isUpdated = myDatabase.updateMonBreakfastData(meal, nutrition, "1");
                        if (isUpdated) {
                            firstMealTv.setText(meal);
                            firstMealNutrition.setText(nutrition);
                        } else {
                            Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                        }
                    }

                        else{
                            searchSunBreakfast(meal);
                            searchSunLunch(meal);
                            searchSunSupper(meal);
                        new AlertDialog.Builder(today_meal.this)
                        .setMessage(dialogMsgPt1 + currentMeal1 + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateMonBreakfastData(meal, nutrition, "1");
                        if (isUpdated) {
                            firstMealTv.setText(meal);
                            firstMealNutrition.setText(nutrition);
                        } else {
                            Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                        }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }
            }
            }

            //          //Monday Lunch
            else if (mealType.equalsIgnoreCase("Monday Lunch")) {
            Cursor res2 = myDatabase.viewMonLunchMeals();
            if (res2.getCount() == 0) {
                    if (myDatabase.searchMonBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchMonSupperMealsSpecific(meal).getCount()!=0 && !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                    }
                    else{
                        searchSunBreakfast(meal);
                        searchSunLunch(meal);
                        searchSunSupper(meal);
                    myDatabase.insertMonLunchData(meal, nutrition);
//                //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    firstMealTv.setText(meal);
                    firstMealNutrition.setText(nutrition);
                    myDatabase.close();}
                }
            else {

                    String currentMeal1 = firstMealTv.getText().toString();
                    String currentMeal2 = secondMealTv.getText().toString();
                    String currentMeal3 = thirdMealTv.getText().toString();
                    String currentMeal4 = fourthMealTv.getText().toString();
                    String currentMeal5 = fifthMealTv.getText().toString();

                    if(meal.equalsIgnoreCase(currentMeal1)||meal.equalsIgnoreCase(currentMeal2)||meal.equalsIgnoreCase(currentMeal3)||
                            meal.equalsIgnoreCase(currentMeal4)||meal.equalsIgnoreCase(currentMeal5)){
                        Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                    }
                    else {

                        if (myDatabase.searchMonBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                            Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                        }
                        else if(myDatabase.searchMonSupperMealsSpecific(meal).getCount()!=0 && !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                    Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                }
                        else if (firstMealTv.getText().toString().trim().equalsIgnoreCase("")){
                            searchSunBreakfast(meal);
                            searchSunLunch(meal);
                            searchSunSupper(meal);
                            boolean isUpdated = myDatabase.updateMonLunchData(meal, nutrition, "1");
                            if (isUpdated) {
                                firstMealTv.setText(meal);
                                firstMealNutrition.setText(nutrition);
                            } else {
                                Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            searchSunBreakfast(meal);
                            searchSunLunch(meal);
                            searchSunSupper(meal);
                        new AlertDialog.Builder(today_meal.this)
                        .setMessage(dialogMsgPt1 + currentMeal1 + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateMonLunchData(meal, nutrition, "1");
                    if (isUpdated) {
//                    //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                        firstMealTv.setText(meal);
                        firstMealNutrition.setText(nutrition);
                    } else {
                        Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                    }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
            }
            //                //Monday Supper
            else if (mealType.equalsIgnoreCase("Monday Supper")) {
            Cursor res2 = myDatabase.viewMonSupperMeals();
            if (res2.getCount() == 0) {

                    if (myDatabase.searchMonLunchMealsSpecific(meal).getCount()!=0 && !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchMonBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                    }
                    else{
                        searchSunBreakfast(meal);
                        searchSunLunch(meal);
                        searchSunSupper(meal);
                    myDatabase.insertMonSupperData(meal, nutrition);
//                //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    firstMealTv.setText(meal);
                    firstMealNutrition.setText(nutrition);}
                    myDatabase.close();
                }
            else {

                    String currentMeal1 = firstMealTv.getText().toString();
                    String currentMeal2 = secondMealTv.getText().toString();
                    String currentMeal3 = thirdMealTv.getText().toString();
                    String currentMeal4 = fourthMealTv.getText().toString();
                    String currentMeal5 = fifthMealTv.getText().toString();

                    if(meal.equalsIgnoreCase(currentMeal1)||meal.equalsIgnoreCase(currentMeal2)||meal.equalsIgnoreCase(currentMeal3)||
                            meal.equalsIgnoreCase(currentMeal4)||meal.equalsIgnoreCase(currentMeal5)){
                        Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                    }
                    else {

                        if (myDatabase.searchMonLunchMealsSpecific(meal).getCount()!=0 && !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                            Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                        }
                        else if(myDatabase.searchMonBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                            Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                        }
                        else if (firstMealTv.getText().toString().trim().equalsIgnoreCase("")){
                            searchSunBreakfast(meal);
                            searchSunLunch(meal);
                            searchSunSupper(meal);
                            boolean isUpdated = myDatabase.updateMonSupperData(meal, nutrition, "1");
                            if (isUpdated) {
                                firstMealTv.setText(meal);
                                firstMealNutrition.setText(nutrition);
                            } else {
                                Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            searchSunBreakfast(meal);
                            searchSunLunch(meal);
                            searchSunSupper(meal);
                        new AlertDialog.Builder(today_meal.this)
                        .setMessage(dialogMsgPt1 + currentMeal1 + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateMonSupperData(meal, nutrition, "1");
                    if (isUpdated) {
//                    //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                        firstMealTv.setText(meal);
                        firstMealNutrition.setText(nutrition);
                    } else {
                        Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                    }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
            }
        }

    else if(pressedTextView.equalsIgnoreCase(mealtv2)){
        if (mealType.equalsIgnoreCase("Monday Breakfast")) {
            Cursor res = myDatabase.viewMonBreakfastMeals();
            String firstmeal = firstMealTv.getText().toString();
            if (res.getCount() < 1 || firstmeal.trim().equalsIgnoreCase("")) {
                Toast.makeText(getApplicationContext(), firstCell, Toast.LENGTH_SHORT).show();
            }
            else if(meal.equalsIgnoreCase(firstmeal)){
                Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
            }
            else if (res.getCount() == 1) { //paused

                if (myDatabase.searchMonLunchMealsSpecific(meal).getCount()!=0 && !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                    Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                }
                else if(myDatabase.searchMonSupperMealsSpecific(meal).getCount()!=0 && !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                    Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                }
                else{
                    searchSunBreakfast(meal);
                    searchSunLunch(meal);
                    searchSunSupper(meal);
                myDatabase.insertMonBreakfastData(meal, nutrition);
//                //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                secondMealTv.setText(meal);
                secondMealNutrition.setText(nutrition);}
                myDatabase.close();
            }else {

                    String currentMeal1 = firstMealTv.getText().toString();
                    String currentMeal2 = secondMealTv.getText().toString();
                    String currentMeal3 = thirdMealTv.getText().toString();
                    String currentMeal4 = fourthMealTv.getText().toString();
                    String currentMeal5 = fifthMealTv.getText().toString();

                    if(meal.equalsIgnoreCase(currentMeal1)||meal.equalsIgnoreCase(currentMeal2)||meal.equalsIgnoreCase(currentMeal3)||
                            meal.equalsIgnoreCase(currentMeal4)||meal.equalsIgnoreCase(currentMeal5)){
                        Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                    }
                    else {

                        if (myDatabase.searchMonLunchMealsSpecific(meal).getCount()!=0 && !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                            Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                        }
                        else if(myDatabase.searchMonSupperMealsSpecific(meal).getCount()!=0 && !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                    Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                }
                        else if (secondMealTv.getText().toString().trim().equalsIgnoreCase("")){
                            searchSunBreakfast(meal);
                            searchSunLunch(meal);
                            searchSunSupper(meal);
                            boolean isUpdated = myDatabase.updateMonBreakfastData(meal, nutrition, "2");
                            if (isUpdated) {
                                secondMealTv.setText(meal);
                                secondMealNutrition.setText(nutrition);
                            } else {
                                Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            searchSunBreakfast(meal);
                            searchSunLunch(meal);
                            searchSunSupper(meal);
                        new AlertDialog.Builder(today_meal.this)
                        .setMessage(dialogMsgPt1 + currentMeal2 + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateMonBreakfastData(meal, nutrition, "2");
                if (isUpdated) {
                    //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                    secondMealTv.setText(meal);
                    secondMealNutrition.setText(nutrition);
                } else {
                    Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
        }
        //                //Monday Lunch
        else if (mealType.equalsIgnoreCase("Monday Lunch")) {
            Cursor res = myDatabase.viewMonLunchMeals();
            String firstmeal = firstMealTv.getText().toString();
            if (res.getCount() < 1 || firstmeal.trim().equalsIgnoreCase("")) {
                Toast.makeText(getApplicationContext(), firstCell, Toast.LENGTH_SHORT).show();
            }
            else if(meal.equalsIgnoreCase(firstmeal)){
                Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
            }
            else if (res.getCount() == 1) {

                if (myDatabase.searchMonBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                    Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                }
                else if(myDatabase.searchMonSupperMealsSpecific(meal).getCount()!=0 && !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                    Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                }
                else{
                    searchSunBreakfast(meal);
                    searchSunLunch(meal);
                    searchSunSupper(meal);
                myDatabase.insertMonLunchData(meal, nutrition);
                //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                secondMealTv.setText(meal);
                secondMealNutrition.setText(nutrition);}
                myDatabase.close();
            }else {

                    String currentMeal1 = firstMealTv.getText().toString();
                    String currentMeal2 = secondMealTv.getText().toString();
                    String currentMeal3 = thirdMealTv.getText().toString();
                    String currentMeal4 = fourthMealTv.getText().toString();
                    String currentMeal5 = fifthMealTv.getText().toString();

                    if(meal.equalsIgnoreCase(currentMeal1)||meal.equalsIgnoreCase(currentMeal2)||meal.equalsIgnoreCase(currentMeal3)||
                            meal.equalsIgnoreCase(currentMeal4)||meal.equalsIgnoreCase(currentMeal5)){
                        Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                    }
                    else {

                        if (myDatabase.searchMonBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                            Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                        }
                        else if(myDatabase.searchMonSupperMealsSpecific(meal).getCount()!=0 && !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                    Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                }
                        else if (secondMealTv.getText().toString().trim().equalsIgnoreCase("")){
                            searchSunBreakfast(meal);
                            searchSunLunch(meal);
                            searchSunSupper(meal);
                            boolean isUpdated = myDatabase.updateMonLunchData(meal, nutrition, "2");
                            if (isUpdated) {
                                secondMealTv.setText(meal);
                                secondMealNutrition.setText(nutrition);
                            } else {
                                Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            searchSunBreakfast(meal);
                            searchSunLunch(meal);
                            searchSunSupper(meal);
                        new AlertDialog.Builder(today_meal.this)
                        .setMessage(dialogMsgPt1 + currentMeal2 + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateMonLunchData(meal, nutrition, "2");
                if (isUpdated) {
                    //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                    secondMealTv.setText(meal);
                    secondMealNutrition.setText(nutrition);
                } else {
                    Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
        }
        //                //Monday Supper
        else if (mealType.equalsIgnoreCase("Monday Supper")) {
            Cursor res = myDatabase.viewMonSupperMeals();
            String firstmeal = firstMealTv.getText().toString();
            if (res.getCount() < 1 || firstmeal.trim().equalsIgnoreCase("")) {
                Toast.makeText(getApplicationContext(), firstCell, Toast.LENGTH_SHORT).show();
            }
            else if(meal.equalsIgnoreCase(firstmeal)){
                Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
            }
            else if (res.getCount() == 1) {

                if (myDatabase.searchMonLunchMealsSpecific(meal).getCount()!=0 && !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                    Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                }
                else if(myDatabase.searchMonBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                    Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                }
                else{
                    searchSunBreakfast(meal);
                    searchSunLunch(meal);
                    searchSunSupper(meal);
                myDatabase.insertMonSupperData(meal, nutrition);
                //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                secondMealTv.setText(meal);
                secondMealNutrition.setText(nutrition);
                myDatabase.close();}
            }else {

                    String currentMeal1 = firstMealTv.getText().toString();
                    String currentMeal2 = secondMealTv.getText().toString();
                    String currentMeal3 = thirdMealTv.getText().toString();
                    String currentMeal4 = fourthMealTv.getText().toString();
                    String currentMeal5 = fifthMealTv.getText().toString();

                    if(meal.equalsIgnoreCase(currentMeal1)||meal.equalsIgnoreCase(currentMeal2)||meal.equalsIgnoreCase(currentMeal3)||
                            meal.equalsIgnoreCase(currentMeal4)||meal.equalsIgnoreCase(currentMeal5)){
                        Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                    }
                    else {

                        if (myDatabase.searchMonLunchMealsSpecific(meal).getCount()!=0 && !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                            Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                        }
                        else if(myDatabase.searchMonBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                            Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                        }
                        else if (secondMealTv.getText().toString().trim().equalsIgnoreCase("")){
                            searchSunBreakfast(meal);
                            searchSunLunch(meal);
                            searchSunSupper(meal);
                            boolean isUpdated = myDatabase.updateMonSupperData(meal, nutrition, "2");
                            if (isUpdated) {
                                secondMealTv.setText(meal);
                                secondMealNutrition.setText(nutrition);
                            } else {
                                Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            searchSunBreakfast(meal);
                            searchSunLunch(meal);
                            searchSunSupper(meal);
                        new AlertDialog.Builder(today_meal.this)
                        .setMessage(dialogMsgPt1 + currentMeal2 + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateMonSupperData(meal, nutrition, "2");
                if (isUpdated) {
                    //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                    secondMealTv.setText(meal);
                    secondMealNutrition.setText(nutrition);
                } else {
                    Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
        }
    }
    else if(pressedTextView.equalsIgnoreCase(mealtv3)){
        if (mealType.equalsIgnoreCase("Monday Breakfast")) {
            Cursor res = myDatabase.viewMonBreakfastMeals();
            String firstmeal = firstMealTv.getText().toString();
            if (res.getCount() < 1 || firstmeal.trim().equalsIgnoreCase("")) {
                Toast.makeText(getApplicationContext(), firstCell, Toast.LENGTH_SHORT).show();
            }
            else if (res.getCount() < 2||secondMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                Toast.makeText(getApplicationContext(), secondCell, Toast.LENGTH_SHORT).show();
            }
            else if(meal.equalsIgnoreCase(firstMealTv.getText().toString())||meal.equalsIgnoreCase(secondMealTv.getText().toString())){
                Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
            }
            else if (res.getCount() == 2) {

                if (myDatabase.searchMonLunchMealsSpecific(meal).getCount()!=0 && !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                    Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                }
                else if(myDatabase.searchMonSupperMealsSpecific(meal).getCount()!=0 && !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                    Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                }
                else{
                    searchSunBreakfast(meal);
                    searchSunLunch(meal);
                    searchSunSupper(meal);
                myDatabase.insertMonBreakfastData(meal, nutrition);
                //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                thirdMealTv.setText(meal);
                thirdMealNutrition.setText(nutrition);}
                myDatabase.close();
            } else {

                String currentMeal1 = firstMealTv.getText().toString();
                String currentMeal2 = secondMealTv.getText().toString();
                String currentMeal3 = thirdMealTv.getText().toString();
                String currentMeal4 = fourthMealTv.getText().toString();
                String currentMeal5 = fifthMealTv.getText().toString();

                if(meal.equalsIgnoreCase(currentMeal1)||meal.equalsIgnoreCase(currentMeal2)||meal.equalsIgnoreCase(currentMeal3)||
                        meal.equalsIgnoreCase(currentMeal4)||meal.equalsIgnoreCase(currentMeal5)){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                }
                else {

                    if (myDatabase.searchMonLunchMealsSpecific(meal).getCount()!=0 && !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchMonSupperMealsSpecific(meal).getCount()!=0 && !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                    Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                }
                    else if (thirdMealTv.getText().toString().trim().equalsIgnoreCase("")){
                        searchSunBreakfast(meal);
                        searchSunLunch(meal);
                        searchSunSupper(meal);
                        boolean isUpdated = myDatabase.updateMonBreakfastData(meal, nutrition, "3");
                        if (isUpdated) {
                            thirdMealTv.setText(meal);
                            thirdMealNutrition.setText(nutrition);
                        } else {
                            Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        searchSunBreakfast(meal);
                        searchSunLunch(meal);
                        searchSunSupper(meal);
                    new AlertDialog.Builder(today_meal.this)
                        .setMessage(dialogMsgPt1 + currentMeal3 + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateMonBreakfastData(meal, nutrition, "3");
                if (isUpdated) {
                    //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                    thirdMealTv.setText(meal);
                    thirdMealNutrition.setText(nutrition);
                } else {
                    Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
        }
        //                //Monday Lunch
        else if (mealType.equalsIgnoreCase("Monday Lunch")) {
            Cursor res = myDatabase.viewMonLunchMeals();
            String firstmeal = firstMealTv.getText().toString();
            if (res.getCount() < 1 || firstmeal.trim().equalsIgnoreCase("")) {
                Toast.makeText(getApplicationContext(), firstCell, Toast.LENGTH_SHORT).show();
            }
            else if (res.getCount() < 2||secondMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                Toast.makeText(getApplicationContext(), secondCell, Toast.LENGTH_SHORT).show();
            }
            else if(meal.equalsIgnoreCase(firstMealTv.getText().toString())||meal.equalsIgnoreCase(secondMealTv.getText().toString())){
                Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
            }
            else if (res.getCount() == 2) {
                if (myDatabase.searchMonBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                    Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                }
                else if(myDatabase.searchMonSupperMealsSpecific(meal).getCount()!=0 && !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                    Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                }
                else{
                    searchSunBreakfast(meal);
                    searchSunLunch(meal);
                    searchSunSupper(meal);

                    myDatabase.insertMonLunchData(meal, nutrition);
                //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                thirdMealTv.setText(meal);
                thirdMealNutrition.setText(nutrition);}
                myDatabase.close();
            } else {

                String currentMeal1 = firstMealTv.getText().toString();
                String currentMeal2 = secondMealTv.getText().toString();
                String currentMeal3 = thirdMealTv.getText().toString();
                String currentMeal4 = fourthMealTv.getText().toString();
                String currentMeal5 = fifthMealTv.getText().toString();

                if(meal.equalsIgnoreCase(currentMeal1)||meal.equalsIgnoreCase(currentMeal2)||meal.equalsIgnoreCase(currentMeal3)||
                        meal.equalsIgnoreCase(currentMeal4)||meal.equalsIgnoreCase(currentMeal5)){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                }
                else {


                    if (myDatabase.searchMonBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchMonSupperMealsSpecific(meal).getCount()!=0 && !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                    Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                }
                    else if (thirdMealTv.getText().toString().trim().equalsIgnoreCase("")){
                        searchSunBreakfast(meal);
                        searchSunLunch(meal);
                        searchSunSupper(meal);
                        boolean isUpdated = myDatabase.updateMonLunchData(meal, nutrition, "3");
                        if (isUpdated) {
                            thirdMealTv.setText(meal);
                            thirdMealNutrition.setText(nutrition);
                        } else {
                            Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        searchSunBreakfast(meal);
                        searchSunLunch(meal);
                        searchSunSupper(meal);
                    new AlertDialog.Builder(today_meal.this)
                        .setMessage(dialogMsgPt1 + currentMeal3 + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateMonLunchData(meal, nutrition, "3");
                if (isUpdated) {
                    //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                    thirdMealTv.setText(meal);
                    thirdMealNutrition.setText(nutrition);
                } else {
                    Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
        }
        //                //Monday Supper yuo
        else if (mealType.equalsIgnoreCase("Monday Supper")) {
            Cursor res = myDatabase.viewMonSupperMeals();
            String firstmeal = firstMealTv.getText().toString();
            if (res.getCount() < 1 || firstmeal.trim().equalsIgnoreCase("")) {
                Toast.makeText(getApplicationContext(), firstCell, Toast.LENGTH_SHORT).show();
            }
            else if (res.getCount() < 2||secondMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                Toast.makeText(getApplicationContext(), secondCell, Toast.LENGTH_SHORT).show();
            }
            else if(meal.equalsIgnoreCase(firstMealTv.getText().toString())||meal.equalsIgnoreCase(secondMealTv.getText().toString())){
                Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
            }
            else if (res.getCount() == 2) {

                if (myDatabase.searchMonLunchMealsSpecific(meal).getCount()!=0 && !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                    Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                }
                else if(myDatabase.searchMonBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                    Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                }
                else{
                    searchSunBreakfast(meal);
                    searchSunLunch(meal);
                    searchSunSupper(meal);
                myDatabase.insertMonSupperData(meal, nutrition);
                //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                thirdMealTv.setText(meal);
                thirdMealNutrition.setText(nutrition);}
                myDatabase.close();
            } else {

                String currentMeal1 = firstMealTv.getText().toString();
                String currentMeal2 = secondMealTv.getText().toString();
                String currentMeal3 = thirdMealTv.getText().toString();
                String currentMeal4 = fourthMealTv.getText().toString();
                String currentMeal5 = fifthMealTv.getText().toString();

                if(meal.equalsIgnoreCase(currentMeal1)||meal.equalsIgnoreCase(currentMeal2)||meal.equalsIgnoreCase(currentMeal3)||
                        meal.equalsIgnoreCase(currentMeal4)||meal.equalsIgnoreCase(currentMeal5)){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                }
                else {

                    if (myDatabase.searchMonLunchMealsSpecific(meal).getCount()!=0 && !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchMonBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                    }
                    else if (thirdMealTv.getText().toString().trim().equalsIgnoreCase("")){
                        searchSunBreakfast(meal);
                        searchSunLunch(meal);
                        searchSunSupper(meal);
                        boolean isUpdated = myDatabase.updateMonSupperData(meal, nutrition, "3");
                        if (isUpdated) {
                            thirdMealTv.setText(meal);
                            thirdMealNutrition.setText(nutrition);
                        } else {
                            Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        searchSunBreakfast(meal);
                        searchSunLunch(meal);
                        searchSunSupper(meal);
                    new AlertDialog.Builder(today_meal.this)
                        .setMessage(dialogMsgPt1 + currentMeal3 + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateMonSupperData(meal, nutrition, "3");
                if (isUpdated) {
                    //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                    thirdMealTv.setText(meal);
                    thirdMealNutrition.setText(nutrition);
                } else {
                    Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
        }
    }
    else if(pressedTextView.equalsIgnoreCase(mealtv4)){

        if (mealType.equalsIgnoreCase("Monday Breakfast")) {
            Cursor res = myDatabase.viewMonBreakfastMeals();
            String firstmeal = firstMealTv.getText().toString();
            if (res.getCount() < 1 || firstmeal.trim().equalsIgnoreCase("")) {
                Toast.makeText(getApplicationContext(), firstCell, Toast.LENGTH_SHORT).show();
            }
            else if (res.getCount() < 2||secondMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                Toast.makeText(getApplicationContext(), secondCell, Toast.LENGTH_SHORT).show();
            }
            else if (res.getCount() < 3||thirdMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                Toast.makeText(getApplicationContext(), thirdCell, Toast.LENGTH_SHORT).show();
            }
            else if(meal.equalsIgnoreCase(firstMealTv.getText().toString())||meal.equalsIgnoreCase(secondMealTv.getText().toString())
                    ||meal.equalsIgnoreCase(thirdMealTv.getText().toString())){
                Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
            }
            else if (res.getCount() == 3) {

                if (myDatabase.searchMonLunchMealsSpecific(meal).getCount()!=0 && !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                    Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                }
                else if(myDatabase.searchMonSupperMealsSpecific(meal).getCount()!=0 && !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                    Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                }
                else{
                    searchSunBreakfast(meal);
                    searchSunLunch(meal);
                    searchSunSupper(meal);
                myDatabase.insertMonBreakfastData(meal, nutrition);
                //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                fourthMealTv.setText(meal);
                fourthMealNutrition.setText(nutrition);}
                myDatabase.close();
            }  else {

                String currentMeal1 = firstMealTv.getText().toString();
                String currentMeal2 = secondMealTv.getText().toString();
                String currentMeal3 = thirdMealTv.getText().toString();
                String currentMeal4 = fourthMealTv.getText().toString();
                String currentMeal5 = fifthMealTv.getText().toString();

                if(meal.equalsIgnoreCase(currentMeal1)||meal.equalsIgnoreCase(currentMeal2)||meal.equalsIgnoreCase(currentMeal3)||
                        meal.equalsIgnoreCase(currentMeal4)||meal.equalsIgnoreCase(currentMeal5)){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                }
                else {

                    if (myDatabase.searchMonLunchMealsSpecific(meal).getCount()!=0 && !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchMonSupperMealsSpecific(meal).getCount()!=0 && !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                    Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                }
                    else if (fourthMealTv.getText().toString().trim().equalsIgnoreCase("")){
                        searchSunBreakfast(meal);
                        searchSunLunch(meal);
                        searchSunSupper(meal);
                        boolean isUpdated = myDatabase.updateMonBreakfastData(meal, nutrition, "4");
                        if (isUpdated) {
                            fourthMealTv.setText(meal);
                            fourthMealNutrition.setText(nutrition);
                        } else {
                            Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        searchSunBreakfast(meal);
                        searchSunLunch(meal);
                        searchSunSupper(meal);
                    new AlertDialog.Builder(today_meal.this)
                            .setMessage(dialogMsgPt1 + currentMeal4 + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateMonBreakfastData(meal, nutrition, "4");
                if (isUpdated) {
                    //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                    fourthMealTv.setText(meal);
                    fourthMealNutrition.setText(nutrition);
                } else {
                    Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
        }
        //                //Monday Lunch
        else if (mealType.equalsIgnoreCase("Monday Lunch")) {
            Cursor res = myDatabase.viewMonLunchMeals();
            String firstmeal = firstMealTv.getText().toString();
            if (res.getCount() < 1 || firstmeal.trim().equalsIgnoreCase("")) {
                Toast.makeText(getApplicationContext(), firstCell, Toast.LENGTH_SHORT).show();
            }
            else if (res.getCount() < 2||secondMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                Toast.makeText(getApplicationContext(), secondCell, Toast.LENGTH_SHORT).show();
            }
            else if (res.getCount() < 3||thirdMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                Toast.makeText(getApplicationContext(), thirdCell, Toast.LENGTH_SHORT).show();
            }
            else if(meal.equalsIgnoreCase(firstMealTv.getText().toString())||meal.equalsIgnoreCase(secondMealTv.getText().toString())
                    ||meal.equalsIgnoreCase(thirdMealTv.getText().toString())){
                Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
            }
            else if (res.getCount() == 3) {

                if (myDatabase.searchMonBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                    Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                }
                else if(myDatabase.searchMonSupperMealsSpecific(meal).getCount()!=0 && !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                    Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                }
                else{
                    searchSunBreakfast(meal);
                    searchSunLunch(meal);
                    searchSunSupper(meal);
                myDatabase.insertMonLunchData(meal, nutrition);
                //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                fourthMealTv.setText(meal);
                fourthMealNutrition.setText(nutrition);}
                myDatabase.close();
            }  else {

                String currentMeal1 = firstMealTv.getText().toString();
                String currentMeal2 = secondMealTv.getText().toString();
                String currentMeal3 = thirdMealTv.getText().toString();
                String currentMeal4 = fourthMealTv.getText().toString();
                String currentMeal5 = fifthMealTv.getText().toString();

                if(meal.equalsIgnoreCase(currentMeal1)||meal.equalsIgnoreCase(currentMeal2)||meal.equalsIgnoreCase(currentMeal3)||
                        meal.equalsIgnoreCase(currentMeal4)||meal.equalsIgnoreCase(currentMeal5)){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                }
                else {

                    if (myDatabase.searchMonBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchMonSupperMealsSpecific(meal).getCount()!=0 && !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                    Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                }
                    else if (fourthMealTv.getText().toString().trim().equalsIgnoreCase("")){
                        searchSunBreakfast(meal);
                        searchSunLunch(meal);
                        searchSunSupper(meal);
                        boolean isUpdated = myDatabase.updateMonLunchData(meal, nutrition, "4");
                        if (isUpdated) {
                            fourthMealTv.setText(meal);
                            fourthMealNutrition.setText(nutrition);
                        } else {
                            Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        searchSunBreakfast(meal);
                        searchSunLunch(meal);
                        searchSunSupper(meal);
                    new AlertDialog.Builder(today_meal.this)
                            .setMessage(dialogMsgPt1 + currentMeal4 + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateMonLunchData(meal, nutrition, "4");
                if (isUpdated) {
                    //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                    fourthMealTv.setText(meal);
                    fourthMealNutrition.setText(nutrition);
                } else {
                    Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
        }
        //                //Monday Supper
        else if (mealType.equalsIgnoreCase("Monday Supper")) {
            Cursor res = myDatabase.viewMonSupperMeals();
            String firstmeal = firstMealTv.getText().toString();
            if (res.getCount() < 1 || firstmeal.trim().equalsIgnoreCase("")) {
                Toast.makeText(getApplicationContext(), firstCell, Toast.LENGTH_SHORT).show();
            }
            else if (res.getCount() < 2||secondMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                Toast.makeText(getApplicationContext(), secondCell, Toast.LENGTH_SHORT).show();
            }
            else if (res.getCount() < 3||thirdMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                Toast.makeText(getApplicationContext(), thirdCell, Toast.LENGTH_SHORT).show();
            }
            else if(meal.equalsIgnoreCase(firstMealTv.getText().toString())||meal.equalsIgnoreCase(secondMealTv.getText().toString())
                    ||meal.equalsIgnoreCase(thirdMealTv.getText().toString())){
                Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
            }
            else if (res.getCount() == 3) {
                if (myDatabase.searchMonLunchMealsSpecific(meal).getCount()!=0 && !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                    Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                }
                else if(myDatabase.searchMonBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                    Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                }
                else{
                    searchSunBreakfast(meal);
                    searchSunLunch(meal);
                    searchSunSupper(meal);

                    myDatabase.insertMonSupperData(meal, nutrition);
                //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                fourthMealTv.setText(meal);
                fourthMealNutrition.setText(nutrition);}
                myDatabase.close();
            }  else {

                String currentMeal1 = firstMealTv.getText().toString();
                String currentMeal2 = secondMealTv.getText().toString();
                String currentMeal3 = thirdMealTv.getText().toString();
                String currentMeal4 = fourthMealTv.getText().toString();
                String currentMeal5 = fifthMealTv.getText().toString();

                if(meal.equalsIgnoreCase(currentMeal1)||meal.equalsIgnoreCase(currentMeal2)||meal.equalsIgnoreCase(currentMeal3)||
                        meal.equalsIgnoreCase(currentMeal4)||meal.equalsIgnoreCase(currentMeal5)){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                }
                else {

                    if (myDatabase.searchMonLunchMealsSpecific(meal).getCount()!=0 && !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchMonBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                    }
                    else if (fourthMealTv.getText().toString().trim().equalsIgnoreCase("")){
                        searchSunBreakfast(meal);
                        searchSunLunch(meal);
                        searchSunSupper(meal);
                        boolean isUpdated = myDatabase.updateMonSupperData(meal, nutrition, "4");
                        if (isUpdated) {
                            fourthMealTv.setText(meal);
                            fourthMealNutrition.setText(nutrition);
                        } else {
                            Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        searchSunBreakfast(meal);
                        searchSunLunch(meal);
                        searchSunSupper(meal);
                    new AlertDialog.Builder(today_meal.this)
                            .setMessage(dialogMsgPt1 + currentMeal4 + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateMonSupperData(meal, nutrition, "4");
                if (isUpdated) {
                    //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                    fourthMealTv.setText(meal);
                    fourthMealNutrition.setText(nutrition);
                } else {
                    Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
        }
    }

    else if(pressedTextView.equalsIgnoreCase(mealtv5)){
        if (mealType.equalsIgnoreCase("Monday Breakfast")) {
            Cursor res = myDatabase.viewMonBreakfastMeals();
            String firstmeal = firstMealTv.getText().toString();
            if (res.getCount() < 1 || firstmeal.trim().equalsIgnoreCase("")) {
                Toast.makeText(getApplicationContext(), firstCell, Toast.LENGTH_SHORT).show();
            }
            else if (res.getCount() < 2||secondMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                Toast.makeText(getApplicationContext(), secondCell, Toast.LENGTH_SHORT).show();
            }
            else if (res.getCount() < 3||thirdMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                Toast.makeText(getApplicationContext(), thirdCell, Toast.LENGTH_SHORT).show();
            }
            else if (res.getCount() < 4||fourthMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                Toast.makeText(getApplicationContext(), fourthCell, Toast.LENGTH_SHORT).show();
            }
            else if(meal.equalsIgnoreCase(firstMealTv.getText().toString())||meal.equalsIgnoreCase(secondMealTv.getText().toString())
                    ||meal.equalsIgnoreCase(thirdMealTv.getText().toString())||meal.equalsIgnoreCase(fourthMealTv.getText().toString())){
                Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
            }
            else if (res.getCount() == 4) {

                if (myDatabase.searchMonLunchMealsSpecific(meal).getCount()!=0 && !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                    Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                }
                else if(myDatabase.searchMonSupperMealsSpecific(meal).getCount()!=0 && !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                    Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                }
                else{
                    searchSunBreakfast(meal);
                    searchSunLunch(meal);
                    searchSunSupper(meal);
                myDatabase.insertMonBreakfastData(meal, nutrition);
                //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                fifthMealTv.setText(meal);
                fifthMealNutrition.setText(nutrition);
                myDatabase.close();}
            }  else {

                String currentMeal1 = firstMealTv.getText().toString();
                String currentMeal2 = secondMealTv.getText().toString();
                String currentMeal3 = thirdMealTv.getText().toString();
                String currentMeal4 = fourthMealTv.getText().toString();
                String currentMeal5 = fifthMealTv.getText().toString();

                if(meal.equalsIgnoreCase(currentMeal1)||meal.equalsIgnoreCase(currentMeal2)||meal.equalsIgnoreCase(currentMeal3)||
                        meal.equalsIgnoreCase(currentMeal4)||meal.equalsIgnoreCase(currentMeal5)){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                }
                else {

                    if (myDatabase.searchMonLunchMealsSpecific(meal).getCount()!=0 && !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchMonSupperMealsSpecific(meal).getCount()!=0 && !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                    Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                }
                    else if (fifthMealTv.getText().toString().trim().equalsIgnoreCase("")){
                        searchSunBreakfast(meal);
                        searchSunLunch(meal);
                        searchSunSupper(meal);
                        boolean isUpdated = myDatabase.updateMonBreakfastData(meal, nutrition, "5");
                        if (isUpdated) {
                            fifthMealTv.setText(meal);
                            fifthMealNutrition.setText(nutrition);
                        } else {
                            Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        searchSunBreakfast(meal);
                        searchSunLunch(meal);
                        searchSunSupper(meal);
                    new AlertDialog.Builder(today_meal.this)
                            .setMessage(dialogMsgPt1 + currentMeal5 + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateMonBreakfastData(meal, nutrition, "5");
                if (isUpdated) {
                    //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                    fifthMealTv.setText(meal);
                    fifthMealNutrition.setText(nutrition);
                } else {
                    Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
        }
        //                //Monday Lunch
        else if (mealType.equalsIgnoreCase("Monday Lunch")) {
            Cursor res = myDatabase.viewMonLunchMeals();
            String firstmeal = firstMealTv.getText().toString();
            if (res.getCount() < 1 || firstmeal.trim().equalsIgnoreCase("")) {
                Toast.makeText(getApplicationContext(), firstCell, Toast.LENGTH_SHORT).show();
            }
            else if (res.getCount() < 2||secondMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                Toast.makeText(getApplicationContext(), secondCell, Toast.LENGTH_SHORT).show();
            }
            else if (res.getCount() < 3||thirdMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                Toast.makeText(getApplicationContext(), thirdCell, Toast.LENGTH_SHORT).show();
            }
            else if (res.getCount() < 4||fourthMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                Toast.makeText(getApplicationContext(), fourthCell, Toast.LENGTH_SHORT).show();
            }
            else if(meal.equalsIgnoreCase(firstMealTv.getText().toString())||meal.equalsIgnoreCase(secondMealTv.getText().toString())
                    ||meal.equalsIgnoreCase(thirdMealTv.getText().toString())||meal.equalsIgnoreCase(fourthMealTv.getText().toString())){
                Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
            }
            else if (res.getCount() == 4) {

                if (myDatabase.searchMonBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                    Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                }
                else if(myDatabase.searchMonSupperMealsSpecific(meal).getCount()!=0 && !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                    Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                }
                else{
                    searchSunBreakfast(meal);
                    searchSunLunch(meal);
                    searchSunSupper(meal);
                myDatabase.insertMonLunchData(meal, nutrition);
                //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                fifthMealTv.setText(meal);
                fifthMealNutrition.setText(nutrition);
                myDatabase.close();}
            } else{
                String currentMeal1 = firstMealTv.getText().toString();
                String currentMeal2 = secondMealTv.getText().toString();
                String currentMeal3 = thirdMealTv.getText().toString();
                String currentMeal4 = fourthMealTv.getText().toString();
                String currentMeal5 = fifthMealTv.getText().toString();

                if(meal.equalsIgnoreCase(currentMeal1)||meal.equalsIgnoreCase(currentMeal2)||meal.equalsIgnoreCase(currentMeal3)||
                        meal.equalsIgnoreCase(currentMeal4)||meal.equalsIgnoreCase(currentMeal5)){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                }
                else {

                    if (myDatabase.searchMonBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchMonSupperMealsSpecific(meal).getCount()!=0 && !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                    Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                }
                    else if (fifthMealTv.getText().toString().trim().equalsIgnoreCase("")){
                        searchSunBreakfast(meal);
                        searchSunLunch(meal);
                        searchSunSupper(meal);
                        boolean isUpdated = myDatabase.updateMonLunchData(meal, nutrition, "5");
                        if (isUpdated) {
                            fifthMealTv.setText(meal);
                            fifthMealNutrition.setText(nutrition);
                        } else {
                            Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        searchSunBreakfast(meal);
                        searchSunLunch(meal);
                        searchSunSupper(meal);
                    new AlertDialog.Builder(today_meal.this)
                            .setMessage(dialogMsgPt1 + currentMeal5 + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateMonLunchData(meal, nutrition, "5");
                if (isUpdated) {
                    //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                    fifthMealTv.setText(meal);
                    fifthMealNutrition.setText(nutrition);
                } else {
                    Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
        }
        //                //Monday Supper
        else if (mealType.equalsIgnoreCase("Monday Supper")) {
            Cursor res = myDatabase.viewMonSupperMeals();
            String firstmeal = firstMealTv.getText().toString();
            if (res.getCount() < 1 || firstmeal.trim().equalsIgnoreCase("")) {
                Toast.makeText(getApplicationContext(), firstCell, Toast.LENGTH_SHORT).show();
            }
            else if (res.getCount() < 2||secondMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                Toast.makeText(getApplicationContext(), secondCell, Toast.LENGTH_SHORT).show();
            }
            else if (res.getCount() < 3||thirdMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                Toast.makeText(getApplicationContext(), thirdCell, Toast.LENGTH_SHORT).show();
            }
            else if (res.getCount() < 4||fourthMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                Toast.makeText(getApplicationContext(), fourthCell, Toast.LENGTH_SHORT).show();
            }
            else if(meal.equalsIgnoreCase(firstMealTv.getText().toString())||meal.equalsIgnoreCase(secondMealTv.getText().toString())
                    ||meal.equalsIgnoreCase(thirdMealTv.getText().toString())||meal.equalsIgnoreCase(fourthMealTv.getText().toString())){
                Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
            }
            else if (res.getCount() == 4) {

                if (myDatabase.searchMonLunchMealsSpecific(meal).getCount()!=0 && !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                    Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                }
                else if(myDatabase.searchMonBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                    Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                }
                else{
                    searchSunBreakfast(meal);
                    searchSunLunch(meal);
                    searchSunSupper(meal);
                myDatabase.insertMonSupperData(meal, nutrition);
                //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                fifthMealTv.setText(meal);
                fifthMealNutrition.setText(nutrition);
                myDatabase.close();}
            }  else {

                String currentMeal1 = firstMealTv.getText().toString();
                String currentMeal2 = secondMealTv.getText().toString();
                String currentMeal3 = thirdMealTv.getText().toString();
                String currentMeal4 = fourthMealTv.getText().toString();
                String currentMeal5 = fifthMealTv.getText().toString();

                if(meal.equalsIgnoreCase(currentMeal1)||meal.equalsIgnoreCase(currentMeal2)||meal.equalsIgnoreCase(currentMeal3)||
                        meal.equalsIgnoreCase(currentMeal4)||meal.equalsIgnoreCase(currentMeal5)){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                }
                else {

                    if (myDatabase.searchMonLunchMealsSpecific(meal).getCount()!=0 && !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchMonBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                    }
                    else if (fifthMealTv.getText().toString().trim().equalsIgnoreCase("")){
                        searchSunBreakfast(meal);
                        searchSunLunch(meal);
                        searchSunSupper(meal);
                        boolean isUpdated = myDatabase.updateMonSupperData(meal, nutrition, "5");
                        if (isUpdated) {
                            fifthMealTv.setText(meal);
                            fifthMealNutrition.setText(nutrition);
                        } else {
                            Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        searchSunBreakfast(meal);
                        searchSunLunch(meal);
                        searchSunSupper(meal);
                    new AlertDialog.Builder(today_meal.this)
                            .setMessage(dialogMsgPt1 + currentMeal5 + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateMonSupperData(meal, nutrition, "5");
                if (isUpdated) {
                    //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                    fifthMealTv.setText(meal);
                    fifthMealNutrition.setText(nutrition);
                } else {
                    Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
        }
    }

    }//END OF MONDAY MEALS


    //TUESDAY MEALS
    public  void tuesdayMeals(String meal, String nutrition) {
//                Tuesday breakfast
        mealType = todaysmealTv.getText().toString();
//                Toast.makeText(getApplicationContext(), pressedTextView, Toast.LENGTH_SHORT).show();

        if (pressedTextView.equalsIgnoreCase(mealtv1)) {

            if (mealType.equalsIgnoreCase("Tuesday Breakfast")) {
                Cursor res2 = myDatabase.viewTueBreakfastMeals();
                if (res2.getCount() == 0) {

                    if (myDatabase.searchTueLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchTueSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                    }
                    else{
                    searchMonBreakfast(meal);
                    searchMonLunch(meal);
                    searchMonSupper(meal);
                    myDatabase.insertTueBreakfastData(meal, nutrition);
                    //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    firstMealTv.setText(meal);
                    firstMealNutrition.setText(nutrition);
                    myDatabase.close();}
                }
                else {

                    String currentMeal = firstMealTv.getText().toString();
                    if(currentMeal.equalsIgnoreCase(meal)){
                        Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                    }
                    else {
                        if (myDatabase.searchTueLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                            Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                        }
                        else if(myDatabase.searchTueSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                            Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                        }
                        else if (firstMealTv.getText().toString().trim().equalsIgnoreCase("")){
                            searchMonBreakfast(meal);
                            searchMonLunch(meal);
                            searchMonSupper(meal);
                            boolean isUpdated = myDatabase.updateTueBreakfastData(meal, nutrition, "1");
                            if (isUpdated) {
                                firstMealTv.setText(meal);
                                firstMealNutrition.setText(nutrition);
                            } else {
                                Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                        searchMonBreakfast(meal);
                        searchMonLunch(meal);
                        searchMonSupper(meal);
                        new AlertDialog.Builder(today_meal.this)
                        .setMessage(dialogMsgPt1 + currentMeal + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateTueBreakfastData(meal, nutrition, "1");
                    if (isUpdated) {
                        //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                        firstMealTv.setText(meal);
                        firstMealNutrition.setText(nutrition);
                    } else {
                        Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                    }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
            }
            //                //Tuesday Lunch
            else if (mealType.equalsIgnoreCase("Tuesday Lunch")) {
                Cursor res2 = myDatabase.viewTueLunchMeals();
                if (res2.getCount() == 0) {
                    if (myDatabase.searchTueBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchTueSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                    }
                    else{
                    searchMonBreakfast(meal);
                    searchMonLunch(meal);
                    searchMonSupper(meal);
                    myDatabase.insertTueLunchData(meal, nutrition);
                    //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    firstMealTv.setText(meal);
                    firstMealNutrition.setText(nutrition);
                    myDatabase.close();}
                }
                else {

                    String currentMeal = firstMealTv.getText().toString();
                    if(currentMeal.equalsIgnoreCase(meal)){
                        Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                    }
                    else {
                        if (myDatabase.searchTueBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                            Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                        }
                        else if(myDatabase.searchTueSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                            Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                        }
                        else if (firstMealTv.getText().toString().trim().equalsIgnoreCase("")){
                            searchMonBreakfast(meal);
                            searchMonLunch(meal);
                            searchMonSupper(meal);
                            boolean isUpdated = myDatabase.updateTueLunchData(meal, nutrition, "1");
                            if (isUpdated) {
                                firstMealTv.setText(meal);
                                firstMealNutrition.setText(nutrition);
                            } else {
                                Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                        searchMonBreakfast(meal);
                        searchMonLunch(meal);
                        searchMonSupper(meal);
                        new AlertDialog.Builder(today_meal.this)
                        .setMessage(dialogMsgPt1 + currentMeal + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateTueLunchData(meal, nutrition, "1");
                    if (isUpdated) {
                        //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                        firstMealTv.setText(meal);
                        firstMealNutrition.setText(nutrition);
                    } else {
                        Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                    }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
            }
            //                //Tuesday Supper
            else if (mealType.equalsIgnoreCase("Tuesday Supper")) {
                Cursor res2 = myDatabase.viewTueSupperMeals();
                if (res2.getCount() == 0) {
                    if (myDatabase.searchTueLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchTueBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                    }
                    else{
                    searchMonBreakfast(meal);
                    searchMonLunch(meal);
                    searchMonSupper(meal);
                    myDatabase.insertTueSupperData(meal, nutrition);
                    //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    firstMealTv.setText(meal);
                    firstMealNutrition.setText(nutrition);
                    myDatabase.close();}
                }
                else {

                    String currentMeal = firstMealTv.getText().toString();
                    if(currentMeal.equalsIgnoreCase(meal)){
                        Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                    }
                    else {
                        if (myDatabase.searchTueLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                            Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                        }
                        else if(myDatabase.searchTueBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                            Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                        }
                        else if (firstMealTv.getText().toString().trim().equalsIgnoreCase("")){
                            searchMonBreakfast(meal);
                            searchMonLunch(meal);
                            searchMonSupper(meal);
                            boolean isUpdated = myDatabase.updateTueSupperData(meal, nutrition, "1");
                            if (isUpdated) {
                                firstMealTv.setText(meal);
                                firstMealNutrition.setText(nutrition);
                            } else {
                                Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                        searchMonBreakfast(meal);
                        searchMonLunch(meal);
                        searchMonSupper(meal);
                        new AlertDialog.Builder(today_meal.this)
                        .setMessage(dialogMsgPt1 + currentMeal + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateTueSupperData(meal, nutrition, "1");
                    if (isUpdated) {
                        //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                        firstMealTv.setText(meal);
                        firstMealNutrition.setText(nutrition);
                    } else {
                        Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                    }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
            }
        }
        else if(pressedTextView.equalsIgnoreCase(mealtv2)){
            if (mealType.equalsIgnoreCase("Tuesday Breakfast")) {
                Cursor res = myDatabase.viewTueBreakfastMeals();
            String firstmeal = firstMealTv.getText().toString();
            if (res.getCount() < 1 || firstmeal.trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), firstCell, Toast.LENGTH_SHORT).show();
                }
                else if(meal.equalsIgnoreCase(firstMealTv.getText().toString())){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                }
                else if (res.getCount() == 1) {
                    if (myDatabase.searchTueLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchTueSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                    }
                    else{
                    searchMonBreakfast(meal);
                    searchMonLunch(meal);
                    searchMonSupper(meal);
                    myDatabase.insertTueBreakfastData(meal, nutrition);
                    //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    secondMealTv.setText(meal);
                    secondMealNutrition.setText(nutrition);
                    myDatabase.close();}
                }else {

                    String currentMeal1 = firstMealTv.getText().toString();
                    String currentMeal2 = secondMealTv.getText().toString();
                    String currentMeal3 = thirdMealTv.getText().toString();
                    String currentMeal4 = fourthMealTv.getText().toString();
                    String currentMeal5 = fifthMealTv.getText().toString();

                    if(meal.equalsIgnoreCase(currentMeal1)||meal.equalsIgnoreCase(currentMeal2)||meal.equalsIgnoreCase(currentMeal3)||
                            meal.equalsIgnoreCase(currentMeal4)||meal.equalsIgnoreCase(currentMeal5)){
                        Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                    }
                    else {
                        if (myDatabase.searchTueLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                            Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                        }
                        else if(myDatabase.searchTueSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                            Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                        }
                        else if (secondMealTv.getText().toString().trim().equalsIgnoreCase("")){
                            searchMonBreakfast(meal);
                            searchMonLunch(meal);
                            searchMonSupper(meal);
                            boolean isUpdated = myDatabase.updateTueBreakfastData(meal, nutrition, "2");
                            if (isUpdated) {
                                secondMealTv.setText(meal);
                                secondMealNutrition.setText(nutrition);
                            } else {
                                Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                        searchMonBreakfast(meal);
                        searchMonLunch(meal);
                        searchMonSupper(meal);
                        new AlertDialog.Builder(today_meal.this)
                        .setMessage(dialogMsgPt1 + currentMeal2 + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateTueBreakfastData(meal, nutrition, "2");
                    if (isUpdated) {
                        //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                        secondMealTv.setText(meal);
                        secondMealNutrition.setText(nutrition);
                    } else {
                        Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                    }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
            }
            //                //Tuesday Lunch
            else if (mealType.equalsIgnoreCase("Tuesday Lunch")) {
                Cursor res = myDatabase.viewTueLunchMeals();
            String firstmeal = firstMealTv.getText().toString();
            if (res.getCount() < 1 || firstmeal.trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), firstCell, Toast.LENGTH_SHORT).show();
                }
                else if(meal.equalsIgnoreCase(firstMealTv.getText().toString())){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                }
                else if (res.getCount() == 1) {
                    if (myDatabase.searchTueBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchTueSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                    }
                    else{
                    searchMonBreakfast(meal);
                    searchMonLunch(meal);
                    searchMonSupper(meal);
                    myDatabase.insertTueLunchData(meal, nutrition);
                    //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    secondMealTv.setText(meal);
                    secondMealNutrition.setText(nutrition);
                    myDatabase.close();}
                }else {

                    String currentMeal1 = firstMealTv.getText().toString();
                    String currentMeal2 = secondMealTv.getText().toString();
                    String currentMeal3 = thirdMealTv.getText().toString();
                    String currentMeal4 = fourthMealTv.getText().toString();
                    String currentMeal5 = fifthMealTv.getText().toString();

                    if(meal.equalsIgnoreCase(currentMeal1)||meal.equalsIgnoreCase(currentMeal2)||meal.equalsIgnoreCase(currentMeal3)||
                            meal.equalsIgnoreCase(currentMeal4)||meal.equalsIgnoreCase(currentMeal5)){
                        Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                    }
                    else {
                        if (myDatabase.searchTueBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                            Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                        }
                        else if(myDatabase.searchTueSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                            Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                        }
                        else if (secondMealTv.getText().toString().trim().equalsIgnoreCase("")){
                            searchMonBreakfast(meal);
                            searchMonLunch(meal);
                            searchMonSupper(meal);
                            boolean isUpdated = myDatabase.updateTueLunchData(meal, nutrition, "2");
                            if (isUpdated) {
                                secondMealTv.setText(meal);
                                secondMealNutrition.setText(nutrition);
                            } else {
                                Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                        searchMonBreakfast(meal);
                        searchMonLunch(meal);
                        searchMonSupper(meal);
                        new AlertDialog.Builder(today_meal.this)
                        .setMessage(dialogMsgPt1 + currentMeal2 + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateTueLunchData(meal, nutrition, "2");
                    if (isUpdated) {
                        //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                        secondMealTv.setText(meal);
                        secondMealNutrition.setText(nutrition);
                    } else {
                        Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                    }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
            }
            //                //Tuesday Supper
            else if (mealType.equalsIgnoreCase("Tuesday Supper")) {
                Cursor res = myDatabase.viewTueSupperMeals();
            String firstmeal = firstMealTv.getText().toString();
            if (res.getCount() < 1 || firstmeal.trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), firstCell, Toast.LENGTH_SHORT).show();
                }
                else if(meal.equalsIgnoreCase(firstMealTv.getText().toString())){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                }
                else if (res.getCount() == 1) {
                    if (myDatabase.searchTueLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchTueBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                    }
                    else{
                    searchMonBreakfast(meal);
                    searchMonLunch(meal);
                    searchMonSupper(meal);
                    myDatabase.insertTueSupperData(meal, nutrition);
                    //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    secondMealTv.setText(meal);
                    secondMealNutrition.setText(nutrition);
                    myDatabase.close();}
                }else {

                    String currentMeal1 = firstMealTv.getText().toString();
                    String currentMeal2 = secondMealTv.getText().toString();
                    String currentMeal3 = thirdMealTv.getText().toString();
                    String currentMeal4 = fourthMealTv.getText().toString();
                    String currentMeal5 = fifthMealTv.getText().toString();

                    if(meal.equalsIgnoreCase(currentMeal1)||meal.equalsIgnoreCase(currentMeal2)||meal.equalsIgnoreCase(currentMeal3)||
                            meal.equalsIgnoreCase(currentMeal4)||meal.equalsIgnoreCase(currentMeal5)){
                        Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                    }
                    else {
                        if (myDatabase.searchTueLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                            Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                        }
                        else if(myDatabase.searchTueBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                            Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                        }
                        else if (secondMealTv.getText().toString().trim().equalsIgnoreCase("")){
                            searchMonBreakfast(meal);
                            searchMonLunch(meal);
                            searchMonSupper(meal);
                            boolean isUpdated = myDatabase.updateTueSupperData(meal, nutrition, "2");
                            if (isUpdated) {
                                secondMealTv.setText(meal);
                                secondMealNutrition.setText(nutrition);
                            } else {
                                Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                        searchMonBreakfast(meal);
                        searchMonLunch(meal);
                        searchMonSupper(meal);
                        new AlertDialog.Builder(today_meal.this)
                        .setMessage(dialogMsgPt1 + currentMeal2 + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateTueSupperData(meal, nutrition, "2");
                    if (isUpdated) {
                        //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                        secondMealTv.setText(meal);
                        secondMealNutrition.setText(nutrition);
                    } else {
                        Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                    }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
            }
        }
        else if(pressedTextView.equalsIgnoreCase(mealtv3)){
            if (mealType.equalsIgnoreCase("Tuesday Breakfast")) {
                Cursor res = myDatabase.viewTueBreakfastMeals();
            String firstmeal = firstMealTv.getText().toString();
            if (res.getCount() < 1 || firstmeal.trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), firstCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 2||secondMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), secondCell, Toast.LENGTH_SHORT).show();
                }
                else if(meal.equalsIgnoreCase(firstMealTv.getText().toString())||meal.equalsIgnoreCase(secondMealTv.getText().toString())){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                }
                else if (res.getCount() == 2) {
                    if (myDatabase.searchTueLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchTueSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                    }
                    else{
                    searchMonBreakfast(meal);
                    searchMonLunch(meal);
                    searchMonSupper(meal);
                    myDatabase.insertTueBreakfastData(meal, nutrition);
                    //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    thirdMealTv.setText(meal);
                    thirdMealNutrition.setText(nutrition);
                    myDatabase.close();}
                } else {

                String currentMeal1 = firstMealTv.getText().toString();
                String currentMeal2 = secondMealTv.getText().toString();
                String currentMeal3 = thirdMealTv.getText().toString();
                String currentMeal4 = fourthMealTv.getText().toString();
                String currentMeal5 = fifthMealTv.getText().toString();

                if(meal.equalsIgnoreCase(currentMeal1)||meal.equalsIgnoreCase(currentMeal2)||meal.equalsIgnoreCase(currentMeal3)||
                        meal.equalsIgnoreCase(currentMeal4)||meal.equalsIgnoreCase(currentMeal5)){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                }
                else {
                    if (myDatabase.searchTueLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchTueSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                    }
                    else if (thirdMealTv.getText().toString().trim().equalsIgnoreCase("")){
                        searchMonBreakfast(meal);
                        searchMonLunch(meal);
                        searchMonSupper(meal);
                        boolean isUpdated = myDatabase.updateTueBreakfastData(meal, nutrition, "3");
                        if (isUpdated) {
                            thirdMealTv.setText(meal);
                            thirdMealNutrition.setText(nutrition);
                        } else {
                            Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                    searchMonBreakfast(meal);
                    searchMonLunch(meal);
                    searchMonSupper(meal);
                    new AlertDialog.Builder(today_meal.this)
                        .setMessage(dialogMsgPt1 + currentMeal3 + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateTueBreakfastData(meal, nutrition, "3");
                    if (isUpdated) {
                        //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                        thirdMealTv.setText(meal);
                        thirdMealNutrition.setText(nutrition);
                    } else {
                        Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                    }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
            }
            //                //Tuesday Lunch
            else if (mealType.equalsIgnoreCase("Tuesday Lunch")) {
                Cursor res = myDatabase.viewTueLunchMeals();
            String firstmeal = firstMealTv.getText().toString();
            if (res.getCount() < 1 || firstmeal.trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), firstCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 2||secondMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), secondCell, Toast.LENGTH_SHORT).show();
                }
                else if(meal.equalsIgnoreCase(firstMealTv.getText().toString())||meal.equalsIgnoreCase(secondMealTv.getText().toString())){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                }
                else if (res.getCount() == 2) {
                    if (myDatabase.searchTueBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchTueSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                    }

                    else{
                    searchMonBreakfast(meal);
                    searchMonLunch(meal);
                    searchMonSupper(meal);
                    myDatabase.insertTueLunchData(meal, nutrition);
                    //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    thirdMealTv.setText(meal);
                    thirdMealNutrition.setText(nutrition);
                    myDatabase.close();}
                } else {

                String currentMeal1 = firstMealTv.getText().toString();
                String currentMeal2 = secondMealTv.getText().toString();
                String currentMeal3 = thirdMealTv.getText().toString();
                String currentMeal4 = fourthMealTv.getText().toString();
                String currentMeal5 = fifthMealTv.getText().toString();

                if(meal.equalsIgnoreCase(currentMeal1)||meal.equalsIgnoreCase(currentMeal2)||meal.equalsIgnoreCase(currentMeal3)||
                        meal.equalsIgnoreCase(currentMeal4)||meal.equalsIgnoreCase(currentMeal5)){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                }
                else {
                    if (myDatabase.searchTueBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchTueSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                    }
                    else if (thirdMealTv.getText().toString().trim().equalsIgnoreCase("")){
                        searchMonBreakfast(meal);
                        searchMonLunch(meal);
                        searchMonSupper(meal);
                        boolean isUpdated = myDatabase.updateTueLunchData(meal, nutrition, "3");
                        if (isUpdated) {
                            thirdMealTv.setText(meal);
                            thirdMealNutrition.setText(nutrition);
                        } else {
                            Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                        }
                    }                   
                    
                    else{
                    searchMonBreakfast(meal);
                    searchMonLunch(meal);
                    searchMonSupper(meal);
                    new AlertDialog.Builder(today_meal.this)
                        .setMessage(dialogMsgPt1 + currentMeal3 + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateTueLunchData(meal, nutrition, "3");
                    if (isUpdated) {
                        //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                        thirdMealTv.setText(meal);
                        thirdMealNutrition.setText(nutrition);
                    } else {
                        Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                    }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
            }
            //                //Tuesday Supper yuo
            else if (mealType.equalsIgnoreCase("Tuesday Supper")) {
                Cursor res = myDatabase.viewTueSupperMeals();
            String firstmeal = firstMealTv.getText().toString();
            if (res.getCount() < 1 || firstmeal.trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), firstCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 2||secondMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), secondCell, Toast.LENGTH_SHORT).show();
                }
                else if(meal.equalsIgnoreCase(firstMealTv.getText().toString())||meal.equalsIgnoreCase(secondMealTv.getText().toString())){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                }
                else if (res.getCount() == 2) {
                    if (myDatabase.searchTueLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchTueBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                    }
                    else{
                    searchMonBreakfast(meal);
                    searchMonLunch(meal);
                    searchMonSupper(meal);
                    myDatabase.insertTueSupperData(meal, nutrition);
                    //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    thirdMealTv.setText(meal);
                    thirdMealNutrition.setText(nutrition);
                    myDatabase.close();}
                } else {

                String currentMeal1 = firstMealTv.getText().toString();
                String currentMeal2 = secondMealTv.getText().toString();
                String currentMeal3 = thirdMealTv.getText().toString();
                String currentMeal4 = fourthMealTv.getText().toString();
                String currentMeal5 = fifthMealTv.getText().toString();

                if(meal.equalsIgnoreCase(currentMeal1)||meal.equalsIgnoreCase(currentMeal2)||meal.equalsIgnoreCase(currentMeal3)||
                        meal.equalsIgnoreCase(currentMeal4)||meal.equalsIgnoreCase(currentMeal5)){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                }
                else {
                    if (myDatabase.searchTueLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchTueBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                    }
                    else if (thirdMealTv.getText().toString().trim().equalsIgnoreCase("")){
                        searchMonBreakfast(meal);
                        searchMonLunch(meal);
                        searchMonSupper(meal);
                        boolean isUpdated = myDatabase.updateTueSupperData(meal, nutrition, "3");
                        if (isUpdated) {
                            thirdMealTv.setText(meal);
                            thirdMealNutrition.setText(nutrition);
                        } else {
                            Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                    searchMonBreakfast(meal);
                    searchMonLunch(meal);
                    searchMonSupper(meal);
                    new AlertDialog.Builder(today_meal.this)
                        .setMessage(dialogMsgPt1 + currentMeal3 + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateTueSupperData(meal, nutrition, "3");
                    if (isUpdated) {
                        //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                        thirdMealTv.setText(meal);
                        thirdMealNutrition.setText(nutrition);
                    } else {
                        Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                    }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
            }
        }
        else if(pressedTextView.equalsIgnoreCase(mealtv4)){

            if (mealType.equalsIgnoreCase("Tuesday Breakfast")) {
                Cursor res = myDatabase.viewTueBreakfastMeals();
            String firstmeal = firstMealTv.getText().toString();
            if (res.getCount() < 1 || firstmeal.trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), firstCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 2||secondMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), secondCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 3||thirdMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), thirdCell, Toast.LENGTH_SHORT).show();
                }
                else if(meal.equalsIgnoreCase(firstMealTv.getText().toString())||meal.equalsIgnoreCase(secondMealTv.getText().toString())
                        ||meal.equalsIgnoreCase(thirdMealTv.getText().toString())){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                }
                else if (res.getCount() == 3) {
                    if (myDatabase.searchTueLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchTueSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                    }
                    else{
                    searchMonBreakfast(meal);
                    searchMonLunch(meal);
                    searchMonSupper(meal);
                    myDatabase.insertTueBreakfastData(meal, nutrition);
                    //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    fourthMealTv.setText(meal);
                    fourthMealNutrition.setText(nutrition);
                    myDatabase.close();}
                }  else {

                    String currentMeal1 = firstMealTv.getText().toString();
                    String currentMeal2 = secondMealTv.getText().toString();
                    String currentMeal3 = thirdMealTv.getText().toString();
                    String currentMeal4 = fourthMealTv.getText().toString();
                    String currentMeal5 = fifthMealTv.getText().toString();

                    if(meal.equalsIgnoreCase(currentMeal1)||meal.equalsIgnoreCase(currentMeal2)||meal.equalsIgnoreCase(currentMeal3)||
                            meal.equalsIgnoreCase(currentMeal4)||meal.equalsIgnoreCase(currentMeal5)){
                        Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                    }
                    else {
                        if (myDatabase.searchTueLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                            Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                        }
                        else if(myDatabase.searchTueSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                            Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                        }
                        else if (fourthMealTv.getText().toString().trim().equalsIgnoreCase("")){
                            searchMonBreakfast(meal);
                            searchMonLunch(meal);
                            searchMonSupper(meal);
                            boolean isUpdated = myDatabase.updateTueBreakfastData(meal, nutrition, "4");
                            if (isUpdated) {
                                fourthMealTv.setText(meal);
                                fourthMealNutrition.setText(nutrition);
                            } else {
                                Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                        searchMonBreakfast(meal);
                        searchMonLunch(meal);
                        searchMonSupper(meal);
                        new AlertDialog.Builder(today_meal.this)
                                .setMessage(dialogMsgPt1 + currentMeal4 + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateTueBreakfastData(meal, nutrition, "4");
                    if (isUpdated) {
                        //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                        fourthMealTv.setText(meal);
                        fourthMealNutrition.setText(nutrition);
                    } else {
                        Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                    }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
            }
            //                //Tuesday Lunch
            else if (mealType.equalsIgnoreCase("Tuesday Lunch")) {
                Cursor res = myDatabase.viewTueLunchMeals();
            String firstmeal = firstMealTv.getText().toString();
            if (res.getCount() < 1 || firstmeal.trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), firstCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 2||secondMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), secondCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 3||thirdMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), thirdCell, Toast.LENGTH_SHORT).show();
                }
                else if(meal.equalsIgnoreCase(firstMealTv.getText().toString())||meal.equalsIgnoreCase(secondMealTv.getText().toString())
                        ||meal.equalsIgnoreCase(thirdMealTv.getText().toString())){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                }
                else if (res.getCount() == 3) {
                    if (myDatabase.searchTueBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchTueSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                    }
                    else{
                    searchMonBreakfast(meal);
                    searchMonLunch(meal);
                    searchMonSupper(meal);
                    myDatabase.insertTueLunchData(meal, nutrition);
                    //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    fourthMealTv.setText(meal);
                    fourthMealNutrition.setText(nutrition);
                    myDatabase.close();}
                }  else {

                    String currentMeal1 = firstMealTv.getText().toString();
                    String currentMeal2 = secondMealTv.getText().toString();
                    String currentMeal3 = thirdMealTv.getText().toString();
                    String currentMeal4 = fourthMealTv.getText().toString();
                    String currentMeal5 = fifthMealTv.getText().toString();

                    if(meal.equalsIgnoreCase(currentMeal1)||meal.equalsIgnoreCase(currentMeal2)||meal.equalsIgnoreCase(currentMeal3)||
                            meal.equalsIgnoreCase(currentMeal4)||meal.equalsIgnoreCase(currentMeal5)){
                        Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                    }
                    else {
                        if (myDatabase.searchTueBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                            Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                        }
                        else if(myDatabase.searchTueSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                            Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                        }
                        else if (fourthMealTv.getText().toString().trim().equalsIgnoreCase("")){
                            searchMonBreakfast(meal);
                            searchMonLunch(meal);
                            searchMonSupper(meal);
                            boolean isUpdated = myDatabase.updateTueLunchData(meal, nutrition, "4");
                            if (isUpdated) {
                                fourthMealTv.setText(meal);
                                fourthMealNutrition.setText(nutrition);
                            } else {
                                Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                        searchMonBreakfast(meal);
                        searchMonLunch(meal);
                        searchMonSupper(meal);
                        new AlertDialog.Builder(today_meal.this)
                                .setMessage(dialogMsgPt1 + currentMeal4 + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateTueLunchData(meal, nutrition, "4");
                    if (isUpdated) {
                        //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                        fourthMealTv.setText(meal);
                        fourthMealNutrition.setText(nutrition);
                    } else {
                        Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                    }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
            }
            //                //Tuesday Supper
            else if (mealType.equalsIgnoreCase("Tuesday Supper")) {
                Cursor res = myDatabase.viewTueSupperMeals();
            String firstmeal = firstMealTv.getText().toString();
            if (res.getCount() < 1 || firstmeal.trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), firstCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 2||secondMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), secondCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 3||thirdMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), thirdCell, Toast.LENGTH_SHORT).show();
                }
                else if(meal.equalsIgnoreCase(firstMealTv.getText().toString())||meal.equalsIgnoreCase(secondMealTv.getText().toString())
                        ||meal.equalsIgnoreCase(thirdMealTv.getText().toString())){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                }
                else if (res.getCount() == 3) {
                    if (myDatabase.searchTueLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchTueBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                    }
                    else{
                    searchMonBreakfast(meal);
                    searchMonLunch(meal);
                    searchMonSupper(meal);
                    myDatabase.insertTueSupperData(meal, nutrition);
                    //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    fourthMealTv.setText(meal);
                    fourthMealNutrition.setText(nutrition);
                    myDatabase.close();}
                }  else {

                    String currentMeal1 = firstMealTv.getText().toString();
                    String currentMeal2 = secondMealTv.getText().toString();
                    String currentMeal3 = thirdMealTv.getText().toString();
                    String currentMeal4 = fourthMealTv.getText().toString();
                    String currentMeal5 = fifthMealTv.getText().toString();

                    if(meal.equalsIgnoreCase(currentMeal1)||meal.equalsIgnoreCase(currentMeal2)||meal.equalsIgnoreCase(currentMeal3)||
                            meal.equalsIgnoreCase(currentMeal4)||meal.equalsIgnoreCase(currentMeal5)){
                        Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                    }
                    else {
                        if (myDatabase.searchTueLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                            Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                        }
                        else if(myDatabase.searchTueBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                            Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                        }
                        else if (fourthMealTv.getText().toString().trim().equalsIgnoreCase("")){
                            searchMonBreakfast(meal);
                            searchMonLunch(meal);
                            searchMonSupper(meal);
                            boolean isUpdated = myDatabase.updateTueSupperData(meal, nutrition, "4");
                            if (isUpdated) {
                                fourthMealTv.setText(meal);
                                fourthMealNutrition.setText(nutrition);
                            } else {
                                Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                        searchMonBreakfast(meal);
                        searchMonLunch(meal);
                        searchMonSupper(meal);
                        new AlertDialog.Builder(today_meal.this)
                                .setMessage(dialogMsgPt1 + currentMeal4 + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateTueSupperData(meal, nutrition, "4");
                    if (isUpdated) {
                        //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                        fourthMealTv.setText(meal);
                        fourthMealNutrition.setText(nutrition);
                    } else {
                        Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                    }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
            }
        }

        else if(pressedTextView.equalsIgnoreCase(mealtv5)){
            if (mealType.equalsIgnoreCase("Tuesday Breakfast")) {
                Cursor res = myDatabase.viewTueBreakfastMeals();
            String firstmeal = firstMealTv.getText().toString();
            if (res.getCount() < 1 || firstmeal.trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), firstCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 2||secondMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), secondCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 3||thirdMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), thirdCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 4||fourthMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), fourthCell, Toast.LENGTH_SHORT).show();
                }
                else if(meal.equalsIgnoreCase(firstMealTv.getText().toString())||meal.equalsIgnoreCase(secondMealTv.getText().toString())
                        ||meal.equalsIgnoreCase(thirdMealTv.getText().toString())||meal.equalsIgnoreCase(fourthMealTv.getText().toString())){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                }
                else if (res.getCount() == 4) {
                    if (myDatabase.searchTueLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchTueSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                    }
                    else{
                    searchMonBreakfast(meal);
                    searchMonLunch(meal);
                    searchMonSupper(meal);
                    myDatabase.insertTueBreakfastData(meal, nutrition);
                    //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    fifthMealTv.setText(meal);
                    fifthMealNutrition.setText(nutrition);
                    myDatabase.close();}
                }  else {

                String currentMeal1 = firstMealTv.getText().toString();
                String currentMeal2 = secondMealTv.getText().toString();
                String currentMeal3 = thirdMealTv.getText().toString();
                String currentMeal4 = fourthMealTv.getText().toString();
                String currentMeal5 = fifthMealTv.getText().toString();

                if(meal.equalsIgnoreCase(currentMeal1)||meal.equalsIgnoreCase(currentMeal2)||meal.equalsIgnoreCase(currentMeal3)||
                        meal.equalsIgnoreCase(currentMeal4)||meal.equalsIgnoreCase(currentMeal5)){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                }
                else {
                    if (myDatabase.searchTueLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchTueSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                    }
                    else if (fifthMealTv.getText().toString().trim().equalsIgnoreCase("")){
                        searchMonBreakfast(meal);
                        searchMonLunch(meal);
                        searchMonSupper(meal);
                        boolean isUpdated = myDatabase.updateTueBreakfastData(meal, nutrition, "5");
                        if (isUpdated) {
                            fifthMealTv.setText(meal);
                            fifthMealNutrition.setText(nutrition);
                        } else {
                            Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                    searchMonBreakfast(meal);
                    searchMonLunch(meal);
                    searchMonSupper(meal);
                    new AlertDialog.Builder(today_meal.this)
                            .setMessage(dialogMsgPt1 + currentMeal5 + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateTueBreakfastData(meal, nutrition, "5");
                    if (isUpdated) {
                        //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                        fifthMealTv.setText(meal);
                        fifthMealNutrition.setText(nutrition);
                    } else {
                        Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                    }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
            }
            //                //Tuesday Lunch
            else if (mealType.equalsIgnoreCase("Tuesday Lunch")) {
                Cursor res = myDatabase.viewTueLunchMeals();
            String firstmeal = firstMealTv.getText().toString();
            if (res.getCount() < 1 || firstmeal.trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), firstCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 2||secondMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), secondCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 3||thirdMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), thirdCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 4||fourthMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), fourthCell, Toast.LENGTH_SHORT).show();
                }
                else if(meal.equalsIgnoreCase(firstMealTv.getText().toString())||meal.equalsIgnoreCase(secondMealTv.getText().toString())
                        ||meal.equalsIgnoreCase(thirdMealTv.getText().toString())||meal.equalsIgnoreCase(fourthMealTv.getText().toString())){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                }
                else if (res.getCount() == 4) {
                    if (myDatabase.searchTueBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchTueSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                    }
                    else{
                    searchMonBreakfast(meal);
                    searchMonLunch(meal);
                    searchMonSupper(meal);
                    myDatabase.insertTueLunchData(meal, nutrition);
                    //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    fifthMealTv.setText(meal);
                    fifthMealNutrition.setText(nutrition);
                    myDatabase.close();}
                }  else {

                String currentMeal1 = firstMealTv.getText().toString();
                String currentMeal2 = secondMealTv.getText().toString();
                String currentMeal3 = thirdMealTv.getText().toString();
                String currentMeal4 = fourthMealTv.getText().toString();
                String currentMeal5 = fifthMealTv.getText().toString();

                if(meal.equalsIgnoreCase(currentMeal1)||meal.equalsIgnoreCase(currentMeal2)||meal.equalsIgnoreCase(currentMeal3)||
                        meal.equalsIgnoreCase(currentMeal4)||meal.equalsIgnoreCase(currentMeal5)){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                }
                else {
                    if (myDatabase.searchTueBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchTueSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                    }
                    else if (fifthMealTv.getText().toString().trim().equalsIgnoreCase("")){
                        searchMonBreakfast(meal);
                        searchMonLunch(meal);
                        searchMonSupper(meal);
                        boolean isUpdated = myDatabase.updateTueLunchData(meal, nutrition, "5");
                        if (isUpdated) {
                            fifthMealTv.setText(meal);
                            fifthMealNutrition.setText(nutrition);
                        } else {
                            Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                    searchMonBreakfast(meal);
                    searchMonLunch(meal);
                    searchMonSupper(meal);
                    new AlertDialog.Builder(today_meal.this)
                            .setMessage(dialogMsgPt1 + currentMeal5 + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateTueLunchData(meal, nutrition, "5");
                    if (isUpdated) {
                        //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                        fifthMealTv.setText(meal);
                        fifthMealNutrition.setText(nutrition);
                    } else {
                        Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                    }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
            }
            //                //Tuesday Supper
            else if (mealType.equalsIgnoreCase("Tuesday Supper")) {
                Cursor res = myDatabase.viewTueSupperMeals();
            String firstmeal = firstMealTv.getText().toString();
            if (res.getCount() < 1 || firstmeal.trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), firstCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 2||secondMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), secondCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 3||thirdMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), thirdCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 4||fourthMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), fourthCell, Toast.LENGTH_SHORT).show();
                }
                else if(meal.equalsIgnoreCase(firstMealTv.getText().toString())||meal.equalsIgnoreCase(secondMealTv.getText().toString())
                        ||meal.equalsIgnoreCase(thirdMealTv.getText().toString())||meal.equalsIgnoreCase(fourthMealTv.getText().toString())){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                }
                else if (res.getCount() == 4) {
                    if (myDatabase.searchTueLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchTueBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                    }
                    else{
                    searchMonBreakfast(meal);
                    searchMonLunch(meal);
                    searchMonSupper(meal);
                    myDatabase.insertTueSupperData(meal, nutrition);
                    //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    fifthMealTv.setText(meal);
                    fifthMealNutrition.setText(nutrition);
                    myDatabase.close();}
                }  else {

                String currentMeal1 = firstMealTv.getText().toString();
                String currentMeal2 = secondMealTv.getText().toString();
                String currentMeal3 = thirdMealTv.getText().toString();
                String currentMeal4 = fourthMealTv.getText().toString();
                String currentMeal5 = fifthMealTv.getText().toString();

                if(meal.equalsIgnoreCase(currentMeal1)||meal.equalsIgnoreCase(currentMeal2)||meal.equalsIgnoreCase(currentMeal3)||
                        meal.equalsIgnoreCase(currentMeal4)||meal.equalsIgnoreCase(currentMeal5)){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                }
                else {
                    if (myDatabase.searchTueLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchTueBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                    }
                    else if (fifthMealTv.getText().toString().trim().equalsIgnoreCase("")){
                        searchMonBreakfast(meal);
                        searchMonLunch(meal);
                        searchMonSupper(meal);
                        boolean isUpdated = myDatabase.updateTueSupperData(meal, nutrition, "5");
                        if (isUpdated) {
                            fifthMealTv.setText(meal);
                            fifthMealNutrition.setText(nutrition);
                        } else {
                            Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                    searchMonBreakfast(meal);
                    searchMonLunch(meal);
                    searchMonSupper(meal);
                    new AlertDialog.Builder(today_meal.this)
                            .setMessage(dialogMsgPt1 + currentMeal5 + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateTueSupperData(meal, nutrition, "5");
                    if (isUpdated) {
                        //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                        fifthMealTv.setText(meal);
                        fifthMealNutrition.setText(nutrition);
                    } else {
                        Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                    }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
            }
        }

    }//END OF TUESDAY MEALS

    //WEDNESDAY MEALS
    public  void wednesdayMeals(String meal, String nutrition) {
//                Wednesday breakfast
        mealType = todaysmealTv.getText().toString();
//                Toast.makeText(getApplicationContext(), pressedTextView, Toast.LENGTH_SHORT).show();

        if (pressedTextView.equalsIgnoreCase(mealtv1)) {

            if (mealType.equalsIgnoreCase("Wednesday Breakfast")) {
                Cursor res2 = myDatabase.viewWedBreakfastMeals();
                if (res2.getCount() == 0) {
                    if (myDatabase.searchWedLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchWedSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                    }
                    else{
                    searchTueBreakfast(meal);
                    searchTueLunch(meal);
                    searchTueSupper(meal);
                    myDatabase.insertWedBreakfastData(meal, nutrition);
                    //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    firstMealTv.setText(meal);
                    firstMealNutrition.setText(nutrition);
                    myDatabase.close();}
                }
                else {

                    String currentMeal = firstMealTv.getText().toString();
                    if(currentMeal.equalsIgnoreCase(meal)){
                        Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                    }
                    else {
                        if (myDatabase.searchWedLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                            Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                        }
                        else if(myDatabase.searchWedSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                            Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                        }
                        else if (firstMealTv.getText().toString().trim().equalsIgnoreCase("")){
                            searchTueBreakfast(meal);
                            searchTueLunch(meal);
                            searchTueSupper(meal);
                            boolean isUpdated = myDatabase.updateWedBreakfastData(meal, nutrition, "1");
                            if (isUpdated) {
                                firstMealTv.setText(meal);
                                firstMealNutrition.setText(nutrition);
                            } else {
                                Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                        searchTueBreakfast(meal);
                        searchTueLunch(meal);
                        searchTueSupper(meal);
                        new AlertDialog.Builder(today_meal.this)
                        .setMessage(dialogMsgPt1 + currentMeal + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateWedBreakfastData(meal, nutrition, "1");
                    if (isUpdated) {
                        //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                        firstMealTv.setText(meal);
                        firstMealNutrition.setText(nutrition);
                    } else {
                        Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                    }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
            }
            //                //Wednesday Lunch
            else if (mealType.equalsIgnoreCase("Wednesday Lunch")) {
                Cursor res2 = myDatabase.viewWedLunchMeals();
                if (res2.getCount() == 0) {
                    if (myDatabase.searchWedBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchWedSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                    }
                    else{
                    searchTueBreakfast(meal);
                    searchTueLunch(meal);
                    searchTueSupper(meal);
                    myDatabase.insertWedLunchData(meal, nutrition);
                    //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    firstMealTv.setText(meal);
                    firstMealNutrition.setText(nutrition);
                    myDatabase.close();}
                }
                else {

                    String currentMeal = firstMealTv.getText().toString();
                    if(currentMeal.equalsIgnoreCase(meal)){
                        Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                    }
                    else {
                        if (myDatabase.searchWedBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                            Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                        }
                        else if(myDatabase.searchWedSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                            Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                        }
                        else if (firstMealTv.getText().toString().trim().equalsIgnoreCase("")){
                            searchTueBreakfast(meal);
                            searchTueLunch(meal);
                            searchTueSupper(meal);
                            boolean isUpdated = myDatabase.updateWedLunchData(meal, nutrition, "1");
                            if (isUpdated) {
                                firstMealTv.setText(meal);
                                firstMealNutrition.setText(nutrition);
                            } else {
                                Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                        searchTueBreakfast(meal);
                        searchTueLunch(meal);
                        searchTueSupper(meal);
                        new AlertDialog.Builder(today_meal.this)
                        .setMessage(dialogMsgPt1 + currentMeal + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateWedLunchData(meal, nutrition, "1");
                    if (isUpdated) {
                        //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                        firstMealTv.setText(meal);
                        firstMealNutrition.setText(nutrition);
                    } else {
                        Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                    }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
            }
            //                //Wednesday Supper
            else if (mealType.equalsIgnoreCase("Wednesday Supper")) {
                Cursor res2 = myDatabase.viewWedSupperMeals();
                if (res2.getCount() == 0) {
                    if (myDatabase.searchWedBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchWedLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                    }
                    else{
                    searchTueBreakfast(meal);
                    searchTueLunch(meal);
                    searchTueSupper(meal);
                    myDatabase.insertWedSupperData(meal, nutrition);
                    //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    firstMealTv.setText(meal);
                    firstMealNutrition.setText(nutrition);
                    myDatabase.close();}
                }
                else {

                    String currentMeal = firstMealTv.getText().toString();
                    if(currentMeal.equalsIgnoreCase(meal)){
                        Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                    }
                    else {
                        if (myDatabase.searchWedBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                            Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                        }
                        else if(myDatabase.searchWedLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                            Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                        }
                        else if (firstMealTv.getText().toString().trim().equalsIgnoreCase("")){
                            searchTueBreakfast(meal);
                            searchTueLunch(meal);
                            searchTueSupper(meal);
                            boolean isUpdated = myDatabase.updateWedSupperData(meal, nutrition, "1");
                            if (isUpdated) {
                                firstMealTv.setText(meal);
                                firstMealNutrition.setText(nutrition);
                            } else {
                                Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                        searchTueBreakfast(meal);
                        searchTueLunch(meal);
                        searchTueSupper(meal);
                        new AlertDialog.Builder(today_meal.this)
                        .setMessage(dialogMsgPt1 + currentMeal + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateWedSupperData(meal, nutrition, "1");
                    if (isUpdated) {
                        //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                        firstMealTv.setText(meal);
                        firstMealNutrition.setText(nutrition);
                    } else {
                        Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                    }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
            }
        }
        else if(pressedTextView.equalsIgnoreCase(mealtv2)){
            if (mealType.equalsIgnoreCase("Wednesday Breakfast")) {
                Cursor res = myDatabase.viewWedBreakfastMeals();
            String firstmeal = firstMealTv.getText().toString();
            if (res.getCount() < 1 || firstmeal.trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), firstCell, Toast.LENGTH_SHORT).show();
                }
                else if(meal.equalsIgnoreCase(firstMealTv.getText().toString())){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                }
                else if (res.getCount() == 1) {
                    if (myDatabase.searchWedLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchWedSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                    }
                    else{
                    searchTueBreakfast(meal);
                    searchTueLunch(meal);
                    searchTueSupper(meal);
                    myDatabase.insertWedBreakfastData(meal, nutrition);
                    //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    secondMealTv.setText(meal);
                    secondMealNutrition.setText(nutrition);
                    myDatabase.close();}
                }else {

                    String currentMeal1 = firstMealTv.getText().toString();
                    String currentMeal2 = secondMealTv.getText().toString();
                    String currentMeal3 = thirdMealTv.getText().toString();
                    String currentMeal4 = fourthMealTv.getText().toString();
                    String currentMeal5 = fifthMealTv.getText().toString();

                    if(meal.equalsIgnoreCase(currentMeal1)||meal.equalsIgnoreCase(currentMeal2)||meal.equalsIgnoreCase(currentMeal3)||
                            meal.equalsIgnoreCase(currentMeal4)||meal.equalsIgnoreCase(currentMeal5)){
                        Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                    }
                    else {
                        if (myDatabase.searchWedLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                            Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                        }
                        else if(myDatabase.searchWedSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                            Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                        }
                        else if (secondMealTv.getText().toString().trim().equalsIgnoreCase("")){
                            searchTueBreakfast(meal);
                            searchTueLunch(meal);
                            searchTueSupper(meal);
                            boolean isUpdated = myDatabase.updateWedBreakfastData(meal, nutrition, "2");
                            if (isUpdated) {
                                secondMealTv.setText(meal);
                                secondMealNutrition.setText(nutrition);
                            } else {
                                Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                        searchTueBreakfast(meal);
                        searchTueLunch(meal);
                        searchTueSupper(meal);
                        new AlertDialog.Builder(today_meal.this)
                        .setMessage(dialogMsgPt1 + currentMeal2 + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateWedBreakfastData(meal, nutrition, "2");
                    if (isUpdated) {
                        //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                        secondMealTv.setText(meal);
                        secondMealNutrition.setText(nutrition);
                    } else {
                        Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                    }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
            }
            //                //Wednesday Lunch
            else if (mealType.equalsIgnoreCase("Wednesday Lunch")) {
                Cursor res = myDatabase.viewWedLunchMeals();
            String firstmeal = firstMealTv.getText().toString();
            if (res.getCount() < 1 || firstmeal.trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), firstCell, Toast.LENGTH_SHORT).show();
                }
                else if(meal.equalsIgnoreCase(firstMealTv.getText().toString())){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                }
                else if (res.getCount() == 1) {
                    if (myDatabase.searchWedBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchWedSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                    }
                    else{
                    searchTueBreakfast(meal);
                    searchTueLunch(meal);
                    searchTueSupper(meal);
                    myDatabase.insertWedLunchData(meal, nutrition);
                    //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    secondMealTv.setText(meal);
                    secondMealNutrition.setText(nutrition);
                    myDatabase.close();}
                }else {

                    String currentMeal1 = firstMealTv.getText().toString();
                    String currentMeal2 = secondMealTv.getText().toString();
                    String currentMeal3 = thirdMealTv.getText().toString();
                    String currentMeal4 = fourthMealTv.getText().toString();
                    String currentMeal5 = fifthMealTv.getText().toString();

                    if(meal.equalsIgnoreCase(currentMeal1)||meal.equalsIgnoreCase(currentMeal2)||meal.equalsIgnoreCase(currentMeal3)||
                            meal.equalsIgnoreCase(currentMeal4)||meal.equalsIgnoreCase(currentMeal5)){
                        Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                    }
                    else {
                        if (myDatabase.searchWedBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                            Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                        }
                        else if(myDatabase.searchWedSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                            Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                        }
                        else if (secondMealTv.getText().toString().trim().equalsIgnoreCase("")){
                            searchTueBreakfast(meal);
                            searchTueLunch(meal);
                            searchTueSupper(meal);
                            boolean isUpdated = myDatabase.updateWedLunchData(meal, nutrition, "2");
                            if (isUpdated) {
                                secondMealTv.setText(meal);
                                secondMealNutrition.setText(nutrition);
                            } else {
                                Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                        searchTueBreakfast(meal);
                        searchTueLunch(meal);
                        searchTueSupper(meal);
                        new AlertDialog.Builder(today_meal.this)
                        .setMessage(dialogMsgPt1 + currentMeal2 + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateWedLunchData(meal, nutrition, "2");
                    if (isUpdated) {
                        //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                        secondMealTv.setText(meal);
                        secondMealNutrition.setText(nutrition);
                    } else {
                        Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                    }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
            }
            //                //Wednesday Supper
            else if (mealType.equalsIgnoreCase("Wednesday Supper")) {
                Cursor res = myDatabase.viewWedSupperMeals();
            String firstmeal = firstMealTv.getText().toString();
            if (res.getCount() < 1 || firstmeal.trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), firstCell, Toast.LENGTH_SHORT).show();
                }
                else if(meal.equalsIgnoreCase(firstMealTv.getText().toString())){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                }
                else if (res.getCount() == 1) {
                    if (myDatabase.searchWedBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchWedLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                    }
                    else{
                    searchTueBreakfast(meal);
                    searchTueLunch(meal);
                    searchTueSupper(meal);
                    myDatabase.insertWedSupperData(meal, nutrition);
                    //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    secondMealTv.setText(meal);
                    secondMealNutrition.setText(nutrition);
                    myDatabase.close();}
                }else {

                    String currentMeal1 = firstMealTv.getText().toString();
                    String currentMeal2 = secondMealTv.getText().toString();
                    String currentMeal3 = thirdMealTv.getText().toString();
                    String currentMeal4 = fourthMealTv.getText().toString();
                    String currentMeal5 = fifthMealTv.getText().toString();

                    if(meal.equalsIgnoreCase(currentMeal1)||meal.equalsIgnoreCase(currentMeal2)||meal.equalsIgnoreCase(currentMeal3)||
                            meal.equalsIgnoreCase(currentMeal4)||meal.equalsIgnoreCase(currentMeal5)){
                        Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                    }
                    else {
                        if (myDatabase.searchWedBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                            Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                        }
                        else if(myDatabase.searchWedLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                            Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                        }
                        else if (secondMealTv.getText().toString().trim().equalsIgnoreCase("")){
                            searchTueBreakfast(meal);
                            searchTueLunch(meal);
                            searchTueSupper(meal);
                            boolean isUpdated = myDatabase.updateWedSupperData(meal, nutrition, "2");
                            if (isUpdated) {
                                secondMealTv.setText(meal);
                                secondMealNutrition.setText(nutrition);
                            } else {
                                Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                        searchTueBreakfast(meal);
                        searchTueLunch(meal);
                        searchTueSupper(meal);
                        new AlertDialog.Builder(today_meal.this)
                        .setMessage(dialogMsgPt1 + currentMeal2 + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateWedSupperData(meal, nutrition, "2");
                    if (isUpdated) {
                        //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                        secondMealTv.setText(meal);
                        secondMealNutrition.setText(nutrition);
                    } else {
                        Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                    }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
            }
        }
        else if(pressedTextView.equalsIgnoreCase(mealtv3)){
            if (mealType.equalsIgnoreCase("Wednesday Breakfast")) {
                Cursor res = myDatabase.viewWedBreakfastMeals();
            String firstmeal = firstMealTv.getText().toString();
            if (res.getCount() < 1 || firstmeal.trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), firstCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 2||secondMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), secondCell, Toast.LENGTH_SHORT).show();
                }
                else if(meal.equalsIgnoreCase(firstMealTv.getText().toString())||meal.equalsIgnoreCase(secondMealTv.getText().toString())){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                }
                else if (res.getCount() == 2) {
                    if (myDatabase.searchWedLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchWedSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                    }
                    else{
                    searchTueBreakfast(meal);
                    searchTueLunch(meal);
                    searchTueSupper(meal);
                    myDatabase.insertWedBreakfastData(meal, nutrition);
                    //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    thirdMealTv.setText(meal);
                    thirdMealNutrition.setText(nutrition);
                    myDatabase.close();}
                } else {

                String currentMeal1 = firstMealTv.getText().toString();
                String currentMeal2 = secondMealTv.getText().toString();
                String currentMeal3 = thirdMealTv.getText().toString();
                String currentMeal4 = fourthMealTv.getText().toString();
                String currentMeal5 = fifthMealTv.getText().toString();

                if(meal.equalsIgnoreCase(currentMeal1)||meal.equalsIgnoreCase(currentMeal2)||meal.equalsIgnoreCase(currentMeal3)||
                        meal.equalsIgnoreCase(currentMeal4)||meal.equalsIgnoreCase(currentMeal5)){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                }
                else {
                    if (myDatabase.searchWedLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchWedSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                    }
                    else if (thirdMealTv.getText().toString().trim().equalsIgnoreCase("")){
                        searchTueBreakfast(meal);
                        searchTueLunch(meal);
                        searchTueSupper(meal);
                        boolean isUpdated = myDatabase.updateWedBreakfastData(meal, nutrition, "3");
                        if (isUpdated) {
                            thirdMealTv.setText(meal);
                            thirdMealNutrition.setText(nutrition);
                        } else {
                            Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                    searchTueBreakfast(meal);
                    searchTueLunch(meal);
                    searchTueSupper(meal);
                    new AlertDialog.Builder(today_meal.this)
                        .setMessage(dialogMsgPt1 + currentMeal3 + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateWedBreakfastData(meal, nutrition, "3");
                    if (isUpdated) {
                        //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                        thirdMealTv.setText(meal);
                        thirdMealNutrition.setText(nutrition);
                    } else {
                        Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                    }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
            }
            //                //Wednesday Lunch
            else if (mealType.equalsIgnoreCase("Wednesday Lunch")) {
                Cursor res = myDatabase.viewWedLunchMeals();
            String firstmeal = firstMealTv.getText().toString();
            if (res.getCount() < 1 || firstmeal.trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), firstCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 2||secondMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), secondCell, Toast.LENGTH_SHORT).show();
                }
                else if(meal.equalsIgnoreCase(firstMealTv.getText().toString())||meal.equalsIgnoreCase(secondMealTv.getText().toString())){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                }
                else if (res.getCount() == 2) {
                    if (myDatabase.searchWedBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchWedSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                    }
                    else{
                    searchTueBreakfast(meal);
                    searchTueLunch(meal);
                    searchTueSupper(meal);
                    myDatabase.insertWedLunchData(meal, nutrition);
                    //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    thirdMealTv.setText(meal);
                    thirdMealNutrition.setText(nutrition);
                    myDatabase.close();}
                } else {

                String currentMeal1 = firstMealTv.getText().toString();
                String currentMeal2 = secondMealTv.getText().toString();
                String currentMeal3 = thirdMealTv.getText().toString();
                String currentMeal4 = fourthMealTv.getText().toString();
                String currentMeal5 = fifthMealTv.getText().toString();

                if(meal.equalsIgnoreCase(currentMeal1)||meal.equalsIgnoreCase(currentMeal2)||meal.equalsIgnoreCase(currentMeal3)||
                        meal.equalsIgnoreCase(currentMeal4)||meal.equalsIgnoreCase(currentMeal5)){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                }
                else {
                    if (myDatabase.searchWedBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchWedSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                    }
                    else if (thirdMealTv.getText().toString().trim().equalsIgnoreCase("")){
                        searchTueBreakfast(meal);
                        searchTueLunch(meal);
                        searchTueSupper(meal);
                        boolean isUpdated = myDatabase.updateWedLunchData(meal, nutrition, "3");
                        if (isUpdated) {
                            thirdMealTv.setText(meal);
                            thirdMealNutrition.setText(nutrition);
                        } else {
                            Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                    searchTueBreakfast(meal);
                    searchTueLunch(meal);
                    searchTueSupper(meal);
                    new AlertDialog.Builder(today_meal.this)
                        .setMessage(dialogMsgPt1 + currentMeal3 + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateWedLunchData(meal, nutrition, "3");
                    if (isUpdated) {
                        //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                        thirdMealTv.setText(meal);
                        thirdMealNutrition.setText(nutrition);
                    } else {
                        Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                    }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
            }
            //                //Wednesday Supper yuo
            else if (mealType.equalsIgnoreCase("Wednesday Supper")) {
                Cursor res = myDatabase.viewWedSupperMeals();
            String firstmeal = firstMealTv.getText().toString();
            if (res.getCount() < 1 || firstmeal.trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), firstCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 2||secondMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), secondCell, Toast.LENGTH_SHORT).show();
                }
                else if(meal.equalsIgnoreCase(firstMealTv.getText().toString())||meal.equalsIgnoreCase(secondMealTv.getText().toString())){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                }
                else if (res.getCount() == 2) {
                    if (myDatabase.searchWedBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchWedLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                    }
                    else{
                    searchTueBreakfast(meal);
                    searchTueLunch(meal);
                    searchTueSupper(meal);
                    myDatabase.insertWedSupperData(meal, nutrition);
                    //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    thirdMealTv.setText(meal);
                    thirdMealNutrition.setText(nutrition);
                    myDatabase.close();}
                } else {

                String currentMeal1 = firstMealTv.getText().toString();
                String currentMeal2 = secondMealTv.getText().toString();
                String currentMeal3 = thirdMealTv.getText().toString();
                String currentMeal4 = fourthMealTv.getText().toString();
                String currentMeal5 = fifthMealTv.getText().toString();

                if(meal.equalsIgnoreCase(currentMeal1)||meal.equalsIgnoreCase(currentMeal2)||meal.equalsIgnoreCase(currentMeal3)||
                        meal.equalsIgnoreCase(currentMeal4)||meal.equalsIgnoreCase(currentMeal5)){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                }
                else {
                    if (myDatabase.searchWedBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchWedLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                    }
                    else if (thirdMealTv.getText().toString().trim().equalsIgnoreCase("")){
                        searchTueBreakfast(meal);
                        searchTueLunch(meal);
                        searchTueSupper(meal);
                        boolean isUpdated = myDatabase.updateWedSupperData(meal, nutrition, "3");
                        if (isUpdated) {
                            thirdMealTv.setText(meal);
                            thirdMealNutrition.setText(nutrition);
                        } else {
                            Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                    searchTueBreakfast(meal);
                    searchTueLunch(meal);
                    searchTueSupper(meal);
                    new AlertDialog.Builder(today_meal.this)
                        .setMessage(dialogMsgPt1 + currentMeal3 + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateWedSupperData(meal, nutrition, "3");
                    if (isUpdated) {
                        //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                        thirdMealTv.setText(meal);
                        thirdMealNutrition.setText(nutrition);
                    } else {
                        Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                    }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
            }
        }
        else if(pressedTextView.equalsIgnoreCase(mealtv4)){

            if (mealType.equalsIgnoreCase("Wednesday Breakfast")) {
                Cursor res = myDatabase.viewWedBreakfastMeals();
            String firstmeal = firstMealTv.getText().toString();
            if (res.getCount() < 1 || firstmeal.trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), firstCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 2||secondMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), secondCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 3||thirdMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), thirdCell, Toast.LENGTH_SHORT).show();
                }
                else if(meal.equalsIgnoreCase(firstMealTv.getText().toString())||meal.equalsIgnoreCase(secondMealTv.getText().toString())
                        ||meal.equalsIgnoreCase(thirdMealTv.getText().toString())){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                }
                else if (res.getCount() == 3) {
                    if (myDatabase.searchWedLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchWedSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                    }
                    else{
                    searchTueBreakfast(meal);
                    searchTueLunch(meal);
                    searchTueSupper(meal);
                    myDatabase.insertWedBreakfastData(meal, nutrition);
                    //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    fourthMealTv.setText(meal);
                    fourthMealNutrition.setText(nutrition);
                    myDatabase.close();}
                }  else {

                    String currentMeal1 = firstMealTv.getText().toString();
                    String currentMeal2 = secondMealTv.getText().toString();
                    String currentMeal3 = thirdMealTv.getText().toString();
                    String currentMeal4 = fourthMealTv.getText().toString();
                    String currentMeal5 = fifthMealTv.getText().toString();

                    if(meal.equalsIgnoreCase(currentMeal1)||meal.equalsIgnoreCase(currentMeal2)||meal.equalsIgnoreCase(currentMeal3)||
                            meal.equalsIgnoreCase(currentMeal4)||meal.equalsIgnoreCase(currentMeal5)){
                        Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                    }
                    else {
                        if (myDatabase.searchWedLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                            Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                        }
                        else if(myDatabase.searchWedSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                            Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                        }
                        else if (fourthMealTv.getText().toString().trim().equalsIgnoreCase("")){
                            searchTueBreakfast(meal);
                            searchTueLunch(meal);
                            searchTueSupper(meal);
                            boolean isUpdated = myDatabase.updateWedBreakfastData(meal, nutrition, "4");
                            if (isUpdated) {
                                fourthMealTv.setText(meal);
                                fourthMealNutrition.setText(nutrition);
                            } else {
                                Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                        searchTueBreakfast(meal);
                        searchTueLunch(meal);
                        searchTueSupper(meal);
                        new AlertDialog.Builder(today_meal.this)
                                .setMessage(dialogMsgPt1 + currentMeal4 + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateWedBreakfastData(meal, nutrition, "4");
                    if (isUpdated) {
                        //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                        fourthMealTv.setText(meal);
                        fourthMealNutrition.setText(nutrition);
                    } else {
                        Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                    }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
            }
            //                //Wednesday Lunch
            else if (mealType.equalsIgnoreCase("Wednesday Lunch")) {
                Cursor res = myDatabase.viewWedLunchMeals();
            String firstmeal = firstMealTv.getText().toString();
            if (res.getCount() < 1 || firstmeal.trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), firstCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 2||secondMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), secondCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 3||thirdMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), thirdCell, Toast.LENGTH_SHORT).show();
                }
                else if(meal.equalsIgnoreCase(firstMealTv.getText().toString())||meal.equalsIgnoreCase(secondMealTv.getText().toString())
                        ||meal.equalsIgnoreCase(thirdMealTv.getText().toString())){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                }
                else if (res.getCount() == 3) {
                    if (myDatabase.searchWedBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchWedSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                    }
                    else{
                    searchTueBreakfast(meal);
                    searchTueLunch(meal);
                    searchTueSupper(meal);
                    myDatabase.insertWedLunchData(meal, nutrition);
                    //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    fourthMealTv.setText(meal);
                    fourthMealNutrition.setText(nutrition);
                    myDatabase.close();}
                }  else {

                    String currentMeal1 = firstMealTv.getText().toString();
                    String currentMeal2 = secondMealTv.getText().toString();
                    String currentMeal3 = thirdMealTv.getText().toString();
                    String currentMeal4 = fourthMealTv.getText().toString();
                    String currentMeal5 = fifthMealTv.getText().toString();

                    if(meal.equalsIgnoreCase(currentMeal1)||meal.equalsIgnoreCase(currentMeal2)||meal.equalsIgnoreCase(currentMeal3)||
                            meal.equalsIgnoreCase(currentMeal4)||meal.equalsIgnoreCase(currentMeal5)){
                        Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                    }
                    else {
                        if (myDatabase.searchWedBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                            Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                        }
                        else if(myDatabase.searchWedSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                            Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                        }
                        else if (fourthMealTv.getText().toString().trim().equalsIgnoreCase("")){
                            searchTueBreakfast(meal);
                            searchTueLunch(meal);
                            searchTueSupper(meal);
                            boolean isUpdated = myDatabase.updateWedLunchData(meal, nutrition, "4");
                            if (isUpdated) {
                                fourthMealTv.setText(meal);
                                fourthMealNutrition.setText(nutrition);
                            } else {
                                Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                        searchTueBreakfast(meal);
                        searchTueLunch(meal);
                        searchTueSupper(meal);
                        new AlertDialog.Builder(today_meal.this)
                                .setMessage(dialogMsgPt1 + currentMeal4 + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateWedLunchData(meal, nutrition, "4");
                    if (isUpdated) {
                        //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                        fourthMealTv.setText(meal);
                        fourthMealNutrition.setText(nutrition);
                    } else {
                        Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                    }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
            }
            //                //Wednesday Supper
            else if (mealType.equalsIgnoreCase("Wednesday Supper")) {
                Cursor res = myDatabase.viewWedSupperMeals();
            String firstmeal = firstMealTv.getText().toString();
            if (res.getCount() < 1 || firstmeal.trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), firstCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 2||secondMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), secondCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 3||thirdMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), thirdCell, Toast.LENGTH_SHORT).show();
                }
                else if(meal.equalsIgnoreCase(firstMealTv.getText().toString())||meal.equalsIgnoreCase(secondMealTv.getText().toString())
                        ||meal.equalsIgnoreCase(thirdMealTv.getText().toString())){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                }
                else if (res.getCount() == 3) {
                    if (myDatabase.searchWedBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchWedLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                    }
                    else{
                    searchTueBreakfast(meal);
                    searchTueLunch(meal);
                    searchTueSupper(meal);
                    myDatabase.insertWedSupperData(meal, nutrition);
                    //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    fourthMealTv.setText(meal);
                    fourthMealNutrition.setText(nutrition);
                    myDatabase.close();}
                }  else {

                    String currentMeal1 = firstMealTv.getText().toString();
                    String currentMeal2 = secondMealTv.getText().toString();
                    String currentMeal3 = thirdMealTv.getText().toString();
                    String currentMeal4 = fourthMealTv.getText().toString();
                    String currentMeal5 = fifthMealTv.getText().toString();

                    if(meal.equalsIgnoreCase(currentMeal1)||meal.equalsIgnoreCase(currentMeal2)||meal.equalsIgnoreCase(currentMeal3)||
                            meal.equalsIgnoreCase(currentMeal4)||meal.equalsIgnoreCase(currentMeal5)){
                        Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                    }
                    else {
                        if (myDatabase.searchWedBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                            Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                        }
                        else if(myDatabase.searchWedLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                            Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                        }
                        else if (fourthMealTv.getText().toString().trim().equalsIgnoreCase("")){
                            searchTueBreakfast(meal);
                            searchTueLunch(meal);
                            searchTueSupper(meal);
                            boolean isUpdated = myDatabase.updateWedSupperData(meal, nutrition, "4");
                            if (isUpdated) {
                                fourthMealTv.setText(meal);
                                fourthMealNutrition.setText(nutrition);
                            } else {
                                Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                        searchTueBreakfast(meal);
                        searchTueLunch(meal);
                        searchTueSupper(meal);
                        new AlertDialog.Builder(today_meal.this)
                                .setMessage(dialogMsgPt1 + currentMeal4 + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateWedSupperData(meal, nutrition, "4");
                    if (isUpdated) {
                        //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                        fourthMealTv.setText(meal);
                        fourthMealNutrition.setText(nutrition);
                    } else {
                        Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                    }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
            }
        }

        else if(pressedTextView.equalsIgnoreCase(mealtv5)){
            if (mealType.equalsIgnoreCase("Wednesday Breakfast")) {
                Cursor res = myDatabase.viewWedBreakfastMeals();
            String firstmeal = firstMealTv.getText().toString();
            if (res.getCount() < 1 || firstmeal.trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), firstCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 2||secondMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), secondCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 3||thirdMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), thirdCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 4||fourthMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), fourthCell, Toast.LENGTH_SHORT).show();
                }
                else if(meal.equalsIgnoreCase(firstMealTv.getText().toString())||meal.equalsIgnoreCase(secondMealTv.getText().toString())
                        ||meal.equalsIgnoreCase(thirdMealTv.getText().toString())||meal.equalsIgnoreCase(fourthMealTv.getText().toString())){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                }
                else if (res.getCount() == 4) {
                    if (myDatabase.searchWedLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchWedSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                    }
                    else{
                    searchTueBreakfast(meal);
                    searchTueLunch(meal);
                    searchTueSupper(meal);
                    myDatabase.insertWedBreakfastData(meal, nutrition);
                    //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    fifthMealTv.setText(meal);
                    fifthMealNutrition.setText(nutrition);
                    myDatabase.close();}
                }  else {

                String currentMeal1 = firstMealTv.getText().toString();
                String currentMeal2 = secondMealTv.getText().toString();
                String currentMeal3 = thirdMealTv.getText().toString();
                String currentMeal4 = fourthMealTv.getText().toString();
                String currentMeal5 = fifthMealTv.getText().toString();

                if(meal.equalsIgnoreCase(currentMeal1)||meal.equalsIgnoreCase(currentMeal2)||meal.equalsIgnoreCase(currentMeal3)||
                        meal.equalsIgnoreCase(currentMeal4)||meal.equalsIgnoreCase(currentMeal5)){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                }
                else {
                    if (myDatabase.searchWedLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchWedSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                    }
                    else if (fifthMealTv.getText().toString().trim().equalsIgnoreCase("")){
                        searchTueBreakfast(meal);
                        searchTueLunch(meal);
                        searchTueSupper(meal);
                        boolean isUpdated = myDatabase.updateWedBreakfastData(meal, nutrition, "5");
                        if (isUpdated) {
                            fifthMealTv.setText(meal);
                            fifthMealNutrition.setText(nutrition);
                        } else {
                            Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                    searchTueBreakfast(meal);
                    searchTueLunch(meal);
                    searchTueSupper(meal);
                    new AlertDialog.Builder(today_meal.this)
                            .setMessage(dialogMsgPt1 + currentMeal5 + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateWedBreakfastData(meal, nutrition, "5");
                    if (isUpdated) {
                        //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                        fifthMealTv.setText(meal);
                        fifthMealNutrition.setText(nutrition);
                    } else {
                        Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                    }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
            }
            //                //Wednesday Lunch
            else if (mealType.equalsIgnoreCase("Wednesday Lunch")) {
                Cursor res = myDatabase.viewWedLunchMeals();
            String firstmeal = firstMealTv.getText().toString();
            if (res.getCount() < 1 || firstmeal.trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), firstCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 2||secondMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), secondCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 3||thirdMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), thirdCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 4||fourthMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), fourthCell, Toast.LENGTH_SHORT).show();
                }
                else if(meal.equalsIgnoreCase(firstMealTv.getText().toString())||meal.equalsIgnoreCase(secondMealTv.getText().toString())
                        ||meal.equalsIgnoreCase(thirdMealTv.getText().toString())||meal.equalsIgnoreCase(fourthMealTv.getText().toString())){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                }
                else if (res.getCount() == 4) {
                    if (myDatabase.searchWedBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchWedSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                    }
                    else{
                    searchTueBreakfast(meal);
                    searchTueLunch(meal);
                    searchTueSupper(meal);
                    myDatabase.insertWedLunchData(meal, nutrition);
                    //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    fifthMealTv.setText(meal);
                    fifthMealNutrition.setText(nutrition);
                    myDatabase.close();}
                }  else {

                String currentMeal1 = firstMealTv.getText().toString();
                String currentMeal2 = secondMealTv.getText().toString();
                String currentMeal3 = thirdMealTv.getText().toString();
                String currentMeal4 = fourthMealTv.getText().toString();
                String currentMeal5 = fifthMealTv.getText().toString();

                if(meal.equalsIgnoreCase(currentMeal1)||meal.equalsIgnoreCase(currentMeal2)||meal.equalsIgnoreCase(currentMeal3)||
                        meal.equalsIgnoreCase(currentMeal4)||meal.equalsIgnoreCase(currentMeal5)){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                }
                else {
                    if (myDatabase.searchWedBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchWedSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                    }
                    else if (fifthMealTv.getText().toString().trim().equalsIgnoreCase("")){
                        searchTueBreakfast(meal);
                        searchTueLunch(meal);
                        searchTueSupper(meal);
                        boolean isUpdated = myDatabase.updateWedLunchData(meal, nutrition, "5");
                        if (isUpdated) {
                            fifthMealTv.setText(meal);
                            fifthMealNutrition.setText(nutrition);
                        } else {
                            Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                    searchTueBreakfast(meal);
                    searchTueLunch(meal);
                    searchTueSupper(meal);
                    new AlertDialog.Builder(today_meal.this)
                            .setMessage(dialogMsgPt1 + currentMeal5 + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateWedLunchData(meal, nutrition, "5");
                    if (isUpdated) {
                        //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                        fifthMealTv.setText(meal);
                        fifthMealNutrition.setText(nutrition);
                    } else {
                        Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                    }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
            }
            //                //Wednesday Supper
            else if (mealType.equalsIgnoreCase("Wednesday Supper")) {
                Cursor res = myDatabase.viewWedSupperMeals();
            String firstmeal = firstMealTv.getText().toString();
            if (res.getCount() < 1 || firstmeal.trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), firstCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 2||secondMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), secondCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 3||thirdMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), thirdCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 4||fourthMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), fourthCell, Toast.LENGTH_SHORT).show();
                }
                else if(meal.equalsIgnoreCase(firstMealTv.getText().toString())||meal.equalsIgnoreCase(secondMealTv.getText().toString())
                        ||meal.equalsIgnoreCase(thirdMealTv.getText().toString())||meal.equalsIgnoreCase(fourthMealTv.getText().toString())){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                }
                else if (res.getCount() == 4) {
                    if (myDatabase.searchWedBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchWedLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                    }
                    else{
                    searchTueBreakfast(meal);
                    searchTueLunch(meal);
                    searchTueSupper(meal);
                    myDatabase.insertWedSupperData(meal, nutrition);
                    //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    fifthMealTv.setText(meal);
                    fifthMealNutrition.setText(nutrition);
                    myDatabase.close();}
                }  else {

                String currentMeal1 = firstMealTv.getText().toString();
                String currentMeal2 = secondMealTv.getText().toString();
                String currentMeal3 = thirdMealTv.getText().toString();
                String currentMeal4 = fourthMealTv.getText().toString();
                String currentMeal5 = fifthMealTv.getText().toString();

                if(meal.equalsIgnoreCase(currentMeal1)||meal.equalsIgnoreCase(currentMeal2)||meal.equalsIgnoreCase(currentMeal3)||
                        meal.equalsIgnoreCase(currentMeal4)||meal.equalsIgnoreCase(currentMeal5)){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                }
                else {
                    if (myDatabase.searchWedBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchWedLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                    }
                    else if (fifthMealTv.getText().toString().trim().equalsIgnoreCase("")){
                        searchTueBreakfast(meal);
                        searchTueLunch(meal);
                        searchTueSupper(meal);
                        boolean isUpdated = myDatabase.updateWedSupperData(meal, nutrition, "5");
                        if (isUpdated) {
                            fifthMealTv.setText(meal);
                            fifthMealNutrition.setText(nutrition);
                        } else {
                            Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                    searchTueBreakfast(meal);
                    searchTueLunch(meal);
                    searchTueSupper(meal);
                    new AlertDialog.Builder(today_meal.this)
                            .setMessage(dialogMsgPt1 + currentMeal5 + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateWedSupperData(meal, nutrition, "5");
                    if (isUpdated) {
                        //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                        fifthMealTv.setText(meal);
                        fifthMealNutrition.setText(nutrition);
                    } else {
                        Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                    }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
            }
        }

    }//END OF WEDNESDAY MEALS

    //THURSDAY MEALS
    public  void thursdayMeals(String meal, String nutrition) {
//                Thursday breakfast
        mealType = todaysmealTv.getText().toString();
//                Toast.makeText(getApplicationContext(), pressedTextView, Toast.LENGTH_SHORT).show();

        if (pressedTextView.equalsIgnoreCase(mealtv1)) {

            if (mealType.equalsIgnoreCase("Thursday Breakfast")) {
                Cursor res2 = myDatabase.viewThurBreakfastMeals();
                if (res2.getCount() == 0) {
                    if (myDatabase.searchThurLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchThurSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                    }
                    else{
                    searchWedBreakfast(meal);
                    searchWedLunch(meal);
                    searchWedSupper(meal);
                    myDatabase.insertThurBreakfastData(meal, nutrition);
                    //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    firstMealTv.setText(meal);
                    firstMealNutrition.setText(nutrition);
                    myDatabase.close();}
                }
                else {

                    String currentMeal = firstMealTv.getText().toString();
                    if(currentMeal.equalsIgnoreCase(meal)){
                        Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                    }
                    else {
                        if (myDatabase.searchThurLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                            Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                        }
                        else if(myDatabase.searchThurSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                            Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                        }
                        else if (firstMealTv.getText().toString().trim().equalsIgnoreCase("")){
                            searchWedBreakfast(meal);
                            searchWedLunch(meal);
                            searchWedSupper(meal);
                            boolean isUpdated = myDatabase.updateThurBreakfastData(meal, nutrition, "1");
                            if (isUpdated) {
                                firstMealTv.setText(meal);
                                firstMealNutrition.setText(nutrition);
                            } else {
                                Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                        searchWedBreakfast(meal);
                        searchWedLunch(meal);
                        searchWedSupper(meal);
                        new AlertDialog.Builder(today_meal.this)
                        .setMessage(dialogMsgPt1 + currentMeal + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateThurBreakfastData(meal, nutrition, "1");
                    if (isUpdated) {
                        //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                        firstMealTv.setText(meal);
                        firstMealNutrition.setText(nutrition);
                    } else {
                        Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                    }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
            }
            //                //Thursday Lunch
            else if (mealType.equalsIgnoreCase("Thursday Lunch")) {
                Cursor res2 = myDatabase.viewThurLunchMeals();
                if (res2.getCount() == 0) {
                    if (myDatabase.searchThurBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchThurSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                    }
                    else{
                    searchWedBreakfast(meal);
                    searchWedLunch(meal);
                    searchWedSupper(meal);
                    myDatabase.insertThurLunchData(meal, nutrition);
                    //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    firstMealTv.setText(meal);
                    firstMealNutrition.setText(nutrition);
                    myDatabase.close();}
                }
            else {

                    String currentMeal = firstMealTv.getText().toString();
                    if(currentMeal.equalsIgnoreCase(meal)){
                        Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                    }
                    else {
                        if (myDatabase.searchThurBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                            Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                        }
                        else if(myDatabase.searchThurSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                            Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                        }
                        else if (firstMealTv.getText().toString().trim().equalsIgnoreCase("")){
                            searchWedBreakfast(meal);
                            searchWedLunch(meal);
                            searchWedSupper(meal);
                            boolean isUpdated = myDatabase.updateThurLunchData(meal, nutrition, "1");
                            if (isUpdated) {
                                firstMealTv.setText(meal);
                                firstMealNutrition.setText(nutrition);
                            } else {
                                Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                        searchWedBreakfast(meal);
                        searchWedLunch(meal);
                        searchWedSupper(meal);
                        new AlertDialog.Builder(today_meal.this)
                        .setMessage(dialogMsgPt1 + currentMeal + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateThurLunchData(meal, nutrition, "1");
                    if (isUpdated) {
                        //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                        firstMealTv.setText(meal);
                        firstMealNutrition.setText(nutrition);
                    } else {
                        Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                    }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
            }
            //                //Thursday Supper
            else if (mealType.equalsIgnoreCase("Thursday Supper")) {
                Cursor res2 = myDatabase.viewThurSupperMeals();
                if (res2.getCount() == 0) {
                    if (myDatabase.searchThurBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchThurLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                    }
                    else{
                    searchWedBreakfast(meal);
                    searchWedLunch(meal);
                    searchWedSupper(meal);
                    myDatabase.insertThurSupperData(meal, nutrition);
                    //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    firstMealTv.setText(meal);
                    firstMealNutrition.setText(nutrition);
                    myDatabase.close();}
                }
                else {

                    String currentMeal = firstMealTv.getText().toString();
                    if(currentMeal.equalsIgnoreCase(meal)){
                        Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                    }
                    else {
                        if (myDatabase.searchThurBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                            Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                        }
                        else if(myDatabase.searchThurLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                            Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                        }
                        else if (firstMealTv.getText().toString().trim().equalsIgnoreCase("")){
                            searchWedBreakfast(meal);
                            searchWedLunch(meal);
                            searchWedSupper(meal);
                            boolean isUpdated = myDatabase.updateThurSupperData(meal, nutrition, "1");
                            if (isUpdated) {
                                firstMealTv.setText(meal);
                                firstMealNutrition.setText(nutrition);
                            } else {
                                Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                        searchWedBreakfast(meal);
                        searchWedLunch(meal);
                        searchWedSupper(meal);
                        new AlertDialog.Builder(today_meal.this)
                        .setMessage(dialogMsgPt1 + currentMeal + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateThurSupperData(meal, nutrition, "1");
                    if (isUpdated) {
                        //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                        firstMealTv.setText(meal);
                        firstMealNutrition.setText(nutrition);
                    } else {
                        Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                    }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
            }
        }
        else if(pressedTextView.equalsIgnoreCase(mealtv2)){
            if (mealType.equalsIgnoreCase("Thursday Breakfast")) {
                Cursor res = myDatabase.viewThurBreakfastMeals();
            String firstmeal = firstMealTv.getText().toString();
            if (res.getCount() < 1 || firstmeal.trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), firstCell, Toast.LENGTH_SHORT).show();
                }
                else if(meal.equalsIgnoreCase(firstMealTv.getText().toString())){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                }
                else if (res.getCount() == 1) {
                    if (myDatabase.searchThurLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchThurSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                    }
                    else{
                    searchWedBreakfast(meal);
                    searchWedLunch(meal);
                    searchWedSupper(meal);
                    myDatabase.insertThurBreakfastData(meal, nutrition);
                    //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    secondMealTv.setText(meal);
                    secondMealNutrition.setText(nutrition);
                    myDatabase.close();}
                }else {

                    String currentMeal1 = firstMealTv.getText().toString();
                    String currentMeal2 = secondMealTv.getText().toString();
                    String currentMeal3 = thirdMealTv.getText().toString();
                    String currentMeal4 = fourthMealTv.getText().toString();
                    String currentMeal5 = fifthMealTv.getText().toString();

                    if(meal.equalsIgnoreCase(currentMeal1)||meal.equalsIgnoreCase(currentMeal2)||meal.equalsIgnoreCase(currentMeal3)||
                            meal.equalsIgnoreCase(currentMeal4)||meal.equalsIgnoreCase(currentMeal5)){
                        Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                    }
                    else {
                        if (myDatabase.searchThurLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                            Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                        }
                        else if(myDatabase.searchThurSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                            Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                        }
                        else if (secondMealTv.getText().toString().trim().equalsIgnoreCase("")){
                            searchWedBreakfast(meal);
                            searchWedLunch(meal);
                            searchWedSupper(meal);
                            boolean isUpdated = myDatabase.updateThurBreakfastData(meal, nutrition, "2");
                            if (isUpdated) {
                                secondMealTv.setText(meal);
                                secondMealNutrition.setText(nutrition);
                            } else {
                                Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                        searchWedBreakfast(meal);
                        searchWedLunch(meal);
                        searchWedSupper(meal);
                        new AlertDialog.Builder(today_meal.this)
                        .setMessage(dialogMsgPt1 + currentMeal2 + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateThurBreakfastData(meal, nutrition, "2");
                    if (isUpdated) {
                        //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                        secondMealTv.setText(meal);
                        secondMealNutrition.setText(nutrition);
                    } else {
                        Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                    }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
            }
            //                //Thursday Lunch
            else if (mealType.equalsIgnoreCase("Thursday Lunch")) {
                Cursor res = myDatabase.viewThurLunchMeals();
            String firstmeal = firstMealTv.getText().toString();
            if (res.getCount() < 1 || firstmeal.trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), firstCell, Toast.LENGTH_SHORT).show();
                }
                else if(meal.equalsIgnoreCase(firstMealTv.getText().toString())){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                }
                else if (res.getCount() == 1) {
                    if (myDatabase.searchThurBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchThurSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                    }
                    else{
                    searchWedBreakfast(meal);
                    searchWedLunch(meal);
                    searchWedSupper(meal);
                    myDatabase.insertThurLunchData(meal, nutrition);
                    //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    secondMealTv.setText(meal);
                    secondMealNutrition.setText(nutrition);
                    myDatabase.close();}
                }else {

                    String currentMeal1 = firstMealTv.getText().toString();
                    String currentMeal2 = secondMealTv.getText().toString();
                    String currentMeal3 = thirdMealTv.getText().toString();
                    String currentMeal4 = fourthMealTv.getText().toString();
                    String currentMeal5 = fifthMealTv.getText().toString();

                    if(meal.equalsIgnoreCase(currentMeal1)||meal.equalsIgnoreCase(currentMeal2)||meal.equalsIgnoreCase(currentMeal3)||
                            meal.equalsIgnoreCase(currentMeal4)||meal.equalsIgnoreCase(currentMeal5)){
                        Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                    }
                    else {
                        if (myDatabase.searchThurBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                            Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                        }
                        else if(myDatabase.searchThurSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                            Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                        }
                        else if (secondMealTv.getText().toString().trim().equalsIgnoreCase("")){
                            searchWedBreakfast(meal);
                            searchWedLunch(meal);
                            searchWedSupper(meal);
                            boolean isUpdated = myDatabase.updateThurLunchData(meal, nutrition, "2");
                            if (isUpdated) {
                                secondMealTv.setText(meal);
                                secondMealNutrition.setText(nutrition);
                            } else {
                                Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                        searchWedBreakfast(meal);
                        searchWedLunch(meal);
                        searchWedSupper(meal);
                        new AlertDialog.Builder(today_meal.this)
                        .setMessage(dialogMsgPt1 + currentMeal2 + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateThurLunchData(meal, nutrition, "2");
                    if (isUpdated) {
                        //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                        secondMealTv.setText(meal);
                        secondMealNutrition.setText(nutrition);
                    } else {
                        Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                    }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
            }
            //                //Thursday Supper
            else if (mealType.equalsIgnoreCase("Thursday Supper")) {
                Cursor res = myDatabase.viewThurSupperMeals();
            String firstmeal = firstMealTv.getText().toString();
            if (res.getCount() < 1 || firstmeal.trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), firstCell, Toast.LENGTH_SHORT).show();
                }
                else if(meal.equalsIgnoreCase(firstMealTv.getText().toString())){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                }
                else if (res.getCount() == 1) {
                    if (myDatabase.searchThurBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchThurLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                    }
                    else{
                    searchWedBreakfast(meal);
                    searchWedLunch(meal);
                    searchWedSupper(meal);
                    myDatabase.insertThurSupperData(meal, nutrition);
                    //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    secondMealTv.setText(meal);
                    secondMealNutrition.setText(nutrition);
                    myDatabase.close();}
                }else {

                    String currentMeal1 = firstMealTv.getText().toString();
                    String currentMeal2 = secondMealTv.getText().toString();
                    String currentMeal3 = thirdMealTv.getText().toString();
                    String currentMeal4 = fourthMealTv.getText().toString();
                    String currentMeal5 = fifthMealTv.getText().toString();

                    if(meal.equalsIgnoreCase(currentMeal1)||meal.equalsIgnoreCase(currentMeal2)||meal.equalsIgnoreCase(currentMeal3)||
                            meal.equalsIgnoreCase(currentMeal4)||meal.equalsIgnoreCase(currentMeal5)){
                        Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                    }
                    else {
                        if (myDatabase.searchThurBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                            Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                        }
                        else if(myDatabase.searchThurLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                            Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                        }
                        else if (secondMealTv.getText().toString().trim().equalsIgnoreCase("")){
                            searchWedBreakfast(meal);
                            searchWedLunch(meal);
                            searchWedSupper(meal);
                            boolean isUpdated = myDatabase.updateThurSupperData(meal, nutrition, "2");
                            if (isUpdated) {
                                secondMealTv.setText(meal);
                                secondMealNutrition.setText(nutrition);
                            } else {
                                Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                        searchWedBreakfast(meal);
                        searchWedLunch(meal);
                        searchWedSupper(meal);
                        new AlertDialog.Builder(today_meal.this)
                        .setMessage(dialogMsgPt1 + currentMeal2 + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateThurSupperData(meal, nutrition, "2");
                    if (isUpdated) {
                        //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                        secondMealTv.setText(meal);
                        secondMealNutrition.setText(nutrition);
                    } else {
                        Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                    }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
            }
        }
        else if(pressedTextView.equalsIgnoreCase(mealtv3)){
            if (mealType.equalsIgnoreCase("Thursday Breakfast")) {
                Cursor res = myDatabase.viewThurBreakfastMeals();
            String firstmeal = firstMealTv.getText().toString();
            if (res.getCount() < 1 || firstmeal.trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), firstCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 2||secondMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), secondCell, Toast.LENGTH_SHORT).show();
                }
                else if(meal.equalsIgnoreCase(firstMealTv.getText().toString())||meal.equalsIgnoreCase(secondMealTv.getText().toString())){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                }
                else if (res.getCount() == 2) {
                    if (myDatabase.searchThurLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchThurSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                    }
                    else{
                    searchWedBreakfast(meal);
                    searchWedLunch(meal);
                    searchWedSupper(meal);
                    myDatabase.insertThurBreakfastData(meal, nutrition);
                    //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    thirdMealTv.setText(meal);
                    thirdMealNutrition.setText(nutrition);
                    myDatabase.close();}
                } else {

                String currentMeal1 = firstMealTv.getText().toString();
                String currentMeal2 = secondMealTv.getText().toString();
                String currentMeal3 = thirdMealTv.getText().toString();
                String currentMeal4 = fourthMealTv.getText().toString();
                String currentMeal5 = fifthMealTv.getText().toString();

                if(meal.equalsIgnoreCase(currentMeal1)||meal.equalsIgnoreCase(currentMeal2)||meal.equalsIgnoreCase(currentMeal3)||
                        meal.equalsIgnoreCase(currentMeal4)||meal.equalsIgnoreCase(currentMeal5)){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                }
                else {
                    if (myDatabase.searchThurLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchThurSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                    }
                    else if (thirdMealTv.getText().toString().trim().equalsIgnoreCase("")){
                        searchWedBreakfast(meal);
                        searchWedLunch(meal);
                        searchWedSupper(meal);
                        boolean isUpdated = myDatabase.updateThurBreakfastData(meal, nutrition, "3");
                        if (isUpdated) {
                            thirdMealTv.setText(meal);
                            thirdMealNutrition.setText(nutrition);
                        } else {
                            Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                    searchWedBreakfast(meal);
                    searchWedLunch(meal);
                    searchWedSupper(meal);
                    new AlertDialog.Builder(today_meal.this)
                        .setMessage(dialogMsgPt1 + currentMeal3 + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateThurBreakfastData(meal, nutrition, "3");
                    if (isUpdated) {
                        //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                        thirdMealTv.setText(meal);
                        thirdMealNutrition.setText(nutrition);
                    } else {
                        Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                    }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
            }
            //                //Thursday Lunch
            else if (mealType.equalsIgnoreCase("Thursday Lunch")) {
                Cursor res = myDatabase.viewThurLunchMeals();
            String firstmeal = firstMealTv.getText().toString();
            if (res.getCount() < 1 || firstmeal.trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), firstCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 2||secondMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), secondCell, Toast.LENGTH_SHORT).show();
                }
                else if(meal.equalsIgnoreCase(firstMealTv.getText().toString())||meal.equalsIgnoreCase(secondMealTv.getText().toString())){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                }
                else if (res.getCount() == 2) {
                    if (myDatabase.searchThurBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchThurSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                    }
                    else{
                    searchWedBreakfast(meal);
                    searchWedLunch(meal);
                    searchWedSupper(meal);
                    myDatabase.insertThurLunchData(meal, nutrition);
                    //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    thirdMealTv.setText(meal);
                    thirdMealNutrition.setText(nutrition);
                    myDatabase.close();}
                } else {

                String currentMeal1 = firstMealTv.getText().toString();
                String currentMeal2 = secondMealTv.getText().toString();
                String currentMeal3 = thirdMealTv.getText().toString();
                String currentMeal4 = fourthMealTv.getText().toString();
                String currentMeal5 = fifthMealTv.getText().toString();

                if(meal.equalsIgnoreCase(currentMeal1)||meal.equalsIgnoreCase(currentMeal2)||meal.equalsIgnoreCase(currentMeal3)||
                        meal.equalsIgnoreCase(currentMeal4)||meal.equalsIgnoreCase(currentMeal5)){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                }
                else {
                    if (myDatabase.searchThurBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchThurSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                    }
                    else if (thirdMealTv.getText().toString().trim().equalsIgnoreCase("")){
                        searchWedBreakfast(meal);
                        searchWedLunch(meal);
                        searchWedSupper(meal);
                        boolean isUpdated = myDatabase.updateThurLunchData(meal, nutrition, "3");
                        if (isUpdated) {
                            thirdMealTv.setText(meal);
                            thirdMealNutrition.setText(nutrition);
                        } else {
                            Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                    searchWedBreakfast(meal);
                    searchWedLunch(meal);
                    searchWedSupper(meal);
                    new AlertDialog.Builder(today_meal.this)
                        .setMessage(dialogMsgPt1 + currentMeal3 + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateThurLunchData(meal, nutrition, "3");
                    if (isUpdated) {
                        //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                        thirdMealTv.setText(meal);
                        thirdMealNutrition.setText(nutrition);
                    } else {
                        Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                    }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
            }
            //                //Thursday Supper yuo
            else if (mealType.equalsIgnoreCase("Thursday Supper")) {
                Cursor res = myDatabase.viewThurSupperMeals();
            String firstmeal = firstMealTv.getText().toString();
            if (res.getCount() < 1 || firstmeal.trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), firstCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 2||secondMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), secondCell, Toast.LENGTH_SHORT).show();
                }
                else if(meal.equalsIgnoreCase(firstMealTv.getText().toString())||meal.equalsIgnoreCase(secondMealTv.getText().toString())){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                }
                else if (res.getCount() == 2) {
                    if (myDatabase.searchThurBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchThurLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                    }
                    else{
                    searchWedBreakfast(meal);
                    searchWedLunch(meal);
                    searchWedSupper(meal);
                    myDatabase.insertThurSupperData(meal, nutrition);
                    //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    thirdMealTv.setText(meal);
                    thirdMealNutrition.setText(nutrition);
                    myDatabase.close();}
                } else {

                String currentMeal1 = firstMealTv.getText().toString();
                String currentMeal2 = secondMealTv.getText().toString();
                String currentMeal3 = thirdMealTv.getText().toString();
                String currentMeal4 = fourthMealTv.getText().toString();
                String currentMeal5 = fifthMealTv.getText().toString();

                if(meal.equalsIgnoreCase(currentMeal1)||meal.equalsIgnoreCase(currentMeal2)||meal.equalsIgnoreCase(currentMeal3)||
                        meal.equalsIgnoreCase(currentMeal4)||meal.equalsIgnoreCase(currentMeal5)){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                }
                else {
                    if (myDatabase.searchThurBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchThurLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                    }
                    else if (thirdMealTv.getText().toString().trim().equalsIgnoreCase("")){
                        searchWedBreakfast(meal);
                        searchWedLunch(meal);
                        searchWedSupper(meal);
                        boolean isUpdated = myDatabase.updateThurSupperData(meal, nutrition, "3");
                        if (isUpdated) {
                            thirdMealTv.setText(meal);
                            thirdMealNutrition.setText(nutrition);
                        } else {
                            Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                    searchWedBreakfast(meal);
                    searchWedLunch(meal);
                    searchWedSupper(meal);
                    new AlertDialog.Builder(today_meal.this)
                        .setMessage(dialogMsgPt1 + currentMeal3 + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateThurSupperData(meal, nutrition, "3");
                    if (isUpdated) {
                        //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                        thirdMealTv.setText(meal);
                        thirdMealNutrition.setText(nutrition);
                    } else {
                        Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                    }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
            }
        }
        else if(pressedTextView.equalsIgnoreCase(mealtv4)){

            if (mealType.equalsIgnoreCase("Thursday Breakfast")) {
                Cursor res = myDatabase.viewThurBreakfastMeals();
            String firstmeal = firstMealTv.getText().toString();
            if (res.getCount() < 1 || firstmeal.trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), firstCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 2||secondMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), secondCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 3||thirdMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), thirdCell, Toast.LENGTH_SHORT).show();
                }
                else if(meal.equalsIgnoreCase(firstMealTv.getText().toString())||meal.equalsIgnoreCase(secondMealTv.getText().toString())
                        ||meal.equalsIgnoreCase(thirdMealTv.getText().toString())){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                }
                else if (res.getCount() == 3) {
                    if (myDatabase.searchThurLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchThurSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                    }
                    else{
                    searchWedBreakfast(meal);
                    searchWedLunch(meal);
                    searchWedSupper(meal);
                    myDatabase.insertThurBreakfastData(meal, nutrition);
                    //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    fourthMealTv.setText(meal);
                    fourthMealNutrition.setText(nutrition);
                    myDatabase.close();}
                }  else {

                    String currentMeal1 = firstMealTv.getText().toString();
                    String currentMeal2 = secondMealTv.getText().toString();
                    String currentMeal3 = thirdMealTv.getText().toString();
                    String currentMeal4 = fourthMealTv.getText().toString();
                    String currentMeal5 = fifthMealTv.getText().toString();

                    if(meal.equalsIgnoreCase(currentMeal1)||meal.equalsIgnoreCase(currentMeal2)||meal.equalsIgnoreCase(currentMeal3)||
                            meal.equalsIgnoreCase(currentMeal4)||meal.equalsIgnoreCase(currentMeal5)){
                        Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                    }
                    else {
                        if (myDatabase.searchThurLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                            Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                        }
                        else if(myDatabase.searchThurSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                            Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                        }
                        else if (fourthMealTv.getText().toString().trim().equalsIgnoreCase("")){
                            searchWedBreakfast(meal);
                            searchWedLunch(meal);
                            searchWedSupper(meal);
                            boolean isUpdated = myDatabase.updateThurBreakfastData(meal, nutrition, "4");
                            if (isUpdated) {
                                fourthMealTv.setText(meal);
                                fourthMealNutrition.setText(nutrition);
                            } else {
                                Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                        searchWedBreakfast(meal);
                        searchWedLunch(meal);
                        searchWedSupper(meal);
                        new AlertDialog.Builder(today_meal.this)
                                .setMessage(dialogMsgPt1 + currentMeal4 + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateThurBreakfastData(meal, nutrition, "4");
                    if (isUpdated) {
                        //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                        fourthMealTv.setText(meal);
                        fourthMealNutrition.setText(nutrition);
                    } else {
                        Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                    }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
            }
            //                //Thursday Lunch
            else if (mealType.equalsIgnoreCase("Thursday Lunch")) {
                Cursor res = myDatabase.viewThurLunchMeals();
            String firstmeal = firstMealTv.getText().toString();
            if (res.getCount() < 1 || firstmeal.trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), firstCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 2||secondMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), secondCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 3||thirdMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), thirdCell, Toast.LENGTH_SHORT).show();
                }
                else if(meal.equalsIgnoreCase(firstMealTv.getText().toString())||meal.equalsIgnoreCase(secondMealTv.getText().toString())
                        ||meal.equalsIgnoreCase(thirdMealTv.getText().toString())){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                }
                else if (res.getCount() == 3) {
                    if (myDatabase.searchThurBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchThurSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                    }
                    else{
                    searchWedBreakfast(meal);
                    searchWedLunch(meal);
                    searchWedSupper(meal);
                    myDatabase.insertThurLunchData(meal, nutrition);
                    //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    fourthMealTv.setText(meal);
                    fourthMealNutrition.setText(nutrition);
                    myDatabase.close();}
                }  else {

                    String currentMeal1 = firstMealTv.getText().toString();
                    String currentMeal2 = secondMealTv.getText().toString();
                    String currentMeal3 = thirdMealTv.getText().toString();
                    String currentMeal4 = fourthMealTv.getText().toString();
                    String currentMeal5 = fifthMealTv.getText().toString();

                    if(meal.equalsIgnoreCase(currentMeal1)||meal.equalsIgnoreCase(currentMeal2)||meal.equalsIgnoreCase(currentMeal3)||
                            meal.equalsIgnoreCase(currentMeal4)||meal.equalsIgnoreCase(currentMeal5)){
                        Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                    }
                    else {
                        if (myDatabase.searchThurBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                            Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                        }
                        else if(myDatabase.searchThurSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                            Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                        }
                        else if (fourthMealTv.getText().toString().trim().equalsIgnoreCase("")){
                            searchWedBreakfast(meal);
                            searchWedLunch(meal);
                            searchWedSupper(meal);
                            boolean isUpdated = myDatabase.updateThurLunchData(meal, nutrition, "4");
                            if (isUpdated) {
                                fourthMealTv.setText(meal);
                                fourthMealNutrition.setText(nutrition);
                            } else {
                                Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                        searchWedBreakfast(meal);
                        searchWedLunch(meal);
                        searchWedSupper(meal);
                        new AlertDialog.Builder(today_meal.this)
                                .setMessage(dialogMsgPt1 + currentMeal4 + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateThurLunchData(meal, nutrition, "4");
                    if (isUpdated) {
                        //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                        fourthMealTv.setText(meal);
                        fourthMealNutrition.setText(nutrition);
                    } else {
                        Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                    }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
            }
            //                //Thursday Supper
            else if (mealType.equalsIgnoreCase("Thursday Supper")) {
                Cursor res = myDatabase.viewThurSupperMeals();
            String firstmeal = firstMealTv.getText().toString();
            if (res.getCount() < 1 || firstmeal.trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), firstCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 2||secondMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), secondCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 3||thirdMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), thirdCell, Toast.LENGTH_SHORT).show();
                }
                else if(meal.equalsIgnoreCase(firstMealTv.getText().toString())||meal.equalsIgnoreCase(secondMealTv.getText().toString())
                        ||meal.equalsIgnoreCase(thirdMealTv.getText().toString())){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                }
                else if (res.getCount() == 3) {
                    if (myDatabase.searchThurBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchThurLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                    }
                    else{
                    searchWedBreakfast(meal);
                    searchWedLunch(meal);
                    searchWedSupper(meal);
                    myDatabase.insertThurSupperData(meal, nutrition);
                    //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    fourthMealTv.setText(meal);
                    fourthMealNutrition.setText(nutrition);
                    myDatabase.close();}
                }  else {

                    String currentMeal1 = firstMealTv.getText().toString();
                    String currentMeal2 = secondMealTv.getText().toString();
                    String currentMeal3 = thirdMealTv.getText().toString();
                    String currentMeal4 = fourthMealTv.getText().toString();
                    String currentMeal5 = fifthMealTv.getText().toString();

                    if(meal.equalsIgnoreCase(currentMeal1)||meal.equalsIgnoreCase(currentMeal2)||meal.equalsIgnoreCase(currentMeal3)||
                            meal.equalsIgnoreCase(currentMeal4)||meal.equalsIgnoreCase(currentMeal5)){
                        Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                    }
                    else {
                        if (myDatabase.searchThurBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                            Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                        }
                        else if(myDatabase.searchThurLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                            Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                        }
                        else if (fourthMealTv.getText().toString().trim().equalsIgnoreCase("")){
                            searchWedBreakfast(meal);
                            searchWedLunch(meal);
                            searchWedSupper(meal);
                            boolean isUpdated = myDatabase.updateThurSupperData(meal, nutrition, "4");
                            if (isUpdated) {
                                fourthMealTv.setText(meal);
                                fourthMealNutrition.setText(nutrition);
                            } else {
                                Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                        searchWedBreakfast(meal);
                        searchWedLunch(meal);
                        searchWedSupper(meal);
                        new AlertDialog.Builder(today_meal.this)
                                .setMessage(dialogMsgPt1 + currentMeal4 + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateThurSupperData(meal, nutrition, "4");
                    if (isUpdated) {
                        //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                        fourthMealTv.setText(meal);
                        fourthMealNutrition.setText(nutrition);
                    } else {
                        Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                    }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
            }
        }

        else if(pressedTextView.equalsIgnoreCase(mealtv5)){
            if (mealType.equalsIgnoreCase("Thursday Breakfast")) {
                Cursor res = myDatabase.viewThurBreakfastMeals();
            String firstmeal = firstMealTv.getText().toString();
            if (res.getCount() < 1 || firstmeal.trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), firstCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 2||secondMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), secondCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 3||thirdMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), thirdCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 4||fourthMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), fourthCell, Toast.LENGTH_SHORT).show();
                }
                else if(meal.equalsIgnoreCase(firstMealTv.getText().toString())||meal.equalsIgnoreCase(secondMealTv.getText().toString())
                        ||meal.equalsIgnoreCase(thirdMealTv.getText().toString())||meal.equalsIgnoreCase(fourthMealTv.getText().toString())){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                }
                else if (res.getCount() == 4) {
                    if (myDatabase.searchThurLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchThurSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                    }
                    else{
                    searchWedBreakfast(meal);
                    searchWedLunch(meal);
                    searchWedSupper(meal);
                    myDatabase.insertThurBreakfastData(meal, nutrition);
                    //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    fifthMealTv.setText(meal);
                    fifthMealNutrition.setText(nutrition);
                    myDatabase.close();}
                }  else {

                String currentMeal1 = firstMealTv.getText().toString();
                String currentMeal2 = secondMealTv.getText().toString();
                String currentMeal3 = thirdMealTv.getText().toString();
                String currentMeal4 = fourthMealTv.getText().toString();
                String currentMeal5 = fifthMealTv.getText().toString();

                if(meal.equalsIgnoreCase(currentMeal1)||meal.equalsIgnoreCase(currentMeal2)||meal.equalsIgnoreCase(currentMeal3)||
                        meal.equalsIgnoreCase(currentMeal4)||meal.equalsIgnoreCase(currentMeal5)){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                }
                else {
                    if (myDatabase.searchThurLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchThurSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                    }
                    else if (fifthMealTv.getText().toString().trim().equalsIgnoreCase("")){
                        searchWedBreakfast(meal);
                        searchWedLunch(meal);
                        searchWedSupper(meal);
                        boolean isUpdated = myDatabase.updateThurBreakfastData(meal, nutrition, "5");
                        if (isUpdated) {
                            fifthMealTv.setText(meal);
                            fifthMealNutrition.setText(nutrition);
                        } else {
                            Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                    searchWedBreakfast(meal);
                    searchWedLunch(meal);
                    searchWedSupper(meal);
                    new AlertDialog.Builder(today_meal.this)
                            .setMessage(dialogMsgPt1 + currentMeal5 + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateThurBreakfastData(meal, nutrition, "5");
                    if (isUpdated) {
                        //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                        fifthMealTv.setText(meal);
                        fifthMealNutrition.setText(nutrition);
                    } else {
                        Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                    }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
            }
            //                //Thursday Lunch
            else if (mealType.equalsIgnoreCase("Thursday Lunch")) {
                Cursor res = myDatabase.viewThurLunchMeals();
            String firstmeal = firstMealTv.getText().toString();
            if (res.getCount() < 1 || firstmeal.trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), firstCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 2||secondMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), secondCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 3||thirdMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), thirdCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 4||fourthMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), fourthCell, Toast.LENGTH_SHORT).show();
                }
                else if(meal.equalsIgnoreCase(firstMealTv.getText().toString())||meal.equalsIgnoreCase(secondMealTv.getText().toString())
                        ||meal.equalsIgnoreCase(thirdMealTv.getText().toString())||meal.equalsIgnoreCase(fourthMealTv.getText().toString())){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                }
                else if (res.getCount() == 4) {
                    if (myDatabase.searchThurBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchThurSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                    }
                    else{
                    searchWedBreakfast(meal);
                    searchWedLunch(meal);
                    searchWedSupper(meal);
                    myDatabase.insertThurLunchData(meal, nutrition);
                    //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    fifthMealTv.setText(meal);
                    fifthMealNutrition.setText(nutrition);
                    myDatabase.close();}
                }  else {

                String currentMeal1 = firstMealTv.getText().toString();
                String currentMeal2 = secondMealTv.getText().toString();
                String currentMeal3 = thirdMealTv.getText().toString();
                String currentMeal4 = fourthMealTv.getText().toString();
                String currentMeal5 = fifthMealTv.getText().toString();

                if(meal.equalsIgnoreCase(currentMeal1)||meal.equalsIgnoreCase(currentMeal2)||meal.equalsIgnoreCase(currentMeal3)||
                        meal.equalsIgnoreCase(currentMeal4)||meal.equalsIgnoreCase(currentMeal5)){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                }
                else {
                    if (myDatabase.searchThurBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchThurSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                    }
                    else if (fifthMealTv.getText().toString().trim().equalsIgnoreCase("")){
                        searchWedBreakfast(meal);
                        searchWedLunch(meal);
                        searchWedSupper(meal);
                        boolean isUpdated = myDatabase.updateThurLunchData(meal, nutrition, "5");
                        if (isUpdated) {
                            fifthMealTv.setText(meal);
                            fifthMealNutrition.setText(nutrition);
                        } else {
                            Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                    searchWedBreakfast(meal);
                    searchWedLunch(meal);
                    searchWedSupper(meal);
                    new AlertDialog.Builder(today_meal.this)
                            .setMessage(dialogMsgPt1 + currentMeal5 + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateThurLunchData(meal, nutrition, "5");
                    if (isUpdated) {
                        //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                        fifthMealTv.setText(meal);
                        fifthMealNutrition.setText(nutrition);
                    } else {
                        Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                    }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
            }
            //                //Thursday Supper
            else if (mealType.equalsIgnoreCase("Thursday Supper")) {
                Cursor res = myDatabase.viewThurSupperMeals();
            String firstmeal = firstMealTv.getText().toString();
            if (res.getCount() < 1 || firstmeal.trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), firstCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 2||secondMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), secondCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 3||thirdMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), thirdCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 4||fourthMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), fourthCell, Toast.LENGTH_SHORT).show();
                }
                else if(meal.equalsIgnoreCase(firstMealTv.getText().toString())||meal.equalsIgnoreCase(secondMealTv.getText().toString())
                        ||meal.equalsIgnoreCase(thirdMealTv.getText().toString())||meal.equalsIgnoreCase(fourthMealTv.getText().toString())){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                }
                else if (res.getCount() == 4) {
                    if (myDatabase.searchThurBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchThurLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                    }
                    else{
                    searchWedBreakfast(meal);
                    searchWedLunch(meal);
                    searchWedSupper(meal);
                    myDatabase.insertThurSupperData(meal, nutrition);
                    //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    fifthMealTv.setText(meal);
                    fifthMealNutrition.setText(nutrition);
                    myDatabase.close();}
                }  else {

                String currentMeal1 = firstMealTv.getText().toString();
                String currentMeal2 = secondMealTv.getText().toString();
                String currentMeal3 = thirdMealTv.getText().toString();
                String currentMeal4 = fourthMealTv.getText().toString();
                String currentMeal5 = fifthMealTv.getText().toString();

                if(meal.equalsIgnoreCase(currentMeal1)||meal.equalsIgnoreCase(currentMeal2)||meal.equalsIgnoreCase(currentMeal3)||
                        meal.equalsIgnoreCase(currentMeal4)||meal.equalsIgnoreCase(currentMeal5)){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                }
                else {
                    if (myDatabase.searchThurBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchThurLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                    }
                    else if (fifthMealTv.getText().toString().trim().equalsIgnoreCase("")){
                        searchWedBreakfast(meal);
                        searchWedLunch(meal);
                        searchWedSupper(meal);
                        boolean isUpdated = myDatabase.updateThurSupperData(meal, nutrition, "5");
                        if (isUpdated) {
                            fifthMealTv.setText(meal);
                            fifthMealNutrition.setText(nutrition);
                        } else {
                            Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                    searchWedBreakfast(meal);
                    searchWedLunch(meal);
                    searchWedSupper(meal);
                    new AlertDialog.Builder(today_meal.this)
                            .setMessage(dialogMsgPt1 + currentMeal5 + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateThurSupperData(meal, nutrition, "5");
                    if (isUpdated) {
                        //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                        fifthMealTv.setText(meal);
                        fifthMealNutrition.setText(nutrition);
                    } else {
                        Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                    }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
            }
        }

    }//END OF THURSDAY MEALS

    //FRIDAY MEALS
    public  void fridayMeals(String meal, String nutrition) {
//                Friday breakfast
        mealType = todaysmealTv.getText().toString();
//                Toast.makeText(getApplicationContext(), pressedTextView, Toast.LENGTH_SHORT).show();

        if (pressedTextView.equalsIgnoreCase(mealtv1)) {

            if (mealType.equalsIgnoreCase("Friday Breakfast")) {
                Cursor res2 = myDatabase.viewFriBreakfastMeals();
                if (res2.getCount() == 0) {
                    if (myDatabase.searchFriLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchFriSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                    }
                    else{
                    searchThurBreakfast(meal);
                    searchThurLunch(meal);
                    searchThurSupper(meal);
                    myDatabase.insertFriBreakfastData(meal, nutrition);
                    //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    firstMealTv.setText(meal);
                    firstMealNutrition.setText(nutrition);
                    myDatabase.close();}
                }
                else {

                    String currentMeal = firstMealTv.getText().toString();
                    if(currentMeal.equalsIgnoreCase(meal)){
                        Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                    }
                    else {
                        if (myDatabase.searchFriLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                            Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                        }
                        else if(myDatabase.searchFriSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                            Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                        }
                        else if (firstMealTv.getText().toString().trim().equalsIgnoreCase("")){
                            searchThurBreakfast(meal);
                            searchThurLunch(meal);
                            searchThurSupper(meal);
                            boolean isUpdated = myDatabase.updateFriBreakfastData(meal, nutrition, "1");
                            if (isUpdated) {
                                firstMealTv.setText(meal);
                                firstMealNutrition.setText(nutrition);
                            } else {
                                Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                        searchThurBreakfast(meal);
                        searchThurLunch(meal);
                        searchThurSupper(meal);
                        new AlertDialog.Builder(today_meal.this)
                        .setMessage(dialogMsgPt1 + currentMeal + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateFriBreakfastData(meal, nutrition, "1");
                    if (isUpdated) {
                        //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                        firstMealTv.setText(meal);
                        firstMealNutrition.setText(nutrition);
                    } else {
                        Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                    }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
            }
            //                //Friday Lunch
            else if (mealType.equalsIgnoreCase("Friday Lunch")) {
                Cursor res2 = myDatabase.viewFriLunchMeals();
                if (res2.getCount() == 0) {
                    if (myDatabase.searchFriBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchFriSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                    }
                    else{
                    searchThurBreakfast(meal);
                    searchThurLunch(meal);
                    searchThurSupper(meal);
                    myDatabase.insertFriLunchData(meal, nutrition);
                    //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    firstMealTv.setText(meal);
                    firstMealNutrition.setText(nutrition);
                    myDatabase.close();}
                }
                else {

                    String currentMeal = firstMealTv.getText().toString();
                    if(currentMeal.equalsIgnoreCase(meal)){
                        Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                    }
                    else {
                        if (myDatabase.searchFriBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                            Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                        }
                        else if(myDatabase.searchFriSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                            Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                        }
                        else if (firstMealTv.getText().toString().trim().equalsIgnoreCase("")){
                            searchThurBreakfast(meal);
                            searchThurLunch(meal);
                            searchThurSupper(meal);
                            boolean isUpdated = myDatabase.updateFriLunchData(meal, nutrition, "1");
                            if (isUpdated) {
                                firstMealTv.setText(meal);
                                firstMealNutrition.setText(nutrition);
                            } else {
                                Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                        searchThurBreakfast(meal);
                        searchThurLunch(meal);
                        searchThurSupper(meal);
                        new AlertDialog.Builder(today_meal.this)
                        .setMessage(dialogMsgPt1 + currentMeal + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateFriLunchData(meal, nutrition, "1");
                    if (isUpdated) {
                        //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                        firstMealTv.setText(meal);
                        firstMealNutrition.setText(nutrition);
                    } else {
                        Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                    }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
            }
            //                //Friday Supper
            else if (mealType.equalsIgnoreCase("Friday Supper")) {
                Cursor res2 = myDatabase.viewFriSupperMeals();
                if (res2.getCount() == 0) {
                    if (myDatabase.searchFriBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchFriLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                    }
                    else{
                    searchThurBreakfast(meal);
                    searchThurLunch(meal);
                    searchThurSupper(meal);
                    myDatabase.insertFriSupperData(meal, nutrition);
                    //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    firstMealTv.setText(meal);
                    firstMealNutrition.setText(nutrition);
                    myDatabase.close();}
                }
                else {

                    String currentMeal = firstMealTv.getText().toString();
                    if(currentMeal.equalsIgnoreCase(meal)){
                        Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                    }
                    else {
                        if (myDatabase.searchFriBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                            Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                        }
                        else if(myDatabase.searchFriLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                            Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                        }
                        else if (firstMealTv.getText().toString().trim().equalsIgnoreCase("")){
                            searchThurBreakfast(meal);
                            searchThurLunch(meal);
                            searchThurSupper(meal);
                            boolean isUpdated = myDatabase.updateFriSupperData(meal, nutrition, "1");
                            if (isUpdated) {
                                firstMealTv.setText(meal);
                                firstMealNutrition.setText(nutrition);
                            } else {
                                Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                        searchThurBreakfast(meal);
                        searchThurLunch(meal);
                        searchThurSupper(meal);
                        new AlertDialog.Builder(today_meal.this)
                        .setMessage(dialogMsgPt1 + currentMeal + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateFriSupperData(meal, nutrition, "1");
                    if (isUpdated) {
                        //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                        firstMealTv.setText(meal);
                        firstMealNutrition.setText(nutrition);
                    } else {
                        Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                    }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
            }
        }
        else if(pressedTextView.equalsIgnoreCase(mealtv2)){
            if (mealType.equalsIgnoreCase("Friday Breakfast")) {
                Cursor res = myDatabase.viewFriBreakfastMeals();
            String firstmeal = firstMealTv.getText().toString();
            if (res.getCount() < 1 || firstmeal.trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), firstCell, Toast.LENGTH_SHORT).show();
                }
                else if(meal.equalsIgnoreCase(firstMealTv.getText().toString())){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                }
                else if (res.getCount() == 1) {
                    if (myDatabase.searchFriLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchFriSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                    }
                    else{
                    searchThurBreakfast(meal);
                    searchThurLunch(meal);
                    searchThurSupper(meal);
                    myDatabase.insertFriBreakfastData(meal, nutrition);
                    //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    secondMealTv.setText(meal);
                    secondMealNutrition.setText(nutrition);
                    myDatabase.close();}
                }else {

                    String currentMeal1 = firstMealTv.getText().toString();
                    String currentMeal2 = secondMealTv.getText().toString();
                    String currentMeal3 = thirdMealTv.getText().toString();
                    String currentMeal4 = fourthMealTv.getText().toString();
                    String currentMeal5 = fifthMealTv.getText().toString();

                    if(meal.equalsIgnoreCase(currentMeal1)||meal.equalsIgnoreCase(currentMeal2)||meal.equalsIgnoreCase(currentMeal3)||
                            meal.equalsIgnoreCase(currentMeal4)||meal.equalsIgnoreCase(currentMeal5)){
                        Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                    }
                    else {
                        if (myDatabase.searchFriLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                            Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                        }
                        else if(myDatabase.searchFriSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                            Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                        }
                        else if (secondMealTv.getText().toString().trim().equalsIgnoreCase("")){
                            searchThurBreakfast(meal);
                            searchThurLunch(meal);
                            searchThurSupper(meal);
                            boolean isUpdated = myDatabase.updateFriBreakfastData(meal, nutrition, "2");
                            if (isUpdated) {
                                secondMealTv.setText(meal);
                                secondMealNutrition.setText(nutrition);
                            } else {
                                Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                        searchThurBreakfast(meal);
                        searchThurLunch(meal);
                        searchThurSupper(meal);
                        new AlertDialog.Builder(today_meal.this)
                        .setMessage(dialogMsgPt1 + currentMeal2 + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateFriBreakfastData(meal, nutrition, "2");
                    if (isUpdated) {
                        //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                        secondMealTv.setText(meal);
                        secondMealNutrition.setText(nutrition);
                    } else {
                        Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                    }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
            }
            //                //Friday Lunch
            else if (mealType.equalsIgnoreCase("Friday Lunch")) {
                Cursor res = myDatabase.viewFriLunchMeals();
            String firstmeal = firstMealTv.getText().toString();
            if (res.getCount() < 1 || firstmeal.trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), firstCell, Toast.LENGTH_SHORT).show();
                }
                else if(meal.equalsIgnoreCase(firstMealTv.getText().toString())){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                }
                else if (res.getCount() == 1) {
                    if (myDatabase.searchFriBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchFriSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                    }
                    else{
                    searchThurBreakfast(meal);
                    searchThurLunch(meal);
                    searchThurSupper(meal);
                    myDatabase.insertFriLunchData(meal, nutrition);
                    //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    secondMealTv.setText(meal);
                    secondMealNutrition.setText(nutrition);
                    myDatabase.close();}
                }else {

                    String currentMeal1 = firstMealTv.getText().toString();
                    String currentMeal2 = secondMealTv.getText().toString();
                    String currentMeal3 = thirdMealTv.getText().toString();
                    String currentMeal4 = fourthMealTv.getText().toString();
                    String currentMeal5 = fifthMealTv.getText().toString();

                    if(meal.equalsIgnoreCase(currentMeal1)||meal.equalsIgnoreCase(currentMeal2)||meal.equalsIgnoreCase(currentMeal3)||
                            meal.equalsIgnoreCase(currentMeal4)||meal.equalsIgnoreCase(currentMeal5)){
                        Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                    }
                    else {
                        if (myDatabase.searchFriBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                            Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                        }
                        else if(myDatabase.searchFriSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                            Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                        }
                        else if (secondMealTv.getText().toString().trim().equalsIgnoreCase("")){
                            searchThurBreakfast(meal);
                            searchThurLunch(meal);
                            searchThurSupper(meal);
                            boolean isUpdated = myDatabase.updateFriLunchData(meal, nutrition, "2");
                            if (isUpdated) {
                                secondMealTv.setText(meal);
                                secondMealNutrition.setText(nutrition);
                            } else {
                                Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                        searchThurBreakfast(meal);
                        searchThurLunch(meal);
                        searchThurSupper(meal);
                        new AlertDialog.Builder(today_meal.this)
                        .setMessage(dialogMsgPt1 + currentMeal2 + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateFriLunchData(meal, nutrition, "2");
                    if (isUpdated) {
                        //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                        secondMealTv.setText(meal);
                        secondMealNutrition.setText(nutrition);
                    } else {
                        Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                    }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
            }
            //                //Friday Supper
            else if (mealType.equalsIgnoreCase("Friday Supper")) {
                Cursor res = myDatabase.viewFriSupperMeals();
            String firstmeal = firstMealTv.getText().toString();
            if (res.getCount() < 1 || firstmeal.trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), firstCell, Toast.LENGTH_SHORT).show();
                }
                else if(meal.equalsIgnoreCase(firstMealTv.getText().toString())){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                }
                else if (res.getCount() == 1) {
                    if (myDatabase.searchFriBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchFriLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                    }
                    else{
                    searchThurBreakfast(meal);
                    searchThurLunch(meal);
                    searchThurSupper(meal);
                    myDatabase.insertFriSupperData(meal, nutrition);
                    //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    secondMealTv.setText(meal);
                    secondMealNutrition.setText(nutrition);
                    myDatabase.close();}
                }else {

                    String currentMeal1 = firstMealTv.getText().toString();
                    String currentMeal2 = secondMealTv.getText().toString();
                    String currentMeal3 = thirdMealTv.getText().toString();
                    String currentMeal4 = fourthMealTv.getText().toString();
                    String currentMeal5 = fifthMealTv.getText().toString();

                    if(meal.equalsIgnoreCase(currentMeal1)||meal.equalsIgnoreCase(currentMeal2)||meal.equalsIgnoreCase(currentMeal3)||
                            meal.equalsIgnoreCase(currentMeal4)||meal.equalsIgnoreCase(currentMeal5)){
                        Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                    }
                    else {
                        if (myDatabase.searchFriBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                            Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                        }
                        else if(myDatabase.searchFriLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                            Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                        }
                        else if (secondMealTv.getText().toString().trim().equalsIgnoreCase("")){
                            searchThurBreakfast(meal);
                            searchThurLunch(meal);
                            searchThurSupper(meal);
                            boolean isUpdated = myDatabase.updateFriSupperData(meal, nutrition, "2");
                            if (isUpdated) {
                                secondMealTv.setText(meal);
                                secondMealNutrition.setText(nutrition);
                            } else {
                                Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                        searchThurBreakfast(meal);
                        searchThurLunch(meal);
                        searchThurSupper(meal);
                        new AlertDialog.Builder(today_meal.this)
                        .setMessage(dialogMsgPt1 + currentMeal2 + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateFriSupperData(meal, nutrition, "2");
                    if (isUpdated) {
                        //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                        secondMealTv.setText(meal);
                        secondMealNutrition.setText(nutrition);
                    } else {
                        Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                    }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
            }
        }
        else if(pressedTextView.equalsIgnoreCase(mealtv3)){
            if (mealType.equalsIgnoreCase("Friday Breakfast")) {
                Cursor res = myDatabase.viewFriBreakfastMeals();
            String firstmeal = firstMealTv.getText().toString();
            if (res.getCount() < 1 || firstmeal.trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), firstCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 2||secondMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), secondCell, Toast.LENGTH_SHORT).show();
                }
                else if(meal.equalsIgnoreCase(firstMealTv.getText().toString())||meal.equalsIgnoreCase(secondMealTv.getText().toString())){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                }
                else if (res.getCount() == 2) {
                    if (myDatabase.searchFriLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchFriSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                    }
                    else{
                    searchThurBreakfast(meal);
                    searchThurLunch(meal);
                    searchThurSupper(meal);
                    myDatabase.insertFriBreakfastData(meal, nutrition);
                    //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    thirdMealTv.setText(meal);
                    thirdMealNutrition.setText(nutrition);
                    myDatabase.close();}
                } else {

                String currentMeal1 = firstMealTv.getText().toString();
                String currentMeal2 = secondMealTv.getText().toString();
                String currentMeal3 = thirdMealTv.getText().toString();
                String currentMeal4 = fourthMealTv.getText().toString();
                String currentMeal5 = fifthMealTv.getText().toString();

                if(meal.equalsIgnoreCase(currentMeal1)||meal.equalsIgnoreCase(currentMeal2)||meal.equalsIgnoreCase(currentMeal3)||
                        meal.equalsIgnoreCase(currentMeal4)||meal.equalsIgnoreCase(currentMeal5)){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                }
                else {
                    if (myDatabase.searchFriLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchFriSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                    }
                    else if (thirdMealTv.getText().toString().trim().equalsIgnoreCase("")){
                        searchFriBreakfast(meal);
                        searchFriLunch(meal);
                        searchFriSupper(meal);
                        boolean isUpdated = myDatabase.updateFriBreakfastData(meal, nutrition, "3");
                        if (isUpdated) {
                            thirdMealTv.setText(meal);
                            thirdMealNutrition.setText(nutrition);
                        } else {
                            Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                    searchThurBreakfast(meal);
                    searchThurLunch(meal);
                    searchThurSupper(meal);
                    new AlertDialog.Builder(today_meal.this)
                        .setMessage(dialogMsgPt1 + currentMeal3 + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateFriBreakfastData(meal, nutrition, "3");
                    if (isUpdated) {
                        //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                        thirdMealTv.setText(meal);
                        thirdMealNutrition.setText(nutrition);
                    } else {
                        Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                    }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
            }
            //                //Friday Lunch
            else if (mealType.equalsIgnoreCase("Friday Lunch")) {
                Cursor res = myDatabase.viewFriLunchMeals();
            String firstmeal = firstMealTv.getText().toString();
            if (res.getCount() < 1 || firstmeal.trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), firstCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 2||secondMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), secondCell, Toast.LENGTH_SHORT).show();
                }
                else if(meal.equalsIgnoreCase(firstMealTv.getText().toString())||meal.equalsIgnoreCase(secondMealTv.getText().toString())){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                }
                else if (res.getCount() == 2) {
                    if (myDatabase.searchFriBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchFriSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                    }
                    else{
                    searchThurBreakfast(meal);
                    searchThurLunch(meal);
                    searchThurSupper(meal);
                    myDatabase.insertFriLunchData(meal, nutrition);
                    //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    thirdMealTv.setText(meal);
                    thirdMealNutrition.setText(nutrition);
                    myDatabase.close();}
                } else {

                String currentMeal1 = firstMealTv.getText().toString();
                String currentMeal2 = secondMealTv.getText().toString();
                String currentMeal3 = thirdMealTv.getText().toString();
                String currentMeal4 = fourthMealTv.getText().toString();
                String currentMeal5 = fifthMealTv.getText().toString();

                if(meal.equalsIgnoreCase(currentMeal1)||meal.equalsIgnoreCase(currentMeal2)||meal.equalsIgnoreCase(currentMeal3)||
                        meal.equalsIgnoreCase(currentMeal4)||meal.equalsIgnoreCase(currentMeal5)){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                }
                else {
                    if (myDatabase.searchFriBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchFriSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                    }
                    else if (thirdMealTv.getText().toString().trim().equalsIgnoreCase("")){
                        searchThurBreakfast(meal);
                        searchThurLunch(meal);
                        searchThurSupper(meal);
                        boolean isUpdated = myDatabase.updateFriLunchData(meal, nutrition, "3");
                        if (isUpdated) {
                            thirdMealTv.setText(meal);
                            thirdMealNutrition.setText(nutrition);
                        } else {
                            Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                    searchThurBreakfast(meal);
                    searchThurLunch(meal);
                    searchThurSupper(meal);
                    new AlertDialog.Builder(today_meal.this)
                        .setMessage(dialogMsgPt1 + currentMeal3 + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateFriLunchData(meal, nutrition, "3");
                    if (isUpdated) {
                        //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                        thirdMealTv.setText(meal);
                        thirdMealNutrition.setText(nutrition);
                    } else {
                        Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                    }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
            }
            //                //Friday Supper yuo
            else if (mealType.equalsIgnoreCase("Friday Supper")) {
                Cursor res = myDatabase.viewFriSupperMeals();
            String firstmeal = firstMealTv.getText().toString();
            if (res.getCount() < 1 || firstmeal.trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), firstCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 2||secondMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), secondCell, Toast.LENGTH_SHORT).show();
                }
                else if(meal.equalsIgnoreCase(firstMealTv.getText().toString())||meal.equalsIgnoreCase(secondMealTv.getText().toString())){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                }
                else if (res.getCount() == 2) {
                    if (myDatabase.searchFriBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchFriLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                    }
                    else{
                    searchThurBreakfast(meal);
                    searchThurLunch(meal);
                    searchThurSupper(meal);
                    myDatabase.insertFriSupperData(meal, nutrition);
                    //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    thirdMealTv.setText(meal);
                    thirdMealNutrition.setText(nutrition);
                    myDatabase.close();}
                } else {

                String currentMeal1 = firstMealTv.getText().toString();
                String currentMeal2 = secondMealTv.getText().toString();
                String currentMeal3 = thirdMealTv.getText().toString();
                String currentMeal4 = fourthMealTv.getText().toString();
                String currentMeal5 = fifthMealTv.getText().toString();

                if(meal.equalsIgnoreCase(currentMeal1)||meal.equalsIgnoreCase(currentMeal2)||meal.equalsIgnoreCase(currentMeal3)||
                        meal.equalsIgnoreCase(currentMeal4)||meal.equalsIgnoreCase(currentMeal5)){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                }
                else {
                    if (myDatabase.searchFriBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchFriLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                    }
                    else if (thirdMealTv.getText().toString().trim().equalsIgnoreCase("")){
                        searchThurBreakfast(meal);
                        searchThurLunch(meal);
                        searchThurSupper(meal);
                        boolean isUpdated = myDatabase.updateFriSupperData(meal, nutrition, "3");
                        if (isUpdated) {
                            thirdMealTv.setText(meal);
                            thirdMealNutrition.setText(nutrition);
                        } else {
                            Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                    searchThurBreakfast(meal);
                    searchThurLunch(meal);
                    searchThurSupper(meal);
                    new AlertDialog.Builder(today_meal.this)
                        .setMessage(dialogMsgPt1 + currentMeal3 + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateFriSupperData(meal, nutrition, "3");
                    if (isUpdated) {
                        //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                        thirdMealTv.setText(meal);
                        thirdMealNutrition.setText(nutrition);
                    } else {
                        Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                    }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
            }
        }
        else if(pressedTextView.equalsIgnoreCase(mealtv4)){

            if (mealType.equalsIgnoreCase("Friday Breakfast")) {
                Cursor res = myDatabase.viewFriBreakfastMeals();
            String firstmeal = firstMealTv.getText().toString();
            if (res.getCount() < 1 || firstmeal.trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), firstCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 2||secondMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), secondCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 3||thirdMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), thirdCell, Toast.LENGTH_SHORT).show();
                }
                else if(meal.equalsIgnoreCase(firstMealTv.getText().toString())||meal.equalsIgnoreCase(secondMealTv.getText().toString())
                        ||meal.equalsIgnoreCase(thirdMealTv.getText().toString())){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                }
                else if (res.getCount() == 3) {
                    if (myDatabase.searchFriLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchFriSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                    }
                    else{
                    searchThurBreakfast(meal);
                    searchThurLunch(meal);
                    searchThurSupper(meal);
                    myDatabase.insertFriBreakfastData(meal, nutrition);
                    //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    fourthMealTv.setText(meal);
                    fourthMealNutrition.setText(nutrition);
                    myDatabase.close();}
                }  else {

                    String currentMeal1 = firstMealTv.getText().toString();
                    String currentMeal2 = secondMealTv.getText().toString();
                    String currentMeal3 = thirdMealTv.getText().toString();
                    String currentMeal4 = fourthMealTv.getText().toString();
                    String currentMeal5 = fifthMealTv.getText().toString();

                    if(meal.equalsIgnoreCase(currentMeal1)||meal.equalsIgnoreCase(currentMeal2)||meal.equalsIgnoreCase(currentMeal3)||
                            meal.equalsIgnoreCase(currentMeal4)||meal.equalsIgnoreCase(currentMeal5)){
                        Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                    }
                    else {
                        if (myDatabase.searchFriLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                            Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                        }
                        else if(myDatabase.searchFriSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                            Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                        }
                        else if (fourthMealTv.getText().toString().trim().equalsIgnoreCase("")){
                            searchThurBreakfast(meal);
                            searchThurLunch(meal);
                            searchThurSupper(meal);
                            boolean isUpdated = myDatabase.updateFriBreakfastData(meal, nutrition, "4");
                            if (isUpdated) {
                                fourthMealTv.setText(meal);
                                fourthMealNutrition.setText(nutrition);
                            } else {
                                Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                        searchThurBreakfast(meal);
                        searchThurLunch(meal);
                        searchThurSupper(meal);
                        new AlertDialog.Builder(today_meal.this)
                                .setMessage(dialogMsgPt1 + currentMeal4 + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateFriBreakfastData(meal, nutrition, "4");
                    if (isUpdated) {
                        //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                        fourthMealTv.setText(meal);
                        fourthMealNutrition.setText(nutrition);
                    } else {
                        Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                    }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
            }
            //                //Friday Lunch
            else if (mealType.equalsIgnoreCase("Friday Lunch")) {
                Cursor res = myDatabase.viewFriLunchMeals();
            String firstmeal = firstMealTv.getText().toString();
            if (res.getCount() < 1 || firstmeal.trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), firstCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 2||secondMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), secondCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 3||thirdMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), thirdCell, Toast.LENGTH_SHORT).show();
                }
                else if(meal.equalsIgnoreCase(firstMealTv.getText().toString())||meal.equalsIgnoreCase(secondMealTv.getText().toString())
                        ||meal.equalsIgnoreCase(thirdMealTv.getText().toString())){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                }
                else if (res.getCount() == 3) {
                    if (myDatabase.searchFriBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchFriSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                    }
                    else{
                    searchThurBreakfast(meal);
                    searchThurLunch(meal);
                    searchThurSupper(meal);
                    myDatabase.insertFriLunchData(meal, nutrition);
                    //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    fourthMealTv.setText(meal);
                    fourthMealNutrition.setText(nutrition);
                    myDatabase.close();}
                }  else {

                    String currentMeal1 = firstMealTv.getText().toString();
                    String currentMeal2 = secondMealTv.getText().toString();
                    String currentMeal3 = thirdMealTv.getText().toString();
                    String currentMeal4 = fourthMealTv.getText().toString();
                    String currentMeal5 = fifthMealTv.getText().toString();

                    if(meal.equalsIgnoreCase(currentMeal1)||meal.equalsIgnoreCase(currentMeal2)||meal.equalsIgnoreCase(currentMeal3)||
                            meal.equalsIgnoreCase(currentMeal4)||meal.equalsIgnoreCase(currentMeal5)){
                        Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                    }
                    else {
                        if (myDatabase.searchFriBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                            Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                        }
                        else if(myDatabase.searchFriSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                            Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                        }
                        else if (fourthMealTv.getText().toString().trim().equalsIgnoreCase("")){
                            searchThurBreakfast(meal);
                            searchThurLunch(meal);
                            searchThurSupper(meal);
                            boolean isUpdated = myDatabase.updateFriLunchData(meal, nutrition, "4");
                            if (isUpdated) {
                                fourthMealTv.setText(meal);
                                fourthMealNutrition.setText(nutrition);
                            } else {
                                Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                        searchThurBreakfast(meal);
                        searchThurLunch(meal);
                        searchThurSupper(meal);
                        new AlertDialog.Builder(today_meal.this)
                                .setMessage(dialogMsgPt1 + currentMeal4 + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateFriLunchData(meal, nutrition, "4");
                    if (isUpdated) {
                        //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                        fourthMealTv.setText(meal);
                        fourthMealNutrition.setText(nutrition);
                    } else {
                        Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                    }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
            }
            //                //Friday Supper
            else if (mealType.equalsIgnoreCase("Friday Supper")) {
                Cursor res = myDatabase.viewFriSupperMeals();
            String firstmeal = firstMealTv.getText().toString();
            if (res.getCount() < 1 || firstmeal.trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), firstCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 2||secondMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), secondCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 3||thirdMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), thirdCell, Toast.LENGTH_SHORT).show();
                }
                else if(meal.equalsIgnoreCase(firstMealTv.getText().toString())||meal.equalsIgnoreCase(secondMealTv.getText().toString())
                        ||meal.equalsIgnoreCase(thirdMealTv.getText().toString())){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                }
                else if (res.getCount() == 3) {
                    if (myDatabase.searchFriBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchFriLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                    }
                    else{
                    searchThurBreakfast(meal);
                    searchThurLunch(meal);
                    searchThurSupper(meal);
                    myDatabase.insertFriSupperData(meal, nutrition);
                    //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    fourthMealTv.setText(meal);
                    fourthMealNutrition.setText(nutrition);
                    myDatabase.close();}
                }  else {

                    String currentMeal1 = firstMealTv.getText().toString();
                    String currentMeal2 = secondMealTv.getText().toString();
                    String currentMeal3 = thirdMealTv.getText().toString();
                    String currentMeal4 = fourthMealTv.getText().toString();
                    String currentMeal5 = fifthMealTv.getText().toString();

                    if(meal.equalsIgnoreCase(currentMeal1)||meal.equalsIgnoreCase(currentMeal2)||meal.equalsIgnoreCase(currentMeal3)||
                            meal.equalsIgnoreCase(currentMeal4)||meal.equalsIgnoreCase(currentMeal5)){
                        Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                    }
                    else {
                        if (myDatabase.searchFriBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                            Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                        }
                        else if(myDatabase.searchFriLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                            Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                        }
                        else if (fourthMealTv.getText().toString().trim().equalsIgnoreCase("")){
                            searchThurBreakfast(meal);
                            searchThurLunch(meal);
                            searchThurSupper(meal);
                            boolean isUpdated = myDatabase.updateFriSupperData(meal, nutrition, "4");
                            if (isUpdated) {
                                fourthMealTv.setText(meal);
                                fourthMealNutrition.setText(nutrition);
                            } else {
                                Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                        searchThurBreakfast(meal);
                        searchThurLunch(meal);
                        searchThurSupper(meal);
                        new AlertDialog.Builder(today_meal.this)
                                .setMessage(dialogMsgPt1 + currentMeal4 + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateFriSupperData(meal, nutrition, "4");
                    if (isUpdated) {
                        //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                        fourthMealTv.setText(meal);
                        fourthMealNutrition.setText(nutrition);
                    } else {
                        Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                    }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
            }
        }

        else if(pressedTextView.equalsIgnoreCase(mealtv5)){
            if (mealType.equalsIgnoreCase("Friday Breakfast")) {
                Cursor res = myDatabase.viewFriBreakfastMeals();
            String firstmeal = firstMealTv.getText().toString();
            if (res.getCount() < 1 || firstmeal.trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), firstCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 2||secondMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), secondCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 3||thirdMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), thirdCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 4||fourthMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), fourthCell, Toast.LENGTH_SHORT).show();
                }
                else if(meal.equalsIgnoreCase(firstMealTv.getText().toString())||meal.equalsIgnoreCase(secondMealTv.getText().toString())
                        ||meal.equalsIgnoreCase(thirdMealTv.getText().toString())||meal.equalsIgnoreCase(fourthMealTv.getText().toString())){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                }
                else if (res.getCount() == 4) {
                    if (myDatabase.searchFriLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchFriSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                    }
                    else{
                    searchThurBreakfast(meal);
                    searchThurLunch(meal);
                    searchThurSupper(meal);
                    myDatabase.insertFriBreakfastData(meal, nutrition);
                    //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    fifthMealTv.setText(meal);
                    fifthMealNutrition.setText(nutrition);
                    myDatabase.close();}
                }  else {

                String currentMeal1 = firstMealTv.getText().toString();
                String currentMeal2 = secondMealTv.getText().toString();
                String currentMeal3 = thirdMealTv.getText().toString();
                String currentMeal4 = fourthMealTv.getText().toString();
                String currentMeal5 = fifthMealTv.getText().toString();

                if(meal.equalsIgnoreCase(currentMeal1)||meal.equalsIgnoreCase(currentMeal2)||meal.equalsIgnoreCase(currentMeal3)||
                        meal.equalsIgnoreCase(currentMeal4)||meal.equalsIgnoreCase(currentMeal5)){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                }
                else {
                    if (myDatabase.searchFriLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchFriSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                    }
                    else if (fifthMealTv.getText().toString().trim().equalsIgnoreCase("")){
                        searchThurBreakfast(meal);
                        searchThurLunch(meal);
                        searchThurSupper(meal);
                        boolean isUpdated = myDatabase.updateFriBreakfastData(meal, nutrition, "5");
                        if (isUpdated) {
                            fifthMealTv.setText(meal);
                            fifthMealNutrition.setText(nutrition);
                        } else {
                            Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                    searchThurBreakfast(meal);
                    searchThurLunch(meal);
                    searchThurSupper(meal);
                    new AlertDialog.Builder(today_meal.this)
                            .setMessage(dialogMsgPt1 + currentMeal5 + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateFriBreakfastData(meal, nutrition, "5");
                    if (isUpdated) {
                        //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                        fifthMealTv.setText(meal);
                        fifthMealNutrition.setText(nutrition);
                    } else {
                        Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                    }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
            }
            //                //Friday Lunch
            else if (mealType.equalsIgnoreCase("Friday Lunch")) {
                Cursor res = myDatabase.viewFriLunchMeals();
            String firstmeal = firstMealTv.getText().toString();
            if (res.getCount() < 1 || firstmeal.trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), firstCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 2||secondMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), secondCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 3||thirdMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), thirdCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 4||fourthMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), fourthCell, Toast.LENGTH_SHORT).show();
                }
                else if(meal.equalsIgnoreCase(firstMealTv.getText().toString())||meal.equalsIgnoreCase(secondMealTv.getText().toString())
                        ||meal.equalsIgnoreCase(thirdMealTv.getText().toString())||meal.equalsIgnoreCase(fourthMealTv.getText().toString())){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                }
                else if (res.getCount() == 4) {
                    if (myDatabase.searchFriBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchFriSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                    }
                    else{
                    searchThurBreakfast(meal);
                    searchThurLunch(meal);
                    searchThurSupper(meal);
                    myDatabase.insertFriLunchData(meal, nutrition);
                    //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    fifthMealTv.setText(meal);
                    fifthMealNutrition.setText(nutrition);
                    myDatabase.close();}
                }  else {

                String currentMeal1 = firstMealTv.getText().toString();
                String currentMeal2 = secondMealTv.getText().toString();
                String currentMeal3 = thirdMealTv.getText().toString();
                String currentMeal4 = fourthMealTv.getText().toString();
                String currentMeal5 = fifthMealTv.getText().toString();

                if(meal.equalsIgnoreCase(currentMeal1)||meal.equalsIgnoreCase(currentMeal2)||meal.equalsIgnoreCase(currentMeal3)||
                        meal.equalsIgnoreCase(currentMeal4)||meal.equalsIgnoreCase(currentMeal5)){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                }
                else {
                    if (myDatabase.searchFriBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchFriSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                    }
                    else if (fifthMealTv.getText().toString().trim().equalsIgnoreCase("")){
                        searchThurBreakfast(meal);
                        searchThurLunch(meal);
                        searchThurSupper(meal);
                        boolean isUpdated = myDatabase.updateFriLunchData(meal, nutrition, "5");
                        if (isUpdated) {
                            fifthMealTv.setText(meal);
                            fifthMealNutrition.setText(nutrition);
                        } else {
                            Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                    searchThurBreakfast(meal);
                    searchThurLunch(meal);
                    searchThurSupper(meal);
                    new AlertDialog.Builder(today_meal.this)
                            .setMessage(dialogMsgPt1 + currentMeal5 + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateFriLunchData(meal, nutrition, "5");
                    if (isUpdated) {
                        //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                        fifthMealTv.setText(meal);
                        fifthMealNutrition.setText(nutrition);
                    } else {
                        Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                    }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
            }
            //                //Friday Supper
            else if (mealType.equalsIgnoreCase("Friday Supper")) {
                Cursor res = myDatabase.viewFriSupperMeals();
            String firstmeal = firstMealTv.getText().toString();
            if (res.getCount() < 1 || firstmeal.trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), firstCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 2||secondMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), secondCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 3||thirdMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), thirdCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 4||fourthMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), fourthCell, Toast.LENGTH_SHORT).show();
                }
                else if(meal.equalsIgnoreCase(firstMealTv.getText().toString())||meal.equalsIgnoreCase(secondMealTv.getText().toString())
                        ||meal.equalsIgnoreCase(thirdMealTv.getText().toString())||meal.equalsIgnoreCase(fourthMealTv.getText().toString())){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                }
                else if (res.getCount() == 4) {
                    if (myDatabase.searchFriBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchFriLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                    }
                    else{
                    searchThurBreakfast(meal);
                    searchThurLunch(meal);
                    searchThurSupper(meal);
                    myDatabase.insertFriSupperData(meal, nutrition);
                    //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    fifthMealTv.setText(meal);
                    fifthMealNutrition.setText(nutrition);
                    myDatabase.close();}
                }  else {

                String currentMeal1 = firstMealTv.getText().toString();
                String currentMeal2 = secondMealTv.getText().toString();
                String currentMeal3 = thirdMealTv.getText().toString();
                String currentMeal4 = fourthMealTv.getText().toString();
                String currentMeal5 = fifthMealTv.getText().toString();

                if(meal.equalsIgnoreCase(currentMeal1)||meal.equalsIgnoreCase(currentMeal2)||meal.equalsIgnoreCase(currentMeal3)||
                        meal.equalsIgnoreCase(currentMeal4)||meal.equalsIgnoreCase(currentMeal5)){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                }
                else {
                    if (myDatabase.searchFriBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchFriLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                    }
                    else if (fifthMealTv.getText().toString().trim().equalsIgnoreCase("")){
                        searchThurBreakfast(meal);
                        searchThurLunch(meal);
                        searchThurSupper(meal);
                        boolean isUpdated = myDatabase.updateFriSupperData(meal, nutrition, "5");
                        if (isUpdated) {
                            fifthMealTv.setText(meal);
                            fifthMealNutrition.setText(nutrition);
                        } else {
                            Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                    searchThurBreakfast(meal);
                    searchThurLunch(meal);
                    searchThurSupper(meal);
                    new AlertDialog.Builder(today_meal.this)
                            .setMessage(dialogMsgPt1 + currentMeal5 + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateFriSupperData(meal, nutrition, "5");
                    if (isUpdated) {
                        //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                        fifthMealTv.setText(meal);
                        fifthMealNutrition.setText(nutrition);
                    } else {
                        Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                    }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
            }
        }

    }//END OF FRIDAY MEALS

    //SATURDAY MEALS
    public  void saturdayMeals(String meal, String nutrition) {
//                Saturday breakfast
        mealType = todaysmealTv.getText().toString();
//                Toast.makeText(getApplicationContext(), pressedTextView, Toast.LENGTH_SHORT).show();

        if (pressedTextView.equalsIgnoreCase(mealtv1)) {

            if (mealType.equalsIgnoreCase("Saturday Breakfast")) {
                Cursor res2 = myDatabase.viewSatBreakfastMeals();
                if (res2.getCount() == 0) {
                    if (myDatabase.searchSatLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchSatSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                    }
                    else{
                    searchFriBreakfast(meal);
                    searchFriLunch(meal);
                    searchFriSupper(meal);
                    myDatabase.insertSatBreakfastData(meal, nutrition);
                    //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    firstMealTv.setText(meal);
                    firstMealNutrition.setText(nutrition);
                    myDatabase.close();}
                }
                else {

                    String currentMeal = firstMealTv.getText().toString();
                    if(currentMeal.equalsIgnoreCase(meal)){
                        Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                    }
                    else {
                        if (myDatabase.searchSatLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                            Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                        }
                        else if(myDatabase.searchSatSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                            Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                        }
                        else if (firstMealTv.getText().toString().trim().equalsIgnoreCase("")){
                            searchFriBreakfast(meal);
                            searchFriLunch(meal);
                            searchFriSupper(meal);
                            boolean isUpdated = myDatabase.updateSatBreakfastData(meal, nutrition, "1");
                            if (isUpdated) {
                                firstMealTv.setText(meal);
                                firstMealNutrition.setText(nutrition);
                            } else {
                                Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                        searchFriBreakfast(meal);
                        searchFriLunch(meal);
                        searchFriSupper(meal);
                        new AlertDialog.Builder(today_meal.this)
                        .setMessage(dialogMsgPt1 + currentMeal + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateSatBreakfastData(meal, nutrition, "1");
                    if (isUpdated) {
                        //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                        firstMealTv.setText(meal);
                        firstMealNutrition.setText(nutrition);
                    } else {
                        Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                    }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
            }
            //                //Saturday Lunch
            else if (mealType.equalsIgnoreCase("Saturday Lunch")) {
                Cursor res2 = myDatabase.viewSatLunchMeals();
                if (res2.getCount() == 0) {
                    if (myDatabase.searchSatBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchSatSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                    }
                    else{
                    searchFriBreakfast(meal);
                    searchFriLunch(meal);
                    searchFriSupper(meal);
                    myDatabase.insertSatLunchData(meal, nutrition);
                    //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    firstMealTv.setText(meal);
                    firstMealNutrition.setText(nutrition);
                    myDatabase.close();}
                }
                else {

                    String currentMeal = firstMealTv.getText().toString();
                    if(currentMeal.equalsIgnoreCase(meal)){
                        Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                    }
                    else {
                        if (myDatabase.searchSatBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                            Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                        }
                        else if(myDatabase.searchSatSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                            Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                        }
                        else if (firstMealTv.getText().toString().trim().equalsIgnoreCase("")){
                            searchFriBreakfast(meal);
                            searchFriLunch(meal);
                            searchFriSupper(meal);
                            boolean isUpdated = myDatabase.updateSatLunchData(meal, nutrition, "1");
                            if (isUpdated) {
                                firstMealTv.setText(meal);
                                firstMealNutrition.setText(nutrition);
                            } else {
                                Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                        searchFriBreakfast(meal);
                        searchFriLunch(meal);
                        searchFriSupper(meal);
                        new AlertDialog.Builder(today_meal.this)
                        .setMessage(dialogMsgPt1 + currentMeal + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateSatLunchData(meal, nutrition, "1");
                    if (isUpdated) {
                        //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                        firstMealTv.setText(meal);
                        firstMealNutrition.setText(nutrition);
                    } else {
                        Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                    }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
            }
            //                //Saturday Supper
            else if (mealType.equalsIgnoreCase("Saturday Supper")) {
                Cursor res2 = myDatabase.viewSatSupperMeals();
                if (res2.getCount() == 0) {
                    if (myDatabase.searchSatBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchSatLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                    }
                    else{
                    searchFriBreakfast(meal);
                    searchFriLunch(meal);
                    searchFriSupper(meal);
                    myDatabase.insertSatSupperData(meal, nutrition);
                    //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    firstMealTv.setText(meal);
                    firstMealNutrition.setText(nutrition);
                    myDatabase.close();}
                }
                else {

                    String currentMeal = firstMealTv.getText().toString();
                    if(currentMeal.equalsIgnoreCase(meal)){
                        Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                    }
                    else {
                        if (myDatabase.searchSatBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                            Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                        }
                        else if(myDatabase.searchSatLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                            Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                        }
                        else if (firstMealTv.getText().toString().trim().equalsIgnoreCase("")){
                            searchFriBreakfast(meal);
                            searchFriLunch(meal);
                            searchFriSupper(meal);
                            boolean isUpdated = myDatabase.updateSatSupperData(meal, nutrition, "1");
                            if (isUpdated) {
                                firstMealTv.setText(meal);
                                firstMealNutrition.setText(nutrition);
                            } else {
                                Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                        searchFriBreakfast(meal);
                        searchFriLunch(meal);
                        searchFriSupper(meal);
                        new AlertDialog.Builder(today_meal.this)
                        .setMessage(dialogMsgPt1 + currentMeal + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateSatSupperData(meal, nutrition, "1");
                    if (isUpdated) {
                        //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                        firstMealTv.setText(meal);
                        firstMealNutrition.setText(nutrition);
                    } else {
                        Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                    }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
            }
        }
        else if(pressedTextView.equalsIgnoreCase(mealtv2)){
            if (mealType.equalsIgnoreCase("Saturday Breakfast")) {
                Cursor res = myDatabase.viewSatBreakfastMeals();
            String firstmeal = firstMealTv.getText().toString();
            if (res.getCount() < 1 || firstmeal.trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), firstCell, Toast.LENGTH_SHORT).show();
                }
                else if(meal.equalsIgnoreCase(firstMealTv.getText().toString())){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                }
                else if (res.getCount() == 1) {
                    if (myDatabase.searchSatLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchSatSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                    }
                    else{
                    searchFriBreakfast(meal);
                    searchFriLunch(meal);
                    searchFriSupper(meal);
                    myDatabase.insertSatBreakfastData(meal, nutrition);
                    //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    secondMealTv.setText(meal);
                    secondMealNutrition.setText(nutrition);
                    myDatabase.close();}
                }else {

                    String currentMeal1 = firstMealTv.getText().toString();
                    String currentMeal2 = secondMealTv.getText().toString();
                    String currentMeal3 = thirdMealTv.getText().toString();
                    String currentMeal4 = fourthMealTv.getText().toString();
                    String currentMeal5 = fifthMealTv.getText().toString();

                    if(meal.equalsIgnoreCase(currentMeal1)||meal.equalsIgnoreCase(currentMeal2)||meal.equalsIgnoreCase(currentMeal3)||
                            meal.equalsIgnoreCase(currentMeal4)||meal.equalsIgnoreCase(currentMeal5)){
                        Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                    }
                    else {
                        if (myDatabase.searchSatLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                            Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                        }
                        else if(myDatabase.searchSatSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                            Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                        }
                        else if (secondMealTv.getText().toString().trim().equalsIgnoreCase("")){
                            searchFriBreakfast(meal);
                            searchFriLunch(meal);
                            searchFriSupper(meal);
                            boolean isUpdated = myDatabase.updateSatBreakfastData(meal, nutrition, "2");
                            if (isUpdated) {
                                secondMealTv.setText(meal);
                                secondMealNutrition.setText(nutrition);
                            } else {
                                Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                        searchFriBreakfast(meal);
                        searchFriLunch(meal);
                        searchFriSupper(meal);
                        new AlertDialog.Builder(today_meal.this)
                        .setMessage(dialogMsgPt1 + currentMeal2 + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateSatBreakfastData(meal, nutrition, "2");
                    if (isUpdated) {
                        //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                        secondMealTv.setText(meal);
                        secondMealNutrition.setText(nutrition);
                    } else {
                        Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                    }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
            }
            //                //Saturday Lunch
            else if (mealType.equalsIgnoreCase("Saturday Lunch")) {
                Cursor res = myDatabase.viewSatLunchMeals();
            String firstmeal = firstMealTv.getText().toString();
            if (res.getCount() < 1 || firstmeal.trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), firstCell, Toast.LENGTH_SHORT).show();
                }
                else if(meal.equalsIgnoreCase(firstMealTv.getText().toString())){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                }
                else if (res.getCount() == 1) {
                    if (myDatabase.searchSatBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchSatSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                    }
                    else{
                    searchFriBreakfast(meal);
                    searchFriLunch(meal);
                    searchFriSupper(meal);
                    myDatabase.insertSatLunchData(meal, nutrition);
                    //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    secondMealTv.setText(meal);
                    secondMealNutrition.setText(nutrition);
                    myDatabase.close();}
                }else {

                    String currentMeal1 = firstMealTv.getText().toString();
                    String currentMeal2 = secondMealTv.getText().toString();
                    String currentMeal3 = thirdMealTv.getText().toString();
                    String currentMeal4 = fourthMealTv.getText().toString();
                    String currentMeal5 = fifthMealTv.getText().toString();

                    if(meal.equalsIgnoreCase(currentMeal1)||meal.equalsIgnoreCase(currentMeal2)||meal.equalsIgnoreCase(currentMeal3)||
                            meal.equalsIgnoreCase(currentMeal4)||meal.equalsIgnoreCase(currentMeal5)){
                        Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                    }
                    else {
                        if (myDatabase.searchSatBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                            Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                        }
                        else if(myDatabase.searchSatSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                            Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                        }
                        else if (secondMealTv.getText().toString().trim().equalsIgnoreCase("")){
                            searchFriBreakfast(meal);
                            searchFriLunch(meal);
                            searchFriSupper(meal);
                            boolean isUpdated = myDatabase.updateSatLunchData(meal, nutrition, "2");
                            if (isUpdated) {
                                secondMealTv.setText(meal);
                                secondMealNutrition.setText(nutrition);
                            } else {
                                Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                        searchFriBreakfast(meal);
                        searchFriLunch(meal);
                        searchFriSupper(meal);
                        new AlertDialog.Builder(today_meal.this)
                        .setMessage(dialogMsgPt1 + currentMeal2 + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateSatLunchData(meal, nutrition, "2");
                    if (isUpdated) {
                        //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                        secondMealTv.setText(meal);
                        secondMealNutrition.setText(nutrition);
                    } else {
                        Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                    }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
            }
            //                //Saturday Supper
            else if (mealType.equalsIgnoreCase("Saturday Supper")) {
                Cursor res = myDatabase.viewSatSupperMeals();
            String firstmeal = firstMealTv.getText().toString();
            if (res.getCount() < 1 || firstmeal.trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), firstCell, Toast.LENGTH_SHORT).show();
                }
                else if(meal.equalsIgnoreCase(firstMealTv.getText().toString())){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                }
                else if (res.getCount() == 1) {
                    if (myDatabase.searchSatBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchSatLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                    }
                    else{
                    searchFriBreakfast(meal);
                    searchFriLunch(meal);
                    searchFriSupper(meal);
                    myDatabase.insertSatSupperData(meal, nutrition);
                    //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    secondMealTv.setText(meal);
                    secondMealNutrition.setText(nutrition);
                    myDatabase.close();}
                }else {

                    String currentMeal1 = firstMealTv.getText().toString();
                    String currentMeal2 = secondMealTv.getText().toString();
                    String currentMeal3 = thirdMealTv.getText().toString();
                    String currentMeal4 = fourthMealTv.getText().toString();
                    String currentMeal5 = fifthMealTv.getText().toString();

                    if(meal.equalsIgnoreCase(currentMeal1)||meal.equalsIgnoreCase(currentMeal2)||meal.equalsIgnoreCase(currentMeal3)||
                            meal.equalsIgnoreCase(currentMeal4)||meal.equalsIgnoreCase(currentMeal5)){
                        Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                    }
                    else {
                        if (myDatabase.searchSatBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                            Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                        }
                        else if(myDatabase.searchSatLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                            Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                        }
                        else if (secondMealTv.getText().toString().trim().equalsIgnoreCase("")){
                            searchFriBreakfast(meal);
                            searchFriLunch(meal);
                            searchFriSupper(meal);
                            boolean isUpdated = myDatabase.updateSatSupperData(meal, nutrition, "2");
                            if (isUpdated) {
                                secondMealTv.setText(meal);
                                secondMealNutrition.setText(nutrition);
                            } else {
                                Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                        searchFriBreakfast(meal);
                        searchFriLunch(meal);
                        searchFriSupper(meal);
                        new AlertDialog.Builder(today_meal.this)
                        .setMessage(dialogMsgPt1 + currentMeal2 + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateSatSupperData(meal, nutrition, "2");
                    if (isUpdated) {
                        //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                        secondMealTv.setText(meal);
                        secondMealNutrition.setText(nutrition);
                    } else {
                        Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                    }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
            }
        }
        else if(pressedTextView.equalsIgnoreCase(mealtv3)){
            if (mealType.equalsIgnoreCase("Saturday Breakfast")) {
                Cursor res = myDatabase.viewSatBreakfastMeals();
            String firstmeal = firstMealTv.getText().toString();
            if (res.getCount() < 1 || firstmeal.trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), firstCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 2||secondMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), secondCell, Toast.LENGTH_SHORT).show();
                }
                else if(meal.equalsIgnoreCase(firstMealTv.getText().toString())||meal.equalsIgnoreCase(secondMealTv.getText().toString())){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                }
                else if (res.getCount() == 2) {
                    if (myDatabase.searchSatLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchSatSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                    }
                    else{
                    searchFriBreakfast(meal);
                    searchFriLunch(meal);
                    searchFriSupper(meal);
                    myDatabase.insertSatBreakfastData(meal, nutrition);
                    //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    thirdMealTv.setText(meal);
                    thirdMealNutrition.setText(nutrition);
                    myDatabase.close();}
                } else {

                String currentMeal1 = firstMealTv.getText().toString();
                String currentMeal2 = secondMealTv.getText().toString();
                String currentMeal3 = thirdMealTv.getText().toString();
                String currentMeal4 = fourthMealTv.getText().toString();
                String currentMeal5 = fifthMealTv.getText().toString();

                if(meal.equalsIgnoreCase(currentMeal1)||meal.equalsIgnoreCase(currentMeal2)||meal.equalsIgnoreCase(currentMeal3)||
                        meal.equalsIgnoreCase(currentMeal4)||meal.equalsIgnoreCase(currentMeal5)){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                }
                else {
                    if (myDatabase.searchSatLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchSatSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                    }
                    else if (thirdMealTv.getText().toString().trim().equalsIgnoreCase("")){
                        searchFriBreakfast(meal);
                        searchFriLunch(meal);
                        searchFriSupper(meal);
                        boolean isUpdated = myDatabase.updateSatBreakfastData(meal, nutrition, "3");
                        if (isUpdated) {
                            thirdMealTv.setText(meal);
                            thirdMealNutrition.setText(nutrition);
                        } else {
                            Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                    searchFriBreakfast(meal);
                    searchFriLunch(meal);
                    searchFriSupper(meal);
                    new AlertDialog.Builder(today_meal.this)
                        .setMessage(dialogMsgPt1 + currentMeal3 + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateSatBreakfastData(meal, nutrition, "3");
                    if (isUpdated) {
                        //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                        thirdMealTv.setText(meal);
                        thirdMealNutrition.setText(nutrition);
                    } else {
                        Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                    }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
            }
            //                //Saturday Lunch
            else if (mealType.equalsIgnoreCase("Saturday Lunch")) {
                Cursor res = myDatabase.viewSatLunchMeals();
            String firstmeal = firstMealTv.getText().toString();
            if (res.getCount() < 1 || firstmeal.trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), firstCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 2||secondMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), secondCell, Toast.LENGTH_SHORT).show();
                }
                else if(meal.equalsIgnoreCase(firstMealTv.getText().toString())||meal.equalsIgnoreCase(secondMealTv.getText().toString())){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                }
                else if (res.getCount() == 2) {
                    if (myDatabase.searchSatBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchSatSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                    }
                    else{
                    searchFriBreakfast(meal);
                    searchFriLunch(meal);
                    searchFriSupper(meal);
                    myDatabase.insertSatLunchData(meal, nutrition);
                    //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    thirdMealTv.setText(meal);
                    thirdMealNutrition.setText(nutrition);
                    myDatabase.close();}
                } else {

                String currentMeal1 = firstMealTv.getText().toString();
                String currentMeal2 = secondMealTv.getText().toString();
                String currentMeal3 = thirdMealTv.getText().toString();
                String currentMeal4 = fourthMealTv.getText().toString();
                String currentMeal5 = fifthMealTv.getText().toString();

                if(meal.equalsIgnoreCase(currentMeal1)||meal.equalsIgnoreCase(currentMeal2)||meal.equalsIgnoreCase(currentMeal3)||
                        meal.equalsIgnoreCase(currentMeal4)||meal.equalsIgnoreCase(currentMeal5)){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                }
                else {
                    if (myDatabase.searchSatBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchSatSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                    }
                    else if (thirdMealTv.getText().toString().trim().equalsIgnoreCase("")){
                        searchFriBreakfast(meal);
                        searchFriLunch(meal);
                        searchFriSupper(meal);
                        boolean isUpdated = myDatabase.updateSatLunchData(meal, nutrition, "3");
                        if (isUpdated) {
                            thirdMealTv.setText(meal);
                            thirdMealNutrition.setText(nutrition);
                        } else {
                            Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                    searchFriBreakfast(meal);
                    searchFriLunch(meal);
                    searchFriSupper(meal);
                    new AlertDialog.Builder(today_meal.this)
                        .setMessage(dialogMsgPt1 + currentMeal3 + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateSatLunchData(meal, nutrition, "3");
                    if (isUpdated) {
                        //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                        thirdMealTv.setText(meal);
                        thirdMealNutrition.setText(nutrition);
                    } else {
                        Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                    }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
            }
            //                //Saturday Supper yuo
            else if (mealType.equalsIgnoreCase("Saturday Supper")) {
                Cursor res = myDatabase.viewSatSupperMeals();
            String firstmeal = firstMealTv.getText().toString();
            if (res.getCount() < 1 || firstmeal.trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), firstCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 2||secondMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), secondCell, Toast.LENGTH_SHORT).show();
                }
                else if(meal.equalsIgnoreCase(firstMealTv.getText().toString())||meal.equalsIgnoreCase(secondMealTv.getText().toString())){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                }
                else if (res.getCount() == 2) {
                    if (myDatabase.searchSatBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchSatLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                    }
                    else{
                    searchFriBreakfast(meal);
                    searchFriLunch(meal);
                    searchFriSupper(meal);
                    myDatabase.insertSatSupperData(meal, nutrition);
                    //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    thirdMealTv.setText(meal);
                    thirdMealNutrition.setText(nutrition);
                    myDatabase.close();}
                } else {

                String currentMeal1 = firstMealTv.getText().toString();
                String currentMeal2 = secondMealTv.getText().toString();
                String currentMeal3 = thirdMealTv.getText().toString();
                String currentMeal4 = fourthMealTv.getText().toString();
                String currentMeal5 = fifthMealTv.getText().toString();

                if(meal.equalsIgnoreCase(currentMeal1)||meal.equalsIgnoreCase(currentMeal2)||meal.equalsIgnoreCase(currentMeal3)||
                        meal.equalsIgnoreCase(currentMeal4)||meal.equalsIgnoreCase(currentMeal5)){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                }
                else {
                    if (myDatabase.searchSatBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchSatLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                    }
                    else if (thirdMealTv.getText().toString().trim().equalsIgnoreCase("")){
                        searchFriBreakfast(meal);
                        searchFriLunch(meal);
                        searchFriSupper(meal);
                        boolean isUpdated = myDatabase.updateSatSupperData(meal, nutrition, "3");
                        if (isUpdated) {
                            thirdMealTv.setText(meal);
                            thirdMealNutrition.setText(nutrition);
                        } else {
                            Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                    searchFriBreakfast(meal);
                    searchFriLunch(meal);
                    searchFriSupper(meal);
                    new AlertDialog.Builder(today_meal.this)
                        .setMessage(dialogMsgPt1 + currentMeal3 + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateSatSupperData(meal, nutrition, "3");
                    if (isUpdated) {
                        //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                        thirdMealTv.setText(meal);
                        thirdMealNutrition.setText(nutrition);
                    } else {
                        Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                    }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
            }
        }
        else if(pressedTextView.equalsIgnoreCase(mealtv4)){

            if (mealType.equalsIgnoreCase("Saturday Breakfast")) {
                Cursor res = myDatabase.viewSatBreakfastMeals();
            String firstmeal = firstMealTv.getText().toString();
            if (res.getCount() < 1 || firstmeal.trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), firstCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 2||secondMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), secondCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 3||thirdMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), thirdCell, Toast.LENGTH_SHORT).show();
                }
                else if(meal.equalsIgnoreCase(firstMealTv.getText().toString())||meal.equalsIgnoreCase(secondMealTv.getText().toString())
                        ||meal.equalsIgnoreCase(thirdMealTv.getText().toString())){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                }
                else if (res.getCount() == 3) {
                    if (myDatabase.searchSatLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchSatSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                    }
                    else{
                    searchFriBreakfast(meal);
                    searchFriLunch(meal);
                    searchFriSupper(meal);
                    myDatabase.insertSatBreakfastData(meal, nutrition);
                    //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    fourthMealTv.setText(meal);
                    fourthMealNutrition.setText(nutrition);
                    myDatabase.close();}
                }  else {

                    String currentMeal1 = firstMealTv.getText().toString();
                    String currentMeal2 = secondMealTv.getText().toString();
                    String currentMeal3 = thirdMealTv.getText().toString();
                    String currentMeal4 = fourthMealTv.getText().toString();
                    String currentMeal5 = fifthMealTv.getText().toString();

                    if(meal.equalsIgnoreCase(currentMeal1)||meal.equalsIgnoreCase(currentMeal2)||meal.equalsIgnoreCase(currentMeal3)||
                            meal.equalsIgnoreCase(currentMeal4)||meal.equalsIgnoreCase(currentMeal5)){
                        Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                    }
                    else {
                        if (myDatabase.searchSatLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                            Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                        }
                        else if(myDatabase.searchSatSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                            Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                        }
                        else if (fourthMealTv.getText().toString().trim().equalsIgnoreCase("")){
                            searchFriBreakfast(meal);
                            searchFriLunch(meal);
                            searchFriSupper(meal);
                            boolean isUpdated = myDatabase.updateSatBreakfastData(meal, nutrition, "4");
                            if (isUpdated) {
                                fourthMealTv.setText(meal);
                                fourthMealNutrition.setText(nutrition);
                            } else {
                                Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                        searchFriBreakfast(meal);
                        searchFriLunch(meal);
                        searchFriSupper(meal);
                        new AlertDialog.Builder(today_meal.this)
                                .setMessage(dialogMsgPt1 + currentMeal4 + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateSatBreakfastData(meal, nutrition, "4");
                    if (isUpdated) {
                        //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                        fourthMealTv.setText(meal);
                        fourthMealNutrition.setText(nutrition);
                    } else {
                        Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                    }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
            }
            //                //Saturday Lunch
            else if (mealType.equalsIgnoreCase("Saturday Lunch")) {
                Cursor res = myDatabase.viewSatLunchMeals();
            String firstmeal = firstMealTv.getText().toString();
            if (res.getCount() < 1 || firstmeal.trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), firstCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 2||secondMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), secondCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 3||thirdMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), thirdCell, Toast.LENGTH_SHORT).show();
                }
                else if(meal.equalsIgnoreCase(firstMealTv.getText().toString())||meal.equalsIgnoreCase(secondMealTv.getText().toString())
                        ||meal.equalsIgnoreCase(thirdMealTv.getText().toString())){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                }
                else if (res.getCount() == 3) {
                    if (myDatabase.searchSatBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchSatSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                    }
                    else{
                    searchFriBreakfast(meal);
                    searchFriLunch(meal);
                    searchFriSupper(meal);
                    myDatabase.insertSatLunchData(meal, nutrition);
                    //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    fourthMealTv.setText(meal);
                    fourthMealNutrition.setText(nutrition);
                    myDatabase.close();}
                }  else {

                    String currentMeal1 = firstMealTv.getText().toString();
                    String currentMeal2 = secondMealTv.getText().toString();
                    String currentMeal3 = thirdMealTv.getText().toString();
                    String currentMeal4 = fourthMealTv.getText().toString();
                    String currentMeal5 = fifthMealTv.getText().toString();

                    if(meal.equalsIgnoreCase(currentMeal1)||meal.equalsIgnoreCase(currentMeal2)||meal.equalsIgnoreCase(currentMeal3)||
                            meal.equalsIgnoreCase(currentMeal4)||meal.equalsIgnoreCase(currentMeal5)){
                        Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                    }
                    else {
                        if (myDatabase.searchSatBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                            Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                        }
                        else if(myDatabase.searchSatSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                            Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                        }
                        else if (fourthMealTv.getText().toString().trim().equalsIgnoreCase("")){
                            searchFriBreakfast(meal);
                            searchFriLunch(meal);
                            searchFriSupper(meal);
                            boolean isUpdated = myDatabase.updateSatLunchData(meal, nutrition, "4");
                            if (isUpdated) {
                                fourthMealTv.setText(meal);
                                fourthMealNutrition.setText(nutrition);
                            } else {
                                Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                        searchFriBreakfast(meal);
                        searchFriLunch(meal);
                        searchFriSupper(meal);
                        new AlertDialog.Builder(today_meal.this)
                                .setMessage(dialogMsgPt1 + currentMeal4 + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateSatLunchData(meal, nutrition, "4");
                    if (isUpdated) {
                        //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                        fourthMealTv.setText(meal);
                        fourthMealNutrition.setText(nutrition);
                    } else {
                        Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                    }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
            }
            //                //Saturday Supper
            else if (mealType.equalsIgnoreCase("Saturday Supper")) {
                Cursor res = myDatabase.viewSatSupperMeals();
            String firstmeal = firstMealTv.getText().toString();
            if (res.getCount() < 1 || firstmeal.trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), firstCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 2||secondMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), secondCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 3||thirdMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), thirdCell, Toast.LENGTH_SHORT).show();
                }
                else if(meal.equalsIgnoreCase(firstMealTv.getText().toString())||meal.equalsIgnoreCase(secondMealTv.getText().toString())
                        ||meal.equalsIgnoreCase(thirdMealTv.getText().toString())){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                }
                else if (res.getCount() == 3) {
                    if (myDatabase.searchSatBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchSatLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                    }
                    else{
                    searchFriBreakfast(meal);
                    searchFriLunch(meal);
                    searchFriSupper(meal);
                    myDatabase.insertSatSupperData(meal, nutrition);
                    //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    fourthMealTv.setText(meal);
                    fourthMealNutrition.setText(nutrition);
                    myDatabase.close();}
                }  else {

                    String currentMeal1 = firstMealTv.getText().toString();
                    String currentMeal2 = secondMealTv.getText().toString();
                    String currentMeal3 = thirdMealTv.getText().toString();
                    String currentMeal4 = fourthMealTv.getText().toString();
                    String currentMeal5 = fifthMealTv.getText().toString();

                    if(meal.equalsIgnoreCase(currentMeal1)||meal.equalsIgnoreCase(currentMeal2)||meal.equalsIgnoreCase(currentMeal3)||
                            meal.equalsIgnoreCase(currentMeal4)||meal.equalsIgnoreCase(currentMeal5)){
                        Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                    }
                    else {
                        if (myDatabase.searchSatBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                            Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                        }
                        else if(myDatabase.searchSatLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                            Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                        }
                        else if (fourthMealTv.getText().toString().trim().equalsIgnoreCase("")){
                            searchFriBreakfast(meal);
                            searchFriLunch(meal);
                            searchFriSupper(meal);
                            boolean isUpdated = myDatabase.updateSatSupperData(meal, nutrition, "4");
                            if (isUpdated) {
                                fourthMealTv.setText(meal);
                                fourthMealNutrition.setText(nutrition);
                            } else {
                                Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                        searchFriBreakfast(meal);
                        searchFriLunch(meal);
                        searchFriSupper(meal);
                        new AlertDialog.Builder(today_meal.this)
                                .setMessage(dialogMsgPt1 + currentMeal4 + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateSatSupperData(meal, nutrition, "4");
                    if (isUpdated) {
                        //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                        fourthMealTv.setText(meal);
                        fourthMealNutrition.setText(nutrition);
                    } else {
                        Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                    }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
            }
        }

        else if(pressedTextView.equalsIgnoreCase(mealtv5)){
            if (mealType.equalsIgnoreCase("Saturday Breakfast")) {
                Cursor res = myDatabase.viewSatBreakfastMeals();
            String firstmeal = firstMealTv.getText().toString();
            if (res.getCount() < 1 || firstmeal.trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), firstCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 2||secondMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), secondCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 3||thirdMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), thirdCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 4||fourthMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), fourthCell, Toast.LENGTH_SHORT).show();
                }
                else if(meal.equalsIgnoreCase(firstMealTv.getText().toString())||meal.equalsIgnoreCase(secondMealTv.getText().toString())
                        ||meal.equalsIgnoreCase(thirdMealTv.getText().toString())||meal.equalsIgnoreCase(fourthMealTv.getText().toString())){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                }
                else if (res.getCount() == 4) {
                    if (myDatabase.searchSatLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchSatSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                    }
                    else{
                    searchFriBreakfast(meal);
                    searchFriLunch(meal);
                    searchFriSupper(meal);
                    myDatabase.insertSatBreakfastData(meal, nutrition);
                    //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    fifthMealTv.setText(meal);
                    fifthMealNutrition.setText(nutrition);
                    myDatabase.close();}
                }  else {

                String currentMeal1 = firstMealTv.getText().toString();
                String currentMeal2 = secondMealTv.getText().toString();
                String currentMeal3 = thirdMealTv.getText().toString();
                String currentMeal4 = fourthMealTv.getText().toString();
                String currentMeal5 = fifthMealTv.getText().toString();

                if(meal.equalsIgnoreCase(currentMeal1)||meal.equalsIgnoreCase(currentMeal2)||meal.equalsIgnoreCase(currentMeal3)||
                        meal.equalsIgnoreCase(currentMeal4)||meal.equalsIgnoreCase(currentMeal5)){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                }
                else {
                    if (myDatabase.searchSatLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchSatSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                    }
                    else if (fifthMealTv.getText().toString().trim().equalsIgnoreCase("")){
                        searchFriBreakfast(meal);
                        searchFriLunch(meal);
                        searchFriSupper(meal);
                        boolean isUpdated = myDatabase.updateSatBreakfastData(meal, nutrition, "5");
                        if (isUpdated) {
                            fifthMealTv.setText(meal);
                            fifthMealNutrition.setText(nutrition);
                        } else {
                            Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                    searchFriBreakfast(meal);
                    searchFriLunch(meal);
                    searchFriSupper(meal);
                    new AlertDialog.Builder(today_meal.this)
                            .setMessage(dialogMsgPt1 + currentMeal5 + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateSatBreakfastData(meal, nutrition, "5");
                    if (isUpdated) {
                        //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                        fifthMealTv.setText(meal);
                        fifthMealNutrition.setText(nutrition);
                    } else {
                        Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                    }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
            }
            //                //Saturday Lunch
            else if (mealType.equalsIgnoreCase("Saturday Lunch")) {
                Cursor res = myDatabase.viewSatLunchMeals();
            String firstmeal = firstMealTv.getText().toString();
            if (res.getCount() < 1 || firstmeal.trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), firstCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 2||secondMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), secondCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 3||thirdMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), thirdCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 4||fourthMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), fourthCell, Toast.LENGTH_SHORT).show();
                }
                else if(meal.equalsIgnoreCase(firstMealTv.getText().toString())||meal.equalsIgnoreCase(secondMealTv.getText().toString())
                        ||meal.equalsIgnoreCase(thirdMealTv.getText().toString())||meal.equalsIgnoreCase(fourthMealTv.getText().toString())){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                }
                else if (res.getCount() == 4) {
                    if (myDatabase.searchSatBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchSatSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                    }
                    else{
                    searchFriBreakfast(meal);
                    searchFriLunch(meal);
                    searchFriSupper(meal);
                    myDatabase.insertSatLunchData(meal, nutrition);
                    //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    fifthMealTv.setText(meal);
                    fifthMealNutrition.setText(nutrition);
                    myDatabase.close();}
                }  else {

                String currentMeal1 = firstMealTv.getText().toString();
                String currentMeal2 = secondMealTv.getText().toString();
                String currentMeal3 = thirdMealTv.getText().toString();
                String currentMeal4 = fourthMealTv.getText().toString();
                String currentMeal5 = fifthMealTv.getText().toString();

                if(meal.equalsIgnoreCase(currentMeal1)||meal.equalsIgnoreCase(currentMeal2)||meal.equalsIgnoreCase(currentMeal3)||
                        meal.equalsIgnoreCase(currentMeal4)||meal.equalsIgnoreCase(currentMeal5)){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                }
                else {
                    if (myDatabase.searchSatBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchSatSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                    }
                    else if (fifthMealTv.getText().toString().trim().equalsIgnoreCase("")){
                        searchFriBreakfast(meal);
                        searchFriLunch(meal);
                        searchFriSupper(meal);
                        boolean isUpdated = myDatabase.updateSatLunchData(meal, nutrition, "5");
                        if (isUpdated) {
                            fifthMealTv.setText(meal);
                            fifthMealNutrition.setText(nutrition);
                        } else {
                            Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                    searchFriBreakfast(meal);
                    searchFriLunch(meal);
                    searchFriSupper(meal);
                    new AlertDialog.Builder(today_meal.this)
                            .setMessage(dialogMsgPt1 + currentMeal5 + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateSatLunchData(meal, nutrition, "5");
                    if (isUpdated) {
                        //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                        fifthMealTv.setText(meal);
                        fifthMealNutrition.setText(nutrition);
                    } else {
                        Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                    }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
            }
            //                //Saturday Supper
            else if (mealType.equalsIgnoreCase("Saturday Supper")) {
                Cursor res = myDatabase.viewSatSupperMeals();
            String firstmeal = firstMealTv.getText().toString();
            if (res.getCount() < 1 || firstmeal.trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), firstCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 2||secondMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), secondCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 3||thirdMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), thirdCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 4||fourthMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), fourthCell, Toast.LENGTH_SHORT).show();
                }
                else if(meal.equalsIgnoreCase(firstMealTv.getText().toString())||meal.equalsIgnoreCase(secondMealTv.getText().toString())
                        ||meal.equalsIgnoreCase(thirdMealTv.getText().toString())||meal.equalsIgnoreCase(fourthMealTv.getText().toString())){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                }
                else if (res.getCount() == 4) {
                    if (myDatabase.searchSatBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchSatLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                    }
                    else{
                    searchFriBreakfast(meal);
                    searchFriLunch(meal);
                    searchFriSupper(meal);
                    myDatabase.insertSatSupperData(meal, nutrition);
                    //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    fifthMealTv.setText(meal);
                    fifthMealNutrition.setText(nutrition);
                    myDatabase.close();}
                }  else {

                String currentMeal1 = firstMealTv.getText().toString();
                String currentMeal2 = secondMealTv.getText().toString();
                String currentMeal3 = thirdMealTv.getText().toString();
                String currentMeal4 = fourthMealTv.getText().toString();
                String currentMeal5 = fifthMealTv.getText().toString();

                if(meal.equalsIgnoreCase(currentMeal1)||meal.equalsIgnoreCase(currentMeal2)||meal.equalsIgnoreCase(currentMeal3)||
                        meal.equalsIgnoreCase(currentMeal4)||meal.equalsIgnoreCase(currentMeal5)){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                }
                else {
                    if (myDatabase.searchSatBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchSatLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                    }
                    else if (fifthMealTv.getText().toString().trim().equalsIgnoreCase("")){
                        searchFriBreakfast(meal);
                        searchFriLunch(meal);
                        searchFriSupper(meal);
                        boolean isUpdated = myDatabase.updateSatSupperData(meal, nutrition, "5");
                        if (isUpdated) {
                            fifthMealTv.setText(meal);
                            fifthMealNutrition.setText(nutrition);
                        } else {
                            Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                    searchFriBreakfast(meal);
                    searchFriLunch(meal);
                    searchFriSupper(meal);
                    new AlertDialog.Builder(today_meal.this)
                            .setMessage(dialogMsgPt1 + currentMeal5 + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateSatSupperData(meal, nutrition, "5");
                    if (isUpdated) {
                        //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                        fifthMealTv.setText(meal);
                        fifthMealNutrition.setText(nutrition);
                    } else {
                        Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                    }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
            }
        }

    }//END OF SATURDAY MEALS

    //SUNDAY MEALS
    public  void sundayMeals(String meal, String nutrition) {
//                Sunday breakfast
        mealType = todaysmealTv.getText().toString();
//                Toast.makeText(getApplicationContext(), pressedTextView, Toast.LENGTH_SHORT).show();

        if (pressedTextView.equalsIgnoreCase(mealtv1)) {

            if (mealType.equalsIgnoreCase("Sunday Breakfast")) {
                Cursor res2 = myDatabase.viewSunBreakfastMeals();
                if (res2.getCount() == 0) {
                    if (myDatabase.searchSunLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                    }
                    else if(meal.equalsIgnoreCase(firstMealTv.getText().toString())||meal.equalsIgnoreCase(secondMealTv.getText().toString())
                            ||meal.equalsIgnoreCase(thirdMealTv.getText().toString())||meal.equalsIgnoreCase(fourthMealTv.getText().toString())){
                        Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                    }
                    else if(myDatabase.searchSunSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                    }
                    else{
                    searchSatBreakfast(meal);
                    searchSatLunch(meal);
                    searchSatSupper(meal);
                    myDatabase.insertSunBreakfastData(meal, nutrition);
                    //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    firstMealTv.setText(meal);
                    firstMealNutrition.setText(nutrition);
                    myDatabase.close();}
                }
                else {

                    String currentMeal = firstMealTv.getText().toString();
                    if(currentMeal.equalsIgnoreCase(meal)){
                        Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                    }
                    else {
                 if(meal.equalsIgnoreCase(firstMealTv.getText().toString())||meal.equalsIgnoreCase(secondMealTv.getText().toString())
                                ||meal.equalsIgnoreCase(thirdMealTv.getText().toString())||meal.equalsIgnoreCase(fourthMealTv.getText().toString())){
                            Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                        }
                        else if (myDatabase.searchSunLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                            Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                        }
                        else if(myDatabase.searchSunSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                            Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                        }
                 else if (firstMealTv.getText().toString().trim().equalsIgnoreCase("")){
                     searchSatBreakfast(meal);
                     searchSatLunch(meal);
                     searchSatSupper(meal);
                     boolean isUpdated = myDatabase.updateSunBreakfastData(meal, nutrition, "1");
                     if (isUpdated) {
                         firstMealTv.setText(meal);
                         firstMealNutrition.setText(nutrition);
                     } else {
                         Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                     }
                 }
                        else{
                        searchSatBreakfast(meal);
                        searchSatLunch(meal);
                        searchSatSupper(meal);
                        new AlertDialog.Builder(today_meal.this)
                        .setMessage(dialogMsgPt1 + currentMeal + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateSunBreakfastData(meal, nutrition, "1");
                    if (isUpdated) {
                        //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                        firstMealTv.setText(meal);
                        firstMealNutrition.setText(nutrition);
                    } else {
                        Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                    }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
            }
            //                //Sunday Lunch
            else if (mealType.equalsIgnoreCase("Sunday Lunch")) {
                Cursor res2 = myDatabase.viewSunLunchMeals();
                if (res2.getCount() == 0) {
                 if(meal.equalsIgnoreCase(firstMealTv.getText().toString())||meal.equalsIgnoreCase(secondMealTv.getText().toString())
                            ||meal.equalsIgnoreCase(thirdMealTv.getText().toString())||meal.equalsIgnoreCase(fourthMealTv.getText().toString())){
                        Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                    }
                    else if (myDatabase.searchSunBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchSunSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                    }
                    else{
                    searchSatBreakfast(meal);
                    searchSatLunch(meal);
                    searchSatSupper(meal);
                    myDatabase.insertSunLunchData(meal, nutrition);
                    //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    firstMealTv.setText(meal);
                    firstMealNutrition.setText(nutrition);
                    myDatabase.close();}
                }
                else {

                    String currentMeal = firstMealTv.getText().toString();
                    if(currentMeal.equalsIgnoreCase(meal)){
                        Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                    }
                    else {if(meal.equalsIgnoreCase(firstMealTv.getText().toString())||meal.equalsIgnoreCase(secondMealTv.getText().toString())
                            ||meal.equalsIgnoreCase(thirdMealTv.getText().toString())||meal.equalsIgnoreCase(fourthMealTv.getText().toString())){
                        Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                        }
                        else if (myDatabase.searchSunBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                            Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                        }
                        else if(myDatabase.searchSunSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                            Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                        }
                    else if (firstMealTv.getText().toString().trim().equalsIgnoreCase("")){
                        searchSatBreakfast(meal);
                        searchSatLunch(meal);
                        searchSatSupper(meal);
                        boolean isUpdated = myDatabase.updateSunLunchData(meal, nutrition, "1");
                        if (isUpdated) {
                            firstMealTv.setText(meal);
                            firstMealNutrition.setText(nutrition);
                        } else {
                            Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                        }
                    }
                        else{
                        searchSatBreakfast(meal);
                        searchSatLunch(meal);
                        searchSatSupper(meal);
                        new AlertDialog.Builder(today_meal.this)
                        .setMessage(dialogMsgPt1 + currentMeal + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateSunLunchData(meal, nutrition, "1");
                    if (isUpdated) {
                        //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                        firstMealTv.setText(meal);
                        firstMealNutrition.setText(nutrition);
                    } else {
                        Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                    }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
            }
            //                //Sunday Supper
            else if (mealType.equalsIgnoreCase("Sunday Supper")) {
                Cursor res2 = myDatabase.viewSunSupperMeals();
                if (res2.getCount() == 0) {
                    if(meal.equalsIgnoreCase(firstMealTv.getText().toString())||meal.equalsIgnoreCase(secondMealTv.getText().toString())
                            ||meal.equalsIgnoreCase(thirdMealTv.getText().toString())||meal.equalsIgnoreCase(fourthMealTv.getText().toString())){
                        Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                    }
                    else if (myDatabase.searchSunBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchSunLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                    }
                    else{
                        searchSatBreakfast(meal);
                        searchSatLunch(meal);
                        searchSatSupper(meal);
                        myDatabase.insertSunSupperData(meal, nutrition);
                        //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                        firstMealTv.setText(meal);
                        firstMealNutrition.setText(nutrition);
                        myDatabase.close();}
                }
                 else {

                    String currentMeal = firstMealTv.getText().toString();
                    if(currentMeal.equalsIgnoreCase(meal)){
                        Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                    }
                    else {if(meal.equalsIgnoreCase(firstMealTv.getText().toString())||meal.equalsIgnoreCase(secondMealTv.getText().toString())
                            ||meal.equalsIgnoreCase(thirdMealTv.getText().toString())||meal.equalsIgnoreCase(fourthMealTv.getText().toString())){
                        Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                        }
                       else if (myDatabase.searchSunBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                            Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                        }
                        else if(myDatabase.searchSunLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                            Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                        }
                    else if (firstMealTv.getText().toString().trim().equalsIgnoreCase("")){
                        searchSatBreakfast(meal);
                        searchSatLunch(meal);
                        searchSatSupper(meal);
                        boolean isUpdated = myDatabase.updateSunSupperData(meal, nutrition, "1");
                        if (isUpdated) {
                            firstMealTv.setText(meal);
                            firstMealNutrition.setText(nutrition);
                        } else {
                            Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                        }
                    }
                        else{
                        searchSatBreakfast(meal);
                        searchSatLunch(meal);
                        searchSatSupper(meal);
                        new AlertDialog.Builder(today_meal.this)
                        .setMessage(dialogMsgPt1 + currentMeal + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateSunSupperData(meal, nutrition, "1");
                    if (isUpdated) {
                        //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                        firstMealTv.setText(meal);
                        firstMealNutrition.setText(nutrition);
                    } else {
                        Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                    }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
            }
        }

        else if(pressedTextView.equalsIgnoreCase(mealtv2)){
            if (mealType.equalsIgnoreCase("Sunday Breakfast")) {
                Cursor res = myDatabase.viewSunBreakfastMeals();
            String firstmeal = firstMealTv.getText().toString();
            if (res.getCount() < 1 || firstmeal.trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), firstCell, Toast.LENGTH_SHORT).show();
                }
                else if(meal.equalsIgnoreCase(firstMealTv.getText().toString())){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                }
                else if (res.getCount() == 1) {if(meal.equalsIgnoreCase(firstMealTv.getText().toString())||meal.equalsIgnoreCase(secondMealTv.getText().toString())
                        ||meal.equalsIgnoreCase(thirdMealTv.getText().toString())||meal.equalsIgnoreCase(fourthMealTv.getText().toString())){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                     }
                   else if (myDatabase.searchSunLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchSunSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                    }
                    else{
                    searchSatBreakfast(meal);
                    searchSatLunch(meal);
                    searchSatSupper(meal);
                    myDatabase.insertSunBreakfastData(meal, nutrition);
                    //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    secondMealTv.setText(meal);
                    secondMealNutrition.setText(nutrition);
                    myDatabase.close();}
                }else {

                    String currentMeal1 = firstMealTv.getText().toString();
                    String currentMeal2 = secondMealTv.getText().toString();
                    String currentMeal3 = thirdMealTv.getText().toString();
                    String currentMeal4 = fourthMealTv.getText().toString();
                    String currentMeal5 = fifthMealTv.getText().toString();

                    if(meal.equalsIgnoreCase(currentMeal1)||meal.equalsIgnoreCase(currentMeal2)||meal.equalsIgnoreCase(currentMeal3)||
                            meal.equalsIgnoreCase(currentMeal4)||meal.equalsIgnoreCase(currentMeal5)){
                        Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                    }
                    else {if(meal.equalsIgnoreCase(firstMealTv.getText().toString())||meal.equalsIgnoreCase(secondMealTv.getText().toString())
                            ||meal.equalsIgnoreCase(thirdMealTv.getText().toString())||meal.equalsIgnoreCase(fourthMealTv.getText().toString())){
                        Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                        }
                       else if (myDatabase.searchSunLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                            Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                        }
                        else if(myDatabase.searchSunSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                            Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                        }
                    else if (secondMealTv.getText().toString().trim().equalsIgnoreCase("")){
                        searchSatBreakfast(meal);
                        searchSatLunch(meal);
                        searchSatSupper(meal);
                        boolean isUpdated = myDatabase.updateSunBreakfastData(meal, nutrition, "2");
                        if (isUpdated) {
                            secondMealTv.setText(meal);
                            secondMealNutrition.setText(nutrition);
                        } else {
                            Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                        }
                    }
                        else{
                        searchSatBreakfast(meal);
                        searchSatLunch(meal);
                        searchSatSupper(meal);
                        new AlertDialog.Builder(today_meal.this)
                        .setMessage(dialogMsgPt1 + currentMeal2 + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateSunBreakfastData(meal, nutrition, "2");
                    if (isUpdated) {
                        //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                        secondMealTv.setText(meal);
                        secondMealNutrition.setText(nutrition);
                    } else {
                        Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                    }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
            }
            //                //Sunday Lunch
            else if (mealType.equalsIgnoreCase("Sunday Lunch")) {
                Cursor res = myDatabase.viewSunLunchMeals();
            String firstmeal = firstMealTv.getText().toString();
            if (res.getCount() < 1 || firstmeal.trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), firstCell, Toast.LENGTH_SHORT).show();
                }
                else if(meal.equalsIgnoreCase(firstMealTv.getText().toString())){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                }
                else if (res.getCount() == 1) {
                    if(meal.equalsIgnoreCase(firstMealTv.getText().toString())||meal.equalsIgnoreCase(secondMealTv.getText().toString())
                        ||meal.equalsIgnoreCase(thirdMealTv.getText().toString())||meal.equalsIgnoreCase(fourthMealTv.getText().toString())){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                    }
                    else if (myDatabase.searchSunBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchSunSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                    }
                    else{
                    searchSatBreakfast(meal);
                    searchSatLunch(meal);
                    searchSatSupper(meal);
                    myDatabase.insertSunLunchData(meal, nutrition);
                    //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    secondMealTv.setText(meal);
                    secondMealNutrition.setText(nutrition);
                    myDatabase.close();}
                }else {

                    String currentMeal1 = firstMealTv.getText().toString();
                    String currentMeal2 = secondMealTv.getText().toString();
                    String currentMeal3 = thirdMealTv.getText().toString();
                    String currentMeal4 = fourthMealTv.getText().toString();
                    String currentMeal5 = fifthMealTv.getText().toString();

                    if(meal.equalsIgnoreCase(currentMeal1)||meal.equalsIgnoreCase(currentMeal2)||meal.equalsIgnoreCase(currentMeal3)||
                            meal.equalsIgnoreCase(currentMeal4)||meal.equalsIgnoreCase(currentMeal5)){
                        Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                    }
                    else {
                        if(meal.equalsIgnoreCase(firstMealTv.getText().toString())||meal.equalsIgnoreCase(secondMealTv.getText().toString())
                            ||meal.equalsIgnoreCase(thirdMealTv.getText().toString())||meal.equalsIgnoreCase(fourthMealTv.getText().toString())){
                        Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                        }
                       else if (myDatabase.searchSunBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                            Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                        }
                        else if(myDatabase.searchSunSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                            Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                        }
                        else if (secondMealTv.getText().toString().trim().equalsIgnoreCase("")){
                            searchSatBreakfast(meal);
                            searchSatLunch(meal);
                            searchSatSupper(meal);
                            boolean isUpdated = myDatabase.updateSunLunchData(meal, nutrition, "2");
                            if (isUpdated) {
                                secondMealTv.setText(meal);
                                secondMealNutrition.setText(nutrition);
                            } else {
                                Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                        searchSatBreakfast(meal);
                        searchSatLunch(meal);
                        searchSatSupper(meal);
                        new AlertDialog.Builder(today_meal.this)
                        .setMessage(dialogMsgPt1 + currentMeal2 + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateSunLunchData(meal, nutrition, "2");
                    if (isUpdated) {
                        //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                        secondMealTv.setText(meal);
                        secondMealNutrition.setText(nutrition);
                    } else {
                        Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                    }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
            }
            //                //Sunday Supper
            else if (mealType.equalsIgnoreCase("Sunday Supper")) {
                Cursor res = myDatabase.viewSunSupperMeals();
            String firstmeal = firstMealTv.getText().toString();
            if (res.getCount() < 1 || firstmeal.trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), firstCell, Toast.LENGTH_SHORT).show();
                }
                else if(meal.equalsIgnoreCase(firstMealTv.getText().toString())){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                }
                else if (res.getCount() == 1) {
                    if(meal.equalsIgnoreCase(firstMealTv.getText().toString())||meal.equalsIgnoreCase(secondMealTv.getText().toString())
                            ||meal.equalsIgnoreCase(thirdMealTv.getText().toString())||meal.equalsIgnoreCase(fourthMealTv.getText().toString())){
                        Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                    }
                    else if (myDatabase.searchSunBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchSunLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                    }
                    else{
                    searchSatBreakfast(meal);
                    searchSatLunch(meal);
                    searchSatSupper(meal);
                    myDatabase.insertSunSupperData(meal, nutrition);
                    //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    secondMealTv.setText(meal);
                    secondMealNutrition.setText(nutrition);
                    myDatabase.close();}
                }else {

                    String currentMeal1 = firstMealTv.getText().toString();
                    String currentMeal2 = secondMealTv.getText().toString();
                    String currentMeal3 = thirdMealTv.getText().toString();
                    String currentMeal4 = fourthMealTv.getText().toString();
                    String currentMeal5 = fifthMealTv.getText().toString();

                    if(meal.equalsIgnoreCase(currentMeal1)||meal.equalsIgnoreCase(currentMeal2)||meal.equalsIgnoreCase(currentMeal3)||
                            meal.equalsIgnoreCase(currentMeal4)||meal.equalsIgnoreCase(currentMeal5)){
                        Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                    }
                    else {
                        if(meal.equalsIgnoreCase(firstMealTv.getText().toString())||meal.equalsIgnoreCase(secondMealTv.getText().toString())
                                ||meal.equalsIgnoreCase(thirdMealTv.getText().toString())||meal.equalsIgnoreCase(fourthMealTv.getText().toString())){
                            Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                        }
                        else if (myDatabase.searchSunBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                            Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                        }
                        else if(myDatabase.searchSunLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                            Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                        }
                        else if (secondMealTv.getText().toString().trim().equalsIgnoreCase("")){
                            searchSatBreakfast(meal);
                            searchSatLunch(meal);
                            searchSatSupper(meal);
                            boolean isUpdated = myDatabase.updateSatSupperData(meal, nutrition, "2");
                            if (isUpdated) {
                                secondMealTv.setText(meal);
                                secondMealNutrition.setText(nutrition);
                            } else {
                                Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                        searchSatBreakfast(meal);
                        searchSatLunch(meal);
                        searchSatSupper(meal);
                        new AlertDialog.Builder(today_meal.this)
                        .setMessage(dialogMsgPt1 + currentMeal2 + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateSunSupperData(meal, nutrition, "2");
                    if (isUpdated) {
                        //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                        secondMealTv.setText(meal);
                        secondMealNutrition.setText(nutrition);
                    } else {
                        Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                    }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
            }
        }
        else if(pressedTextView.equalsIgnoreCase(mealtv3)){
            if (mealType.equalsIgnoreCase("Sunday Breakfast")) {
                Cursor res = myDatabase.viewSunBreakfastMeals();
            String firstmeal = firstMealTv.getText().toString();
            if (res.getCount() < 1 || firstmeal.trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), firstCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 2||secondMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), secondCell, Toast.LENGTH_SHORT).show();
                }
                else if(meal.equalsIgnoreCase(firstMealTv.getText().toString())||meal.equalsIgnoreCase(secondMealTv.getText().toString())){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                }
                else if (res.getCount() == 2) {
                    if(meal.equalsIgnoreCase(firstMealTv.getText().toString())||meal.equalsIgnoreCase(secondMealTv.getText().toString())
                            ||meal.equalsIgnoreCase(thirdMealTv.getText().toString())||meal.equalsIgnoreCase(fourthMealTv.getText().toString())){
                        Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                    }
                    else if (myDatabase.searchSunLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchSunSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                    }
                    else{
                    searchSatBreakfast(meal);
                    searchSatLunch(meal);
                    searchSatSupper(meal);
                    myDatabase.insertSunBreakfastData(meal, nutrition);
                    //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    thirdMealTv.setText(meal);
                    thirdMealNutrition.setText(nutrition);
                    myDatabase.close();}
                } else {

                String currentMeal1 = firstMealTv.getText().toString();
                String currentMeal2 = secondMealTv.getText().toString();
                String currentMeal3 = thirdMealTv.getText().toString();
                String currentMeal4 = fourthMealTv.getText().toString();
                String currentMeal5 = fifthMealTv.getText().toString();

                if(meal.equalsIgnoreCase(currentMeal1)||meal.equalsIgnoreCase(currentMeal2)||meal.equalsIgnoreCase(currentMeal3)||
                        meal.equalsIgnoreCase(currentMeal4)||meal.equalsIgnoreCase(currentMeal5)){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                }
                else {
                    if(meal.equalsIgnoreCase(firstMealTv.getText().toString())||meal.equalsIgnoreCase(secondMealTv.getText().toString())
                            ||meal.equalsIgnoreCase(thirdMealTv.getText().toString())||meal.equalsIgnoreCase(fourthMealTv.getText().toString())){
                        Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                    }
                    else if (myDatabase.searchSunLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchSunSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                    }
                    else if (thirdMealTv.getText().toString().trim().equalsIgnoreCase("")){
                        searchSatBreakfast(meal);
                        searchSatLunch(meal);
                        searchSatSupper(meal);
                        boolean isUpdated = myDatabase.updateSunBreakfastData(meal, nutrition, "3");
                        if (isUpdated) {
                            thirdMealTv.setText(meal);
                            thirdMealNutrition.setText(nutrition);
                        } else {
                            Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                    searchSatBreakfast(meal);
                    searchSatLunch(meal);
                    searchSatSupper(meal);
                    new AlertDialog.Builder(today_meal.this)
                        .setMessage(dialogMsgPt1 + currentMeal3 + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateSunBreakfastData(meal, nutrition, "3");
                    if (isUpdated) {
                        //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                        thirdMealTv.setText(meal);
                        thirdMealNutrition.setText(nutrition);
                    } else {
                        Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                    }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
            }
            //                //Sunday Lunch
            else if (mealType.equalsIgnoreCase("Sunday Lunch")) {
                Cursor res = myDatabase.viewSunLunchMeals();
            String firstmeal = firstMealTv.getText().toString();
            if (res.getCount() < 1 || firstmeal.trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), firstCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 2||secondMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), secondCell, Toast.LENGTH_SHORT).show();
                }
                else if(meal.equalsIgnoreCase(firstMealTv.getText().toString())||meal.equalsIgnoreCase(secondMealTv.getText().toString())){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                }
                else if (res.getCount() == 2) {

                    if(meal.equalsIgnoreCase(firstMealTv.getText().toString())||meal.equalsIgnoreCase(secondMealTv.getText().toString())
                            ||meal.equalsIgnoreCase(thirdMealTv.getText().toString())||meal.equalsIgnoreCase(fourthMealTv.getText().toString())){
                        Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                    }
                    else if (myDatabase.searchSunBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchSunSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                    }
                    else{
                    searchSatBreakfast(meal);
                    searchSatLunch(meal);
                    searchSatSupper(meal);
                    myDatabase.insertSunLunchData(meal, nutrition);
                    //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    thirdMealTv.setText(meal);
                    thirdMealNutrition.setText(nutrition);
                    myDatabase.close();}
                } else {

                String currentMeal1 = firstMealTv.getText().toString();
                String currentMeal2 = secondMealTv.getText().toString();
                String currentMeal3 = thirdMealTv.getText().toString();
                String currentMeal4 = fourthMealTv.getText().toString();
                String currentMeal5 = fifthMealTv.getText().toString();

                if(meal.equalsIgnoreCase(currentMeal1)||meal.equalsIgnoreCase(currentMeal2)||meal.equalsIgnoreCase(currentMeal3)||
                        meal.equalsIgnoreCase(currentMeal4)||meal.equalsIgnoreCase(currentMeal5)){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                }
                else {
                    if(meal.equalsIgnoreCase(firstMealTv.getText().toString())||meal.equalsIgnoreCase(secondMealTv.getText().toString())
                            ||meal.equalsIgnoreCase(thirdMealTv.getText().toString())||meal.equalsIgnoreCase(fourthMealTv.getText().toString())){
                        Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                    }
                    else if (myDatabase.searchSunBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchSunSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                    }
                    else if (thirdMealTv.getText().toString().trim().equalsIgnoreCase("")){
                        searchSatBreakfast(meal);
                        searchSatLunch(meal);
                        searchSatSupper(meal);
                        boolean isUpdated = myDatabase.updateSunLunchData(meal, nutrition, "3");
                        if (isUpdated) {
                            thirdMealTv.setText(meal);
                            thirdMealNutrition.setText(nutrition);
                        } else {
                            Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                    searchSatBreakfast(meal);
                    searchSatLunch(meal);
                    searchSatSupper(meal);
                    new AlertDialog.Builder(today_meal.this)
                        .setMessage(dialogMsgPt1 + currentMeal3 + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateSunLunchData(meal, nutrition, "3");
                    if (isUpdated) {
                        //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                        thirdMealTv.setText(meal);
                        thirdMealNutrition.setText(nutrition);
                    } else {
                        Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                    }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
            }
            //                //Sunday Supper yuo
            else if (mealType.equalsIgnoreCase("Sunday Supper")) {
                Cursor res = myDatabase.viewSunSupperMeals();
            String firstmeal = firstMealTv.getText().toString();
            if (res.getCount() < 1 || firstmeal.trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), firstCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 2||secondMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), secondCell, Toast.LENGTH_SHORT).show();
                }
                else if(meal.equalsIgnoreCase(firstMealTv.getText().toString())||meal.equalsIgnoreCase(secondMealTv.getText().toString())){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                }
                else if (res.getCount() == 2) {
                    if(meal.equalsIgnoreCase(firstMealTv.getText().toString())||meal.equalsIgnoreCase(secondMealTv.getText().toString())
                            ||meal.equalsIgnoreCase(thirdMealTv.getText().toString())||meal.equalsIgnoreCase(fourthMealTv.getText().toString())){
                        Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                    }
                    else if (myDatabase.searchSunBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchSunLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                    }
                    else{
                    searchSatBreakfast(meal);
                    searchSatLunch(meal);
                    searchSatSupper(meal);
                    myDatabase.insertSunSupperData(meal, nutrition);
                    //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    thirdMealTv.setText(meal);
                    thirdMealNutrition.setText(nutrition);
                    myDatabase.close();}
                } else {

                String currentMeal1 = firstMealTv.getText().toString();
                String currentMeal2 = secondMealTv.getText().toString();
                String currentMeal3 = thirdMealTv.getText().toString();
                String currentMeal4 = fourthMealTv.getText().toString();
                String currentMeal5 = fifthMealTv.getText().toString();

                if(meal.equalsIgnoreCase(currentMeal1)||meal.equalsIgnoreCase(currentMeal2)||meal.equalsIgnoreCase(currentMeal3)||
                        meal.equalsIgnoreCase(currentMeal4)||meal.equalsIgnoreCase(currentMeal5)){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                }
                else {
                    if(meal.equalsIgnoreCase(firstMealTv.getText().toString())||meal.equalsIgnoreCase(secondMealTv.getText().toString())
                            ||meal.equalsIgnoreCase(thirdMealTv.getText().toString())||meal.equalsIgnoreCase(fourthMealTv.getText().toString())){
                        Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                    }
                    else if (myDatabase.searchSunBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchSunLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                    }
                    else if (thirdMealTv.getText().toString().trim().equalsIgnoreCase("")){
                        searchSatBreakfast(meal);
                        searchSatLunch(meal);
                        searchSatSupper(meal);
                        boolean isUpdated = myDatabase.updateSunSupperData(meal, nutrition, "3");
                        if (isUpdated) {
                            thirdMealTv.setText(meal);
                            thirdMealNutrition.setText(nutrition);
                        } else {
                            Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                    searchSatBreakfast(meal);
                    searchSatLunch(meal);
                    searchSatSupper(meal);
                    new AlertDialog.Builder(today_meal.this)
                        .setMessage(dialogMsgPt1 + currentMeal3 + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateSunSupperData(meal, nutrition, "3");
                    if (isUpdated) {
                        //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                        thirdMealTv.setText(meal);
                        thirdMealNutrition.setText(nutrition);
                    } else {
                        Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                    }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
            }
        }
        else if(pressedTextView.equalsIgnoreCase(mealtv4)){

            if (mealType.equalsIgnoreCase("Sunday Breakfast")) {
                Cursor res = myDatabase.viewSunBreakfastMeals();
            String firstmeal = firstMealTv.getText().toString();
            if (res.getCount() < 1 || firstmeal.trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), firstCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 2||secondMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), secondCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 3||thirdMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), thirdCell, Toast.LENGTH_SHORT).show();
                }
                else if(meal.equalsIgnoreCase(firstMealTv.getText().toString())||meal.equalsIgnoreCase(secondMealTv.getText().toString())
                        ||meal.equalsIgnoreCase(thirdMealTv.getText().toString())){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                }
                else if (res.getCount() == 3) {
                    if(meal.equalsIgnoreCase(firstMealTv.getText().toString())||meal.equalsIgnoreCase(secondMealTv.getText().toString())
                            ||meal.equalsIgnoreCase(thirdMealTv.getText().toString())||meal.equalsIgnoreCase(fourthMealTv.getText().toString())){
                        Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                    }
                    else if (myDatabase.searchSunLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchSunSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                    }
                    else{
                    searchSatBreakfast(meal);
                    searchSatLunch(meal);
                    searchSatSupper(meal);
                    myDatabase.insertSunBreakfastData(meal, nutrition);
                    //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    fourthMealTv.setText(meal);
                    fourthMealNutrition.setText(nutrition);
                    myDatabase.close();}
                }  else {

                    String currentMeal1 = firstMealTv.getText().toString();
                    String currentMeal2 = secondMealTv.getText().toString();
                    String currentMeal3 = thirdMealTv.getText().toString();
                    String currentMeal4 = fourthMealTv.getText().toString();
                    String currentMeal5 = fifthMealTv.getText().toString();

                    if(meal.equalsIgnoreCase(currentMeal1)||meal.equalsIgnoreCase(currentMeal2)||meal.equalsIgnoreCase(currentMeal3)||
                            meal.equalsIgnoreCase(currentMeal4)||meal.equalsIgnoreCase(currentMeal5)){
                        Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                    }
                    else {
                        if(meal.equalsIgnoreCase(firstMealTv.getText().toString())||meal.equalsIgnoreCase(secondMealTv.getText().toString())
                                ||meal.equalsIgnoreCase(thirdMealTv.getText().toString())||meal.equalsIgnoreCase(fourthMealTv.getText().toString())){
                            Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                        }
                        else if (myDatabase.searchSunLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                            Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                        }
                        else if(myDatabase.searchSunSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                            Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                        }
                        else if (fourthMealTv.getText().toString().trim().equalsIgnoreCase("")){
                            searchSatBreakfast(meal);
                            searchSatLunch(meal);
                            searchSatSupper(meal);
                            boolean isUpdated = myDatabase.updateSunBreakfastData(meal, nutrition, "4");
                            if (isUpdated) {
                                fourthMealTv.setText(meal);
                                fourthMealNutrition.setText(nutrition);
                            } else {
                                Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                        searchSatBreakfast(meal);
                        searchSatLunch(meal);
                        searchSatSupper(meal);
                        new AlertDialog.Builder(today_meal.this)
                                .setMessage(dialogMsgPt1 + currentMeal4 + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateSunBreakfastData(meal, nutrition, "4");
                    if (isUpdated) {
                        //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                        fourthMealTv.setText(meal);
                        fourthMealNutrition.setText(nutrition);
                    } else {
                        Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                    }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
            }
            //                //Sunday Lunch
            else if (mealType.equalsIgnoreCase("Sunday Lunch")) {
                Cursor res = myDatabase.viewSunLunchMeals();
            String firstmeal = firstMealTv.getText().toString();
            if (res.getCount() < 1 || firstmeal.trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), firstCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 2||secondMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), secondCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 3||thirdMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), thirdCell, Toast.LENGTH_SHORT).show();
                }
                else if(meal.equalsIgnoreCase(firstMealTv.getText().toString())||meal.equalsIgnoreCase(secondMealTv.getText().toString())
                        ||meal.equalsIgnoreCase(thirdMealTv.getText().toString())){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                }
                else if (res.getCount() == 3) {
                    if(meal.equalsIgnoreCase(firstMealTv.getText().toString())||meal.equalsIgnoreCase(secondMealTv.getText().toString())
                            ||meal.equalsIgnoreCase(thirdMealTv.getText().toString())||meal.equalsIgnoreCase(fourthMealTv.getText().toString())){
                        Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                    }
                    else if (myDatabase.searchSunLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchSunSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                    }
                    else{
                    searchSatBreakfast(meal);
                    searchSatLunch(meal);
                    searchSatSupper(meal);
                    myDatabase.insertSunLunchData(meal, nutrition);
                    //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    fourthMealTv.setText(meal);
                    fourthMealNutrition.setText(nutrition);
                    myDatabase.close();}
                }  else {

                    String currentMeal1 = firstMealTv.getText().toString();
                    String currentMeal2 = secondMealTv.getText().toString();
                    String currentMeal3 = thirdMealTv.getText().toString();
                    String currentMeal4 = fourthMealTv.getText().toString();
                    String currentMeal5 = fifthMealTv.getText().toString();

                    if(meal.equalsIgnoreCase(currentMeal1)||meal.equalsIgnoreCase(currentMeal2)||meal.equalsIgnoreCase(currentMeal3)||
                            meal.equalsIgnoreCase(currentMeal4)||meal.equalsIgnoreCase(currentMeal5)){
                        Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                    }
                    else {
                        if(meal.equalsIgnoreCase(firstMealTv.getText().toString())||meal.equalsIgnoreCase(secondMealTv.getText().toString())
                                ||meal.equalsIgnoreCase(thirdMealTv.getText().toString())||meal.equalsIgnoreCase(fourthMealTv.getText().toString())){
                            Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                        }
                        else if (myDatabase.searchSunLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                            Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                        }
                        else if(myDatabase.searchSunSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                            Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                        }
                        else if (fourthMealTv.getText().toString().trim().equalsIgnoreCase("")){
                            searchSatBreakfast(meal);
                            searchSatLunch(meal);
                            searchSatSupper(meal);
                            boolean isUpdated = myDatabase.updateSunLunchData(meal, nutrition, "4");
                            if (isUpdated) {
                                fourthMealTv.setText(meal);
                                fourthMealNutrition.setText(nutrition);
                            } else {
                                Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                        searchSatBreakfast(meal);
                        searchSatLunch(meal);
                        searchSatSupper(meal);
                        new AlertDialog.Builder(today_meal.this)
                                .setMessage(dialogMsgPt1 + currentMeal4 + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateSunLunchData(meal, nutrition, "4");
                    if (isUpdated) {
                        //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                        fourthMealTv.setText(meal);
                        fourthMealNutrition.setText(nutrition);
                    } else {
                        Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                    }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
            }
            //                //Sunday Supper
            else if (mealType.equalsIgnoreCase("Sunday Supper")) {
                Cursor res = myDatabase.viewSunSupperMeals();
            String firstmeal = firstMealTv.getText().toString();
            if (res.getCount() < 1 || firstmeal.trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), firstCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 2||secondMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), secondCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 3||thirdMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), thirdCell, Toast.LENGTH_SHORT).show();
                }
                else if(meal.equalsIgnoreCase(firstMealTv.getText().toString())||meal.equalsIgnoreCase(secondMealTv.getText().toString())
                        ||meal.equalsIgnoreCase(thirdMealTv.getText().toString())){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                }
                else if (res.getCount() == 3) {
                    if(meal.equalsIgnoreCase(firstMealTv.getText().toString())||meal.equalsIgnoreCase(secondMealTv.getText().toString())
                            ||meal.equalsIgnoreCase(thirdMealTv.getText().toString())||meal.equalsIgnoreCase(fourthMealTv.getText().toString())){
                        Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                    }
                    else if (myDatabase.searchSunBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchSunLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                    }
                    else{
                    searchSatBreakfast(meal);
                    searchSatLunch(meal);
                    searchSatSupper(meal);
                    myDatabase.insertSunSupperData(meal, nutrition);
                    //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    fourthMealTv.setText(meal);
                    fourthMealNutrition.setText(nutrition);
                    myDatabase.close();}
                }  else {

                    String currentMeal1 = firstMealTv.getText().toString();
                    String currentMeal2 = secondMealTv.getText().toString();
                    String currentMeal3 = thirdMealTv.getText().toString();
                    String currentMeal4 = fourthMealTv.getText().toString();
                    String currentMeal5 = fifthMealTv.getText().toString();

                    if(meal.equalsIgnoreCase(currentMeal1)||meal.equalsIgnoreCase(currentMeal2)||meal.equalsIgnoreCase(currentMeal3)||
                            meal.equalsIgnoreCase(currentMeal4)||meal.equalsIgnoreCase(currentMeal5)){
                        Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                    }
                    else {
                        if(meal.equalsIgnoreCase(firstMealTv.getText().toString())||meal.equalsIgnoreCase(secondMealTv.getText().toString())
                                ||meal.equalsIgnoreCase(thirdMealTv.getText().toString())||meal.equalsIgnoreCase(fourthMealTv.getText().toString())){
                            Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                        }
                        else if (myDatabase.searchSunBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                            Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                        }
                        else if(myDatabase.searchSunLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                            Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                        }
                        else if (fourthMealTv.getText().toString().trim().equalsIgnoreCase("")){
                            searchSatBreakfast(meal);
                            searchSatLunch(meal);
                            searchSatSupper(meal);
                            boolean isUpdated = myDatabase.updateSunSupperData(meal, nutrition, "4");
                            if (isUpdated) {
                                fourthMealTv.setText(meal);
                                fourthMealNutrition.setText(nutrition);
                            } else {
                                Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                        searchSatBreakfast(meal);
                        searchSatLunch(meal);
                        searchSatSupper(meal);
                        new AlertDialog.Builder(today_meal.this)
                                .setMessage(dialogMsgPt1 + currentMeal4 + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateSunSupperData(meal, nutrition, "4");
                    if (isUpdated) {
                        //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                        fourthMealTv.setText(meal);
                        fourthMealNutrition.setText(nutrition);
                    } else {
                        Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                    }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
            }
        }

        else if(pressedTextView.equalsIgnoreCase(mealtv5)){
            if (mealType.equalsIgnoreCase("Sunday Breakfast")) {
                Cursor res = myDatabase.viewSunBreakfastMeals();
            String firstmeal = firstMealTv.getText().toString();
            if (res.getCount() < 1 || firstmeal.trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), firstCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 2||secondMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), secondCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 3||thirdMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), thirdCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 4||fourthMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), fourthCell, Toast.LENGTH_SHORT).show();
                }
                else if(meal.equalsIgnoreCase(firstMealTv.getText().toString())||meal.equalsIgnoreCase(secondMealTv.getText().toString())
                        ||meal.equalsIgnoreCase(thirdMealTv.getText().toString())||meal.equalsIgnoreCase(fourthMealTv.getText().toString())){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                }
                else if (res.getCount() == 4) {
                    if(meal.equalsIgnoreCase(firstMealTv.getText().toString())||meal.equalsIgnoreCase(secondMealTv.getText().toString())
                            ||meal.equalsIgnoreCase(thirdMealTv.getText().toString())||meal.equalsIgnoreCase(fourthMealTv.getText().toString())){
                        Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                    }
                    else if (myDatabase.searchSunLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchSunSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                    }
                    else{
                    searchSatBreakfast(meal);
                    searchSatLunch(meal);
                    searchSatSupper(meal);
                    myDatabase.insertSunBreakfastData(meal, nutrition);
                    //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    fifthMealTv.setText(meal);
                    fifthMealNutrition.setText(nutrition);
                    myDatabase.close();}
                }  else {

                String currentMeal1 = firstMealTv.getText().toString();
                String currentMeal2 = secondMealTv.getText().toString();
                String currentMeal3 = thirdMealTv.getText().toString();
                String currentMeal4 = fourthMealTv.getText().toString();
                String currentMeal5 = fifthMealTv.getText().toString();

                if(meal.equalsIgnoreCase(currentMeal1)||meal.equalsIgnoreCase(currentMeal2)||meal.equalsIgnoreCase(currentMeal3)||
                        meal.equalsIgnoreCase(currentMeal4)||meal.equalsIgnoreCase(currentMeal5)){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                }
                else {
                    if(meal.equalsIgnoreCase(firstMealTv.getText().toString())||meal.equalsIgnoreCase(secondMealTv.getText().toString())
                            ||meal.equalsIgnoreCase(thirdMealTv.getText().toString())||meal.equalsIgnoreCase(fourthMealTv.getText().toString())){
                        Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                    }
                    else if (myDatabase.searchSunLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchSunSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                    }
                    else if (fifthMealTv.getText().toString().trim().equalsIgnoreCase("")){
                        searchSatBreakfast(meal);
                        searchSatLunch(meal);
                        searchSatSupper(meal);
                        boolean isUpdated = myDatabase.updateSunBreakfastData(meal, nutrition, "5");
                        if (isUpdated) {
                            fifthMealTv.setText(meal);
                            fifthMealNutrition.setText(nutrition);
                        } else {
                            Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                    searchSatBreakfast(meal);
                    searchSatLunch(meal);
                    searchSatSupper(meal);
                    new AlertDialog.Builder(today_meal.this)
                            .setMessage(dialogMsgPt1 + currentMeal5 + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateSunBreakfastData(meal, nutrition, "5");
                    if (isUpdated) {
                        //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                        fifthMealTv.setText(meal);
                        fifthMealNutrition.setText(nutrition);
                    } else {
                        Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                    }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
            }
            //                //Sunday Lunch
            else if (mealType.equalsIgnoreCase("Sunday Lunch")) {
                Cursor res = myDatabase.viewSunLunchMeals();
            String firstmeal = firstMealTv.getText().toString();
            if (res.getCount() < 1 || firstmeal.trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), firstCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 2||secondMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), secondCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 3||thirdMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), thirdCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 4||fourthMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), fourthCell, Toast.LENGTH_SHORT).show();
                }
                else if(meal.equalsIgnoreCase(firstMealTv.getText().toString())||meal.equalsIgnoreCase(secondMealTv.getText().toString())
                        ||meal.equalsIgnoreCase(thirdMealTv.getText().toString())||meal.equalsIgnoreCase(fourthMealTv.getText().toString())){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                }
                else if (res.getCount() == 4) {
                    if(meal.equalsIgnoreCase(firstMealTv.getText().toString())||meal.equalsIgnoreCase(secondMealTv.getText().toString())
                            ||meal.equalsIgnoreCase(thirdMealTv.getText().toString())||meal.equalsIgnoreCase(fourthMealTv.getText().toString())){
                        Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                    }
                    else if (myDatabase.searchSunBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchSunSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                    }
                    else{
                    searchSatBreakfast(meal);
                    searchSatLunch(meal);
                    searchSatSupper(meal);
                    myDatabase.insertSunLunchData(meal, nutrition);
                    //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    fifthMealTv.setText(meal);
                    fifthMealNutrition.setText(nutrition);
                    myDatabase.close();}
                }  else {

                String currentMeal1 = firstMealTv.getText().toString();
                String currentMeal2 = secondMealTv.getText().toString();
                String currentMeal3 = thirdMealTv.getText().toString();
                String currentMeal4 = fourthMealTv.getText().toString();
                String currentMeal5 = fifthMealTv.getText().toString();

                if(meal.equalsIgnoreCase(currentMeal1)||meal.equalsIgnoreCase(currentMeal2)||meal.equalsIgnoreCase(currentMeal3)||
                        meal.equalsIgnoreCase(currentMeal4)||meal.equalsIgnoreCase(currentMeal5)){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                }
                else {
                    if(meal.equalsIgnoreCase(firstMealTv.getText().toString())||meal.equalsIgnoreCase(secondMealTv.getText().toString())
                            ||meal.equalsIgnoreCase(thirdMealTv.getText().toString())||meal.equalsIgnoreCase(fourthMealTv.getText().toString())){
                        Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                    }
                    else if (myDatabase.searchSunBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchSunSupperMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodaySupper,Toast.LENGTH_LONG).show();
                    }
                    else if (fifthMealTv.getText().toString().trim().equalsIgnoreCase("")){
                        searchSatBreakfast(meal);
                        searchSatLunch(meal);
                        searchSatSupper(meal);
                        boolean isUpdated = myDatabase.updateSunLunchData(meal, nutrition, "5");
                        if (isUpdated) {
                            fifthMealTv.setText(meal);
                            fifthMealNutrition.setText(nutrition);
                        } else {
                            Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                    searchSatBreakfast(meal);
                    searchSatLunch(meal);
                    searchSatSupper(meal);
                    new AlertDialog.Builder(today_meal.this)
                            .setMessage(dialogMsgPt1 + currentMeal5 + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateSunLunchData(meal, nutrition, "5");
                    if (isUpdated) {
                        //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                        fifthMealTv.setText(meal);
                        fifthMealNutrition.setText(nutrition);
                    } else {
                        Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                    }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
            }
            //                //Sunday Supper
            else if (mealType.equalsIgnoreCase("Sunday Supper")) {
                Cursor res = myDatabase.viewSunSupperMeals();
            String firstmeal = firstMealTv.getText().toString();
            if (res.getCount() < 1 || firstmeal.trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), firstCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 2||secondMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), secondCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 3||thirdMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), thirdCell, Toast.LENGTH_SHORT).show();
                }
            else if (res.getCount() < 4||fourthMealTv.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), fourthCell, Toast.LENGTH_SHORT).show();
                }
                else if(meal.equalsIgnoreCase(firstMealTv.getText().toString())||meal.equalsIgnoreCase(secondMealTv.getText().toString())
                        ||meal.equalsIgnoreCase(thirdMealTv.getText().toString())||meal.equalsIgnoreCase(fourthMealTv.getText().toString())){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                }
                else if (res.getCount() == 4) {
                    if(meal.equalsIgnoreCase(firstMealTv.getText().toString())||meal.equalsIgnoreCase(secondMealTv.getText().toString())
                            ||meal.equalsIgnoreCase(thirdMealTv.getText().toString())||meal.equalsIgnoreCase(fourthMealTv.getText().toString())){
                        Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                    }
                    else if (myDatabase.searchSunBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchSunLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                    }
                    else{
                    searchSatBreakfast(meal);
                    searchSatLunch(meal);
                    searchSatSupper(meal);
                    myDatabase.insertSunSupperData(meal, nutrition);
                    //Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                    fifthMealTv.setText(meal);
                    fifthMealNutrition.setText(nutrition);
                    myDatabase.close();}
                }  else {

                String currentMeal1 = firstMealTv.getText().toString();
                String currentMeal2 = secondMealTv.getText().toString();
                String currentMeal3 = thirdMealTv.getText().toString();
                String currentMeal4 = fourthMealTv.getText().toString();
                String currentMeal5 = fifthMealTv.getText().toString();

                if(meal.equalsIgnoreCase(currentMeal1)||meal.equalsIgnoreCase(currentMeal2)||meal.equalsIgnoreCase(currentMeal3)||
                        meal.equalsIgnoreCase(currentMeal4)||meal.equalsIgnoreCase(currentMeal5)){
                    Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
//                        foodInfoMessage(titleForMealChange,forSameMealsChange+mealType.substring(day.length()+1).toLowerCase()); //stophere
                }
                else {
                    if(meal.equalsIgnoreCase(firstMealTv.getText().toString())||meal.equalsIgnoreCase(secondMealTv.getText().toString())
                            ||meal.equalsIgnoreCase(thirdMealTv.getText().toString())||meal.equalsIgnoreCase(fourthMealTv.getText().toString())){
                        Toast.makeText(getApplicationContext(), forSameMealsChange+mealType.substring(day.length()+1).toLowerCase(), Toast.LENGTH_SHORT).show();
                    }
                    else if (myDatabase.searchSunBreakfastMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
                        Toast.makeText(getApplicationContext(),alreadyExistTodayBreak,Toast.LENGTH_LONG).show();
                    }
                    else if(myDatabase.searchSunLunchMealsSpecific(meal).getCount()!=0&& !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){

                        Toast.makeText(getApplicationContext(),alreadyExistTodayLunch,Toast.LENGTH_LONG).show();
                    }
                    else if (fifthMealTv.getText().toString().trim().equalsIgnoreCase("")){
                        searchSatBreakfast(meal);
                        searchSatLunch(meal);
                        searchSatSupper(meal);
                        boolean isUpdated = myDatabase.updateSunSupperData(meal, nutrition, "5");
                        if (isUpdated) {
                            fifthMealTv.setText(meal);
                            fifthMealNutrition.setText(nutrition);
                        } else {
                            Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                    searchSatBreakfast(meal);
                    searchSatLunch(meal);
                    searchSatSupper(meal);
                    new AlertDialog.Builder(today_meal.this)
                            .setMessage(dialogMsgPt1 + currentMeal5 + dialogMsgPt2 + meal + dialogMsgPt3)
                        .setPositiveButton(dialogMsgPositive, (dialog, id) -> {

                        boolean isUpdated = myDatabase.updateSunSupperData(meal, nutrition, "5");
                    if (isUpdated) {
                        //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                        fifthMealTv.setText(meal);
                        fifthMealNutrition.setText(nutrition);
                    } else {
                        Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_SHORT).show();
                    }

                    })
                    .setNegativeButton(dialogMsgNegative, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }}).show();}

                    myDatabase.close();
                }}
            }
        }

    }//END OF SUNDAY MEALS

    public void clearTextFields(){
        firstMealTv.setText("");
        firstMealNutrition.setText("");
        secondMealTv.setText("");
        secondMealNutrition.setText("");
        thirdMealTv.setText("");
        thirdMealNutrition.setText("");
        fourthMealTv.setText("");
        fourthMealNutrition.setText("");
        fifthMealTv.setText("");
        fifthMealNutrition.setText("");
    }


    //searcing yesterday meals and gives an advise if found
    public void searchMonBreakfast(String meal){
        Cursor res2=  myDatabase.searchMonBreakfastMealsSpecific(meal);
        if (res2.getCount()!=0 && !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
            Toast.makeText(getApplicationContext(), yesterdayBreakfast, Toast.LENGTH_LONG).show();
        }
    }
    public void searchMonLunch(String meal){
        Cursor res2=  myDatabase.searchMonLunchMealsSpecific(meal);
        if (res2.getCount()!=0 && !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
            Toast.makeText(getApplicationContext(), yesterdayLunch, Toast.LENGTH_LONG).show();
        }
    }
    public void searchMonSupper(String meal){
        Cursor res2=  myDatabase.searchMonSupperMealsSpecific(meal);
        if (res2.getCount()!=0 && !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
            Toast.makeText(getApplicationContext(), yesterdaySupper, Toast.LENGTH_LONG).show();
        }
    }
    public void searchTueBreakfast(String meal){
        Cursor res2=  myDatabase.searchTueBreakfastMealsSpecific(meal);
        if (res2.getCount()!=0 && !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
            Toast.makeText(getApplicationContext(), yesterdayBreakfast, Toast.LENGTH_LONG).show();
        }
    }
    public void searchTueLunch(String meal){
        Cursor res2=  myDatabase.searchTueLunchMealsSpecific(meal);
        if (res2.getCount()!=0 && !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
            Toast.makeText(getApplicationContext(), yesterdayLunch, Toast.LENGTH_LONG).show();
        }
    }
    public void searchTueSupper(String meal){
        Cursor res2=  myDatabase.searchTueSupperMealsSpecific(meal);
        if (res2.getCount()!=0 && !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
            Toast.makeText(getApplicationContext(), yesterdaySupper, Toast.LENGTH_LONG).show();
        }
    }
    public void searchWedBreakfast(String meal){
        Cursor res2=  myDatabase.searchWedBreakfastMealsSpecific(meal);
        if (res2.getCount()!=0 && !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
            Toast.makeText(getApplicationContext(), yesterdayBreakfast, Toast.LENGTH_LONG).show();
        }
    }
    public void searchWedLunch(String meal){
        Cursor res2=  myDatabase.searchWedLunchMealsSpecific(meal);
        if (res2.getCount()!=0 && !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
            Toast.makeText(getApplicationContext(), yesterdayLunch, Toast.LENGTH_LONG).show();
        }
    }
    public void searchWedSupper(String meal){
        Cursor res2=  myDatabase.searchWedSupperMealsSpecific(meal);
        if (res2.getCount()!=0 && !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
            Toast.makeText(getApplicationContext(), yesterdaySupper, Toast.LENGTH_LONG).show();
        }
    }
    public void searchThurBreakfast(String meal){
        Cursor res2=  myDatabase.searchThurBreakfastMealsSpecific(meal);
        if (res2.getCount()!=0 && !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
            Toast.makeText(getApplicationContext(), yesterdayBreakfast, Toast.LENGTH_LONG).show();
        }
    }
    public void searchThurLunch(String meal){
        Cursor res2=  myDatabase.searchThurLunchMealsSpecific(meal);
        if (res2.getCount()!=0 && !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
            Toast.makeText(getApplicationContext(), yesterdayLunch, Toast.LENGTH_LONG).show();
        }
    }
    public void searchThurSupper(String meal){
        Cursor res2=  myDatabase.searchThurSupperMealsSpecific(meal);
        if (res2.getCount()!=0 && !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
            Toast.makeText(getApplicationContext(), yesterdaySupper, Toast.LENGTH_LONG).show();
        }
    }
    public void searchFriBreakfast(String meal){
        Cursor res2=  myDatabase.searchFriBreakfastMealsSpecific(meal);
        if (res2.getCount()!=0 && !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
            Toast.makeText(getApplicationContext(), yesterdayBreakfast, Toast.LENGTH_LONG).show();
        }
    }
    public void searchFriLunch(String meal){
        Cursor res2=  myDatabase.searchFriLunchMealsSpecific(meal);
        if (res2.getCount()!=0 && !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
            Toast.makeText(getApplicationContext(), yesterdayLunch, Toast.LENGTH_LONG).show();
        }
    }
    public void searchFriSupper(String meal){
        Cursor res2=  myDatabase.searchFriSupperMealsSpecific(meal);
        if (res2.getCount()!=0 && !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
            Toast.makeText(getApplicationContext(), yesterdaySupper, Toast.LENGTH_LONG).show();
        }
    }
    public void searchSatBreakfast(String meal){
        Cursor res2=  myDatabase.searchSatBreakfastMealsSpecific(meal);
        if (res2.getCount()!=0 && !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
            Toast.makeText(getApplicationContext(), yesterdayBreakfast, Toast.LENGTH_LONG).show();
        }
    }
    public void searchSatLunch(String meal){
        Cursor res2=  myDatabase.searchSatLunchMealsSpecific(meal);
        if (res2.getCount()!=0 && !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
            Toast.makeText(getApplicationContext(), yesterdayLunch, Toast.LENGTH_LONG).show();
        }
    }
    public void searchSatSupper(String meal){
        Cursor res2=  myDatabase.searchSatSupperMealsSpecific(meal);
        if (res2.getCount()!=0 && !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
            Toast.makeText(getApplicationContext(), yesterdaySupper, Toast.LENGTH_LONG).show();
        }
    }
    public void searchSunBreakfast(String meal){
        Cursor res2=  myDatabase.searchSunBreakfastMealsSpecific(meal);
        if (res2.getCount()!=0 && !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
            Toast.makeText(getApplicationContext(), yesterdayBreakfast, Toast.LENGTH_LONG).show();
        }
    }
    public void searchSunLunch(String meal){
        Cursor res2=  myDatabase.searchSunLunchMealsSpecific(meal);
        if (res2.getCount()!=0 && !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
            Toast.makeText(getApplicationContext(), yesterdayLunch, Toast.LENGTH_LONG).show();
        }
    }
    public void searchSunSupper(String meal){
        Cursor res2=  myDatabase.searchSunSupperMealsSpecific(meal);
        if (res2.getCount()!=0 && !meal.equalsIgnoreCase(tea) && !meal.equalsIgnoreCase(chocolate) && !meal.equalsIgnoreCase(coffee) && !meal.equalsIgnoreCase(ugali)){
            Toast.makeText(getApplicationContext(), yesterdaySupper, Toast.LENGTH_LONG).show();
        }
    }

    //delete dialog builder
    public static void showDialog(Context context, String message,
                                  DialogInterface.OnClickListener onClickListener) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
//        dialog.setTitle(title);
        dialog.setMessage(message);
        dialog.setPositiveButton("Yes",onClickListener);
        dialog.setNegativeButton("Cancel",null);
        dialog.show();
    }

    //START OF DELETE METHODS
    //monday breakfast
    public void deleteMonBreakfastMeal(String meal, String id){
        showDialog(this,"Are you sure to delete "+meal+"?",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                myDatabase.updateMonBreakfastData("","",id);

                String meal1 = "",meal1Nut = "",meal2 = "",meal2Nut = "",meal3 = "",meal3Nut = "",meal4 = "",meal4Nut = "",meal5 = "",meal5Nut = "";

                Cursor res1 = myDatabase.viewMonBreakfastMealsSpecific("1");
                while (res1.moveToNext()) {
                    meal1 = res1.getString(1);
                    meal1Nut = res1.getString(2);}
                Cursor res2 = myDatabase.viewMonBreakfastMealsSpecific("2");
                while (res2.moveToNext()) {
                    meal2 = res2.getString(1);
                    meal2Nut = res2.getString(2);}
                Cursor res3 = myDatabase.viewMonBreakfastMealsSpecific("3");
                while (res3.moveToNext()) {
                    meal3 = res3.getString(1);
                    meal3Nut = res3.getString(2);}
                Cursor res4 = myDatabase.viewMonBreakfastMealsSpecific("4");
                while (res4.moveToNext()) {
                    meal4 = res4.getString(1);
                    meal4Nut = res4.getString(2);}
                Cursor res5 = myDatabase.viewMonBreakfastMealsSpecific("5");
                while (res5.moveToNext()) {
                    meal5 = res5.getString(1);
                    meal5Nut = res5.getString(2);}

                //first meal empty, move subsequents meals up
                if(meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateMonBreakfastData(meal2,meal2Nut,"1");
                    myDatabase.updateMonBreakfastData(meal3,meal3Nut,"2");
                    myDatabase.updateMonBreakfastData(meal4,meal4Nut,"3");
                    myDatabase.updateMonBreakfastData(meal5,meal5Nut,"4");
                    myDatabase.updateMonBreakfastData("","","5");
                    Toast.makeText(getApplicationContext(),"first",Toast.LENGTH_SHORT).show();
                }
                //first and fifth meal empty , move subsequents meals up
                else if(meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateMonBreakfastData(meal2,meal2Nut,"1");
                    myDatabase.updateMonBreakfastData(meal3,meal3Nut,"2");
                    myDatabase.updateMonBreakfastData(meal4,meal4Nut,"3");
                    myDatabase.updateMonBreakfastData("","","4");
                    Toast.makeText(getApplicationContext(),"second",Toast.LENGTH_SHORT).show();
                }
                //first,fourth and fifth meal empty, move subsequents meals up
                else if(meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateMonBreakfastData(meal2,meal2Nut,"1");
                    myDatabase.updateMonBreakfastData(meal3,meal3Nut,"2");
                    myDatabase.updateMonBreakfastData("","","3");
                    Toast.makeText(getApplicationContext(),"third",Toast.LENGTH_SHORT).show();
                }
                //first,third,fourth and fifth meal empty, move subsequents meals up
                else if(meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateMonBreakfastData(meal2,meal2Nut,"1");
                    myDatabase.updateMonBreakfastData("","","2");
                    Toast.makeText(getApplicationContext(),"fourth",Toast.LENGTH_SHORT).show();
                }
                //second meal empty, move subsequents meals up
                else if(!meal1.trim().equalsIgnoreCase("")&&meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
//                    myDatabase.updateMonBreakfastData(meal2,meal2Nut,"1");
                    myDatabase.updateMonBreakfastData(meal3,meal3Nut,"2");
                    myDatabase.updateMonBreakfastData(meal4,meal4Nut,"3");
                    myDatabase.updateMonBreakfastData(meal5,meal5Nut,"4");
                    myDatabase.updateMonBreakfastData("","","5");
                    Toast.makeText(getApplicationContext(),"fifth",Toast.LENGTH_SHORT).show();
                }
                //second,fifth meal empty, move subsequents meals up
                else if(!meal1.trim().equalsIgnoreCase("")&&meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
//                    myDatabase.updateMonBreakfastData(meal2,meal2Nut,"1");
                    myDatabase.updateMonBreakfastData(meal3,meal3Nut,"2");
                    myDatabase.updateMonBreakfastData(meal4,meal4Nut,"3");
//                    myDatabase.updateMonBreakfastData(meal5,meal5Nut,"4");
                    myDatabase.updateMonBreakfastData("","","4");
                    Toast.makeText(getApplicationContext(),"sixth",Toast.LENGTH_SHORT).show();
                }
                //second,fifth,fourth meal empty, move subsequents meals up
                else if(!meal1.trim().equalsIgnoreCase("")&&meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateMonBreakfastData(meal3,meal3Nut,"2");
                    myDatabase.updateMonBreakfastData("","","3");
                    Toast.makeText(getApplicationContext(),"seventh",Toast.LENGTH_SHORT).show();
                }
                //third meal empty, move subsequents meals up
                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateMonBreakfastData(meal4,meal4Nut,"3");
                    myDatabase.updateMonBreakfastData(meal5,meal5Nut,"4");
                    myDatabase.updateMonBreakfastData("","","5");
                    Toast.makeText(getApplicationContext(),"eighth",Toast.LENGTH_SHORT).show();
                }
                //third,fifth meal empty, move subsequents meals up
                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateMonBreakfastData(meal4,meal4Nut,"3");
                    myDatabase.updateMonBreakfastData("","","4");
                    Toast.makeText(getApplicationContext(),"nine",Toast.LENGTH_SHORT).show();
                }
                //fourth meal empty, move subsequents meals up
                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateMonBreakfastData(meal5,meal5Nut,"4");
                    myDatabase.updateMonBreakfastData("","","5");
                    Toast.makeText(getApplicationContext(),"ten",Toast.LENGTH_SHORT).show();
                }
                mondayBreakfast();
            }
        });
    }
    //monday lunch
    public void deleteMonLunchMeal(String meal, String id){
        showDialog(this,"Are you sure to delete "+meal+"?",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                myDatabase.updateMonLunchData("","",id);
                String meal1 = "",meal1Nut = "",meal2 = "",meal2Nut = "",meal3 = "",meal3Nut = "",meal4 = "",meal4Nut = "",meal5 = "",meal5Nut = "";

                Cursor res1 = myDatabase.viewMonLunchMealsSpecific("1");
                while (res1.moveToNext()) {
                    meal1 = res1.getString(1);
                    meal1Nut = res1.getString(2);}
                Cursor res2 = myDatabase.viewMonLunchMealsSpecific("2");
                while (res2.moveToNext()) {
                    meal2 = res2.getString(1);
                    meal2Nut = res2.getString(2);}
                Cursor res3 = myDatabase.viewMonLunchMealsSpecific("3");
                while (res3.moveToNext()) {
                    meal3 = res3.getString(1);
                    meal3Nut = res3.getString(2);}
                Cursor res4 = myDatabase.viewMonLunchMealsSpecific("4");
                while (res4.moveToNext()) {
                    meal4 = res4.getString(1);
                    meal4Nut = res4.getString(2);}
                Cursor res5 = myDatabase.viewMonLunchMealsSpecific("5");
                while (res5.moveToNext()) {
                    meal5 = res5.getString(1);
                    meal5Nut = res5.getString(2);}

                if(meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateMonLunchData(meal2,meal2Nut,"1");
                    myDatabase.updateMonLunchData(meal3,meal3Nut,"2");
                    myDatabase.updateMonLunchData(meal4,meal4Nut,"3");
                    myDatabase.updateMonLunchData(meal5,meal5Nut,"4");
                    myDatabase.updateMonLunchData("","","5");
                }
                else if(meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateMonLunchData(meal2,meal2Nut,"1");
                    myDatabase.updateMonLunchData(meal3,meal3Nut,"2");
                    myDatabase.updateMonLunchData(meal4,meal4Nut,"3");
                    myDatabase.updateMonLunchData("","","4");
                }
                else if(meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateMonLunchData(meal2,meal2Nut,"1");
                    myDatabase.updateMonLunchData(meal3,meal3Nut,"2");
                    myDatabase.updateMonLunchData("","","3");
                }
                else if(meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateMonLunchData(meal2,meal2Nut,"1");
                    myDatabase.updateMonLunchData("","","2");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateMonLunchData(meal3,meal3Nut,"2");
                    myDatabase.updateMonLunchData(meal4,meal4Nut,"3");
                    myDatabase.updateMonLunchData(meal5,meal5Nut,"4");
                    myDatabase.updateMonLunchData("","","5");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateMonLunchData(meal3,meal3Nut,"2");
                    myDatabase.updateMonLunchData(meal4,meal4Nut,"3");
                    myDatabase.updateMonLunchData("","","4");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateMonLunchData(meal3,meal3Nut,"2");
                    myDatabase.updateMonLunchData("","","3");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateMonLunchData(meal4,meal4Nut,"3");
                    myDatabase.updateMonLunchData(meal5,meal5Nut,"4");
                    myDatabase.updateMonLunchData("","","5");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateMonLunchData(meal4,meal4Nut,"3");
                    myDatabase.updateMonLunchData("","","4");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateMonLunchData(meal5,meal5Nut,"4");
                    myDatabase.updateMonLunchData("","","5");
                }

                mondayLunch();
            }
        });
    }
    //monday Supper
    public void deleteMonSupperMeal(String meal, String id){
        showDialog(this,"Are you sure to delete "+meal+"?",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                myDatabase.updateMonSupperData("","",id);
                String meal1 = "",meal1Nut = "",meal2 = "",meal2Nut = "",meal3 = "",meal3Nut = "",meal4 = "",meal4Nut = "",meal5 = "",meal5Nut = "";

                Cursor res1 = myDatabase.viewMonSupperMealsSpecific("1");
                while (res1.moveToNext()) {
                    meal1 = res1.getString(1);
                    meal1Nut = res1.getString(2);}
                Cursor res2 = myDatabase.viewMonSupperMealsSpecific("2");
                while (res2.moveToNext()) {
                    meal2 = res2.getString(1);
                    meal2Nut = res2.getString(2);}
                Cursor res3 = myDatabase.viewMonSupperMealsSpecific("3");
                while (res3.moveToNext()) {
                    meal3 = res3.getString(1);
                    meal3Nut = res3.getString(2);}
                Cursor res4 = myDatabase.viewMonSupperMealsSpecific("4");
                while (res4.moveToNext()) {
                    meal4 = res4.getString(1);
                    meal4Nut = res4.getString(2);}
                Cursor res5 = myDatabase.viewMonSupperMealsSpecific("5");
                while (res5.moveToNext()) {
                    meal5 = res5.getString(1);
                    meal5Nut = res5.getString(2);}

                if(meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateMonSupperData(meal2,meal2Nut,"1");
                    myDatabase.updateMonSupperData(meal3,meal3Nut,"2");
                    myDatabase.updateMonSupperData(meal4,meal4Nut,"3");
                    myDatabase.updateMonSupperData(meal5,meal5Nut,"4");
                    myDatabase.updateMonSupperData("","","5");
                }
                else if(meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateMonSupperData(meal2,meal2Nut,"1");
                    myDatabase.updateMonSupperData(meal3,meal3Nut,"2");
                    myDatabase.updateMonSupperData(meal4,meal4Nut,"3");
                    myDatabase.updateMonSupperData("","","4");
                }
                else if(meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateMonSupperData(meal2,meal2Nut,"1");
                    myDatabase.updateMonSupperData(meal3,meal3Nut,"2");
                    myDatabase.updateMonSupperData("","","3");
                }
                else if(meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateMonSupperData(meal2,meal2Nut,"1");
                    myDatabase.updateMonSupperData("","","2");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
//                    myDatabase.updateMonSupperData(meal2,meal2Nut,"1");
                    myDatabase.updateMonSupperData(meal3,meal3Nut,"2");
                    myDatabase.updateMonSupperData(meal4,meal4Nut,"3");
                    myDatabase.updateMonSupperData(meal5,meal5Nut,"4");
                    myDatabase.updateMonSupperData("","","5");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateMonSupperData(meal3,meal3Nut,"2");
                    myDatabase.updateMonSupperData(meal4,meal4Nut,"3");
                    myDatabase.updateMonSupperData("","","4");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateMonSupperData(meal3,meal3Nut,"2");
                    myDatabase.updateMonSupperData("","","3");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateMonSupperData(meal4,meal4Nut,"3");
                    myDatabase.updateMonSupperData(meal5,meal5Nut,"4");
                    myDatabase.updateMonSupperData("","","5");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateMonSupperData(meal4,meal4Nut,"3");
                    myDatabase.updateMonSupperData("","","4");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateMonSupperData(meal5,meal5Nut,"4");
                    myDatabase.updateMonSupperData("","","5");
                }

                mondaySupper();
            }
        });
    }
    //Tuesday breakfast
    public void deleteTueBreakfastMeal(String meal, String id){
        showDialog(this,"Are you sure to delete "+meal+"?",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                myDatabase.updateTueBreakfastData("","",id);
                String meal1 = "",meal1Nut = "",meal2 = "",meal2Nut = "",meal3 = "",meal3Nut = "",meal4 = "",meal4Nut = "",meal5 = "",meal5Nut = "";

                Cursor res1 = myDatabase.viewTueBreakfastMealsSpecific("1");
                while (res1.moveToNext()) {
                    meal1 = res1.getString(1);
                    meal1Nut = res1.getString(2);}
                Cursor res2 = myDatabase.viewTueBreakfastMealsSpecific("2");
                while (res2.moveToNext()) {
                    meal2 = res2.getString(1);
                    meal2Nut = res2.getString(2);}
                Cursor res3 = myDatabase.viewTueBreakfastMealsSpecific("3");
                while (res3.moveToNext()) {
                    meal3 = res3.getString(1);
                    meal3Nut = res3.getString(2);}
                Cursor res4 = myDatabase.viewTueBreakfastMealsSpecific("4");
                while (res4.moveToNext()) {
                    meal4 = res4.getString(1);
                    meal4Nut = res4.getString(2);}
                Cursor res5 = myDatabase.viewTueBreakfastMealsSpecific("5");
                while (res5.moveToNext()) {
                    meal5 = res5.getString(1);
                    meal5Nut = res5.getString(2);}

                if(meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateTueBreakfastData(meal2,meal2Nut,"1");
                    myDatabase.updateTueBreakfastData(meal3,meal3Nut,"2");
                    myDatabase.updateTueBreakfastData(meal4,meal4Nut,"3");
                    myDatabase.updateTueBreakfastData(meal5,meal5Nut,"4");
                    myDatabase.updateTueBreakfastData("","","5");
                }
                else if(meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateTueBreakfastData(meal2,meal2Nut,"1");
                    myDatabase.updateTueBreakfastData(meal3,meal3Nut,"2");
                    myDatabase.updateTueBreakfastData(meal4,meal4Nut,"3");
                    myDatabase.updateTueBreakfastData("","","4");
                }
                else if(meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateTueBreakfastData(meal2,meal2Nut,"1");
                    myDatabase.updateTueBreakfastData(meal3,meal3Nut,"2");
                    myDatabase.updateTueBreakfastData("","","3");
                }
                else if(meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateTueBreakfastData(meal2,meal2Nut,"1");
                    myDatabase.updateTueBreakfastData("","","2");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
//                    myDatabase.updateTueBreakfastData(meal2,meal2Nut,"1");
                    myDatabase.updateTueBreakfastData(meal3,meal3Nut,"2");
                    myDatabase.updateTueBreakfastData(meal4,meal4Nut,"3");
                    myDatabase.updateTueBreakfastData(meal5,meal5Nut,"4");
                    myDatabase.updateTueBreakfastData("","","5");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
//                    myDatabase.updateTueBreakfastData(meal2,meal2Nut,"1");
                    myDatabase.updateTueBreakfastData(meal3,meal3Nut,"2");
                    myDatabase.updateTueBreakfastData(meal4,meal4Nut,"3");
//                    myDatabase.updateTueBreakfastData(meal5,meal5Nut,"4");
                    myDatabase.updateTueBreakfastData("","","4");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateTueBreakfastData(meal3,meal3Nut,"2");
                    myDatabase.updateTueBreakfastData("","","3");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateTueBreakfastData(meal4,meal4Nut,"3");
                    myDatabase.updateTueBreakfastData(meal5,meal5Nut,"4");
                    myDatabase.updateTueBreakfastData("","","5");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateTueBreakfastData(meal4,meal4Nut,"3");
                    myDatabase.updateTueBreakfastData("","","4");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateTueBreakfastData(meal5,meal5Nut,"4");
                    myDatabase.updateTueBreakfastData("","","5");
                }

                tuesdayBreakfast();
            }
        });
    }
    //Tuesday lunch
    public void deleteTueLunchMeal(String meal, String id){
        showDialog(this,"Are you sure to delete "+meal+"?",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                myDatabase.updateTueLunchData("","",id);
                String meal1 = "",meal1Nut = "",meal2 = "",meal2Nut = "",meal3 = "",meal3Nut = "",meal4 = "",meal4Nut = "",meal5 = "",meal5Nut = "";

                Cursor res1 = myDatabase.viewTueLunchMealsSpecific("1");
                while (res1.moveToNext()) {
                    meal1 = res1.getString(1);
                    meal1Nut = res1.getString(2);}
                Cursor res2 = myDatabase.viewTueLunchMealsSpecific("2");
                while (res2.moveToNext()) {
                    meal2 = res2.getString(1);
                    meal2Nut = res2.getString(2);}
                Cursor res3 = myDatabase.viewTueLunchMealsSpecific("3");
                while (res3.moveToNext()) {
                    meal3 = res3.getString(1);
                    meal3Nut = res3.getString(2);}
                Cursor res4 = myDatabase.viewTueLunchMealsSpecific("4");
                while (res4.moveToNext()) {
                    meal4 = res4.getString(1);
                    meal4Nut = res4.getString(2);}
                Cursor res5 = myDatabase.viewTueLunchMealsSpecific("5");
                while (res5.moveToNext()) {
                    meal5 = res5.getString(1);
                    meal5Nut = res5.getString(2);}

                if(meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateTueLunchData(meal2,meal2Nut,"1");
                    myDatabase.updateTueLunchData(meal3,meal3Nut,"2");
                    myDatabase.updateTueLunchData(meal4,meal4Nut,"3");
                    myDatabase.updateTueLunchData(meal5,meal5Nut,"4");
                    myDatabase.updateTueLunchData("","","5");
                }
                else if(meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateTueLunchData(meal2,meal2Nut,"1");
                    myDatabase.updateTueLunchData(meal3,meal3Nut,"2");
                    myDatabase.updateTueLunchData(meal4,meal4Nut,"3");
                    myDatabase.updateTueLunchData("","","4");
                }
                else if(meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateTueLunchData(meal2,meal2Nut,"1");
                    myDatabase.updateTueLunchData(meal3,meal3Nut,"2");
                    myDatabase.updateTueLunchData("","","3");
                }
                else if(meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateTueLunchData(meal2,meal2Nut,"1");
                    myDatabase.updateTueLunchData("","","2");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateTueLunchData(meal3,meal3Nut,"2");
                    myDatabase.updateTueLunchData(meal4,meal4Nut,"3");
                    myDatabase.updateTueLunchData(meal5,meal5Nut,"4");
                    myDatabase.updateTueLunchData("","","5");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateTueLunchData(meal3,meal3Nut,"2");
                    myDatabase.updateTueLunchData(meal4,meal4Nut,"3");
                    myDatabase.updateTueLunchData("","","4");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateTueLunchData(meal3,meal3Nut,"2");
                    myDatabase.updateTueLunchData("","","3");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateTueLunchData(meal4,meal4Nut,"3");
                    myDatabase.updateTueLunchData(meal5,meal5Nut,"4");
                    myDatabase.updateTueLunchData("","","5");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateTueLunchData(meal4,meal4Nut,"3");
                    myDatabase.updateTueLunchData("","","4");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateTueLunchData(meal5,meal5Nut,"4");
                    myDatabase.updateTueLunchData("","","5");
                }

                tuesdayLunch();
            }
        });
    }
    //Tuesday Supper
    public void deleteTueSupperMeal(String meal, String id){
        showDialog(this,"Are you sure to delete "+meal+"?",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                myDatabase.updateTueSupperData("","",id);
                String meal1 = "",meal1Nut = "",meal2 = "",meal2Nut = "",meal3 = "",meal3Nut = "",meal4 = "",meal4Nut = "",meal5 = "",meal5Nut = "";

                Cursor res1 = myDatabase.viewTueSupperMealsSpecific("1");
                while (res1.moveToNext()) {
                    meal1 = res1.getString(1);
                    meal1Nut = res1.getString(2);}
                Cursor res2 = myDatabase.viewTueSupperMealsSpecific("2");
                while (res2.moveToNext()) {
                    meal2 = res2.getString(1);
                    meal2Nut = res2.getString(2);}
                Cursor res3 = myDatabase.viewTueSupperMealsSpecific("3");
                while (res3.moveToNext()) {
                    meal3 = res3.getString(1);
                    meal3Nut = res3.getString(2);}
                Cursor res4 = myDatabase.viewTueSupperMealsSpecific("4");
                while (res4.moveToNext()) {
                    meal4 = res4.getString(1);
                    meal4Nut = res4.getString(2);}
                Cursor res5 = myDatabase.viewTueSupperMealsSpecific("5");
                while (res5.moveToNext()) {
                    meal5 = res5.getString(1);
                    meal5Nut = res5.getString(2);}

                if(meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateTueSupperData(meal2,meal2Nut,"1");
                    myDatabase.updateTueSupperData(meal3,meal3Nut,"2");
                    myDatabase.updateTueSupperData(meal4,meal4Nut,"3");
                    myDatabase.updateTueSupperData(meal5,meal5Nut,"4");
                    myDatabase.updateTueSupperData("","","5");
                }
                else if(meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateTueSupperData(meal2,meal2Nut,"1");
                    myDatabase.updateTueSupperData(meal3,meal3Nut,"2");
                    myDatabase.updateTueSupperData(meal4,meal4Nut,"3");
                    myDatabase.updateTueSupperData("","","4");
                }
                else if(meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateTueSupperData(meal2,meal2Nut,"1");
                    myDatabase.updateTueSupperData(meal3,meal3Nut,"2");
                    myDatabase.updateTueSupperData("","","3");
                }
                else if(meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateTueSupperData(meal2,meal2Nut,"1");
                    myDatabase.updateTueSupperData("","","2");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
//                    myDatabase.updateTueSupperData(meal2,meal2Nut,"1");
                    myDatabase.updateTueSupperData(meal3,meal3Nut,"2");
                    myDatabase.updateTueSupperData(meal4,meal4Nut,"3");
                    myDatabase.updateTueSupperData(meal5,meal5Nut,"4");
                    myDatabase.updateTueSupperData("","","5");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateTueSupperData(meal3,meal3Nut,"2");
                    myDatabase.updateTueSupperData(meal4,meal4Nut,"3");
                    myDatabase.updateTueSupperData("","","4");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateTueSupperData(meal3,meal3Nut,"2");
                    myDatabase.updateTueSupperData("","","3");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateTueSupperData(meal4,meal4Nut,"3");
                    myDatabase.updateTueSupperData(meal5,meal5Nut,"4");
                    myDatabase.updateTueSupperData("","","5");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateTueSupperData(meal4,meal4Nut,"3");
                    myDatabase.updateTueSupperData("","","4");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateTueSupperData(meal5,meal5Nut,"4");
                    myDatabase.updateTueSupperData("","","5");
                }

                tuesdaySupper();
            }
        });
    }
    //Wednesday breakfast
    public void deleteWedBreakfastMeal(String meal, String id){
        showDialog(this,"Are you sure to delete "+meal+"?",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                myDatabase.updateWedBreakfastData("","",id);
                String meal1 = "",meal1Nut = "",meal2 = "",meal2Nut = "",meal3 = "",meal3Nut = "",meal4 = "",meal4Nut = "",meal5 = "",meal5Nut = "";

                Cursor res1 = myDatabase.viewWedBreakfastMealsSpecific("1");
                while (res1.moveToNext()) {
                    meal1 = res1.getString(1);
                    meal1Nut = res1.getString(2);}
                Cursor res2 = myDatabase.viewWedBreakfastMealsSpecific("2");
                while (res2.moveToNext()) {
                    meal2 = res2.getString(1);
                    meal2Nut = res2.getString(2);}
                Cursor res3 = myDatabase.viewWedBreakfastMealsSpecific("3");
                while (res3.moveToNext()) {
                    meal3 = res3.getString(1);
                    meal3Nut = res3.getString(2);}
                Cursor res4 = myDatabase.viewWedBreakfastMealsSpecific("4");
                while (res4.moveToNext()) {
                    meal4 = res4.getString(1);
                    meal4Nut = res4.getString(2);}
                Cursor res5 = myDatabase.viewWedBreakfastMealsSpecific("5");
                while (res5.moveToNext()) {
                    meal5 = res5.getString(1);
                    meal5Nut = res5.getString(2);}

                if(meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateWedBreakfastData(meal2,meal2Nut,"1");
                    myDatabase.updateWedBreakfastData(meal3,meal3Nut,"2");
                    myDatabase.updateWedBreakfastData(meal4,meal4Nut,"3");
                    myDatabase.updateWedBreakfastData(meal5,meal5Nut,"4");
                    myDatabase.updateWedBreakfastData("","","5");
                }
                else if(meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateWedBreakfastData(meal2,meal2Nut,"1");
                    myDatabase.updateWedBreakfastData(meal3,meal3Nut,"2");
                    myDatabase.updateWedBreakfastData(meal4,meal4Nut,"3");
                    myDatabase.updateWedBreakfastData("","","4");
                }
                else if(meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateWedBreakfastData(meal2,meal2Nut,"1");
                    myDatabase.updateWedBreakfastData(meal3,meal3Nut,"2");
                    myDatabase.updateWedBreakfastData("","","3");
                }
                else if(meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateWedBreakfastData(meal2,meal2Nut,"1");
                    myDatabase.updateWedBreakfastData("","","2");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
//                    myDatabase.updateWedBreakfastData(meal2,meal2Nut,"1");
                    myDatabase.updateWedBreakfastData(meal3,meal3Nut,"2");
                    myDatabase.updateWedBreakfastData(meal4,meal4Nut,"3");
                    myDatabase.updateWedBreakfastData(meal5,meal5Nut,"4");
                    myDatabase.updateWedBreakfastData("","","5");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
//                    myDatabase.updateWedBreakfastData(meal2,meal2Nut,"1");
                    myDatabase.updateWedBreakfastData(meal3,meal3Nut,"2");
                    myDatabase.updateWedBreakfastData(meal4,meal4Nut,"3");
//                    myDatabase.updateWedBreakfastData(meal5,meal5Nut,"4");
                    myDatabase.updateWedBreakfastData("","","4");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateWedBreakfastData(meal3,meal3Nut,"2");
                    myDatabase.updateWedBreakfastData("","","3");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateWedBreakfastData(meal4,meal4Nut,"3");
                    myDatabase.updateWedBreakfastData(meal5,meal5Nut,"4");
                    myDatabase.updateWedBreakfastData("","","5");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateWedBreakfastData(meal4,meal4Nut,"3");
                    myDatabase.updateWedBreakfastData("","","4");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateWedBreakfastData(meal5,meal5Nut,"4");
                    myDatabase.updateWedBreakfastData("","","5");
                }

                wednesdayBreakfast();
            }
        });
    }
    //Wednesday lunch
    public void deleteWedLunchMeal(String meal, String id){
        showDialog(this,"Are you sure to delete "+meal+"?",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                myDatabase.updateWedLunchData("","",id);
                String meal1 = "",meal1Nut = "",meal2 = "",meal2Nut = "",meal3 = "",meal3Nut = "",meal4 = "",meal4Nut = "",meal5 = "",meal5Nut = "";

                Cursor res1 = myDatabase.viewWedLunchMealsSpecific("1");
                while (res1.moveToNext()) {
                    meal1 = res1.getString(1);
                    meal1Nut = res1.getString(2);}
                Cursor res2 = myDatabase.viewWedLunchMealsSpecific("2");
                while (res2.moveToNext()) {
                    meal2 = res2.getString(1);
                    meal2Nut = res2.getString(2);}
                Cursor res3 = myDatabase.viewWedLunchMealsSpecific("3");
                while (res3.moveToNext()) {
                    meal3 = res3.getString(1);
                    meal3Nut = res3.getString(2);}
                Cursor res4 = myDatabase.viewWedLunchMealsSpecific("4");
                while (res4.moveToNext()) {
                    meal4 = res4.getString(1);
                    meal4Nut = res4.getString(2);}
                Cursor res5 = myDatabase.viewWedLunchMealsSpecific("5");
                while (res5.moveToNext()) {
                    meal5 = res5.getString(1);
                    meal5Nut = res5.getString(2);}

                if(meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateWedLunchData(meal2,meal2Nut,"1");
                    myDatabase.updateWedLunchData(meal3,meal3Nut,"2");
                    myDatabase.updateWedLunchData(meal4,meal4Nut,"3");
                    myDatabase.updateWedLunchData(meal5,meal5Nut,"4");
                    myDatabase.updateWedLunchData("","","5");
                }
                else if(meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateWedLunchData(meal2,meal2Nut,"1");
                    myDatabase.updateWedLunchData(meal3,meal3Nut,"2");
                    myDatabase.updateWedLunchData(meal4,meal4Nut,"3");
                    myDatabase.updateWedLunchData("","","4");
                }
                else if(meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateWedLunchData(meal2,meal2Nut,"1");
                    myDatabase.updateWedLunchData(meal3,meal3Nut,"2");
                    myDatabase.updateWedLunchData("","","3");
                }
                else if(meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateWedLunchData(meal2,meal2Nut,"1");
                    myDatabase.updateWedLunchData("","","2");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateWedLunchData(meal3,meal3Nut,"2");
                    myDatabase.updateWedLunchData(meal4,meal4Nut,"3");
                    myDatabase.updateWedLunchData(meal5,meal5Nut,"4");
                    myDatabase.updateWedLunchData("","","5");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateWedLunchData(meal3,meal3Nut,"2");
                    myDatabase.updateWedLunchData(meal4,meal4Nut,"3");
                    myDatabase.updateWedLunchData("","","4");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateWedLunchData(meal3,meal3Nut,"2");
                    myDatabase.updateWedLunchData("","","3");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateWedLunchData(meal4,meal4Nut,"3");
                    myDatabase.updateWedLunchData(meal5,meal5Nut,"4");
                    myDatabase.updateWedLunchData("","","5");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateWedLunchData(meal4,meal4Nut,"3");
                    myDatabase.updateWedLunchData("","","4");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateWedLunchData(meal5,meal5Nut,"4");
                    myDatabase.updateWedLunchData("","","5");
                }

                wednesdayLunch();
            }
        });
    }
    //Wednesday Supper
    public void deleteWedSupperMeal(String meal, String id){
        showDialog(this,"Are you sure to delete "+meal+"?",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                myDatabase.updateWedSupperData("","",id);
                String meal1 = "",meal1Nut = "",meal2 = "",meal2Nut = "",meal3 = "",meal3Nut = "",meal4 = "",meal4Nut = "",meal5 = "",meal5Nut = "";

                Cursor res1 = myDatabase.viewWedSupperMealsSpecific("1");
                while (res1.moveToNext()) {
                    meal1 = res1.getString(1);
                    meal1Nut = res1.getString(2);}
                Cursor res2 = myDatabase.viewWedSupperMealsSpecific("2");
                while (res2.moveToNext()) {
                    meal2 = res2.getString(1);
                    meal2Nut = res2.getString(2);}
                Cursor res3 = myDatabase.viewWedSupperMealsSpecific("3");
                while (res3.moveToNext()) {
                    meal3 = res3.getString(1);
                    meal3Nut = res3.getString(2);}
                Cursor res4 = myDatabase.viewWedSupperMealsSpecific("4");
                while (res4.moveToNext()) {
                    meal4 = res4.getString(1);
                    meal4Nut = res4.getString(2);}
                Cursor res5 = myDatabase.viewWedSupperMealsSpecific("5");
                while (res5.moveToNext()) {
                    meal5 = res5.getString(1);
                    meal5Nut = res5.getString(2);}

                if(meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateWedSupperData(meal2,meal2Nut,"1");
                    myDatabase.updateWedSupperData(meal3,meal3Nut,"2");
                    myDatabase.updateWedSupperData(meal4,meal4Nut,"3");
                    myDatabase.updateWedSupperData(meal5,meal5Nut,"4");
                    myDatabase.updateWedSupperData("","","5");
                }
                else if(meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateWedSupperData(meal2,meal2Nut,"1");
                    myDatabase.updateWedSupperData(meal3,meal3Nut,"2");
                    myDatabase.updateWedSupperData(meal4,meal4Nut,"3");
                    myDatabase.updateWedSupperData("","","4");
                }
                else if(meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateWedSupperData(meal2,meal2Nut,"1");
                    myDatabase.updateWedSupperData(meal3,meal3Nut,"2");
                    myDatabase.updateWedSupperData("","","3");
                }
                else if(meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateWedSupperData(meal2,meal2Nut,"1");
                    myDatabase.updateWedSupperData("","","2");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
//                    myDatabase.updateWedSupperData(meal2,meal2Nut,"1");
                    myDatabase.updateWedSupperData(meal3,meal3Nut,"2");
                    myDatabase.updateWedSupperData(meal4,meal4Nut,"3");
                    myDatabase.updateWedSupperData(meal5,meal5Nut,"4");
                    myDatabase.updateWedSupperData("","","5");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateWedSupperData(meal3,meal3Nut,"2");
                    myDatabase.updateWedSupperData(meal4,meal4Nut,"3");
                    myDatabase.updateWedSupperData("","","4");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateWedSupperData(meal3,meal3Nut,"2");
                    myDatabase.updateWedSupperData("","","3");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateWedSupperData(meal4,meal4Nut,"3");
                    myDatabase.updateWedSupperData(meal5,meal5Nut,"4");
                    myDatabase.updateWedSupperData("","","5");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateWedSupperData(meal4,meal4Nut,"3");
                    myDatabase.updateWedSupperData("","","4");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateWedSupperData(meal5,meal5Nut,"4");
                    myDatabase.updateWedSupperData("","","5");
                }

                wednesdaySupper();
            }
        });
    }
    //Thursday breakfast
    public void deleteThurBreakfastMeal(String meal, String id){
        showDialog(this,"Are you sure to delete "+meal+"?",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                myDatabase.updateThurBreakfastData("","",id);
                String meal1 = "",meal1Nut = "",meal2 = "",meal2Nut = "",meal3 = "",meal3Nut = "",meal4 = "",meal4Nut = "",meal5 = "",meal5Nut = "";

                Cursor res1 = myDatabase.viewThurBreakfastMealsSpecific("1");
                while (res1.moveToNext()) {
                    meal1 = res1.getString(1);
                    meal1Nut = res1.getString(2);}
                Cursor res2 = myDatabase.viewThurBreakfastMealsSpecific("2");
                while (res2.moveToNext()) {
                    meal2 = res2.getString(1);
                    meal2Nut = res2.getString(2);}
                Cursor res3 = myDatabase.viewThurBreakfastMealsSpecific("3");
                while (res3.moveToNext()) {
                    meal3 = res3.getString(1);
                    meal3Nut = res3.getString(2);}
                Cursor res4 = myDatabase.viewThurBreakfastMealsSpecific("4");
                while (res4.moveToNext()) {
                    meal4 = res4.getString(1);
                    meal4Nut = res4.getString(2);}
                Cursor res5 = myDatabase.viewThurBreakfastMealsSpecific("5");
                while (res5.moveToNext()) {
                    meal5 = res5.getString(1);
                    meal5Nut = res5.getString(2);}

                if(meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateThurBreakfastData(meal2,meal2Nut,"1");
                    myDatabase.updateThurBreakfastData(meal3,meal3Nut,"2");
                    myDatabase.updateThurBreakfastData(meal4,meal4Nut,"3");
                    myDatabase.updateThurBreakfastData(meal5,meal5Nut,"4");
                    myDatabase.updateThurBreakfastData("","","5");
                }
                else if(meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateThurBreakfastData(meal2,meal2Nut,"1");
                    myDatabase.updateThurBreakfastData(meal3,meal3Nut,"2");
                    myDatabase.updateThurBreakfastData(meal4,meal4Nut,"3");
                    myDatabase.updateThurBreakfastData("","","4");
                }
                else if(meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateThurBreakfastData(meal2,meal2Nut,"1");
                    myDatabase.updateThurBreakfastData(meal3,meal3Nut,"2");
                    myDatabase.updateThurBreakfastData("","","3");
                }
                else if(meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateThurBreakfastData(meal2,meal2Nut,"1");
                    myDatabase.updateThurBreakfastData("","","2");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
//                    myDatabase.updateThurBreakfastData(meal2,meal2Nut,"1");
                    myDatabase.updateThurBreakfastData(meal3,meal3Nut,"2");
                    myDatabase.updateThurBreakfastData(meal4,meal4Nut,"3");
                    myDatabase.updateThurBreakfastData(meal5,meal5Nut,"4");
                    myDatabase.updateThurBreakfastData("","","5");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
//                    myDatabase.updateThurBreakfastData(meal2,meal2Nut,"1");
                    myDatabase.updateThurBreakfastData(meal3,meal3Nut,"2");
                    myDatabase.updateThurBreakfastData(meal4,meal4Nut,"3");
//                    myDatabase.updateThurBreakfastData(meal5,meal5Nut,"4");
                    myDatabase.updateThurBreakfastData("","","4");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateThurBreakfastData(meal3,meal3Nut,"2");
                    myDatabase.updateThurBreakfastData("","","3");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateThurBreakfastData(meal4,meal4Nut,"3");
                    myDatabase.updateThurBreakfastData(meal5,meal5Nut,"4");
                    myDatabase.updateThurBreakfastData("","","5");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateThurBreakfastData(meal4,meal4Nut,"3");
                    myDatabase.updateThurBreakfastData("","","4");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateThurBreakfastData(meal5,meal5Nut,"4");
                    myDatabase.updateThurBreakfastData("","","5");
                }

                thursdayBreakfast();
            }
        });
    }
    //Thursday lunch
    public void deleteThurLunchMeal(String meal, String id){
        showDialog(this,"Are you sure to delete "+meal+"?",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                myDatabase.updateThurLunchData("","",id);
                String meal1 = "",meal1Nut = "",meal2 = "",meal2Nut = "",meal3 = "",meal3Nut = "",meal4 = "",meal4Nut = "",meal5 = "",meal5Nut = "";

                Cursor res1 = myDatabase.viewThurLunchMealsSpecific("1");
                while (res1.moveToNext()) {
                    meal1 = res1.getString(1);
                    meal1Nut = res1.getString(2);}
                Cursor res2 = myDatabase.viewThurLunchMealsSpecific("2");
                while (res2.moveToNext()) {
                    meal2 = res2.getString(1);
                    meal2Nut = res2.getString(2);}
                Cursor res3 = myDatabase.viewThurLunchMealsSpecific("3");
                while (res3.moveToNext()) {
                    meal3 = res3.getString(1);
                    meal3Nut = res3.getString(2);}
                Cursor res4 = myDatabase.viewThurLunchMealsSpecific("4");
                while (res4.moveToNext()) {
                    meal4 = res4.getString(1);
                    meal4Nut = res4.getString(2);}
                Cursor res5 = myDatabase.viewThurLunchMealsSpecific("5");
                while (res5.moveToNext()) {
                    meal5 = res5.getString(1);
                    meal5Nut = res5.getString(2);}

                if(meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateThurLunchData(meal2,meal2Nut,"1");
                    myDatabase.updateThurLunchData(meal3,meal3Nut,"2");
                    myDatabase.updateThurLunchData(meal4,meal4Nut,"3");
                    myDatabase.updateThurLunchData(meal5,meal5Nut,"4");
                    myDatabase.updateThurLunchData("","","5");
                }
                else if(meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateThurLunchData(meal2,meal2Nut,"1");
                    myDatabase.updateThurLunchData(meal3,meal3Nut,"2");
                    myDatabase.updateThurLunchData(meal4,meal4Nut,"3");
                    myDatabase.updateThurLunchData("","","4");
                }
                else if(meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateThurLunchData(meal2,meal2Nut,"1");
                    myDatabase.updateThurLunchData(meal3,meal3Nut,"2");
                    myDatabase.updateThurLunchData("","","3");
                }
                else if(meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateThurLunchData(meal2,meal2Nut,"1");
                    myDatabase.updateThurLunchData("","","2");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateThurLunchData(meal3,meal3Nut,"2");
                    myDatabase.updateThurLunchData(meal4,meal4Nut,"3");
                    myDatabase.updateThurLunchData(meal5,meal5Nut,"4");
                    myDatabase.updateThurLunchData("","","5");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateThurLunchData(meal3,meal3Nut,"2");
                    myDatabase.updateThurLunchData(meal4,meal4Nut,"3");
                    myDatabase.updateThurLunchData("","","4");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateThurLunchData(meal3,meal3Nut,"2");
                    myDatabase.updateThurLunchData("","","3");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateThurLunchData(meal4,meal4Nut,"3");
                    myDatabase.updateThurLunchData(meal5,meal5Nut,"4");
                    myDatabase.updateThurLunchData("","","5");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateThurLunchData(meal4,meal4Nut,"3");
                    myDatabase.updateThurLunchData("","","4");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateThurLunchData(meal5,meal5Nut,"4");
                    myDatabase.updateThurLunchData("","","5");
                }

                thursdayLunch();
            }
        });
    }
    //Thursday Supper
    public void deleteThurSupperMeal(String meal, String id){
        showDialog(this,"Are you sure to delete "+meal+"?",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                myDatabase.updateThurSupperData("","",id);
                String meal1 = "",meal1Nut = "",meal2 = "",meal2Nut = "",meal3 = "",meal3Nut = "",meal4 = "",meal4Nut = "",meal5 = "",meal5Nut = "";

                Cursor res1 = myDatabase.viewThurSupperMealsSpecific("1");
                while (res1.moveToNext()) {
                    meal1 = res1.getString(1);
                    meal1Nut = res1.getString(2);}
                Cursor res2 = myDatabase.viewThurSupperMealsSpecific("2");
                while (res2.moveToNext()) {
                    meal2 = res2.getString(1);
                    meal2Nut = res2.getString(2);}
                Cursor res3 = myDatabase.viewThurSupperMealsSpecific("3");
                while (res3.moveToNext()) {
                    meal3 = res3.getString(1);
                    meal3Nut = res3.getString(2);}
                Cursor res4 = myDatabase.viewThurSupperMealsSpecific("4");
                while (res4.moveToNext()) {
                    meal4 = res4.getString(1);
                    meal4Nut = res4.getString(2);}
                Cursor res5 = myDatabase.viewThurSupperMealsSpecific("5");
                while (res5.moveToNext()) {
                    meal5 = res5.getString(1);
                    meal5Nut = res5.getString(2);}

                if(meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateThurSupperData(meal2,meal2Nut,"1");
                    myDatabase.updateThurSupperData(meal3,meal3Nut,"2");
                    myDatabase.updateThurSupperData(meal4,meal4Nut,"3");
                    myDatabase.updateThurSupperData(meal5,meal5Nut,"4");
                    myDatabase.updateThurSupperData("","","5");
                }
                else if(meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateThurSupperData(meal2,meal2Nut,"1");
                    myDatabase.updateThurSupperData(meal3,meal3Nut,"2");
                    myDatabase.updateThurSupperData(meal4,meal4Nut,"3");
                    myDatabase.updateThurSupperData("","","4");
                }
                else if(meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateThurSupperData(meal2,meal2Nut,"1");
                    myDatabase.updateThurSupperData(meal3,meal3Nut,"2");
                    myDatabase.updateThurSupperData("","","3");
                }
                else if(meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateThurSupperData(meal2,meal2Nut,"1");
                    myDatabase.updateThurSupperData("","","2");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
//                    myDatabase.updateThurSupperData(meal2,meal2Nut,"1");
                    myDatabase.updateThurSupperData(meal3,meal3Nut,"2");
                    myDatabase.updateThurSupperData(meal4,meal4Nut,"3");
                    myDatabase.updateThurSupperData(meal5,meal5Nut,"4");
                    myDatabase.updateThurSupperData("","","5");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateThurSupperData(meal3,meal3Nut,"2");
                    myDatabase.updateThurSupperData(meal4,meal4Nut,"3");
                    myDatabase.updateThurSupperData("","","4");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateThurSupperData(meal3,meal3Nut,"2");
                    myDatabase.updateThurSupperData("","","3");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateThurSupperData(meal4,meal4Nut,"3");
                    myDatabase.updateThurSupperData(meal5,meal5Nut,"4");
                    myDatabase.updateThurSupperData("","","5");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateThurSupperData(meal4,meal4Nut,"3");
                    myDatabase.updateThurSupperData("","","4");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateThurSupperData(meal5,meal5Nut,"4");
                    myDatabase.updateThurSupperData("","","5");
                }

                thursdaySupper();
            }
        });
    }
    //Friday breakfast
    public void deleteFriBreakfastMeal(String meal, String id){
        showDialog(this,"Are you sure to delete "+meal+"?",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                myDatabase.updateFriBreakfastData("","",id);
                String meal1 = "",meal1Nut = "",meal2 = "",meal2Nut = "",meal3 = "",meal3Nut = "",meal4 = "",meal4Nut = "",meal5 = "",meal5Nut = "";

                Cursor res1 = myDatabase.viewFriBreakfastMealsSpecific("1");
                while (res1.moveToNext()) {
                    meal1 = res1.getString(1);
                    meal1Nut = res1.getString(2);}
                Cursor res2 = myDatabase.viewFriBreakfastMealsSpecific("2");
                while (res2.moveToNext()) {
                    meal2 = res2.getString(1);
                    meal2Nut = res2.getString(2);}
                Cursor res3 = myDatabase.viewFriBreakfastMealsSpecific("3");
                while (res3.moveToNext()) {
                    meal3 = res3.getString(1);
                    meal3Nut = res3.getString(2);}
                Cursor res4 = myDatabase.viewFriBreakfastMealsSpecific("4");
                while (res4.moveToNext()) {
                    meal4 = res4.getString(1);
                    meal4Nut = res4.getString(2);}
                Cursor res5 = myDatabase.viewFriBreakfastMealsSpecific("5");
                while (res5.moveToNext()) {
                    meal5 = res5.getString(1);
                    meal5Nut = res5.getString(2);}

                if(meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateFriBreakfastData(meal2,meal2Nut,"1");
                    myDatabase.updateFriBreakfastData(meal3,meal3Nut,"2");
                    myDatabase.updateFriBreakfastData(meal4,meal4Nut,"3");
                    myDatabase.updateFriBreakfastData(meal5,meal5Nut,"4");
                    myDatabase.updateFriBreakfastData("","","5");
                }
                else if(meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateFriBreakfastData(meal2,meal2Nut,"1");
                    myDatabase.updateFriBreakfastData(meal3,meal3Nut,"2");
                    myDatabase.updateFriBreakfastData(meal4,meal4Nut,"3");
                    myDatabase.updateFriBreakfastData("","","4");
                }
                else if(meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateFriBreakfastData(meal2,meal2Nut,"1");
                    myDatabase.updateFriBreakfastData(meal3,meal3Nut,"2");
                    myDatabase.updateFriBreakfastData("","","3");
                }
                else if(meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateFriBreakfastData(meal2,meal2Nut,"1");
                    myDatabase.updateFriBreakfastData("","","2");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
//                    myDatabase.updateFriBreakfastData(meal2,meal2Nut,"1");
                    myDatabase.updateFriBreakfastData(meal3,meal3Nut,"2");
                    myDatabase.updateFriBreakfastData(meal4,meal4Nut,"3");
                    myDatabase.updateFriBreakfastData(meal5,meal5Nut,"4");
                    myDatabase.updateFriBreakfastData("","","5");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
//                    myDatabase.updateFriBreakfastData(meal2,meal2Nut,"1");
                    myDatabase.updateFriBreakfastData(meal3,meal3Nut,"2");
                    myDatabase.updateFriBreakfastData(meal4,meal4Nut,"3");
//                    myDatabase.updateFriBreakfastData(meal5,meal5Nut,"4");
                    myDatabase.updateFriBreakfastData("","","4");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateFriBreakfastData(meal3,meal3Nut,"2");
                    myDatabase.updateFriBreakfastData("","","3");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateFriBreakfastData(meal4,meal4Nut,"3");
                    myDatabase.updateFriBreakfastData(meal5,meal5Nut,"4");
                    myDatabase.updateFriBreakfastData("","","5");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateFriBreakfastData(meal4,meal4Nut,"3");
                    myDatabase.updateFriBreakfastData("","","4");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateFriBreakfastData(meal5,meal5Nut,"4");
                    myDatabase.updateFriBreakfastData("","","5");
                }

                fridayBreakfast();
            }
        });
    }
    //Friday lunch
    public void deleteFriLunchMeal(String meal, String id){
        showDialog(this,"Are you sure to delete "+meal+"?",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                myDatabase.updateFriLunchData("","",id);
                String meal1 = "",meal1Nut = "",meal2 = "",meal2Nut = "",meal3 = "",meal3Nut = "",meal4 = "",meal4Nut = "",meal5 = "",meal5Nut = "";

                Cursor res1 = myDatabase.viewFriLunchMealsSpecific("1");
                while (res1.moveToNext()) {
                    meal1 = res1.getString(1);
                    meal1Nut = res1.getString(2);}
                Cursor res2 = myDatabase.viewFriLunchMealsSpecific("2");
                while (res2.moveToNext()) {
                    meal2 = res2.getString(1);
                    meal2Nut = res2.getString(2);}
                Cursor res3 = myDatabase.viewFriLunchMealsSpecific("3");
                while (res3.moveToNext()) {
                    meal3 = res3.getString(1);
                    meal3Nut = res3.getString(2);}
                Cursor res4 = myDatabase.viewFriLunchMealsSpecific("4");
                while (res4.moveToNext()) {
                    meal4 = res4.getString(1);
                    meal4Nut = res4.getString(2);}
                Cursor res5 = myDatabase.viewFriLunchMealsSpecific("5");
                while (res5.moveToNext()) {
                    meal5 = res5.getString(1);
                    meal5Nut = res5.getString(2);}

                if(meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateFriLunchData(meal2,meal2Nut,"1");
                    myDatabase.updateFriLunchData(meal3,meal3Nut,"2");
                    myDatabase.updateFriLunchData(meal4,meal4Nut,"3");
                    myDatabase.updateFriLunchData(meal5,meal5Nut,"4");
                    myDatabase.updateFriLunchData("","","5");
                }
                else if(meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateFriLunchData(meal2,meal2Nut,"1");
                    myDatabase.updateFriLunchData(meal3,meal3Nut,"2");
                    myDatabase.updateFriLunchData(meal4,meal4Nut,"3");
                    myDatabase.updateFriLunchData("","","4");
                }
                else if(meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateFriLunchData(meal2,meal2Nut,"1");
                    myDatabase.updateFriLunchData(meal3,meal3Nut,"2");
                    myDatabase.updateFriLunchData("","","3");
                }
                else if(meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateFriLunchData(meal2,meal2Nut,"1");
                    myDatabase.updateFriLunchData("","","2");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateFriLunchData(meal3,meal3Nut,"2");
                    myDatabase.updateFriLunchData(meal4,meal4Nut,"3");
                    myDatabase.updateFriLunchData(meal5,meal5Nut,"4");
                    myDatabase.updateFriLunchData("","","5");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateFriLunchData(meal3,meal3Nut,"2");
                    myDatabase.updateFriLunchData(meal4,meal4Nut,"3");
                    myDatabase.updateFriLunchData("","","4");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateFriLunchData(meal3,meal3Nut,"2");
                    myDatabase.updateFriLunchData("","","3");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateFriLunchData(meal4,meal4Nut,"3");
                    myDatabase.updateFriLunchData(meal5,meal5Nut,"4");
                    myDatabase.updateFriLunchData("","","5");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateFriLunchData(meal4,meal4Nut,"3");
                    myDatabase.updateFriLunchData("","","4");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateFriLunchData(meal5,meal5Nut,"4");
                    myDatabase.updateFriLunchData("","","5");
                }

                fridayLunch();
            }
        });
    }
    //Friday Supper
    public void deleteFriSupperMeal(String meal, String id){
        showDialog(this,"Are you sure to delete "+meal+"?",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                myDatabase.updateFriSupperData("","",id);
                String meal1 = "",meal1Nut = "",meal2 = "",meal2Nut = "",meal3 = "",meal3Nut = "",meal4 = "",meal4Nut = "",meal5 = "",meal5Nut = "";

                Cursor res1 = myDatabase.viewFriSupperMealsSpecific("1");
                while (res1.moveToNext()) {
                    meal1 = res1.getString(1);
                    meal1Nut = res1.getString(2);}
                Cursor res2 = myDatabase.viewFriSupperMealsSpecific("2");
                while (res2.moveToNext()) {
                    meal2 = res2.getString(1);
                    meal2Nut = res2.getString(2);}
                Cursor res3 = myDatabase.viewFriSupperMealsSpecific("3");
                while (res3.moveToNext()) {
                    meal3 = res3.getString(1);
                    meal3Nut = res3.getString(2);}
                Cursor res4 = myDatabase.viewFriSupperMealsSpecific("4");
                while (res4.moveToNext()) {
                    meal4 = res4.getString(1);
                    meal4Nut = res4.getString(2);}
                Cursor res5 = myDatabase.viewFriSupperMealsSpecific("5");
                while (res5.moveToNext()) {
                    meal5 = res5.getString(1);
                    meal5Nut = res5.getString(2);}

                if(meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateFriSupperData(meal2,meal2Nut,"1");
                    myDatabase.updateFriSupperData(meal3,meal3Nut,"2");
                    myDatabase.updateFriSupperData(meal4,meal4Nut,"3");
                    myDatabase.updateFriSupperData(meal5,meal5Nut,"4");
                    myDatabase.updateFriSupperData("","","5");
                }
                else if(meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateFriSupperData(meal2,meal2Nut,"1");
                    myDatabase.updateFriSupperData(meal3,meal3Nut,"2");
                    myDatabase.updateFriSupperData(meal4,meal4Nut,"3");
                    myDatabase.updateFriSupperData("","","4");
                }
                else if(meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateFriSupperData(meal2,meal2Nut,"1");
                    myDatabase.updateFriSupperData(meal3,meal3Nut,"2");
                    myDatabase.updateFriSupperData("","","3");
                }
                else if(meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateFriSupperData(meal2,meal2Nut,"1");
                    myDatabase.updateFriSupperData("","","2");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
//                    myDatabase.updateFriSupperData(meal2,meal2Nut,"1");
                    myDatabase.updateFriSupperData(meal3,meal3Nut,"2");
                    myDatabase.updateFriSupperData(meal4,meal4Nut,"3");
                    myDatabase.updateFriSupperData(meal5,meal5Nut,"4");
                    myDatabase.updateFriSupperData("","","5");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateFriSupperData(meal3,meal3Nut,"2");
                    myDatabase.updateFriSupperData(meal4,meal4Nut,"3");
                    myDatabase.updateFriSupperData("","","4");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateFriSupperData(meal3,meal3Nut,"2");
                    myDatabase.updateFriSupperData("","","3");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateFriSupperData(meal4,meal4Nut,"3");
                    myDatabase.updateFriSupperData(meal5,meal5Nut,"4");
                    myDatabase.updateFriSupperData("","","5");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateFriSupperData(meal4,meal4Nut,"3");
                    myDatabase.updateFriSupperData("","","4");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateFriSupperData(meal5,meal5Nut,"4");
                    myDatabase.updateFriSupperData("","","5");
                }

                fridaySupper();
            }
        });
    }
    //Satday breakfast
    public void deleteSatBreakfastMeal(String meal, String id){
        showDialog(this,"Are you sure to delete "+meal+"?",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                myDatabase.updateSatBreakfastData("","",id);
                String meal1 = "",meal1Nut = "",meal2 = "",meal2Nut = "",meal3 = "",meal3Nut = "",meal4 = "",meal4Nut = "",meal5 = "",meal5Nut = "";

                Cursor res1 = myDatabase.viewSatBreakfastMealsSpecific("1");
                while (res1.moveToNext()) {
                    meal1 = res1.getString(1);
                    meal1Nut = res1.getString(2);}
                Cursor res2 = myDatabase.viewSatBreakfastMealsSpecific("2");
                while (res2.moveToNext()) {
                    meal2 = res2.getString(1);
                    meal2Nut = res2.getString(2);}
                Cursor res3 = myDatabase.viewSatBreakfastMealsSpecific("3");
                while (res3.moveToNext()) {
                    meal3 = res3.getString(1);
                    meal3Nut = res3.getString(2);}
                Cursor res4 = myDatabase.viewSatBreakfastMealsSpecific("4");
                while (res4.moveToNext()) {
                    meal4 = res4.getString(1);
                    meal4Nut = res4.getString(2);}
                Cursor res5 = myDatabase.viewSatBreakfastMealsSpecific("5");
                while (res5.moveToNext()) {
                    meal5 = res5.getString(1);
                    meal5Nut = res5.getString(2);}

                if(meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateSatBreakfastData(meal2,meal2Nut,"1");
                    myDatabase.updateSatBreakfastData(meal3,meal3Nut,"2");
                    myDatabase.updateSatBreakfastData(meal4,meal4Nut,"3");
                    myDatabase.updateSatBreakfastData(meal5,meal5Nut,"4");
                    myDatabase.updateSatBreakfastData("","","5");
                }
                else if(meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateSatBreakfastData(meal2,meal2Nut,"1");
                    myDatabase.updateSatBreakfastData(meal3,meal3Nut,"2");
                    myDatabase.updateSatBreakfastData(meal4,meal4Nut,"3");
                    myDatabase.updateSatBreakfastData("","","4");
                }
                else if(meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateSatBreakfastData(meal2,meal2Nut,"1");
                    myDatabase.updateSatBreakfastData(meal3,meal3Nut,"2");
                    myDatabase.updateSatBreakfastData("","","3");
                }
                else if(meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateSatBreakfastData(meal2,meal2Nut,"1");
                    myDatabase.updateSatBreakfastData("","","2");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
//                    myDatabase.updateSatBreakfastData(meal2,meal2Nut,"1");
                    myDatabase.updateSatBreakfastData(meal3,meal3Nut,"2");
                    myDatabase.updateSatBreakfastData(meal4,meal4Nut,"3");
                    myDatabase.updateSatBreakfastData(meal5,meal5Nut,"4");
                    myDatabase.updateSatBreakfastData("","","5");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
//                    myDatabase.updateSatBreakfastData(meal2,meal2Nut,"1");
                    myDatabase.updateSatBreakfastData(meal3,meal3Nut,"2");
                    myDatabase.updateSatBreakfastData(meal4,meal4Nut,"3");
//                    myDatabase.updateSatBreakfastData(meal5,meal5Nut,"4");
                    myDatabase.updateSatBreakfastData("","","4");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateSatBreakfastData(meal3,meal3Nut,"2");
                    myDatabase.updateSatBreakfastData("","","3");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateSatBreakfastData(meal4,meal4Nut,"3");
                    myDatabase.updateSatBreakfastData(meal5,meal5Nut,"4");
                    myDatabase.updateSatBreakfastData("","","5");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateSatBreakfastData(meal4,meal4Nut,"3");
                    myDatabase.updateSatBreakfastData("","","4");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateSatBreakfastData(meal5,meal5Nut,"4");
                    myDatabase.updateSatBreakfastData("","","5");
                }

                saturdayBreakfast();
            }
        });
    }
    //Satday lunch
    public void deleteSatLunchMeal(String meal, String id){
        showDialog(this,"Are you sure to delete "+meal+"?",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                myDatabase.updateSatLunchData("","",id);
                String meal1 = "",meal1Nut = "",meal2 = "",meal2Nut = "",meal3 = "",meal3Nut = "",meal4 = "",meal4Nut = "",meal5 = "",meal5Nut = "";

                Cursor res1 = myDatabase.viewSatLunchMealsSpecific("1");
                while (res1.moveToNext()) {
                    meal1 = res1.getString(1);
                    meal1Nut = res1.getString(2);}
                Cursor res2 = myDatabase.viewSatLunchMealsSpecific("2");
                while (res2.moveToNext()) {
                    meal2 = res2.getString(1);
                    meal2Nut = res2.getString(2);}
                Cursor res3 = myDatabase.viewSatLunchMealsSpecific("3");
                while (res3.moveToNext()) {
                    meal3 = res3.getString(1);
                    meal3Nut = res3.getString(2);}
                Cursor res4 = myDatabase.viewSatLunchMealsSpecific("4");
                while (res4.moveToNext()) {
                    meal4 = res4.getString(1);
                    meal4Nut = res4.getString(2);}
                Cursor res5 = myDatabase.viewSatLunchMealsSpecific("5");
                while (res5.moveToNext()) {
                    meal5 = res5.getString(1);
                    meal5Nut = res5.getString(2);}

                if(meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateSatLunchData(meal2,meal2Nut,"1");
                    myDatabase.updateSatLunchData(meal3,meal3Nut,"2");
                    myDatabase.updateSatLunchData(meal4,meal4Nut,"3");
                    myDatabase.updateSatLunchData(meal5,meal5Nut,"4");
                    myDatabase.updateSatLunchData("","","5");
                }
                else if(meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateSatLunchData(meal2,meal2Nut,"1");
                    myDatabase.updateSatLunchData(meal3,meal3Nut,"2");
                    myDatabase.updateSatLunchData(meal4,meal4Nut,"3");
                    myDatabase.updateSatLunchData("","","4");
                }
                else if(meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateSatLunchData(meal2,meal2Nut,"1");
                    myDatabase.updateSatLunchData(meal3,meal3Nut,"2");
                    myDatabase.updateSatLunchData("","","3");
                }
                else if(meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateSatLunchData(meal2,meal2Nut,"1");
                    myDatabase.updateSatLunchData("","","2");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateSatLunchData(meal3,meal3Nut,"2");
                    myDatabase.updateSatLunchData(meal4,meal4Nut,"3");
                    myDatabase.updateSatLunchData(meal5,meal5Nut,"4");
                    myDatabase.updateSatLunchData("","","5");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateSatLunchData(meal3,meal3Nut,"2");
                    myDatabase.updateSatLunchData(meal4,meal4Nut,"3");
                    myDatabase.updateSatLunchData("","","4");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateSatLunchData(meal3,meal3Nut,"2");
                    myDatabase.updateSatLunchData("","","3");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateSatLunchData(meal4,meal4Nut,"3");
                    myDatabase.updateSatLunchData(meal5,meal5Nut,"4");
                    myDatabase.updateSatLunchData("","","5");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateSatLunchData(meal4,meal4Nut,"3");
                    myDatabase.updateSatLunchData("","","4");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateSatLunchData(meal5,meal5Nut,"4");
                    myDatabase.updateSatLunchData("","","5");
                }

                saturdayLunch();
            }
        });
    }
    //Saturday Supper
    public void deleteSatSupperMeal(String meal, String id){
        showDialog(this,"Are you sure to delete "+meal+"?",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                myDatabase.updateSatSupperData("","",id);
                String meal1 = "",meal1Nut = "",meal2 = "",meal2Nut = "",meal3 = "",meal3Nut = "",meal4 = "",meal4Nut = "",meal5 = "",meal5Nut = "";

                Cursor res1 = myDatabase.viewSatSupperMealsSpecific("1");
                while (res1.moveToNext()) {
                    meal1 = res1.getString(1);
                    meal1Nut = res1.getString(2);}
                Cursor res2 = myDatabase.viewSatSupperMealsSpecific("2");
                while (res2.moveToNext()) {
                    meal2 = res2.getString(1);
                    meal2Nut = res2.getString(2);}
                Cursor res3 = myDatabase.viewSatSupperMealsSpecific("3");
                while (res3.moveToNext()) {
                    meal3 = res3.getString(1);
                    meal3Nut = res3.getString(2);}
                Cursor res4 = myDatabase.viewSatSupperMealsSpecific("4");
                while (res4.moveToNext()) {
                    meal4 = res4.getString(1);
                    meal4Nut = res4.getString(2);}
                Cursor res5 = myDatabase.viewSatSupperMealsSpecific("5");
                while (res5.moveToNext()) {
                    meal5 = res5.getString(1);
                    meal5Nut = res5.getString(2);}

                if(meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateSatSupperData(meal2,meal2Nut,"1");
                    myDatabase.updateSatSupperData(meal3,meal3Nut,"2");
                    myDatabase.updateSatSupperData(meal4,meal4Nut,"3");
                    myDatabase.updateSatSupperData(meal5,meal5Nut,"4");
                    myDatabase.updateSatSupperData("","","5");
                }
                else if(meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateSatSupperData(meal2,meal2Nut,"1");
                    myDatabase.updateSatSupperData(meal3,meal3Nut,"2");
                    myDatabase.updateSatSupperData(meal4,meal4Nut,"3");
                    myDatabase.updateSatSupperData("","","4");
                }
                else if(meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateSatSupperData(meal2,meal2Nut,"1");
                    myDatabase.updateSatSupperData(meal3,meal3Nut,"2");
                    myDatabase.updateSatSupperData("","","3");
                }
                else if(meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateSatSupperData(meal2,meal2Nut,"1");
                    myDatabase.updateSatSupperData("","","2");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
//                    myDatabase.updateSatSupperData(meal2,meal2Nut,"1");
                    myDatabase.updateSatSupperData(meal3,meal3Nut,"2");
                    myDatabase.updateSatSupperData(meal4,meal4Nut,"3");
                    myDatabase.updateSatSupperData(meal5,meal5Nut,"4");
                    myDatabase.updateSatSupperData("","","5");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateSatSupperData(meal3,meal3Nut,"2");
                    myDatabase.updateSatSupperData(meal4,meal4Nut,"3");
                    myDatabase.updateSatSupperData("","","4");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateSatSupperData(meal3,meal3Nut,"2");
                    myDatabase.updateSatSupperData("","","3");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateSatSupperData(meal4,meal4Nut,"3");
                    myDatabase.updateSatSupperData(meal5,meal5Nut,"4");
                    myDatabase.updateSatSupperData("","","5");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateSatSupperData(meal4,meal4Nut,"3");
                    myDatabase.updateSatSupperData("","","4");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateSatSupperData(meal5,meal5Nut,"4");
                    myDatabase.updateSatSupperData("","","5");
                }

                saturdaySupper();
            }
        });
    }
    //sunday breakfast
    public void deleteSunBreakfastMeal(String meal, String id){
        showDialog(this,"Are you sure to delete "+meal+"?",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                myDatabase.updateSunBreakfastData("","",id);
                String meal1 = "",meal1Nut = "",meal2 = "",meal2Nut = "",meal3 = "",meal3Nut = "",meal4 = "",meal4Nut = "",meal5 = "",meal5Nut = "";

                Cursor res1 = myDatabase.viewSunBreakfastMealsSpecific("1");
                while (res1.moveToNext()) {
                    meal1 = res1.getString(1);
                    meal1Nut = res1.getString(2);}
                Cursor res2 = myDatabase.viewSunBreakfastMealsSpecific("2");
                while (res2.moveToNext()) {
                    meal2 = res2.getString(1);
                    meal2Nut = res2.getString(2);}
                Cursor res3 = myDatabase.viewSunBreakfastMealsSpecific("3");
                while (res3.moveToNext()) {
                    meal3 = res3.getString(1);
                    meal3Nut = res3.getString(2);}
                Cursor res4 = myDatabase.viewSunBreakfastMealsSpecific("4");
                while (res4.moveToNext()) {
                    meal4 = res4.getString(1);
                    meal4Nut = res4.getString(2);}
                Cursor res5 = myDatabase.viewSunBreakfastMealsSpecific("5");
                while (res5.moveToNext()) {
                    meal5 = res5.getString(1);
                    meal5Nut = res5.getString(2);}

                if(meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateSunBreakfastData(meal2,meal2Nut,"1");
                    myDatabase.updateSunBreakfastData(meal3,meal3Nut,"2");
                    myDatabase.updateSunBreakfastData(meal4,meal4Nut,"3");
                    myDatabase.updateSunBreakfastData(meal5,meal5Nut,"4");
                    myDatabase.updateSunBreakfastData("","","5");
                }
                else if(meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateSunBreakfastData(meal2,meal2Nut,"1");
                    myDatabase.updateSunBreakfastData(meal3,meal3Nut,"2");
                    myDatabase.updateSunBreakfastData(meal4,meal4Nut,"3");
                    myDatabase.updateSunBreakfastData("","","4");
                }
                else if(meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateSunBreakfastData(meal2,meal2Nut,"1");
                    myDatabase.updateSunBreakfastData(meal3,meal3Nut,"2");
                    myDatabase.updateSunBreakfastData("","","3");
                }
                else if(meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateSunBreakfastData(meal2,meal2Nut,"1");
                    myDatabase.updateSunBreakfastData("","","2");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
//                    myDatabase.updateSunBreakfastData(meal2,meal2Nut,"1");
                    myDatabase.updateSunBreakfastData(meal3,meal3Nut,"2");
                    myDatabase.updateSunBreakfastData(meal4,meal4Nut,"3");
                    myDatabase.updateSunBreakfastData(meal5,meal5Nut,"4");
                    myDatabase.updateSunBreakfastData("","","5");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
//                    myDatabase.updateSunBreakfastData(meal2,meal2Nut,"1");
                    myDatabase.updateSunBreakfastData(meal3,meal3Nut,"2");
                    myDatabase.updateSunBreakfastData(meal4,meal4Nut,"3");
//                    myDatabase.updateSunBreakfastData(meal5,meal5Nut,"4");
                    myDatabase.updateSunBreakfastData("","","4");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateSunBreakfastData(meal3,meal3Nut,"2");
                    myDatabase.updateSunBreakfastData("","","3");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateSunBreakfastData(meal4,meal4Nut,"3");
                    myDatabase.updateSunBreakfastData(meal5,meal5Nut,"4");
                    myDatabase.updateSunBreakfastData("","","5");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateSunBreakfastData(meal4,meal4Nut,"3");
                    myDatabase.updateSunBreakfastData("","","4");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateSunBreakfastData(meal5,meal5Nut,"4");
                    myDatabase.updateSunBreakfastData("","","5");
                }

                sundayBreakfast();
            }
        });
    }
    //Sunday lunch
    public void deleteSunLunchMeal(String meal, String id){
        showDialog(this,"Are you sure to delete "+meal+"?",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                myDatabase.updateSunLunchData("","",id);
                String meal1 = "",meal1Nut = "",meal2 = "",meal2Nut = "",meal3 = "",meal3Nut = "",meal4 = "",meal4Nut = "",meal5 = "",meal5Nut = "";

                Cursor res1 = myDatabase.viewSunLunchMealsSpecific("1");
                while (res1.moveToNext()) {
                    meal1 = res1.getString(1);
                    meal1Nut = res1.getString(2);}
                Cursor res2 = myDatabase.viewSunLunchMealsSpecific("2");
                while (res2.moveToNext()) {
                    meal2 = res2.getString(1);
                    meal2Nut = res2.getString(2);}
                Cursor res3 = myDatabase.viewSunLunchMealsSpecific("3");
                while (res3.moveToNext()) {
                    meal3 = res3.getString(1);
                    meal3Nut = res3.getString(2);}
                Cursor res4 = myDatabase.viewSunLunchMealsSpecific("4");
                while (res4.moveToNext()) {
                    meal4 = res4.getString(1);
                    meal4Nut = res4.getString(2);}
                Cursor res5 = myDatabase.viewSunLunchMealsSpecific("5");
                while (res5.moveToNext()) {
                    meal5 = res5.getString(1);
                    meal5Nut = res5.getString(2);}

                if(meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateSunLunchData(meal2,meal2Nut,"1");
                    myDatabase.updateSunLunchData(meal3,meal3Nut,"2");
                    myDatabase.updateSunLunchData(meal4,meal4Nut,"3");
                    myDatabase.updateSunLunchData(meal5,meal5Nut,"4");
                    myDatabase.updateSunLunchData("","","5");
                }
                else if(meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateSunLunchData(meal2,meal2Nut,"1");
                    myDatabase.updateSunLunchData(meal3,meal3Nut,"2");
                    myDatabase.updateSunLunchData(meal4,meal4Nut,"3");
                    myDatabase.updateSunLunchData("","","4");
                }
                else if(meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateSunLunchData(meal2,meal2Nut,"1");
                    myDatabase.updateSunLunchData(meal3,meal3Nut,"2");
                    myDatabase.updateSunLunchData("","","3");
                }
                else if(meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateSunLunchData(meal2,meal2Nut,"1");
                    myDatabase.updateSunLunchData("","","2");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateSunLunchData(meal3,meal3Nut,"2");
                    myDatabase.updateSunLunchData(meal4,meal4Nut,"3");
                    myDatabase.updateSunLunchData(meal5,meal5Nut,"4");
                    myDatabase.updateSunLunchData("","","5");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateSunLunchData(meal3,meal3Nut,"2");
                    myDatabase.updateSunLunchData(meal4,meal4Nut,"3");
                    myDatabase.updateSunLunchData("","","4");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateSunLunchData(meal3,meal3Nut,"2");
                    myDatabase.updateSunLunchData("","","3");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateSunLunchData(meal4,meal4Nut,"3");
                    myDatabase.updateSunLunchData(meal5,meal5Nut,"4");
                    myDatabase.updateSunLunchData("","","5");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateSunLunchData(meal4,meal4Nut,"3");
                    myDatabase.updateSunLunchData("","","4");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateSunLunchData(meal5,meal5Nut,"4");
                    myDatabase.updateSunLunchData("","","5");
                }

                sundayLunch();
            }
        });
    }
    //Sunday Supper
    public void deleteSunSupperMeal(String meal, String id){
        showDialog(this,"Are you sure to delete "+meal+"?",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                myDatabase.updateSunSupperData("","",id);
                String meal1 = "",meal1Nut = "",meal2 = "",meal2Nut = "",meal3 = "",meal3Nut = "",meal4 = "",meal4Nut = "",meal5 = "",meal5Nut = "";

                Cursor res1 = myDatabase.viewSunSupperMealsSpecific("1");
                while (res1.moveToNext()) {
                    meal1 = res1.getString(1);
                    meal1Nut = res1.getString(2);}
                Cursor res2 = myDatabase.viewSunSupperMealsSpecific("2");
                while (res2.moveToNext()) {
                    meal2 = res2.getString(1);
                    meal2Nut = res2.getString(2);}
                Cursor res3 = myDatabase.viewSunSupperMealsSpecific("3");
                while (res3.moveToNext()) {
                    meal3 = res3.getString(1);
                    meal3Nut = res3.getString(2);}
                Cursor res4 = myDatabase.viewSunSupperMealsSpecific("4");
                while (res4.moveToNext()) {
                    meal4 = res4.getString(1);
                    meal4Nut = res4.getString(2);}
                Cursor res5 = myDatabase.viewSunSupperMealsSpecific("5");
                while (res5.moveToNext()) {
                    meal5 = res5.getString(1);
                    meal5Nut = res5.getString(2);}

                if(meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateSunSupperData(meal2,meal2Nut,"1");
                    myDatabase.updateSunSupperData(meal3,meal3Nut,"2");
                    myDatabase.updateSunSupperData(meal4,meal4Nut,"3");
                    myDatabase.updateSunSupperData(meal5,meal5Nut,"4");
                    myDatabase.updateSunSupperData("","","5");
                }
                else if(meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateSunSupperData(meal2,meal2Nut,"1");
                    myDatabase.updateSunSupperData(meal3,meal3Nut,"2");
                    myDatabase.updateSunSupperData(meal4,meal4Nut,"3");
                    myDatabase.updateSunSupperData("","","4");
                }
                else if(meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateSunSupperData(meal2,meal2Nut,"1");
                    myDatabase.updateSunSupperData(meal3,meal3Nut,"2");
                    myDatabase.updateSunSupperData("","","3");
                }
                else if(meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateSunSupperData(meal2,meal2Nut,"1");
                    myDatabase.updateSunSupperData("","","2");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
//                    myDatabase.updateSunSupperData(meal2,meal2Nut,"1");
                    myDatabase.updateSunSupperData(meal3,meal3Nut,"2");
                    myDatabase.updateSunSupperData(meal4,meal4Nut,"3");
                    myDatabase.updateSunSupperData(meal5,meal5Nut,"4");
                    myDatabase.updateSunSupperData("","","5");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateSunSupperData(meal3,meal3Nut,"2");
                    myDatabase.updateSunSupperData(meal4,meal4Nut,"3");
                    myDatabase.updateSunSupperData("","","4");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateSunSupperData(meal3,meal3Nut,"2");
                    myDatabase.updateSunSupperData("","","3");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateSunSupperData(meal4,meal4Nut,"3");
                    myDatabase.updateSunSupperData(meal5,meal5Nut,"4");
                    myDatabase.updateSunSupperData("","","5");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&meal3.trim().equalsIgnoreCase("")&&!meal4.trim().equalsIgnoreCase("")&&meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateSunSupperData(meal4,meal4Nut,"3");
                    myDatabase.updateSunSupperData("","","4");
                }
                else if(!meal1.trim().equalsIgnoreCase("")&&!meal2.trim().equalsIgnoreCase("")&&!meal3.trim().equalsIgnoreCase("")&&meal4.trim().equalsIgnoreCase("")&&!meal5.trim().equalsIgnoreCase("")){
                    myDatabase.updateSunSupperData(meal5,meal5Nut,"4");
                    myDatabase.updateSunSupperData("","","5");
                }

                sundaySupper();
            }
        });
    }

}
