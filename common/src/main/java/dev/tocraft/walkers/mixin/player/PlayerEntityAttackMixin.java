package dev.tocraft.walkers.mixin.player;

import dev.tocraft.walkers.api.PlayerShape;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public abstract class PlayerEntityAttackMixin extends LivingEntity {

    private PlayerEntityAttackMixin(EntityType<? extends LivingEntity> entityType, Level world) {
        super(entityType, world);
    }

    @Inject(method = "attack", at = @At("HEAD"), cancellable = true)
    private void walkers$shapeAttack(Entity target, CallbackInfo ci) {
        LivingEntity shape = PlayerShape.getCurrentShape((Player) (Object) this);

        if (shape != null && this.level() instanceof ServerLevel serverLevel) {

            // 只在空手时让形态代替攻击
            if (this.getMainHandItem().isEmpty()) {
                try {
                    shape.doHurtTarget(serverLevel, target);
                    ci.cancel(); // 阻止原版攻击逻辑
                } catch (Exception ignored) {
                    // 某些生物没有自定义攻击逻辑，忽略即可
                }
            }
        }
    }
}
