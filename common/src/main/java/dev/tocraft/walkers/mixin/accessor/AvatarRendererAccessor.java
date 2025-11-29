package dev.tocraft.walkers.mixin.accessor;

import net.minecraft.client.renderer.entity.player.AvatarRenderer;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(AvatarRenderer.class)
public interface AvatarRendererAccessor {
    @Invoker("getRenderOffset")
    Vec3 callSuperGetRenderOffset(AvatarRenderState state);
}
