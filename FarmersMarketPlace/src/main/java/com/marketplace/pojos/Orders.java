package com.marketplace.pojos;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "orders")
public class Orders implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private Integer orderId;

	@Column(name = "payment_status", length = 1)
	private boolean paymentStatus;

	@Column(name = "delivery_status", length = 1)
	private boolean deliveryStatus;

	@OneToMany(mappedBy = "orders",orphanRemoval = true,cascade = CascadeType.REFRESH)
	@JsonIgnore
	private List<OrderDetails> orderDetails = new ArrayList<OrderDetails>();

	@ManyToOne(cascade = CascadeType.REFRESH,optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	@Column(name = "place_order_date")
	private Date placeOrderDate;
	
	@Column(name = "delivery_date")
	private Date deliveryDate;

	public Orders() {
		System.out.println("Orders Constructor invoked");
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer aOrderId) {
		orderId = aOrderId;
	}

	public boolean isPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(boolean aPaymentStatus) {
		paymentStatus = aPaymentStatus;
	}

	public boolean isDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(boolean aDeliveryStatus) {
		deliveryStatus = aDeliveryStatus;
	}

	public List<OrderDetails> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetails> aOrderDetails) {
		orderDetails = aOrderDetails;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User aUser) {
		user = aUser;
	}
	
	
	public Date getPlaceOrderDate() {
		return placeOrderDate;
	}

	public void setPlaceOrderDate(Date placeOrderDate) {
		this.placeOrderDate = placeOrderDate;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	@Override
	public String toString() {
		return "Orders [orderId=" + orderId + ", paymentStatus=" + paymentStatus + ", deliveryStatus=" + deliveryStatus
				+ ", orderDetails=" + orderDetails + ", user=" + user + ", placeOrderDate=" + placeOrderDate
				+ ", deliveryDate=" + deliveryDate + "]";
	}

	

}
