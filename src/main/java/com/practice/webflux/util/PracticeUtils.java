package com.practice.webflux.util;

import com.github.javafaker.Faker;

public class PracticeUtils {
	
	private static final Faker FAKER = Faker.instance();
	
	public static Faker faker() {

		return FAKER;

	}

}
