package fr.simplon.domesdespuys.view;

public class userView {

    public interface Id {}
    public interface Email {}
    public interface Firstname {}
    public interface Lastname {}
    public interface Phone {}
    public interface Role {}
    public interface CreatedAt {}

    public interface UserListView extends Id, Email, Firstname, Lastname {}
    public interface UserDetailView extends UserListView, Phone, Role, CreatedAt {}

}