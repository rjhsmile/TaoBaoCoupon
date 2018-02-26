package index.apptaolink;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.ali.auth.third.login.callback.LogoutCallback;
import com.alibaba.baichuan.android.trade.AlibcTrade;
import com.alibaba.baichuan.android.trade.AlibcTradeSDK;
import com.alibaba.baichuan.android.trade.model.AlibcShowParams;
import com.alibaba.baichuan.android.trade.model.OpenType;
import com.alibaba.baichuan.android.trade.page.AlibcBasePage;
import com.alibaba.baichuan.android.trade.page.AlibcDetailPage;
import com.alibaba.baichuan.android.trade.page.AlibcMyOrdersPage;
import com.alibaba.baichuan.trade.biz.core.taoke.AlibcTaokeParams;
import com.alibaba.baichuan.trade.biz.login.AlibcLogin;
import com.alibaba.baichuan.trade.biz.login.AlibcLoginCallback;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "DemoApplication";
    private String taokeUrl;
    private WebView webView;
    private Map<String, String> exParams = new HashMap<>();
    private AlibcShowParams alibcShowParams;
    TextView mtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = (WebView) findViewById(R.id.webView);

        mtitle = (TextView) findViewById(R.id.title);
        //商品id
        taokeUrl="523166400801";
        //商品链接
        //taokeUrl = "https://ai.m.taobao.com/search.html?q=%E7%94%B7%E8%A3%85&pid=mm_48512871_8544703_28814507";
    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.card:
                //淘宝打开连接
                alibcShowParams = new AlibcShowParams(OpenType.Native, false);
                alibcShowParams.setClientType("taobao_oscheme");
                initdata();
                break;
            case R.id.order:
                alibcShowParams = new AlibcShowParams(OpenType.Native, false);
                alibcShowParams.setClientType("taobao_scheme");
                exParams.put("isv_code", "appisvcode");
                //订单
                AlibcBasePage alibcBasePage = new AlibcMyOrdersPage(0, true);
                AlibcTrade.show(this, alibcBasePage, alibcShowParams, null, exParams, new DemoTradeCallback());
                break;
            case R.id.tmallwb:
                //天猫打开链接
                alibcShowParams = new AlibcShowParams(OpenType.Native, false);
                alibcShowParams.setClientType("tmall_scheme");
                initdata();
                break;
            case R.id.taowb:
                //淘宝打开链接
                alibcShowParams = new AlibcShowParams(OpenType.Native, false);
                alibcShowParams.setClientType("taobao_scheme");
                initdata();
                break;
            case R.id.logout:
                logout();
                break;
            case R.id.login:
                Login();
                break;
            case R.id.wb:
                alibcShowParams = new AlibcShowParams(OpenType.H5, false);
                initdata();
                break;
        }
    }

    /**
     * 退出登录
     */
    public void logout() {
        AlibcLogin alibcLogin = AlibcLogin.getInstance();
        alibcLogin.logout( new AlibcLoginCallback() {
            @Override
            public void onSuccess(int i) {
                Toast.makeText(MainActivity.this, "退出成功 ",
                        Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(int i, String s) {
                Toast.makeText(MainActivity.this, "退出失败 ",
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    private void Login() {

        AlibcLogin alibcLogin = AlibcLogin.getInstance();
        alibcLogin.showLogin( new AlibcLoginCallback() {

            @Override
            public void onSuccess(int i) {
                Toast.makeText(MainActivity.this, "登录成功 ",
                        Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(int i, String s) {
                Toast.makeText(MainActivity.this, "登录失败 ",
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    private void initdata() {
        WebChromeClient webChromeClient = new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                mtitle.setText(view.getTitle());
            }
        };

        /**
         * 打开电商组件,支持使用外部webview
         *
         * @param activity             必填
         * @param webView              外部 webView
         * @param webViewClient        webview的webViewClient
         * @param webChromeClient      webChromeClient客户端
         * @param tradePage            页面类型,必填，不可为null，详情见下面tradePage类型介绍
         * @param showParams           show参数
         * @param taokeParams          淘客参数
         * @param trackParam           yhhpass参数
         * @param tradeProcessCallback 交易流程的回调，必填，不允许为null；
         * @return 0标识跳转到手淘打开了, 1标识用h5打开,-1标识出错
         */

        AlibcTaokeParams alibcTaokeParams = new AlibcTaokeParams(); // 若非淘客taokeParams设置为null即可
        alibcTaokeParams.pid = "mm_48512871_11860020_43616333";
        alibcTaokeParams.subPid = "mm_48512871_11860020_43616333";
        alibcTaokeParams.adzoneid = "43616333";
        alibcTaokeParams.extraParams = new HashMap<>();
        alibcTaokeParams.extraParams.put("taokeAppkey", "23272848");//一定要是淘宝联盟后台的appKey

        //商品ID打开页面
        AlibcBasePage alibcBasePage = new AlibcDetailPage(taokeUrl);
        //商品链接打开页面
//        AlibcPage alibcBasePage = new AlibcPage(taokeUrl);
        exParams.put("isv_code", "appisvcode");
        AlibcTrade.show(this, webView, null, webChromeClient, alibcBasePage, alibcShowParams, alibcTaokeParams, exParams, new DemoTradeCallback());
    }


    @Override
    protected void onDestroy() {
        //调用了AlibcTrade.show方法的Activity都需要调用AlibcTradeSDK.destory()
        AlibcTradeSDK.destory();
        super.onDestroy();
    }
}
