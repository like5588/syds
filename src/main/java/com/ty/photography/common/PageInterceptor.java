package com.ty.photography.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.executor.parameter.DefaultParameterHandler;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;

@Intercepts({ @Signature(method = "prepare", type = StatementHandler.class, args = { Connection.class }) })
public class PageInterceptor implements Interceptor {

	private String databaseType;// 数据库类型，不同的数据库有不同的分页方法

	public Object intercept(Invocation invocation) throws Throwable {
		final RoutingStatementHandler handler = (RoutingStatementHandler) invocation
				.getTarget();
		final StatementHandler delegate = (StatementHandler) ReflectUtil
				.getFieldValue(handler, "delegate");
		final BoundSql boundSql = delegate.getBoundSql();
		final Object obj = boundSql.getParameterObject();

		if (obj instanceof SearchPageUtil) {
			final SearchPageUtil page = (SearchPageUtil) obj;
			// 通过反射获取delegate父类BaseStatementHandler的mappedStatement属性
			MappedStatement mappedStatement = (MappedStatement) ReflectUtil
					.getFieldValue(delegate, "mappedStatement");
			// 拦截到的prepare方法参数是一个Connection对象
			Connection connection = (Connection) invocation.getArgs()[0];
			// 获取当前要执行的Sql语句，也就是我们直接在Mapper映射语句中写的Sql语句
			final String sql = boundSql.getSql();
			// 给当前的page参数对象设置总记录数
			this.setTotalRecord(page, mappedStatement, connection);
			// 获取分页Sql语句
			final String pageSql = this.getPageSql(page, sql);
			// 利用反射设置当前BoundSql对应的sql属性为我们建立好的分页Sql语句
			ReflectUtil.setFieldValue(boundSql, "sql", pageSql);
		}
		return invocation.proceed();
	}

	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	public void setProperties(Properties properties) {
		this.databaseType = properties.getProperty("databaseType");
	}

	private String getPageSql(SearchPageUtil page, String sql) {
		final StringBuffer sqlBuffer = new StringBuffer(sql);
		if ("mysql".equalsIgnoreCase(databaseType)) {
			return getMysqlPageSql(page, sqlBuffer);
		} else if ("oracle".equalsIgnoreCase(databaseType)) {
			return getOraclePageSql(page, sqlBuffer);
		}
		return sqlBuffer.toString();
	}

	private String getMysqlPageSql(SearchPageUtil page, StringBuffer sqlBuffer) {
		// 计算第一条记录的位置，Mysql中记录的位置是从0开始的。
		// int offset = (page.getPage().getPageIndex() - 1) *
		// page.getPageSize();
		sqlBuffer.append(" limit ").append(page.getStartRow()).append(",")
				.append(page.getPageSize());
		return sqlBuffer.toString();
	}

	private String getOraclePageSql(SearchPageUtil page, StringBuffer sqlBuffer) {
		// 计算第一条记录的位置，Oracle分页是通过rownum进行的，而rownum是从1开始的
		final int offset = (page.getPage().getPageIndex() - 1)
				* page.getPageSize() + 1;
		sqlBuffer.insert(0, "select u.*, rownum r from (")
				.append(") u where rownum < ")
				.append(offset + page.getPageSize());
		sqlBuffer.insert(0, "select * from (").append(") where r >= ")
				.append(offset);
		// 上面的Sql语句拼接之后大概是这个样子：
		// select * from (select u.*, rownum r from (select * from t_user) u
		// where rownum < 31) where r >= 16
		return sqlBuffer.toString();
	}

	private void setTotalRecord(SearchPageUtil page,
			MappedStatement mappedStatement, Connection connection) {
		// 获取对应的BoundSql，这个BoundSql其实跟我们利用StatementHandler获取到的BoundSql是同一个对象。
		// delegate里面的boundSql也是通过mappedStatement.getBoundSql(paramObj)方法获取到的。
		final BoundSql boundSql = mappedStatement.getBoundSql(page);
		// 获取到我们自己写在Mapper映射语句中对应的Sql语句
		final String sql = boundSql.getSql();
		// 通过查询Sql语句获取到对应的计算总记录数的sql语句
		final String countSql = this.getCountSql(sql);
		// 通过BoundSql获取对应的参数映射
		final List<ParameterMapping> parameterMappings = boundSql
				.getParameterMappings();
		// 利用Configuration、查询记录数的Sql语句countSql、参数映射关系parameterMappings和参数对象page建立查询记录数对应的BoundSql对象。
		final BoundSql countBoundSql = new BoundSql(
				mappedStatement.getConfiguration(), countSql,
				parameterMappings, page);
		// 通过mappedStatement、参数对象page和BoundSql对象countBoundSql建立一个用于设定参数的ParameterHandler对象
		final ParameterHandler parameterHandler = new DefaultParameterHandler(
				mappedStatement, page, countBoundSql);
		// 通过connection建立一个countSql对应的PreparedStatement对象。
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = connection.prepareStatement(countSql);
			// 通过parameterHandler给PreparedStatement对象设置参数
			parameterHandler.setParameters(pstmt);
			// 之后就是执行获取总记录数的Sql语句和获取结果了。
			rs = pstmt.executeQuery();
			if (rs.next()) {
				final int totalRecord = rs.getInt(1);
				// 给当前的参数page对象设置总记录数
				page.getPage().setRowTotal(totalRecord);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private String getCountSql(String sql) {
		int index = sql.toLowerCase().indexOf("from");
		return "select count(*) " + sql.substring(index);
	}

}