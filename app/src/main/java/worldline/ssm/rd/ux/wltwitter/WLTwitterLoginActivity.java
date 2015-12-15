package worldline.ssm.rd.ux.wltwitter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import worldline.ssm.rd.ux.wltwitter.utils.PreferenceHandler;


/**
 * Created by Matthieu on 04/12/2015.
 */
public class WLTwitterLoginActivity extends Activity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_layout);

        findViewById(R.id.loginButton).setOnClickListener(this);


        if((PreferenceHandler.getPref(getString(R.string.key_login)) != null) && (PreferenceHandler.getPref(getString(R.string.key_password)) != null)) {
            Intent intent = new Intent(getApplicationContext(), WLTwitterActivity.class);
            Bundle extras = new Bundle();
            extras.putString(getString(R.string.key_login), PreferenceHandler.getPref(getString(R.string.key_login)));
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
            Toast.makeText(this, getString(R.string.toast_error_login), Toast.LENGTH_LONG).show();
        } else {
            if(((CheckBox)findViewById(R.id.rememberMe)).isChecked()) {
                PreferenceHandler.addPref(getString(R.string.key_login), loginText.getText().toString());
                PreferenceHandler.addPref(getString(R.string.key_password), passWordText.getText().toString());
            }

            //Create and send Intent
            Intent intent = new Intent(getApplicationContext(), WLTwitterActivity.class);
            Bundle extras = new Bundle(); //Create a bundle
            extras.putString( getString(R.string.key_login) ,loginText.getText().toString()); //Fill bundle extras with login text
            intent.putExtras(extras); //Add bundle to intent
            startActivity(intent); //Activate intent
           // finish(); //Finish this activity
        }
    }
}
