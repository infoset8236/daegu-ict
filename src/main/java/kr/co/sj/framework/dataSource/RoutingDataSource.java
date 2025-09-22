package kr.co.sj.framework.dataSource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
* @author sidnancy
*/
public class RoutingDataSource extends AbstractRoutingDataSource {
	@Override
	protected Object determineCurrentLookupKey() {
		return ContextHolder.getDataSourceType();
	}
}