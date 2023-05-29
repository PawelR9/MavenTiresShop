package camp.it.shop;

import camp.it.shop.configuration.AppConfiguration;
import camp.it.shop.core.ICore;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(
                        AppConfiguration.class);

        ICore core = context.getBean(ICore.class);
        core.start();
    }
}
