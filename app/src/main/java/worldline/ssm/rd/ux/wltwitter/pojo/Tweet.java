package worldline.ssm.rd.ux.wltwitter.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.google.gson.annotations.SerializedName;

public class Tweet implements Parcelable{

	private int mData;

	@SerializedName("created_at")
	public String dateCreated;

	@SerializedName("id")
	public String id;

	@SerializedName("text")
	public String text;

	@SerializedName("in_reply_to_status_id")
	public String inReplyToStatusId;

	@SerializedName("in_reply_to_user_id")
	public String inReplyToUserId;

	@SerializedName("in_reply_to_screen_name")
	public String inReplyToScreenName;

	@SerializedName("user")
	public TwitterUser user;

	@Override
	public String toString() {
		return text;
	}

	public Tweet() {
	}

	public long getDateCreatedTimestamp(){
		final SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss ZZZZZ yyyy", Locale.ENGLISH);
		dateFormat.setLenient(false);
		try {
			final Date created = dateFormat.parse(dateCreated);
			return created.getTime();

		} catch (Exception e) {
			return 0;
		}
	}



	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(mData);
	}

	public static final Parcelable.Creator<Tweet> CREATOR = new Parcelable.Creator<Tweet>() {
		public Tweet createFromParcel(Parcel in) {
			return new Tweet(in);
		}
		public Tweet[] newArray(int size) {
			return new Tweet[size];
		}
	};

	private Tweet(Parcel in) {
		mData = in.readInt();
	}
}