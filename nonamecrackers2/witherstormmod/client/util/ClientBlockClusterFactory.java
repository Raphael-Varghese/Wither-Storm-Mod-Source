/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.level.Level
 *  net.minecraftforge.network.PlayMessages$SpawnEntity
 */
package nonamecrackers2.witherstormmod.client.util;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PlayMessages;
import nonamecrackers2.witherstormmod.client.entity.ClientBlockClusterEntity;
import nonamecrackers2.witherstormmod.common.entity.BlockClusterEntity;
import nonamecrackers2.witherstormmod.common.init.WitherStormModEntityTypes;

public class ClientBlockClusterFactory {
    public static ClientBlockClusterEntity make(PlayMessages.SpawnEntity packet, Level level) {
        return new ClientBlockClusterEntity((EntityType<? extends BlockClusterEntity>)((EntityType)WitherStormModEntityTypes.BLOCK_CLUSTER.get()), level);
    }
}

