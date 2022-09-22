package com.hznu.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author LIN
 * @date 2022/9/6 9:19
 */

@Data
public class reqHead implements Serializable {
    private String reqId;
    private reqPub reqpub;
}
