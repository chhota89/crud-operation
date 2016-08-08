package com.bridgelabz.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.bridgelabz.dao.InsertDao;
import com.bridgelabz.dto.EmployeeDto;
import com.google.android.gcm.server.InvalidRequestException;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

@RestController("restController")
@EnableWebMvc
public class MyRestController {
	@Autowired
	InsertDao dao;
	 final int retries = 3;
	 private static final String API_KEY="AIzaSyDS8LvhHIwteKkIWSyXTvRF9NLBjZS9gG8"; 
	 private static final String GCM_ID="APA91bHS3sCqN1kxGXjhEDTQw2UXEBlFuwZNbpJ_0yy_KE_7YS8Bap7nIxqt778YiLOJj6T7vWaDZlZ2W61nuG8ubGEILGjxxMNBF8GR6LV23-ZuRvAJc75NxO9WTNLaueL7tGi7V9paSTVp1Hl5e_uTJAV22YjHPg";
	
	 
	 @RequestMapping(value = "gamecard", method = RequestMethod.GET, produces = "application/json")
	public List<EmployeeDto> displayJson() {
		List<EmployeeDto> list = dao.getAssendingOrder();
		// model1.addAttribute("nameAsList", list);
		return list;
	}

	@RequestMapping(value = "sendGcm", method = RequestMethod.GET, produces = "application/json")
	public Result sendGcmMessage() {
		Result result=null;
		Sender sender = new Sender(API_KEY);
		Message msg2 = new Message.Builder().addData("message", "Something New for You").build();
		try {

			result = sender.send(msg2, GCM_ID, retries);

			System.out.println("Send message "+result.getMessageId());

		} catch (InvalidRequestException e) {
			System.out.println("Invalid Request "+e.getMessage());
		} catch (IOException e) {
			System.out.println("IO Exception"+e.getMessage());
		}
		finally{
			return result;
		}
	}
	
	
	@RequestMapping(value = "getList", params="value", method = RequestMethod.GET, produces = "application/json")
	public List<EmployeeDto> getList(@RequestParam("value") int value) {
		return dao.getTenEmployeeList(value);
	}
}
