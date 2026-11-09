package fr.simplon.domesdespuys.view;

public class imageView {

    public interface Id {}
    public interface Path {}
    public interface Extension {}
    public interface IsDefault {}

    public interface ImageListView extends Id, Path, Extension, IsDefault {}
}