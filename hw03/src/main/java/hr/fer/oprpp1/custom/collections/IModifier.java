package hr.fer.oprpp1.custom.collections;

/**
 * The interface Modifier.
 *
 * @param <T> return type
 * @param <K> input type
 * @author franzekan
 */
public interface IModifier<T, K> {
    /**
     * Modify t.
     *
     * @param t the t
     * @return the t
     */
    T modify(K t);
}
