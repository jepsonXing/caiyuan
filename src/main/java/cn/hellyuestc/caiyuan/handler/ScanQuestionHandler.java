package cn.hellyuestc.caiyuan.handler;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.hellyuestc.caiyuan.entity.Question;
import cn.hellyuestc.caiyuan.service.ScanQuestionService;

@Controller
public class ScanQuestionHandler {
	
	@Autowired
	private ScanQuestionService service;

	@RequestMapping("/scanQuestionNew")
	public String scanQuestionNew(HttpSession session) {
		List<Question> questionListOrderByTime = null;
		try {
			questionListOrderByTime = (List<Question>) session.getAttribute("questionListOrderByTime");
		} catch(NullPointerException e) {
			session.setAttribute("questionListOrderByTime", questionListOrderByTime);
		}
		List<Question> newQuestionList = null;
		newQuestionList = service.queryNewQuestion();
		if (null != newQuestionList) {
			questionListOrderByTime.addAll(newQuestionList);
			session.setAttribute("questionListOrderByTime", questionListOrderByTime);
		}
		return "redirect:/";
	}
	
}
