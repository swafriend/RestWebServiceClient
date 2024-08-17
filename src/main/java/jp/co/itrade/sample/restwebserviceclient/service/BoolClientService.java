package jp.co.itrade.sample.restwebserviceclient.service;

import jakarta.annotation.PostConstruct;
import jp.co.itrade.sample.restwebserviceclient.data.BookResource;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.IntStream;

@Component
class BoolClientService {

    RestOperations restOperations;

    public BoolClientService(RestOperations restOperations, WebClient.Builder webClientBuilder) {
        this.restOperations = restOperations;
        this.webClient = webClientBuilder.build();
    }

    private final WebClient webClient;

    @PostConstruct
    private void init() {

//        BookResource bookResource = restOperations.getForObject(
//                "http://localhost:8080/books/00000000-0000-0000-0000-000000000000",
//                BookResource.class);
//        System.out.println(Objects.requireNonNull(bookResource).getName());

        BookResource bookResource1 = new BookResource();
        bookResource1.setName("Wadasadamu");
        bookResource1.setPublishedDate(LocalDate.of(2016, 4, 1));
        URI createdResourceUri = restOperations.postForLocation("http://localhost:8080/books", bookResource1);
        System.out.println(createdResourceUri);

        BookResource bookResource2 = new BookResource();
        bookResource2.setName("Wadaharuna");
        bookResource2.setPublishedDate(LocalDate.of(2016, 4, 1));
        RequestEntity<BookResource> requestEntity = RequestEntity.post("http://localhost:8080/books").
                contentType(MediaType.APPLICATION_JSON).
                header("X-Track-Id", UUID.randomUUID().toString()).
                body(bookResource2);
        ResponseEntity<Void> responseEntity = restOperations.exchange(requestEntity, Void.class);
        System.out.println(Objects.requireNonNull(responseEntity.getHeaders().getLocation()));
        System.out.println(Objects.requireNonNull(responseEntity.getStatusCode()));
        System.out.println(Objects.requireNonNull(responseEntity.getHeaders()));

//        RequestEntity<Void> requestEntity2 = RequestEntity
//                .get(UriComponentsBuilder
//                        .fromUriString("http://localhost:8080/books/{bookId}")
//                        .buildAndExpand("00000000-0000-0000-0000-000000000000")
//                        .encode()
//                        .toUri())
//                .header("X-Track-Id", UUID.randomUUID().toString())
//                .build();
//        System.out.println(requestEntity2);
//        ResponseEntity<String> rateResponse = restOperations.exchange(requestEntity2, String.class);



//
//        String publishDate = "2016/04/01";
//        String name = "Wada";
//        RequestEntity<Void> request = RequestEntity
//                .get(UriComponentsBuilder
//                        .fromUriString("http://localhost:8080/RestWebService/GetLigerMessageList")
//                        .encode().build().toUri())
//                .build();
//        ResponseEntity<List<String>> rateResponse = restOperations.exchange(request, new ParameterizedTypeReference<>() {
//        });
        
//        System.out.println(rateResponse);

        System.out.println("before:");
        Mono<String> message = webClient.get()
                .uri("http://localhost:8080/books/00000000-0000-0000-0000-000000000000")
//                .uri(uriBuilder -> uriBuilder.path("http://localhost:8080/books/00000000-0000-0000-0000-000000000000").build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class);
        System.out.println("after:");

        IntStream.range(1,10).forEach(value -> {
            try {
                System.out.println("waiting:" + value + LocalDateTime.now());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        System.out.println(message.block());

    }


}
