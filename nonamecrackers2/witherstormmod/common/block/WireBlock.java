/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  com.google.common.collect.Maps
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.BlockPos$MutableBlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.core.Direction$Plane
 *  net.minecraft.core.Vec3i
 *  net.minecraft.core.particles.DustParticleOptions
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.item.context.BlockPlaceContext
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.LevelReader
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.Mirror
 *  net.minecraft.world.level.block.Rotation
 *  net.minecraft.world.level.block.state.BlockBehaviour$Properties
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.StateDefinition$Builder
 *  net.minecraft.world.level.block.state.properties.BlockStateProperties
 *  net.minecraft.world.level.block.state.properties.EnumProperty
 *  net.minecraft.world.level.block.state.properties.Property
 *  net.minecraft.world.level.block.state.properties.RedstoneSide
 *  net.minecraft.world.phys.shapes.CollisionContext
 *  net.minecraft.world.phys.shapes.Shapes
 *  net.minecraft.world.phys.shapes.VoxelShape
 *  org.joml.Vector3f
 */
package nonamecrackers2.witherstormmod.common.block;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import java.util.Map;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.RedstoneSide;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import nonamecrackers2.witherstormmod.common.init.WitherStormModBlocks;
import org.joml.Vector3f;

public class WireBlock
extends Block {
    public static final EnumProperty<RedstoneSide> NORTH = BlockStateProperties.f_61383_;
    public static final EnumProperty<RedstoneSide> EAST = BlockStateProperties.f_61382_;
    public static final EnumProperty<RedstoneSide> SOUTH = BlockStateProperties.f_61384_;
    public static final EnumProperty<RedstoneSide> WEST = BlockStateProperties.f_61385_;
    public static final Map<Direction, EnumProperty<RedstoneSide>> PROPERTY_BY_DIRECTION = Maps.newEnumMap((Map)ImmutableMap.of((Object)Direction.NORTH, NORTH, (Object)Direction.EAST, EAST, (Object)Direction.SOUTH, SOUTH, (Object)Direction.WEST, WEST));
    private static final VoxelShape SHAPE_DOT = Block.m_49796_((double)3.0, (double)0.0, (double)3.0, (double)13.0, (double)1.0, (double)13.0);
    private static final Map<Direction, VoxelShape> SHAPES_FLOOR = Maps.newEnumMap((Map)ImmutableMap.of((Object)Direction.NORTH, (Object)Block.m_49796_((double)3.0, (double)0.0, (double)0.0, (double)13.0, (double)1.0, (double)13.0), (Object)Direction.SOUTH, (Object)Block.m_49796_((double)3.0, (double)0.0, (double)3.0, (double)13.0, (double)1.0, (double)16.0), (Object)Direction.EAST, (Object)Block.m_49796_((double)3.0, (double)0.0, (double)3.0, (double)16.0, (double)1.0, (double)13.0), (Object)Direction.WEST, (Object)Block.m_49796_((double)0.0, (double)0.0, (double)3.0, (double)13.0, (double)1.0, (double)13.0)));
    private static final Map<Direction, VoxelShape> SHAPES_UP = Maps.newEnumMap((Map)ImmutableMap.of((Object)Direction.NORTH, (Object)Shapes.m_83110_((VoxelShape)SHAPES_FLOOR.get(Direction.NORTH), (VoxelShape)Block.m_49796_((double)3.0, (double)0.0, (double)0.0, (double)13.0, (double)16.0, (double)1.0)), (Object)Direction.SOUTH, (Object)Shapes.m_83110_((VoxelShape)SHAPES_FLOOR.get(Direction.SOUTH), (VoxelShape)Block.m_49796_((double)3.0, (double)0.0, (double)15.0, (double)13.0, (double)16.0, (double)16.0)), (Object)Direction.EAST, (Object)Shapes.m_83110_((VoxelShape)SHAPES_FLOOR.get(Direction.EAST), (VoxelShape)Block.m_49796_((double)15.0, (double)0.0, (double)3.0, (double)16.0, (double)16.0, (double)13.0)), (Object)Direction.WEST, (Object)Shapes.m_83110_((VoxelShape)SHAPES_FLOOR.get(Direction.WEST), (VoxelShape)Block.m_49796_((double)0.0, (double)0.0, (double)3.0, (double)1.0, (double)16.0, (double)13.0))));
    private final Map<BlockState, VoxelShape> shapesCache = Maps.newHashMap();
    protected final Vector3f color;

    public WireBlock(float r, float g, float b, BlockBehaviour.Properties properties) {
        super(properties);
        this.m_49959_((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)this.f_49792_.m_61090_()).m_61124_(NORTH, (Comparable)RedstoneSide.NONE)).m_61124_(EAST, (Comparable)RedstoneSide.NONE)).m_61124_(SOUTH, (Comparable)RedstoneSide.NONE)).m_61124_(WEST, (Comparable)RedstoneSide.NONE));
        for (BlockState state : this.m_49965_().m_61056_()) {
            this.shapesCache.put(state, this.calculateShape(state));
        }
        this.color = new Vector3f(r, g, b);
    }

    protected VoxelShape calculateShape(BlockState state) {
        VoxelShape voxelshape = SHAPE_DOT;
        for (Direction direction : Direction.Plane.HORIZONTAL) {
            RedstoneSide redstoneside = (RedstoneSide)state.m_61143_((Property)PROPERTY_BY_DIRECTION.get(direction));
            if (redstoneside == RedstoneSide.SIDE) {
                voxelshape = Shapes.m_83110_((VoxelShape)voxelshape, (VoxelShape)SHAPES_FLOOR.get(direction));
                continue;
            }
            if (redstoneside != RedstoneSide.UP) continue;
            voxelshape = Shapes.m_83110_((VoxelShape)voxelshape, (VoxelShape)SHAPES_UP.get(direction));
        }
        return voxelshape;
    }

    public VoxelShape m_5940_(BlockState state, BlockGetter reader, BlockPos pos, CollisionContext context) {
        return this.shapesCache.get(state);
    }

    public BlockState m_5573_(BlockPlaceContext context) {
        return this.getConnectionState((BlockGetter)context.m_43725_(), this.m_49966_(), context.m_8083_());
    }

    protected BlockState getConnectionState(BlockGetter reader, BlockState state, BlockPos pos) {
        boolean flag6;
        boolean flag = WireBlock.isDot(state);
        state = this.getMissingConnections(reader, this.m_49966_(), pos);
        if (flag && WireBlock.isDot(state)) {
            return state;
        }
        boolean flag1 = ((RedstoneSide)state.m_61143_(NORTH)).m_61761_();
        boolean flag2 = ((RedstoneSide)state.m_61143_(SOUTH)).m_61761_();
        boolean flag3 = ((RedstoneSide)state.m_61143_(EAST)).m_61761_();
        boolean flag4 = ((RedstoneSide)state.m_61143_(WEST)).m_61761_();
        boolean flag5 = !flag1 && !flag2;
        boolean bl = flag6 = !flag3 && !flag4;
        if (!flag4 && flag5) {
            state = (BlockState)state.m_61124_(WEST, (Comparable)RedstoneSide.SIDE);
        }
        if (!flag3 && flag5) {
            state = (BlockState)state.m_61124_(EAST, (Comparable)RedstoneSide.SIDE);
        }
        if (!flag1 && flag6) {
            state = (BlockState)state.m_61124_(NORTH, (Comparable)RedstoneSide.SIDE);
        }
        if (!flag2 && flag6) {
            state = (BlockState)state.m_61124_(SOUTH, (Comparable)RedstoneSide.SIDE);
        }
        return state;
    }

    protected BlockState getMissingConnections(BlockGetter reader, BlockState state, BlockPos pos) {
        boolean flag = !reader.m_8055_(pos.m_7494_()).m_60796_(reader, pos);
        for (Direction direction : Direction.Plane.HORIZONTAL) {
            if (((RedstoneSide)state.m_61143_((Property)PROPERTY_BY_DIRECTION.get(direction))).m_61761_()) continue;
            RedstoneSide redstoneside = this.getConnectingSide(reader, pos, direction, flag);
            state = (BlockState)state.m_61124_((Property)PROPERTY_BY_DIRECTION.get(direction), (Comparable)redstoneside);
        }
        return state;
    }

    public BlockState m_7417_(BlockState state, Direction direction, BlockState state1, LevelAccessor world, BlockPos pos, BlockPos pos1) {
        if (direction == Direction.DOWN) {
            return state;
        }
        if (direction == Direction.UP) {
            return this.getConnectionState((BlockGetter)world, state, pos);
        }
        RedstoneSide redstoneside = this.getConnectingSide((BlockGetter)world, pos, direction);
        return redstoneside.m_61761_() == ((RedstoneSide)state.m_61143_((Property)PROPERTY_BY_DIRECTION.get(direction))).m_61761_() && !WireBlock.isCross(state) ? (BlockState)state.m_61124_((Property)PROPERTY_BY_DIRECTION.get(direction), (Comparable)redstoneside) : this.getConnectionState((BlockGetter)world, (BlockState)this.m_49966_().m_61124_((Property)PROPERTY_BY_DIRECTION.get(direction), (Comparable)redstoneside), pos);
    }

    protected static boolean isCross(BlockState state) {
        return ((RedstoneSide)state.m_61143_(NORTH)).m_61761_() && ((RedstoneSide)state.m_61143_(SOUTH)).m_61761_() && ((RedstoneSide)state.m_61143_(EAST)).m_61761_() && ((RedstoneSide)state.m_61143_(WEST)).m_61761_();
    }

    protected static boolean isDot(BlockState state) {
        return !((RedstoneSide)state.m_61143_(NORTH)).m_61761_() && !((RedstoneSide)state.m_61143_(SOUTH)).m_61761_() && !((RedstoneSide)state.m_61143_(EAST)).m_61761_() && !((RedstoneSide)state.m_61143_(WEST)).m_61761_();
    }

    public void m_7742_(BlockState state, LevelAccessor world, BlockPos pos, int p_196248_4_, int p_196248_5_) {
        BlockPos.MutableBlockPos blockpos$mutable = new BlockPos.MutableBlockPos();
        for (Direction direction : Direction.Plane.HORIZONTAL) {
            RedstoneSide redstoneside = (RedstoneSide)state.m_61143_((Property)PROPERTY_BY_DIRECTION.get(direction));
            if (redstoneside == RedstoneSide.NONE || world.m_8055_((BlockPos)blockpos$mutable.m_122159_((Vec3i)pos, direction)).m_60713_((Block)this)) continue;
            blockpos$mutable.m_122173_(Direction.DOWN);
            BlockState blockstate = world.m_8055_((BlockPos)blockpos$mutable);
            if (!blockstate.m_60713_(Blocks.f_50455_)) {
                BlockPos blockpos = blockpos$mutable.m_121945_(direction.m_122424_());
                BlockState blockstate1 = blockstate.m_60728_(direction.m_122424_(), world.m_8055_(blockpos), world, (BlockPos)blockpos$mutable, blockpos);
                WireBlock.m_49908_((BlockState)blockstate, (BlockState)blockstate1, (LevelAccessor)world, (BlockPos)blockpos$mutable, (int)p_196248_4_, (int)p_196248_5_);
            }
            blockpos$mutable.m_122159_((Vec3i)pos, direction).m_122173_(Direction.UP);
            BlockState blockstate3 = world.m_8055_((BlockPos)blockpos$mutable);
            if (blockstate3.m_60713_(Blocks.f_50455_)) continue;
            BlockPos blockpos1 = blockpos$mutable.m_121945_(direction.m_122424_());
            BlockState blockstate2 = blockstate3.m_60728_(direction.m_122424_(), world.m_8055_(blockpos1), world, (BlockPos)blockpos$mutable, blockpos1);
            WireBlock.m_49908_((BlockState)blockstate3, (BlockState)blockstate2, (LevelAccessor)world, (BlockPos)blockpos$mutable, (int)p_196248_4_, (int)p_196248_5_);
        }
    }

    protected RedstoneSide getConnectingSide(BlockGetter reader, BlockPos pos, Direction direction) {
        return this.getConnectingSide(reader, pos, direction, !reader.m_8055_(pos.m_7494_()).m_60796_(reader, pos));
    }

    protected RedstoneSide getConnectingSide(BlockGetter reader, BlockPos pos, Direction direction, boolean upCheck) {
        boolean flag;
        BlockPos blockpos = pos.m_121945_(direction);
        BlockState blockstate = reader.m_8055_(blockpos);
        if (upCheck && (flag = this.canSurviveOn(reader, blockpos, blockstate)) && WireBlock.canConnectTo(reader.m_8055_(blockpos.m_7494_()), reader, blockpos.m_7494_(), null)) {
            if (blockstate.m_60783_(reader, blockpos, direction.m_122424_())) {
                return RedstoneSide.UP;
            }
            return RedstoneSide.SIDE;
        }
        return !WireBlock.canConnectTo(blockstate, reader, blockpos, direction) && (blockstate.m_60796_(reader, blockpos) || !WireBlock.canConnectTo(reader.m_8055_(blockpos.m_7495_()), reader, blockpos.m_7495_(), null)) ? RedstoneSide.NONE : RedstoneSide.SIDE;
    }

    public boolean m_7898_(BlockState state, LevelReader reader, BlockPos pos) {
        BlockPos blockpos = pos.m_7495_();
        BlockState blockstate = reader.m_8055_(blockpos);
        return this.canSurviveOn((BlockGetter)reader, blockpos, blockstate);
    }

    protected boolean canSurviveOn(BlockGetter reader, BlockPos pos, BlockState state) {
        return state.m_60783_(reader, pos, Direction.UP) || state.m_60713_(Blocks.f_50332_);
    }

    protected void checkCornerChangeAt(Level world, BlockPos pos) {
        if (world.m_8055_(pos).m_60713_((Block)this)) {
            world.m_46672_(pos, (Block)this);
            for (Direction direction : Direction.values()) {
                world.m_46672_(pos.m_121945_(direction), (Block)this);
            }
        }
    }

    public void m_6807_(BlockState state, Level world, BlockPos pos, BlockState state1, boolean flag) {
        if (!state1.m_60713_(state.m_60734_()) && !world.f_46443_) {
            for (Direction direction : Direction.Plane.VERTICAL) {
                world.m_46672_(pos.m_121945_(direction), (Block)this);
            }
            this.updateNeighborsOfNeighboringWires(world, pos);
        }
    }

    public void m_6810_(BlockState state, Level world, BlockPos pos, BlockState state1, boolean flag) {
        if (!flag && !state.m_60713_(state1.m_60734_())) {
            super.m_6810_(state, world, pos, state1, flag);
            if (!world.f_46443_) {
                for (Direction direction : Direction.values()) {
                    world.m_46672_(pos.m_121945_(direction), (Block)this);
                }
                this.updateNeighborsOfNeighboringWires(world, pos);
            }
        }
    }

    protected void updateNeighborsOfNeighboringWires(Level world, BlockPos pos) {
        for (Direction direction : Direction.Plane.HORIZONTAL) {
            this.checkCornerChangeAt(world, pos.m_121945_(direction));
        }
        for (Direction direction1 : Direction.Plane.HORIZONTAL) {
            BlockPos blockpos = pos.m_121945_(direction1);
            if (world.m_8055_(blockpos).m_60796_((BlockGetter)world, blockpos)) {
                this.checkCornerChangeAt(world, blockpos.m_7494_());
                continue;
            }
            this.checkCornerChangeAt(world, blockpos.m_7495_());
        }
    }

    public void m_6861_(BlockState state, Level world, BlockPos pos, Block block, BlockPos pos1, boolean flag) {
        if (!world.f_46443_ && !state.m_60710_((LevelReader)world, pos)) {
            WireBlock.m_49950_((BlockState)state, (Level)world, (BlockPos)pos);
            world.m_7471_(pos, false);
        }
    }

    protected static boolean canConnectTo(BlockState state, BlockGetter world, BlockPos pos, @Nullable Direction direction) {
        return state.m_60713_((Block)WitherStormModBlocks.TAINTED_DUST.get());
    }

    protected void spawnParticlesAlongLine(Level world, RandomSource random, BlockPos pos, Vector3f colors, Direction direction, Direction direction1, float f3, float f4) {
        float f = f4 - f3;
        if (!(random.m_188501_() >= 0.2f * f)) {
            float f1 = 0.4375f;
            float f2 = f3 + f * random.m_188501_();
            double d0 = 0.5 + (double)(f1 * (float)direction.m_122429_()) + (double)(f2 * (float)direction1.m_122429_());
            double d1 = 0.5 + (double)(f1 * (float)direction.m_122430_()) + (double)(f2 * (float)direction1.m_122430_());
            double d2 = 0.5 + (double)(f1 * (float)direction.m_122431_()) + (double)(f2 * (float)direction1.m_122431_());
            world.m_7106_((ParticleOptions)new DustParticleOptions(new Vector3f(colors.x(), colors.y(), colors.z()), 1.0f), (double)pos.m_123341_() + d0, (double)pos.m_123342_() + d1, (double)pos.m_123343_() + d2, 0.0, 0.0, 0.0);
        }
    }

    public void m_214162_(BlockState state, Level world, BlockPos pos, RandomSource random) {
        block4: for (Direction direction : Direction.Plane.HORIZONTAL) {
            RedstoneSide redstoneside = (RedstoneSide)state.m_61143_((Property)PROPERTY_BY_DIRECTION.get(direction));
            switch (redstoneside) {
                case UP: {
                    this.spawnParticlesAlongLine(world, random, pos, this.color, direction, Direction.UP, -0.5f, 0.5f);
                }
                case SIDE: {
                    this.spawnParticlesAlongLine(world, random, pos, this.color, Direction.DOWN, direction, 0.0f, 0.5f);
                    continue block4;
                }
            }
            this.spawnParticlesAlongLine(world, random, pos, this.color, Direction.DOWN, direction, 0.0f, 0.3f);
        }
    }

    public BlockState m_6843_(BlockState state, Rotation rotation) {
        switch (rotation) {
            case CLOCKWISE_180: {
                return (BlockState)((BlockState)((BlockState)((BlockState)state.m_61124_(NORTH, (Comparable)((RedstoneSide)state.m_61143_(SOUTH)))).m_61124_(EAST, (Comparable)((RedstoneSide)state.m_61143_(WEST)))).m_61124_(SOUTH, (Comparable)((RedstoneSide)state.m_61143_(NORTH)))).m_61124_(WEST, (Comparable)((RedstoneSide)state.m_61143_(EAST)));
            }
            case COUNTERCLOCKWISE_90: {
                return (BlockState)((BlockState)((BlockState)((BlockState)state.m_61124_(NORTH, (Comparable)((RedstoneSide)state.m_61143_(EAST)))).m_61124_(EAST, (Comparable)((RedstoneSide)state.m_61143_(SOUTH)))).m_61124_(SOUTH, (Comparable)((RedstoneSide)state.m_61143_(WEST)))).m_61124_(WEST, (Comparable)((RedstoneSide)state.m_61143_(NORTH)));
            }
            case CLOCKWISE_90: {
                return (BlockState)((BlockState)((BlockState)((BlockState)state.m_61124_(NORTH, (Comparable)((RedstoneSide)state.m_61143_(WEST)))).m_61124_(EAST, (Comparable)((RedstoneSide)state.m_61143_(NORTH)))).m_61124_(SOUTH, (Comparable)((RedstoneSide)state.m_61143_(EAST)))).m_61124_(WEST, (Comparable)((RedstoneSide)state.m_61143_(SOUTH)));
            }
        }
        return state;
    }

    public BlockState m_6943_(BlockState state, Mirror mirror) {
        switch (mirror) {
            case LEFT_RIGHT: {
                return (BlockState)((BlockState)state.m_61124_(NORTH, (Comparable)((RedstoneSide)state.m_61143_(SOUTH)))).m_61124_(SOUTH, (Comparable)((RedstoneSide)state.m_61143_(NORTH)));
            }
            case FRONT_BACK: {
                return (BlockState)((BlockState)state.m_61124_(EAST, (Comparable)((RedstoneSide)state.m_61143_(WEST)))).m_61124_(WEST, (Comparable)((RedstoneSide)state.m_61143_(EAST)));
            }
        }
        return super.m_6943_(state, mirror);
    }

    protected void m_7926_(StateDefinition.Builder<Block, BlockState> builder) {
        builder.m_61104_(new Property[]{NORTH, EAST, SOUTH, WEST});
    }

    public Vector3f getColor() {
        return this.color;
    }
}

