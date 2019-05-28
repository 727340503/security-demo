package com.imooc.security.core.qq.socal.conn;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

import com.imooc.security.core.qq.socal.api.QQApi;
import com.imooc.security.core.qq.socal.api.QQUserInfo;

public class QQApiAdapter implements ApiAdapter<QQApi>{

	@Override
	public boolean test(QQApi api) {
		return true;
	}

	@Override
	public void setConnectionValues(QQApi api, ConnectionValues values) {
		QQUserInfo userInfo = api.getUserInfo();
		
		values.setDisplayName(userInfo.getNickname());
		values.setImageUrl(userInfo.getFigureurl());
		values.setProfileUrl(null);
		values.setProviderUserId(userInfo.getOpenId());
	}

	@Override
	public UserProfile fetchUserProfile(QQApi api) {
		return null;
	}

	@Override
	public void updateStatus(QQApi api, String message) {
		// TODO Auto-generated method stub
		//Nothing to do
	}

}
