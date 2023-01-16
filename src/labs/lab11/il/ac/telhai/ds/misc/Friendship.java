package labs.lab11.il.ac.telhai.ds.misc;

import java.util.Date;

public class Friendship {
	Date start;

	public Friendship(Date start) {
		this.start = start;
	}

	@Override
	public String toString() {
		return start.toString();
	}

}
