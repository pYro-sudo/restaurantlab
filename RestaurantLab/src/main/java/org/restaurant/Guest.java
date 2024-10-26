package org.restaurant;

import org.json.JSONObject;

public interface Guest {
    Order payForTheOrder(Order order);
    Tip tip(double amount);
    Review writeReview();
}
