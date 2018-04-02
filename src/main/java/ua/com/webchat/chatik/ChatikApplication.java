package ua.com.webchat.chatik;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import ua.com.webchat.chatik.config.AppConfig;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

@SpringBootApplication
public class ChatikApplication extends SpringBootServletInitializer {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(ChatikApplication.class, args);
	}

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		super.onStartup(servletContext);

		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.register(AppConfig.class);
	}
}
