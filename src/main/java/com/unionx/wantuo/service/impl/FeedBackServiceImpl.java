package com.unionx.wantuo.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.unionx.wantuo.base.BaseServiceImpl;
import com.unionx.wantuo.dao.FeedBackMapper;
import com.unionx.wantuo.model.FeedBack;
import com.unionx.wantuo.service.FeedBackService;

@Service("feedBackService")
public class FeedBackServiceImpl extends BaseServiceImpl<FeedBack> implements FeedBackService {
	
	@Autowired
	private FeedBackMapper fbm;

	
}
