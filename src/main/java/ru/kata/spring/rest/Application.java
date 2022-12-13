//package ru.kata.spring.rest;
//
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//
//public class Application {
//	public static void main(String[] args) {
//		ApplicationContext context = new AnnotationConfigApplicationContext(WebConfig.class);
//		RestTemplateClient client = context.getBean("restTemplateClient", RestTemplateClient.class);
//
//		User addUser = new User(3L, "James", "Brown", (byte)69);
//		User updateUser = new User(3L, "Thomas", "Shelby", (byte)69);
//		User userDelete = new User();
//		userDelete.setId(3L);
//
//		String saveCookies = client.getAllUsers().getHeaders().getValuesAsList("Set-Cookie").get(0);
//		System.out.println(saveCookies);
//
//		String secret1 = client.addUser(addUser, saveCookies).getBody();
//		String secret2 = client.updateUser(updateUser, saveCookies).getBody();
//		String secret3 = client.deleteUser(userDelete, saveCookies).getBody();
//
//		System.out.println(secret1 + secret2 + secret3);
//
//
//	}
//}
