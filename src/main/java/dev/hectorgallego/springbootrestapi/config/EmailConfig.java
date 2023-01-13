package dev.hectorgallego.springbootrestapi.config;

import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import com.nimbusds.jose.util.StandardCharset;

@Configuration
public class EmailConfig {

    SpringTemplateEngine getTemplateEngine(ITemplateResolver templateResolver){

        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.addTemplateResolver(templateResolver);
        return templateEngine;

    }

    public ClassLoaderTemplateResolver getTemplateResolver(){
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();

        templateResolver.setPrefix("/templates");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCharacterEncoding(StandardCharset.UTF_8.name());
        templateResolver.setCacheable(false);

        return templateResolver;
    }
    
}
