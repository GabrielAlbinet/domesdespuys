package fr.simplon.domesdespuys.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductPostDTO {
    private String label;
    private String description;
    private int price;
    private Long idBrand;
    private List<String> images;
}