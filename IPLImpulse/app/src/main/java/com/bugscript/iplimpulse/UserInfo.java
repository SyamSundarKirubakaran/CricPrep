package com.bugscript.iplimpulse;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserInfo extends AppCompatActivity {
    @BindView(R.id.editText) EditText u_name;
    @BindView(R.id.spinner) Spinner spinner;
    @BindView(R.id.floatingActionButton) FloatingActionButton fab1;

    public String[] teams = {"CSK", "RCB", "MI", "KXIP", "KKR", "DD", "RR", "SRH"};
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        ButterKnife.bind(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,teams);
        spinner.setAdapter(adapter);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Are you sure about this?", Snackbar.LENGTH_LONG)
                        .setAction("Yes", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String u_name_confirmed = u_name.getText().toString().trim();
                                String home_team = spinner.getSelectedItem().toString();
                                databaseReference= FirebaseDatabase.getInstance().getReference();
                                databaseReference.child("user").child(MainActivity.currentUser.getUid()).child("user_name").setValue(u_name_confirmed);
                                databaseReference.child("user").child(MainActivity.currentUser.getUid()).child("support_team").setValue(home_team);
                                finish();
                            }
                        }).show();
            }
        });
    }
}