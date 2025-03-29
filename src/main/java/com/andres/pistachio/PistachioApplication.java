package com.andres.pistachio;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

import com.andres.pistachio.categoria.CategoriaRepository;
import com.andres.pistachio.producto.ProductoRepository;
import com.andres.pistachio.security.AuthenticationService;
import com.andres.pistachio.security.RegistroUsuarioDto;
import com.andres.pistachio.serviceSQL.SqlExecutionService;
import com.andres.pistachio.usuario.UsuarioRepository;

import jakarta.transaction.Transactional;

@SpringBootApplication
public class PistachioApplication {
	
	private static final Logger log = LoggerFactory.getLogger(PistachioApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(PistachioApplication.class, args);
	}

	@Bean
	@Transactional
	@Order(value = 1)
	CommandLineRunner crearAdmin(AuthenticationService authService, UsuarioRepository usuarioRepository, SqlExecutionService sqlExecutionService) {
		return args -> {
			if (usuarioRepository.count() == 0) {
				RegistroUsuarioDto admin = new RegistroUsuarioDto("eveylea@gmail.com", "pistachio2025?", "EveYLea",
						"Pistachio", "2996357610");
				authService.signup(admin);
				log.info("==> Usuario ADMIN creado");
			} else {
				log.warn("==> Usuario ADMIN existe");
			}
		};
	}
	
	@Bean
	@Transactional
	@Order(value = 2)
	CommandLineRunner insertarDatosCategoriasBBDD(CategoriaRepository catRepo, SqlExecutionService sqlExecutionService) {
		return args -> {
			if (catRepo.count() == 0) {
				try {
			        sqlExecutionService.executeSqlFromFile("categorias.sql");
			        log.info("==> SQL del archivo categorias.sql ejecutado correctamente");
			    } catch (IOException e) {
			    	log.warn("==> Error al ejecutar el SQL desde el archivo de categorias.sql: " + e.getMessage());
			    }
			}else {
				log.info("==> Ya hay datos en la tabla categorias");
			}
		};
	}
	
	@Bean
	@Transactional
	@Order(value = 3)
	CommandLineRunner insertarDatosProductosBBDD(ProductoRepository prodRepo, SqlExecutionService sqlExecutionService) {
		return args -> {
			if (prodRepo.count() == 0) {
				try {
			        sqlExecutionService.executeSqlFromFile("productos.sql");
			        log.info("==> SQL del archivo productos.sql ejecutado correctamente");
			    } catch (IOException e) {
			    	log.info("==> Error al ejecutar el SQL desde el archivo de productos.sql : " + e.getMessage());
			    }
			}else {
				log.info("==> Existen datos en la tabla productos");
			}
		};
	}
}
