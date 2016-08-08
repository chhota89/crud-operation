package com.bridgelabz.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bridgelabz.dao.InsertDao;
import com.bridgelabz.dto.EmployeeDto;
import com.bridgelabz.utility.StringUtility;

@Controller("abcd")
public class Cont {
	
	@Autowired
	InsertDao dao;
	
	private static final int min=1;
	private static final int max=100;
	
	
	
	/*@RequestMapping("login")
	public ModelAndView redirect()
	{
		System.out.println("in resirect");
		return new ModelAndView("index");
	}*/
/*
	@ResponseBody
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public String insetMethod(HttpServletRequest req) {

		String name = req.getParameter("name");
		int age = Integer.parseInt(req.getParameter("age"));
		System.out.println("Value get " + name + " " + age);
		EmployeeDto employeeDto = new EmployeeDto();
		employeeDto.setAge(age);
		employeeDto.setName(name);
		boolean result = dao.isInserted(employeeDto);
		return "result: " + result;

	}*/
	@RequestMapping(value="insert",method = RequestMethod.GET)
	public ModelAndView insertMethod(HttpServletRequest req){
		
		String name=req.getParameter("name");
		int age=Integer.parseInt(req.getParameter("age"));
		Map<String, Object> model = new HashMap<String, Object>();
		System.out.println("Value get "+name+" "+age);
		EmployeeDto employeeDto=new EmployeeDto();
		employeeDto.setAge(age);
		employeeDto.setName(name);
		model.put("name", name);
		model.put("age", age);
		boolean result=dao.isInserted(employeeDto);
		//return "result: "+result;
	    return new ModelAndView("result",model);
		
	}
	/*@RequestMapping(value = "/insert")
	public String inset(@RequestParam(value="name") String name,@RequestParam(value="age") int age){
		EmployeeDto employeeDto=new EmployeeDto();
		employeeDto.setAge(age);
		employeeDto.setName(name);
		boolean result=dao.isInserted(employeeDto);
		return "result";
    }*/
	
	@RequestMapping(value="/display")
	public ModelAndView displayData(){
		Map<String, Object> model = new HashMap<String, Object>();
		List<EmployeeDto> list=dao.getEmployeeList();
		model.put("EmployeeDto", list);
		return new ModelAndView("displaylist",model);
	}
	
	@RequestMapping(value="update",method = RequestMethod.GET)
	public ModelAndView update(HttpServletRequest req){
		
		String name=req.getParameter("name");
		int age=Integer.parseInt(req.getParameter("age"));
		EmployeeDto employeeDto=new EmployeeDto();
		employeeDto.setAge(age);
		employeeDto.setName(name);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("name", name);
		model.put("age", age);
		dao.update(employeeDto);
		//return "result: "+result;
	    return new ModelAndView("result",model);
		
	}
	
	@RequestMapping(value="delete",method = RequestMethod.GET)
	public ModelAndView delete(HttpServletRequest req){
		
		String name=req.getParameter("name");
		EmployeeDto employeeDto=new EmployeeDto();
		employeeDto.setName(name);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("name", name);
		dao.delete(employeeDto);
		//return "result: "+result;
	    return new ModelAndView("result",model);
	}
	
	@RequestMapping(value="displayName")
	public ModelAndView displayName(){
		Map<String, Object> model = new HashMap<String, Object>();
		List<String> list=dao.displayName();
		model.put("nameList", list);
		//model1.addAttribute("nameAsList", list);
		return new ModelAndView("displayName",model);
	}
	
	
	@RequestMapping(value="accendingOrderByAge")
	public ModelAndView accendingOrderByAge(){
		Map<String, Object> model = new HashMap<String, Object>();
		List<EmployeeDto> list=dao.getAssendingOrder();
		model.put("EmployeeDto", list);
		//model1.addAttribute("nameAsList", list);
		return new ModelAndView("displaylist",model);
	}
	
	//http://localhost:8080/CrudeOperation/reqParam?value=mohmad
	
	@RequestMapping(value="reqParam", params="value" ,method = RequestMethod.GET, headers="Accept=application/json")
	public List<EmployeeDto> accendingOrderByAge(@RequestParam("value") String abc){
		System.out.println("Request Parameter "+abc);
		
		Map<String, Object> model = new HashMap<String, Object>();
		List<EmployeeDto> list=dao.getAssendingOrder();
		model.put("EmployeeDto", list);
		//model1.addAttribute("nameAsList", list);
		return list;
	}
	
	
	//Path Veriable demo
	@RequestMapping(value="pathVeriable/{id}")
	public ModelAndView pathVeriable(@PathVariable("id") String veriable){
		System.out.println("Path nveriable is "+veriable);
		Map<String, Object> model = new HashMap<String, Object>();
		List<EmployeeDto> list=dao.getAssendingOrder();
		model.put("EmployeeDto", list);
		//model1.addAttribute("nameAsList", list);
		return new ModelAndView("displaylist",model);
	}
	
	@RequestMapping(value="insertEmpList")
	public ModelAndView insertEmpList(){
		ArrayList<EmployeeDto> list=new ArrayList<EmployeeDto>();
		for(int i=0;i<50;i++){
			list.add(new EmployeeDto(StringUtility.nextSessionId(), StringUtility.randInt(min,max)));
		}
		boolean result=dao.isInserted(list);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("name", result);
	    return new ModelAndView("result",model);
		
	}
	
}
