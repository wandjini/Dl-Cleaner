package com.liferay.dl.cleaner.portlet;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.dl.cleaner.messaging.impl.CheckUnreferencedFilesMessageListener;
import com.liferay.dl.cleaner.portlet.util.ActionKeys;
import com.liferay.dl.cleaner.service.UnusedFileServiceUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Lock;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.service.LockLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.util.bridges.mvc.MVCPortlet;

/**
 * This Class manages the DlCleaner FE view
 * 
 * @author guywandji
 *
 */
public class DlCleanerPortlet extends MVCPortlet{

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		
		super.doView(renderRequest, renderResponse);
	}
	
	public void deleteUnusedFile(ActionRequest actionRequest, ActionResponse actionResponse){
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		long unusedFileId = ParamUtil.getLong(actionRequest, "unusedFileId");
		
	
		try {
			UnusedFileServiceUtil.deleteUnusedFile(themeDisplay.getUserId(), themeDisplay.getScopeGroupId(), unusedFileId);
		} catch (Exception e) {
			if(e instanceof PrincipalException){
				SessionErrors.add(actionRequest, PrincipalException.class);
			}
			else{
				SessionErrors.add(actionRequest,"generic-error");
			}
		}
	}
	public void startCleanerJob(ActionRequest actionRequest, ActionResponse actionResponse){
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		JSONObject payLoad = JSONFactoryUtil.createJSONObject()
				.put("userId", themeDisplay.getUserId())
				.put("groupId", themeDisplay.getScopeGroupId());
				
		Message message = new Message();
		message.setPayload(payLoad.toString());
		try {
			
			Lock lock = LockLocalServiceUtil.getLock(CheckUnreferencedFilesMessageListener.class.getName(),
					ActionKeys.KEY_JOB); 
			if(lock == null)
				MessageBusUtil.sendMessage(ActionKeys.DESTINATION_NAME, message);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	

}
