package worldline.ssm.rd.ux.wltwitter;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Space;
import android.widget.TextView;

import worldline.ssm.rd.ux.wltwitter.async.RetrieveTwitterPic;
import worldline.ssm.rd.ux.wltwitter.interfaces.OneTweetListener;
import worldline.ssm.rd.ux.wltwitter.pojo.Tweet;

/**
 * Created by Matthieu on 17/12/2015.
 */
public class OneTweetFragment extends Fragment implements View.OnClickListener {
    final static private String TWEET_KEY = "tweet";
    private OneTweetListener mListener;
    private Tweet mTweet;

    public static OneTweetFragment newInstance(Tweet tweet) {
        final OneTweetFragment fragment = new OneTweetFragment();
        final Bundle argument = new Bundle();
        argument.putParcelable(TWEET_KEY, tweet);
        fragment.setArguments(argument);
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(activity instanceof OneTweetListener) {
            mListener = (OneTweetListener)activity;
        }


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.one_tweet_fragment_layout, container, false);
        mTweet = getArguments().getParcelable(TWEET_KEY);

        ((TextView)rootView.findViewById(R.id.userName_one_t_frag)).setText(mTweet.user.name);
        ((TextView)rootView.findViewById(R.id.alias_one_t_frag)).setText("@" + mTweet.user.screenName);
        ((TextView)rootView.findViewById(R.id.text_one_t_frag)).setText(mTweet.text);



        return rootView;
    }

    public void setClickActions(View view) {
        Button reply = (Button)view.findViewById(R.id.reply_one_t_frag);
        reply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onReplyClick(mTweet);
            }
        });

        Button retweet = (Button)view.findViewById(R.id.retweet_one_t_frag);
        retweet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onRetweetClick(mTweet);
            }
        });

        Button star = (Button)view.findViewById(R.id.star_one_t_frag);
        star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onStarClick(mTweet);
            }
        });

       /* Space space = (Space)view.findViewById(R.id.empty_one_t_frag);
        space.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onOutOf();
            }
        });*/
    }

    @Override
    public void onClick(View v) {

    }
}
