package edu.prz.delivery;

import edu.prz.delivery.restaurants.domain.restaurant.Product;
import edu.prz.delivery.restaurants.domain.restaurant.ProductVariant;
import edu.prz.delivery.restaurants.domain.restaurant.Restaurant;
import edu.prz.delivery.restaurants.domain.restaurant.RestaurantRepository;
import java.math.BigDecimal;
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
	public CommandLineRunner seedDemoData(RestaurantRepository restaurantRepository) {
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
				restaurantRepository.saveAll(List.of(pizzaRes, burgerRes, sushiRes, tacoRes, saladRes, sweetRes));
			}
		};
	}
}
