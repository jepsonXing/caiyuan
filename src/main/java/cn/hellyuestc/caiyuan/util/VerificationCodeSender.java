package cn.hellyuestc.caiyuan.util;

import java.util.Date;
import java.util.Random;

import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

public class VerificationCodeSender {

	public static String sendVerificationCode(String phone) {
		String url = MyConstant.URL;
		String appkey = MyConstant.APPKEY;
		String secret = MyConstant.SECRET;
		
		String code = "";
	    Random r = new Random(new Date().getTime());
	    for(int i = 0; i < 6; i++){
	        code = code+r.nextInt(10);
	    }
	    
	    TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
	    AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
	    req.setExtend("");
	    req.setSmsType(MyConstant.SMS_TYPE_SMS);
	    req.setSmsFreeSignName(MyConstant.SMS_FREE_SIGN_NAME);
	    String json="{\"number\":\""+code+"\"}";
	    req.setSmsParamString(json);
	    req.setRecNum(phone);
	    req.setSmsTemplateCode(MyConstant.SMS_TEMPLATE_CODE);
	    
	    AlibabaAliqinFcSmsNumSendResponse rsp = null;
        try {
            rsp = client.execute(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return code;
	}
	
}
