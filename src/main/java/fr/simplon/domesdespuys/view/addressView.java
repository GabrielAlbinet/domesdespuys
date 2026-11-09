package fr.simplon.domesdespuys.view;

public class addressView {

    public interface Id {}
    public interface Line {}
    public interface ExtraLine {}
    public interface City {}
    public interface ZipCode {}
    public interface Country {}
    public interface Phone {}
    public interface Firstname {}
    public interface Lastname {}
    public interface CreatedAt {}
    
    public interface AddressListView extends Id, Line, ExtraLine, City, ZipCode, Country, Phone, Firstname, Lastname {}
    public interface AddressDetailView extends AddressListView, CreatedAt {}
    public interface AddressResumeView extends Line, City, ZipCode {}

}