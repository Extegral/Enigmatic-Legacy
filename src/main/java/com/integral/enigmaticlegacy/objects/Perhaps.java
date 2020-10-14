package com.integral.enigmaticlegacy.objects;

import java.util.Random;

import com.integral.enigmaticlegacy.items.EyeOfNebula;

/**
 * A percentage-based value that can be converted into factor,
 * or 1.0 + factor in case it's required.
 * @author Integral
 */

public class Perhaps {

	private static final Random theySeeMeRollin = new Random();
	private int probability;

	public Perhaps(int probability) {
		this.probability = probability;
	}

	public boolean roll() {
		return Math.random() <= this.asMultiplier(false);
	}

	public int asPercentage() {
		return this.probability;
	}

	public double asMultiplier(boolean baseOne) {
		if (baseOne)
			return 1.0D + (this.probability / 100D);
		else
			return this.probability / 100D;

	}

	public double asMultiplierInverted() {
		return 1.0D - (this.probability / 100D);
	}

	public float asModifier(boolean baseOne) {
		if (baseOne)
			return 1.0F + (this.probability / 100F);
		else
			return this.probability / 100F;

	}

	public float asModifierInverted() {
		return 1.0F - (this.probability / 100F);
	}

}
