package fr.marcjus.mod.entities;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

@Deprecated
public class EntityMagiBall extends EntityThrowable {

	public EntityMagiBall(World world) {
		super(world);
	}

	public EntityMagiBall(World world, EntityLivingBase thrower) {
		super(world, thrower);
	}

	public EntityMagiBall(World world, double x, double y, double z) {
		super(world, x, y, z);
	}

	@Override
	public void onImpact(RayTraceResult result) {
		if (!this.world.isRemote) {
			setDead();
			if (result.entityHit != null) {
				result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.thrower), rand.nextFloat());
				if (result.entityHit instanceof EntityLivingBase) {
					EntityLivingBase base = (EntityLivingBase) result.entityHit;
					base.motionY += 0.5;
				}

			}
		}
	}

}
