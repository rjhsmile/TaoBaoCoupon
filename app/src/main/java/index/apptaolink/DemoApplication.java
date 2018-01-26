package index.apptaolink;

import android.app.Application;
import android.widget.Toast;

import com.alibaba.baichuan.android.trade.AlibcTradeSDK;
import com.alibaba.baichuan.android.trade.callback.AlibcTradeInitCallback;

/**
 * Created by sospartan on 1/28/16.
 */
public class DemoApplication extends Application {

  public static final String TAG = "DemoApplication";
  public static DemoApplication mActivity;

  @Override
  public void onCreate() {
    super.onCreate();

     mActivity = this;
    /**
     * 初始化
     */
    AlibcTradeSDK.asyncInit(this, new AlibcTradeInitCallback() {
      @Override
      public void onSuccess() {
        //初始化成功，设置相关的全局配置参数
        Toast.makeText(mActivity, "/初始化成功 ",
                Toast.LENGTH_LONG).show();
      }

      @Override
      public void onFailure(int code, String msg) {
        //初始化失败，可以根据code和msg判断失败原因，详情参见错误说明
        Toast.makeText(mActivity, "初始化失败 ",
                Toast.LENGTH_LONG).show();
      }
    });


  }
}
