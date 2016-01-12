package worldline.ssm.rd.ux.wltwitter;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import worldline.ssm.rd.ux.wltwitter.async.RetrieveTwitterPic;
import worldline.ssm.rd.ux.wltwitter.interfaces.TweetListener;
import worldline.ssm.rd.ux.wltwitter.pojo.Tweet;
import worldline.ssm.rd.ux.wltwitter.utils.ImageMemoryCache;

/**
 * Created by Matthieu on 17/12/2015.
 */
public class TweetsAdapter extends BaseAdapter{
    List<Tweet> mTweets;
    LayoutInflater mInflater;
    TweetListener mtweetsListener;
    ImageMemoryCache mMemoryCache;


    public TweetsAdapter(List<Tweet> mTweets, AdapterView.OnItemClickListener listener, TweetListener tweetsListener) {
        this.mTweets = mTweets;
        this.mInflater = LayoutInflater.from(WLTwitterApplication.getContext());
        this.mtweetsListener = tweetsListener;
        this.mMemoryCache = new ImageMemoryCache(5000);
    }

    @Override
    public int getCount() {
        return null != mTweets ? mTweets.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return null != mTweets ? mTweets.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(null == convertView) {
            convertView = mInflater.inflate(R.layout.tweet_adapter_layout, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final Tweet tweet = (Tweet) getItem(position);

        final Bitmap profilePic = mMemoryCache.getBitmapFromMemCache(tweet.user.profileImageUrl);
        if(null == profilePic) {
            new RetrieveTwitterPic(holder.image, mMemoryCache).execute(tweet.user.profileImageUrl);
        } else {
            holder.image.setImageBitmap(profilePic);
        }



        holder.userName.setText(tweet.user.name);

        holder.aliasName.setText(tweet.user.screenName);

        holder.textTweet.setText(tweet.text);


        holder.rtButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mtweetsListener.onRetweet(tweet);
            }
        });
        return convertView;
    }
    private class ViewHolder {
        public ImageView image;
        public TextView userName;
        public TextView aliasName;
        public TextView textTweet;
        public Button rtButton;

        public ViewHolder(View view) {
            this.image = (ImageView) view.findViewById(R.id.adapter_image);
            this.userName = (TextView) view.findViewById(R.id.adapter_text_left);
            this.aliasName = (TextView) view.findViewById(R.id.adapter_text_right);
            this.textTweet = (TextView) view.findViewById(R.id.adapter_text_bottom);
            this.rtButton = (Button) view.findViewById(R.id.adapter_rt_button);
        }
    }
}
