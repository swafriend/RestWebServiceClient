package jp.co.itrade.sample.restwebserviceclient.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Data
public class BookResourceQuery implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private String bookId;
    private String name;

    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDate publishedDate;


}
