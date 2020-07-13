package com.example.quizz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    BDDManager bddManager;
    String string="";
    Mots mots;
    final long p=1;
    TextView motView;
    TextView motView2;
    private static final String TAG="MainActivity";
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         final TextView resultat=(TextView) findViewById(R.id.textView);
         final  TextView motView=(TextView) findViewById(R.id.textView2);
         final TextView motView2=(TextView) findViewById(R.id.textView3);
         final EditText edit1=(EditText) findViewById(R.id.edit1);
         final  EditText edit2=(EditText) findViewById(R.id.edit);
         final Button button=(Button) findViewById(R.id.button);
         final ImageView imageView=(ImageView) findViewById(R.id.imageView3);
         bddManager=new BDDManager(getApplicationContext());
        Mots mot1=new Mots("bags");
        Mots mot2=new Mots("draw");
        Mots mot3=new Mots("hair");
        Mots mot4=new Mots("boat");

        Mots mot5=new Mots("hero");
        Mots mot6=new Mots("home");
        Mots mot7=new Mots("left");
        Mots mot8=new Mots("mail");
        Mots mot9=new Mots("meet");
        Mots mot10=new Mots("path");
        Mots mot11=new Mots("lila");

if (bddManager.check()){


        long mot_id=bddManager.AjoutMot(mot1);
        long mot_id2=bddManager.AjoutMot(mot2);
        long mot_id3=bddManager.AjoutMot(mot3);
        long mot_id4=bddManager.AjoutMot(mot4);

        long mot_id5=bddManager.AjoutMot(mot5);
        long mot_id6=bddManager.AjoutMot(mot6);
        long mot_id7=bddManager.AjoutMot(mot7);

        long mot_id9=bddManager.AjoutMot(mot9);
        long mot_id10=bddManager.AjoutMot(mot10);
        long mot_id11=bddManager.AjoutMot(mot11);
}


        Toast.makeText(this, String.valueOf(bddManager.getScore(p).position), Toast.LENGTH_SHORT).show();
        if (bddManager.getScore(p).position<10){

            nextWord(bddManager.getScore(p).position+1);
            resultat.setText(String.valueOf(bddManager.getScore(p).position)+"/10");

        }
        else
            if (bddManager.getScore(p).position>=10){

            Intent intent=new Intent(MainActivity.this,Bravo.class);
            startActivity(intent);


        }

 }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"onDestroy");


         bddManager.close();
    }

    public  String nextWord(final long p){
        string="";
    final TextView resultat=(TextView) findViewById(R.id.textView);
    final  TextView motView=(TextView) findViewById(R.id.textView2);
    final TextView motView2=(TextView) findViewById(R.id.textView3);
    final EditText edit1=(EditText) findViewById(R.id.edit1);
    final  EditText edit2=(EditText) findViewById(R.id.edit);
        final ImageView imageView=(ImageView) findViewById(R.id.imageView3);
    edit1.setText("");
    edit2.setText("");
     //   imageView.setVisibility(View.GONE);
    string = string + bddManager.getMot1(p).mots;
    final char first = string.charAt(0);
    final char second = string.charAt(1);
    final char third = string.charAt(2);
    char last = string.charAt(string.length() - 1);
    final Button button=(Button) findViewById(R.id.button);


    motView.setText(String.valueOf(first));
    motView2.setText(String.valueOf(last));

  //  Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
    button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(MainActivity.this, string, Toast.LENGTH_SHORT).show();
            String editvaluue1 = edit1.getText().toString();
            String editvaluue2 = edit2.getText().toString();
            String position="";
            //       Score score=new Score(String.valueOf(p));
            //            long score_id=bddManager.AjoutScore(score);


            if ((editvaluue1.equals(String.valueOf(second))) && (editvaluue2.equals(String.valueOf(third))))
            {   Score score=new Score(p);
                add(p);




                Toast toast = new Toast(MainActivity.this);
                ImageView view = new ImageView(MainActivity.this);
                view.setImageResource(R.drawable.happy);
                toast.setView(view);
                toast.show();
                position=String.valueOf(bddManager.getScore(p).position);
                // imageView.setImageResource(R.drawable.happy);
               //Toast.makeText(MainActivity.this, bddManager.getScore(p).score, Toast.LENGTH_SHORT).show();

                resultat.setText(position + "/10");
                nextWord(p+1);
            }else {
                edit1.setText("");
                edit2.setText("");
                Toast toast = new Toast(MainActivity.this);
                ImageView view = new ImageView(MainActivity.this);
                view.setImageResource(R.drawable.sad);
                toast.setView(view);
                toast.show();

            }
        }
    });

    return string;
}
public Boolean add(long position){

       return bddManager.addScore(position);
}


}
