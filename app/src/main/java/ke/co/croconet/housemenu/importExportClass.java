package ke.co.croconet.housemenu;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

public class importExportClass extends MainActivity{
    static String mealMenuTitle="";
    static String mealMenuStr= "Coming soon, keep checking for updates. Thanks for installing.";

//    Toast.makeText(getApplicationContext(),"Import Successful!", Toast.LENGTH_SHORT).show();

//
//        public void importDB(Context context) {
//            try {
//                File sd = Environment.getExternalStorageDirectory();
//                if (sd.canWrite()) {
//                    File backupDB = context.getDatabasePath(databaseHelper.table());
//                    String backupDBPath = String.format("%s.bak", databaseHelper.table());
//                    File currentDB = new File(sd, backupDBPath);
//
//                    FileChannel src = new FileInputStream(currentDB).getChannel();
//                    FileChannel dst = new FileOutputStream(backupDB).getChannel();
//                    dst.transferFrom(src, 0, src.size());
//                    src.close();
//                    dst.close();
//
//                    Toast.makeText(getApplicationContext(),"Import Successful!", Toast.LENGTH_SHORT).show();
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//                Toast.makeText(getApplicationContext(),"Import failed! "+e, Toast.LENGTH_SHORT).show();
//
//            }
//        }
//
//        public static void exportDB(Context context) {
//            try {
//                File sd = Environment.getExternalStorageDirectory();
//                File data = Environment.getDataDirectory();
//
//                if (sd.canWrite()) {
//                    String backupDBPath = String.format("%s.bak", DBHandler.getDBName());
//                    File currentDB = context.getDatabasePath(DBHandler.getDBName());
//                    File backupDB = new File(sd, backupDBPath);
//
//                    FileChannel src = new FileInputStream(currentDB).getChannel();
//                    FileChannel dst = new FileOutputStream(backupDB).getChannel();
//                    dst.transferFrom(src, 0, src.size());
//                    src.close();
//                    dst.close();
//
//                    MyApplication.toastSomething(context, "Backup Successful!");
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }



}

//imageButton.setOnTouchListener(new OnTouchListener() {
//@Override
//public boolean onTouch(View v, MotionEvent event) {
//        if(event.getAction() == MotionEvent.ACTION_UP){
//
//        // Do what you want
//        return true;
//        }
//        return false;
//        }
//        });
