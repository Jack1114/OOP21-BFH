package model.player;

/**
 * @author Olivia
 *
 */

import java.util.Objects;

public class Pair<X,Y> {
	private  X x;
	private  Y y;
	
	public Pair(X x, Y y) {
		this.x = x;
		this.y = y;
	}


	public X getX() {
		return x;
	}
	
	public void setX(X newX) {
		x=newX;
	}

	public Y getY() {
		return y;
	}

	public void setY(Y newY) {
		y=newY;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

	public boolean equals(Pair<X, Y> pair) {
		return (this.x == pair.getX() && this.y == pair.getY());		
	}


	@Override
	public String toString() {
		return "[x=" + x + ", y=" + y + "]";
	}

}
