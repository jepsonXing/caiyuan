package cn.hellyuestc.caiyuan.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.hellyuestc.caiyuan.service.CommonService;
import cn.hellyuestc.caiyuan.service.UserService;
import cn.hellyuestc.caiyuan.util.MyUtil;
import cn.hellyuestc.caiyuan.util.Response;
import cn.hellyuestc.caiyuan.util.Status;

@Controller
public class UserController {

	private Map<String, String> map;
	@Autowired
	private UserService userService;
	@Autowired
	private CommonService commonService;

	/*
	 * 获取手机验证码
	 */
	@RequestMapping(value="/verificationcodes", params={"phone"}, method=RequestMethod.GET)
	public @ResponseBody Response getVerificationCode(String phone) {
		Map<String, Object> map = commonService.getVerificationCode(phone);
		if (map.get("ok") != null) {
			return new Response(new Status(200, "验证码已发送"));
		} else {
			return new Response(new Status(400, "error"), map);
		}
	}
	
	/*
	 * 手机注册
	 */
	@RequestMapping(value="/users", params={"type=phone"}, method=RequestMethod.POST)
	public @ResponseBody Response  registeByPhone(String phone, String verificationCode, String password, String confirmPassword, HttpServletResponse response) {
		Map<String, Object> map = userService.addUserByPhone(phone, verificationCode, password, confirmPassword, response);
		if (map.get("error") != null) {
			return new Response(new Status(400, "error"), map);
		} else {
			return new Response(new Status(201, "注册成功"), map);
		}
	}
	
	/*
	 * 邮箱注册
	 */
	@RequestMapping(value="/users", params={"type=email"}, method=RequestMethod.POST)
	@ResponseBody
	public Response registeByEmail(String email, String password, String confirmPassword) {
		Map<String, String> map = userService.addUserByEmail(email, password, confirmPassword);

		if (map.get("ok") != null) {
			return new Response(new Status(201, "系统已向您的邮箱发送了一份邮件，验证后即可登录"));
		} else {
			return new Response(new Status(400, "error"), map);
		}
	}

	/*
	 * 激活邮箱失败
	 */
	@RequestMapping("/acvateEmailError")
	@ResponseBody
	public Response acvateEmailError() {
		return new Response(new Status(400, "error"), map);
	}

	/*
	 * 激活邮箱
	 */
	@RequestMapping(value = "/users/{email}/{activationCode}", method = RequestMethod.GET)
	public String activateEmail(@PathVariable String email, @PathVariable String activationCode) {
		Map<String, String> map = userService.activateEmail(email, activationCode);

		if (map.get("ok") != null) {
			return "activateEmailSuccess";
		} else {
			this.map = map;
			return "redirect:/acvateEmailError";
		}
	}

	/*
	 * 更换用户头像
	 */
	@RequestMapping(value = "/users/{id}", params = { "field=avatarUrl" })
	@ResponseBody
	public Response updateAvatarUrl(@PathVariable long id, MultipartFile userAvatarImage, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<>();
		long userId = 0;

		map = commonService.getUserIdFromRedis(request);
		
		// 未登录
		if (map.get("userId") == null) {
			return new Response(new Status(400, "error"), map);
		}

		userId = (long) map.get("userId");

		// 请求的id与userId不相对应
		if (id != userId) {
			map.clear();
			map.put("id-error", "请求的id与登录用户id不一致");
			return new Response(new Status(400, "error"), map);
		}

		if (!MyUtil.isLegalImage(userAvatarImage)) {
			map.clear();
			map.put("image-error", "请上传后缀为.bmp、.jpg、.jpeg、.png、.gif的图片");
			return new Response(new Status(400, "error"), map);
		}

		// 包含原始文件名的字符串
		String originalImageName = userAvatarImage.getOriginalFilename();
		// 提取文件拓展名
		String imageNameExtension = originalImageName.substring(originalImageName.indexOf("."), originalImageName.length());
		String imageName = "userAvatars/" + id + imageNameExtension;
		
		commonService.putImageToLocal(userAvatarImage, request.getServletContext().getRealPath("/"), imageName);
		
		String avatarUrl = imageName;
		userService.updateAvatarUrl(userId, avatarUrl);
		map.clear();
		map.put("avatarUrl", avatarUrl);
		return new Response(new Status(201, "更换用户头像成功"), map);
	}
	
	/*
	 * 手机验证重置密码
	 */
	@RequestMapping(value="/resetpassword", params = { "type=phone" })
	public @ResponseBody Response resetPasswordByPhone(String phone, String verificationCode, String password, String confirmPassword) {
		Map<String, Object> map = userService.resetPasswordByPhone(phone, verificationCode, password, confirmPassword);
		if (map.get("error") != null) {
			return new Response(new Status(400, "error"), map);
		} else {
			return new Response(new Status(201, "重置密码成功"));
		}
	}

}