package fr.simplon.domesdespuys.service.interfaces;

public interface iUpdate<T, D> {

    T update(String id, D dto);

}
