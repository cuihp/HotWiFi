# HotWiFi
  android 便携式热点的开启与关闭

# How to


* Step 1. Add it in your root build.gradle at the end of repositories:
```
allprojects {
	repositories {
          ...
	  maven { url 'https://jitpack.io' }
	}
}
```
* Step 2. Add the dependency
```
dependencies {
	compile 'com.github.cuihp:HotWiFi:-SNAPSHOT'
}
```

* 初始化

```
        //注册handler
        WifiAPManager.getInstance(this).regitsterHandler(mHandler);
        //开启wifi热点
        WifiAPManager.getInstance(this).turnOnWifiAp("ssid", "123123123", WifiAPManager.WifiSecurityType.WIFICIPHER_WPA2);
```

* 监听是否开启成功

```
//接收message，做处理
    private Handler mHandler = new Handler(){
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case WifiAPManager.MESSAGE_AP_STATE_ENABLED:
                    Log.i("WifiAPManager",""wifi热点开启成功" );
                    break;
                case WifiAPManager.MESSAGE_AP_STATE_FAILED:
                    Log.i("WifiAPManager","wifi热点关闭");
                    break;
                default:
                    break;
            }
        }
    };
```

* 释放Handler

```
 @Override
    protected void onDestroy() {
        super.onDestroy();
        WifiAPManager.getInstance(this).unregitsterHandler();
    }
 ```
