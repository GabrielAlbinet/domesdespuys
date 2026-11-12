package fr.simplon.domesdespuys.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImageDTO {
    private String path;
    private String extension;
    private Boolean isDefault;
}