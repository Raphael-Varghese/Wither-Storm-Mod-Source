/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.nbt.Tag
 *  net.minecraft.network.FriendlyByteBuf
 *  net.minecraft.network.syncher.EntityDataSerializer
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.phys.Vec2
 *  net.minecraft.world.phys.Vec3
 *  net.minecraftforge.registries.DeferredRegister
 *  net.minecraftforge.registries.ForgeRegistries$Keys
 */
package nonamecrackers2.witherstormmod.common.serializer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import nonamecrackers2.witherstormmod.api.common.ai.symbiont.SpellType;
import nonamecrackers2.witherstormmod.api.common.registry.WitherStormModRegistries;
import nonamecrackers2.witherstormmod.common.entity.CommandBlockEntity;
import nonamecrackers2.witherstormmod.common.entity.WitheredSymbiontEntity;
import nonamecrackers2.witherstormmod.common.util.WitherStormModNBTUtil;

public class WitherStormModDataSerializers {
    public static final DeferredRegister<EntityDataSerializer<?>> DATA_SERIALIZERS = DeferredRegister.create((ResourceKey)ForgeRegistries.Keys.ENTITY_DATA_SERIALIZERS, (String)"witherstormmod");
    public static final EntityDataSerializer<Map<BlockPos, BlockState>> BLOCK_STATE_POS_MAP = new EntityDataSerializer<Map<BlockPos, BlockState>>(){

        public void write(FriendlyByteBuf buffer, Map<BlockPos, BlockState> map) {
            buffer.m_236831_(map, (buf, pos) -> buf.m_130064_(pos), (buf, state) -> buf.m_130130_(Block.m_49956_((BlockState)state)));
        }

        public Map<BlockPos, BlockState> read(FriendlyByteBuf buffer) {
            return buffer.m_236847_(buf -> buf.m_130135_(), buf -> Block.m_49803_((int)buf.m_130242_()));
        }

        public Map<BlockPos, BlockState> copy(Map<BlockPos, BlockState> map) {
            return new HashMap<BlockPos, BlockState>(map);
        }
    };
    public static final EntityDataSerializer<List<CompoundTag>> COMPOUND_LIST = new EntityDataSerializer<List<CompoundTag>>(){

        public void write(FriendlyByteBuf buffer, List<CompoundTag> list) {
            CompoundTag compound = new CompoundTag();
            compound.m_128365_("List", (Tag)WitherStormModNBTUtil.writeCompoundList(list));
            buffer.m_130079_(compound);
        }

        public List<CompoundTag> read(FriendlyByteBuf buffer) {
            CompoundTag compound = buffer.m_130260_();
            return WitherStormModNBTUtil.readCompoundList(compound.m_128437_("List", 10));
        }

        public List<CompoundTag> copy(List<CompoundTag> list) {
            return new ArrayList<CompoundTag>(list);
        }
    };
    public static final EntityDataSerializer<Vec2> VECTOR_2F = new EntityDataSerializer<Vec2>(){

        public void write(FriendlyByteBuf buffer, Vec2 vector) {
            buffer.writeFloat(vector.f_82470_);
            buffer.writeFloat(vector.f_82471_);
        }

        public Vec2 read(FriendlyByteBuf buffer) {
            return new Vec2(buffer.readFloat(), buffer.readFloat());
        }

        public Vec2 copy(Vec2 vector) {
            return new Vec2(vector.f_82470_, vector.f_82471_);
        }
    };
    public static final EntityDataSerializer<Optional<Vec3>> OPTIONAL_VECTOR_3D = new EntityDataSerializer<Optional<Vec3>>(){

        public void write(FriendlyByteBuf buffer, Optional<Vec3> vector) {
            buffer.writeBoolean(vector.isPresent());
            vector.ifPresent(pos -> {
                buffer.writeDouble(pos.m_7096_());
                buffer.writeDouble(pos.m_7098_());
                buffer.writeDouble(pos.m_7094_());
            });
        }

        public Optional<Vec3> read(FriendlyByteBuf buffer) {
            return buffer.readBoolean() ? Optional.of(new Vec3(buffer.readDouble(), buffer.readDouble(), buffer.readDouble())) : Optional.empty();
        }

        public Optional<Vec3> copy(Optional<Vec3> vector) {
            return vector;
        }
    };
    public static final EntityDataSerializer<SpellType> SPELL_TYPE = new EntityDataSerializer<SpellType>(){

        public void write(FriendlyByteBuf buffer, SpellType type) {
            buffer.writeRegistryId(WitherStormModRegistries.SPELL_TYPES.get(), (Object)type);
        }

        public SpellType read(FriendlyByteBuf buffer) {
            return (SpellType)buffer.readRegistryId();
        }

        public SpellType copy(SpellType type) {
            return type;
        }
    };
    public static final EnumDataSerializer<CommandBlockEntity.Mode> MODE_ENUM = new EnumDataSerializer<CommandBlockEntity.Mode>(CommandBlockEntity.Mode.class);
    public static final EnumDataSerializer<CommandBlockEntity.State> STATE_ENUM = new EnumDataSerializer<CommandBlockEntity.State>(CommandBlockEntity.State.class);
    public static final EnumDataSerializer<WitheredSymbiontEntity.BossfightStage> BOSSFIGHT_STAGE_ENUM = new EnumDataSerializer<WitheredSymbiontEntity.BossfightStage>(WitheredSymbiontEntity.BossfightStage.class);

    static {
        DATA_SERIALIZERS.register("block_state_pos_map", () -> BLOCK_STATE_POS_MAP);
        DATA_SERIALIZERS.register("compound_list", () -> COMPOUND_LIST);
        DATA_SERIALIZERS.register("vector2f", () -> VECTOR_2F);
        DATA_SERIALIZERS.register("mode_enum", () -> MODE_ENUM);
        DATA_SERIALIZERS.register("state_enum", () -> STATE_ENUM);
        DATA_SERIALIZERS.register("bossfight_state_enum", () -> BOSSFIGHT_STAGE_ENUM);
        DATA_SERIALIZERS.register("vector3d", () -> OPTIONAL_VECTOR_3D);
        DATA_SERIALIZERS.register("spell_type", () -> SPELL_TYPE);
    }

    public static class EnumDataSerializer<T extends Enum<T>>
    implements EntityDataSerializer<T> {
        private final Class<T> enumClass;

        private EnumDataSerializer(Class<T> enumClass) {
            this.enumClass = enumClass;
        }

        public void write(FriendlyByteBuf buffer, T enub) {
            buffer.m_130068_(enub);
        }

        public T read(FriendlyByteBuf buffer) {
            return (T)buffer.m_130066_(this.enumClass);
        }

        public T copy(T enub) {
            return enub;
        }
    }
}

