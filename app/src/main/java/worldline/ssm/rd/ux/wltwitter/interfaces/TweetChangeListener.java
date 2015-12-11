package worldline.ssm.rd.ux.wltwitter.interfaces;

import java.util.List;

import worldline.ssm.rd.ux.wltwitter.pojo.Tweet;

/**
 * Created by Matthieu on 11/12/2015.
 */
public interface TweetChangeListener {
    public void onTweetRetrieved(List<Tweet> tweets);
}
