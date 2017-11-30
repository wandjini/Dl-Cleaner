<%@ include file="/html/init.jsp" %>
<%
SearchContainer searchContainer = (SearchContainer)request.getAttribute("liferay-ui:search:searchContainer");
DisplayTerms displayTerms = searchContainer.getDisplayTerms();
%>
<liferay-ui:search-toggle
buttonLabel="Search"
displayTerms="<%= displayTerms %>"
id="toggle_id_orphan_file_search">
<aui:select name="orphan">
	<aui:option label="YES" value="true" selected="<%=orphan %>"></aui:option>
	<aui:option label="NO" value="false" selected="<%= !orphan %>"></aui:option>
</aui:select>
</liferay-ui:search-toggle>