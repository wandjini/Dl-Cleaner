package com.liferay.dl.cleaner.portlet.util;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.util.portlet.PortletProps;

/**
 * This calls is used to retrieve default email sender and receiver
 * 
 * @author guywandji
 *
 */
public class PropsUtil extends com.liferay.portal.kernel.util.PropsUtil {
	private final static String EMAIL_SENDER_ADDRESS="email.sender.address";
	
	private final static String DEFAULT_DESTINATION_ADDRESS="default.destination.email.address";
	
	public final static String getEmailsenderAddress(){
		
		return GetterUtil.getString(PortletProps.get(EMAIL_SENDER_ADDRESS));
	}
	
	public final static String getDefaultDestinationAddress(){
		
		return GetterUtil.getString(PortletProps.get(DEFAULT_DESTINATION_ADDRESS));
	}
}
