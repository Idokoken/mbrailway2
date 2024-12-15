//package ndgroups.mbrailway2.service;
//
//import com.stripe.Stripe;
//import com.stripe.exception.StripeException;
//import com.stripe.model.PaymentIntent;
//import ndgroups.mbrailway2.DTO.ProductRequest;
//import ndgroups.mbrailway2.DTO.StripeResponse;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Service
//public class StripeService {
//    @Value("${stripe.secret.key}")
//    private String secretKey;
//
//    public void checkoutProduct(ProductRequest productRequest) {
//        Stripe.apiKey = secretKey;
//    }
////    public PaymentIntent createPaymentIntent(String currency, long amount) throws StripeException {
////        Map<String, Object> params = new HashMap<>();
////        params.put("amount", amount); // Amount in cents (e.g., $10 = 1000 cents)
////        params.put("currency", currency);
////        params.put("payment_method_types", java.util.List.of("card")); // Supported payment methods
////
////        return PaymentIntent.create(params);
////    }
////
////    public PaymentIntent retrievePaymentIntent(String paymentIntentId) throws StripeException {
////        return PaymentIntent.retrieve(paymentIntentId);
////    }
//
//
//}
