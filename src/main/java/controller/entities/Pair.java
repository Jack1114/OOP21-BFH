package controller.entities;

/**
 * @author Olivia
 *
 */

import java.util.Objects;

public class Pair<X,Y> {
	private final X x;
	private final Y y;
	
	//tutto generato automaticamente

	public Pair(X x, Y y) {
		this.x = x;
		this.y = y;
		
	
	}


	public X getX() {
		return x;
	}


	public Y getY() {
		return y;
	}


	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		@SuppressWarnings("rawtypes")
		Pair other = (Pair) obj;
		return Objects.equals(x, other.x) && Objects.equals(y, other.y);
	}


	@Override
	public String toString() {
		return "Pair [x=" + x + ", y=" + y + "]";
	}

}
