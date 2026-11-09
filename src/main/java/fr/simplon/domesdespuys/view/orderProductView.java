package fr.simplon.domesdespuys.view;

public class orderProductView {

    public interface Id {}
    public interface Quantity {}
    public interface Price {}
    public interface Product {}

    public interface OrderProductListView extends Id, Quantity, Price, Product, productView.ProductListView {}

}