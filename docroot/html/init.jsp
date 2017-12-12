<%
/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */
%>
<%@page import="com.liferay.portal.security.auth.PrincipalException"%>
<%@page import="com.liferay.dl.cleaner.service.UnusedFileLocalServiceUtil"%>
<%@page import="com.liferay.dl.cleaner.service.WcReferencedFileLocalServiceUtil"%>
<%@page import="com.liferay.dl.cleaner.model.UnusedFile"%>
<%@page import="com.liferay.dl.cleaner.service.permission.UnusedFilePermission"%>
<%@page import="com.liferay.portal.kernel.util.WebKeys"%>
<%@page import="com.liferay.portal.kernel.dao.search.ResultRow"%>
<%@page import="com.liferay.portal.kernel.dao.search.SearchContainer"%>
<%@page import="com.liferay.portal.kernel.dao.search.DisplayTerms"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.liferay.dl.cleaner.portlet.util.ActionKeys"%>

<%@page import="com.liferay.dl.cleaner.service.permission.WcReferencedFilePermission"%>
<%@page import="com.liferay.dl.cleaner.model.WcReferencedFile"%>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/security" prefix="liferay-security" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>
<%@page import="com.liferay.portal.kernel.dao.orm.QueryUtil"%>
<%@page import="com.liferay.portal.service.GroupLocalServiceUtil"%>
<%@page import="com.liferay.portal.model.Group"%>
<%@page import="java.util.List"%>
<portlet:defineObjects />
<liferay-theme:defineObjects />
<portlet:renderURL var="orPhanFilesUrl">
	<portlet:param name="mvcPath" value="/html/orphanfile/view.jsp"/>
</portlet:renderURL>
<%
	Boolean orphan = ParamUtil.getBoolean(renderRequest, "orphan", true);
	long groupId = ParamUtil.getLong(request, "groupId");
	boolean deleted = ParamUtil.getBoolean(request, "deleted", false);
%>