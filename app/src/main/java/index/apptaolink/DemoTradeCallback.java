package index.apptaolink;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.baichuan.android.trade.callback.AlibcTradeCallback;
import com.alibaba.baichuan.trade.biz.context.AlibcTradeResult;

/**
 * Created by fenghaoxiu on 16/8/23.
 */
public class DemoTradeCallback implements AlibcTradeCallback {


    @Override
    public void onTradeSuccess(AlibcTradeResult alibcTradeResult) {
        Log.i("成功信息",alibcTradeResult.resultType.toString());
    }

    @Override
    public void onFailure(int errCode, String errMsg) {
        Log.i("失败信息",errMsg);
    }
}
