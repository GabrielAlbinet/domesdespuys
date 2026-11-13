package fr.simplon.domesdespuys.view;

public class ExceptionView {
    public interface Message {}
    public interface Status {}
    public interface Name {}

    public interface ExceptionViewFull extends
            Message,
            Status,
            Name {}
}