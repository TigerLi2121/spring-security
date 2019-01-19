package com.mm.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author lwl
 */
@Builder(toBuilder = true)
@Data
public class FileInfo implements Serializable {

    private String path;

}
