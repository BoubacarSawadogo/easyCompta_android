package com.example.easycompta.manager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.easycompta.entities.User;
import com.example.easycompta.services.ConnexionBd;

import java.util.ArrayList;
import java.util.List;

public class UserManager {

    // This method help to get all users in database
    private final static String queryGetAll = "select * from user";

    public static List<User> getAll(Context ctx) {
        ArrayList<User> users = new ArrayList<>();

        SQLiteDatabase bd = ConnexionBd.getBd(ctx);

        Cursor cursor = bd.rawQuery(queryGetAll, null);

        while (cursor.moveToNext()) {
            users.add(new User(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5)));
        }

        return users;
    }

    // Add new user
    public static Boolean add(User user, Context ctx) {
        ContentValues cv = new ContentValues();
        SQLiteDatabase bd = ConnexionBd.getBd(ctx);
        cv.put("id", user.getId());
        cv.put("firstname", user.getFirstname());
        cv.put("lastname", user.getLastname());
        cv.put("email", user.getEmail());
        cv.put("password", user.getPassword());
        cv.put("phone", user.getPhone());
        long resultat = bd.insert("user", null, cv);
        ConnexionBd.closeBd();
        if(resultat ==-1) return false;
        else return true;

    }

    public static Boolean checkemail(String email,Context ctx) {
        SQLiteDatabase db = ConnexionBd.getBd(ctx);
        Cursor cursor = db.rawQuery("select * from user  where email = ?", new String[]{email});
        if (cursor.getCount() > 0) return false;
        else return true;
    }

    public static Boolean checkemailpassword(String email, String password, Context ctx) {
        SQLiteDatabase db = ConnexionBd.getBd(ctx);
        Cursor cursor = db.rawQuery("select * from user where email = ? and password = ?", new String[]{email, password});
        if (cursor.getCount() > 0) return true;
        else return false;
    }

  /* public static String getUserFirstname(String password, String username, Context ctx){
        SQLiteDatabase db = ConnexionBd.getBd(ctx);
        Cursor cursor = db.rawQuery("select * from user where email=" +username+" and password="+password, null);
        if(cursor!=null)
    {
        cursor.moveToFirst();
        }
        String prenom = cursor.getString(cursor.getColumnIndex("firstname"));
       return  prenom;
    }

    public static  ArrayList<User> getByLastNamePwd(String username, String password, Context ctx){
        ArrayList<User> listUser = new ArrayList<User>();
        SQLiteDatabase db = ConnexionBd.getBd(ctx);
        Cursor cursor = db.rawQuery("select * from user where email=" +username+" and password="+password, new String[]{password, username});
        while (cursor.moveToNext()){
            User user =new User();
            user.setId(cursor.getInt(0));
            user.setFirstname(cursor.getString(1));
            user.setLastname(cursor.getString(2));
            user.setPassword(cursor.getString(3));
            user.setPhone(cursor.getString(4));
            listUser.add(user);
        }
        cursor.close();

        return listUser;
    }*/


}
