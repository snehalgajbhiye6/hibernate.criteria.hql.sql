package com.app;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;

public class Test {
	private static SessionFactory sf=null;
    Scanner sc=new Scanner(System.in);
	static {
		Configuration con=new Configuration().configure();
		StandardServiceRegistryBuilder builder=new StandardServiceRegistryBuilder();
		builder.applySettings(con.getProperties());
		ServiceRegistry sr=builder.build();
		sf=con.buildSessionFactory(sr);
	}
	@SuppressWarnings("unchecked")
	public void criteriaTest()
	{
		Session s=sf.openSession();
		Criteria cr=s.createCriteria(Employee.class);
		List<Employee>list=cr.list();
		list.forEach(System.out::println);//returns all records
	}
	
	@SuppressWarnings("unchecked")
	public void restictions()
	{
		Session s=sf.openSession();
		Criteria cr=s.createCriteria(Employee.class);
		cr.add(Restrictions.eq("name", "arti")).add(Restrictions.eq("mobile", 91011));//return artis record
		List<Employee> list=cr.list();
		list.forEach(System.out::println);
	}
	public void betweenRestiction() {
		Session session = sf.openSession();
		Criteria cr = session.createCriteria(Employee.class);
		cr.add(Restrictions.between("salary", new Double(40000), new Double(60000)));//all
		//cr.add(Restrictions.lt("salary", new Double(60000)));//less than this salary.
		@SuppressWarnings("unchecked")
		List<Employee> list = cr.list();
		list.forEach(System.out::println);
	}

	public void uniqueRestultRestiction() {
		Session session = sf.openSession();
		Criteria cr = session.createCriteria(Employee.class);
		cr.add(Restrictions.eq("name", "vrushali")).add(Restrictions.eq("mobile", 5678));//returns vrushalis record.
		Employee employee = (Employee) cr.uniqueResult();
		System.out.println(employee);
	}

	public void projectionsTest() {
		Session session = sf.openSession();
		Criteria cr = session.createCriteria(Employee.class);
		cr.setProjection(Projections.max("salary"));//returns max salary
		Double count = (Double) cr.uniqueResult();
		System.out.println(count);

	}

	public void columnWiseProjection() {
		Session session = sf.openSession();
		@SuppressWarnings("unused")
		Criteria cr = session.createCriteria(Employee.class);
		ProjectionList projectionList = Projections.projectionList();
		projectionList.add(Projections.property("name"));
		//projectionList.add(Projections.property("mobile"));
	}
	public static void main(String[] args) {
		Test t=new Test();
		//t.criteriaTest();
		//t.restictions();
		//t.betweenRestiction();
		//t.uniqueRestultRestiction();
		//t.projectionsTest();
		t.columnWiseProjection();
	}

}
