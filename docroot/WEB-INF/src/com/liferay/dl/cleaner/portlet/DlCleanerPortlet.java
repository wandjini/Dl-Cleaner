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
import com.liferay.dl.cleaner.service.WcReferencedFileServiceUtil;
import com.liferay.portal.NoSuchLockException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
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
public class DlCleanerPortlet extends MVCPortlet {

	private static Log _log = LogFactoryUtil.getLog(DlCleanerPortlet.class);

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {

		super.doView(renderRequest, renderResponse);
	}

	/**
	 * This method remove deletes the unused file
	 * 
	 * @param actionRequest
	 * @param actionResponse
	 */
	public void deleteUnusedFile(ActionRequest actionRequest, ActionResponse actionResponse) {
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		long[] deleteUnusedFileIds = ParamUtil.getLongValues(actionRequest, "deleteUnusedFileIds");

		try {
			if(deleteUnusedFileIds != null && deleteUnusedFileIds.length > 0){
				for(long unusedFileId : deleteUnusedFileIds)	
					UnusedFileServiceUtil.deleteUnusedFile(themeDisplay.getUserId(), themeDisplay.getScopeGroupId(),
							unusedFileId);
			}
		} catch (Exception e) {
			if (e instanceof PrincipalException) {
				SessionErrors.add(actionRequest, PrincipalException.class);
			} else {
				SessionErrors.add(actionRequest, "generic-error");
			}
		}
	}

	/**
	 * This Method is used to start the job
	 * 
	 * @param actionRequest
	 * @param actionResponse
	 */
	public void runJob(ActionRequest actionRequest, ActionResponse actionResponse) {
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		JSONObject payLoad = JSONFactoryUtil.createJSONObject().put("userId", themeDisplay.getUserId()).put("companyId",
				themeDisplay.getCompanyId());

		Message message = new Message();
		message.setPayload(payLoad.toString());
		try {

			Lock lock = LockLocalServiceUtil.getLock(CheckUnreferencedFilesMessageListener.class.getName(),
					ActionKeys.KEY_JOB);
			if (lock == null)
				MessageBusUtil.sendMessage(ActionKeys.DESTINATION_NAME, message);
		} catch (Exception e) {
			if (e instanceof NoSuchLockException) {
				MessageBusUtil.sendMessage(ActionKeys.DESTINATION_NAME, message);
			} else {
				_log.error("Error runing the job", e);
				SessionErrors.add(actionRequest, "generic-error");
			}
		}

	}

	/**
	 * This method is used to delete referenced file
	 * 
	 * @param actionRequest
	 * @param actionResponse
	 */
	public void deleteWcRefencedFile(ActionRequest actionRequest, ActionResponse actionResponse) {
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		//long wcReferencedFileId = ParamUtil.getLong(actionRequest, "wcReferencedFileId");
		long[] deleteWcReferencedFileIds = ParamUtil.getLongValues(actionRequest, "deleteWcReferencedFileIds");
		try {
			if(deleteWcReferencedFileIds != null && deleteWcReferencedFileIds.length > 0){
				for(long wcReferencedFileId : deleteWcReferencedFileIds )
					WcReferencedFileServiceUtil.deleteWcReferencedFile(themeDisplay.getScopeGroupId(), wcReferencedFileId);
			}
			
		} catch (Exception e) {
			if (e instanceof PrincipalException) {
				SessionErrors.add(actionRequest, PrincipalException.class);
			} else {
				SessionErrors.add(actionRequest, "generic-error");
			}
		}
	}
}
