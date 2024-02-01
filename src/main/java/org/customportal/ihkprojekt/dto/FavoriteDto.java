package org.customportal.ihkprojekt.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteDto {

    private Long favoriteId;

    private String favoritenTitle;

    private Long customizingId;

    private Long userId;


}
