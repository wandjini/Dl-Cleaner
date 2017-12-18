package com.liferay.dl.cleaner.portlet;

import java.io.IOException;
import java.lang.reflect.Method;

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
import com.liferay.portal.kernel.cluster.ClusterInvokeThreadLocal;
import com.liferay.portal.kernel.cluster.ClusterMasterExecutorUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.MethodHandler;
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
	public void init() throws PortletException {

		Lock lock = getLock();
		boolean isClusterEnabled = ClusterInvokeThreadLocal.isEnabled();
		if (lock != null && (!isClusterEnabled || ClusterMasterExecutorUtil.isMaster())) {
			try {
				LockLocalServiceUtil.deleteLock(lock);
			} catch (SystemException e) {
				_log.error(e);
			}
		}
		super.init();
	}

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {

		Lock lock = getLock();
		if (lock != null) {
			include("/html/task_running.jsp", renderRequest, renderResponse);
			return;
		}

		super.doView(renderRequest, renderResponse);
	}

	private Lock getLock() {
		Lock lock = null;
		try {
			lock = LockLocalServiceUtil.getLock(CheckUnreferencedFilesMessageListener.class.getName(),ActionKeys.KEY_JOB);
		} catch(Exception e) {
			if (e instanceof NoSuchLockException) {
				if (_log.isDebugEnabled()) {
					_log.debug("There is no lock, so no task should be running");
				}
			}
			else {
				_log.error(e);
			}
		}
		return lock;
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

		Lock lock = getLock();
		if (lock == null) {
			executeJob(message, actionResponse);
		}

	}
	
	private void executeJob(Message message, ActionResponse actionResponse) {
		if (ClusterInvokeThreadLocal.isEnabled()) {
			try {
				Method method = MessageBusUtil.class.getMethod("sendMessage", String.class, Message.class);
				MethodHandler methodHandler = new MethodHandler(
						method, ActionKeys.DESTINATION_NAME, message);

				ClusterMasterExecutorUtil.executeOnMaster(methodHandler);
			} catch(NoSuchMethodException nsme) {
				_log.error("The method doesn't exist", nsme);
			} catch(SystemException se) {
				_log.error(se);
			}
		} else {
			MessageBusUtil.sendMessage(ActionKeys.DESTINATION_NAME, message);
		}
		actionResponse.setRenderParameter("mvcPath", "/html/task_running.jsp");
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
