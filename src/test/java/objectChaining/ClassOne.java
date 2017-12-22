package objectChaining;

public class ClassOne {

	public static ClassTwo MethodOne() {
		System.out.println("Method one");
		return new ClassTwo();
	}

	public ClassOne m1() {
		System.out.println("M1");
		return this;
	}

	public ClassOne m2() {
		System.out.println("M2");
		return this;
	}
}
