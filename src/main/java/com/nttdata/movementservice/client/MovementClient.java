package com.nttdata.movementservice.client;

import com.nttdata.movementservice.model.api.RequestMovement;
import com.nttdata.movementservice.model.api.ResponseMovement;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class MovementClient {
  private final WebClient client;

  // Spring Boot auto-configures a `WebClient.Builder` instance with nice defaults and customizations.
  // We can use it to create a dedicated `WebClient` for our component.
  public MovementClient(WebClient.Builder builder) {
    this.client = builder.baseUrl("http://localhost:8006").build();
  }

  public Mono<ResponseMovement> getInfo(RequestMovement request) {
    return this.client.get().uri("/findall", request)
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToMono(ResponseMovement.class);
    //.map(Movement::getId);
  }
}
