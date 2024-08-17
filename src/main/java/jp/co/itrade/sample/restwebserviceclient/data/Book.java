package jp.co.itrade.sample.restwebserviceclient.data;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Book {
//    @Serial
//    private static final long serialVersionUID = 1L;
    private String bookId;
    private String name;
    private LocalDate publishedDate;
}
