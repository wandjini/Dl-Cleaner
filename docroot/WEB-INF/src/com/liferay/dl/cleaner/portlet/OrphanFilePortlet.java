package com.liferay.dl.cleaner.portlet;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.dl.cleaner.service.WcReferencedFileServiceUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.util.bridges.mvc.MVCPortlet;

/**
 * Portlet implementation class OrphanFilePortlet
 */
public class OrphanFilePortlet extends MVCPortlet {
	
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
	
		super.doView(renderRequest, renderResponse);
	}
	public void deleteWcRefencedFile(ActionRequest actionRequest, ActionResponse actionResponse){
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		long wcReferencedFileId = ParamUtil.getLong(actionRequest, "wcReferencedFileId");
		
	
		try {
			WcReferencedFileServiceUtil.deleteWcReferencedFile(themeDisplay.getScopeGroupId(), wcReferencedFileId);
			
		} catch (Exception e) {
			if(e instanceof PrincipalException){
				SessionErrors.add(actionRequest, PrincipalException.class);
			}
			else{
				SessionErrors.add(actionRequest,"generic-error");
			}
		}
	}

}
