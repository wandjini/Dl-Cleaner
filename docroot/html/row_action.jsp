
<%@include file="/html/init.jsp"%>

<%
SearchContainer searchContainer = (SearchContainer)request.getAttribute("liferay-ui:search:searchContainer");
String redirectUrl = searchContainer.getIteratorURL().toString();
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);
UnusedFile unusedFile = (UnusedFile) row.getObject();
%>

 <% if(UnusedFilePermission.contains(permissionChecker, themeDisplay.getScopeGroupId(), ActionKeys.DELETE_UNUSED_FILE)){ %>
	<portlet:actionURL name="deleteUnusedFile" var="deleteUnusedFileURL">
		<portlet:param name="unusedFileId" value="<%= String.valueOf(unusedFile.getUnusedFileId())%>"></portlet:param>
	</portlet:actionURL>
	<liferay-ui:icon-delete url="<%=deleteUnusedFileURL.toString()%>" />
<% } %> 