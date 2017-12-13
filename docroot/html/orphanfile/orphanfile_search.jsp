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
<aui:select name="groupId" label="site">
	
		<aui:option label="ALL" value="0"></aui:option>
		<%if(groups != null && !groups.isEmpty()){
			for(Group group: groups){
				if(group.isSite()){%>
					<aui:option label="<%= group.getDescriptiveName() %>" value="<%=String.valueOf(group.getGroupId()) %>" selected="<%= groupId == group.getGroupId() %>"></aui:option>
				
				<%}%>
			<%}%>
		<%}%>>
	</aui:select>
</liferay-ui:search-toggle>