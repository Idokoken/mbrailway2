//package ndgroups.mbrailway2.config;
//
//import com.stripe.Stripe;
//import jakarta.annotation.PostConstruct;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class StripeConfig {
//    @Value("${stripe.secret.key}")
//    private String secretKey;
//    @Value("${stripe.publishable.key}")
//    private String publishableKey;
//
//
//    @PostConstruct
//    public void setup() {
//        Stripe.apiKey = secretKey;
//    }
//}
//
