package com.eliezer.apibarberia;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class ServicesService {
	
	@Autowired
	private ServicesRepository serviceRepo;
	
	public List<Service> listaServicios(){
		return serviceRepo.findAll();
	}
	
	public void insertService(@RequestParam Service service) {
		serviceRepo.save(service);
	}
	public void deleteService(@RequestParam Integer id){ serviceRepo.deleteById(id);}

	public void modifyService(@RequestParam Integer id,Service service){
		Optional<Service> bdService = serviceRepo.findById(id);
		if(bdService.isPresent()){
			Service serviceOptional=bdService.get();
			System.out.println(serviceOptional);
			serviceOptional.setDescription(service.getDescription());
			serviceOptional.setDuration(service.getDuration());
			serviceOptional.setName(service.getName());
			serviceOptional.setPrice(service.getPrice());
			serviceRepo.save(serviceOptional);

		}


	}
}
