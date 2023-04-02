package ru.kazmin.models;

import lombok.Getter;
import lombok.Setter;
import ru.kazmin.util.State;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Table(name = "bid")
public class Bid {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id")
        private User user;

        @Column
        @Enumerated(EnumType.STRING)
        private State state;

        @Column
        private String essence;

        @Column
        private LocalDateTime time;

}
