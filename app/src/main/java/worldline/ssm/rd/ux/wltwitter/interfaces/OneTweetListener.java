package worldline.ssm.rd.ux.wltwitter.interfaces;

import worldline.ssm.rd.ux.wltwitter.pojo.Tweet;

/**
 * Created by Matthieu on 17/12/2015.
 */
public interface OneTweetListener {
    void onRetweetClick(Tweet tweet);
    void onReplyClick(Tweet tweet);
    void onOutOf();
    void onStarClick(Tweet tweet);

}
