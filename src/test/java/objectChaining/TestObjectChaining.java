package objectChaining;

import static objectChaining.ClassOne.MethodOne;

public class TestObjectChaining {

	public static void main(String[] args) {
		MethodOne().MethodTwo().MethodThree();

		ClassOne class1 = new ClassOne();
		class1.m1().m2();
	}

}
