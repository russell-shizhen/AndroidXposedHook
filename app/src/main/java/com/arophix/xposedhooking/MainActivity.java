package com.arophix.xposedhooking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // Example of a call to a native method
        TextView tv = (TextView) findViewById(R.id.sample_text);
    
        DummyPerson dummyPerson = new DummyPerson();
        String myName = dummyPerson.getFullName();
        tv.setText(myName);
    
        TextView tv2 = (TextView) findViewById(R.id.sample_text2);
        String myName2 = dummyPerson.getFullName("zhang", "shizhen");
        tv2.setText(myName2);
    }

}
