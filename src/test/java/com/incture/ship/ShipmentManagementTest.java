package com.incture.ship;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class ShipmentManagementTest {
	
	@InjectMocks
	private ShipmentController shipmentController;
	
	@Mock
	private ShipmentRepository shipmentRepository;
	
	@Test
	public void getShipmentDetails() {
		
		Shipment shipment = new Shipment(1L,2L,new Date(1676646936357L),new Date(1677303336359L),new Date(1677128008603L));
		List<Shipment> exceptedShipmentDetails = new ArrayList<>();
		exceptedShipmentDetails.add(shipment);
		
		when(shipmentRepository.findAll()).thenReturn(exceptedShipmentDetails);
		
		List<Shipment> actualShipmentDetails = shipmentController.getShipmentDetails();
		
		verify(shipmentRepository, times(1)).findAll();
		
		assertEquals(actualShipmentDetails.size(), exceptedShipmentDetails.size());
		
	}
	
	@Test
	public void getShipmentDetailsById() {
		Shipment exceptedShipmentDetails = new Shipment(1L,2L,new Date(1676646936357L),new Date(1677303336359L),new Date(1677128008603L));
		Long shipmentId = 1L;
		when(shipmentRepository.findById(shipmentId)).thenReturn(Optional.of(exceptedShipmentDetails));
		
		ResponseEntity<Shipment> actualShipmentDetails = shipmentController.getShipmentDetailsById(shipmentId);
		
		verify(shipmentRepository, times(1)).findById(shipmentId);
		
		assertEquals(actualShipmentDetails.getBody(), exceptedShipmentDetails);
		
	}
	
	@Test 
	public void deleteShipment() {
		Shipment exceptedShipmentDetails = new Shipment(1L,2L,new Date(1676646936357L),new Date(1677303336359L),new Date(1677128008603L));
		Long shipmentId = 1L;
		when(shipmentRepository.findById(shipmentId)).thenReturn(Optional.of(exceptedShipmentDetails));
		
		ResponseEntity<Shipment> actualShipmentDetails = shipmentController.getShipmentDetailsById(shipmentId);
		
		verify(shipmentRepository, times(1)).findById(shipmentId);
		assertEquals(actualShipmentDetails.getBody(), exceptedShipmentDetails);
		
		doNothing().when(shipmentRepository).deleteById(shipmentId);
		
		ResponseEntity<String> result = shipmentController.deleteShipment(1L);
		verify(shipmentRepository, times(1)).deleteById(1L);
		assertEquals(HttpStatus.OK, result.getStatusCode());
		assertEquals("Deleted", result.getBody());	
	}
}
