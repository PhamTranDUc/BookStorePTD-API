package com.ShopComputerPTD.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
@MappedSuperclass
public class BaseEntity {

    @Column(name= "create_at")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createAt;

    @Column(name="update_at")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updateAt;

    @PrePersist
    public void onCreate(){
        this.createAt= LocalDateTime.now();
        this.updateAt= LocalDateTime.now();
    }

    @PreUpdate
    public void opUpdate(){
        this.updateAt= LocalDateTime.now();
    }

}
