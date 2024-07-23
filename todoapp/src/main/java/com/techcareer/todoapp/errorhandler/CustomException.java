package com.techcareer.todoapp.errorhandler;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class CustomException {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy HH:mm:ss")

    @Accessors(prefix = "m_")
    private LocalDateTime m_timestamp;

    @Accessors(prefix = "m_")
    private HttpStatus m_status;

    @Accessors(prefix = "m_")
    private String m_message;
}
