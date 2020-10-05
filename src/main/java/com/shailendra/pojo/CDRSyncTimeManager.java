package com.shailendra.pojo;

import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "cdr_sync_tm")
public class CDRSyncTimeManager {

    @Id
    @GeneratedValue
    private int id;

    @CreationTimestamp
    private Date lastSyncTime;
}
