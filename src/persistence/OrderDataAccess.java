package persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import domain.Customer;
import domain.Order;
import domain.Address;


public class OrderDataAccess {
	
	public static boolean addOrder(int orderId, String date, Customer customer, String item, double price)
	{
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Customer.class).addAnnotatedClass(Address.class).addAnnotatedClass(Order.class).buildSessionFactory();
		boolean flag = false;
		Session session = factory.getCurrentSession();
		
		try
		{
			session.beginTransaction();
			
			Customer customerDetails = CustomerDataAccess.searchCustomer(customer.getName());
			
			Order order = new Order(orderId, price, item, date, customerDetails);
			
			session.save(order);
			
			session.save(customer);
			session.getTransaction().commit();
			
			flag = true;
			
		} catch(Exception e)
		{
			 System.out.println("Problem creating session factory");
		     e.printStackTrace();
		} finally {
			factory.close();
		
		}
		return flag;
	}
	
	public static Order searchOrder(int orderId)
	{
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Customer.class).addAnnotatedClass(Order.class).addAnnotatedClass(Address.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		Order order = null;
		
		try
		{
			session.beginTransaction();
			
			order = session.get(Order.class, orderId);
			
			session.getTransaction().commit();
		
		} catch(Exception e)
		{
			 System.out.println("Problem creating session factory");
		     e.printStackTrace();
		} finally {
			factory.close();
		
		}
		return order;
	}
	
	public static boolean updateOrder(int orderId, String orderDate, Customer customer, String item, double price)
	{
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Customer.class).addAnnotatedClass(Order.class).addAnnotatedClass(Address.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		Order order = null;
		boolean flag = false;
		
		try
		{
			session.beginTransaction();
			
			order = session.get(Order.class, orderId);
			
			order.setDate(orderDate);
			order.setCustomer(customer);
			order.setItem(item);
			order.setPrice(price);
			
			session.update(order);
			
			session.getTransaction().commit();
			
			flag = true;
		} catch(Exception e)
		{
			 System.out.println("Problem creating session factory");
		     e.printStackTrace();
		} finally {
			factory.close();
		
		}
		return flag;
	}
	
	public static boolean deleteOrder(int orderId)
	{
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Customer.class).addAnnotatedClass(Order.class).addAnnotatedClass(Address.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		Order order = null;
		boolean flag = false;
		
		try
		{
			session.beginTransaction();
		
			order = session.get(Order.class, orderId);
			
			session.delete(order);
		
			session.getTransaction().commit();
			
			flag = true;
		} catch(Exception e)
		{
			 System.out.println("Problem creating session factory");
		     e.printStackTrace();
		} finally {
			factory.close();
		
		}
		return flag;
	}
	
}
