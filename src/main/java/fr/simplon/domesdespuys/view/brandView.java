package fr.simplon.domesdespuys.view;

public class brandView {

    public interface Id {}
    public interface Label {}
    public interface Logo {}
    public interface Slug {}
    public interface Products {}

    public interface BrandListView extends Label, Slug {}
    public interface BrandDetailView extends
            BrandListView,
            Logo, Products,
            productView.ProductListView {}

}
