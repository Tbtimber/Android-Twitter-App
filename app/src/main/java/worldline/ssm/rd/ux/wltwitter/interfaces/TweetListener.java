package worldline.ssm.rd.ux.wltwitter.interfaces;

import worldline.ssm.rd.ux.wltwitter.pojo.Tweet;

/**
 * Created by Matthieu on 11/12/2015.
 */
public interface TweetListener {
    public void onRetweet(Tweet tweet);
    public void onViewTweet(Tweet tweet);
}
