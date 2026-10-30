package nonamecrackers2.witherstormmod.api.common.entity;

import javax.annotation.Nullable;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

public interface WitherStormBase {
    public float getMouthAnimation(int var1, float var2);

    public float getBrokenJawAnimation(int var1, float var2);

    public float getFadeAnimation(float var1);

    public float getFadeAnimation();

    public float getTentacleAnimation(float var1);

    public Vec3 getHeadPos(int var1);

    public float getHeadYRot(int var1);

    public float getHeadYRotO(int var1);

    public float getHeadXRot(int var1);

    public float getHeadXRotO(int var1);

    public float getXBodyRot();

    public float getXBodyRotO();

    public boolean areOtherHeadsDisabled();

    public boolean isHeadInjured(int var1);

    default public boolean canBeDistracted(int head, DistractionType type) {
        return this.tractorBeamActive(head);
    }

    default public boolean isDistracted(int head) {
        return this.getDistractedPos(head) != null;
    }

    @Nullable
    public Vec3 getDistractedPos(int var1);

    public void setDistractedPos(int var1, @Nullable Vec3 var2);

    public void makeDistracted(Vec3 var1, int var2, int var3);

    public void setLookAt(int var1, @Nullable Vec3 var2, int var3);

    public float getHeadShakeAnim(int var1, float var2);

    @Nullable
    public LivingEntity getTarget(int var1);

    public void setTarget(int var1, @Nullable LivingEntity var2);

    public boolean canSee(int var1, Entity var2);

    public boolean isPosBehindBack(Vec3 var1);

    public boolean isDeadOrPlayingDead();

    public boolean isPlayingDead();

    default public boolean isEntityBehindBack(Entity entity) {
        return this.isPosBehindBack(entity.m_20182_());
    }

    default public void setLookAt(int head, @Nullable Vec3 pos) {
        this.setLookAt(head, pos, 3);
    }

    default public int getTotalHeads() {
        return 3;
    }

    default public double getTractorBeamCutoffDistance(int head) {
        return -1.0;
    }

    default public boolean tractorBeamActive(int head) {
        boolean flag = false;
        flag = this.areOtherHeadsDisabled() ? head == 0 : true;
        return flag && !this.isHeadInjured(head);
    }

    default public Vec3 getViewVector(float x, float y, float range) {
        float f = x * ((float)Math.PI / 180);
        float f1 = -y * ((float)Math.PI / 180);
        float f2 = Mth.m_14089_((float)f1);
        float f3 = Mth.m_14031_((float)f1);
        float f4 = Mth.m_14089_((float)f);
        float f5 = Mth.m_14031_((float)f);
        return new Vec3((double)(f3 * f4) * (double)range, (double)(-f5) * (double)range, (double)(f2 * f4) * (double)range);
    }

    public static enum DistractionType {
        ENTITY_BASED,
        STRUCTURES;

    }
}

