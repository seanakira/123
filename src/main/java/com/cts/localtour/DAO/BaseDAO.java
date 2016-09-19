package com.cts.localtour.DAO;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.persistence.UniqueConstraint;

//import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Projections;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

//import com.hillpool.czbbb.model.Product2CarModelRelationItem;

/**
 * @author wudw
 * 2014年3月18日 上午11:49:03
 */
@Repository
public class BaseDAO<T>{
	@Autowired
	private HibernateTemplate hibernateTemplate;
	@Autowired
	protected SessionFactory sessionFactory;

	public T add(T t) throws Exception {
		hibernateTemplate.save(t);
		return  t;
	}

	public void update(T t) throws Exception {
		hibernateTemplate.update(t);
	}

	
	public T merge(T t) throws Exception {
		hibernateTemplate.merge(t);
		return t;
	}
	
	public void delete(T t) throws Exception {
		hibernateTemplate.delete(t);
	}

	
	public T get(Class<T> entityClass, Serializable id) {
		T ret = (T) hibernateTemplate.get(entityClass, id);
		return ret;
	}
	
	public void deleteById(Serializable id) throws Exception {
		T ret = this.get(this.getEntityClass(), id);
		if (ret != null){
			delete(ret);
		}
	}
	
	
//	@Override
//	public List<T> findAll() throws Exception {
//		String hql = "from "+this.getEntityClassName();
//		return this.queryForList(hql, null).getResultList();
//		
//	}
//	@Override
//	public PageList<T> findAll(PageBean pageBean) throws Exception {
//		String hql = "from "+this.getEntityClassName();
//		return (PageList<T>)this.queryForList(hql, pageBean);
//		
//	}
	@SuppressWarnings("unchecked")
	
	public T findById(Serializable id) throws Exception {
		return (T)hibernateTemplate.get(getEntityClassName(), id);
		
	}
//	public T findForObject(final T entity){
//		PageList<T> pageList = findForList(entity, null);
//		if(pageList != null && pageList.getResultList() != null && !pageList.getResultList().isEmpty()){
//			return pageList.get(0);
//		}
//		return null;
//	}
//	@SuppressWarnings("unchecked")
//	public PageList<T> findForList(final T entity, final PageBean pageBean){
//		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(entity.getClass()).add(Example.create(entity));
//		List<?> list = null;
//		if(!PageBean.isEmpty(pageBean)) {
//			list = hibernateTemplate.findByCriteria(detachedCriteria, pageBean.getLimit(), pageBean.getOffset());
//			Criteria criteria = detachedCriteria.getExecutableCriteria(hibernateTemplate.getSessionFactory().getCurrentSession());
//			Long totalCount = (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
//			pageBean.setTotalCount(totalCount);
//		} else {
//			list =hibernateTemplate.findByCriteria(detachedCriteria);
//		}
//		return new PageList<T>((List<T>)list, pageBean);
//	}
	
	// 
	
//	/**
//	 * 单对象查询
//
//	 * @param hql
//	 * @param objects
//	 * @return
//	 * 2014年3月21日 下午2:08:26
//	 */
//	protected T queryForObject(final String hql, final Object ... objects){
//		PageList<T> pageList = this.queryForList(hql, null, null, objects);
//		if(pageList != null && pageList.isEmpty()){
//			return pageList.get(0);
//		}
//		return null;
//	}

	/**
	* 执行查询结果集

	* @param hql
	* @param page
	* @param pageSize
	* @param objects
	* @return
	* 2014年3月21日 下午2:08:09
	*/
//	protected PageList<T> queryForList(final String hql, final PageBean pageBean, final Object ... objects ){
//		return hibernateTemplate.execute(new HibernateCallback<PageList<T>>() {
//			@SuppressWarnings("unchecked")
//			@Override
//			public PageList<T> doInHibernate(Session session) throws HibernateException {
//				Query createQuery = createQuery(session, hql, objects);
//				List<T> list = null;
//				if(pageBean != null){
//					createQuery.setFirstResult(pageBean.getLimit());   
//					createQuery.setMaxResults(pageBean.getOffset());  
//					list = createQuery.list();
//					String countHql = null;
//					if(StringUtils.containsIgnoreCase(hql, "from")){
//						countHql = "select count(0) "+StringUtils.substring(hql, StringUtils.indexOfIgnoreCase(hql, "from"));
//					}
//					Query countQuery = createQuery(session, countHql, objects);
//					List<Long> countList = countQuery.list();
//					Long totalCount = 0L;
//					if(countList != null && countList.size() > 0){
//						totalCount =countList.get(0);
//					}
//					pageBean.setTotalCount(totalCount);;
//				} else {
//					list = createQuery.list();
//				}
//				return new PageList<T>(list, pageBean);
//			}
//			
//		});
//	}

	/**
	* 执行查询以外的操作
	* @param hql
	* @param objs
	* 2014年3月21日 上午11:42:19
	*/
	protected Integer executeUpdate(final String hql, final Object ... objects){
		return hibernateTemplate.execute(new HibernateCallback<Integer>() {
			@Override
			public Integer doInHibernate(Session session) throws HibernateException {
				Query createQuery = createQuery(session, hql, objects);
				return createQuery.executeUpdate();
			}
		});
	}
	/**
	* 得到泛型中的实体类型

	* @return
	* 2014年3月18日 下午2:32:35
	*/
	protected Class<T> getEntityClass(){
		@SuppressWarnings("unchecked")
		Class<T> entityClass = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		return entityClass;
	}
	/**
	* @Author: Charles
	* @Description: 获取表主键类型
	* @param clazz
	* @return Type:
	*/
	public Type getPkType() {
		ClassMetadata meta = hibernateTemplate.getSessionFactory().getClassMetadata(getEntityClass());
		return meta.getIdentifierType();
	}
	/**
	* 获取主键名

	* @return
	* 2014年3月21日 下午2:42:49
	*/
	public String getPkColunmName(){
		ClassMetadata meta = hibernateTemplate.getSessionFactory().getClassMetadata(getEntityClass());
		return meta.getIdentifierPropertyName();
	}

/**
	* 获取实体类型名

	* @return
	* 2014年3月18日 下午2:33:01
	*/
	protected String getEntityClassName() {
		ClassMetadata meta = hibernateTemplate.getSessionFactory().getClassMetadata(getEntityClass());
		return meta.getEntityName();
	}
	/**
	* 返回设置好参数的查询对象

	* @param query
	* @param objects
	* 2014年3月21日 下午2:07:56
	*/
	private Query createQuery(Session session,String hql, Object ... objects) {
		Query query = session.createQuery(hql);
		if (objects != null){
			for (int i = 0; i < objects.length; i++) {
				query.setParameter(i, objects[i]);
			}
		}
		return query;
	}

	@SuppressWarnings("unchecked")
	public List<T> find(String hql, String[] paramNames, Object[] values) {
		List<T> ret = null;
		ret = (List<T>)hibernateTemplate.findByNamedParam(hql, paramNames,values);
		return ret;
	}

	@SuppressWarnings("unchecked")
	public List find(final String hql, final Map<String, Object> param, final int pageNo,
			final int pageSize) {
		return hibernateTemplate.execute(new HibernateCallback<List>() {
			@Override
			public List doInHibernate(Session session) throws HibernateException {
				String shql=hql;
				if (pageNo == 0&&pageSize!=0) {
					String sql = "select count(*) ";
					shql = sql + hql;
				}
				Query query = session.createQuery(shql);
				if (param != null && param.size() > 0) {
					for (String property : param.keySet()) {
						if (param.get(property) instanceof List){
							query.setParameterList(property, (Collection)param.get(property));
						}else{
							query.setParameter(property,
									param.get(property));
						}
					}
				}
				if (pageNo != 0 && pageSize != 0) {
					query.setFirstResult((pageNo - 1) * pageSize);
					query.setMaxResults(pageSize);
				}
				List list = null;
				try{
					list = query.list();
				}catch (HibernateException e) {
					e.printStackTrace();
					throw e;
				}
				return list;
			}
		});
		
	}
	
	@SuppressWarnings("unchecked")
	public Integer getCounts(final String hql,final Map<String, Object> param){
		return (Integer) hibernateTemplate.execute(new HibernateCallback<Integer>() {

			@Override
			public Integer doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery(hql);
				if (param != null && param.size() > 0) {
					for (String property : param.keySet()) {
						if (param.get(property) instanceof List){
							query.setParameterList(property, (Collection)param.get(property));
						}else{
							query.setParameter(property,
									param.get(property));
						}
					}
				}
				return Integer.parseInt(query.uniqueResult().toString());
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	public List<T> find(final String hql,final Map<String, Object> param) {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		if (param != null && param.size() > 0) {
			for (String property : param.keySet()) {
				query.setParameter(property,param.get(property));
			}
		}
		return query.list();
		/*return hibernateTemplate.execute(new HibernateCallback<List>() {
			@Override
			public List<T> doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery(hql);
				if (param != null && param.size() > 0) {
					for (String property : param.keySet()) {
						query.setParameter(property,param.get(property));
					}
				}
				return query.list();
			}
		});*/

	}
//
//	@Override
//	public List<T> find(String hql) {
//		return this.queryForList(hql, null).getResultList();
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List findHql(String hql) {
//		return this.queryForList(hql, null).getResultList();
//	}

	@SuppressWarnings("unchecked")
	public Object getById(Class c, Serializable id) {
		Object ret = hibernateTemplate.get(c, id);
		return ret;
	}

	@SuppressWarnings("unchecked")
	public List findHql(final String hql, final Map<String, Object> param) {
		return hibernateTemplate.execute(new HibernateCallback<List>() {
			@Override
			public List<T> doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery(hql);
				if (param != null && param.size() > 0) {
					for (String property : param.keySet()) {
						query.setParameter(property,param.get(property));
					}
				}
				return query.list();
			}
		});
	}

	@SuppressWarnings("unchecked")
	public int updateHql(String hql) {
		return executeUpdate(hql, null);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	public List<T> find(String hql) {
		List<T> list = this.find(hql, null);
		return list;
	}


	public List findHql(String hql) {
		List list = findHql(hql, null);
		return list;
	}
	
	public int updateByParam(String hql, Object... objects){
		return executeUpdate(hql, objects);
	}
	
	@SuppressWarnings("rawtypes")
	public List getAllByString(final String hql, final Object...objects){
		return hibernateTemplate.execute(new HibernateCallback<List>() {
			@SuppressWarnings("unchecked")
			public List<T> doInHibernate(Session session) throws HibernateException {
				Query createQuery = createQuery(session, hql, objects);
				return createQuery.list();
			}
		});
	}
	
	@SuppressWarnings("rawtypes")
	public List getAllBystringLimit(final String hql, final int maxResults, final Object...objects){
		return hibernateTemplate.execute(new HibernateCallback<List>() {
			public List<?> doInHibernate(Session session) throws HibernateException {
				Query createQuery = createQuery(session, hql, objects);
				createQuery.setMaxResults(maxResults);
				return createQuery.list();
			}
		});
	}

	@SuppressWarnings({ "rawtypes" })
	public void deleteByString(String hql,Object...objects) {
		hibernateTemplate.bulkUpdate(hql, objects);
	}

}