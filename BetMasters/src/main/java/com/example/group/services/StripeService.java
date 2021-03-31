package com.example.group.services;

import com.stripe.Stripe;
import com.stripe.model.Charge;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

/**
 *
 * @author Ody
 */
@Service
public class StripeService {

    @Autowired
    public StripeService() {
        Stripe.apiKey = "sk_test_JpHaNxIfoo7zJhQzFSPyDRLh00wUdZ3Mny";
    }

    public Charge chargeNewCard(String token, double amount) throws Exception {
        Map<String, Object> chargeParams = new HashMap<String, Object>();
        chargeParams.put("amount", (int)(amount * 100));
        chargeParams.put("currency", "eur");
        chargeParams.put("source", token);
        Charge charge = Charge.create(chargeParams);
        return charge;
    }
}
