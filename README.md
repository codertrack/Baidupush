# Baidupush

1.打开push.baidu.com
2.下载Android 推送的sdk
3.看下文档中心如何继承到自己的应用
4.需要创建应用 填写应用名称
5.需要在应用配置中填写当前应用的包名,也就是app模块下的 build.gradle文件中的Appid的值

6.需要复制sdk中的相关文件
1.需要把push_service.jar复制到模块下的libs目录
2.需要把所有的sdk中的libs下的/so库文件,复制到app/src/main/jniLibs目录 如果这个目录不存在,则新建一个目录

3.复制图片资源文件到drawable-hdpi
4.复制布局文件到 layout目录
5.复制colors文件中的内容到values/colors文件中

6.复制权限内容
<!-- Push service 运行需要的权限 -->
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.READ_PHONE_STATE" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
<uses-permission android:name="android.permission.RECEIVE_BOOT_COMPLETED" />
<uses-permission android:name="android.permission.WRITE_SETTINGS" />
<uses-permission android:name="android.permission.VIBRATE" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.DISABLE_KEYGUARD" />
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
<uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
<!-- 富媒体需要声明的权限 -->
<uses-permission android:name="android.permission.ACCESS_DOWNLOAD_MANAGER"/>
<uses-permission android:name="android.permission.DOWNLOAD_WITHOUT_NOTIFICATION" />
<uses-permission android:name="android.permission.EXPAND_STATUS_BAR" />

<!-- push service start -->
<!-- 用于接收系统消息以保证PushService正常运行 -->
<receiver android:name="com.baidu.android.pushservice.PushServiceReceiver"
    android:process=":bdservice_v1" >
    <intent-filter>
        <action android:name="android.intent.action.BOOT_COMPLETED" />
        <action android:name="android.net.conn.CONNECTIVITY_CHANGE" />
        <action android:name="com.baidu.android.pushservice.action.notification.SHOW" />
        <action android:name="com.baidu.android.pushservice.action.media.CLICK" />
        <!-- 以下四项为可选的action声明，可大大提高service存活率和消息到达速度 -->
        <action android:name="android.intent.action.MEDIA_MOUNTED" />
        <action android:name="android.intent.action.USER_PRESENT" />
        <action android:name="android.intent.action.ACTION_POWER_CONNECTED" />
        <action android:name="android.intent.action.ACTION_POWER_DISCONNECTED" />
    </intent-filter>


</receiver>
<!-- Push服务接收客户端发送的各种请求-->
<receiver android:name="com.baidu.android.pushservice.RegistrationReceiver"
    android:process=":bdservice_v1" >
    <intent-filter>
        <action android:name="com.baidu.android.pushservice.action.METHOD" />
        <action android:name="com.baidu.android.pushservice.action.BIND_SYNC" />
    </intent-filter>
    <intent-filter>
        <action android:name="android.intent.action.PACKAGE_REMOVED" />
        <data android:scheme="package" />
    </intent-filter>
</receiver>
<service android:name="com.baidu.android.pushservice.PushService" android:exported="true"
    android:process=":bdservice_v1" >
    <intent-filter >
            <action android:name="com.baidu.android.pushservice.action.PUSH_SERVICE" />
    </intent-filter>
</service>
<!-- 4.4版本新增的CommandService声明，提升小米和魅族手机上的实际推送到达率 -->
<service android:name="com.baidu.android.pushservice.CommandService"
    android:exported="true" />
<!-- push结束 -->



7.调用初始化代码初始推送服务

PushManager.startWork(getApplicationContext(),
				PushConstants.LOGIN_TYPE_API_KEY,"ekIFdwpVdeCl85tONRKkO7kj");

第三个参数市apikey 在应用的配置界面找到

8.自定义一个pushReceiver继承PushMEsageReceiver 并注册

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


9注册自定义广播接收器

  <receiver android:name=".PushReceiver">
            <intent-filter>
                <!-- 接收push消息 -->
                <action android:name="com.baidu.android.pushservice.action.MESSAGE" />
                <!-- 接收bind、setTags等method的返回结果-->
                <action android:name="com.baidu.android.pushservice.action.RECEIVE" />
                <!-- 接收通知点击事件，和通知自定义内容 -->
                <action android:name="com.baidu.android.pushservice.action.notification.CLICK" />
            </intent-filter>

        </receiver>



10,发送通知测试


