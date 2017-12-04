
<%@include file="/html/init.jsp"%>

<%
SearchContainer searchContainer = (SearchContainer)request.getAttribute("liferay-ui:search:searchContainer");
String redirectUrl = searchContainer.getIteratorURL().toString();
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);
WcReferencedFile wcReferencedFile = (WcReferencedFile) row.getObject();
%>

 <% if(WcReferencedFilePermission.contains(permissionChecker, themeDisplay.getScopeGroupId(), ActionKeys.DELETE_WCREFERENCED_FILE)){ %>
	<portlet:actionURL name="deleteWcRefencedFile" var="deleteWcRefencedFileURL">
		<portlet:param name="wcReferencedFileId" value="<%= String.valueOf(wcReferencedFile.getWcRefencedFileId())%>"></portlet:param>
		<portlet:param name="redirect" value="<%=themeDisplay.getURLCurrent() %>"/>
	</portlet:actionURL>
	<liferay-ui:icon-delete url="<%=deleteWcRefencedFileURL.toString()%>" />
<% } %> 