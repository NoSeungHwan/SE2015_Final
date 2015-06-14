package kr.ac.mju;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class GesulController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private LoginService service;
	
	@RequestMapping(value = "/GesulController/createAccount.do", method = RequestMethod.POST)
	public String createAccount(HttpServletRequest request) throws UnsupportedEncodingException, SQLException{
		request.setCharacterEncoding("utf-8");
		service.createAccount();
		
		return "redirect:/";
	}
	@RequestMapping(value = "/GeseulController/selectData.do", method = RequestMethod.POST)
	public String selectData(HttpServletRequest request) throws UnsupportedEncodingException, SQLException{
		request.setCharacterEncoding("utf-8");
		service.selectData();
		
		return "redirect:/";
	}

}
