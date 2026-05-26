package edu.prz.delivery;

import edu.prz.delivery.orders.domain.foodorder.FoodOrder;
import edu.prz.delivery.orders.domain.foodorder.FoodOrderRepository;
import edu.prz.delivery.orders.domain.foodorder.OrderItem;
import edu.prz.delivery.orders.domain.foodorder.OrderStatus;
import edu.prz.delivery.payments.domain.payment.Payment;
import edu.prz.delivery.payments.domain.payment.PaymentMethod;
import edu.prz.delivery.payments.domain.payment.PaymentRepository;
import edu.prz.delivery.payments.domain.payment.PaymentStatus;
import edu.prz.delivery.restaurants.domain.restaurant.Product;
import edu.prz.delivery.restaurants.domain.restaurant.ProductVariant;
import edu.prz.delivery.restaurants.domain.restaurant.Restaurant;
import edu.prz.delivery.restaurants.domain.restaurant.RestaurantRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DeliveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeliveryApplication.class, args);
	}

	@Bean
	public CommandLineRunner seedDemoData(
			RestaurantRepository restaurantRepository,
			FoodOrderRepository foodOrderRepository,
			PaymentRepository paymentRepository
	) {
		return args -> {
			if (restaurantRepository.count() == 0) {
				// 1. Seed Italian Restaurant
				Restaurant pizzaRes = new Restaurant();
				pizzaRes.setName("Pizzeria Bella Italia");
				pizzaRes.setAddress("Krakowskie Przedmiescie 12, Warszawa");
				pizzaRes.setOpeningHours("12:00 - 23:00");
				pizzaRes.setContactInfo("22-123-45-67");

				Product margherita = new Product();
				margherita.setName("Pizza Margherita");
				margherita.setDescription("Klasyczna pizza neapolitańska z sosem pomidorowym, świeżą mozzarellą i świeżą bazylią");
				margherita.setCategory("Pizza");
				margherita.setPrice(new BigDecimal("36.00"));
				margherita.setAvailable(true);

				ProductVariant standardMarg = new ProductVariant();
				standardMarg.setName("Standardowa (32cm)");
				standardMarg.setPrice(new BigDecimal("36.00"));

				ProductVariant familyMarg = new ProductVariant();
				familyMarg.setName("Rodzinna (45cm)");
				familyMarg.setPrice(new BigDecimal("46.00"));
				margherita.setVariants(List.of(standardMarg, familyMarg));

				Product diavola = new Product();
				diavola.setName("Pizza Diavola");
				diavola.setDescription("Pikantna pizza z sosem pomidorowym, mozzarellą, ostrym salami i oliwą chili");
				diavola.setCategory("Pizza");
				diavola.setPrice(new BigDecimal("44.00"));
				diavola.setAvailable(true);

				ProductVariant standardDiav = new ProductVariant();
				standardDiav.setName("Standardowa (32cm)");
				standardDiav.setPrice(new BigDecimal("44.00"));

				ProductVariant largeDiav = new ProductVariant();
				largeDiav.setName("Duża (45cm)");
				largeDiav.setPrice(new BigDecimal("54.00"));
				diavola.setVariants(List.of(standardDiav, largeDiav));

				pizzaRes.getProducts().add(margherita);
				pizzaRes.getProducts().add(diavola);

				// 2. Seed American Restaurant
				Restaurant burgerRes = new Restaurant();
				burgerRes.setName("Burger House & Grill");
				burgerRes.setAddress("Piotrkowska 89, Lodz");
				burgerRes.setOpeningHours("11:00 - 22:00");
				burgerRes.setContactInfo("42-987-65-43");

				Product classicBurger = new Product();
				classicBurger.setName("Klasyczny Burger Wołowy");
				classicBurger.setDescription("Soczysty kotlet wołowy, świeża sałata, pomidor, ogórek kiszony i domowy sos burgerowy");
				classicBurger.setCategory("Burgery");
				classicBurger.setPrice(new BigDecimal("29.00"));
				classicBurger.setAvailable(true);

				Product fries = new Product();
				fries.setName("Frytki z serem i bekonem");
				fries.setDescription("Złociste frytki polane ciepłym sosem cheddar i posypane chrupiącymi kawałkami bekonu");
				fries.setCategory("Dodatki");
				fries.setPrice(new BigDecimal("18.00"));
				fries.setAvailable(true);

				burgerRes.getProducts().add(classicBurger);
				burgerRes.getProducts().add(fries);

				// 3. Seed Sushi Restaurant
				Restaurant sushiRes = new Restaurant();
				sushiRes.setName("Sushi Garden");
				sushiRes.setAddress("Grunwaldzka 15, Poznan");
				sushiRes.setOpeningHours("13:00 - 22:00");
				sushiRes.setContactInfo("61-444-55-66");

				Product caliRoll = new Product();
				caliRoll.setName("Zestaw California Roll");
				caliRoll.setDescription("8 sztuk pysznych sushi rolli z sałatką krabową, awokado, ogórkiem i sezamem");
				caliRoll.setCategory("Sushi");
				caliRoll.setPrice(new BigDecimal("32.00"));
				caliRoll.setAvailable(true);

				sushiRes.getProducts().add(caliRoll);

				// 4. Seed Mexican Restaurant
				Restaurant tacoRes = new Restaurant();
				tacoRes.setName("Taco Loco");
				tacoRes.setAddress("Szewska 4, Krakow");
				tacoRes.setOpeningHours("12:00 - 24:00");
				tacoRes.setContactInfo("12-333-22-11");

				Product tacos = new Product();
				tacos.setName("Trio Tacos z Wołowiną");
				tacos.setDescription("Trzy miękkie tortille kukurydziane z doprawionym mięsem mielonym wołowym, serem i świeżym pico de gallo");
				tacos.setCategory("Meksykańskie");
				tacos.setPrice(new BigDecimal("34.00"));
				tacos.setAvailable(true);

				ProductVariant mildTaco = new ProductVariant();
				mildTaco.setName("Łagodna salsa");
				mildTaco.setPrice(new BigDecimal("34.00"));

				ProductVariant spicyTaco = new ProductVariant();
				spicyTaco.setName("Ostra salsa Habanero");
				spicyTaco.setPrice(new BigDecimal("36.00"));
				tacos.setVariants(List.of(mildTaco, spicyTaco));

				Product quesadilla = new Product();
				quesadilla.setName("Quesadilla z kurczakiem");
				quesadilla.setDescription("Zapiekana tortilla pszenna faszerowana grillowanym kurczakiem, ciągnącym się serem jack i szczypiorkiem");
				quesadilla.setCategory("Meksykańskie");
				quesadilla.setPrice(new BigDecimal("31.00"));
				quesadilla.setAvailable(true);

				tacoRes.getProducts().add(tacos);
				tacoRes.getProducts().add(quesadilla);

				// 5. Seed Vegetarian Cafe
				Restaurant saladRes = new Restaurant();
				saladRes.setName("Green & Healthy Cafe");
				saladRes.setAddress("Karmelicka 22, Krakow");
				saladRes.setOpeningHours("08:00 - 20:00");
				saladRes.setContactInfo("12-999-88-77");

				Product caesarSalad = new Product();
				caesarSalad.setName("Sałatka Cezar z chrupiącym tofu");
				caesarSalad.setDescription("Świeża sałata rzymska, pieczone grzanki z tofu, wegański parmezan i sos Cezar");
				caesarSalad.setCategory("Sałatki");
				caesarSalad.setPrice(new BigDecimal("28.00"));
				caesarSalad.setAvailable(true);

				Product smoothie = new Product();
				smoothie.setName("Zielony koktajl oczyszczający");
				smoothie.setDescription("Wyciskany na zimno koktajl ze szpinaku, jarmużu, zielonego jabłka, imbiru, banana i świeżego soku z limonki");
				smoothie.setCategory("Napoje");
				smoothie.setPrice(new BigDecimal("16.00"));
				smoothie.setAvailable(true);

				saladRes.getProducts().add(caesarSalad);
				saladRes.getProducts().add(smoothie);

				// 6. Seed Bakery & Desserts
				Restaurant sweetRes = new Restaurant();
				sweetRes.setName("Sweet Delights Bakery");
				sweetRes.setAddress("Chmielna 5, Warszawa");
				sweetRes.setOpeningHours("07:00 - 19:00");
				sweetRes.setContactInfo("22-999-11-22");

				Product fudgeCake = new Product();
				fudgeCake.setName("Ciepłe ciasto czekoladowe");
				fudgeCake.setDescription("Bogate, podwójnie czekoladowe ciasto podawane na ciepło z płynnym czekoladowym środkiem");
				fudgeCake.setCategory("Desery");
				fudgeCake.setPrice(new BigDecimal("22.00"));
				fudgeCake.setAvailable(true);

				Product muffin = new Product();
				muffin.setName("Muffin z jagodami");
				muffin.setDescription("Puszysta babeczka wypełniona ekologicznymi leśnymi jagodami z kruszonką cynamonową na wierzchu");
				muffin.setCategory("Wypieki");
				muffin.setPrice(new BigDecimal("11.00"));
				muffin.setAvailable(true);

				sweetRes.getProducts().add(fudgeCake);
				sweetRes.getProducts().add(muffin);

				// Save all to database
				List<Restaurant> savedRestaurants = restaurantRepository.saveAll(List.of(
						pizzaRes, burgerRes, sushiRes, tacoRes, saladRes, sweetRes
				));

				// 7. Seed Mock Orders & Payments
				if (foodOrderRepository.count() == 0) {
					Restaurant savedPizza = savedRestaurants.get(0);
					Restaurant savedBurger = savedRestaurants.get(1);
					Restaurant savedSushi = savedRestaurants.get(2);

					// Order 1: PENDING (Waiting for payment)
					FoodOrder order1 = new FoodOrder();
					order1.setRestaurantId(savedPizza.getId());
					order1.setCustomerId(101L);
					order1.setDeliveryAddress("ul. Chopina 15, Rzeszów");
					order1.setDescription("Prośba o podwójny ser na marghericie.");
					order1.setStatus(OrderStatus.PENDING);
					order1.setOrderTime(LocalDateTime.now().minusMinutes(30));
					order1.setEstimatedDeliveryTime(LocalDateTime.now().plusMinutes(15));

					OrderItem o1Item1 = new OrderItem();
					o1Item1.setProductId(savedPizza.getProducts().get(0).getId());
					o1Item1.setProductName(savedPizza.getProducts().get(0).getName());
					o1Item1.setQuantity(2);
					o1Item1.setPrice(savedPizza.getProducts().get(0).getPrice());

					OrderItem o1Item2 = new OrderItem();
					o1Item2.setProductId(savedPizza.getProducts().get(1).getId());
					o1Item2.setProductName(savedPizza.getProducts().get(1).getName());
					o1Item2.setQuantity(1);
					o1Item2.setPrice(savedPizza.getProducts().get(1).getPrice());

					order1.setItems(new ArrayList<>(List.of(o1Item1, o1Item2)));
					foodOrderRepository.save(order1);

					// Order 2: ACCEPTED (Paid, in progress)
					FoodOrder order2 = new FoodOrder();
					order2.setRestaurantId(savedBurger.getId());
					order2.setCustomerId(102L);
					order2.setDeliveryAddress("al. Rejtana 20, Rzeszów");
					order2.setDescription("Bez cebuli w burgerze.");
					order2.setStatus(OrderStatus.ACCEPTED);
					order2.setOrderTime(LocalDateTime.now().minusMinutes(15));
					order2.setEstimatedDeliveryTime(LocalDateTime.now().plusMinutes(30));

					OrderItem o2Item1 = new OrderItem();
					o2Item1.setProductId(savedBurger.getProducts().get(0).getId());
					o2Item1.setProductName(savedBurger.getProducts().get(0).getName());
					o2Item1.setQuantity(1);
					o2Item1.setPrice(savedBurger.getProducts().get(0).getPrice());

					OrderItem o2Item2 = new OrderItem();
					o2Item2.setProductId(savedBurger.getProducts().get(1).getId());
					o2Item2.setProductName(savedBurger.getProducts().get(1).getName());
					o2Item2.setQuantity(2);
					o2Item2.setPrice(savedBurger.getProducts().get(1).getPrice());

					order2.setItems(new ArrayList<>(List.of(o2Item1, o2Item2)));

					Payment payment2 = new Payment();
					payment2.setAmount(new BigDecimal("65.00")); // 29 + 18*2
					payment2.setMethod(PaymentMethod.ONLINE);
					payment2.setStatus(PaymentStatus.COMPLETED);
					payment2.setOrder(order2);
					order2.setPayment(payment2);

					foodOrderRepository.save(order2);

					// Order 3: DELIVERED (Finished successfully)
					FoodOrder order3 = new FoodOrder();
					order3.setRestaurantId(savedSushi.getId());
					order3.setCustomerId(103L);
					order3.setCourierId(501L);
					order3.setDeliveryAddress("ul. Piłsudskiego 5, Rzeszów");
					order3.setDescription("Dzwonić domofonem nr 4.");
					order3.setStatus(OrderStatus.DELIVERED);
					order3.setOrderTime(LocalDateTime.now().minusHours(2));
					order3.setEstimatedDeliveryTime(LocalDateTime.now().minusHours(1).minusMinutes(15));
					order3.setDeliveryTime(LocalDateTime.now().minusHours(1).minusMinutes(20));

					OrderItem o3Item1 = new OrderItem();
					o3Item1.setProductId(savedSushi.getProducts().get(0).getId());
					o3Item1.setProductName(savedSushi.getProducts().get(0).getName());
					o3Item1.setQuantity(2);
					o3Item1.setPrice(savedSushi.getProducts().get(0).getPrice());

					order3.setItems(new ArrayList<>(List.of(o3Item1)));

					Payment payment3 = new Payment();
					payment3.setAmount(new BigDecimal("64.00")); // 32 * 2
					payment3.setMethod(PaymentMethod.ONLINE);
					payment3.setStatus(PaymentStatus.COMPLETED);
					payment3.setOrder(order3);
					order3.setPayment(payment3);

					foodOrderRepository.save(order3);
				}
			}
		};
	}
}
