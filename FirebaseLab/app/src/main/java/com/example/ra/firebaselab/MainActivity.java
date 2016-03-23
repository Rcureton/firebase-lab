package com.example.ra.firebaselab;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.firebase.ui.FirebaseListAdapter;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Firebase mFire;
    ListView mListview;
    Button mButton;
    EditText mEditText;
    String mUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        setupUsername();

        mButton=(Button)findViewById(R.id.button);
        mListview=(ListView)findViewById(R.id.listView);
        mEditText=(EditText)findViewById(R.id.editText);

        mFire= new Firebase("https://chatroomappga.firebaseio.com/");

        final Firebase userRef= mFire.child("user-name");
        final Firebase messagesRef= mFire.child("messages");

        FirebaseListAdapter<String> adapter1= new FirebaseListAdapter<String>(this,String.class, android.R.layout.simple_list_item_1,messagesRef) {
            @Override
            protected void populateView(View view, String s, int i) {
                TextView text= (TextView)view.findViewById(android.R.id.text1);
                text.setText(s);
            }
        };

        mListview.setAdapter(adapter1);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userRef.setValue(mEditText.getText().toString());
                messagesRef.push().setValue(mEditText.getText().toString());
            }
        });




    }
    private void setupUsername(){
        SharedPreferences preferences= getApplication().getSharedPreferences("ChatPrefs",0);
        mUsername= preferences.getString("username",null);
        if(mUsername==null){
            Random r=new Random();
            mUsername= "User" + r.nextInt(10000);
            preferences.edit().putString("username",mUsername).commit();

        }
    }
}
