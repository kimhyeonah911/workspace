package z.practice.collection;

import java.util.Objects;

public class Vegetable extends Farm{
	private String name;

	public Vegetable() {
		super();
	}

	public Vegetable(String kind, String name) {
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
		return "채소: " + name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), name);
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Vegetable) {
			Vegetable v = (Vegetable)obj;
			if(v.getName().equals(this.name) && !super.equals(v.getKind())) {
				return true;
			}
		}
		return false;
	}
	
}
