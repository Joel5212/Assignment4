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


public class CustomerDataAccess {
	
	public static boolean addCustomer(String name, String phoneNum, String email, String street, String city, String state, int zipCode)
	{
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Customer.class).addAnnotatedClass(Address.class).addAnnotatedClass(Order.class).buildSessionFactory();
		boolean flag = false;
		Session session = factory.getCurrentSession();
		
		try
		{
			session.beginTransaction();
			
			Address address = new Address(street, city, state, zipCode);
			
			session.save(address);
			
			Customer customer = new Customer(name, phoneNum, email, address);
			
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
	
	public static List<Customer> getListOfCustomers()
	{
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Customer.class).addAnnotatedClass(Address.class).addAnnotatedClass(Order.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		List<Customer> listCustomers = null;
		
		try
		{
			
			session.beginTransaction();
			
			String hql = "FROM Customer";
			Query<Customer> query = session.createQuery(hql);
			listCustomers = query.list();
			
			session.getTransaction().commit();
		
		} catch(Exception e)
		{
			 System.out.println("Problem creating session factory");
		     e.printStackTrace();
		} finally {
			factory.close();
		
		}
		return listCustomers;
	}
	
	public static Customer searchCustomer(String name)
	{
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Customer.class).addAnnotatedClass(Order.class).addAnnotatedClass(Address.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		Customer customer = null;
		
		try
		{
			session.beginTransaction();
			
			List<Customer> customers = getListOfCustomers();
			for(Customer customerVal : customers)
			{
				if(customerVal.getName().equals(name))
				{
					customer = customerVal;
					break;
				}
			}
			session.getTransaction().commit();
		
		} catch(Exception e)
		{
			 System.out.println("Problem creating session factory");
		     e.printStackTrace();
		} finally {
			factory.close();
		
		}
		return customer;
	}
	
	public static boolean updateCustomer(String name, String phoneNum, String email, String street, String city, String state, int zipCode)
	{
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Customer.class).addAnnotatedClass(Order.class).addAnnotatedClass(Address.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		Customer customer = null;
		boolean flag = false;
		
		try
		{
			session.beginTransaction();
			
			customer = searchCustomer(name);
			
			customer.setName(name);
			customer.setPhone(phoneNum);
			customer.setEmail(email);
			
			
			int addressId = customer.getAddress().getAddressId();
			
			Address address = session.get(Address.class, addressId);
			
			address.setCity(city);
			address.setState(state);
			address.setStreet(street);
			address.setZipCode(zipCode);
			
			session.update(customer);
			
			
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
	
	public static boolean deleteCustomer(String name)
	{
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Customer.class).addAnnotatedClass(Order.class).addAnnotatedClass(Address.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		Customer customer = null;
		boolean flag = false;
		
		try
		{
			session.beginTransaction();
		
			customer = searchCustomer(name);
			
			session.delete(customer);
		
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
