package one.digitalinnovation.singleton;

/**
 * Singleton "preguiçoso".
 *
 * @author Mariana Molina
 */

public class SingletonLazy {
    private static SingletonLazy instancia;

    private SingletonLazy() {
        super();
    }

    public static SingletonLazy getInstancia() {
        if (instancia == null) {
            instancia = new SingletonLazy();
        }
        return instancia;
    }
}
