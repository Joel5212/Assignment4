package domain;

import java.util.*;

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
@Table(name = "customer")
public class Customer
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "customer_id")
	private int customerId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "phone")
	private String phoneNum;
	
	@Column(name="email")
	private String email;

	@OneToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="address_id")
	private Address address;
	
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
     private Set<Order> orders = new HashSet<Order>();

	public Customer(String name, String phoneNum, String email, Address address) {
		super();
		this.name = name;
		this.phoneNum = phoneNum;
		this.email = email;
		this.address= address;
	}
	
	public Customer() {
		
	}
	
	public Set<Order> getOrders() {
		return orders;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getPhone() {
		return phoneNum;
	}

	public void setPhone(String phone) {
		this.phoneNum = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name= name;
	}
	
	

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", phone=" + phoneNum + ", email=" + email + "]";
	}
}