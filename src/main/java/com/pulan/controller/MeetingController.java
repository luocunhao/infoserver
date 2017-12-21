package com.pulan.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.pulan.model.MeetingRoom;
import com.pulan.model.oa.MeetingMain;
import com.pulan.model.oa.MeetingMainAreader;
import com.pulan.model.oa.MeetingMainAttendPerson;
import com.pulan.model.oa.MeetingMainCopyPerson;
import com.pulan.model.oa.MeetingMainReader;
import com.pulan.model.oa.MeetingresMain;
import com.pulan.model.oa.NotifyTodo;
import com.pulan.model.oa.NotifyTodoTarget;
import com.pulan.model.oa.Template;
import com.pulan.service.MeetingOAService;
import com.pulan.service.MeetingRoomService;
import com.pulan.utils.DealTimeUtils;

@Controller 
@RequestMapping(value = "/info")
public class MeetingController {
	@Autowired
	private MeetingRoomService meetingRoomServiceimpl;
	@Autowired
	private MeetingOAService meetingOAServiceImpl;
	//获取所有部门
	@ResponseBody
	@RequestMapping("/getAllCategoryMain")
	public JSONObject getAllCategoryMain(){
		JSONObject ret = new JSONObject();
		List<Template> list = meetingOAServiceImpl.getAllCategoryMain();
		ret.put("resp", list);
		return ret;
	}
	//获取所有部门下所有会议模板
	@ResponseBody
	@RequestMapping("/getAllKmMeetingTemplate")
	public JSONObject getAllKmMeetingTemplate(@Param("doc_category_id") String doc_category_id){
		JSONObject ret = new JSONObject();
		List<Template> list = meetingOAServiceImpl.getAllKmMeetingTemplate(doc_category_id);
		ret.put("resp", list);
		return ret;
	}
	//获取所有员工
	@ResponseBody
	@RequestMapping("/getAllOrgPerson")
	public JSONObject getAllOrgPerson(){
		JSONObject ret = new JSONObject();
		List<Template> list = meetingOAServiceImpl.getAllOrgPerson();
		ret.put("resp", list);
		return ret;
	}
	@ResponseBody
	@RequestMapping("/idleMeeting")
	public JSONObject idleMeeting(@Param("starttime") String starttime,@Param("endtime") String endtime){
		JSONObject ret = new JSONObject();
		List<MeetingRoom> idleRoomList = new ArrayList<MeetingRoom>();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		long start_time = 0l;
		long end_time = 0l;
		try {
			start_time = df.parse(starttime).getTime();
			end_time = df.parse(endtime).getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//忙会议室
		List<String> listed = meetingRoomServiceimpl.getMeetingRoomBytime(start_time,end_time);
		//所有会议室
		List<String> list = meetingRoomServiceimpl.getAllMeetingRoom();
		//空闲会议室
		list.removeAll(listed);
		for(String str:list){
			idleRoomList.add(meetingRoomServiceimpl.getRoomNameByString(str).get(0));
		}
		ret.put("resp", idleRoomList);
		return ret;
	}
	@ResponseBody
	@Transactional
	@RequestMapping(value="/insertMeeting", method = RequestMethod.POST)
	public JSONObject insertMeeting(@RequestBody String MsgBody,HttpServletRequest request){
		JSONObject ret = new JSONObject();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String now = df.format(new Date());
		JSONObject MsgObj = JSONObject.parseObject(MsgBody);
		//km_meeting_main param
		String meetingMainFd_id = UUID.randomUUID().toString().replaceAll("-", "");// meetingMain.setFd_id("18676762954");
		String meetingMainDoc_subject = MsgObj.getString("meetingMainDoc_subject");//meetingMain.setDoc_subject("普兰会议测试");
		String meetingMainBeginTime = MsgObj.getString("meetingMainBeginTime");
		String meetingMainEndTime = MsgObj.getString("meetingMainEndTime");
		Map<String,String> map = DealTimeUtils.getAllFormatTimeForMeetingMain(meetingMainBeginTime, meetingMainEndTime);
		String meetingMainHbm_hold_date =  map.get("hbm_hold_date");//meetingMain.setHbm_hold_date("1511546400000");
		String meetingMainFd_hold_date_time = map.get("fd_hold_date_time");//meetingMain.setFd_hold_date_time("2017-11-25");
		String meetingMainHbm_hold_time = map.get("hbm_hold_time");//meetingMain.setHbm_hold_time("7200000");
		String meetingMainHbm_finish_date =  map.get("hbm_finish_date");//meetingMain.setHbm_finish_date("1511546400000");
		String meetingMainHbm_finish_time =  map.get("hbm_finish_time");//meetingMain.setHbm_finish_time("14400000");
		String meetingMainDoc_content = MsgObj.getString("meetingMainDoc_content");//meetingMain.setDoc_content("就测试一下");
		String meetingMainDoc_createtime = now;//meetingMain.setDoc_create_time("2017-11-24 09:00:00");
		String meetingMainFd_last_modify_time = now;//meetingMain.setFd_last_modified_time("2017-11-24 09:00:00");
		String meetingMainDoc_Creator = MsgObj.getString("meetingMainDoc_Creator");//meetingOAServiceImpl.getEleIdByLoginName(MsgObj.getString("meetingMainDoc_Creator"));//meetingMain.setDoc_creator_id("155fdc44be11b461c560f7e471d8cf65");//登录名
		String meetingMainFd_emcee_id = MsgObj.getString("meetingMainFd_emcee_id");//meetingMain.setFd_emcee_id("155fdc44be11b461c560f7e471d8cf65");//bduser
		String meetingMainDept_id = meetingOAServiceImpl.getEmpByfdid(meetingMainDoc_Creator).get(0);//meetingMain.setDoc_dept_id("14f1ba990dad466e8d2b04b46a8a9ecd");
		String meetingMainFd_template_id = MsgObj.getString("meetingMainFd_template_id");//meetingMain.setFd_template_id("1419ad5ee8c3ed2f375237a46c490505");//关联km_meeting_template sys_category_main 
		String meetingMainFd_host_id = MsgObj.getString("meetingMainFd_host_id");//meetingMain.setFd_host_id("14158f176991dea83be3fbb44ae8ed7b");//luo
		String meetingFd_hold_place = MsgObj.getString("meetingFd_hold_place");//meetingMain.setFd_hold_place("1419aead8d1214df19c298c4cecb98de");//
		String meetingFd_notify_type =  MsgObj.getString("meetingFd_notify_type");
		
		//km_meeting_attend_person param
		JSONArray attendPersons = MsgObj.getJSONArray("attendPersons");
		
		//km_meeting_copy_person param
		JSONArray copyPersons = MsgObj.getJSONArray("copyPersons");
		
		//km_meetingres_main param
		String meetingResMainFdid = UUID.randomUUID().toString().replaceAll("-", "");
		String meetingResMainDoc_subject = meetingMainDoc_subject;//MsgObj.getString("meetingResMainDoc_subject");
		String meetingResMainDoc_create_time = now;
		String meetingResBegintime = meetingMainBeginTime ;//MsgObj.getString("meetingResBegintime");
		String meetingResEndtime = meetingMainEndTime	;//MsgObj.getString("meetingResEndtime");
		Map<String,String> meetingResMap = DealTimeUtils.getAllFormatTimeForMeetingResMain(meetingResBegintime, meetingResEndtime);
		String meetingMainResfd_time_interval = meetingResMap.get("fd_time_interval");
		String meetingMainResHbm_start_date = meetingResMap.get("hbm_start_date");
		String meetingMainResHbm_start_time = meetingResMap.get("hbm_start_time");
		String meetingMainResFd_order_start_date_time = meetingResMap.get("fd_order_start_date_time");
		String meetingMainResHbm_finish_date = meetingResMap.get("hbm_finish_date");
		String meetingMainResHbm_finish_time = meetingResMap.get("hbm_finish_time");
		String meetingMainResFd_order_finish_date_time = meetingResMap.get("fd_order_finish_date_time");
		String meetingMainResFd_borrow_reason = MsgObj.getString("meetingMainResFd_borrow_reason");
		String meetingMainResDoc_publish_time = now;
		String meetingMainResFd_last_modified_time = now;
		String meetingMainResFd_meeting_id = meetingMainFd_id;
		String meetingMainResDoc_creator_id = meetingMainDoc_Creator;
		String meetingMainResFd_proposer_id = meetingMainDoc_Creator;
		String meetingMainResDoc_dept_id = meetingMainDept_id;
		String meetingMainResFd_resource_id = meetingFd_hold_place;
		
		
		//notify param 直接传会议室点而不是会议室id
		String attendNotifyId = UUID.randomUUID().toString().replaceAll("-", "");
		String copyNotifyId = UUID.randomUUID().toString().replaceAll("-", "");
		String emceeNotifyId = UUID.randomUUID().toString().replaceAll("-", "");
		String meetingMainPlace = MsgObj.getString("meetingMainPlace");//会议室中文名
		
		//km_meeting_main
		MeetingMain meetingMain = new MeetingMain();
		meetingMain.setFd_id(meetingMainFd_id);
		meetingMain.setDoc_subject(meetingMainDoc_subject);
		meetingMain.setHbm_hold_date(meetingMainHbm_hold_date);
		meetingMain.setFd_hold_date_time(meetingMainFd_hold_date_time);
		meetingMain.setHbm_hold_time(meetingMainHbm_hold_time);
		meetingMain.setHbm_finish_date(meetingMainHbm_finish_date);
		meetingMain.setHbm_finish_time(meetingMainHbm_finish_time);
		meetingMain.setDoc_content(meetingMainDoc_content);
		meetingMain.setDoc_create_time(meetingMainDoc_createtime);
		meetingMain.setFd_last_modified_time(meetingMainFd_last_modify_time);
		meetingMain.setDoc_creator_id(meetingMainDoc_Creator);//bduser
		meetingMain.setFd_emcee_id(meetingMainFd_emcee_id);//bduser
		meetingMain.setDoc_dept_id(meetingMainDept_id);//
		meetingMain.setFd_template_id(meetingMainFd_template_id);//关联km_meeting_template sys_category_main 
		meetingMain.setFd_host_id(meetingMainFd_host_id);//luo
		meetingMain.setFd_hold_place(meetingFd_hold_place);//
		meetingMain.setFd_notify_type(meetingFd_notify_type);
		meetingOAServiceImpl.insertMeetingMain(meetingMain);
		
		//km_meetingres_main
		MeetingresMain mrm =new MeetingresMain();
		mrm.setFd_id(meetingResMainFdid);
		mrm.setDoc_subject(meetingResMainDoc_subject);
		mrm.setDoc_create_time(meetingResMainDoc_create_time);
		mrm.setFd_time_interval(meetingMainResfd_time_interval);
		mrm.setHbm_start_date(meetingMainResHbm_start_date);
		mrm.setHbm_start_time(meetingMainResHbm_start_time);
		mrm.setFd_order_start_date_time(meetingMainResFd_order_start_date_time);
		mrm.setHbm_finish_date(meetingMainResHbm_finish_date);
		mrm.setHbm_finish_time(meetingMainResHbm_finish_time);
		mrm.setFd_order_finish_date_time(meetingMainResFd_order_finish_date_time);
//		mrm.setFd_resource_continue("0");
		mrm.setFd_borrow_reason(meetingMainResFd_borrow_reason);
//		mrm.setAuth_reader_flag("0");
		mrm.setDoc_publish_time(meetingMainResDoc_publish_time);
		mrm.setFd_last_modified_time(meetingMainResFd_last_modified_time);
		mrm.setFd_meeting_id(meetingMainResFd_meeting_id);
		mrm.setDoc_creator_id(meetingMainResDoc_creator_id);
		mrm.setFd_proposer_id(meetingMainResFd_proposer_id);
		mrm.setDoc_dept_id(meetingMainResDoc_dept_id);
		mrm.setFd_resource_id(meetingMainResFd_resource_id);//关联km_meetingres_info
		meetingOAServiceImpl.insertMeetingresMain(mrm);
		
		//notify
		//会议组织人notify
		NotifyTodo emceeNotify = new NotifyTodo();
		emceeNotify.setFd_id(emceeNotifyId);
		emceeNotify.setFd_model_name("com.landray.kmss.km.meeting.model.KmMeetingMain");
		emceeNotify.setFs_model_id(meetingMain.getFd_id());
		emceeNotify.setFd_subject("请您组织会议，主题是："+meetingMain.getDoc_subject()+"(召开时间:"+meetingMainBeginTime+";召开地点:"+meetingMainPlace+")");
		emceeNotify.setFd_type("2");
		emceeNotify.setFd_link("/km/meeting/km_meeting_main/kmMeetingMain.do?method=view&fdId="+meetingMain.getFd_id());
		emceeNotify.setFd_bundle("km-meeting:kmMeetingMain.emcee.notify");
		emceeNotify.setFd_replace_text("[{\"key\":\"%km-meeting:kmMeetingMain.fdHoldDateTime%\",\"value\":\""+meetingMainBeginTime+"\"},{\"key\":\"%km-meeting:kmMeetingMain.fdNotifyTitle%\",\"value\":\""+meetingMain.getDoc_subject()+"\"},{\"key\":\"%km-meeting:kmMeetingMain.fdHoldPlace%\",\"value\":\""+meetingMainPlace+"\"}]");
		emceeNotify.setFd_create_time(now);
		//会议参加人notify
		NotifyTodo attendNotify = new NotifyTodo();
		attendNotify.setFd_id(attendNotifyId);
		attendNotify.setFd_model_name("com.landray.kmss.km.meeting.model.KmMeetingMain");
		attendNotify.setFs_model_id(meetingMain.getFd_id());
		attendNotify.setFd_subject("邀请您参加会议：\""+meetingMain.getDoc_subject()+"\"的会议(召开时间:"+meetingMainBeginTime+";召开地点:"+meetingMainPlace+")");
		attendNotify.setFd_link("/km/meeting/km_meeting_main/kmMeetingMain.do?method=view&fdId="+meetingMain.getFd_id());
		attendNotify.setFd_bundle("km-meeting:kmMeetingMain.attend.notify");
		attendNotify.setFd_replace_text("[{\"key\":\"%km-meeting:kmMeetingMain.fdHoldDateTime%\",\"value\":\""+meetingMainBeginTime+"\"},{\"key\":\"%km-meeting:kmMeetingMain.fdNotifyTitle%\",\"value\":\""+meetingMain.getDoc_subject()+"\"},{\"key\":\"%km-meeting:kmMeetingMain.fdHoldPlace%\",\"value\":\""+meetingMainPlace+"\"}]");
		attendNotify.setFd_create_time(now);
		//会议抄送人notify
		NotifyTodo copyNotify = new NotifyTodo();
		copyNotify.setFd_id(copyNotifyId);
		copyNotify.setFd_model_name("com.landray.kmss.km.meeting.model.KmMeetingMain");
		copyNotify.setFs_model_id(meetingMain.getFd_id());
		copyNotify.setFd_subject("会议抄送通知：请您关注主题为:\""+meetingMain.getDoc_subject()+"\"的会议");
		copyNotify.setFd_type("2");
		copyNotify.setFd_link("/km/meeting/km_meeting_main/kmMeetingMain.do?method=view&fdId="+meetingMain.getFd_id());
		copyNotify.setFd_bundle("km-meeting:kmMeeting.meeting.copy.notify");
		copyNotify.setFd_replace_text("[{\"key\":\"%km-meeting:kmMeetingMain.fdNotifyTitle%\",\"value\":\""+meetingMain.getDoc_subject()+"\"}]");
		copyNotify.setFd_create_time(now);
		meetingOAServiceImpl.insertNotifyTodo(emceeNotify);
		meetingOAServiceImpl.insertNotifyTodo(attendNotify);
		meetingOAServiceImpl.insertNotifyTodo(copyNotify);
		//km_meeting_attend_person 和会议参加Notify 和点击查看权限
		for(int i = 0 ;i< attendPersons.size();i++){
			String meetingAttendFdid = attendPersons.getString(i);
			MeetingMainAttendPerson mmap = new MeetingMainAttendPerson(meetingMain.getFd_id(),meetingAttendFdid);
			NotifyTodoTarget ntt = new NotifyTodoTarget(attendNotifyId,meetingAttendFdid);
			meetingOAServiceImpl.insertNotifyTodoTarget(ntt);
			meetingOAServiceImpl.insertMeetingMainAttendPerson(mmap);
			}
		//km_meeting_copy_person和抄送notify 和 点击查看权限
		for(int i = 0 ;i< copyPersons.size();i++){
			String meetingCopyFdid = copyPersons.getString(i);
			MeetingMainCopyPerson mmcp = new MeetingMainCopyPerson(meetingMain.getFd_id(),meetingCopyFdid);
			NotifyTodoTarget ntt = new NotifyTodoTarget(copyNotifyId,meetingCopyFdid);
			meetingOAServiceImpl.insertNotifyTodoTarget(ntt);
			meetingOAServiceImpl.insertMeetingMainCopyPerson(mmcp);
		}
		//既是抄送人 又是参与人 去重  权限这赋予一次
		attendPersons.addAll(copyPersons);
		//集合添加创建人 循环里面赋权
		attendPersons.add(meetingMainResDoc_creator_id);
		//集合添加组织人  循环里面赋权
		attendPersons.add(meetingMainFd_emcee_id);
		Set<String> set = new HashSet<String>();
		for(int i=0;i<attendPersons.size();i++){
			String id = attendPersons.getString(i);
			if(!set.contains(id)){
				MeetingMainAreader mmr = new MeetingMainAreader(meetingMainFd_id,id);
				meetingOAServiceImpl.insertMeetingMainAreader(mmr);
				set.add(id);
			}
		}
		//会议组织notify
		NotifyTodoTarget emceeNotifyTodoTarget = new NotifyTodoTarget(emceeNotifyId,meetingMainFd_emcee_id);
		meetingOAServiceImpl.insertNotifyTodoTarget(emceeNotifyTodoTarget);
		//权限 可阅读这  花样年中国集团
		MeetingMainReader meetingMainReader =new MeetingMainReader(meetingMainFd_id,"14158f173ee9bb207355e674839b8855");
		meetingOAServiceImpl.insertMeetingMainReader(meetingMainReader);
		ret.put("resp", "0");
		return ret;
	}
}
