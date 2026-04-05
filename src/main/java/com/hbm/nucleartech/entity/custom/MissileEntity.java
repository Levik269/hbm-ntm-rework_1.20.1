package com.hbm.nucleartech.entity.custom;

import com.hbm.nucleartech.entity.HbmEntities;
import com.hbm.nucleartech.explosion.NuclearBombType;
import com.hbm.nucleartech.explosion.NuclearExplosion;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;

public class MissileEntity extends Entity {

    // тип взрыва
    private NuclearBombType bombType = NuclearBombType.STANDARD;

    // цель
    private double targetX, targetY, targetZ;

    // физика полёта
    private double velX, velY, velZ;
    private int flightTime = 0;
    private int maxFlightTime = 200; // тики до взрыва по таймеру

    // фаза полёта
    private boolean launched = false;

    private static final EntityDataAccessor<Float> ROLL =
            SynchedEntityData.defineId(MissileEntity.class, EntityDataSerializers.FLOAT);

    public MissileEntity(EntityType<? extends MissileEntity> type, Level level) {
        super(type, level);
    }

    // Used by typed entity factories (MISSILE_TACTICAL etc.) so /summon gives the right bomb type by default
    public MissileEntity(EntityType<? extends MissileEntity> type, Level level, NuclearBombType defaultType) {
        super(type, level);
        this.bombType = defaultType;
    }

    public MissileEntity(Level level, double x, double y, double z,
                          double targetX, double targetY, double targetZ,
                          NuclearBombType bombType) {
        this(HbmEntities.MISSILE.get(), level);
        setPos(x, y, z);
        this.targetX = targetX;
        this.targetY = targetY;
        this.targetZ = targetZ;
        this.bombType = bombType;
        this.launched = true;
        calculateTrajectory();
    }

    private void calculateTrajectory() {
        double dx = targetX - getX();
        double dz = targetZ - getZ();
        double horizontalDist = Math.sqrt(dx*dx + dz*dz);

        // время полёта зависит от дистанции
        maxFlightTime = (int)(horizontalDist * 0.8 + 60);
        maxFlightTime = Math.max(60, Math.min(maxFlightTime, 600));

        // горизонтальные скорости
        velX = dx / maxFlightTime;
        velZ = dz / maxFlightTime;

        // вертикальная скорость — баллистическая дуга
        // v0y = (targetY - y) / t + g * t / 2
        double gravity = 0.05;
        velY = (targetY - getY()) / maxFlightTime + gravity * maxFlightTime / 2.0;
    }

    @Override
    public void tick() {
        super.tick();
        if (level().isClientSide) return;
        if (!launched) return;

        flightTime++;

        // гравитация
        velY -= 0.05;

        // движение
        double newX = getX() + velX;
        double newY = getY() + velY;
        double newZ = getZ() + velZ;

        setPos(newX, newY, newZ);

        // поворот ракеты по направлению движения
        Vec3 vel = new Vec3(velX, velY, velZ);
        if (vel.lengthSqr() > 0.001) {
            double yaw = Math.toDegrees(Math.atan2(-velX, -velZ));
            double pitch = Math.toDegrees(Math.atan2(-velY, Math.sqrt(velX*velX + velZ*velZ)));
            setYRot((float)yaw);
            setXRot((float)pitch);
        }

        // проверяем столкновение с блоком
        if (!level().isEmptyBlock(blockPosition())) {
            detonate();
            return;
        }

        // взрываемся у цели или по таймеру
        double distToTarget = Math.sqrt(
                Math.pow(getX() - targetX, 2) +
                Math.pow(getY() - targetY, 2) +
                Math.pow(getZ() - targetZ, 2)
        );

        if (distToTarget < 3.0 || flightTime >= maxFlightTime) {
            detonate();
        }
    }

    private void detonate() {
        if (!level().isClientSide && level() instanceof ServerLevel serverLevel) {
            NuclearExplosion.explode(serverLevel, getX(), getY(), getZ(), bombType);
        }
        discard();
    }

    @Override
    protected void defineSynchedData() {
        entityData.define(ROLL, 0f);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        targetX = tag.getDouble("targetX");
        targetY = tag.getDouble("targetY");
        targetZ = tag.getDouble("targetZ");
        velX = tag.getDouble("velX");
        velY = tag.getDouble("velY");
        velZ = tag.getDouble("velZ");
        flightTime = tag.getInt("flightTime");
        maxFlightTime = tag.getInt("maxFlightTime");
        launched = tag.getBoolean("launched");
        String bombTypeName = tag.getString("bombType");
        bombType = getBombTypeByName(bombTypeName);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        tag.putDouble("targetX", targetX);
        tag.putDouble("targetY", targetY);
        tag.putDouble("targetZ", targetZ);
        tag.putDouble("velX", velX);
        tag.putDouble("velY", velY);
        tag.putDouble("velZ", velZ);
        tag.putInt("flightTime", flightTime);
        tag.putInt("maxFlightTime", maxFlightTime);
        tag.putBoolean("launched", launched);
        tag.putString("bombType", bombType.name);
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    private NuclearBombType getBombTypeByName(String name) {
        return switch (name) {
            case "tactical" -> NuclearBombType.TACTICAL;
            case "thermonuclear" -> NuclearBombType.THERMONUCLEAR;
            case "bunker_buster" -> NuclearBombType.BUNKER_BUSTER;
            case "cobalt" -> NuclearBombType.COBALT;
            default -> NuclearBombType.STANDARD;
        };
    }
}
