package model.player;
import java.util.Objects;

/**
 * @param <X>
 * @param <Y>
 */

public class Pair<X,Y> {
	private  X x;
	private  Y y;
	
	public Pair(X x, Y y) {
		this.x = x;
		this.y = y;
	}


	/**
	 * @return x
	 */
	public X getX() {
		return x;
	}
	
	/**
	 * @param newX
	 */
	public void setX(X newX) {
		x=newX;
	}

	/**
	 * @return y
	 */
	public Y getY() {
		return y;
	}

	/**
	 * @param newY
	 */
	public void setY(Y newY) {
		y=newY;
	}

	/**
	 *@return int 
	 */
	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

	/**
	 * @param pair
	 * @return true o false
	 */
	public boolean equals(Pair<X, Y> pair) {
		return (this.x == pair.getX() && this.y == pair.getY());		
	}


	/**
	 * @return a string
	 */
	@Override
	public String toString() {
		return "[x=" + x + ", y=" + y + "]";
	}

}
