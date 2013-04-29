package utility;

/**
 * Allows two objects to be stored together as a Pair even if they are of different types.
 * @author Jamison Bradley
 *
 * @param <E> The class type of the "Left" object.
 * @param <T> The class type of the "Right" object.
 */
public class Pair<E, T> {

	/**
	 * "Left" object stored in the Pair.
	 */
	private E left;
	/**
	 * "Right" object stored in the Pair.
	 */
	private T right;
	
	/**
	 * Constructs the pair with the two objects that it is going to store.
	 * @param left "Left" object
	 * @param right "Right" object
	 */
	public Pair(E left, T right){
		this.left = left;
		this.right = right;
	}
	
	public E getLeft(){
		return left;
	}
	
	public T getRight(){
		return right;
	}
}
