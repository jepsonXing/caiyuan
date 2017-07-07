package cn.hellyuestc.caiyuan.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class TestController {

	@RequestMapping("/testUploadImage")
	@ResponseBody
	public Map<String, String> test(MultipartFile userAvatarImage, HttpServletRequest request) {
		Map<String, String> map = new HashMap<>();
		
		String path = request.getServletContext().getRealPath("/userAvatars");
		File userAvatar = new File(path, userAvatarImage.getOriginalFilename());
		try {
			userAvatarImage.transferTo(userAvatar);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		map.put("ok", "test");
		return map;
	}

}