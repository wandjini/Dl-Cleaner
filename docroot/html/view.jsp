
<%@include file="/html/init.jsp" %>
<liferay-portlet:renderURL varImpl="iteratorURL"></liferay-portlet:renderURL>
<portlet:actionURL name="runJob" var="runJobUrl" ></portlet:actionURL>

<liferay-ui:error exception="<%=PrincipalException.class %>" message="delete-file-principal-exception"></liferay-ui:error>
<liferay-ui:error key="generic-error" message="generic-error-msg"></liferay-ui:error>

<aui:button href="<%=runJobUrl %>" value="run-job"/>
<liferay-ui:search-container 
	emptyResultsMessage="no-entries-were-found"
	iteratorURL="<%= iteratorURL %>"
	deltaConfigurable="true"
	>
	 <liferay-ui:search-container-results
                results="<%= UnusedFileLocalServiceUtil.getUnusedFilesByCompanyAndState(themeDisplay.getCompanyId(), false,  searchContainer.getStart(), searchContainer.getEnd()) %>"
                total="<%=UnusedFileLocalServiceUtil.countUnusedFilesByCompanyAndState(themeDisplay.getCompanyId(), false)%>"
        />
        <liferay-ui:search-container-row
                className="com.liferay.dl.cleaner.model.UnusedFile"
                keyProperty="unusedFileId" modelVar="unusedFile" escapedModel="<%= true %>">
                <liferay-ui:search-container-column-text name="fileEntryId"  property="fileEntryId">
                </liferay-ui:search-container-column-text>	
                <liferay-ui:search-container-column-text name="title" property="dlFileTitle" >
                	
                </liferay-ui:search-container-column-text>
                 <liferay-ui:search-container-column-text name="comment" property="comment" >
                	
                </liferay-ui:search-container-column-text>
				<liferay-ui:search-container-column-jsp
					align="right"
					path="/html/row_action.jsp"
				/>
        </liferay-ui:search-container-row>
        <liferay-ui:search-iterator />
</liferay-ui:search-container>