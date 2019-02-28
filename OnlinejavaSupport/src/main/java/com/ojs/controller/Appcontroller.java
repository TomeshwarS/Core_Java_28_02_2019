package com.ojs.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ojs.constants.OJSConstants;
import com.ojs.entity.Option;
import com.ojs.entity.Question;
import com.ojs.model.QuestionAndOptionDto;
import com.ojs.model.User;
import com.ojs.mysql.connection.MySqlConnection;

/*import com.ojs.entity.Users;
*/
@Controller
public class Appcontroller {

	@Autowired
	MySqlConnection mySqlConnection;
// Logger logger=Logger.getLogger(Appcontroller.class);  
	Long nextCount = 1L;

	@RequestMapping("/next")
	public ModelAndView hello(@RequestParam("optionId") String optionId) {
		nextCount++;

		Boolean isSaved = saveSelectedEvent(optionId);

		System.out.println("Output >> " + optionId);

		System.out.println("nextCount" + nextCount);

		Question question = this.getAllTestQuestionsWithANsDB(nextCount);
		QuestionAndOptionDto outputToRenderOnUi = new QuestionAndOptionDto();
		outputToRenderOnUi.setQueName(question.getName());
		outputToRenderOnUi.setQueSerialNo(question.getQue_serial_no());
		List<Option> opnlist = question.getListOptions();
		Option option1 = opnlist.get(0);
		outputToRenderOnUi.setOptionName1(option1.getName());
		Option option2 = opnlist.get(1);
		outputToRenderOnUi.setOptionName2(option2.getName());
		Option option3 = opnlist.get(2);
		outputToRenderOnUi.setOptionName3(option3.getName());
		Option option4 = opnlist.get(3);
		outputToRenderOnUi.setOptionName4(option4.getName());

		outputToRenderOnUi.setOptionId1(option1.getOptionId());
		outputToRenderOnUi.setOptionId2(option2.getOptionId());
		outputToRenderOnUi.setOptionId3(option3.getOptionId());
		outputToRenderOnUi.setOptionId4(option4.getOptionId());
		return new ModelAndView("hello", "question_opn", outputToRenderOnUi);
	}

	public Boolean saveSelectedEvent(String name) {
		return false;
	}

	// This is home Page Method
	@RequestMapping("/start_test")
	public ModelAndView getNextActionData() {
		nextCount = 0L;
		Question question = this.getAllTestQuestionsWithANsDB();
		QuestionAndOptionDto outputToRenderOnUi = new QuestionAndOptionDto();
		outputToRenderOnUi.setQueName(question.getName());
		outputToRenderOnUi.setQueSerialNo(question.getQue_serial_no());
		List<Option> opnlist = question.getListOptions();
		Option option1 = opnlist.get(0);
		outputToRenderOnUi.setOptionName1(option1.getName());
		Option option2 = opnlist.get(1);
		outputToRenderOnUi.setOptionName2(option2.getName());
		Option option3 = opnlist.get(2);
		outputToRenderOnUi.setOptionName3(option3.getName());
		Option option4 = opnlist.get(3);
		outputToRenderOnUi.setOptionName4(option4.getName());

		outputToRenderOnUi.setOptionId1(option1.getOptionId());
		outputToRenderOnUi.setOptionId2(option2.getOptionId());
		outputToRenderOnUi.setOptionId3(option3.getOptionId());
		outputToRenderOnUi.setOptionId4(option4.getOptionId());

		return new ModelAndView("hello", "question_opn", outputToRenderOnUi);
	}

	@SuppressWarnings("all")
	public Question getAllTestQuestionsWithANsDB() {
		Question question = null;
		List<Option> opnlist = new ArrayList<>();

		try {
			Connection con = mySqlConnection.getMysqlConnection();
			PreparedStatement ps = con.prepareStatement(
					"SELECT q.name  as q_name, q.que_serial_no ,q.marks, q.answer_id,o.name as opn_name , o.option_id FROM questions q inner join options o on q.que_serial_no=o.q_serrial_id where q.que_serial_no=?; ");

			ps.setLong(1, OJSConstants.INITIALRECORD);
			ResultSet rs = ps.executeQuery();

			int excecutionCout = 0;
			int initialCount = -1;
			while (rs.next()) {

				String name = rs.getString("q_name");
				Long marks = rs.getLong("marks");
				Long answerId = rs.getLong("answer_id");
				Long queSerialNo = rs.getLong("que_serial_no");
				Long optionId = rs.getLong("option_id");
				String opnName = rs.getString("opn_name");
				initialCount++;

				if (initialCount == 0) {
					question = new Question();
					question.setAnswerId(answerId);
					question.setQue_serial_no(queSerialNo);
					question.setMarks(marks);
					question.setName(name);
				} else if (initialCount == 4) {
					initialCount = -1;
				}
				Option option = new Option();
				option.setQ_serrial_id(queSerialNo);
				option.setName(opnName);
				option.setOptionId(optionId);
				opnlist.add(option);
				question.setListOptions(opnlist);
				excecutionCout++;

				if (excecutionCout == 4) {

					excecutionCout = 0;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();

		}

		return question;
	}

	// After Click on localhost:8080 this method will get Called
	@RequestMapping("/")
	public ModelAndView index() {

		System.out.println("App Stared !!!!!");

		System.out.println("Redirecting to welcome page ");

		return new ModelAndView("welcome");

	}

	@SuppressWarnings("unused")
	@RequestMapping("/login")
	public ModelAndView login(@RequestParam("UserName") String UserName, @RequestParam("password") String Password,
			HttpServletRequest request) {
		ModelAndView mav = null;
		System.out.println("UserNameme " + UserName);
		System.out.println("Password " + Password);

		User user = getUserDetailsBuUserNameAndPassWord(UserName, Password);

		if (null != user) {

			if (user.getName().equals(OJSConstants.ADMIN_NAME) && user.getPass().equals(OJSConstants.ADMIN_PASS)) {
				HttpSession session = request.getSession();
				session.setAttribute("userName", user.getName());
				session.setAttribute("pass", user.getPass());
				session.setAttribute("loggedInUserId", user.getId());
				enableLoggedInStatus(user.getId());
				mav = new ModelAndView("admin", "message", "WelCome Admin !!!");

			} else {
				HttpSession session = request.getSession();
				session.setAttribute("userName", user.getName());
				session.setAttribute("pass", user.getPass());
				session.setAttribute("loggedInUserId", user.getId());
				mav = new ModelAndView("user", "message", "Welcome User !!");
				enableLoggedInStatus(user.getId());

			}

		} else {
			mav = new ModelAndView("welcome", "message", " Invalid User !!!");

		}

		return mav;

	}

	private void enableLoggedInStatus(Long id) {
		Connection con = mySqlConnection.getMysqlConnection();
		System.out.println("connection" + con); // id, name, pass, isActive, role_id

		try {
			PreparedStatement ps = con.prepareStatement(" update users set isActive=true where id=?");
			ps.setLong(1, id);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private User getUserDetailsBuUserNameAndPassWord(String userName, String password) {
		User user = null;
		try {
			Connection con = mySqlConnection.getMysqlConnection();
			System.out.println("connection" + con);
			PreparedStatement ps = con.prepareStatement(" SELECT * FROM users where name=? and pass=?");
			ps.setString(1, userName);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				Long id = rs.getLong("id");
				user = new User(rs.getString("name"), rs.getString("pass"));
				user.setId(id);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;

	}

	@SuppressWarnings("all")
	public Question getAllTestQuestionsWithANsDB(Long id) {
		Question question = null;
		List<Option> opnlist = new ArrayList<>();

		try {
			Connection con = mySqlConnection.getMysqlConnection();
			PreparedStatement ps = con.prepareStatement(
					"SELECT q.name  as q_name, q.que_serial_no ,q.marks, q.answer_id,o.name as opn_name , o.option_id FROM questions q inner join options o on q.que_serial_no=o.q_serrial_id where q.que_serial_no=?; ");

			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();

			int excecutionCout = 0;
			int initialCount = -1;
			while (rs.next()) {

				String name = rs.getString("q_name");
				Long marks = rs.getLong("marks");
				Long answerId = rs.getLong("answer_id");
				Long queSerialNo = rs.getLong("que_serial_no");
				Long optionId = rs.getLong("option_id");
				String opnName = rs.getString("opn_name");
				initialCount++;

				if (initialCount == 0) {
					question = new Question();
					question.setAnswerId(answerId);
					question.setQue_serial_no(queSerialNo);
					question.setMarks(marks);
					question.setName(name);
				} else if (initialCount == 4) {
					initialCount = -1;
				}
				Option option = new Option();
				option.setQ_serrial_id(queSerialNo);
				option.setName(opnName);
				option.setOptionId(optionId);
				opnlist.add(option);
				question.setListOptions(opnlist);
				excecutionCout++;

				if (excecutionCout == 4) {

					excecutionCout = 0;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();

		}

		return question;
	}

// This is the test method 
	private List<Question> getAllTestQuestionsWithANs() {

		List<Question> questionList = new ArrayList<Question>();

		Question question = new Question();
		question.setAnswerId(4L);
		question.setId(1L);
		question.setMarks(1L);
		question.setName("What is PolyM?");

		Option option1 = new Option();
		option1.setQ_serrial_id(1L);
		Option option2 = new Option();

		option1.setName("MOverloading is PloyM");
		option2.setQ_serrial_id(1L);
		option2.setName("MOverriding is PloyM");

		Option option3 = new Option();
		option3.setQ_serrial_id(1L);
		option3.setName("CTP is MO");
		Option option4 = new Option();

		option4.setQ_serrial_id(1L);
		option4.setName("all of above ");

		ArrayList<Option> list = new ArrayList<>();
		list.add(option1);
		list.add(option2);
		list.add(option3);
		list.add(option4);
		question.setListOptions(list);
		questionList.add(question);
		return questionList;
	}

	@RequestMapping("/reg")
	public ModelAndView registerRedirect() {
		System.out.println("registerRedirect");
		return new ModelAndView("register");
	}

	@RequestMapping("/register")
	public ModelAndView register(@ModelAttribute User user) {
		Boolean isResistered = registerUser(user);
		ModelAndView mav=null;
		if(isResistered) {
			mav=new ModelAndView("register", "message" , "Register Sucess.");
		}else {
			mav=new ModelAndView("register", "message" , "Register failed.");
		}
		return mav;
	}

	private Boolean registerUser(User user) {
		Boolean isResistered = false;
		try {
			Connection con = mySqlConnection.getMysqlConnection();
			PreparedStatement ps = con
			.prepareStatement("insert into users( name, pass, isActive, role_id) values (?,?,?,?)");

			ps.setString(1, user.getName());
			ps.setString(2, user.getPass());
			ps.setBoolean(3, false);
			ps.setLong(4, 2L);
			int rowAffected = ps.executeUpdate();

			if (rowAffected > 0) {

				isResistered = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return isResistered;
	}
	
	
	@RequestMapping("/r_forget")
	public ModelAndView mk() {
		return new ModelAndView("forget_pass");
	}
	
	@RequestMapping("/forget_p")
	public ModelAndView forget_p(@RequestParam("name") String name) {
		
		// select pass from users where name=? 
		//  name
		
		return new ModelAndView("forget_pass");
	}
}
