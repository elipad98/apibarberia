package com.eliezer.apibarberia.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.eliezer.apibarberia.entity.Service;
import com.eliezer.apibarberia.repository.ServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class ServicesService {

	@Autowired
	private ServicesRepository serviceRepo;

	public List<Service> listServices(){
		List<Service> services = serviceRepo.findAll();
		return Optional.ofNullable(services).orElse(Collections.emptyList());
	}

	public Boolean insertService(@RequestParam Service service) {
		return Optional.ofNullable(service)
				.filter(serviceOpt ->!serviceRepo.existsByName(serviceOpt.getName()))
				.map(serviceRepo::save)
				.isPresent();
	}
	public Boolean deleteService(@RequestParam Integer id){
		return Optional.of(id)
				.filter(serviceRepo::existsById)
				.map(ExistId->{
					serviceRepo.deleteById(ExistId);
					return true;
				})
				.orElse(false);
	}

	public ResponseEntity<String> modifyService(@RequestParam Integer id, Service service){
		Optional<Service> bdService = serviceRepo.findById(id);
		if (bdService.isPresent()){
			Service existingService = bdService.get();
			existingService.setDescription(service.getDescription());
			existingService.setDuration(service.getDuration());
			existingService.setName(service.getName());
			existingService.setPrice(service.getPrice());
			serviceRepo.save(existingService);
			return ResponseEntity.ok("Service Modify sucessfully");

		}else {return ResponseEntity.notFound().build();}
	}
}

