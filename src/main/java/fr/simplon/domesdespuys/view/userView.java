package fr.simplon.domesdespuys.view;

public class userView {

    public interface Id {}
    public interface Email {}
    public interface Firstname {}
    public interface Lastname {}
    public interface Phone {}
    public interface Role {}
    public interface CreatedAt {}
    public interface Addresses {}
    public interface Orders {}

    public interface UserListView extends Id, Email, Firstname, Lastname {}
    public interface UserDetailView extends UserListView, Phone, Role, CreatedAt, Addresses, Orders,
                    orderView.OrderListView, addressView.AddressListView{}
    public interface UserResumeView extends Email, Firstname, Lastname {}

}