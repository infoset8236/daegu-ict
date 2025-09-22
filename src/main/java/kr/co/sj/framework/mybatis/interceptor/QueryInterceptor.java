package kr.co.sj.framework.mybatis.interceptor;

import kr.co.sj.framework.utils.BeanFinder;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.*;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author whalesoft
 * @date 2020.08.28
 *
 */
@Intercepts({
		@Signature(
				type = Executor.class,
				method = "update",
				args = {MappedStatement.class, Object.class}
		),
		@Signature(
				type = Executor.class,
				method = "query",
				args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}
		),
		@Signature(
				type = Executor.class,
				method = "query",
				args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}
		)
})
public class QueryInterceptor implements Interceptor {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	private static String WORK_REASON = "work_reason";

	private Map<String, Object> afterUpdateDate = new LinkedHashMap<>();

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		Object proceed = null;
		if(RequestContextHolder.getRequestAttributes() != null) {
			HttpServletRequest request = BeanFinder.getHttpServletRequest();
	
			Object[] args = invocation.getArgs();
			MappedStatement ms = (MappedStatement) args[0];
	
			// 로그기록 쿼리는 pass
			if (StringUtils.contains(ms.getId(), "kr.co.sj.app.cms.workingLog.WorkingLogDao")) {
				return invocation.proceed();
			}
	
			Object param = (Object) args[1];
			BoundSql boundSql = ms.getBoundSql(param);
			String sql = boundSql.getSql();
	
			try {
				String[] methodPath = ms.getId().split("\\.");
				String beanName = methodPath[methodPath.length - 2];
				beanName = ms.getId().substring(0, ms.getId().lastIndexOf(".") - 3) + "Service";
				// beanName = ms.getId().substring(0, ms.getId().lastIndexOf(".") );
				Class<?> clazz = Class.forName(beanName);
				String methodName = methodPath[methodPath.length - 1];
				Method[] methods = clazz.getDeclaredMethods();
				Method method = null;
				WorkingLogger annotation = null;
				for (Method m : methods) {
					if (methodName.equals(m.getName())) {
						annotation = (WorkingLogger) m.getAnnotation(WorkingLogger.class);
						if (annotation != null) {
							method = m;
							break;
						}
					}
				}
	
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (proceed == null) {
					proceed = invocation.proceed();
				}
			}
		} else {
			proceed = invocation.proceed();
		}

		return proceed;
	}

	/**
	 * @author whalesoft
	 * @date 2020.09.02
	 *
	 * @param param
	 * @return
	 *
	 */
	private String getWorkReason(Object param, BoundSql boundSql) {
		if (param instanceof Integer || param instanceof Long || param instanceof Float || param instanceof Double || param instanceof String || param instanceof Map) {
			return null;
		}

		Class<? extends Object> paramClass = param.getClass();
		Class<? extends Object> superclass = paramClass.getSuperclass();

		Field field = null;
		// PagingUtils
		try {
			// BeanUtils
			Class<?> superSuperclass = superclass.getSuperclass();
			field = superSuperclass.getDeclaredField(WORK_REASON);
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
		}
		if (field == null) {
			return "";
		}
		field.setAccessible(true);
		Object valueObject;
		String value = "NULL";
		try {
			valueObject = field.get(param);
			if (valueObject != null) {
				value = valueObject.toString();
			}
		} catch (IllegalArgumentException e1) {
			// TODO Auto-generated catch block
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
		}

		return value;

	}

	private String getMappedQuery(Object param, BoundSql boundSql, String sql, MappedStatement ms) throws NoSuchFieldException, IllegalAccessException {
		if (!afterUpdateDate.isEmpty()) {
			afterUpdateDate = new LinkedHashMap<>();
		}
		if (param instanceof Integer || param instanceof Long || param instanceof Float || param instanceof Double) {
			sql = sql.replaceFirst("\\?", param.toString());
		} else if (param instanceof String) {
			sql = sql.replaceFirst("\\?", "'" + param + "'");
		} else if (param instanceof Map) {
			List<ParameterMapping> paramMapping = boundSql.getParameterMappings();
			TypeHandlerRegistry typeHandlerRegistry = ms.getConfiguration().getTypeHandlerRegistry();
			Class<? extends Object> paramClass = param.getClass();
			for (ParameterMapping mapping : paramMapping) {
				if (mapping.getMode() != ParameterMode.OUT) {
					// ArrayList
					Object value;
					String propValue = mapping.getProperty();
					if (boundSql.hasAdditionalParameter(propValue)) {
						value = boundSql.getAdditionalParameter(propValue);
					} else if (paramClass == null) {
						value = null;
					} else if (typeHandlerRegistry.hasTypeHandler(paramClass.getClass())) {
						value = paramClass;
					} else {
						MetaObject metaObject = ms.getConfiguration().newMetaObject(paramClass);
						value = metaObject.getValue(propValue);
					}
					if (value == null) {
						continue;
					}
					if (value instanceof String) {
						sql = sql.replaceFirst("\\?", "'" + value + "'");
					} else {
						sql = sql.replaceFirst("\\?", value.toString());
					}
				} else {
					// Map
					String propValue = mapping.getProperty();
					@SuppressWarnings ("rawtypes")
					Object value = ((Map) param).get(propValue);
					if (value == null) {
						continue;
					}
					if (value instanceof String) {
						sql = sql.replaceFirst("\\?", "'" + value + "'");
					} else {
						sql = sql.replaceFirst("\\?", value.toString());
					}
				}

			}
		} else {
			List<ParameterMapping> paramMapping = boundSql.getParameterMappings();
			Class<? extends Object> paramClass = param.getClass();
			Class<? extends Object> superclass = paramClass.getSuperclass();
			for (ParameterMapping mapping : paramMapping) {
				String propValue = mapping.getProperty();
				Field field = null;
				try {
					field = paramClass.getDeclaredField(propValue);
				} catch (Exception e) {
					try {
						// PagingUtils
						field = superclass.getDeclaredField(propValue);
					} catch (Exception e2) {
						// BeanUtils
						Class<?> superclass2 = superclass.getSuperclass();
						field = superclass2.getDeclaredField(propValue);
					}
				}
				field.setAccessible(true);
				Class<?> javaType = mapping.getJavaType();
				if (String.class == javaType) {
					sql = sql.replaceFirst("\\?", "'" + field.get(param) + "'");
					afterUpdateDate.put(propValue, field.get(param));
				} else {
					Object valueObject = field.get(param);
					String value = "NULL";
					try {
						value = valueObject.toString();
					} catch (NullPointerException e) {} catch (Exception e) {}
					sql = sql.replaceFirst("\\?", "'" + value + "'");

					afterUpdateDate.put(propValue, valueObject);
					// sql = sql.replaceFirst("\\?", field.get(param).toString());
				}
			}
		}
		sql = sql.replaceAll("(\\t\\t)", "");
		return sql;
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {

	}

	private String updateData(WorkingLogger annotation, String sql, JdbcTemplate jdbcTemplate) {
		String work_result = "";
		String tableName = "NONE";
		String where = "";

		if (StringUtils.isNotEmpty(annotation.tableName()) && !"NONE".equals(annotation.tableName())) {
			try {
				String sqlToLowerCase = sql.toLowerCase();

				tableName = annotation.tableName();

				if (sqlToLowerCase.contains("where")) {
					where = sql.substring(sqlToLowerCase.indexOf("where"));
				}

				StringBuilder fields = new StringBuilder();
				Iterator<String> iter = afterUpdateDate.keySet().iterator();
				while (iter.hasNext()) {
					fields.append(iter.next()).append(iter.hasNext() == true ? "," : "");
				}

				String beforeUpdateSql = "SELECT " + fields + " FROM " + tableName + " " + where;

				LinkedHashMap<String, Object> beforeUpdateData = (LinkedHashMap<String, Object>) jdbcTemplate.queryForMap(beforeUpdateSql);
				LinkedHashMap<String, Object> LowerbeforeUpdateData = new LinkedHashMap<>();

				// 소문자 변환
				for (Map.Entry<String, Object> entry : beforeUpdateData.entrySet()) {
					String lowerCaseKey = entry.getKey().toLowerCase();
					LowerbeforeUpdateData.put(lowerCaseKey, entry.getValue());
				}

				List<String> dataList = new ArrayList<>();

				dataList.add("before=" + LowerbeforeUpdateData);
				dataList.add("after=" + afterUpdateDate);

				work_result = dataList.toString();
			} catch (Exception e) {
				return work_result;
			}
		} else {
			return afterUpdateDate.toString();
		}


		return work_result;
	}
}
