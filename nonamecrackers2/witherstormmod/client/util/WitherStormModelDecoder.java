/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.geom.builders.CubeDeformation
 *  net.minecraft.client.model.geom.builders.CubeListBuilder
 *  net.minecraftforge.fml.ModList
 *  net.minecraftforge.forgespi.locating.IModFile
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package nonamecrackers2.witherstormmod.client.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.Scanner;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.forgespi.locating.IModFile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WitherStormModelDecoder {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final int VERSION = 0;

    public static void load(String id, CubeListBuilder builder, CubeDeformation def, float texScale) {
        IModFile modFile = ModList.get().getModFileById("witherstormmod").getFile();
        Path path = modFile.findResource(new String[]{"assets/witherstormmod/models/witherstorm/" + id + ".wsm"});
        File file = new File(path.toString());
        try {
            Scanner scanner = new Scanner(file);
            boolean checkedVersion = false;
            while (scanner.hasNextLine()) {
                String[] functions;
                String line = scanner.nextLine();
                if (!checkedVersion) {
                    if (line.startsWith("v")) {
                        try {
                            if (Integer.valueOf(line.split("v")[0]) == 0) {
                                checkedVersion = true;
                                continue;
                            }
                            LOGGER.error("Outdated model version, please update to {}", (Object)0);
                        }
                        catch (Exception e) {
                            LOGGER.error("Failed to parse version for '{}'", (Object)id);
                            e.printStackTrace();
                        }
                        break;
                    }
                    LOGGER.error("Invalid version string. Must be of format 'v{version}'");
                    continue;
                }
                for (String function : functions = line.split(";")) {
                    try {
                        String[] values;
                        String param;
                        if (function.startsWith("texOffs")) {
                            param = function.replaceAll("texOffs(", "").replaceAll(")", "");
                            values = param.split(",");
                            int texX = Integer.valueOf(values[0]);
                            int texY = Integer.valueOf(values[1]);
                            builder.m_171514_(texX, texY);
                            continue;
                        }
                        if (function.startsWith("addBox")) {
                            param = function.replaceAll("addBox(", "").replaceAll(")", "");
                            values = param.split(",");
                            float f = Float.valueOf(values[0]).floatValue();
                            float f1 = Float.valueOf(values[0]).floatValue();
                            float f2 = Float.valueOf(values[0]).floatValue();
                            float f3 = Float.valueOf(values[0]).floatValue();
                            float f4 = Float.valueOf(values[0]).floatValue();
                            float f5 = Float.valueOf(values[0]).floatValue();
                            builder.m_171496_(f, f1, f2, f3, f4, f5, def, texScale, texScale);
                            continue;
                        }
                        LOGGER.warn("Unknown function '{}'. Ignoring", (Object)function);
                    }
                    catch (Exception e) {
                        LOGGER.error("Failed to parse function");
                        e.printStackTrace();
                    }
                }
            }
            scanner.close();
        }
        catch (FileNotFoundException e) {
            LOGGER.warn("Failed to find model with id '{}'", (Object)id);
            e.printStackTrace();
        }
    }
}

