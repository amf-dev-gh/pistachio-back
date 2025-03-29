package com.andres.pistachio.serviceSQL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
public class SqlExecutionService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	/*
	 * Servicio de ejecución de QUERY SQL al iniciar la APP. Para evitar problemas de Generacion de tablas/entidades.
	 */
	public void executeSqlFromFile(String filePath) throws IOException {
		ClassPathResource resource = new ClassPathResource(filePath);
		String sql = new String(FileCopyUtils.copyToByteArray(resource.getInputStream()), StandardCharsets.UTF_8);
		jdbcTemplate.execute(sql);
	}
}
