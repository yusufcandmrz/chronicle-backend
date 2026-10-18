package com.yusufcandmrz.chronicle.dto;

import com.yusufcandmrz.chronicle.validation.annotation.CurrentOrPastYear;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookRequest {

    @NotBlank(message = "Title cannot be blank")
    @Size(max = 255, message = "Title cannot exceed 255 characters")
    private String title;

    @NotNull
    @Positive
    @CurrentOrPastYear
    private Integer releaseYear;

    @NotEmpty(message = "At least one author is required")
    @Size(max = 20, message = "Maximum 20 authors are allowed")
    private List<
            @NotBlank(message = "Author name cannot be blank")
            @Size(max = 100, message = "Author name cannot exceed 100 characters")
                    String> authorList;

    @Size(max = 20, message = "Maximum 20 translators are allowed")
    private List<
            @NotBlank(message = "Translator name cannot be blank")
            @Size(max = 100, message = "Translator name cannot exceed 100 characters")
                    String> translatorList;

    @NotBlank(message = "Publisher is required")
    @Size(max = 100, message = "Publisher name cannot exceed 100 characters")
    private String publisher;
}
