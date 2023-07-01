package com.BlogApi.Blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
@SpringBootApplication
public class BlogApplication implements CommandLineRunner {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		jdbcTemplate.update("Create table IF NOT EXISTS Blog_Details(" +
				" blog_id INT GENERATED ALWAYS AS IDENTITY," +
				" user_id INT NOT NULL," +
				" title VARCHAR(255) NOT NULL," +
				" author VARCHAR(255) NOT NULL," +
				" body VARCHAR(255) NOT NULL," +
				" PRIMARY KEY(blog_id)" +
				");");
		jdbcTemplate.update("Create table IF NOT EXISTS Comment_Details(" +
				" comment_id INT GENERATED ALWAYS AS IDENTITY," +
				" user_id INT NOT NULL," +
				" blog_id INT NOT NULL," +
				" body VARCHAR(255) NOT NULL, " +
				" PRIMARY KEY(comment_id)," +
				" CONSTRAINT fk_blog_id FOREIGN KEY(blog_id) references Blog_Details(blog_id) on delete cascade" +
				");");
		jdbcTemplate.update("create table IF NOT EXISTS Tag_Details (" +
				" tag_name VARCHAR(255) NOT NULL," +
				" blog_id INT NOT NULL," +
				" PRIMARY KEY(tag_name, blog_id)," +
				" CONSTRAINT fk_tag FOREIGN KEY(blog_id) references Blog_details(blog_id) on delete cascade" +
				");");
		jdbcTemplate.update("CREATE TABLE IF NOT EXISTS tag_collection (" +
				" seq integer NOT NULL SERIAL," +
				" tag_name VARCHAR(255) NOT NULL," +
				" CONSTRAINT tag_collection_pkey PRIMARY KEY (seq)," +
				" CONSTRAINT tag_collection_tag_name_key UNIQUE (tag_name)" +
				");");
		jdbcTemplate.update("DELETE from tag_collection");
		jdbcTemplate.update("Insert into tag_collection (tag_name) values ('Advertising')");
		jdbcTemplate.update("Insert into tag_collection (tag_name) values ('Books')");
		jdbcTemplate.update("Insert into tag_collection (tag_name) values ('Fashion')");
		jdbcTemplate.update("Insert into tag_collection (tag_name) values ('Education')");
		jdbcTemplate.update("Insert into tag_collection (tag_name) values ('Food')");
		jdbcTemplate.update("Insert into tag_collection (tag_name) values ('Fitness')");
		jdbcTemplate.update("Insert into tag_collection (tag_name) values ('Humor')");
		jdbcTemplate.update("Insert into tag_collection (tag_name) values ('Marketing')");
		jdbcTemplate.update("Insert into tag_collection (tag_name) values ('Music')");
		jdbcTemplate.update("Insert into tag_collection (tag_name) values ('Photography')");
		jdbcTemplate.update("Insert into tag_collection (tag_name) values ('News')");
		jdbcTemplate.update("Insert into tag_collection (tag_name) values ('Parenting')");
		jdbcTemplate.update("Insert into tag_collection (tag_name) values ('Movies')");
		jdbcTemplate.update("Insert into tag_collection (tag_name) values ('Travel')");
		jdbcTemplate.update("Insert into tag_collection (tag_name) values ('Web Design')");
		jdbcTemplate.update("Insert into tag_collection (tag_name) values ('Technology')");
		jdbcTemplate.update("Insert into tag_collection (tag_name) values ('Science')");
		jdbcTemplate.update("Insert into tag_collection (tag_name) values ('AI')");
		jdbcTemplate.update("Insert into tag_collection (tag_name) values ('Health')");
		jdbcTemplate.update("Insert into tag_collection (tag_name) values ('Politics')");

	}
}
