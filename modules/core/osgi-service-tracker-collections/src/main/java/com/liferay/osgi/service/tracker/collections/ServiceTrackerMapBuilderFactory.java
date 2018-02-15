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

package com.liferay.osgi.service.tracker.collections;

import com.liferay.osgi.service.tracker.collections.internal.DefaultServiceTrackerCustomizer;
import com.liferay.osgi.service.tracker.collections.internal.map.MultiValueServiceTrackerBucketFactory;
import com.liferay.osgi.service.tracker.collections.internal.map.ServiceTrackerMapImpl;
import com.liferay.osgi.service.tracker.collections.internal.map.SingleValueServiceTrackerBucketFactory;
import com.liferay.osgi.service.tracker.collections.map.PropertyServiceReferenceMapper;
import com.liferay.osgi.service.tracker.collections.map.ServiceReferenceMapper;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerBucketFactory;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMap;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMapListener;

import java.util.Comparator;
import java.util.List;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

/**
 * @author Carlos Sierra Andrés
 */
public class ServiceTrackerMapBuilderFactory {

	public interface Final<K, SR, NR, R> {

		public ServiceTrackerMap<K, R> build();

		public Final<K, SR, NR, R> open();

		public Final<K, SR, NR, R> withListener(
			ServiceTrackerMapListener<K, NR, R> serviceTrackerMapListener);

	}

	public interface Mapper<K, SR, NR, R> {

		public Final<K, SR, NR, List<NR>> multiValue();

		public Final<K, SR, NR, List<NR>> multiValue(
			Comparator<ServiceReference<SR>> comparator);

		public Final<K, SR, NR, NR> singleValue();

		public Final<K, SR, NR, NR> singleValue(
			Comparator<ServiceReference<SR>> comparator);

		public <R> Final<K, SR, NR, R> withFactory(
			ServiceTrackerBucketFactory<SR, NR, R> bucketFactory);

	}

	public interface Selector<SR, NR> {

		public <K> Mapper<K, SR, NR, ?> map(
			ServiceReferenceMapper<K, SR> mapper);

		public Mapper<String, SR, NR, NR> map(String property);

		public <NR> Selector<SR, NR> newSelector(
			ServiceTrackerCustomizer<SR, NR> customizer);

		public Selector<SR, NR> newSelector(String filter);

	}

	public interface SelectorBuilder {

		public static <T> Selector<T, T> clazz(
			BundleContext bundleContext, Class<T> clazz) {

			return new SelectorImpl<>(bundleContext, clazz, null, null);
		}

		public static <T> Selector<T, T> clazz(
			BundleContext bundleContext, Class<T> clazz, String filter) {

			return new SelectorImpl<>(bundleContext, clazz, filter, null);
		}

		public static Selector<Object, Object> clazz(
			BundleContext bundleContext, String className) {

			return new SelectorImpl<>(
				bundleContext, Object.class, "(objectClass=" + className + ")",
				null);
		}

		public static Selector<?, ?> filter(
			BundleContext bundleContext, String filter) {

			return new SelectorImpl<>(
				bundleContext, Object.class, filter, null);
		}

	}

	private static class FinalImpl<K, SR, NR, R>
		implements Final<K, SR, NR, R> {

		public FinalImpl(
			BundleContext bundleContext, Class<SR> clazz,
			ServiceTrackerCustomizer<SR, NR> customizer, String filter,
			ServiceReferenceMapper<K, SR> mapper,
			ServiceTrackerBucketFactory<SR, NR, R> bucketFactory,
			ServiceTrackerMapListener<K, NR, R>
				serviceTrackerMapListener, boolean open) {

			_bundleContext = bundleContext;
			_clazz = clazz;

			if (customizer == null) {
				_customizer = new DefaultServiceTrackerCustomizer(
					_bundleContext);
			}
			else {
				_customizer = customizer;
			}

			_filter = filter;
			_mapper = mapper;
			_bucketFactory = bucketFactory;
			_serviceTrackerMapListener = serviceTrackerMapListener;
			_open = open;
		}

		@Override
		public ServiceTrackerMap<K, R> build() {
			ServiceTrackerMap<K, R> serviceTrackerMap =
				new ServiceTrackerMapImpl<>(
					_bundleContext, _clazz, _filter, _mapper, _customizer,
					_bucketFactory, _serviceTrackerMapListener);

			if (_open) {
				serviceTrackerMap.open();
			}

			return serviceTrackerMap;
		}

		@Override
		public Final<K, SR, NR, R> open() {
			return new FinalImpl<>(
				_bundleContext, _clazz, _customizer, _filter, _mapper,
				_bucketFactory, _serviceTrackerMapListener, true);
		}

		@Override
		public Final<K, SR, NR, R> withListener(
			ServiceTrackerMapListener<K, NR, R> serviceTrackerMapListener) {

			return new FinalImpl<>(
				_bundleContext, _clazz, _customizer, _filter, _mapper,
				_bucketFactory, serviceTrackerMapListener, _open);
		}

		private final ServiceTrackerBucketFactory<SR, NR, R> _bucketFactory;
		private final BundleContext _bundleContext;
		private final Class<SR> _clazz;
		private final ServiceTrackerCustomizer<SR, NR> _customizer;
		private final String _filter;
		private final ServiceReferenceMapper<K, SR> _mapper;
		private final boolean _open;
		private final ServiceTrackerMapListener<K, NR, R>
			_serviceTrackerMapListener;

	}

	private static class MapperImpl<K, SR, NR, R>
		implements Mapper<K, SR, NR, R> {

		public MapperImpl(
			BundleContext bundleContext, Class<SR> clazz,
			ServiceTrackerCustomizer<SR, NR> customizer,
			ServiceReferenceMapper<K, SR> mapper, String filter) {

			_bundleContext = bundleContext;
			_clazz = clazz;
			_customizer = customizer;
			_mapper = mapper;
			_filter = filter;
		}

		@Override
		public Final<K, SR, NR, List<NR>> multiValue() {
			return new FinalImpl<>(
				_bundleContext, _clazz, _customizer, _filter, _mapper,
				new MultiValueServiceTrackerBucketFactory<>(), null, false);
		}

		@Override
		public Final<K, SR, NR, List<NR>> multiValue(
			Comparator<ServiceReference<SR>> comparator) {

			return new FinalImpl<>(
				_bundleContext, _clazz, _customizer, _filter, _mapper,
				new MultiValueServiceTrackerBucketFactory<>(comparator), null,
				false);
		}

		@Override
		public Final<K, SR, NR, NR> singleValue() {
			return new FinalImpl<>(
				_bundleContext, _clazz, _customizer, _filter, _mapper,
				new SingleValueServiceTrackerBucketFactory<>(), null, false);
		}

		@Override
		public Final<K, SR, NR, NR> singleValue(
			Comparator<ServiceReference<SR>> comparator) {

			return new FinalImpl<>(
				_bundleContext, _clazz, _customizer, _filter, _mapper,
				new SingleValueServiceTrackerBucketFactory<>(comparator), null,
				false);
		}

		@Override
		public <R> Final<K, SR, NR, R> withFactory(
			ServiceTrackerBucketFactory<SR, NR, R> bucketFactory) {

			return new FinalImpl<>(
				_bundleContext, _clazz, _customizer, _filter, _mapper,
				bucketFactory, null, false);
		}

		private final BundleContext _bundleContext;
		private final Class<SR> _clazz;
		private final ServiceTrackerCustomizer<SR, NR> _customizer;
		private final String _filter;
		private final ServiceReferenceMapper<K, SR> _mapper;

	}

	private static class SelectorImpl<T, NR> implements Selector<T, NR> {

		@Override
		public <K> Mapper<K, T, NR, ?> map(
			ServiceReferenceMapper<K, T> mapper) {

			return new MapperImpl<>(
				_bundleContext, _clazz, _customizer, mapper, null);
		}

		@Override
		public Mapper<String, T, NR, NR> map(String property) {
			if (_filter == null) {
				return new MapperImpl<>(
					_bundleContext, _clazz, _customizer,
					new PropertyServiceReferenceMapper<>(property),
					"(" + property + "=*)");
			}
			else {
				return new MapperImpl<>(
					_bundleContext, _clazz, _customizer,
					new PropertyServiceReferenceMapper<>(property), _filter);
			}
		}

		@Override
		public <NR> Selector<T, NR> newSelector(
			ServiceTrackerCustomizer<T, NR> customizer) {

			return new SelectorImpl<>(
				_bundleContext, _clazz, _filter, customizer);
		}

		@Override
		public Selector<T, NR> newSelector(String filter) {
			return new SelectorImpl<>(
				_bundleContext, _clazz, filter, _customizer);
		}

		private SelectorImpl(
			BundleContext bundleContext, Class<T> clazz, String filter,
			ServiceTrackerCustomizer<T, NR> customizer) {

			_bundleContext = bundleContext;
			_clazz = clazz;
			_filter = filter;
			_customizer = customizer;
		}

		private final BundleContext _bundleContext;
		private final Class<T> _clazz;
		private final ServiceTrackerCustomizer<T, NR> _customizer;
		private final String _filter;

	}

}