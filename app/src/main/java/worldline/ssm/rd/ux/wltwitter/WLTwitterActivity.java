package worldline.ssm.rd.ux.wltwitter;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


import worldline.ssm.rd.ux.wltwitter.async.RetrieveTweetsAsyncTask;
import worldline.ssm.rd.ux.wltwitter.interfaces.TweetListener;
import worldline.ssm.rd.ux.wltwitter.pojo.Tweet;
import worldline.ssm.rd.ux.wltwitter.utils.PreferenceHandler;


public class WLTwitterActivity extends Activity implements TweetListener {
    @Override
    public void onRetweet(Tweet tweet) {

    }

    @Override
    public void onViewTweet(Tweet tweet) {
        Toast.makeText(this, tweet.text, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onTweetRetrieved() {
        //pour changer la couleur entourant la listView du fragment
       // findViewById(R.id.main_activity_layout).setBackgroundColor(getResources().getColor(R.color.dark_gray_twitter));
        findViewById(R.id.main_activity_layout).setBackgroundColor(getResources().getColor(R.color.blue_twitter));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Subtitle setup
        String login = getIntent().getExtras().getString(getString(R.string.key_login)); //Get string stored in intent's extras

        try {
            getActionBar().setSubtitle(login); //Set subtitle to be that string
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        findViewById(R.id.main_activity_layout).setBackgroundColor(getResources().getColor(R.color.blue_twitter));

        TweetFragment fragment = new TweetFragment();
        fragmentTransaction.add(R.id.main_activity_layout, fragment).commit();
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

        PreferenceHandler.clearPref();

        finish();//Finish this activity => go back to previous (login)
    }
}
