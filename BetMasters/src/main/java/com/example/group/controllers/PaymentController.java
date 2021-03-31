package com.example.group.controllers;

import com.example.group.entities.User;
import com.example.group.repos.RoleRepository;
import com.example.group.services.StripeService;
import com.example.group.services.UserService;
import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PaymentController {
    
    @Autowired
    private RoleRepository roleRepo;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private StripeService stripeService;
    
    // Reading the value from the application.properties file
    @Value("${STRIPE_PUBLIC_KEY}")
    private String stripePublicKey;

    @RequestMapping("/buy")
    public String home(Model model, Principal principal) {
        model.addAttribute("amount", 25 * 100); // In cents
        model.addAttribute("stripePublicKey", stripePublicKey);
        model.addAttribute("email", principal.getName());
        return "paymentForm";
    }

    @RequestMapping(value = "/charge", method = RequestMethod.POST)
    public String chargeCard(HttpServletRequest request, Principal principal) throws Exception {
        String token = request.getParameter("stripeToken");
        Double amount = Double.parseDouble(request.getParameter("amount"));
        stripeService.chargeNewCard(token, amount);
        User user = userService.findByEmail(principal.getName());
        user.addRole(roleRepo.findRoleByName("ROLE_PREMIUM_USER"));
        userService.update(user);
        return "redirect:/login?premiumUser";
    }
}
