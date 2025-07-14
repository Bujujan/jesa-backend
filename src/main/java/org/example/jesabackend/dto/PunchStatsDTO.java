package org.example.jesabackend.dto;

public class PunchStatsDTO {
    private long totalPunches;
    private long newThisMonth;
    private long resolved;
    private long pending;

    public PunchStatsDTO(long totalPunches, long newThisMonth, long resolved, long pending) {
        this.totalPunches = totalPunches;
        this.newThisMonth = newThisMonth;
        this.resolved = resolved;
        this.pending = pending;
    }

    public long getTotalPunches() {
        return totalPunches;
    }

    public long getNewThisMonth() {
        return newThisMonth;
    }

    public long getResolved() {
        return resolved;
    }

    public long getPending() {
        return pending;
    }
}