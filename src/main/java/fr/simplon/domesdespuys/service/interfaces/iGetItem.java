package fr.simplon.domesdespuys.service.interfaces;

import java.util.Optional;

public interface iGetItem<T>{

    T getById(String id);

}
