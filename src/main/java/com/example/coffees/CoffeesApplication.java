package com.example.coffees;

import com.example.coffees.model.*;
import com.example.coffees.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class CoffeesApplication implements CommandLineRunner {

	private final BrewedCoffeeRepository brewedCoffeeRepository;
	private final ClientRepository clientRepository;
	private final CoffeeRepository coffeeRepository;
	private final ShopOrderRepository shopOrderRepository;
	private final SyrupRepository syrupRepository;

	public CoffeesApplication(BrewedCoffeeRepository brewedCoffeeRepository, ClientRepository clientRepository, CoffeeRepository coffeeRepository, ShopOrderRepository shopOrderRepository, SyrupRepository syrupRepository) {
		this.brewedCoffeeRepository = brewedCoffeeRepository;
		this.clientRepository = clientRepository;
		this.coffeeRepository = coffeeRepository;
		this.shopOrderRepository = shopOrderRepository;
		this.syrupRepository = syrupRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(CoffeesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//Adds coffees
		Coffee coffee1 = new Coffee("N45", 2.77);
		Coffee coffee2 = new Coffee("Geisha", 1.20);

		coffeeRepository.save(coffee1);
		coffeeRepository.save(coffee2);

		//Adds syrups
		Syrup syrup1 = new Syrup("Caramel", 0.2);
		Syrup syrup2 = new Syrup("Chocolate", 0.3);

		syrupRepository.save(syrup1);
		syrupRepository.save(syrup2);

		//Adds client
		Client client = new Client("Gigel");

		clientRepository.save(client);

		//Adds brewed coffee
		BrewedCoffee brewedCoffee = new BrewedCoffee("N45 + caramel", false, coffee1, syrup1);

		brewedCoffeeRepository.save(brewedCoffee);

		//Adds order
		ShopOrder order = new ShopOrder();
		order.setCoffeeList(Arrays.asList(brewedCoffee));
		order.setClient(client);

		shopOrderRepository.save(order);

//		brewedCoffee.setOrderList(Arrays.asList(order));
	}
}
