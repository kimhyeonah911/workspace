package z.practice.collection;

import java.util.Objects;

public class Farm {
	private String kind;

	public Farm() {
		super();
	}

	public Farm(String kind) {
		super();
		this.kind = kind;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	@Override
	public String toString() {
		return "(" + kind + ")";
	}

	@Override
	public int hashCode() {
		return Objects.hash(kind);
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Fruit) {
			Farm f = (Fruit)obj;
			if(f.getKind().equals(this.kind)) {
				return true;
			}
		}
		return false;
	}
	
}
