package com.springboot.muliti.db;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
	@Autowired
	@Qualifier("postgresJdbcTemplate")
	private JdbcTemplate postgresJdbcTemplate;

	@Autowired
	@Qualifier("mysqlJdbcTemplate")
	private JdbcTemplate mysqlJdbcTemplate;

	@RequestMapping("/getPGUser")
	public String getPGUser() {
		Map<String, Object> map = new HashMap<String, Object>();

		String query = "SELECT * FROM sys_role LIMIT 0, 1";

		try {
			map = postgresJdbcTemplate.queryForMap(query);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "posgresTemplate:" + map.toString();
	}

	@RequestMapping("/getMYUser")
	public String getMYUser() {
		Map<String, Object> map = new HashMap<String, Object>();

		String query = "SELECT * FROM city LIMIT 0, 1";

		try {
			map = mysqlJdbcTemplate.queryForMap(query);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "mysqlTemplate:" + map.toString();
	}

}
