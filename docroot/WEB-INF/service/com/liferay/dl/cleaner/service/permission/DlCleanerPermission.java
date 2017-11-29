package com.liferay.dl.cleaner.service.permission;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;

public class DlCleanerPermission {

	private static final String _CLASS_NAME = "com.liferay.dl.cleaner.model";

    public static void check(PermissionChecker permissionChecker, long groupId, String actionId) throws PortalException, SystemException {

        if (!contains(permissionChecker, groupId, actionId)) {
            throw new PrincipalException();
        }
    }
    

    public static boolean contains(PermissionChecker permissionChecker, long groupId, String actionId) throws PortalException,
            SystemException {

		return permissionChecker.hasPermission(
			groupId, _CLASS_NAME, groupId, actionId);

    }
}
