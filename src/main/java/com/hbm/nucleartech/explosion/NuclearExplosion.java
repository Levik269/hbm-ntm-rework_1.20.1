package com.hbm.nucleartech.explosion;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import com.hbm.nucleartech.HBM;

import java.util.*;

@Mod.EventBusSubscriber(modid = HBM.MOD_ID)
public class NuclearExplosion {

    private static final int RAYS_PER_TICK = 128;
    private static final List<PendingExplosion> pending = new ArrayList<>();

    public static void explode(ServerLevel level, double x, double y, double z, NuclearBombType type) {
        pending.add(new PendingExplosion(level, x, y, z, type));
    }

    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        Iterator<PendingExplosion> iter = pending.iterator();
        while (iter.hasNext()) {
            PendingExplosion exp = iter.next();
            if (exp.isDone()) iter.remove();
            else exp.tick();
        }
    }

    private static class PendingExplosion {
        final ServerLevel level;
        final double x, y, z;
        final NuclearBombType type;
        final List<Vec3> rays;
        final Random rand = new Random();
        int index = 0;
        boolean craterDone = false;
        boolean raysDone = false;
        boolean fillDone = false;
        boolean smoothDone = false;

        PendingExplosion(ServerLevel level, double x, double y, double z, NuclearBombType type) {
            this.level = level;
            this.x = x; this.y = y; this.z = z;
            this.type = type;
            this.rays = fibonacciSphere(type.rayCount);
        }

        void tick() {

            // шаг 1 — воронка
            if (!craterDone) {
                int craterR = (int)(type.radius * 0.7f);
                BlockPos center = new BlockPos((int)x, (int)y, (int)z);
                for (int dx = -craterR; dx <= craterR; dx++)
                    for (int dy = -craterR; dy <= craterR; dy++)
                        for (int dz = -craterR; dz <= craterR; dz++) {
                            float ex = dx / type.scaleH;
                            float ey = dy / type.scaleV;
                            float ez = dz / type.scaleH;
                            if (ex*ex + ey*ey + ez*ez <= craterR*craterR)
                                level.setBlock(center.offset(dx, dy, dz),
                                        Blocks.AIR.defaultBlockState(), 3);
                        }
                craterDone = true;
                return;
            }

            // шаг 2 — лучи
            if (!raysDone) {
                int end = Math.min(index + RAYS_PER_TICK, rays.size());
                for (int i = index; i < end; i++) {
                    Vec3 dir = rays.get(i);
                    dir = new Vec3(
                            dir.x + (rand.nextDouble() - 0.5) * 0.35,
                            dir.y + (rand.nextDouble() - 0.5) * 0.35,
                            dir.z + (rand.nextDouble() - 0.5) * 0.35
                    ).normalize();
                    castRay(level, x, y, z, dir, type.radius, type.radius,
                            1.0f, 0, type.scaleH, type.scaleV,
                            type.hardThreshold, type.decayRate);
                }
                index = end;
                if (index >= rays.size()) raysDone = true;
                return;
            }

            // шаг 3 — заполняем пустоты внутри взрыва
            if (!fillDone) {
                fillDone = true;
                int fillR = (int)(type.radius * 0.9f);
                BlockPos center = new BlockPos((int)x, (int)y, (int)z);
                for (int dx = -fillR; dx <= fillR; dx++)
                    for (int dy = -fillR; dy <= fillR; dy++)
                        for (int dz = -fillR; dz <= fillR; dz++) {
                            float ex = dx / type.scaleH;
                            float ey = dy / type.scaleV;
                            float ez = dz / type.scaleH;
                            if (ex*ex + ey*ey + ez*ez > fillR*fillR) continue;
                            BlockPos pos = center.offset(dx, dy, dz);
                            if (level.getBlockState(pos).isAir()) continue;
                            int airCount = 0;
                            for (int[] n : new int[][]{{1,0,0},{-1,0,0},{0,1,0},{0,-1,0},{0,0,1},{0,0,-1}})
                                if (level.getBlockState(pos.offset(n[0], n[1], n[2])).isAir())
                                    airCount++;
                            if (airCount >= 4)
                                level.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
                        }
                return;
            }

            // шаг 4 — сглаживание щупалец (3 прохода)
            if (!smoothDone) {
                smoothDone = true;
                int smoothR = (int)(type.radius * type.smoothRadius);
                int craterR = (int)(type.radius * 0.7f);
                BlockPos center = new BlockPos((int)x, (int)y, (int)z);

                for (int pass = 0; pass < 3; pass++) {
                    List<BlockPos> toRemove = new ArrayList<>();
                    for (int dx = -smoothR; dx <= smoothR; dx++)
                        for (int dy = -smoothR; dy <= smoothR; dy++)
                            for (int dz = -smoothR; dz <= smoothR; dz++) {
                                float ex = dx / type.scaleH;
                                float ey = dy / type.scaleV;
                                float ez = dz / type.scaleH;
                                float distSq = ex*ex + ey*ey + ez*ez;
                                if (distSq <= craterR*craterR) continue;
                                if (distSq > smoothR*smoothR) continue;
                                BlockPos pos = center.offset(dx, dy, dz);
                                if (level.getBlockState(pos).isAir()) continue;
                                int airNeighborsCloser = 0;
                                int totalNeighborsCloser = 0;
                                for (int nx = -1; nx <= 1; nx++)
                                    for (int ny = -1; ny <= 1; ny++)
                                        for (int nz = -1; nz <= 1; nz++) {
                                            if (nx == 0 && ny == 0 && nz == 0) continue;
                                            float nex = (dx+nx) / type.scaleH;
                                            float ney = (dy+ny) / type.scaleV;
                                            float nez = (dz+nz) / type.scaleH;
                                            float nDistSq = nex*nex + ney*ney + nez*nez;
                                            if (nDistSq < distSq) {
                                                totalNeighborsCloser++;
                                                if (level.getBlockState(pos.offset(nx, ny, nz)).isAir())
                                                    airNeighborsCloser++;
                                            }
                                        }
                                if (totalNeighborsCloser > 0 && airNeighborsCloser * 3 >= totalNeighborsCloser * 2)
                                    toRemove.add(pos);
                            }
                    for (BlockPos pos : toRemove)
                        level.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
                    if (toRemove.isEmpty()) break;
                }
            }
        }

        boolean isDone() { return craterDone && raysDone && fillDone && smoothDone; }
    }

    private static void castRay(ServerLevel level, double ox, double oy, double oz,
                                Vec3 dir, float blastPower, float range,
                                float energy, int depth, float scaleH, float scaleV,
                                float hardThreshold, float decayRate) {
        if (energy <= 0.05f || depth > 3) return;

        float stepSize = 0.5f;
        float traveled = 0f;
        double cx = ox, cy = oy, cz = oz;
        float maxEnergy = energy;

        float absDirY = Math.abs((float)dir.y);
        float absDirH = (float)Math.sqrt(dir.x*dir.x + dir.z*dir.z);
        float scaledRange = range * (absDirH * scaleH + absDirY * scaleV);

        double stepX = dir.x * stepSize * scaleH;
        double stepY = dir.y * stepSize * scaleV;
        double stepZ = dir.z * stepSize * scaleH;
        double stepLen = Math.sqrt(stepX*stepX + stepY*stepY + stepZ*stepZ);

        if (scaledRange <= 0 || stepLen <= 0) return;

        while (traveled < scaledRange && energy > 0.05f) {
            cx += stepX;
            cy += stepY;
            cz += stepZ;
            traveled += stepLen;

            energy -= (decayRate / scaledRange) * (float)stepLen * maxEnergy;

            BlockPos pos = new BlockPos((int)Math.floor(cx), (int)Math.floor(cy), (int)Math.floor(cz));
            BlockState state = level.getBlockState(pos);

            if (state.isAir()) continue;

            float hardness = state.getDestroySpeed(level, pos);
            if (hardness < 0) continue;

            float energyCost = hardness / blastPower;

            if (hardness >= hardThreshold) {
                if (depth < 3) {
                    List<Vec3> deflected = getDeflectedRays(dir, 6);
                    float childEnergy = energy * 0.3f;
                    float remainingRange = scaledRange - traveled;
                    for (Vec3 dDir : deflected)
                        castRay(level, cx, cy, cz, dDir, blastPower,
                                remainingRange, childEnergy, depth + 1,
                                scaleH, scaleV, hardThreshold, decayRate);
                }
                energy = 0;
            } else if (energyCost < energy) {
                energy -= energyCost;
                level.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
            } else {
                energy = 0;
            }
        }
    }

    private static List<Vec3> getDeflectedRays(Vec3 original, int count) {
        List<Vec3> result = new ArrayList<>();
        Vec3 perp = Math.abs(original.x) < 0.9
                ? new Vec3(1, 0, 0).cross(original).normalize()
                : new Vec3(0, 1, 0).cross(original).normalize();
        for (int i = 0; i < count; i++) {
            double angle = 2 * Math.PI * i / count;
            Vec3 rotated = rotateAround(original, perp, Math.toRadians(45));
            Vec3 deflected = rotateAround(rotated, original, angle);
            result.add(deflected.normalize());
        }
        return result;
    }

    private static Vec3 rotateAround(Vec3 v, Vec3 axis, double angle) {
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);
        return v.scale(cos).add(axis.cross(v).scale(sin))
                .add(axis.scale(axis.dot(v) * (1 - cos)));
    }

    private static List<Vec3> fibonacciSphere(int count) {
        List<Vec3> points = new ArrayList<>();
        double phi = Math.PI * (3.0 - Math.sqrt(5.0));
        for (int i = 0; i < count; i++) {
            double y = 1 - (i / (double)(count - 1)) * 2;
            double r = Math.sqrt(1 - y * y);
            double theta = phi * i;
            points.add(new Vec3(Math.cos(theta) * r, y, Math.sin(theta) * r));
        }
        return points;
    }
}