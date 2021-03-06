package com.integral.enigmaticlegacy.triggers;

import javax.annotation.Nonnull;

import com.google.gson.JsonObject;
import com.integral.enigmaticlegacy.EnigmaticLegacy;
import com.integral.enigmaticlegacy.items.RevelationTome;

import net.minecraft.advancements.criterion.AbstractCriterionTrigger;
import net.minecraft.advancements.criterion.CriterionInstance;
import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.loot.ConditionArrayParser;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;

public class RevelationGainTrigger extends AbstractCriterionTrigger<RevelationGainTrigger.Instance> {
	public static final ResourceLocation ID = new ResourceLocation(EnigmaticLegacy.MODID, "embrace_revelation");
	public static final RevelationGainTrigger INSTANCE = new RevelationGainTrigger();

	private RevelationGainTrigger() {}

	@Nonnull
	@Override
	public ResourceLocation getId() {
		return RevelationGainTrigger.ID;
	}

	@Nonnull
	@Override
	public RevelationGainTrigger.Instance deserializeTrigger(@Nonnull JsonObject json, @Nonnull EntityPredicate.AndPredicate playerPred, ConditionArrayParser conditions) {
		return new RevelationGainTrigger.Instance(playerPred, JSONUtils.getString(json, "point_type"), JSONUtils.getInt(json, "point_amount"));
	}

	public void trigger(ServerPlayerEntity player, RevelationTome.TomeType type, int amount) {
		this.triggerListeners(player, instance -> instance.test(type, amount));
	}

	static class Instance extends CriterionInstance {
		private final String revelationType;
		private final int requiredAmount;

		Instance(EntityPredicate.AndPredicate playerPred, String type, int amount) {
			super(RevelationGainTrigger.ID, playerPred);
			this.revelationType = type;
			this.requiredAmount = amount;
		}

		@Nonnull
		@Override
		public ResourceLocation getId() {
			return RevelationGainTrigger.ID;
		}

		boolean test(RevelationTome.TomeType type, int count) {
			boolean success = RevelationTome.TomeType.resolveType(this.revelationType).equals(type) && count >= this.requiredAmount;
			//System.out.println("Requested amount: " + this.requiredAmount + ", provided amount: " + count + ", requested/provided revelation type: " + this.revelationType + "/" + type.registryName +  ", success: " + success);
			return success;
		}
	}
}

