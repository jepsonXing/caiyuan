package cn.hellyuestc.caiyuan.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

import cn.hellyuestc.caiyuan.util.MyConstant;

@Controller
public class TestController {

	@RequestMapping("/testUploadImage")
	@ResponseBody
	public Map<String, String> test(MultipartFile userAvatarImage) {
		Map<String, String> map = new HashMap<>();
		
		//构造一个带指定Zone对象的配置类
		Configuration cfg = new Configuration(Zone.zone2());
		//...其他参数参考类注释

		UploadManager uploadManager = new UploadManager(cfg);
		//...生成上传凭证，然后准备上传
		String accessKey = MyConstant.QINIU_ACCESS_KEY;
		String secretKey = MyConstant.QINIU_SECRET_KEY;
		String bucket = MyConstant.QINIU_BUCKET_NAME;

		//默认不指定key的情况下，以文件内容的hash值作为文件名
		String key = "test.png";

		try {
		    byte[] uploadBytes;
			uploadBytes = userAvatarImage.getBytes();
		    Auth auth = Auth.create(accessKey, secretKey);
		    String upToken = auth.uploadToken(bucket);

		    try {
		        Response response = uploadManager.put(uploadBytes, key, upToken);
		        //解析上传成功的结果
		        DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
		        System.out.println(putRet.key);
		        System.out.println(putRet.hash);
		    } catch (QiniuException ex) {
		        Response r = ex.response;
		        System.err.println(r.toString());
		        try {
		            System.err.println(r.bodyString());
		        } catch (QiniuException ex2) {
		            //ignore
		        }
		    }
		} catch (UnsupportedEncodingException ex) {
		    //ignore
		} catch (IOException e) {
			e.printStackTrace();
		}

		map.put("ok", "test");
		return map;
	}
	
}
