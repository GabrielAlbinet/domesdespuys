package fr.simplon.domesdespuys.view;

public class orderView {

    public interface Id {}
    public interface Number {}
    public interface Status {}
    public interface CreatedAt {}
    public interface User {}
    public interface Address {}
    public interface OrderProducts {}

    public interface OrderListView extends Id, Number, Status, CreatedAt {}
    public interface OrderDetailView extends OrderListView, User, Address, OrderProducts,
            userView.UserResumeView, addressView.AddressResumeView, orderProductView.OrderProductListView {}

}