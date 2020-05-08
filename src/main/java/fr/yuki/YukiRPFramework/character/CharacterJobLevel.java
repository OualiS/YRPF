package fr.yuki.YukiRPFramework.character;

import fr.yuki.YukiRPFramework.manager.JobManager;
import fr.yuki.YukiRPFramework.model.JobLevel;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class CharacterJobLevel {
    private String jobId;
    private int exp;

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public JobLevel getJobLevel() {
        ArrayList<JobLevel> levels = new ArrayList<>(JobManager.getJobLevels().stream()
                .filter(x -> x.getJobId().equals(this.getJobId())).collect(Collectors.toList()));
        JobLevel level = levels.get(0);
        for(JobLevel l : levels) {
            if(l.getLevel() > level.getLevel() && this.getExp() >= l.getExpFloor()) {
                level = l;
            }
        }
        return level;
    }
}
