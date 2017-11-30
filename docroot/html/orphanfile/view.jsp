<%@include file="/html/init.jsp" %>
<liferay-portlet:renderURL varImpl="iteratorURL"></liferay-portlet:renderURL>

<liferay-ui:error exception="<%=PrincipalException.class %>" message="delete-file-principal-exception"></liferay-ui:error>
<liferay-ui:error key="generic-error" message="generic-error-msg"></liferay-ui:error>

<liferay-ui:search-container 
	emptyResultsMessage="no-entries-were-found"
	iteratorURL="<%= iteratorURL %>"
	deltaConfigurable="true"
	>
	 <liferay-ui:search-container-results
                results="<%= WcReferencedFileLocalServiceUtil.getWcReferencedFilesByCompanyIdAndOrphan(themeDisplay.getCompanyId(), true,  searchContainer.getStart(), searchContainer.getEnd()) %>"
                total="<%=WcReferencedFileLocalServiceUtil.countWcReferencedFilesByCompanyIdAndOrphan(themeDisplay.getCompanyId(), true)%>"
        />
        <liferay-ui:search-container-row
                className="com.liferay.dl.cleaner.model.WcReferencedFile"
                keyProperty="wcReferencedId" modelVar="wcReferencedFile" escapedModel="<%= true %>">
                <liferay-ui:search-container-column-text name="articleId"  property="articleId">
                </liferay-ui:search-container-column-text>	
                <liferay-ui:search-container-column-text name="type" property="type" >
                	
                </liferay-ui:search-container-column-text>
                 <liferay-ui:search-container-column-text name="referencedFileId" property="dlFileUuId" >
                	
                </liferay-ui:search-container-column-text>
				
        </liferay-ui:search-container-row>
        <liferay-ui:search-iterator />
</liferay-ui:search-container>