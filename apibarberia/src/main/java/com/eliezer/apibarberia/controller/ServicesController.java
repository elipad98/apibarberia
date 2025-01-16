package com.eliezer.apibarberia.controller;

import java.util.List;
import java.util.Optional;

import com.eliezer.apibarberia.entity.Service;
import com.eliezer.apibarberia.service.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ServicesController {
	@Autowired
	private ServicesService services;
	@GetMapping("/services")
	public List<Service> getServices() {
		return services.listServices();
	}
	@PostMapping("/services")
	public ResponseEntity<String> insertService(@RequestBody Service service) {
		return services.insertService(service)
				?ResponseEntity.ok("Service inserted successfully")
				:ResponseEntity.badRequest().body("Service already exist");
	}

	@DeleteMapping("/services/{id}")
	public ResponseEntity<String> deleteService(@PathVariable Integer id){
		return Optional.of(id)
				.filter(services::deleteService)
				.map(deletedId -> ResponseEntity.ok("Service removed susseffully"))
				.orElseGet(()->ResponseEntity.notFound().build());
	}
	@PutMapping("/services/{id}")
	public ResponseEntity<String> modifyService(@PathVariable Integer id, @RequestBody Service service){
		return services.modifyService(id,service);
	}

}
