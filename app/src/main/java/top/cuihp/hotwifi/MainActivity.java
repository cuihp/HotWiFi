package top.cuihp.hotwifi;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import top.cuihp.wifi.WifiAPManager;

public class MainActivity extends AppCompatActivity {
private TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text=(TextView)findViewById(R.id.text);

       //注册handler
        WifiAPManager.getInstance(this).regitsterHandler(mHandler);
        //开启wifi热点
        WifiAPManager.getInstance(this).turnOnWifiAp("ssid", "123123123", WifiAPManager.WifiSecurityType.WIFICIPHER_WPA2);


    }

    //接收message，做处理
    private Handler mHandler = new Handler(){
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case WifiAPManager.MESSAGE_AP_STATE_ENABLED:
                    text.setText("wifi热点开启成功" );
                    break;
                case WifiAPManager.MESSAGE_AP_STATE_FAILED:
                    text.setText("wifi热点关闭");
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        WifiAPManager.getInstance(this).unregitsterHandler();
    }
}
