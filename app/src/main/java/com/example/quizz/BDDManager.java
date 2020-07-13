package com.example.quizz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import static java.sql.Types.INTEGER;

public class BDDManager extends SQLiteOpenHelper {
   private static final String DATABASE_NAME="Quizz";

    private static final int DATABASE_VERSION=1;
    private static final String Mot_Table="MotTable";
    private static final String Score_Table="ScoreTable";

    private static final String Mot_ID="MotId";
    private static final String Score_ID="ScoreId";

    private static final String Mot_ATT="MotAtt";
    private static final String Score_ATT="ScoreAtt";
    private static final String Score_POSITION="Scoreposition";


    private static final  String CREATE_Mots_Table="CREATE TABLE "
            + Mot_Table + "(" + Mot_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + Mot_ATT + " TEXT "  + ")";

    private static final String CREATE_Score_Table = "CREATE TABLE "
            + Score_Table + "(" + Score_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + Score_ATT+ " TEXT," + Score_POSITION + " INTEGER" + ")";





    public BDDManager( Context context) {
        super(context,DATABASE_NAME,null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       db.execSQL(CREATE_Mots_Table);
       db.execSQL(CREATE_Score_Table);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+Mot_Table + ";");

        db.execSQL("DROP TABLE IF EXISTS "+Score_Table + ";");
        onCreate(db);

    }
    //Ajouter les mots
    public long AjoutMot(Mots mots){

        SQLiteDatabase database=this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(Mot_ATT,mots.getMots());
        //insering
        long mot_id=database.insert(Mot_Table,null,values);
        return mot_id;
    }


    Boolean addScore(long position){
       ContentValues contentValues=new ContentValues();
       contentValues.put(Score_POSITION,position);
       SQLiteDatabase database=getWritableDatabase();

        return database.insert(Score_Table,null,contentValues) !=-1 ;
    }
    Cursor getPosition(){
        SQLiteDatabase database=getReadableDatabase();


        return database.rawQuery("SELECT "+Score_POSITION +" FROM "+ Score_Table,null );
    }



    //Recuperation des mots
    public List<Mots> getMots(){
        List<Mots> mots= new ArrayList<Mots>();
        String selectQuery="SELECT * FROM " + Mot_Table;

        SQLiteDatabase database =this.getReadableDatabase();
        Cursor cursor=database.rawQuery(selectQuery,null);

      if (cursor.moveToFirst()){
            do {Mots mots1= new Mots();
               mots1.setId(cursor.getInt((cursor.getColumnIndex(Mot_ID))));
                mots1.setMots((cursor.getString(cursor.getColumnIndex(Mot_ATT))));

                mots.add(mots1);} while (cursor.moveToNext());}

        return mots;
    }

    public List<Mots> getMot(long p){
        List<Mots> mots= new ArrayList<Mots>();
        String selectQuery="SELECT * FROM " + Mot_Table +" WHERE "+ Mot_ID +" = "+ p;

        SQLiteDatabase database =this.getReadableDatabase();
        Cursor cursor=database.rawQuery(selectQuery,null);

        if (cursor.moveToFirst()){
            do {Mots mots1= new Mots();
                mots1.setId(cursor.getInt((cursor.getColumnIndex(Mot_ID))));
                mots1.setMots((cursor.getString(cursor.getColumnIndex(Mot_ATT))));

                mots.add(mots1);} while (cursor.moveToNext());}

        return mots;
    }

    public Mots getMot1(long p){
        Mots mots1= new Mots();
        String selectQuery="SELECT * FROM " + Mot_Table +" WHERE "+ Mot_ID +" = "+ p;

        SQLiteDatabase database =this.getReadableDatabase();
        Cursor cursor=database.rawQuery(selectQuery,null);

        if (cursor.moveToFirst()){
            do {
                mots1.setId(cursor.getInt((cursor.getColumnIndex(Mot_ID))));
                mots1.setMots((cursor.getString(cursor.getColumnIndex(Mot_ATT))));

            } while (cursor.moveToNext());}

        return mots1;
    }

    public Score getScore(long p){
        Score score=new Score();
        String selectQuery="SELECT * FROM "+ Score_Table ;

        SQLiteDatabase database =this.getReadableDatabase();
        Cursor cursor=database.rawQuery(selectQuery,null);

        if (cursor.moveToFirst()){
            do {
            score.setIdS(cursor.getInt((cursor.getColumnIndex(Score_ID))));
              score.setScore((cursor.getString(cursor.getColumnIndex(Score_ATT))));
               score.setPosition((cursor.getLong(cursor.getColumnIndex(Score_POSITION))));

            } while (cursor.moveToNext());}

        return score;
    }




    //Delete
    public void deleteMots(long mot_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Mot_Table, Mot_ID + " = ?",

                new String[] { String.valueOf(mot_id) });
    }
    public void deleteScores(long score_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Score_Table, Score_ID + " = ?",

                new String[] { String.valueOf(score_id) });
    }

    //Close
    public void closeBD(){
        SQLiteDatabase database=this.getReadableDatabase();
        if (database !=null && database.isOpen())
            database.close();
    }
    public Boolean check(){
        Boolean b;
        SQLiteDatabase database=this.getWritableDatabase();
        String count ="SELECT count(*) FROM "+Mot_Table;
        Cursor cursor=database.rawQuery(count,null);
        cursor.moveToFirst();
        int icount=cursor.getInt(0);
        if (icount==0){
        b=  true;
        }else b=false;
    return b;}
}


