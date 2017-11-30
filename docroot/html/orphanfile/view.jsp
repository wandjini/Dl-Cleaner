
<%@include file="/html/init.jsp" %>

<liferay-portlet:renderURL varImpl="iteratorURL">
</liferay-portlet:renderURL>
<liferay-portlet:renderURL varImpl="searchReferencedFileURL" >
	<portlet:param name="mvcPath" value="/html/orphanfile/view.jsp"/>
</liferay-portlet:renderURL>
<liferay-ui:error exception="<%=PrincipalException.class %>" message="delete-file-principal-exception"></liferay-ui:error>
<liferay-ui:error key="generic-error" message="generic-error-msg"></liferay-ui:error>
<aui:form action="<%=searchReferencedFileURL %>"  name="fm">
	<liferay-ui:search-container 
		displayTerms="<%= new DisplayTerms(renderRequest) %>"
		emptyResultsMessage="no-entries-were-found"
		iteratorURL="<%= iteratorURL %>"
		deltaConfigurable="true"
		>
		<liferay-ui:search-form
			page="/html/orphanfile/orphanfile_search.jsp"
			servletContext="<%= application %>"
			/>
		
		 <liferay-ui:search-container-results
	                results="<%= WcReferencedFileLocalServiceUtil.getWcReferencedFilesByCompanyIdAndOrphan(themeDisplay.getCompanyId(), orphan,  searchContainer.getStart(), searchContainer.getEnd()) %>"
	                total="<%=WcReferencedFileLocalServiceUtil.countWcReferencedFilesByCompanyIdAndOrphan(themeDisplay.getCompanyId(), orphan)%>"
	        />
	        <liferay-ui:search-container-row
	                className="com.liferay.dl.cleaner.model.WcReferencedFile"
	                keyProperty="wcRefencedFileId" modelVar="wcReferencedFile" escapedModel="<%= true %>">
	                <liferay-ui:search-container-column-text name="articleId"  property="articleId">
	                </liferay-ui:search-container-column-text>	
	                <liferay-ui:search-container-column-text name="type" property="type" >
	                	
	                </liferay-ui:search-container-column-text>
	                <liferay-ui:search-container-column-text name="referencedFileId" property="dlFileUuId" >
	                	
	                </liferay-ui:search-container-column-text>
					<liferay-ui:search-container-column-text name="orphan" >
	                	<%= wcReferencedFile.isOrphan() ? "YES" :"NO"%>
	                </liferay-ui:search-container-column-text>
	        </liferay-ui:search-container-row>
	        <liferay-ui:search-iterator />
	</liferay-ui:search-container>
</aui:form>