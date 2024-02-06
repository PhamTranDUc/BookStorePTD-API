package com.BookStorePTD.BookStorePTD;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
@SpringBootApplication
public class ShopBookPtdApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopBookPtdApplication.class, args);
	}

	@Bean
	public Cloudinary cloudinary(){
		Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
				"cloud_name", "dmedbpuv3",
				"api_key", "882697469482764",
				"api_secret", "aAHGXO5N0Zi8kNhysKuYD-uVg-o"));
		return cloudinary;
	}

}
