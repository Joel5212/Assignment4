package domain;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "orderobj")
public class Order
{
	@Id
	@Column(name = "order_id")
	private int orderId;
	
	@Column(name = "date")
	private String date;
	
	@Column(name="item")
	private String item;
	
	@Column(name="price")
	private double price;

	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="customer_id")
	private Customer customer;

	public Order(int orderId, double price, String item, String date, Customer customer) {
		super();
		
		this.orderId = orderId;
		
		this.date = date;
		
		this.item = item;
		
		this.price = price;
		
		this.customer = customer;
	}
	
	public Order()
	{
		
	}
	
	public int getOrderId() {
		return orderId;
	}

	public void setOrderID(int orderId) {
		this.orderId = orderId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", date=" + date + ", item=" + item + ", price=" + price + "]";
	}

}