package worldline.ssm.rd.ux.wltwitter.async;

import android.os.AsyncTask;

import java.util.List;

import worldline.ssm.rd.ux.wltwitter.helpers.TwitterHelper;
import worldline.ssm.rd.ux.wltwitter.pojo.Tweet;

/**
 * Created by Matthieu on 11/12/2015.
 */
public class RetrieveTweetsAsyncTask extends AsyncTask<String, Integer  , List<Tweet>> {
    private String appName = "WLTwitter";
    @Override
    protected List<Tweet> doInBackground(String... params) {
        int size = params.length;
        List<Tweet> tweetRetrieved = null;
        for(int i=0;i<size;i++) {
            if(i == 0) {
                tweetRetrieved = TwitterHelper.getTweetsOfUser(params[0]);
            }
        }
        return tweetRetrieved;
    }

    @Override
    protected void onPostExecute(List<Tweet> tweets) {
        for(Tweet t: tweets) {
            System.out.println("["+this.appName+"]" + t.text);
        }
    }
}
