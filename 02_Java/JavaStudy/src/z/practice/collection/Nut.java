package z.practice.collection;

import java.util.Objects;

public class Nut extends Farm{
	private String name;

	public Nut() {
		super();
	}

	public Nut(String kind, String name) {
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
		return "견과: " + name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), name);
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Nut) {
			Nut n = (Nut)obj;
			if(n.getName().equals(this.name) && !super.equals(n.getKind())) {
				return true;
			}
		}
		return false;
	}
	
}
