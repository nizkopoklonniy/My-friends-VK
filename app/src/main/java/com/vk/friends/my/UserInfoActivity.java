package com.vk.friends.my;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.vk.friends.my.utils.Settings;

/**
 * User information activity. The activity shows avatar, full user
 * name, birthday and city.
 */
public class UserInfoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_info);

		ImageView userPhotoImageView = (ImageView) findViewById(R.id.user_photo);
		TextView userFullNameTextView = (TextView) findViewById(R.id.full_name_descr);
		TextView userBDateTextView = (TextView) findViewById(R.id.bdate);
        TextView cityTextView = (TextView) findViewById(R.id.city);

		Intent intent = getIntent();
		String photo = intent.getStringExtra(Settings.KEY_USER_PHOTO);
		//new DownloadImageTask(userPhotoImageView).execute();

		ImageLoader imageLoader = ImageLoader.getInstance();
		imageLoader.displayImage(photo,userPhotoImageView);

		String userNameData = intent.getStringExtra(Settings.KEY_USER_NAME);
		String bDateData = intent.getStringExtra(Settings.KEY_USER_BDATE);

		userFullNameTextView.setText(userNameData);
		userBDateTextView.setText(bDateData);

        String city = intent.getStringExtra(Settings.KEY_USER_CITY);
        if (city != null){
            cityTextView.setText(city);
        }
	}
}