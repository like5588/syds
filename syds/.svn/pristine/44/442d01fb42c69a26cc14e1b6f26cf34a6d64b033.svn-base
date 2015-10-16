package com.ty.photography.job;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ty.photography.dao.JudgeLogonMapper;
import com.ty.photography.dao.PhotoInfoMapper;
import com.ty.photography.model.JudgeLogon;

@Service("photoDistributionJob") 
public class PhotoDistributionJob{
	private Logger log = LoggerFactory.getLogger(PhotoDistributionJob.class);
	@Autowired
	private PhotoInfoMapper photoInfoMapper;
	@Autowired
	private JudgeLogonMapper judgeLogonMapper;

	public void distribution(){
		try{
			JudgeLogon judgeLogon = new JudgeLogon();
			judgeLogon.setType(9);
			judgeLogon.setJudgeRole(0);
			judgeLogon.setStatus(0);
			judgeLogon.setDistribution(0);
			List<JudgeLogon> judgeLogonList = judgeLogonMapper.queryByConstraint(judgeLogon);
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			for (JudgeLogon judge : judgeLogonList) {
				if(judge.getApprFromDate() != null && judge.getApprToDate() != null){
					Calendar toDate = Calendar.getInstance();
					toDate.setTime(judge.getApprToDate());
					toDate.add(Calendar.DAY_OF_YEAR, 1);
					if(cal.getTime().after(judge.getApprFromDate())){
						Map<String,Object> paramMap = new HashMap<String,Object>();
						paramMap.put("fromDate", judge.getApprFromDate());
						paramMap.put("toDate", toDate.getTime());
						paramMap.put("judgeGroupId", judge.getJudgeGroupId());
						paramMap.put("judgeId", judge.getId());
						photoInfoMapper.photoDistribution(paramMap);
					}
					if(cal.after(toDate)){
						judgeLogon = new JudgeLogon();
						judgeLogon.setId(judge.getId());
						judgeLogon.setDistribution(1);
						judgeLogonMapper.updateById(judgeLogon);
					}
				}
			}
		}catch(Exception e){
			log.error("----PhotoDistributionJob has error----",e);
		}
		
	}

}
