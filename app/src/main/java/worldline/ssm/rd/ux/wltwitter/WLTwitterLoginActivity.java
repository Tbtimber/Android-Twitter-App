package worldline.ssm.rd.ux.wltwitter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


/**
 * Created by Matthieu on 04/12/2015.
 */
public class WLTwitterLoginActivity extends Activity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_layout);

        findViewById(R.id.loginButton).setOnClickListener(this);

        Context context = getApplication();
        SharedPreferences sharedPref = context.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        if ((sharedPref.getString("login", "null") != "null") && (sharedPref.getString("password", "null") != "null")) {
            Intent intent = new Intent(getApplicationContext(), WLTwitterActivity.class);
            Bundle extras = new Bundle();
            extras.putString("login", sharedPref.getString("login", "null"));
            intent.putExtras(extras);
            startActivity(intent);
        }


    }

    @Override
    public void onClick(View v) {
        //Get the login and password edit text content
        EditText loginText = (EditText) findViewById(R.id.loginEditText);
        EditText passWordText = (EditText) findViewById(R.id.passwordEditText);

        //If they are empty Toast the shit out the users
        if(TextUtils.isEmpty(loginText.getText()) || TextUtils.isEmpty(passWordText.getText())) {
            //Toast Error message
            Toast.makeText(this, "Error", Toast.LENGTH_LONG).show();
        } else {
            //Create new SharedParameters
            Context context = getApplication();
            SharedPreferences prefs = context.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit(); //Get editor
            //Create/Change preferences parameter
            editor.putString("login", loginText.getText().toString());
            editor.putString("password", passWordText.getText().toString());
            editor.commit(); //Apply changes to sharedpreference


            //Create and send Intent
            Intent intent = new Intent(getApplicationContext(), WLTwitterActivity.class);
            Bundle extras = new Bundle(); //Create a bundle
            extras.putString( "login" ,loginText.getText().toString()); //Fill bundle extras with login text
            intent.putExtras(extras); //Add bundle to intent
            startActivity(intent); //Activate intent
           // finish(); //Finish this activity
        }
    }
}
