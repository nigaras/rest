package objectChaining;

public class ClassTwo {

	public static ClassThree MethodTwo() {
		System.out.println("Method two");
		return new ClassThree();
	}

}
