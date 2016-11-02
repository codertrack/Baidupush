package session.baidupush;

import android.content.Context;

import com.baidu.android.pushservice.PushMessageReceiver;

import java.util.List;

/**
 * Created by wukai on 16/11/2.
 */

public class PushReceiver extends PushMessageReceiver {
	@Override
	public void onBind(Context context, int i, String s, String s1, String s2, String s3) {

	}

	@Override
	public void onUnbind(Context context, int i, String s) {

	}

	@Override
	public void onSetTags(Context context, int i, List<String> list, List<String> list1, String s) {

	}

	@Override
	public void onDelTags(Context context, int i, List<String> list, List<String> list1, String s) {

	}

	@Override
	public void onListTags(Context context, int i, List<String> list, String s) {

	}

	@Override
	public void onMessage(Context context, String s, String s1) {

		System.out.println("s="+s);
	}

	@Override
	public void onNotificationClicked(Context context, String s, String s1, String s2) {

	}

	@Override
	public void onNotificationArrived(Context context, String s, String s1, String s2) {

	}
}
