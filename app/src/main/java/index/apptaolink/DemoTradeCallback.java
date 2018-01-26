package index.apptaolink;

import com.alibaba.baichuan.android.trade.callback.AlibcTradeCallback;
import com.alibaba.baichuan.android.trade.model.TradeResult;

/**
 * Created by fenghaoxiu on 16/8/23.
 */
public class DemoTradeCallback implements AlibcTradeCallback {


    @Override
    public void onFailure(int errCode, String errMsg) {
    }

    @Override
    public void onTradeSuccess(TradeResult tradeResult) {

    }
}
