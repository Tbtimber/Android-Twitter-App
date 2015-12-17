package worldline.ssm.rd.ux.wltwitter;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import worldline.ssm.rd.ux.wltwitter.pojo.Tweet;

/**
 * Created by Matthieu on 17/12/2015.
 */
public class OneTweetFragment extends Fragment {
    final static private String TWEET_KEY = "tweet";

    public static OneTweetFragment newInstance(Tweet tweet) {
        final OneTweetFragment fragment = new OneTweetFragment();
        final Bundle argument = new Bundle();
        argument.putParcelable(TWEET_KEY, tweet);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.one_tweet_fragment_layout, container, false);
    }
}
