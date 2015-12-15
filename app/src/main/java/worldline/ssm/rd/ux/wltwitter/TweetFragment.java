package worldline.ssm.rd.ux.wltwitter;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import java.util.List;

import worldline.ssm.rd.ux.wltwitter.async.RetrieveTweetsAsyncTask;
import worldline.ssm.rd.ux.wltwitter.interfaces.TweetChangeListener;
import worldline.ssm.rd.ux.wltwitter.interfaces.TweetListener;
import worldline.ssm.rd.ux.wltwitter.pojo.Tweet;
import worldline.ssm.rd.ux.wltwitter.utils.PreferenceHandler;

public class TweetFragment extends Fragment implements TweetChangeListener, AdapterView.OnItemClickListener{
    private ListView mListView;
    private RetrieveTweetsAsyncTask mAsyncTask;
    private TweetListener mListener;
    @Override
    public void onAttach(Activity activity) {
        //attach fragment to activity + setListener of the fragment from the TweetActivity
        super.onAttach(activity);
        if(activity instanceof TweetListener) {
            mListener = (TweetListener) activity;
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        final String login = PreferenceHandler.getPref("login");

        if(!TextUtils.isEmpty(login)) {
            mAsyncTask = new RetrieveTweetsAsyncTask(this);
            mAsyncTask.execute(login);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_tweet, container, false);
        mListView = (ListView) rootView.findViewById(R.id.tweetsListView);
        mListView.setOnItemClickListener(this);
        final ProgressBar progressBar = new ProgressBar(getActivity());
        progressBar.setLayoutParams(new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.WRAP_CONTENT, Gravity.CENTER));
        progressBar.setIndeterminate(true);
        mListView.setBackgroundColor(getResources().getColor(R.color.blue_twitter));
        mListView.setEmptyView(progressBar);

        ViewGroup root = (ViewGroup) rootView.findViewById(R.id.tweetsRootRelativeLayout);
        root.addView(progressBar);
        root.setBackgroundColor(getResources().getColor(R.color.blue_twitter));
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onTweetRetrieved(List<Tweet> tweets) {
        final ArrayAdapter<Tweet> adapter = new ArrayAdapter<Tweet>(getActivity(), android.R.layout.simple_list_item_1, tweets);
        mListView.setBackgroundColor(getResources().getColor(R.color.grey_twitter));
        mListener.onTweetRetrieved();
        mListView.setAdapter(adapter);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(null != mListener) {
            final Tweet tweet = (Tweet) parent.getItemAtPosition(position);
            mListener.onViewTweet(tweet);
        }
    }
}
