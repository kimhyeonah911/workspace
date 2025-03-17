package z.practice.collection;

import java.util.Objects;

public class Fruit extends Farm{
	private String name;

	public Fruit() {
		super();
	}

	public Fruit(String kind, String name) {
		super(kind);
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "과일: " + name;
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), name);
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Fruit) {
			Fruit f = (Fruit)obj;
			if(f.getName().equals(this.name) && !super.equals(f.getKind())) {
				return true;
			}
		}
		return false;
		}
	
}
