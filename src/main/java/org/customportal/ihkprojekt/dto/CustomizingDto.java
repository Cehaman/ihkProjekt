package org.customportal.ihkprojekt.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.customportal.ihkprojekt.model.Comment;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomizingDto {

    private Long id;

    private String title;

    private String content;

    private LocalDateTime createdAt;

    private List<CommentDto> comments;

    private List<Long> tagIds;


}
