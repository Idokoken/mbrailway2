//package ndgroups.mbrailway2.controller;
//
//
//import com.stripe.exception.StripeException;
//import com.stripe.model.PaymentIntent;
//import ndgroups.mbrailway2.service.StripeService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Controller
//@RequestMapping("/payments")
//public class PaymentController {
//    @Autowired
//    private StripeService stripeService;
//
//    @PostMapping("/create-payment-intent")
//    public Map<String, Object> createPaymentIntent(@RequestParam long amount, @RequestParam String currency) {
//        Map<String, Object> response = new HashMap<>();
//        try {
//            PaymentIntent paymentIntent = stripeService.createPaymentIntent(currency, amount);
//            response.put("status", "success");
//            response.put("clientSecret", paymentIntent.getClientSecret());
//        } catch (StripeException e) {
//            response.put("status", "error");
//            response.put("message", e.getMessage());
//        }
//        return response;
//    }
//
//    @GetMapping("/retrieve-payment-intent/{paymentIntentId}")
//    public Map<String, Object> retrievePaymentIntent(@PathVariable String paymentIntentId) {
//        Map<String, Object> response = new HashMap<>();
//        try {
//            PaymentIntent paymentIntent = stripeService.retrievePaymentIntent(paymentIntentId);
//            response.put("status", "success");
//            response.put("paymentIntent", paymentIntent);
//        } catch (StripeException e) {
//            response.put("status", "error");
//            response.put("message", e.getMessage());
//        }
//        return response;
//    }
//
//
//
//}
