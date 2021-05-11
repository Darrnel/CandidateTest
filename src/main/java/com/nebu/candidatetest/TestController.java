package com.nebu.candidatetest;

import java.nio.channels.FileLock;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.nebu.candidatetest.Model.HourType;
import com.nebu.candidatetest.Model.Interviewer;
import com.nebu.candidatetest.Model.Meeting;
import com.nebu.candidatetest.Model.Supervisor;
import com.nebu.candidatetest.Service.Services;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {
	List<Interviewer> interviewerList = new ArrayList<>();
	List<Meeting> meetingList = new ArrayList<>();
	List<HourType> hourTypeList = new ArrayList<>();
	List<Supervisor> supervisorList = new ArrayList<>();

	Date startDate = null;
	Date endDate = null;

	@RequestMapping(value={"/CandidateTest"}, method = RequestMethod.GET)
	public ModelAndView getMessage() {
		ModelAndView modelAndView = new ModelAndView();
		String result = "";

		//supervisors
		try {
			Connection conn = DatabaseTools.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Supervisor");


			while (rs.next()) {
				Supervisor supervisor = new Supervisor(rs.getInt("ID"), rs.getString("NAME"));
				supervisorList.add(supervisor);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// interviewers
		try {
			Connection conn = DatabaseTools.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Interviewer");


			while (rs.next()) {
				Interviewer interviewer = new Interviewer(rs.getInt("ID"), rs.getString("NAME"),
						rs.getFloat("HOURLYRATE"), supervisorList.get(rs.getInt("SUPERVISORID")-1).getName());
				interviewerList.add(interviewer);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//hourtypes
		try {
			Connection conn = DatabaseTools.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Hourtype");


			while (rs.next()) {
				HourType hourType = new HourType(rs.getInt("ID"), rs.getString("Hourtypename"), rs.getFloat("Ratio"));
				hourTypeList.add(hourType);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//sessions
		try {
			Connection conn = DatabaseTools.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Session");


			while (rs.next()) {
				Meeting meeting = new Meeting(interviewerList.get(rs.getInt("INTERVIEWERID")-1), hourTypeList.get(rs.getInt("HOURTYPEID")-1),
						rs.getDate("SESSIONSTART"), rs.getDate("SESSIONEND"),
						rs.getTime("SESSIONSTART"), rs.getTime("SESSIONEND"));

				meetingList.add(meeting);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		modelAndView.addObject("startDate", startDate);
		modelAndView.addObject("endDate", endDate);

		// Interviewer earning
		HashMap<String, Float> interviewerEarnings;
		if(startDate == null && endDate == null)
			interviewerEarnings = Services.interviewerEarning(meetingList, interviewerList);
		else
			interviewerEarnings = Services.interviewerEarning(meetingList, interviewerList, startDate, endDate);

		// Supervisor earning
		HashMap<String, Float> supervisorEarnings;
			supervisorEarnings = Services.supervisorEarningMobile(meetingList, supervisorList);


		modelAndView.addObject("interviewerList", interviewerEarnings);
		modelAndView.addObject("supervisorList", supervisorEarnings);

		modelAndView.setViewName("index");

		return modelAndView;
	}
}