package com.incture.ship;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/ship")
public class ShipmentController {

	@Autowired
	ShipmentRepository shipmentRepository;
	
	@GetMapping("/")
	public List<Shipment> getShipmentDetails() {
		return shipmentRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Shipment> getShipmentDetailsById(@PathVariable Long id) {
		try {
			Optional<Shipment> getShipment = shipmentRepository.findById(id);
			return new ResponseEntity<>(getShipment.get(), HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/")
	public ResponseEntity<Shipment> placeOrderToShipment(@RequestBody Shipment shipment) {
		try {
			Shipment newShipment = shipmentRepository.save(shipment);
			return new ResponseEntity<>(newShipment, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Shipment> orderDelivered(@PathVariable Long id, @RequestBody Shipment shipment) {
		Optional<Shipment> getShipment = shipmentRepository.findById(id);
		if(getShipment.isPresent()) {
			Shipment updateShipment = getShipment.get();
			updateShipment.setDeliveredDate(shipment.getDeliveredDate());
			return new ResponseEntity<>(shipmentRepository.save(updateShipment), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteShipment(@PathVariable Long id) {
		try {
			shipmentRepository.deleteById(id);
			return new ResponseEntity<>("Deleted", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

}
