package com.eliezer.apibarberia.controller;

import java.util.List;

import com.eliezer.apibarberia.Service;
import com.eliezer.apibarberia.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
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
        return services.listaServicios();
    }
	@PostMapping("/services")
	public void insertService(@RequestBody Service service) {
			 services.insertService(service);
	}

	@DeleteMapping("/services")
	public void deleteService(@RequestBody Integer id){
		services.deleteService(id);
	}
	@PutMapping("/services/{id}")
	public void modifyService(@PathVariable Integer id, @RequestBody Service service){
		 services.modifyService(service.getId(),service);
	}

}
