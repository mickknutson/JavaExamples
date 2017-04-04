package com.baselogic.tutorials.domain;

import com.baselogic.tutorials.domain.parachute.Parachute;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * Equipment Used
 */
public class Equipment extends AbstractEntity
        implements Serializable {
    String suit;
    String container;
    Parachute parachute;
    String deploymentMethod;

}

