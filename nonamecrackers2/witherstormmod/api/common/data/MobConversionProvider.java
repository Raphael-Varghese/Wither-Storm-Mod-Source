package nonamecrackers2.witherstormmod.api.common.data;

import com.google.common.collect.Maps;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.nio.file.Path;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraftforge.registries.ForgeRegistries;
import nonamecrackers2.witherstormmod.common.resources.taint.MobConversion;

public abstract class MobConversionProvider
implements DataProvider {
    private final Map<EntityType<?>, MobConversion> conversions = Maps.newHashMap();
    private final Path outputPath;

    public MobConversionProvider(PackOutput output, String modid) {
        this.outputPath = output.m_247566_(PackOutput.Target.DATA_PACK).resolve(modid).resolve("tainting/entity");
    }

    protected abstract void addConversions();

    protected void add(MobConversion conversion) {
        if (this.conversions.containsKey(conversion.from())) {
            throw new IllegalArgumentException("Type '" + conversion.from() + "' is already mapped");
        }
        this.conversions.put(conversion.from(), conversion);
    }

    protected void add(EntityType<? extends Mob> from, EntityType<? extends Mob> to, boolean convertFromWitherSickness) {
        this.add(new MobConversion(from, to, convertFromWitherSickness));
    }

    protected void add(EntityType<? extends Mob> from, EntityType<? extends Mob> to) {
        this.add(from, to, true);
    }

    public CompletableFuture<?> m_213708_(CachedOutput output) {
        this.addConversions();
        return CompletableFuture.allOf((CompletableFuture[])this.conversions.values().stream().map(conversion -> {
            JsonObject object = new JsonObject();
            ResourceLocation from = ForgeRegistries.ENTITY_TYPES.getKey(conversion.from());
            ResourceLocation to = ForgeRegistries.ENTITY_TYPES.getKey(conversion.to());
            object.addProperty("from", from.toString());
            object.addProperty("to", to.toString());
            object.addProperty("convert_from_sickness", Boolean.valueOf(conversion.canBeConvertedFromWitherSickness()));
            return DataProvider.m_253162_((CachedOutput)output, (JsonElement)object, (Path)this.outputPath.resolve(from.m_135815_() + "_to_" + to.m_135815_() + ".json"));
        }).toArray(CompletableFuture[]::new));
    }

    public String m_6055_() {
        return "Mob conversions";
    }
}

