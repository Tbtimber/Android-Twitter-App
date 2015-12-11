package worldline.ssm.rd.ux.wltwitter;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import worldline.ssm.rd.ux.wltwitter.async.RetrieveTweetsAsyncTask;
import worldline.ssm.rd.ux.wltwitter.utils.PreferenceHandler;


public class WLTwitterActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Subtitle setup
        String login = getIntent().getExtras().getString("login"); //Get string stored in intent's extras

        try {
            getActionBar().setSubtitle(login); //Set subtitle to be that string
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        RetrieveTweetsAsyncTask task = new RetrieveTweetsAsyncTask();
        task.execute(login);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId(); //Get selected menu item ID

        //noinspection SimplifiableIfStatement
        if (id == R.id.logout_setting) { //Check if right id
            logout(); //perform logout actions
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void logout() {
        //Delete previous log info in preferences
        //Context context = getApplication();
        //SharedPreferences prefs = context.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        //SharedPreferences.Editor editor =  prefs.edit();
        //editor.clear(); //Clear all preferences
        //editor.commit(); //Apply changes

        PreferenceHandler.clearPref();

        finish();//Finish this activity => go back to previous (login)
    }
}
