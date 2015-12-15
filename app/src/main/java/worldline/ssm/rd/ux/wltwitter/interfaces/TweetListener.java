package worldline.ssm.rd.ux.wltwitter.interfaces;

import worldline.ssm.rd.ux.wltwitter.pojo.Tweet;

/**
 * Created by Matthieu on 11/12/2015.
 */
public interface TweetListener {
    void onRetweet(Tweet tweet);
    void onViewTweet(Tweet tweet);
    void onTweetRetrieved();
}
