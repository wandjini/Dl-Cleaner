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

package com.liferay.dl.cleaner.service.base;

import com.liferay.dl.cleaner.model.LostFile;
import com.liferay.dl.cleaner.service.LostFileLocalService;
import com.liferay.dl.cleaner.service.persistence.LostFilePersistence;
import com.liferay.dl.cleaner.service.persistence.UnusedFilePersistence;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.bean.IdentifiableBean;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.PersistedModel;
import com.liferay.portal.service.BaseLocalServiceImpl;
import com.liferay.portal.service.PersistedModelLocalServiceRegistryUtil;
import com.liferay.portal.service.persistence.UserPersistence;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the lost file local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.dl.cleaner.service.impl.LostFileLocalServiceImpl}.
 * </p>
 *
 * @author guywandji
 * @see com.liferay.dl.cleaner.service.impl.LostFileLocalServiceImpl
 * @see com.liferay.dl.cleaner.service.LostFileLocalServiceUtil
 * @generated
 */
public abstract class LostFileLocalServiceBaseImpl extends BaseLocalServiceImpl
	implements LostFileLocalService, IdentifiableBean {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.liferay.dl.cleaner.service.LostFileLocalServiceUtil} to access the lost file local service.
	 */

	/**
	 * Adds the lost file to the database. Also notifies the appropriate model listeners.
	 *
	 * @param lostFile the lost file
	 * @return the lost file that was added
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public LostFile addLostFile(LostFile lostFile) throws SystemException {
		lostFile.setNew(true);

		return lostFilePersistence.update(lostFile);
	}

	/**
	 * Creates a new lost file with the primary key. Does not add the lost file to the database.
	 *
	 * @param lostFileId the primary key for the new lost file
	 * @return the new lost file
	 */
	@Override
	public LostFile createLostFile(long lostFileId) {
		return lostFilePersistence.create(lostFileId);
	}

	/**
	 * Deletes the lost file with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param lostFileId the primary key of the lost file
	 * @return the lost file that was removed
	 * @throws PortalException if a lost file with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public LostFile deleteLostFile(long lostFileId)
		throws PortalException, SystemException {
		return lostFilePersistence.remove(lostFileId);
	}

	/**
	 * Deletes the lost file from the database. Also notifies the appropriate model listeners.
	 *
	 * @param lostFile the lost file
	 * @return the lost file that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public LostFile deleteLostFile(LostFile lostFile) throws SystemException {
		return lostFilePersistence.remove(lostFile);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(LostFile.class,
			clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public List dynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return lostFilePersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.dl.cleaner.model.impl.LostFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return lostFilePersistence.findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.dl.cleaner.model.impl.LostFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return lostFilePersistence.findWithDynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	 * Returns the number of rows that match the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows that match the dynamic query
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery)
		throws SystemException {
		return lostFilePersistence.countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Returns the number of rows that match the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows that match the dynamic query
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection) throws SystemException {
		return lostFilePersistence.countWithDynamicQuery(dynamicQuery,
			projection);
	}

	@Override
	public LostFile fetchLostFile(long lostFileId) throws SystemException {
		return lostFilePersistence.fetchByPrimaryKey(lostFileId);
	}

	/**
	 * Returns the lost file with the primary key.
	 *
	 * @param lostFileId the primary key of the lost file
	 * @return the lost file
	 * @throws PortalException if a lost file with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public LostFile getLostFile(long lostFileId)
		throws PortalException, SystemException {
		return lostFilePersistence.findByPrimaryKey(lostFileId);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException, SystemException {
		return lostFilePersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the lost files.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.dl.cleaner.model.impl.LostFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of lost files
	 * @param end the upper bound of the range of lost files (not inclusive)
	 * @return the range of lost files
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<LostFile> getLostFiles(int start, int end)
		throws SystemException {
		return lostFilePersistence.findAll(start, end);
	}

	/**
	 * Returns the number of lost files.
	 *
	 * @return the number of lost files
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int getLostFilesCount() throws SystemException {
		return lostFilePersistence.countAll();
	}

	/**
	 * Updates the lost file in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param lostFile the lost file
	 * @return the lost file that was updated
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public LostFile updateLostFile(LostFile lostFile) throws SystemException {
		return lostFilePersistence.update(lostFile);
	}

	/**
	 * Returns the lost file local service.
	 *
	 * @return the lost file local service
	 */
	public com.liferay.dl.cleaner.service.LostFileLocalService getLostFileLocalService() {
		return lostFileLocalService;
	}

	/**
	 * Sets the lost file local service.
	 *
	 * @param lostFileLocalService the lost file local service
	 */
	public void setLostFileLocalService(
		com.liferay.dl.cleaner.service.LostFileLocalService lostFileLocalService) {
		this.lostFileLocalService = lostFileLocalService;
	}

	/**
	 * Returns the lost file remote service.
	 *
	 * @return the lost file remote service
	 */
	public com.liferay.dl.cleaner.service.LostFileService getLostFileService() {
		return lostFileService;
	}

	/**
	 * Sets the lost file remote service.
	 *
	 * @param lostFileService the lost file remote service
	 */
	public void setLostFileService(
		com.liferay.dl.cleaner.service.LostFileService lostFileService) {
		this.lostFileService = lostFileService;
	}

	/**
	 * Returns the lost file persistence.
	 *
	 * @return the lost file persistence
	 */
	public LostFilePersistence getLostFilePersistence() {
		return lostFilePersistence;
	}

	/**
	 * Sets the lost file persistence.
	 *
	 * @param lostFilePersistence the lost file persistence
	 */
	public void setLostFilePersistence(LostFilePersistence lostFilePersistence) {
		this.lostFilePersistence = lostFilePersistence;
	}

	/**
	 * Returns the unused file local service.
	 *
	 * @return the unused file local service
	 */
	public com.liferay.dl.cleaner.service.UnusedFileLocalService getUnusedFileLocalService() {
		return unusedFileLocalService;
	}

	/**
	 * Sets the unused file local service.
	 *
	 * @param unusedFileLocalService the unused file local service
	 */
	public void setUnusedFileLocalService(
		com.liferay.dl.cleaner.service.UnusedFileLocalService unusedFileLocalService) {
		this.unusedFileLocalService = unusedFileLocalService;
	}

	/**
	 * Returns the unused file remote service.
	 *
	 * @return the unused file remote service
	 */
	public com.liferay.dl.cleaner.service.UnusedFileService getUnusedFileService() {
		return unusedFileService;
	}

	/**
	 * Sets the unused file remote service.
	 *
	 * @param unusedFileService the unused file remote service
	 */
	public void setUnusedFileService(
		com.liferay.dl.cleaner.service.UnusedFileService unusedFileService) {
		this.unusedFileService = unusedFileService;
	}

	/**
	 * Returns the unused file persistence.
	 *
	 * @return the unused file persistence
	 */
	public UnusedFilePersistence getUnusedFilePersistence() {
		return unusedFilePersistence;
	}

	/**
	 * Sets the unused file persistence.
	 *
	 * @param unusedFilePersistence the unused file persistence
	 */
	public void setUnusedFilePersistence(
		UnusedFilePersistence unusedFilePersistence) {
		this.unusedFilePersistence = unusedFilePersistence;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.service.CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.service.CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	/**
	 * Returns the resource local service.
	 *
	 * @return the resource local service
	 */
	public com.liferay.portal.service.ResourceLocalService getResourceLocalService() {
		return resourceLocalService;
	}

	/**
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		com.liferay.portal.service.ResourceLocalService resourceLocalService) {
		this.resourceLocalService = resourceLocalService;
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public com.liferay.portal.service.UserLocalService getUserLocalService() {
		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(
		com.liferay.portal.service.UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	/**
	 * Returns the user remote service.
	 *
	 * @return the user remote service
	 */
	public com.liferay.portal.service.UserService getUserService() {
		return userService;
	}

	/**
	 * Sets the user remote service.
	 *
	 * @param userService the user remote service
	 */
	public void setUserService(
		com.liferay.portal.service.UserService userService) {
		this.userService = userService;
	}

	/**
	 * Returns the user persistence.
	 *
	 * @return the user persistence
	 */
	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	/**
	 * Sets the user persistence.
	 *
	 * @param userPersistence the user persistence
	 */
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	public void afterPropertiesSet() {
		Class<?> clazz = getClass();

		_classLoader = clazz.getClassLoader();

		PersistedModelLocalServiceRegistryUtil.register("com.liferay.dl.cleaner.model.LostFile",
			lostFileLocalService);
	}

	public void destroy() {
		PersistedModelLocalServiceRegistryUtil.unregister(
			"com.liferay.dl.cleaner.model.LostFile");
	}

	/**
	 * Returns the Spring bean ID for this bean.
	 *
	 * @return the Spring bean ID for this bean
	 */
	@Override
	public String getBeanIdentifier() {
		return _beanIdentifier;
	}

	/**
	 * Sets the Spring bean ID for this bean.
	 *
	 * @param beanIdentifier the Spring bean ID for this bean
	 */
	@Override
	public void setBeanIdentifier(String beanIdentifier) {
		_beanIdentifier = beanIdentifier;
	}

	@Override
	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		if (contextClassLoader != _classLoader) {
			currentThread.setContextClassLoader(_classLoader);
		}

		try {
			return _clpInvoker.invokeMethod(name, parameterTypes, arguments);
		}
		finally {
			if (contextClassLoader != _classLoader) {
				currentThread.setContextClassLoader(contextClassLoader);
			}
		}
	}

	protected Class<?> getModelClass() {
		return LostFile.class;
	}

	protected String getModelClassName() {
		return LostFile.class.getName();
	}

	/**
	 * Performs an SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) throws SystemException {
		try {
			DataSource dataSource = lostFilePersistence.getDataSource();

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql, new int[0]);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = com.liferay.dl.cleaner.service.LostFileLocalService.class)
	protected com.liferay.dl.cleaner.service.LostFileLocalService lostFileLocalService;
	@BeanReference(type = com.liferay.dl.cleaner.service.LostFileService.class)
	protected com.liferay.dl.cleaner.service.LostFileService lostFileService;
	@BeanReference(type = LostFilePersistence.class)
	protected LostFilePersistence lostFilePersistence;
	@BeanReference(type = com.liferay.dl.cleaner.service.UnusedFileLocalService.class)
	protected com.liferay.dl.cleaner.service.UnusedFileLocalService unusedFileLocalService;
	@BeanReference(type = com.liferay.dl.cleaner.service.UnusedFileService.class)
	protected com.liferay.dl.cleaner.service.UnusedFileService unusedFileService;
	@BeanReference(type = UnusedFilePersistence.class)
	protected UnusedFilePersistence unusedFilePersistence;
	@BeanReference(type = com.liferay.counter.service.CounterLocalService.class)
	protected com.liferay.counter.service.CounterLocalService counterLocalService;
	@BeanReference(type = com.liferay.portal.service.ResourceLocalService.class)
	protected com.liferay.portal.service.ResourceLocalService resourceLocalService;
	@BeanReference(type = com.liferay.portal.service.UserLocalService.class)
	protected com.liferay.portal.service.UserLocalService userLocalService;
	@BeanReference(type = com.liferay.portal.service.UserService.class)
	protected com.liferay.portal.service.UserService userService;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private String _beanIdentifier;
	private ClassLoader _classLoader;
	private LostFileLocalServiceClpInvoker _clpInvoker = new LostFileLocalServiceClpInvoker();
}