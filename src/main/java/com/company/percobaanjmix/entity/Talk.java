package com.company.percobaanjmix.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.DependsOnProperties;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import io.jmix.core.metamodel.annotation.JmixProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@JmixEntity
@Table(name = "TALK")
@Entity
public class Talk {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @InstanceName
    @Column(name = "TOPIC", nullable = false)
    @NotNull
    private String topic;

    @Column(name = "START_DATE", nullable = false)
    @NotNull
    private LocalDateTime startDate;

    @JoinColumn(name = "SPEAKER_ID", nullable = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private QuickStart speaker;

    @Column(name = "DURATION", nullable = false)
    @NotNull
    private Integer duration;

    @Column(name = "DESCRIPTION")
    @Lob
    private String description;

    @DependsOnProperties({"startDate", "duration"})
    @Transient
    @JmixProperty
    public LocalDateTime getEndDate() {

        return (startDate != null && duration != null) ? startDate.plusHours(duration) : null;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public QuickStart getSpeaker() {
        return speaker;
    }

    public void setSpeaker(QuickStart speaker) {
        this.speaker = speaker;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

}