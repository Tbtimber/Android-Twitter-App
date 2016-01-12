package worldline.ssm.rd.ux.wltwitter.async;

import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import worldline.ssm.rd.ux.wltwitter.helpers.TwitterHelper;
import worldline.ssm.rd.ux.wltwitter.interfaces.TweetChangeListener;
import worldline.ssm.rd.ux.wltwitter.pojo.Tweet;

/**
 * Created by Matthieu on 11/12/2015.
 */
public class RetrieveTweetsAsyncTask extends AsyncTask<String, Integer, List<Tweet>> {
    private TweetChangeListener mListener;

    public RetrieveTweetsAsyncTask(TweetChangeListener mListener) {
        this.mListener = mListener;
    }

    @Override
    protected void onPostExecute(List<Tweet> tweets) {
        super.onPostExecute(tweets);
        for(Tweet t: tweets) {
            Log.d("TweetAsyncTask", t.text);
        }
        mListener.onTweetRetrieved(tweets);
    }

    @Override
    protected List<Tweet> doInBackground(String... params) {
        if(params.length >= 1) {
            if(params[0] != null) {
                List<Tweet> tweets = TwitterHelper.getTweetsOfUser(params[0]);
                return tweets;
            }
        }
        return null;
    }
}
