package com.cookerytech;

import com.cookerytech.domain.Role;
import com.cookerytech.domain.enums.RoleType;
import com.cookerytech.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;


@SpringBootApplication
@EnableScheduling


public class CookerytechApplication {

	public static void main(String[] args) {
		SpringApplication.run(CookerytechApplication.class, args);
	}

	@Component
	@AllArgsConstructor
	class DemoCommandLineRunner implements CommandLineRunner {

		RoleRepository roleRepository;

		@Override
		public void run(String... args) throws Exception {

			if (!roleRepository.findByType(RoleType.ROLE_CUSTOMER).isPresent()) {
				Role roleCustomer = new Role();
				roleCustomer.setType(RoleType.ROLE_CUSTOMER);
				roleRepository.save(roleCustomer);
			}

			if (!roleRepository.findByType(RoleType.ROLE_ADMIN).isPresent()) {
				Role roleAdmin = new Role();
				roleAdmin.setType(RoleType.ROLE_ADMIN);
				roleRepository.save(roleAdmin);
			}

			if (!roleRepository.findByType(RoleType.ROLE_PRODUCT_MANAGER).isPresent()) {
				Role roleAdmin = new Role();
				roleAdmin.setType(RoleType.ROLE_PRODUCT_MANAGER);
				roleRepository.save(roleAdmin);
			}

			if (!roleRepository.findByType(RoleType.ROLE_SALES_SPECIALIST).isPresent()) {
				Role roleAdmin = new Role();
				roleAdmin.setType(RoleType.ROLE_SALES_SPECIALIST);
				roleRepository.save(roleAdmin);
			}

			if (!roleRepository.findByType(RoleType.ROLE_SALES_MANAGER).isPresent()) {
				Role roleAdmin = new Role();
				roleAdmin.setType(RoleType.ROLE_SALES_MANAGER);
				roleRepository.save(roleAdmin);
			}


		}


	}
}

