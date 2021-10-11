package oprpp1.p02.demo;

public class Sorter {

    /**
     * Method sorts a given array of exactly 2 array (returns new array)
     * @param polje init array
     * @return new sorted array
     * @throws NullPointerException if array passed is null
     * @throws IllegalArgumentException if array has more or less than 2 elems
     */
    public static int[] sortirajDvaElementa(int[] polje) {
        if (polje == null) throw new NullPointerException();
        if (polje.length != 2) throw new IllegalArgumentException("Polje treba imati točno dva elementa.");

        if (polje[0] > polje[1]) {
            return new int[]{polje[1], polje[0]};
        } else {
            return new int[]{polje[0], polje[1]};
        }
    }
}
