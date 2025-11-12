package com.learnkey.learnkey_api; //Paquete al que pertence el archivo, organiza el codigo.

import org.springframework.boot.SpringApplication; //importan librerias ya hechas de Spring Boot. Esta arranca la app
import org.springframework.boot.autoconfigure.SpringBootApplication; //es una anotacion que marca esta clase como la principal al empezar con @

@SpringBootApplication // Activa las configuraciones dentro del proyecto, busca automaticamente los
						// controladores, servicios, modelos, etc
public class LearnkeyApiApplication { // Define la clase publica

	public static void main(String[] args) {
		SpringApplication.run(LearnkeyApiApplication.class, args); // arranca el servidor de Spring Boot, crea el
																	// entorno, carga tdo, es el que enciende el back
	}

}
