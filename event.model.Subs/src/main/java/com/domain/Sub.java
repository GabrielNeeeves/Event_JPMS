package com.domain;

public record Sub(int subId, int eventId, String participantEmail) implements Comparable<Sub> {

    @Override
    public int compareTo(Sub s) {
        if(this.subId == s.subId) return 0;
        return this.subId > s.subId ? 1 : -1;
    }

}
