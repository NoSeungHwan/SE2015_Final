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
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private LoginService service;
	
	
	@RequestMapping(value = "/loginController/createAccount.do", method = RequestMethod.POST)
	public String createAccount(HttpServletRequest request) throws UnsupportedEncodingException, SQLException{
		request.setCharacterEncoding("utf-8");
		service.createAccount();
		
		return "redirect:/";
	}
	
	@RequestMapping(value = "/loginController/createDB.do", method = RequestMethod.POST)
	public String createDB(HttpServletRequest request) throws UnsupportedEncodingException, SQLException{
		request.setCharacterEncoding("utf-8");
		service.createDB();
		
		return "redirect:/";
	}
	
	@RequestMapping(value = "/loginController/createTable.do", method = RequestMethod.POST)
	public String createTable(HttpServletRequest request) throws UnsupportedEncodingException, SQLException{
		request.setCharacterEncoding("utf-8");
		service.createTable();
		
		return "redirect:/";
	}
	

	
	@RequestMapping(value = "/loginController/selectData.do", method = RequestMethod.POST)
	public String selectData(HttpServletRequest request) throws UnsupportedEncodingException, SQLException{
		request.setCharacterEncoding("utf-8");
		service.selectData();
		
		return "redirect:/";
	}
	
	
	
	@RequestMapping(value = "/loginController/login.do", method = RequestMethod.POST)
	public String login(HttpServletRequest request) throws UnsupportedEncodingException{
		request.setCharacterEncoding("utf-8");
		String userID = request.getParameter("userID");
		String userPassword = request.getParameter("userPassword");
		
		//LoginInfo 
		//에러, 정상처리
		User user = null;
		try {
			user = service.login(userID, userPassword);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		logger.info("로그인 요청:"+userID);
		if(user == null){
			return "redirect:/";
		}else if(user.category == "학생"){
			request.getSession().setAttribute("userSession", user);
			return "Student";
		}else{
			request.getSession().setAttribute("userSession", user);
			return "Professor";
		}
	}
	
	@RequestMapping(value = "/loginController/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request){
		request.getSession().removeAttribute("userSession");
		return "redirect:/";
	}
	
}












