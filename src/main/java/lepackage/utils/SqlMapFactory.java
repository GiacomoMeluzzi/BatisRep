package lepackage.utils;

import java.io.Reader;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlMapFactory {

	private SqlSessionFactory sqlSessionFactory;

	private SqlSession sqlSession;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static ThreadLocal<SqlMapFactory> THREAD_LOCAL = new ThreadLocal() {
		protected SqlMapFactory initialValue() {
			return new SqlMapFactory();
		}
	};

	private final String RESOURCE = "mybatis-config.xml";

	public static SqlMapFactory instance() {
		return (SqlMapFactory) THREAD_LOCAL.get();
	}

	private SqlMapFactory() {
	}

	private SqlSessionFactory getSqlSessionFactory() {
		if (this.sqlSessionFactory == null) {
			try {
				Reader reader = Resources.getResourceAsReader(RESOURCE);
				SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
				this.sqlSessionFactory = builder.build(reader);
			} catch (Exception var3) {
				Exception e = var3;
				e.printStackTrace();
			}
		}
		return this.sqlSessionFactory;
	}

	public SqlSession openSession() {
		if (this.sqlSession == null) {
			this.sqlSession = ((SqlMapFactory) THREAD_LOCAL.get()).getSqlSessionFactory().openSession();

		}
		return this.sqlSession;
	}

	public void commitSession() {
		if (this.sqlSession != null) {
			((SqlMapFactory) THREAD_LOCAL.get()).sqlSession.commit();
		}
	}

	public void closeSession() {
		if ((null != ((SqlMapFactory) THREAD_LOCAL.get()).sqlSession)) {
			((SqlMapFactory) THREAD_LOCAL.get()).sqlSession.close();
			((SqlMapFactory) THREAD_LOCAL.get()).sqlSession = null;
		}
	}

	public <T> T getMapper(Class<T> type) {
		return ((SqlMapFactory) THREAD_LOCAL.get()).sqlSession.getMapper(type);
	}

	public void rollbackSession() {
		if (instance().sqlSession != null) {
			instance().sqlSession.rollback();
		}
	}

}