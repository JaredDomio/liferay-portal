/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.layout.page.template.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;

import com.liferay.layout.page.template.model.LayoutPageTemplateCollection;
import com.liferay.layout.page.template.model.LayoutPageTemplateCollectionModel;
import com.liferay.layout.page.template.model.LayoutPageTemplateCollectionSoap;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Serializable;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The base model implementation for the LayoutPageTemplateCollection service. Represents a row in the &quot;LayoutPageTemplateCollection&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link LayoutPageTemplateCollectionModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link LayoutPageTemplateCollectionImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LayoutPageTemplateCollectionImpl
 * @see LayoutPageTemplateCollection
 * @see LayoutPageTemplateCollectionModel
 * @generated
 */
@JSON(strict = true)
@ProviderType
public class LayoutPageTemplateCollectionModelImpl extends BaseModelImpl<LayoutPageTemplateCollection>
	implements LayoutPageTemplateCollectionModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a layout page template collection model instance should use the {@link LayoutPageTemplateCollection} interface instead.
	 */
	public static final String TABLE_NAME = "LayoutPageTemplateCollection";
	public static final Object[][] TABLE_COLUMNS = {
			{ "layoutPageTemplateCollectionId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "name", Types.VARCHAR },
			{ "description", Types.VARCHAR },
			{ "type_", Types.INTEGER }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("layoutPageTemplateCollectionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("description", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("type_", Types.INTEGER);
	}

	public static final String TABLE_SQL_CREATE = "create table LayoutPageTemplateCollection (layoutPageTemplateCollectionId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,name VARCHAR(75) null,description STRING null,type_ INTEGER)";
	public static final String TABLE_SQL_DROP = "drop table LayoutPageTemplateCollection";
	public static final String ORDER_BY_JPQL = " ORDER BY layoutPageTemplateCollection.name ASC";
	public static final String ORDER_BY_SQL = " ORDER BY LayoutPageTemplateCollection.name ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.layout.page.template.service.util.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.layout.page.template.model.LayoutPageTemplateCollection"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.layout.page.template.service.util.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.layout.page.template.model.LayoutPageTemplateCollection"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.layout.page.template.service.util.ServiceProps.get(
				"value.object.column.bitmask.enabled.com.liferay.layout.page.template.model.LayoutPageTemplateCollection"),
			true);
	public static final long GROUPID_COLUMN_BITMASK = 1L;
	public static final long NAME_COLUMN_BITMASK = 2L;
	public static final long TYPE_COLUMN_BITMASK = 4L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static LayoutPageTemplateCollection toModel(
		LayoutPageTemplateCollectionSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		LayoutPageTemplateCollection model = new LayoutPageTemplateCollectionImpl();

		model.setLayoutPageTemplateCollectionId(soapModel.getLayoutPageTemplateCollectionId());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setName(soapModel.getName());
		model.setDescription(soapModel.getDescription());
		model.setType(soapModel.getType());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<LayoutPageTemplateCollection> toModels(
		LayoutPageTemplateCollectionSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<LayoutPageTemplateCollection> models = new ArrayList<LayoutPageTemplateCollection>(soapModels.length);

		for (LayoutPageTemplateCollectionSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.layout.page.template.service.util.ServiceProps.get(
				"lock.expiration.time.com.liferay.layout.page.template.model.LayoutPageTemplateCollection"));

	public LayoutPageTemplateCollectionModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _layoutPageTemplateCollectionId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setLayoutPageTemplateCollectionId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _layoutPageTemplateCollectionId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return LayoutPageTemplateCollection.class;
	}

	@Override
	public String getModelClassName() {
		return LayoutPageTemplateCollection.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("layoutPageTemplateCollectionId",
			getLayoutPageTemplateCollectionId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("name", getName());
		attributes.put("description", getDescription());
		attributes.put("type", getType());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long layoutPageTemplateCollectionId = (Long)attributes.get(
				"layoutPageTemplateCollectionId");

		if (layoutPageTemplateCollectionId != null) {
			setLayoutPageTemplateCollectionId(layoutPageTemplateCollectionId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		Integer type = (Integer)attributes.get("type");

		if (type != null) {
			setType(type);
		}
	}

	@JSON
	@Override
	public long getLayoutPageTemplateCollectionId() {
		return _layoutPageTemplateCollectionId;
	}

	@Override
	public void setLayoutPageTemplateCollectionId(
		long layoutPageTemplateCollectionId) {
		_layoutPageTemplateCollectionId = layoutPageTemplateCollectionId;
	}

	@JSON
	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_columnBitmask |= GROUPID_COLUMN_BITMASK;

		if (!_setOriginalGroupId) {
			_setOriginalGroupId = true;

			_originalGroupId = _groupId;
		}

		_groupId = groupId;
	}

	public long getOriginalGroupId() {
		return _originalGroupId;
	}

	@JSON
	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	@JSON
	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_userId = userId;
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return "";
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	@JSON
	@Override
	public String getUserName() {
		if (_userName == null) {
			return "";
		}
		else {
			return _userName;
		}
	}

	@Override
	public void setUserName(String userName) {
		_userName = userName;
	}

	@JSON
	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	@JSON
	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public boolean hasSetModifiedDate() {
		return _setModifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_setModifiedDate = true;

		_modifiedDate = modifiedDate;
	}

	@JSON
	@Override
	public String getName() {
		if (_name == null) {
			return "";
		}
		else {
			return _name;
		}
	}

	@Override
	public void setName(String name) {
		_columnBitmask = -1L;

		if (_originalName == null) {
			_originalName = _name;
		}

		_name = name;
	}

	public String getOriginalName() {
		return GetterUtil.getString(_originalName);
	}

	@JSON
	@Override
	public String getDescription() {
		if (_description == null) {
			return "";
		}
		else {
			return _description;
		}
	}

	@Override
	public void setDescription(String description) {
		_description = description;
	}

	@JSON
	@Override
	public int getType() {
		return _type;
	}

	@Override
	public void setType(int type) {
		_columnBitmask |= TYPE_COLUMN_BITMASK;

		if (!_setOriginalType) {
			_setOriginalType = true;

			_originalType = _type;
		}

		_type = type;
	}

	public int getOriginalType() {
		return _originalType;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
			LayoutPageTemplateCollection.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public LayoutPageTemplateCollection toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (LayoutPageTemplateCollection)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		LayoutPageTemplateCollectionImpl layoutPageTemplateCollectionImpl = new LayoutPageTemplateCollectionImpl();

		layoutPageTemplateCollectionImpl.setLayoutPageTemplateCollectionId(getLayoutPageTemplateCollectionId());
		layoutPageTemplateCollectionImpl.setGroupId(getGroupId());
		layoutPageTemplateCollectionImpl.setCompanyId(getCompanyId());
		layoutPageTemplateCollectionImpl.setUserId(getUserId());
		layoutPageTemplateCollectionImpl.setUserName(getUserName());
		layoutPageTemplateCollectionImpl.setCreateDate(getCreateDate());
		layoutPageTemplateCollectionImpl.setModifiedDate(getModifiedDate());
		layoutPageTemplateCollectionImpl.setName(getName());
		layoutPageTemplateCollectionImpl.setDescription(getDescription());
		layoutPageTemplateCollectionImpl.setType(getType());

		layoutPageTemplateCollectionImpl.resetOriginalValues();

		return layoutPageTemplateCollectionImpl;
	}

	@Override
	public int compareTo(
		LayoutPageTemplateCollection layoutPageTemplateCollection) {
		int value = 0;

		value = getName().compareTo(layoutPageTemplateCollection.getName());

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LayoutPageTemplateCollection)) {
			return false;
		}

		LayoutPageTemplateCollection layoutPageTemplateCollection = (LayoutPageTemplateCollection)obj;

		long primaryKey = layoutPageTemplateCollection.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return ENTITY_CACHE_ENABLED;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
		LayoutPageTemplateCollectionModelImpl layoutPageTemplateCollectionModelImpl =
			this;

		layoutPageTemplateCollectionModelImpl._originalGroupId = layoutPageTemplateCollectionModelImpl._groupId;

		layoutPageTemplateCollectionModelImpl._setOriginalGroupId = false;

		layoutPageTemplateCollectionModelImpl._setModifiedDate = false;

		layoutPageTemplateCollectionModelImpl._originalName = layoutPageTemplateCollectionModelImpl._name;

		layoutPageTemplateCollectionModelImpl._originalType = layoutPageTemplateCollectionModelImpl._type;

		layoutPageTemplateCollectionModelImpl._setOriginalType = false;

		layoutPageTemplateCollectionModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<LayoutPageTemplateCollection> toCacheModel() {
		LayoutPageTemplateCollectionCacheModel layoutPageTemplateCollectionCacheModel =
			new LayoutPageTemplateCollectionCacheModel();

		layoutPageTemplateCollectionCacheModel.layoutPageTemplateCollectionId = getLayoutPageTemplateCollectionId();

		layoutPageTemplateCollectionCacheModel.groupId = getGroupId();

		layoutPageTemplateCollectionCacheModel.companyId = getCompanyId();

		layoutPageTemplateCollectionCacheModel.userId = getUserId();

		layoutPageTemplateCollectionCacheModel.userName = getUserName();

		String userName = layoutPageTemplateCollectionCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			layoutPageTemplateCollectionCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			layoutPageTemplateCollectionCacheModel.createDate = createDate.getTime();
		}
		else {
			layoutPageTemplateCollectionCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			layoutPageTemplateCollectionCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			layoutPageTemplateCollectionCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		layoutPageTemplateCollectionCacheModel.name = getName();

		String name = layoutPageTemplateCollectionCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			layoutPageTemplateCollectionCacheModel.name = null;
		}

		layoutPageTemplateCollectionCacheModel.description = getDescription();

		String description = layoutPageTemplateCollectionCacheModel.description;

		if ((description != null) && (description.length() == 0)) {
			layoutPageTemplateCollectionCacheModel.description = null;
		}

		layoutPageTemplateCollectionCacheModel.type = getType();

		return layoutPageTemplateCollectionCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{layoutPageTemplateCollectionId=");
		sb.append(getLayoutPageTemplateCollectionId());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", name=");
		sb.append(getName());
		sb.append(", description=");
		sb.append(getDescription());
		sb.append(", type=");
		sb.append(getType());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(34);

		sb.append("<model><model-name>");
		sb.append(
			"com.liferay.layout.page.template.model.LayoutPageTemplateCollection");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>layoutPageTemplateCollectionId</column-name><column-value><![CDATA[");
		sb.append(getLayoutPageTemplateCollectionId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>groupId</column-name><column-value><![CDATA[");
		sb.append(getGroupId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userName</column-name><column-value><![CDATA[");
		sb.append(getUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(getName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>description</column-name><column-value><![CDATA[");
		sb.append(getDescription());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>type</column-name><column-value><![CDATA[");
		sb.append(getType());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = LayoutPageTemplateCollection.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			LayoutPageTemplateCollection.class
		};
	private long _layoutPageTemplateCollectionId;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private String _name;
	private String _originalName;
	private String _description;
	private int _type;
	private int _originalType;
	private boolean _setOriginalType;
	private long _columnBitmask;
	private LayoutPageTemplateCollection _escapedModel;
}