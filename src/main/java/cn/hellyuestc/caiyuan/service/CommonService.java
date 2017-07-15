package cn.hellyuestc.caiyuan.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public interface CommonService {
	
	/*
	 * 检查用户是否已登录
	 */
	public Map<String, Object> getUserIdFromRedis(HttpServletRequest request);
	
	/*
	 * 保存文件到本地
	 */
	public void putImageToLocal(MultipartFile image, String localPath, String LocalImageName);
	
	/*
	 * 检查时间和请求数量是否正确
	 */
	public Map<String, Object> validateTimeAndCount(String time, int count);
	
}
