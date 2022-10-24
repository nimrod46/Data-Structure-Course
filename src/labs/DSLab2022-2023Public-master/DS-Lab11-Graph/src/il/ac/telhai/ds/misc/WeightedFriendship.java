import java.util.Date;

import il.ac.telhai.ds.graph.Weighted;

public class WeightedFriendship implements Weighted {
	Date start;
	Double weight;

	public WeightedFriendship(Date start, double weight) {
		this.start = start;
		this.weight= weight;
	}

	@Override
	public String toString() {
		return start.toString();
	}

	public double getWeight() {
		return weight;
	}
}