package fr.yuki.yrpf.manager;

import eu.bebendorf.ajorm.Repo;
import fr.yuki.yrpf.enums.JobEnum;
import fr.yuki.yrpf.job.tools.Generator;
import fr.yuki.yrpf.job.tools.GrowBox;
import fr.yuki.yrpf.model.GrowboxModel;
import fr.yuki.yrpf.model.JobTool;
import fr.yuki.yrpf.net.payload.GrowboxFillWaterPotPayload;
import net.onfirenetwork.onsetjava.Onset;
import net.onfirenetwork.onsetjava.data.Vector;
import net.onfirenetwork.onsetjava.entity.Player;

import java.util.stream.Collectors;

public class GrowboxManager {
    public static void init() {
        // Load growboxes
        for(GrowboxModel growboxModel : Repo.get(GrowboxModel.class).all()) {
            JobTool jobTool = new JobTool();
            jobTool.setId(-1);
            jobTool.setModelId(50007);
            jobTool.setName("GrowBox");
            jobTool.setJobType("WEED");
            jobTool.setLevelRequired(1);
            jobTool.setReward(0);
            jobTool.setX(growboxModel.getX());
            jobTool.setY(growboxModel.getY());
            jobTool.setZ(growboxModel.getZ());
            jobTool.setRX(growboxModel.getRx());
            jobTool.setRY(growboxModel.getRy());
            jobTool.setRZ(growboxModel.getRz());
            jobTool.setSX(1);
            jobTool.setSY(1);
            jobTool.setSZ(1);
            jobTool.setJobToolType("GROWBOX");
            jobTool.setCanShowText(false);
            ((GrowBox)jobTool.getJobToolHandler()).setGrowboxModel(growboxModel);
            JobManager.getJobTools().add(jobTool);
            jobTool.spawn(JobManager.getJobs().get(JobEnum.WEED));
        }

        Onset.timer(10000, () -> {
            tickGrow();
        });

        Onset.timer(20000, () -> {
            tickGenerator();
        });
    }

    public static void tickGrow() {
        try {
            for(JobTool jobTool : JobManager.getJobTools().stream().filter(x -> x.getJobToolType().toLowerCase().equals("growbox"))
                .collect(Collectors.toList())) {
                try {
                    GrowBox growBox = (GrowBox)jobTool.getJobToolHandler();
                    growBox.tickGrow();
                }catch (Exception ex) {
                    ex.printStackTrace();
                    Onset.print("Can't tick single grow: " + ex.toString());
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            Onset.print("Can't tick grow: " + ex.toString());
        }
    }

    public static void tickGenerator() {
        try {
            for(JobTool jobTool : JobManager.getJobTools().stream().filter(x -> x.getJobToolType().toLowerCase().equals("generator"))
                    .collect(Collectors.toList())) {
                try {
                    Generator generator = (Generator) jobTool.getJobToolHandler();
                    generator.tickFuel();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    Onset.print("Can't tick single generator: " + ex.toString());
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();;
            Onset.print("Can't tick generator: " + ex.toString());
        }
    }

    public static Generator getGeneratorNearby(Vector position, int distance) {
        for(JobTool jobTool : JobManager.getJobTools().stream().filter(x -> x.getJobToolType().toLowerCase().equals("generator"))
                .collect(Collectors.toList())) {
            Generator generator = (Generator)jobTool.getJobToolHandler();
            if(new Vector(jobTool.getX(), jobTool.getY(), jobTool.getZ()).distance(position) <= distance) {
                return generator;
            }
        }
        return null;
    }

    public static Generator getGeneratorOnNearby(Vector position, int distance) {
        for(JobTool jobTool : JobManager.getJobTools().stream().filter(x -> x.getJobToolType().toLowerCase().equals("generator"))
                .collect(Collectors.toList())) {
            Generator generator = (Generator)jobTool.getJobToolHandler();
            if(generator.isOn()) {
                if(new Vector(jobTool.getX(), jobTool.getY(), jobTool.getZ()).distance(position) <= distance) {
                    return generator;
                }
            }
        }
        return null;
    }

    public static void handleGrowboxFillWaterPot(Player player, GrowboxFillWaterPotPayload payload) {
        JobTool jobTool = JobManager.getJobTools().stream().filter
                (x -> x.getUuid().equals(payload.getGrowboxId())).findFirst().orElse(null);
        if(jobTool == null) return;
        GrowBox growBox = (GrowBox)jobTool.getJobToolHandler();
        growBox.fillPotWater(player, payload.getPotId());
    }

    public static void handleGrowboxFillSeedPot(Player player, GrowboxFillWaterPotPayload payload) {
        JobTool jobTool = JobManager.getJobTools().stream().filter
                (x -> x.getUuid().equals(payload.getGrowboxId())).findFirst().orElse(null);
        if(jobTool == null) return;
        GrowBox growBox = (GrowBox)jobTool.getJobToolHandler();
        growBox.fillPotSeed(player, payload.getPotId());
    }

    public static void handleGrowboxHarvestRequest(Player player, GrowboxFillWaterPotPayload payload) {
        JobTool jobTool = JobManager.getJobTools().stream().filter
                (x -> x.getUuid().equals(payload.getGrowboxId())).findFirst().orElse(null);
        if(jobTool == null) return;
        GrowBox growBox = (GrowBox)jobTool.getJobToolHandler();
        growBox.harvestPot(player, payload.getPotId());
    }

    public static void handleGrowboxTakePotRequest(Player player, GrowboxFillWaterPotPayload payload) {
        JobTool jobTool = JobManager.getJobTools().stream().filter
                (x -> x.getUuid().equals(payload.getGrowboxId())).findFirst().orElse(null);
        if(jobTool == null) return;
        GrowBox growBox = (GrowBox)jobTool.getJobToolHandler();
        growBox.takePot(player, payload.getPotId());
    }

    public static void handleGrowboxDestroy(Player player, String growboxId) {
        JobTool jobTool = JobManager.getJobTools().stream().filter
                (x -> x.getUuid().equals(growboxId)).findFirst().orElse(null);
        if(jobTool == null) return;
        GrowBox growBox = (GrowBox)jobTool.getJobToolHandler();
        growBox.destroy(player);
    }
}
