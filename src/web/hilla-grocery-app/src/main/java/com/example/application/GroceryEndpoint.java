package com.example.application;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaadin.flow.server.auth.AnonymousAllowed;

import dev.hilla.Endpoint;
import dev.hilla.Nonnull;

import io.dapr.client.DaprClient;
import io.dapr.client.DaprClientBuilder;
import io.dapr.client.domain.HttpExtension;

@Endpoint
@AnonymousAllowed
public class GroceryEndpoint {

    //Identifier in Dapr for the service this client will invoke.
    private static final String SERVICE_APP_ID = "grocery-service";    

    /**
     * Json serializer/deserializer.
     */
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public @Nonnull List<@Nonnull GroceryItem> getGroceries() throws Exception {
        try (DaprClient client = (new DaprClientBuilder()).build()) {
            GroceryItem[] response = client.invokeMethod(SERVICE_APP_ID, "groceries", null,
                    HttpExtension.GET,
                    GroceryItem[].class).block();

            return Arrays.asList(response);
        }
    }

    public GroceryItem save(GroceryItem item) throws Exception {
        
        try (DaprClient client = (new DaprClientBuilder()).build()) {
            GroceryItem response = client.invokeMethod(SERVICE_APP_ID, "groceries/create",
                    OBJECT_MAPPER.writeValueAsString(item),
                    HttpExtension.POST,
                    GroceryItem.class).block();

            return response;
        }
    }
}
