package com.incture.ship;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Shipment")
public class Shipment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Long orderId;
	
	private Date shipmentDate;
	
	private Date estimatedDeliveryDate;
	
	private Date deliveredDate;


	public Shipment(Long id, Long orderId, Date shipmentDate, Date estimatedDeliveryDate, Date deliveredDate) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.shipmentDate = shipmentDate;
		this.estimatedDeliveryDate = estimatedDeliveryDate;
		this.deliveredDate = deliveredDate;
	}

	public Shipment() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Date getEstimatedDeliveryDate() {
		return estimatedDeliveryDate;
	}

	public void setEstimatedDeliveryDate(Date estimatedDeliveryDate) {
		this.estimatedDeliveryDate = estimatedDeliveryDate;
	}

	public Date getDeliveredDate() {
		return deliveredDate;
	}

	public void setDeliveredDate(Date deliveredDate) {
		this.deliveredDate = deliveredDate;
	}

	public Date getShipmentDate() {
		return shipmentDate;
	}

	public void setShipmentDate(Date shipmentDate) {
		this.shipmentDate = shipmentDate;
	}


	@Override
	public String toString() {
		return "Shipment [id=" + id + ", orderId=" + orderId + ", shipmentDate=" + shipmentDate
				+ ", estimatedDeliveryDate=" + estimatedDeliveryDate + ", deliveredDate=" + deliveredDate + "]";
	}
	
}
