package worldline.ssm.rd.ux.wltwitter.fragments;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.List;

import worldline.ssm.rd.ux.wltwitter.R;
import worldline.ssm.rd.ux.wltwitter.async.RetrieveTweetsAsyncTask;
import worldline.ssm.rd.ux.wltwitter.interfaces.TweetsChangeListener;
import worldline.ssm.rd.ux.wltwitter.pojo.Tweet;
import worldline.ssm.rd.ux.wltwitter.utils.PreferenceHandler;

/**
 * Created by Matthieu on 11/12/2015.
 */
public class TweetsFragment extends Fragment implements TweetsChangeListener {
    TweetsInterfaceListener mListener;
    private String login;
    private ListView mListView;
    RetrieveTweetsAsyncTask mTask;

    public TweetsFragment(String login) {
        this.login = login;
    }

    @Override
    public void onStart() {
        super.onStart();

        final String login = PreferenceHandler.getPref("login");
        if(!TextUtils.isEmpty(login)) {
            mTask = new RetrieveTweetsAsyncTask(this);
            mTask.execute(login);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.tweet_fragment_layout, container, false);
        mListView = (ListView) rootView.findViewById(R.id.tweetsListView);
        final ProgressBar progressBar = new ProgressBar(getActivity());
        progressBar.setLayoutParams(new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT, Gravity.CENTER));
        progressBar.setIndeterminate(true);
        ViewGroup root = (ViewGroup) rootView.findViewById(R.id.tweet_fragment);
        root.addView(progressBar);
        return rootView;
    }

    @Override
    public void onTweetRetrieved(List<Tweet> tweets) {
        final ArrayAdapter<Tweet> adapter = new ArrayAdapter<Tweet>(getActivity(), android.R.layout.simple_list_item_1, tweets);
        mListView.setAdapter(adapter);
    }

    public interface TweetsInterfaceListener {
        public void tweetsInterfaceListener();
    }

    @Override
    public void onAttach(Activity activity) throws ClassCastException {
        super.onAttach(activity);
        try {
            this.mListener = (TweetsInterfaceListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + "must implement TweetsInterfaceListener");
        }
    }
}
