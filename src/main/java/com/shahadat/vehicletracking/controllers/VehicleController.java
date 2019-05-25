package com.shahadat.vehicletracking.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shahadat.vehicletracking.exception.ResourceNotFoundException;
import com.shahadat.vehicletracking.model.Vehicle;
import com.shahadat.vehicletracking.repository.VehicleRepository;

@RestController
@RequestMapping("/api")
public class VehicleController {

	@Autowired
	private VehicleRepository vehicleRepository;
	
	@PostMapping("/vehicle")
	public synchronized Vehicle createVehicle(@Valid @RequestBody Vehicle vehicle) {
		return vehicleRepository.save(vehicle);
		
	}
	
	@GetMapping("/vehicles")
	public List<Vehicle> getAllVehicles(){
		return vehicleRepository.findAll();
		
	}
	@GetMapping("/vehicles/{vehicle_id}")
	public ResponseEntity<Vehicle> getUserByVehicleId(@PathVariable(value="vehicle_id") Long vehicleId)
	throws ResourceNotFoundException {
		Vehicle vehicle = vehicleRepository.findById(vehicleId)
				.orElseThrow(() -> new ResourceNotFoundException("Vehicle not found on : " + vehicleId));
		
		return ResponseEntity.ok().body(vehicle);
	}
	
	@PutMapping("/vehicles/{vehicle_id}")
	public ResponseEntity<Vehicle> updateVehicle(@PathVariable(value="vehicle_id") Long vehicleId,
			@Valid @RequestBody Vehicle vehicleDetails) throws ResourceNotFoundException{
		
		Vehicle vehicle = vehicleRepository.findById(vehicleId)
				.orElseThrow(() -> new ResourceNotFoundException("Vehicle not found on : " + vehicleId));
		vehicle.setLat(vehicleDetails.getLat());
		vehicle.setLang(vehicleDetails.getLang());
		vehicle.setTracking_time(new Date());
		final Vehicle updateVehicle = vehicleRepository.save(vehicle);
		return ResponseEntity.ok(updateVehicle);
	}
	
	@DeleteMapping("/vehicles/{vehicle_id}")
	public Map<String, Boolean> deleteVehicle(@PathVariable(value = "vehicle_id") Long vehicleId) throws Exception {
	    Vehicle vehicle =
	        vehicleRepository
	            .findById(vehicleId)
	            .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found on :: " + vehicleId));
	    vehicleRepository.delete(vehicle);
	    Map<String, Boolean> response = new HashMap<>();
	    response.put("deleted", Boolean.TRUE);
	    return response;
	  }
	
}
