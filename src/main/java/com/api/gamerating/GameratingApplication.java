package com.api.gamerating;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class GameratingApplication {

	public static void main(String[] args) {
		SpringApplication.run(GameratingApplication.class, args);
	}



		/*
	@Bean
	public CommandLineRunner commandLineRunner(AppDao appDao){
		return running -> {
			//saveGame(appDao);
			//findGame(appDao);
			//deleteGame(appDao);
			//findRating(appDao);
			//deleteRating(appDao);

			//createInstructorWithCategory(appDao);
			//findInstructorWithCategory(appDao);
			//findCategoryForGame(appDao);
			//findGameWithCategoryJoinFetch(appDao);
			//updateGame(appDao);
			//updateCategory(appDao);
			//deleteGame(appDao);
			//deleteCategory(appDao);

			//----one to many uni directional----

			//createGameandReviews(appDao);
			//retrieveGameandReviews(appDao);
			//deleteGameandReviews(appDao);

			//----many to many----
			//createGameandUsers(appDao);
			//findGameandUsers(appDao);
			//findUsersAndGames(appDao);
			//addMoreGamesForUsers(appDao);
			//deleteGame(appDao);
			//deleteUser(appDao);



		};
	}

	private void deleteUser(AppDao appDao) {

		appDao.deleteUserById(1);
	}

	private void addMoreGamesForUsers(AppDao appDao) {

		User user = appDao.findUserandGameByGameId(1);
		Review review1 = appDao.findGameAndReviewsByGameId(1);

		Game game = new Game("God Of War","Sony Entertainment","Platformer",new Timestamp(new Date(2021,03,21).getTime()));
		Rating rating = new Rating("MA","for 15 and up","Australian Rating Board");
		Review review1 = new Review("Amazing game - 10/10");
		Review review2 = new Review("Pretty average game - 7/10");
		Review review3 = new Review("Pretty boring game - 5/10");

		Game game1 = new Game("Spider-Man","Sony Entertainment","Platformer",new Timestamp(new Date(2021,03,21).getTime()));
		Rating rating2 = new Rating("M","Mature","Australian Rating Board");
		Review review12 = new Review("Amazing game - 10/10");
		Review review22 = new Review("Pretty average game - 7/10");
		Review review32 = new Review("Pretty boring game - 5/10");

		game.setRating(rating); // will save the two objects through cascading defined in the entity class game


		user.addReviewByUser(review12);
		user.addReviewByUser(review22);
		user.addReviewByUser(review32);

		game1.setRating(rating2); // will save the two objects through cascading defined in the entity class game


		game1.addReview(review12);
		game1.addReview(review22);
		game1.addReview(review32);

		user.addGameUser(game);
		user.addGameUser(game1);

		appDao.update(user);


		System.out.println("----Game Details----\n\n");
		System.out.println("---------------------------------------------------------\n\n");
		System.out.println("Game: " + user.getGame() + " \n");
		System.out.println("Users: " + user + " \n");
		System.out.println("\n\n---------------------------------------------------------");



	}

	private void findUsersAndGames(AppDao appDao) {
		User user = appDao.findUserandGameByGameId(1);

		System.out.println("----Game Details----\n\n");
		System.out.println("---------------------------------------------------------\n\n");
		System.out.println("Game: " + user.getGame() + " \n");
		System.out.println("Users: " + user  + " \n");
		System.out.println("\n\n---------------------------------------------------------");
	}

	private void findGameandUsers(AppDao appDao) {
		Game game = appDao.findGameandUsersByGameId(1);

		System.out.println("----Game Details----\n\n");
		System.out.println("---------------------------------------------------------\n\n");
		System.out.println("Game: " + game + " \n");
		System.out.println("Users: " + game.getUsers() + " \n");
		System.out.println("\n\n---------------------------------------------------------");

	}

	private void createGameandUsers(AppDao appDao) {
		Game game = new Game("Ratchet and Clank: Rift Apart","Sony Entertainment","Platformer",new Timestamp(new Date(2021,03,21).getTime()));
		Rating rating = new Rating("PG","for 12 and up","Australian Rating Board");
		Category category1 = new Category("Action");
		Category category2 = new Category("Adventure");
		Review review1 = new Review("Amazing game - 10/10");
		Review review2 = new Review("Pretty average game - 7/10");
		Review review3 = new Review("Pretty boring game - 5/10");
		User user1 = new User("Brendan","Marcolin","b@gmail.com");
		User user2 = new User("Ellayna","Marcolin","e@gmail.com");
		User user3 = new User("Matt","Marcolin","m@gmail.com");

		game.setRating(rating); // will save the two objects through cascading defined in the entity class game
		game.add(category1);
		game.add(category2);

		game.addReview(review1);
		game.addReview(review2);
		game.addReview(review3);

		game.addGameUser(user1);
		game.addGameUser(user2);
		game.addGameUser(user3);

		appDao.save(game);

		System.out.println("----Game Details----\n\n");
		System.out.println("---------------------------------------------------------\n\n");
		System.out.println("Game: " + game + " \n");
		System.out.println("Category: " + game.getCategories() + " \n");
		System.out.println("Review: " + game.getReviews() + " \n");
		System.out.println("Users: " + game.getUsers() + " \n");
		System.out.println("\n\n---------------------------------------------------------");

				;
	}

	private void deleteGameandReviews(AppDao appDao) {

		System.out.println("Game Being Deleted.....");
		appDao.deleteGame(1);

		System.out.println("---Game Deleted---");
	}

	private void retrieveGameandReviews(AppDao appDao) {
		Game game = appDao.findGameAndReviewsByGameId(1);

		System.out.println("Game:  " + game);

		System.out.println("Reviews:  " + game.getReviews());



	}

	private void createGameandReviews(AppDao appDao) {

		Game game = new Game("Ratchet and Clank: Rift Apart","Sony Entertainment","Platformer",new Timestamp(new Date(2021,03,21).getTime()));
		Rating rating = new Rating("PG","for 12 and up","Australian Rating Board");
		Category category1 = new Category("Action");
		Category category2 = new Category("Adventure");
		Review review1 = new Review("Amazing game - 10/10");
		Review review2 = new Review("Pretty average game - 7/10");
		Review review3 = new Review("Pretty boring game - 5/10");

		game.setRating(rating); // will save the two objects through cascading defined in the entity class game
		game.add(category1);
		game.add(category2);

		game.addReview(review1);
		game.addReview(review2);
		game.addReview(review3);


		appDao.save(game);

	}

	private void deleteCategory(AppDao appDao) {

		appDao.deleteCategoryById(10);
		appDao.deleteCategoryById(11);


	}

	private void updateCategory(AppDao appDao) {

		Category category1 = appDao.findCategoryById(10);
		Category category2 = appDao.findCategoryById(11);

		category1.setTitle("Horror");
		category2.setTitle("Romance");

		appDao.update(category1);
		appDao.update(category2);

	}

	private void updateGame(AppDao appDao) {

		Game game = appDao.findGameById(1);
		game.setDescription("A furry guy and his robot friend");
		appDao.update(game);



	}

	private void findGameWithCategoryJoinFetch(AppDao appDao) {

		Game game = appDao.findGameByCategoryIdJoinFetch(1);

		System.out.println("Game: " + game);

		System.out.println("Rating: " + game.getRating());

		System.out.println("Categorizes: " + game.getCategories());
	}

	private void findCategoryForGame(AppDao appDao) {

		Game game = appDao.findGameById(1);

		System.out.println("Game: " + game);

		System.out.println("Rating: " + game.getRating());

		//find categories by lazy loading
		List<Category> categories = appDao.findGameByCategoryId(1);
		game.setCategories(categories);
		System.out.println("Categorizes: " + game.getCategories());


	}

	private void findInstructorWithCategory(AppDao appDao) {

		Game game = appDao.findGameById(1);

		System.out.println("Game: " + game);

		System.out.println("Rating: " + game.getRating());

		//Eager loading
		System.out.println("Catergores: " + game.getCategories());

	}

	private void createInstructorWithCategory(AppDao appDao) {

		Game game = new Game("Ratchet and Clank: Rift Apart","Sony Entertainment","Platformer",new Timestamp(new Date(2021,03,21).getTime()));
		Rating rating = new Rating("PG","for 12 and up","Australian Rating Board");
		Category category1 = new Category("Action");
		Category category2 = new Category("Adventure");

		game.setRating(rating); // will save the two objects through cascading defined in the entity class game
		game.add(category1);
		game.add(category2);
		appDao.save(game);

	}

	private void deleteRating(AppDao appDao) {

		appDao.deleteRatingById(3);
		System.out.println("Deleted the rating");
	}

	private void findRating(AppDao appDao) {

		Rating rating = appDao.findRatingById(2);


		System.out.println("Rating: " + rating);

		System.out.println("Game: " + rating.getGame());


	}

	private void deleteGame(AppDao appDao) {
		appDao.deleteGame(1);
		System.out.println("Deleted th game");
	}

	private void findGame(AppDao appDao) {

		Game game = appDao.findGameById(1);

		System.out.println("Game: " + game);

		System.out.println("Rating: " + game.getRating());

	}

	private void saveGame(AppDao appDao) {


		Game game = new Game("Ratchet and Clank: Rift Apart","Sony Entertainment","Platformer",new Timestamp(new Date(2021,03,21).getTime()));
		Rating rating = new Rating("PG","for 12 and up","Australian Rating Board");


		game.setRating(rating); // will save the two objects through cascading defined in the entity class game

		appDao.save(game);
	}


	 */

}
