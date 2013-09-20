package com.seitenbau.testing.dbunit.generator.values;

import java.util.Random;

public class BooleanGenerator implements ValueGenerator {

	private Random random;

	private final String[] values = { "true", "false" };

	@Override
	public void initialize(long seed) {
		random = new Random(seed);
	}

	@Override
	public String nextValue() {
		return values[random.nextInt(values.length)];
	}

}